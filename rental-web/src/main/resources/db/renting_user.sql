CREATE USER 'renting'@'%' IDENTIFIED BY 'renting';
GRANT ALL ON renting.* TO 'renting'@'%';
FLUSH PRIVILEGES;