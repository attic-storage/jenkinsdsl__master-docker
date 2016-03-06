FROM jenkins:latest

COPY plugins.txt /usr/share/jenkins/ref/
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/ref/plugins.txt

ENV ADMIN_USERNAME admin
#default admin password : override!
ENV DEFAULT_ADMIN_PASSWORD adminjenkins
ENV ADMIN_PASSWORD $DEFAULT_ADMIN_PASSWORD

COPY init_scripts/*.groovy /usr/share/jenkins/ref/init.groovy.d/

EXPOSE 16042