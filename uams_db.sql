/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : uams_db

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 28/11/2024 11:55:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator_tab
-- ----------------------------
DROP TABLE IF EXISTS `administrator_tab`;
CREATE TABLE `administrator_tab`  (
  `adm_id` int(0) NOT NULL COMMENT '教务员id',
  `adm_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '姓名',
  `adm_rank` tinyint(0) NOT NULL COMMENT '教务员等级(1-院级教务员，2-校级教务员)',
  `adm_tel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '电话',
  `adm_account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '账号',
  `adm_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '密码',
  `adm_status` tinyint(0) NOT NULL COMMENT '状态，0-停用，1-启用',
  PRIMARY KEY (`adm_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for approvalform_tab
-- ----------------------------
DROP TABLE IF EXISTS `approvalform_tab`;
CREATE TABLE `approvalform_tab`  (
  `apf_id` int(0) NOT NULL COMMENT '开课审批号',
  `apf_adm_id` int(0) NOT NULL COMMENT '审批教务员id',
  `apf_tch_id` int(0) NOT NULL COMMENT '提交审批的教师id',
  `apf_cou_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '待审批课程识别码',
  `apf_status` tinyint(0) NOT NULL COMMENT '审批状态(0-未审批，1-审批通过，2-审批驳回)',
  `apf_note` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '审批意见',
  PRIMARY KEY (`apf_id`) USING BTREE,
  INDEX `FK_apf_adm_id`(`apf_adm_id`) USING BTREE,
  INDEX `FK_apf_cou_code`(`apf_cou_code`) USING BTREE,
  CONSTRAINT `FK_apf_adm_id` FOREIGN KEY (`apf_adm_id`) REFERENCES `administrator_tab` (`adm_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_apf_cou_code` FOREIGN KEY (`apf_cou_code`) REFERENCES `course_tab` (`cou_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class_tab
-- ----------------------------
DROP TABLE IF EXISTS `class_tab`;
CREATE TABLE `class_tab`  (
  `cla_id` int(0) NOT NULL COMMENT '教学班id(自增长的唯一索引)',
  `cla_cou_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '课程识别码',
  `cla_tch_id` int(0) NOT NULL COMMENT '任课教师',
  `cla_toschool_1` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '可选课学院1',
  `cla_toschool_2` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '可选课学院2',
  `cla_toschool_3` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '可选课学院3',
  `cla_toshcool_4` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '可选课学院4',
  `cla_timeset` set('11','12','13','14','15','21','22','23','24','25','31','32','33','34','35','41','42','43','44','45','51','52','53','54','55','61','62','63','64','65','71','72','73','74','75') CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT '' COMMENT '表示上课日期(\'nm\'，星期n的第m节课)',
  `cla_weekset` set('1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25') CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT '' COMMENT '表示上课周数(\'n\'，第n周)',
  `cla_classroom` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '课室',
  PRIMARY KEY (`cla_cou_code`, `cla_tch_id`) USING BTREE,
  UNIQUE INDEX `U_cla_id`(`cla_id`) USING BTREE,
  INDEX `FK_cla_tch_id`(`cla_tch_id`) USING BTREE,
  CONSTRAINT `FK_cla_cou_code` FOREIGN KEY (`cla_cou_code`) REFERENCES `course_tab` (`cou_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_cla_tch_id` FOREIGN KEY (`cla_tch_id`) REFERENCES `teacher_tab` (`tch_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_tab
-- ----------------------------
DROP TABLE IF EXISTS `course_tab`;
CREATE TABLE `course_tab`  (
  `cou_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '课程识别码(同一课程具有同一识别码，但不一定是同一id。例如，高等数学1同时会有很多教学班，每个教学班都有自己的id，但是共用一个识别码)',
  `cou_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '课程名',
  `cou_ifexam` enum('考查','考试') CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '考查/考试',
  `cou_ifscore` enum('评分','评级') CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '评分/评级',
  `cou_term` int(0) NOT NULL COMMENT '课程开设学期',
  `cou_text` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '课程大纲',
  `cou_type` enum('公共艺术类','核心通识类','科学素养类','专业课','体育类','思想政治类') CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '课程类型(\'公共艺术类\',\'核心通识类\',\'科学素养类\',\'专业课\',\'体育类\',\'思想政治类\')',
  `cou_language` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '课程语言',
  `cou_rank` enum('公必','公选','专必','专选','荣誉课程') CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '课程级别(\'公必\',\'公选\',\'专必\',\'专选\',\'荣誉课程\')',
  `cou_status` tinyint(0) NOT NULL COMMENT '选课状态(1-选课，2-选课关闭，3-退课关闭，4-待审批，5-审批驳回，6-课程关闭)',
  `cou_school` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '授课单位名称',
  `cou_score` int(0) NOT NULL COMMENT '课程学分',
  PRIMARY KEY (`cou_code`) USING BTREE,
  INDEX `cou_code`(`cou_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for evaluation_tab
-- ----------------------------
DROP TABLE IF EXISTS `evaluation_tab`;
CREATE TABLE `evaluation_tab`  (
  `eva_tch_id` int(0) NOT NULL COMMENT '教师id外键',
  `eva_stu_id` int(0) NOT NULL COMMENT '学生id外键',
  `eva_term` int(0) NOT NULL COMMENT '对应学期',
  `eva_score` float NULL DEFAULT NULL COMMENT '分数',
  `eva_text` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '评语',
  `eva_item_1` int(0) NULL DEFAULT NULL COMMENT '评分项1',
  `eva_item_2` int(0) NULL DEFAULT NULL COMMENT '评分项2',
  `eva_item_3` int(0) NULL DEFAULT NULL COMMENT '评分项3',
  `eva_item_4` int(0) NULL DEFAULT NULL COMMENT '评分项4',
  PRIMARY KEY (`eva_tch_id`, `eva_stu_id`, `eva_term`) USING BTREE,
  INDEX `FK_eva_stu_id`(`eva_stu_id`) USING BTREE,
  CONSTRAINT `FK_eva_stu_id` FOREIGN KEY (`eva_stu_id`) REFERENCES `student_tab` (`stu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_eva_tch_id` FOREIGN KEY (`eva_tch_id`) REFERENCES `teacher_tab` (`tch_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for school_tab
-- ----------------------------
DROP TABLE IF EXISTS `school_tab`;
CREATE TABLE `school_tab`  (
  `sch_id` int(0) NOT NULL,
  `sch_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL,
  PRIMARY KEY (`sch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for score_tab
-- ----------------------------
DROP TABLE IF EXISTS `score_tab`;
CREATE TABLE `score_tab`  (
  `sco_stu_id` int(0) NOT NULL COMMENT '学生id外键',
  `sco_cla_id` int(0) NOT NULL COMMENT '课程i教学班id外键',
  `sco_score_1` float NULL DEFAULT NULL COMMENT '成绩',
  `sco_item_1` int(0) NULL DEFAULT NULL COMMENT '评分项1',
  `sco_item_2` int(0) NULL DEFAULT NULL COMMENT '评分项2',
  `sco_item_3` int(0) NULL DEFAULT NULL COMMENT '评分项3',
  `sco_item_4` int(0) NULL DEFAULT NULL COMMENT '评分项4',
  `sco_note` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '备注',
  `sco_score_2` float NULL DEFAULT NULL COMMENT '折合绩点',
  PRIMARY KEY (`sco_stu_id`, `sco_cla_id`) USING BTREE,
  INDEX `FK_sco_cla_id`(`sco_cla_id`) USING BTREE,
  CONSTRAINT `FK_sco_cla_id` FOREIGN KEY (`sco_cla_id`) REFERENCES `class_tab` (`cla_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_sco_stu_id` FOREIGN KEY (`sco_stu_id`) REFERENCES `student_tab` (`stu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student_tab
-- ----------------------------
DROP TABLE IF EXISTS `student_tab`;
CREATE TABLE `student_tab`  (
  `stu_id` int(0) NOT NULL COMMENT '学生id',
  `stu_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '姓名',
  `stu_sex` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '性别(男/女)',
  `stu_tel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '电话',
  `stu_grade` int(0) NOT NULL COMMENT '分属年级',
  `stu_sch` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '分属学院',
  `stu_picture` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '存储学生照片的文件地址',
  `stu_account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '账号',
  `stu_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '密码',
  `stu_status` tinyint(0) NOT NULL COMMENT '状态，0-停用，1-启用',
  PRIMARY KEY (`stu_id`) USING BTREE,
  INDEX `FK_stu_gra_id`(`stu_grade`) USING BTREE,
  INDEX `FK_stu_sch_id`(`stu_sch`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teacher_tab
-- ----------------------------
DROP TABLE IF EXISTS `teacher_tab`;
CREATE TABLE `teacher_tab`  (
  `tch_id` int(0) NOT NULL COMMENT '教师id',
  `tch_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '教师姓名',
  `tch_sex` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '性别(男/女)',
  `tch_sch` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '分属学院/单位',
  `tch_tel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '电话',
  `tch_picture` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL COMMENT '照片文件路径',
  `tch_account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '账号',
  `tch_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NOT NULL COMMENT '密码',
  `tch_status` tinyint(0) NOT NULL COMMENT '状态，0-停用，1-启用',
  PRIMARY KEY (`tch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
