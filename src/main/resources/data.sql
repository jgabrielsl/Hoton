INSERT INTO `hoton`.`usuario` (`email`, `enabled`, `first_name`, `last_name`, `password`) VALUES ('victor.rafalucca@gmail.com', 1, 'victor', 'lucca', '$2y$12$CZp1e5z0/eu4I5z.9GWX7O3KD6I7l6d8cbI/nV.ai.5WZDPSGleoG');

INSERT INTO `hoton`.`usuario` (`email`, `enabled`, `first_name`, `last_name`, `password`) VALUES ('gjoao7277@gmail.com', 1, 'Joao', 'lucca', '$2y$12$CZp1e5z0/eu4I5z.9GWX7O3KD6I7l6d8cbI/nV.ai.5WZDPSGleoG');

INSERT INTO `hoton`.`usuario` (`email`, `enabled`, `first_name`, `last_name`, `password`) VALUES ('jonatandrumond@yahoo.com.br', 1, 'Jonatan', 'Drumond', '$2y$12$CZp1e5z0/eu4I5z.9GWX7O3KD6I7l6d8cbI/nV.ai.5WZDPSGleoG');

INSERT INTO `hoton`.`role` (`name`) VALUES ('ADMIN');

INSERT INTO `hoton`.`role` (`name`) VALUES ('USER');

INSERT INTO `hoton`.`usuario_roles` (`usuario_id`, `role_id`) VALUES (1, 1);

INSERT INTO `hoton`.`usuario_roles` (`usuario_id`, `role_id`) VALUES (1, 2);

INSERT INTO `hoton`.`usuario_roles` (`usuario_id`, `role_id`) VALUES (2, 1);

INSERT INTO `hoton`.`usuario_roles` (`usuario_id`, `role_id`) VALUES (2, 2);

INSERT INTO `hoton`.`usuario_roles` (`usuario_id`, `role_id`) VALUES (3, 1);

INSERT INTO `hoton`.`usuario_roles` (`usuario_id`, `role_id`) VALUES (3, 2);

commit;