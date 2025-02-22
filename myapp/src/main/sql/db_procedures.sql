-- ALL IN DB check 3_4a
--@block set global variables (= prices for products accordint to subscription type)
SET @films_only = 0.4;
SET @series_only = 0.2;
SET @films_series = 0.3;
SET @series_films = 0.1;
SET @user_now = -1;

-- @block query3.1
CREATE TABLE IF NOT EXISTS z3_1films(
    film_id smallint(5) NOT NULL,
    title varchar(128) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS z3_1series(
    series_id smallint(25) NOT NULL,
    title varchar(128) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE PROCEDURE IF NOT EXISTS  zhtoumeno3_1 (IN c enum('m', 's'), IN number INT, IN startdate DATE, IN enddate DATE)
BEGIN
    DECLARE done INT;
    DECLARE msid SMALLINT(5);
    DECLARE mst VARCHAR(128);

    IF (c = 'm') THEN
    BEGIN

        DECLARE result_m CURSOR FOR SELECT rental.film_id
        FROM rental USE INDEX (rentdate)
        WHERE rental.rental_date BETWEEN startdate AND enddate AND rental.film_id IS NOT NULL
        GROUP BY rental.film_id
        ORDER BY count(rental.film_id) DESC
        LIMIT 0, number;

        DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=1;

        SET done = 0;
        OPEN result_m;

        REPEAT
        FETCH  result_m INTO msid;
        IF (done = 0) THEN
                    SELECT title INTO mst
                    FROM film
                    WHERE film_id = msid;
                    INSERT INTO z3_1films VALUES(msid,mst);
        END IF;
        UNTIL (done =1)
        END REPEAT;
        CLOSE result_m;

    END;
    ELSEIF (c = 's') THEN
    BEGIN

        DECLARE result_s CURSOR FOR SELECT season.series_id
        FROM season INNER JOIN episode ON season.season_id = episode.season_id
        INNER JOIN rental ON episode.episode_id = rental.episode_id
        WHERE rental.episode_id IN
        (SELECT rental.episode_id
        FROM rental USE INDEX (rentdate) WHERE rental.rental_date BETWEEN startdate AND enddate AND rental.episode_id IS NOT NULL
        )
        GROUP BY season.series_id
        ORDER BY count(rental.episode_id) DESC
        LIMIT 0, number;

        DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=1;

        SET done = 0;
        OPEN result_s;
        REPEAT
        FETCH result_s INTO msid;
        IF (done = 0) THEN
                    SELECT title INTO mst
                    FROM series
                    WHERE series_id= msid;
                    INSERT INTO z3_1series VALUES(msid,mst);
        END IF;
        UNTIL (done =1)
        END REPEAT;
        CLOSE result_s;

    END;
    END IF;
END$

-- @block call zhtoumeno3_1
CALL zhtoumeno3_1('m', 2, '2005-06-15 01:25:08', '2005-9-19 03:42:27');

-- @block zhtoumeno3_2
CREATE PROCEDURE IF NOT EXISTS zhtoumeno3_2(IN em VARCHAR(50), IN d DATE, OUT rentals SMALLINT(3)) 
BEGIN 
    DECLARE done INT;
    DECLARE cust_id SMALLINT(5) UNSIGNED;
    
    SELECT customer_id
    INTO cust_id
    FROM customer 
    WHERE customer_id IN 
    (SELECT user_id FROM user WHERE email=em);
    
    SELECT count(rental_id) INTO rentals
    FROM rental USE INDEX (fk_rental_customer)
    WHERE rental.customer_id = cust_id AND DATE(rental.rental_date) = d 
    GROUP BY rental.customer_id;
    
END$

-- @block test zhtoumeno3_2
SET @temp=0;
CALL zhtoumeno3_2('MAE.FLETCHER@sakilacustomer.org','2005-9-19', @temp);

-- @block zhtoumeno3_3
DROP TABLE z3_3films;
DROP TABLE z3_3series;
CREATE TABLE IF NOT EXISTS z3_3films(
    dates VARCHAR(25) NOT NULL,
    film_payments DECIMAL(7,2) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS z3_3series(
    dates VARCHAR(25) NOT NULL,
    episode_payments DECIMAL(7,2) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

DROP PROCEDURE IF EXISTS zhtoumeno3_3;
CREATE PROCEDURE IF NOT EXISTS zhtoumeno3_3()
BEGIN
    DECLARE done INT;
    DECLARE f_sum DECIMAL(7,2);
    DECLARE f_date VARCHAR(25);
    DECLARE s_sum DECIMAL(7,2);
    DECLARE s_date VARCHAR(25);
     
    DECLARE films_sum CURSOR FOR 
    SELECT sum(payment.amount), EXTRACT(YEAR_MONTH FROM payment.payment_date)
    FROM payment INNER JOIN rental ON payment.rental_id = rental.rental_id
    WHERE rental.film_id IS NOT NULL
    GROUP BY EXTRACT(YEAR_MONTH FROM payment.payment_date)
    ORDER BY EXTRACT(YEAR_MONTH FROM payment.payment_date) ASC;

    DECLARE series_sum CURSOR FOR 
    SELECT sum(payment.amount), EXTRACT(YEAR_MONTH FROM payment.payment_date)
    FROM payment INNER JOIN rental ON payment.rental_id = rental.rental_id
    WHERE rental.film_id IS NULL
    GROUP BY EXTRACT(YEAR_MONTH FROM payment.payment_date)
    ORDER BY EXTRACT(YEAR_MONTH FROM payment.payment_date) ASC;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=1;
    open films_sum;
    open series_sum;
    
    delete from z3_3films;
    SET done = 0;
    REPEAT
    FETCH films_sum INTO f_sum, f_date;
    IF (done = 0) THEN
        INSERT INTO z3_3films VALUES (f_date, f_sum);
    END IF;
    UNTIL(done = 1)
    END REPEAT;
    
    SET done = 0;
    delete from z3_3series;
    REPEAT
    FETCH series_sum INTO s_sum, s_date;
    IF (done = 0) THEN
        INSERT INTO z3_3series VALUES (s_date, s_sum);
    END IF;
    UNTIL(done = 1)
    END REPEAT;

    SELECT dates AS "DATE", film_payments AS "INCOME FROM FILMS" FROM z3_3films;
    SELECT dates AS "DATE", episode_payments AS "INCOME FROM SERIES" FROM z3_3series;
END$

-- @block test zhtoumeno3_3
call zhtoumeno3_3();

-- @block zhtoumeno3_4a
DROP PROCEDURE IF EXISTS zhtoumeno3_4a$
CREATE PROCEDURE IF NOT EXISTS zhtoumeno3_4a(IN lastname1 VARCHAR(45), IN lastname2 VARCHAR(45))
BEGIN

    SELECT count(actor_id) AS 'Actor Count With Same Lastname'
    FROM actor USE INDEX(lastname)
    WHERE last_name BETWEEN lastname1 AND lastname2;

    SELECT first_name AS "First name", last_name AS "Last name"
    FROM actor USE INDEX(lastname)
    WHERE last_name BETWEEN lastname1 AND lastname2;
END$

-- @block test zhtoumeno3_4a
call zhtoumeno3_4a("Cunt","Dick");

-- @block zhtoumeno3_4b
CREATE TABLE IF NOT EXISTS z3_4b(
    first_name varchar(45) NOT NULL,
    last_name varchar(45) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

DROP PROCEDURE IF EXISTS zhtoumeno3_4b;
CREATE PROCEDURE zhtoumeno3_4b(IN lastname VARCHAR(45) )
BEGIN 
    DECLARE done INT;
    DECLARE fn VARCHAR(45); 
    DECLARE ln VARCHAR(45);

    DECLARE fullname CURSOR FOR 
    SELECT first_name, last_name 
    FROM actor USE INDEX(lastname)
    WHERE actor.last_name = lastname;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=1;

    SET done = 0;
    OPEN fullname;
    IF (FOUND_ROWS() > 1) THEN 
        SELECT FOUND_ROWS() AS 'Count';
    END IF;
    
    delete from z3_4b;

    REPEAT
    FETCH fullname INTO fn, ln;
    IF(done = 0) THEN
        INSERT INTO z3_4b VALUES (fn, ln);
    END IF;
    UNTIL(done =1)
    END REPEAT;

    CLOSE fullname;
    SELECT first_name AS "First Name", last_name AS "Last Name" FROM z3_4b;
END;

-- @block test zhtoumeno3_4b
call zhtoumeno3_4b("DAVIS");

--@block payment_procedure
CREATE PROCEDURE pay4product(IN productType enum("film", "episode"), IN custID SMALLINT(5))
BEGIN
    DECLARE customer_type enum ("FILMS ONLY", "SERIES ONLY", "FILMS AND SERIES");
    DECLARE to_pay DECIMAL(5,2);

    SELECT cust_type INTO customer_type 
    FROM customer
    WHERE customer_id = custID;

    IF (customer_type = "FILMS ONLY") THEN 
        SET to_pay = @films_only;
    ELSEIF (customer_type = "SERIES ONLY") THEN 
        SET to_pay = @series_only;
    ELSE 
        IF (productType = "episode") THEN 
                SET to_pay = @series_films;
            ELSE 
                SET to_pay = @films_series;
            END IF;

        END IF;
    
    SELECT to_pay;
END$
