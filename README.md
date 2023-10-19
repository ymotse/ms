### This is a studying project, that implements Eureka server and clients, Gateway, and api services.


#### Manually running:

Service-Registry:
```  
java -jar service-registry/target/service-registry-0.0.1-SNAPSHOT.jar --registry.hostname=localhost --registry.port=8761 --server.port=8761 
```  

Gateway-API:
```  
java -jar gateway-api/target/gateway-api-0.0.1-SNAPSHOT.jar --registry.hostname=localhost --registry.port=8761 --server.port=8000 
```  

Booking-Service:
```  
java -jar booking-service/target/booking-service-0.0.1-SNAPSHOT.jar --registry.hostname=localhost --registry.port=8761 --server.port=8101 --spring.profiles.active=dev 
```  

Exchange-Service:
```  
java -jar exchange-service/target/exchange-service-0.0.1-SNAPSHOT.jar --registry.hostname=localhost --registry.port=8761 --server.port=8201 --spring.profiles.active=dev 
```

