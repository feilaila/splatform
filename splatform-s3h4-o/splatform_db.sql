/*
Navicat MySQL Data Transfer

Source Server         : spaltform
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : splatform_db

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2015-02-05 13:31:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `muke_course`
-- ----------------------------
DROP TABLE IF EXISTS `muke_course`;
CREATE TABLE `muke_course` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(18) DEFAULT NULL COMMENT '创建时间',
  `img` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `info` varchar(255) DEFAULT NULL COMMENT '简介',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `type_id` int(8) NOT NULL COMMENT '课程类型id',
  `sys_user_id` int(8) NOT NULL COMMENT '用户id',
  `video_id` int(8) DEFAULT NULL COMMENT '附件id',
  `status` int(1) NOT NULL COMMENT '状态 0待审核  1已审核  2 已下线 ;默认为0',
  PRIMARY KEY (`id`),
  KEY `FK5014C5F88A5D8984` (`type_id`),
  KEY `FK5014C5F8A83935E6` (`sys_user_id`),
  CONSTRAINT `FK5014C5F88A5D8984` FOREIGN KEY (`type_id`) REFERENCES `muke_course_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of muke_course
-- ----------------------------
INSERT INTO `muke_course` VALUES ('1', '20150204170432', '', '测试1测试1测试1测试1', '测试1', '测试1测试1', '1', '2', null, '0');
INSERT INTO `muke_course` VALUES ('2', '20150205120931', '', 'C语言经典讲解', 'C语言', 'C语言111', '2', '2', null, '0');
INSERT INTO `muke_course` VALUES ('3', '20150205120952', '', 'html实战训练100案例', 'html实战', 'html实战', '3', '2', null, '0');

-- ----------------------------
-- Table structure for `muke_course_type`
-- ----------------------------
DROP TABLE IF EXISTS `muke_course_type`;
CREATE TABLE `muke_course_type` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of muke_course_type
-- ----------------------------
INSERT INTO `muke_course_type` VALUES ('1', 'JAVA', '3', 'java经典教材', null);
INSERT INTO `muke_course_type` VALUES ('2', 'C', '5', 'C语言编程', null);
INSERT INTO `muke_course_type` VALUES ('3', 'html', '2', 'html语法讲解', null);
INSERT INTO `muke_course_type` VALUES ('4', 'css', '6', 'css编程技巧', null);

-- ----------------------------
-- Table structure for `t_sh_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_sh_user`;
CREATE TABLE `t_sh_user` (
  `auid` int(8) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `terminal_id` varchar(20) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `group_id` varchar(8) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `start_date` varchar(20) DEFAULT NULL,
  `end_date` varchar(20) DEFAULT NULL,
  `last_login_ip` varchar(100) DEFAULT NULL,
  `groupName` varchar(255) DEFAULT NULL,
  `limit_year` int(3) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`auid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sh_user
-- ----------------------------
INSERT INTO `t_sh_user` VALUES ('1', '981233589@qq.com', '啥的', '123', '13511112222', '测试1', '6', '1', '20141120', '20141231', '128.0.0.1', null, null, null);
INSERT INTO `t_sh_user` VALUES ('2', '981233589@qq.com', '小k', '123', '13866553333', 'u007', '6', '1', '20141120', '20141231', '127.0.0.1', null, null, null);
INSERT INTO `t_sh_user` VALUES ('3', '981233589@qq.com', '刚刚', '123', '13511113333', '12u8', '7', '9', '20140520', '20141231', '125.255.231.255', null, '3', '测试12u8');
INSERT INTO `t_sh_user` VALUES ('4', '41221111@126.com', 'dd ', '123', '15655543232', '测试2', '7', '9', '20141022', '20141231', '127.0.0.2', null, null, null);
INSERT INTO `t_sh_user` VALUES ('5', '222111@163.com', 'TT', '123', '18677729388', 'au7', '9', '1', '20140521', '20141230', '128.0.2.5', null, null, null);
INSERT INTO `t_sh_user` VALUES ('7', '981@123.com', 'ceshi11', '123', '15011125555', '测试11', '7', '1', '2014-11-18', '2014-11-30', null, null, null, null);
INSERT INTO `t_sh_user` VALUES ('8', '二恶额@qq.com', '测试2', '123', '13855554444', '测试2', '6', '1', '2014-11-01', '2014-12-31', null, null, '3', '测试第二个');
INSERT INTO `t_sh_user` VALUES ('9', '1@11.com', '', '', '13811112222', '', '0', '9', '', '', null, null, null, '');
INSERT INTO `t_sh_user` VALUES ('10', '123123@123.com', '刚刚', '', '15010001000', '小刚', '8', '1', '20140520', '20141231', null, null, '5', '测试');
INSERT INTO `t_sh_user` VALUES ('11', '', '刚刚', '', '', '', '8', '9', '20140520', '20141231', null, null, null, '');
INSERT INTO `t_sh_user` VALUES ('12', '130@139.com', '初级的', '123', '13011112222', '测试ceshi', '6', '1', '20141201', '20141231', null, null, '3', '测试ceshi');

-- ----------------------------
-- Table structure for `t_sys_attachment`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_attachment`;
CREATE TABLE `t_sys_attachment` (
  `aid` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT '附件id',
  `type` tinyint(2) DEFAULT NULL COMMENT '附件类型  1头像图片 2大图 3 文件  4  音乐  5  视频',
  `uid` mediumint(8) DEFAULT NULL COMMENT '会员id；添加人员id',
  `downloads` mediumint(8) DEFAULT NULL COMMENT '下载次数',
  `filename` varchar(100) DEFAULT NULL COMMENT '文件原名称',
  `newfilename` varchar(100) DEFAULT NULL COMMENT '上传的文件新名称',
  `filepath` varchar(100) DEFAULT NULL COMMENT '文件保存路径',
  `uploadtime` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 COMMENT='附件信息表';

-- ----------------------------
-- Records of t_sys_attachment
-- ----------------------------
INSERT INTO `t_sys_attachment` VALUES ('66', '1', '2', null, 'Desert.jpg', 'Desert.jpg', '/splatform-s3h4-m/res/Desert.jpg', '2015-02-04 12:37:57');
INSERT INTO `t_sys_attachment` VALUES ('67', '1', '2', null, 'Hydrangeas.jpg', 'Hydrangeas.jpg', '/splatform-s3h4-m/res/Hydrangeas.jpg', '2015-02-04 12:40:00');
INSERT INTO `t_sys_attachment` VALUES ('68', '1', '2', null, 'Chrysanthemum.jpg', 'Chrysanthemum.jpg', '/splatform-s3h4-m/res/Chrysanthemum.jpg', '2015-02-04 12:41:53');
INSERT INTO `t_sys_attachment` VALUES ('69', '1', '2', null, '519b59626b555.jpg', '519b59626b555.jpg', '/splatform-s3h4-m/res/519b59626b555.jpg', '2015-02-04 12:44:15');
INSERT INTO `t_sys_attachment` VALUES ('70', '1', '2', null, '491738.jpg', '491738.jpg', '/splatform-s3h4-m/res/491738.jpg', '2015-02-04 12:44:54');
INSERT INTO `t_sys_attachment` VALUES ('71', '1', '2', null, '11223133_095309330335_2.jpg', '11223133_095309330335_2.jpg', '/splatform-s3h4-m/res/11223133_095309330335_2.jpg', '2015-02-04 12:49:23');
INSERT INTO `t_sys_attachment` VALUES ('72', '1', '2', null, '519b59626b555.jpg', '519b59626b555.jpg', '/splatform-s3h4-m/res/519b59626b555.jpg', '2015-02-04 14:17:26');
INSERT INTO `t_sys_attachment` VALUES ('73', '1', '2', null, '491738.jpg', '491738.jpg', '/splatform-s3h4-m/res/491738.jpg', '2015-02-04 14:47:47');
INSERT INTO `t_sys_attachment` VALUES ('74', '1', '2', null, '519b59626b555.jpg', '519b59626b555.jpg', '/splatform-s3h4-m/res/519b59626b555.jpg', '2015-02-04 15:02:46');
INSERT INTO `t_sys_attachment` VALUES ('75', '1', '2', null, '491738.jpg', '491738.jpg', '/splatform-s3h4-m/res/491738.jpg', '2015-02-04 15:05:37');
INSERT INTO `t_sys_attachment` VALUES ('76', '1', '2', null, '519b59626b555.jpg', '519b59626b555.jpg', '/splatform-s3h4-m/res/519b59626b555.jpg', '2015-02-04 15:07:37');
INSERT INTO `t_sys_attachment` VALUES ('77', '1', '2', null, '491738.jpg', '491738.jpg', '/splatform-s3h4-m/res/491738.jpg', '2015-02-04 15:13:40');
INSERT INTO `t_sys_attachment` VALUES ('78', '1', '2', null, '11223133_095309330335_2.jpg', '11223133_095309330335_2.jpg', '/splatform-s3h4-m/res/11223133_095309330335_2.jpg', '2015-02-04 15:14:58');
INSERT INTO `t_sys_attachment` VALUES ('79', '1', '2', null, 'QQ图片20141118083214.jpg', 'QQ图片20141118083214.jpg', '/splatform-s3h4-m/res/QQ图片20141118083214.jpg', '2015-02-04 15:15:10');
INSERT INTO `t_sys_attachment` VALUES ('80', '1', '2', null, 'QQ20141118083214.jpg', 'QQ20141118083214.jpg', '/splatform-s3h4-m/res/QQ20141118083214.jpg', '2015-02-04 15:15:40');
INSERT INTO `t_sys_attachment` VALUES ('81', '1', null, null, '11223133_095309330335_2.jpg', '11223133_095309330335_2.jpg', '/splatform-s3h4-m/res/11223133_095309330335_2.jpg', '2015-02-04 16:16:23');
INSERT INTO `t_sys_attachment` VALUES ('82', '1', '2', null, '11223133_095309330335_2.jpg', '11223133_095309330335_2.jpg', '/splatform-s3h4-m/res/11223133_095309330335_2.jpg', '2015-02-04 16:18:43');
INSERT INTO `t_sys_attachment` VALUES ('83', '5', '2', null, 'Wildlife.wmv', 'Wildlife.wmv', '/splatform-s3h4-m/res/Wildlife.wmv', '2015-02-04 16:40:52');
INSERT INTO `t_sys_attachment` VALUES ('84', '5', '2', null, 'Wildlife.wmv', 'Wildlife.wmv', '/splatform-s3h4-m/res/Wildlife.wmv', '2015-02-04 16:50:45');

-- ----------------------------
-- Table structure for `t_sys_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dictionary`;
CREATE TABLE `t_sys_dictionary` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `dic_name` varchar(50) DEFAULT NULL,
  `dic_info` varchar(255) DEFAULT NULL,
  `parent_id` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for `t_sys_group`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_group`;
CREATE TABLE `t_sys_group` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(18) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `group_desc` varchar(50) DEFAULT NULL,
  `menu_btn` varchar(30) DEFAULT NULL,
  `menu_code` varchar(14) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_sys_group
-- ----------------------------
INSERT INTO `t_sys_group` VALUES ('1', '系统管理组2', '20150131204906', '测试的', null, null, null);
INSERT INTO `t_sys_group` VALUES ('2', '客服A组', '20150202170158', '客服A组的111', null, null, null);
INSERT INTO `t_sys_group` VALUES ('3', '客服B组', '20150201035924', '客服B组', null, null, null);
INSERT INTO `t_sys_group` VALUES ('4', '会员组', '20141118111200', '会员组', null, null, null);
INSERT INTO `t_sys_group` VALUES ('7', '测试组织0202', '20150202220138', '测试组织0202', null, null, null);
INSERT INTO `t_sys_group` VALUES ('10', '客服A1组', '20150126230112', '客服A1组嗯', null, null, null);
INSERT INTO `t_sys_group` VALUES ('12', '测试0126', '20150126175146', '测试0126', null, null, null);
INSERT INTO `t_sys_group` VALUES ('13', '测试0126-1', '20150126224616', '测试0126-1', null, null, null);
INSERT INTO `t_sys_group` VALUES ('14', '测试0126-2', '20150126175552', '测试0126-2', null, null, null);
INSERT INTO `t_sys_group` VALUES ('19', '测试0126-7', '20150127001959', '测试0126-7', null, null, null);
INSERT INTO `t_sys_group` VALUES ('20', '测试0128-1', '20150131213702', '测试0128-1', null, null, null);
INSERT INTO `t_sys_group` VALUES ('21', '测试0131-1', '20150131235153', '测试0131-1', null, null, null);

-- ----------------------------
-- Table structure for `t_sys_group_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_group_role`;
CREATE TABLE `t_sys_group_role` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `group_id` int(8) DEFAULT NULL,
  `role_id` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAD532673105BF75B` (`group_id`),
  KEY `FKAD5326738330C7D9` (`role_id`),
  CONSTRAINT `FKAD532673105BF75B` FOREIGN KEY (`group_id`) REFERENCES `t_sys_group` (`id`),
  CONSTRAINT `FKAD5326738330C7D9` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_group_role
-- ----------------------------
INSERT INTO `t_sys_group_role` VALUES ('6', '4', '6');
INSERT INTO `t_sys_group_role` VALUES ('7', '4', '7');
INSERT INTO `t_sys_group_role` VALUES ('8', '4', '8');
INSERT INTO `t_sys_group_role` VALUES ('9', '4', '9');
INSERT INTO `t_sys_group_role` VALUES ('85', '3', '14');
INSERT INTO `t_sys_group_role` VALUES ('86', '3', '16');
INSERT INTO `t_sys_group_role` VALUES ('92', '2', '31');
INSERT INTO `t_sys_group_role` VALUES ('93', '7', '32');

-- ----------------------------
-- Table structure for `t_sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(20) DEFAULT NULL,
  `menu_code` varchar(20) DEFAULT NULL,
  `menu_pid` int(8) DEFAULT NULL,
  `menu_url` varchar(50) DEFAULT NULL,
  `leaf_yn` int(1) DEFAULT NULL,
  `menu_btns` varchar(50) DEFAULT NULL,
  `icon_tag` varchar(20) DEFAULT NULL,
  `has_child` tinyint(1) DEFAULT NULL COMMENT '是否有子菜单,1有  0没有',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('2', '系统管理', 'sys_menu', '0', '', '0', '', 'icon-tasks', '1');
INSERT INTO `t_sys_menu` VALUES ('3', '参数设置', 'params_menu', '2', 'gmanage.do', '1', null, null, '0');
INSERT INTO `t_sys_menu` VALUES ('4', '组织管理', 'group_menu', '2', 'gmanage.do', '1', 'add_btn,edit_btn,del_btn,audit_btn', null, '0');
INSERT INTO `t_sys_menu` VALUES ('5', '角色管理', 'role_menu', '2', 'romanage.do', '1', 'add_btn,edit_btn,del_btn,audit_btn', null, '0');
INSERT INTO `t_sys_menu` VALUES ('6', '用户管理', 'user_menu', '2', 'umanage.do', '1', 'add_btn,edit_btn,del_btn,audit_btn', null, '0');
INSERT INTO `t_sys_menu` VALUES ('7', '会员管理', 'auser_menu', '2', 'aumanage.do', '1', null, null, '0');
INSERT INTO `t_sys_menu` VALUES ('8', '课程管理', 'course_menu', '0', '', '0', null, 'icon-table', '1');
INSERT INTO `t_sys_menu` VALUES ('9', '课程列表', 'courseslist_menu', '8', 'coursemanage.do', '1', null, null, '0');
INSERT INTO `t_sys_menu` VALUES ('10', '商品管理', 'goods_menu', '0', null, '0', null, 'icon-keyboard', '1');
INSERT INTO `t_sys_menu` VALUES ('11', '商品列表', 'goodslist_menu', '10', '/goods/list.do', '1', null, null, '0');
INSERT INTO `t_sys_menu` VALUES ('12', '商品审核', 'goodsaudit_menu', '10', '/goods/toAuditList.do', '1', null, null, '0');
INSERT INTO `t_sys_menu` VALUES ('13', '商品报表', 'goodsreport_menu', '10', '/goods/toReportList.do', '1', null, null, '0');
INSERT INTO `t_sys_menu` VALUES ('16', '订单管理', 'order_menu', '0', null, '0', null, 'icon-exchange', '1');
INSERT INTO `t_sys_menu` VALUES ('17', '订单审核', 'order_audit', '16', '/order/oaudit.do', '1', null, null, '0');
INSERT INTO `t_sys_menu` VALUES ('18', '订单报表', 'order_export', '16', '/order/export.do', '1', null, null, '0');

-- ----------------------------
-- Table structure for `t_sys_operate`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_operate`;
CREATE TABLE `t_sys_operate` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `operate_code` varchar(30) DEFAULT NULL,
  `operate_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_operate
-- ----------------------------
INSERT INTO `t_sys_operate` VALUES ('1', 'add_btn', '添加');
INSERT INTO `t_sys_operate` VALUES ('2', 'del_btn', '删除');
INSERT INTO `t_sys_operate` VALUES ('3', 'query_btn', '查询');
INSERT INTO `t_sys_operate` VALUES ('4', 'edit_btn', '修改');

-- ----------------------------
-- Table structure for `t_sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(18) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `operate_id` int(8) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `group_id` int(8) DEFAULT NULL,
  `checked` tinyint(1) NOT NULL,
  `operateName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD28DE253105BF75B` (`group_id`),
  KEY `FKD28DE2534F4DA930` (`group_id`),
  CONSTRAINT `FKD28DE253105BF75B` FOREIGN KEY (`group_id`) REFERENCES `t_sys_group` (`id`),
  CONSTRAINT `FKD28DE2534F4DA930` FOREIGN KEY (`group_id`) REFERENCES `t_sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', '超级管理员', '测试', '1', null, '1', '0', null);
INSERT INTO `t_sys_role` VALUES ('2', '普通管理员', '普通管理员', '1', '20140520120015', '3', '0', null);
INSERT INTO `t_sys_role` VALUES ('3', '客服1', '客服1', '2', '20140510110015', '2', '0', null);
INSERT INTO `t_sys_role` VALUES ('4', '客服22', '测试', '1', null, '2', '0', null);
INSERT INTO `t_sys_role` VALUES ('5', 'super管理员', 'super管理员', '1', '20140505120015', '1', '0', null);
INSERT INTO `t_sys_role` VALUES ('6', '初级会员', '初级会员', '1', '20140505121115', '4', '0', null);
INSERT INTO `t_sys_role` VALUES ('7', '普通会员', '普通会员', '1', '20140505121115', '4', '0', null);
INSERT INTO `t_sys_role` VALUES ('8', '高级会员', '测试', '1', null, '4', '0', null);
INSERT INTO `t_sys_role` VALUES ('9', 'VIP会员', 'VIP会员', '1', '20140505121115', '4', '0', null);
INSERT INTO `t_sys_role` VALUES ('13', '测试角色', '测试', '1', '20141130120816', '1', '0', null);
INSERT INTO `t_sys_role` VALUES ('14', '测试的', '测试', '1', '20141130131825', '1', '0', null);
INSERT INTO `t_sys_role` VALUES ('16', '测试23', '测试', '1', '20141130131959', '3', '0', null);
INSERT INTO `t_sys_role` VALUES ('20', '测试是是是', '测试', '1', '20141130152254', '2', '0', null);
INSERT INTO `t_sys_role` VALUES ('21', 'AAAXX', '测试', '1', null, '3', '0', null);
INSERT INTO `t_sys_role` VALUES ('25', '测试0202-1', '测试0202-1', '2', '20150202105557', null, '0', null);
INSERT INTO `t_sys_role` VALUES ('31', '测试0202-5', '测试0202-5', '2', '20150202132217', null, '0', null);
INSERT INTO `t_sys_role` VALUES ('32', '测试0202-10', '测试0202-10', '2', '20150202215626', null, '0', null);

-- ----------------------------
-- Table structure for `t_sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_menu`;
CREATE TABLE `t_sys_role_menu` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(8) NOT NULL,
  `menu_id` int(8) NOT NULL,
  `menu_btn` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEE8E7C0B69C23339` (`menu_id`),
  KEY `FKEE8E7C0B8330C7D9` (`role_id`),
  CONSTRAINT `FKEE8E7C0B69C23339` FOREIGN KEY (`menu_id`) REFERENCES `t_sys_menu` (`id`),
  CONSTRAINT `FKEE8E7C0B8330C7D9` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role_menu
-- ----------------------------
INSERT INTO `t_sys_role_menu` VALUES ('27', '31', '2', null);
INSERT INTO `t_sys_role_menu` VALUES ('28', '31', '3', null);
INSERT INTO `t_sys_role_menu` VALUES ('29', '31', '4', null);
INSERT INTO `t_sys_role_menu` VALUES ('30', '31', '5', null);
INSERT INTO `t_sys_role_menu` VALUES ('31', '31', '6', null);
INSERT INTO `t_sys_role_menu` VALUES ('32', '32', '8', null);
INSERT INTO `t_sys_role_menu` VALUES ('33', '32', '9', null);

-- ----------------------------
-- Table structure for `t_sys_role_operate`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_operate`;
CREATE TABLE `t_sys_role_operate` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `role_id` int(8) DEFAULT NULL,
  `operate_id` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK932813588330C7D9` (`role_id`),
  KEY `FK93281358C046E0FB` (`operate_id`),
  CONSTRAINT `FK932813588330C7D9` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`),
  CONSTRAINT `FK93281358C046E0FB` FOREIGN KEY (`operate_id`) REFERENCES `t_sys_operate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_sys_role_operate
-- ----------------------------
INSERT INTO `t_sys_role_operate` VALUES ('4', '2', '1');
INSERT INTO `t_sys_role_operate` VALUES ('5', '2', '3');

-- ----------------------------
-- Table structure for `t_sys_settings`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_settings`;
CREATE TABLE `t_sys_settings` (
  `key` varchar(200) NOT NULL COMMENT '设置键名',
  `value` text NOT NULL COMMENT '设置内容，大量数据将序列化',
  PRIMARY KEY (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_settings
-- ----------------------------

-- ----------------------------
-- Table structure for `t_sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `uid` int(8) NOT NULL AUTO_INCREMENT,
  `usercode` varchar(32) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `terminal_id` varchar(20) DEFAULT NULL,
  `valid_time` varchar(14) DEFAULT NULL,
  `create_time` varchar(14) DEFAULT NULL,
  `change_pwd_time` varchar(14) DEFAULT NULL,
  `lock_status` int(1) DEFAULT NULL COMMENT '0 未锁定',
  `status` int(1) DEFAULT NULL,
  `last_login_time` varchar(14) DEFAULT NULL,
  `last_login_ip` varchar(100) DEFAULT NULL,
  `group_id` int(8) DEFAULT NULL,
  `faceimg_aid` int(8) DEFAULT NULL COMMENT '头像id，对应attach表aid',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'ceshi1', '123', '981233555@qq.com', 'admin', '10210005555', null, null, null, '0', '1', null, null, '1', null);
INSERT INTO `t_sys_user` VALUES ('2', 'admin', '123', '981233555@qq.com', '超级管理员', '11110000111', '20141203', '20141202', null, '0', '1', null, null, '1', '82');
INSERT INTO `t_sys_user` VALUES ('3', 'xiaozhi', '', '12555@126.com', '小智', '15011112222', '20141203', '20141202', null, '0', '1', null, null, '2', null);
INSERT INTO `t_sys_user` VALUES ('4', 'bigtou', '', '4545115@163.com', '大头哥', '13811112223', '20141225', '20141202', null, '0', '1', null, null, '3', null);
INSERT INTO `t_sys_user` VALUES ('5', 'kefux', '', '4545115@163.com', '客服YYY', '15011112288', '20141231', '20141202', null, '0', '1', null, null, '4', null);
INSERT INTO `t_sys_user` VALUES ('7', '测试2', '123', '981@qq.com', 'ddd', '13013013000', '20141231', '20141202', null, '0', '9', null, null, '7', null);
INSERT INTO `t_sys_user` VALUES ('8', '超人1', '123', '123@qq.com', '超人1', '14766665555', '20141231', '20141201', null, '0', '9', null, null, '7', null);
INSERT INTO `t_sys_user` VALUES ('9', 'ceshi1205', '123', '123@126.com', '测试1205', '13011112222', '20141231', '20141205', null, '0', '1', null, null, '4', null);
INSERT INTO `t_sys_user` VALUES ('10', 'cskf1', '123', '1122@qq.com', '测试客服1', '15011112222', '20150228', '20150126', null, '0', '1', null, null, '2', null);
INSERT INTO `t_sys_user` VALUES ('11', 'lscs1', '123', '123@qq.com', '老师测试1', '15012134567', '20150228', '20150201', null, '0', '1', null, null, '7', null);
INSERT INTO `t_sys_user` VALUES ('12', 'ceshi22', '123', '123@qq.com', '测试用户22', '15111112154', '20150228', '20150201', null, '0', '1', null, null, '7', null);

-- ----------------------------
-- Table structure for `t_sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `user_id` int(8) DEFAULT NULL,
  `role_id` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEEC648ED8330C7D9` (`role_id`),
  KEY `FKEEC648ED285B8BB9` (`user_id`),
  CONSTRAINT `FKEEC648ED285B8BB9` FOREIGN KEY (`user_id`) REFERENCES `t_sys_user` (`uid`),
  CONSTRAINT `FKEEC648ED8330C7D9` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES ('13', '3', '16');
INSERT INTO `t_sys_user_role` VALUES ('21', '4', '1');
INSERT INTO `t_sys_user_role` VALUES ('26', '5', '14');
INSERT INTO `t_sys_user_role` VALUES ('27', '7', '2');
INSERT INTO `t_sys_user_role` VALUES ('29', '2', '2');
INSERT INTO `t_sys_user_role` VALUES ('35', '1', '1');
INSERT INTO `t_sys_user_role` VALUES ('36', '2', '1');
INSERT INTO `t_sys_user_role` VALUES ('37', '9', '2');
