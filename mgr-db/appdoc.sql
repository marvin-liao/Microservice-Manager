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

DROP TABLE IF EXISTS `api`;
CREATE TABLE `api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `host` varchar(255) DEFAULT NULL,
  `basepath` varchar(255) DEFAULT NULL,
  `doc` text,
  `lastmodify` datetime DEFAULT NULL,
  `result` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `api_item`;
CREATE TABLE `api_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `operationid` varchar(255) DEFAULT NULL,
  `consumes` varchar(255) DEFAULT NULL,
  `lastmodify` datetime DEFAULT NULL,
  `result` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `machine`;
CREATE TABLE `machine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `host` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `lastmodify` datetime DEFAULT NULL,
  `result` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `instanceid` varchar(255) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `app` varchar(255) DEFAULT NULL,
  `ipAddr` varchar(255) DEFAULT NULL,
  `homepageurl` varchar(255) DEFAULT NULL,
  `statuspageurl` varchar(255) DEFAULT NULL,
  `healthcheckurl` varchar(255) DEFAULT NULL,
  `lastupdatedtimestamp` bigint DEFAULT NULL,
  `lastmodify` datetime DEFAULT NULL,
  `result` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
