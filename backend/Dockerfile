FROM tomcat:9.0.64-jdk8-corretto

ADD target/shop_rest.war /usr/local/tomcat/webapps/

ADD tomcat-conf/context.xml /usr/local/tomcat/conf 

EXPOSE 8080

CMD ["catalina.sh", "run"]