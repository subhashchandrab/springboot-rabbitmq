FROM openjdk:16-jdk
#RUN addgroup --system spring && adduser --system spring -ingroup spring
#USER spring:spring
#ARG JAR_FILE=target/*.jar
COPY SpringBootRabbitMQHelloWorld-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
