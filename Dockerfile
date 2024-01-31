FROM openjdk:17
COPY target/postalItemsApplication-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "postalItemsApplication-0.0.1-SNAPSHOT.jar"]
