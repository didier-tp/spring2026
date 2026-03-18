set JAVA_HOME=C:\Program Files\Java\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%
echo %PATH%
echo "******************************"
echo "via config/application.properties externe au .jar , :8282 a la place de :8181"
echo "http://localhost:8282/appliSpring"
echo "******************************"
REM sbapp.jar est ici une copie de target/sbapp-0.0.1-SNAPSHOT.jar
java -Dspring.profiles.active=dev2,reInit -jar debutAppliSpringWeb.jar

pause