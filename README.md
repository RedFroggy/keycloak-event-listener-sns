# **keycloak-event-listener-sns** 

<div align="center">
<img src="./assets/logo.png" width="15%"/> 
  <img src="./assets/keycloak-logo1.png" width="35%" />
  <img src="./assets/AWS-Logo.svg" width="22%"/>
</div>
</br>

<div align="center">
<a href="https://github.com/RedFroggy/keycloak-event-listener-sns"><img src="https://github.com/RedFroggy/keycloak-event-listener-sns/actions/workflows/build.yml/badge.svg"/></a><a href="https://github.com/RedFroggy/keycloak-event-listener-sns"><img src="https://github.com/RedFroggy/keycloak-event-listener-sns/actions/workflows/release.yml/badge.svg"/></a>

[![semantic-release](https://img.shields.io/badge/%20%20%F0%9F%93%A6%F0%9F%9A%80-semantic--release-e10079.svg)](https://github.com/semantic-release/semantic-release)
</div>

</br>

A [Keycloak](https://www.keycloak.org/) SPI that publishes events to an aws sns topic.

| Statements                  | Branches                | Functions                 | Lines             |
| --------------------------- | ----------------------- | ------------------------- | ----------------- |
| ![Statements](https://img.shields.io/badge/statements-100%25-brightgreen.svg?style=flat) | ![Branches](https://img.shields.io/badge/branches-100%25-brightgreen.svg?style=flat) | ![Functions](https://img.shields.io/badge/functions-100%25-brightgreen.svg?style=flat) | ![Lines](https://img.shields.io/badge/lines-100%25-brightgreen.svg?style=flat) |

## Features

* Listen event on Keycloak
* Send event on a topic aws sns

## Compatibility
The version ![GitHub Release Date](https://img.shields.io/github/release/RedFroggy/keycloak-event-listener-sns?style=plastic) of this plugin is compatible with Keycloak `21.0.x` and higher.

## How to install?

Download a release (*.jar file) that works with your Keycloak version from
the [list of releases](https://github.com/RedFroggy/keycloak-event-listener-sns/releases).

### Server

Copy the jar to the `providers` folder and execute the following command:

```shell
${kc.home.dir}/bin/kc.sh build
```

Note: the jars files are available directly on releases

### Maven

You can also clone the Github Repository and install the plugin locally.

### Container image (Docker)

For Docker-based setups mount or copy the jar to `/opt/keycloak/providers`.

You may want to check [docker-compose.yml](docker-compose.yml) as an example.

with docker-compose:
1. package the code with aws dependencies, use maven 'docker-compose' profile to package 

```bash
mvn package -P docker-compose
docker-compose up -d
```

1. create .env file and add your own aws informations, put in `environment` of docker-compose file the corresponding key and secret key names.

## How to use it

### Requirements

Verify event listeners is deploy in keycloak. Got to {keycloak url}/admin/master/console/#/master/realm-settings/events.

<div align="center">

![server-info_event](/assets/server-info_event.png)
</div>

### Configuration

Once the installation is complete, the `aws-sns` event listener appears in "
realm-settings/events" on your realm. Add and save to enable "AWS-SNS", remove and save to disable "AWS-SNS"

<div align="center">

![enable_event-listener](/assets/enable_event-listener.png)
</div>

<div align="center">

![required-actions-conf](/assets/event-listener_updated.png)
</div>

Once enabled, all of the actions in your account (details  updat, login, logout,...) will be published on aws sns topic.

[See Keycloak class *Event* for details of published elements](https://www.keycloak.org/docs-api/13.0/javadocs/org/keycloak/events/Event.html)

#### **Sns topic arn configuration**

You have two possibilities to configure the event topic arn and admin event topic arn :
1. use the `.env` file add your own aws informations about sns topic arn, put in `environment` of docker-compose file the corresponding event topic arn and admin event topic arn names.

```
 - KC_SNS_EVENT_TOPIC_ARN=${KC_SNS_EVENT_TOPIC_ARN-TODO}
 - KC_SNS_ADMIN_EVENT_TOPIC_ARN=${KC_SNS_ADMIN_EVENT_TOPIC_ARN-TODO}
```
2. use the following lines in `command` in docker-compose file

```
'--spi-events-listener-aws-sns-event-topic-arn=TODO', 
'--spi-events-listener-aws-sns-admin-event-topic-arn=TODO'
```
## How to contribute

[See here](CONTRIBUTING.en.md)
