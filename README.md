# Automatización de Pruebas de Integración en DynamoDB

El sistema de gestión de pedidos utiliza una tabla DynamoDB para almacenar información de pedidos. Automatiza pruebas de integración que validen la lectura y escritura de pedidos en esta tabla, cubriendo el flujo completo de persistencia.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Automatizacion de pruebas de integracion contra DynamoDB |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Tener el proyecto base del reto funcionando en tu máquina: clonar el repositorio, instalar las herramientas y verificar que compila y arranca antes de empezar a practicar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Instala las herramientas para ejecutar el proyecto: JDK 17+ y Maven 3.9+.
- Clona el repositorio del reto (el enlace viene en la entrega del reto y en la sección "Repositorio del Proyecto" del PDF) y ábrelo en tu IDE.
- Instala las dependencias y compila ejecutando `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q` en la raíz del repositorio.
- Confirma que el comando termina sin errores: el proyecto arranca y sus pruebas corren.
- Ubica los archivos de práctica: son los que contienen `TODO(reto)` (búscalos en el repositorio). Lee el README.md y el AGENTS.md antes de empezar.

**Entregable:** El proyecto compila/arranca sin errores con `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q`.

<details>
<summary>Pistas de conocimiento</summary>

- Los archivos con `TODO(reto)` son el ejercicio: el resto del repositorio ya está resuelto y no hace falta tocarlo.
- Si el comando de verificación falla, revisa la versión de las herramientas instaladas antes de modificar código.
- AGENTS.md describe el reto, el comando de verificación y las reglas del repositorio.

</details>

### Fase 1: Configuración del Entorno de Pruebas

**Objetivo:** Configurar el entorno de pruebas para interactuar con DynamoDB.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configura el proyecto con las dependencias necesarias para usar Serenity BDD 4 y el patrón Screenplay.
- Establece una conexión con una tabla DynamoDB local o en la nube para las pruebas.
- Crea un escenario de aceptación que verifique la persistencia de un item en DynamoDB sin usar Thread.sleep; las esperas deben ser explícitas.

**Entregable:** Proyecto configurado con las dependencias y conexión a DynamoDB, y un escenario de aceptación funcional.

<details>
<summary>Pistas de conocimiento</summary>

- Configuración de Serenity BDD 4 y el patrón Screenplay.
- Uso de esperas explícitas en lugar de Thread.sleep.

</details>

### Fase 2: Automatización de Pruebas de Escritura

**Objetivo:** Automatizar pruebas que validen la escritura de pedidos en DynamoDB.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Crea pruebas automatizadas que escriban un pedido en DynamoDB y verifiquen que se ha persistido correctamente.
- Asegúrate de que las pruebas manejen posibles errores de escritura y validen los resultados esperados.

**Entregable:** Pruebas automatizadas que escriben y verifican la persistencia de pedidos en DynamoDB.

<details>
<summary>Pistas de conocimiento</summary>

- Manejo de errores en pruebas automatizadas.
- Validación de resultados esperados en pruebas de integración.

</details>

### Fase 3: Automatización de Pruebas de Lectura

**Objetivo:** Automatizar pruebas que validen la lectura de pedidos desde DynamoDB.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Crea pruebas automatizadas que lean un pedido desde DynamoDB y verifiquen que los datos son los esperados.
- Asegúrate de que las pruebas manejen posibles errores de lectura y validen los resultados esperados.

**Entregable:** Pruebas automatizadas que leen y verifican la correcta recuperación de pedidos desde DynamoDB.

<details>
<summary>Pistas de conocimiento</summary>

- Manejo de errores en pruebas automatizadas.
- Validación de resultados esperados en pruebas de integración.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son las pruebas de integración y por qué son importantes en este contexto?
- **paraQueSirve**: ¿Para qué sirve automatizar pruebas de integración contra DynamoDB?
- **comoSeUsa**: ¿Cómo se usa Serenity BDD 4 con el patrón Screenplay para automatizar pruebas?
- **erroresComunes**: ¿Cuáles son los errores comunes al automatizar pruebas de integración y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la automatización de pruebas de integración en términos de esperas y manejo de errores?

## Criterios de Evaluacion

- Configuración correcta del entorno de pruebas con Serenity BDD 4 y el patrón Screenplay.
- Uso de esperas explícitas en lugar de Thread.sleep.
- Creación de pruebas automatizadas que escriban y verifiquen la persistencia de pedidos en DynamoDB.
- Creación de pruebas automatizadas que lean y verifiquen la correcta recuperación de pedidos desde DynamoDB.
- Manejo adecuado de errores en las pruebas automatizadas.
- Validación correcta de resultados esperados en las pruebas de integración.

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

## Pruebas de aceptacion

Las pruebas de aceptacion (columna "Prueba" de la tabla) son de **solo lectura**: definen que significa "listo". No las edites, no las borres y no les agregues `skip`; lo que se resuelve es el codigo de cada pieza.

## Restricciones del pedido

| Restriccion | Donde se cumple | Estado |
|-------------|-----------------|--------|
| Usar Serenity BDD 4 con el patron Screenplay (dependencia serenity en el manifiesto) | chequeo estatico (manifest): exige `serenity-core`, `serenity-screenplay` | Verificada |
| No usar Thread.sleep; las esperas deben ser explicitas | chequeo estatico (code): prohibe `Thread.sleep` | Verificada |
| Un escenario de aceptacion verifica el item persistido en DynamoDB | sin ubicar | Restriccion no verificada en sin ubicar |

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

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
