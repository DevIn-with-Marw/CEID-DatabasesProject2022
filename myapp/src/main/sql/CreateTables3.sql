-- @block Bookmarked query
-- @group Ungrouped
-- @name Create Tables

-- Table Creation Schema

-- Table structure for table "actor"
CREATE TABLE IF NOT EXISTS actor (
    actor_id smallint(5) unsigned NOT NULL AUTO_INCREMENT,
    first_name varchar(45) NOT NULL,
    last_name varchar(45) NOT NULL,
    PRIMARY KEY (actor_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "language"
CREATE TABLE IF NOT EXISTS lang (
    lang_id tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
    lang_name char(20) NOT NULL,
    PRIMARY KEY (lang_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "film"
CREATE TABLE IF NOT EXISTS film (
    film_id smallint(5) unsigned NOT NULL AUTO_INCREMENT,
    title varchar(128) NOT NULL,
    film_description text,
    release_year year(4) DEFAULT NULL,
    language_id tinyint(3) unsigned NOT NULL,
    original_language_id tinyint(3) unsigned DEFAULT NULL,
    film_length smallint(5) unsigned DEFAULT NULL,
    rating enum('G', 'PG', 'PG-13', 'R', 'NC-17') DEFAULT 'G',
    special_features set("Trailers", "Commentaries", "Deleted Scenes", "Behind the Scenes") DEFAULT NULL,
    PRIMARY KEY (film_id),
    CONSTRAINT fk_film_language FOREIGN KEY (language_id) REFERENCES lang (lang_id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_film_language_original FOREIGN KEY (original_language_id) REFERENCES lang (lang_id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "film_actor"
CREATE TABLE IF NOT EXISTS film_actor (
    actor_id smallint(5) unsigned NOT NULL,
    film_id smallint(5) unsigned NOT NULL,
    PRIMARY KEY (actor_id, film_id),
    CONSTRAINT fk_film_actor_actor FOREIGN KEY (actor_id) REFERENCES actor (actor_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_film_actor_film FOREIGN KEY (film_id) REFERENCES film (film_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "category"
CREATE TABLE IF NOT EXISTS category (
    category_id tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
    catname varchar(25) NOT NULL,
    PRIMARY KEY (category_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "film_category"
CREATE TABLE IF NOT EXISTS film_category (
    film_id smallint(5) unsigned NOT NULL,
    category_id tinyint(3) unsigned NOT NULL,
    PRIMARY KEY (film_id, category_id),
    CONSTRAINT fk_film_category_category FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_film_category_film FOREIGN KEY (film_id) REFERENCES film (film_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "series"
CREATE TABLE IF NOT EXISTS series (
    series_id smallint(5) unsigned NOT NULL AUTO_INCREMENT,     
    title varchar(128) NOT NULL,
    series_description text,
    release_year year(4) DEFAULT NULL,
    language_id tinyint(3) unsigned NOT NULL,
    original_language_id tinyint(3) unsigned DEFAULT NULL,
    rating enum('G', 'PG', 'PG-13', 'R', 'NC-17') DEFAULT 'G',
    PRIMARY KEY (series_id),
    CONSTRAINT fk_series_language FOREIGN KEY (language_id) REFERENCES lang (lang_id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_series_language_original FOREIGN KEY (original_language_id) REFERENCES lang (lang_id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "season"
CREATE TABLE IF NOT EXISTS season (
    season_id smallint(5) unsigned NOT NULL AUTO_INCREMENT,
    series_id smallint(5) unsigned NOT NULL,
    season_num tinyint(2) unsigned NOT NULL,
    num_of_episodes tinyint(2),
    release_year year(4) DEFAULT NULL,
    PRIMARY KEY(season_id),
    CONSTRAINT season_to_series FOREIGN KEY (series_id) REFERENCES series(series_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "season-actor" 
CREATE TABLE IF NOT EXISTS season_actor(
    actor_id smallint(5) unsigned NOT NULL,
    in_season smallint(5) unsigned NOT NULL,
    PRIMARY KEY (actor_id, in_season),
    CONSTRAINT fk_series_actor_actor FOREIGN KEY (actor_id) REFERENCES actor (actor_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_series_actor_season FOREIGN KEY (in_season) REFERENCES season(season_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "series-category" 
CREATE TABLE IF NOT EXISTS series_category (
    series_id smallint(5) unsigned NOT NULL,
    category_id tinyint(3) unsigned NOT NULL,
    PRIMARY KEY (series_id, category_id),
    CONSTRAINT fk_series_category_category FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_series_category_film FOREIGN KEY (series_id) REFERENCES series (series_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "episode" 
CREATE TABLE IF NOT EXISTS episode (
    episode_id smallint(5) unsigned NOT NULL AUTO_INCREMENT,    
    title varchar(128) NOT NULL,
    season_id smallint(5) unsigned NOT NULL, 
    episode_num tinyint(2) unsigned NOT NULL,
    episode_length smallint(5) unsigned DEFAULT NULL,
    rating enum('G', 'PG', 'PG-13', 'R', 'NC-17') DEFAULT 'G',
    PRIMARY KEY(episode_id),
    CONSTRAINT fk_episode_season FOREIGN KEY (season_id) REFERENCES season(season_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "country" 
CREATE TABLE IF NOT EXISTS country (
    country_id smallint(5) unsigned NOT NULL AUTO_INCREMENT,
    country varchar(50) NOT NULL,
    PRIMARY KEY (country_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "city" 
CREATE TABLE IF NOT EXISTS city (
    city_id smallint(5) unsigned NOT NULL AUTO_INCREMENT,
    city varchar(50) NOT NULL,
    country_id smallint(5) unsigned NOT NULL,
    PRIMARY KEY (city_id),
    CONSTRAINT fk_city_country FOREIGN KEY (country_id) REFERENCES country (country_id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "address" 
CREATE TABLE IF NOT EXISTS address (
    address_id smallint(5) unsigned NOT NULL AUTO_INCREMENT,
    address_name varchar(50) NOT NULL,
    district varchar(20) DEFAULT NULL,
    city_id smallint(5) unsigned NOT NULL,
    postal_code varchar(10) DEFAULT NULL,
    phone varchar(20) NOT NULL,
    PRIMARY KEY (address_id),
    CONSTRAINT fk_address_city FOREIGN KEY (city_id) REFERENCES city (city_id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "user" 
CREATE TABLE IF NOT EXISTS user(
    user_id smallint(5) unsigned NOT NULL AUTO_INCREMENT,
    first_name varchar(45) NOT NULL,
    last_name varchar(45) NOT NULL,
    email varchar(50) DEFAULT NULL,
    active tinyint(1) NOT NULL DEFAULT 1,
    create_date datetime NOT NULL,
    usertype enum("CUSTOMER", "ADMIN", "EMPLOYEE"),
    pending boolean,
    PRIMARY KEY (user_id),
    UNIQUE KEY (email)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "customer" 
CREATE TABLE IF NOT EXISTS customer (
    customer_id smallint(5) unsigned NOT NULL,
    cust_type enum("FILMS ONLY", "SERIES ONLY", "FILMS AND SERIES") DEFAULT NULL,
    address_id smallint(5) unsigned DEFAULT NULL,
    PRIMARY KEY (customer_id),
    CONSTRAINT fk_customer_address FOREIGN KEY (address_id) REFERENCES address (address_id) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_user_customer FOREIGN KEY (customer_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "employee" 
CREATE TABLE IF NOT EXISTS employee (
    employee_id smallint(5) unsigned NOT NULL,
    phone int(10) unsigned DEFAULT NULL,
    PRIMARY KEY(employee_id),
    CONSTRAINT fk_user_employee FOREIGN KEY (employee_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "admin" 
CREATE TABLE IF NOT EXISTS admin (
    admin_id smallint(5) unsigned NOT NULL,
    PRIMARY KEY(admin_id),
    CONSTRAINT fk_user_admin FOREIGN KEY (admin_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "rental" 
CREATE TABLE IF NOT EXISTS rental (
    rental_id int(11) NOT NULL AUTO_INCREMENT,
    rental_date datetime NOT NULL,
    film_id smallint(5) unsigned DEFAULT NULL,
    episode_id smallint(5) unsigned DEFAULT NULL,
    customer_id smallint(5) unsigned NOT NULL,
    PRIMARY KEY (rental_id),
    CONSTRAINT fk_rental_customer FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_rental_film_film FOREIGN KEY (film_id) REFERENCES film (film_id) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_rental_episode_ep FOREIGN KEY (episode_id) REFERENCES episode (episode_id) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "payment" 
CREATE TABLE IF NOT EXISTS payment (
    payment_id smallint(5) unsigned NOT NULL AUTO_INCREMENT,
    customer_id smallint(5) unsigned,
    rental_id int(11),
    amount decimal(5,2) DEFAULT 0.4, 
    payment_date datetime NOT NULL,
    PRIMARY KEY (payment_id),
    CONSTRAINT fk_payment_customer FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_payment_rental FOREIGN KEY (rental_id) REFERENCES rental (rental_id) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Table structure for table "log" 
CREATE TABLE IF NOT EXISTS log (
    log_entry int(11) NOT NULL AUTO_INCREMENT,
    email varchar(70),
    date_of_event DATETIME NOT NULL,
    type_of_event enum("INSERT", "UPDATE", "DELETE"),
    success boolean NOT NULL,
    table_name varchar(50) NOT NULL,
    PRIMARY KEY(log_entry),
    CONSTRAINT fk_log_user FOREIGN KEY (email) REFERENCES user (email) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
