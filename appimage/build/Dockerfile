 
FROM payara/micro:5.191
ADD mysql-connector-java-5.0.8-bin.jar $HOME
COPY asadmin.txt $HOME
ADD entrypoint.sh $HOME
ADD HistoryCars.war $HOME

ENTRYPOINT ["sh","entrypoint.sh"]




