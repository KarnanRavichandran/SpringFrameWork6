---basic---


Make a Dockerfile - case sensitive, add the below lines:

FROM openjdk:18.0-slim                          //base img
COPY target/*.jar app.jar			//Copying all files from target and putting into app.jar
EXPOSE 5000					//Exposing the port
ENTRYPOINT ["java","-jar","/app.jar"]		//when a container is created using our docker image we need to run our application

Run- Maven Clean, Install -> it builds jar file.

Go to cmd,

cd - to the directory where Dockerfile is present,

docker build -t paul/hello-world:v1 . - building docker image

docker run -d -p 5000:5000 paul/hello-world:v1 // running the docker image

Now go to localhost port 5000.


IMPORTANT NOTE:

docker container ls // check for any containers created 
docker image list // check for available docker images



---Advanced-1---

The previous method has some flaws, as we are building the jar files by manually executing maven goals. 
In this method, we include the jar building while we build docker image itself. (Takes a long time)



FROM maven:3.8.6-openjdk-18-slim AS build       //base img //build stage
WORKDIR /home/app				//setting working directory
COPY . /home/app				//copy everything from working directory to /home/app
RUN mvn -f /home/app/pom.xml clean package	//running mvn clean package //-f /home/app/pom.xml - we are using the pom.xml to buildjar file in docker image

FROM openjdk:18.0-slim
EXPOSE 5000
COPY --from=build /home/app/target/*.jar app.jar	//instead of copying from local, we are copying jar files from build stage --from=build /home/app/target/*.jar to app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]


Go to cmd,

cd - to the directory where Dockerfile is present,

docker build -t paul/hello-world:v2 . //building image

docker run -d -p 5000:5000 paul/hello-world:v2  // running the docker image

Now go to localhost port 5000.



---Advanced-2---

Improving layer cache, so the build time gets reduced. 
(layer by layer - changes made in particular layer will only be re-executed and non changes in the layer are used for building the docker image.)


FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /home/app

COPY ./pom.xml /home/app/pom.xml
COPY ./src/main/java/com/paul/rest/webservices/restfulwebservices/RestfulWebServicesApplication.java	/home/app/src/main/java/com/paul/rest/webservices/restfulwebservices/RestfulWebServicesApplication.java

RUN mvn -f /home/app/pom.xml clean package

COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:18.0-slim
EXPOSE 5000
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]

Go to cmd,

cd - to the directory where Dockerfile is present,

docker build -t paul/hello-world:v3 . //building image - takes a lot of time building for first time

docker run -d -p 5000:5000 paul/hello-world:v3  // running the docker image

Now go to localhost port 5000.



Make some changes in code ---

docker build -t paul/hello-world:v4 . //building image - takes a less time

docker run -d -p 5000:5000 paul/hello-world:v4  // running the docker image

Now go to localhost port 5000.





----USING MAVEN PLUGINS----

Go to maven panel -> plugins ->spring:boot-> spring-boot:build-image.


Go to cmd,

docker image list // check if our image is present,

C:\Users\PAUL TIMOTHY>docker image list
REPOSITORY                 TAG              IMAGE ID       CREATED        SIZE
paketobuildpacks/run       base-cnb         e06d1d37657c   2 weeks ago    87MB
mysql                      8-oracle         7484689f290f   3 weeks ago    538MB
paketobuildpacks/builder   base             6b5f3b73c356   43 years ago   1.2GB
hello-world-java           0.0.1-SNAPSHOT   00f62d9b96a4   43 years ago   283MB 	// our docker image

Now to run,
docker container run -d -p 5000:5000 hello-world-java:0.0.1-SNAPSHOT


Note: re-building image is also quicker using maven plugin when there are code changes -> spring-boot:build-image





