SELECT * from rental;

-- @block log
SELECT * from log;

-- @block payment
SELECT * from payment;

-- @block undochanges
DELETE from rental WHERE rental_id=33;
DELETE from log;

-- @block TRIGGERS
SHOW TRIGGERS;

-- @BLOCK
SHOW PROCEDURE STATUS;

-- @block procedures
--check zhtoumeno3_4a again. for some reason it was not in the base from te start.
SELECT routine_name                                                                                 
FROM information_schema.routines                                                                                     
WHERE routine_schema = 'project2022' AND routine_type='PROCEDURE'; 

-- @block 
SELECT * FROM log;

-- @BLOCK
select * from rental;

-- @block 
SELECT * from payment;