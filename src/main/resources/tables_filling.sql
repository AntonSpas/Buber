INSERT INTO `buber`.`admins` (`login`, `password`) VALUES 
("first_admin", "123000321"),
("second_admin", "yoho003");

UPDATE admins SET password=SHA1('freedom003') WHERE id=1;
UPDATE admins SET password=SHA1('yoho003') WHERE id=2;

INSERT INTO districts VALUES
  ('Tsentralny'),
  ('Savetski'),
  ('Pershamayski'),
  ('Zavodski');

INSERT INTO streets VALUES
  ('Aeroflotskaya', 'Tsentralny'),
  ('Bogdanovicha', 'Tsentralny'),
  ('Chaykovskogo', 'Tsentralny'),
  ('Daumana', 'Savetski'),
  ('Dimitrova', 'Savetski'),
  ('Engelsa', 'Savetski'),
  ('Fedorova', 'Pershamayski'),
  ('Filimonova', 'Pershamayski'),
  ('Golodeda', 'Pershamayski'),
  ('Izmailovskaya', 'Zavodski'),
  ('Kalinina', 'Zavodski'),
  ('Lopatina', 'Zavodski');

INSERT INTO distance VALUES
  (3.25, 'Tsentralny', 'Savetski'),
  (4.55, 'Tsentralny', 'Pershamayski'),
  (6.85, 'Tsentralny', 'Zavodski'),
  (5.15, 'Savetski', 'Pershamayski'),
  (2.95, 'Savetski', 'Zavodski'),
  (4.35, 'Pershamayski', 'Zavodski');

