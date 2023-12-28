FROM maven:3.9.6
WORKDIR /usr/src/test_framework
COPY pom.xml .
RUN mvn verify clean --fail-never
COPY . .
CMD ["mvn", "clean", "test"]
# , "-Dtest=MainFunctionalityTests#test_debugging"