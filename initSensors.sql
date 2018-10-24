DELETE FROM SENSORS;
INSERT INTO SENSORS (ID, LATITUDE, LONGITUDE, NAME, TYPE)
VALUES
       (0, 47.205541, -1.566923, 'Machine ile','temperature'),
       (1, 47.217838, -1.537736, 'Gare SNCF', 'pressure'),
       (2, 47.182146, -1.519409, 'Vertou','windspeed'),
       (3, 47.224721, -1.629246, 'Atlantis','winddirection'),
       (4, 47.190210, -1.614462, 'Pont de cheviré','temperature'),
       (5, 47.244668, -1.579106, 'Breil','pressure'),
       (6, 47.247928, -1.551106, 'Ecole Centrale','windspeed'),
       (7, 47.223562, -1.581857, 'Parc procé','winddirection'),
       (8, 47.232185, -1.548198, 'Erdre','temperature'),
       (9, 47.217580, -1.495841, 'Ile Loire','pressure');
