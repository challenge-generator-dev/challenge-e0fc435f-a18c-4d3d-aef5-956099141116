package com.pragma.order.tasks;

import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Configuración de DynamoDB Local para pruebas de integración.
 * Utiliza Testcontainers con LocalStack para proporcionar un entorno aislado
 * de DynamoDB sin necesidad de infraestructura externa.
 */
public class DynamoDBLocalSetup {

    private static LocalStackContainer localStackContainer;
    private static DynamoDbClient dynamoDBClient;
    private static boolean tablaInicializada = false;
    private static final String TABLA_PEDIDOS = "Orders";

    /**
     * Inicializa el cliente de DynamoDB apuntando al contenedor LocalStack.
     * @return Cliente de DynamoDB configurado para pruebas locales.
     */
    public static synchronized DynamoDbClient getDynamoDBClient() {
        if (dynamoDBClient == null) {
            if (localStackContainer == null) {
                localStackContainer = new LocalStackContainer("localstack/localstack:4.0.3")
                        .withServices(LocalStackContainer.Service.DYNAMODB)
                        .withExposedPorts(4566);
            }
            
            localStackContainer.start();
            
            String endpoint = String.format("http://%s:%d",
                    localStackContainer.getHost(),
                    localStackContainer.getMappedPort(4566));
            
            dynamoDBClient = DynamoDbClient.builder()
                    .endpointOverride(URI.create(endpoint))
                    .region(Region.US_EAST_1)
                    .credentialsProvider(StaticCredentialsProvider.create(
                            AwsBasicCredentials.create("test", "test")))
                    .build();
        }
        return dynamoDBClient;
    }

    /**
     * Inicializa la tabla de pedidos en DynamoDB local si no existe.
     * Define la clave de partición como orderId.
     */
    public static synchronized void inicializarTablaPedidos() {
        if (tablaInicializada) {
            return;
        }

        DynamoDbClient client = getDynamoDBClient();

        try {
            DescribeTableRequest describeRequest = DescribeTableRequest.builder()
                    .tableName(TABLA_PEDIDOS)
                    .build();
            
            client.describeTable(describeRequest);
            tablaInicializada = true;
            return;
        } catch (ResourceNotFoundException e) {
            // La tabla no existe, procedemos a crearla
        }

        List<AttributeDefinition> attributeDefinitions = new ArrayList<>();
        attributeDefinitions.add(AttributeDefinition.builder()
                .attributeName("orderId")
                .attributeType("S")
                .build());

        KeySchemaElement keySchema = KeySchemaElement.builder()
                .attributeName("orderId")
                .keyType(KeyType.HASH)
                .build();

        CreateTableRequest createTableRequest = CreateTableRequest.builder()
                .tableName(TABLA_PEDIDOS)
                .keySchema(keySchema)
                .attributeDefinitions(attributeDefinitions)
                .billingMode(BillingMode.PAY_PER_REQUEST)
                .build();

        client.createTable(createTableRequest);
        
        // Esperar a que la tabla esté activa
        waitForTableActive(client, TABLA_PEDIDOS);
        
        tablaInicializada = true;
    }

    /**
     * Espera hasta que la tabla esté en estado ACTIVE.
     * @param client Cliente de DynamoDB
     * @param tableName Nombre de la tabla a verificar
     */
    private static void waitForTableActive(DynamoDbClient client, String tableName) {
        int maxIntentos = 30;
        int intentoActual = 0;
        
        while (intentoActual < maxIntentos) {
            try {
                DescribeTableResponse response = client.describeTable(
                        DescribeTableRequest.builder().tableName(tableName).build());
                
                if (TableStatus.ACTIVE.name().equals(response.table().tableStatusAsString())) {
                    return;
                }
            } catch (Exception e) {
                // Continuar esperando
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting for table", ie);
            }
            
            intentoActual++;
        }
        
        throw new RuntimeException("La tabla " + tableName + " no pudo ser creada en el tiempo esperado");
    }

    /**
     * Limpia todos los registros de la tabla de pedidos.
     * Útil para ejecutar pruebas en un estado limpio.
     */
    public static void limpiarTablaPedidos() {
        DynamoDbClient client = getDynamoDBClient();
        
        try {
            ScanResponse scanResponse = client.scan(ScanRequest.builder()
                    .tableName(TABLA_PEDIDOS)
                    .build());
            
            for (Map<String, AttributeValue> item : scanResponse.items()) {
                String orderId = item.get("orderId").s();
                
                client.deleteItem(DeleteItemRequest.builder()
                        .tableName(TABLA_PEDIDOS)
                        .key(Map.of("orderId", AttributeValue.builder().s(orderId).build()))
                        .build());
            }
        } catch (Exception e) {
            // Si la tabla no existe o está vacía, no hacemos nada
        }
    }

    /**
     * Obtiene el endpoint de DynamoDB local para configuración de clientes.
     * @return URL del endpoint de DynamoDB local.
     */
    public static String getDynamoDBEndpoint() {
        return String.format("http://%s:%d",
                localStackContainer.getHost(),
                localStackContainer.getMappedPort(4566));
    }

    /**
     * Obtiene las credenciales estáticas para LocalStack.
     * @return Credenciales básicas de AWS para pruebas.
     */
    public static StaticCredentialsProvider getStaticCredentials() {
        return StaticCredentialsProvider.create(
                AwsBasicCredentials.create("test", "test"));
    }

    /**
     * Obtiene la región configurada para las pruebas.
     * @return Región de AWS para pruebas.
     */
    public static Region getAwsRegion() {
        return Region.US_EAST_1;
    }

    /**
     * Detiene el contenedor de LocalStack.
     * Generalmente no es necesario llamarlo manualmente ya que Testcontainers
     * maneja el ciclo de vida automáticamente.
     */
    public static synchronized void cleanup() {
        if (localStackContainer != null && localStackContainer.isRunning()) {
            localStackContainer.stop();
        }
        dynamoDBClient = null;
        localStackContainer = null;
        tablaInicializada = false;
    }
}