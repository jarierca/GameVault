-- PLAYER
SELECT * FROM PLAYER;

-- PLATFORM
SELECT * FROM PLATFORM;

-- GENRE
SELECT * FROM GENRE;

-- DEVELOPER
SELECT * FROM DEVELOPER;

-- PUBLISHER
SELECT * FROM PUBLISHER;

-- VIDEOGAME
SELECT * FROM VIDEOGAME;

-- PLAYER_VIDEOGAME
SELECT * FROM PLAYER_VIDEOGAME;


select v.* from videogame v 
join platform p on p.id = v.platform_id 

select v.*
from videogame v 
join player_videogame pv ON pv.videogame_id = pv.player_id 
join player p on v.id = pv.player_id 
where p.username = 'javi'
	

select v.title,g."name" from videogame v 
join genre g on g.id = v.genre_id 
 