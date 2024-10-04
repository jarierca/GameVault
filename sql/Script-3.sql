-- SHOW ALL TABLES
SELECT TABLE_NAME
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'public'; 

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

-- COLLECTION
SELECT * FROM GAMECOLLECTION;

-- COLLECTION_VIDEOGAME
SELECT * FROM COLLECTION_VIDEOGAME;


SELECT V.* 
FROM VIDEOGAME V 
	JOIN PLATFORM P ON P.ID = V.PLATFORM_ID 


SELECT P.USERNAME, V.*
FROM VIDEOGAME V 
	JOIN COLLECTION_VIDEOGAME CV ON CV.VIDEOGAME_ID = V.ID 
	JOIN GAMECOLLECTION G ON G.ID = CV.COLLECTION_ID
	JOIN PLAYER P ON P.ID = G.PLAYER_ID 
WHERE G.PLAYER_ID = :playerId
GROUP BY P.USERNAME, V.id
ORDER BY V.TITLE 


SELECT cv.* 
from COLLECTION_VIDEOGAME CV 
join GAMECOLLECTION G ON CV.COLLECTION_ID = g.ID 
join PLAYER P on p.ID = g.PLAYER_ID 
where p.ID = :playerId
	and cv.ID = :collectionVideogameId 

SELECT G 
FROM GAMECOLLECTION G 
JOIN PLAYER P ON P.ID = G.PLAYER_ID 
WHERE P.ID = :playerId 
	AND G.ID = :collectionId
	
	
SELECT V.TITLE, G.NAME 
FROM VIDEOGAME V 
	JOIN GENRE G ON G.ID = V.GENRE_ID 
 
	
	
select * from GAMECOLLECTION G
join PLAYER P on p.ID = g.PLAYER_ID 
where p.USERNAME = 'devi'

