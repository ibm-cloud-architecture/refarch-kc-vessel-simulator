# Microprofile 3.2 openliberty vessel simulator

This a simple vessel source events generatator taking into account the route from two harbors, the vessel characteristic and the geodetic curve shortest path.

This project is part of the Container shipment reference implementation as presented in this [project](https://ibm-cloud-architecture.github.io/refarch-kc)

![1](https://github.com/ibm-cloud-architecture/refarch-kc/blob/master/analysis/vessel-dom-cmd3.png)


The service exposes simple REST API to support getting vessels and fleets, and start and stop simulator to emulate vessel movements and container metrics events generation. When a vessel leaves or enters it will also generates the events as listed in the analysis.

## Technology used

* Using JAXRS API to define REST resources
* Using microprofile 3.2 for API documentation, metrics, heath and readiness
* Using [Reactive messaging in microservice](https://openliberty.io/blog/2019/09/13/microprofile-reactive-messaging-19009.html#mpreactive) as part of microprofile 3.2
* How to leverage OpenLiberty in container to support microprofile services

## User stories

This service keeps track of each of the vesselts available for transporting containers. Each vessel has a unique vesselID. We limit the implementation scope to a minimum viable product to demonstrate the vessel mouvement and generate events to kafka so we can apply stateful operator to compute average speed, ETA, and max speed metrics. The following user stories are implemented:

* [ ] The information about each vessel is kept in a json file so we an modify those data if necessary. Vessels are uniquely identified by their name (as vesselID).
* [ ] The capacity of a vessel is represented by a matrix, number of rows x number of columns to make it simpler. Therefore the total number of container is rows*columns.
* [ ] Support GPS lat/log position reports, as vessel position event, of the position of the vessel a different point in time. This is modeled as csv file with one row of (lat,log) pair, a row representing a time stamp. ()
* [ ] Generate vessel event when leaving source port and when entering destination port, and when docked.
* [ ] Define query of what happen to a vessel from a given time to retrace its past voyages.

## Build and run

* Keep up to date on appsody version: `brew upgrade appsody`
* The code template was create with the command: `appsody init java-openliberty kafka`
* Start a local Kafka broker and Zookeeper server from the `local-kafka` folder using `docker-compose up`. The network is `local-kafka_default`
* Build with `appsody build`
* Execute with `appsdody run`


To access the metrics, it is protected by a password for the user admin, so to get the Keystore password do: 
```
 docker exec -ti aecc3c3a4f25 bash -c "grep keystore /opt/ol/wlp/usr/servers/defaultServer/server.env"
```

## Deploy to openshift

* login to image registry: `oc registry login`

## Additional reading

* [Getting Started with openshift on learn.openshift.com](https://learn.openshift.com/introduction/getting-started/)
* [IBM Event Driven Reference architecture](https://ibm-cloud-architecture.github.io/refarch-eda/)
* [Event driven design patterns](https://ibm-cloud-architecture.github.io/refarch-eda/design-patterns/ED-patterns/)
* [Open Liberty](https://openliberty.io/)
* [Appsody OpenLiberty stack](https://appsody.dev/tutorials/open-liberty-stack-tutorial/)
* [Java microprofile](https://openliberty.io/docs/ref/general/#microprofile.html)
* [SmallRye reactive messaging with kafka](https://smallrye.io/smallrye-reactive-messaging/smallrye-reactive-messaging/2/kafka/kafka.html)
* [Kafka producer implementation considerations](https://ibm-cloud-architecture.github.io/refarch-eda/kafka/producers/)
* [Kafka Streams introduction](https://ibm-cloud-architecture.github.io/refarch-eda/kafka/kafka-stream/)


## Contribute

We welcome your contributions. There are multiple ways to contribute: report bugs and improvement suggestion, improve documentation and contribute code.
We really value contributions and to maximize the impact of code contributions we request that any contributions follow these guidelines:

The [contributing guidelines are in this note.](./CONTRIBUTING.md)

## Contributors

* Lead development [Jerome Boyer](https://www.linkedin.com/in/jeromeboyer/)
