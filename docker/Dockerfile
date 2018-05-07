FROM java:8
VOLUME /app
ADD my.springcloudstack-oauth2-server.jar my.springcloudstack-oauth2-server.jar
RUN bash -c 'touch /my.springcloudstack-oauth2-server.jar'
ENTRYPOINT ["java","-jar","/my.springcloudstack-oauth2-server.jar"]