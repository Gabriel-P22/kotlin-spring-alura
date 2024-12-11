INSERT INTO users VALUES (2, 'user test', 'admin@email.com', '$2a$12$NOe6Wtc6exyE7VNQa4Dz.uaoT.q55i4WfNIvMzMOAmq.3C2IH4G0a');
INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO user_role (user_id, role_id) VALUES(2, 3)