# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener el proyecto completo y arrancable. Guarda los archivos que te devuelva en la
carpeta del repo, respetando exactamente las rutas.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/pragma/order/adapters/dynamodb/DynamoDBOrderRepository.java` — La pieza p3 (Implementación de persistencia de pedidos en DynamoDB, fase f2) vive aqui: este archivo es su unico dueno (blueprint v2).

## Mapa de componentes

El repo tiene una carpeta por componente. Cada uno se instala y verifica dentro de la suya.

| Componente | Carpeta | Rol | Capa | Track | Tecnologias |
|---|---|---|---|---|---|
| `app` | (raiz) | practice, chapter | testing | Automatizador | - |
| `infra-local` | (raiz) | support | infra | Compose local | DynamoDB |

## Orden de arranque

1. `docker compose up -d` (en la raiz) — levanta la infraestructura local.
2. `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` — componente `app` (practice, chapter).

## Herramientas

- **Java**: Tecnologia pedida para este reto: forma parte del stack con el que se resuelve el ejercicio.
- **Java Selenium**: Tecnologia del perfil del candidato: el reto se resuelve con ella.

## Como saber que terminaste

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Calidad de Software, Especialidad Automatizador, Tecnología Java Selenium, Advanced L2

### Brecha de conocimiento
Automatizar pruebas de integracion sobre una tabla DynamoDB

### Misión / candidato
Cubrir el flujo de persistencia de pedidos

### Reto
- Tema: Automatizacion de pruebas de integracion contra DynamoDB
- Seniority: advanced-l2
- Tipo: practical
- Título: Automatización de Pruebas de Integración en DynamoDB
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración del Entorno de Pruebas — objetivo: Configurar el entorno de pruebas para interactuar con DynamoDB. — entregable (NO resolver): Proyecto configurado con las dependencias y conexión a DynamoDB, y un escenario de aceptación funcional.
- Fase 2: Automatización de Pruebas de Escritura — objetivo: Automatizar pruebas que validen la escritura de pedidos en DynamoDB. — entregable (NO resolver): Pruebas automatizadas que escriben y verifican la persistencia de pedidos en DynamoDB.
- Fase 3: Automatización de Pruebas de Lectura — objetivo: Automatizar pruebas que validen la lectura de pedidos desde DynamoDB. — entregable (NO resolver): Pruebas automatizadas que leen y verifican la correcta recuperación de pedidos desde DynamoDB.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (entrega de archivos).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Entrega de archivos
Entregá cada archivo con su ruta completa, respetando exactamente la estructura de rutas
indicada por los marcadores: un bloque por archivo, precedido por su ruta. No generes ZIPs
ni archivos adjuntos.
La entrega debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: Entregá los archivos completos al finalizar. No preguntes si el usuario
quiere recibirlos. Simplemente entregalos; No debes desplegar en el chat el resumen de lo que arreglaste, solo entregá los archivos.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del último archivo entregado
No preguntes si el usuario quiere los archivos: simplemente entregalos siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: src/test/java/com/pragma/order/runners/RunCucumberTest.java ===
package com.pragma.order.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.pragma.order.steps",
    plugin = {
        "pretty",
        "json:target/cucumber-reports/cucumber.json",
        "html:target/cucumber-reports/cucumber.html"
    },
    tags = "@regression"
)
public class RunCucumberTest {
}

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.13</version>
        <relativePath/>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>reto</artifactId>
    <version>1.0.0</version>
    <name>reto</name>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>7.34.9</version>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>7.34.9</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-core</artifactId>
            <version>4.3.4</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-cucumber</artifactId>
            <version>4.3.4</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay</artifactId>
            <version>4.3.4</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay-webdriver</artifactId>
            <version>4.3.4</version>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>localstack</artifactId>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>testcontainers</artifactId>
        </dependency>
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>dynamodb</artifactId>
            <version>2.59.2</version>
        </dependency>
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>dynamodb-enhanced</artifactId>
            <version>2.59.2</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay-rest</artifactId>
            <version>4.3.4</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/order/ports/OrderPersistencePort.java ===
package com.pragma.order.ports;

import com.pragma.order.models.Order;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

/**
 * Puerto que define el contrato para la persistencia de órdenes.
 * Las implementaciones de este puerto deben manejar la conexión con DynamoDB
 * y las operaciones de lectura/escritura de órdenes.
 */
public interface OrderPersistencePort {

    /**
     * Guarda una orden en DynamoDB.
     * @param order La orden a persistir.
     * @throws DynamoDbException Si ocurre un error durante la operación.
     */
    void saveOrder(Order order) throws DynamoDbException;

    /**
     * Recupera una orden por su ID.
     * @param orderId El ID de la orden a recuperar.
     * @return La orden recuperada, o null si no existe.
     * @throws DynamoDbException Si ocurre un error durante la operación.
     */
    Order findOrderById(String orderId) throws DynamoDbException;

    /**
     * Elimina una orden por su ID.
     * @param orderId El ID de la orden a eliminar.
     * @throws DynamoDbException Si ocurre un error durante la operación.
     */
    void deleteOrder(String orderId) throws DynamoDbException;
}

// === ARCHIVO: src/main/java/com/pragma/order/models/Order.java ===
package com.pragma.order.models;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * Modelo de dominio que representa una orden.
 * Contiene los atributos necesarios para persistir en DynamoDB.
 */
public class Order {
    private String orderId;
    private String customerId;
    private LocalDateTime orderDate;
    private String status;
    private Map<String, Integer> items;
    private double totalAmount;

    public Order() {
    }

    public Order(String orderId, String customerId, LocalDateTime orderDate, String status,
                 Map<String, Integer> items, double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    // Getters y setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.totalAmount, totalAmount) == 0 &&
                Objects.equals(orderId, order.orderId) &&
                Objects.equals(customerId, order.customerId) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(status, order.status) &&
                Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, orderDate, status, items, totalAmount);
    }
}

// === ARCHIVO: src/main/java/com/pragma/order/adapters/dynamodb/config/DynamoDBClientProvider.java ===
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

// === ARCHIVO: src/main/java/com/pragma/order/adapters/dynamodb/DynamoDBOrderRepository.java ===
// TODO(reto): archivo stubbeado; implementa los cuerpos.
package com.pragma.order.adapters.dynamodb;

import com.pragma.order.models.Order;
import com.pragma.order.ports.OrderPersistencePort;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DynamoDBOrderRepository implements OrderPersistencePort {

    private static final String TABLE_NAME = "orders";
    private final DynamoDbClient dynamoDBClient;

    public DynamoDBOrderRepository(DynamoDbClient dynamoDBClient) {
        this.dynamoDBClient = dynamoDBClient;
    }

    @Override
    public void saveOrder(Order order) throws DynamoDbException {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (order.getOrderId() == null || order.getOrderId().isBlank()) {
            throw new IllegalArgumentException("Order ID cannot be null or empty");
        }
        if (order.getCustomerId() == null || order.getCustomerId().isBlank()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty");
        }
        if (order.getStatus() == null || order.getStatus().isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        try {
            PutItemRequest request = PutItemRequest.builder()
                    .tableName(TABLE_NAME)
                    .item(toItem(order))
                    .build();
            dynamoDBClient.putItem(request);
        } catch (DynamoDbException e) {
            throw DynamoDbException.builder()
                    .message("Failed to persist order: " + order.getOrderId())
                    .cause(e)
                    .build();
        }
    }

    @Override
    public Order findOrderById(String orderId) throws DynamoDbException {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("Order ID cannot be null or empty");
        }

        try {
            GetItemRequest request = GetItemRequest.builder()
                    .tableName(TABLE_NAME)
                    .key(Map.of("orderId", AttributeValue.builder().s(orderId).build()))
                    .build();

            GetItemResponse response = dynamoDBClient.getItem(request);

            if (!response.hasItem() || response.item().isEmpty()) {
                throw ResourceNotFoundException.builder()
                        .message("Order not found with ID: " + orderId)
                        .build();
            }

            return fromItem(response.item());
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (DynamoDbException e) {
            throw DynamoDbException.builder()
                    .message("Failed to retrieve order: " + orderId)
                    .cause(e)
                    .build();
        }
    }

    @Override
    public void deleteOrder(String orderId) throws DynamoDbException {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("Order ID cannot be null or empty");
        }

        try {
            DeleteItemRequest request = DeleteItemRequest.builder()
                    .tableName(TABLE_NAME)
                    .key(Map.of("orderId", AttributeValue.builder().s(orderId).build()))
                    .build();
            dynamoDBClient.deleteItem(request);
        } catch (DynamoDbException e) {
            throw DynamoDbException.builder()
                    .message("Failed to delete order: " + orderId)
                    .cause(e)
                    .build();
        }
    }

    public Map<String, AttributeValue> toItem(Order order) {
      throw new UnsupportedOperationException("TODO(reto): toItem");
    }

    public Order fromItem(Map<String, AttributeValue> item) {
      throw new UnsupportedOperationException("TODO(reto): fromItem");
    }
}

// === ARCHIVO: src/test/java/com/pragma/order/features/WriteOrderScenario.java ===
package com.pragma.order.features;

import com.pragma.order.adapters.dynamodb.DynamoDBOrderRepository;
import com.pragma.order.models.Order;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.LocalDateTime;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WriteOrderScenario {

    @Mock
    private DynamoDbClient dynamoDBClient;

    private DynamoDBOrderRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        OnStage.setTheStage(new OnlineCast());
        repository = new DynamoDBOrderRepository(dynamoDBClient);
    }

    @Test
    void should_persist_order_in_dynamodb() {
        Order order = new Order(
                "ORD-001",
                "CUST-001",
                LocalDateTime.now(),
                "PENDING",
                Map.of("ITEM-001", 2, "ITEM-002", 1),
                150.00
        );

        when(dynamoDBClient.putItem(any(PutItemRequest.class)))
                .thenReturn(PutItemResponse.builder().build());

        repository.saveOrder(order);

        verify(dynamoDBClient, times(1)).putItem(argThat((PutItemRequest request) -> 
                "orders".equals(request.tableName()) &&
                "ORD-001".equals(request.item().get("orderId").s()) &&
                "CUST-001".equals(request.item().get("customerId").s()) &&
                "PENDING".equals(request.item().get("status").s())
        ));
    }

    @Test
    void should_fail_when_dynamodb_is_unavailable() {
        Order order = new Order(
                "ORD-002",
                "CUST-002",
                LocalDateTime.now(),
                "PENDING",
                Map.of("ITEM-001", 1),
                75.00
        );

        when(dynamoDBClient.putItem(any(PutItemRequest.class)))
                .thenThrow(DynamoDbException.builder()
                        .message("Connection refused")
                        .build());

        try {
            repository.saveOrder(order);
        } catch (DynamoDbException e) {
            verify(dynamoDBClient, times(1)).putItem(any(PutItemRequest.class));
            return;
        }
        throw new AssertionError("Expected DynamoDbException to be thrown");
    }

    @Test
    void should_fail_when_order_is_invalid() {
        Order orderWithNullId = new Order(
                null,
                "CUST-003",
                LocalDateTime.now(),
                "PENDING",
                Map.of("ITEM-001", 1),
                50.00
        );

        try {
            repository.saveOrder(orderWithNullId);
        } catch (IllegalArgumentException e) {
            verify(dynamoDBClient, never()).putItem(any(PutItemRequest.class));
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException to be thrown");
    }

    @Test
    void should_fail_when_customer_id_is_blank() {
        Order orderWithBlankCustomer = new Order(
                "ORD-004",
                "",
                LocalDateTime.now(),
                "PENDING",
                Map.of("ITEM-001", 1),
                50.00
        );

        try {
            repository.saveOrder(orderWithBlankCustomer);
        } catch (IllegalArgumentException e) {
            verify(dynamoDBClient, never()).putItem(any(PutItemRequest.class));
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException to be thrown");
    }

    @Test
    void should_fail_when_status_is_null() {
        Order orderWithNullStatus = new Order(
                "ORD-005",
                "CUST-005",
                LocalDateTime.now(),
                null,
                Map.of("ITEM-001", 1),
                50.00
        );

        try {
            repository.saveOrder(orderWithNullStatus);
        } catch (IllegalArgumentException e) {
            verify(dynamoDBClient, never()).putItem(any(PutItemRequest.class));
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException to be thrown");
    }

    @Test
    void should_fail_when_order_is_null() {
        try {
            repository.saveOrder(null);
        } catch (IllegalArgumentException e) {
            verify(dynamoDBClient, never()).putItem(any(PutItemRequest.class));
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException to be thrown");
    }
}

// === ARCHIVO: src/test/java/com/pragma/order/features/ReadOrderScenario.java ===
package com.pragma.order.features;

import com.pragma.order.adapters.dynamodb.DynamoDBOrderRepository;
import com.pragma.order.models.Order;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReadOrderScenario {

    @Mock
    private DynamoDbClient dynamoDBClient;

    private DynamoDBOrderRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        OnStage.setTheStage(new OnlineCast());
        repository = new DynamoDBOrderRepository(dynamoDBClient);
    }

    @Test
    void should_retrieve_order_from_dynamodb() {
        String orderId = "ORD-001";
        
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("orderId", AttributeValue.builder().s(orderId).build());
        item.put("customerId", AttributeValue.builder().s("CUST-001").build());
        item.put("orderDate", AttributeValue.builder().s("2024-01-15T10:30:00").build());
        item.put("status", AttributeValue.builder().s("PENDING").build());
        
        Map<String, AttributeValue> itemsMap = new HashMap<>();
        itemsMap.put("ITEM-001", AttributeValue.builder().n("2").build());
        item.put("items", AttributeValue.builder().m(itemsMap).build());
        
        item.put("totalAmount", AttributeValue.builder().n("150.00").build());

        GetItemResponse response = GetItemResponse.builder()
                .item(item)
                .build();

        when(dynamoDBClient.getItem(any(GetItemRequest.class)))
                .thenReturn(response);

        Order result = repository.findOrderById(orderId);

        assertThat(result, is(notNullValue()));
        assertThat(result.getOrderId(), equalTo(orderId));
        assertThat(result.getCustomerId(), equalTo("CUST-001"));
        assertThat(result.getStatus(), equalTo("PENDING"));
        assertThat(result.getTotalAmount(), equalTo(150.00));
        
        verify(dynamoDBClient, times(1)).getItem(any(GetItemRequest.class));
    }

    @Test
    void should_fail_when_order_does_not_exist() {
        String orderId = "NON-EXISTENT";
        
        GetItemResponse response = GetItemResponse.builder()
                .item(new HashMap<>())
                .build();

        when(dynamoDBClient.getItem(any(GetItemRequest.class)))
                .thenReturn(response);

        try {
            repository.findOrderById(orderId);
        } catch (ResourceNotFoundException e) {
            verify(dynamoDBClient, times(1)).getItem(any(GetItemRequest.class));
            return;
        }
        throw new AssertionError("Expected ResourceNotFoundException to be thrown");
    }

    @Test
    void should_fail_when_dynamodb_is_unavailable() {
        String orderId = "ORD-002";

        when(dynamoDBClient.getItem(any(GetItemRequest.class)))
                .thenThrow(DynamoDbException.builder()
                        .message("Service unavailable")
                        .build());

        try {
            repository.findOrderById(orderId);
        } catch (DynamoDbException e) {
            verify(dynamoDBClient, times(1)).getItem(any(GetItemRequest.class));
            return;
        }
        throw new AssertionError("Expected DynamoDbException to be thrown");
    }

    @Test
    void should_fail_when_order_id_is_blank() {
        try {
            repository.findOrderById("");
        } catch (IllegalArgumentException e) {
            verify(dynamoDBClient, never()).getItem(any(GetItemRequest.class));
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException to be thrown");
    }

    @Test
    void should_fail_when_order_id_is_null() {
        try {
            repository.findOrderById(null);
        } catch (IllegalArgumentException e) {
            verify(dynamoDBClient, never()).getItem(any(GetItemRequest.class));
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException to be thrown");
    }

    @Test
    void should_handle_order_with_null_fields() {
        String orderId = "ORD-003";
        
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("orderId", AttributeValue.builder().s(orderId).build());
        item.put("customerId", AttributeValue.builder().s("CUST-003").build());
        item.put("orderDate", AttributeValue.builder().s("2024-01-15T10:30:00").build());
        item.put("status", AttributeValue.builder().s("PENDING").build());
        item.put("items", AttributeValue.builder().m(new HashMap<>()).build());
        item.put("totalAmount", AttributeValue.builder().n("0.0").build());

        GetItemResponse response = GetItemResponse.builder()
                .item(item)
                .build();

        when(dynamoDBClient.getItem(any(GetItemRequest.class)))
                .thenReturn(response);

        Order result = repository.findOrderById(orderId);

        assertThat(result, is(notNullValue()));
        assertThat(result.getOrderId(), equalTo(orderId));
        assertThat(result.getItems(), is(notNullValue()));
        assertThat(result.getTotalAmount(), equalTo(0.0));
    }
}

// === ARCHIVO: src/main/java/XSteps.java ===
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

// === ARCHIVO: src/test/java/com/pragma/order/tasks/DynamoDBLocalSetup.java ===
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

// === ARCHIVO: docker-compose.yml ===
# Infraestructura local del reto. SOLO desarrollo: credenciales de ejemplo (local-dev-only), puertos en loopback.
# Levantar: docker compose up -d --wait
name: infra-local
services:
  dynamodb-local:
    image: "amazon/dynamodb-local:2.5.2"
    command: ["-jar","DynamoDBLocal.jar","-sharedDb","-inMemory"]
    ports:
      - "127.0.0.1:8000:8000"
    healthcheck:
      test: ["CMD-SHELL","bash -c ':> /dev/tcp/127.0.0.1/8000' || exit 1"]
      interval: 10s
      timeout: 5s
      retries: 10
      start_period: 5s


// === ARCHIVO: infra-local/README.md ===
# Infraestructura local

Servicios de soporte para el reto, definidos en `docker-compose.yml` (raiz del repo).

```bash
docker compose up -d --wait
```

| Servicio | Imagen | Puertos (host) |
|---|---|---|
| DynamoDB Local | `amazon/dynamodb-local:2.5.2` | 8000 |

Credenciales: solo de desarrollo local (`local-dev-only`, `local`). No son secretos ni deben reutilizarse.
Los scripts de `infra-local/` (tablas, topics) se ejecutan con el compose en estado healthy.

Tablas de DynamoDB: `Orders` (pk `orderId`), `orders` (pk `orderId`).


// === ARCHIVO: infra-local/dynamodb/create-tables.sh ===
#!/usr/bin/env bash
# Crea las tablas en DynamoDB Local (idempotente). Credenciales dummy: DynamoDB Local no valida firmas.
set -euo pipefail

export AWS_ACCESS_KEY_ID=local
export AWS_SECRET_ACCESS_KEY=local
export AWS_REGION=us-east-1
ENDPOINT="${DYNAMODB_ENDPOINT:-http://localhost:8000}"

create_table() {
  local name="$1" pk="$2" sk="${3:-}"
  if aws dynamodb describe-table --table-name "$name" --endpoint-url "$ENDPOINT" >/dev/null 2>&1; then
    echo "Tabla $name ya existe"
    return
  fi
  local attrs="AttributeName=$pk,AttributeType=S"
  local keys="AttributeName=$pk,KeyType=HASH"
  if [ -n "$sk" ]; then
    attrs="$attrs AttributeName=$sk,AttributeType=S"
    keys="$keys AttributeName=$sk,KeyType=RANGE"
  fi
  # shellcheck disable=SC2086
  aws dynamodb create-table --table-name "$name" --attribute-definitions $attrs --key-schema $keys \
    --billing-mode PAY_PER_REQUEST --endpoint-url "$ENDPOINT" >/dev/null
  echo "Tabla $name creada"
}

create_table "Orders" "orderId"
create_table "orders" "orderId"

```
