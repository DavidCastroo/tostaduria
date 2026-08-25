# Taller Práctico: Arquitectura Hexagonal — Specialty Coffee Roasters

## Estudiante
- [David Fernando Castro Lopez]

## Descripción

Sistema de procesamiento de pedidos de café para una tostaduría de Café de Especialidad, implementado en Java siguiendo la Arquitectura Hexagonal (Ports & Adapters). El núcleo del sistema es completamente independiente de frameworks, bases de datos o tecnologías externas.

## Tecnologías

- Java 17+ (probado con JDK 19)
- Maven

## Estructura del proyecto

```
src/main/java/tostaduria/
├── domain/                          # Núcleo — sin dependencias externas
│   ├── entities/
│   │   ├── CoffeeBean.java
│   │   └── Order.java
│   ├── exceptions/
│   │   ├── StockInsuficienteException.java
│   │   └── GranoNoEncontradoException.java
│   └── enums/
│       └── MetodoPreparacion.java
│
├── application/                     # Casos de uso + puertos
│   ├── ports/out/
│   │   ├── InventoryPort.java
│   │   └── OrderRepositoryPort.java
│   └── usecases/
│       └── ProcessCoffeeOrderUseCase.java
│
├── infrastructure/                  # Adaptadores (tecnología concreta)
│   └── adapters/
│       ├── in/console/
│       │   └── ConsoleOrderController.java
│       └── out/persistence/
│           ├── InMemoryInventoryAdapter.java
│           └── InMemoryOrderAdapter.java
│
└── Main.java                        # Ensamblaje (inyección de dependencias)
```

## Cómo ejecutar

Desde la raíz del proyecto (donde está el `pom.xml`):

```bash
mvn compile exec:java "-Dexec.mainClass=tostaduria.Main"
```

O ejecutando `Main.java` directamente desde el IDE.

El programa muestra un menú interactivo por consola donde se puede:
- Ingresar el tipo de grano (ej. `Geisha`, `Bourbon Rosado`)
- Ingresar la cantidad en gramos
- Elegir un método de preparación (`ESPRESSO`, `V60`, `PRENSA_FRANCESA`, `AEROPRESS`, `CHEMEX`)

El sistema confirma el pedido si hay stock suficiente, o lo rechaza con un mensaje claro si no hay stock o el grano no existe.

### Inventario inicial (datos de prueba)

| Grano | Cantidad disponible |
|---|---|
| Geisha | 500g |
| Bourbon Rosado | 300g |

## Misión 5: Reflexión Arquitectónica

**1. Si el día de mañana la tostaduría decide cambiar la base de datos en memoria por PostgreSQL, ¿qué carpetas o clases de tu proyecto tendrías que modificar y cuáles se mantendrían intactas?**

Solo sería necesario modificar la capa `infrastructure/adapters/out/persistence/`: reemplazar (o agregar) `InMemoryInventoryAdapter` e `InMemoryOrderAdapter` por nuevas clases (ej. `PostgresInventoryAdapter`, `PostgresOrderAdapter`) que implementen las mismas interfaces `InventoryPort` y `OrderRepositoryPort`. También habría que actualizar el ensamblaje en `Main.java` para instanciar las nuevas clases.

Se mantendrían completamente intactas: todo `domain/` (entidades, excepciones, enums) y todo `application/` (puertos y `ProcessCoffeeOrderUseCase`), ya que ninguna de esas clases conoce ni depende de la tecnología de persistencia usada.

**2. ¿Por qué es importante que el `ProcessCoffeeOrderUseCase` no conozca la existencia del `InMemoryInventoryAdapter`?**

Porque así el caso de uso queda desacoplado de cualquier tecnología concreta de persistencia. Al depender únicamente de la interfaz "InventoryPort" (y no de una implementación específica), se puede cambiar la tecnología de almacenamiento (memoria, PostgreSQL, MongoDB, etc.) sin modificar ni una línea de la lógica de negocio, Ademas esto facilita las pruebas unitarias ya que se puede inyectar un adaptador falso en lugar de uno real sin alterar el caso de uso.

