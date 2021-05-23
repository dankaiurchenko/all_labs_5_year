library(sparklyr)

# spark_available_versions()
# spark_install("3.1")

sc <- spark_connect(master = "local", version = "3.1", packages = "kafka")
read_options <- list(kafka.bootstrap.servers = "localhost:9092", subscribe = "topic1")
write_options <- list(kafka.bootstrap.servers = "localhost:9092", topic = "topic2")

stream <- stream_read_kafka(sc, options = read_options) %>%
  stream_write_kafka(options = write_options)

stream_stop(stream)
 
