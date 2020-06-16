/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 80014
Source Host           : localhost:3306
Source Database       : security

Target Server Type    : MYSQL
Target Server Version : 80014
File Encoding         : 65001

Date: 2020-06-16 23:26:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `permission_NAME` varchar(30) DEFAULT NULL COMMENT '菜单名称',
  `permission_url` varchar(100) DEFAULT NULL COMMENT '菜单地址',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父菜单id',
  `order_num` int(4) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=40003 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('10000', '系统管理', null, '0', '1');
INSERT INTO `sys_permission` VALUES ('10001', '用户管理', 'admin/user', '10000', '1');
INSERT INTO `sys_permission` VALUES ('10002', '角色管理', 'admin/role', '10000', '2');
INSERT INTO `sys_permission` VALUES ('20000', '订单中心', null, '0', '2');
INSERT INTO `sys_permission` VALUES ('20001', '已下单', '/order/done', '20000', '1');
INSERT INTO `sys_permission` VALUES ('20002', '未下单', '/order/no', '20000', '2');
INSERT INTO `sys_permission` VALUES ('20003', '取消订单', '/order/cancel', '20000', '3');
INSERT INTO `sys_permission` VALUES ('30000', '购物车', null, '0', '3');
INSERT INTO `sys_permission` VALUES ('30001', '添加购物车', '/car/add', '30000', '1');
INSERT INTO `sys_permission` VALUES ('30002', '修改购物车', '/car/update', '30000', '2');
INSERT INTO `sys_permission` VALUES ('30003', '删除购物车', '/car/delete', '30000', '3');
INSERT INTO `sys_permission` VALUES ('40000', '支付中心', null, '0', '4');
INSERT INTO `sys_permission` VALUES ('40001', '已支付', '/pay/payed', '40000', '1');
INSERT INTO `sys_permission` VALUES ('40002', '未支付', '/pay/noPayed', '40000', '2');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `ROLE_NAME` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `ROLE_DESC` varchar(60) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN', 'admin管理员角色');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_USER', 'user普通用户角色');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `RID` int(11) NOT NULL COMMENT '角色编号',
  `PID` int(11) NOT NULL COMMENT '权限编号',
  PRIMARY KEY (`RID`,`PID`),
  KEY `FK_Reference_12` (`PID`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`RID`) REFERENCES `sys_role` (`ID`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`PID`) REFERENCES `sys_permission` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '10000');
INSERT INTO `sys_role_permission` VALUES ('2', '10000');
INSERT INTO `sys_role_permission` VALUES ('1', '10001');
INSERT INTO `sys_role_permission` VALUES ('1', '10002');
INSERT INTO `sys_role_permission` VALUES ('2', '10002');
INSERT INTO `sys_role_permission` VALUES ('1', '20000');
INSERT INTO `sys_role_permission` VALUES ('1', '20001');
INSERT INTO `sys_role_permission` VALUES ('1', '20002');
INSERT INTO `sys_role_permission` VALUES ('1', '20003');
INSERT INTO `sys_role_permission` VALUES ('1', '30000');
INSERT INTO `sys_role_permission` VALUES ('1', '30001');
INSERT INTO `sys_role_permission` VALUES ('1', '30002');
INSERT INTO `sys_role_permission` VALUES ('1', '30003');
INSERT INTO `sys_role_permission` VALUES ('1', '40000');
INSERT INTO `sys_role_permission` VALUES ('1', '40001');
INSERT INTO `sys_role_permission` VALUES ('1', '40002');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `last_login_time` datetime DEFAULT NULL COMMENT '上一次登录时间',
  `STATUS` tinyint(1) DEFAULT '1' COMMENT '账号是否可用。默认为1（可用）',
  `image_url` varchar(100) DEFAULT '/assets/images/dudu.jpg' COMMENT '头像地址',
  `not_expired` tinyint(1) DEFAULT '1' COMMENT '是否过期。默认为1（没有过期）',
  `account_not_locked` tinyint(1) DEFAULT '1' COMMENT '账号是否锁定。默认为1（没有锁定）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(64) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'bbd126f4856c57f68d4e30264da6a4e6', null, '1', '/assets/images/dudu.jpg', '1', '1', null, null, null, null);
INSERT INTO `sys_user` VALUES ('2', 'user', 'bbd126f4856c57f68d4e30264da6a4e6', null, '1', '/assets/images/dudu.jpg', '1', '1', null, null, null, null);
INSERT INTO `sys_user` VALUES ('20', '关羽', 'bbd126f4856c57f68d4e30264da6a4e6', null, '1', '/assets/images/dudu.jpg', '0', '1', '2020-06-11 22:25:38', '2020-06-16 21:39:56', 'admin', '关羽');
INSERT INTO `sys_user` VALUES ('21', '张飞', 'bbd126f4856c57f68d4e30264da6a4e6', null, '1', '/assets/images/dudu.jpg', '1', '1', '2020-06-11 22:26:21', '2020-06-11 22:55:15', 'admin', 'admin');
INSERT INTO `sys_user` VALUES ('24', '王辉', 'bbd126f4856c57f68d4e30264da6a4e6', null, '1', '/assets/images/dudu.jpg', '1', '1', '2020-06-13 11:41:01', '2020-06-13 11:41:01', '关羽', '关羽');
INSERT INTO `sys_user` VALUES ('25', '吕布', 'bbd126f4856c57f68d4e30264da6a4e6', null, '1', '/assets/images/dudu.jpg', '1', '1', '2020-06-13 11:42:34', '2020-06-13 11:42:34', '关羽', '关羽');
INSERT INTO `sys_user` VALUES ('26', '刘备', 'bbd126f4856c57f68d4e30264da6a4e6', null, '1', '/assets/images/dudu.jpg', '1', '1', '2020-06-13 11:44:35', '2020-06-13 11:44:35', '关羽', '关羽');
INSERT INTO `sys_user` VALUES ('27', '诸葛亮', 'bbd126f4856c57f68d4e30264da6a4e6', null, '1', '/assets/images/dudu.jpg', '1', '1', '2020-06-14 17:07:44', '2020-06-14 17:08:00', '关羽', '关羽');
INSERT INTO `sys_user` VALUES ('28', '司马懿', 'bbd126f4856c57f68d4e30264da6a4e6', null, '1', '/assets/images/dudu.jpg', '1', '1', '2020-06-14 17:27:44', '2020-06-14 17:27:44', '关羽', '关羽');
INSERT INTO `sys_user` VALUES ('29', '曹操', 'bbd126f4856c57f68d4e30264da6a4e6', null, '1', '/assets/images/dudu.jpg', '1', '1', '2020-06-14 17:27:51', '2020-06-14 17:27:51', '关羽', '关羽');
INSERT INTO `sys_user` VALUES ('30', '典韦', 'bbd126f4856c57f68d4e30264da6a4e6', null, '1', '/assets/images/dudu.jpg', '1', '1', '2020-06-14 17:27:59', '2020-06-14 17:27:59', '关羽', '关羽');
INSERT INTO `sys_user` VALUES ('31', '白起', 'bbd126f4856c57f68d4e30264da6a4e6', null, '1', '/assets/images/dudu.jpg', '0', '1', '2020-06-14 17:28:06', '2020-06-16 21:47:40', '关羽', '关羽');
INSERT INTO `sys_user` VALUES ('32', '扁鹊', 'bbd126f4856c57f68d4e30264da6a4e6', null, '1', '/assets/images/dudu.jpg', '1', '1', '2020-06-16 21:47:33', '2020-06-16 21:47:33', '关羽', '关羽');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `UID` int(11) NOT NULL COMMENT '用户编号',
  `RID` int(11) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`UID`,`RID`),
  KEY `FK_Reference_10` (`RID`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`RID`) REFERENCES `sys_role` (`ID`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`UID`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('20', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');
INSERT INTO `sys_user_role` VALUES ('2', '2');
