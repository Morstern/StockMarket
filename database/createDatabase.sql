CREATE DATABASE `stock-market`;

USE `stock-market`;
CREATE TABLE `stock` (
   `ISIN` varchar(12) NOT NULL,
   `name` varchar(45) NOT NULL,
   PRIMARY KEY (`ISIN`)
 );
CREATE TABLE `stockprice` (
  `idstockprice` int NOT NULL AUTO_INCREMENT,
  `ISIN` varchar(12) NOT NULL,
  `buyout` decimal(15,4) NOT NULL,
  `sellout` decimal(15,4) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`idstockprice`),
  KEY `ISIN_idx` (`ISIN`),
  CONSTRAINT `FKe2eg0lswooqesws930gqff3ch` FOREIGN KEY (`ISIN`) REFERENCES `stock` (`ISIN`),
  CONSTRAINT `ISIN_SP` FOREIGN KEY (`ISIN`) REFERENCES `stock` (`ISIN`)
);
CREATE TABLE `user` (
  `iduser` int NOT NULL AUTO_INCREMENT,
  `email` varchar(60) NOT NULL,
  `password` varchar(128) NOT NULL,
  `role` varchar(20) NOT NULL,
  `non_expired` tinyint NOT NULL,
  `non_locked` tinyint NOT NULL,
  `credentials_non_expired` tinyint NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `email_UNIQUE` (`email`)
);
CREATE TABLE `stocksubscription` (
  `ISIN` varchar(12) NOT NULL,
  `iduser` int NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`ISIN`,`iduser`),
  CONSTRAINT `FKkm7f2dfqfidbb2agqqmtkoyqh` FOREIGN KEY (`ISIN`) REFERENCES `stock` (`ISIN`),
  CONSTRAINT `iduser_SS` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`),
  CONSTRAINT `ISIN_SS` FOREIGN KEY (`ISIN`) REFERENCES `stock` (`ISIN`)
) 
CREATE TABLE `userstock` (
  `iduserstock` int NOT NULL AUTO_INCREMENT,
  `ISIN` varchar(12) NOT NULL,
  `iduser` int NOT NULL,
  `amount` int NOT NULL,
  `price` decimal(15,4) NOT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`iduserstock`),
  KEY `ISIN_SU_idx` (`ISIN`),
  KEY `iduser_SU_idx` (`iduser`),
  CONSTRAINT `FK9r3i817cgoj8yidy9b11sew9o` FOREIGN KEY (`ISIN`) REFERENCES `stock` (`ISIN`),
  CONSTRAINT `ISIN_US` FOREIGN KEY (`ISIN`) REFERENCES `stock` (`ISIN`),
  CONSTRAINT `userid_US` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`)
)