DELETE FROM jee.sensors;
ALTER TABLE jee.sensors AUTO_INCREMENT = 0;
INSERT INTO jee.sensors (latitude, longitude, name, type)
VALUES
       (47.205541, -1.566923, 'Machine ile','temperature'),
       (47.217838, -1.537736, 'Gare SNCF', 'pressure'),
       (47.182146, -1.519409, 'Vertou','windspeed'),
       (47.224721, -1.629246, 'Atlantis','winddirection'),
       (47.190210, -1.614462, 'Pont de cheviré','temperature'),
       (47.244668, -1.579106, 'Breil','pressure'),
       (47.247928, -1.551106, 'Ecole Centrale','windspeed'),
       (47.223562, -1.581857, 'Parc procé','winddirection'),
       (47.232185, -1.548198, 'Erdre','temperature'),
       (47.217580, -1.495841, 'Ile Loire','pressure');
SELECT * FROM jee.sensors
