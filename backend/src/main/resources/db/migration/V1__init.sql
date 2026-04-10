CREATE TABLE User
(
    -- MySQL doesn't have a native UUID, we will use VARCHAR(36)
    idUser         VARCHAR(36) PRIMARY KEY,
    username       VARCHAR(50) UNIQUE NOT NULL,
    email          VARCHAR(50) UNIQUE NOT NULL,
    password       VARCHAR(70)        NOT NULL,
    profilePicture VARCHAR(255)       NOT NULL DEFAULT 'https://default-avatar.com/user.png',
    createdAt      TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt      TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Post
(
    idPost    VARCHAR(36) PRIMARY KEY,
    text      VARCHAR(255) NOT NULL,
    deadline  TIMESTAMP    NOT NULL,
    isPublic  BOOLEAN      NOT NULL DEFAULT TRUE,
    isActive  BOOLEAN      NOT NULL DEFAULT TRUE,
    idUser    VARCHAR(36)  NOT NULL,
    createdAt TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fkUserPost FOREIGN KEY (idUser)
        REFERENCES User (idUser) ON DELETE CASCADE
);

CREATE TABLE Content
(
    idContent VARCHAR(36) PRIMARY KEY,
    link      VARCHAR(255)                                      NOT NULL,
    type      ENUM ('GIF', 'MP4', 'JPEG', 'JPG', 'PNG', 'WEBP') NOT NULL,
    idPost    VARCHAR(36)                                       NOT NULL,
    CONSTRAINT fkPostContent FOREIGN KEY (idPost)
        REFERENCES Post (idPost) ON DELETE CASCADE
);

-- 'Like' is a keyword, use backticks when referencing this table
CREATE TABLE `Like`
(
    idPost    VARCHAR(36) NOT NULL,
    idUser    VARCHAR(36) NOT NULL,
    createdAt TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (idPost, idUser),
    CONSTRAINT fkLikePost FOREIGN KEY (idPost) REFERENCES Post (idPost) ON DELETE CASCADE,
    CONSTRAINT fkLikeUser FOREIGN KEY (idUser) REFERENCES User (idUser) ON DELETE CASCADE
);

CREATE TABLE Follower
(
    idFollowed VARCHAR(36) NOT NULL,
    idFollower VARCHAR(36) NOT NULL,
    createdAt  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (idFollowed, idFollower),
    CONSTRAINT fkFollowed FOREIGN KEY (idFollowed) REFERENCES User (idUser) ON DELETE CASCADE,
    CONSTRAINT fkFollower FOREIGN KEY (idFollower) REFERENCES User (idUser) ON DELETE CASCADE,
    CONSTRAINT checkNotSelfFollow CHECK (idFollowed <> idFollower)
);

CREATE INDEX idxPostIsActive ON Post (isActive);
CREATE INDEX idxPostDeadline ON Post (deadline);
CREATE INDEX idxUserUsername ON User (username);