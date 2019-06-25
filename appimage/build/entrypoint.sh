
#! /bin/sh

sleep 10
java -jar payara-micro.jar --addLibs mysql-connector-java-5.0.8-bin.jar --rootDir /opt/payara/rootDir --postbootcommandfile asadmin.txt --deploy HistoryCars.war
