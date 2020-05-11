FROM java:8
EXPOSE 8002
ADD /target/order-0.0.1-SNAPSHOT.war order-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java","-jar","order-0.0.1-SNAPSHOT.war"]