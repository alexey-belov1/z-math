FROM openjdk:14

COPY /target/back-1.0.jar /z-math-back.jar

EXPOSE 80

CMD ["java", "-jar", "/z-math-back.jar"]
