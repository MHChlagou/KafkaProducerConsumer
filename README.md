# Kafka Producer & Consumer with Spring Boot 
### You don't need to install Kafka in your Machine, I already created a docker file who will handle Zookeeper and Kafka ENV.
### Make sure that you have Docker compose installed in your Machine.
First you need to run the /kafka-producer/docker-compose file to do so follow this steps :
Open your teminal ->
Move to the directory where the docker compose file placed ->
Run the cmd : docker-compose up -d

Once its running the second step is to add some data to the H2 Database with POST Request using postman via this URL :  http://127.0.0.1:9001/kafka/load 

Next step is to run producer project and begin publishing data by typping this URL : 

http://127.0.0.1:9001/kafka/publish/all 

After that run the consumer project and try to look in the console logs :  
you will see your Object in Json format with others record data. 

Example of Object data : {name='Hedi', dept='Informatique', salary=60000}


## I hope this samples Kafka projects can help you :) 
