-- phpMyAdmin SQL Dump
-- version 2.10.2
-- http://www.phpmyadmin.net
-- 
-- 主机: localhost
-- 生成日期: 2014 年 08 月 10 日 01:51
-- 服务器版本: 5.0.67
-- PHP 版本: 5.2.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- 数据库: `graduation`
-- 

-- --------------------------------------------------------

-- 
-- 表的结构 `selectfirst`
-- 

CREATE TABLE `selectfirst` (
  `sel_ID` int(11) NOT NULL auto_increment COMMENT '标号',
  `top_ID` varchar(255) NOT NULL COMMENT '课题ID',
  `sT_ID` int(11) default NULL COMMENT '子课题ID',
  `stu_ID` varchar(12) NOT NULL COMMENT '选择人学号',
  `sel_knowing` text NOT NULL COMMENT '对课题的认识',
  `sel_status` varchar(10) NOT NULL COMMENT '状态--默认0未被选择，1确定为任务人',
  PRIMARY KEY  (`sel_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='课题的初步选择记录表' AUTO_INCREMENT=7 ;

-- 
-- 导出表中的数据 `selectfirst`
-- 

INSERT INTO `selectfirst` VALUES (1, '20140807164146', NULL, '1106401001', '是哥伦比亚作家加西亚·马尔克斯的代表作，也是拉丁美洲魔幻现实主义文学的代表作，被誉为“再现拉丁美洲历史社会图景的鸿篇巨著”。作品描写了布恩迪亚家族七代人的传奇故事', '0');
INSERT INTO `selectfirst` VALUES (2, '20140807164146', NULL, '1106401002', '作品融入神话传说、民间故事、宗教典故等神秘因素，巧妙地糅合了现实与虚幻，展现出一个瑰丽的想象世界，成为20世纪最重要的经典文学巨著之一', '0');
INSERT INTO `selectfirst` VALUES (3, '20140807164146', NULL, '1106401023', '新中国三十年改革开放而重新崛起的历程。《百年孤独》的作者加西亚·马尔克斯在完成巨著后曾有一个夙愿：希望一百年孤独的历史永远消失并再也不会出现。但愿，作者的苦心和夙愿梦想成真。', '0');
INSERT INTO `selectfirst` VALUES (4, '20140807164146', NULL, '1106401042', '不管走到哪，都要永远记住：过去是虚假的，往事时不能返回的，每一个消逝的春天都一去不复返了。最狂热、最坚贞的爱情也只是过眼云烟', '0');
INSERT INTO `selectfirst` VALUES (5, '20140807105535', 7, '1106401001', '遭受百年孤独的家庭，注定不会在大地上第二次出现了”我的理解与书中的简介不同，书前面的简介说这句话代表的是积极，黑暗总会过去，光明即将来临。', '0');
INSERT INTO `selectfirst` VALUES (6, '20140807105535', 8, '1106401002', '奥雷连诺上校采取的方式是战争，永无休止的战争来排遣孤独，但战争只让他更加寂寞。梅梅用的是爱情，可惜他的爱情不被母亲允许，寂寞也就依然如影相随。', '0');
