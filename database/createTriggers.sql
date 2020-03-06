DELIMITER //
CREATE TRIGGER `user_BEFORE_DELETE` BEFORE DELETE 
ON `user`
FOR EACH ROW BEGIN
DELETE FROM `stocksubscription` WHERE `iduser` = OLD.iduser;
DELETE FROM `userstock` WHERE `iduser` = OLD.iduser;
END//
DELIMITER ;