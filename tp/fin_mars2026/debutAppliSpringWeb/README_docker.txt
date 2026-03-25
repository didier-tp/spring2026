pour test de l'image docker fabriquée par docker build -t didierdefrance69/appli_spring_v3:1 .
docker container run --name appli-spring-v3 -p8181:8181 -d didierdefrance69/appli_spring_v3:1
http://localhost:8181/appliSpring
docker container stop appli-spring-v3 && docker container rm appli-spring-v3
ou bien docker container rm -f appli-spring-v3