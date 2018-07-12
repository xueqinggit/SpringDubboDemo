/*
 Navicat Premium Data Transfer

 Source Server         : MySQL王学卿
 Source Server Type    : MySQL
 Source Server Version : 50536
 Source Host           : localhost:3306
 Source Schema         : springboot

 Target Server Type    : MySQL
 Target Server Version : 50536
 File Encoding         : 65001

 Date: 12/07/2018 09:10:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module`  (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `mname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES (1, 'add');
INSERT INTO `module` VALUES (2, 'delete');
INSERT INTO `module` VALUES (3, 'query');
INSERT INTO `module` VALUES (4, 'update');

-- ----------------------------
-- Table structure for module_role
-- ----------------------------
DROP TABLE IF EXISTS `module_role`;
CREATE TABLE `module_role`  (
  `rid` int(11) NULL DEFAULT NULL,
  `mid` int(11) NULL DEFAULT NULL,
  INDEX `rid`(`rid`) USING BTREE,
  INDEX `mid`(`mid`) USING BTREE,
  CONSTRAINT `mid` FOREIGN KEY (`mid`) REFERENCES `module` (`mid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rid` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of module_role
-- ----------------------------
INSERT INTO `module_role` VALUES (1, 1);
INSERT INTO `module_role` VALUES (1, 2);
INSERT INTO `module_role` VALUES (1, 3);
INSERT INTO `module_role` VALUES (1, 4);
INSERT INTO `module_role` VALUES (2, 1);
INSERT INTO `module_role` VALUES (2, 3);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin');
INSERT INTO `role` VALUES (2, 'customer');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `age` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年龄',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '小王', '123', '18', '110');
INSERT INTO `user` VALUES (2, '小李', '123', '19', '120');
INSERT INTO `user` VALUES (3, '小龙', '123', '20', '119');
INSERT INTO `user` VALUES (4, '小赵', '123', '21', '122');
INSERT INTO `user` VALUES (5, '小刘', '123', '22', '2111');
INSERT INTO `user` VALUES (6, '小丽', '123', '23', '222');
INSERT INTO `user` VALUES (7, '小小', '123', '24', '333');
INSERT INTO `user` VALUES (8, '小西', '123', '25', '555');
INSERT INTO `user` VALUES (9, NULL, '123', '26', '888');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `uid` int(11) NULL DEFAULT NULL,
  `rid` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (2, 2);

SET FOREIGN_KEY_CHECKS = 1;
