-- we need variable @user_now (= user id of current user)
-- trigger 4_1 notes
-- deletes on rental/payment made only by the admin
-- -- (we might need the triggers if the admin wants to delete some records to free up space in the database)
-- before update on rental/payment abort (no one can change a rental/payment that has already been made)

-- @block rental_made (after insert) IN DB
CREATE TRIGGER afterINSrental AFTER INSERT ON rental
FOR EACH ROW
BEGIN 
	DECLARE email_now VARCHAR(50);

	SELECT email INTO email_now
	FROM user 
	WHERE user.user_id = @user_now;

	INSERT INTO log VALUES(NULL, email_now, NOW(), "INSERT", 1, "rental");
	
END$

-- @block payment_made (after insert) IN DB
CREATE TRIGGER afterINSpayment AFTER INSERT ON payment
FOR EACH ROW 
BEGIN 
	DECLARE email_now VARCHAR(50);

	SELECT email INTO email_now
	FROM user 
	WHERE user.user_id = @user_now;

	INSERT INTO log VALUES(NULL, email_now, NOW(), "INSERT", 1, "payment");
END$

-- @block delete rental
CREATE TRIGGER beforeDELrental BEFORE DELETE ON rental 
FOR EACH ROW 
BEGIN 
    DECLARE type_now enum("CUSTOMER", "ADMIN", "EMPLOYEE");
    DECLARE email_now VARCHAR(70);

    SELECT usertype, email INTO type_now, email_now
    FROM user 
    WHERE user_id = @user_now;

    IF (type_now != "ADMIN") THEN
        INSERT INTO log VALUES(NULL, email_now, CURDATE(), "DELETE", 0, "rental");
        SIGNAL SQLSTATE VALUE "45000"
		SET MESSAGE_TEXT ="You are not authorized to change this value.";
    END IF;

END$

CREATE TRIGGER beforeDELpayment BEFORE DELETE ON payment 
FOR EACH ROW 
BEGIN 
    DECLARE type_now enum("CUSTOMER", "ADMIN", "EMPLOYEE");
    DECLARE email_now VARCHAR(70);

    SELECT usertype, email INTO type_now, email_now
    FROM user 
    WHERE user_id = @user_now;
    IF (type_now != "ADMIN") THEN
        INSERT INTO log VALUES(NULL, email_now, CURDATE(), "DELETE", 0, "payment");
        SIGNAL SQLSTATE VALUE "45000"
		SET MESSAGE_TEXT ="You are not authorized to change this value.";
    END IF;

END$


CREATE TRIGGER beforeUPrental BEFORE UPDATE ON rental
FOR EACH ROW 
BEGIN 
    DECLARE type_now enum("CUSTOMER", "ADMIN", "EMPLOYEE");
    DECLARE email_now VARCHAR(70);

    SELECT usertype, email INTO type_now, email_now
    FROM user 
    WHERE user_id = @user_now;

    IF (type_now != "ADMIN") THEN
    INSERT INTO log VALUES(NULL, email_now, CURDATE(), "UPDATE", 0, "rental");
        SIGNAL SQLSTATE VALUE "45000"
		SET MESSAGE_TEXT ="You are not authorized to change this value.";
    END IF;

END$

CREATE TRIGGER beforeUPpayment BEFORE UPDATE ON payment 
FOR EACH ROW 
BEGIN 
    DECLARE type_now enum("CUSTOMER", "ADMIN", "EMPLOYEE");
    DECLARE email_now VARCHAR(70);

    SELECT usertype, email INTO type_now, email_now
    FROM user 
    WHERE user_id = @user_now;

    IF (type_now != "ADMIN") THEN
    INSERT INTO log VALUES(NULL, email_now, CURDATE(), "UPDATE", 0, "payment");
        SIGNAL SQLSTATE VALUE "45000"
		SET MESSAGE_TEXT ="You are not authorized to change this value.";
    END IF;

END$

-- @block trigger4_2 IN DB
CREATE TRIGGER discount BEFORE INSERT ON payment
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	DECLARE num_of_rents SMALLINT(3);

	SELECT email INTO email_now
	FROM user 
	WHERE user.user_id = NEW.customer_id;

	call zhtoumeno3_2(email_now, DATE(NEW.payment_date), num_of_rents);

	IF num_of_rents % 3 = 0 THEN 
	SET NEW.amount = NEW.amount/2; 
	END IF;
END$

-- @block trigger4_3 (before update)
-- a customer can only change his own profile settings through the gui. An employee can change any customers settings or his own through the gui
CREATE TRIGGER declineUpdate BEFORE UPDATE ON user
FOR EACH ROW
BEGIN
	DECLARE em VARCHAR(50);
	DECLARE curuser_type enum("ADMIN","EMPLOYEE","CUSTOMER");

	SELECT email, usertype INTO em, curuser_type
	FROM user 
	WHERE user.user_id = @user_now;

	IF (curuser_type = "CUSTOMER") THEN 
		IF (OLD.usertype != NEW.usertype OR OLD.email != NEW.email OR OLD.user_id != NEW.user_id) THEN 
			SIGNAL SQLSTATE VALUE "45000"
			SET MESSAGE_TEXT ="You are not authorized to change this value.";
		END IF;
	ELSEIF (curuser_type = "EMPLOYEE") THEN
		IF (OLD.email != NEW.email) THEN 
			SIGNAL SQLSTATE VALUE "45000"
			SET MESSAGE_TEXT ="You are not authorized to change this value.";
		END IF;
	END IF;
END$

-- @block AFTER DELETE TRIGGERS FOR ALL TABLES IN DB
-- actor
CREATE TRIGGER delactor AFTER DELETE ON actor
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "actor");

END$

--	language
CREATE TRIGGER dellang AFTER DELETE ON lang
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "lang");
END$

-- film
CREATE TRIGGER delfilm AFTER DELETE ON film
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "film");

END$

-- film_actor
CREATE TRIGGER delfilm_actor AFTER DELETE ON film_actor
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "film_actor");
END$

-- category
CREATE TRIGGER delcategory AFTER DELETE ON category
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "category");
END$

-- film_category
CREATE TRIGGER delfilm_category AFTER DELETE ON film_category
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "film_category");
END$

-- series
CREATE TRIGGER delseries AFTER DELETE ON series
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "series");
END$

-- season
CREATE TRIGGER delseason AFTER DELETE ON season
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "season");
END$

-- season_actor
CREATE TRIGGER delseason_actor AFTER DELETE ON season_actor
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "season_actor");
END$

-- series_category
CREATE TRIGGER delseries_category AFTER DELETE ON series_category
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "series_category");
END$

CREATE TRIGGER delepisode AFTER DELETE ON episode
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "episode");
END$

-- country
CREATE TRIGGER delcountry AFTER DELETE ON country
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "country");
END$

-- city
CREATE TRIGGER delcity AFTER DELETE ON city
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "city");
END$

-- address
CREATE TRIGGER deladdress AFTER DELETE ON address
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "address");
END$

-- user
CREATE TRIGGER deluser AFTER DELETE ON user
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "user");
END$

-- customer
CREATE TRIGGER delcustomer AFTER DELETE ON customer
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "customer");
END$

-- employee
CREATE TRIGGER delemployee AFTER DELETE ON employee
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "employee");
END$

-- admin
CREATE TRIGGER deladmin AFTER DELETE ON admin
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "admin");
END$

-- rental
CREATE TRIGGER delrental AFTER DELETE ON rental
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "rental");
END$

-- payment
CREATE TRIGGER delpayment AFTER DELETE ON payment
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "DELETE", 1, "payment");
END$

-- @block AFTER INSERT TRIGGERS FOR ALL TABLES 
-- actor
CREATE TRIGGER insactor AFTER INSERT ON actor
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "actor");

END$

--	language
CREATE TRIGGER inslang AFTER INSERT ON lang
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "lang");
END$

-- film
CREATE TRIGGER insfilm AFTER INSERT ON film
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "film");

END$

-- film_actor
CREATE TRIGGER insfilm_actor AFTER INSERT ON film_actor
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "film_actor");
END$

-- category
CREATE TRIGGER inscategory AFTER INSERT ON category
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "category");
END$

-- film_category
CREATE TRIGGER insfilm_category AFTER INSERT ON film_category
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "film_category");
END$

-- series
CREATE TRIGGER insseries AFTER INSERT ON series
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "series");
END$

-- season
CREATE TRIGGER insseason AFTER INSERT ON season
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "season");
END$

-- season_actor
CREATE TRIGGER insseason_actor AFTER INSERT ON season_actor
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "season_actor");
END$

-- series_category
CREATE TRIGGER insseries_category AFTER INSERT ON series_category
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "series_category");
END$

CREATE TRIGGER insepisode AFTER INSERT ON episode
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "episode");
END$

-- country
CREATE TRIGGER inscountry AFTER INSERT ON country
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "country");
END$

-- city
CREATE TRIGGER inscity AFTER INSERT ON city
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "city");
END$

-- address
CREATE TRIGGER insaddress AFTER INSERT ON address
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "address");
END$

-- user
CREATE TRIGGER insuser AFTER INSERT ON user
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "user");
END$

-- customer
CREATE TRIGGER inscustomer AFTER INSERT ON customer
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "customer");
END$

-- employee
CREATE TRIGGER insemployee AFTER INSERT ON employee
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "employee");
END$

-- admin
CREATE TRIGGER insadmin AFTER INSERT ON admin
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "INSERT", 1, "admin");
END$

-- (rental + payment exists above, according to query 4.1)

-- @block AFTER UPDATE TRIGGERS FOR ALL TABLES
CREATE TRIGGER upactor AFTER UPDATE ON actor
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "actor");

END$

--	language
CREATE TRIGGER uplang AFTER UPDATE ON lang
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "lang");
END$

-- film
CREATE TRIGGER upfilm AFTER UPDATE ON film
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "film");

END$

-- film_actor
CREATE TRIGGER upfilm_actor AFTER UPDATE ON film_actor
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "film_actor");
END$

-- category
CREATE TRIGGER upcategory AFTER UPDATE ON category
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "category");
END$

-- film_category
CREATE TRIGGER upfilm_category AFTER UPDATE ON film_category
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "film_category");
END$

-- series
CREATE TRIGGER upseries AFTER UPDATE ON series
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "series");
END$

-- season
CREATE TRIGGER upseason AFTER UPDATE ON season
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "season");
END$

-- season_actor
CREATE TRIGGER upseason_actor AFTER UPDATE ON season_actor
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "season_actor");
END$

-- series_category
CREATE TRIGGER upseries_category AFTER UPDATE ON series_category
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "series_category");
END$

CREATE TRIGGER upepisode AFTER UPDATE ON episode
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "episode");
END$

-- country
CREATE TRIGGER upcountry AFTER UPDATE ON country
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "country");
END$

-- city
CREATE TRIGGER upcity AFTER UPDATE ON city
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "city");
END$

-- address
CREATE TRIGGER upaddress AFTER UPDATE ON address
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "address");
END$

-- user
CREATE TRIGGER upuser AFTER UPDATE ON user
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "user");
END$

-- customer
CREATE TRIGGER upcustomer AFTER UPDATE ON customer
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "customer");
END$

-- employee
CREATE TRIGGER upemployee AFTER UPDATE ON employee
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "employee");
END$

-- admin
CREATE TRIGGER upadmin AFTER UPDATE ON admin
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "admin");
END$

-- rental
CREATE TRIGGER uprental AFTER UPDATE ON rental
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "rental");
END$

-- payment
CREATE TRIGGER uppayment AFTER UPDATE ON payment
FOR EACH ROW
BEGIN
	DECLARE email_now VARCHAR(50);
	SELECT email INTO email_now 
	FROM user 
	WHERE user_id=@user_now;
	INSERT INTO log VALUES (NULL, email_now, NOW(), "UPDATE", 1, "payment");
END$

