# product-svc

This service provide API(s) for Product view and search related funclities.

This is registered with Netflix Eureka in [Registration Server](https://github.com/atanubhowmick/sapient-registration-server) and accessible through Netflix Zuul that is [Gateway Server](https://github.com/atanubhowmick/sapient-gateway-server).

H2 Runtime database is being used. Please look into `schema.sql` and `data.sql` inside src/main/resources for DB details.

`QueryPageable` and `QueryPageableSpecification` are used to provide the search/filter/pagination functionalities. 

1. **Important URL**

    Eureka Dashboard:             http://localhost:8085/
    
    Zuul Actuator :               http://localhost:8086/actuator/info
    
    Product Service Swagger URL:  http://localhost:8086/product/swagger-ui.html
    
    H2 Ddatabase Login URL:       http://localhost:8086/product/h2
