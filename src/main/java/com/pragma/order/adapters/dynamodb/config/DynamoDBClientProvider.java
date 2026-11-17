package com.pragma.order.adapters.dynamodb.config;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import java.net.URI;

/**
 * Proveedor de la instancia configurada de DynamoDBClient.
 * Configura la conexión con DynamoDB local usando Testcontainers para pruebas.
 */
public class DynamoDBClientProvider {

    private static final String LOCALSTACK_ENDPOINT = "http://localhost:4566";
    private static final String AWS_ACCESS_KEY = "test";
    private static final String AWS_SECRET_KEY = "test";
    private static final Region AWS_REGION = Region.US_EAST_1;

    /**
     * Crea y configura una instancia de DynamoDBClient para pruebas locales.
     * @return Instancia configurada de DynamoDbClient.
     */
    public static DynamoDbClient createLocalDynamoDBClient() {
        return DynamoDbClient.builder()
                .endpointOverride(URI.create(LOCALSTACK_ENDPOINT))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(AWS_ACCESS_KEY, AWS_SECRET_KEY)))
                .region(AWS_REGION)
                .build();
    }
}