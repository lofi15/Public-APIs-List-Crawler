# Public-APIs-List-Crawler

# Requirements :
1. Docker 
2. maven


# Steps to follow
  1. Clone project
  2. navigate into project directory
  3. execute following commands
```sh
$ mvn package -DskipTests
$ sudo docker create -v /var/lib/postgresql/data --name PostgresData alpine 
$ sudo docker build ./ -t webcrawlerapp
$ sudo docker-compose up
```
4. The crawler is up and running, to check the data fetched, have to go look inside the database container.
5. On seperate terminal window, to access database container: copy the postgres container id from :
 ```sh
 $ docker ps -a
```
6. Open container and access postgres database inside, where required tables are stored:
 ```sh
 $ docker exec -it container_ID bash
 $ psql -U postgres
```

# DB schema

### api categories

 id | category_name |
----|---------------|
1 | Animals
2 | Anime
3 | Anti-Malware
4 | Art & Design
           
### api details
 id  |  api_name  |  auth  | category_id |  cors   |description| https |   link
------|------|------|------|------|------|------|------|
 649 | Bitly      | OAuth  |          42 | unknown | URL shortener and link management | t |  http://dev.bitly.com
 650 | CleanURI   | No     |          42 | yes     | URL shortener service                              | t     | https://cleanuri.com/docs
 651 | ClickMeter | apiKey |          42 | unknown | Monitor, compare and optimize your marketing links | t     | https://support.clickmeter.com/hc/en-us/categories/201474986
 652 | Rebrandly  | apiKey |          42 | unknown | Custom URL shortener for sharing branded links     | t     | https://developers.rebrandly.com/v1/docs
 
 ### Commands to create database 
 
```sh
CREATE TABLE api_categories(
    id serial PRIMARY KEY,
    category_name VARCHAR (256)
);

CREATE TABLE api_details(

    id serial PRIMARY KEY,
    category_id int NOT NULL,
    api_name VARCHAR (256),
    description VARCHAR (256),
    auth VARCHAR (256),
    https BOOLEAN,
    cors VARCHAR (5),
    link VARCHAR (256)

);

ALTER TABLE api_details
   ADD FOREIGN KEY (category_id) REFERENCES api_categories (id);
```
 
 # Tasks Completed :
1.  Code follows concept of OOPS
2.  Support for Authentication & token expiration
3.  Support for Pagination to get all data
4.  Support for Rate limiting
5.  Crawled all API entries for all categories and stored it in a database
6.  No of entries in database (api_details): 640

# Scope for improvement
1. Development of project in different language which has less configuration hassle.
2. Find more elegant way to deal with pagination and token expiration while fetching data.

 
 
