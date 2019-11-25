FROM tomcat:8
# Take the war and copy to webapps of tomcat
COPY /Users/Shared/Jenkins/Home/workspace/Maven_Project/target/*.war /usr/local/tomcat/webapps/BookStore-BookStore.war
