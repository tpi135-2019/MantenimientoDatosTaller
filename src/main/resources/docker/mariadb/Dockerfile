FROM mariadb:10.3
MAINTAINER kevin Figueroa GF15006
ENV MYSQL_USER cmansky
ENV MYSQL_ROOT_PASSWORD secretpass
ENV MYSQL_PASSWORD pass
ENV MYSQL_DATABASE taller
COPY backup.sql /docker-entrypoint-initdb.d
VOLUME mariaData /var/lib/mysql
EXPOSE 3306:3306
