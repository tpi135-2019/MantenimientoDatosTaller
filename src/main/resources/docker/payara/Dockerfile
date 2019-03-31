FROM payara/server-full:5.183
MAINTAINER kevin Figueroa GF15006
LABEL servidor de aplicaciones
ENV PAYARA_PATH /opt/payara
RUN wget -O $PAYARA_PATH/glassfish/lib/mariadb.jar https://downloads.mariadb.com/Connectors/java/connector-java-2.3.0/mariadb-java-client-2.3.0.jar \
&& echo 'AS_ADMIN_PASSWORD=admin' > $PAYARA_PATH/pass.txt
WORKDIR $PAYARA_PATH/bin
RUN ./asadmin start-domain && \
./asadmin -I false --user admin --passwordfile $PAYARA_PATH/pass.txt create-jdbc-connection-pool \
--datasourceclassname org.mariadb.jdbc.MariaDbDataSource --restype javax.sql.DataSource --property \ user=cmansky:password=pass:servername=hostinho:portnumber=3306:databasename=taller talleres && \
./asadmin -I false --user admin --passwordfile $PAYARA_PATH/pass.txt create-jdbc-resource --connectionpoolid talleres jdbc/taller_tpi && \
./asadmin -I false --user admin --passwordfile $PAYARA_PATH/pass.txt stop-domain
WORKDIR $PAYARA_PATH
#EXPOSE 4848 4848 8080 8080
COPY HistoryCars-1.0-SNAPSHOT.war $DEPLOY_DIR
