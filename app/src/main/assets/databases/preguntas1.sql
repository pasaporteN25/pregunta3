BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "preguntas" (
	"id"	INTEGER,
	"pregunta"	TEXT NOT NULL,
	"corr_resp"	TEXT NOT NULL,
	"incorr_resp"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
INSERT INTO "preguntas" ("id","pregunta","corr_resp","incorr_resp") VALUES (1,'¿Cual es la capital de Buenos Aires?','La Plata','CABA, Quilmes, AMBA');
INSERT INTO "preguntas" ("id","pregunta","corr_resp","incorr_resp") VALUES (2,'¿Cual es la capital de Estados Unidos?','Washington','Nueva York, Texas, California');
INSERT INTO "preguntas" ("id","pregunta","corr_resp","incorr_resp") VALUES (3,'¿Cual es la capital de Italia?','Roma','Calabria, Sicilia, Piamonte');
INSERT INTO "preguntas" ("id","pregunta","corr_resp","incorr_resp") VALUES (4,'¿Cual es la capital de Egipto?','El Cairo','Petra, Alejandria, Guiza');
INSERT INTO "preguntas" ("id","pregunta","corr_resp","incorr_resp") VALUES (5,'¿Cual es la capital de Japon?','Tokio','Kioto, Ikebukuro, Okinawa');
COMMIT;
