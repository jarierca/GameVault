-- Datos de prueba para la tabla PLAYER
INSERT INTO PLAYER (USERNAME, EMAIL, PASSWORD, ROLE) VALUES
('player1', 'player1@example.com', 'password1','user'),
('player2', 'player2@example.com', 'password2','user'),
('player3', 'player3@example.com', 'password3','user');

-- Datos de prueba para la tabla PLATFORM
INSERT INTO PLATFORM (NAME) VALUES
('PlayStation 4'),
('Xbox One'),
('Nintendo Switch'),
('PC');

-- Datos de prueba para la tabla GENRE
INSERT INTO GENRE (NAME) VALUES
('Action'),
('Adventure'),
('RPG'),
('Strategy'),
('Simulation');

-- Datos de prueba para la tabla DEVELOPER
INSERT INTO DEVELOPER (NAME) VALUES
('Ubisoft'),
('Electronic Arts'),
('Rockstar Games'),
('Bethesda Softworks'),
('Nintendo');

-- Datos de prueba para la tabla VIDEOGAME
INSERT INTO VIDEOGAME (NAME, RELEASEYEAR, PLATFORM_ID, GENRE_ID, DEVELOPER_ID) VALUES
('Assassin''s Creed Odyssey', 2018, 1, 1, 1),
('Red Dead Redemption 2', 2018, 2, 1, 3),
('The Legend of Zelda: Breath of the Wild', 2017, 3, 2, 5),
('The Witcher 3: Wild Hunt', 2015, 4, 3, 4),
('FIFA 21', 2020, 1, 1, 2),
('Animal Crossing: New Horizons', 2020, 3, 4, 5);

-- Datos de prueba para la tabla PLAYER_VIDEOGAME
INSERT INTO PLAYER_VIDEOGAME (PLAYER_ID, VIDEOGAME_ID, DIGITAL, PHYSICAL_STATUS, PURCHASE_DATE) VALUES
(1, 1, false, 'New', '2021-01-15'),
(2, 2, false, 'Used', '2020-10-20'),
(3, 3, true, null, null),
(1, 4, false, 'Damaged', '2019-05-10'),
(2, 5, true, null, null),
(3, 6, false, 'New', '2020-03-25');
