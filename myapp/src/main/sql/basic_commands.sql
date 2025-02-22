-- @block test connection
SHOW TABLES;

-- @block Drop TABLES
DROP TABLE IF EXISTS log, payment, rental, admin, employee, customer, user, address, city, country,  episode;
DROP TABLE IF EXISTS series_category, season_actor, season, series, film_category, category, film_actor, film, lang, actor;

-- @block Select admin
SELECT * FROM admin;

-- @block Select employee
SELECT * FROM employee;

-- @block Select customer
SELECT * FROM customer;

-- @block Delete inserts
DELETE FROM admin;

-- @block Show index
show indexes from film;

-- @block show procedures
SELECT 
    routine_schema as "Database",
    routine_name
FROM 
    information_schema.routines
WHERE 
    routine_type = 'PROCEDURE' AND routine_schema = 'project2022';