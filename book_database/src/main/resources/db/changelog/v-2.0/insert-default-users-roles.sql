INSERT INTO `users` (`username`, `age`, `password`)
    VALUE   ('maxim', '31', '$2a$10$kztkKK2oECmldPNRUfHU.e3LYcnoxu3W2uAmY6Q9iiNmUIERm0A..'),
        ('guest', '30', '$2a$10$kztkKK2oECmldPNRUfHU.e3LYcnoxu3W2uAmY6Q9iiNmUIERm0A..');
GO

INSERT INTO `roles` (`name`)
VALUE ('ROLE_ADMIN'), ('ROLE_GUEST');
GO

INSERT INTO `users_roles`(`user_id`, `role_id`)
SELECT (SELECT id FROM `users` WHERE `username` = 'maxim'), (SELECT id FROM `roles` WHERE `name` = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT id FROM `users` WHERE `username` = 'guest'), (SELECT id FROM `roles` WHERE `name` = 'ROLE_GUEST');
GO
