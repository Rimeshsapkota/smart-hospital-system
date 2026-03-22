# smart-hospital-system

Mono<T>

1 item → container has something inside it
example: Mono<UserResponse>
container holds one UserResponse object

0 item → container is empty
example: Mono.empty()
no result inside — operation had nothing to return

Error  → container caught an exception
example: Mono.error(new AlreadyExistException())
not empty, not a value — something went wrong

    "error:": "No HandlerResultHandler for MonoFlatMapMany"

    - "8762:8761"   # ✅ Mac port 8762 → container port 8761
                      # local uses 8761
                      # Docker uses 8762

Service             Local (IntelliJ)    Docker (browser)
──────────────────────────────────────────────────────
discovery-server    localhost:8761      localhost:9761
config-service      localhost:8888      localhost:9888
auth-service        localhost:8144      localhost:9144
api-gateway         localhost:8020      localhost:9020
mysql               localhost:3306      localhost:3307

docker ps
docker images------>List all Docker images on your machine
docker compose down----->down all service from container
docker compose up -d------>run all service in container
docker stop container-name---->Stop a running container gracefully
docker start container-name-------->Start a stopped container
docker build -t smart-hospital-api-gateway.1.0:latest---->this need docker file in that folder to run 
docker compose up -d --force-recreate image_name----->recreate the service
docker rm -f config-service----->to remove service from container

### from that one image you can create as many containers as you need
docker run -d --name auth-service-1 -p 8144:8144 smart-hospital-auth-service-1.0:latest
docker run -d --name auth-service-2 -p 8145:8144 smart-hospital-auth-service-1.0:latest

### three step after update the code and deploy in the doceker
mvn clean package → docker build → docker compose up --force-recreate




