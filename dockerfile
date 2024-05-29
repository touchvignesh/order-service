FROM alpine:latest
RUN apk add --no-cache openjdk21

COPY build/libs/order-service-1.0-SNAPSHOT.jar /order-service-1.0-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "/order-service-1.0-SNAPSHOT.jar"]