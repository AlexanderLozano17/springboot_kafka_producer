# **Spring Boot + Apache Kafka + PostgreSQL**

Este proyecto demuestra cómo integrar **Spring Boot con Apache Kafka y PostgreSQL**, permitiendo enviar, recibir y almacenar mensajes en formato **JSON** de manera eficiente.

---

## **📌 Tecnologías Utilizadas**
✅ **Spring Boot** (Gestión de dependencias y REST API)  
✅ **Apache Kafka** (Mensajería asincrónica)  
✅ **Spring Kafka** (Cliente Kafka para Spring Boot)  
✅ **PostgreSQL** (Base de datos relacional)  
✅ **Spring Data JPA** (ORM para base de datos)  
✅ **Jackson** (Conversión de JSON en Java)  
✅ **Maven** (Gestión del proyecto)  

---

## **📌 Pasos Realizados**

### **1️⃣ Configuración de PostgreSQL**
- Instalamos PostgreSQL y creamos una base de datos llamada `kafkadb`.
- Configuramos las credenciales de acceso en `application.yml`.

### **2️⃣ Instalación y Configuración de Kafka**
- Instalamos y ejecutamos Apache Kafka.
- Creamos un tópico llamado `test-topic`.
- Configuramos Kafka en Spring Boot para producir y consumir mensajes.

### **3️⃣ Creación de la Entidad para Almacenar JSON**
- Definimos una entidad `Mensaje` para almacenar los datos en PostgreSQL.
- Configuramos JPA para que la tabla se cree automáticamente.

### **4️⃣ Implementación del Consumidor de Kafka**
- Implementamos un servicio que escucha los mensajes de Kafka.
- Al recibir un mensaje, lo convierte en JSON y lo almacena en la base de datos.

### **5️⃣ Implementación del Productor de Kafka**
- Creamos un servicio para enviar mensajes JSON a Kafka.
- Exponemos una API REST que permite enviar mensajes desde un cliente externo.

### **6️⃣ Pruebas del Sistema**
- Probamos la API enviando mensajes a Kafka mediante `curl` o Postman.
- Verificamos que el consumidor los reciba y los almacene en PostgreSQL.
- Consultamos la base de datos para comprobar los mensajes guardados.

---

## **📌 Comandos Útiles para Kafka**

### **✅ Iniciar Kafka (Si está en Docker)**
```bash
docker start kafka
```

### **✅ Crear un tópico en Kafka**
```bash
docker exec -it kafka kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

### **✅ Verificar los tópicos existentes**
```bash
docker exec -it kafka kafka-topics.sh --list --bootstrap-server localhost:9092
```

### **✅ Describir un tópico en Kafka**
```bash
docker exec -it kafka kafka-topics.sh --describe --topic test-topic --bootstrap-server localhost:9092
```

### **✅ Enviar un mensaje de prueba a Kafka desde la terminal**
```bash
docker exec -it kafka kafka-console-producer.sh --broker-list localhost:9092 --topic test-topic
```
_Escribe el mensaje y presiona Enter._

### **✅ Leer los mensajes desde Kafka**
```bash
docker exec -it kafka kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning
```

---

## **📌 🚀 Conclusión**
✅ **Spring Boot envía y recibe mensajes JSON con Apache Kafka.**  
✅ **Los mensajes se almacenan como `jsonb` en PostgreSQL.**  
✅ **Podemos probar la API con `curl` o Postman.**  

