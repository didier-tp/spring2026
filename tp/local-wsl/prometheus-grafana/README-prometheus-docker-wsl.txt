cd /mnt/c/tp/local-git-didier-tp-repositories/spring2026/tp/local-wsl/prometheus-grafana
docker compose up -d
http://localhost:9090 dans un navigateur pour accéder à prometheus et vérifier le status
http://localhost:3000 dans un navigateur pour accéder à grafana avec comme login par defaut (admin, admin)
docker compose down


====================
par défaut wsl accède à la machine hôte windows avec une ip spéciale:
ip route show | grep -i default | awk '{ print $3}' (ex: 172.18.192.1)
WSL2 peut accéder à la machine windows hôte via localhost si version windows 11 récente et si
option networkingMode=mirrored dans .wslconfig

=====
#http://localhost:8181/appliSpring/
#http://localhost:8181/appliSpring/actuator

#http://localhost:8181/appliSpring/actuator/prometheus ==> affiche une liste de query pour prometheus
#with micrometer-registry-prometheus in springBoot pom.xml
#and management.endpoint.prometheus.enabled=true
#and management.endpoints.web.exposure.include=prometheus
#in application.properties
