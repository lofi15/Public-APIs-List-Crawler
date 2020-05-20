# Public-APIs-List-Crawler

# Requirements :
1. Docker 
2. maven


# Steps to follow
  1. Clone proejct
  2. navigate into project directory
  3. execute following commands
```sh
$ mvn package
$ sudo docker create -v /var/lib/postgresql/data --name PostgresData alpine 
$ sudo docker run -p 5433:5432 --name postgres -e POSTGRES_PASSWORD=postgres -d --volumes-from PostgresData postgres
$ sudo docker build ./ -t webcrawlerapp
$ sudo docker-compose up
```
4. The crawler is up and running, to check the data fetched, have to look inside the db container.
5. To access db container: get the container id using :
 ```sh
 $ docker ps -a
```
6. Open container and access postgres db inside:
 ```sh
 $ docker exec -it container_ID bash
 $ psql -U postgres
```
