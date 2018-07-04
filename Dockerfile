#IMAGE: Get the base image for Liberty
FROM websphere-liberty:beta

COPY liberty/server.xml /opt/ibm/wlp/usr/servers/defaultServer/
COPY target/spring-jms-activemq-helloworld-0.0.1-SNAPSHOT.jar /opt/ibm/wlp/usr/servers/defaultServer/apps

#FEATURES: Install any features that are required
RUN /opt/ibm/wlp/bin/installUtility install  --acceptLicense \
	springBoot-1.5; exit 0
