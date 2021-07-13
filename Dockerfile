FROM openjdk:14

COPY /target/rest-1.0.jar /rest.jar

CMD ["java", "-jar", "/rest.jar"]
