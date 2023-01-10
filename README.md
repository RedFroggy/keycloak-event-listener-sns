# **keycloak-event-listener-sns**
A [Keycloak](https://www.keycloak.org/) SPI that publishes events to an aws sns topic.

## **Compatibility**
The version 1.0 of this plugin is compatible with Keycloak `20.0.x` and higher.

## **How to install ?**
In a terminal, install docker-compose with the below command

```bash
sudo apt install docker-compose
```

### **Server**
Create a network by using the image `quay.io/keycloak/keycloak:20.0` with the below command 

```bash
docker-compose up -d
```

See the different created containers and the associated ports, with the below command

```bash
docker ps
```
Here, the used ports are :
- HTTP PORT = 9080
- HTTPS PORT = 9443

### **Maven**

You can also clone the Github Repository and install the plugin locally.

### **Container image (Docker)**

For Docker-based setups mount or copy the jar to `/opt/keycloak/providers`.

You may want to check [docker-compose.yml](docker-compose.yml) as an example.