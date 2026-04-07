-- 1. Tabla de Usuarios
CREATE TABLE User (
                      idUser VARCHAR(36) PRIMARY KEY, -- MySQL no tiene tipo UUID nativo, usamos VARCHAR(36)
                      username VARCHAR(50) UNIQUE NOT NULL,
                      email VARCHAR(50) UNIQUE NOT NULL,
                      password VARCHAR(70) NOT NULL,
                      profilePicture VARCHAR(255) NOT NULL DEFAULT 'https://default-avatar.com/user.png',
                      createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 2. Tabla de Posts
CREATE TABLE Post (
                      idPost VARCHAR(36) PRIMARY KEY,
                      text VARCHAR(255) NOT NULL,
                      deadline TIMESTAMP NOT NULL,
                      isPublic BOOLEAN NOT NULL DEFAULT TRUE,
                      isActive BOOLEAN NOT NULL DEFAULT TRUE,
                      idUser VARCHAR(36) NOT NULL,
                      createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      CONSTRAINT fkUserPost FOREIGN KEY (idUser)
                          REFERENCES User(idUser) ON DELETE CASCADE
);

-- 3. Tabla de Contenido (Multimedia)
-- En MySQL, el ENUM se define directamente así:
CREATE TABLE Content (
                         idContent VARCHAR(36) PRIMARY KEY,
                         link VARCHAR(255) NOT NULL,
                         type ENUM('GIF', 'MP4', 'JPEG', 'JPG', 'PNG', 'WEBP') NOT NULL,
                         idPost VARCHAR(36) NOT NULL,
                         CONSTRAINT fkPostContent FOREIGN KEY (idPost)
                             REFERENCES Post(idPost) ON DELETE CASCADE
);

-- 4. Tabla de Likes
CREATE TABLE `Like` ( -- 'Like' es palabra reservada en MySQL, se rodea con comillas simples invertidas
                        idPost VARCHAR(36) NOT NULL,
                        idUser VARCHAR(36) NOT NULL,
                        createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (idPost, idUser),
                        CONSTRAINT fkLikePost FOREIGN KEY (idPost) REFERENCES Post(idPost) ON DELETE CASCADE,
                        CONSTRAINT fkLikeUser FOREIGN KEY (idUser) REFERENCES User(idUser) ON DELETE CASCADE
);

-- 5. Tabla de Seguidores
CREATE TABLE Follower (
                          idFollowed VARCHAR(36) NOT NULL,
                          idFollower VARCHAR(36) NOT NULL,
                          createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (idFollowed, idFollower),
                          CONSTRAINT fkFollowed FOREIGN KEY (idFollowed) REFERENCES User(idUser) ON DELETE CASCADE,
                          CONSTRAINT fkFollower FOREIGN KEY (idFollower) REFERENCES User(idUser) ON DELETE CASCADE,
                          CONSTRAINT checkNotSelfFollow CHECK (idFollowed <> idFollower)
);

-- Índices
CREATE INDEX idxPostIsActive ON Post(isActive);
CREATE INDEX idxPostDeadline ON Post(deadline);
CREATE INDEX idxUserUsername ON User(username);