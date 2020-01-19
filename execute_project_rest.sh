# Build user project with maven and copy the users jar to docker topology
cd project/spring-server-movies || exit
mvn clean package install
cp target/swagger-spring-movies-1.0.0.jar ../../docker/docker-images/backend-movies/

# Go back to project root
cd ..

# Build movies project with maven and copy movies jar to docker topology
cd spring-server-users || exit
mvn clean package install
cp target/swagger-spring-users-1.0.0.jar ../../docker/docker-images/backend-users/

# Go back to docker topology
cd ../../docker || exit

# docker compose
docker-compose down
docker-compose up --build

# Shut everything down when done
docker-compose down

# Clean docker (For my memory). You can comment these lines if you don't want a clean up of your system.
docker system prune
docker volume prune

