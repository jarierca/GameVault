-- Selección de todos los datos de la tabla PLAYER
SELECT * FROM PLAYER;

-- Selección de todos los datos de la tabla PLATFORM
SELECT * FROM PLATFORM;

-- Selección de todos los datos de la tabla GENRE
SELECT * FROM GENRE;

-- Selección de todos los datos de la tabla DEVELOPER
SELECT * FROM DEVELOPER;

-- Selección de todos los datos de la tabla VIDEOGAME
SELECT * FROM VIDEOGAME;

-- Selección de todos los datos de la tabla PLAYER_VIDEOGAME
SELECT * FROM PLAYER_VIDEOGAME;


select v.*
from videogame v 
join player_videogame pv ON pv.videogame_id = pv.player_id 
join player p on v.id = pv.player_id 
where p.username = 'javi'
	