
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

--       "API": "Cat Facts",
--       "Description": "Daily cat facts",
--       "Auth": "",
--       "HTTPS": true,
--       "Cors": "no",
--       "Link": "https://alexwohlbruck.github.io/cat-facts/",
--       "Category": "Animals"

);

ALTER TABLE api_details
   ADD FOREIGN KEY (category_id) REFERENCES api_categories (id);