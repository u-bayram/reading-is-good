FROM adoptopenjdk/openjdk11:ubi
COPY target/readingisgood-0.0.1-SNAPSHOT.jar readingisgood-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/readingisgood-0.0.1-SNAPSHOT.jar"]