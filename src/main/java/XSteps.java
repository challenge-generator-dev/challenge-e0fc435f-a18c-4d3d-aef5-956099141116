package com.pragma.order.steps;

import com.pragma.order.adapters.dynamodb.DynamoDBOrderRepository;
import com.pragma.order.models.Order;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class XSteps {

    private DynamoDBOrderRepository orderRepository;
    private Order ordenActual;
    private Order ordenRecuperada;
    private String orderIdGenerado;
    private RuntimeException errorCapturado;

    @Dado("que el actor tiene acceso al repositorio de pedidos en DynamoDB")
    public void queElActorTieneAccesoAlRepositorioDePedidosEnDynamoDB() {
        DynamoDbClient dynamoDBClient = DynamoDbClient.builder()
                .endpointOverride(java.net.URI.create("http://localhost:4566"))
                .region(software.amazon.awssdk.regions.Region.US_EAST_1)
                .credentialsProvider(() -> software.amazon.awssdk.auth.credentials.AwsBasicCredentials.create(
                        "test", "test"))
                .build();
        
        orderRepository = new DynamoDBOrderRepository(dynamoDBClient);
    }

    @Cuando("el actor guarda un pedido con los siguientes datos:")
    public void elActorGuardaUnPedidoConLosSiguientesDatos(Map<String, String> datosPedido) {
        orderIdGenerado = UUID.randomUUID().toString();
        
        Map<String, Integer> items = crearItemsDesdeDatos(datosPedido.get("item"), datosPedido.get("cantidad"));
        
        ordenActual = new Order(
                orderIdGenerado,
                datosPedido.get("customerId"),
                LocalDateTime.now(),
                datosPedido.get("status"),
                items,
                Double.parseDouble(datosPedido.get("totalAmount"))
        );
        
        try {
            orderRepository.saveOrder(ordenActual);
        } catch (DynamoDbException e) {
            errorCapturado = e;
        }
    }

    private Map<String, Integer> crearItemsDesdeDatos(String item, String cantidad) {
        Map<String, Integer> items = new HashMap<>();
        if (item != null && cantidad != null) {
            items.put(item, Integer.parseInt(cantidad));
        }
        return items;
    }

    @Entonces("el pedido debe persistirse correctamente en DynamoDB")
    public void elPedidoDebePersistirseCorrectamenteEnDynamoDB() {
        if (errorCapturado != null) {
            throw new AssertionError("No debe haber error al guardar el pedido", errorCapturado);
        }
        
        if (ordenActual.getOrderId() == null) {
            throw new AssertionError("El ID del pedido debe estar generado");
        }
    }

    @Y("el sistema debe poder recuperar el pedido guardado")
    public void elSistemaDebePoderRecuperarElPedidoGuardado() {
        try {
            ordenRecuperada = orderRepository.findOrderById(orderIdGenerado);
        } catch (DynamoDbException e) {
            errorCapturado = e;
        }
        
        if (errorCapturado != null) {
            throw new AssertionError("No debe haber error al recuperar el pedido", errorCapturado);
        }
        
        if (ordenRecuperada == null) {
            throw new AssertionError("El pedido debe existir en DynamoDB");
        }
        
        if (!orderIdGenerado.equals(ordenRecuperada.getOrderId())) {
            throw new AssertionError("El ID del pedido recuperado debe coincidir");
        }
        
        if (!ordenActual.getCustomerId().equals(ordenRecuperada.getCustomerId())) {
            throw new AssertionError("El customerId debe coincidir");
        }
        
        if (!ordenActual.getStatus().equals(ordenRecuperada.getStatus())) {
            throw new AssertionError("El status debe coincidir");
        }
    }

    @Y("los items del pedido deben verificarse en DynamoDB")
    public void losItemsDelPedidoDebenVerificarseEnDynamoDB() {
        if (ordenRecuperada.getItems() == null) {
            throw new AssertionError("Los items deben existir en el pedido recuperado");
        }
        
        if (!ordenActual.getItems().equals(ordenRecuperada.getItems())) {
            throw new AssertionError("Los items del pedido recuperado deben coincidir con los guardados");
        }
    }

    @Cuando("el actor intenta guardar un pedido con datos inválidos")
    public void elActorIntentaGuardarUnPedidoConDatosInválidos() {
        ordenActual = new Order(
                null,
                null,
                LocalDateTime.now(),
                "PENDING",
                Map.of(),
                0.0
        );
        
        try {
            orderRepository.saveOrder(ordenActual);
        } catch (DynamoDbException e) {
            errorCapturado = e;
        }
    }

    @Entonces("el sistema debe lanzar una excepción de DynamoDB")
    public void elSistemaDebeLanzarUnaExcepciónDeDynamoDB() {
        if (!(errorCapturado instanceof DynamoDbException)) {
            throw new AssertionError("Debe capturar una excepción de DynamoDB");
        }
    }

    @Dado("que existe un pedido guardado en DynamoDB")
    public void queExisteUnPedidoGuardadoEnDynamoDB() {
        orderIdGenerado = UUID.randomUUID().toString();
        
        Map<String, Integer> items = crearItemsDesdeDatos("Laptop", "1");
        
        ordenActual = new Order(
                orderIdGenerado,
                "CUST-001",
                LocalDateTime.now(),
                "COMPLETED",
                items,
                1500.00
        );
        
        orderRepository.saveOrder(ordenActual);
    }

    @Cuando("el actor recupera el pedido por su ID")
    public void elActorRecuperaElPedidoPorSuID() {
        try {
            ordenRecuperada = orderRepository.findOrderById(orderIdGenerado);
        } catch (DynamoDbException e) {
            errorCapturado = e;
        }
    }

    @Entonces("el sistema debe retornar los datos completos del pedido")
    public void elSistemaDebeRetornarLosDatosCompletosDelPedido() {
        if (ordenRecuperada == null) {
            throw new AssertionError("El pedido debe ser recuperado correctamente");
        }
        
        if (!orderIdGenerado.equals(ordenRecuperada.getOrderId())) {
            throw new AssertionError("El orderId debe coincidir");
        }
        
        if (!ordenActual.getCustomerId().equals(ordenRecuperada.getCustomerId())) {
            throw new AssertionError("El customerId debe coincidir");
        }
        
        if (!ordenActual.getStatus().equals(ordenRecuperada.getStatus())) {
            throw new AssertionError("El status debe coincidir");
        }
        
        if (!ordenActual.getItems().equals(ordenRecuperada.getItems())) {
            throw new AssertionError("Los items deben coincidir");
        }
    }
}