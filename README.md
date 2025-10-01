# Interview Demo

## Structure / Estructura :package:
| Element/Elemento | Description | Descripción |
|------------------|-------------|-------------|
| account-service | Accounts and transactions| Cuentas y movimientos|
| db | Database scripts | Archivos de base de datos|
| messaging-dto | DTOs and constants for RabbitMQ implemented in both microservices| DTOs y constantes para RabbitMQ implementado en ambos microservicios|
| user-service | Persons and clients | Personas y clientes |
| docker-compose.yml | Docker execution file | Archivo de ejecución de docker |
| interview-demo.json | Postman file | Archivo para postman |

## Steps to execute / Pasos para ejecutar :arrow_forward:
### Github
1. Clone repository / Clone el repositorio
2. Exececute **mvn clean install** on messaging-dto / Ejecute **mvn clean install** en el proyecto messaging-dto
3. Execute **mvn clean package** on account-service / Ejecute **mvn clean package** en el proyecto account-service
4. Execute **mvn clean package** on user-service / Ejecute **mvn clean package** en el proyecto user-service
5. Execute **docker-compose up -d** to build project containers / Ejecute **docker-compose up -d** para construirs los contenedores del proyecto.
### Zip
1. Unzip project / Descomprima el proyecto
2. Execute **docker-compose up -d** to build project containers / Ejecute **docker-compose up -d** para construirs los contenedores del proyecto.

*Note: docker-compose runs the database scripts and preloads some records into the clients and accounts tables. It is not necessary to run the scripts separately. Transactions remains empty so as not to break the consistency of the balances, so you will have to insert records manually.*

*Nota: docker-compose ejecuta los scripts de base de datos y precarga algunos registros en las tablas de clientes y cuentas. No es necesario ejecutar los scripts aparte. Transacciones se queda vacia para no romper la coherencia de los saldos por lo que tendrá que insertar registros manualmente.*

## Containers / Contenedores :atom:
| Name / Nombre | Ports / Puertos | Description | Descripción |
|-----------|-----------|-----------|-----------|
| rabbitmq | 15672:15672, 5672:5672 | RabbitMQ instance | Instancia de RabbitMQ |
| user-postgres | 5432:5432 | Clients database | Base de datos de clientes |
| account-postgres | 5433:5432 | Accounts database | Base de datos de cuentas |
| user-service | 8081:8080 | Clients microservice | Microservicio de clientes |
| account-service | 8082:8080 | Accounts microservice | Microservicio de cuentas|

## Tests / Pruebas :hammer_and_wrench:

### Unit and Integration / Unitarias e Integración

- **user-service**: 
    - Unit / Unitarias
    - Integration / Integración
- **account-service**: 
    - Unit / Unitarias

### Postman

- A JSON file is included for importing into Postman with all microservice endpoints.(interview-demo.json)

- Se incluye un archivo JSON para importar en postman con todos los endpoints de los microservicios.(interview-demo.json)

### Swagger

Swagger was implemented to facilitate manual testing without relying on external tools / Se implementó swagger para facilitar el testeo manual sin depender de herramientas externas.

**Swagger URLs:**
- [user-service](http://localhost:8081/swagger-ui/index.html)
- [account-service](http://localhost:8082/swagger-ui/index.html)

## Extras :white_check_mark:

- **SpringSecurity**: To encrypt passwords / Para cifrar contraseñas
- **Swagger**: To facilitate testing / Para facilitar pruebas
- **RabbitMQ**: For communication between microservices / Para comunicación entre microservicios
- **H2**: To avoid modifying the database during integration testing / Para no modificar la base de datos durante las pruebas de integración