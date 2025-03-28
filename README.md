# **Spring Boot Modular + Apache Kafka + Docker Compose**

Este proyecto demuestra cómo integrar **Spring Boot con Apache Kafka**, permitiendo enviar, recibir y almacenar mensajes en formato **JSON** de manera eficiente. Además, utilizamos **Docker Compose** para simplificar la configuración y ejecución del entorno, y **Kafka UI** para visualizar y monitorear en tiempo real los tópicos, mensajes y el estado del **broker** Kafka.

El proyecto de **Spring Boot** está desarrollado de manera **modular**, lo que facilita la escalabilidad, mantenimiento y separación de responsabilidades.

---

## **📌 Tecnologías Utilizadas**
✅ **Spring Boot** (Gestión de dependencias y REST API)  
✅ **Apache Kafka** (Mensajería asincrónica)  
✅ **Spring Kafka** (Cliente Kafka para Spring Boot)   
✅ **Jackson** (Conversión de JSON en Java)  
✅ **Maven** (Gestión del proyecto)  
✅ **Docker** (Contenedorización de servicios)  
✅ **Docker Compose** (Orquestación de contenedores)  
✅ **Kafka UI** (Interfaz gráfica para administrar Kafka)  
✅ **Zookeeper** (Coordinador de servicios para Kafka)  

---

## **📌 Estructura del Proyecto**

```
springboot_kafka_producer/        # Proyecto raíz (Parent POM)
│── main-app/                     # Módulo principal (ejecutable)
│   ├── src/main/java/com/demo/main/  
│   ├── src/main/resources/  
│   └── pom.xml  
│── module-producer/               # Módulo responsable de la lógica de producción de mensajes Kafka
│   ├── src/main/java/com/demo/producer/  
│   ├── src/main/resources/  
│   └── pom.xml  
│── module-core/                   # Módulo con clases comunes y configuraciones compartidas
│   ├── src/main/java/com/demo/core/  
│   ├── src/main/resources/  
│   └── pom.xml  
│── docker-compose.yml             # Configuración de Docker para Kafka y Zookeeper  
│── pom.xml                        # POM principal que define los módulos  
│── README.md                      # Documentación del proyecto  
```

📌 **Explicación de los módulos:**  
- **`main-app`**: Contiene la aplicación principal, punto de entrada con la clase `SpringBootApplication`.  
- **`module-producer`**: Lógica para enviar mensajes a Kafka (producers).  
- **`module-core`**: Clases comunes, configuración de Kafka, DTOs, etc.  

Este diseño modular permite mayor **reutilización** y **mantenibilidad**.



---

## **📌 Arquitectura del Proyecto**

### 🏗️ **Estructura Modular**
El proyecto está organizado en módulos para mejorar la mantenibilidad:
- **módulo-api**: Contiene los controladores y endpoints.
- **módulo-servicio**: Implementa la lógica de negocio y la comunicación con Kafka.
- **módulo-dominio**: Define las entidades y modelos de datos.
- **módulo-configuración**: Centraliza la configuración de Kafka y otros servicios.

---

## **📌 Servicios incluidos en Docker Compose**

### 🔹 **Zookeeper**
Servicio esencial para coordinar los brokers de Kafka. Configurado en el puerto **2181** con autenticación deshabilitada.  
📌 *Los datos se almacenan en un volumen para persistencia.*

### 🔹 **Brokers de Kafka (kafka1 & kafka2)**
Cada broker tiene:
- **Listeners internos** para comunicación dentro del clúster.
- **Listeners externos** para comunicación con clientes fuera de Docker.
- **Replicación** para alta disponibilidad.
- **Persistencia** para evitar pérdida de datos.

📌 *Usa `host.docker.internal` para conexiones desde el sistema anfitrión sin exponer una IP específica.*

### 🔹 **Kafka-UI**
Interfaz web accesible en **http://localhost:8080**, conectada a ambos brokers.

---

## **📌 Pasos para levantar los servicios**

### 🔥 **Iniciar los contenedores**
Ejecuta el siguiente comando en la terminal:

```sh
docker-compose up -d
```

📌 *Esto iniciará los servicios en segundo plano.*

### 🖥️ **Verificar que los contenedores están corriendo**
Ejecuta:

```sh
docker ps
```

Deberías ver los contenedores `zookeeper`, `kafka1`, `kafka2` y `kafka-ui` en ejecución.

### 🌍 **Acceder a la interfaz web de Kafka**
Abre un navegador y entra a:

```
http://localhost:8080
```

Aquí podrás visualizar los topics y administrar Kafka gráficamente.

### ✅ **Probar la conexión con Kafka**
Para listar los topics en Kafka, usa:

```sh
docker exec -it kafka1 kafka-topics.sh --bootstrap-server kafka1:9092 --list
```

### 📌 **Topic de prueba**
Cuando se ejecuta el proyecto de **Spring Boot** y todos los servicios de **Docker** están en funcionamiento, automáticamente se crea un **topic inicial** desde la aplicación.

Si quieres crearlo manualmente, ejecuta:

```sh
docker exec -it kafka1 kafka-topics.sh --bootstrap-server kafka1:9092 --create --topic test-topic --partitions 3 --replication-factor 2
```

Para verificar que se creó correctamente:

```sh
docker exec -it kafka1 kafka-topics.sh --bootstrap-server kafka1:9092 --describe --topic test-topic
```

### 🛑 **Apagar los servicios**
Si necesitas detener todos los contenedores:

```sh
docker-compose down
```

📌 *Para eliminar los volúmenes y datos almacenados, usa:*

```sh
docker-compose down -v
```

---

## **📌 Recursos adicionales**
- [Kafka Documentation](https://kafka.apache.org/documentation/)
- [Bitnami Kafka](https://hub.docker.com/r/bitnami/kafka)

