/*
 Navicat Premium Data Transfer

 Source Server         : swagger
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : swagger

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 09/05/2018 01:36:08
*/

SET NAMES utf8;

CREATE DATABASE `swagger` CHARACTER SET utf8 COLLATE utf8_general_ci;
USE swagger;

-- ----------------------------
-- Table structure for appdoc
-- ----------------------------
DROP TABLE IF EXISTS `appdoc`;
CREATE TABLE `appdoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `doc` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
