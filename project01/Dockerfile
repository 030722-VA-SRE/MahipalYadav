
FROM maven:3.6.3-openjdk-8 as builder

COPY src/ src/
COPY pom.xml pom.xml

RUN mvn package -Dmaven.test.skip -Pprod

FROM java:8 as runner

COPY --from=builder target/project01.jar project01.jar


ENTRYPOINT ["java", "-jar", "/project01.jar"]