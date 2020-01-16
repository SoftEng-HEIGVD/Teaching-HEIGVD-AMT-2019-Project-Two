FROM mysql:5.7.28

COPY conf/my.cnf /etc/mysql/my.cnf
COPY dump/users_api.sql docker-entrypoint-initdb.d/
