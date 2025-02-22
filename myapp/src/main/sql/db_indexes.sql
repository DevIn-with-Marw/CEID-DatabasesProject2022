-- @block rental INDEXES
CREATE INDEX IF NOT EXISTS rentdate ON rental(rental_date);

-- @block actor indexes 
CREATE INDEX IF NOT EXISTS lastname ON actor(last_name);
