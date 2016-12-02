use nncloud;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE activation_functions;
TRUNCATE TABLE synapses;
TRUNCATE TABLE neurons;
TRUNCATE TABLE layers;  
TRUNCATE TABLE networks;
SET FOREIGN_KEY_CHECKS = 1;

SELECT * FROM activation_functions;

SELECT COUNT(id_synapse) FROM synapses;

UPDATE users SET activated = 1 where login='test';

SELECT s.value FROM synapses s;
SELECT * FROM synapses;

ALTER TABLE synapses ADD value DOUBLE;

ALTER TABLE users DROP INDEX session_id_UNIQUE;

ALTER TABLE activation_functions ADD first_derivative VARCHAR(90);

SELECT * FROM users;

SELECT * FROM networks;

SELECT * FROM networks;
SELECT * FROM layers;

SELECT * FROM neurons;

SELECT * FROM activation_functions;