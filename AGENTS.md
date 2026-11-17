# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Automatización de Pruebas de Integración en DynamoDB**.

| | |
|---|---|
| Tema | Automatizacion de pruebas de integracion contra DynamoDB |
| Nivel | advanced-l2 |
| Chapter | Calidad de Software |
| Especialidad | Automatizador |
| Stack | Java / Serenity BDD 4 + Screenplay |
| Patron arquitectonico | Screenplay con patrones de automatizacion (Page Object Model evolucionado a Screenplay) |
| Tiempo estimado | 8 horas |

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

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz con el runner y el plugin de reportes`
- `src/test/resources/features con los .feature en Gherkin`
- `src/test/java/.../runners con el runner`
- `src/test/java/.../pages o /tasks con Page Objects o Screenplay`
- `src/test/java/.../steps con los step definitions`
- `serenity.conf o config del entorno`

Trampas conocidas:

- Sin parent POM que gestione versiones, TODA dependencia lleva su `<version>` completa de tres segmentos.
- El groupId de Serenity es `net.serenity-bdd`, NO `org.serenity-bdd`. Con el groupId equivocado el artefacto no existe y el build muere resolviendo dependencias.
- Coordenadas exactas de lo mas usado: Selenium `org.seleniumhq.selenium:selenium-java`, Rest Assured `io.rest-assured:rest-assured`, Karate `com.intuit.karate:karate-junit5`, Cucumber `io.cucumber:cucumber-java`.
- JUnit 5 se declara con `junit-jupiter` (agregador) y necesita `maven-surefire-plugin` reciente para ejecutarse.
- Serenity y Cucumber tienen que ser de lineas compatibles entre si; mezclarlas rompe el runner.

Dependencias:

- org.springframework.boot:spring-boot-starter-web
- org.springframework.boot:spring-boot-starter-test
- io.cucumber:cucumber-java 7.34.9
- io.cucumber:cucumber-junit 7.34.9
- net.serenity-bdd:serenity-core 4.3.4
- net.serenity-bdd:serenity-cucumber 4.3.4
- net.serenity-bdd:serenity-screenplay 4.3.4
- net.serenity-bdd:serenity-screenplay-webdriver 4.3.4
- org.junit.jupiter:junit-jupiter
- org.testcontainers:localstack
- org.testcontainers:testcontainers
- software.amazon.awssdk:dynamodb 2.59.2
- software.amazon.awssdk:dynamodb-enhanced 2.59.2
- net.serenity-bdd:serenity-screenplay-rest 4.3.4

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Segui el orden de arranque del mapa de componentes (infraestructura local primero) y corre `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q` desde la raiz: mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Configuración del Entorno de Pruebas**: Proyecto configurado con las dependencias y conexión a DynamoDB, y un escenario de aceptación funcional.
- **Fase 2 — Automatización de Pruebas de Escritura**: Pruebas automatizadas que escriben y verifican la persistencia de pedidos en DynamoDB.
- **Fase 3 — Automatización de Pruebas de Lectura**: Pruebas automatizadas que leen y verifican la correcta recuperación de pedidos desde DynamoDB.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/pragma/order/adapters/dynamodb/DynamoDBOrderRepository.java` — La pieza p3 (Implementación de persistencia de pedidos en DynamoDB, fase f2) vive aqui: este archivo es su unico dueno (blueprint v2).

## Mapa de fases

La **Fase 0** es clonar el repositorio, instalar dependencias y correr las pruebas de aceptacion: todas fallan porque la logica de cada pieza esta pendiente.

| Fase | Pieza (skill) | Archivo:simbolo | Criterios | Prueba | Comando |
|------|---------------|-----------------|-----------|--------|---------|
| f1 — Configuración del Entorno de Pruebas | Configuración de entorno de pruebas con DynamoDB local | — | El proyecto tiene las dependencias necesarias para interactuar con DynamoDB y Serenity BDD.; La conexión a DynamoDB local está configurada y es verificable mediante un escenario de aceptación. | — | — |
| f1 — Configuración del Entorno de Pruebas | Definición de puerto para persistencia de pedidos | — | El proyecto tiene las dependencias necesarias para interactuar con DynamoDB y Serenity BDD.; La conexión a DynamoDB local está configurada y es verificable mediante un escenario de aceptación. | — | — |
| f2 — Automatización de Pruebas de Escritura | Implementación de persistencia de pedidos en DynamoDB | `src/main/java/com/pragma/order/adapters/dynamodb/DynamoDBOrderRepository.java:DynamoDBOrderRepository.save` | Las pruebas automatizadas escriben un pedido en DynamoDB y verifican su persistencia.; Se manejan casos de error como escritura fallida o datos inválidos. | — | — |
| f2 — Automatización de Pruebas de Escritura | Automatización de pruebas de escritura con Screenplay | — | Las pruebas automatizadas escriben un pedido en DynamoDB y verifican su persistencia.; Se manejan casos de error como escritura fallida o datos inválidos. | — | — |
| f3 — Automatización de Pruebas de Lectura | Implementación de recuperación de pedidos desde DynamoDB | `src/main/java/com/pragma/order/adapters/dynamodb/DynamoDBOrderRepository.java:DynamoDBOrderRepository.findById` | Las pruebas automatizadas leen un pedido desde DynamoDB y verifican su contenido.; Se manejan casos de error como lectura fallida o pedido inexistente. | — | — |
| f3 — Automatización de Pruebas de Lectura | Automatización de pruebas de lectura con Screenplay | — | Las pruebas automatizadas leen un pedido desde DynamoDB y verifican su contenido.; Se manejan casos de error como lectura fallida o pedido inexistente. | — | — |

## Pruebas de aceptacion y ubicacion de la logica

Las pruebas de aceptacion (columna "Prueba" de la tabla) son de **solo lectura**: definen que significa "listo". No las edites, no las borres y no les agregues `skip`; lo que se resuelve es el codigo de cada pieza.

La logica de cada pieza va en su archivo; no la muevas a otro.

## Restricciones del pedido

| Restriccion | Donde se cumple | Estado |
|-------------|-----------------|--------|
| Usar Serenity BDD 4 con el patron Screenplay (dependencia serenity en el manifiesto) | chequeo estatico (manifest): exige `serenity-core`, `serenity-screenplay` | Verificada |
| No usar Thread.sleep; las esperas deben ser explicitas | chequeo estatico (code): prohibe `Thread.sleep` | Verificada |
| Un escenario de aceptacion verifica el item persistido en DynamoDB | sin ubicar | Restriccion no verificada en sin ubicar |

## Lo que falta y tenes que completar

No se detectaron huecos: estan los archivos declarados, el boilerplate del stack y ninguna referencia quedo colgando. Igual corre el comando de verificacion — que los archivos existan no garantiza que compilen.

### Presentes (13)

- `src/test/java/com/pragma/order/runners/RunCucumberTest.java`
- `pom.xml`
- `src/main/java/com/pragma/order/ports/OrderPersistencePort.java`
- `src/main/java/com/pragma/order/models/Order.java`
- `src/main/java/com/pragma/order/adapters/dynamodb/config/DynamoDBClientProvider.java`
- `src/main/java/com/pragma/order/adapters/dynamodb/DynamoDBOrderRepository.java`
- `src/test/java/com/pragma/order/features/WriteOrderScenario.java`
- `src/test/java/com/pragma/order/features/ReadOrderScenario.java`
- `src/main/java/XSteps.java`
- `src/test/java/com/pragma/order/tasks/DynamoDBLocalSetup.java`
- `docker-compose.yml`
- `infra-local/README.md`
- `infra-local/dynamodb/create-tables.sh`

### Capas del patron declarado

Son las capas que la propuesta arquitectonica plantea, marcadas segun existan hoy en el repo. Orientan donde poner el codigo nuevo; no son una condicion para compilar.

- `src/main/java/com/pragma/order` (presente)
- `src/main/java/com/pragma/order/adapters/dynamodb` (presente)
- `src/main/java/com/pragma/order/ports` (presente)
- `src/main/java/com/pragma/order/tasks` (ausente)
- `src/main/java/com/pragma/order/models` (presente)
- `src/test/java/com/pragma/order/features` (presente)
- `src/test/java/com/pragma/order/tasks` (presente)
- `src/test/java/com/pragma/order/questions` (ausente)
- `src/test/resources/features` (ausente)

## Verificacion

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- Cada componente vive en su carpeta: instala y compila dentro de ella, y no muevas archivos entre componentes.
- Respeta los contratos entre componentes (endpoints, topics) tal como estan declarados en el mapa de componentes.
- El patron es **Screenplay con patrones de automatizacion (Page Object Model evolucionado a Screenplay)**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Calidad de Software, Especialidad Automatizador, Tecnología Java Selenium, Advanced L2
- Brecha que el reto ataca: Automatizar pruebas de integracion sobre una tabla DynamoDB
- Mision: Cubrir el flujo de persistencia de pedidos

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
