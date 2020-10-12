FROM tomcat:8
COPY target/*.war /usr/local/tomcat/webapps/
ENV mysqlHost=mysqlserver mysqlPort=3306 mysqlDatabase=book mysqlUser=apper mysqlPass=app123
EXPOSE 8080
CMD ["catalina.sh", "run"]