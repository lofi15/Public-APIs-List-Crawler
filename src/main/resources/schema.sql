
CREATE TABLE api_categories(
    category_id serial NOT NULL,
    category_name VARCHAR (256) PRIMARY KEY
);

CREATE TABLE api_details(

    category_id int NOT NULL PRIMARY KEY ,
    api_name VARCHAR (256),
    description VARCHAR (256),
    auth VARCHAR (256),
    https BOOLEAN,
    cors VARCHAR (5),
    link VARCHAR (256)

--       "API": "Cat Facts",
--       "Description": "Daily cat facts",
--       "Auth": "",
--       "HTTPS": true,
--       "Cors": "no",
--       "Link": "https://alexwohlbruck.github.io/cat-facts/",
--       "Category": "Animals"

);