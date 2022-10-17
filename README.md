# KAFKA-4090
https://issues.apache.org/jira/browse/KAFKA-4090

### How to: generate the `keystore.jks` file

cmd: `keytool -keystore kafka.server.keystore.jks -alias localhost -validity 365000 -genkey -keyalg RSA`
Password used to generate the .jks: password

See https://docs.confluent.io/3.0.0/kafka/ssl.html#generate-ssl-key-and-certificate-for-each-kafka-broker

### How to: generate the `truststore.jks` file

```bash
# 1.
openssl req -new -x509 -keyout ca-key -out ca-cert -days 365000
# PEM pass phrase used: password

# 2.
keytool -keystore kafka.server.truststore.jks -alias CARoot -import -file ca-cert
# password used: password
```