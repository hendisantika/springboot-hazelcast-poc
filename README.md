# springboot-hazelcast-poc

## Hazelcast Integration With SpringBoot

We all are building various applications in Spring Boot. Spring boot is a simple lightweight spring application with the integrated server. We can run java[spring] application without any hassle, with most of the things are driven by application properties.  We run single Spring Boot application in the single JVM. Many times we have to run more than one instance of the same application, to manage a large number of requests. We put these instances behind the load balancer, and load balancer will decide which instance should handle the current request.

So our request comes to the load balancer. The load balancer is the entry point of the request. Here we have App 1, which have 3 instances.

Instance: Same Application running in the different server [or in there scope].

So, whenever request goes to any of the instances of App 1, it should behave in the same manner. We create multiple instances so that we can handle more requests. Consider these 3 green regions as separate JVM [seperate tomcat sever].

As we all know two JVMs does not tall to each other, We can consider JVM as an isolated boundary to run a Java application.

## Problem Statement
Now suppose you want to somehow share data between these 3 different instances running on different JVM. Assume you need to count of the total number of request server by App 1. If we create a single variable in App1, which will be auto incremented each time request comes to application, it will not solve our problem. Lets name variable requestCounter. Now suppose the total number of request come to load balancer be 3.

### Flow Of Request

Request 1 goes to instance 1
Request 2 goes to instance 2
Request 3 goes to instance 1

### If We request for the total number of requests server by App 1

Instance 1 will return 2
Instance 2 will return 1
Instance 3 will return 0
Though App 1 serves total number 3 request, all instance provide the wrong result. This is because&nbsp; we are saving the requestCounter variable at an individual instance, thus every instance is giving the response of its state. We can either get the count from all the instance and sum it, but assume if you have hundred of instance of the same application, how you will do that.Even if we are ready to call each instance, we need to maintain the IP address of all the instances which can be very difficult.

For the same, we will you Hazelcast. What Hazelcast&nbsp; will do, it will share the state of variable among different instances [JVM].&nbsp; So in this blog, we will create a simple Spring Boot Application with HazelCast support. And try to share a single long variable among the different instances of Application.

Now your code is ready, start this spring boot application twice on the different port. Let’s assume we have started two instances on port 8080, 8081.

* When you make a request on http://localhost:8080/api/test you will get the result as “App1 1”.
* When you make a request on http://localhost:8081/api/test you will get the result as “App1 2″[Note -> we have made our first request to instance 2, still the value of variable come as 2].
* If you again make a request on http://localhost:8080/api/test you will get the result as “App1 3”.
 

This is a very simple POC for haze-cast integration with spring boot, it has enormous usecase, We will see some the use cases.

### Other Use Cases
To maintain the session amoung distributed application.
Have distributed cache, [If we maintain cache at the instance level, then cache created at instance 1 will not benefit the instance 2]
Distributed in-memory data storage.[Hazelcast contain replica also in case some instance goes down]

#### Run Locally

```
git clone https://github.com/hendisantika/springboot-hazelcast-poc.git
```

```
mvn clean spring-boot:run
```

