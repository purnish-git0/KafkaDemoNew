.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

.\bin\windows\kafka-server-start.bat .\config\server.properties

.\bin\windows\kafka-topics.bat --create --topic message-from-user-event --bootstrap-server localhost:9092

.\bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --topic cab-reservation --create --partitions 3

.\bin\windows\kafka-console-consumer.bat --topic message-from-user-event --from-beginning --bootstrap-server localhost:9092

.\bin\windows\kafka-console-producer.bat --topic cab-reservation --bootstrap-server localhost:9092

.\bin\windows\kafka-console-producer.bat --topic cab-reservation --bootstrap-server localhost:9092
