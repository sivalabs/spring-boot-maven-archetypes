# spring-boot-maven-archetypes

## How to use?

The archetypes are published to Maven central. 
You can generate projects using these archetypes using `mvn archetype:generate` and select the archetype number.

### Install archetypes locally

Clone the repo `git clone https://github.com/sivalabs/spring-boot-maven-archetypes.git`

Run install script `./install.sh`

## Archetypes

### 1. spring-boot-java-basic-archetype
Generates basic SpringBoot application.
1. Configured Dockerfile, Jenkinsfile

**Generate application from archetype:**

```
mvn archetype:generate \
    -B -DarchetypeGroupId=io.github.sivalabs.maven.archetypes \
    -DarchetypeArtifactId=spring-boot-java-basic-archetype \
    -DarchetypeVersion=0.0.1 \
    -DgroupId=com.mycompany \
    -DartifactId=myapp \
    -Dversion=1.0-SNAPSHOT \
    -Dpackage=com.mycompany.myapp
```

### 2. spring-boot-java-rest-api-archetype
Generates basic SpringBoot REST API application with the following features:
1. Database support (H2/Postgres)
2. Configured Dockerfile, Jenkinsfile
3. Flyway DB migrations
4. Monitoring with Prometheus, Grafana
5. ELK based logging

**Generate application from archetype:**

```
mvn archetype:generate \
    -B -DarchetypeGroupId=io.github.sivalabs.maven.archetypes \
    -DarchetypeArtifactId=spring-boot-java-rest-api-archetype \
    -DarchetypeVersion=0.0.1 \
    -DgroupId=com.mycompany \
    -DartifactId=myapp \
    -Dversion=1.0-SNAPSHOT \
    -Dpackage=com.mycompany.myapp
```

### 3. spring-boot-java-rest-api-secure-archetype

Generates basic SpringBoot REST API application with the following features:
1. Database support (H2/Postgres)
2. JWT Token based security
3. Configured Dockerfile, Jenkinsfile
4. Flyway DB migrations
5. Monitoring with Prometheus, Grafana
6. ELK based logging

**Generate application from archetype:**

```
mvn archetype:generate \
    -B -DarchetypeGroupId=io.github.sivalabs.maven.archetypes \
    -DarchetypeArtifactId=spring-boot-java-rest-api-secure-archetype \
    -DarchetypeVersion=0.0.1 \
    -DgroupId=com.mycompany \
    -DartifactId=myapp \
    -Dversion=1.0-SNAPSHOT \
    -Dpackage=com.mycompany.myapp
```

## Developer Notes

Procedure for deploying to Maven Central https://central.sonatype.org/pages/apache-maven.html

Create or update archetypes and set version to SNAPSHOT (ex: 1.0.0-SNAPSHOT)

Deploy SNAPSHOT version to https://oss.sonatype.org/content/repositories/snapshots/

`spring-boot-maven-archetypes> mvn clean deploy`

Deploy release version to Maven Central

```
spring-boot-maven-archetypes> mvn release:clean release:prepare
spring-boot-maven-archetypes> mvn release:perform`
```
