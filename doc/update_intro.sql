/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.161
Source Server Version : 50540
Source Host           : 192.168.0.161:3306
Source Database       : update

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2016-01-04 09:37:13
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `update_intro`
-- ----------------------------
DROP TABLE IF EXISTS `update_intro`;
CREATE TABLE `update_intro` (
  `id` varchar(64) NOT NULL,
  `sys_type` varchar(64) DEFAULT '0' COMMENT '更新文件适用的系统类型',
  `sys_version` varchar(64) DEFAULT NULL COMMENT '更新文件对应的系统版本信息，ps: v1.2.0.2567',
  `file_name` varchar(64) DEFAULT NULL,
  `update_file_size` double DEFAULT NULL COMMENT '更新文件的大小',
  `update_file_url` varchar(64) DEFAULT NULL COMMENT '更新文件的url',
  `check_sum` mediumtext COMMENT '更新文件的校验和',
  `is_latest_stable` int(11) DEFAULT NULL COMMENT '否是是最新的版本，这个字段不一定用',
  `is_disable` int(11) DEFAULT NULL COMMENT '该版本是否可用，如果被置为不可用，更新的时候，不会将它作为更新文件',
  `description` longtext COMMENT '对更新文件的描述',
  `create_time` datetime DEFAULT NULL COMMENT '更新文件的创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of update_intro
-- ----------------------------
INSERT INTO `update_intro` VALUES ('1c00de69-ecd3-400e-9022-21bf1f7b0c0d', 'AS20151231-LU', '1.2.4.5678', 'chart.png', '87992', 'workspace://1302d121-c0a7-442f-a0d5-55a8e89a112c', '437375146', null, null, '新增功能\r\n\r\n1\r\n新增了公积金网上汇缴支付功能，办理好相关手续的单位可以通过系统完成每月公积金汇缴业务。\r\n2\r\n新增了公积金网上补缴支付功能，办理好相关手续的单位可以通过系统为符合网上补缴条件的职工完成公积金补缴业务。\r\n3\r\n主界面的菜单栏进行了调整，新增了用户管理模块，提供单位USB_KEY的管理功能。\r\n4\r\n拥有一证通卡的单位，可以使用一证通卡登录本系统。\r\n \r\n其他改进\r\n1\r\n“单位会员综合服务系统”现正式更名为：“单位公积金网上业务办理系统”。\r\n2\r\n封存业务，导出盘片功能，更新了模版。\r\n3\r\n帐册打印模块，调整了打印表单的名称。\r\n4\r\n数据下载后，主界面右下角的末次汇缴月份会随着最新数据进行更新。', '2015-12-31 17:12:37');
