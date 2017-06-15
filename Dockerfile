FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER cmcornejo@atsistemas.com
VOLUME /tmp

ADD bat-desk-web/target/bat-desk-web.war bat-desk-web.war
RUN sh -c 'touch /bat-desk-web.war'

ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/bat-desk-web.war"]