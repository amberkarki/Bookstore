
FROM tomcat:8
COPY target/*.war /usr/local/tomcat/webapps
EXPOSE 9090
