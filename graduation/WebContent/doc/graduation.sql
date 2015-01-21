-- phpMyAdmin SQL Dump
-- version 4.3.0
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-01-12 14:41:50
-- 服务器版本： 5.6.21-log
-- PHP Version: 5.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `graduation`
--

-- --------------------------------------------------------

--
-- 表的结构 `apply`
--

DROP TABLE IF EXISTS `apply`;
CREATE TABLE IF NOT EXISTS `apply` (
`id` int(11) NOT NULL COMMENT 'id自增',
  `user_id` varchar(255) NOT NULL COMMENT '申请人编号',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `type` varchar(255) NOT NULL COMMENT '类型|1 开题答辩 2 毕业答辩',
  `pass` varchar(255) NOT NULL DEFAULT '0' COMMENT '是否同意答辩|指导老师是否同意参加答辩| 0 未处理1 不同意 2 同意',
  `status` varchar(255) NOT NULL DEFAULT '1' COMMENT '状态| 1 未受理 2 已受理',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='开题答辩|毕业答辩申请表';

--
-- 转存表中的数据 `apply`
--

INSERT INTO `apply` (`id`, `user_id`, `department_id`, `type`, `pass`, `status`, `create_time`) VALUES
(17, '1106401023', '064', '1', '1', '2', '2015-01-09 21:51:32'),
(18, '1106401023', '064', '2', '1', '2', '2015-01-10 11:00:22'),
(19, '1106401023', '064', '2', '2', '2', '2015-01-10 11:05:46'),
(20, '1106401023', '064', '1', '2', '2', '2015-01-10 13:07:54'),
(21, '1106401023', '064', '2', '2', '2', '2015-01-11 16:44:36'),
(23, '1106401001', '064', '1', '0', '1', '2015-01-12 09:27:44'),
(24, '1106401001', '064', '2', '0', '1', '2015-01-12 09:29:22');

-- --------------------------------------------------------

--
-- 表的结构 `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
  `dept_ID` varchar(10) NOT NULL COMMENT '系部ID',
  `dept_Name` varchar(50) NOT NULL COMMENT '系部名称',
  `major_type` varchar(255) NOT NULL DEFAULT '1' COMMENT '系部专业类型| 1 理工医农学类  2 人文社科类 3 其他'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系部表';

--
-- 转存表中的数据 `department`
--

INSERT INTO `department` (`dept_ID`, `dept_Name`, `major_type`) VALUES
('064', '计算机工程系', '3'),
('154', '教育科学系', '2');

-- --------------------------------------------------------

--
-- 表的结构 `deptmanager`
--

DROP TABLE IF EXISTS `deptmanager`;
CREATE TABLE IF NOT EXISTS `deptmanager` (
  `dm_ID` varchar(12) NOT NULL COMMENT '系管编号',
  `dept_ID` varchar(10) DEFAULT NULL COMMENT '系部ID',
  `dm_Name` varchar(16) DEFAULT NULL COMMENT '系管姓名',
  `dm_Sex` varchar(4) DEFAULT NULL COMMENT '性别',
  `dm_Tel` varchar(15) DEFAULT NULL COMMENT '联系电话',
  `dm_Email` varchar(30) NOT NULL COMMENT '电子邮箱'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系部管理员表';

--
-- 转存表中的数据 `deptmanager`
--

INSERT INTO `deptmanager` (`dm_ID`, `dept_ID`, `dm_Name`, `dm_Sex`, `dm_Tel`, `dm_Email`) VALUES
('06001', '064', '彭小宁', '1', '', '1230@qq.com'),
('06033', '064', '黄隆华', '2', '', '123@qq.com'),
('154', '154', '唐老师', '2', '18274591193', '123@qq.com'),
('05001', '154', '陈老师', '1', '15758849499', '1230@qq.com'),
('15054', '154', '唐老师', '2', '18274591193', '123@qq.com'),
('12014', '064', 'xyz', '1', '', '7400@qq.com'),
('06023', '064', 'jqc', '2', '15758849499', 'fjkjfk@132.com'),
('6029', '064', '唐老师', '女', '18274591193', '123@qq.com');

-- --------------------------------------------------------

--
-- 表的结构 `gradeall`
--

DROP TABLE IF EXISTS `gradeall`;
CREATE TABLE IF NOT EXISTS `gradeall` (
`gA_ID` int(11) NOT NULL COMMENT '综合成绩编号',
  `stu_ID` varchar(12) DEFAULT NULL COMMENT '学号',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `gA_Grade` float DEFAULT NULL COMMENT '综合成绩',
  `status` varchar(255) NOT NULL DEFAULT '1' COMMENT '状态| 1 有效 2 无效',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='综合成绩表';

--
-- 转存表中的数据 `gradeall`
--

INSERT INTO `gradeall` (`gA_ID`, `stu_ID`, `department_id`, `gA_Grade`, `status`, `create_time`) VALUES
(3, '1106401023', '064', 100.3, '1', '2015-01-11 17:52:49');

-- --------------------------------------------------------

--
-- 表的结构 `gradeone`
--

DROP TABLE IF EXISTS `gradeone`;
CREATE TABLE IF NOT EXISTS `gradeone` (
`go_ID` int(11) NOT NULL COMMENT '表一编号',
  `stu_ID` varchar(12) DEFAULT NULL COMMENT '学号',
  `go_One` float DEFAULT NULL COMMENT '项一成绩',
  `go_Two` float DEFAULT NULL COMMENT '项二成绩',
  `go_Three` float DEFAULT NULL COMMENT '项三成绩',
  `go_Four` float DEFAULT NULL COMMENT '项四成绩',
  `go_Five` float DEFAULT NULL COMMENT '项五成绩',
  `go_Six` float DEFAULT NULL COMMENT '项六成绩',
  `go_All` float DEFAULT NULL COMMENT '总成绩',
  `content` varchar(255) DEFAULT NULL COMMENT '指导教师评定意见',
  `status` varchar(255) NOT NULL DEFAULT '1' COMMENT '状态| 1 有效 2 无效',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='成绩表一|指导教师评分表';

--
-- 转存表中的数据 `gradeone`
--

INSERT INTO `gradeone` (`go_ID`, `stu_ID`, `go_One`, `go_Two`, `go_Three`, `go_Four`, `go_Five`, `go_Six`, `go_All`, `content`, `status`, `create_time`) VALUES
(5, '1106401023', 8, 13, 52, 55, 41, 0, 169, '不错', '1', '2015-01-11 16:45:21');

-- --------------------------------------------------------

--
-- 表的结构 `gradethree`
--

DROP TABLE IF EXISTS `gradethree`;
CREATE TABLE IF NOT EXISTS `gradethree` (
`gtr_ID` int(11) NOT NULL COMMENT '表三编号',
  `stu_ID` varchar(12) DEFAULT NULL COMMENT '学号',
  `gtr_One` float DEFAULT NULL COMMENT '项一成绩',
  `gtr_Two` float DEFAULT NULL COMMENT '项二成绩',
  `gtr_Three` float DEFAULT NULL COMMENT '项三成绩',
  `gtr_Four` float DEFAULT NULL COMMENT '项四成绩',
  `gtr_Five` float DEFAULT NULL COMMENT '项五成绩',
  `gtr_Six` float DEFAULT NULL COMMENT '项六成绩',
  `gtr_All` float DEFAULT NULL COMMENT '总成绩',
  `content` varchar(255) DEFAULT NULL COMMENT '评审意见',
  `status` varchar(255) NOT NULL DEFAULT '1' COMMENT '状态| 1 有效 2 无效',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='成绩表三|答辩小组老师评分表';

--
-- 转存表中的数据 `gradethree`
--

INSERT INTO `gradethree` (`gtr_ID`, `stu_ID`, `gtr_One`, `gtr_Two`, `gtr_Three`, `gtr_Four`, `gtr_Five`, `gtr_Six`, `gtr_All`, `content`, `status`, `create_time`) VALUES
(3, '1106401023', 8, 30, 3, 6, 20, 0, 67, 'fafdad', '1', '2015-01-11 17:52:48');

-- --------------------------------------------------------

--
-- 表的结构 `gradetwo`
--

DROP TABLE IF EXISTS `gradetwo`;
CREATE TABLE IF NOT EXISTS `gradetwo` (
`gt_ID` int(11) NOT NULL COMMENT '表二编号',
  `stu_ID` varchar(12) DEFAULT NULL COMMENT '学号',
  `gt_One` float DEFAULT NULL COMMENT '项一成绩',
  `gt_Two` float DEFAULT NULL COMMENT '项二成绩',
  `gt_Three` float DEFAULT NULL COMMENT '项三成绩',
  `gt_Four` float DEFAULT NULL COMMENT '项四成绩',
  `gt_Five` float DEFAULT NULL COMMENT '项五成绩',
  `gt_Six` float DEFAULT NULL COMMENT '项六成绩',
  `gt_All` float DEFAULT NULL COMMENT '总成绩',
  `content` varchar(255) DEFAULT NULL COMMENT '评审意见',
  `status` varchar(255) NOT NULL DEFAULT '1' COMMENT '状态| 1 有效 2 无效',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='成绩表二|评阅教师评分表';

--
-- 转存表中的数据 `gradetwo`
--

INSERT INTO `gradetwo` (`gt_ID`, `stu_ID`, `gt_One`, `gt_Two`, `gt_Three`, `gt_Four`, `gt_Five`, `gt_Six`, `gt_All`, `content`, `status`, `create_time`) VALUES
(5, '1106401023', 8, 13, 25, 24, 6, 0, 76, '有一定的创新', '1', '2015-01-11 15:42:50');

-- --------------------------------------------------------

--
-- 表的结构 `graduateinfo`
--

DROP TABLE IF EXISTS `graduateinfo`;
CREATE TABLE IF NOT EXISTS `graduateinfo` (
`gdi_ID` int(11) NOT NULL COMMENT '答辩ID',
  `name` varchar(255) DEFAULT NULL COMMENT '标题|信息标题',
  `stu_ID` varchar(12) DEFAULT NULL COMMENT '学号',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `gdi_Date` varchar(255) DEFAULT NULL COMMENT '答辩日期',
  `gdi_Place` varchar(20) DEFAULT NULL COMMENT '答辩地点',
  `gdi_Number` int(11) DEFAULT NULL COMMENT '答辩序号',
  `judge` varchar(255) NOT NULL COMMENT '评审教师',
  `headerman` varchar(255) NOT NULL COMMENT '评审组长',
  `begroup` varchar(255) DEFAULT NULL COMMENT '组别|所属组',
  `content` varchar(255) DEFAULT NULL COMMENT '内容|会议记录摘要',
  `status` varchar(255) NOT NULL DEFAULT '1' COMMENT '状态|1 未处理,2 已处理',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='毕业答辩信息表';

--
-- 转存表中的数据 `graduateinfo`
--

INSERT INTO `graduateinfo` (`gdi_ID`, `name`, `stu_ID`, `department_id`, `gdi_Date`, `gdi_Place`, `gdi_Number`, `judge`, `headerman`, `begroup`, `content`, `status`, `create_time`) VALUES
(4, '', '1106401023', '064', '2015-01-30', 'E1A-301', 1, '06013,06014,06015,06016,06017,06018,06020,06021,06023', '06013', '3', 'fafdad', '2', '2015-01-11 16:00:03'),
(5, '', '1106401023', '064', '2015-01-28', 'E1A-301', 1, '06003,06010,06011,06012,06013,06014,06015,06016,06017', '06015', '3', 'fafdad', '2', '2015-01-11 16:48:17');

-- --------------------------------------------------------

--
-- 表的结构 `linkeddata_apply_gradeall`
--

DROP TABLE IF EXISTS `linkeddata_apply_gradeall`;
CREATE TABLE IF NOT EXISTS `linkeddata_apply_gradeall` (
`id` int(11) NOT NULL COMMENT 'ID|只能是数字',
  `item_id` varchar(255) NOT NULL COMMENT '被关联编号|多的对象的编号',
  `rel_id` varchar(255) NOT NULL COMMENT '关联编号|一的对象的编号',
  `extend` varchar(255) NOT NULL DEFAULT '0' COMMENT '扩展信息|如标签关联的分类信息等',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='关联数据|答辩申请与毕业答辩总成绩表关联';

-- --------------------------------------------------------

--
-- 表的结构 `linkeddata_apply_gradethree`
--

DROP TABLE IF EXISTS `linkeddata_apply_gradethree`;
CREATE TABLE IF NOT EXISTS `linkeddata_apply_gradethree` (
`id` int(11) NOT NULL COMMENT 'ID|只能是数字',
  `item_id` varchar(255) NOT NULL COMMENT '被关联编号|多的对象的编号',
  `rel_id` varchar(255) NOT NULL COMMENT '关联编号|一的对象的编号',
  `extend` varchar(255) NOT NULL DEFAULT '0' COMMENT '扩展信息|如标签关联的分类信息等',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='关联数据|答辩申请与毕业答辩成绩表三关联';

-- --------------------------------------------------------

--
-- 表的结构 `linkeddata_apply_graduateinfo`
--

DROP TABLE IF EXISTS `linkeddata_apply_graduateinfo`;
CREATE TABLE IF NOT EXISTS `linkeddata_apply_graduateinfo` (
`id` int(11) NOT NULL COMMENT 'ID|只能是数字',
  `item_id` varchar(255) NOT NULL COMMENT '被关联编号|多的对象的编号',
  `rel_id` varchar(255) NOT NULL COMMENT '关联编号|一的对象的编号',
  `extend` varchar(255) NOT NULL DEFAULT '0' COMMENT '扩展信息|如标签关联的分类信息等',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='关联数据|答辩申请与毕业答辩信息表关联';

--
-- 转存表中的数据 `linkeddata_apply_graduateinfo`
--

INSERT INTO `linkeddata_apply_graduateinfo` (`id`, `item_id`, `rel_id`, `extend`, `create_time`) VALUES
(4, '19', '4', '0', '2015-01-11 16:00:03'),
(5, '21', '5', '0', '2015-01-11 16:48:17');

-- --------------------------------------------------------

--
-- 表的结构 `linkeddata_apply_topicapply`
--

DROP TABLE IF EXISTS `linkeddata_apply_topicapply`;
CREATE TABLE IF NOT EXISTS `linkeddata_apply_topicapply` (
`id` int(11) NOT NULL COMMENT 'ID|只能是数字',
  `item_id` varchar(255) NOT NULL COMMENT '被关联编号|多的对象的编号',
  `rel_id` varchar(255) NOT NULL COMMENT '关联编号|一的对象的编号',
  `extend` varchar(255) NOT NULL DEFAULT '0' COMMENT '扩展信息|如标签关联的分类信息等',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='关联数据|答辩申请与答辩申请表关联';

--
-- 转存表中的数据 `linkeddata_apply_topicapply`
--

INSERT INTO `linkeddata_apply_topicapply` (`id`, `item_id`, `rel_id`, `extend`, `create_time`) VALUES
(7, '17', '7', '0', '2015-01-09 21:51:33'),
(8, '20', '8', '0', '2015-01-10 13:07:54'),
(9, '23', '9', '0', '2015-01-12 09:27:44');

-- --------------------------------------------------------

--
-- 表的结构 `linkeddata_apply_topicfinish`
--

DROP TABLE IF EXISTS `linkeddata_apply_topicfinish`;
CREATE TABLE IF NOT EXISTS `linkeddata_apply_topicfinish` (
`id` int(11) NOT NULL COMMENT 'ID|只能是数字',
  `item_id` varchar(255) NOT NULL COMMENT '被关联编号|多的对象的编号',
  `rel_id` varchar(255) NOT NULL COMMENT '关联编号|一的对象的编号',
  `extend` varchar(255) NOT NULL DEFAULT '0' COMMENT '扩展信息|如标签关联的分类信息等',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='关联数据|毕业答辩相关材料关联';

--
-- 转存表中的数据 `linkeddata_apply_topicfinish`
--

INSERT INTO `linkeddata_apply_topicfinish` (`id`, `item_id`, `rel_id`, `extend`, `create_time`) VALUES
(11, '18', '11', '0', '2015-01-10 11:00:22'),
(12, '19', '12', '0', '2015-01-10 11:05:46'),
(13, '21', '13', '0', '2015-01-11 16:44:37'),
(14, '24', '14', '0', '2015-01-12 09:29:22');

-- --------------------------------------------------------

--
-- 表的结构 `linkeddata_apply_topicinfo`
--

DROP TABLE IF EXISTS `linkeddata_apply_topicinfo`;
CREATE TABLE IF NOT EXISTS `linkeddata_apply_topicinfo` (
`id` int(11) NOT NULL COMMENT 'ID|只能是数字',
  `item_id` varchar(255) NOT NULL COMMENT '被关联编号|多的对象的编号',
  `rel_id` varchar(255) NOT NULL COMMENT '关联编号|一的对象的编号',
  `extend` varchar(255) NOT NULL DEFAULT '0' COMMENT '扩展信息|如标签关联的分类信息等',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='关联数据|答辩申请与开题答辩信息表关联';

--
-- 转存表中的数据 `linkeddata_apply_topicinfo`
--

INSERT INTO `linkeddata_apply_topicinfo` (`id`, `item_id`, `rel_id`, `extend`, `create_time`) VALUES
(7, '20', '7', '0', '2015-01-11 10:14:51');

-- --------------------------------------------------------

--
-- 表的结构 `linkeddata_apply_topicreport`
--

DROP TABLE IF EXISTS `linkeddata_apply_topicreport`;
CREATE TABLE IF NOT EXISTS `linkeddata_apply_topicreport` (
`id` int(11) NOT NULL COMMENT 'ID|只能是数字',
  `item_id` varchar(255) NOT NULL COMMENT '被关联编号|多的对象的编号',
  `rel_id` varchar(255) NOT NULL COMMENT '关联编号|一的对象的编号',
  `extend` varchar(255) NOT NULL DEFAULT '0' COMMENT '扩展信息|如标签关联的分类信息等',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='关联数据|答辩申请与答辩报告书关联';

--
-- 转存表中的数据 `linkeddata_apply_topicreport`
--

INSERT INTO `linkeddata_apply_topicreport` (`id`, `item_id`, `rel_id`, `extend`, `create_time`) VALUES
(7, '17', '7', '0', '2015-01-09 21:51:33'),
(8, '20', '8', '0', '2015-01-10 13:07:54'),
(9, '23', '9', '0', '2015-01-12 09:27:44');

-- --------------------------------------------------------

--
-- 表的结构 `linkeddata_apply_topicscore`
--

DROP TABLE IF EXISTS `linkeddata_apply_topicscore`;
CREATE TABLE IF NOT EXISTS `linkeddata_apply_topicscore` (
`id` int(11) NOT NULL COMMENT 'ID|只能是数字',
  `item_id` varchar(255) NOT NULL COMMENT '被关联编号|多的对象的编号',
  `rel_id` varchar(255) NOT NULL COMMENT '关联编号|一的对象的编号',
  `extend` varchar(255) NOT NULL DEFAULT '0' COMMENT '扩展信息|如标签关联的分类信息等',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='关联数据|答辩申请与开题答辩成绩关联';

--
-- 转存表中的数据 `linkeddata_apply_topicscore`
--

INSERT INTO `linkeddata_apply_topicscore` (`id`, `item_id`, `rel_id`, `extend`, `create_time`) VALUES
(4, '20', '4', '0', '2015-01-11 10:40:29');

-- --------------------------------------------------------

--
-- 表的结构 `linkeddata_meeting_graduateinfo`
--

DROP TABLE IF EXISTS `linkeddata_meeting_graduateinfo`;
CREATE TABLE IF NOT EXISTS `linkeddata_meeting_graduateinfo` (
`id` int(11) NOT NULL COMMENT 'ID|只能是数字',
  `item_id` varchar(255) NOT NULL COMMENT '被关联编号|多的对象的编号',
  `rel_id` varchar(255) NOT NULL COMMENT '关联编号|一的对象的编号',
  `extend` varchar(255) NOT NULL DEFAULT '0' COMMENT '扩展信息|如标签关联的分类信息等',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='关联数据|会议记录表与毕业答辩信息表关联';

--
-- 转存表中的数据 `linkeddata_meeting_graduateinfo`
--

INSERT INTO `linkeddata_meeting_graduateinfo` (`id`, `item_id`, `rel_id`, `extend`, `create_time`) VALUES
(2, '7', '4', '0', '2015-01-11 16:37:17'),
(3, '8', '5', '0', '2015-01-11 17:52:48');

-- --------------------------------------------------------

--
-- 表的结构 `meeting`
--

DROP TABLE IF EXISTS `meeting`;
CREATE TABLE IF NOT EXISTS `meeting` (
`id` int(11) NOT NULL COMMENT 'id自增',
  `name` varchar(255) DEFAULT NULL COMMENT '会议标题',
  `parent_id` varchar(255) NOT NULL COMMENT '所属父类',
  `start_time` varchar(255) DEFAULT NULL COMMENT '会议开始时间',
  `place` varchar(255) DEFAULT NULL COMMENT '会议地点',
  `person` varchar(255) DEFAULT NULL COMMENT '与会人员',
  `content` text COMMENT '会议记录摘要',
  `hoster` varchar(255) DEFAULT NULL COMMENT '会议主持人',
  `recorder` varchar(255) DEFAULT NULL COMMENT '会议记录人'
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='会议|会议记录表';

--
-- 转存表中的数据 `meeting`
--

INSERT INTO `meeting` (`id`, `name`, `parent_id`, `start_time`, `place`, `person`, `content`, `hoster`, `recorder`) VALUES
(6, '学生 1106401023 的开题答辩会议记录', '8', '2015-01-23', 'E1A-302', '06002,06003,06004,06005,06006,06007,06008,06011,06012', '你清楚你将要完成的课题的内容吗', '米春桥', '米春桥'),
(7, '学生 1106401023 【贺盼华】 的毕业答辩会议记录', '4', '2015-01-30', 'E1A-301', '06013,06014,06015,06016,06017,06018,06020,06021,06023', 'afdasd', 'fsad', 'dsaf'),
(8, '学生 1106401023 【贺盼华】 的毕业答辩会议记录', '5', '2015-01-28', 'E1A-301', '06003,06010,06011,06012,06013,06014,06015,06016,06017', 'fasdf', 'fadsf', 'adsfsda');

-- --------------------------------------------------------

--
-- 表的结构 `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
`id` int(11) NOT NULL COMMENT '编号|系统自动生成|show|hidden',
  `name` varchar(255) DEFAULT NULL COMMENT '标题|信息标题',
  `content` text COMMENT '内容|详细内容',
  `to_id` varchar(255) NOT NULL COMMENT '收件人|收件人编号|show|linked',
  `from_id` varchar(255) NOT NULL COMMENT '发送人|发送人编号|show|linked',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '查看状态|1->未查看,2->已查看|show|select',
  `create_time` varchar(255) DEFAULT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=86 DEFAULT CHARSET=utf8 COMMENT='消息|消息模块';

--
-- 转存表中的数据 `message`
--

INSERT INTO `message` (`id`, `name`, `content`, `to_id`, `from_id`, `status`, `create_time`) VALUES
(49, '学生选择教师课题', '学生：黄泽西 已选择您的课题并选择您为指导老师，请注意查看学生选择列表！', '06012', '1106401042', 1, '2015-01-09 19:29:16'),
(50, '学生选择指导老师', '学生：张海兰 已选择您作为指导老师，请注意查看学生选择列表！', '06012', '1106401001', 1, '2015-01-09 19:37:24'),
(51, '学生选择教师课题', '学生：贺盼华 已选择您的课题并选择您为指导老师，请注意查看学生选择列表！', '06012', '1106401023', 1, '2015-01-09 20:06:23'),
(52, '学生选择教师课题', '学生：刘剑华 已选择您的课题并选择您为指导老师，请注意查看学生选择列表！', '06012', '1106401021', 1, '2015-01-09 20:07:15'),
(53, '学生选择教师课题', '学生：黄艳 已选择您的课题并选择您为指导老师，请注意查看学生选择列表！', '06012', '1106401030', 1, '2015-01-09 20:07:53'),
(54, '学生选择教师课题', '学生：刘炎培 已选择您的课题并选择您为指导老师，请注意查看学生选择列表！', '06012', '1106401031', 1, '2015-01-09 20:13:40'),
(55, '学生选择教师课题', '学生：张会超 已选择您的课题并选择您为指导老师，请注意查看学生选择列表！', '06012', '1106401005', 1, '2015-01-09 20:14:17'),
(56, '学生选择教师课题', '学生：李占晓 已选择您的课题并选择您为指导老师，请注意查看学生选择列表！', '06012', '1106401007', 1, '2015-01-09 20:14:42'),
(57, '你被指导老师拒绝为任务人了', '你选的课题已经被老师我是06012确认为 非任务人 ，请及时查看', '1106401001', '06012', 1, '2015-01-09 20:23:38'),
(58, '你被指导老师拒绝为任务人了', '你选的课题已经被老师我是06012确认为 非任务人 ，请及时查看', '1106401042', '06012', 1, '2015-01-09 20:34:06'),
(59, '你被指导老师确认为任务人了', '你选的课题已经被老师我是06012确认为 任务人 ，请及时查看', '1106401023', '06012', 1, '2015-01-09 20:34:41'),
(60, '你被指导老师拒绝为任务人了', '你选的课题已经被老师我是06012确认为 非任务人 ，请及时查看', '1106401030', '06012', 1, '2015-01-09 20:34:57'),
(61, '你被指导老师拒绝为任务人了', '你选的课题已经被老师我是06012确认为 非任务人 ，请及时查看', '1106401007', '06012', 1, '2015-01-09 20:35:04'),
(62, '怀化学院本科毕业论文(设计)任务书', '指导老师 我是06012 已经下发怀化学院本科毕业论文(设计)任务书，请注意查收', '1106401023', '06012', 2, '2015-01-09 21:20:37'),
(63, '怀化学院本科毕业论文(设计)任务书', '指导老师 我是06012 已经下发怀化学院本科毕业论文(设计)任务书，请注意查收', '1106401023', '06012', 1, '2015-01-09 21:21:07'),
(64, '学生 1106401023【贺盼华】提交了开题答辩申请', '学生 1106401023【贺盼华】提交了开题答辩申请，请你及时查看，并确认是否同意其参加答辩', '06012', '1106401023', 1, '2015-01-09 21:51:33'),
(65, '你的指导老师 06012 【我是06012】 不同意你参加答辩', '你的指导老师 06012 【我是06012】 不同意你参加答辩，请你完善相关的文档后，再提交答辩申请', '1106401023', '06012', 1, '2015-01-09 21:59:06'),
(66, '学生 1106401023【贺盼华】提交了毕业答辩申请', '学生 1106401023【贺盼华】提交了毕业答辩申请，请你及时查看，并确认是否同意其参加答辩', '', '1106401023', 1, '2015-01-10 11:00:22'),
(67, '你的指导老师 06012 【我是06012】 不同意你参加答辩', '你的指导老师 06012 【我是06012】 不同意你参加答辩，请你完善相关的文档后，再提交答辩申请', '1106401023', '06012', 1, '2015-01-10 11:04:31'),
(68, '学生 1106401023【贺盼华】提交了毕业答辩申请', '学生 1106401023【贺盼华】提交了毕业答辩申请，请你及时查看，并确认是否同意其参加答辩', '', '1106401023', 1, '2015-01-10 11:05:46'),
(69, '学生 1106401023【贺盼华】提交了开题答辩申请', '学生 1106401023【贺盼华】提交了开题答辩申请，请你及时查看，并确认是否同意其参加答辩', '06012', '1106401023', 1, '2015-01-10 13:07:54'),
(70, '你被指导老师确认为任务人了', '你选的课题已经被老师我是06012确认为 任务人 ，请及时查看', '1106401005', '06012', 1, '2015-01-10 13:47:52'),
(71, '你被指导老师确认为任务人了', '你选的课题已经被老师我是06012确认为 任务人 ，请及时查看非任务人，请及时查看 ', '1106401021', '06012', 1, '2015-01-10 13:47:52'),
(72, '你被指导老师确认为任务人了', '你选的课题已经被老师我是06012确认为 任务人 ，请及时查看非任务人，请及时查看 非任务人，请及时查看 ', '1106401031', '06012', 1, '2015-01-10 13:47:52'),
(73, '学生选择教师课题', '学生：杨策 已选择您的课题并选择您为指导老师，请注意查看学生选择列表！', '06012', '1106401008', 1, '2015-01-10 13:53:50'),
(74, '你被指导老师拒绝为任务人了', '你选的课题已经被老师我是06012确认为 非任务人 ，请及时查看', '1106401008', '06012', 1, '2015-01-10 13:54:23'),
(75, '学生选择指导老师', '学生：张海兰 已选择您作为指导老师，请注意查看学生选择列表！', '06012', '1106401001', 1, '2015-01-10 14:01:11'),
(76, '课题转让', '学生：黄泽西 向你转让课题，请注意查看选择课题模块内容！', '1106401010', '1106401042', 1, '2015-01-10 14:04:25'),
(77, '学生选择指导老师', '学生：薛惠琼 已选择您作为指导老师，请注意查看学生选择列表！', '06012', '1106401010', 1, '2015-01-10 14:06:07'),
(78, '你被指导老师确认为任务人了', '你选的课题已经被老师我是06012确认为 任务人 ，请及时查看', '1106401010', '06012', 1, '2015-01-10 14:10:00'),
(79, '你被指导老师确认为任务人了', '你选的课题已经被老师我是06012确认为 任务人 ，请及时查看', '1106401001', '06012', 1, '2015-01-10 14:12:52'),
(80, '你的指导老师 06012 【我是06012】 同意你参加答辩', '你的指导老师 06012 【我是06012】 同意你参加答辩， 请你做好答辩的准备，及时查看答辩相关信息', '1106401023', '06012', 1, '2015-01-11 10:04:16'),
(81, '你的指导老师 06012 【我是06012】 同意你参加答辩', '你的指导老师 06012 【我是06012】 同意你参加答辩， 请你做好答辩的准备，及时查看答辩相关信息', '1106401023', '06012', 1, '2015-01-11 11:52:37'),
(82, '学生 1106401023【贺盼华】提交了毕业答辩申请', '学生 1106401023【贺盼华】提交了毕业答辩申请，请你及时查看，并确认是否同意其参加答辩', '', '1106401023', 1, '2015-01-11 16:44:37'),
(83, '你的指导老师 06012 【我是06012】 同意你参加答辩', '你的指导老师 06012 【我是06012】 同意你参加答辩， 请你做好答辩的准备，及时查看答辩相关信息', '1106401023', '06012', 1, '2015-01-11 16:45:21'),
(84, '学生 1106401001【张海兰】提交了开题答辩申请', '学生 1106401001【张海兰】提交了开题答辩申请，请你及时查看，并确认是否同意其参加答辩', '06012', '1106401001', 1, '2015-01-12 09:27:44'),
(85, '学生 1106401001【张海兰】提交了毕业答辩申请', '学生 1106401001【张海兰】提交了毕业答辩申请，请你及时查看，并确认是否同意其参加答辩', '06012', '1106401001', 1, '2015-01-12 09:29:23');

-- --------------------------------------------------------

--
-- 表的结构 `opentopicinfo`
--

DROP TABLE IF EXISTS `opentopicinfo`;
CREATE TABLE IF NOT EXISTS `opentopicinfo` (
`opi_ID` int(11) NOT NULL COMMENT '开题编号',
  `name` varchar(255) DEFAULT NULL COMMENT '标题|信息标题',
  `stu_ID` varchar(12) DEFAULT NULL COMMENT '学号',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `opi_Date` varchar(255) DEFAULT NULL COMMENT '开题日期',
  `opi_Place` varchar(20) DEFAULT NULL COMMENT '开题地点',
  `opi_Number` varchar(255) DEFAULT NULL COMMENT '开题答辩序号',
  `judge` varchar(255) NOT NULL COMMENT '评审教师',
  `headerman` varchar(255) NOT NULL COMMENT '评审组长',
  `begroup` varchar(255) DEFAULT NULL COMMENT '组别|所属组',
  `content` text COMMENT '内容|会议记录摘要',
  `status` varchar(255) NOT NULL DEFAULT '1' COMMENT '状态|1 未处理,2 已处理',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='开题答辩信息表';

--
-- 转存表中的数据 `opentopicinfo`
--

INSERT INTO `opentopicinfo` (`opi_ID`, `name`, `stu_ID`, `department_id`, `opi_Date`, `opi_Place`, `opi_Number`, `judge`, `headerman`, `begroup`, `content`, `status`, `create_time`) VALUES
(7, '', '1106401023', '064', '2015-01-23', 'E1A-302', '1', '06002,06003,06004,06005,06006,06007,06008,06011,06012', '06012', '1', '', '2', '2015-01-11 10:14:51');

-- --------------------------------------------------------

--
-- 表的结构 `opentopicscore`
--

DROP TABLE IF EXISTS `opentopicscore`;
CREATE TABLE IF NOT EXISTS `opentopicscore` (
`id` int(11) NOT NULL COMMENT 'id自增',
  `student_id` varchar(255) NOT NULL COMMENT '学生编号',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `score` double NOT NULL COMMENT '开题答辩分数',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='开题答辩成绩表';

--
-- 转存表中的数据 `opentopicscore`
--

INSERT INTO `opentopicscore` (`id`, `student_id`, `department_id`, `score`, `create_time`) VALUES
(4, '1106401023', '064', 88, '2015-01-11 10:40:29');

-- --------------------------------------------------------

--
-- 表的结构 `profession`
--

DROP TABLE IF EXISTS `profession`;
CREATE TABLE IF NOT EXISTS `profession` (
  `pro_ID` varchar(10) NOT NULL COMMENT '专业编号',
  `gra_ID` int(11) DEFAULT NULL COMMENT '年级编号',
  `pro_Name` varchar(50) NOT NULL COMMENT '专业名称',
  `dept_ID` varchar(255) DEFAULT NULL COMMENT '系部编号'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='专业表';

--
-- 转存表中的数据 `profession`
--

INSERT INTO `profession` (`pro_ID`, `gra_ID`, `pro_Name`, `dept_ID`) VALUES
('06401', 15, '计算机科学与技术', '064'),
('06402', 15, '网络工程', '064'),
('06403', 16, '计算机科学与技术', '064'),
('06404', 16, '网络工程', '064'),
('06405', 18, '计算机科学与技术', '064'),
('06406', 18, '网络工程', '064'),
('06407', 19, '计算机科学与技术', '064'),
('06408', 19, '网络工程', '064'),
('06409', 17, '计算机科学与技术', '064'),
('06410', 17, '网络工程', '064'),
('06411', 20, '计算机科学与技术', '064'),
('06412', 20, '网络工程', '064'),
('06413', 20, '软件工程', '064');

-- --------------------------------------------------------

--
-- 表的结构 `revieworder`
--

DROP TABLE IF EXISTS `revieworder`;
CREATE TABLE IF NOT EXISTS `revieworder` (
`id` int(11) NOT NULL COMMENT 'id自增',
  `student_id` varchar(255) NOT NULL COMMENT '学生编号',
  `teacher_id` varchar(255) NOT NULL COMMENT '教师编号',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `tbgrade_id` varchar(255) NOT NULL COMMENT '年级|所属年级',
  `status` varchar(255) NOT NULL DEFAULT '1' COMMENT '状态|1 未处理,2 已处理',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=190 DEFAULT CHARSET=utf8 COMMENT='毕业评阅教师安排表';

--
-- 转存表中的数据 `revieworder`
--

INSERT INTO `revieworder` (`id`, `student_id`, `teacher_id`, `department_id`, `tbgrade_id`, `status`, `create_time`) VALUES
(144, '1106401001', '06034', '064', '17', '1', '2015-01-11 11:27:04'),
(145, '1106401002', '06009', '064', '17', '1', '2015-01-11 11:27:04'),
(146, '1106401003', '06017', '064', '17', '1', '2015-01-11 11:27:04'),
(147, '1106401004', '06024', '064', '17', '1', '2015-01-11 11:27:04'),
(148, '1106401005', '06006', '064', '17', '1', '2015-01-11 11:27:04'),
(149, '1106401006', '06038', '064', '17', '1', '2015-01-11 11:27:04'),
(150, '1106401007', '06013', '064', '17', '1', '2015-01-11 11:27:04'),
(151, '1106401008', '06020', '064', '17', '1', '2015-01-11 11:27:04'),
(152, '1106401009', '06016', '064', '17', '1', '2015-01-11 11:27:04'),
(153, '1106401010', '06033', '064', '17', '1', '2015-01-11 11:27:04'),
(154, '1106401011', '06012', '064', '17', '1', '2015-01-11 11:27:04'),
(155, '1106401012', '06018', '064', '17', '1', '2015-01-11 11:27:04'),
(156, '1106401013', '06024', '064', '17', '1', '2015-01-11 11:27:04'),
(157, '1106401014', '06036', '064', '17', '1', '2015-01-11 11:27:04'),
(158, '1106401015', '06017', '064', '17', '1', '2015-01-11 11:27:04'),
(159, '1106401016', '06028', '064', '17', '1', '2015-01-11 11:27:04'),
(160, '1106401017', '06003', '064', '17', '1', '2015-01-11 11:27:04'),
(161, '1106401018', '06010', '064', '17', '1', '2015-01-11 11:27:04'),
(162, '1106401019', '06024', '064', '17', '1', '2015-01-11 11:27:04'),
(163, '1106401020', '06007', '064', '17', '1', '2015-01-11 11:27:04'),
(164, '1106401021', '06010', '064', '17', '1', '2015-01-11 11:27:04'),
(165, '1106401022', '06003', '064', '17', '1', '2015-01-11 11:27:04'),
(166, '1106401023', '06017', '064', '17', '2', '2015-01-11 11:27:04'),
(167, '1106401024', '06023', '064', '17', '1', '2015-01-11 11:27:04'),
(168, '1106401025', '06038', '064', '17', '1', '2015-01-11 11:27:04'),
(169, '1106401026', '06022', '064', '17', '1', '2015-01-11 11:27:04'),
(170, '1106401027', '06026', '064', '17', '1', '2015-01-11 11:27:04'),
(171, '1106401028', '06023', '064', '17', '1', '2015-01-11 11:27:04'),
(172, '1106401029', '06021', '064', '17', '1', '2015-01-11 11:27:04'),
(173, '1106401030', '06016', '064', '17', '1', '2015-01-11 11:27:04'),
(174, '1106401031', '06006', '064', '17', '1', '2015-01-11 11:27:04'),
(175, '1106401032', '06013', '064', '17', '1', '2015-01-11 11:27:04'),
(176, '1106401033', '06003', '064', '17', '1', '2015-01-11 11:27:04'),
(177, '1106401034', '06027', '064', '17', '1', '2015-01-11 11:27:04'),
(178, '1106401035', '06020', '064', '17', '1', '2015-01-11 11:27:04'),
(179, '1106401036', '06008', '064', '17', '1', '2015-01-11 11:27:04'),
(180, '1106401037', '06016', '064', '17', '1', '2015-01-11 11:27:04'),
(181, '1106401039', '06012', '064', '17', '1', '2015-01-11 11:27:04'),
(182, '1106401040', '06034', '064', '17', '1', '2015-01-11 11:27:04'),
(183, '1106401041', '06025', '064', '17', '1', '2015-01-11 11:27:04'),
(184, '1106401042', '06009', '064', '17', '1', '2015-01-11 11:27:04'),
(185, '1106401043', '06016', '064', '17', '1', '2015-01-11 11:27:04'),
(186, '1106401046', '06023', '064', '17', '1', '2015-01-11 11:27:04'),
(187, '1106401047', '06007', '064', '17', '1', '2015-01-11 11:27:04'),
(188, '1206403011', '06013', '064', '17', '1', '2015-01-11 11:27:04'),
(189, '1206403014', '06011', '064', '17', '1', '2015-01-11 11:27:04');

-- --------------------------------------------------------

--
-- 表的结构 `reviewresult`
--

DROP TABLE IF EXISTS `reviewresult`;
CREATE TABLE IF NOT EXISTS `reviewresult` (
`id` int(11) NOT NULL COMMENT '编号|系统自动生成',
  `top_ID` varchar(255) NOT NULL COMMENT '课题ID',
  `judge_ID` varchar(255) NOT NULL COMMENT '评审人ID',
  `status` varchar(255) NOT NULL DEFAULT '1' COMMENT '评审结果|1 通过 2 不通过'
) ENGINE=MyISAM AUTO_INCREMENT=136 DEFAULT CHARSET=utf8 COMMENT='课题评审结果';

--
-- 转存表中的数据 `reviewresult`
--

INSERT INTO `reviewresult` (`id`, `top_ID`, `judge_ID`, `status`) VALUES
(52, '20141231051648', '03037', '1'),
(53, '20141231052618', '03037', '1'),
(54, '20141231052618', '06014', '1'),
(55, '20141231060103', '06014', '1'),
(56, '20141231051648', '06014', '1'),
(57, '20141231060532', '06014', '1'),
(58, '20141231060338', '06014', '1'),
(59, '20141231060155', '06014', '1'),
(60, '20150109025330', '06012', '1'),
(61, '20141231051648', '06012', '1'),
(62, '20141231052618', '06012', '2'),
(63, '20141231060103', '06012', '1'),
(64, '20141231060155', '06012', '1'),
(65, '20141231060532', '06012', '1'),
(66, '20141231090816', '06012', '1'),
(67, '20150109021159', '06012', '1'),
(68, '20141231084558', '06012', '2'),
(69, '20141231051648', '06002', '2'),
(70, '20141231052618', '06002', '2'),
(71, '20141231060103', '06002', '1'),
(72, '20141231060155', '06002', '1'),
(73, '20141231060338', '06002', '1'),
(74, '20141231084558', '06002', '1'),
(75, '20141231060532', '06002', '1'),
(76, '20141231090816', '06002', '1'),
(77, '20150109021159', '06002', '1'),
(78, '20150109025330', '06002', '1'),
(79, '20150109110622', '06002', '1'),
(80, '20150109110802', '06002', '1'),
(81, '20150109110802', '06003', '1'),
(82, '20150109110622', '06003', '1'),
(83, '20150109025330', '06003', '1'),
(84, '20141231051648', '06003', '2'),
(85, '20141231052618', '06003', '2'),
(86, '20141231060103', '06003', '2'),
(87, '20141231060155', '06003', '1'),
(88, '20141231060338', '06003', '1'),
(89, '20141231060532', '06003', '1'),
(90, '20141231084558', '06003', '1'),
(91, '20141231090816', '06003', '1'),
(92, '20150109021159', '06003', '2'),
(93, '20141231051648', '06004', '2'),
(94, '20141231052618', '06004', '2'),
(95, '20141231060103', '06004', '1'),
(96, '20141231060155', '06004', '1'),
(97, '20141231060338', '06004', '1'),
(98, '20141231060532', '06004', '1'),
(99, '20150109025330', '06004', '1'),
(100, '20150109021159', '06004', '1'),
(101, '20150109110622', '06004', '2'),
(102, '20150109110802', '06004', '1'),
(103, '20141231051648', '06005', '2'),
(104, '20141231060103', '06005', '1'),
(105, '20141231060155', '06005', '1'),
(106, '20141231060338', '06005', '1'),
(107, '20141231090816', '06005', '2'),
(108, '20150109110622', '06005', '1'),
(109, '20150109025330', '06005', '1'),
(110, '20150109021159', '06005', '2'),
(111, '20150109110802', '06005', '1'),
(112, '20150109110802', '06006', '1'),
(113, '20141231052618', '06006', '2'),
(114, '20141231060103', '06006', '1'),
(115, '20141231060155', '06006', '1'),
(116, '20141231060338', '06006', '2'),
(117, '20141231090816', '06006', '2'),
(118, '20150109021159', '06006', '2'),
(119, '20150109110622', '06006', '1'),
(120, '20141231060338', '06007', '2'),
(121, '20141231084558', '06007', '1'),
(122, '20141231060532', '06007', '1'),
(123, '20141231090816', '06007', '2'),
(124, '20150109021159', '06007', '2'),
(125, '20150109025330', '06007', '1'),
(126, '20150109110622', '06007', '1'),
(127, '20150109110802', '06007', '2'),
(128, '20141231060532', '06009', '2'),
(129, '20141231090816', '06009', '2'),
(130, '20141231084558', '06009', '1'),
(131, '20150109025330', '06009', '1'),
(132, '20150109110622', '06009', '2'),
(133, '20150109110802', '06009', '1'),
(134, '20141231084558', '06010', '2'),
(135, '20141231084558', '06011', '2');

-- --------------------------------------------------------

--
-- 表的结构 `room`
--

DROP TABLE IF EXISTS `room`;
CREATE TABLE IF NOT EXISTS `room` (
`id` int(11) NOT NULL COMMENT 'id自增',
  `number` varchar(255) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '标题|信息标题',
  `parent_id` varchar(255) NOT NULL COMMENT '所属父类',
  `usable` varchar(255) NOT NULL DEFAULT '1' COMMENT '是否可用| 1 可用 2 不可用',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='教室|教室模块';

--
-- 转存表中的数据 `room`
--

INSERT INTO `room` (`id`, `number`, `name`, `parent_id`, `usable`, `create_time`) VALUES
(10, 'E1A-301', '东区A栋301', '064', '1', '2014-12-31 17:20:28'),
(11, 'E1A-302', '东区A栋302', '064', '1', '2014-12-31 17:22:48');

-- --------------------------------------------------------

--
-- 表的结构 `selectfirst`
--

DROP TABLE IF EXISTS `selectfirst`;
CREATE TABLE IF NOT EXISTS `selectfirst` (
`sel_ID` int(11) NOT NULL COMMENT '标号',
  `top_ID` varchar(255) DEFAULT NULL COMMENT '课题ID',
  `stu_ID` varchar(12) NOT NULL COMMENT '选择人学号',
  `sel_knowing` text COMMENT '对课题的认识',
  `sel_status` varchar(10) NOT NULL COMMENT '状态--默认0未被选择，1确定为任务人 2 确定为非任务人',
  `tea_ID` varchar(12) DEFAULT NULL COMMENT '教师ID',
  `dept_ID` varchar(10) DEFAULT NULL COMMENT '系部ID'
) ENGINE=MyISAM AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='课题的初步选择记录表';

--
-- 转存表中的数据 `selectfirst`
--

INSERT INTO `selectfirst` (`sel_ID`, `top_ID`, `stu_ID`, `sel_knowing`, `sel_status`, `tea_ID`, `dept_ID`) VALUES
(30, '20150109025330', '1106401042', '<p>基于android的掌上点餐系统</p>', '2', '06012', '064'),
(31, '20150109110802', '1106401001', '<p>湘西旅游导游----android 应用开发</p>', '1', '06012', '064'),
(32, '20150109025330', '1106401023', '<p>股票软件</p>', '1', '06012', '064'),
(33, '20141231084558', '1106401021', '<p>移动应用开发</p>', '2', '06012', '064'),
(34, '20150109025330', '1106401030', '<p>股票软件</p>', '2', '06012', '064'),
(35, '20141231084558', '1106401031', '<p>移动应用开发</p>', '2', '06012', '064'),
(36, '20141231084558', '1106401005', '<p>移动应用开发</p>', '1', '06012', '064'),
(37, '20150109025330', '1106401007', '<p>移动应用开发</p>', '2', '06012', '064'),
(38, NULL, '1106401040', NULL, '0', NULL, '064'),
(39, NULL, '1106401036', NULL, '0', NULL, '064'),
(40, '20141231084558', '1106401008', NULL, '2', '06012', '064'),
(41, '20141231060155', '1106401010', '<p>基于android的掌上点餐系统</p>', '1', '06012', '064');

-- --------------------------------------------------------

--
-- 表的结构 `settime`
--

DROP TABLE IF EXISTS `settime`;
CREATE TABLE IF NOT EXISTS `settime` (
`id` int(11) NOT NULL COMMENT '系统自动生成',
  `name` varchar(255) DEFAULT NULL COMMENT '标题',
  `dept_ID` varchar(255) DEFAULT NULL COMMENT '系部编号',
  `gra_number` varchar(255) DEFAULT NULL COMMENT '届数|对应哪一届',
  `start_time` varchar(255) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(255) DEFAULT NULL COMMENT '结束时间',
  `mark` varchar(255) DEFAULT NULL COMMENT '1 提交课题 2审核 3选择 4 查看课题'
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='时间设置表';

--
-- 转存表中的数据 `settime`
--

INSERT INTO `settime` (`id`, `name`, `dept_ID`, `gra_number`, `start_time`, `end_time`, `mark`) VALUES
(15, '2015届毕业生课题提交时间', '064', '17', '2014-12-31 17:15:08', '2015-03-12 00:00:00', '1'),
(16, '2015届毕业生审核课题时间', '064', '17', '2014-12-31 17:16:38', '2015-03-12 00:00:00', '2'),
(17, '2015届选择课题时间', '064', '17', '2014-12-31 17:17:38', '2015-03-20 00:00:00', '3'),
(18, '2015届开题答辩申请时间', '064', '17', '2014-12-31 17:18:43', '2015-03-05 00:00:00', '5'),
(19, '2015届毕业答辩申请时间', '064', '17', '2014-12-31 17:19:18', '2015-03-05 00:00:00', '6'),
(20, '2015届毕业生转让课题时间', '064', '17', '2015-01-09 21:27:03', '2015-03-12 00:00:00', '7');

-- --------------------------------------------------------

--
-- 表的结构 `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `stu_ID` varchar(12) NOT NULL COMMENT '学号',
  `cla_ID` varchar(255) DEFAULT NULL COMMENT '班级编号',
  `stu_Name` varchar(16) DEFAULT NULL COMMENT '姓名',
  `stu_Sex` varchar(4) DEFAULT NULL COMMENT '性别| 1 男 2 女',
  `stu_Tel` varchar(15) DEFAULT NULL COMMENT '电话',
  `stu_Email` varchar(30) NOT NULL COMMENT '邮箱',
  `status` varchar(255) NOT NULL DEFAULT '1' COMMENT '状态| 1  激活 2 未激活  3 不能使用',
  `image_path` varchar(255) DEFAULT NULL COMMENT '头像路径'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='学生表';

--
-- 转存表中的数据 `student`
--

INSERT INTO `student` (`stu_ID`, `cla_ID`, `stu_Name`, `stu_Sex`, `stu_Tel`, `stu_Email`, `status`, `image_path`) VALUES
('1106401002', '0640101', '李靖文', '1', '15874592649', 'xjiujiu@foxmail.com', '1', NULL),
('1206403014', '0640101', '向延珍', '1', '1511525930', '740713481@sina.cn', '1', 'uploadfiles/20141231/0f506870-d092-441b-9023-d5599a86142d.jpg'),
('1206403011', '0640101', '哇哇', '2', '11', '54891@1.com', '1', NULL),
('1106401001', '0640101', '张海兰', '2', '15874592649', '1505388138@qq.com', '1', NULL),
('1106401003', '0640101', '莫换招', '2', '15874592649', 'huangzec@foxmail.com', '1', NULL),
('1106401004', '0640101', '李苗', '1', '15111592794', 'huangzec@foxmail.com', '1', NULL),
('1106401005', '0640101', '张会超', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401006', '0640101', '李莉', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401007', '0640101', '李占晓', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401008', '0640101', '杨策', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401009', '0640101', '包一伟', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401010', '0640101', '薛惠琼', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401011', '0640101', '周豪侠', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401012', '0640101', '李亚萍', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401013', '0640101', '姜其灿', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401014', '0640101', '张惠君', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401015', '0640101', '黄岳军', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401016', '0640101', '张谢勇', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401017', '0640101', '何强', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401018', '0640101', '汤政', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401019', '0640101', '邹丹', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401020', '0640101', '谢琪', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401021', '0640101', '刘剑华', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401022', '0640101', '谭亮', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401023', '0640101', '贺盼华', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401024', '0640101', '冯甜', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401025', '0640101', '尹稳定', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401026', '0640101', '刘美', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401027', '0640101', '罗政', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401028', '0640101', '赵雄枫', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401029', '0640101', '李官武', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401030', '0640101', '黄艳', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401031', '0640101', '刘炎培', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401032', '0640101', '林佳', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401033', '0640101', '康德志', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401034', '0640101', '王浪', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401035', '0640101', '胡文俊', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401036', '0640101', '廖哲民', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401037', '0640101', '李勇', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401039', '0640101', '唐真', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401040', '0640101', '袁洁', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401041', '0640101', '龙兴华', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401042', '0640101', '黄泽西', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401043', '0640101', '蒋周民', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401046', '0640101', '熊华源', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106401047', '0640101', '张家超', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402001', '0640102', '何群娣', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402002', '0640102', '黄洁霞', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402003', '0640102', '高星', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402004', '0640102', '郭辉', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402005', '0640102', '代立群', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402006', '0640102', '陈杨', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402007', '0640102', '支妹', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402008', '0640102', '余英豪', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402009', '0640102', '何霖', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402010', '0640102', '徐荣峰', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402011', '0640102', '陈辉', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402012', '0640102', '张文禄', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402013', '0640102', '陈金兰', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402014', '0640102', '张楚', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402015', '0640102', '邱少雄', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402016', '0640102', '钟楚历', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402017', '0640102', '李杰', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402018', '0640102', '丁小芳', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402019', '0640102', '曾凯丽', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402020', '0640102', '戴翔', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402021', '0640102', '吴娟', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402022', '0640102', '杨成伟', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402023', '0640102', '周奔', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402024', '0640102', '刘玉樱', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402025', '0640102', '胡伟亮', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402026', '0640102', '沈云顺', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402027', '0640102', '龙婷婷', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402028', '0640102', '沈轲', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402029', '0640102', '罗丹', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402030', '0640102', '朱尚军', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402031', '0640102', '尹明', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402032', '0640102', '郑召召', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402033', '0640102', '曾安安', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402034', '0640102', '卓勇', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402035', '0640102', '杨华', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402036', '0640102', '石雄伟', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402037', '0640102', '曹斌', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402038', '0640102', '谭松松', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402039', '0640102', '蒋淑', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402040', '0640102', '周权', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402041', '0640102', '梁玉叶', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402042', '0640102', '黄李金', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402043', '0640102', '黄寿敏', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402044', '0640102', '钟森明', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1106402045', '0640102', '叶少华', '2', '', 'huangzec@foxmail.com', '1', NULL),
('1112404029', '0640102', '吴瑶', '2', '', 'huangzec@foxmail.com', '1', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `stu_tea_topic`
--

DROP TABLE IF EXISTS `stu_tea_topic`;
CREATE TABLE IF NOT EXISTS `stu_tea_topic` (
`stt_ID` int(11) NOT NULL,
  `stt_Status` bit(1) DEFAULT NULL,
  `stu_ID` varchar(12) DEFAULT NULL,
  `tea_ID` varchar(12) DEFAULT NULL,
  `Tea_tea_ID` varchar(12) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `taskdoc`
--

DROP TABLE IF EXISTS `taskdoc`;
CREATE TABLE IF NOT EXISTS `taskdoc` (
`tD_ID` int(11) NOT NULL COMMENT '任务书编号',
  `title` varchar(255) DEFAULT NULL COMMENT '题目|毕业设计题目',
  `stu_ID` varchar(12) DEFAULT NULL COMMENT '学号',
  `tea_ID` varchar(12) DEFAULT NULL COMMENT '教师工号',
  `source` varchar(255) DEFAULT NULL COMMENT '题目来源|1 科学技术 2 生产实践 3 社会经济 4 自拟 5 其他',
  `tD_conRequest` text COMMENT '毕业设计内容要求',
  `tD_devTools` text COMMENT '开发工具',
  `tD_refMaterial` text COMMENT '参考资料',
  `tD_workPlane` text COMMENT '工作计划',
  `receipt_time` varchar(255) DEFAULT NULL COMMENT '接收任务日期',
  `finish_time` varchar(255) DEFAULT NULL COMMENT '要求完成任务日期',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='毕业设计任务书';

--
-- 转存表中的数据 `taskdoc`
--

INSERT INTO `taskdoc` (`tD_ID`, `title`, `stu_ID`, `tea_ID`, `source`, `tD_conRequest`, `tD_devTools`, `tD_refMaterial`, `tD_workPlane`, `receipt_time`, `finish_time`, `create_time`) VALUES
(4, '股票软件', '1106401023', '06012', '2', '&lt;p&gt;												&lt;&#x2F;p&gt;&lt;p&gt;毕业设计要求&lt;&#x2F;p&gt;&lt;p&gt;\r\n											&lt;&#x2F;p&gt;', NULL, '&lt;p&gt;												&lt;&#x2F;p&gt;&lt;p&gt;参考资料&lt;&#x2F;p&gt;&lt;p&gt;\r\n											&lt;&#x2F;p&gt;', '&lt;p&gt;												&lt;&#x2F;p&gt;&lt;p&gt;工作计划&lt;&#x2F;p&gt;&lt;p&gt;	\r\n											&lt;&#x2F;p&gt;', '2015-1-9', '2015-05-14', '2015-01-09 21:21:07');

-- --------------------------------------------------------

--
-- 表的结构 `tballdoc`
--

DROP TABLE IF EXISTS `tballdoc`;
CREATE TABLE IF NOT EXISTS `tballdoc` (
`id` int(11) NOT NULL COMMENT 'id索引',
  `stu_ID` varchar(12) NOT NULL COMMENT '学生ID',
  `dept_ID` varchar(10) NOT NULL COMMENT '系部ID',
  `doc_Title` varchar(255) DEFAULT NULL COMMENT '标题',
  `doc_Content` varchar(255) DEFAULT NULL COMMENT '文件内容',
  `file_Path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间'
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='最终所有材料信息表';

--
-- 转存表中的数据 `tballdoc`
--

INSERT INTO `tballdoc` (`id`, `stu_ID`, `dept_ID`, `doc_Title`, `doc_Content`, `file_Path`, `create_time`) VALUES
(7, '1206403011', '064', 'graduation', 'graduation.zip', '/uploadfiles/2014-12-31/b5b32a8e-afae-4476-9d42-35ca39cd33cd.zip', '2014-12-31 17:21:34'),
(8, '1106401023', '064', 'PHP：面向对象、模式与实践（第三版）高清PDF和完整源码', 'PHP：面向对象、模式与实践（第三版）高清PDF和完整源码.rar', '/uploadfiles/2015-01-12/0caac25b-476c-4176-9bfa-b80c2a5707dd.rar', '2015-01-12 09:35:36'),
(9, '1106401023', '064', 'Joomla_3.3.6-Stable-Full_Package', 'Joomla_3.3.6-Stable-Full_Package.zip', '/uploadfiles/2015-01-12/404d6c15-0c22-4333-8e6a-65c52f80472e.zip', '2015-01-12 09:44:11');

-- --------------------------------------------------------

--
-- 表的结构 `tbclass`
--

DROP TABLE IF EXISTS `tbclass`;
CREATE TABLE IF NOT EXISTS `tbclass` (
  `cla_ID` varchar(255) NOT NULL COMMENT '班级编号',
  `cla_Name` varchar(255) DEFAULT NULL COMMENT '班级名称',
  `pro_ID` varchar(10) DEFAULT NULL COMMENT '专业编号'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='班级表';

--
-- 转存表中的数据 `tbclass`
--

INSERT INTO `tbclass` (`cla_ID`, `cla_Name`, `pro_ID`) VALUES
('0640101', '11计算机1班', '06409'),
('0640102', '11计算机2班', '06409'),
('0640201', '12计算机1班', '06411'),
('0640202', '12计算机2班', '06411'),
('0640103', '11网络工程3班', '06410'),
('0640104', '11网络工程4班', '06410'),
('0640203', '12网络工程3班', '06412'),
('0640204', '12网络工程4班', '06412');

-- --------------------------------------------------------

--
-- 表的结构 `tbdocnum`
--

DROP TABLE IF EXISTS `tbdocnum`;
CREATE TABLE IF NOT EXISTS `tbdocnum` (
`id` int(11) NOT NULL COMMENT 'id索引',
  `docnum` int(11) DEFAULT NULL COMMENT '文件数量',
  `numstate` varchar(20) DEFAULT NULL COMMENT '审核状态',
  `stu_ID` varchar(12) DEFAULT NULL COMMENT '学号'
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='文件数量信息表';

--
-- 转存表中的数据 `tbdocnum`
--

INSERT INTO `tbdocnum` (`id`, `docnum`, `numstate`, `stu_ID`) VALUES
(1, 1, '0', '1206403011'),
(2, 1, '2', '1106401023');

-- --------------------------------------------------------

--
-- 表的结构 `tbgrade`
--

DROP TABLE IF EXISTS `tbgrade`;
CREATE TABLE IF NOT EXISTS `tbgrade` (
`gra_ID` int(11) NOT NULL COMMENT '年级编号',
  `dept_ID` varchar(10) DEFAULT NULL COMMENT '系部ID',
  `gra_Number` varchar(8) NOT NULL COMMENT '届数'
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='年级表';

--
-- 转存表中的数据 `tbgrade`
--

INSERT INTO `tbgrade` (`gra_ID`, `dept_ID`, `gra_Number`) VALUES
(15, '064', '2011届'),
(16, '064', '2012届'),
(17, '064', '2015届'),
(18, '064', '2013届'),
(19, '064', '2014届'),
(20, '064', '2016届');

-- --------------------------------------------------------

--
-- 表的结构 `tboffice`
--

DROP TABLE IF EXISTS `tboffice`;
CREATE TABLE IF NOT EXISTS `tboffice` (
  `off_ID` varchar(12) NOT NULL COMMENT '管理员ID',
  `off_Name` varchar(16) DEFAULT NULL COMMENT '姓名',
  `off_Sex` varchar(4) DEFAULT NULL COMMENT '性别',
  `off_Tel` varchar(15) DEFAULT NULL COMMENT '电话',
  `off_Email` varchar(30) NOT NULL COMMENT '邮箱'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='教务处管理员表';

--
-- 转存表中的数据 `tboffice`
--

INSERT INTO `tboffice` (`off_ID`, `off_Name`, `off_Sex`, `off_Tel`, `off_Email`) VALUES
('admin', 'admin', NULL, NULL, '');

-- --------------------------------------------------------

--
-- 表的结构 `tbtopic`
--

DROP TABLE IF EXISTS `tbtopic`;
CREATE TABLE IF NOT EXISTS `tbtopic` (
  `top_ID` varchar(255) NOT NULL COMMENT '课题ID',
  `top_commitID` varchar(12) DEFAULT NULL COMMENT '提交人ID',
  `top_Name` varchar(100) NOT NULL COMMENT '课题名称',
  `top_Number` int(11) DEFAULT NULL COMMENT '完成人数',
  `top_Status` varchar(255) DEFAULT NULL COMMENT '0-未审核 | 1-通过 | 2-未通过',
  `top_Content` text COMMENT '课题内容',
  `top_Flag` varchar(10) CHARACTER SET ucs2 DEFAULT NULL COMMENT '标记提交人类型',
  `top_Type` varchar(10) NOT NULL COMMENT '课题类型| 1 论文 2 设计 3 其他',
  `top_Source` varchar(255) NOT NULL COMMENT '课题来源| 1 科学技术 2 生产实践 3 社会经济 4  自拟 5 其他',
  `top_Keywords` varchar(255) NOT NULL COMMENT '关键字',
  `top_Year` varchar(255) DEFAULT NULL COMMENT '年份',
  `dept_ID` varchar(255) DEFAULT NULL COMMENT '所属系部',
  `parent_ID` varchar(255) NOT NULL DEFAULT '0' COMMENT '父课题ID',
  `completer_ID` varchar(12) DEFAULT NULL COMMENT '任务人学号'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='课题表';

--
-- 转存表中的数据 `tbtopic`
--

INSERT INTO `tbtopic` (`top_ID`, `top_commitID`, `top_Name`, `top_Number`, `top_Status`, `top_Content`, `top_Flag`, `top_Type`, `top_Source`, `top_Keywords`, `top_Year`, `dept_ID`, `parent_ID`, `completer_ID`) VALUES
('20141231051648', '1206403011', '怀化学院计算机系工程', 1, '2', '<p>怀化学院计算机系操作系统</p>', '1', '2', '3', '', '2014', '064', '0', NULL),
('20141231052618', '06014', '怀化学院', 2, '2', '<p>子课题名称一子课题名称一子课题名称一子课题名称一子课题名称一子课题名称一</p>', '2', '1', '1', '课题', '2014', '064', '0', NULL),
('201412310526181', '06014', '子课题名称一', 1, '2', '<p>子课题名称一子课题名称一子课题名称一子课题名称一子课题名称一子课题名称一</p>', '2', '1', '1', '课题', '2014', '064', '20141231052618', NULL),
('201412310526182', '06014', '子课题名称二', 1, '2', '<p>子课题名称一子课题名称一子课题名称一子课题名称一子课题名称一子课题名称一</p>', '2', '1', '1', '课题', '2014', '064', '20141231052618', NULL),
('20141231060103', '1106401042', '基于android的掌上点餐系统', 1, '1', '<p>基于android的掌上点餐系统</p>', '1', '2', '1', 'android', NULL, '064', '0', NULL),
('20141231060155', '1106401042', 'springmvc毕业设计', 1, '1', '<p>基于android的掌上点餐系统</p>', '1', '1', '1', 'android', NULL, '064', '0', NULL),
('20141231060338', '1106401042', 'php自动化', 1, '1', '<p>php自动化</p>', '1', '1', '1', 'php', '2014', '064', '0', NULL),
('20141231060532', '1106401042', 'android 开发', 1, '1', '<p>android 开发</p>', '1', '2', '1', 'android', '2014', '064', '0', NULL),
('20141231084558', '06012', '移动应用开发', 1, '1', '<p>移动应用开发</p>', '2', '2', '4', '移动应用', '2014', '064', '0', NULL),
('20141231090816', '1206403014', '放大是范德萨费', 1, '2', '<p>放大是范德萨费放大是范德萨费放大是范德萨费放大是范德萨费放大是范德萨费放大是范德萨费</p>', '1', '1', '1', '放大', '2014', '064', '0', NULL),
('20150109110622', '1106401001', '湘西旅游开发', 1, '1', '<p><br/>湘西旅游开发</p>', '1', '3', '2', '湘西', '2015', '064', '0', NULL),
('20150109110802', '1106401001', '湘西旅游导游----android 应用开发', 1, '1', '<p>湘西旅游导游----android 应用开发</p>', '1', '2', '3', 'android', '2015', '064', '0', NULL),
('20150109021159', '06012', '4949小游戏', 2, '2', '<p>4949小游戏</p>', '2', '2', '2', '游戏', '2015', '064', '0', NULL),
('201501090211591', '06012', '4949小游戏-后台', 1, '2', '<p>4949小游戏</p>', '2', '2', '2', '游戏', NULL, '064', '20150109021159', NULL),
('201501090211592', '06012', '4949小游戏--移动端', 1, '2', '<p>4949小游戏</p>', '2', '2', '2', '游戏', NULL, '064', '20150109021159', NULL),
('20150109025330', '06012', '股票软件', 1, '1', '<p>股票软件</p>', '2', '2', '4', '股票', '2015', '064', '0', NULL),
('20150111060808', '06012', 'jquery 开发', 2, '0', '<p>jquery 开发</p>', '2', '2', '2', 'jquery ', '2015', '064', '0', NULL),
('201501110608081', '06012', 'jquery 开发--web', 1, '0', '<p>jquery 开发</p>', '2', '2', '2', 'jquery ', NULL, '064', '20150111060808', NULL),
('201501110608082', '06012', 'jquery 开发--moblie', 1, '0', '<p>jquery 开发</p>', '2', '2', '2', 'jquery ', NULL, '064', '20150111060808', NULL),
('20150111084334', '1106401001', 'spring hibnet', 1, '0', '<p>spring hibnet</p>', '1', '1', '2', 'android', '2015', '064', '0', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `teacher`
--

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE IF NOT EXISTS `teacher` (
  `tea_ID` varchar(12) NOT NULL COMMENT '教师工号',
  `dept_ID` varchar(10) DEFAULT NULL COMMENT '系部ID',
  `tea_Name` varchar(16) DEFAULT NULL COMMENT '教师姓名',
  `tea_Sex` varchar(4) DEFAULT NULL COMMENT '性别',
  `tea_Pos` varchar(20) DEFAULT NULL COMMENT '职称|0 助教 1 讲师 2 副教授 3 教授',
  `status` varchar(255) NOT NULL DEFAULT '1' COMMENT '状态| 1  激活 2 未激活  3 不能使用',
  `tea_Judge` tinyint(1) DEFAULT NULL COMMENT '课题审核',
  `tea_Group` tinyint(1) DEFAULT NULL COMMENT '答辩组长',
  `tea_Tel` varchar(15) DEFAULT NULL COMMENT '联系电话',
  `tea_Email` varchar(30) NOT NULL COMMENT '电子邮箱',
  `image_path` varchar(255) DEFAULT NULL COMMENT '头像路径'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='教师信息表';

--
-- 转存表中的数据 `teacher`
--

INSERT INTO `teacher` (`tea_ID`, `dept_ID`, `tea_Name`, `tea_Sex`, `tea_Pos`, `status`, `tea_Judge`, `tea_Group`, `tea_Tel`, `tea_Email`, `image_path`) VALUES
('06002', '064', '黄老师', '2', '2', '1', NULL, NULL, '1555', '1505388138@qq.com', NULL),
('06033', '064', '黄隆华', '1', '1', '1', NULL, NULL, '', '1111@qq.com', NULL),
('06034', '064', '张三', '1', '0', '1', NULL, NULL, '', '1234656@qq.com', NULL),
('06035', '064', '李四', '1', '0', '1', NULL, NULL, '', '123456@qq.com', NULL),
('06036', '064', '丽萨', '1', '0', '1', NULL, NULL, '', '4562@qq.com', NULL),
('06003', '064', '彭老师', '1', '4', '1', NULL, NULL, '', '1505388138@qq.com', NULL),
('06037', '064', '李伟', '1', '0', '1', NULL, NULL, '', '123466@qq.com', NULL),
('06038', '064', '米春桥', '1', '3', '1', NULL, NULL, '', '123456@qq.com', NULL),
('06001', '064', '彭老师', '1', '3', '1', NULL, NULL, '15874592649', '1505388138@qq.com', NULL),
('06004', '064', '黄老师', '2', '2', '1', NULL, NULL, '', '1505388138@qq.com', NULL),
('06005', '064', '彭老师', '1', '1', '1', NULL, NULL, '', '1505388138@qq.com', NULL),
('06006', '064', '黄老师', '1', '5', '1', NULL, NULL, '', '1505388138@qq.com', NULL),
('06007', '064', '彭老师', '2', '4', '1', NULL, NULL, '', '1505388138@qq.com', NULL),
('06008', '064', '黄老师', '1', '4', '1', NULL, NULL, '', '1505388138@qq.com', NULL),
('06009', '064', '彭老师', '1', '3', '1', NULL, NULL, '', '1505388138@qq.com', NULL),
('06010', '064', '黄老师', '2', '4', '1', NULL, NULL, '', '1505388138@qq.com', NULL),
('06011', '064', '我是06011', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06012', '064', '我是06012', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06013', '064', '我是06013', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06014', '064', '我是06014', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06015', '064', '我是06015', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06016', '064', '我是06016', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06017', '064', '我是06017', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06018', '064', '我是06018', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06019', '064', '我是06019', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06020', '064', '我是06020', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06021', '064', '我是06021', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06022', '064', '我是06022', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06023', '064', '我是06023', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06024', '064', '我是06024', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06025', '064', '我是06025', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06026', '064', '我是06026', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06027', '064', '我是06027', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06028', '064', '我是06028', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL),
('06029', '064', '我是06029', '2', '1', '1', NULL, NULL, '', 'huangzec@foxmail.com', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `topicapply`
--

DROP TABLE IF EXISTS `topicapply`;
CREATE TABLE IF NOT EXISTS `topicapply` (
`id` int(11) NOT NULL COMMENT 'id自增',
  `user_id` varchar(255) NOT NULL COMMENT '学生学号',
  `topic_id` varchar(255) NOT NULL COMMENT '课题ID',
  `parent_id` varchar(255) NOT NULL COMMENT '所属系部',
  `open_time` varchar(255) NOT NULL COMMENT '申请开通答辩时间',
  `knowing` text COMMENT '对课题的理解',
  `teacher_idea` text COMMENT '指导老师意见',
  `paper_idea` text COMMENT '论文委员会意见',
  `department_idea` text COMMENT '系部意见',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 '
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='开题答辩申请书';

--
-- 转存表中的数据 `topicapply`
--

INSERT INTO `topicapply` (`id`, `user_id`, `topic_id`, `parent_id`, `open_time`, `knowing`, `teacher_idea`, `paper_idea`, `department_idea`, `create_time`) VALUES
(7, '1106401023', '20150109025330', '064', '2015-01-31', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 13px; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 13px; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 13px; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 13px; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;&#x2F;span&gt;&lt;&#x2F;span&gt;&lt;&#x2F;span&gt;&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '<p>同意开题</p>', '', '', '2015-01-09'),
(8, '1106401023', '20150109025330', '064', '2015-01-16', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 13px; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '', '', '', '2015-01-10'),
(9, '1106401001', '20150109110802', '064', '2015-01-22', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 13px; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;湘西旅游导游----android 应用开发&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '', '', '', '2015-01-12');

-- --------------------------------------------------------

--
-- 表的结构 `topicfinish`
--

DROP TABLE IF EXISTS `topicfinish`;
CREATE TABLE IF NOT EXISTS `topicfinish` (
`id` int(11) NOT NULL COMMENT 'id自增',
  `stu_id` varchar(12) DEFAULT NULL COMMENT '学号',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `title` varchar(255) DEFAULT NULL COMMENT '标题|信息标题',
  `content` text COMMENT '内容|详细内容',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='毕业答辩相关材料表';

--
-- 转存表中的数据 `topicfinish`
--

INSERT INTO `topicfinish` (`id`, `stu_id`, `department_id`, `title`, `content`, `create_time`) VALUES
(11, '1106401023', '064', '贺盼华的毕业答辩申请相关材料', '&lt;p style=&quot;line-height: 16px;&quot;&gt;&lt;img style=&quot;vertical-align: middle; margin-right: 2px;&quot; src=&quot;&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;dialogs&#x2F;attachment&#x2F;fileTypeImages&#x2F;icon_rar.gif&quot;&#x2F;&gt;&lt;a style=&quot;font-size:12px; color:#0066cc;&quot; href=&quot;&#x2F;uploadfiles&#x2F;file&#x2F;20150110&#x2F;1420858815817058184.rar&quot; title=&quot;PHP：面向对象、模式与实践（第三版）高清PDF和完整源码.rar&quot;&gt;PHP：面向对象、模式与实践（第三版）高清PDF和完整源码.rar&lt;&#x2F;a&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;', '2015-01-10 11:00:22'),
(12, '1106401023', '064', '贺盼华的毕业答辩申请相关材料', '&lt;p style=&quot;line-height: 16px;&quot;&gt;&lt;img style=&quot;vertical-align: middle; margin-right: 2px;&quot; src=&quot;&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;dialogs&#x2F;attachment&#x2F;fileTypeImages&#x2F;icon_rar.gif&quot;&#x2F;&gt;&lt;a style=&quot;font-size:12px; color:#0066cc;&quot; href=&quot;&#x2F;graduation&#x2F;uploadfiles&#x2F;file&#x2F;20150110&#x2F;1420859111998077167.rar&quot; title=&quot;PHP：面向对象、模式与实践（第三版）高清PDF和完整源码.rar&quot;&gt;PHP：面向对象、模式与实践（第三版）高清PDF和完整源码.rar&lt;&#x2F;a&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;', '2015-01-10 11:05:46'),
(13, '1106401023', '064', '贺盼华的毕业答辩申请相关材料', '&lt;p&gt;									&lt;&#x2F;p&gt;&lt;p style=&quot;line-height: 16px;&quot;&gt;&lt;img style=&quot;vertical-align: middle; margin-right: 2px;&quot; src=&quot;&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;dialogs&#x2F;attachment&#x2F;fileTypeImages&#x2F;icon_rar.gif&quot;&#x2F;&gt;&lt;a style=&quot;font-size:12px; color:#0066cc;&quot; href=&quot;&#x2F;graduation&#x2F;uploadfiles&#x2F;file&#x2F;20150110&#x2F;1420859111998077167.rar&quot; title=&quot;PHP：面向对象、模式与实践（第三版）高清PDF和完整源码.rar&quot;&gt;PHP：面向对象、模式与实践（第三版）高清PDF和完整源码.rar&lt;&#x2F;a&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;						\r\n								&lt;&#x2F;p&gt;', '2015-01-11 16:44:37'),
(14, '1106401001', '064', '张海兰的毕业答辩申请相关材料', '', '2015-01-12 09:29:22');

-- --------------------------------------------------------

--
-- 表的结构 `topicorderreview`
--

DROP TABLE IF EXISTS `topicorderreview`;
CREATE TABLE IF NOT EXISTS `topicorderreview` (
`id` int(12) NOT NULL COMMENT 'id自增',
  `name` varchar(255) DEFAULT NULL COMMENT '标题|信息标题',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `judge_tea` text NOT NULL COMMENT '课题评审教师',
  `judge_year` varchar(255) NOT NULL COMMENT '课题评审年限',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='课题评审教师安排';

--
-- 转存表中的数据 `topicorderreview`
--

INSERT INTO `topicorderreview` (`id`, `name`, `department_id`, `judge_tea`, `judge_year`, `create_time`) VALUES
(5, '怀化学院', '064', '06001,06002,06003,06004,06005,06006,06007,06008,06009,06010,06011,06012,06013,06014,06015,06016,06017,06018,06019,06020,06021,06022,06023,06024,06025,06026,06027,06028,06029,06033,06034,06035,06036,06037,06038', '2014', '2014-12-31 20:43:00'),
(6, '2015届课题评审安排', '064', '06001,06002,06003,06004,06005,06006,06007,06008,06009,06010,06011,06012,06013,06014,06015,06016,06017,06018,06019,06020,06021,06022,06023,06024,06025,06026,06027,06028,06029,06033,06034,06035,06036,06037,06038', '2015', '2015-01-09 17:29:55');

-- --------------------------------------------------------

--
-- 表的结构 `topicreport`
--

DROP TABLE IF EXISTS `topicreport`;
CREATE TABLE IF NOT EXISTS `topicreport` (
`id` int(11) NOT NULL COMMENT 'ID|只能是数字',
  `stu_id` varchar(255) DEFAULT NULL COMMENT '学号',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `meaning` text COMMENT '意义、研究动向和见解',
  `content` text COMMENT '课题内容',
  `research` text COMMENT '设计方案、研究方法',
  `deadline` text COMMENT '完成期限和预期进度',
  `referencesl` text COMMENT '参考资料',
  `teacher_view` text COMMENT '指导老师意见',
  `meeting` varchar(255) DEFAULT NULL COMMENT '开题报告会纪要',
  `judge_view` text COMMENT '答辩组意见',
  `department_view` text COMMENT '系部意见',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09'
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='开题报告书';

--
-- 转存表中的数据 `topicreport`
--

INSERT INTO `topicreport` (`id`, `stu_id`, `department_id`, `meaning`, `content`, `research`, `deadline`, `referencesl`, `teacher_view`, `meeting`, `judge_view`, `department_view`, `create_time`) VALUES
(7, '1106401023', '064', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;&#x2F;span&gt;&lt;&#x2F;span&gt;&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;&#x2F;span&gt;&lt;&#x2F;span&gt;&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;&#x2F;span&gt;&lt;&#x2F;span&gt;&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;&#x2F;span&gt;&lt;&#x2F;span&gt;&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;&#x2F;span&gt;&lt;&#x2F;span&gt;&lt;&#x2F;span&gt;&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '<p>																	</p><p><span style="color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);">同意开题<span style="color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);"><span style="color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);"><span style="color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 20px; font-weight: bold; line-height: 18px; background-color: rgb(245, 245, 245);"></span></span></span></span></p><p>	\r\n																</p>', NULL, NULL, NULL, '2015-01-09 21:51:33'),
(8, '1106401023', '064', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 13px; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 13px; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 13px; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 13px; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 13px; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;股票软件&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '', '6', '目标明确，同意开题', NULL, '2015-01-10 13:07:54'),
(9, '1106401001', '064', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 13px; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;湘西旅游导游----android 应用开发&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 13px; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;湘西旅游导游----android 应用开发&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 13px; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;湘西旅游导游----android 应用开发&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: &amp;#39;Helvetica Neue&amp;#39;, Helvetica, Arial, sans-serif; font-size: 13px; line-height: 18px; background-color: rgb(245, 245, 245);&quot;&gt;湘西旅游导游----android 应用开发&lt;&#x2F;span&gt;&lt;&#x2F;p&gt;', '', NULL, NULL, NULL, '2015-01-12 09:27:44');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
`userid` int(11) NOT NULL COMMENT 'id编号',
  `username` varchar(255) NOT NULL COMMENT '用户账号',
  `password` varchar(255) DEFAULT NULL COMMENT '登陆密码',
  `permissions` int(11) DEFAULT NULL COMMENT '用户类型'
) ENGINE=MyISAM AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COMMENT='用户表';

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`userid`, `username`, `password`, `permissions`) VALUES
(35, 'admin', '21232F297A57A5A743894A0E4A801FC3', 4),
(36, '06001', '63A6330825EA313D633F232656950817', 3),
(37, '1206403011', '67D04B1C7D849B09E452F6C1D28D8C72', 1),
(38, '1106401042', 'F9A6736C8EC591A6B1632A8D31FD8B29', 1),
(39, '06014', '7B314E7FE1F4CD580FF69B5A4054B3E4', 2),
(40, '1206403014', 'E718BD27BFF091F1148A110DBE19B876', 1),
(41, '03037', '41DA1B1DD459747AC5EDEBC216278121', 2),
(42, '06033', '6398796F364CE2E7005F31BBD08BA210', 2),
(43, '06012', 'FA126A9987EFB7A0CAE973127EAD801F', 2),
(44, '1106401001', '35D1C06469FB5E7A9F4E7312FFF1A4BE', 1),
(45, '06002', 'A0B32E3DEFF10B975FAB42DC5B5C7702', 2),
(46, '06003', '6AD12FA5CB42F30CF25EECE923E5AC56', 2),
(47, '06004', 'B2CC53A7C97A20228DC9ABCF0165CBAA', 2),
(48, '06005', 'DE49432EF8A45A3EF88FA9F1F847EF8F', 2),
(49, '06006', 'B92F1E2AFC2AEA2AD029AA05F7D2389E', 2),
(50, '06007', 'F205A52005F9A51DD847D0E0E960DD93', 2),
(51, '06009', 'FCEAAA2A329C2B7D1AC74CB1560A987E', 2),
(52, '06010', '5EFBBBA79EA78D7F2E829DC3ABDA0669', 2),
(53, '06011', '63C9B9988D637D34A8AA9F62B50F9404', 2),
(54, '1106401002', '8A4013A2506B464D90864FA7DA0B592D', 1),
(55, '1106401023', 'C3CBE9150EF238FFF870D7F9BB07FDFF', 1),
(56, '1106401021', '6735F9F16C485E77FA72972FE311D412', 1),
(57, '1106401030', '66F9FCB273178E90894A7F6C9285AA20', 1),
(58, '1106401031', 'B8843EE082F8A989BD1D49A8A958205E', 1),
(59, '1106401005', 'D62A2699FB97218390132923526CDAB8', 1),
(60, '1106401007', '5A48D6F75F03BE8515ED324EC8763D6F', 1),
(61, '1106401040', '3B83F59C575FA4D2AA2B5F269739CE91', 1),
(62, '1106401032', '2E35F9D4B0D1BBBB5E1587AA8509B862', 1),
(63, '1106401008', 'DBD3A82BB42D002BAA5143FC339E19B7', 1),
(64, '1106401010', 'F975BDD6D735BD167A3E9EB7E71FD5AF', 1),
(65, '06033', '6398796F364CE2E7005F31BBD08BA210', 3),
(66, '06017', '12E722941EC90460F0F4140E6D688475', 2),
(67, '06013', 'F4BC2D08D2A155EC41B7CD19A8681210', 2),
(68, '06015', '3053F5B72C1D5D296948743FC8E25459', 2),
(69, '1106401012', 'C39A617B0E3D4D90BEAE82D1C84113C2', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `apply`
--
ALTER TABLE `apply`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
 ADD PRIMARY KEY (`dept_ID`);

--
-- Indexes for table `deptmanager`
--
ALTER TABLE `deptmanager`
 ADD PRIMARY KEY (`dm_ID`);

--
-- Indexes for table `gradeall`
--
ALTER TABLE `gradeall`
 ADD PRIMARY KEY (`gA_ID`);

--
-- Indexes for table `gradeone`
--
ALTER TABLE `gradeone`
 ADD PRIMARY KEY (`go_ID`);

--
-- Indexes for table `gradethree`
--
ALTER TABLE `gradethree`
 ADD PRIMARY KEY (`gtr_ID`);

--
-- Indexes for table `gradetwo`
--
ALTER TABLE `gradetwo`
 ADD PRIMARY KEY (`gt_ID`);

--
-- Indexes for table `graduateinfo`
--
ALTER TABLE `graduateinfo`
 ADD PRIMARY KEY (`gdi_ID`);

--
-- Indexes for table `linkeddata_apply_gradeall`
--
ALTER TABLE `linkeddata_apply_gradeall`
 ADD PRIMARY KEY (`id`), ADD KEY `rel_id` (`rel_id`), ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `linkeddata_apply_gradethree`
--
ALTER TABLE `linkeddata_apply_gradethree`
 ADD PRIMARY KEY (`id`), ADD KEY `rel_id` (`rel_id`), ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `linkeddata_apply_graduateinfo`
--
ALTER TABLE `linkeddata_apply_graduateinfo`
 ADD PRIMARY KEY (`id`), ADD KEY `rel_id` (`rel_id`), ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `linkeddata_apply_topicapply`
--
ALTER TABLE `linkeddata_apply_topicapply`
 ADD PRIMARY KEY (`id`), ADD KEY `rel_id` (`rel_id`), ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `linkeddata_apply_topicfinish`
--
ALTER TABLE `linkeddata_apply_topicfinish`
 ADD PRIMARY KEY (`id`), ADD KEY `rel_id` (`rel_id`), ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `linkeddata_apply_topicinfo`
--
ALTER TABLE `linkeddata_apply_topicinfo`
 ADD PRIMARY KEY (`id`), ADD KEY `rel_id` (`rel_id`), ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `linkeddata_apply_topicreport`
--
ALTER TABLE `linkeddata_apply_topicreport`
 ADD PRIMARY KEY (`id`), ADD KEY `rel_id` (`rel_id`), ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `linkeddata_apply_topicscore`
--
ALTER TABLE `linkeddata_apply_topicscore`
 ADD PRIMARY KEY (`id`), ADD KEY `rel_id` (`rel_id`), ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `linkeddata_meeting_graduateinfo`
--
ALTER TABLE `linkeddata_meeting_graduateinfo`
 ADD PRIMARY KEY (`id`), ADD KEY `rel_id` (`rel_id`), ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `meeting`
--
ALTER TABLE `meeting`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `opentopicinfo`
--
ALTER TABLE `opentopicinfo`
 ADD PRIMARY KEY (`opi_ID`);

--
-- Indexes for table `opentopicscore`
--
ALTER TABLE `opentopicscore`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `profession`
--
ALTER TABLE `profession`
 ADD PRIMARY KEY (`pro_ID`), ADD KEY `FKB7DCE0FCBBE179F1` (`dept_ID`);

--
-- Indexes for table `revieworder`
--
ALTER TABLE `revieworder`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reviewresult`
--
ALTER TABLE `reviewresult`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `selectfirst`
--
ALTER TABLE `selectfirst`
 ADD PRIMARY KEY (`sel_ID`), ADD KEY `FKD355B34BE9E36BC` (`top_ID`);

--
-- Indexes for table `settime`
--
ALTER TABLE `settime`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
 ADD PRIMARY KEY (`stu_ID`), ADD KEY `FK8FFE823BA0799862` (`cla_ID`);

--
-- Indexes for table `stu_tea_topic`
--
ALTER TABLE `stu_tea_topic`
 ADD PRIMARY KEY (`stt_ID`), ADD UNIQUE KEY `stt_ID` (`stt_ID`);

--
-- Indexes for table `taskdoc`
--
ALTER TABLE `taskdoc`
 ADD PRIMARY KEY (`tD_ID`);

--
-- Indexes for table `tballdoc`
--
ALTER TABLE `tballdoc`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbclass`
--
ALTER TABLE `tbclass`
 ADD PRIMARY KEY (`cla_ID`), ADD KEY `FKA5336FCAAA202A33` (`pro_ID`);

--
-- Indexes for table `tbdocnum`
--
ALTER TABLE `tbdocnum`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbgrade`
--
ALTER TABLE `tbgrade`
 ADD PRIMARY KEY (`gra_ID`);

--
-- Indexes for table `tboffice`
--
ALTER TABLE `tboffice`
 ADD PRIMARY KEY (`off_ID`);

--
-- Indexes for table `tbtopic`
--
ALTER TABLE `tbtopic`
 ADD PRIMARY KEY (`top_ID`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
 ADD PRIMARY KEY (`tea_ID`);

--
-- Indexes for table `topicapply`
--
ALTER TABLE `topicapply`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `topicfinish`
--
ALTER TABLE `topicfinish`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `topicorderreview`
--
ALTER TABLE `topicorderreview`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `topicreport`
--
ALTER TABLE `topicreport`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `apply`
--
ALTER TABLE `apply`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增',AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT for table `gradeall`
--
ALTER TABLE `gradeall`
MODIFY `gA_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '综合成绩编号',AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `gradeone`
--
ALTER TABLE `gradeone`
MODIFY `go_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '表一编号',AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `gradethree`
--
ALTER TABLE `gradethree`
MODIFY `gtr_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '表三编号',AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `gradetwo`
--
ALTER TABLE `gradetwo`
MODIFY `gt_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '表二编号',AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `graduateinfo`
--
ALTER TABLE `graduateinfo`
MODIFY `gdi_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '答辩ID',AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `linkeddata_apply_gradeall`
--
ALTER TABLE `linkeddata_apply_gradeall`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID|只能是数字',AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `linkeddata_apply_gradethree`
--
ALTER TABLE `linkeddata_apply_gradethree`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID|只能是数字',AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `linkeddata_apply_graduateinfo`
--
ALTER TABLE `linkeddata_apply_graduateinfo`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID|只能是数字',AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `linkeddata_apply_topicapply`
--
ALTER TABLE `linkeddata_apply_topicapply`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID|只能是数字',AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `linkeddata_apply_topicfinish`
--
ALTER TABLE `linkeddata_apply_topicfinish`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID|只能是数字',AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `linkeddata_apply_topicinfo`
--
ALTER TABLE `linkeddata_apply_topicinfo`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID|只能是数字',AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `linkeddata_apply_topicreport`
--
ALTER TABLE `linkeddata_apply_topicreport`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID|只能是数字',AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `linkeddata_apply_topicscore`
--
ALTER TABLE `linkeddata_apply_topicscore`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID|只能是数字',AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `linkeddata_meeting_graduateinfo`
--
ALTER TABLE `linkeddata_meeting_graduateinfo`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID|只能是数字',AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `meeting`
--
ALTER TABLE `meeting`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增',AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号|系统自动生成|show|hidden',AUTO_INCREMENT=86;
--
-- AUTO_INCREMENT for table `opentopicinfo`
--
ALTER TABLE `opentopicinfo`
MODIFY `opi_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '开题编号',AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `opentopicscore`
--
ALTER TABLE `opentopicscore`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增',AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `revieworder`
--
ALTER TABLE `revieworder`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增',AUTO_INCREMENT=190;
--
-- AUTO_INCREMENT for table `reviewresult`
--
ALTER TABLE `reviewresult`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号|系统自动生成',AUTO_INCREMENT=136;
--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增',AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `selectfirst`
--
ALTER TABLE `selectfirst`
MODIFY `sel_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '标号',AUTO_INCREMENT=42;
--
-- AUTO_INCREMENT for table `settime`
--
ALTER TABLE `settime`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统自动生成',AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `stu_tea_topic`
--
ALTER TABLE `stu_tea_topic`
MODIFY `stt_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `taskdoc`
--
ALTER TABLE `taskdoc`
MODIFY `tD_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务书编号',AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `tballdoc`
--
ALTER TABLE `tballdoc`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id索引',AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `tbdocnum`
--
ALTER TABLE `tbdocnum`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id索引',AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `tbgrade`
--
ALTER TABLE `tbgrade`
MODIFY `gra_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '年级编号',AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `topicapply`
--
ALTER TABLE `topicapply`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增',AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `topicfinish`
--
ALTER TABLE `topicfinish`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增',AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `topicorderreview`
--
ALTER TABLE `topicorderreview`
MODIFY `id` int(12) NOT NULL AUTO_INCREMENT COMMENT 'id自增',AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `topicreport`
--
ALTER TABLE `topicreport`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID|只能是数字',AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id编号',AUTO_INCREMENT=70;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
