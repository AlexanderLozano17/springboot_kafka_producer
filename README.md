# **Spring Boot + Apache Kafka + PostgreSQL + Docker Compose**

Este proyecto demuestra cómo integrar **Spring Boot con Apache Kafka y PostgreSQL**, permitiendo enviar, recibir y almacenar mensajes en formato **JSON** de manera eficiente. Además, utilizamos **Docker Compose** para simplificar la configuración y ejecución del entorno, y **Kafka UI** para visualizar y monitorear en tiempo real los tópicos, mensajes y el estado del **broker** Kafka.

---

## **📌 Tecnologías Utilizadas**
✅ **Spring Boot** (Gestión de dependencias y REST API)  
✅ **Apache Kafka** (Mensajería asincrónica)  
✅ **Spring Kafka** (Cliente Kafka para Spring Boot)  
✅ **PostgreSQL** (Base de datos relacional)  
✅ **Spring Data JPA** (ORM para base de datos)  
✅ **Jackson** (Conversión de JSON en Java)  
✅ **Maven** (Gestión del proyecto)  
✅ **Docker** (Contenedorización de servicios)  
✅ **Docker Compose** (Orquestación de contenedores)  
✅ **Kafka UI** (Interfaz gráfica para administrar Kafka)  
✅ **Zookeeper** (Coordinador de servicios para Kafka)  

---

## **📌 Pasos Realizados**

### **1️⃣ Creación del `docker-compose.yml`**
- Definimos servicios en `docker-compose.yml` para **Zookeeper, Kafka y Kafka UI**.
- Configuramos volúmenes persistentes para evitar la pérdida de datos entre reinicios.
- Definimos una red de Docker para la comunicación entre los contenedores.

### **2️⃣ Configuración de PostgreSQL**
- Instalamos PostgreSQL y creamos una base de datos llamada `kafkadb`.
- Configuramos las credenciales de acceso en `application.yml`.

### **3️⃣ Instalación y Configuración de Kafka**
- Creamos un contenedor de Kafka con conexión a Zookeeper.
- Configuramos `KAFKA_CFG_ADVERTISED_LISTENERS` para permitir conexiones externas.
- Creamos un tópico llamado `test-topic`.
- Configuramos Kafka en Spring Boot para producir y consumir mensajes.

### **4️⃣ Implementación del Consumidor de Kafka**
- Implementamos un servicio que escucha los mensajes de Kafka.
- Al recibir un mensaje, lo convierte en JSON y lo almacena en la base de datos.

### **5️⃣ Implementación del Productor de Kafka**
- Creamos un servicio para enviar mensajes JSON a Kafka.
- Exponemos una API REST que permite enviar mensajes desde un cliente externo.

### **6️⃣ Uso de Kafka UI**
- Instalamos **Kafka UI** como contenedor en Docker.
- Accedemos a `http://localhost:8080Este proyecto demuestra cómo integrar **Spring Boot con Apache Kafka y PostgreSQL**, permitiendo enviar, recibir y almacenar mensajes en formato **JSON** de manera eficiente. Además, utilizamos **Docker Compose** para simplificar la configuración y ejecución del entorno, y **Kafka UI** para visualizar y monitorear en tiempo real los tópicos, mensajes y el estado del **broker** Kafka.
Este proyecto demuestra cómo integrar **Spring Boot con Apache Kafka y PostgreSQL**, permitiendo enviar, recibir y almacenar mensajes en formato **JSON** de manera eficiente. Además, utilizamos **Docker Compose** para simplificar la configuración y ejecución del entorno, y **Kafka UI** para visualizar y monitorear en tiempo real los tópicos, mensajes y el estado del **broker** Kafka.
` para visualizar los tópicos y mensajes en tiempo real.

### **7️⃣ Pruebas del Sistema**
- Probamos la API enviando mensajes a Kafka mediante `curl` o Postman.
- Verificamos que el consumidor los reciba y los almacene en PostgreSQL.
- Consultamos la base de datos para comprobar los mensajes guardados.

---

## **📌 Comandos Útiles para Docker y Kafka**

### **✅ Iniciar los contenedores con Docker Compose**
```bash
docker-compose up -d
