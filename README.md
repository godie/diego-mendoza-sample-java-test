## Requirements:

* Docker <a href="https://hub.docker.com/">https://hub.docker.com/</a>
* Java 1.8


### Create docker image

```bash
$ docker build -t com/godieboy/clip .
```

### show the follow:
 
```bash
Sending build context to Docker daemon  37.38MB
Step 1/4 : FROM openjdk:8-jdk-alpine
8-jdk-alpine: Pulling from library/openjdk
e7c96db7181b: Pull complete 
f910a506b6cb: Pull complete 
c2274a1a0e27: Pull complete 
Digest: sha256:94792824df2df33402f201713f932b58cb9de94a0cd524164a0f2283343547b3
Status: Downloaded newer image for openjdk:8-jdk-alpine
 ---> a3562aa0b991
Step 2/4 : VOLUME /tmp
 ---> Running in 90b6baa5a84c
Removing intermediate container 90b6baa5a84c
 ---> b4492ab65708
Step 3/4 : COPY  target/*.jar ClipApp.jar
 ---> f00d82a75559
Step 4/4 : ENTRYPOINT ["java","-jar","/ClipApp.jar"]
 ---> Running in 306692fef86a
Removing intermediate container 306692fef86a
 ---> 20a8bc2be657
Successfully built 20a8bc2be657
Successfully tagged com/godieboy/clip:latest

```

### Run docker in port 8080
```bash
$ docker run -p 8080:8080 -t com/godieboy/clip
```

<p>Can verify if everyting is fine :</p>

<a href="http://127.0.0.1:8080">Link to app</a>


### You can run the test the App with follow command
```bash
./mvnw test
```
--

