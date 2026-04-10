INSERT INTO User (idUser, username, email, password, profilePicture)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'danisanc', 'daniel@test.com',
        '$2a$10$8.UnVuG9HHgffUDAlk8q6Ou5HECyZ4zR0h2F6j6.3A.T6fW9L3bW.',
        'https://api.dicebear.com/7.x/avataaars/svg?seed=Daniel'),
       ('a1b2c3d4-e5f6-4a5b-b6c7-d8e9f0a1b2c3', 'caro_dev', 'caro@test.com',
        '$2a$10$8.UnVuG9HHgffUDAlk8q6Ou5HECyZ4zR0h2F6j6.3A.T6fW9L3bW.',
        'https://api.dicebear.com/7.x/avataaars/svg?seed=Caro'),
       ('f47ac10b-58cc-4372-a567-0e02b2c3d479', 'profe_java', 'profe@test.com',
        '$2a$10$8.UnVuG9HHgffUDAlk8q6Ou5HECyZ4zR0h2F6j6.3A.T6fW9L3bW.',
        'https://api.dicebear.com/7.x/avataaars/svg?seed=Profe');

INSERT INTO Post (idPost, text, deadline, isPublic, isActive, idUser)
VALUES ('11111111-1111-1111-1111-111111111111', '¡Hola mundo! Mi primer post en la red social.', '2026-12-31 23:59:59',
        TRUE, TRUE, '550e8400-e29b-41d4-a716-446655440000');

INSERT INTO Post (idPost, text, deadline, isPublic, isActive, idUser)
VALUES ('22222222-2222-2222-2222-222222222222', 'Este es un anuncio temporal que expirará pronto.',
        '2026-04-02 12:00:00', TRUE, TRUE, 'a1b2c3d4-e5f6-4a5b-b6c7-d8e9f0a1b2c3');

INSERT INTO Content (idContent, link, type, idPost)
VALUES ('c1c1c1c1-c1c1-c1c1-c1c1-c1c1c1c1c1c1', 'https://images.unsplash.com/photo-1517694712202-14dd9538aa97', 'JPEG',
        '11111111-1111-1111-1111-111111111111'),
       ('d2d2d2d2-d2d2-d2d2-d2d2-d2d2d2d2d2d2', 'https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExNHJq/giphy.gif',
        'GIF', '11111111-1111-1111-1111-111111111111');

INSERT INTO `Like` (idPost, idUser)
VALUES ('11111111-1111-1111-1111-111111111111', 'a1b2c3d4-e5f6-4a5b-b6c7-d8e9f0a1b2c3'),
       ('11111111-1111-1111-1111-111111111111', 'f47ac10b-58cc-4372-a567-0e02b2c3d479');

INSERT INTO Follower (idFollowed, idFollower)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'a1b2c3d4-e5f6-4a5b-b6c7-d8e9f0a1b2c3'),
       ('550e8400-e29b-41d4-a716-446655440000', 'f47ac10b-58cc-4372-a567-0e02b2c3d479'),
       ('a1b2c3d4-e5f6-4a5b-b6c7-d8e9f0a1b2c3', '550e8400-e29b-41d4-a716-446655440000');