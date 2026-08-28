<plugin>
		<groupId>org.flywaydb</groupId>
		<artifactId>flyway-maven-plugin</artifactId>
		<version>13.2.0</version>
</plugin>
====
mvn clean flyway:migrate or with itelliJ maven/.../plugins/flyway/migrate
optional specific config: -Dflyway.configFiles=myFlywayConfig.conf (default is flyway.conf)
===
in fyway.conf:
-------------
flyway.user=sa
#flyway.password=root
flyway.password=
flyway.schemas=mydbbank
flyway.url=jdbc:h2:~/mydb1
#flyway.url=jdbc:mysql://localhost:3306/mydbbank  (will use jdbc jar of classpath from maven config)
flyway.locations=filesystem:db/migration  for src/main/resources/db/migrtion
=====
convention de nommage des fichiers:
Vxyz__actionZz.sql (ex: V1__initdb.sql , V2__updateXxTable.sql , ...)
Attention : on ne peut pas avoir deux fichiers avec même version
==> V1__zzz , V1_1__zzz ou V2026_09_01__zzz ou ...

===
in flyway sql script , CREATE TABLE , ... BUT no insert into

===
NB: alone, flyway-maven-plugin works well with h2
for mysql, postgres, ... a additional dependency is required
ex:
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-database-postgresql</artifactId>
    <artifactId>flyway-mysql</artifactId>
  	<version>13.2.0</version>
</dependency>

====

https://www.baeldung.com/sql/insert-if-not-exists-operation