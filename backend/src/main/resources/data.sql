INSERT INTO Persons_Data (first_name, last_name, email, password, role)
VALUES ('Admin', 'User', 'admin@example.com', '$2a$12$g.CwlGZ0x6tZx.mSzAJFbOeD2TYniH72hYN6CyVdiPFt17PtTg7H.', 1);

INSERT INTO admin (admin_id) VALUES ((SELECT id FROM Persons_Data WHERE email = 'admin@example.com'));