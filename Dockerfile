#
# build stage
#
FROM maven:3.6.3-jdk-14 AS mavenbuild
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install package

#
# deploy package stage
#
FROM tomcat:8.5
COPY --from=mavenbuild /home/app/target/*.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]