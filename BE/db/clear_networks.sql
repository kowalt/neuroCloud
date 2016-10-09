use nncloud;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE activation_functions;
TRUNCATE TABLE synapses;
TRUNCATE TABLE neurons;
TRUNCATE TABLE layers;  
TRUNCATE TABLE networks;
SET FOREIGN_KEY_CHECKS = 1;

SELECT * FROM activation_functions;

SELECT * FROM users;

UPDATE users SET activated = 1 where login='test';

SELECT s.value FROM synapses s;
SELECT * FROM synapses;

ALTER TABLE synapses ADD value DOUBLE;

ALTER TABLE users DROP INDEX session_id_UNIQUE;

SELECT * FROM user;