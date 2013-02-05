/*
 Navicat Premium Data Transfer

 Source Server         : mySQL
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost
 Source Database       : Charity_DB_Test_Model

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : utf-8

 Date: 01/30/2013 02:21:38 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `Access_Log`
-- ----------------------------
DROP TABLE IF EXISTS `Access_Log`;
CREATE TABLE `Access_Log` (
  `Access_Log_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Form_Id` int(11) NOT NULL,
  `Field_Id` int(11) DEFAULT NULL,
  `User_Id` int(11) NOT NULL,
  `Access_Start_Time` datetime DEFAULT '1970-01-01 00:00:00',
  `Access_End_Time` datetime DEFAULT '1970-01-01 00:00:00',
  `Device` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Location` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `isOnline` tinyint(4) NOT NULL DEFAULT '0',
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Access_Log_Id`),
  KEY `Form_Id` (`Form_Id`),
  KEY `Field_Id` (`Field_Id`),
  KEY `User_Id` (`User_Id`),
  CONSTRAINT `FK_Access_Log_Form_Fields_ID` FOREIGN KEY (`Field_Id`) REFERENCES `Form_Fields` (`Field_Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_Access_Log_Form_ID` FOREIGN KEY (`Form_Id`) REFERENCES `Form` (`Form_Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_Access_Log_Users_ID` FOREIGN KEY (`User_Id`) REFERENCES `Users` (`User_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=ascii;

-- ----------------------------
--  Table structure for `Event`
-- ----------------------------
DROP TABLE IF EXISTS `Event`;
CREATE TABLE `Event` (
  `Event_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Event_Name` varchar(255) NOT NULL,
  `Event_Description` text NOT NULL,
  `Event_Location` varchar(255) NOT NULL,
  `Event_Date` date NOT NULL,
  `Event_Time` time NOT NULL,
  `User_Id` int(11) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Event_Id`),
  KEY `User_Id` (`User_Id`),
  CONSTRAINT `FK_Event_User_Id` FOREIGN KEY (`User_Id`) REFERENCES `Users` (`User_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `Event`
-- ----------------------------
BEGIN;
INSERT INTO `Event` VALUES ('1', 'Hope Fund Raising', 'Fund Raising ', 'London', '2013-01-07', '09:00:00', '1', '2013-01-26 13:16:08'), ('4', 'Hope Carnival Party', '', 'London', '2013-02-17', '19:00:00', '1', '2013-01-26 13:17:42'), ('6', 'Hope Valentines Party', '', 'London', '2013-02-14', '18:00:00', '2', '2013-01-26 16:58:00');
COMMIT;

-- ----------------------------
--  Table structure for `Field_Selection`
-- ----------------------------
DROP TABLE IF EXISTS `Field_Selection`;
CREATE TABLE `Field_Selection` (
  `Field_Selection_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Field_Id` int(11) NOT NULL,
  `Field_Selection_Value` varchar(255) NOT NULL,
  `Timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Field_Selection_Id`),
  KEY `Field_Id` (`Field_Id`),
  CONSTRAINT `FK_Field_Selection_Form_Field` FOREIGN KEY (`Field_Id`) REFERENCES `Form_Fields` (`Field_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `Field_Selection`
-- ----------------------------
BEGIN;
INSERT INTO `Field_Selection` VALUES ('1', '3', 'Male', '2013-01-25 18:29:20'), ('2', '3', 'Female', '2013-01-25 20:52:57'), ('3', '8', 'American Indian', '2013-01-28 20:01:41'), ('4', '8', 'Asian', '2013-01-28 20:01:59'), ('5', '8', 'African American', '2013-01-28 20:02:09'), ('6', '8', 'Hispanic', '2013-01-28 20:02:21'), ('8', '8', 'White', '2013-01-28 20:02:46'), ('9', '8', 'Other', '2013-01-28 20:02:53'), ('15', '3', 'NA', '2013-01-28 20:34:01');
COMMIT;

-- ----------------------------
--  Table structure for `Field_Type`
-- ----------------------------
DROP TABLE IF EXISTS `Field_Type`;
CREATE TABLE `Field_Type` (
  `Field_Type_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Field_Type` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Field_DataType` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Field_Description` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT '1',
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Field_Type_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=ascii;

-- ----------------------------
--  Records of `Field_Type`
-- ----------------------------
BEGIN;
INSERT INTO `Field_Type` VALUES ('1', 'Text', 'String', 'Textbox - Alphanumeric', '1', '2013-01-25 15:01:17'), ('2', 'Number', 'Integer', 'Textbox - Numeric', '1', '2013-01-25 15:10:27'), ('3', 'Text', 'String', 'Dropdown', '1', '2013-01-28 20:09:31'), ('5', 'Date', 'Date', 'Date', '1', '2013-01-25 15:09:18'), ('6', 'Checkbox', 'Tinyint', 'Checkbox', '1', '2013-01-28 20:09:42'), ('7', 'Image', 'Longblob', 'Image', '1', '2013-01-25 15:14:30');
COMMIT;

-- ----------------------------
--  Table structure for `Form`
-- ----------------------------
DROP TABLE IF EXISTS `Form`;
CREATE TABLE `Form` (
  `Form_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Form_Type_Id` int(11) DEFAULT NULL,
  `Form_Name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Date_Created` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `URL` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT '1',
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Form_Id`),
  KEY `Form_Type_Id` (`Form_Type_Id`),
  CONSTRAINT `FK_Form_Form_Type_ID` FOREIGN KEY (`Form_Type_Id`) REFERENCES `Form_Type` (`Form_Type_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=ascii;

-- ----------------------------
--  Records of `Form`
-- ----------------------------
BEGIN;
INSERT INTO `Form` VALUES ('1', '1', 'Input_Form', '2013-01-20 20:57:24', null, '1', '2013-01-20 20:57:30'), ('2', '1', 'Personal Information', '2013-01-28 19:48:16', null, '1', '2013-01-28 19:48:25'), ('3', '1', 'Location Details', '2013-01-28 19:48:45', null, '1', '2013-01-28 19:48:53'), ('4', '1', 'Medical Condition', '2013-01-28 19:49:25', null, '1', '2013-01-28 19:49:54'), ('5', '1', 'Background Details', '2013-01-28 19:49:46', null, '1', '2013-01-28 19:49:59');
COMMIT;

-- ----------------------------
--  Table structure for `Form_Permissions`
-- ----------------------------
DROP TABLE IF EXISTS `Form_Permissions`;
CREATE TABLE `Form_Permissions` (
  `Form_Id` int(11) NOT NULL,
  `User_Type_Id` int(11) NOT NULL,
  `Permission` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Form_Id`,`User_Type_Id`),
  KEY `Form_Id` (`Form_Id`),
  KEY `User_Type_Id` (`User_Type_Id`),
  CONSTRAINT `FK_Form_Permissions_Form_ID` FOREIGN KEY (`Form_Id`) REFERENCES `Form` (`Form_Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_Form_Permissions_User_Type_ID` FOREIGN KEY (`User_Type_Id`) REFERENCES `User_Type` (`User_Type_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=ascii;

-- ----------------------------
--  Records of `Form_Permissions`
-- ----------------------------
BEGIN;
INSERT INTO `Form_Permissions` VALUES ('1', '1', null, b'1', '2013-01-30 01:29:33'), ('1', '2', null, b'1', '2013-01-30 01:30:17'), ('2', '1', null, b'1', '2013-01-30 01:29:45'), ('2', '2', null, b'1', '2013-01-30 01:29:55'), ('4', '1', null, b'1', '2013-01-30 01:30:31'), ('5', '1', null, b'1', '2013-01-30 01:30:47');
COMMIT;

-- ----------------------------
--  Table structure for `Form_Type`
-- ----------------------------
DROP TABLE IF EXISTS `Form_Type`;
CREATE TABLE `Form_Type` (
  `Form_Type_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Form_Type` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `isActive` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Form_Type_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=ascii;

-- ----------------------------
--  Records of `Form_Type`
-- ----------------------------
BEGIN;
INSERT INTO `Form_Type` VALUES ('1', 'Input_Form', '1'), ('2', 'Donation_Form', '1'), ('3', 'Calendar_Form', '1');
COMMIT;

-- ----------------------------
--  Table structure for `Mailing_Group`
-- ----------------------------
DROP TABLE IF EXISTS `Mailing_Group`;
CREATE TABLE `Mailing_Group` (
  `Mailing_Group_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Mailing_Group` varchar(255) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Mailing_Group_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `Mailing_List`
-- ----------------------------
DROP TABLE IF EXISTS `Mailing_List`;
CREATE TABLE `Mailing_List` (
  `Mailing_Group_Id` int(11) NOT NULL,
  `User_Id` int(11) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Mailing_Group_Id`,`User_Id`),
  KEY `Mailing_Group_Id` (`Mailing_Group_Id`),
  KEY `User_Id` (`User_Id`),
  CONSTRAINT `FK_Mailing_List_Mailing_Group_Id` FOREIGN KEY (`Mailing_Group_Id`) REFERENCES `Mailing_Group` (`Mailing_Group_Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_Mailing_List_User_Id` FOREIGN KEY (`User_Id`) REFERENCES `Users` (`User_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `User_Type`
-- ----------------------------
DROP TABLE IF EXISTS `User_Type`;
CREATE TABLE `User_Type` (
  `User_Type_Id` int(11) NOT NULL AUTO_INCREMENT,
  `User_Type` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `User_Type_Description` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT '1',
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`User_Type_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=ascii;

-- ----------------------------
--  Records of `User_Type`
-- ----------------------------
BEGIN;
INSERT INTO `User_Type` VALUES ('1', 'Charity_Administrator', null, '1', '2013-01-20 16:24:15'), ('2', 'Charity_Worker', null, '1', '2013-01-20 16:24:26');
COMMIT;

-- ----------------------------
--  Table structure for `Users`
-- ----------------------------
DROP TABLE IF EXISTS `Users`;
CREATE TABLE `Users` (
  `User_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(20) CHARACTER SET utf8 NOT NULL,
  `User_Type_Id` int(11) NOT NULL,
  `User_Password` varchar(250) CHARACTER SET utf8 NOT NULL,
  `Salt` varchar(10) NOT NULL,
  `User_Email` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `Date_Created` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `isActive` tinyint(1) NOT NULL DEFAULT '1',
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`User_Id`),
  KEY `User_Type_Id` (`User_Type_Id`),
  CONSTRAINT `FK_User_User_Type_ID` FOREIGN KEY (`User_Type_Id`) REFERENCES `User_Type` (`User_Type_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=ascii;

-- ----------------------------
--  Records of `Users`
-- ----------------------------
BEGIN;
INSERT INTO `Users` VALUES ('1', 'lchirchop', '1', '', '', null, '2013-01-20 20:49:45', '1', '2013-01-20 20:50:04'), ('2', 'yfarrugia', '2', '', '', null, '2013-01-20 20:50:41', '1', '2013-01-20 20:50:50'), ('3', 'amartin', '2', '', '', null, '2013-01-20 20:51:13', '1', '2013-01-20 20:51:22'), ('4', 'asood', '2', '', '', null, '2013-01-20 20:51:38', '1', '2013-01-20 20:51:50'), ('5', 'iblue', '2', '', '', null, '2013-01-20 20:51:50', '1', '2013-01-20 20:52:24'), ('6', 'mnajim', '2', '', '', null, '2013-01-20 20:53:22', '1', '2013-01-20 20:53:32'), ('7', 'wbalasundaram', '2', '', '', null, '2013-01-20 20:54:28', '1', '2013-01-20 20:54:38');
COMMIT;

-- ----------------------------
--  Table structure for `filled_form`
-- ----------------------------
DROP TABLE IF EXISTS `filled_form`;
CREATE TABLE `filled_form` (
  `Filled_Form_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Field_Id` int(11) DEFAULT NULL,
  `Value` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `User_Id` int(11) DEFAULT NULL,
  `Record_Id` int(11) NOT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT '1',
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Filled_Form_Id`),
  KEY `Field_Id` (`Field_Id`),
  KEY `FK74012581C8251CD5` (`User_Id`),
  CONSTRAINT `FK74012581C8251CD5` FOREIGN KEY (`User_Id`) REFERENCES `Users` (`User_Id`),
  CONSTRAINT `FK_Filled_Form_Form_Fields_ID` FOREIGN KEY (`Field_Id`) REFERENCES `Form_Fields` (`Field_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=ascii;

-- ----------------------------
--  Records of `filled_form`
-- ----------------------------
BEGIN;
INSERT INTO `filled_form` VALUES ('1', '1', 'Axel', '2', '1', '1', '2013-01-25 16:52:06'), ('2', '2', 'Smith', '2', '1', '1', '2013-01-25 16:52:14'), ('3', '1', 'Mark', '2', '2', '1', '2013-01-25 16:52:17'), ('4', '2', 'Hans', '2', '2', '1', '2013-01-25 16:52:20'), ('5', '1', 'Rebecca', '3', '3', '1', '2013-01-25 16:52:22'), ('6', '2', 'Williams', '3', '3', '1', '2013-01-25 16:52:24'), ('7', '1', 'Isabel', '2', '4', '1', '2013-01-25 16:52:27'), ('8', '2', 'Williams', '2', '4', '1', '2013-01-25 16:52:29'), ('9', '1', 'John', '4', '5', '1', '2013-01-25 16:52:31'), ('10', '2', 'Cross', '4', '5', '1', '2013-01-25 16:52:33'), ('11', '1', 'Elton', '3', '6', '1', '2013-01-25 16:52:36'), ('12', '2', 'Frey', '3', '6', '1', '2013-01-25 16:52:38'), ('13', '1', 'Kim', '5', '7', '1', '2013-01-25 16:52:40'), ('14', '2', 'Green', '5', '7', '1', '2013-01-25 16:52:44'), ('15', '1', 'Adam', '5', '8', '1', '2013-01-25 16:52:46'), ('16', '2', 'Bone', '5', '8', '1', '2013-01-25 16:52:48'), ('17', '1', 'Amy', '6', '9', '1', '2013-01-25 16:52:52'), ('18', '2', 'Wells', '6', '9', '1', '2013-01-25 16:52:54'), ('19', '1', 'Harry', '5', '10', '1', '2013-01-25 16:52:57'), ('20', '2', 'Pace', '5', '10', '1', '2013-01-25 16:53:00'), ('21', '1', 'Simon', '6', '11', '1', '2013-01-25 16:53:02'), ('22', '2', 'Attard', '6', '11', '1', '2013-01-25 16:53:04'), ('23', '1', 'Matt', '5', '12', '1', '2013-01-25 16:53:07'), ('24', '2', 'Stivala', '5', '12', '1', '2013-01-25 16:53:09'), ('25', '1', 'Chris', '2', '13', '1', '2013-01-25 16:53:13'), ('26', '2', 'Gilmor', '2', '13', '1', '2013-01-25 16:53:16'), ('27', '1', 'Fran', '4', '14', '1', '2013-01-25 16:53:25'), ('28', '2', 'Nicholas', '4', '14', '1', '2013-01-25 16:53:28'), ('29', '1', 'Roger', '6', '15', '1', '2013-01-25 16:53:31'), ('30', '2', 'Gilford', '6', '15', '1', '2013-01-25 16:53:33'), ('31', '1', 'David', '2', '16', '1', '2013-01-25 16:53:36'), ('32', '2', 'Waters', '2', '16', '1', '2013-01-25 16:53:43'), ('33', '1', 'Syd', '7', '17', '1', '2013-01-25 16:57:04'), ('34', '2', 'Barret', '7', '17', '1', '2013-01-25 16:57:04'), ('35', '1', 'Rod', '7', '18', '1', '2013-01-25 16:57:24'), ('36', '2', 'Reeds', '7', '18', '1', '2013-01-25 16:57:24'), ('37', '3', 'Male', '7', '18', '1', '2013-01-25 16:57:24'), ('38', '3', 'Male', '7', '17', '1', '2013-01-25 16:57:04'), ('39', '3', 'Male', '2', '16', '1', '2013-01-28 20:23:08'), ('40', '3', 'Male', '6', '15', '1', '2013-01-28 20:23:02'), ('41', '3', 'Male', '4', '14', '1', '2013-01-28 20:22:59'), ('42', '3', 'Male', '2', '13', '1', '2013-01-28 20:24:01'), ('43', '3', 'Male', '5', '12', '1', '2013-01-28 20:24:41'), ('44', '3', 'Male', '6', '11', '1', '2013-01-28 20:25:07'), ('45', '3', 'Male', '5', '10', '1', '2013-01-28 20:25:36'), ('46', '3', 'Female', '6', '9', '1', '2013-01-28 20:26:09'), ('47', '3', 'Male', '5', '8', '1', '2013-01-28 20:27:04'), ('48', '3', 'Female', '5', '7', '1', '2013-01-28 20:28:09'), ('49', '3', 'Male', '4', '5', '1', '2013-01-28 20:29:54'), ('50', '3', 'Male', '3', '6', '1', '2013-01-28 20:29:49'), ('51', '8', 'White', '3', '6', '1', '2013-01-28 20:30:47'), ('52', '8', 'Asian', '4', '5', '1', '2013-01-28 20:31:10'), ('53', '8', 'Other', '5', '7', '1', '2013-01-28 20:31:39'), ('54', '8', 'Hispanic', '5', '8', '1', '2013-01-28 20:32:18'), ('55', '8', 'White', '6', '9', '1', '2013-01-28 20:32:54'), ('56', '8', 'Other', '5', '10', '1', '2013-01-28 20:33:25');
COMMIT;

-- ----------------------------
--  Table structure for `form_fields`
-- ----------------------------
DROP TABLE IF EXISTS `form_fields`;
CREATE TABLE `form_fields` (
  `Field_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Form_Id` int(11) NOT NULL,
  `Field_Label` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Field_Type_Id` int(11) DEFAULT NULL,
  `X_coordinate` float DEFAULT NULL,
  `Y_coordinate` float DEFAULT NULL,
  `isRequired` bit(1) DEFAULT NULL,
  `Default_Value` bit(1) DEFAULT NULL,
  `Min_Value` float DEFAULT NULL,
  `Max_Value` float DEFAULT NULL,
  `User_Id` int(11) DEFAULT NULL,
  `Date_Created` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `isActive` tinyint(1) NOT NULL DEFAULT '1',
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Field_Id`),
  KEY `Form_Id` (`Form_Id`),
  KEY `Field_Type_Id` (`Field_Type_Id`),
  KEY `FKD5C64A94C8251CD5` (`User_Id`),
  CONSTRAINT `FKD5C64A94C8251CD5` FOREIGN KEY (`User_Id`) REFERENCES `Users` (`User_Id`),
  CONSTRAINT `FK_Form_Fields_Field_Type_ID` FOREIGN KEY (`Field_Type_Id`) REFERENCES `Field_Type` (`Field_Type_Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_Form_Fields_Form_ID` FOREIGN KEY (`Form_Id`) REFERENCES `Form` (`Form_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=ascii;

-- ----------------------------
--  Records of `form_fields`
-- ----------------------------
BEGIN;
INSERT INTO `form_fields` VALUES ('1', '1', 'Name', '1', null, null, b'1', null, null, null, '2', '2012-01-01 00:00:00', '1', '2013-01-28 19:51:55'), ('2', '1', 'Surname', '1', null, null, b'1', null, null, null, '2', '2012-01-01 00:00:00', '1', '2013-01-28 19:51:51'), ('3', '1', 'Gender', '3', null, null, b'1', null, null, null, '3', '2012-01-01 00:00:00', '1', '2013-01-28 19:52:03'), ('4', '2', 'Age', '1', null, null, b'1', null, null, null, '1', '1970-01-01 00:00:00', '1', '2013-01-28 19:51:47'), ('7', '1', 'Hometown', '1', null, null, b'1', null, null, null, '1', '1970-01-01 00:00:00', '1', '2013-01-28 19:51:43'), ('8', '2', 'Ethnicity', '3', null, null, b'1', null, null, null, '2', '1970-01-01 00:00:00', '1', '2013-01-28 19:57:14'), ('9', '4', 'Weight in Kg', '1', null, null, b'1', null, null, null, '2', '1970-01-01 00:00:00', '1', '2013-01-28 20:04:59'), ('10', '4', 'Height in cm', '1', null, null, b'1', null, null, null, '2', '1970-01-01 00:00:00', '1', '2013-01-28 20:07:37'), ('11', '4', 'Injured', '6', null, null, b'1', null, null, null, '5', '1970-01-01 00:00:00', '1', '2013-01-28 20:13:44');
COMMIT;

-- ----------------------------
--  Procedure structure for `spDynamicSearch`
-- ----------------------------
DROP PROCEDURE IF EXISTS `spDynamicSearch`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `spDynamicSearch`(
	IN Criteria VARCHAR(100),
	FieldLabel VARCHAR(100)
)
BEGIN
	DECLARE
		finish INT DEFAULT 0 ; DECLARE
			fld_id INT ; DECLARE
				fld_lbl VARCHAR(100); 
				DECLARE	str VARCHAR(10000)DEFAULT "SELECT TEMP.* FROM(SELECT a.Record_Id," ; DECLARE
						curs CURSOR FOR SELECT
							a.Field_Id,
							a.Field_Label
						FROM
							Form_Fields a
						INNER JOIN Form b ON a.Form_Id = b.Form_Id
						WHERE
							a.isActive = 1
						AND b.isActive = 1 ; DECLARE
							CONTINUE HANDLER FOR NOT found
						SET finish = 1 ; OPEN curs ; my_loop :
						LOOP
							FETCH curs INTO fld_id,
							fld_lbl ;
						IF finish = 1 THEN
							LEAVE my_loop ;
						END
						IF ;
						SET str = concat(
							str,
							"MAX(IF(a.Field_Id = '",
							fld_id,
							"', a.Value, NULL)) as  `",
							fld_lbl,
							"`,"
						);
						END
						LOOP
							; CLOSE curs ;
						SET str = substr(str, 1, char_length(str) - 1);
						SET @str = concat(
							str,
							" FROM Filled_Form a WHERE a.isActive = 1 group By a.Record_Id) TEMP WHERE TEMP.",
							FieldLabel,
							"= '",
							Criteria,
							"'"
						); PREPARE stmt
						FROM
							@str ; EXECUTE stmt ; DEALLOCATE PREPARE stmt ;
						END
 ;;
delimiter ;

-- ----------------------------
--  Procedure structure for `spSchemaGeneration`
-- ----------------------------
DROP PROCEDURE IF EXISTS `spSchemaGeneration`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `spSchemaGeneration`(IN DB_Name VARCHAR(100))
BEGIN
	DECLARE
		sql_cmd VARCHAR(10000)DEFAULT "CREATE DATABASE " ;
	SET @sql_cmd = concat(sql_cmd, DB_Name, ";"); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Access_Log (Access_Log_Id int(11) NOT NULL AUTO_INCREMENT,
																		  Form_Id int(11) NOT NULL,
																		  Field_Id int(11) DEFAULT NULL,
																		  User_Id int(11) NOT NULL,
																		  Access_Start_Time datetime DEFAULT '1970-01-01 00:00:00',
																		  Access_End_Time datetime DEFAULT NULL,
																		  Device varchar(100) CHARACTER SET utf8 DEFAULT NULL,
																		  Location varchar(100) CHARACTER SET utf8 DEFAULT NULL,
																		  isOnline tinyint(1) NOT NULL DEFAULT '0',	
																		  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
																		  PRIMARY KEY (`Access_Log_Id`),
																		  KEY Form_Id (Form_Id),
																		  KEY Field_Id (Field_Id),
																		  KEY User_Id (User_Id)
																		) ENGINE=InnoDB DEFAULT CHARSET=ascii;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Event (
																		  Event_Id int(11) NOT NULL AUTO_INCREMENT,
																		  Event_Name varchar(255) NOT NULL,
																		  Event_Description text NOT NULL,
																		  Event_Location varchar(255) NOT NULL,
																		  Event_Date date NOT NULL,
																		  Event_Time time NOT NULL,
																		  User_Id int(11) NOT NULL,
																		  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
																		  PRIMARY KEY (Event_Id),
																		  KEY User_Id (User_Id)	
																		) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Field_Selection (
															  Field_Selection_Id int(11) NOT NULL AUTO_INCREMENT,
															  Field_Id int(11) NOT NULL,
															  Field_Selection_Value varchar(255) NOT NULL,
															  Timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
															  PRIMARY KEY (Field_Selection_Id),
															  KEY Field_Id (Field_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Field_Type (
															  Field_Type_Id int(11) NOT NULL AUTO_INCREMENT,
															  Field_Type varchar(100) CHARACTER SET utf8 DEFAULT NULL,
															  Field_DataType varchar(100) CHARACTER SET utf8 DEFAULT NULL,
															  Field_Description varchar(200) CHARACTER SET utf8 DEFAULT NULL,
															  isActive tinyint(1) NOT NULL DEFAULT '1',
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
															  PRIMARY KEY (Field_Type_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Filled_Form (
															  Filled_Form_Id int(11) NOT NULL AUTO_INCREMENT,
															  Field_Id int(11) DEFAULT NULL,
															  Value varchar(250) CHARACTER SET utf8 DEFAULT NULL,
															  User_Id int(11) DEFAULT NULL,
															  Record_Id int(11) NOT NULL,
															  isActive tinyint(1) NOT NULL DEFAULT '1',
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
															  PRIMARY KEY (Filled_Form_Id),
															  KEY Field_Id (Field_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Form (
															  Form_Id int(11) NOT NULL AUTO_INCREMENT,
															  Form_Type_Id int(11) DEFAULT NULL,
															  Form_Name varchar(100) CHARACTER SET utf8 DEFAULT NULL,
															  Date_Created datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
															  URL varchar(250) CHARACTER SET utf8 DEFAULT NULL,
															  isActive tinyint(1) NOT NULL DEFAULT '1',
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
															  PRIMARY KEY (Form_Id),
															  KEY Form_Type_Id (Form_Type_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Form_Fields (
															  Field_Id int(11) NOT NULL AUTO_INCREMENT,
															  Form_Id int(11) NOT NULL,
															  Field_Label varchar(100) CHARACTER SET utf8 DEFAULT NULL,
															  Field_Type_Id int(11) DEFAULT NULL,
															  X_coordinate float DEFAULT NULL,
															  Y_coordinate float DEFAULT NULL,
															  isRequired bit(1) DEFAULT NULL,
															  Default_Value bit(1) DEFAULT NULL,
															  Min_Value float DEFAULT NULL,
															  Max_Value float DEFAULT NULL,
															  User_Id int(11) DEFAULT NULL,
															  Date_Created datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
															  isActive tinyint(1) NOT NULL DEFAULT '1',
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
															  PRIMARY KEY (Field_Id),
															  KEY Form_Id (Form_Id),
															  KEY Field_Type_Id (Field_Type_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Form_Permissions (
															  Form_Id int(11) NOT NULL,
															  User_Type_Id int(11) NOT NULL,
															  Permission varchar(100) CHARACTER SET utf8 DEFAULT NULL,
															  isActive bit(1) DEFAULT NULL,
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
															  PRIMARY KEY (Form_Id,User_Type_Id),
															  KEY Form_Id (Form_Id),
															  KEY User_Type_Id (User_Type_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Form_Type (
															  Form_Type_Id int(11) NOT NULL AUTO_INCREMENT,
															  Form_Type varchar(100) CHARACTER SET utf8 DEFAULT NULL,
															  isActive tinyint(4) NOT NULL DEFAULT '1',
															  PRIMARY KEY (Form_Type_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Mailing_Group (
															  Mailing_Group_Id int(11) NOT NULL AUTO_INCREMENT,
															  Mailing_Group varchar(255) NOT NULL,
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
															  PRIMARY KEY (Mailing_Group_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Mailing_List (
															  Mailing_Group_Id int(11) NOT NULL,
															  User_Id int(11) NOT NULL,
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
															  PRIMARY KEY (Mailing_Group_Id,User_Id),
															  KEY Mailing_Group_Id (Mailing_Group_Id),
															  KEY User_Id (User_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".User_Type (
															  User_Type_Id int(11) NOT NULL AUTO_INCREMENT,
															  User_Type varchar(100) CHARACTER SET utf8 DEFAULT NULL,
															  User_Type_Description varchar(200) CHARACTER SET utf8 DEFAULT NULL,
															  isActive tinyint(1) NOT NULL DEFAULT '1',
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
															  PRIMARY KEY (User_Type_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Users (
															  User_Id int(11) NOT NULL AUTO_INCREMENT,
															  Username varchar(20) CHARACTER SET utf8 NOT NULL,
															  User_Type_Id int(11) NOT NULL,
															  User_Password varchar(250) CHARACTER SET utf8 NOT NULL,
															  Salt varchar(10) NOT NULL,
															  User_Email varchar(250) CHARACTER SET utf8 DEFAULT NULL,
															  Date_Created datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
															  isActive tinyint(1) NOT NULL DEFAULT '1',
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
															  PRIMARY KEY (User_Id),
															  KEY User_Type_Id (User_Type_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Mailing_List ADD CONSTRAINT FK_Mailing_List_User_Id FOREIGN KEY (User_Id) REFERENCES ",
		DB_Name,
		".Users (User_Id) ON UPDATE CASCADE, 
									ADD CONSTRAINT FK_Mailing_List_Mailing_Group_Id FOREIGN KEY (Mailing_Group_Id) REFERENCES ",
		DB_Name,
		".Mailing_Group (Mailing_Group_Id) ON UPDATE CASCADE;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Users ADD CONSTRAINT FK_User_User_Type_ID FOREIGN KEY (User_Type_Id) REFERENCES ",
		DB_Name,
		".User_Type (User_Type_Id)"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Access_Log ADD CONSTRAINT FK_Access_Log_Form_Fields_ID FOREIGN KEY (Field_Id) REFERENCES ",
		DB_Name,
		".Form_Fields (Field_Id) ON UPDATE CASCADE,
									ADD CONSTRAINT FK_Access_Log_Form_ID FOREIGN KEY (Form_Id) REFERENCES ",
		DB_Name,
		".Form (Form_Id) ON UPDATE CASCADE,
									ADD CONSTRAINT FK_Access_Log_Users_ID FOREIGN KEY (User_Id) REFERENCES ",
		DB_Name,
		".Users (User_Id) ON UPDATE CASCADE"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Event ADD CONSTRAINT FK_Event_User_Id FOREIGN KEY (User_Id) REFERENCES ",
		DB_Name,
		".Users (User_Id) ON UPDATE CASCADE"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Field_Selection ADD CONSTRAINT FK_Field_Selection_Form_Field FOREIGN KEY (Field_Id) REFERENCES ",
		DB_Name,
		".Form_Fields (Field_Id) ON UPDATE CASCADE"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Filled_Form ADD CONSTRAINT FK_Filled_Form_Form_Fields_ID FOREIGN KEY (Field_Id) REFERENCES ",
		DB_Name,
		".Form_Fields (Field_Id) ON UPDATE CASCADE"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Form ADD CONSTRAINT FK_Form_Form_Type_ID FOREIGN KEY (Form_Type_Id) REFERENCES ",
		DB_Name,
		".Form_Type (Form_Type_Id)"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Form_Fields ADD CONSTRAINT FK_Form_Fields_Field_Type_ID FOREIGN KEY (Field_Type_Id) REFERENCES ",
		DB_Name,
		".Field_Type (Field_Type_Id) ON UPDATE CASCADE, 
									ADD CONSTRAINT FK_Form_Fields_Form_ID FOREIGN KEY (Form_Id) REFERENCES ",
		DB_Name,
		".Form (Form_Id)"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Form_Permissions ADD CONSTRAINT FK_Form_Permissions_Form_ID FOREIGN KEY (Form_Id) REFERENCES ",
		DB_Name,
		".Form (Form_Id) ON UPDATE CASCADE,
									ADD CONSTRAINT FK_Form_Permissions_User_Type_ID FOREIGN KEY (User_Type_Id) REFERENCES ",
		DB_Name,
		".User_Type (User_Type_Id) ON UPDATE CASCADE"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ; DEALLOCATE PREPARE stmt ;
	END
 ;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
