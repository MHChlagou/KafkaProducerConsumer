# Kafka Producer & Consumer with Spring Boot 
### You don't need to install Kafka in your Machine, I already create a docker file how will take care about Zookeeper and Kafka ENV.
### Make sure that you have Docker compose installed in your Machine.
First you need to run the /sample-kafka-producer/docker-compose file to do it follow this steps :
Open your teminal ->
Move to the directory where the docker compose file placed ->
Run the cmd : docker-compose up -d

Once it running the second steps is to run the producer project and sending your Json data by typping this URL : http://127.0.0.1:9001/kafka/publish/{name} , you can put your name for Example : Hedi.

After that run the consumer project and try to look in the console logs : you have to see something like this :
{name='Hedi', dept='Informatique', salary=60000}


## I hope this samples Kafka projects can help you :) 
