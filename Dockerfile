FROM ibm-semeru-runtimes:open-21-jre
ADD target/completable-future-openj9.jar app.jar
CMD ["java", "-jar", "app.jar"]
EXPOSE 8080