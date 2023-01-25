# **keycloak-event-listener-sns**
A [Keycloak](https://www.keycloak.org/) SPI that publishes events to an aws sns topic.

## **Compatibility**
The version 1.0 of this plugin is compatible with Keycloak `20.0.x` and higher.

## How to install?

```bash
sudo apt install docker-compose
```

### Server

Copy the jar to the `providers` folder and execute the following command:

```shell
${kc.home.dir}/bin/kc.sh build
```

### Maven

You can also clone the Github Repository and install the plugin locally.

### Container image (Docker)

For Docker-based setups mount or copy the jar to `/opt/keycloak/providers`.

You may want to check [docker-compose.yml](docker-compose.yml) as an example.

with docker-compose:
1. package the code with aws dependencies, use maven 'docker-compose' profile to package 
1. create .env file and add your own aws informations, see environment key in docker-compose

```bash
mvn package -P docker-compose
docker-compose up -d
```