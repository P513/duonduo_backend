CREATE TABLE users(

                             id int NOT NULL AUTO_INCREMENT,

                             username varchar(10),

                             password varchar(12) NOT NULL,

                             firstName varchar(10),

                             lastName varchar(10),

                             age tinyint,

                             salary int,

                             PRIMARY KEY(id)

       );