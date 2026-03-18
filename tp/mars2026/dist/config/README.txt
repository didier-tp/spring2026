lorsque l'application springBoot est lancée/démarré ,
les fichiers .properties sont recherchés à différents endroits selon
https://docs.spring.io/spring-boot/reference/features/external-config.html
=======
le sous répertoire "config" du répertoire courant est un des endroits 
où l'on peut placer une configuration externe au .jar (qui sera prioritaire au .properties interne au .jar)
=====
dist/config/application.properties comporte ici par exemple un numéro de port différent (:8282)
donc l'url menant à l'application démarré via lancerSpringBootApp.bat
est donc http://localhost:8282/appliSpring