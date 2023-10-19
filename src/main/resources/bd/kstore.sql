/*
 Navicat Premium Data Transfer

 Source Server         :127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           :
 Source Schema         : mtstore

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 12/10/2023 17:43:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for kz_agent
-- ----------------------------
DROP TABLE IF EXISTS `kz_agent`;
CREATE TABLE `kz_agent`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `agent_name` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '代理商名称',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级代理商',
  `level` int(180) NULL DEFAULT NULL COMMENT '代理级别',
  `contact_phone` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `contact_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区域',
  `expired_time` datetime(0) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `status_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `un_phone`(`contact_phone`) USING BTREE COMMENT '手机号唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代理商表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_agent
-- ----------------------------
INSERT INTO `kz_agent` VALUES (1, '惠速达配送', NULL, NULL, '12345678901', NULL, NULL, NULL, NULL, NULL, 694, '2022-02-23 11:51:12', '2022-02-23 11:51:12', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_article
-- ----------------------------
DROP TABLE IF EXISTS `kz_article`;
CREATE TABLE `kz_article`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NULL DEFAULT NULL COMMENT '分类id',
  `title` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面图',
  `img_urls` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '轮播图',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '摘要',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文章详情',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `attachments` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `status_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `visited` int(11) NULL DEFAULT NULL COMMENT '点击次数',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章资讯表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_article
-- ----------------------------
INSERT INTO `kz_article` VALUES (33, 19, '蔬菜价格全面下调，物价局果断出手稳定市场1', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351704244.png', '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352148034.png\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351704244.png\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351634059.png\"]', '蔬菜价格全面下调', '<p>是法萨芬水电费是大法官是多少</p>', '管理员', NULL, NULL, NULL, 98, 694, '2022-02-23 11:51:12', '2023-04-27 11:47:56', 1, 0, 0);
INSERT INTO `kz_article` VALUES (34, 19, '蔬菜价格全面下调，物价局果断出手稳定市场2', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351704244.png', '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352148034.png\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351704244.png\"]', '蔬菜价格全面下调', '<p>是法萨芬水电费是大法官是多少</p>', '管理员', NULL, NULL, NULL, 87, 694, '2022-02-23 11:51:12', '2023-04-27 11:47:56', 1, 0, 0);
INSERT INTO `kz_article` VALUES (35, 19, '蔬菜价格全面下调，物价局果断出手稳定市场3', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351704244.png', '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352148034.png\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351704244.png\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351634059.png\"]', '蔬菜价格全面下调', '<p>是法萨芬水电费是大法官是多少</p>', '管理员', NULL, NULL, NULL, 86, 694, '2022-02-23 11:51:12', '2023-04-27 11:47:56', 1, 0, 0);
INSERT INTO `kz_article` VALUES (46, 19, '蔬菜价格全面下调，物价局果断出手稳定市场xxxx', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351704244.png', '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352148034.png\"]', '蔬菜价格全面下调', '<p>是法萨芬水电费是大法官是多少</p>', '管理员', NULL, NULL, NULL, 87, 694, '2022-02-23 11:51:12', '2023-04-27 11:47:56', 1, 0, 0);
INSERT INTO `kz_article` VALUES (48, 1, '618期间全场免运费，快来看看吧~', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685513530950.png', '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433494355.png\"]', '618期间全场免运费，快来看看吧~', '<p>618期间全场免运费，快来看看吧~618期间全场免运费，快来看看吧~618期间全场免运费，快来看看吧~</p><p>618期间全场免运费，快来看看吧~618期间全场免运费，快来看看吧~618期间全场免运费，快来看看吧~</p><p>618期间全场免运费，快来看看吧~618期间全场免运费，快来看看吧~618期间全场免运费，快来看看吧~</p><p>618期间全场免运费，快来看看吧~618期间全场免运费，快来看看吧~618期间全场免运费，快来看看吧~</p><p>618期间全场免运费，快来看看吧~618期间全场免运费，快来看看吧~618期间全场免运费，快来看看吧~</p><p>618期间全场免运费，快来看看吧~618期间全场免运费，快来看看吧~618期间全场免运费，快来看看吧~</p><p>618期间全场免运费，快来看看吧~618期间全场免运费，快来看看吧~618期间全场免运费，快来看看吧~</p><p>618期间全场免运费，快来看看吧~618期间全场免运费，快来看看吧~618期间全场免运费，快来看看吧~</p>', '管理员', NULL, NULL, NULL, 86, 1, '2023-06-08 14:19:36', '2023-06-08 14:19:56', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_article_category
-- ----------------------------
DROP TABLE IF EXISTS `kz_article_category`;
CREATE TABLE `kz_article_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父级ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'banner标题',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  `url_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转链接',
  `sort` int(255) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_article_category
-- ----------------------------
INSERT INTO `kz_article_category` VALUES (1, 0, '公告', NULL, NULL, NULL, NULL, NULL, '2023-06-08 14:18:14', '2023-06-08 14:18:14', 1, 1, 0, 0);
INSERT INTO `kz_article_category` VALUES (19, 0, '热点新闻', NULL, NULL, NULL, NULL, 1, '2023-04-27 10:33:20', '2023-04-27 10:33:20', 1, 1, 0, 0);
INSERT INTO `kz_article_category` VALUES (20, 0, '专业知识', NULL, NULL, NULL, NULL, 2, '2023-04-27 10:33:33', '2023-04-27 10:33:33', 1, 1, 0, 0);
INSERT INTO `kz_article_category` VALUES (21, 0, '专业知识1', NULL, NULL, NULL, NULL, 2, '2023-04-27 10:33:33', '2023-04-27 10:33:33', 1, 1, 0, 0);
INSERT INTO `kz_article_category` VALUES (22, 0, '专业知识2', NULL, NULL, NULL, NULL, 2, '2023-04-27 10:33:33', '2023-04-27 10:33:33', 1, 1, 0, 0);

-- ----------------------------
-- Table structure for kz_category
-- ----------------------------
DROP TABLE IF EXISTS `kz_category`;
CREATE TABLE `kz_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父级ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'banner标题',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  `url_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转链接',
  `sort` int(255) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_category
-- ----------------------------
INSERT INTO `kz_category` VALUES (1, 0, '新鲜蔬菜', NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351634059.png', NULL, 'pages/goods/goodsList?categoryId=1', 99, '2021-11-22 16:30:32', '2023-05-19 14:04:38', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (2, 0, '米面粮油', NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351704244.png', NULL, '/pages/goods/goodsList?categoryId=2', 99, '2023-03-29 15:38:38', '2023-05-05 18:11:49', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (3, 0, '调料酒水', NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163656.png', NULL, '/pages/goods/goodsList?categoryId=3', 98, '2023-04-13 10:08:50', '2023-06-25 10:50:55', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (4, 0, '肉类冻品', NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163666.png', NULL, '/pages/goods/goodsList?categoryId=4', 97, '2023-04-13 10:09:05', '2023-06-25 10:52:06', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (5, 0, '豆制品', NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163670.png', NULL, '/pages/goods/goodsList?categoryId=5', 96, '2023-04-13 10:09:18', '2023-06-25 10:51:54', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (6, 0, '新鲜水果', NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1686534447802.png', NULL, NULL, 99, '2023-05-16 16:48:38', '2023-06-25 10:51:18', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (7, 0, '南北干货', NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163660.png', NULL, NULL, 98, '2023-05-16 17:12:09', '2023-06-25 10:51:34', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (11, 1, '新鲜叶菜', NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352148044.png', NULL, 'pages/goods/goodsList?categoryId=11', 99, '2023-03-29 15:58:17', '2023-05-23 09:36:55', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (12, 1, '茎菜', NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683619665956.png', NULL, NULL, 2, '2023-06-08 17:45:53', '2023-06-08 17:45:53', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (13, 1, '根茎', NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682582997379.png', NULL, NULL, 3, '2023-06-08 17:46:07', '2023-06-08 17:46:07', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (14, 1, '豆荚', NULL, NULL, NULL, NULL, NULL, '2023-06-08 17:46:19', '2023-06-08 17:46:19', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (15, 1, '茄果', NULL, NULL, NULL, NULL, NULL, '2023-06-08 17:46:29', '2023-06-08 17:46:29', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (16, 1, '甘蓝', NULL, NULL, NULL, NULL, NULL, '2023-06-08 17:46:38', '2023-06-08 17:46:38', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (17, 1, '葱蒜', NULL, NULL, NULL, NULL, NULL, '2023-06-08 17:46:47', '2023-06-08 17:46:47', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (18, 1, '瓜菜', NULL, NULL, NULL, NULL, NULL, '2023-06-08 17:46:55', '2023-06-08 17:46:55', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (19, 1, '食用菌', NULL, NULL, NULL, NULL, NULL, '2023-06-08 17:47:02', '2023-06-08 17:47:02', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (111, 11, '青菜', NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683619665956.png', NULL, NULL, 1, '2023-06-08 17:42:22', '2023-06-08 17:42:22', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (112, 11, '白菜', NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683619494565.png', NULL, NULL, 2, '2023-06-08 17:42:38', '2023-06-08 17:42:38', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (21, 2, '大米', NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683618692507.png', NULL, NULL, 1, '2023-06-08 17:43:44', '2023-06-08 17:43:44', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (22, 2, '菜籽油', NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683618664655.png', NULL, NULL, 1, '2023-06-08 17:44:00', '2023-06-08 17:44:00', 1, 1, 0, 0);
INSERT INTO `kz_category` VALUES (211, 21, '泰国香米', NULL, NULL, NULL, '11', 3, '2023-06-09 17:54:23', '2023-06-09 17:54:23', 1, 1, 0, 0);

-- ----------------------------
-- Table structure for kz_comment
-- ----------------------------
DROP TABLE IF EXISTS `kz_comment`;
CREATE TABLE `kz_comment`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  `is_delete` tinyint(2) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for kz_diy_nav
-- ----------------------------
DROP TABLE IF EXISTS `kz_diy_nav`;
CREATE TABLE `kz_diy_nav`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标地址',
  `selected_icon_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选中图标地址',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导航标题',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `page_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转链接',
  `sort` int(255) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '导航栏DIY表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_diy_nav
-- ----------------------------
INSERT INTO `kz_diy_nav` VALUES (105, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684223515302.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804811.png', '首页', NULL, 'pages/index/index', NULL, '2023-06-26 19:27:52', '2023-06-26 19:27:52', 1, 1, 0, 0);
INSERT INTO `kz_diy_nav` VALUES (106, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684223739092.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804803.png', '分类', NULL, 'pages/tabs/classify', NULL, '2023-06-26 19:27:52', '2023-06-26 19:27:52', 1, 1, 0, 0);
INSERT INTO `kz_diy_nav` VALUES (107, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684223739094.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804813.png', '发现', NULL, 'pages/service/find', NULL, '2023-06-26 19:27:52', '2023-06-26 19:27:52', 1, 1, 0, 0);
INSERT INTO `kz_diy_nav` VALUES (108, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684223739090.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804807.png', '购物车', NULL, 'pages/tabs/cart', NULL, '2023-06-26 19:27:52', '2023-06-26 19:27:52', 1, 1, 0, 0);
INSERT INTO `kz_diy_nav` VALUES (109, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684223739095.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804830.png', '我的', NULL, 'pages/tabs/user', NULL, '2023-06-26 19:27:52', '2023-06-26 19:27:52', 1, 1, 0, 0);

-- ----------------------------
-- Table structure for kz_diy_page
-- ----------------------------
DROP TABLE IF EXISTS `kz_diy_page`;
CREATE TABLE `kz_diy_page`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '页面唯一id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面标题',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `page_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面类型，比如首页，分类，自定义，个人中心等等',
  `page_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转链接',
  `page_data` json NULL COMMENT '页面数据',
  `attr_data` json NULL COMMENT '属性，备用',
  `extra_data` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展等，备用',
  `sort` int(255) NULL DEFAULT NULL COMMENT '排序',
  `is_default` tinyint(2) NULL DEFAULT NULL COMMENT '是否默认',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uuid`(`uuid`) USING BTREE COMMENT '页面唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'DIY页面表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_diy_page
-- ----------------------------
INSERT INTO `kz_diy_page` VALUES (17, '250e4e13', '商城首页', NULL, 'home', NULL, '[{\"type\": \"info\", \"title\": \"页面标题\"}, {\"data\": [{\"img\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433665364.png\", \"url\": \"\", \"form\": {}, \"name\": \"1春季尝鲜-生鲜果蔬手机banner(1).png\", \"opentype\": \"click\", \"pagePath\": \"/pages/goods/goodsList?categoryId=9\"}, {\"img\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433665374.png\", \"url\": \"\", \"form\": {}, \"name\": \"春季尝鲜(1).png\", \"opentype\": \"click\", \"pagePath\": \"/pages/goods/goodsList?categoryId=10\"}, {\"img\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433494358.png\", \"url\": \"\", \"form\": {}, \"name\": \"蔬菜.png\", \"opentype\": \"click\", \"pagePath\": \"/pages/goods/goodsList?categoryId=9\"}], \"type\": \"banner\", \"options\": {}}, {\"data\": [\"无用\"], \"type\": \"notice\", \"options\": {\"imgUrl\": \"https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/notice_icon.png\", \"newFlag\": true, \"isHidden\": false, \"paddingB\": 10, \"paddingL\": 10, \"paddingR\": 10, \"paddingT\": 10, \"itemColor\": \"#ffffff\", \"lineColor\": \"#cccccc\", \"textColor\": \"#333333\", \"borderRadius\": 0}}, {\"data\": [{\"title\": \"新鲜蔬菜\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163651.gif\", \"pagePath\": \"/pages/goods/goodsList?categoryId=9\", \"selectIndex\": 9}, {\"title\": \"时令水果\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441375448.png\", \"pagePath\": \"/pages/goods/goodsList?categoryId=19\", \"selectIndex\": 9}, {\"title\": \"海鲜水产\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163663.png\", \"pagePath\": \"/pages/goods/goodsList?categoryId=15\", \"selectIndex\": 9}, {\"title\": \"速食熟食\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163665.png\", \"pagePath\": \"/pages/goods/goodsList?categoryId=16\", \"selectIndex\": 9, \"selectedIconPath\": \"https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/menu_2a.png\"}, {\"title\": \"火锅食材\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163670.png\", \"pagePath\": \"/pages/goods/goodsList?categoryId=15\", \"selectIndex\": 9}, {\"title\": \"粮油调味\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163657.png\", \"pagePath\": \"/pages/goods/goodsList?categoryId=14\", \"selectIndex\": 9}, {\"title\": \"牛奶雪糕\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163659.png\", \"selectIndex\": 9}, {\"title\": \"休闲食品\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163662.png\", \"pagePath\": \"/pages/goods/goodsList?categoryId=20\", \"selectIndex\": 9}, {\"title\": \"冰凉一夏\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163656.png\", \"selectIndex\": 9}, {\"title\": \"日用百货\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163669.png\", \"pagePath\": \"/pages/goods/goodsList?categoryId=20\", \"selectIndex\": 9}], \"type\": \"navigation\", \"options\": {\"style\": \"wrap\", \"width\": \"20%\", \"isHidden\": false}}, {\"data\": [1, 2, 3], \"type\": \"coupon\", \"options\": {\"style\": \"3\", \"title\": \"超值优惠券\", \"bgColor\": \"#ffffff\", \"btnColor\": \"#FF2C3C\", \"isHidden\": false, \"moreText\": \"全部\", \"paddingB\": 10, \"paddingL\": 10, \"paddingR\": 10, \"paddingT\": 10, \"itemColor\": \"#FCE7E7\", \"textColor\": \"#FF2C3C\", \"titleColor\": \"#333333\", \"btnTextColor\": \"#FFFFFF\"}}, {\"data\": [{\"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685503212918.png\", \"pagePath\": \"/pages/goods/goodsList?categoryId=9\"}], \"type\": \"magicCube\", \"options\": {\"type\": 7, \"padding\": 0, \"isHidden\": false, \"paddingB\": 0, \"paddingL\": 16, \"paddingR\": 16, \"paddingT\": 0, \"outPadding\": 16, \"borderRadius\": 1, \"marginBottom\": 20}, \"tabType\": \"7\"}, {\"data\": [1, 2, 3], \"type\": \"secKill\", \"options\": {\"isBtn\": true, \"title\": \"限时秒杀\", \"color1\": \"#FFFFFF\", \"color2\": \"#BCF5F7\", \"isPrice\": true, \"isTitle\": true, \"fontSize\": 14, \"goodsNum\": 3, \"isHidden\": false, \"isZhekou\": true, \"issecKill\": false, \"paddingLR\": 10, \"paddingTB\": 15, \"smallIcon\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684831479361.png\", \"titleColor\": \"#000000\", \"borderRadius\": 10}}, {\"data\": [{\"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433665364.png\", \"pagePath\": \"/pages/goods/goodsList?categoryId=9\"}, {\"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433665374.png\", \"pagePath\": \"/pages/goods/goodsList?categoryId=10\"}, {\"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433494358.png\", \"pagePath\": \"/pages/goods/goodsList?categoryId=14\"}], \"type\": \"magicCube\", \"options\": {\"type\": 3, \"padding\": 0, \"isHidden\": false, \"paddingB\": 0, \"paddingL\": 16, \"paddingR\": 16, \"paddingT\": 0, \"outPadding\": 16, \"borderRadius\": 1, \"marginBottom\": 16}, \"tabType\": \"3\"}, {\"data\": [1, 2, 3], \"type\": \"bargaining\", \"options\": {\"isBtn\": true, \"title\": \"砍价专区Bargaining\", \"color1\": \"#F7D264\", \"color2\": \"#F1F8BF\", \"isPrice\": true, \"fontSize\": 14, \"goodsNum\": 3, \"isHidden\": false, \"paddingLR\": 10, \"paddingTB\": 15, \"titleColor\": \"#ED980F\", \"borderRadius\": 10}}, {\"data\": [1, 2, 3], \"type\": \"coupon\", \"options\": {\"color1\": \"#ff0000\", \"color2\": \"#cccccc\", \"bgColor\": \"#ffffff\", \"isHidden\": false}}, {\"data\": [1, 2, 3], \"type\": \"pintuan\", \"options\": {\"isBtn\": false, \"title\": \"拼团活动\", \"color1\": \"#FFFFFF\", \"color2\": \"#FFFFFF\", \"isPrice\": true, \"isTitle\": true, \"fontSize\": 14, \"goodsNum\": 3, \"isHidden\": false, \"isCantuan\": false, \"isPintuan\": false, \"paddingLR\": 9, \"paddingTB\": 15, \"smallIcon\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684831157320.png\", \"titleColor\": \"#000000\", \"borderRadius\": 10}}]', NULL, NULL, NULL, 1, '2023-04-18 09:12:44', '2023-06-28 18:03:24', 1, 1, 0, 0);
INSERT INTO `kz_diy_page` VALUES (18, '5e3c08be', '个人中心页', NULL, 'userCenter', NULL, '[{\"type\": \"info\", \"title\": \"个人中心\"}, {\"data\": [{\"num\": \"0\", \"title\": \"余额\", \"pagePath\": \"/pages/userPage/balance\"}, {\"num\": \"0\", \"title\": \"积分\", \"pagePath\": \"/pages/userPage/integral\"}, {\"num\": \"0\", \"title\": \"优惠券\", \"pagePath\": \"/pages/userPage/myCoupon\"}, {\"num\": \"0\", \"title\": \"收藏\", \"pagePath\": \"\"}], \"type\": \"vipInfo\", \"options\": {\"bgImg\": \"https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1678960654708.png\", \"bgColor\": \"#42CA4D\", \"showVip\": true, \"numColor\": \"#333\", \"nameColor\": \"#FFFFFF\", \"showColor\": true, \"titleColor\": \"#333\"}}, {\"data\": [{\"title\": \"待付款\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683631713555.png\"}, {\"title\": \"待发货\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683631749707.png\"}, {\"title\": \"待收货\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683631713554.png\"}, {\"title\": \"待评价\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683631713549.png\"}, {\"title\": \"退款/售后\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683631713529.png\"}], \"type\": \"orderBar\", \"options\": {}}, {\"data\": [{\"title\": \"签到\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504317.png\", \"enabled\": true, \"pagePath\": \"/pages/userPage/signIn\"}, {\"title\": \"邀请有礼\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504320.png\", \"enabled\": true, \"pagePath\": \"/pages/promotion/promotion\"}, {\"title\": \"拼团\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504314.png\", \"enabled\": true, \"pagePath\": \"/pages/product/ptRecord\"}, {\"title\": \"砍价\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504308.png\", \"enabled\": true, \"pagePath\": \"/pages/product/bargainingRecord\"}, {\"title\": \"代言\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504305.png\", \"enabled\": true, \"pagePath\": \"/pages/service/find\"}, {\"title\": \"排行榜\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504313.png\", \"enabled\": false}, {\"title\": \"领券中心\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504310.png\", \"enabled\": true, \"pagePath\": \"/pages/order/coupon\"}, {\"title\": \"充值\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504295.png\", \"enabled\": true, \"pagePath\": \"/pages/recharge/recharge\"}, {\"title\": \"品牌甄选\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504316.png\", \"enabled\": false, \"pagePath\": \"/pages/pay/index\"}, {\"title\": \"积分中心\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504307.png\", \"enabled\": true, \"pagePath\": \"/pages/userPage/integral\"}, {\"title\": \"短视频\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504311.png\", \"enabled\": false, \"pagePath\": \"/pages/userPage/shortVideo\"}, {\"title\": \"商家入驻\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504318.png\", \"enabled\": false}], \"type\": \"service\", \"options\": {\"title\": \"常用工具\", \"width\": 36, \"bgColor\": \"#fff\", \"fontSize\": 14, \"direction\": true, \"paddingLR\": 10, \"paddingTB\": 15, \"borderRadius\": 10}}, {\"data\": [{\"title\": \"核销\", \"imgUrl\": \"https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687946826832.png\", \"enabled\": true, \"pagePath\": \"/pages/pay/writeOff\"}, {\"title\": \"我的地址\", \"imgUrl\": \"https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687946826845.png\", \"enabled\": true, \"pagePath\": \"/pages/service/address\"}, {\"title\": \"充值中心\", \"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683619665956.png\", \"enabled\": false, \"pagePath\": \"/pages/recharge/recharge\"}, {\"title\": \"设置\", \"imgUrl\": \"https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687946826844.png\", \"enabled\": true, \"pagePath\": \"/pages/userPage/setting\"}], \"type\": \"service\", \"options\": {\"title\": \"服务功能\", \"width\": 24, \"bgColor\": \"#fff\", \"fontSize\": 14, \"direction\": true, \"paddingLR\": 10, \"paddingTB\": 15, \"borderRadius\": 10}}]', NULL, NULL, NULL, 1, '2023-05-09 15:05:29', '2023-06-28 18:08:20', 1, 1, 0, 0);
INSERT INTO `kz_diy_page` VALUES (19, 'dcygxx2t', '商城首页-复制', NULL, 'home', NULL, '[{\"type\": \"info\", \"title\": \"页面标题\"}, {\"data\": [{\"img\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433665364.png\", \"url\": \"\", \"form\": {}, \"name\": \"1春季尝鲜-生鲜果蔬手机banner(1).png\", \"opentype\": \"click\"}, {\"img\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433665374.png\", \"url\": \"\", \"form\": {}, \"name\": \"春季尝鲜(1).png\", \"opentype\": \"click\"}, {\"img\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433494358.png\", \"url\": \"\", \"form\": {}, \"name\": \"蔬菜.png\", \"opentype\": \"click\"}], \"type\": \"banner\", \"options\": {\"height\": 218, \"showSus\": true, \"susList\": [{\"img\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683619876880.png\", \"text\": \"下单免排队\", \"title\": \"门店自取\", \"pagePath\": \"\"}, {\"img\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683619866824.png\", \"text\": \"无接触配送\", \"title\": \"外卖\", \"pagePath\": \"\"}]}}, {\"data\": [\"无用\"], \"type\": \"notice\", \"options\": {\"imgUrl\": \"https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/notice_icon.png\", \"newFlag\": true, \"isHidden\": false, \"paddingB\": 10, \"paddingL\": 10, \"paddingR\": 10, \"paddingT\": 10, \"itemColor\": \"#ffffff\", \"lineColor\": \"#cccccc\", \"textColor\": \"#333333\", \"borderRadius\": 0}}, {\"data\": [{\"title\": \"新鲜蔬菜\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163651.gif\", \"pagePath\": \"/pages/goods/goodsList?categoryId=9\", \"selectIndex\": 0}, {\"title\": \"时令水果\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441375448.png\", \"selectIndex\": 0}, {\"title\": \"海鲜水产\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163663.png\", \"selectIndex\": 0}, {\"title\": \"速食熟食\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163665.png\", \"selectIndex\": 0, \"selectedIconPath\": \"https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/menu_2a.png\"}, {\"title\": \"火锅食材\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163670.png\", \"selectIndex\": 0}, {\"title\": \"粮油调味\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163657.png\", \"selectIndex\": 0}, {\"title\": \"牛奶雪糕\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163659.png\", \"selectIndex\": 0}, {\"title\": \"休闲食品\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163662.png\", \"selectIndex\": 0}, {\"title\": \"冰凉一夏\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163656.png\", \"selectIndex\": 0}, {\"title\": \"日用百货\", \"enabled\": true, \"iconPath\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163669.png\", \"selectIndex\": 0}], \"type\": \"navigation\", \"options\": {\"style\": \"wrap\", \"width\": \"20%\", \"isHidden\": false}}, {\"data\": [1, 2, 3], \"type\": \"coupon\", \"options\": {\"style\": \"3\", \"title\": \"超值优惠券\", \"bgColor\": \"#ffffff\", \"btnColor\": \"#FF2C3C\", \"isHidden\": false, \"moreText\": \"全部\", \"paddingB\": 10, \"paddingL\": 10, \"paddingR\": 10, \"paddingT\": 10, \"itemColor\": \"#FCE7E7\", \"textColor\": \"#FF2C3C\", \"titleColor\": \"#333333\", \"btnTextColor\": \"#FFFFFF\"}}, {\"data\": [{\"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685503212918.png\"}], \"type\": \"magicCube\", \"options\": {\"type\": 7, \"padding\": 0, \"isHidden\": false, \"paddingB\": 0, \"paddingL\": 16, \"paddingR\": 16, \"paddingT\": 0, \"outPadding\": 16, \"borderRadius\": 1, \"marginBottom\": 20}, \"tabType\": \"7\"}, {\"data\": [1, 2, 3], \"type\": \"secKill\", \"options\": {\"isBtn\": true, \"title\": \"限时秒杀\", \"color1\": \"#FFFFFF\", \"color2\": \"#BCF5F7\", \"isPrice\": true, \"isTitle\": true, \"fontSize\": 14, \"goodsNum\": 3, \"isHidden\": false, \"isZhekou\": true, \"issecKill\": false, \"paddingLR\": 10, \"paddingTB\": 15, \"smallIcon\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684831479361.png\", \"titleColor\": \"#000000\", \"borderRadius\": 10}}, {\"data\": [{\"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433665364.png\"}, {\"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433665374.png\"}, {\"imgUrl\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433494358.png\"}], \"type\": \"magicCube\", \"options\": {\"type\": 3, \"padding\": 0, \"isHidden\": false, \"paddingB\": 0, \"paddingL\": 16, \"paddingR\": 16, \"paddingT\": 0, \"outPadding\": 16, \"borderRadius\": 1, \"marginBottom\": 16}, \"tabType\": \"3\"}, {\"data\": [\"无用\"], \"type\": \"title\", \"options\": {\"style\": \"1\", \"color1\": \"#000000\", \"color2\": \"#999999\", \"color3\": \"#999999\", \"bgColor\": \"#ffffff\", \"bigTitle\": \"今日特惠\", \"isHidden\": false, \"moreText\": \"更多\", \"paddingB\": 10, \"paddingL\": 10, \"paddingR\": 10, \"paddingT\": 10, \"smallTitle\": \"\", \"bigFontSize\": 14, \"secIsHidden\": false, \"moreFontSize\": 14, \"smallFontSize\": 12}}, {\"data\": [{\"id\": 67, \"sort\": 0, \"tags\": [\"好的\", \"大家都觉得\", \"就打架大家\"], \"attrs\": null, \"price\": 10.5, \"sales\": 68, \"imgUrl\": \"https://gw.alicdn.com/imgextra/O1CN01chNAuz2IDTAhH8U8j_!!2200608599252.jpg\", \"status\": null, \"volume\": 1, \"weight\": 1, \"barCode\": null, \"coupons\": null, \"details\": null, \"enabled\": true, \"imgUrls\": [\"https://gw.alicdn.com/imgextra/O1CN01chNAuz2IDTAhH8U8j_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN017eAntP2IDT90FCdgs_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i1/2200608599252/O1CN01Ry8fd02IDT99AxznF_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i2/2200608599252/O1CN01PF6mEr2IDT96CmBwo_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i1/2200608599252/O1CN01WW17LV2IDT8xK9BSg_!!2200608599252.jpg\"], \"keyword\": null, \"storeId\": null, \"visited\": 137, \"activity\": [\"SECKILL\"], \"isDelete\": false, \"propType\": \"single\", \"services\": null, \"stockNum\": 99, \"tenantId\": 0, \"unitName\": null, \"videoUrl\": null, \"costPrice\": null, \"categoryId\": 14, \"createTime\": \"2023-05-16 17:09:57\", \"createUser\": 0, \"internalId\": null, \"merchantId\": null, \"sellStatus\": [2, 4], \"statusDesc\": null, \"updateTime\": \"2023-05-31 14:31:33\", \"description\": \"<p><br></p>\", \"productInfo\": \"海盐黑胡椒\", \"productName\": \"海盐黑胡椒粒碎研磨柠檬低脂健身鸡胸肉煎牛排混合调料食用胡椒粉\", \"productType\": \"product\", \"categoryList\": [14], \"categoryName\": \"调料酒水\", \"shippingType\": null, \"originalPrice\": 20, \"shippingTemplate\": null}, {\"id\": 66, \"sort\": 0, \"tags\": null, \"attrs\": null, \"price\": 6.9, \"sales\": 74, \"imgUrl\": \"//img.alicdn.com/imgextra/i3/2200608599252/O1CN01evRsme2IDTDZgfZR2_!!0-item_pic.jpg\", \"status\": null, \"volume\": 0, \"weight\": 0, \"barCode\": null, \"coupons\": null, \"details\": null, \"enabled\": true, \"imgUrls\": [\"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN01Zf0yek2IDTDNfW0c2_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN01LZPq6F2IDTE7aZnMR_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i4/2200608599252/O1CN01pDRTbl2IDTDuPYaI6_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i1/2200608599252/O1CN01GWXE4n2IDTEg0b9qt_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN01VbdQZp2IDTE4gpGyj_!!2200608599252.jpg\"], \"keyword\": null, \"storeId\": null, \"visited\": 60, \"activity\": null, \"isDelete\": false, \"propType\": \"single\", \"services\": null, \"stockNum\": 99, \"tenantId\": 0, \"unitName\": null, \"videoUrl\": null, \"costPrice\": null, \"categoryId\": 20, \"createTime\": \"2023-05-16 17:04:58\", \"createUser\": 0, \"internalId\": null, \"merchantId\": null, \"sellStatus\": null, \"statusDesc\": null, \"updateTime\": \"2023-05-19 16:01:56\", \"description\": \"<p><br></p>\", \"productInfo\": null, \"productName\": \"0脂肪荞麦面条纯正宗无糖精低脂全麦孕妇主食速食细挂面袋非手工\", \"productType\": \"product\", \"categoryList\": [20], \"categoryName\": \"南北干货\", \"shippingType\": null, \"originalPrice\": 20, \"shippingTemplate\": null}, {\"id\": 65, \"sort\": 0, \"tags\": null, \"attrs\": null, \"price\": 18.8, \"sales\": 0, \"imgUrl\": \"//img.alicdn.com/imgextra/i1/2200608599252/O1CN01t8dUaB2IDTBzdnqYT_!!2200608599252-0-lubanu-s.jpg\", \"status\": null, \"volume\": 0, \"weight\": 0, \"barCode\": null, \"coupons\": null, \"details\": null, \"enabled\": true, \"imgUrls\": [\"https://img.alicdn.com/imgextra/i2/2200608599252/O1CN01eawdys2IDTBy1yzRG_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i2/2200608599252/O1CN01B2cUJq2IDTE2M4fLt_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i2/2200608599252/O1CN014bf9js2IDTC8r3AjR_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i4/2200608599252/O1CN01OIjRpb2IDTCBOfvvM_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN019O3yqZ2IDTE7Qrj9y_!!2200608599252.jpg\"], \"keyword\": null, \"storeId\": null, \"visited\": 5, \"activity\": null, \"isDelete\": false, \"propType\": \"single\", \"services\": null, \"stockNum\": 99, \"tenantId\": 0, \"unitName\": null, \"videoUrl\": null, \"costPrice\": null, \"categoryId\": 20, \"createTime\": \"2023-05-16 17:03:29\", \"createUser\": 0, \"internalId\": null, \"merchantId\": null, \"sellStatus\": null, \"statusDesc\": null, \"updateTime\": \"2023-05-16 17:15:15\", \"description\": \"<p ><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i3/2200608599252/O1CN01tmZVk12IDSwhdXzp9_!!2200608599252.jpg\\\" align=\\\"absmiddle\\\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i1/2200608599252/O1CN01HTBzTH2IDSwge2Dac_!!2200608599252.jpg\\\" align=\\\"absmiddle\\\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i1/2200608599252/O1CN01vDQ34g2IDSwiR2n8C_!!2200608599252.jpg\\\" align=\\\"absmiddle\\\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i2/2200608599252/O1CN017mU8x62IDSwlmSPM8_!!2200608599252.jpg\\\" align=\\\"absmiddle\\\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i4/2200608599252/O1CN01UGYCRA2IDSwkxCj9h_!!2200608599252.jpg\\\" align=\\\"absmiddle\\\" /> </p> \\n  <p>&nbsp;<img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i4/2200608599252/O1CN01GKe6au2IDT9qHslDk_!!2200608599252.jpg\\\" align=\\\"absmiddle\\\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i3/2200608599252/O1CN01EkzPkR2IDT9vf19Ib_!!2200608599252.jpg\\\" align=\\\"absmiddle\\\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i1/2200608599252/O1CN01n8u4nV2IDT9nDaQDS_!!2200608599252.jpg\\\" align=\\\"absmiddle\\\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i1/2200608599252/O1CN01uGjtpD2IDT9xfDjis_!!2200608599252.jpg\\\" align=\\\"absmiddle\\\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i2/2200608599252/O1CN011RFOzf2IDT9saGCyS_!!2200608599252.jpg\\\" align=\\\"absmiddle\\\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i3/2200608599252/O1CN013EZv5b2IDT9zhoYcz_!!2200608599252.jpg\\\" align=\\\"absmiddle\\\" /></p> \\n  <p>&nbsp;</p> \\n  <p>&nbsp;</p><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"https://www.o0b.cn/i.php?t.png&rid=gw-4.646346e18b0d9&p=2105076281&k=75408&t=1684227811\\\" style=\\\"display:none\\\" />\", \"productInfo\": null, \"productName\": \"东北黑米5斤大米新货黑龙江农家紫米香米五谷杂粮粥糯米五常\", \"productType\": \"product\", \"categoryList\": [20], \"categoryName\": \"南北干货\", \"shippingType\": null, \"originalPrice\": 30, \"shippingTemplate\": null}, {\"id\": 64, \"sort\": 0, \"tags\": null, \"attrs\": null, \"price\": 10.9, \"sales\": 0, \"imgUrl\": \"//img.alicdn.com/imgextra/i2/2200608599252/O1CN01Mi5IE72IDTDSZx6dr_!!2200608599252-0-lubanu-s.jpg\", \"status\": null, \"volume\": 0, \"weight\": 0, \"barCode\": null, \"coupons\": null, \"details\": null, \"enabled\": true, \"imgUrls\": [\"https://img.alicdn.com/imgextra/i4/2200608599252/O1CN01dRJIDo2IDTDh2K893_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i4/2200608599252/O1CN01Pyk1Ek2IDTE1aR18j_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN01mLVsOm2IDTE0H4TA5_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN018diUYA2IDTEBXMXRt_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i4/2200608599252/O1CN018fU2uj2IDTE88wZFH_!!2200608599252.jpg\"], \"keyword\": null, \"storeId\": null, \"visited\": 0, \"activity\": null, \"isDelete\": false, \"propType\": \"single\", \"services\": null, \"stockNum\": 99, \"tenantId\": 0, \"unitName\": null, \"videoUrl\": null, \"costPrice\": null, \"categoryId\": 20, \"createTime\": \"2023-05-16 17:01:12\", \"createUser\": 0, \"internalId\": null, \"merchantId\": null, \"sellStatus\": null, \"statusDesc\": null, \"updateTime\": \"2023-05-16 17:15:24\", \"description\": \"\", \"productInfo\": null, \"productName\": \"东北红衣生花生米500g黑龙江四粒红花生仁新鲜红皮不带壳生的新货\", \"productType\": \"product\", \"categoryList\": [20], \"categoryName\": \"南北干货\", \"shippingType\": null, \"originalPrice\": 49.8, \"shippingTemplate\": null}, {\"id\": 63, \"sort\": 0, \"tags\": null, \"attrs\": null, \"price\": 48.8, \"sales\": 0, \"imgUrl\": \"//img.alicdn.com/imgextra/i4/2200608599252/O1CN01E9bTGj2IDTEQm4EA2_!!2200608599252-0-lubanu-s.jpg\", \"status\": null, \"volume\": 0, \"weight\": 0, \"barCode\": null, \"coupons\": null, \"details\": null, \"enabled\": true, \"imgUrls\": [\"https://img.alicdn.com/imgextra/i2/2200608599252/O1CN0118UBt72IDTAhlRnbp_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN011ZkJjr2IDTEVBLDfM_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i1/2200608599252/O1CN01WDNx392IDTET1AelS_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i4/2200608599252/O1CN01okI7Ru2IDTEGXLm1O_!!2200608599252.jpg\", \"https://img.alicdn.com/imgextra/i1/2200608599252/O1CN01jAiJry2IDTEPjRRTq_!!2200608599252.jpg\"], \"keyword\": null, \"storeId\": null, \"visited\": 0, \"activity\": null, \"isDelete\": false, \"propType\": \"single\", \"services\": null, \"stockNum\": 99, \"tenantId\": 0, \"unitName\": null, \"videoUrl\": null, \"costPrice\": null, \"categoryId\": 20, \"createTime\": \"2023-05-16 17:00:34\", \"createUser\": 0, \"internalId\": null, \"merchantId\": null, \"sellStatus\": null, \"statusDesc\": null, \"updateTime\": \"2023-05-24 20:34:44\", \"description\": \"<p><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i1/2200608599252/O1CN01rQZqwT2IDSwet8F27_!!2200608599252.jpg\\\" align=\\\"absmiddle\\\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i3/2200608599252/O1CN01iNaLYy2IDSwh5jzlF_!!2200608599252.jpg\\\" align=\\\"absmiddle\\\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i4/2200608599252/O1CN017z288J2IDSwj7Xl3m_!!2200608599252.jpg\\\" align=\\\"absmiddle\\\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i3/2200608599252/O1CN017JDSDW2IDSwh5lGuy_!!2200608599252.jpg\\\" align=\\\"absmiddle\\\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i2/2200608599252/O1CN01l5th9C2IDT9Of6fSh_!!2200608599252.jpg\\\" align=\\\"absmiddle\\\" /> </p> \\n  <p ><br /> <img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i4/T2s4moXH8XXXXXXXXX-350475995.png?p=hb_v3_client_1012452_start_top_1\\\" /></p> \\n  <p ><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"http://img.alicdn.com/imgextra/i4/T2s4moXH8XXXXXXXXX-350475995.png?p=hb_v3_client_1012452_end_top_1\\\" /></p><img style=\'max-width:100%;vertical-align: middle;\' src=\\\"https://www.o0b.cn/i.php?t.png&rid=gw-1.64634630ec4e2&p=2105076281&k=75408&t=1684227635\\\" style=\\\"display:none\\\" />\", \"productInfo\": null, \"productName\": \"东北黑木耳干货500g新货小碗耳特产正宗黑龙江无根非秋鼠野生特级\", \"productType\": \"product\", \"categoryList\": [20], \"categoryName\": \"南北干货\", \"shippingType\": null, \"originalPrice\": 100, \"shippingTemplate\": null}, {\"id\": 62, \"sort\": 0, \"tags\": null, \"attrs\": null, \"price\": 1696, \"sales\": 0, \"imgUrl\": \"https://img.alicdn.com/imgextra/i1/6000000007041/O1CN01Riim5r21spUadRSy9_!!6000000007041-0-picassoopen.jpg\", \"status\": null, \"volume\": 1, \"weight\": 1, \"barCode\": null, \"coupons\": null, \"details\": null, \"enabled\": true, \"imgUrls\": [\"https://img.alicdn.com/imgextra/i1/6000000007041/O1CN01Riim5r21spUadRSy9_!!6000000007041-0-picassoopen.jpg\", \"https://img.alicdn.com/imgextra/i1/725677994/O1CN01fD4rKk28vIq1i4ahw_!!725677994-0-sm.jpg\", \"https://img.alicdn.com/imgextra/i4/725677994/O1CN01zdeLRT28vIpxs0LAx_!!725677994.jpg\", \"https://img.alicdn.com/imgextra/i4/725677994/O1CN01A994Mz28vIptwzkyI_!!725677994.jpg\", \"https://img.alicdn.com/imgextra/i2/725677994/O1CN01rwuy7128vIptwy0qd_!!725677994.jpg\"], \"keyword\": null, \"storeId\": null, \"visited\": 2, \"activity\": null, \"isDelete\": false, \"propType\": \"single\", \"services\": null, \"stockNum\": 98, \"tenantId\": 0, \"unitName\": null, \"videoUrl\": null, \"costPrice\": null, \"categoryId\": 14, \"createTime\": \"2023-05-16 16:51:43\", \"createUser\": 0, \"internalId\": null, \"merchantId\": null, \"sellStatus\": null, \"statusDesc\": null, \"updateTime\": \"2023-05-30 11:55:17\", \"description\": \"\", \"productInfo\": null, \"productName\": \"洋河礼盒梦之蓝M6-52度500ml*2瓶口感绵柔浓香型酒水礼盒装送礼\", \"productType\": \"product\", \"categoryList\": [14], \"categoryName\": \"调料酒水\", \"shippingType\": null, \"originalPrice\": 2035, \"shippingTemplate\": null}, {\"id\": 61, \"sort\": 0, \"tags\": null, \"attrs\": null, \"price\": 420, \"sales\": 0, \"imgUrl\": \"//img.alicdn.com/imgextra/i4/6000000007092/O1CN0121ly4f22GBjo2KmcY_!!6000000007092-0-sm.jpg\", \"status\": null, \"volume\": 0, \"weight\": 0, \"barCode\": null, \"coupons\": null, \"details\": null, \"enabled\": true, \"imgUrls\": [\"https://img.alicdn.com/imgextra/i4/6000000007092/O1CN0121ly4f22GBjo2KmcY_!!6000000007092-0-sm.jpg\", \"https://img.alicdn.com/imgextra/i2/6000000007415/O1CN016zbOjk24e7QYbhpb3_!!6000000007415-0-at.jpg\", \"https://img.alicdn.com/imgextra/i4/725677994/O1CN013nbrQE28vIjY6B1JG_!!725677994.jpg\", \"https://img.alicdn.com/imgextra/i3/725677994/O1CN01ufGsGR28vIjWTyVOE_!!725677994.jpg\", \"https://img.alicdn.com/imgextra/i4/725677994/O1CN01uhg02G28vIjZYWpd7_!!725677994.jpg\"], \"keyword\": null, \"storeId\": null, \"visited\": 4, \"activity\": null, \"isDelete\": false, \"propType\": \"single\", \"services\": null, \"stockNum\": 22, \"tenantId\": 0, \"unitName\": null, \"videoUrl\": null, \"costPrice\": null, \"categoryId\": 14, \"createTime\": \"2023-05-16 16:51:20\", \"createUser\": 0, \"internalId\": null, \"merchantId\": null, \"sellStatus\": null, \"statusDesc\": null, \"updateTime\": \"2023-05-30 21:10:35\", \"description\": \"\", \"productInfo\": null, \"productName\": \"洋河海之蓝52度礼盒酒480ml*2瓶礼盒浓香型白酒酒水口感绵柔\", \"productType\": \"product\", \"categoryList\": [14], \"categoryName\": \"调料酒水\", \"shippingType\": null, \"originalPrice\": 450, \"shippingTemplate\": null}], \"type\": \"product\", \"options\": {\"radio\": \"1\", \"cateList\": [9], \"isHidden\": false, \"paddingB\": 10, \"paddingL\": 10, \"paddingR\": 10, \"paddingT\": 10, \"productNum\": 20}, \"tabType\": 2}, {\"data\": [1, 2, 3], \"type\": \"bargaining\", \"options\": {\"isBtn\": true, \"title\": \"砍价专区Bargaining\", \"color1\": \"#F7D264\", \"color2\": \"#F1F8BF\", \"isPrice\": true, \"fontSize\": 14, \"goodsNum\": 3, \"isHidden\": false, \"paddingLR\": 10, \"paddingTB\": 15, \"titleColor\": \"#ED980F\", \"borderRadius\": 10}}, {\"data\": [1, 2, 3], \"type\": \"coupon\", \"options\": {\"color1\": \"#ff0000\", \"color2\": \"#cccccc\", \"bgColor\": \"#ffffff\", \"isHidden\": false}}, {\"data\": [1, 2, 3], \"type\": \"pintuan\", \"options\": {\"isBtn\": false, \"title\": \"拼团活动\", \"color1\": \"#FFFFFF\", \"color2\": \"#FFFFFF\", \"isPrice\": true, \"isTitle\": true, \"fontSize\": 14, \"goodsNum\": 3, \"isHidden\": false, \"isCantuan\": false, \"isPintuan\": false, \"paddingLR\": 9, \"paddingTB\": 15, \"smallIcon\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684831157320.png\", \"titleColor\": \"#000000\", \"borderRadius\": 10}}, {\"data\": [{\"img\": \"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681371391192.png\"}], \"type\": \"hotZone\", \"options\": {\"isHidden\": false, \"zoneList\": [{\"topPer\": 0.1211, \"leftPer\": 0.1915, \"pagePath\": \"/pages/index/index\", \"selected\": 0, \"widthPer\": 0.3095, \"heightPer\": 0.3055}, {\"topPer\": 0.5188, \"leftPer\": 0.6402, \"pagePath\": \"/pages/index/index\", \"selected\": 0, \"widthPer\": 0.196, \"heightPer\": 0.2709}]}}]', NULL, NULL, NULL, 0, '2023-06-21 16:50:46', '2023-06-21 16:59:25', 1, 1, 0, 0);

-- ----------------------------
-- Table structure for kz_diy_path
-- ----------------------------
DROP TABLE IF EXISTS `kz_diy_path`;
CREATE TABLE `kz_diy_path`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '#',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '页面名称',
  `h5_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'H5链接',
  `mp_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '小程序链接',
  `page_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '页面类型',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `status_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '页面路径' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_diy_path
-- ----------------------------
INSERT INTO `kz_diy_path` VALUES (1, '首页', NULL, 'pages/index/index', 'system', NULL, NULL, NULL, 1, '2023-04-25 11:33:51', '2023-04-25 11:33:51', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (2, '领券中心', 'pages/order/coupon', 'pages/order/coupon', 'system', '领券中心', NULL, NULL, 1, '2023-05-09 16:55:48', '2023-05-09 16:59:31', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (3, '我的地址', 'pages/service/address', 'pages/service/address', 'system', '我的地址', NULL, NULL, 1, '2023-05-09 16:56:37', '2023-05-09 16:59:25', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (4, '个人中心', 'pages/tabs/user', 'pages/tabs/user', 'system', NULL, NULL, NULL, 1, '2023-05-09 16:58:32', '2023-05-09 16:58:32', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (5, '购物车', 'pages/tabs/cart', 'pages/tabs/cart', 'system', NULL, NULL, NULL, 1, '2023-05-09 16:58:47', '2023-05-09 16:58:47', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (6, '商品分类', NULL, 'pages/tabs/classify', 'system', NULL, NULL, NULL, 1, '2023-05-09 16:59:15', '2023-05-09 16:59:15', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (7, '充值中心', NULL, 'pages/recharge/recharge', 'system', NULL, NULL, NULL, 1, '2023-05-10 11:45:52', '2023-05-10 19:19:56', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (8, '资讯中心', NULL, 'pages/service/find', 'system', NULL, NULL, NULL, 1, '2023-05-10 11:46:35', '2023-05-10 19:20:00', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (9, '我的推广', NULL, 'pages/promotion/promotion', 'system', NULL, NULL, NULL, 1, '2023-05-10 11:46:57', '2023-05-10 19:20:03', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (11, '设置', NULL, 'pages/userPage/setting', 'system', NULL, NULL, NULL, 1, '2023-05-19 11:20:02', '2023-05-19 11:20:02', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (12, '核销', NULL, 'pages/pay/writeOff', 'system', NULL, NULL, NULL, 1, '2023-05-25 10:41:23', '2023-05-25 10:42:46', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (13, '余额', NULL, 'pages/userPage/balance', 'system', NULL, NULL, NULL, 1, '2023-05-26 15:22:01', '2023-05-26 15:22:01', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (14, '我的优惠券', NULL, 'pages/userPage/myCoupon', 'system', NULL, NULL, NULL, 1, '2023-05-26 18:14:17', '2023-05-26 18:15:22', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (15, '线下支付', NULL, 'pages/pay/index', 'system', NULL, NULL, NULL, 1, '2023-05-29 09:58:10', '2023-05-29 09:58:10', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (16, '签到', NULL, 'pages/userPage/signIn', 'system', NULL, NULL, NULL, 1, '2023-05-29 09:58:47', '2023-05-29 09:58:47', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (17, '积分中心', NULL, 'pages/userPage/integral', 'system', NULL, NULL, NULL, 1, '2023-06-05 15:02:14', '2023-06-05 16:15:35', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (18, '拼团记录', NULL, 'pages/product/ptRecord', 'system', NULL, NULL, NULL, 1, '2023-06-07 15:37:57', '2023-06-07 15:37:57', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (19, '拼团活动页', NULL, 'pages/product/pintuan', 'system', NULL, NULL, NULL, 1, '2023-06-07 15:38:15', '2023-06-07 15:38:15', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (20, '短视频', NULL, 'pages/userPage/shortVideo', 'system', NULL, NULL, NULL, 1, '2023-06-13 17:38:25', '2023-06-13 17:38:25', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (21, '点单', NULL, 'pages/tabs/order', 'system', NULL, NULL, NULL, 1, '2023-06-25 09:51:40', '2023-06-25 09:51:40', 1, 0, 0);
INSERT INTO `kz_diy_path` VALUES (22, '砍价记录', NULL, 'pages/product/bargainingRecord', 'system', NULL, NULL, NULL, 1, '2023-06-28 17:49:28', '2023-06-28 17:49:28', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_feedback
-- ----------------------------
DROP TABLE IF EXISTS `kz_feedback`;
CREATE TABLE `kz_feedback`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '反馈类型',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '留言内容',
  `status` int(255) NULL DEFAULT NULL COMMENT '处理状态 0 未处理 1 已处理',
  `status_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态描述 ',
  `result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '处理结果',
  `img_urls` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '图片',
  `is_read` tinyint(2) NULL DEFAULT 0 COMMENT '是否已读',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  `create_user` bigint(20) NOT NULL COMMENT '用户id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `is_delete` tinyint(1) NULL DEFAULT 0,
  `enabled` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '意见反馈' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_feedback
-- ----------------------------
INSERT INTO `kz_feedback` VALUES (42, NULL, '王同学1', '15026453200', 'GUEST', '测试反馈', 0, '未处理', NULL, '[\"https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/dance/2023119/RDiVgaRzhZSe.png\"]', 0, 0, 810, '2023-01-19 18:49:45', '2023-01-19 18:49:45', 0, 1);
INSERT INTO `kz_feedback` VALUES (43, NULL, '王萌萌', '15026498906', 'GUEST', '反馈意  见反馈意见反馈意见反馈意见反馈意见反馈意见\n\n反馈意见反馈意见反馈意见', 0, '未处理', NULL, '[\"https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/dance/2023210/NKOBbcTPVbUO.jpg\",\"https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/dance/2023210/QiidwcOGzIoB.jpg\",\"https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/dance/2023210/OTHVJzrSJdLs.jpg\"]', 0, 0, 856, '2023-02-10 13:41:06', '2023-02-10 13:41:06', 0, 1);

-- ----------------------------
-- Table structure for kz_merchant
-- ----------------------------
DROP TABLE IF EXISTS `kz_merchant`;
CREATE TABLE `kz_merchant`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '#',
  `agent_id` int(11) NULL DEFAULT NULL COMMENT '上级代理商',
  `merchant_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商家名称，营业执照',
  `short_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户简称',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `front_img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家头图',
  `img_urls` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  `category_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省市区',
  `address_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `license_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照',
  `latitude` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经纬度',
  `longitude` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经纬度',
  `contact_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `expired_time` date NULL DEFAULT NULL COMMENT '到期时间',
  `is_online` tinyint(1) NULL DEFAULT NULL COMMENT '是否上线',
  `status` int(255) NULL DEFAULT NULL COMMENT '状态',
  `status_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '是否启用',
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商家表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_merchant
-- ----------------------------
INSERT INTO `kz_merchant` VALUES (13, NULL, '惠速达配送科技有限公司', '惠速达配送', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/yxjwAPP/upload1683184889000.jpg', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/yxjwAPP/upload1683184923000.jpg', '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/yxjwAPP/upload1683184928000.jpg\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/yxjwAPP/upload1683184928000.jpg\"]', '1111111111111111111111111111111111', '1', '[\"浙江省\",\"杭州市\",\"滨江区\"]', '创之海科技园C203室', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/yxjwAPP/upload1683184903000.jpg', NULL, NULL, '丁先生', '13675175408', NULL, NULL, NULL, NULL, '2023-05-04 15:24:40', '2023-05-04 15:24:40', 1, 1, 0, 0);

-- ----------------------------
-- Table structure for kz_notice
-- ----------------------------
DROP TABLE IF EXISTS `kz_notice`;
CREATE TABLE `kz_notice`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `img_urls` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '图片地址',
  `is_read` tinyint(2) NULL DEFAULT 0 COMMENT '是否已读',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT NULL,
  `is_delete` tinyint(2) NULL DEFAULT NULL,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_notice
-- ----------------------------
INSERT INTO `kz_notice` VALUES (1, '双十一大优惠！', '双十一大优惠！双十一大优惠！双十一大优惠！', 859, '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/yxjwAPP/upload1676342897000.jpg\"]', 0, '2023-02-14 10:48:52', '2023-02-14 10:48:52', 10, 1, 0, 0);

-- ----------------------------
-- Table structure for kz_order
-- ----------------------------
DROP TABLE IF EXISTS `kz_order`;
CREATE TABLE `kz_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电影票：座位信息；小吃：数量',
  `open_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信用户id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '随机订单号',
  `transaction_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信订单id',
  `prepay_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预订单id',
  `refund_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退款订单id',
  `amount` int(11) NULL DEFAULT NULL COMMENT '数量',
  `total` decimal(10, 2) NOT NULL COMMENT '总价',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '无意义字段',
  `biz_type` int(11) NULL DEFAULT NULL COMMENT '业务类型 1 购买商品，2 充值 3 开通vip',
  `status` int(11) NULL DEFAULT NULL COMMENT '订单状态 0 未支付，1 已支付，2 已取消 3 已出票 4 已过期 5 已退款',
  `status_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态描述',
  `extra` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '支付订单提交的信息',
  `create_time` datetime(0) NOT NULL COMMENT '下单时间',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '订单所属用户',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '是否可用',
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 180 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_order
-- ----------------------------
INSERT INTO `kz_order` VALUES (1, NULL, NULL, 'oq0id5NtaqFy8pWB1ydIrC2EfUSs', '2023041005454978857', NULL, 'wx1017455022468637484270c07ba9f30000', NULL, NULL, 0.01, NULL, NULL, NULL, NULL, '{\"itemId\":0,\"planId\":0,\"total\":\"0.01\"}', '2023-04-10 17:45:50', NULL, '2023-04-10 17:45:50', 865, 1, 0, 0);
INSERT INTO `kz_order` VALUES (2, NULL, NULL, 'oq0id5NtaqFy8pWB1ydIrC2EfUSs', '202304210631367578306', NULL, 'wx2118313726383641b156838b666ffb0000', NULL, NULL, 20.00, NULL, NULL, NULL, NULL, 'null', '2023-04-21 18:31:37', NULL, '2023-04-21 18:31:37', 865, 1, 0, 0);
INSERT INTO `kz_order` VALUES (7, NULL, NULL, 'oq0id5NtaqFy8pWB1ydIrC2EfUSs', '202304230559129787621', NULL, 'wx23175913590037eb17c9de719fbb380000', NULL, NULL, 230.00, NULL, NULL, NULL, NULL, 'null', '2023-04-23 17:59:14', NULL, '2023-04-23 17:59:14', 865, 1, 0, 0);
INSERT INTO `kz_order` VALUES (11, NULL, NULL, 'oq0id5NtaqFy8pWB1ydIrC2EfUSs', '202304240917592362450', NULL, 'wx240918096963342611bc649068e7ba0000', NULL, NULL, 230.00, NULL, NULL, 0, '未支付', 'null', '2023-04-24 09:18:00', NULL, '2023-04-24 09:18:09', 865, 1, 0, 0);
INSERT INTO `kz_order` VALUES (12, NULL, NULL, 'oq0id5NtaqFy8pWB1ydIrC2EfUSs', '202304240921131497397', NULL, 'wx24092120536314a2fc8a94e8e548530000', NULL, NULL, 280.00, NULL, NULL, 0, '未支付', 'null', '2023-04-24 09:21:14', NULL, '2023-04-24 09:21:19', 865, 1, 0, 0);
INSERT INTO `kz_order` VALUES (19, NULL, NULL, 'oq0id5NtaqFy8pWB1ydIrC2EfUSs', '202304260414397579642', NULL, 'wx261614420143662025e3d8d2073b1a0000', NULL, NULL, 0.03, NULL, NULL, 0, '未支付', '{\"levelId\":23}', '2023-04-26 16:14:40', NULL, '2023-04-26 16:14:40', 865, 1, 0, 0);
INSERT INTO `kz_order` VALUES (20, NULL, NULL, 'oq0id5NtaqFy8pWB1ydIrC2EfUSs', '202304260429460963439', NULL, 'wx261629484160628abc123cb9df6efa0000', NULL, NULL, 0.03, NULL, NULL, 0, '未支付', '{\"levelId\":23}', '2023-04-26 16:29:47', NULL, '2023-04-26 16:29:47', 865, 1, 0, 0);
INSERT INTO `kz_order` VALUES (24, NULL, NULL, 'oq0id5EsPxoTUfaLm2EgloamlrK4', '202304260450435213208', NULL, 'wx2616504563859026e6343542d3aa540000', NULL, NULL, 0.03, NULL, NULL, 0, '未支付', '{\"levelId\":23}', '2023-04-26 16:50:44', NULL, '2023-04-26 16:50:44', 866, 1, 0, 0);
INSERT INTO `kz_order` VALUES (25, NULL, NULL, 'oq0id5EsPxoTUfaLm2EgloamlrK4', '202304260450496728167', NULL, 'wx26165051837993abed07a6bdfabc6a0000', NULL, NULL, 0.03, NULL, NULL, 0, '未支付', '{\"levelId\":23}', '2023-04-26 16:50:50', NULL, '2023-04-26 16:50:50', 866, 1, 0, 0);

-- ----------------------------
-- Table structure for kz_order_queue
-- ----------------------------
DROP TABLE IF EXISTS `kz_order_queue`;
CREATE TABLE `kz_order_queue`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '随机订单号',
  `create_time` datetime(0) NOT NULL COMMENT '下单时间',
  `is_update` tinyint(255) NULL DEFAULT NULL COMMENT '是否核销',
  `is_sync` tinyint(2) NULL DEFAULT 0,
  `enabled` tinyint(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `un_order_id`(`order_id`) USING BTREE COMMENT '唯一约束'
) ENGINE = InnoDB AUTO_INCREMENT = 184 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付队列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_order_queue
-- ----------------------------
INSERT INTO `kz_order_queue` VALUES (1, '202304100545497885758', '2023-04-10 17:45:50', NULL, 0, NULL);
INSERT INTO `kz_order_queue` VALUES (4, '202304210631367578306', '2023-04-21 18:31:37', NULL, 0, NULL);
INSERT INTO `kz_order_queue` VALUES (5, '202304210632386127035', '2023-04-21 18:32:39', NULL, 0, NULL);
INSERT INTO `kz_order_queue` VALUES (6, '202304230350080409065', '2023-04-23 15:50:09', NULL, 0, NULL);

-- ----------------------------
-- Table structure for kz_print_template
-- ----------------------------
DROP TABLE IF EXISTS `kz_print_template`;
CREATE TABLE `kz_print_template`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) NULL DEFAULT NULL COMMENT '所属门店',
  `template_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板名称',
  `template_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板类型',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  `template` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '模板内容',
  `details` json NULL COMMENT '配置详情',
  `sort` int(255) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '是否启用',
  `is_default` tinyint(2) NULL DEFAULT NULL COMMENT '是否模板',
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '打印模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_print_template
-- ----------------------------
INSERT INTO `kz_print_template` VALUES (19, NULL, '商城小票模板', '1', '默认小票模板', '<CB>小票名称</CB><BR>名称           单价  数量 金额<BR>${details}--------------------------------<BR>合计：  　 　　　 ${totalNum}  ￥${totalPrice}元<BR>优惠金额：  　 　　 -￥${couponPrice}元<BR>实付金额：  　 　　　￥${payPrice}元<BR>--------------------------------<BR>订单编号：${orderId}<BR>下单时间：${createTime}<BR>--------------------------------<BR>会员昵称：${addressInfo.nickName}<BR>--------------------------------<BR>买家姓名：${nickName}<BR>联系方式：${phone}<BR>--------------------------------<BR>客服电话 ${storePhone}<BR>商城地址 ${storeAddress}<BR>--------------------------------<BR><C>谢谢惠顾，欢迎下次光临</C>', '{\"title\": \"小票名称\", \"vipInfo\": [\"会员昵称\", \"会员等级\"], \"mallInfo\": [\"联系方式\", \"商城地址\"], \"buyerInfo\": [\"买家姓名\", \"联系方式\"], \"goodsInfo\": [\"商品名称\", \"商品数量\", \"商品金额\", \"合计\", \"优惠金额\", \"实付金额\"], \"orderInfo\": [\"订单编号\", \"下单时间\"], \"footerInfo\": \"谢谢惠顾，欢迎下次光临\", \"headerInfo\": [\"小票标题\"], \"remarkInfo\": []}', 3, '2023-06-15 18:40:11', '2023-06-16 17:18:08', 1, 1, NULL, 0, 0);

-- ----------------------------
-- Table structure for kz_printer
-- ----------------------------
DROP TABLE IF EXISTS `kz_printer`;
CREATE TABLE `kz_printer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) NULL DEFAULT NULL COMMENT '所属门店',
  `printer_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打印机名称',
  `print_num` int(11) NULL DEFAULT NULL COMMENT '打印份数',
  `printer_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打印机类型',
  `sn_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '序列号',
  `sn_secret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备密钥',
  `app_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'appId',
  `app_secret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'app密钥',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打印机位置',
  `is_online` tinyint(255) NULL DEFAULT 0 COMMENT '是否在线',
  `last_check_time` datetime(0) NULL DEFAULT NULL COMMENT '最近一次检测时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  `sort` int(255) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '打印机信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_printer
-- ----------------------------
INSERT INTO `kz_printer` VALUES (17, NULL, '默认打印机', 1, 'feie', '932522977', 'jhv9gx87', 'dingxiaosong1987@126.com', 'YIPSw3AEHfWCMJbc', NULL, 1, '2023-07-21 17:40:00', '默认打印机', 1, '2023-06-15 15:46:55', '2023-07-21 17:40:00', 1, 1, 0, 0);

-- ----------------------------
-- Table structure for kz_store
-- ----------------------------
DROP TABLE IF EXISTS `kz_store`;
CREATE TABLE `kz_store`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '门店id',
  `short_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '门店简称',
  `store_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '门店名称',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头图',
  `img_urls` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '轮播图',
  `store_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '店铺类型 local,delivery,mall',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省市区',
  `address_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `latitude` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经纬度',
  `longitude` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经纬度',
  `is_online` tinyint(2) NULL DEFAULT NULL COMMENT '是否营业',
  `start_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '营业开始时间',
  `end_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '营业结束时间',
  `contact_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注信息',
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '所属机构',
  `is_delete` tinyint(2) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '门店信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store
-- ----------------------------
INSERT INTO `kz_store` VALUES (9, NULL, NULL, '惠速达配送超级体验店', '陆川县九洲市场', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682582997379.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682583071750.png', '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682583071750.png\"]', 'local', '[\"浙江省\",\"杭州市\",\"滨江区\"]', '浙江省杭州市滨江区长河街道杭州滨江星光大道亚朵酒店国能中心', '30.209228', '120.205258', 1, '09:26', '21:26', '马先生', '15685424625', '11111', NULL, 62, '2023-01-19 16:17:34', '2023-04-28 10:57:54', 1, 0, 0);
INSERT INTO `kz_store` VALUES (10, NULL, NULL, '惠速达配送城南中转仓', '陆川县城南', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682582997379.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682583071750.png', '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682583071750.png\"]', 'local', '[\"浙江省\",\"杭州市\",\"滨江区\"]', '浙江省杭州市滨江区浦沿街道汉堡王(滨江宝龙城市广场店)宝龙城(杭州滨江店)', '30.18684', '120.168331', 1, '09:26', '21:26', '马先生', '15685424625', '2222222', NULL, 1, '2023-01-19 16:22:40', '2023-04-28 10:57:35', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_store_after_sales
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_after_sales`;
CREATE TABLE `kz_store_after_sales`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '商品信息',
  `apply_type` int(11) NULL DEFAULT NULL COMMENT '申请类型 1：仅退款，2：退货退款',
  `return_type` int(11) NULL DEFAULT NULL COMMENT '退货方式 1：上门取件 2：自行寄回',
  `apply_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '退款原因',
  `goods_status` int(11) NULL DEFAULT NULL COMMENT '货物状态',
  `order_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `img_urls` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '申请图片',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '申请信息',
  `reply` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '商家答复',
  `pay_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际支付金额',
  `refund_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '退款金额',
  `address_id` int(11) NULL DEFAULT NULL COMMENT '收货地址id',
  `address_info` json NULL COMMENT '收件信息',
  `pickup_info` json NULL COMMENT '取件信息',
  `delivery_info` json NULL COMMENT '快递信息',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `status_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_order`(`order_id`) USING BTREE COMMENT '订单唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城-售后记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_after_sales
-- ----------------------------
INSERT INTO `kz_store_after_sales` VALUES (1, '浙江特级白萝卜，生津止渴赛人参', 1, NULL, '不想要了', 2, '202306080419329634996', 868, NULL, NULL, '订单已超过7天，不可退款~', 0.01, NULL, NULL, NULL, NULL, NULL, 1, '等待商家处理', 868, '2023-06-12 18:27:10', '2023-06-13 10:50:05', 1, 0, 0);
INSERT INTO `kz_store_after_sales` VALUES (2, NULL, 1, NULL, '不想要了', 2, '202306080904044224339', 867, '[\"https://kinzhan-im-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/168657337158368.png\",\"https://kinzhan-im-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/168657337162575.png\"]', '不想要了', NULL, 0.02, NULL, NULL, NULL, NULL, NULL, 1, '等待商家处理', 867, '2023-06-12 20:36:12', '2023-06-12 20:36:12', 1, 0, 0);
INSERT INTO `kz_store_after_sales` VALUES (4, '浙江特级白萝卜，生津止渴赛人参', 1, NULL, '不想要了', 2, '202306070332434102537', 868, NULL, NULL, NULL, 0.01, NULL, NULL, NULL, NULL, NULL, 1, '等待商家处理', 868, '2023-06-13 10:41:44', '2023-06-13 11:02:31', 1, 1, 0);
INSERT INTO `kz_store_after_sales` VALUES (7, '浙江特级白萝卜，生津止渴赛人参', 2, NULL, '不想要了', 1, '202306070223028559059', 868, '[\"https://kinzhan-im-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/168662681494285.png\"]', '哈哈哈哈哈', NULL, 0.01, 0.01, NULL, NULL, NULL, NULL, 1, '等待商家处理', 868, '2023-06-13 11:26:56', '2023-06-13 11:26:56', 1, 0, 0);
INSERT INTO `kz_store_after_sales` VALUES (8, '2022东北黏玉米棒黄糯苞米现摘真空包装新鲜甜玉米粒粘非即食粗粮', 2, 2, '买错了', 1, '2023051906332932957', 868, '[\"https://kinzhan-im-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/168662692524187.png\"]', '大范甘迪凡人换个地方黄日华', NULL, 0.02, 0.02, 24, '{\"phone\": \"18368836191\", \"address\": [\"广东省\", \"广州市\", \"海珠区\"], \"storeId\": null, \"nickName\": \"张三\", \"pickupTime\": \"\", \"addressDetail\": \"新港中路397号\"}', NULL, '{\"orderId\": \"\", \"deliveryId\": \"\", \"deliveryNo\": \"12318\", \"deliveryCompany\": \"顺丰快递\"}', 6, '完成', 868, '2023-06-13 11:28:47', '2023-06-13 14:49:54', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_store_attr_template
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_attr_template`;
CREATE TABLE `kz_store_attr_template`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `attr_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格名称',
  `values` json NULL COMMENT '规格值',
  `sort` int(255) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '规格模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_attr_template
-- ----------------------------
INSERT INTO `kz_store_attr_template` VALUES (10, '纸张', '[{\"value\": \"纸张\", \"detail\": [\"A4\", \"A3\"]}, {\"value\": \"颜色\", \"detail\": [\"白色\", \"红色\"]}]', NULL, NULL, NULL, NULL, 1, 0, 0);
INSERT INTO `kz_store_attr_template` VALUES (24, '手机', '[{\"value\": \"颜色\", \"detail\": [\"黑色\", \"白色\"]}, {\"value\": \"尺寸\", \"detail\": [\"5寸\", \"7寸\"]}]', NULL, NULL, NULL, NULL, 1, 0, 0);
INSERT INTO `kz_store_attr_template` VALUES (25, '酒吧', '[{\"value\": \"模式\", \"detail\": [\"商务KTV\", \"量贩KTV\", \"清吧\"]}]', NULL, NULL, NULL, NULL, 1, 0, 0);
INSERT INTO `kz_store_attr_template` VALUES (26, '包装', '[{\"value\": \"包装\", \"detail\": [\"大罐\", \"小罐\"]}]', NULL, NULL, NULL, NULL, 1, 0, 0);

-- ----------------------------
-- Table structure for kz_store_bargain
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_bargain`;
CREATE TABLE `kz_store_bargain`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动名称',
  `price_range` json NULL COMMENT '每次砍价区间',
  `product_ids` json NULL COMMENT '商品列表',
  `share_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分享标题',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '活动开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `expire_minute` int(11) NULL DEFAULT NULL COMMENT '持续时间（分钟）',
  `rules` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '活动规则',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `status_desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城-砍价' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_bargain
-- ----------------------------
INSERT INTO `kz_store_bargain` VALUES (1, '日常砍价活动', '[1, 10]', '[67, 37]', '是兄弟就来帮我砍一刀~', '2023-06-01 00:00:00', '2023-06-30 00:00:00', 60, '1111', 2, '已结束', 1, '2023-06-07 19:24:53', '2023-06-08 10:57:43', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_store_bargain_log
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_bargain_log`;
CREATE TABLE `kz_store_bargain_log`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `bargain_id` int(11) NOT NULL COMMENT '砍价活动id',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '发起id',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `product_id` int(11) NOT NULL COMMENT '砍价商品id',
  `product_detail_id` int(11) NOT NULL COMMENT '商品详情id',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '当前价格',
  `bargain_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '保护价',
  `cut_price` decimal(10, 2) NOT NULL COMMENT '砍掉的金额',
  `pay_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '实付价格',
  `price_range` json NULL COMMENT '每次砍价区间',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '活动开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `expired_time` datetime(0) NULL DEFAULT NULL COMMENT '砍价过期时间',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `status_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  `version` int(255) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城-砍价记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_bargain_log
-- ----------------------------
INSERT INTO `kz_store_bargain_log` VALUES (4, 1, 0, 868, 37, 792, '浙江特级白萝卜，生津止渴赛人参', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681375393826.png', 90.00, 10.00, 0.00, 79.36, '[1, 10]', '2023-06-01 00:00:00', '2023-06-30 00:00:00', '2023-06-08 12:03:34', 1, '进行中', 868, '2023-06-08 11:03:34', '2023-06-08 11:24:15', 1, 0, 0, 3);

-- ----------------------------
-- Table structure for kz_store_cart
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_cart`;
CREATE TABLE `kz_store_cart`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL,
  `store_id` int(11) NULL DEFAULT NULL COMMENT '店铺id',
  `product_id` int(11) NULL DEFAULT NULL,
  `product_detail_id` int(11) NULL DEFAULT NULL,
  `cart_num` int(11) NULL DEFAULT NULL,
  `scope` int(255) NULL DEFAULT NULL COMMENT '购买渠道',
  `combination_id` int(11) NULL DEFAULT NULL COMMENT '拼团活动id',
  `combination_log_id` int(11) NULL DEFAULT NULL COMMENT '拼团日志id',
  `bargain_id` int(11) NULL DEFAULT NULL COMMENT '砍价活动id',
  `bargain_log_id` int(11) NULL DEFAULT NULL COMMENT '砍价日志id',
  `is_hidden` tinyint(2) NULL DEFAULT 0 COMMENT '直接购买',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 267 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城购物车表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_cart
-- ----------------------------
INSERT INTO `kz_store_cart` VALUES (148, 867, NULL, 63, 914, 1, 5, NULL, NULL, NULL, NULL, 1, 867, '2023-06-06 11:51:59', '2023-06-06 11:52:10', 1, 0, 0);
INSERT INTO `kz_store_cart` VALUES (150, 868, NULL, 64, 915, 1, 5, NULL, NULL, NULL, NULL, 1, 868, '2023-06-06 11:54:03', '2023-06-06 11:54:03', 1, 0, 0);
INSERT INTO `kz_store_cart` VALUES (151, 868, NULL, 65, 916, 1, 3, NULL, NULL, NULL, NULL, 1, 868, '2023-06-06 17:39:59', '2023-06-06 17:39:59', 1, 0, 0);
INSERT INTO `kz_store_cart` VALUES (152, 868, NULL, 37, 786, 1, 1, NULL, NULL, NULL, NULL, 1, 868, '2023-06-06 17:59:13', '2023-06-25 09:28:38', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_store_combination
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_combination`;
CREATE TABLE `kz_store_combination`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动名称',
  `product_ids` json NULL COMMENT '商品列表',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `group_num` int(11) NULL DEFAULT NULL COMMENT '成团人数',
  `expire_minute` int(11) NULL DEFAULT NULL COMMENT '成团有效期（分钟）',
  `limit_num` int(11) NULL DEFAULT NULL COMMENT '数量限制',
  `is_virtual` tinyint(1) NULL DEFAULT NULL COMMENT '虚拟成团',
  `share_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分享标题',
  `rules` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '规则',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `status_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城-拼团' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_combination
-- ----------------------------
INSERT INTO `kz_store_combination` VALUES (1, '天天早上蔬菜拼', '[67, 66]', '2023-06-01 00:00:00', '2023-06-30 00:00:00', 2, 60, NULL, 1, NULL, '拼团不成退款', 2, '已结束', 1, '2023-06-02 14:28:54', '2023-06-06 16:43:44', 1, 0, 0);
INSERT INTO `kz_store_combination` VALUES (2, '天天买', '[37, 65]', '2023-06-01 00:00:00', '2023-06-30 00:00:00', 2, 2, NULL, 1, '拼着买更实惠！', '1111', 2, '已结束', 1, '2023-06-02 14:50:24', '2023-06-09 14:48:14', 1, 0, 0);
-- ----------------------------
-- Table structure for kz_store_combination_log
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_combination_log`;
CREATE TABLE `kz_store_combination_log`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL DEFAULT 0 COMMENT '团id',
  `combination_id` int(11) NOT NULL COMMENT '拼团活动id',
  `order_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `activity_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动名称',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `price` decimal(10, 2) NOT NULL COMMENT '原价',
  `combination_price` decimal(10, 2) NOT NULL COMMENT '拼团加',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `limit_num` int(11) NULL DEFAULT NULL COMMENT '数量限制',
  `group_num` int(11) NULL DEFAULT NULL COMMENT '成团人数',
  `expired_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `is_virtual` tinyint(1) NULL DEFAULT 0 COMMENT '虚拟成团',
  `is_leader` tinyint(1) NULL DEFAULT 0 COMMENT '团长',
  `uid` int(11) NOT NULL COMMENT '用户',
  `status` int(11) NULL DEFAULT NULL COMMENT '拼团状态 0->进行中，1->成功，2->失败',
  `status_desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拼团状态描述',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_success` tinyint(1) NULL DEFAULT NULL COMMENT '是否成功',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城-拼团记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_combination_log
-- ----------------------------
INSERT INTO `kz_store_combination_log` VALUES (4, 4, 2, '202306070332434102537', '天天买', 37, '浙江特级白萝卜，生津止渴赛人参', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681375393826.png', 0.01, 0.01, '2023-06-01 00:00:00', '2023-06-30 00:00:00', NULL, 2, '2023-06-07 16:32:44', 0, 1, 868, 1, '成功', 868, '2023-06-07 15:32:44', '2023-06-07 15:32:44', 1, 1, 0, 0);
INSERT INTO `kz_store_combination_log` VALUES (5, 4, 2, '202306070333460545174', '天天买', 37, '浙江特级白萝卜，生津止渴赛人参', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681375393826.png', 0.01, 0.01, '2023-06-01 00:00:00', '2023-06-30 00:00:00', NULL, 2, '2023-06-07 16:32:44', 0, 0, 867, 1, '成功', 868, '2023-06-07 15:33:52', '2023-06-07 15:33:52', 1, 1, 0, 0);
INSERT INTO `kz_store_combination_log` VALUES (6, 6, 2, '202306070354431851325', '天天买', 37, '浙江特级白萝卜，生津止渴赛人参', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681371391192.png', 0.03, 0.03, '2023-06-01 00:00:00', '2023-06-30 00:00:00', NULL, 2, '2023-06-07 16:54:49', 0, 1, 867, 2, '失败', 0, '2023-06-07 15:54:49', '2023-06-07 15:54:49', 1, 0, 0, 0);
-- ----------------------------
-- Table structure for kz_store_comment
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_comment`;
CREATE TABLE `kz_store_comment`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单ID',
  `product_id` int(20) NULL DEFAULT NULL COMMENT '产品id',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `attr_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性名称',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品图片快照',
  `cart_num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品单价',
  `uid` int(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价',
  `level` tinyint(1) NULL DEFAULT NULL COMMENT '1好评，2中评，3差评',
  `product_level` tinyint(1) NULL DEFAULT NULL COMMENT '商品分数',
  `delivery_level` tinyint(1) NULL DEFAULT NULL COMMENT '物流分数',
  `service_level` tinyint(1) NULL DEFAULT NULL COMMENT '服务分数',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `append` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '追评',
  `img_urls` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论图片',
  `reply_content` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '管理员回复内容',
  `reply_time` datetime(0) NULL DEFAULT NULL COMMENT '管理员回复时间',
  `is_reply` tinyint(1) NULL DEFAULT 0 COMMENT '0未回复1已回复',
  `status` int(10) NULL DEFAULT 0 COMMENT '评价状态',
  `status_desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评价状态描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` int(255) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `add_time`(`create_time`) USING BTREE,
  INDEX `product_score`(`product_level`) USING BTREE,
  INDEX `service_score`(`service_level`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城商品评价表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_comment
-- ----------------------------
INSERT INTO `kz_store_comment` VALUES (24, '202305170546188109371', 36, '应季本园头茬上海青，富含维生素C', '默认', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681375393826.png', 1, 0.02, 868, 0.02, 1, 5, 5, 5, 'hhhhhhhhh', NULL, NULL, NULL, NULL, 0, 0, NULL, '2023-05-17 18:06:20', '2023-05-17 18:50:31', 868, 1, 0, 0);
INSERT INTO `kz_store_comment` VALUES (25, '202305170653349744603', 52, '五色糙米5斤低脂粗粮大米黑米主食饭新七色五谷杂粮孕妇三色健身', '默认', '//img.alicdn.com/imgextra/i4/2200608599252/O1CN014sHQVG2IDTEkYioMD_!!2200608599252-0-lubanu-s.jpg', 1, 0.03, 867, 0.03, 5, NULL, NULL, NULL, '很好感谢', NULL, '[\"https://kinzhan-im-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/168439711358153.png\"]', NULL, NULL, 0, 1, '已评价', '2023-05-17 18:55:33', '2023-05-18 16:05:21', 867, 1, 0, 0);
INSERT INTO `kz_store_comment` VALUES (26, '202305170653349744603', 53, '2022东北黏玉米棒黄糯苞米现摘真空包装新鲜甜玉米粒粘非即食粗粮', '默认', 'https://img.alicdn.com/imgextra/i2/2200608599252/O1CN012UGWOp2IDTDxE3ajW_!!2200608599252.jpg', 1, 0.02, 867, 0.02, 2, NULL, NULL, NULL, '好的', NULL, '[\"https://kinzhan-im-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/168439712008545.png\"]', NULL, NULL, 0, 1, '已评价', '2023-05-17 18:55:33', '2023-05-18 16:05:21', 867, 1, 0, 0);
INSERT INTO `kz_store_comment` VALUES (27, '202305180923569892059', 53, '2022东北黏玉米棒黄糯苞米现摘真空包装新鲜甜玉米粒粘非即食粗粮', '默认', 'https://img.alicdn.com/imgextra/i2/2200608599252/O1CN012UGWOp2IDTDxE3ajW_!!2200608599252.jpg', 1, 0.02, 868, 0.02, 5, NULL, NULL, NULL, '极地沙尘暴', NULL, '[\"https://kinzhan-im-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/1684377448111138.png\",\"https://kinzhan-im-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/1684377448122106.png\"]', '亲爱的，，非常感谢您对我们的厚爱与支持。您的满意是我们的动力，欢迎您再次光临，祝您生活愉快!', NULL, 1, 1, '已评价', '2023-05-18 09:26:24', '2023-05-18 12:06:23', 868, 1, 0, 0);

-- ----------------------------
-- Table structure for kz_store_coupon
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_coupon`;
CREATE TABLE `kz_store_coupon`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '优惠券id',
  `shop_id` int(11) NULL DEFAULT NULL COMMENT '店铺id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '优惠券名称',
  `coupon_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '优惠券类型，满减，折扣，类目，无门槛等',
  `total` int(11) NULL DEFAULT 0 COMMENT '总量',
  `used` int(11) NULL DEFAULT 0 COMMENT '已领取',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠券面值',
  `scope` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '使用场景',
  `min_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '最低消费',
  `discount` decimal(10, 2) NULL DEFAULT NULL COMMENT '折扣，请填入小数',
  `hold_limit` int(10) NULL DEFAULT NULL COMMENT '最大持有量',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始领取时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束领取时间',
  `use_start_time` datetime(0) NULL DEFAULT NULL COMMENT '可用时间',
  `use_end_time` datetime(0) NULL DEFAULT NULL COMMENT '可用结束时间',
  `expired_days` int(255) NULL DEFAULT NULL COMMENT '领取后过期时间',
  `is_show` tinyint(1) NULL DEFAULT 1 COMMENT '是否开放领取，1,0',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '优惠券使用说明',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态  0 未开放  1 正常 2 过期 3 抢光',
  `status_desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城优惠券表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_coupon
-- ----------------------------
INSERT INTO `kz_store_coupon` VALUES (1, NULL, '10元无门槛', 'ALL', 999, 14, 10.00, 'ALL', NULL, NULL, 10, '2023-05-08 00:00:00', '2023-05-31 00:00:00', '2023-05-08 00:00:00', '2023-05-31 00:00:00', NULL, 1, '(1) 优惠券仅限在有效期内使用，过期自动作废，不能延期使用；\n\n(2) 优惠券只可用于抵扣商品金额，不能抵扣运费，不设找零，不能提现；\n\n(3) 优惠券抵扣部分不提供发票，不计入用户的累计消费积分；\n\n(4) 优惠券金额大于订单金额，使用支付后差额不予退回，优惠券金额小于订单金额，需由用户支付差额；\n\n(5) 已领取的优惠券仅限会员本人使用，严禁出售或转让，如经发现并证实，该优惠券予以收回；\n\n(6) 线下获得优惠券后请在“我的帐户-优惠券”中输入优惠券的编号激活使用；\n\n(7) 一张订单仅限使用一张优惠券，不可拆分，且优惠券一经使用不予退还；\n\n(8) 如订单中使用优惠券且有多件商品，但只退其中几件，那么商品优惠分摊按商品价格比重系统自动计算，返还用户实际支付金额，优惠券不退;', 2, '正常', 1, '2023-05-08 09:12:39', '2023-05-11 10:22:01', 1, 0, 0, 18);
INSERT INTO `kz_store_coupon` VALUES (2, NULL, '品类优惠券', 'FULL', 1, 1, 6.00, 'CATEGORY', 10.00, NULL, 2, '2023-05-08 00:00:00', '2023-05-31 00:00:00', '2023-05-08 00:00:00', '2023-05-31 00:00:00', 4, 1, '(1) 优惠券仅限在有效期内使用，过期自动作废，不能延期使用；\n\n(2) 优惠券只可用于抵扣商品金额，不能抵扣运费，不设找零，不能提现；\n\n(3) 优惠券抵扣部分不提供发票，不计入用户的累计消费积分；\n\n(4) 优惠券金额大于订单金额，使用支付后差额不予退回，优惠券金额小于订单金额，需由用户支付差额；\n\n(5) 已领取的优惠券仅限会员本人使用，严禁出售或转让，如经发现并证实，该优惠券予以收回；\n\n(6) 线下获得优惠券后请在“我的帐户-优惠券”中输入优惠券的编号激活使用；\n\n(7) 一张订单仅限使用一张优惠券，不可拆分，且优惠券一经使用不予退还；\n\n(8) 如订单中使用优惠券且有多件商品，但只退其中几件，那么商品优惠分摊按商品价格比重系统自动计算，返还用户实际支付金额，优惠券不退;', 2, '正常', 1, '2023-05-08 10:34:04', '2023-05-09 11:57:36', 1, 0, 0, 3);
INSERT INTO `kz_store_coupon` VALUES (3, NULL, '商品优惠券', 'DISCOUNT', 3, 3, 0.00, 'CATEGORY', 50.00, 0.90, 3, '2023-05-08 00:00:00', '2023-05-31 00:00:00', '2023-05-08 00:00:00', '2023-05-31 00:00:00', 7, 1, '(1) 优惠券仅限在有效期内使用，过期自动作废，不能延期使用；\n\n(2) 优惠券只可用于抵扣商品金额，不能抵扣运费，不设找零，不能提现；\n\n(3) 优惠券抵扣部分不提供发票，不计入用户的累计消费积分；\n\n(4) 优惠券金额大于订单金额，使用支付后差额不予退回，优惠券金额小于订单金额，需由用户支付差额；\n\n(5) 已领取的优惠券仅限会员本人使用，严禁出售或转让，如经发现并证实，该优惠券予以收回；\n\n(6) 线下获得优惠券后请在“我的帐户-优惠券”中输入优惠券的编号激活使用；\n\n(7) 一张订单仅限使用一张优惠券，不可拆分，且优惠券一经使用不予退还；\n\n(8) 如订单中使用优惠券且有多件商品，但只退其中几件，那么商品优惠分摊按商品价格比重系统自动计算，返还用户实际支付金额，优惠券不退;', 2, '正常', 1, '2023-05-08 10:36:14', '2023-05-26 14:07:35', 1, 0, 0, 7);
INSERT INTO `kz_store_coupon` VALUES (4, NULL, 'cs优惠券', 'ALL', 4, 2, 4.00, 'PRODUCT', NULL, NULL, 3, '2023-05-08 00:00:00', '2023-05-31 00:00:00', '2023-05-08 00:00:00', '2023-05-31 00:00:00', 5, 0, '(1) 优惠券仅限在有效期内使用，过期自动作废，不能延期使用；\n\n(2) 优惠券只可用于抵扣商品金额，不能抵扣运费，不设找零，不能提现；\n\n(3) 优惠券抵扣部分不提供发票，不计入用户的累计消费积分；\n\n(4) 优惠券金额大于订单金额，使用支付后差额不予退回，优惠券金额小于订单金额，需由用户支付差额；\n\n(5) 已领取的优惠券仅限会员本人使用，严禁出售或转让，如经发现并证实，该优惠券予以收回；\n\n(6) 线下获得优惠券后请在“我的帐户-优惠券”中输入优惠券的编号激活使用；\n\n(7) 一张订单仅限使用一张优惠券，不可拆分，且优惠券一经使用不予退还；\n\n(8) 如订单中使用优惠券且有多件商品，但只退其中几件，那么商品优惠分摊按商品价格比重系统自动计算，返还用户实际支付金额，优惠券不退;', 2, '正常', 1, '2023-05-08 14:27:27', '2023-05-24 20:01:10', 1, 0, 0, 12);
INSERT INTO `kz_store_coupon` VALUES (5, NULL, '新人见面礼', 'ALL', 999, 23, 10.00, 'ALL', NULL, NULL, 1, '2023-06-01 00:00:00', '2024-01-01 00:00:00', '2023-06-01 00:00:00', '2024-01-01 00:00:00', 30, 1, '新人见面礼', 1, '正常', 1, '2023-06-02 15:07:48', '2023-06-06 15:41:05', 1, 0, 0, 23);
INSERT INTO `kz_store_coupon` VALUES (6, NULL, '618满减', 'FULL', 999, 22, 50.00, 'ALL', 300.00, NULL, 5, '2023-06-01 00:00:00', '2023-06-18 00:00:00', '2023-06-18 00:00:00', '2023-06-18 00:00:00', 30, 1, '618满减', 2, '正常', 1, '2023-06-02 15:08:51', '2023-06-09 23:25:33', 1, 0, 0, 22);
INSERT INTO `kz_store_coupon` VALUES (7, NULL, '618满减', 'FULL', 999, 1, 50.00, 'ALL', 300.00, NULL, 0, '2023-06-01 00:00:00', '2023-07-31 00:00:00', '2023-06-17 00:00:00', '2023-07-31 00:00:00', NULL, 1, '618满减', 1, '正常', 1, '2023-06-27 16:15:10', '2023-06-28 09:47:32', 1, 0, 0, 1);
INSERT INTO `kz_store_coupon` VALUES (8, NULL, '全场9折', 'DISCOUNT', 999, 2, NULL, 'ALL', 100.00, 0.90, 0, '2023-06-01 00:00:00', '2023-08-31 00:00:00', '2023-06-01 00:00:00', '2024-07-31 00:00:00', NULL, 1, '全场9折', 1, '正常', 1, '2023-06-27 16:16:13', '2023-07-20 11:24:12', 1, 0, 0, 2);

-- ----------------------------
-- Table structure for kz_store_coupon_category
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_coupon_category`;
CREATE TABLE `kz_store_coupon_category`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `coupon_id` int(11) NULL DEFAULT NULL COMMENT '优惠券id',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '分类id',
  `category_list` json NULL COMMENT '分类级联',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城-优惠券分类关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_coupon_category
-- ----------------------------
INSERT INTO `kz_store_coupon_category` VALUES (6, 3, 10, '[10]', 1, '2023-05-09 11:57:32', '2023-05-09 11:57:32', 1, 0, 0);
INSERT INTO `kz_store_coupon_category` VALUES (7, 2, 14, '[14]', 1, '2023-05-09 11:57:36', '2023-05-09 11:57:36', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_store_coupon_product
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_coupon_product`;
CREATE TABLE `kz_store_coupon_product`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `coupon_id` int(11) NULL DEFAULT NULL COMMENT '优惠券id',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 99 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城-优惠券商品关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_coupon_product
-- ----------------------------
INSERT INTO `kz_store_coupon_product` VALUES (1, 3, 37, 1, '2023-05-08 10:36:14', '2023-05-08 10:36:14', 1, 0, 0);
INSERT INTO `kz_store_coupon_product` VALUES (2, 3, 41, 1, '2023-05-08 10:36:14', '2023-05-08 10:36:14', 1, 0, 0);
INSERT INTO `kz_store_coupon_product` VALUES (3, 4, 41, 1, '2023-05-08 14:27:27', '2023-05-08 14:27:27', 1, 0, 0);
INSERT INTO `kz_store_coupon_product` VALUES (4, 4, 37, 1, '2023-05-08 14:27:27', '2023-05-08 14:27:27', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_store_express
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_express`;
CREATE TABLE `kz_store_express`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品服务ID',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '运费模板',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模板名称',
  `calc_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '计费方式，按件数 num，按重量weight，按体积volume',
  `address` json NULL COMMENT '地区',
  `num` decimal(10, 2) NULL DEFAULT NULL COMMENT '首件',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '运费',
  `extra_num` decimal(10, 2) NULL DEFAULT NULL COMMENT '续件',
  `extra_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '续件费',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '概述',
  `is_default` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '是否默认',
  `is_free` tinyint(1) NULL DEFAULT NULL COMMENT '开启满包邮',
  `full_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '满包邮金额',
  `is_delivery` tinyint(255) NULL DEFAULT 1 COMMENT '是否配送',
  `sort` int(11) UNSIGNED NULL DEFAULT 100 COMMENT '排序方式(数字越小越靠前)',
  `store_id` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '店铺id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` int(255) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT NULL COMMENT '启用',
  `is_delete` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '是否删除(1已删除)',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `store_id`(`store_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10009 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城运费模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_express
-- ----------------------------
INSERT INTO `kz_store_express` VALUES (10005, 0, '默认运费', 'num', '[\"全部\"]', 1.00, 6.00, 1.00, 3.00, '', 1, 1, 0.00, 1, 100, 0, '2023-05-31 16:23:36', '2023-06-07 09:47:54', 1, 1, 0, 0);
INSERT INTO `kz_store_express` VALUES (10008, 10005, '', NULL, '[\"广东省\", \"全部\"]', NULL, NULL, NULL, NULL, '', 0, NULL, NULL, 0, 100, 0, '2023-06-07 09:47:54', '2023-06-07 09:47:54', 1, 1, 0, 0);

-- ----------------------------
-- Table structure for kz_store_order
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_order`;
CREATE TABLE `kz_store_order`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商城订单id',
  `uid` int(11) NULL DEFAULT NULL,
  `scope` int(11) NULL DEFAULT 1 COMMENT '活动场景',
  `total_num` int(11) NULL DEFAULT NULL COMMENT '商品总量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单总价',
  `pay_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际支付金额',
  `coupon_id` int(11) NULL DEFAULT NULL COMMENT '优惠券',
  `coupon_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠券金额',
  `postage_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '邮费',
  `total_credit` decimal(10, 2) NULL DEFAULT NULL COMMENT '积分抵扣',
  `user_remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '用户备注',
  `seller_remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '卖家备注',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '门店id',
  `shipping_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '配送方式 快递 delivery ，门店自提 pickup',
  `extra_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '扩展信息',
  `address_info` json NULL COMMENT '收件信息',
  `delivery_info` json NULL COMMENT '快递信息',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `product_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '商品信息',
  `activity_info` json NULL COMMENT '活动信息',
  `is_paid` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '支付状态',
  `pay_time` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '支付时间',
  `pay_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '支付方式',
  `pay_order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单id',
  `verify_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '核销码',
  `verify_time` datetime(0) NULL DEFAULT NULL COMMENT '核销时间',
  `verify_uid` int(11) NULL DEFAULT NULL COMMENT '核销员',
  `first_uid` int(11) NULL DEFAULT 0 COMMENT '一级分销员',
  `second_uid` int(11) NULL DEFAULT 0 COMMENT '二级分销员',
  `first_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '一级返佣',
  `second_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '二级返佣',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `status_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `fee_status` int(1) NULL DEFAULT 0 COMMENT '返佣结算状态 0 未结算 1已结算 ',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `un_order`(`order_id`) USING BTREE COMMENT '唯一',
  UNIQUE INDEX `uni_verify_code`(`verify_code`) USING BTREE COMMENT '核销码唯一',
  INDEX `idx_order`(`order_id`) USING BTREE COMMENT '索引',
  INDEX `idx_uid`(`uid`) USING BTREE COMMENT '用户订单'
) ENGINE = InnoDB AUTO_INCREMENT = 169 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_order
-- ----------------------------
INSERT INTO `kz_store_order` VALUES (37, '202305170546188109371', 868, 1, 1, 0.02, 0.02, NULL, 0.00, 0.00, NULL, '', NULL, 0, 'DELIVERY', NULL, '{\"phone\": \"18368836191\", \"address\": [\"广东省\", \"广州市\", \"海珠区\"], \"nickName\": \"张三\", \"addressDetail\": \"新港中路397号\"}', '{\"orderId\": \"202305170546188109371\", \"deliveryId\": \"STO\", \"deliveryNo\": \"773221128519785\", \"deliveryCompany\": \"申通快递\"}', NULL, '应季本园头茬上海青，富含维生素C', NULL, 1, 0, 'WEIXIN', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, 7, '待评价', NULL, 868, '2023-05-17 17:46:19', '2023-05-17 17:47:49', 1, 0, 0);
INSERT INTO `kz_store_order` VALUES (38, '202305170653349744603', 867, 1, 2, 0.05, 0.05, NULL, 0.00, 0.00, NULL, '', NULL, 0, 'DELIVERY', NULL, '{\"phone\": \"13675175408\", \"address\": [\"浙江省\", \"杭州市\", \"滨江区\"], \"nickName\": \"丁晓松\", \"addressDetail\": \"铂金名筑3栋3单元1002室\"}', '{\"orderId\": \"202305170653349744603\", \"deliveryId\": \"ZTO\", \"deliveryNo\": \"78681269457779\", \"deliveryCompany\": \"中通快递\"}', NULL, '五色糙米5斤低脂粗粮大米黑米主食饭新七色五谷杂粮孕妇三色健身,2022东北黏玉米棒黄糯苞米现摘真空包装新鲜甜玉米粒粘非即食粗粮', NULL, 1, 0, 'WEIXIN', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, 8, '订单完成', NULL, 867, '2023-05-17 18:53:35', '2023-05-17 18:55:11', 1, 0, 0);
INSERT INTO `kz_store_order` VALUES (39, '202305180923569892059', 868, 1, 2, 0.04, 0.04, NULL, 0.00, 0.00, NULL, '', NULL, 0, 'DELIVERY', NULL, '{\"phone\": \"18368836191\", \"address\": [\"广东省\", \"广州市\", \"海珠区\"], \"nickName\": \"张三\", \"addressDetail\": \"新港中路397号\"}', '{\"orderId\": \"202305180923569892059\", \"deliveryId\": \"STO\", \"deliveryNo\": \"773221128519785\", \"deliveryCompany\": \"申通快递\"}', NULL, '2022东北黏玉米棒黄糯苞米现摘真空包装新鲜甜玉米粒粘非即食粗粮,应季本园头茬上海青，富含维生素C', NULL, 1, 0, 'WEIXIN', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, 8, '订单完成', NULL, 868, '2023-05-18 09:23:57', '2023-05-18 09:26:12', 1, 0, 0);
INSERT INTO `kz_store_order` VALUES (40, '202305180221553942124', 867, 1, 1, 0.02, 0.02, NULL, 0.00, 0.00, NULL, '', NULL, 0, 'DELIVERY', NULL, '{\"phone\": \"13675175408\", \"address\": [\"浙江省\", \"杭州市\", \"滨江区\"], \"nickName\": \"丁晓松\", \"addressDetail\": \"铂金名筑3栋3单元1002室\"}', NULL, NULL, '应季本园头茬上海青，富含维生素C', NULL, 1, 0, 'WEIXIN', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, 2, '待发货', NULL, 867, '2023-05-18 14:21:55', '2023-05-18 14:21:55', 1, 0, 0);
INSERT INTO `kz_store_order` VALUES (41, '202305180359176877143', 867, 1, 6, 0.12, 0.12, NULL, 0.00, 0.00, NULL, '', NULL, 0, 'DELIVERY', NULL, '{\"phone\": \"13675175408\", \"address\": [\"浙江省\", \"杭州市\", \"滨江区\"], \"nickName\": \"丁晓松\", \"addressDetail\": \"铂金名筑3栋3单元1002室\"}', NULL, NULL, '应季本园头茬上海青，富含维生素C', NULL, 1, 0, 'WEIXIN', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, 2, '待发货', NULL, 867, '2023-05-18 15:59:18', '2023-05-18 15:59:18', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_store_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_order_detail`;
CREATE TABLE `kz_store_order_detail`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `store_id` int(11) NULL DEFAULT NULL COMMENT '店铺id',
  `order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `product_id` int(11) NULL DEFAULT NULL,
  `product_detail_id` int(11) NULL DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称快照',
  `attr_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品属性快照',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品图片快照',
  `cart_num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `weight` decimal(10, 2) NULL DEFAULT NULL COMMENT '重量',
  `volume` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '体积',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品单价',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价',
  `credit` decimal(10, 2) NULL DEFAULT NULL COMMENT '所需积分',
  `total_credit` decimal(10, 2) NULL DEFAULT NULL COMMENT '总积分',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `status_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 206 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城订单详情，快照表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_order_detail
-- ----------------------------
INSERT INTO `kz_store_order_detail` VALUES (54, 868, NULL, '202305170546188109371', 36, 879, '应季本园头茬上海青，富含维生素C', '默认', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681375393826.png', 1, NULL, 0.00, 0.02, 0.02, NULL, NULL, NULL, NULL, 868, '2023-05-17 17:46:19', '2023-05-17 17:46:19', 1, 0, 0);
INSERT INTO `kz_store_order_detail` VALUES (55, 867, NULL, '202305170653349744603', 52, 922, '五色糙米5斤低脂粗粮大米黑米主食饭新七色五谷杂粮孕妇三色健身', '默认', '//img.alicdn.com/imgextra/i4/2200608599252/O1CN014sHQVG2IDTEkYioMD_!!2200608599252-0-lubanu-s.jpg', 1, NULL, 0.00, 0.03, 0.03, NULL, NULL, NULL, NULL, 867, '2023-05-17 18:53:35', '2023-05-17 18:53:35', 1, 0, 0);
INSERT INTO `kz_store_order_detail` VALUES (56, 867, NULL, '202305170653349744603', 53, 921, '2022东北黏玉米棒黄糯苞米现摘真空包装新鲜甜玉米粒粘非即食粗粮', '默认', 'https://img.alicdn.com/imgextra/i2/2200608599252/O1CN012UGWOp2IDTDxE3ajW_!!2200608599252.jpg', 1, NULL, 0.00, 0.02, 0.02, NULL, NULL, NULL, NULL, 867, '2023-05-17 18:53:35', '2023-05-17 18:53:35', 1, 0, 0);
INSERT INTO `kz_store_order_detail` VALUES (58, 868, NULL, '202305180923569892059', 36, 879, '应季本园头茬上海青，富含维生素C', '默认', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681375393826.png', 1, NULL, 0.00, 0.02, 0.02, NULL, NULL, NULL, NULL, 868, '2023-05-18 09:23:57', '2023-05-18 09:23:57', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_store_order_status
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_order_status`;
CREATE TABLE `kz_store_order_status`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `status` int(11) NULL DEFAULT NULL COMMENT '订单状态',
  `status_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单状态描述',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `tenant_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28527 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城订单状态记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_order_status
-- ----------------------------
INSERT INTO `kz_store_order_status` VALUES (16, '202305170546188109371', 1, '创建订单', '2023-05-17 17:46:19', 0);
INSERT INTO `kz_store_order_status` VALUES (17, '202305170546188109371', 2, '支付成功', '2023-05-17 17:46:40', 0);
INSERT INTO `kz_store_order_status` VALUES (18, '202305170546188109371', 3, '已发货', '2023-05-17 17:47:49', 0);
INSERT INTO `kz_store_order_status` VALUES (20, '202305170546188109371', 4, '确认收货', '2023-05-17 18:06:20', 0);
INSERT INTO `kz_store_order_status` VALUES (25, '202305180923569892059', 1, '创建订单', '2023-05-18 09:23:57', 0);
INSERT INTO `kz_store_order_status` VALUES (26, '202305180923569892059', 2, '支付成功', '2023-05-18 09:24:48', 0);
INSERT INTO `kz_store_order_status` VALUES (27, '202305180923569892059', 3, '已发货', '2023-05-18 09:26:12', 0);
INSERT INTO `kz_store_order_status` VALUES (28, '202305180923569892059', 4, '确认收货', '2023-05-18 09:26:24', 0);
INSERT INTO `kz_store_order_status` VALUES (31, '202305180923569892059', 7, '用户点评', '2023-05-18 10:37:40', 0);
INSERT INTO `kz_store_order_status` VALUES (32, '202305180923569892059', 8, '订单完成', '2023-05-18 10:37:40', 0);

-- ----------------------------
-- Table structure for kz_store_product
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_product`;
CREATE TABLE `kz_store_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NULL DEFAULT NULL COMMENT '分类id',
  `category_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '分类数组',
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '商户id',
  `store_id` int(11) NULL DEFAULT NULL COMMENT '店铺id',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品类型，如普通商品，虚拟商品',
  `product_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品简介',
  `prop_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格类型，如single, multiple',
  `internal_id` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自编码id',
  `bar_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '条形码',
  `keyword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键词',
  `tags` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `attrs` json NULL COMMENT '属性',
  `extra` json NULL COMMENT '其他参数信息',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品主图，建议128 * 128',
  `img_urls` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片列表',
  `video_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频链接',
  `unit_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格，售价',
  `cost_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '成本价',
  `original_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '划线价',
  `seckill_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '秒杀价, 取SKU最低',
  `combination_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '拼团，取SKU最低',
  `bargain_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '砍价，取SKU最低',
  `credit` decimal(10, 2) NULL DEFAULT NULL COMMENT '兑换所需积分',
  `credit_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '积分差价',
  `rebate_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '预计最高佣金',
  `rebate_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '返佣方式',
  `sales` int(11) NULL DEFAULT 0 COMMENT '销量',
  `stock_num` int(11) NULL DEFAULT 0 COMMENT '库存',
  `description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详细描述',
  `service_ids` json NULL COMMENT '商品服务',
  `shipping_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '快递，自提，同城配送',
  `shipping_template` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配送模板',
  `weight` decimal(8, 2) NULL DEFAULT 0.00 COMMENT '重量',
  `volume` decimal(8, 2) NULL DEFAULT 0.00 COMMENT '体积',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `visited` int(11) NULL DEFAULT 0 COMMENT '访问次数',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态，审核',
  `status_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `sell_status` json NULL COMMENT '推荐类型，热卖1，新品2，精品4，促销8',
  `activity` json NULL COMMENT '商品活动',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT NULL COMMENT '上架，下架',
  `is_delete` tinyint(2) NULL DEFAULT NULL,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  `version` int(255) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_product
-- ----------------------------
INSERT INTO `kz_store_product` VALUES (36, 11, '[9,11]', NULL, NULL, '应季本园头茬上海青，富含维生素C', 'product', '农副产品农副产品农副产品农副产品', 'single', NULL, NULL, NULL, NULL, NULL, NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352148044.png', '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352148044.png\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352148034.png\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681439748652.png\"]', NULL, '件', 0.02, 0.01, 0.03, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 97, 99834, '<p><img style=\'width:100%;height: auto;display:block;\' src=\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351879066.png\" alt=\"\" data-href=\"\" style=\"width: 100%;height: auto;\"/></p><p>上海青为一年或二年生<a href=\"https://baike.baidu.com/item/%E8%8D%89%E6%9C%AC/541696?fromModule=lemma_inlink\" target=\"_blank\">草本</a>，高25-70厘米，无毛，带粉霜；根粗，坚硬，常成纺锤形块根，顶端常有短根颈；基直立，有分枝。基生叶倒卵形或宽倒卵形，长20-30厘米， 坚实，深绿色，有光泽，基部渐狭成宽柄。全缘或有不显明圆齿或波状齿。中脉白色。宽达1.5厘米，有多条纵脉；叶柄长3-5厘米，有或无窄边；下部茎生叶和基生叶相似，基部渐狭成叶柄；上部茎生叶倒卵形或椭圆形，长3-7厘米，宽1-3.5厘米， 基部抱茎，宽展，两侧有垂耳，全缘，微带粉霜。</p><p>	<a href=\"https://baike.baidu.com/item/%E6%80%BB%E7%8A%B6%E8%8A%B1%E5%BA%8F/795476?fromModule=lemma_inlink\" target=\"_blank\">总状花序</a>顶生，呈圆锥状；花浅黄色，长约1厘米，授粉后长达1.5厘米；花梗细,和花等长或较短；萼片长圆形，长3-4毫米， 直立开展，白色或黄色；花瓣长圆形，长约5毫米，顶端圆钝，有脉纹，具宽爪。长角果线形，长2-6厘米， 宽3-4毫米， 坚硬，无毛，果瓣有明显中脉及网结侧脉；喙顶端细，基部宽，长8-12毫米；果梗长8-30毫米。种子球形，直径1-1.5毫米， 紫褐色，有蜂窝纹。花期4月，果期5月。</p><p><br></p>', NULL, NULL, NULL, 1.00, 1.00, 3, 135, NULL, NULL, NULL, NULL, '2023-04-11 17:56:46', '2023-06-25 16:10:34', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (37, 9, '[9]', NULL, NULL, '浙江特级白萝卜，生津止渴赛人参', 'product', '新鲜萝卜', 'multiple', NULL, NULL, NULL, NULL, '[{\"attr\": \"规格\", \"values\": [\"100g\", \"500g\"]}, {\"attr\": \"颜色\", \"values\": [\"青色\", \"绿色\", \"靛青色\", \"浅绿色\"]}]', NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681375393826.png', '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681375393826.png\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681375393816.png\"]', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/yxjwAPP/upload1683880011000.mp4', '公斤', 90.00, 3.00, 6.00, 0.01, 0.01, 10.00, 70.00, NULL, NULL, NULL, 19, 7950, '<p><img style=\'max-width:100%\' src=\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681375370298.png\"></p><p><img style=\'max-width:100%\' src=\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681375382004.png\"></p><p>赛人参的萝卜</p>', NULL, NULL, NULL, 0.00, 0.00, 3, 311, NULL, NULL, '[1, 2, 4]', '[\"SECKILL\", \"COMBINATION\", \"BARGAIN\"]', '2023-04-13 16:43:37', '2023-06-25 16:11:08', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (41, 11, '[9,11]', NULL, NULL, '山东特级有机大白菜，营养美味富含维生素', 'product', '我是好吃的大白菜', 'multiple', NULL, NULL, NULL, NULL, '[{\"attr\": \"重量\", \"values\": [\"200g\", \"1000g\"]}, {\"attr\": \"颜色\", \"values\": [\"白色\", \"青色\"]}, {\"attr\": \"1\", \"values\": [\"2\", \"3\"]}]', NULL, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351634059.png', '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352148044.png\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352148034.png\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351704244.png\"]', NULL, 'g', 1.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 99, '<p><br></p>', NULL, NULL, NULL, 0.00, 0.00, 4, 0, NULL, NULL, '[1, 2]', NULL, '2023-04-19 10:41:53', '2023-05-16 14:22:17', 1, 0, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (52, 10, '[10]', NULL, NULL, '五色糙米5斤低脂粗粮大米黑米主食饭新七色五谷杂粮孕妇三色健身', 'product', '五色糙米5斤低脂粗粮大米黑米主食饭新七色五谷杂粮孕妇三色健身', 'single', NULL, NULL, NULL, NULL, NULL, NULL, '//img.alicdn.com/imgextra/i4/2200608599252/O1CN014sHQVG2IDTEkYioMD_!!2200608599252-0-lubanu-s.jpg', '[\"https://img.alicdn.com/imgextra/i4/2200608599252/O1CN0164ry6m2IDTEgj3887_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i4/2200608599252/O1CN01MeAHZ82IDTBKe7MeM_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i1/2200608599252/O1CN01jytzxb2IDTCI3JoPD_!!2200608599252.png\",\"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN01iPqeqY2IDTATVsFm4_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i4/2200608599252/O1CN018dQZ7y2IDT8ympnw4_!!2200608599252.jpg\"]', NULL, NULL, 0.03, 0.03, 0.03, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 86, '<p><img style=\'max-width:100%\' src=\"http://img.alicdn.com/imgextra/i4/2200608599252/O1CN01mKBN4T2IDT9kmRxX7_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%\' src=\"http://img.alicdn.com/imgextra/i4/2200608599252/O1CN01yMFeO82IDT9k657jP_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%\' src=\"http://img.alicdn.com/imgextra/i4/2200608599252/O1CN01aS7Mu42IDT9lry7q6_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%\' src=\"http://img.alicdn.com/imgextra/i3/2200608599252/O1CN01MrJ3712IDT9k0wZCM_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%\' src=\"http://img.alicdn.com/imgextra/i2/2200608599252/O1CN01FgIaY82IDT9lrwiXq_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%\' src=\"http://img.alicdn.com/imgextra/i2/2200608599252/O1CN01xvwn6e2IDT9gxJqvM_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%\' src=\"http://img.alicdn.com/imgextra/i4/2200608599252/O1CN01wha4md2IDTDwYfWoc_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%\' src=\"http://img.alicdn.com/imgextra/i4/2200608599252/O1CN01OwmwO82IDTDvBnFwZ_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%\' src=\"http://img.alicdn.com/imgextra/i1/2200608599252/O1CN0195sqLO2IDT9rN5h2f_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%\' src=\"http://img.alicdn.com/imgextra/i3/2200608599252/O1CN01FxoRDx2IDT9kmPTfJ_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%\' src=\"http://img.alicdn.com/imgextra/i3/2200608599252/O1CN01lhclbt2IDT9CW3TgW_!!2200608599252.jpg\" align=\"absmiddle\" /> </p><img style=\'max-width:100%\' src=\"https://www.o0b.cn/i.php?t.png&rid=gw-1.64630926c411c&p=2105076281&k=75408&t=1684212009\" style=\"display:none\" />11111111111111111111111111111111', NULL, NULL, NULL, 0.00, 0.00, 0, 17, NULL, NULL, NULL, NULL, '2023-05-16 12:40:08', '2023-06-25 16:29:22', 0, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (53, 9, '[9]', NULL, NULL, '2022东北黏玉米棒黄糯苞米现摘真空包装新鲜甜玉米粒粘非即食粗粮', 'product', '即食粗粮', 'single', NULL, NULL, NULL, NULL, NULL, NULL, 'https://img.alicdn.com/imgextra/i2/2200608599252/O1CN012UGWOp2IDTDxE3ajW_!!2200608599252.jpg', '[\"https://img.alicdn.com/imgextra/i2/2200608599252/O1CN012UGWOp2IDTDxE3ajW_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i1/2200608599252/O1CN01JDdwMY2IDTE5iug51_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i1/2200608599252/O1CN01HivvfS2IDTAvRiVSk_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i4/2200608599252/O1CN01LKMEbN2IDTECnIGjA_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i1/2200608599252/O1CN01JrIKxh2IDTE78WjOv_!!2200608599252.jpg\"]', NULL, NULL, 0.02, 0.01, 0.01, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 26, 9999961, '<p><img style=\'width:100%;height: auto;display:block;\' src=\"http://img.alicdn.com/imgextra/i1/2200608599252/O1CN01I5bJRx2IDTAjmpLks_!!2200608599252.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;height: auto;\"/><img style=\'width:100%;height: auto;display:block;\' src=\"http://img.alicdn.com/imgextra/i3/2200608599252/O1CN01ADUKu22IDTAvvQhDb_!!2200608599252.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;height: auto;\"/><img style=\'width:100%;height: auto;display:block;\' src=\"http://img.alicdn.com/imgextra/i3/2200608599252/O1CN013DYTZc2IDTAvRn09h_!!2200608599252.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;height: auto;\"/><img style=\'width:100%;height: auto;display:block;\' src=\"http://img.alicdn.com/imgextra/i4/2200608599252/O1CN01sRYHHQ2IDTAmsPReh_!!2200608599252.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;height: auto;\"/><img style=\'width:100%;height: auto;display:block;\' src=\"http://img.alicdn.com/imgextra/i3/2200608599252/O1CN01R8hOSP2IDTAphtahc_!!2200608599252.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;height: auto;\"/><img style=\'width:100%;height: auto;display:block;\' src=\"http://img.alicdn.com/imgextra/i1/2200608599252/O1CN01LyI3Pn2IDTAphvwDu_!!2200608599252.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;height: auto;\"/><img style=\'width:100%;height: auto;display:block;\' src=\"http://img.alicdn.com/imgextra/i2/2200608599252/O1CN01LsjZqG2IDTAphv8Kl_!!2200608599252.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;height: auto;\"/> </p>', NULL, NULL, NULL, 0.00, 0.00, 0, 70, NULL, NULL, NULL, NULL, '2023-05-16 14:29:02', '2023-06-25 16:25:17', 0, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (54, 11, '[9,11]', NULL, NULL, '血桃5斤水果新鲜现摘现发红心桃子当季毛桃整箱脆桃应季水蜜桃子', 'product', '血桃', 'single', NULL, NULL, NULL, NULL, NULL, NULL, 'https://img.alicdn.com/imgextra/i1/444189831/O1CN01JT1iSy2MUeYDu7bvK_!!444189831.jpg', '[\"https://img.alicdn.com/imgextra/i1/444189831/O1CN01JT1iSy2MUeYDu7bvK_!!444189831.jpg\",\"https://img.alicdn.com/imgextra/i3/444189831/O1CN01ZWPYzR2MUeYD9XUIa_!!444189831.jpg\",\"https://img.alicdn.com/imgextra/i1/444189831/O1CN01KYpN5S2MUeYKG9yUo_!!444189831.jpg\",\"https://img.alicdn.com/imgextra/i3/444189831/O1CN01Urag172MUeYKGAF8m_!!444189831.jpg\",\"https://img.alicdn.com/imgextra/i1/444189831/O1CN0100M9fs2MUeYFzXidB_!!444189831.jpg\"]', NULL, NULL, 14.90, NULL, 15.80, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 50, 49, '<div >\n   <div >\n    <img  src=\"http://img.alicdn.com/imgextra/i4/444189831/O1CN018gzgEK2MUeYGI2HxR_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i4/444189831/O1CN01MPZl8T2MUeYIwILFX_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i4/444189831/O1CN01wn17pg2MUeYBquakt_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i1/444189831/O1CN016i14Ti2MUeYHm4CoD_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i1/444189831/O1CN01IELfAZ2MUeYMyw5b1_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i1/444189831/O1CN01tZMvQ52MUeYGLgtPP_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i1/444189831/O1CN01G8CNXp2MUeYsofc5O_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i2/444189831/O1CN01fXvFb82MUeYlVxR6p_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i2/444189831/O1CN015fhugz2MUeYLJO9Z2_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i4/444189831/O1CN01fex7k52MUeYGLkFKR_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i3/444189831/O1CN01JhfePj2MUeYFyHVIX_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i1/444189831/O1CN01mnO3As2MUeYGLgcoS_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i2/444189831/O1CN01LwPSEE2MUeYFzMJ72_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i3/444189831/O1CN01IzFCFs2MUeYDu1pvQ_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i3/444189831/O1CN01opORzt2MUeYLqn7bg_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i3/444189831/O1CN01TItWMY2MUeYGLiyLI_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i4/444189831/O1CN01oyu6lc2MUeY7AbBkP_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i2/444189831/O1CN01PwsYyO2MUeYMywDwm_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i4/444189831/O1CN01BIKQcU2MUeYMyvt9k_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i4/444189831/O1CN01DcM51K2MUeYD9Sv5R_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i2/444189831/O1CN01VVy7RF2MUeYLJOchQ_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i3/444189831/O1CN01PRx4L92MUeYGLiq2K_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i3/444189831/O1CN01oDjqf32MUeYIwK5RV_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i3/444189831/O1CN01G0v1tE2MUeYHm4LBB_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i3/444189831/O1CN0131G0kv2MUeYNoans0_!!444189831.jpg\" />\n    <img  src=\"http://img.alicdn.com/imgextra/i1/444189831/O1CN01eA4eXY2MUeYJgFf6c_!!444189831.jpg\" />\n   </div>\n  </div><img style=\'max-width:100%;vertical-align: middle;\' src=\"https://www.o0b.cn/i.php?t.png&rid=gw-1.6463275e0036f&p=2105076281&k=75408&t=1684219746\" style=\"display:none\" />', NULL, NULL, NULL, 0.00, 0.00, 0, 34, NULL, NULL, NULL, NULL, '2023-05-16 14:49:05', '2023-07-11 14:27:43', 0, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (55, 10, '[10]', NULL, NULL, '海南妃子笑荔枝5斤新鲜水果当季整箱包邮王白糖现摘桂婴荔枝肉3味', 'product', '荔枝肉', 'single', NULL, NULL, NULL, NULL, NULL, NULL, '//img.alicdn.com/imgextra/i3/3015214310/O1CN01Lfz9HN1hi1s6fmjrW_!!0-item_pic.jpg', '[\"https://img.alicdn.com/imgextra/i3/3015214310/O1CN01bQYOgB1hi1sF40Pky_!!3015214310.jpg\",\"https://img.alicdn.com/imgextra/i1/3015214310/O1CN012Bgc3o1hi1s3lvaS1_!!3015214310.jpg\",\"https://img.alicdn.com/imgextra/i2/3015214310/O1CN012OFCXe1hi1s8pznlu_!!3015214310.jpg\",\"https://img.alicdn.com/imgextra/i2/3015214310/O1CN01eKDgMA1hi1sA8isbh_!!3015214310.jpg\",\"https://img.alicdn.com/imgextra/i2/3015214310/O1CN01abBa5v1hi1s5prRx5_!!3015214310.jpg\"]', NULL, NULL, 34.80, NULL, 179.80, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 93, '<p><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i1/3015214310/O1CN01LQCbD41hi1s7IXlY4_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i4/3015214310/O1CN01v1vNgs1hi1s6EJkqT_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i1/3015214310/O1CN01S2Imfp1hi1s7fc4Ds_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i4/3015214310/O1CN011d3cCI1hi1s1igxM5_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i1/3015214310/O1CN0136PvVG1hi1s6EIPhk_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i3/3015214310/O1CN01WXDHXX1hi1s9Y4e6A_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i2/3015214310/O1CN01U32F2Q1hi1sBkSlj1_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i3/3015214310/O1CN01i4WwoP1hi1s7fcbV4_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i2/3015214310/O1CN0196ivyA1hi1sCsqErk_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i3/3015214310/O1CN01ny9JGC1hi1s31Ogf2_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i2/3015214310/O1CN01gmgoq71hi1sBkTqE4_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i2/3015214310/O1CN01U0lQpX1hi1s31PpO0_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i4/3015214310/O1CN013yh94N1hi1s7IYIpN_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i3/3015214310/O1CN01ZDoY3q1hi1s6EKYmY_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i3/3015214310/O1CN01Dju7vD1hi1siko25H_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i1/3015214310/O1CN0174Iqc71hi1s7IYlxF_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i2/3015214310/O1CN012xzLDB1hi1s0OqkT3_!!3015214310.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/><img style=\'width:100%;vertical-align: middle;\' src=\"https://www.o0b.cn/i.php?t.png&rid=gw-4.6461d3382d71f&p=2032699483&k=53597&t=1684132666\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/></p>', NULL, NULL, NULL, 0.00, 0.00, 0, 2, NULL, NULL, NULL, NULL, '2023-05-16 14:55:46', '2023-06-25 16:27:36', 0, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (56, 19, '[19]', NULL, NULL, '四川汶川青脆李脱骨李冰糖李脆甜土李子孕妇新鲜水果5斤包邮', 'product', '四川汶川', 'single', NULL, NULL, NULL, NULL, NULL, NULL, '//img.alicdn.com/imgextra/i4/877425650/O1CN01bEBi0y1rbkW3W25t1_!!877425650.jpg', '[\"//img.alicdn.com/imgextra/i4/877425650/O1CN01bEBi0y1rbkW3W25t1_!!877425650.jpg\",\"//img.alicdn.com/imgextra/i2/877425650/O1CN01ZvYfCd1rbkbrqKWG1_!!877425650.jpg\",\"//img.alicdn.com/imgextra/i1/877425650/O1CN01tvO7Km1rbkWQMPTpx_!!877425650.jpg\",\"//img.alicdn.com/imgextra/i3/877425650/O1CN010yLJVw1rbkWCtERZn_!!877425650.jpg\",\"//img.alicdn.com/imgextra/i4/877425650/O1CN01c5POT21rbkVx95pWj_!!877425650.jpg\"]', NULL, NULL, 26.80, NULL, 62.80, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 98, '', NULL, NULL, NULL, 0.00, 0.00, 0, 2, NULL, NULL, NULL, NULL, '2023-05-16 16:42:17', '2023-06-25 11:52:07', 0, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (57, 19, '[19]', NULL, NULL, '四川不知火丑橘10斤新鲜水果整箱应当季丑八怪粑粑桔子柑橘子耙耙', 'product', '丑橘', 'single', NULL, NULL, NULL, NULL, NULL, NULL, '//img.alicdn.com/imgextra/i1/2939922051/O1CN01HOEgIy1R1P9KpBKRr_!!0-item_pic.jpg', '[\"https://img.alicdn.com/imgextra/i4/2939922051/O1CN011b1K7y1R1P8a59RtY_!!2939922051.jpg\",\"https://img.alicdn.com/imgextra/i2/2939922051/O1CN01Thoz4w1R1P8XA1XRF_!!2939922051.jpg\",\"https://img.alicdn.com/imgextra/i1/2939922051/O1CN0186cPt91R1P8bgfJI3_!!2939922051.jpg\",\"https://img.alicdn.com/imgextra/i1/2939922051/O1CN011FAAPa1R1P8hi83B2_!!2939922051.jpg\",\"https://img.alicdn.com/imgextra/i4/2939922051/O1CN01Tdxmys1R1PA9zRvGU_!!2939922051.jpg\"]', NULL, NULL, 27.80, NULL, 124.00, 1.00, NULL, NULL, NULL, NULL, NULL, NULL, 0, 93, '', NULL, NULL, NULL, 0.00, 0.00, 0, 12, NULL, NULL, NULL, '[\"SECKILL\"]', '2023-05-16 16:44:05', '2023-06-26 14:51:39', 0, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (58, 19, '[19]', NULL, NULL, '圣女果小黄瓜青瓜樱桃小番茄新鲜西红柿子千禧水果生吃自然熟5斤', 'product', '圣女果', 'single', NULL, NULL, NULL, NULL, NULL, NULL, '//img.alicdn.com/imgextra/i1/2200772182649/O1CN01TeXg9z1VRHsiBd0gd_!!0-item_pic.jpg', '[\"//img.alicdn.com/imgextra/i1/2200772182649/O1CN01TeXg9z1VRHsiBd0gd_!!0-item_pic.jpg\",\"//img.alicdn.com/imgextra/i1/2200772182649/O1CN01akVnFf1VRHgemswUk_!!2200772182649.jpg\",\"//img.alicdn.com/imgextra/i2/2200772182649/O1CN0174xLAJ1VRHgh6O5aX_!!2200772182649.jpg\",\"//img.alicdn.com/imgextra/i3/2200772182649/O1CN01VDOIpD1VRHgfwZmra_!!2200772182649.jpg\",\"//img.alicdn.com/imgextra/i2/2200772182649/O1CN016NURaZ1VRHjbbjKBm_!!2200772182649.jpg\"]', NULL, NULL, 19.80, NULL, 159.00, 1.00, NULL, NULL, NULL, NULL, NULL, NULL, 0, 98, '', NULL, NULL, NULL, 0.00, 0.00, 0, 7, NULL, NULL, NULL, '[\"SECKILL\"]', '2023-05-16 16:45:43', '2023-06-02 16:04:52', 0, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (59, 19, '[19]', NULL, NULL, '阳光玫瑰葡萄3/5斤 新鲜水果提子无籽 晴王无籽香印葡萄 青提当季', 'product', NULL, 'single', NULL, NULL, NULL, NULL, NULL, NULL, '//img.alicdn.com/imgextra/i2/2215068608487/O1CN01EVw9HU2CZ6FPngl3N_!!2215068608487.jpg', '[\"https://img.alicdn.com/imgextra/i2/2215068608487/O1CN01RpIdV72CZ6FVWl9xB_!!2215068608487.jpg\",\"https://img.alicdn.com/imgextra/i1/2215068608487/O1CN01Wunicr2CZ6DCWdltm_!!2215068608487.jpg\",\"https://img.alicdn.com/imgextra/i1/2215068608487/O1CN0107Kxh82CZ6DHsidAl_!!2215068608487.jpg\",\"https://img.alicdn.com/imgextra/i1/2215068608487/O1CN01zce03G2CZ6D72MAN8_!!2215068608487.jpg\",\"https://img.alicdn.com/imgextra/i2/2215068608487/O1CN01hpg6yZ2CZ6DH13a1u_!!2215068608487.jpg\"]', NULL, NULL, 59.90, NULL, 69.90, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 97, '', NULL, NULL, NULL, 0.00, 0.00, 0, 6, NULL, NULL, NULL, NULL, '2023-05-16 16:46:59', '2023-05-25 16:58:24', 0, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (60, 14, '[14]', NULL, NULL, '洋河海之蓝42度375ml单瓶装绵柔型口感浓香型白酒酒水礼品', 'product', NULL, 'single', NULL, NULL, NULL, NULL, NULL, NULL, '//img.alicdn.com/imgextra/i4/6000000008057/O1CN01AxTMI929O9wTxNAiL_!!6000000008057-0-sm.jpg', '[\"//img.alicdn.com/imgextra/i4/6000000008057/O1CN01AxTMI929O9wTxNAiL_!!6000000008057-0-sm.jpg\",\"//img.alicdn.com/imgextra/i4/725677994/O1CN01uviahn28vIps707ka_!!725677994-0-sm.jpg\",\"//img.alicdn.com/imgextra/i3/725677994/O1CN01y77iVO28vIq1U6lzv_!!725677994.jpg\",\"//img.alicdn.com/imgextra/i1/725677994/O1CN01theWtQ28vIprWv05w_!!725677994.jpg\",\"//img.alicdn.com/imgextra/i1/725677994/O1CN01S8u09j28vIq0nEvBu_!!725677994.jpg\"]', NULL, NULL, 146.00, NULL, 146.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 98, '', NULL, NULL, NULL, 0.00, 0.00, 0, 2, NULL, NULL, NULL, NULL, '2023-05-16 16:51:01', '2023-05-30 11:55:15', 0, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (61, 14, '[14]', NULL, NULL, '洋河海之蓝52度礼盒酒480ml*2瓶礼盒浓香型白酒酒水口感绵柔', 'product', NULL, 'single', NULL, NULL, NULL, NULL, NULL, NULL, '//img.alicdn.com/imgextra/i4/6000000007092/O1CN0121ly4f22GBjo2KmcY_!!6000000007092-0-sm.jpg', '[\"https://img.alicdn.com/imgextra/i4/6000000007092/O1CN0121ly4f22GBjo2KmcY_!!6000000007092-0-sm.jpg\",\"https://img.alicdn.com/imgextra/i2/6000000007415/O1CN016zbOjk24e7QYbhpb3_!!6000000007415-0-at.jpg\",\"https://img.alicdn.com/imgextra/i4/725677994/O1CN013nbrQE28vIjY6B1JG_!!725677994.jpg\",\"https://img.alicdn.com/imgextra/i3/725677994/O1CN01ufGsGR28vIjWTyVOE_!!725677994.jpg\",\"https://img.alicdn.com/imgextra/i4/725677994/O1CN01uhg02G28vIjZYWpd7_!!725677994.jpg\"]', NULL, NULL, 420.00, NULL, 450.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 17, '', '[10004, 10003]', NULL, NULL, 0.00, 0.00, 0, 21, NULL, NULL, '[2]', NULL, '2023-05-16 16:51:20', '2023-06-25 16:52:27', 0, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (62, 14, '[14]', NULL, NULL, '洋河礼盒梦之蓝M6-52度500ml*2瓶口感绵柔浓香型酒水礼盒装送礼', 'product', NULL, 'single', NULL, NULL, NULL, NULL, NULL, NULL, 'https://img.alicdn.com/imgextra/i1/6000000007041/O1CN01Riim5r21spUadRSy9_!!6000000007041-0-picassoopen.jpg', '[\"https://img.alicdn.com/imgextra/i1/6000000007041/O1CN01Riim5r21spUadRSy9_!!6000000007041-0-picassoopen.jpg\",\"https://img.alicdn.com/imgextra/i1/725677994/O1CN01fD4rKk28vIq1i4ahw_!!725677994-0-sm.jpg\",\"https://img.alicdn.com/imgextra/i4/725677994/O1CN01zdeLRT28vIpxs0LAx_!!725677994.jpg\",\"https://img.alicdn.com/imgextra/i4/725677994/O1CN01A994Mz28vIptwzkyI_!!725677994.jpg\",\"https://img.alicdn.com/imgextra/i2/725677994/O1CN01rwuy7128vIptwy0qd_!!725677994.jpg\"]', NULL, NULL, 1696.00, NULL, 2035.00, 1.00, NULL, NULL, NULL, NULL, NULL, NULL, 1, 75, '', NULL, NULL, NULL, 1.00, 1.00, 0, 19, NULL, NULL, NULL, '[\"SECKILL\"]', '2023-05-16 16:51:43', '2023-06-25 18:01:06', 0, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (63, 20, '[20]', NULL, NULL, '东北黑木耳干货500g新货小碗耳特产正宗黑龙江无根非秋鼠野生特级', 'product', NULL, 'single', NULL, NULL, NULL, NULL, NULL, NULL, '//img.alicdn.com/imgextra/i4/2200608599252/O1CN01E9bTGj2IDTEQm4EA2_!!2200608599252-0-lubanu-s.jpg', '[\"https://img.alicdn.com/imgextra/i2/2200608599252/O1CN0118UBt72IDTAhlRnbp_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN011ZkJjr2IDTEVBLDfM_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i1/2200608599252/O1CN01WDNx392IDTET1AelS_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i4/2200608599252/O1CN01okI7Ru2IDTEGXLm1O_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i1/2200608599252/O1CN01jAiJry2IDTEPjRRTq_!!2200608599252.jpg\"]', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/yxjwAPP/upload1687918367000.mp4', NULL, 48.80, NULL, 100.00, NULL, NULL, NULL, 10.00, NULL, NULL, NULL, 0, 91, '<p><img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i1/2200608599252/O1CN01rQZqwT2IDSwet8F27_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i3/2200608599252/O1CN01iNaLYy2IDSwh5jzlF_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i4/2200608599252/O1CN017z288J2IDSwj7Xl3m_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i3/2200608599252/O1CN017JDSDW2IDSwh5lGuy_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i2/2200608599252/O1CN01l5th9C2IDT9Of6fSh_!!2200608599252.jpg\" align=\"absmiddle\" /> </p> \n  <p ><br /> <img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i4/T2s4moXH8XXXXXXXXX-350475995.png?p=hb_v3_client_1012452_start_top_1\" /></p> \n  <p ><img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i4/T2s4moXH8XXXXXXXXX-350475995.png?p=hb_v3_client_1012452_end_top_1\" /></p><img style=\'max-width:100%;vertical-align: middle;\' src=\"https://www.o0b.cn/i.php?t.png&rid=gw-1.64634630ec4e2&p=2105076281&k=75408&t=1684227635\" style=\"display:none\" />', NULL, NULL, NULL, 0.00, 0.00, 0, 24, NULL, NULL, NULL, '[\"CREDIT\"]', '2023-05-16 17:00:34', '2023-07-11 14:49:56', 0, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (64, 20, '[20]', NULL, NULL, '东北红衣生花生米500g黑龙江四粒红花生仁新鲜红皮不带壳生的新货', 'product', NULL, 'single', NULL, NULL, NULL, NULL, NULL, NULL, '//img.alicdn.com/imgextra/i2/2200608599252/O1CN01Mi5IE72IDTDSZx6dr_!!2200608599252-0-lubanu-s.jpg', '[\"https://img.alicdn.com/imgextra/i4/2200608599252/O1CN01dRJIDo2IDTDh2K893_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i4/2200608599252/O1CN01Pyk1Ek2IDTE1aR18j_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN01mLVsOm2IDTE0H4TA5_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN018diUYA2IDTEBXMXRt_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i4/2200608599252/O1CN018fU2uj2IDTE88wZFH_!!2200608599252.jpg\"]', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/yxjwAPP/upload1687918349000.mp4', NULL, 10.90, NULL, 49.80, 1.00, NULL, NULL, 5.00, NULL, NULL, NULL, 1, 97, '<p><br></p>', NULL, NULL, NULL, 0.00, 0.00, 0, 21, NULL, NULL, NULL, '[\"SECKILL\", \"CREDIT\"]', '2023-05-16 17:01:12', '2023-06-28 10:12:32', 0, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (65, 20, '[20]', NULL, NULL, '东北黑米5斤大米新货黑龙江农家紫米香米五谷杂粮粥糯米五常', 'product', NULL, 'single', NULL, NULL, NULL, NULL, NULL, NULL, '//img.alicdn.com/imgextra/i1/2200608599252/O1CN01t8dUaB2IDTBzdnqYT_!!2200608599252-0-lubanu-s.jpg', '[\"https://img.alicdn.com/imgextra/i2/2200608599252/O1CN01eawdys2IDTBy1yzRG_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i2/2200608599252/O1CN01B2cUJq2IDTE2M4fLt_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i2/2200608599252/O1CN014bf9js2IDTC8r3AjR_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i4/2200608599252/O1CN01OIjRpb2IDTCBOfvvM_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN019O3yqZ2IDTE7Qrj9y_!!2200608599252.jpg\"]', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/yxjwAPP/upload1687918321000.mp4', NULL, 18.80, NULL, 30.00, 1.00, 2.00, NULL, 600.00, NULL, NULL, NULL, 0, 96, '<p ><img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i3/2200608599252/O1CN01tmZVk12IDSwhdXzp9_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i1/2200608599252/O1CN01HTBzTH2IDSwge2Dac_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i1/2200608599252/O1CN01vDQ34g2IDSwiR2n8C_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i2/2200608599252/O1CN017mU8x62IDSwlmSPM8_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i4/2200608599252/O1CN01UGYCRA2IDSwkxCj9h_!!2200608599252.jpg\" align=\"absmiddle\" /> </p> \n  <p>&nbsp;<img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i4/2200608599252/O1CN01GKe6au2IDT9qHslDk_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i3/2200608599252/O1CN01EkzPkR2IDT9vf19Ib_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i1/2200608599252/O1CN01n8u4nV2IDT9nDaQDS_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i1/2200608599252/O1CN01uGjtpD2IDT9xfDjis_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i2/2200608599252/O1CN011RFOzf2IDT9saGCyS_!!2200608599252.jpg\" align=\"absmiddle\" /><img style=\'max-width:100%;vertical-align: middle;\' src=\"http://img.alicdn.com/imgextra/i3/2200608599252/O1CN013EZv5b2IDT9zhoYcz_!!2200608599252.jpg\" align=\"absmiddle\" /></p> \n  <p>&nbsp;</p> \n  <p>&nbsp;</p><img style=\'max-width:100%;vertical-align: middle;\' src=\"https://www.o0b.cn/i.php?t.png&rid=gw-4.646346e18b0d9&p=2105076281&k=75408&t=1684227811\" style=\"display:none\" />', NULL, NULL, NULL, 0.00, 0.00, 0, 36, NULL, NULL, NULL, '[\"SECKILL\", \"CREDIT\", \"COMBINATION\"]', '2023-05-16 17:03:29', '2023-06-28 10:12:16', 0, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (66, 20, '[20]', NULL, NULL, '0脂肪荞麦面条纯正宗无糖精低脂全麦孕妇主食速食细挂面袋非手工', 'product', NULL, 'single', NULL, NULL, NULL, NULL, NULL, NULL, '//img.alicdn.com/imgextra/i3/2200608599252/O1CN01evRsme2IDTDZgfZR2_!!0-item_pic.jpg', '[\"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN01Zf0yek2IDTDNfW0c2_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN01LZPq6F2IDTE7aZnMR_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i4/2200608599252/O1CN01pDRTbl2IDTDuPYaI6_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i1/2200608599252/O1CN01GWXE4n2IDTEg0b9qt_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN01VbdQZp2IDTE4gpGyj_!!2200608599252.jpg\"]', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/yxjwAPP/upload1687918307000.mp4', NULL, 6.90, NULL, 20.00, 1.00, 1.00, NULL, 200.00, NULL, 0.69, 'default', 74, 79, '<p><br></p>', '[10004, 10003]', NULL, NULL, 0.00, 0.00, 0, 114, NULL, NULL, '[2]', '[\"SECKILL\", \"COMBINATION\", \"PROMOTE\"]', '2023-05-16 17:04:58', '2023-07-03 20:19:05', 0, 1, 0, 0, 0);
INSERT INTO `kz_store_product` VALUES (67, 14, '[14]', NULL, NULL, '海盐黑胡椒粒碎研磨柠檬低脂健身鸡胸肉煎牛排混合调料食用胡椒粉', 'product', '海盐黑胡椒', 'multiple', NULL, NULL, NULL, '[\"好的\",\"大家都觉得\",\"就打架大家\"]', NULL, NULL, 'https://gw.alicdn.com/imgextra/O1CN01chNAuz2IDTAhH8U8j_!!2200608599252.jpg', '[\"https://gw.alicdn.com/imgextra/O1CN01chNAuz2IDTAhH8U8j_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i3/2200608599252/O1CN017eAntP2IDT90FCdgs_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i1/2200608599252/O1CN01Ry8fd02IDT99AxznF_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i2/2200608599252/O1CN01PF6mEr2IDT96CmBwo_!!2200608599252.jpg\",\"https://img.alicdn.com/imgextra/i1/2200608599252/O1CN01WW17LV2IDT8xK9BSg_!!2200608599252.jpg\"]', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/yxjwAPP/upload1687918289000.mp4', NULL, 10.50, NULL, 20.00, 1.00, 8.00, 9.00, 600.00, NULL, 1.05, 'default', 69, 96, '<p><br></p>', '[10002, 10003, 10001, 10004]', NULL, NULL, 1.00, 1.00, 0, 343, NULL, NULL, '[2, 4]', '[\"SECKILL\", \"CREDIT\", \"COMBINATION\", \"BARGAIN\", \"REBATE\", \"PROMOTE\"]', '2023-05-16 17:09:57', '2023-07-11 17:23:47', 0, 1, 0, 0, 0);

-- ----------------------------
-- Table structure for kz_store_product_detail
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_product_detail`;
CREATE TABLE `kz_store_product_detail`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '唯一值',
  `product_id` bigint(20) UNSIGNED NOT NULL COMMENT '商品ID',
  `img_url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `attr_key` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品属性索引值 (attr_value|attr_value[|....])',
  `stock_num` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '属性对应的库存',
  `sales` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '销量',
  `price` decimal(8, 2) UNSIGNED NULL DEFAULT NULL COMMENT '属性金额',
  `cost_price` decimal(8, 2) UNSIGNED NULL DEFAULT NULL COMMENT '成本价',
  `original_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '原价',
  `seckill_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '秒杀价',
  `seckill_num` int(11) NULL DEFAULT NULL COMMENT '秒杀库存',
  `bargain_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '砍价活动价',
  `bargain_num` int(11) NULL DEFAULT NULL COMMENT '砍价库存',
  `combination_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '拼团价',
  `combination_num` int(11) NULL DEFAULT NULL COMMENT '拼团库存',
  `credit` decimal(10, 2) NULL DEFAULT NULL COMMENT '兑换所需积分',
  `credit_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '积分差价',
  `credit_num` int(11) NULL DEFAULT NULL COMMENT '积分库存',
  `rebate_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '返佣方式',
  `first_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '一级返佣',
  `second_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '二级返佣',
  `third_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '三级返佣',
  `bar_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品条码',
  `weight` decimal(8, 2) NULL DEFAULT 0.00 COMMENT '重量',
  `volume` decimal(8, 2) NULL DEFAULT 0.00 COMMENT '体积',
  `sort` int(255) NULL DEFAULT 0 COMMENT '排序',
  `visited` int(255) NULL DEFAULT 0 COMMENT '访问次数',
  `status` int(255) NULL DEFAULT NULL COMMENT '状态，审核',
  `status_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT NULL COMMENT '上架，下架',
  `is_delete` tinyint(2) NULL DEFAULT NULL,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  `version` int(255) NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique`(`product_id`, `attr_key`) USING BTREE,
  INDEX `sku_id`(`product_id`, `attr_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 948 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品属性值表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_product_detail
-- ----------------------------
INSERT INTO `kz_store_product_detail` VALUES (785, '', 37, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681371391192.png', '100g,青色', 984, 4, 90.00, 100.00, 50.00, 0.03, 99, 10.00, 99, 0.03, 10, 300.00, 0.03, 10, NULL, NULL, NULL, NULL, NULL, 50.00, 20.00, 0, 0, NULL, NULL, '2023-06-07 09:19:54', '2023-06-09 23:28:47', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product_detail` VALUES (786, '', 37, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681375393816.png', '100g,绿色', 998, 0, 90.00, 100.00, 50.00, 0.02, 99, 10.00, 99, 0.02, 10, 400.00, 0.02, 10, NULL, NULL, NULL, NULL, NULL, 50.00, 20.00, 0, 0, NULL, NULL, '2023-06-07 09:19:54', '2023-06-25 09:28:38', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product_detail` VALUES (787, '', 37, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681371349636.png', '100g,靛青色', 999, 0, 90.00, 100.00, 50.00, 0.04, 99, 10.00, 99, 0.04, 10, 200.00, 0.04, 10, NULL, NULL, NULL, NULL, NULL, 50.00, 20.00, 0, 0, NULL, NULL, '2023-06-07 09:19:54', '2023-06-07 09:19:54', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product_detail` VALUES (788, '', 37, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681375393826.png', '100g,浅绿色', 990, 4, 90.00, 100.00, 50.00, 0.01, 99, 10.00, 99, 0.01, 10, 500.00, 0.01, 10, NULL, NULL, NULL, NULL, NULL, 50.00, 20.00, 0, 0, NULL, NULL, '2023-06-07 09:19:54', '2023-06-08 16:22:05', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product_detail` VALUES (789, '', 37, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351704244.png', '500g,青色', 999, 1, 90.00, 100.00, 50.00, 0.07, 99, 10.00, 99, 0.07, 10, 80.00, 0.07, 10, NULL, NULL, NULL, NULL, NULL, 50.00, 20.00, 0, 0, NULL, NULL, '2023-06-07 09:19:54', '2023-06-07 09:19:54', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product_detail` VALUES (790, '', 37, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352148034.png', '500g,绿色', 987, 2, 90.00, 100.00, 50.00, 0.06, 99, 10.00, 99, 0.06, 10, 90.00, 0.06, 10, NULL, NULL, NULL, NULL, NULL, 50.00, 20.00, 0, 0, NULL, NULL, '2023-06-07 09:19:54', '2023-06-25 16:11:08', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product_detail` VALUES (791, '', 37, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351634059.png', '500g,靛青色', 998, 1, 90.00, 100.00, 50.00, 0.08, 99, 10.00, 99, 0.08, 10, 70.00, 0.08, 10, NULL, NULL, NULL, NULL, NULL, 50.00, 20.00, 0, 0, NULL, NULL, '2023-06-07 09:19:54', '2023-06-14 17:00:30', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product_detail` VALUES (792, '', 37, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352148044.png', '500g,浅绿色', 995, 0, 90.00, 100.00, 50.00, 0.05, 99, 10.00, 99, 0.05, 10, 100.00, 0.05, 10, NULL, NULL, NULL, NULL, NULL, 50.00, 20.00, 0, 0, NULL, NULL, '2023-06-07 09:19:54', '2023-06-08 14:20:20', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product_detail` VALUES (869, '', 41, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1678960875309.png', '2,200g,白色', 99, 0, 1.00, 2.00, 3.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6.00, 7.00, 0, 0, NULL, NULL, '2023-05-16 14:22:17', '2023-05-16 14:22:17', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product_detail` VALUES (870, '', 41, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1678962383659.png', '200g,3,白色', 99, 0, 1.00, 2.00, 3.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6.00, 7.00, 0, 0, NULL, NULL, '2023-05-16 14:22:17', '2023-05-16 14:22:17', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product_detail` VALUES (871, '', 41, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1678962370719.png', '2,200g,青色', 99, 0, 1.00, 2.00, 3.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6.00, 7.00, 0, 0, NULL, NULL, '2023-05-16 14:22:17', '2023-05-16 14:22:17', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product_detail` VALUES (872, '', 41, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681439748652.png', '200g,3,青色', 99, 0, 1.00, 2.00, 3.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6.00, 7.00, 0, 0, NULL, NULL, '2023-05-16 14:22:17', '2023-05-16 14:22:17', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product_detail` VALUES (873, '', 41, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1679022654738.png', '1000g,2,白色', 99, 0, 1.00, 2.00, 3.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6.00, 7.00, 0, 0, NULL, NULL, '2023-05-16 14:22:17', '2023-05-16 14:22:17', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product_detail` VALUES (874, '', 41, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1678962370716.png', '1000g,3,白色', 99, 0, 1.00, 2.00, 3.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6.00, 7.00, 0, 0, NULL, NULL, '2023-05-16 14:22:17', '2023-05-16 14:22:17', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product_detail` VALUES (875, '', 41, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1679022654758.png', '1000g,2,青色', 99, 0, 1.00, 2.00, 3.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6.00, 7.00, 0, 0, NULL, NULL, '2023-05-16 14:22:17', '2023-05-16 14:22:17', 1, 1, 0, 0, 0);
INSERT INTO `kz_store_product_detail` VALUES (876, '', 41, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1678962370708.png', '1000g,3,青色', 99, 0, 1.00, 2.00, 3.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6.00, 7.00, 0, 0, NULL, NULL, '2023-05-16 14:22:17', '2023-05-16 14:22:17', 1, 1, 0, 0, 0);

-- ----------------------------
-- Table structure for kz_store_product_history
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_product_history`;
CREATE TABLE `kz_store_product_history`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '用户ID',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型(收藏(collect）、点赞(like))',
  `product_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '商品ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `is_delete` tinyint(1) NULL DEFAULT 0,
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT NULL,
  `tenant_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uid`(`uid`, `product_id`, `type`) USING BTREE,
  INDEX `type`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 590 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城用户足迹，点赞，收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for kz_store_recharge
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_recharge`;
CREATE TABLE `kz_store_recharge`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '显示名称',
  `price` decimal(10, 2) NOT NULL COMMENT '充值金额',
  `extra_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '额外赠送',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '概述',
  `sort` int(11) UNSIGNED NULL DEFAULT 100 COMMENT '排序方式(数字越小越靠前)',
  `store_id` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '店铺id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` int(255) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT NULL COMMENT '启用',
  `is_delete` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '是否删除(1已删除)',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `store_id`(`store_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城充值方案' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_recharge
-- ----------------------------
INSERT INTO `kz_store_recharge` VALUES (1, '', 0.01, 100.00, '测试金额', 1, 0, '2023-05-26 15:01:17', '2023-05-26 15:02:15', 1, 1, 0, 0);
INSERT INTO `kz_store_recharge` VALUES (2, '', 20.00, 1.00, '', 2, 0, '2023-05-26 15:01:41', '2023-05-26 15:02:20', 1, 1, 0, 0);
INSERT INTO `kz_store_recharge` VALUES (3, '', 50.00, 5.00, '', 3, 0, '2023-05-26 15:02:07', '2023-05-26 15:02:07', 1, 1, 0, 0);
INSERT INTO `kz_store_recharge` VALUES (4, '', 100.00, 25.00, '', 4, 0, '2023-05-26 15:03:06', '2023-05-26 15:03:06', 1, 1, 0, 0);
INSERT INTO `kz_store_recharge` VALUES (5, '', 200.00, 50.00, '', 5, 0, '2023-05-26 15:03:20', '2023-05-26 15:03:20', 1, 1, 0, 0);
INSERT INTO `kz_store_recharge` VALUES (6, '', 500.00, 100.00, '', 6, 0, '2023-05-26 15:03:30', '2023-05-26 15:03:30', 1, 1, 0, 0);

-- ----------------------------
-- Table structure for kz_store_seckill
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_seckill`;
CREATE TABLE `kz_store_seckill`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动名称',
  `activity_time` date NULL DEFAULT NULL COMMENT '活动时间',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '秒杀开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '秒杀结束时间',
  `img_urls` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '轮播图',
  `hours` time(0) NULL DEFAULT NULL COMMENT '参与的整点',
  `rules` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '规则',
  `product_ids` json NULL COMMENT '商品列表',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态 0 未开始 1 进行中 2 已结束',
  `status_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `un_idx`(`activity_time`, `hours`) USING BTREE COMMENT 'un_activity'
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城-秒杀活动表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_seckill
-- ----------------------------
INSERT INTO `kz_store_seckill` VALUES (1, '每日9点活动', NULL, '2023-05-09 09:00:00', '2023-05-11 00:00:00', NULL, NULL, '1、秒杀商品数量有限先到先得\n2、秒杀商品需支付成功后才算秒杀成功\n3、商品在秒杀活动期间将不能以正常售价购买，购物车内的秒杀商品将暂时失效\n4、秒杀商品支持平台的退货服务，将以实付金额进行等额退款\n5、秒杀商品暂不同其他优惠共享\n6、如被发现有违规行为（如恶意刷订单获取秒杀商品等违反活动公平性的问题），活动组织方将取消你的秒杀订单，原路退还支付金额。', NULL, 2, '已结束', 1, '2023-05-23 22:28:32', '2023-06-02 15:46:47', 1, 1, 0);
INSERT INTO `kz_store_seckill` VALUES (2, '每天15点活动', '2023-06-12', '2023-06-12 15:00:00', '2023-06-12 17:00:00', '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433665374.png\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433494355.png\"]', '15:00:00', '1、秒杀商品数量有限先到先得\n2、秒杀商品需支付成功后才算秒杀成功\n3、商品在秒杀活动期间将不能以正常售价购买，购物车内的秒杀商品将暂时失效\n4、秒杀商品支持平台的退货服务，将以实付金额进行等额退款\n5、秒杀商品暂不同其他优惠共享\n6、如被发现有违规行为（如恶意刷订单获取秒杀商品等违反活动公平性的问题），活动组织方将取消你的秒杀订单，原路退还支付金额。', '[67, 66, 65, 64, 62, 58, 57]', 2, '已结束', 1, '2023-05-23 22:31:24', '2023-06-12 10:03:56', 1, 0, 0);
INSERT INTO `kz_store_seckill` VALUES (3, '17点活动', '2023-06-12', '2023-06-12 17:00:00', '2023-06-12 19:00:00', '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685513609354.png\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685513609356.png\"]', '17:00:00', '1111111', '[37, 67, 66, 65, 64, 62, 58, 57]', 2, '已结束', 1, '2023-06-02 15:46:35', '2023-06-12 10:04:04', 1, 0, 0);
INSERT INTO `kz_store_seckill` VALUES (4, '19点场次', '2023-06-12', '2023-06-12 19:00:00', '2023-06-12 21:00:00', '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433494356.png\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433494351.png\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433494355.png\"]', '19:00:00', '222222', '[67, 66, 65]', 2, '已结束', 1, '2023-06-02 15:56:34', '2023-06-12 10:04:12', 1, 0, 0);
INSERT INTO `kz_store_seckill` VALUES (5, '21', '2023-06-12', '2023-06-12 21:00:00', '2023-06-12 23:00:00', '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433665364.png\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433665374.png\"]', '21:00:00', '1111', '[67, 66, 65]', 2, '已结束', 1, '2023-06-02 15:57:05', '2023-06-12 10:04:27', 1, 0, 0);
INSERT INTO `kz_store_seckill` VALUES (6, '23点', '2023-06-12', '2023-06-12 23:00:00', '2023-06-13 01:00:00', '[\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433494358.png\",\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433494356.png\"]', '23:00:00', '1111', '[67, 66, 65]', 2, '已结束', 1, '2023-06-02 15:57:28', '2023-06-12 10:04:35', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_store_seckill_detail
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_seckill_detail`;
CREATE TABLE `kz_store_seckill_detail`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `seckill_id` int(11) NULL DEFAULT NULL COMMENT '秒杀活动id',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '原价',
  `activity_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '秒杀价',
  `limit_num` int(11) NULL DEFAULT NULL COMMENT '限制购买数量 0 不限制',
  `stock_num` int(11) NULL DEFAULT NULL COMMENT '库存数量',
  `sales` int(11) NULL DEFAULT 0 COMMENT '销量',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `status_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城-秒杀活动商品场次关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for kz_store_service
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_service`;
CREATE TABLE `kz_store_service`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品服务ID',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '服务名称',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '概述',
  `is_default` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '是否默认(新增商品时)',
  `sort` int(11) UNSIGNED NULL DEFAULT 100 COMMENT '排序方式(数字越小越靠前)',
  `store_id` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '店铺id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` int(255) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT NULL COMMENT '启用',
  `is_delete` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '是否删除(1已删除)',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `store_id`(`store_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10005 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城商品服务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_store_service
-- ----------------------------
INSERT INTO `kz_store_service` VALUES (10001, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684202280754.png', '七天无理由退货', '满足相应条件时，消费者可申请7天无理由退货', 1, 100, 10001, '2023-05-13 21:31:02', '2023-05-16 09:59:56', NULL, 1, 0, 0);
INSERT INTO `kz_store_service` VALUES (10002, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683631713555.png', '全场包邮', '所有商品包邮（偏远地区除外）', 0, 100, 10001, '2023-05-13 21:31:04', '2023-05-13 21:33:11', NULL, 1, 0, 0);
INSERT INTO `kz_store_service` VALUES (10003, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683631749707.png', '48小时发货', '下单后48小时之内发货', 1, 100, 10001, '2023-05-13 21:31:07', '2023-05-13 21:32:37', NULL, 1, 0, 0);
INSERT INTO `kz_store_service` VALUES (10004, 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684202374932.png', '运费险', '退货免运费', 0, 5, 0, '2023-05-16 09:59:43', '2023-05-16 09:59:43', 1, 1, 0, 0);

-- ----------------------------
-- Table structure for kz_store_tags
-- ----------------------------
DROP TABLE IF EXISTS `kz_store_tags`;
CREATE TABLE `kz_store_tags`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类标题',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  `url_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转链接',
  `sort` int(255) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '特色分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for kz_sys_address
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_address`;
CREATE TABLE `kz_sys_address`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收件人',
  `postal_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` json NULL COMMENT '省市区',
  `address_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '详细地址',
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_default` tinyint(255) NULL DEFAULT 0 COMMENT '默认',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT NULL COMMENT '状态',
  `is_delete` tinyint(1) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统售后收货地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_sys_address
-- ----------------------------
INSERT INTO `kz_sys_address` VALUES (23, '13675175408', '丁晓松', NULL, '[\"浙江省\", \"杭州市\", \"滨江区\"]', '铂金名筑3栋3单元1002室', NULL, 1, 867, '2023-05-09 14:37:40', '2023-06-21 16:37:13', 1, 1, 0);
-- ----------------------------
-- Table structure for kz_sys_column_config
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_column_config`;
CREATE TABLE `kz_sys_column_config`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `column_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `column_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dict_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `extra` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `form_show` bit(1) NULL DEFAULT NULL,
  `form_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `key_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `list_show` bit(1) NULL DEFAULT NULL,
  `not_null` bit(1) NULL DEFAULT NULL,
  `query_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date_annotation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 780 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成字段信息存储' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_sys_column_config
-- ----------------------------
INSERT INTO `kz_sys_column_config` VALUES (83, 'kz_merchant', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '#', NULL);
INSERT INTO `kz_sys_column_config` VALUES (84, 'kz_merchant', 'agent_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '上级代理商', NULL);
INSERT INTO `kz_sys_column_config` VALUES (85, 'kz_merchant', 'merchant_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'1', NULL, '商家名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (86, 'kz_merchant', 'short_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '商户简称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (87, 'kz_merchant', 'avatar_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '头像地址', NULL);
INSERT INTO `kz_sys_column_config` VALUES (88, 'kz_merchant', 'front_img_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '商家头图', NULL);
INSERT INTO `kz_sys_column_config` VALUES (89, 'kz_merchant', 'img_urls', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '图片', NULL);
INSERT INTO `kz_sys_column_config` VALUES (90, 'kz_merchant', 'description', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '描述信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (91, 'kz_merchant', 'category_id', 'varchar', NULL, '', b'1', 'select', '', b'1', b'0', NULL, '分类', NULL);
INSERT INTO `kz_sys_column_config` VALUES (92, 'kz_merchant', 'address', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '省市区', NULL);
INSERT INTO `kz_sys_column_config` VALUES (93, 'kz_merchant', 'address_detail', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '详细地址', NULL);
INSERT INTO `kz_sys_column_config` VALUES (94, 'kz_merchant', 'license_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '营业执照', NULL);
INSERT INTO `kz_sys_column_config` VALUES (95, 'kz_merchant', 'latitude', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '经纬度', NULL);
INSERT INTO `kz_sys_column_config` VALUES (96, 'kz_merchant', 'longitude', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '经纬度', NULL);
INSERT INTO `kz_sys_column_config` VALUES (97, 'kz_merchant', 'contact_user', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '联系人', NULL);
INSERT INTO `kz_sys_column_config` VALUES (98, 'kz_merchant', 'contact_phone', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '联系电话', NULL);
INSERT INTO `kz_sys_column_config` VALUES (99, 'kz_merchant', 'expired_time', 'date', NULL, '', b'1', 'datetime', '', b'1', b'0', NULL, '到期时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (100, 'kz_merchant', 'is_online', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '是否上线', NULL);
INSERT INTO `kz_sys_column_config` VALUES (101, 'kz_merchant', 'status', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (102, 'kz_merchant', 'status_desc', 'varchar', NULL, '', b'0', 'input', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (103, 'kz_merchant', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (104, 'kz_merchant', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (105, 'kz_merchant', 'create_user', 'int', NULL, '', b'0', 'number', '', b'1', b'0', NULL, '创建人', NULL);
INSERT INTO `kz_sys_column_config` VALUES (106, 'kz_merchant', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '是否启用', NULL);
INSERT INTO `kz_sys_column_config` VALUES (107, 'kz_merchant', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (108, 'kz_merchant', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'1', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (109, 'kz_sys_directory', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (110, 'kz_sys_directory', 'title', 'varchar', NULL, '', b'1', 'input', 'MUL', b'1', b'0', NULL, '标题', NULL);
INSERT INTO `kz_sys_column_config` VALUES (111, 'kz_sys_directory', 'description', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '备注信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (112, 'kz_sys_directory', 'target', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '平台，机构，老师', NULL);
INSERT INTO `kz_sys_column_config` VALUES (113, 'kz_sys_directory', 'create_user', 'int', NULL, '', b'0', 'number', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (114, 'kz_sys_directory', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (115, 'kz_sys_directory', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (116, 'kz_sys_directory', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (117, 'kz_sys_directory', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'1', b'0', NULL, '所属机构', NULL);
INSERT INTO `kz_sys_column_config` VALUES (118, 'kz_sys_directory', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (119, 'kz_store_product', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (120, 'kz_store_product', 'product_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'1', NULL, '商品名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (121, 'kz_store_product', 'product_type', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '商品类型，如普通商品，虚拟商品', NULL);
INSERT INTO `kz_sys_column_config` VALUES (122, 'kz_store_product', 'product_info', 'text', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '商品简介', NULL);
INSERT INTO `kz_sys_column_config` VALUES (123, 'kz_store_product', 'category_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '分类id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (124, 'kz_store_product', 'img_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'1', NULL, '显示图片', NULL);
INSERT INTO `kz_sys_column_config` VALUES (125, 'kz_store_product', 'img_urls', 'text', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '图片列表', NULL);
INSERT INTO `kz_sys_column_config` VALUES (126, 'kz_store_product', 'video_url', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '视频链接', NULL);
INSERT INTO `kz_sys_column_config` VALUES (127, 'kz_store_product', 'unit_name', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '单位', NULL);
INSERT INTO `kz_sys_column_config` VALUES (128, 'kz_store_product', 'price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '价格', NULL);
INSERT INTO `kz_sys_column_config` VALUES (129, 'kz_store_product', 'sales', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '销量', NULL);
INSERT INTO `kz_sys_column_config` VALUES (131, 'kz_store_product', 'description', 'longtext', NULL, '', b'1', NULL, '', b'0', b'1', NULL, '详细描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (132, 'kz_store_product', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (133, 'kz_store_product', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (134, 'kz_store_product', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (135, 'kz_store_product', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '上架，下架', NULL);
INSERT INTO `kz_sys_column_config` VALUES (136, 'kz_store_product', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (137, 'kz_sys_gen_config', 'id', 'bigint', NULL, 'auto_increment', NULL, NULL, 'PRI', NULL, b'0', NULL, 'ID', NULL);
INSERT INTO `kz_sys_column_config` VALUES (138, 'kz_sys_gen_config', 'table_name', 'varchar', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '表名', NULL);
INSERT INTO `kz_sys_column_config` VALUES (139, 'kz_sys_gen_config', 'author', 'varchar', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '作者', NULL);
INSERT INTO `kz_sys_column_config` VALUES (140, 'kz_sys_gen_config', 'cover', 'bit', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '是否覆盖', NULL);
INSERT INTO `kz_sys_column_config` VALUES (141, 'kz_sys_gen_config', 'module_name', 'varchar', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '模块名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (142, 'kz_sys_gen_config', 'pack', 'varchar', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '至于哪个包下', NULL);
INSERT INTO `kz_sys_column_config` VALUES (143, 'kz_sys_gen_config', 'path', 'varchar', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '前端代码生成的路径', NULL);
INSERT INTO `kz_sys_column_config` VALUES (144, 'kz_sys_gen_config', 'api_path', 'varchar', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '前端Api文件路径', NULL);
INSERT INTO `kz_sys_column_config` VALUES (145, 'kz_sys_gen_config', 'prefix', 'varchar', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '表前缀', NULL);
INSERT INTO `kz_sys_column_config` VALUES (146, 'kz_sys_gen_config', 'api_alias', 'varchar', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '接口名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (147, 'kz_sys_gen_config', 'page_type', 'varchar', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '编辑模式，page，dialog， drawer', NULL);
INSERT INTO `kz_sys_column_config` VALUES (148, 'kz_sys_gen_config', 'super_class', 'tinyint', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '是否继承超类', NULL);
INSERT INTO `kz_sys_column_config` VALUES (149, 'kz_user_level', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '#', NULL);
INSERT INTO `kz_sys_column_config` VALUES (151, 'kz_user_level', 'icon', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '图标', NULL);
INSERT INTO `kz_sys_column_config` VALUES (152, 'kz_user_level', 'img_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '图片', NULL);
INSERT INTO `kz_sys_column_config` VALUES (153, 'kz_user_level', 'description', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '权益信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (155, 'kz_user_level', 'sort', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '排序', NULL);
INSERT INTO `kz_sys_column_config` VALUES (156, 'kz_user_level', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (157, 'kz_user_level', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (158, 'kz_user_level', 'create_user', 'int', NULL, '', b'0', 'number', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (159, 'kz_user_level', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '启用', NULL);
INSERT INTO `kz_sys_column_config` VALUES (160, 'kz_user_level', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (161, 'kz_user_level', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (162, 'kz_user_group', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (163, 'kz_user_group', 'group_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '会员分组', NULL);
INSERT INTO `kz_sys_column_config` VALUES (164, 'kz_user_group', 'icon', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '图标', NULL);
INSERT INTO `kz_sys_column_config` VALUES (165, 'kz_user_group', 'img_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '图片', NULL);
INSERT INTO `kz_sys_column_config` VALUES (166, 'kz_user_group', 'description', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '权益信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (167, 'kz_user_group', 'url_path', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '跳转链接', NULL);
INSERT INTO `kz_sys_column_config` VALUES (168, 'kz_user_group', 'sort', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '排序', NULL);
INSERT INTO `kz_sys_column_config` VALUES (169, 'kz_user_group', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (170, 'kz_user_group', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (171, 'kz_user_group', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (172, 'kz_user_group', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (173, 'kz_user_group', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (174, 'kz_user_group', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (175, 'kz_store_product', 'merchant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '商户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (176, 'kz_store_product', 'store_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '店铺id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (177, 'kz_store_product', 'prop_type', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '规格类型，如single, multiple', NULL);
INSERT INTO `kz_sys_column_config` VALUES (178, 'kz_store_product', 'internal_id', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '自编码id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (179, 'kz_store_product', 'bar_code', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '条形码', NULL);
INSERT INTO `kz_sys_column_config` VALUES (180, 'kz_store_product', 'keyword', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '关键词', NULL);
INSERT INTO `kz_sys_column_config` VALUES (181, 'kz_store_product', 'tags', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '标签', NULL);
INSERT INTO `kz_sys_column_config` VALUES (182, 'kz_store_product', 'cost_price', 'decimal', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '成本价', NULL);
INSERT INTO `kz_sys_column_config` VALUES (183, 'kz_store_product', 'original_price', 'decimal', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '划线价', NULL);
INSERT INTO `kz_sys_column_config` VALUES (184, 'kz_store_product', 'stock_num', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '库存', NULL);
INSERT INTO `kz_sys_column_config` VALUES (185, 'kz_store_product', 'shipping_type', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '快递，自提', NULL);
INSERT INTO `kz_sys_column_config` VALUES (186, 'kz_store_product', 'shipping_template', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '配送模板', NULL);
INSERT INTO `kz_sys_column_config` VALUES (187, 'kz_store_product', 'sort', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '排序', NULL);
INSERT INTO `kz_sys_column_config` VALUES (188, 'kz_store_product', 'visited', 'int', NULL, '', b'1', 'number', '', b'0', b'0', NULL, '访问次数', NULL);
INSERT INTO `kz_sys_column_config` VALUES (189, 'kz_store_product', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (190, 'kz_store_product', 'status', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '状态，审核', NULL);
INSERT INTO `kz_sys_column_config` VALUES (191, 'kz_store_product', 'status_desc', 'varchar', NULL, '', b'0', 'input', '', b'0', b'0', NULL, '状态描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (192, 'kz_store_attr_template', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (193, 'kz_store_attr_template', 'attr_key', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '规格名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (194, 'kz_store_attr_template', 'values', 'json', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '规格值', NULL);
INSERT INTO `kz_sys_column_config` VALUES (195, 'kz_store_attr_template', 'sort', 'int', NULL, '', b'0', 'number', '', b'1', b'0', NULL, '排序', NULL);
INSERT INTO `kz_sys_column_config` VALUES (196, 'kz_store_attr_template', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (197, 'kz_store_attr_template', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (198, 'kz_store_attr_template', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (199, 'kz_store_attr_template', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (200, 'kz_store_attr_template', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (201, 'kz_store_attr_template', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (202, 'kz_store_product_attr', 'id', 'bigint', NULL, 'auto_increment', b'0', NULL, 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (203, 'kz_store_product_attr', 'uuid', 'varchar', NULL, '', b'1', 'input', 'MUL', b'1', b'1', NULL, '唯一值', NULL);
INSERT INTO `kz_sys_column_config` VALUES (204, 'kz_store_product_attr', 'product_id', 'bigint', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '商品ID', NULL);
INSERT INTO `kz_sys_column_config` VALUES (205, 'kz_store_product_attr', 'img_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '图片', NULL);
INSERT INTO `kz_sys_column_config` VALUES (206, 'kz_store_product_attr', 'attr_key', 'varchar', NULL, '', b'1', 'input', '', b'1', b'1', NULL, '商品属性索引值 (attr_value|attr_value[|....])', NULL);
INSERT INTO `kz_sys_column_config` VALUES (207, 'kz_store_product_attr', 'stock_num', 'int', NULL, '', b'1', 'number', '', b'1', b'1', NULL, '属性对应的库存', NULL);
INSERT INTO `kz_sys_column_config` VALUES (208, 'kz_store_product_attr', 'sales', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '销量', NULL);
INSERT INTO `kz_sys_column_config` VALUES (209, 'kz_store_product_attr', 'price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '属性金额', NULL);
INSERT INTO `kz_sys_column_config` VALUES (210, 'kz_store_product_attr', 'cost_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '成本价', NULL);
INSERT INTO `kz_sys_column_config` VALUES (211, 'kz_store_product_attr', 'original_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '原价', NULL);
INSERT INTO `kz_sys_column_config` VALUES (212, 'kz_store_product_attr', 'bar_code', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '商品条码', NULL);
INSERT INTO `kz_sys_column_config` VALUES (213, 'kz_store_product_attr', 'weight', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '重量', NULL);
INSERT INTO `kz_sys_column_config` VALUES (214, 'kz_store_product_attr', 'volume', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '体积', NULL);
INSERT INTO `kz_sys_column_config` VALUES (215, 'kz_store_product_attr', 'sort', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '排序', NULL);
INSERT INTO `kz_sys_column_config` VALUES (216, 'kz_store_product_attr', 'visited', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '访问次数', NULL);
INSERT INTO `kz_sys_column_config` VALUES (217, 'kz_store_product_attr', 'status', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '状态，审核', NULL);
INSERT INTO `kz_sys_column_config` VALUES (218, 'kz_store_product_attr', 'status_desc', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '状态描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (219, 'kz_store_product_attr', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (220, 'kz_store_product_attr', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (221, 'kz_store_product_attr', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (222, 'kz_store_product_attr', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '上架，下架', NULL);
INSERT INTO `kz_sys_column_config` VALUES (223, 'kz_store_product_attr', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (224, 'kz_store_product_attr', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (225, 'kz_diy_page', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (226, 'kz_diy_page', 'uuid', 'varchar', NULL, '', b'1', 'input', 'UNI', b'1', b'0', NULL, '页面唯一id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (227, 'kz_diy_page', 'title', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '页面标题', NULL);
INSERT INTO `kz_sys_column_config` VALUES (228, 'kz_diy_page', 'description', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '备注信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (229, 'kz_diy_page', 'page_type', 'varchar', NULL, '', b'1', 'select', '', b'1', b'0', NULL, '页面类型，比如首页，分类，自定义，个人中心等等', NULL);
INSERT INTO `kz_sys_column_config` VALUES (230, 'kz_diy_page', 'page_path', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '跳转链接', NULL);
INSERT INTO `kz_sys_column_config` VALUES (231, 'kz_diy_page', 'page_data', 'longtext', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '页面数据', NULL);
INSERT INTO `kz_sys_column_config` VALUES (232, 'kz_diy_page', 'attr_data', 'json', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '属性，备用', NULL);
INSERT INTO `kz_sys_column_config` VALUES (233, 'kz_diy_page', 'extra_data', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '扩展等，备用', NULL);
INSERT INTO `kz_sys_column_config` VALUES (234, 'kz_diy_page', 'sort', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '排序', NULL);
INSERT INTO `kz_sys_column_config` VALUES (235, 'kz_diy_page', 'is_default', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '是否默认', NULL);
INSERT INTO `kz_sys_column_config` VALUES (236, 'kz_diy_page', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (237, 'kz_diy_page', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (238, 'kz_diy_page', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (239, 'kz_diy_page', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (240, 'kz_diy_page', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (241, 'kz_diy_page', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (242, 'kz_agent', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (243, 'kz_agent', 'agent_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '代理商名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (244, 'kz_agent', 'parent_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '父级代理商', NULL);
INSERT INTO `kz_sys_column_config` VALUES (245, 'kz_agent', 'level', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '代理级别', NULL);
INSERT INTO `kz_sys_column_config` VALUES (246, 'kz_agent', 'contact_phone', 'varchar', NULL, '', b'1', 'input', 'UNI', b'1', b'0', NULL, '联系方式', NULL);
INSERT INTO `kz_sys_column_config` VALUES (247, 'kz_agent', 'contact_user', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '联系人', NULL);
INSERT INTO `kz_sys_column_config` VALUES (248, 'kz_agent', 'address', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '区域', NULL);
INSERT INTO `kz_sys_column_config` VALUES (249, 'kz_agent', 'expired_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (250, 'kz_agent', 'status', 'int', NULL, '', b'0', 'number', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (251, 'kz_agent', 'status_desc', 'varchar', NULL, '', b'0', 'input', '', b'1', b'0', NULL, '状态描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (252, 'kz_agent', 'create_user', 'int', NULL, '', b'0', 'number', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (253, 'kz_agent', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (254, 'kz_agent', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (255, 'kz_agent', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (256, 'kz_agent', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (257, 'kz_agent', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (258, 'kz_store_cart', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (259, 'kz_store_cart', 'uid', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (260, 'kz_store_cart', 'product_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (261, 'kz_store_cart', 'product_detail_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (262, 'kz_store_cart', 'cart_num', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (263, 'kz_store_cart', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (264, 'kz_store_cart', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (265, 'kz_store_cart', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (266, 'kz_store_cart', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (267, 'kz_store_cart', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (268, 'kz_store_cart', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (269, 'kz_user_address', 'id', 'int', NULL, 'auto_increment', NULL, NULL, 'PRI', NULL, b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (270, 'kz_user_address', 'uid', 'int', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (271, 'kz_user_address', 'phone', 'varchar', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '电话', NULL);
INSERT INTO `kz_sys_column_config` VALUES (272, 'kz_user_address', 'nick_name', 'varchar', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '收件人', NULL);
INSERT INTO `kz_sys_column_config` VALUES (273, 'kz_user_address', 'postal_code', 'varchar', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (274, 'kz_user_address', 'address', 'varchar', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '省市区', NULL);
INSERT INTO `kz_sys_column_config` VALUES (275, 'kz_user_address', 'address_detail', 'text', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '详细地址', NULL);
INSERT INTO `kz_sys_column_config` VALUES (276, 'kz_user_address', 'create_time', 'datetime', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (277, 'kz_user_address', 'update_time', 'datetime', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (278, 'kz_user_address', 'enabled', 'tinyint', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (279, 'kz_user_address', 'is_delete', 'tinyint', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (280, 'kz_user_address', 'tenant_id', 'int', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (281, 'kz_store_order', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '#', NULL);
INSERT INTO `kz_sys_column_config` VALUES (282, 'kz_store_order', 'uid', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '用户', NULL);
INSERT INTO `kz_sys_column_config` VALUES (283, 'kz_store_order', 'total_num', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '商品总量', NULL);
INSERT INTO `kz_sys_column_config` VALUES (284, 'kz_store_order', 'total_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '订单总价', NULL);
INSERT INTO `kz_sys_column_config` VALUES (285, 'kz_store_order', 'pay_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '实付金额', NULL);
INSERT INTO `kz_sys_column_config` VALUES (286, 'kz_store_order', 'coupon_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '优惠券', NULL);
INSERT INTO `kz_sys_column_config` VALUES (287, 'kz_store_order', 'coupon_price', 'decimal', NULL, '', b'0', NULL, '', b'0', b'0', NULL, '优惠券金额', NULL);
INSERT INTO `kz_sys_column_config` VALUES (288, 'kz_store_order', 'postage_price', 'decimal', NULL, '', b'0', NULL, '', b'0', b'0', NULL, '邮费', NULL);
INSERT INTO `kz_sys_column_config` VALUES (289, 'kz_store_order', 'user_remarks', 'text', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '用户备注', NULL);
INSERT INTO `kz_sys_column_config` VALUES (290, 'kz_store_order', 'store_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '门店id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (291, 'kz_store_order', 'shipping_type', 'tinyint', NULL, '', b'1', 'switch', '', b'0', b'0', NULL, '配送方式', NULL);
INSERT INTO `kz_sys_column_config` VALUES (292, 'kz_store_order', 'extra_info', 'text', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '扩展信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (293, 'kz_store_order', 'address_info', 'json', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '收件信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (294, 'kz_store_order', 'delivery_info', 'json', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '快递信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (295, 'kz_store_order', 'is_paid', 'tinyint', NULL, '', b'0', 'switch', '', b'1', b'0', NULL, '支付状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (296, 'kz_store_order', 'pay_time', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '支付时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (297, 'kz_store_order', 'pay_type', 'varchar', NULL, '', b'0', 'input', '', b'0', b'0', NULL, '支付方式', NULL);
INSERT INTO `kz_sys_column_config` VALUES (298, 'kz_store_order', 'pay_order_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '支付订单id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (299, 'kz_store_order', 'status', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (300, 'kz_store_order', 'status_desc', 'varchar', NULL, '', b'0', 'input', '', b'0', b'0', NULL, '状态描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (301, 'kz_store_order', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (302, 'kz_store_order', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '下单时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (303, 'kz_store_order', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (304, 'kz_store_order', 'enabled', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (305, 'kz_store_order', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (306, 'kz_store_order', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (307, 'kz_store_order_detail', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '#', NULL);
INSERT INTO `kz_sys_column_config` VALUES (308, 'kz_store_order_detail', 'shop_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '店铺id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (309, 'kz_store_order_detail', 'order_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (310, 'kz_store_order_detail', 'product_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (311, 'kz_store_order_detail', 'product_detail_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (312, 'kz_store_order_detail', 'product_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '商品名称快照', NULL);
INSERT INTO `kz_sys_column_config` VALUES (313, 'kz_store_order_detail', 'attr_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '商品属性快照', NULL);
INSERT INTO `kz_sys_column_config` VALUES (314, 'kz_store_order_detail', 'img_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '商品图片快照', NULL);
INSERT INTO `kz_sys_column_config` VALUES (315, 'kz_store_order_detail', 'cart_num', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '数量', NULL);
INSERT INTO `kz_sys_column_config` VALUES (316, 'kz_store_order_detail', 'price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '商品单价', NULL);
INSERT INTO `kz_sys_column_config` VALUES (317, 'kz_store_order_detail', 'total_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '总价', NULL);
INSERT INTO `kz_sys_column_config` VALUES (318, 'kz_store_order_detail', 'status', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (319, 'kz_store_order_detail', 'status_desc', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '状态描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (320, 'kz_store_order_detail', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (321, 'kz_store_order_detail', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (322, 'kz_store_order_detail', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (323, 'kz_store_order_detail', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (324, 'kz_store_order_detail', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (325, 'kz_store_order_detail', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (326, 'kz_store_order_detail', 'uid', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '用户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (327, 'kz_store_order_status', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (328, 'kz_store_order_status', 'order_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '订单id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (329, 'kz_store_order_status', 'status', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '订单状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (330, 'kz_store_order_status', 'status_desc', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '订单状态描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (331, 'kz_store_order_status', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (332, 'kz_diy_path', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '#', NULL);
INSERT INTO `kz_sys_column_config` VALUES (333, 'kz_diy_path', 'title', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '页面名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (334, 'kz_diy_path', 'h5_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, 'H5链接', NULL);
INSERT INTO `kz_sys_column_config` VALUES (335, 'kz_diy_path', 'mp_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '小程序链接', NULL);
INSERT INTO `kz_sys_column_config` VALUES (336, 'kz_diy_path', 'page_type', 'varchar', NULL, '', b'1', 'select', '', b'1', b'0', NULL, '页面类型', NULL);
INSERT INTO `kz_sys_column_config` VALUES (337, 'kz_diy_path', 'description', 'varchar', NULL, '', b'1', 'textarea', '', b'1', b'0', NULL, '备注', NULL);
INSERT INTO `kz_sys_column_config` VALUES (338, 'kz_diy_path', 'status', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (339, 'kz_diy_path', 'status_desc', 'varchar', NULL, '', b'0', 'input', '', b'0', b'0', NULL, '状态描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (340, 'kz_diy_path', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (341, 'kz_diy_path', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (342, 'kz_diy_path', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (343, 'kz_diy_path', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (344, 'kz_diy_path', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (345, 'kz_diy_path', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (346, 'kz_user_level', 'title', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '等级名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (347, 'kz_user_level', 'is_vip', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '付费会员', NULL);
INSERT INTO `kz_sys_column_config` VALUES (348, 'kz_user_level', 'condition', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '升级条件', NULL);
INSERT INTO `kz_sys_column_config` VALUES (349, 'kz_user_level', 'discount', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '折扣', NULL);
INSERT INTO `kz_sys_column_config` VALUES (350, 'kz_user_level', 'price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '购买价格', NULL);
INSERT INTO `kz_sys_column_config` VALUES (351, 'kz_article', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (352, 'kz_article', 'title', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '标题', NULL);
INSERT INTO `kz_sys_column_config` VALUES (353, 'kz_article', 'img_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '封面图', NULL);
INSERT INTO `kz_sys_column_config` VALUES (354, 'kz_article', 'img_urls', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '轮播图', NULL);
INSERT INTO `kz_sys_column_config` VALUES (355, 'kz_article', 'description', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '摘要', NULL);
INSERT INTO `kz_sys_column_config` VALUES (356, 'kz_article', 'content', 'int', NULL, '', b'1', 'number', '', b'0', b'0', NULL, '文章详情', NULL);
INSERT INTO `kz_sys_column_config` VALUES (357, 'kz_article', 'author', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '作者', NULL);
INSERT INTO `kz_sys_column_config` VALUES (358, 'kz_article', 'attachments', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '附件', NULL);
INSERT INTO `kz_sys_column_config` VALUES (359, 'kz_article', 'status', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (360, 'kz_article', 'status_desc', 'varchar', NULL, '', b'0', 'input', '', b'0', b'0', NULL, '状态描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (361, 'kz_article', 'visited', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '点击次数', NULL);
INSERT INTO `kz_sys_column_config` VALUES (362, 'kz_article', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (363, 'kz_article', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (364, 'kz_article', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (365, 'kz_article', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (366, 'kz_article', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (367, 'kz_article', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (368, 'kz_article_category', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (369, 'kz_article_category', 'parent_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '父级ID', NULL);
INSERT INTO `kz_sys_column_config` VALUES (370, 'kz_article_category', 'title', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, 'banner标题', NULL);
INSERT INTO `kz_sys_column_config` VALUES (371, 'kz_article_category', 'icon', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '图标', NULL);
INSERT INTO `kz_sys_column_config` VALUES (372, 'kz_article_category', 'img_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '图片', NULL);
INSERT INTO `kz_sys_column_config` VALUES (373, 'kz_article_category', 'description', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '描述信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (374, 'kz_article_category', 'url_path', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '跳转链接', NULL);
INSERT INTO `kz_sys_column_config` VALUES (375, 'kz_article_category', 'sort', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '排序', NULL);
INSERT INTO `kz_sys_column_config` VALUES (376, 'kz_article_category', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (377, 'kz_article_category', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (378, 'kz_article_category', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (379, 'kz_article_category', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (380, 'kz_article_category', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (381, 'kz_article_category', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (382, 'kz_store', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '#', NULL);
INSERT INTO `kz_sys_column_config` VALUES (383, 'kz_store', 'uuid', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (384, 'kz_store', 'merchant_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '商户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (385, 'kz_store', 'store_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'1', NULL, '门店名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (386, 'kz_store', 'avatar_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '头像', NULL);
INSERT INTO `kz_sys_column_config` VALUES (387, 'kz_store', 'img_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '头图', NULL);
INSERT INTO `kz_sys_column_config` VALUES (388, 'kz_store', 'img_urls', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '轮播图', NULL);
INSERT INTO `kz_sys_column_config` VALUES (389, 'kz_store', 'store_type', 'varchar', NULL, '', b'1', 'select', '', b'1', b'1', NULL, '店铺类型 local,delivery,mall', NULL);
INSERT INTO `kz_sys_column_config` VALUES (390, 'kz_store', 'short_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '门店简称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (391, 'kz_store', 'address', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '省市区', NULL);
INSERT INTO `kz_sys_column_config` VALUES (392, 'kz_store', 'address_detail', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '详细地址', NULL);
INSERT INTO `kz_sys_column_config` VALUES (393, 'kz_store', 'latitude', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '经纬度', NULL);
INSERT INTO `kz_sys_column_config` VALUES (394, 'kz_store', 'longitude', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '经纬度', NULL);
INSERT INTO `kz_sys_column_config` VALUES (395, 'kz_store', 'is_online', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '是否营业', NULL);
INSERT INTO `kz_sys_column_config` VALUES (396, 'kz_store', 'start_time', 'datetime', NULL, '', b'1', 'datetime', '', b'1', b'0', NULL, '营业开始时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (397, 'kz_store', 'end_time', 'datetime', NULL, '', b'1', 'datetime', '', b'1', b'0', NULL, '营业结束时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (398, 'kz_store', 'contact_user', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '联系人', NULL);
INSERT INTO `kz_sys_column_config` VALUES (399, 'kz_store', 'contact_phone', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '联系方式', NULL);
INSERT INTO `kz_sys_column_config` VALUES (400, 'kz_store', 'description', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '备注信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (401, 'kz_store', 'tags', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (402, 'kz_store', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (403, 'kz_store', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (404, 'kz_store', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (405, 'kz_store', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (406, 'kz_store', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '所属机构', NULL);
INSERT INTO `kz_sys_column_config` VALUES (407, 'kz_store', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (408, 'kz_store_coupon', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '优惠券id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (409, 'kz_store_coupon', 'shop_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '店铺id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (410, 'kz_store_coupon', 'title', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '优惠券名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (411, 'kz_store_coupon', 'coupon_type', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '优惠券类型，满减，折扣，类目，无门槛等', NULL);
INSERT INTO `kz_sys_column_config` VALUES (412, 'kz_store_coupon', 'total', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '总量', NULL);
INSERT INTO `kz_sys_column_config` VALUES (413, 'kz_store_coupon', 'remain', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '剩余', NULL);
INSERT INTO `kz_sys_column_config` VALUES (414, 'kz_store_coupon', 'price', 'decimal', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '优惠券面值', NULL);
INSERT INTO `kz_sys_column_config` VALUES (415, 'kz_store_coupon', 'min_price', 'decimal', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '最低消费', NULL);
INSERT INTO `kz_sys_column_config` VALUES (416, 'kz_store_coupon', 'discount', 'decimal', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '折扣，请填入小数', NULL);
INSERT INTO `kz_sys_column_config` VALUES (417, 'kz_store_coupon', 'hold_limit', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '最大持有量', NULL);
INSERT INTO `kz_sys_column_config` VALUES (418, 'kz_store_coupon', 'start_time', 'datetime', NULL, '', b'1', 'datetime', '', b'1', b'0', NULL, '开始领取时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (419, 'kz_store_coupon', 'end_time', 'datetime', NULL, '', b'1', 'datetime', '', b'1', b'0', NULL, '结束领取时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (420, 'kz_store_coupon', 'use_start_time', 'datetime', NULL, '', b'1', 'datetime', '', b'1', b'0', NULL, '可用时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (421, 'kz_store_coupon', 'use_end_time', 'datetime', NULL, '', b'1', 'datetime', '', b'1', b'0', NULL, '可用结束时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (422, 'kz_store_coupon', 'expired_days', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '领取后过期时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (423, 'kz_store_coupon', 'is_show', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '是否开放领取，1,0', NULL);
INSERT INTO `kz_sys_column_config` VALUES (424, 'kz_store_coupon', 'status', 'int', NULL, '', b'0', 'number', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (425, 'kz_store_coupon', 'status_desc', 'varchar', NULL, '', b'0', 'input', '', b'1', b'0', NULL, '状态描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (426, 'kz_store_coupon', 'create_user', 'int', NULL, '', b'0', 'number', '', b'1', b'0', NULL, '操作员', NULL);
INSERT INTO `kz_sys_column_config` VALUES (427, 'kz_store_coupon', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (428, 'kz_store_coupon', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (429, 'kz_store_coupon', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (430, 'kz_store_coupon', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (431, 'kz_store_coupon', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (432, 'kz_store_coupon_product', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (433, 'kz_store_coupon_product', 'coupon_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '优惠券id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (434, 'kz_store_coupon_product', 'product_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '商品id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (435, 'kz_store_coupon_product', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '操作员', NULL);
INSERT INTO `kz_sys_column_config` VALUES (436, 'kz_store_coupon_product', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (437, 'kz_store_coupon_product', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (438, 'kz_store_coupon_product', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (439, 'kz_store_coupon_product', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '逻辑删除', NULL);
INSERT INTO `kz_sys_column_config` VALUES (440, 'kz_store_coupon_product', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (441, 'kz_store_coupon_category', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (442, 'kz_store_coupon_category', 'coupon_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '优惠券id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (443, 'kz_store_coupon_category', 'category_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '分类id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (444, 'kz_store_coupon_category', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '操作员', NULL);
INSERT INTO `kz_sys_column_config` VALUES (445, 'kz_store_coupon_category', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (446, 'kz_store_coupon_category', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (447, 'kz_store_coupon_category', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (448, 'kz_store_coupon_category', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '逻辑删除', NULL);
INSERT INTO `kz_sys_column_config` VALUES (449, 'kz_store_coupon_category', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (450, 'kz_user_coupon', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (451, 'kz_user_coupon', 'uuid', 'varchar', NULL, '', b'0', 'input', '', b'1', b'0', NULL, '唯一识别码', NULL);
INSERT INTO `kz_sys_column_config` VALUES (452, 'kz_user_coupon', 'title', 'varchar', NULL, '', b'0', 'input', '', b'1', b'0', NULL, '优惠券名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (453, 'kz_user_coupon', 'uid', 'int', NULL, '', b'0', 'number', '', b'1', b'0', NULL, '用户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (454, 'kz_user_coupon', 'coupon_id', 'int', NULL, '', b'0', 'number', '', b'1', b'0', NULL, '优惠券id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (455, 'kz_user_coupon', 'coupon_type', 'varchar', NULL, '', b'0', 'input', '', b'1', b'0', NULL, '优惠券类型，满减，折扣，类目，无门槛等', NULL);
INSERT INTO `kz_sys_column_config` VALUES (456, 'kz_user_coupon', 'obtain_type', 'varchar', NULL, '', b'0', 'input', '', b'1', b'0', NULL, '获取方式，领取，发放', NULL);
INSERT INTO `kz_sys_column_config` VALUES (457, 'kz_user_coupon', 'shop_id', 'int', NULL, '', b'0', 'number', '', b'1', b'0', NULL, '店铺id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (458, 'kz_user_coupon', 'price', 'decimal', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '优惠券面值', NULL);
INSERT INTO `kz_sys_column_config` VALUES (459, 'kz_user_coupon', 'min_price', 'decimal', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '最低消费', NULL);
INSERT INTO `kz_sys_column_config` VALUES (460, 'kz_user_coupon', 'discount', 'decimal', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '折扣', NULL);
INSERT INTO `kz_sys_column_config` VALUES (461, 'kz_user_coupon', 'use_start_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '可用时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (462, 'kz_user_coupon', 'use_end_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '可用结束时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (463, 'kz_user_coupon', 'status', 'int', NULL, '', b'0', 'number', '', b'1', b'0', NULL, '使用状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (464, 'kz_user_coupon', 'status_desc', 'varchar', NULL, '', b'0', 'input', '', b'1', b'0', NULL, '使用状态：0->未使用；1->已使用；2->已过期', NULL);
INSERT INTO `kz_sys_column_config` VALUES (465, 'kz_user_coupon', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '操作员', NULL);
INSERT INTO `kz_sys_column_config` VALUES (466, 'kz_user_coupon', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (467, 'kz_user_coupon', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (468, 'kz_user_coupon', 'enabled', 'tinyint', NULL, '', b'0', 'switch', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (469, 'kz_user_coupon', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (470, 'kz_user_coupon', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (471, 'kz_store_service', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '#', NULL);
INSERT INTO `kz_sys_column_config` VALUES (472, 'kz_store_service', 'img_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '图标', NULL);
INSERT INTO `kz_sys_column_config` VALUES (473, 'kz_store_service', 'title', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '服务名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (474, 'kz_store_service', 'description', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '概述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (475, 'kz_store_service', 'is_default', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '是否默认(新增商品时)', NULL);
INSERT INTO `kz_sys_column_config` VALUES (476, 'kz_store_service', 'sort', 'int', NULL, '', b'1', 'number', '', b'0', b'0', NULL, '排序方式(数字越小越靠前)', NULL);
INSERT INTO `kz_sys_column_config` VALUES (477, 'kz_store_service', 'store_id', 'int', NULL, '', b'1', 'number', 'MUL', b'0', b'0', NULL, '店铺id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (478, 'kz_store_service', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '创建时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (479, 'kz_store_service', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (480, 'kz_store_service', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (481, 'kz_store_service', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '启用', NULL);
INSERT INTO `kz_sys_column_config` VALUES (482, 'kz_store_service', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '是否删除(1已删除)', NULL);
INSERT INTO `kz_sys_column_config` VALUES (483, 'kz_store_service', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (484, 'kz_store_comment', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '#', NULL);
INSERT INTO `kz_sys_column_config` VALUES (485, 'kz_store_comment', 'order_id', 'bigint', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '订单ID', NULL);
INSERT INTO `kz_sys_column_config` VALUES (486, 'kz_store_comment', 'product_id', 'bigint', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '产品id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (487, 'kz_store_comment', 'product_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '商品名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (488, 'kz_store_comment', 'attr_key', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '属性名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (489, 'kz_store_comment', 'uid', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '用户ID', NULL);
INSERT INTO `kz_sys_column_config` VALUES (490, 'kz_store_comment', 'level', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '1好评，2中评，3差评', NULL);
INSERT INTO `kz_sys_column_config` VALUES (491, 'kz_store_comment', 'product_level', 'tinyint', NULL, '', b'1', 'switch', 'MUL', b'1', b'0', NULL, '商品分数', NULL);
INSERT INTO `kz_sys_column_config` VALUES (492, 'kz_store_comment', 'delivery_level', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '物流分数', NULL);
INSERT INTO `kz_sys_column_config` VALUES (493, 'kz_store_comment', 'service_level', 'tinyint', NULL, '', b'1', 'switch', 'MUL', b'1', b'0', NULL, '服务分数', NULL);
INSERT INTO `kz_sys_column_config` VALUES (494, 'kz_store_comment', 'description', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '评论内容', NULL);
INSERT INTO `kz_sys_column_config` VALUES (495, 'kz_store_comment', 'append', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '追评', NULL);
INSERT INTO `kz_sys_column_config` VALUES (496, 'kz_store_comment', 'img_urls', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '评论图片', NULL);
INSERT INTO `kz_sys_column_config` VALUES (497, 'kz_store_comment', 'reply_content', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '管理员回复内容', NULL);
INSERT INTO `kz_sys_column_config` VALUES (498, 'kz_store_comment', 'reply_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '管理员回复时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (499, 'kz_store_comment', 'is_reply', 'tinyint', NULL, '', b'0', 'switch', '', b'1', b'0', NULL, '0未回复1已回复', NULL);
INSERT INTO `kz_sys_column_config` VALUES (500, 'kz_store_comment', 'create_time', 'datetime', NULL, '', b'0', 'datetime', 'MUL', b'1', b'0', NULL, '评论时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (501, 'kz_store_comment', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (502, 'kz_store_comment', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (503, 'kz_store_comment', 'enabled', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (504, 'kz_store_comment', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (505, 'kz_store_comment', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (506, 'kz_wx_task', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (507, 'kz_wx_task', 'app_type', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '小程序类型，个人personal，企业company', NULL);
INSERT INTO `kz_sys_column_config` VALUES (508, 'kz_wx_task', 'app_id', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (509, 'kz_wx_task', 'app_secret', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (510, 'kz_wx_task', 'task_id', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '任务id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (511, 'kz_wx_task', 'authorize_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '给用户扫码认证的验证url', NULL);
INSERT INTO `kz_sys_column_config` VALUES (512, 'kz_wx_task', 'payload', 'json', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '请求实体', NULL);
INSERT INTO `kz_sys_column_config` VALUES (513, 'kz_wx_task', 'status', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (514, 'kz_wx_task', 'status_desc', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '状态描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (515, 'kz_wx_task', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '操作员', NULL);
INSERT INTO `kz_sys_column_config` VALUES (516, 'kz_wx_task', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (517, 'kz_wx_task', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (518, 'kz_wx_task', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (519, 'kz_wx_task', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '逻辑删除', NULL);
INSERT INTO `kz_sys_column_config` VALUES (520, 'kz_wx_task', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (521, 'kz_store_seckill', 'id', 'int', NULL, 'auto_increment', NULL, NULL, 'PRI', NULL, b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (522, 'kz_store_seckill', 'activity_name', 'varchar', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '活动名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (523, 'kz_store_seckill', 'start_time', 'datetime', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '秒杀开始时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (524, 'kz_store_seckill', 'end_time', 'datetime', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '秒杀结束时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (525, 'kz_store_seckill', 'rules', 'text', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '规则', NULL);
INSERT INTO `kz_sys_column_config` VALUES (526, 'kz_store_seckill', 'status', 'int', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (527, 'kz_store_seckill', 'status_desc', 'varchar', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '状态描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (528, 'kz_store_seckill', 'create_user', 'int', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '操作员', NULL);
INSERT INTO `kz_sys_column_config` VALUES (529, 'kz_store_seckill', 'create_time', 'datetime', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (530, 'kz_store_seckill', 'update_time', 'datetime', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (531, 'kz_store_seckill', 'enabled', 'tinyint', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (532, 'kz_store_seckill', 'is_delete', 'tinyint', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '逻辑删除', NULL);
INSERT INTO `kz_sys_column_config` VALUES (533, 'kz_store_seckill', 'tenant_id', 'int', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (534, 'kz_store_seckill_detail', 'id', 'int', NULL, 'auto_increment', NULL, NULL, 'PRI', NULL, b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (535, 'kz_store_seckill_detail', 'seckill_id', 'int', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '秒杀活动id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (536, 'kz_store_seckill_detail', 'product_id', 'int', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '商品id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (537, 'kz_store_seckill_detail', 'product_detail_id', 'int', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '商品sku表', NULL);
INSERT INTO `kz_sys_column_config` VALUES (538, 'kz_store_seckill_detail', 'price', 'decimal', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '原价', NULL);
INSERT INTO `kz_sys_column_config` VALUES (539, 'kz_store_seckill_detail', 'min_price', 'decimal', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '秒杀价', NULL);
INSERT INTO `kz_sys_column_config` VALUES (540, 'kz_store_seckill_detail', 'limit_num', 'int', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '限制购买数量 0 不限制', NULL);
INSERT INTO `kz_sys_column_config` VALUES (541, 'kz_store_seckill_detail', 'stock_num', 'int', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '库存数量', NULL);
INSERT INTO `kz_sys_column_config` VALUES (542, 'kz_store_seckill_detail', 'sales', 'int', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '销量', NULL);
INSERT INTO `kz_sys_column_config` VALUES (543, 'kz_store_seckill_detail', 'status', 'int', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (544, 'kz_store_seckill_detail', 'status_desc', 'varchar', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '状态描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (545, 'kz_store_seckill_detail', 'create_user', 'int', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '操作员', NULL);
INSERT INTO `kz_sys_column_config` VALUES (546, 'kz_store_seckill_detail', 'create_time', 'datetime', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (547, 'kz_store_seckill_detail', 'update_time', 'datetime', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (548, 'kz_store_seckill_detail', 'enabled', 'tinyint', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (549, 'kz_store_seckill_detail', 'is_delete', 'tinyint', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '逻辑删除', NULL);
INSERT INTO `kz_sys_column_config` VALUES (550, 'kz_store_seckill_detail', 'tenant_id', 'int', NULL, '', NULL, NULL, '', NULL, b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (551, 'kz_store_recharge', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '商品服务ID', NULL);
INSERT INTO `kz_sys_column_config` VALUES (552, 'kz_store_recharge', 'title', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '显示名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (553, 'kz_store_recharge', 'price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '充值金额', NULL);
INSERT INTO `kz_sys_column_config` VALUES (554, 'kz_store_recharge', 'extra_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '额外赠送', NULL);
INSERT INTO `kz_sys_column_config` VALUES (555, 'kz_store_recharge', 'description', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '概述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (556, 'kz_store_recharge', 'sort', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '排序方式(数字越小越靠前)', NULL);
INSERT INTO `kz_sys_column_config` VALUES (557, 'kz_store_recharge', 'store_id', 'int', NULL, '', b'1', 'number', 'MUL', b'1', b'0', NULL, '店铺id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (558, 'kz_store_recharge', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '创建时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (559, 'kz_store_recharge', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (560, 'kz_store_recharge', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (561, 'kz_store_recharge', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '启用', NULL);
INSERT INTO `kz_sys_column_config` VALUES (562, 'kz_store_recharge', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '是否删除(1已删除)', NULL);
INSERT INTO `kz_sys_column_config` VALUES (563, 'kz_store_recharge', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (564, 'kz_user_tags', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (565, 'kz_user_tags', 'title', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '用户标签', NULL);
INSERT INTO `kz_sys_column_config` VALUES (566, 'kz_user_tags', 'icon', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '图标', NULL);
INSERT INTO `kz_sys_column_config` VALUES (567, 'kz_user_tags', 'img_url', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '图片', NULL);
INSERT INTO `kz_sys_column_config` VALUES (568, 'kz_user_tags', 'description', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '权益信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (569, 'kz_user_tags', 'sort', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '排序', NULL);
INSERT INTO `kz_sys_column_config` VALUES (570, 'kz_user_tags', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (571, 'kz_user_tags', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (572, 'kz_user_tags', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (573, 'kz_user_tags', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (574, 'kz_user_tags', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (575, 'kz_user_tags', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (576, 'kz_store_express', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '#', NULL);
INSERT INTO `kz_sys_column_config` VALUES (577, 'kz_store_express', 'parent_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '运费模板', NULL);
INSERT INTO `kz_sys_column_config` VALUES (578, 'kz_store_express', 'title', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '模板名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (579, 'kz_store_express', 'calc_type', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '计费方式，按件数 num，按重量weight，按体积volume', NULL);
INSERT INTO `kz_sys_column_config` VALUES (580, 'kz_store_express', 'address', 'json', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '地区', NULL);
INSERT INTO `kz_sys_column_config` VALUES (581, 'kz_store_express', 'num', 'int', NULL, '', b'1', 'number', '', b'0', b'0', NULL, '首件', NULL);
INSERT INTO `kz_sys_column_config` VALUES (582, 'kz_store_express', 'weight', 'decimal', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '首重', NULL);
INSERT INTO `kz_sys_column_config` VALUES (583, 'kz_store_express', 'volume', 'decimal', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '首体积', NULL);
INSERT INTO `kz_sys_column_config` VALUES (584, 'kz_store_express', 'price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '运费', NULL);
INSERT INTO `kz_sys_column_config` VALUES (585, 'kz_store_express', 'extra_num', 'int', NULL, '', b'1', 'number', '', b'0', b'0', NULL, '续件', NULL);
INSERT INTO `kz_sys_column_config` VALUES (586, 'kz_store_express', 'extra_weight', 'decimal', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '续重', NULL);
INSERT INTO `kz_sys_column_config` VALUES (587, 'kz_store_express', 'extra_volume', 'decimal', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '续体积', NULL);
INSERT INTO `kz_sys_column_config` VALUES (588, 'kz_store_express', 'extra_price', 'decimal', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '续件费', NULL);
INSERT INTO `kz_sys_column_config` VALUES (589, 'kz_store_express', 'description', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '概述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (590, 'kz_store_express', 'is_default', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '是否默认', NULL);
INSERT INTO `kz_sys_column_config` VALUES (591, 'kz_store_express', 'is_free', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '开启满包邮', NULL);
INSERT INTO `kz_sys_column_config` VALUES (592, 'kz_store_express', 'full_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '满包邮金额', NULL);
INSERT INTO `kz_sys_column_config` VALUES (593, 'kz_store_express', 'is_delivery', 'tinyint', NULL, '', b'1', 'switch', '', b'0', b'0', NULL, '是否配送', NULL);
INSERT INTO `kz_sys_column_config` VALUES (594, 'kz_store_express', 'sort', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '排序', NULL);
INSERT INTO `kz_sys_column_config` VALUES (595, 'kz_store_express', 'store_id', 'int', NULL, '', b'0', 'number', 'MUL', b'0', b'0', NULL, '店铺id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (596, 'kz_store_express', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '创建时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (597, 'kz_store_express', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (598, 'kz_store_express', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (599, 'kz_store_express', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '启用', NULL);
INSERT INTO `kz_sys_column_config` VALUES (600, 'kz_store_express', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '是否删除(1已删除)', NULL);
INSERT INTO `kz_sys_column_config` VALUES (601, 'kz_store_express', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (602, 'kz_store_combination', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (603, 'kz_store_combination', 'activity_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '活动名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (604, 'kz_store_combination', 'product_ids', 'json', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '商品列表', NULL);
INSERT INTO `kz_sys_column_config` VALUES (605, 'kz_store_combination', 'start_time', 'datetime', NULL, '', b'1', 'datetime', '', b'1', b'0', NULL, '开始时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (606, 'kz_store_combination', 'end_time', 'datetime', NULL, '', b'1', 'datetime', '', b'0', b'0', NULL, '结束时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (607, 'kz_store_combination', 'group_num', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '成团人数', NULL);
INSERT INTO `kz_sys_column_config` VALUES (608, 'kz_store_combination', 'expired_time', 'time', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '成团有效期（分钟）', NULL);
INSERT INTO `kz_sys_column_config` VALUES (609, 'kz_store_combination', 'limit_num', 'int', NULL, '', b'1', 'number', '', b'0', b'0', NULL, '数量限制', NULL);
INSERT INTO `kz_sys_column_config` VALUES (610, 'kz_store_combination', 'is_virtual', 'tinyint', NULL, '', b'1', 'switch', '', b'0', b'0', NULL, '虚拟成团', NULL);
INSERT INTO `kz_sys_column_config` VALUES (611, 'kz_store_combination', 'rules', 'text', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '规则', NULL);
INSERT INTO `kz_sys_column_config` VALUES (612, 'kz_store_combination', 'status', 'int', NULL, '', b'1', 'number', '', b'0', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (613, 'kz_store_combination', 'status_desc', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '状态描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (614, 'kz_store_combination', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '操作员', NULL);
INSERT INTO `kz_sys_column_config` VALUES (615, 'kz_store_combination', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (616, 'kz_store_combination', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (617, 'kz_store_combination', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (618, 'kz_store_combination', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '逻辑删除', NULL);
INSERT INTO `kz_sys_column_config` VALUES (619, 'kz_store_combination', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (620, 'kz_store_combination_log', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (621, 'kz_store_combination_log', 'group_id', 'int', NULL, '', b'1', 'number', '', b'1', b'1', NULL, '团id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (622, 'kz_store_combination_log', 'combination_id', 'int', NULL, '', b'1', 'number', '', b'1', b'1', NULL, '拼团活动id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (623, 'kz_store_combination_log', 'activity_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '活动名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (624, 'kz_store_combination_log', 'product_id', 'json', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '商品', NULL);
INSERT INTO `kz_sys_column_config` VALUES (625, 'kz_store_combination_log', 'product_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '商品名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (626, 'kz_store_combination_log', 'img_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '商品图片', NULL);
INSERT INTO `kz_sys_column_config` VALUES (627, 'kz_store_combination_log', 'price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '原价', NULL);
INSERT INTO `kz_sys_column_config` VALUES (628, 'kz_store_combination_log', 'combination_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '拼团加', NULL);
INSERT INTO `kz_sys_column_config` VALUES (629, 'kz_store_combination_log', 'start_time', 'datetime', NULL, '', b'1', 'datetime', '', b'1', b'0', NULL, '开始时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (630, 'kz_store_combination_log', 'end_time', 'datetime', NULL, '', b'1', 'datetime', '', b'1', b'0', NULL, '结束时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (631, 'kz_store_combination_log', 'limit_num', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '数量限制', NULL);
INSERT INTO `kz_sys_column_config` VALUES (632, 'kz_store_combination_log', 'group_num', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '成团人数', NULL);
INSERT INTO `kz_sys_column_config` VALUES (633, 'kz_store_combination_log', 'expired_time', 'datetime', NULL, '', b'1', 'datetime', '', b'1', b'0', NULL, '结束时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (634, 'kz_store_combination_log', 'is_virtual', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '虚拟成团', NULL);
INSERT INTO `kz_sys_column_config` VALUES (635, 'kz_store_combination_log', 'is_leader', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '团长', NULL);
INSERT INTO `kz_sys_column_config` VALUES (636, 'kz_store_combination_log', 'uid', 'int', NULL, '', b'1', 'number', '', b'1', b'1', NULL, '用户', NULL);
INSERT INTO `kz_sys_column_config` VALUES (637, 'kz_store_combination_log', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '操作员', NULL);
INSERT INTO `kz_sys_column_config` VALUES (638, 'kz_store_combination_log', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (639, 'kz_store_combination_log', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (640, 'kz_store_combination_log', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (641, 'kz_store_combination_log', 'is_success', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '是否成功', NULL);
INSERT INTO `kz_sys_column_config` VALUES (642, 'kz_store_combination_log', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '逻辑删除', NULL);
INSERT INTO `kz_sys_column_config` VALUES (643, 'kz_store_combination_log', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (644, 'kz_store_bargain', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (645, 'kz_store_bargain', 'activity_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '活动名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (646, 'kz_store_bargain', 'price_range', 'json', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '每次砍价区间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (647, 'kz_store_bargain', 'product_ids', 'json', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '商品列表', NULL);
INSERT INTO `kz_sys_column_config` VALUES (648, 'kz_store_bargain', 'share_title', 'varchar', NULL, '', b'1', 'input', '', b'0', b'0', NULL, '分享标题', NULL);
INSERT INTO `kz_sys_column_config` VALUES (649, 'kz_store_bargain', 'start_time', 'datetime', NULL, '', b'1', 'datetime', '', b'1', b'0', NULL, '活动开始时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (650, 'kz_store_bargain', 'end_time', 'datetime', NULL, '', b'1', 'datetime', '', b'1', b'0', NULL, '结束时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (651, 'kz_store_bargain', 'expire_minute', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '持续时间（分钟）', NULL);
INSERT INTO `kz_sys_column_config` VALUES (652, 'kz_store_bargain', 'rules', 'text', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '活动规则', NULL);
INSERT INTO `kz_sys_column_config` VALUES (653, 'kz_store_bargain', 'status', 'int', NULL, '', b'1', 'number', '', b'0', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (654, 'kz_store_bargain', 'status_desc', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '状态描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (655, 'kz_store_bargain', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '操作员', NULL);
INSERT INTO `kz_sys_column_config` VALUES (656, 'kz_store_bargain', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (657, 'kz_store_bargain', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (658, 'kz_store_bargain', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (659, 'kz_store_bargain', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '逻辑删除', NULL);
INSERT INTO `kz_sys_column_config` VALUES (660, 'kz_store_bargain', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (661, 'kz_store_bargain_log', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (662, 'kz_store_bargain_log', 'parent_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '发起id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (663, 'kz_store_bargain_log', 'uid', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '用户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (664, 'kz_store_bargain_log', 'product_id', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '砍价商品id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (665, 'kz_store_bargain_log', 'product_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '商品名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (666, 'kz_store_bargain_log', 'img_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '图片', NULL);
INSERT INTO `kz_sys_column_config` VALUES (667, 'kz_store_bargain_log', 'price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '当前价格', NULL);
INSERT INTO `kz_sys_column_config` VALUES (668, 'kz_store_bargain_log', 'bargain_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '保护价', NULL);
INSERT INTO `kz_sys_column_config` VALUES (669, 'kz_store_bargain_log', 'cut_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '砍掉的金额', NULL);
INSERT INTO `kz_sys_column_config` VALUES (670, 'kz_store_bargain_log', 'price_range', 'json', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '每次砍价区间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (671, 'kz_store_bargain_log', 'start_time', 'datetime', NULL, '', b'1', 'datetime', '', b'1', b'0', NULL, '活动开始时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (672, 'kz_store_bargain_log', 'end_time', 'datetime', NULL, '', b'1', 'datetime', '', b'1', b'0', NULL, '结束时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (673, 'kz_store_bargain_log', 'status', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (674, 'kz_store_bargain_log', 'status_desc', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '状态描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (675, 'kz_store_bargain_log', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '操作员', NULL);
INSERT INTO `kz_sys_column_config` VALUES (676, 'kz_store_bargain_log', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (677, 'kz_store_bargain_log', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (678, 'kz_store_bargain_log', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (679, 'kz_store_bargain_log', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '逻辑删除', NULL);
INSERT INTO `kz_sys_column_config` VALUES (680, 'kz_store_bargain_log', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (681, 'kz_store_after_sales', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (682, 'kz_store_after_sales', 'product_info', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '商品信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (683, 'kz_store_after_sales', 'order_id', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '订单id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (684, 'kz_store_after_sales', 'uid', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '用户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (685, 'kz_store_after_sales', 'pay_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '实际支付金额', NULL);
INSERT INTO `kz_sys_column_config` VALUES (686, 'kz_store_after_sales', 'refund_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '退款金额', NULL);
INSERT INTO `kz_sys_column_config` VALUES (687, 'kz_store_after_sales', 'address_info', 'json', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '收件信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (688, 'kz_store_after_sales', 'pickup_info', 'json', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '取件信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (689, 'kz_store_after_sales', 'delivery_info', 'json', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '快递信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (690, 'kz_store_after_sales', 'status', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (691, 'kz_store_after_sales', 'status_desc', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '状态描述', NULL);
INSERT INTO `kz_sys_column_config` VALUES (692, 'kz_store_after_sales', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '操作员', NULL);
INSERT INTO `kz_sys_column_config` VALUES (693, 'kz_store_after_sales', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (694, 'kz_store_after_sales', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '更新时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (695, 'kz_store_after_sales', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (696, 'kz_store_after_sales', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '逻辑删除', NULL);
INSERT INTO `kz_sys_column_config` VALUES (697, 'kz_store_after_sales', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (698, 'kz_sys_address', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '#', NULL);
INSERT INTO `kz_sys_column_config` VALUES (699, 'kz_sys_address', 'phone', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '电话', NULL);
INSERT INTO `kz_sys_column_config` VALUES (700, 'kz_sys_address', 'nick_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '收件人', NULL);
INSERT INTO `kz_sys_column_config` VALUES (701, 'kz_sys_address', 'postal_code', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (702, 'kz_sys_address', 'address', 'json', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '省市区', NULL);
INSERT INTO `kz_sys_column_config` VALUES (703, 'kz_sys_address', 'address_detail', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '详细地址', NULL);
INSERT INTO `kz_sys_column_config` VALUES (704, 'kz_sys_address', 'tags', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (705, 'kz_sys_address', 'is_default', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '默认', NULL);
INSERT INTO `kz_sys_column_config` VALUES (706, 'kz_sys_address', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (707, 'kz_sys_address', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (708, 'kz_sys_address', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (709, 'kz_sys_address', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (710, 'kz_sys_address', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (711, 'kz_sys_address', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (712, 'kz_withdraw', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (713, 'kz_withdraw', 'total_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '提现金额', NULL);
INSERT INTO `kz_sys_column_config` VALUES (714, 'kz_withdraw', 'pay_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '打款金额', NULL);
INSERT INTO `kz_sys_column_config` VALUES (715, 'kz_withdraw', 'qrcode_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'1', NULL, '提现到的账户', NULL);
INSERT INTO `kz_sys_column_config` VALUES (716, 'kz_withdraw', 'status', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '提现申请状态 -1 取消 0 已提交 1 驳回 2 通过已打款，完成', NULL);
INSERT INTO `kz_sys_column_config` VALUES (717, 'kz_withdraw', 'status_desc', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '提现状态', NULL);
INSERT INTO `kz_sys_column_config` VALUES (718, 'kz_withdraw', 'status_reason', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '提现拒绝原因', NULL);
INSERT INTO `kz_sys_column_config` VALUES (719, 'kz_withdraw', 'comments', 'text', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '备注信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (720, 'kz_withdraw', 'order_id', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '打款订单id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (721, 'kz_withdraw', 'order_time', 'datetime', NULL, '', b'1', 'datetime', '', b'1', b'0', NULL, '打款时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (722, 'kz_withdraw', 'order_img_url', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '支付截图', NULL);
INSERT INTO `kz_sys_column_config` VALUES (723, 'kz_withdraw', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (724, 'kz_withdraw', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (725, 'kz_withdraw', 'uid', 'int', NULL, '', b'1', 'number', '', b'1', b'1', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (726, 'kz_withdraw', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (727, 'kz_withdraw', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (728, 'kz_withdraw', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (729, 'kz_printer', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '#', NULL);
INSERT INTO `kz_sys_column_config` VALUES (730, 'kz_printer', 'store_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '所属门店', NULL);
INSERT INTO `kz_sys_column_config` VALUES (731, 'kz_printer', 'printer_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '打印机名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (732, 'kz_printer', 'print_num', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '打印份数', NULL);
INSERT INTO `kz_sys_column_config` VALUES (733, 'kz_printer', 'printer_type', 'varchar', NULL, '', b'1', 'select', '', b'1', b'0', NULL, '打印机类型', NULL);
INSERT INTO `kz_sys_column_config` VALUES (734, 'kz_printer', 'sn_id', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '序列哈', NULL);
INSERT INTO `kz_sys_column_config` VALUES (735, 'kz_printer', 'sn_secret', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '设备密钥', NULL);
INSERT INTO `kz_sys_column_config` VALUES (736, 'kz_printer', 'app_key', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, 'appId', NULL);
INSERT INTO `kz_sys_column_config` VALUES (737, 'kz_printer', 'app_secret', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, 'app密钥', NULL);
INSERT INTO `kz_sys_column_config` VALUES (738, 'kz_printer', 'position', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '打印机位置', NULL);
INSERT INTO `kz_sys_column_config` VALUES (739, 'kz_printer', 'is_online', 'tinyint', NULL, '', b'0', 'switch', '', b'1', b'0', NULL, '是否在线', NULL);
INSERT INTO `kz_sys_column_config` VALUES (740, 'kz_printer', 'last_check_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '最近一次检测时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (741, 'kz_printer', 'description', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '备注', NULL);
INSERT INTO `kz_sys_column_config` VALUES (742, 'kz_printer', 'sort', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '排序', NULL);
INSERT INTO `kz_sys_column_config` VALUES (743, 'kz_printer', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '添加时间', NULL);
INSERT INTO `kz_sys_column_config` VALUES (744, 'kz_printer', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (745, 'kz_printer', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (746, 'kz_printer', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (747, 'kz_printer', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (748, 'kz_printer', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (749, 'kz_print_template', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '#', NULL);
INSERT INTO `kz_sys_column_config` VALUES (750, 'kz_print_template', 'store_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '所属门店', NULL);
INSERT INTO `kz_sys_column_config` VALUES (751, 'kz_print_template', 'template_name', 'varchar', NULL, '', b'1', 'input', '', b'1', b'1', NULL, '模板名称', NULL);
INSERT INTO `kz_sys_column_config` VALUES (752, 'kz_print_template', 'template_type', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '模板类型', NULL);
INSERT INTO `kz_sys_column_config` VALUES (753, 'kz_print_template', 'description', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '描述信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (754, 'kz_print_template', 'template', 'text', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '模板内容', NULL);
INSERT INTO `kz_sys_column_config` VALUES (755, 'kz_print_template', 'details', 'json', NULL, '', b'1', NULL, '', b'0', b'0', NULL, '配置详情', NULL);
INSERT INTO `kz_sys_column_config` VALUES (756, 'kz_print_template', 'printer_ids', 'json', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '打印机', NULL);
INSERT INTO `kz_sys_column_config` VALUES (757, 'kz_print_template', 'sort', 'int', NULL, '', b'1', 'number', '', b'1', b'0', NULL, '排序', NULL);
INSERT INTO `kz_sys_column_config` VALUES (758, 'kz_print_template', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (759, 'kz_print_template', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (760, 'kz_print_template', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (761, 'kz_print_template', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '是否启用', NULL);
INSERT INTO `kz_sys_column_config` VALUES (762, 'kz_print_template', 'is_default', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '是否模板', NULL);
INSERT INTO `kz_sys_column_config` VALUES (763, 'kz_print_template', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (764, 'kz_print_template', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '租户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (765, 'kz_user_brokerage_bill', 'id', 'int', NULL, 'auto_increment', b'0', 'number', 'PRI', b'1', b'0', NULL, '#', NULL);
INSERT INTO `kz_sys_column_config` VALUES (766, 'kz_user_brokerage_bill', 'action', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '操作类型，签到，邀请用户，购买商品', NULL);
INSERT INTO `kz_sys_column_config` VALUES (767, 'kz_user_brokerage_bill', 'title', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '账单原由，明细等等', NULL);
INSERT INTO `kz_sys_column_config` VALUES (768, 'kz_user_brokerage_bill', 'amount', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '金额', NULL);
INSERT INTO `kz_sys_column_config` VALUES (769, 'kz_user_brokerage_bill', 'total_amount', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '总额', NULL);
INSERT INTO `kz_sys_column_config` VALUES (770, 'kz_user_brokerage_bill', 'direction', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '增加 -，或者减少 +', NULL);
INSERT INTO `kz_sys_column_config` VALUES (771, 'kz_user_brokerage_bill', 'description', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '描述信息', NULL);
INSERT INTO `kz_sys_column_config` VALUES (772, 'kz_user_brokerage_bill', 'attr', 'varchar', NULL, '', b'1', 'input', '', b'1', b'0', NULL, '备用字段', NULL);
INSERT INTO `kz_sys_column_config` VALUES (773, 'kz_user_brokerage_bill', 'uid', 'int', NULL, '', b'1', 'number', '', b'1', b'1', NULL, '用户id', NULL);
INSERT INTO `kz_sys_column_config` VALUES (774, 'kz_user_brokerage_bill', 'create_user', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (775, 'kz_user_brokerage_bill', 'create_time', 'datetime', NULL, '', b'0', 'datetime', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (776, 'kz_user_brokerage_bill', 'update_time', 'datetime', NULL, '', b'0', 'datetime', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (777, 'kz_user_brokerage_bill', 'enabled', 'tinyint', NULL, '', b'1', 'switch', '', b'1', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (778, 'kz_user_brokerage_bill', 'is_delete', 'tinyint', NULL, '', b'0', 'switch', '', b'0', b'0', NULL, '', NULL);
INSERT INTO `kz_sys_column_config` VALUES (779, 'kz_user_brokerage_bill', 'tenant_id', 'int', NULL, '', b'0', 'number', '', b'0', b'0', NULL, '', NULL);

-- ----------------------------
-- Table structure for kz_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_config`;
CREATE TABLE `kz_sys_config`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段名称',
  `group_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分组',
  `k` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `v` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件类型',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '描述信息',
  `is_hidden` tinyint(1) NULL DEFAULT NULL COMMENT '是否隐藏，0 不隐藏，1 隐藏，后台使用',
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '系统删除',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_field`(`k`) USING BTREE COMMENT '字段唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统基础配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_sys_config
-- ----------------------------
INSERT INTO `kz_sys_config` VALUES (1, '套餐说明', NULL, 'packageDesc', '1、套餐购买默认有效期一个月\n2、企业升级套餐时退差价的方式返回到账户，计算公式：退款金额 =（付费金额/每月天数）* 未使用天数', 'textarea', '', 0, 1, 1, '2022-12-01 22:00:55', '2022-12-01 22:00:57', 0, 0);
INSERT INTO `kz_sys_config` VALUES (2, '职位审核', NULL, 'isAudit', '0', 'switch', NULL, 0, 1, 1, '2022-12-07 17:33:45', '2022-12-07 17:33:47', 0, 0);
INSERT INTO `kz_sys_config` VALUES (3, '默认套餐', NULL, 'defaultPackage', '8', 'input', NULL, 0, NULL, 1, '2022-12-07 17:33:45', '2022-12-07 17:33:45', 0, 0);
INSERT INTO `kz_sys_config` VALUES (4, '试用', NULL, 'onTrailLimit', '60', 'input', NULL, 0, NULL, 1, '2022-12-07 17:33:45', '2022-12-07 17:33:45', 0, 0);
INSERT INTO `kz_sys_config` VALUES (5, '付费', NULL, 'paidLimit', '200', 'input', NULL, NULL, NULL, 1, '2022-12-07 17:33:45', '2022-12-07 17:33:45', 0, 0);
INSERT INTO `kz_sys_config` VALUES (6, '审核模式', NULL, 'reviewMode', '1', 'switch', NULL, NULL, NULL, 1, '2022-12-07 17:33:45', '2022-12-07 17:33:45', 0, 0);

-- ----------------------------
-- Table structure for kz_sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_dept`;
CREATE TABLE `kz_sys_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织名称',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父级ID',
  `hierarchy` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '层级信息，从根节点到当前节点的最短路径，使用-分割节点ID',
  `depth` int(1) NULL DEFAULT NULL COMMENT '当前节点深度',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  `sort` int(1) NULL DEFAULT 1 COMMENT '排序字段，由小到大',
  `admin_id` int(11) NULL DEFAULT NULL COMMENT '部门负责人',
  `create_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enabled` tinyint(2) NULL DEFAULT 1,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  `is_delete` tinyint(2) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '组织架构' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_sys_dept
-- ----------------------------
INSERT INTO `kz_sys_dept` VALUES (1, '平台', 0, '1', 1, '平台描述', 1, 1, NULL, '2023-03-02 11:56:05', '2023-03-21 11:49:57', 1, 0, 0);
INSERT INTO `kz_sys_dept` VALUES (2, '杭州市市场监督管理局', 1, NULL, NULL, '杭州市市场监督管理局', 1, 100, '1', '2023-03-21 11:30:29', '2023-03-29 19:50:25', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_dict`;
CREATE TABLE `kz_sys_dict`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父级ID',
  `dict_name` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `dict_value` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `extra` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '扩展信息',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序用',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '是否可用，0 或 1',
  `is_delete` tinyint(2) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 328 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_sys_dict
-- ----------------------------
INSERT INTO `kz_sys_dict` VALUES (262, 0, '认证状态', 'VERIFY_STATUS', NULL, '2022-04-05 19:17:19', '2022-04-05 19:17:19', 1, 2, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (263, 262, '待认证', '0', NULL, '2022-04-05 19:18:58', '2022-04-07 11:00:22', 1, 2, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (264, 262, '认证通过', '1', NULL, '2022-04-05 19:19:08', '2022-04-05 19:30:47', 1, 0, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (265, 262, '认证不通过', '2', NULL, '2022-04-05 19:19:19', '2022-04-05 19:30:51', 1, 3, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (269, 0, '行业类别', 'MERCHANT_CATEGORY', NULL, '2022-04-07 14:18:27', '2022-04-07 14:18:27', 1, 3, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (279, 269, '餐饮饭店', '70001', NULL, '2022-04-07 14:45:08', '2022-04-07 14:50:00', 1, 1, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (280, 269, '超市五金', '70002', NULL, '2022-04-07 14:45:17', '2022-04-07 14:50:05', 1, 2, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (281, 269, '酒店住宿、娱乐', '70003', NULL, '2022-04-07 14:45:23', '2022-04-07 14:50:10', 1, 4, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (282, 269, '下午茶', '70004', NULL, '2022-04-07 14:45:31', '2022-04-07 14:50:15', 1, 3, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (283, 269, '西餐', '70005', NULL, '2022-04-07 14:45:38', '2022-04-07 14:50:20', 1, 5, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (284, 269, '家电服务', '70006', NULL, '2022-04-07 14:45:48', '2022-04-07 14:50:26', 1, 6, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (285, 269, '炸鸡汉堡', '70007', NULL, '2022-04-07 14:46:03', '2022-04-07 14:50:30', 1, 7, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (309, 0, '快递公司', 'DELIVERY', NULL, '2023-05-17 11:23:51', '2023-05-17 11:23:51', 1, 3, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (310, 309, '顺丰快递', 'SF', NULL, '2023-05-17 11:33:25', '2023-05-17 15:46:17', 1, 1, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (311, 309, '申通快递', 'STO', NULL, '2023-05-17 11:33:37', '2023-05-17 15:46:22', 1, 2, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (312, 309, '圆通快递', 'YTO', NULL, '2023-05-17 11:33:48', '2023-05-17 15:46:25', 1, 3, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (313, 309, '中通快递', 'ZTO', NULL, '2023-05-17 11:41:21', '2023-05-17 15:46:28', 1, 4, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (314, 309, '韵达快递', 'YD', NULL, '2023-05-17 11:41:35', '2023-05-17 15:46:31', 1, 5, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (315, 309, '京东快递', 'JD', NULL, '2023-05-17 11:41:49', '2023-05-17 15:46:34', 1, 6, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (316, 309, 'EMS', 'EMS', NULL, '2023-05-17 11:42:06', '2023-05-17 11:42:06', 1, 7, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (317, 309, '极兔速递', 'JTSD', NULL, '2023-05-17 11:45:16', '2023-05-17 14:35:21', 1, 8, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (318, 309, '邮政快递包裹', 'YZPY', NULL, '2023-05-17 14:34:54', '2023-05-17 14:34:54', 1, 9, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (319, 0, '退货原因', 'REFUND_REASON', NULL, '2023-06-12 18:12:00', '2023-06-12 18:12:00', 1, 4, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (320, 319, '拍错/多拍/不喜欢', '拍错/多拍/不喜欢', NULL, '2023-06-12 18:17:54', '2023-06-12 18:17:54', 1, 1, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (321, 319, '商品与描述不符', '商品与描述不符', NULL, '2023-06-12 18:18:48', '2023-06-12 18:18:48', 1, 2, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (322, 319, '卖家发错货', '卖家发错货', NULL, '2023-06-12 18:19:03', '2023-06-12 18:19:03', 1, 3, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (323, 319, '少件/破损/污渍等', '少件/破损/污渍等', NULL, '2023-06-12 18:19:45', '2023-06-12 18:19:45', 1, 4, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (324, 319, '商品质量问题', '商品质量问题', NULL, '2023-06-12 18:19:56', '2023-06-12 18:19:56', 1, 5, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (325, 319, '其他', '其他', NULL, '2023-06-12 18:20:14', '2023-06-12 18:21:31', 1, 100, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (326, 319, '快递一直未收到', '快递一直未收到', NULL, '2023-06-12 18:21:15', '2023-06-12 18:21:15', 1, 6, 1, 0);
INSERT INTO `kz_sys_dict` VALUES (327, 319, '未按规定时间发货', '未按规定时间发货', NULL, '2023-06-12 18:21:44', '2023-06-12 18:21:44', 1, 6, 1, 0);

-- ----------------------------
-- Table structure for kz_sys_directory
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_directory`;
CREATE TABLE `kz_sys_directory`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注信息',
  `target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '平台，机构，老师',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  `is_delete` tinyint(2) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `un_name`(`title`, `tenant_id`) USING BTREE COMMENT '文件夹名称唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '目录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_sys_directory
-- ----------------------------
INSERT INTO `kz_sys_directory` VALUES (33, '默认', NULL, NULL, 0, NULL, '2023-03-31 17:54:53', '2023-05-31 11:10:30', 1, 0, 0);
INSERT INTO `kz_sys_directory` VALUES (34, '等级图标', NULL, NULL, 7, NULL, '2023-04-25 16:06:48', '2023-05-31 11:10:30', 1, 0, 0);
INSERT INTO `kz_sys_directory` VALUES (35, '个人中心图标', NULL, NULL, 6, 1, '2023-05-09 16:38:16', '2023-05-31 11:10:30', 1, 0, 0);
INSERT INTO `kz_sys_directory` VALUES (36, '订单中心', NULL, NULL, 5, 1, '2023-05-09 19:28:27', '2023-05-31 11:10:30', 1, 0, 0);
INSERT INTO `kz_sys_directory` VALUES (37, '灰色图标', NULL, NULL, 4, 1, '2023-05-16 09:57:43', '2023-05-31 11:10:30', 1, 0, 0);
INSERT INTO `kz_sys_directory` VALUES (38, '导航图标', NULL, NULL, 3, 1, '2023-05-16 15:39:55', '2023-05-31 11:10:30', 1, 0, 0);
INSERT INTO `kz_sys_directory` VALUES (39, '海报', NULL, NULL, 8, 1, '2023-05-18 18:46:59', '2023-05-31 11:10:30', 1, 0, 0);
INSERT INTO `kz_sys_directory` VALUES (40, '轮播图', NULL, NULL, 2, 1, '2023-05-30 15:57:57', '2023-05-31 11:10:30', 1, 0, 0);
INSERT INTO `kz_sys_directory` VALUES (41, '有色图标', NULL, NULL, 1, 1, '2023-05-30 18:03:12', '2023-05-31 11:10:30', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_sys_form
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_form`;
CREATE TABLE `kz_sys_form`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `form_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `form_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '系统删除',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `un_form`(`form_id`) USING BTREE COMMENT '表单key唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for kz_sys_gen_config
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_gen_config`;
CREATE TABLE `kz_sys_gen_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `cover` bit(1) NULL DEFAULT NULL COMMENT '是否覆盖',
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `pack` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '至于哪个包下',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端代码生成的路径',
  `api_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端Api文件路径',
  `prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表前缀',
  `api_alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口名称',
  `page_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编辑模式，page，dialog， drawer',
  `super_class` tinyint(1) NULL DEFAULT NULL COMMENT '是否继承超类',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `un_table`(`table_name`) USING BTREE COMMENT '表唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成器配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_sys_gen_config
-- ----------------------------
INSERT INTO `kz_sys_gen_config` VALUES (2, 'kz_merchant', 'songsir', b'1', 'template', 'com.mtstore.server', 'template', 'merchant\\', 'kz_', '商家', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (3, 'kz_sys_directory', 'songsir', NULL, '视频目录', 'com.mtstore.server', 'sys/directory', 'sys/directory\\', 'kz_', '视频目录', NULL, NULL);
INSERT INTO `kz_sys_gen_config` VALUES (4, 'kz_store_product', 'songsir', b'1', '普通商品', 'com.mtstore.server', 'store/product', 'store/product\\', 'kz_', '普通商品', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (5, 'kz_sys_gen_config', 'songsir', NULL, '代码生成器配置', 'com.mtstore.server', 'sys/gen/config', 'sys/gen/config\\', 'kz_', '代码生成器配置', NULL, NULL);
INSERT INTO `kz_sys_gen_config` VALUES (6, 'kz_user_level', 'songsir', b'1', 'template', 'com.mtstore.server', 'user\\level', 'user\\level\\', 'kz_', '用户等级', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (7, 'kz_user_group', 'songsir', NULL, 'template', 'com.mtstore.server', 'user\\group', 'user\\group\\', 'kz_', '用户等级', NULL, NULL);
INSERT INTO `kz_sys_gen_config` VALUES (8, 'kz_store_attr_template', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\attr\\template', 'store\\attr\\template\\', 'kz_', '规格模板', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (9, 'kz_store_product_attr', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\product\\attr', 'store\\product\\attr\\', 'kz_', '商品属性值', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (10, 'kz_diy_page', 'songsir', b'1', 'template', 'com.mtstore.server', 'diy\\page', 'diy\\page\\', 'kz_', 'DIY页面', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (11, 'kz_agent', 'songsir', b'1', 'template', 'com.mtstore.server', 'agent', 'agent\\', 'kz_', '代理商', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (12, 'kz_store_cart', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\cart', 'store\\cart\\', 'kz_', '商城购物车', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (14, 'kz_user_address', 'songsir', b'1', 'template', 'com.mtstore.server', 'user\\address', 'user\\address\\', 'kz_', '用户收货地址', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (16, 'kz_store_order', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\order', 'store\\order\\', 'kz_', '商城订单', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (18, 'kz_store_order_detail', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\order\\detail', 'store\\order\\detail\\', 'kz_', '商城订单详情，快照', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (20, 'kz_store_order_status', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\order\\status', 'store\\order\\status\\', 'kz_', '商城订单状态记录', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (21, 'kz_diy_path', 'songsir', b'1', 'template', 'com.mtstore.server', 'diy\\path', 'diy\\path\\', 'kz_', '页面路径', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (23, 'kz_article', 'songsir', b'1', 'template', 'com.mtstore.server', 'article', 'article\\', 'kz_', '文章资讯', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (24, 'kz_article_category', 'songsir', b'1', 'template', 'com.mtstore.server', 'article\\category', 'article\\category\\', 'kz_', '文章分类', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (25, 'kz_store', 'songsir', b'1', 'template', 'com.mtstore.server', 'store', 'store\\', 'kz_', '门店', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (26, 'kz_store_coupon', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\coupon', 'store\\coupon\\', 'kz_', '商城优惠券', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (27, 'kz_store_coupon_product', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\coupon\\product', 'store\\coupon\\product\\', 'kz_', '商城-优惠券商品关系', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (28, 'kz_store_coupon_category', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\coupon\\category', 'store\\coupon\\category\\', 'kz_', '商城-优惠券分类关系', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (29, 'kz_user_coupon', 'songsir', b'1', 'template', 'com.mtstore.server', 'user\\coupon', 'user\\coupon\\', 'kz_', '用户优惠券', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (30, 'kz_store_service', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\service', 'store\\service\\', 'kz_', '商城商品服务', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (31, 'kz_store_comment', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\comment', 'store\\comment\\', 'kz_', '商城商品评价', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (32, 'kz_wx_task', 'songsir', b'1', 'template', 'com.mtstore.server', 'wx\\task', 'wx\\task\\', 'kz_', '应用管理', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (33, 'kz_store_seckill', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\seckill', 'store\\seckill\\', 'kz_', '商城-秒杀会场', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (34, 'kz_store_seckill_detail', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\seckill\\detail', 'store\\seckill\\detail\\', 'kz_', '商城-秒杀商品关系\r\n', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (35, 'kz_store_recharge', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\recharge', 'store\\recharge\\', 'kz_', '商城充值方案', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (37, 'kz_user_tags', 'songsir', b'1', 'template', 'com.mtstore.server', 'user\\tags', 'user\\tags\\', 'kz_', '用户标签', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (39, 'kz_store_express', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\express', 'store\\express\\', 'kz_', '商城运费模板', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (40, 'kz_store_combination', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\combination', 'store\\combination\\', 'kz_', '商城-拼团', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (41, 'kz_store_combination_log', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\combination\\log', 'store\\combination\\log\\', 'kz_', '商城-拼团记录', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (42, 'kz_store_bargain', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\bargain', 'store\\bargain\\', 'kz_', '商城-砍价', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (43, 'kz_store_bargain_log', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\bargain\\log', 'store\\bargain\\log\\', 'kz_', '商城-砍价记录', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (44, 'kz_store_after_sales', 'songsir', b'1', 'template', 'com.mtstore.server', 'store\\after\\sales', 'store\\after\\sales\\', 'kz_', '商城-售后记录', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (45, 'kz_sys_address', 'songsir', b'1', 'template', 'com.mtstore.server', 'sys\\address', 'sys\\address\\', 'kz_', '系统售后收货地址', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (46, 'kz_withdraw', 'songsir', b'1', 'template', 'com.mtstore.server', 'withdraw', 'withdraw\\', 'kz_', '提现分润记录', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (47, 'kz_printer', 'songsir', b'1', 'template', 'com.mtstore.server', 'printer', 'printer\\', 'kz_', '打印机', 'dialog', 1);
INSERT INTO `kz_sys_gen_config` VALUES (48, 'kz_print_template', 'songsir', b'1', 'template', 'com.mtstore.server', 'print\\template', 'print\\template\\', 'kz_', '打印模板', 'page', 1);
INSERT INTO `kz_sys_gen_config` VALUES (49, 'kz_user_brokerage_bill', 'songsir', b'1', 'template', 'com.mtstore.server', 'user\\brokerage\\bill', 'user\\brokerage\\bill\\', 'kz_', '用户佣金记录', 'dialog', 1);

-- ----------------------------
-- Table structure for kz_sys_logging_log
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_logging_log`;
CREATE TABLE `kz_sys_logging_log`  (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for kz_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_menu`;
CREATE TABLE `kz_sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父级ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单显示名称',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权标识',
  `path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `target` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '打开方式 (1组件 2内链 3外链)',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对应Vue等组件',
  `uri` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '定位标识 (打开方式为组件时其值为组件相对路径，其他为URL地址)',
  `sort` int(11) NULL DEFAULT 1 COMMENT '显示排序',
  `no_keep_alive` tinyint(1) NULL DEFAULT 0 COMMENT '组件缓存：0-开启，1-关闭',
  `no_column` tinyint(1) NULL DEFAULT NULL COMMENT '隐藏二级菜单',
  `dynamic_new_tab` tinyint(1) NULL DEFAULT 0 COMMENT '是否是动态组件',
  `hidden` tinyint(1) NULL DEFAULT 0 COMMENT '隐藏菜单:  0-否，1-是',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '菜单类型 （0目录，1菜单，2按钮）',
  `remarks` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态，是否可用0不可用，1可用',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除标识，未删除为 0，已删除为删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 293 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_sys_menu
-- ----------------------------
INSERT INTO `kz_sys_menu` VALUES (1, 0, 'Root', '工作台', 'apps-2-line', NULL, '/', '1', 'Layout', '', 0, 0, NULL, 0, 0, 0, NULL, 10, '2021-11-18 17:25:45', '2023-03-13 15:50:35', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (2, 0, 'System', '系统', 'dashboard-line', NULL, '/system', '1', 'Layout', '', 99, 0, NULL, 0, 0, 0, NULL, 10, '2021-11-18 17:19:24', '2023-03-13 15:52:05', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (3, 1, 'Index', '首页', NULL, NULL, 'index', '1', '@/views/index', '', 1, 0, NULL, 0, 0, 0, NULL, NULL, '2021-11-18 17:26:36', '2022-12-05 20:59:51', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (4, 2, 'User', '系统用户', NULL, NULL, '/system/user', '1', '@/views/system/user', '', 1, 0, NULL, 0, 0, 0, NULL, 10, '2021-11-18 17:27:38', '2021-11-19 14:44:16', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (6, 5, 'AdvanceTable', '综合表格', NULL, NULL, 'advanceTable', '1', '@/views/vab/table/advanceTable', '', 1, 0, NULL, 0, 0, 0, NULL, 10, '2021-11-18 18:12:03', '2021-11-18 18:12:08', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (7, 184, 'Menu', '系统菜单', NULL, NULL, '/system/menu', '1', '@/views/system/menu/index', '', 1, 0, NULL, 0, 0, 0, NULL, NULL, NULL, '2023-03-30 15:17:07', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (8, 2, 'Role', '角色管理', NULL, NULL, '/system/role', '1', '@views/system/role/index', '', 1, 0, NULL, 0, 0, 0, NULL, 10, '2021-11-19 14:19:48', '2022-03-28 11:05:58', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (11, 2, 'Center', '用户中心', NULL, NULL, '/system/user/center', '1', '@views/system/user/center', '', 1, 0, NULL, 0, 1, 0, NULL, 10, '2021-11-19 16:24:05', '2023-03-13 16:18:33', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (12, 0, 'Market', '小程序', 'apps-fill', NULL, '/market', '1', 'Layout', '', 7, 0, NULL, 0, 1, 0, NULL, NULL, '2021-11-22 18:16:07', '2022-04-10 20:56:29', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (15, 0, 'Order', '订单', 'apps-fill', NULL, '/order', '1', 'Layout', '', 5, 0, NULL, 0, 0, 0, NULL, 10, '2021-11-22 18:21:24', '2023-03-13 16:13:23', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (19, 15, 'OrderList', '支付订单', NULL, NULL, '/order/list', '1', '@views/order/index', '', 1, 0, NULL, 0, 0, 0, NULL, 10, '2021-11-22 18:54:03', '2023-04-18 17:23:24', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (20, 15, 'Confirm', '订单核销', NULL, NULL, '/order/confirm', '1', '@views/order/confirm', '', 1, 0, NULL, 0, 0, 0, NULL, NULL, '2021-11-22 18:54:21', '2022-04-10 20:56:22', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (23, 0, 'User', '用户', 'apps-fill', NULL, '/user', '1', 'Layout', '', 6, 0, NULL, 0, 1, 0, NULL, 10, '2021-11-23 14:56:13', '2022-12-04 19:51:40', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (24, 23, 'UserList', '用户列表', NULL, NULL, '/user/index', '1', '@views/user/index', '', 1, 0, NULL, 0, 0, 0, NULL, 10, '2021-11-23 14:57:50', '2021-11-23 14:57:50', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (25, 12, 'Nav', '导航栏管理', NULL, NULL, '/market/nav/index', '1', '@views/nav/index', '', 1, 0, NULL, 0, 1, 0, NULL, NULL, '2021-11-23 16:22:49', '2022-04-10 20:56:26', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (26, 2, 'Dict', '字典管理', NULL, NULL, '/system/dict', '1', '@views/system/dict/index', '', 1, 0, NULL, 0, 0, 0, NULL, 10, '2021-11-23 20:22:56', '2023-02-21 16:16:39', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (32, 0, 'Item', '商品', 'apps-fill', NULL, '/item', '1', 'Layout', '', 4, 0, NULL, 0, 1, 0, NULL, 10, '2021-11-30 23:03:56', '2022-04-19 14:07:38', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (33, 32, 'ItemList', '商品列表', NULL, NULL, '/item/index', '1', '@views/item/index', '', 1, 0, NULL, 0, 0, 0, NULL, 10, '2021-11-30 23:04:44', '2021-11-30 23:04:44', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (34, 39, 'Store', '商户列表', NULL, NULL, '/merchant/index', '1', '@views/merchant/index', '', 1, 0, NULL, 0, 0, 0, NULL, NULL, '2021-12-21 18:31:01', '2022-04-10 20:54:57', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (35, 32, 'Category', '分类管理', NULL, NULL, '/item/category', '1', '@views/category/index', '', 1, 0, NULL, 0, 0, 0, NULL, 10, '2021-12-03 15:12:12', '2021-12-03 15:12:12', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (36, 32, 'ItemEdit', '编辑商品', NULL, NULL, '/item/edit', '1', '@views/item/detail', '', 1, 0, NULL, 0, 1, 0, NULL, 10, '2021-11-30 23:05:29', '2021-11-30 23:05:29', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (40, 0, 'Finance', '财务', 'apps-fill', NULL, '/finance', '1', 'Layout', ' ', 1, 0, NULL, 0, 0, 0, NULL, NULL, '2021-12-21 18:57:21', '2022-04-10 20:55:50', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (41, 203, 'Property', '系统配置', NULL, NULL, '/system/property', '1', '@views/system/property', '', 1, 0, NULL, 0, 1, 0, NULL, 10, '2022-03-24 15:34:53', '2023-06-21 14:55:45', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (52, 60, 'TenantList', '租户列表', NULL, NULL, '/tenant/list', '1', '@views/tenant/index', '', 1, 0, NULL, 0, 0, 0, NULL, 1, '2022-04-08 19:03:58', '2023-04-07 12:00:52', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (53, 40, 'AccountList', '账户管理', NULL, NULL, '/finance/account', '1', '@views/finance/account', '', 1, 0, NULL, 0, 0, 0, NULL, NULL, '2022-04-08 19:13:38', '2022-04-10 20:55:36', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (54, 60, 'TenantEdit', '编辑租户', NULL, NULL, '/tenant/edit', '1', '@views/tenant/center', '', 1, 1, NULL, 0, 1, 0, NULL, 1, '2022-04-08 19:21:42', '2023-04-07 12:01:19', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (58, 55, 'Statistics', '数据统计', NULL, NULL, '/subject/statistics', '1', '@views/statistics/index', '', 1, 0, NULL, 0, 0, 0, NULL, NULL, '2022-04-10 21:36:58', '2022-11-18 09:51:39', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (60, 2, 'Tenant', '租户管理', 'building-line', NULL, '/tenant', '1', 'Layout', '', 60, 0, NULL, 0, 1, 0, NULL, 1, '2022-04-08 19:01:11', '2023-06-21 15:21:25', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (61, 89, 'PlanDetail', '计划详情', NULL, NULL, '/plan/detail', '1', '@views/plan/detail', '', 1, 1, NULL, 0, 1, 0, NULL, NULL, '2022-04-11 20:24:38', '2023-03-08 17:51:22', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (63, 184, 'PropertyList', '配置管理', NULL, NULL, '/system/property/list', '1', '@views/system/property/list', '', 1, 0, NULL, 0, 0, 0, NULL, 1, '2022-04-13 10:21:07', '2023-03-16 14:29:23', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (64, 23, 'UserDetail', '用户详情', NULL, NULL, '/user/edit', '1', '@views/user/center', '', 1, 1, NULL, 0, 1, 0, NULL, 1, '2022-04-17 11:21:15', '2022-04-17 11:21:15', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (65, 60, 'AgentCode', '开课码管理', NULL, NULL, '/agent/code', '1', '@views/agent/code', '', 1, 0, NULL, 0, 0, 0, NULL, NULL, '2022-05-18 15:06:17', '2022-11-30 20:26:32', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (66, 1, 'home', '首页', NULL, NULL, '/home/index', '1', '@views/home/index', '', 1, 0, 1, 0, 0, 0, NULL, 10, '2022-11-17 18:15:43', '2022-11-17 18:47:45', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (67, 1, 'statistics', '数据统计', NULL, NULL, '/statistics', '1', '@views/statistics', '', 1, 0, NULL, 0, 0, 0, NULL, NULL, '2022-11-17 18:44:46', '2023-03-31 15:09:04', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (68, 0, 'User', '会员', 'account-circle-fill', NULL, '/user', '1', 'Layout', '', 3, 0, NULL, 0, 0, 0, NULL, 1, '2022-11-18 09:22:30', '2023-06-21 15:04:28', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (69, 68, 'user', '会员管理', NULL, NULL, '/user/index', '1', '@views/student/index', '', 1, 0, NULL, 0, 0, 0, NULL, 1, '2022-11-18 09:23:14', '2023-04-07 09:22:51', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (76, 69, 'edit', '添加/编辑/查看学员', NULL, NULL, '/student/edit', '1', '@views/student/edit', '', 1, 1, NULL, 0, 1, 0, NULL, 1, '2022-11-18 15:47:55', '2023-04-07 10:16:34', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (80, 74, 'lessonEdit', '添加/编辑/查看教案', NULL, NULL, '/lesson/edit', '1', '@views/lesson/edit', '', 1, 0, NULL, 0, 1, 0, NULL, NULL, '2022-11-25 11:42:07', '2023-03-08 17:51:25', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (96, 1, 'waitdo', '我的待办', NULL, NULL, '/home/waitdo', '1', '@views/home/waitdo', '', 1, 0, NULL, 0, 0, 0, NULL, 1, '2022-11-28 09:40:31', '2022-11-28 09:40:31', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (97, 1, 'info', '消息详情', NULL, NULL, '/info/index', '1', '@views/info/index', '', 1, 1, NULL, 0, 1, 0, NULL, 1, '2022-11-29 15:03:45', '2022-11-29 15:11:57', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (98, 1, 'infoListt', '消息列表', NULL, NULL, '/info/infoList', '1', '@views/info/infoList', '', 1, 1, NULL, 0, 1, 0, NULL, 1, '2022-11-29 15:11:49', '2023-02-02 18:55:14', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (114, 69, 'StudentView', '查看', NULL, 'student:view', '/', '1', NULL, '', 1, 0, NULL, 0, 1, 2, NULL, 10, '2022-12-22 14:58:22', '2023-04-07 09:34:48', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (115, 69, 'StudentAdd', '添加', NULL, 'student:add', '/', '1', NULL, '', 1, 0, NULL, 0, 1, 2, NULL, 10, '2022-12-22 16:17:32', '2023-04-07 09:34:53', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (116, 69, 'StudentEdit', '编辑', NULL, 'student:edit', '/', '1', NULL, '', 1, 0, NULL, 0, 1, 2, NULL, 10, '2022-12-22 16:21:31', '2023-04-07 09:35:05', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (117, 69, 'StudentEnabled', '禁用/启用', NULL, 'student:enabled', '/', '1', NULL, '', 1, 0, NULL, 0, 1, 2, NULL, 10, '2022-12-22 16:23:09', '2023-04-07 09:35:11', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (118, 69, 'StudentImport', '导入', NULL, 'student:import', '/', '1', NULL, '', 1, 0, NULL, 0, 1, 2, NULL, 10, '2022-12-22 16:27:52', '2023-04-07 09:35:16', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (119, 69, 'StudentExport', '导出', NULL, 'student:export', '/', '1', NULL, '', 1, 0, NULL, 0, 1, 2, NULL, 10, '2022-12-22 16:28:51', '2023-04-07 09:35:21', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (120, 69, 'StudentDelete', '删除', NULL, 'student:delete', '/', '1', NULL, '', 1, 0, NULL, 0, 1, 2, NULL, 10, '2022-12-22 16:29:25', '2023-04-07 09:35:26', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (162, 61, 'CommentAdd', '添加', NULL, 'comment:add', '/', '1', NULL, '', 1, 0, NULL, 0, 1, 2, NULL, NULL, '2022-12-22 17:52:44', '2022-12-22 17:52:57', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (172, 2, 'SystemView', '查看', NULL, 'system:view', '/', '1', NULL, '', 1, 0, NULL, 0, 1, 2, NULL, 1, '2022-12-22 18:07:57', '2022-12-22 18:07:57', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (173, 2, 'SystemAdd', '添加', NULL, 'system:add', '/', '1', NULL, '', 1, 0, NULL, 0, 1, 2, NULL, 1, '2022-12-22 18:08:20', '2022-12-22 18:08:20', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (174, 2, 'SystemEdit', '编辑', NULL, 'system:edit', '/', '1', NULL, '', 1, 0, NULL, 0, 1, 2, NULL, 1, '2022-12-22 18:08:42', '2022-12-22 18:08:42', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (175, 2, 'SystemDelete', '删除', NULL, 'system:delete', '/', '1', NULL, '', 1, 0, NULL, 0, 1, 2, NULL, 1, '2022-12-22 18:09:12', '2022-12-22 18:09:12', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (176, 2, 'SystemEnable', '禁用/启用', NULL, 'system:enable', '/', '1', NULL, '', 1, 0, NULL, 0, 1, 2, NULL, 1, '2022-12-22 18:09:44', '2022-12-22 18:09:44', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (179, 203, 'SystemConfig', '分组配置', NULL, NULL, '/system/settings', '1', '@views/system/property/group', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-02-23 19:50:00', '2023-04-06 16:44:44', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (180, 2, 'Dept', '组织架构', NULL, NULL, '/system/dept', '1', '@views/system/dept', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-03-08 17:34:33', '2023-03-08 17:35:01', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (181, 184, 'Generator', '代码生成', NULL, NULL, '/system/generator', '1', '@views/system/generator/index', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-03-08 18:11:43', '2023-03-10 13:51:26', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (182, 184, 'Preview', '代码预览', NULL, NULL, '/system/generator/preview', '1', '@views/system/generator/preview', '', 1, 0, NULL, 0, 1, 1, NULL, 1, '2023-03-09 09:19:07', '2023-03-10 13:51:34', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (183, 184, 'GenSettings', '代码配置', NULL, NULL, '/system/generator/config', '1', '@views/system/generator/config', '', 1, 0, NULL, 0, 1, 1, NULL, 1, '2023-03-09 09:20:25', '2023-03-10 13:51:40', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (184, 0, 'Develop', '开发', 'contacts-fill', NULL, '/develop', '1', 'Layout', '', 100, 0, NULL, 0, 0, 0, NULL, 1, '2023-03-10 13:50:15', '2023-03-13 15:52:33', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (185, 184, 'Quartz', '任务调度', NULL, NULL, '/system/quartz', '1', '@views/system/quartz/index', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-03-10 15:50:35', '2023-03-10 15:50:35', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (186, 184, 'QuartzLog', '调度日志', NULL, NULL, '/system/quartz/log', '1', '@views/system/quartz/log', '', 1, 0, NULL, 0, 1, 1, NULL, 1, '2023-03-10 15:51:32', '2023-03-10 15:51:32', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (187, 184, 'FRender', '自定义表单', NULL, NULL, '/system/frender', '1', '@views/system/frender', '', 1, 0, 1, 0, 0, 1, NULL, 1, '2023-03-10 18:30:16', '2023-03-10 18:30:16', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (188, 184, 'docs', '接口文档', NULL, NULL, '//localhost:8300/doc.html#/home', '_blank', NULL, '', 1, 0, NULL, 1, 0, 1, NULL, 1, '2023-03-16 15:47:33', '2023-03-29 14:51:03', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (189, 0, 'Mall', '商城', 'briefcase-5-fill', NULL, '/mall', '1', 'Layout', '', 2, 0, NULL, 0, 0, 0, NULL, 1, '2023-03-29 10:01:56', '2023-05-23 10:58:23', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (190, 189, 'Goods', '商品管理', NULL, NULL, '/product/index', '', '@/views/mall/product/index', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-03-29 10:03:37', '2023-04-11 14:57:19', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (191, 189, 'Category', '分类管理', NULL, NULL, '/mall/category/index', '1', '@/views/mall/category/index', '', 3, 0, NULL, 0, 0, 1, NULL, 1, '2023-03-29 10:04:20', '2023-04-11 14:59:10', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (192, 1, 'Diy', '页面DIY', NULL, NULL, '/diy/index', '', '@views/diy/index', '', 1, 0, NULL, 0, 0, 0, NULL, NULL, '2023-03-30 14:12:00', '2023-03-30 15:13:00', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (193, 198, 'DIY', 'DIY页面', NULL, NULL, '/diy/index', '', '@views/diy/index', '', 7, 0, 1, 0, 1, 1, NULL, 1, '2023-03-30 15:12:53', '2023-04-18 14:54:15', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (194, 0, 'Content', '内容', 'article-line', NULL, '/content', '', 'Layout', '', 7, 0, NULL, 0, 0, 0, NULL, 1, '2023-03-30 15:18:31', '2023-04-07 11:54:30', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (195, 194, 'File', '文件管理', NULL, NULL, '/content/fileList', '', '@views/file/index', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-03-30 15:20:08', '2023-03-30 15:36:01', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (196, 195, 'FileList', '文件列表', NULL, NULL, '/file/index', '', '@/views/file/index', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-03-30 15:37:42', '2023-03-30 15:37:42', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (197, 190, 'GoodsList', '商品列表', NULL, NULL, '/goods/index', '', '@/views/mall/goods/index', '', 1, 0, NULL, 0, 0, 1, NULL, NULL, '2023-03-31 11:51:25', '2023-04-11 14:57:14', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (198, 189, 'MallDiy', '装修', 'hammer-line', NULL, '/mall/diy', '1', 'Layout', '', 6, 0, NULL, 0, 0, 0, NULL, 1, '2023-03-31 15:10:27', '2023-06-21 15:03:46', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (199, 198, 'BottomNavTemplate', '底部导航', NULL, NULL, '/mall/template/nav', '', '@views/diy/nav', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-03-31 15:53:19', '2023-03-31 16:09:33', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (200, 198, 'GoodsTemplate', '列表模板', NULL, NULL, '/mall/template/goods', '', '@/views/diy/goods', '', 1, 0, NULL, 0, 1, 1, NULL, 1, '2023-03-31 15:56:25', '2023-06-21 15:00:55', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (201, 198, 'CategoryTemplate', '分类模板', NULL, NULL, '/mall/template/category', '', '@/views/diy/category', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-03-31 15:57:27', '2023-03-31 16:03:58', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (202, 198, 'UserCenterTemplate', '个人中心', NULL, NULL, '/mall/template/userCenter', '', '@/views/diy/userCenter', '', 1, 0, NULL, 0, 1, 1, NULL, 1, '2023-03-31 15:59:01', '2023-05-09 16:18:39', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (203, 2, 'Property', '配置管理', NULL, NULL, '/system/property', '', NULL, '', 1, 0, NULL, 0, 1, 0, NULL, 1, '2023-04-06 16:43:55', '2023-06-21 17:24:40', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (204, 203, 'PropertySimple', '单个配置', NULL, NULL, '/system/property/simple', '', '@views/system/property/simple', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-06 16:45:45', '2023-04-06 16:45:45', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (205, 69, 'userLevel', '会员等级', NULL, NULL, '/user/level', '', '@views/user/level', '', 2, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-07 09:23:42', '2023-04-07 09:42:35', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (206, 69, 'userTags', '会员标签', NULL, NULL, '/user/tags', '', '@views/user/tags', '', 8, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-07 09:24:17', '2023-05-26 17:58:41', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (207, 69, 'userList', '学员列表', NULL, NULL, '/student/list', '', '@views/student/index', '', 7, 0, NULL, 0, 1, 1, NULL, 1, '2023-04-07 09:34:12', '2023-06-14 14:36:08', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (208, 0, 'Biz', '运营', 'chrome-line', NULL, '/biz', '1', 'Layout', '', 3, 0, NULL, 0, 0, 0, NULL, 1, '2023-04-07 10:18:27', '2023-04-07 10:18:27', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (209, 210, 'Merchant', '商户', NULL, NULL, '/merchant/index', '', '@views/merchant/index', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-07 10:19:25', '2023-04-19 18:15:37', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (210, 208, 'merchant', '商户管理', NULL, NULL, '/merchant', '1', 'Layout', '', 1, 0, NULL, 0, 0, 0, NULL, 1, '2023-04-07 10:35:09', '2023-04-07 10:35:09', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (211, 208, 'Agent', '代理商管理', NULL, NULL, '/agent', '1', '@views/agent/index', '', 2, 0, NULL, 0, 0, 0, NULL, 1, '2023-04-07 11:55:55', '2023-04-19 18:15:47', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (212, 211, 'AgentList', '代理商列表', NULL, NULL, '/agent/index', '', '@views/agent/index', '', 0, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-07 11:57:10', '2023-04-19 18:15:53', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (213, 198, 'themeDiy', '主题风格', NULL, NULL, '/diy/theme', '1', '@views/diy/theme', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-07 14:41:37', '2023-04-07 14:41:37', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (214, 69, 'UserList', '会员列表', NULL, NULL, '/user/list', '1', '@views/user/index', '', 0, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-10 14:26:03', '2023-04-18 16:38:55', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (215, 189, 'ProductEdit', '商品编辑', NULL, NULL, '/product/edit', '', '@views/mall/product/edit', '', 2, 0, NULL, 0, 1, 1, NULL, 1, '2023-04-11 14:58:48', '2023-04-11 17:07:40', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (216, 198, 'PageList', '页面管理', NULL, NULL, '/diy/page', '', '@views/diy/page/index', '', 0, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-17 17:54:57', '2023-04-25 11:13:51', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (217, 194, 'Article', '文章管理', NULL, NULL, '/article/index', '', '@views/article/index', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-18 14:58:46', '2023-04-18 15:04:00', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (218, 217, 'ArticleList', '文章列表', NULL, NULL, '/article/index', '', '@views/article/index', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-18 14:59:43', '2023-04-18 15:04:10', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (219, 217, 'ArticleCategory', '文章分类', NULL, NULL, '/article/category', '', '@views/article/category', '', 3, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-18 15:00:40', '2023-04-18 15:04:24', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (220, 15, 'StoreOrder', '商城订单', NULL, NULL, '/store/order/index', '', '@views/mall/order/index', '', 0, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-18 17:24:42', '2023-04-23 10:14:14', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (221, 15, 'AfterSaleOrder', '售后订单', NULL, NULL, '/order/afterSales', '', '@views/mall/afterSales', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-18 17:25:52', '2023-06-12 21:01:42', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (222, 210, 'MerchantDetail', '商户详情', NULL, NULL, '/merchant/detail', '', '@views/merchant/detail', '', 2, 0, NULL, 0, 1, 1, NULL, 1, '2023-04-19 18:17:00', '2023-04-19 18:17:00', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (223, 210, 'MerchantEdit', '编辑商户', NULL, NULL, '/merchant/edit', '', '@views/merchant/edit', '', 4, 0, NULL, 0, 1, 1, NULL, 1, '2023-04-19 18:17:42', '2023-04-19 18:17:42', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (224, 0, 'Property', '设置', 'settings-5-line', NULL, '/property', '', 'Layout', '', 8, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-24 14:46:32', '2023-04-24 14:46:32', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (225, 224, 'Miniapp', '微信小程序', NULL, NULL, '/property/minimp', '', '@views/property/miniapp', '', 8, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-24 14:48:07', '2023-04-24 14:48:07', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (226, 198, 'UrlPath', '页面路径', NULL, NULL, '/diy/url', '', '@views/diy/url', '', 11, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-24 17:11:44', '2023-04-24 17:11:44', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (227, 69, 'VipUser', 'VIP会员卡', NULL, NULL, '/user/vip', '', '@views/user/vip/index', '', 4, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-25 15:50:53', '2023-04-26 11:56:26', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (228, 217, 'ArticleEdit', '编辑文章', NULL, NULL, '/article/edit', '', '@views/article/edit', '', 4, 0, NULL, 0, 1, 1, NULL, 1, '2023-04-27 10:55:11', '2023-04-27 11:47:16', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (229, 210, 'Store', '门店', NULL, NULL, '/store/index', '', '@views/store/index', '', 5, 0, NULL, 0, 0, 1, NULL, 1, '2023-04-27 14:59:37', '2023-04-27 14:59:37', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (230, 210, 'MerchantEdit', '编辑门店', NULL, NULL, '/store/edit', '', '@views/store/edit', '', 6, 0, NULL, 0, 1, 1, NULL, 1, '2023-04-27 15:37:50', '2023-04-27 15:37:50', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (231, 189, 'ProductImport', '商品导入', NULL, NULL, '/product/import', '', '@views/mall/product/import', '', 6, 0, NULL, 0, 0, 1, NULL, NULL, '2023-05-04 10:18:42', '2023-05-16 14:25:03', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (232, 189, 'ProductCrawler', '商品采集', NULL, NULL, 'product/crawler', '', '@views/mall/product/crawler', '', 7, 0, NULL, 0, 0, 1, NULL, NULL, '2023-05-04 10:20:02', '2023-05-16 14:25:08', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (233, 245, 'Coupon', '优惠券', NULL, NULL, '/coupon/index', '', '@views/mall/coupon/index', '', 14, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-06 15:10:34', '2023-05-17 19:37:21', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (234, 245, 'CouponEdit', '编辑优惠券', NULL, NULL, '/coupon/edit', '', '@views/mall/coupon/edit', '', 16, 0, NULL, 0, 1, 1, NULL, 1, '2023-05-06 15:11:32', '2023-05-17 19:37:34', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (235, 224, 'tradeSettings', '交易设置', NULL, NULL, '/property/trade', '', '@views/property/trade', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-09 11:40:09', '2023-05-09 11:40:09', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (236, 245, 'Combination', '拼团', NULL, NULL, '/combination/index', '', '@views/mall/combination/index', '', 10, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-09 20:15:47', '2023-05-23 21:27:02', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (237, 245, 'Seckill', '秒杀', NULL, NULL, '/seckill/index', '', '@views/mall/seckill/index', '', 11, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-09 20:16:39', '2023-06-06 17:31:30', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (238, 245, 'Bargain', '砍价', NULL, NULL, '/bargain/index', '', '@views/mall/bargain/index', '', 13, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-09 20:17:37', '2023-05-23 21:27:12', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (239, 245, 'Community', '社区团购', NULL, NULL, '/community/index', '', '@views/mall/community/index', '', 14, 0, NULL, 0, 1, 1, NULL, NULL, '2023-05-09 20:25:39', '2023-05-30 10:14:48', 1, 1);
INSERT INTO `kz_sys_menu` VALUES (240, 248, 'promoteSettings', '分销设置', NULL, NULL, '/property/promote', '', '@views/property/promote', '', 5, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-10 09:05:26', '2023-05-18 18:43:11', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (241, 224, 'withdraw', '提现设置', NULL, NULL, '/property/withdraw', '', '@views/property/withdraw', '', 8, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-10 09:06:04', '2023-05-10 09:06:04', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (242, 224, 'Payment', '支付设置', NULL, NULL, '/property/payment', '', '@views/property/payment', '', 10, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-10 10:55:36', '2023-05-10 10:55:36', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (243, 208, 'MallService', '商城服务', NULL, NULL, '/mall/service/index', '', '@views/mall/service/index', '', 17, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-13 21:27:43', '2023-06-21 15:08:02', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (244, 224, 'website', '站点设置', NULL, NULL, '/property/website', '', '@views/property/website', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-15 11:21:02', '2023-05-15 11:21:02', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (245, 208, 'market', '营销功能', NULL, NULL, '/market', '', 'Layout', '', 0, 0, NULL, 0, 0, 0, NULL, 1, '2023-05-17 19:36:25', '2023-06-21 15:10:18', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (246, 245, 'package', '组合套餐', NULL, NULL, '/package/index', '', '@views/mall/package/index', '', 7, 0, NULL, 0, 1, 1, NULL, 1, '2023-05-17 19:38:43', '2023-05-30 10:14:23', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (247, 15, 'Comments', '商品评论', NULL, NULL, '/comment/index', '', '@views/mall/comment/index', '', 8, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-18 11:04:48', '2023-06-21 15:11:09', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (248, 208, 'promote', '分销管理', NULL, NULL, '/promote/index', '', '@views/promote/index', '', 15, 0, NULL, 0, 0, 0, NULL, 1, '2023-05-18 17:11:13', '2023-06-21 15:07:42', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (249, 248, 'promoter', '分销员', NULL, NULL, '/promote/promoter', '', '@views/promote/promoter', '', 3, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-18 17:52:43', '2023-05-18 17:52:43', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (250, 224, 'weixinOpen', '开放平台配置', NULL, NULL, '/property/open', '', '@views/property/open', '', 11, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-19 18:16:40', '2023-06-08 15:27:02', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (251, 0, 'Shop', '店铺', 'store-2-line', NULL, '/shop/', '', 'Layout', '', 1, 0, NULL, 0, 1, 1, NULL, 1, '2023-05-23 10:57:56', '2023-06-21 15:04:06', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (252, 251, 'Applet', '小程序管理', NULL, NULL, '/applet', '', '@views/applet', '', 1, 0, NULL, 0, 0, 0, NULL, 1, '2023-05-23 11:02:16', '2023-05-23 11:02:16', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (253, 252, 'Wx', '微信小程序', NULL, NULL, '/applet/wx', '', '@views/applet/wx', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-23 11:04:29', '2023-05-23 11:04:29', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (254, 252, 'createApplet', '添加小程序', NULL, NULL, '/applet/wx/create', '1', '@views/applet/wx/edit', '', 3, 0, NULL, 0, 1, 1, NULL, 1, '2023-05-23 14:53:28', '2023-05-23 14:54:15', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (255, 245, 'Credit', '积分商城', NULL, NULL, '/credit/index', '', '@views/mall/credit/index', '', 6, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-23 21:26:53', '2023-05-23 21:28:20', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (256, 245, 'editSeckill', '创建秒杀活动', NULL, NULL, '/seckill/edit', '', '@views/mall/seckill/edit', '', 11, 0, NULL, 0, 1, 1, NULL, 1, '2023-05-23 22:01:34', '2023-05-23 22:01:34', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (257, 245, 'seckillProducts', '秒杀活动商品', NULL, NULL, '/seckill/details', '', '@views/mall/seckill/details', '', 11, 0, NULL, 0, 1, 1, NULL, 1, '2023-05-25 16:45:24', '2023-05-25 16:45:24', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (258, 245, 'SeckillProduct', '创建秒杀商品', NULL, NULL, '/seckill/product', '', '@views/mall/seckill/product', '', 11, 0, NULL, 0, 1, 1, NULL, 1, '2023-05-25 19:46:53', '2023-05-25 19:46:53', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (259, 245, 'recharge', '充值', NULL, NULL, '/recharge/index', '', '@views/mall/recharge/index', '', 15, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-26 14:31:08', '2023-05-26 14:31:08', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (260, 224, 'agreement', '用户协议/规则', NULL, NULL, '/property/agreement', '', '@views/property/agreement', '', 8, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-26 15:18:31', '2023-05-26 15:18:31', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (261, 208, 'finance', '财务', NULL, NULL, '/finance', '', '/finance', '', 0, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-26 16:49:32', '2023-06-21 15:24:16', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (262, 261, 'balance', '余额管理', NULL, NULL, '/balance', '', '@views/finance/balance/index', '', 5, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-26 16:50:29', '2023-05-26 16:52:13', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (263, 261, 'credit', '积分管理', NULL, NULL, '/credit', '', '@views/finance/credit/index', '', 4, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-26 16:52:00', '2023-05-26 16:52:20', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (264, 261, 'FinanceIndex', '财务概况', NULL, NULL, '/finance/index', '', '@views/finance/index', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-29 10:30:59', '2023-05-29 10:30:59', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (265, 261, 'Brokerage', '佣金管理', NULL, NULL, '/brokerage', '', '@views/finance/brokerage/index', '', 3, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-30 15:38:41', '2023-05-30 15:38:41', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (266, 208, 'Express', '运费模板', NULL, NULL, '/express/index', '', '@views/mall/express/index', '', 8, 0, NULL, 0, 0, 1, NULL, 1, '2023-05-31 15:42:30', '2023-06-21 15:06:46', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (267, 208, 'ExpressEdit', '编辑运费模板', NULL, NULL, '/express/edit', '', '@views/mall/express/edit', '', 9, 0, NULL, 0, 1, 1, NULL, 1, '2023-05-31 15:43:22', '2023-06-21 15:06:59', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (268, 245, 'combinationEdit', '拼团设置', NULL, NULL, '/combination/edit', '', '@views/mall/combination/edit', '', 10, 0, NULL, 0, 1, 1, NULL, 1, '2023-06-02 12:21:48', '2023-06-02 12:26:02', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (269, 245, 'EditCredit', '编辑积分商品', NULL, NULL, '/credit/edit', '', '@views/mall/credit/edit', '', 6, 0, NULL, 0, 1, 1, NULL, 1, '2023-06-05 10:00:00', '2023-06-05 10:00:07', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (270, 224, 'CreditSettings', '积分设置', NULL, NULL, '/property/credit', '', '@views/property/credit', '', 3, 0, NULL, 0, 0, 1, NULL, 1, '2023-06-05 11:32:08', '2023-06-05 11:32:08', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (271, 245, 'CombinationProducts', '拼团活动商品', NULL, NULL, '/combination/details', '1', '@views/mall/combination/details', '', 10, 0, NULL, 0, 1, 1, NULL, 1, '2023-06-06 17:30:09', '2023-06-06 17:31:41', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (272, 245, 'combinationProduct', '创建拼团商品', NULL, NULL, '/combination/product', '', '@views/mall/combination/product', '', 10, 0, NULL, 0, 1, 1, NULL, 1, '2023-06-06 17:31:19', '2023-06-06 17:31:35', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (273, 245, 'bargainEdit', '砍价设置', NULL, NULL, '/bargain/edit', '1', '	\r\n@views/mall/bargain/edit', '', 13, 0, NULL, 0, 1, 1, NULL, 1, '2023-06-06 17:31:19', '2023-06-06 17:31:19', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (274, 245, 'bargainDetails', '砍价活动商品', NULL, NULL, '/bargain/details', '1', '	\r\n@views/mall/bargain/details', '', 13, 0, NULL, 0, 1, 1, NULL, 1, '2023-06-06 17:31:19', '2023-06-07 18:39:36', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (275, 245, 'bargainProduct', '创建砍价商品', NULL, NULL, '/bargain/product', '1', '	\r\n@views/mall/bargain/product', '', 13, 0, NULL, 0, 1, 1, NULL, 1, '2023-06-06 17:31:19', '2023-06-06 17:31:19', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (276, 248, 'promoteProducts', '分销商品', NULL, NULL, '/promote/products', '', '@views/promote/products', '', 3, 0, NULL, 0, 0, 1, NULL, 1, '2023-06-09 17:36:36', '2023-06-09 17:41:31', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (277, 248, 'promoteProduct', '创建分销商品', NULL, NULL, '/promote/product', '', '@views/promote/product', '', 3, 0, NULL, 0, 1, 1, NULL, 1, '2023-06-09 17:36:36', '2023-06-09 17:41:31', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (278, 2, 'SystemAddress', '售后地址', NULL, NULL, '/system/address', '', '@views/system/address', '', 10, 0, NULL, 0, 0, 1, NULL, 1, '2023-06-13 10:45:43', '2023-06-13 10:45:43', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (279, 261, 'Withdraw', '提现管理', NULL, NULL, '/withdraw', '', '@views/withdraw/index', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-06-14 19:34:17', '2023-06-14 19:34:17', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (280, 208, 'Print', '打印管理', NULL, NULL, '/print', '', 'Layout', '', 12, 0, NULL, 0, 0, 1, NULL, 1, '2023-06-15 14:07:05', '2023-06-21 15:07:31', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (281, 280, 'PrintTemplate', '打印模板', NULL, NULL, '/print/template/index', '', '@views/print/template/index', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-06-15 14:08:01', '2023-06-15 14:08:01', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (282, 280, 'PrintTemplateEdit', '打印模板设置', NULL, NULL, '/print/template/edit', '', '@views/print/template/edit', '', 1, 0, NULL, 0, 1, 1, NULL, 1, '2023-06-15 14:08:56', '2023-06-15 19:12:47', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (283, 280, 'printer', '打印机管理', NULL, NULL, '/printer', '', '@views/print/printer/index', '', 3, 0, NULL, 0, 0, 1, NULL, 1, '2023-06-15 14:26:08', '2023-06-15 14:26:08', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (284, 0, 'Stat', '数据', 'numbers-line', NULL, '/stat', '', 'Layout', '', 1, 0, NULL, 0, 0, 0, NULL, 1, '2023-06-16 08:59:47', '2023-06-21 15:06:09', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (285, 284, 'statBase', '概况', NULL, NULL, '/stat/index', '', '@views/stat/index', '', 1, 0, NULL, 0, 0, 1, NULL, 1, '2023-06-16 09:01:56', '2023-06-16 09:01:56', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (286, 284, 'productStat', '商品统计', NULL, NULL, '/stat/product', '', '@views/stat/product', '', 2, 0, NULL, 0, 0, 1, NULL, 1, '2023-06-16 09:02:40', '2023-06-16 09:03:29', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (287, 284, 'userStat', '用户统计', NULL, NULL, '/stat/user', '', '@views/stat/user', '', 3, 0, NULL, 0, 0, 1, NULL, 1, '2023-06-16 09:03:22', '2023-06-16 09:03:22', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (288, 284, 'tradeStat', '交易统计', NULL, NULL, '/stat/trade', '', '@views/stat/trade', '', 4, 0, NULL, 0, 0, 1, NULL, 1, '2023-06-16 09:04:17', '2023-06-16 09:04:17', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (289, 280, 'printSettings', '打印设置', NULL, NULL, '/property/print', '', '@views/property/print', '', 8, 0, NULL, 0, 0, 1, NULL, 1, '2023-06-16 11:36:36', '2023-06-16 11:36:36', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (290, 224, 'Mp', '微信公众号', NULL, NULL, '/property/mp', '', '@views/property/mp', '', 8, 0, NULL, 0, 1, 1, NULL, 1, '2023-04-24 14:48:07', '2023-06-26 09:43:44', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (291, 224, 'Storage', '存储设置', NULL, NULL, '/property/storage', '', '@views/property/storage', '', 6, 0, NULL, 0, 0, 1, NULL, 1, '2023-06-26 09:09:25', '2023-06-26 09:09:25', 1, 0);
INSERT INTO `kz_sys_menu` VALUES (292, 224, 'Message', '短信设置', NULL, NULL, '/property/message', '', '@views/property/message', '', 6, 0, NULL, 0, 0, 1, NULL, 1, '2023-06-26 09:15:48', '2023-06-26 09:16:29', 1, 0);

-- ----------------------------
-- Table structure for kz_sys_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_operate_log`;
CREATE TABLE `kz_sys_operate_log`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ip_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `module` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for kz_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_permission`;
CREATE TABLE `kz_sys_permission`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `menu_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1729 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_sys_permission
-- ----------------------------
INSERT INTO `kz_sys_permission` VALUES (64, 1, 1);
INSERT INTO `kz_sys_permission` VALUES (65, 1, 3);
INSERT INTO `kz_sys_permission` VALUES (66, 1, 66);
INSERT INTO `kz_sys_permission` VALUES (67, 1, 55);
INSERT INTO `kz_sys_permission` VALUES (68, 1, 56);
INSERT INTO `kz_sys_permission` VALUES (69, 1, 57);
INSERT INTO `kz_sys_permission` VALUES (70, 1, 58);
INSERT INTO `kz_sys_permission` VALUES (71, 1, 59);
INSERT INTO `kz_sys_permission` VALUES (72, 1, 61);
INSERT INTO `kz_sys_permission` VALUES (73, 1, 60);
INSERT INTO `kz_sys_permission` VALUES (74, 1, 52);
INSERT INTO `kz_sys_permission` VALUES (75, 1, 54);
INSERT INTO `kz_sys_permission` VALUES (76, 1, 62);
INSERT INTO `kz_sys_permission` VALUES (77, 1, 65);
INSERT INTO `kz_sys_permission` VALUES (78, 1, 32);
INSERT INTO `kz_sys_permission` VALUES (79, 1, 33);
INSERT INTO `kz_sys_permission` VALUES (80, 1, 35);
INSERT INTO `kz_sys_permission` VALUES (81, 1, 36);
INSERT INTO `kz_sys_permission` VALUES (82, 1, 15);
INSERT INTO `kz_sys_permission` VALUES (83, 1, 19);
INSERT INTO `kz_sys_permission` VALUES (84, 1, 23);
INSERT INTO `kz_sys_permission` VALUES (85, 1, 24);
INSERT INTO `kz_sys_permission` VALUES (86, 1, 64);
INSERT INTO `kz_sys_permission` VALUES (87, 1, 2);
INSERT INTO `kz_sys_permission` VALUES (88, 1, 4);
INSERT INTO `kz_sys_permission` VALUES (89, 1, 7);
INSERT INTO `kz_sys_permission` VALUES (90, 1, 8);
INSERT INTO `kz_sys_permission` VALUES (91, 1, 11);
INSERT INTO `kz_sys_permission` VALUES (92, 1, 26);
INSERT INTO `kz_sys_permission` VALUES (93, 1, 41);
INSERT INTO `kz_sys_permission` VALUES (94, 1, 63);
INSERT INTO `kz_sys_permission` VALUES (1252, 5, 66);
INSERT INTO `kz_sys_permission` VALUES (1253, 5, 96);
INSERT INTO `kz_sys_permission` VALUES (1254, 5, 75);
INSERT INTO `kz_sys_permission` VALUES (1255, 5, 80);
INSERT INTO `kz_sys_permission` VALUES (1256, 5, 127);
INSERT INTO `kz_sys_permission` VALUES (1257, 5, 128);
INSERT INTO `kz_sys_permission` VALUES (1258, 5, 129);
INSERT INTO `kz_sys_permission` VALUES (1259, 5, 130);
INSERT INTO `kz_sys_permission` VALUES (1260, 5, 131);
INSERT INTO `kz_sys_permission` VALUES (1261, 5, 132);
INSERT INTO `kz_sys_permission` VALUES (1262, 5, 59);
INSERT INTO `kz_sys_permission` VALUES (1263, 5, 82);
INSERT INTO `kz_sys_permission` VALUES (1264, 5, 113);
INSERT INTO `kz_sys_permission` VALUES (1265, 5, 133);
INSERT INTO `kz_sys_permission` VALUES (1266, 5, 134);
INSERT INTO `kz_sys_permission` VALUES (1267, 5, 135);
INSERT INTO `kz_sys_permission` VALUES (1268, 5, 136);
INSERT INTO `kz_sys_permission` VALUES (1269, 5, 137);
INSERT INTO `kz_sys_permission` VALUES (1270, 5, 138);
INSERT INTO `kz_sys_permission` VALUES (1271, 5, 139);
INSERT INTO `kz_sys_permission` VALUES (1272, 5, 140);
INSERT INTO `kz_sys_permission` VALUES (1273, 5, 141);
INSERT INTO `kz_sys_permission` VALUES (1274, 5, 101);
INSERT INTO `kz_sys_permission` VALUES (1275, 5, 102);
INSERT INTO `kz_sys_permission` VALUES (1276, 5, 103);
INSERT INTO `kz_sys_permission` VALUES (1277, 5, 161);
INSERT INTO `kz_sys_permission` VALUES (1278, 5, 163);
INSERT INTO `kz_sys_permission` VALUES (1279, 5, 164);
INSERT INTO `kz_sys_permission` VALUES (1280, 5, 165);
INSERT INTO `kz_sys_permission` VALUES (1281, 5, 11);
INSERT INTO `kz_sys_permission` VALUES (1282, 5, 177);
INSERT INTO `kz_sys_permission` VALUES (1283, 5, 74);
INSERT INTO `kz_sys_permission` VALUES (1284, 5, 81);
INSERT INTO `kz_sys_permission` VALUES (1285, 5, 100);
INSERT INTO `kz_sys_permission` VALUES (1286, 5, 77);
INSERT INTO `kz_sys_permission` VALUES (1287, 5, 78);
INSERT INTO `kz_sys_permission` VALUES (1288, 5, 79);
INSERT INTO `kz_sys_permission` VALUES (1289, 5, 88);
INSERT INTO `kz_sys_permission` VALUES (1290, 5, 149);
INSERT INTO `kz_sys_permission` VALUES (1291, 5, 150);
INSERT INTO `kz_sys_permission` VALUES (1292, 5, 151);
INSERT INTO `kz_sys_permission` VALUES (1293, 5, 152);
INSERT INTO `kz_sys_permission` VALUES (1294, 5, 153);
INSERT INTO `kz_sys_permission` VALUES (1295, 5, 154);
INSERT INTO `kz_sys_permission` VALUES (1296, 5, 155);
INSERT INTO `kz_sys_permission` VALUES (1297, 5, 156);
INSERT INTO `kz_sys_permission` VALUES (1298, 5, 157);
INSERT INTO `kz_sys_permission` VALUES (1299, 5, 158);
INSERT INTO `kz_sys_permission` VALUES (1300, 5, 159);
INSERT INTO `kz_sys_permission` VALUES (1301, 5, 160);
INSERT INTO `kz_sys_permission` VALUES (1302, 5, 1);
INSERT INTO `kz_sys_permission` VALUES (1303, 5, 2);
INSERT INTO `kz_sys_permission` VALUES (1304, 5, 70);
INSERT INTO `kz_sys_permission` VALUES (1474, 2, 66);
INSERT INTO `kz_sys_permission` VALUES (1475, 2, 67);
INSERT INTO `kz_sys_permission` VALUES (1476, 2, 52);
INSERT INTO `kz_sys_permission` VALUES (1477, 2, 54);
INSERT INTO `kz_sys_permission` VALUES (1478, 2, 59);
INSERT INTO `kz_sys_permission` VALUES (1479, 2, 82);
INSERT INTO `kz_sys_permission` VALUES (1480, 2, 113);
INSERT INTO `kz_sys_permission` VALUES (1481, 2, 133);
INSERT INTO `kz_sys_permission` VALUES (1482, 2, 134);
INSERT INTO `kz_sys_permission` VALUES (1483, 2, 135);
INSERT INTO `kz_sys_permission` VALUES (1484, 2, 136);
INSERT INTO `kz_sys_permission` VALUES (1485, 2, 137);
INSERT INTO `kz_sys_permission` VALUES (1486, 2, 138);
INSERT INTO `kz_sys_permission` VALUES (1487, 2, 139);
INSERT INTO `kz_sys_permission` VALUES (1488, 2, 140);
INSERT INTO `kz_sys_permission` VALUES (1489, 2, 141);
INSERT INTO `kz_sys_permission` VALUES (1490, 2, 71);
INSERT INTO `kz_sys_permission` VALUES (1491, 2, 99);
INSERT INTO `kz_sys_permission` VALUES (1492, 2, 104);
INSERT INTO `kz_sys_permission` VALUES (1493, 2, 106);
INSERT INTO `kz_sys_permission` VALUES (1494, 2, 108);
INSERT INTO `kz_sys_permission` VALUES (1495, 2, 166);
INSERT INTO `kz_sys_permission` VALUES (1496, 2, 167);
INSERT INTO `kz_sys_permission` VALUES (1497, 2, 168);
INSERT INTO `kz_sys_permission` VALUES (1498, 2, 169);
INSERT INTO `kz_sys_permission` VALUES (1499, 2, 73);
INSERT INTO `kz_sys_permission` VALUES (1500, 2, 109);
INSERT INTO `kz_sys_permission` VALUES (1501, 2, 110);
INSERT INTO `kz_sys_permission` VALUES (1502, 2, 111);
INSERT INTO `kz_sys_permission` VALUES (1503, 2, 112);
INSERT INTO `kz_sys_permission` VALUES (1504, 2, 170);
INSERT INTO `kz_sys_permission` VALUES (1505, 2, 171);
INSERT INTO `kz_sys_permission` VALUES (1506, 2, 4);
INSERT INTO `kz_sys_permission` VALUES (1507, 2, 8);
INSERT INTO `kz_sys_permission` VALUES (1508, 2, 26);
INSERT INTO `kz_sys_permission` VALUES (1509, 2, 11);
INSERT INTO `kz_sys_permission` VALUES (1510, 2, 172);
INSERT INTO `kz_sys_permission` VALUES (1511, 2, 173);
INSERT INTO `kz_sys_permission` VALUES (1512, 2, 174);
INSERT INTO `kz_sys_permission` VALUES (1513, 2, 175);
INSERT INTO `kz_sys_permission` VALUES (1514, 2, 176);
INSERT INTO `kz_sys_permission` VALUES (1515, 2, 60);
INSERT INTO `kz_sys_permission` VALUES (1516, 2, 81);
INSERT INTO `kz_sys_permission` VALUES (1517, 2, 72);
INSERT INTO `kz_sys_permission` VALUES (1518, 2, 1);
INSERT INTO `kz_sys_permission` VALUES (1519, 2, 70);
INSERT INTO `kz_sys_permission` VALUES (1520, 2, 2);
INSERT INTO `kz_sys_permission` VALUES (1615, 3, 66);
INSERT INTO `kz_sys_permission` VALUES (1616, 3, 67);
INSERT INTO `kz_sys_permission` VALUES (1617, 3, 69);
INSERT INTO `kz_sys_permission` VALUES (1618, 3, 76);
INSERT INTO `kz_sys_permission` VALUES (1619, 3, 93);
INSERT INTO `kz_sys_permission` VALUES (1620, 3, 78);
INSERT INTO `kz_sys_permission` VALUES (1621, 3, 79);
INSERT INTO `kz_sys_permission` VALUES (1622, 3, 88);
INSERT INTO `kz_sys_permission` VALUES (1623, 3, 59);
INSERT INTO `kz_sys_permission` VALUES (1624, 3, 82);
INSERT INTO `kz_sys_permission` VALUES (1625, 3, 86);
INSERT INTO `kz_sys_permission` VALUES (1626, 3, 87);
INSERT INTO `kz_sys_permission` VALUES (1627, 3, 24);
INSERT INTO `kz_sys_permission` VALUES (1628, 3, 64);
INSERT INTO `kz_sys_permission` VALUES (1629, 3, 4);
INSERT INTO `kz_sys_permission` VALUES (1630, 3, 75);
INSERT INTO `kz_sys_permission` VALUES (1631, 3, 80);
INSERT INTO `kz_sys_permission` VALUES (1632, 3, 127);
INSERT INTO `kz_sys_permission` VALUES (1633, 3, 128);
INSERT INTO `kz_sys_permission` VALUES (1634, 3, 129);
INSERT INTO `kz_sys_permission` VALUES (1635, 3, 130);
INSERT INTO `kz_sys_permission` VALUES (1636, 3, 131);
INSERT INTO `kz_sys_permission` VALUES (1637, 3, 132);
INSERT INTO `kz_sys_permission` VALUES (1638, 3, 61);
INSERT INTO `kz_sys_permission` VALUES (1639, 3, 90);
INSERT INTO `kz_sys_permission` VALUES (1640, 3, 142);
INSERT INTO `kz_sys_permission` VALUES (1641, 3, 143);
INSERT INTO `kz_sys_permission` VALUES (1642, 3, 144);
INSERT INTO `kz_sys_permission` VALUES (1643, 3, 145);
INSERT INTO `kz_sys_permission` VALUES (1644, 3, 146);
INSERT INTO `kz_sys_permission` VALUES (1645, 3, 147);
INSERT INTO `kz_sys_permission` VALUES (1646, 3, 148);
INSERT INTO `kz_sys_permission` VALUES (1647, 3, 8);
INSERT INTO `kz_sys_permission` VALUES (1648, 3, 11);
INSERT INTO `kz_sys_permission` VALUES (1649, 3, 73);
INSERT INTO `kz_sys_permission` VALUES (1650, 3, 109);
INSERT INTO `kz_sys_permission` VALUES (1651, 3, 170);
INSERT INTO `kz_sys_permission` VALUES (1652, 3, 171);
INSERT INTO `kz_sys_permission` VALUES (1653, 3, 121);
INSERT INTO `kz_sys_permission` VALUES (1654, 3, 122);
INSERT INTO `kz_sys_permission` VALUES (1655, 3, 123);
INSERT INTO `kz_sys_permission` VALUES (1656, 3, 124);
INSERT INTO `kz_sys_permission` VALUES (1657, 3, 125);
INSERT INTO `kz_sys_permission` VALUES (1658, 3, 126);
INSERT INTO `kz_sys_permission` VALUES (1659, 3, 149);
INSERT INTO `kz_sys_permission` VALUES (1660, 3, 150);
INSERT INTO `kz_sys_permission` VALUES (1661, 3, 151);
INSERT INTO `kz_sys_permission` VALUES (1662, 3, 152);
INSERT INTO `kz_sys_permission` VALUES (1663, 3, 153);
INSERT INTO `kz_sys_permission` VALUES (1664, 3, 154);
INSERT INTO `kz_sys_permission` VALUES (1665, 3, 155);
INSERT INTO `kz_sys_permission` VALUES (1666, 3, 157);
INSERT INTO `kz_sys_permission` VALUES (1667, 3, 177);
INSERT INTO `kz_sys_permission` VALUES (1668, 3, 178);
INSERT INTO `kz_sys_permission` VALUES (1669, 3, 94);
INSERT INTO `kz_sys_permission` VALUES (1670, 3, 95);
INSERT INTO `kz_sys_permission` VALUES (1671, 3, 114);
INSERT INTO `kz_sys_permission` VALUES (1672, 3, 115);
INSERT INTO `kz_sys_permission` VALUES (1673, 3, 116);
INSERT INTO `kz_sys_permission` VALUES (1674, 3, 117);
INSERT INTO `kz_sys_permission` VALUES (1675, 3, 118);
INSERT INTO `kz_sys_permission` VALUES (1676, 3, 119);
INSERT INTO `kz_sys_permission` VALUES (1677, 3, 120);
INSERT INTO `kz_sys_permission` VALUES (1678, 3, 113);
INSERT INTO `kz_sys_permission` VALUES (1679, 3, 133);
INSERT INTO `kz_sys_permission` VALUES (1680, 3, 134);
INSERT INTO `kz_sys_permission` VALUES (1681, 3, 135);
INSERT INTO `kz_sys_permission` VALUES (1682, 3, 136);
INSERT INTO `kz_sys_permission` VALUES (1683, 3, 137);
INSERT INTO `kz_sys_permission` VALUES (1684, 3, 138);
INSERT INTO `kz_sys_permission` VALUES (1685, 3, 139);
INSERT INTO `kz_sys_permission` VALUES (1686, 3, 140);
INSERT INTO `kz_sys_permission` VALUES (1687, 3, 141);
INSERT INTO `kz_sys_permission` VALUES (1688, 3, 101);
INSERT INTO `kz_sys_permission` VALUES (1689, 3, 102);
INSERT INTO `kz_sys_permission` VALUES (1690, 3, 103);
INSERT INTO `kz_sys_permission` VALUES (1691, 3, 161);
INSERT INTO `kz_sys_permission` VALUES (1692, 3, 163);
INSERT INTO `kz_sys_permission` VALUES (1693, 3, 164);
INSERT INTO `kz_sys_permission` VALUES (1694, 3, 165);
INSERT INTO `kz_sys_permission` VALUES (1695, 3, 23);
INSERT INTO `kz_sys_permission` VALUES (1696, 3, 74);
INSERT INTO `kz_sys_permission` VALUES (1697, 3, 89);
INSERT INTO `kz_sys_permission` VALUES (1698, 3, 85);
INSERT INTO `kz_sys_permission` VALUES (1699, 3, 68);
INSERT INTO `kz_sys_permission` VALUES (1700, 3, 81);
INSERT INTO `kz_sys_permission` VALUES (1701, 3, 100);
INSERT INTO `kz_sys_permission` VALUES (1702, 3, 172);
INSERT INTO `kz_sys_permission` VALUES (1703, 3, 173);
INSERT INTO `kz_sys_permission` VALUES (1704, 3, 174);
INSERT INTO `kz_sys_permission` VALUES (1705, 3, 175);
INSERT INTO `kz_sys_permission` VALUES (1706, 3, 1);
INSERT INTO `kz_sys_permission` VALUES (1707, 3, 70);
INSERT INTO `kz_sys_permission` VALUES (1708, 3, 77);
INSERT INTO `kz_sys_permission` VALUES (1709, 3, 2);
INSERT INTO `kz_sys_permission` VALUES (1710, 3, 72);
INSERT INTO `kz_sys_permission` VALUES (1711, 7, 66);
INSERT INTO `kz_sys_permission` VALUES (1712, 7, 1);
INSERT INTO `kz_sys_permission` VALUES (1713, 8, 66);
INSERT INTO `kz_sys_permission` VALUES (1714, 8, 77);
INSERT INTO `kz_sys_permission` VALUES (1715, 8, 149);
INSERT INTO `kz_sys_permission` VALUES (1716, 8, 151);
INSERT INTO `kz_sys_permission` VALUES (1717, 8, 78);
INSERT INTO `kz_sys_permission` VALUES (1718, 8, 153);
INSERT INTO `kz_sys_permission` VALUES (1719, 8, 88);
INSERT INTO `kz_sys_permission` VALUES (1720, 8, 155);
INSERT INTO `kz_sys_permission` VALUES (1721, 8, 150);
INSERT INTO `kz_sys_permission` VALUES (1722, 8, 152);
INSERT INTO `kz_sys_permission` VALUES (1723, 8, 79);
INSERT INTO `kz_sys_permission` VALUES (1724, 8, 154);
INSERT INTO `kz_sys_permission` VALUES (1725, 8, 157);
INSERT INTO `kz_sys_permission` VALUES (1726, 8, 1);
INSERT INTO `kz_sys_permission` VALUES (1727, 6, 66);
INSERT INTO `kz_sys_permission` VALUES (1728, 6, 1);

-- ----------------------------
-- Table structure for kz_sys_property
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_property`;
CREATE TABLE `kz_sys_property`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT 0,
  `groups` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属分组',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性名称',
  `pk` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '系统配置key',
  `k` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性',
  `v` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '属性值',
  `multiple` tinyint(1) NULL DEFAULT 0 COMMENT '是否多值',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表单类型',
  `is_hidden` tinyint(1) NULL DEFAULT 0 COMMENT '是否显示',
  `is_sync` tinyint(1) NULL DEFAULT 0 COMMENT '是否同步',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `is_public` tinyint(1) NULL DEFAULT 0 COMMENT '前端显示',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_user` int(10) NULL DEFAULT NULL,
  `enabled` tinyint(1) NULL DEFAULT 1,
  `is_delete` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_key`(`k`) USING BTREE COMMENT '唯一',
  UNIQUE INDEX `uni_pk`(`pk`) USING BTREE COMMENT '系统属性唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_sys_property
-- ----------------------------
INSERT INTO `kz_sys_property` VALUES (1, 7, 'wx.miniapp', '小程序名称', NULL, 'appName', '惠速达配送', 0, 'input', 0, 0, 1, 1, '2022-03-24 20:52:26', '2023-05-15 11:19:52', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (2, 7, 'wx.miniapp', '小程序AppId', NULL, 'appId', 'wx68b06c460cd4a3bd', 0, 'input', 0, 0, 2, 0, '2022-03-24 20:52:26', '2023-05-15 11:19:52', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (3, 7, 'wx.miniapp', '小程序Secret', NULL, 'secret', '2c7a5395e7f48e3f260848de5f860c1d', 0, 'input', 0, 0, 3, 0, '2022-03-24 20:52:26', '2023-05-15 11:19:52', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (4, 7, 'wx.miniapp', '小程序消息通知Token', NULL, 'token', 'kFzkBds7daFVozGgK1vHnPsnHucrZuO5', 0, 'input', 0, 0, 4, 0, '2022-03-24 20:52:26', '2023-05-15 11:19:52', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (5, 7, 'wx.miniapp', '小程序消息通知aesKey', NULL, 'aesKey', 'GttqH0mA6sjF4YEeaYYeztbBTzhT79WD4ghb67hJUbu', 0, 'input', 0, 0, 5, 0, '2022-03-24 20:52:26', '2023-05-15 11:19:52', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (6, 7, 'wx.miniapp', '小程序消息通知格式', NULL, 'msgDataFormat', 'JSON', 0, 'input', 0, 0, 6, 0, '2022-03-24 20:52:26', '2023-05-15 11:19:52', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (7, 0, 'wx.miniapp', '小程序管理', NULL, 'group', '', 0, 'form', 0, 0, 0, 0, '2022-04-13 11:00:17', '2023-05-15 11:19:52', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (8, 0, 'wx.pay', '支付配置', NULL, 'payment', NULL, 0, NULL, 0, 1, NULL, 0, NULL, '2023-05-30 20:49:06', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (9, 0, 'aliyun.msg', '短信配置', NULL, 'aliyun.msg', NULL, 0, NULL, 0, 0, NULL, 0, NULL, '2023-03-21 09:36:51', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (10, 0, 'oss', '存储配置', NULL, 'oss', NULL, 0, NULL, 0, 0, NULL, 0, NULL, '2023-06-26 14:33:12', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (18, 8, 'wx.pay', '小程序appid', 'wx.pay.appId', 'mAppId', 'wx329cc0563d6ec2ca', 0, 'input', 0, 0, 1, 0, NULL, NULL, NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (19, 8, 'wx.pay', '微信支付商户号', 'wx.pay.mchId', 'mchId', '1611075905', 0, 'input', 0, 0, 2, 0, '2023-03-16 11:33:24', '2023-05-30 20:49:06', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (20, 8, 'wx.pay', '微信支付商户密钥', 'wx.pay.mchKey', 'mchKey', 'yQL0H05fONuuMgeC5HqPoFSbw0nBmWzC', 0, 'input', 0, 0, 3, 0, '2023-03-16 11:33:24', '2023-05-30 20:49:06', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (21, 8, 'wx.pay', 'p12证书', 'wx.pay.keyPath', 'keyPath', 'upload/file/ae5876d1-5b45-4ab6-863c-0b206c5f0dc6.p12', 0, 'input', 0, 0, 4, 0, '2023-03-16 11:33:24', '2023-05-30 20:49:06', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (22, 0, 'shopSettings', '店铺设置', NULL, 'shopSettings', NULL, 0, 'form', 0, 0, NULL, 0, '2023-03-16 11:47:33', '2023-06-26 16:38:11', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (23, 9, 'aliyun.msg', '短信服务商', NULL, 'msgOrgType', 'ALIYUN', 0, 'radio', 0, 0, NULL, 0, '2023-03-21 09:31:11', '2023-03-21 09:36:52', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (24, 9, 'aliyun.msg', 'AccessKeyID', NULL, 'accessKeyId', 'LTAI5t7JR7wwTXgZ32gJaen6', 0, 'input', 0, 0, NULL, 0, '2023-03-21 09:31:11', '2023-03-21 09:36:52', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (25, 9, 'aliyun.msg', 'AccessKeySecret', NULL, 'accessSecret', 'OvLcLDVgzqN7SMggHMLVcwMjcZhCxk', 0, 'password', 0, 0, NULL, 0, '2023-03-21 09:31:11', '2023-03-21 09:36:52', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (26, 9, 'aliyun.msg', '模板CODE', NULL, 'templateCode', 'SMS_217965520', 0, 'input', 0, 0, NULL, 0, '2023-03-21 09:31:11', '2023-03-21 09:36:52', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (27, 9, 'aliyun.msg', '短信签名', NULL, 'signName', '千栈', 0, 'input', 0, 0, NULL, 0, '2023-03-21 09:31:11', '2023-03-21 09:36:52', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (28, 9, 'aliyun.msg', '区域Id', NULL, 'regionId', 'cn-hangzhou', 0, 'input', 0, 0, NULL, 0, '2023-03-21 09:31:11', '2023-03-21 09:36:52', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (29, 22, 'shopSettings', '分类页样式', NULL, 'categoryStyle', '2-3', 0, 'select', 0, 0, 1, 1, '2023-04-06 16:36:04', '2023-06-26 16:38:11', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (30, 22, 'shopSettings', '个人页样式', NULL, 'userCenterStyle', NULL, 0, 'select', 0, 0, 2, 1, '2023-04-06 16:36:04', '2023-06-26 16:38:11', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (31, 22, 'shopSettings', '主题色', NULL, 'themeColor', '#42CA4D', 0, 'color', 0, 0, 3, 1, '2023-04-06 16:36:04', '2023-06-26 16:38:12', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (32, 22, 'shopSettings', '按钮文字颜色', NULL, 'btnFontColor', '#FFFFFF', 0, 'color', 0, 0, 4, 1, '2023-04-18 17:20:55', '2023-06-26 16:38:12', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (33, 0, 'trade', '交易设置', NULL, 'trade', NULL, 0, 'form', 0, 0, NULL, 0, '2023-05-09 11:42:26', '2023-05-09 14:07:57', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (34, 33, 'trade', '自动收货时间（天）', NULL, 'autoFinishDays', '7', 0, 'number', 0, 0, NULL, 0, '2023-05-09 11:45:57', '2023-05-09 14:07:57', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (35, 33, 'trade', '未支付自动关闭时间（分钟）', NULL, 'autoClose', '30', 0, 'number', 0, 0, NULL, 0, '2023-05-09 11:45:57', '2023-05-09 14:07:57', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (36, 33, 'trade', '在线支付', NULL, 'onlinePay', '1', 0, 'switch', 0, 0, NULL, 0, '2023-05-09 11:45:57', '2023-05-09 14:07:57', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (37, 33, 'trade', '余额支付', NULL, 'balancePay', '1', 0, 'switch', 0, 0, NULL, 0, '2023-05-09 11:45:57', '2023-05-09 14:07:57', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (42, 0, 'promote', '分销设置', NULL, 'promote', NULL, 0, 'form', 1, 0, NULL, 0, '2023-05-09 20:29:48', '2023-05-18 18:40:43', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (43, 42, 'promote', '是否开启分销', NULL, 'promoteEnabled', '1', 0, 'switch', 0, 0, NULL, 0, '2023-05-09 20:37:15', '2023-05-18 18:40:43', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (44, 42, 'promote', '分销层级', NULL, 'promoteLevel', '2', 0, 'radio', 0, 0, NULL, 0, '2023-05-09 20:37:15', '2023-05-18 18:40:43', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (46, 42, 'promote', '分销商售申请方式', NULL, 'applyType', 'all', 0, 'radio', 0, 0, NULL, 0, '2023-05-09 20:37:15', '2023-05-18 18:40:43', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (47, 42, 'promote', '成为分销商的条件', NULL, 'conditionType', 'none', 0, 'radio', 0, 0, NULL, 0, '2023-05-09 20:37:15', '2023-05-18 18:40:43', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (48, 42, 'promote', '一级返佣比例（%）', NULL, 'firstRate', '10', 0, 'number', 0, 0, NULL, 0, '2023-05-09 20:37:15', '2023-05-18 18:40:43', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (49, 42, 'promote', '二级返佣比例（%）', NULL, 'secondRate', '5', 0, 'number', 0, 0, NULL, 0, '2023-05-09 20:37:15', '2023-05-18 18:40:43', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (50, 0, 'withdraw', '提现设置', NULL, 'withdraw', NULL, 0, 'form', 1, 0, NULL, 0, '2023-05-09 20:38:21', '2023-06-14 19:51:36', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (51, 50, 'withdraw', '提现审核', NULL, 'isAudit', '1', 0, 'switch', 0, 0, 1, 0, '2023-05-09 20:41:11', '2023-06-14 19:51:36', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (52, 50, 'withdraw', '佣金手续费（%）', NULL, 'rebate', '1', 0, 'number', 0, 0, 2, 0, '2023-05-09 20:41:11', '2023-06-14 19:51:37', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (53, 50, 'withdraw', '最低提现额度', NULL, 'minLimit', '200', 0, 'number', 0, 0, 3, 1, '2023-05-09 20:41:11', '2023-06-14 19:51:37', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (54, 50, 'withdraw', '最高提现额度', NULL, 'maxLimit', '2000', 0, 'number', 0, 0, 4, 1, '2023-05-09 20:41:11', '2023-06-14 19:51:37', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (55, 50, 'withdraw', '提现说明', NULL, 'withdrawRule', '1、由于微信支付手续费原因，每笔提现得手续费为1%\n2、请上传清晰，正确的微信个人收款码，建议采用系统截图\n3、提现预计在7个工作日内处理完成', 0, 'textarea', 0, 0, 5, 1, '2023-05-09 20:41:11', '2023-06-14 19:51:37', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (56, 8, 'wx.pay', '支付通道', NULL, 'channel', 'DIRECT', 0, 'radio', 0, 0, 0, 0, '2023-05-10 10:49:29', '2023-05-30 20:49:06', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (57, 7, 'wx.miniapp', '小程序logo', NULL, 'appLogo', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687863851728.png', 0, 'input', 0, 0, 0, 1, '2023-05-10 15:04:39', '2023-05-15 11:19:52', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (58, 0, 'agreement', '用户协议', NULL, 'agreement', NULL, 0, 'form', 1, 0, NULL, 0, '2023-05-10 15:34:42', '2023-05-31 14:33:04', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (59, 58, 'agreement', '用户隐私协议', NULL, 'privacy', '隐私政策\r\n本应用尊重并保护所有使用服务用户的个人隐私权。为了给您提供更准确、更有个性化的服务，本应用会按照本隐私权政策的规定使用和披露您的个人信息。但本应用将以高度的勤勉、审慎义务对待这些信息。除本隐私权政策另有规定外，在未征得您事先许可的情况下，本应用不会将这些信息对外披露或向第三方提供。本应用会不时更新本隐私权政策。 您在同意本应用服务使用协议之时，即视为您已经同意本隐私权政策全部内容。本隐私权政策属于本应用服务使用协议不可分割的一部分。\r\n\r\n1. 适用范围\r\na) 在您注册本应用帐号时，您根据本应用要求提供的个人注册信息；\r\nb) 在您使用本应用网络服务，或访问本应用平台网页时，本应用自动接收并记录的您的浏览器和计算机上的信息，包括但不限于您的IP地址、浏览器的类型、使用的语言、访问日期和时间、软硬件特征信息及您需求的网页记录等数据；\r\nc) 本应用通过合法途径从商业伙伴处取得的用户个人数据。\r\n您了解并同意，以下信息不适用本隐私权政策：\r\na) 您在使用本应用平台提供的搜索服务时输入的关键字信息；\r\nb) 本应用收集到的您在本应用发布的有关信息数据，包括但不限于参与活动、成交信息及评价详情；\r\nc) 违反法律规定或违反本应用规则行为及本应用已对您采取的措施。\r\n2. 信息使用\r\na) 本应用不会向任何无关第三方提供、出售、出租、分享或交易您的个人信息，除非事先得到您的许可，或该第三方和本应用（含本应用关联公司）单独或共同为您提供服务，且在该服务结束后，其将被禁止访问包括其以前能够访问的所有这些资料。\r\nb) 本应用亦不允许任何第三方以任何手段收集、编辑、出售或者无偿传播您的个人信息。任何本应用平台用户如从事上述活动，一经发现，本应用有权立即终止与该用户的服务协议。\r\nc) 为服务用户的目的，本应用可能通过使用您的个人信息，向您提供您感兴趣的信息，包括但不限于向您发出产品和服务信息，或者与本应用合作伙伴共享信息以便他们向您发送有关其产品和服务的信息（后者需要您的事先同意）。\r\n3. 信息披露\r\n在如下情况下，本应用将依据您的个人意愿或法律的规定全部或部分的披露您的个人信息：\r\na) 经您事先同意，向第三方披露；\r\nb) 为提供您所要求的产品和服务，而必须和第三方分享您的个人信息；\r\nc) 根据法律的有关规定，或者行政或司法机构的要求，向第三方或者行政、司法机构披露；\r\nd) 如您出现违反中国有关法律、法规或者本应用服务协议或相关规则的情况，需要向第三方披露；\r\ne) 如您是适格的知识产权投诉人并已提起投诉，应被投诉人要求，向被投诉人披露，以便双方处理可能的权利纠纷；\r\nf) 在本应用平台上创建的某一交易中，如交易任何一方履行或部分履行了交易义务并提出信息披露请求的，本应用有权决定向该用户提供其交易对方的联络方式等必要信息，以促成交易的完成或纠纷的解决。\r\ng) 其它本应用根据法律、法规或者网站政策认为合适的披露。\r\n\r\n4. 信息存储和交换\r\n本应用收集的有关您的信息和资料将保存在本应用及（或）其关联公司的服务器上，这些信息和资料可能传送至您所在国家、地区或本应用收集信息和资料所在地的境外并在境外被访问、存储和展示。\r\n5. Cookie的使用\r\na) 在您未拒绝接受cookies的情况下，本应用会在您的计算机上设定或取用cookies ，以便您能登录或使用依赖于cookies的本应用平台服务或功能。本应用使用cookies可为您提供更加周到的个性化服务，包括推广服务。\r\nb) 您有权选择接受或拒绝接受cookies。您可以通过修改浏览器设置的方式拒绝接受cookies。但如果您选择拒绝接受cookies，则您可能无法登录或使用依赖于cookies的本应用网络服务或功能。\r\nc) 通过本应用所设cookies所取得的有关信息，将适用本政策。\r\n6. 信息安全\r\na) 本应用帐号均有安全保护功能，请妥善保管您的用户名及密码信息。本应用将通过对用户密码进行加密等安全措施确保您的信息不丢失，不被滥用和变造。尽管有前述安全措施，但同时也请您注意在信息网络上不存在“完善的安全措施”。\r\nb) 在使用本应用网络服务进行网上交易时，您不可避免的要向交易对方或潜在的交易对方披露自己的个人信息，如联络方式或者邮政地址。请您妥善保护自己的个人信息，仅在必要的情形下向他人提供。如您发现自己的个人信息泄密，尤其是本应用用户名及密码发生泄露，请您立即联络本应用客服，以便本应用采取相应措施。\r\n\r\n用户协议\r\n\"当您申请用户时，表示您已经同意遵守本规章。\r\n\r\n欢迎您加入本站点参与交流和讨论，本站点为社区，为维护网上公共秩序和社会稳定，请您自觉遵守以下条款：\r\n\r\n一、不得利用本站危害国家安全、泄露国家秘密，不得侵犯国家社会集体的和公民的合法权益，不得利用本站制作、复制和传播下列信息：\r\n（一）煽动抗拒、破坏宪法和法律、行政法规实施的；\r\n（二）煽动颠覆国家政权，推翻社会主义制度的；\r\n（三）煽动分裂国家、破坏国家统一的；\r\n（四）煽动民族仇恨、民族歧视，破坏民族团结的；\r\n（五）捏造或者歪曲事实，散布谣言，扰乱社会秩序的；\r\n（六）宣扬封建迷信、淫秽、色情、赌博、暴力、凶杀、恐怖、教唆犯罪的；　\r\n（七）公然侮辱他人或者捏造事实诽谤他人的，或者进行其他恶意攻击的；\r\n（八）损害国家机关信誉的；\r\n（九）其他违反宪法和法律行政法规的；\r\n（十）进行商业广告行为的。\r\n二、互相尊重，对自己的言论和行为负责。\r\n三、禁止在申请用户时使用相关本站的词汇，或是带有侮辱、毁谤、造谣类的或是有其含义的各种语言进行注册用户，否则我们会将其删除。\r\n四、禁止以任何方式对本站进行各种破坏行为。\r\n五、如果您有违反国家相关法律法规的行为，本站概不负责，您的登录信息均被记录无疑，必要时，我们会向相关的国家管理部门提供此类信息。\"', 0, 'textarea', 0, 0, 1, 1, '2023-05-10 15:36:15', '2023-05-31 14:33:04', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (60, 0, 'website', '站点配置', NULL, 'website', NULL, 0, 'form', 1, 0, NULL, 1, '2023-05-15 11:12:11', '2023-05-15 16:17:45', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (61, 60, 'website', '站点logo', NULL, 'logo', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687863851728.png', 0, 'input', 0, 0, NULL, 1, '2023-05-15 11:15:08', '2023-05-15 16:17:45', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (62, 60, 'website', '站点名称', NULL, 'siteName', '千麦生鲜商城', 0, 'input', 0, 0, NULL, 1, '2023-05-15 11:15:08', '2023-05-15 16:17:45', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (63, 60, 'website', '站点介绍', NULL, 'description', '千麦生鲜商城管理系统，五合一社交电商小程序系统', 0, 'textarea', 0, 0, NULL, 1, '2023-05-15 11:16:57', '2023-05-15 16:17:45', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (64, 60, 'website', '登录页背景图', NULL, 'background', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684121447519.png', 0, 'input', 0, 0, NULL, 1, '2023-05-15 11:16:57', '2023-05-15 16:17:45', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (69, 7, 'wx.miniapp', '小程序原始id', NULL, 'input', NULL, 0, 'input', 0, 0, NULL, 0, '2023-05-15 11:19:52', '2023-05-15 11:19:52', 1, 1, 0);
INSERT INTO `kz_sys_property` VALUES (70, 60, 'website', '备案号', NULL, 'beian', '浙ICP备2021017792号', 0, 'input', 0, 0, NULL, 0, '2023-05-15 16:17:45', '2023-05-15 16:17:45', 1, 1, 0);
INSERT INTO `kz_sys_property` VALUES (71, 42, 'promote', '分销海报', NULL, 'posterImgs', '[\"https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684406829072.png\",\"https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684406829082.png\"]', 1, 'input', 0, 0, NULL, 1, '2023-05-18 18:40:43', '2023-05-18 18:40:43', 1, 1, 0);
INSERT INTO `kz_sys_property` VALUES (72, 0, 'wx.open', '微信开放平台', NULL, 'wx.open', NULL, 0, 'form', 1, 0, NULL, 0, '2023-05-19 18:11:36', '2023-05-19 18:21:29', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (79, 72, 'wx.open', 'appId', NULL, 'componentAppId', 'wx3c2689bf3092c4eb', 0, 'input', 0, 0, NULL, 0, '2023-05-19 18:15:12', '2023-05-19 18:21:29', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (80, 72, 'wx.open', 'appSecret', NULL, 'componentAppSecret', 'c8c3413b872723b749ecee8e5c292fb3', 0, 'input', 0, 0, NULL, 0, '2023-05-19 18:15:12', '2023-05-19 18:21:29', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (81, 72, 'wx.open', '消息校验Token', NULL, 'componentToken', 'KpnZ299NpuE33UK38urPNuqKu92UuEBP', 0, 'input', 0, 0, NULL, 0, '2023-05-19 18:15:12', '2023-05-19 18:21:29', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (82, 72, 'wx.open', '消息加解密Key', NULL, 'componentAesKey', 'unI5U5F5Gr8eightI5IoU2ebeeR5INzS282OI228EDB', 0, 'input', 0, 0, NULL, 0, '2023-05-19 18:15:12', '2023-05-19 18:21:29', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (83, 72, 'wx.open', 'verifyTicket', NULL, 'componentVerifyTicket', 'ticket@@@CfdeVxGePHpgLxxoACj1KCqVn_dDfk2Tp4Q47JQbC42iadbOtd8ko8LfbTC36IrlTaye3j7piMaWah3_dXpKMA', 0, 'input', 0, 0, NULL, 0, '2023-05-29 15:41:33', '2023-05-29 15:41:35', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (84, 58, 'agreement', '充值说明', NULL, 'rechargeDesc', '1、充值后帐户的金额不可提现，可用于商城消费使用\n2、佣金导入账户之后不能再次导出\n3、账户充值出现问题可联系商城客服，也可拨打商城客服热线\n4、充值赠送活动，请选择如上充值方案，自定义充值金额不享受赠送福利', 0, 'textarea', 0, 0, 2, 0, '2023-05-26 15:14:49', '2023-05-31 14:33:04', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (85, 8, 'wx.pay', '支付回调地址', NULL, 'notifyUrl', 'https://api.b2c.kinstore.cn/callback/notify/order', 0, 'input', 0, 0, 5, 0, '2023-05-30 20:42:51', '2023-05-30 20:49:06', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (86, 58, 'agreement', '划线价说明', NULL, 'underlineDesc', '划线价格：划线的价格可能是商品的专柜价、吊牌价、正品零售价、指导价、曾经展示过的销售价等，仅供您参考。\n\n未划线价格：未划线的价格是商品的销售标价，具体的成交价格可能因会员使用优惠券、积分等发生变化，最终以订单结算价格为准。\n\n若商家针对划线价格进行说明，以商家的表述为准。', 0, 'textarea', 0, 0, 3, 1, '2023-05-31 14:33:04', '2023-05-31 14:33:04', 1, 1, 0);
INSERT INTO `kz_sys_property` VALUES (87, 0, 'credit', '积分设置', NULL, 'credit', NULL, 0, 'form', 1, 0, NULL, 0, '2023-06-05 11:01:57', '2023-06-05 11:04:53', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (91, 87, 'credit', '签到积分奖励', NULL, 'checkInPrice', '10', 0, 'input', 0, 0, 1, 1, '2023-06-05 11:04:53', '2023-06-05 11:04:53', 1, 1, 0);
INSERT INTO `kz_sys_property` VALUES (92, 87, 'credit', '购买商品', NULL, 'buyProdPrice', '1', 0, 'input', 0, 0, 2, 1, '2023-06-05 11:04:53', '2023-06-05 11:04:53', 1, 1, 0);
INSERT INTO `kz_sys_property` VALUES (93, 87, 'credit', '评论积分奖励', NULL, 'commentPrice', '20', 0, 'input', 0, 0, 3, 1, '2023-06-05 11:04:53', '2023-06-05 11:04:53', 1, 1, 0);
INSERT INTO `kz_sys_property` VALUES (94, 87, 'credit', '积分规则', NULL, 'creditRule', '1、注册奖励：用户在小程序中完成注册后，将获得积分作为注册奖励。\n\n2、消费积分：用户在小程序中消费1元，将获得积分作为消费积分。例如，每消费1元获得1积分。\n\n3、分享奖励：用户将小程序分享给好友，并且好友通过分享链接成功注册并完成首次消费，用户将获得Z积分作为分享奖励。\n\n4、连续签到：用户每天登录小程序并签到，连续签到天数递增。连续签到奖励将根据连续签到天数提供不同的积分奖励。例如，第1天签到获得5积分，第2天签到获得10积分，第3天签到获得15积分，以此类推。\n\n5、活动奖励：小程序定期举办各种活动，用户参与活动并完成指定任务后将获得额外的积分奖励。\n\n6、等级制度：用户的积分累积达到一定阈值后，将升级为不同等级。不同等级的用户将享受不同的特权和额外的积分奖励。\n\n7、积分兑换：用户可以使用积分在小程序中兑换不同的优惠券、礼品或特殊服务。', 0, 'textarea', 0, 0, 4, 1, '2023-06-05 11:04:53', '2023-06-05 11:04:53', 1, 1, 0);
INSERT INTO `kz_sys_property` VALUES (95, 0, 'print', '打印设置', NULL, 'print', NULL, 0, 'form', 1, 0, NULL, 0, '2023-06-16 11:27:41', '2023-06-16 14:29:26', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (96, 95, 'print', '选择打印机', NULL, 'printerId', '17', 0, 'number', 0, 0, 2, 0, '2023-06-16 11:31:04', '2023-06-16 14:29:26', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (97, 95, 'print', '选择打印模板', NULL, 'templateId', '19', 0, 'number', 0, 0, 3, 0, '2023-06-16 11:31:04', '2023-06-16 14:29:26', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (98, 95, 'print', '订单打印方式', NULL, 'printScope', '[\"PAID\",\"ORDER\"]', 1, 'checkbox', 0, 0, 4, 0, '2023-06-16 11:31:04', '2023-06-16 14:29:26', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (99, 95, 'print', '开启打印', NULL, 'printSwitch', '1', 0, 'switch', 0, 0, 1, 0, '2023-06-16 14:28:54', '2023-06-16 14:29:26', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (101, 0, 'mp', '微信公众号配置', NULL, 'mp', NULL, 0, 'form', 1, 0, NULL, 0, '2023-06-21 14:32:38', '2023-06-21 14:41:28', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (102, 101, 'mp', '公众号名称', NULL, 'mpTitle', '惠速达配送', 0, 'input', 0, 0, 1, 1, '2023-06-21 14:34:16', '2023-06-21 14:41:28', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (103, 101, 'mp', '公众号二维码', NULL, 'mpQrCode', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687330121841.png', 0, 'input', 0, 0, 2, 1, '2023-06-21 14:34:16', '2023-06-21 14:41:28', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (104, 101, 'mp', '公众号logo', NULL, 'mpLogo', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684481268957.png', 0, 'input', 0, 0, 3, 1, '2023-06-21 14:41:28', '2023-06-21 14:41:28', 1, 1, 0);
INSERT INTO `kz_sys_property` VALUES (107, 10, 'oss', '区域', NULL, 'region', 'oss-cn-hangzhou', 0, 'select', 0, 0, 2, 1, '2023-06-26 14:17:29', '2023-06-26 14:33:13', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (108, 10, 'oss', 'bucket', NULL, 'bucket', 'kmp-oss', 0, 'input', 0, 0, 3, 1, '2023-06-26 14:17:29', '2023-06-26 14:33:13', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (109, 10, 'oss', 'accessKeyId', NULL, 'oAccessKeyId', 'LTAI5t7JR7wwTXgZ32gJaen6', 0, 'input', 0, 0, 4, 1, '2023-06-26 14:19:40', '2023-06-26 14:33:13', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (110, 10, 'oss', 'accessKeySecret', NULL, 'oAccessKeySecret', 'OvLcLDVgzqN7SMggHMLVcwMjcZhCxk', 0, 'password', 0, 0, 5, 1, '2023-06-26 14:19:40', '2023-06-26 14:33:13', NULL, 1, 0);
INSERT INTO `kz_sys_property` VALUES (118, 10, 'oss', '存储引擎', NULL, 'oEngine', 'oss', 0, 'radio', 0, 0, 1, 1, '2023-06-26 14:33:12', '2023-06-26 14:33:12', 1, 1, 0);
INSERT INTO `kz_sys_property` VALUES (119, 22, 'shopSettings', '副主题色', NULL, 'secondColor', '#FFBD41', 0, 'color', 0, 0, 5, 1, '2023-06-26 16:38:12', '2023-06-26 16:38:12', 1, 1, 0);


-- ----------------------------
-- Table structure for kz_sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_quartz_job`;
CREATE TABLE `kz_sys_quartz_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Spring Bean名称',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron 表达式',
  `is_pause` bit(1) NULL DEFAULT NULL COMMENT '状态：1暂停、0启用',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `is_delete` tinyint(1) NULL DEFAULT 0,
  `enabled` tinyint(1) NULL DEFAULT 1,
  `tenant_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_sys_quartz_job
-- ----------------------------
INSERT INTO `kz_sys_quartz_job` VALUES (1, 'visitsTask', '0 0 0 * * ?', b'1', '更新访客记录', 'run', NULL, '每日0点创建新的访客记录', NULL, '2019-01-08 14:53:31', '2023-03-13 11:32:12', 0, 1, 0);
INSERT INTO `kz_sys_quartz_job` VALUES (4, 'orderSyncTask', '0/5 * * * * ?', b'1', '订单支付主动同步', 'syncQueueOrder', NULL, '主动同步订单支付状态，2秒一次处理订单队列', 1, '2023-05-18 19:45:42', '2023-05-30 20:22:28', 0, 1, 0);
INSERT INTO `kz_sys_quartz_job` VALUES (5, 'couponTask', '0 5 0 ? * *', b'0', '用户优惠券过期检测', 'userCouponCheck', NULL, '每天凌晨5分检测用户优惠券过期', 1, '2023-06-01 17:57:22', '2023-06-01 18:10:22', 0, 1, 0);
INSERT INTO `kz_sys_quartz_job` VALUES (6, 'seckillTask', '0/5 * * * * ?', b'1', '秒杀活动检测', 'run', NULL, '检测秒杀活动状态，自动开启状态，或者关闭', 1, '2023-06-01 20:07:40', '2023-07-04 09:07:54', 0, 1, 0);
INSERT INTO `kz_sys_quartz_job` VALUES (7, 'couponTask', '0 0 0/1 * * ?', b'0', '优惠券检测任务', 'couponCheck', NULL, '优惠券开放或者过期检测任务', 1, '2023-06-06 14:14:25', '2023-06-06 14:16:24', 0, 1, 0);
INSERT INTO `kz_sys_quartz_job` VALUES (8, 'combinationTask', '0 0 0/1 * * ?', b'0', '拼团活动检测', 'run', NULL, '每小时一次拼团活动检测', 1, '2023-06-07 11:23:57', '2023-06-07 11:23:57', 0, 1, 0);
INSERT INTO `kz_sys_quartz_job` VALUES (9, 'combinationTask', '0 0/1 * * * ?', b'0', '拼团记录检测', 'checkLog', NULL, '拼团成功，失败检测', 1, '2023-06-07 15:12:30', '2023-06-07 15:17:30', 0, 1, 0);
INSERT INTO `kz_sys_quartz_job` VALUES (10, 'bargainTask', '0 0/5 * * * ?', b'0', '砍价活动检测', 'run', NULL, '砍价活动检测,根据开始，结束时间，开启关闭', 1, '2023-06-07 20:09:00', '2023-06-07 20:12:05', 0, 1, 0);
INSERT INTO `kz_sys_quartz_job` VALUES (11, 'storeOrderTask', '0 0/5 * * * ?', b'0', '过期订单清理', 'closeOrder', NULL, '过期订单清理', 1, '2023-06-12 20:44:21', '2023-06-12 20:58:58', 0, 1, 0);
INSERT INTO `kz_sys_quartz_job` VALUES (12, 'printerTask', '0 0/5 * * * ?', b'0', '同步打印机状态', 'run', NULL, '同步打印机状态', 1, '2023-06-15 20:00:31', '2023-06-15 20:00:31', 0, 1, 0);

-- ----------------------------
-- Table structure for kz_sys_quartz_log
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_quartz_log`;
CREATE TABLE `kz_sys_quartz_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_id` bigint(20) NULL DEFAULT NULL,
  `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `is_success` bit(1) NULL DEFAULT NULL,
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` bigint(20) NULL DEFAULT NULL,
  `create_user` int(255) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `is_delete` tinyint(1) NULL DEFAULT 0,
  `enabled` tinyint(1) NULL DEFAULT 1,
  `tenant_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 859104 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务日志' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for kz_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_role`;
CREATE TABLE `kz_sys_role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `data_scope` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据范围',
  `is_system` tinyint(2) NULL DEFAULT 0 COMMENT '是否是系统权限',
  `is_hidden` tinyint(2) NULL DEFAULT NULL COMMENT '是否隐藏',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `un_role`(`role_name`) USING BTREE COMMENT '角色唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_sys_role
-- ----------------------------
INSERT INTO `kz_sys_role` VALUES (1, 'SA', '超级管理员', NULL, 1, 1, '2022-04-01 10:00:39', '2022-12-29 11:39:03', NULL, 1, 0, 0);
INSERT INTO `kz_sys_role` VALUES (2, 'ADMIN', '管理员', NULL, 1, 1, '2021-07-08 10:27:05', '2021-07-08 10:27:12', NULL, 1, 0, 0);
INSERT INTO `kz_sys_role` VALUES (3, 'COMPANY', '机构', NULL, 1, 0, '2021-07-08 10:27:07', '2022-12-03 14:55:05', NULL, 1, 0, 0);
INSERT INTO `kz_sys_role` VALUES (4, 'AGENT', '代理商', NULL, 1, 0, '2022-03-28 14:09:10', '2022-03-28 14:11:35', NULL, 1, 0, 0);
INSERT INTO `kz_sys_role` VALUES (6, 'STORE', '门店', NULL, 1, 0, '2022-11-25 19:52:41', '2022-11-25 19:52:41', NULL, 1, 0, 0);
INSERT INTO `kz_sys_role` VALUES (7, 'SALE', '销售', NULL, 0, 0, '2022-11-25 19:52:41', '2022-12-29 11:07:51', NULL, 1, 0, 0);
INSERT INTO `kz_sys_role` VALUES (8, 'STORE_SINGLE', '单个门店', NULL, 0, 0, '2023-02-13 15:34:55', '2023-02-13 15:34:55', 62, 1, 0, 800009);

-- ----------------------------
-- Table structure for kz_sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_tenant`;
CREATE TABLE `kz_sys_tenant`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `agent_name` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构全称',
  `short_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构简称',
  `group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构组名',
  `app_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'APP名称',
  `sale_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '销售id',
  `parent_id` int(11) NULL DEFAULT NULL,
  `parent_path` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '依赖路径，如0-1-2',
  `store_num` int(11) NULL DEFAULT NULL,
  `level` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省市区',
  `address_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `user_limit` int(255) NULL DEFAULT NULL COMMENT '限制用户数',
  `pay_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '付费类型',
  `unlock_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '解锁方式，day,week',
  `unlock_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '解锁当周练习',
  `expired_limit` tinyint(255) NULL DEFAULT 0 COMMENT '超期开关',
  `expired_days` int(11) NULL DEFAULT NULL COMMENT '过期天数设置，不可见班级，练习，计划',
  `contact_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `is_delete` tinyint(2) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `un_phone`(`mobile`) USING BTREE COMMENT '手机号唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 800013 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for kz_sys_upload
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_upload`;
CREATE TABLE `kz_sys_upload`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件名',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件的绝对路径，本地可以使用相对路径',
  `file_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件类型，如图片，视频等',
  `directory_id` int(11) NOT NULL COMMENT '目录id',
  `hashed` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件hash',
  `extra` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  `is_delete` tinyint(2) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 193 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '上传文件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_sys_upload
-- ----------------------------
INSERT INTO `kz_sys_upload` VALUES (29, 'menu_2.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1680256520755.png', NULL, 33, NULL, NULL, 1, '2023-03-31 17:55:22', '2023-03-31 17:55:22', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (30, 'menu_2a.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1680256520758.png', NULL, 33, NULL, NULL, 1, '2023-03-31 17:55:22', '2023-03-31 17:55:22', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (31, 'home.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1680256520723.png', NULL, 33, NULL, NULL, 1, '2023-03-31 17:55:22', '2023-03-31 17:55:22', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (32, 'menu_1a.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1680256520748.png', NULL, 33, NULL, NULL, 1, '2023-03-31 17:55:22', '2023-03-31 17:55:22', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (33, 'sampling.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1680256520760.png', NULL, 33, NULL, NULL, 1, '2023-03-31 17:55:22', '2023-03-31 17:55:22', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (34, 'menu_1.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1680256520743.png', NULL, 33, NULL, NULL, 1, '2023-03-31 17:55:22', '2023-03-31 17:55:22', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (35, 'sampling_a.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1680256520763.png', NULL, 33, NULL, NULL, 1, '2023-03-31 17:55:22', '2023-03-31 17:55:22', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (36, 'user.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1680256520769.png', NULL, 33, NULL, NULL, 1, '2023-03-31 17:55:22', '2023-03-31 17:55:22', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (37, 'user_a.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1680256520773.png', NULL, 33, NULL, NULL, 1, '2023-03-31 17:55:22', '2023-03-31 17:55:22', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (38, 'home_a.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1680256520733.png', NULL, 33, NULL, NULL, 1, '2023-03-31 17:55:22', '2023-03-31 17:55:22', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (39, 'classify.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1680514493782.png', NULL, 33, NULL, NULL, 1, '2023-04-03 17:34:52', '2023-04-03 17:34:52', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (40, 'classify_a.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1680514493791.png', NULL, 33, NULL, NULL, 1, '2023-04-03 17:34:52', '2023-04-03 17:34:52', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (41, 'integralMall.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681199893387.png', NULL, 33, NULL, NULL, 1, '2023-04-11 15:58:14', '2023-04-11 15:58:14', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (42, 'orderCenter.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681199897959.png', NULL, 33, NULL, NULL, 1, '2023-04-11 15:58:18', '2023-04-11 15:58:18', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (43, 'recharge.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681199902244.png', NULL, 33, NULL, NULL, 1, '2023-04-11 15:58:22', '2023-04-11 15:58:22', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (44, 'recharge.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681265298666.png', NULL, 33, NULL, NULL, 1, '2023-04-12 10:08:21', '2023-04-12 10:08:21', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (45, 'cf50453062354784809e6ff9dbdaaff1.jpeg', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351634059.png', NULL, 33, NULL, NULL, 1, '2023-04-13 10:07:14', '2023-04-13 10:07:14', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (46, '1642573139744_4.jpg', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681351704244.png', NULL, 33, NULL, NULL, 1, '2023-04-13 10:08:24', '2023-04-13 10:08:24', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (48, '5f082af0193bb.jpg', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352148034.png', NULL, 33, NULL, NULL, 1, '2023-04-13 10:15:48', '2023-04-13 10:15:48', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (49, '20171030113546_3427.jpg', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352148044.png', NULL, 33, NULL, NULL, 1, '2023-04-13 10:15:48', '2023-04-13 10:15:48', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (50, 'cart.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352992372.png', NULL, 33, NULL, NULL, 1, '2023-04-13 10:29:52', '2023-04-13 10:29:52', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (51, 'cart_a.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352992380.png', NULL, 33, NULL, NULL, 1, '2023-04-13 10:29:52', '2023-04-13 10:29:52', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (52, '296112397_481761957283889_2054788084825031347_n.jpg', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681371349636.png', NULL, 33, NULL, NULL, 1, '2023-04-13 15:35:50', '2023-04-13 15:35:50', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (53, 'e9b262ec60c117f8ed76c1b70319f64a.jpg', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681371391192.png', NULL, 33, NULL, NULL, 1, '2023-04-13 15:36:31', '2023-04-13 15:36:31', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (54, 'dd6e468069e64af0be67f6d8a4dba090.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681375393816.png', NULL, 33, NULL, NULL, 1, '2023-04-13 16:43:14', '2023-04-13 16:43:14', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (55, 'lb.jpg', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681375393826.png', NULL, 33, NULL, NULL, 1, '2023-04-13 16:43:14', '2023-04-13 16:43:14', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (57, '0d8d1234dd9fc0220e354d8dc853cc18.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442035.png', NULL, 34, NULL, NULL, 1, '2023-04-26 10:50:42', '2023-04-26 10:50:42', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (58, 'f53a741be75061c46a4dbc3a7848dd0f.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442052.png', NULL, 34, NULL, NULL, 1, '2023-04-26 10:50:42', '2023-04-26 10:50:42', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (59, '02413380e8cfa9765a899b3bb3ea2ac5.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442049.png', NULL, 34, NULL, NULL, 1, '2023-04-26 10:50:42', '2023-04-26 10:50:42', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (60, '1f3dcfb92c6c0de32277f3f1c39c1beb.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442040.png', NULL, 34, NULL, NULL, 1, '2023-04-26 10:50:42', '2023-04-26 10:50:42', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (61, '041d82f1897ca8ea80e90691ecf3bf22.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442047.png', NULL, 34, NULL, NULL, 1, '2023-04-26 10:50:42', '2023-04-26 10:50:42', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (62, 'dda3470f4ee342e1d469708ab94f41ac.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442050.png', NULL, 34, NULL, NULL, 1, '2023-04-26 10:50:42', '2023-04-26 10:50:42', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (63, '7d730d11416f36ac6739036942662fc1.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442046.png', NULL, 34, NULL, NULL, 1, '2023-04-26 10:50:42', '2023-04-26 10:50:42', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (64, '6a856e7439f50480d6a1c95852b34a11.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442044.png', NULL, 34, NULL, NULL, 1, '2023-04-26 10:50:42', '2023-04-26 10:50:42', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (65, '5ba52baa1852c480f9f6905d18f2d9ac.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442042.png', NULL, 34, NULL, NULL, 1, '2023-04-26 10:50:42', '2023-04-26 10:50:42', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (66, '5f4e85de6ca059cc8a0bde3c05a68635.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442043.png', NULL, 34, NULL, NULL, 1, '2023-04-26 10:50:42', '2023-04-26 10:50:42', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (67, '列表页-VIP角标.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682479764583.png', NULL, 34, NULL, NULL, 1, '2023-04-26 11:29:25', '2023-04-26 11:29:25', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (68, 'x.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682582997379.png', NULL, 33, NULL, NULL, 1, '2023-04-27 16:09:58', '2023-04-27 16:09:58', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (69, 'd0649b995a50de8137b7be59ff97ff0c.jpg', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682583071750.png', NULL, 33, NULL, NULL, 1, '2023-04-27 16:11:15', '2023-04-27 16:11:15', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (70, 'payment.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683618636233.png', NULL, 33, NULL, NULL, 1, '2023-05-09 15:50:39', '2023-05-09 15:50:39', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (71, 'WiFi.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683618664655.png', NULL, 33, NULL, NULL, 1, '2023-05-09 15:51:07', '2023-05-09 15:51:07', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (72, 'location.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683618683418.png', NULL, 33, NULL, NULL, 1, '2023-05-09 15:51:26', '2023-05-09 15:51:26', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (73, 'coupon.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683618692507.png', NULL, 33, NULL, NULL, 1, '2023-05-09 15:51:35', '2023-05-09 15:51:35', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (74, 'logout.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683619494565.png', NULL, 33, NULL, NULL, 1, '2023-05-09 16:04:57', '2023-05-09 16:04:57', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (75, 'recharge.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683619665956.png', NULL, 33, NULL, NULL, 1, '2023-05-09 16:07:48', '2023-05-09 16:07:48', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (76, 'find.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683619866824.png', NULL, 33, NULL, NULL, 1, '2023-05-09 16:11:09', '2023-05-09 16:11:09', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (77, 'promotion.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683619876880.png', NULL, 33, NULL, NULL, 1, '2023-05-09 16:11:19', '2023-05-09 16:11:19', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (78, '砍价.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504308.png', NULL, 35, NULL, NULL, 1, '2023-05-09 16:38:24', '2023-05-09 16:38:24', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (79, '领券.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504310.png', NULL, 35, NULL, NULL, 1, '2023-05-09 16:38:24', '2023-05-09 16:38:24', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (80, '排行榜.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504313.png', NULL, 35, NULL, NULL, 1, '2023-05-09 16:38:24', '2023-05-09 16:38:24', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (81, '代言.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504305.png', NULL, 35, NULL, NULL, 1, '2023-05-09 16:38:24', '2023-05-09 16:38:24', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (82, '美发丽人.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504311.png', NULL, 35, NULL, NULL, 1, '2023-05-09 16:38:24', '2023-05-09 16:38:24', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (83, '品牌甄选.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504316.png', NULL, 35, NULL, NULL, 1, '2023-05-09 16:38:24', '2023-05-09 16:38:24', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (84, '签到.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504317.png', NULL, 35, NULL, NULL, 1, '2023-05-09 16:38:24', '2023-05-09 16:38:24', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (85, '拼团.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504314.png', NULL, 35, NULL, NULL, 1, '2023-05-09 16:38:25', '2023-05-09 16:38:25', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (86, '邀请有礼.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504320.png', NULL, 35, NULL, NULL, 1, '2023-05-09 16:38:25', '2023-05-09 16:38:25', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (87, '积分.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504307.png', NULL, 35, NULL, NULL, 1, '2023-05-09 16:38:25', '2023-05-09 16:38:25', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (88, '商家入驻.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504318.png', NULL, 35, NULL, NULL, 1, '2023-05-09 16:38:25', '2023-05-09 16:38:25', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (89, '充值.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683621504295.png', NULL, 35, NULL, NULL, 1, '2023-05-09 16:38:25', '2023-05-09 16:38:25', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (90, '待办.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683631713551.png', NULL, 36, NULL, NULL, 1, '2023-05-09 19:28:34', '2023-05-09 19:28:34', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (91, '收付款.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683631713555.png', NULL, 36, NULL, NULL, 1, '2023-05-09 19:28:34', '2023-05-09 19:28:34', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (92, '订单管理.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683631713529.png', NULL, 36, NULL, NULL, 1, '2023-05-09 19:28:34', '2023-05-09 19:28:34', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (93, '待收货.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683631713554.png', NULL, 36, NULL, NULL, 1, '2023-05-09 19:28:34', '2023-05-09 19:28:34', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (95, '订单打回修改.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683631713549.png', NULL, 36, NULL, NULL, 1, '2023-05-09 19:28:34', '2023-05-09 19:28:34', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (96, '待发货 (1).png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1683631749707.png', NULL, 36, NULL, NULL, 1, '2023-05-09 19:29:10', '2023-05-09 19:29:10', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (97, '购物类网站登录页.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684121447519.png', NULL, 33, NULL, NULL, 1, '2023-05-15 11:30:48', '2023-05-15 11:30:48', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (98, '商城.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684123040893.png', NULL, 33, NULL, NULL, 1, '2023-05-15 11:57:21', '2023-05-15 11:57:21', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (99, '交易保障险.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684202268844.png', NULL, 37, NULL, NULL, 1, '2023-05-16 09:57:49', '2023-05-16 09:57:49', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (100, '7天退换.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684202280754.png', NULL, 37, NULL, NULL, 1, '2023-05-16 09:58:01', '2023-05-16 09:58:01', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (101, '15分钟响应.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684202292537.png', NULL, 37, NULL, NULL, 1, '2023-05-16 09:58:13', '2023-05-16 09:58:13', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (102, '客服旺旺.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684202308006.png', NULL, 37, NULL, NULL, 1, '2023-05-16 09:58:28', '2023-05-16 09:58:28', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (103, '意见反馈.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684202370271.png', NULL, 37, NULL, NULL, 1, '2023-05-16 09:59:30', '2023-05-16 09:59:30', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (104, '扫码.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684202370273.png', NULL, 37, NULL, NULL, 1, '2023-05-16 09:59:30', '2023-05-16 09:59:30', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (105, '服务保障.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684202374932.png', NULL, 37, NULL, NULL, 1, '2023-05-16 09:59:35', '2023-05-16 09:59:35', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (106, 'bianji.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804784.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (107, 'biaodan.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804793.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (108, 'fenxiang.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804801.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (109, 'dizhi.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804796.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (110, 'gerenzhongxin.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804804.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (111, 'fenlei.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804799.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (112, 'jiangpin.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804808.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (113, 'dizhi_1.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804798.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (114, 'lianxiwomen.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804813.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (115, 'gouwuche.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804807.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (116, 'gongzuotongji.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804805.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (117, 'gengduo.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804803.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (118, 'kucun.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804811.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (119, 'jine.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804810.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (120, 'shouye.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804823.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (121, 'saoma.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804819.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (122, 'liulanqi.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804816.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (123, 'lishi.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804814.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (124, 'shanchu.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804820.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (125, 'shezhi.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804822.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (126, 'shujia.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804824.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (127, 'rili.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804817.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (128, 'biaoqian.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804795.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (129, 'sousuo.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804826.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (130, 'xiangji.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804831.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (131, 'wode.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804830.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (132, 'wanjie.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804828.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (133, 'tupian.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804827.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (134, 'zhibo.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804837.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (135, 'xiaoxi.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804832.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (136, 'yinhangqia.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804834.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (137, 'youhuiquan.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684222804835.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:40:05', '2023-05-16 15:40:05', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (138, 'home.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684223515302.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:51:55', '2023-05-16 15:51:55', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (139, 'fa.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684223739094.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:55:39', '2023-05-16 15:55:39', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (140, 'user.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684223739095.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:55:39', '2023-05-16 15:55:39', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (141, 'cate.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684223739092.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:55:39', '2023-05-16 15:55:39', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (142, 'cart.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684223739090.png', NULL, 38, NULL, NULL, 1, '2023-05-16 15:55:39', '2023-05-16 15:55:39', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (143, 'spread_2.jpeg', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684406829082.png', NULL, 39, NULL, NULL, 1, '2023-05-18 18:47:09', '2023-05-18 18:47:09', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (144, 'spread_1.jpeg', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684406829072.png', NULL, 39, NULL, NULL, 1, '2023-05-18 18:47:09', '2023-05-18 18:47:09', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (145, 'set-up-dot.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684466334668.png', NULL, 33, NULL, NULL, 1, '2023-05-19 11:18:53', '2023-05-19 11:18:53', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (148, 'logo1.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684481268957.png', NULL, 33, NULL, NULL, 1, '2023-05-19 15:27:49', '2023-05-19 15:27:49', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (151, 'pintuan.gif', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684831479354.png', NULL, 33, NULL, NULL, 1, '2023-05-23 16:44:41', '2023-05-23 16:44:41', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (152, 'seckill.gif', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1684831479361.png', NULL, 33, NULL, NULL, 1, '2023-05-23 16:44:41', '2023-05-23 16:44:41', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (153, '春季尝鲜-生鲜果蔬手机banner.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433494355.png', NULL, 40, NULL, NULL, 1, '2023-05-30 15:58:16', '2023-05-30 15:58:16', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (154, 'shucaishuiguo.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433494351.png', NULL, 40, NULL, NULL, 1, '2023-05-30 15:58:16', '2023-05-30 15:58:16', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (155, '果蔬.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433494356.png', NULL, 40, NULL, NULL, 1, '2023-05-30 15:58:16', '2023-05-30 15:58:16', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (156, '蔬菜.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433494358.png', NULL, 40, NULL, NULL, 1, '2023-05-30 15:58:16', '2023-05-30 15:58:16', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (157, '春季尝鲜(1).png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433665374.png', NULL, 40, NULL, NULL, 1, '2023-05-30 16:01:06', '2023-05-30 16:01:06', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (158, '1春季尝鲜-生鲜果蔬手机banner(1).png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685433665364.png', NULL, 40, NULL, NULL, 1, '2023-05-30 16:01:06', '2023-05-30 16:01:06', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (159, '168109231864336ede81ae9.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163665.png', NULL, 41, NULL, NULL, 1, '2023-05-30 18:06:04', '2023-05-30 18:06:04', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (160, '4331320d3636430a545b558ae9d9ed69.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163662.png', NULL, 41, NULL, NULL, 1, '2023-05-30 18:06:04', '2023-05-30 18:06:04', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (161, '388dcbc58f1bbae702ee38378374f6a7.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163659.png', NULL, 41, NULL, NULL, 1, '2023-05-30 18:06:04', '2023-05-30 18:06:04', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (162, '1669953264638976f0639d5.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163666.png', NULL, 41, NULL, NULL, 1, '2023-05-30 18:06:04', '2023-05-30 18:06:04', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (163, '157927ee8883d476c845c133aee4b02f.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163660.png', NULL, 41, NULL, NULL, 1, '2023-05-30 18:06:04', '2023-05-30 18:06:04', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (164, '168109197964336d8b74bb5.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163663.png', NULL, 41, NULL, NULL, 1, '2023-05-30 18:06:04', '2023-05-30 18:06:04', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (165, 'b6cc477243dfae90bee3dd52018e9df7.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163667.png', NULL, 41, NULL, NULL, 1, '2023-05-30 18:06:04', '2023-05-30 18:06:04', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (166, 'ba2c004c2699f6747d9a66aeebb3f97e.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163669.png', NULL, 41, NULL, NULL, 1, '2023-05-30 18:06:04', '2023-05-30 18:06:04', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (167, '50c101cde5c7f485dd9ecf6e6236dd50.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163657.png', NULL, 41, NULL, NULL, 1, '2023-05-30 18:06:04', '2023-05-30 18:06:04', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (168, '3e5836993bffc5b49a0c9412d1045225.gif', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163651.gif', NULL, 41, NULL, NULL, 1, '2023-05-30 18:06:04', '2023-05-30 18:06:04', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (169, 'ff2898bd39767e2f64c774d9f577acb4.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163670.png', NULL, 41, NULL, NULL, 1, '2023-05-30 18:06:04', '2023-05-30 18:06:04', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (170, '4cabe1bec86b82d4a2d174baed92a354.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441163656.png', NULL, 41, NULL, NULL, 1, '2023-05-30 18:06:04', '2023-05-30 18:06:04', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (171, '1663903655632d27a7ec72c.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685441375448.png', NULL, 41, NULL, NULL, 1, '2023-05-30 18:09:36', '2023-05-30 18:09:36', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (172, 'se.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685503212918.png', NULL, 40, NULL, NULL, 1, '2023-05-31 11:20:13', '2023-05-31 11:20:13', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (173, '未标题-1.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685513530950.png', NULL, 40, NULL, NULL, 1, '2023-05-31 14:12:11', '2023-05-31 14:12:11', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (174, '未标题-3.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685513609356.png', NULL, 40, NULL, NULL, 1, '2023-05-31 14:13:29', '2023-05-31 14:13:29', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (175, '未标题-2.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1685513609354.png', NULL, 40, NULL, NULL, 1, '2023-05-31 14:13:29', '2023-05-31 14:13:29', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (176, 'c9e072cc6bdd9f5c3339cbc69b3de4f19fad6f597301a-jAJPdl.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1686534447802.png', NULL, 41, NULL, NULL, 1, '2023-06-12 09:47:28', '2023-06-12 09:47:28', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (177, 'qrcode_for_gh_4607ea89dc2f_258.jpg', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687330121841.png', NULL, 33, NULL, NULL, 1, '2023-06-21 14:48:42', '2023-06-21 14:48:42', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (178, 'qm.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687682653451.png', NULL, 33, NULL, NULL, 1, '2023-06-25 16:44:14', '2023-06-25 16:44:14', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (179, 'notice_icon.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687762438932.png', NULL, 33, NULL, NULL, 1, '2023-06-26 14:53:58', '2023-06-26 14:53:58', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (184, 'mallcode.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687764449453.png', NULL, 33, NULL, NULL, 1, '2023-06-26 15:27:28', '2023-06-26 15:27:28', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (185, 'goldB.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687860311653.png', NULL, 33, NULL, NULL, 1, '2023-06-27 18:05:10', '2023-06-27 18:05:10', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (186, 'couponStyle3.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687860645928.png', NULL, 33, NULL, NULL, 1, '2023-06-27 18:10:44', '2023-06-27 18:10:44', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (187, 'rightPhone.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687860778546.png', NULL, 33, NULL, NULL, 1, '2023-06-27 18:12:57', '2023-06-27 18:12:57', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (188, 'rightCode.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687860778541.png', NULL, 33, NULL, NULL, 1, '2023-06-27 18:12:57', '2023-06-27 18:12:57', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (189, 'sxlogo1.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687863851728.png', NULL, 33, NULL, NULL, 1, '2023-06-27 19:04:12', '2023-06-27 19:04:12', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (190, '完善资料.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687946826844.png', NULL, 35, NULL, NULL, 1, '2023-06-28 18:07:07', '2023-06-28 18:07:07', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (191, '我的地址.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687946826845.png', NULL, 35, NULL, NULL, 1, '2023-06-28 18:07:07', '2023-06-28 18:07:07', 1, 0, 0);
INSERT INTO `kz_sys_upload` VALUES (192, '订单.png', 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1687946826832.png', NULL, 35, NULL, NULL, 1, '2023-06-28 18:07:07', '2023-06-28 18:07:07', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `kz_sys_user`;
CREATE TABLE `kz_sys_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `dept_id` int(11) NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gender` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `data_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据域类型，agent,store',
  `store_ids` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '勾选的门店列表',
  `enabled` tinyint(2) NOT NULL DEFAULT 1,
  `create_user` int(255) NULL DEFAULT NULL,
  `is_delete` tinyint(2) NULL DEFAULT NULL,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  `store_id` int(11) NULL DEFAULT NULL COMMENT '默认门店',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_sys_user
-- ----------------------------
INSERT INTO `kz_sys_user` VALUES (1, 1, NULL, 'super-admin', '超级管理员', '男', '$2a$10$i4h9iA5r8ofJFVUwL15uR.eEixo0uU9BTJttLV94PWRVQn.m5ACBy', 'dingxiaosong1987@126.com', '13675175408', '2022-04-02 17:50:26', '2023-06-28 16:38:23', NULL, 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', NULL, NULL, 1, 10, 0, 0, NULL);
INSERT INTO `kz_sys_user` VALUES (10, 2, NULL, 'admin', '管理员', '男', '$2a$10$lYqZEMlbSwSNwUK/3UO/Oe5yfiZngx7NcRzsLXekAWB7rC1UuOKGi', 'dingxiaosong1987@126.com', '13900000000', '2021-09-27 08:13:31', '2023-02-15 16:33:30', NULL, 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', NULL, NULL, 1, 8, 0, 0, NULL);

-- ----------------------------
-- Table structure for kz_user
-- ----------------------------
DROP TABLE IF EXISTS `kz_user`;
CREATE TABLE `kz_user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员码',
  `open_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `real_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `level_id` int(255) NULL DEFAULT NULL COMMENT '会员等级',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省市区',
  `address_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `invite_id` int(11) NULL DEFAULT NULL COMMENT '邀请用户id',
  `is_vip` tinyint(1) NULL DEFAULT 0 COMMENT 'VIP会员',
  `expired_time` datetime(0) NULL DEFAULT NULL COMMENT '会员到期时间',
  `balance` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '余额',
  `credit` decimal(20, 2) NULL DEFAULT 0.00 COMMENT '积分',
  `brokerage` decimal(20, 2) NULL DEFAULT NULL COMMENT '佣金',
  `exp` bigint(20) NULL DEFAULT NULL COMMENT '经验值',
  `tags` json NULL COMMENT '用户标签',
  `is_promoter` tinyint(1) NULL DEFAULT 0 COMMENT '是否分销员',
  `last_check_time` datetime(0) NULL DEFAULT NULL COMMENT '上次签到时间',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `check_times` int(11) NULL DEFAULT 0 COMMENT '连续签到天数，7天则0',
  `create_user` int(10) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT 1,
  `is_delete` tinyint(1) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  `store_id` int(11) NULL DEFAULT NULL COMMENT '默认门店',
  `log_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传日志id',
  `version` int(255) NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_phone`(`phone`) USING BTREE COMMENT '手机号唯一',
  UNIQUE INDEX `uni_uuid`(`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 872 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '普通用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_user
-- ----------------------------
INSERT INTO `kz_user` VALUES (854, NULL, NULL, '11', '2312323', '13692424124', '男', 14, NULL, NULL, 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/dance/default/student.jpg', 868, 0, NULL, 0.00, 0.00, 0.00, NULL, NULL, 0, NULL, NULL, 0, 62, '2023-02-03 18:12:37', '2023-02-03 18:12:37', 1, 0, 800009, NULL, NULL, 0);
INSERT INTO `kz_user` VALUES (855, NULL, NULL, '213', '2132321', '13685264225', '女', 14, NULL, NULL, 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/dance/default/student.jpg', NULL, 0, NULL, 0.00, 0.00, 0.00, NULL, NULL, 1, NULL, NULL, 0, 62, '2023-02-03 18:14:45', '2023-06-19 10:33:37', 1, 0, 800009, NULL, NULL, 1);
INSERT INTO `kz_user` VALUES (857, NULL, NULL, '23', '213123', '13653542424', '男', 14, NULL, NULL, 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/dance/default/student.jpg', 867, 0, NULL, 0.00, 0.00, 0.00, NULL, NULL, 1, NULL, NULL, 0, 62, '2023-02-03 18:18:44', '2023-02-03 18:19:36', 1, 0, 800009, NULL, NULL, 0);
INSERT INTO `kz_user` VALUES (867, '3074741382781009334', 'oq0id5EsPxoTUfaLm2EgloamlrK4', '买菜小哥', NULL, '13675175408', '男', 14, NULL, NULL, 'https://kinzhan-im-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/1682576832292106.png', 868, 0, NULL, 449.49, 1416.00, 500.00, 600, NULL, 1, '2023-06-08 00:00:00', '2023-06-28 11:49:16', 1, 0, '2023-04-26 18:37:36', '2023-06-28 11:49:16', 1, 0, 0, NULL, NULL, 245);
INSERT INTO `kz_user` VALUES (868, '3036053857494956330', 'oq0id5NtaqFy8pWB1ydIrC2EfUSs', 'Mercurial.', NULL, '18368836191', '男', 14, NULL, NULL, 'https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/168793428287121.png', NULL, 0, NULL, 2088.87, 1997.00, 350.00, 1500, NULL, 1, '2023-05-29 00:00:00', '2023-06-28 17:22:43', 1, 0, '2023-04-27 09:32:59', '2023-06-28 17:22:43', 1, 0, 0, NULL, NULL, 261);
INSERT INTO `kz_user` VALUES (869, '3094320545916246151', 'oq0id5AtXqu6YlIed8KfdKKZMgKY', '微信用户', NULL, '15388900913', '', NULL, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132', NULL, 0, NULL, 0.00, 10.00, NULL, NULL, NULL, 0, NULL, '2023-07-20 22:33:43', 0, 0, '2023-07-11 14:21:35', '2023-07-20 22:33:43', 1, 0, 0, NULL, NULL, NULL);
INSERT INTO `kz_user` VALUES (870, '3019007593844915839', 'oq0id5NoBXmD60ctxkX747G7pOW4', '微信用户', NULL, '15111095682', '', NULL, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132', NULL, 0, NULL, 0.00, 10.00, NULL, NULL, NULL, 0, NULL, '2023-07-11 15:07:34', 0, 0, '2023-07-11 15:04:56', '2023-07-11 15:07:34', 1, 0, 0, NULL, NULL, NULL);
INSERT INTO `kz_user` VALUES (871, '3019357900644298440', 'oq0id5FqT7_zNGtAcQy_A6VtFd_A', '微信用户', NULL, '15210296267', '', NULL, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132', NULL, 0, NULL, 0.00, 10.00, NULL, NULL, NULL, 0, NULL, '2023-07-20 11:22:51', 0, 0, '2023-07-20 11:22:46', '2023-07-20 11:22:51', 1, 0, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for kz_user_address
-- ----------------------------
DROP TABLE IF EXISTS `kz_user_address`;
CREATE TABLE `kz_user_address`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收件人',
  `postal_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` json NULL COMMENT '省市区',
  `address_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '详细地址',
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_default` tinyint(255) NULL DEFAULT 0 COMMENT '默认',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT NULL COMMENT '状态',
  `is_delete` tinyint(1) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户收货地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_user_address
-- ----------------------------
INSERT INTO `kz_user_address` VALUES (23, 867, '13675175408', '丁晓松', NULL, '[\"浙江省\", \"杭州市\", \"滨江区\"]', '铂金名筑3栋3单元1002室', NULL, 1, 867, '2023-05-09 14:37:40', '2023-05-09 14:37:40', 1, 0, 0);


-- ----------------------------
-- Table structure for kz_user_balance
-- ----------------------------
DROP TABLE IF EXISTS `kz_user_balance`;
CREATE TABLE `kz_user_balance`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `total_value` decimal(10, 2) NULL DEFAULT NULL COMMENT '总余额',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_user` int(10) NOT NULL,
  `enabled` tinyint(1) NULL DEFAULT 1,
  `is_delete` tinyint(1) NULL DEFAULT 0,
  `version` int(10) NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户账户余额表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for kz_user_bill
-- ----------------------------
DROP TABLE IF EXISTS `kz_user_bill`;
CREATE TABLE `kz_user_bill`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型,积分，余额',
  `action` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型，签到，邀请用户，购买商品',
  `amount` decimal(10, 2) NOT NULL COMMENT '金额',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总额',
  `direction` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '增加 -，或者减少 +',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账单原由，明细等等',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  `attr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用字段',
  `uid` int(10) NOT NULL COMMENT '用户id',
  `create_user` int(10) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `enabled` tinyint(1) NULL DEFAULT 1,
  `is_delete` tinyint(1) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2791 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户余额，积分记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_user_bill
-- ----------------------------
INSERT INTO `kz_user_bill` VALUES (2756, 'credit', 'SYSTEM_USER_HANDLE', 100.00, 100.00, '+', '管理员操作', '测试充值', NULL, 868, 1, '2023-04-12 16:48:20', '2023-04-12 16:48:20', 1, 0, 0);
INSERT INTO `kz_user_bill` VALUES (2757, 'balance', 'SYSTEM_USER_HANDLE', 500.00, 500.00, '0', '管理员操作', '1111', NULL, 868, 1, '2023-04-12 16:50:50', '2023-04-12 16:50:50', 1, 0, 0);
INSERT INTO `kz_user_bill` VALUES (2785, 'BALANCE', 'WITHDRAW_BROKERAGE', 10.00, 1708.87, '+', '佣金提取到余额', NULL, NULL, 868, 868, '2023-06-19 18:09:56', '2023-06-19 18:09:56', 1, 0, 0);
INSERT INTO `kz_user_bill` VALUES (2788, 'CREDIT', 'FIRST_LOGIN', 10.00, 10.00, '+', '用户注册', '用户注册赠送10积分', NULL, 869, 0, '2023-07-11 14:21:35', '2023-07-11 14:21:35', 1, 0, 0);


-- ----------------------------
-- Table structure for kz_user_brokerage_bill
-- ----------------------------
DROP TABLE IF EXISTS `kz_user_brokerage_bill`;
CREATE TABLE `kz_user_brokerage_bill`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `action` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型，签到，邀请用户，购买商品',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账单原由，明细等等',
  `amount` decimal(10, 2) NOT NULL COMMENT '金额',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总额',
  `direction` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '增加 -，或者减少 +',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  `attr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用字段',
  `uid` int(10) NOT NULL COMMENT '用户id',
  `create_user` int(10) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `enabled` tinyint(1) NULL DEFAULT 1,
  `is_delete` tinyint(1) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_action`(`action`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2778 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户佣金记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_user_brokerage_bill
-- ----------------------------
INSERT INTO `kz_user_brokerage_bill` VALUES (2775, 'WITHDRAW_BROKERAGE', '佣金提取到余额', 10.00, 350.00, '-', NULL, NULL, 868, 868, '2023-06-19 18:09:56', '2023-06-19 18:09:56', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_user_coupon
-- ----------------------------
DROP TABLE IF EXISTS `kz_user_coupon`;
CREATE TABLE `kz_user_coupon`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一识别码',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '优惠券名称',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `coupon_id` int(11) NULL DEFAULT NULL COMMENT '优惠券id',
  `coupon_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '优惠券类型，满减，折扣，类目，无门槛等',
  `obtain_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '获取方式，领取，发放',
  `shop_id` int(11) NULL DEFAULT NULL COMMENT '店铺id',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠券面值',
  `min_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '最低消费',
  `discount` decimal(10, 2) NULL DEFAULT NULL COMMENT '折扣',
  `use_start_time` datetime(0) NULL DEFAULT NULL COMMENT '可用时间',
  `use_end_time` datetime(0) NULL DEFAULT NULL COMMENT '可用结束时间',
  `use_time` datetime(0) NULL DEFAULT NULL COMMENT '使用时间',
  `status` int(10) NULL DEFAULT NULL COMMENT '使用状态',
  `status_desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '使用状态：0->未使用；1->已使用；2->已过期',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uuid`(`uuid`) USING BTREE COMMENT '唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城优惠券表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_user_coupon
-- ----------------------------
INSERT INTO `kz_user_coupon` VALUES (14, '248303977155005', '10元无门槛', 867, 1, 'ALL', 'user', NULL, 10.00, NULL, NULL, '2023-05-08 00:00:00', '2023-05-31 00:00:00', NULL, 2, '已过期', 867, '2023-05-09 10:16:59', '2023-05-09 10:16:59', 1, 0, 0);
INSERT INTO `kz_user_coupon` VALUES (15, '470142743935943', '10元无门槛', 867, 1, 'ALL', 'user', NULL, 10.00, NULL, NULL, '2023-05-08 00:00:00', '2023-05-31 00:00:00', NULL, 2, '已过期', 867, '2023-05-09 10:17:19', '2023-05-09 10:17:19', 1, 0, 0);
INSERT INTO `kz_user_coupon` VALUES (26, '881231809690351', '商品优惠券', 868, 3, 'DISCOUNT', 'user', NULL, 0.00, 50.00, 0.90, '2023-05-08 00:00:00', '2023-05-31 00:00:00', NULL, 2, '已过期', 868, '2023-05-09 11:00:31', '2023-05-09 11:00:31', 1, 0, 0);
INSERT INTO `kz_user_coupon` VALUES (27, '978942375572868', 'cs优惠券', 868, 4, 'ALL', 'user', NULL, 4.00, NULL, NULL, '2023-05-08 00:00:00', '2023-05-31 00:00:00', NULL, 1, '已使用', 868, '2023-05-09 11:00:32', '2023-05-09 11:00:32', 1, 0, 0);
INSERT INTO `kz_user_coupon` VALUES (28, '987158576738507', '10元无门槛', 868, 1, 'ALL', 'user', NULL, 10.00, NULL, NULL, '2023-05-08 00:00:00', '2023-05-31 00:00:00', NULL, 2, '已过期', 868, '2023-05-11 10:16:06', '2023-05-11 10:16:06', 1, 0, 0);
INSERT INTO `kz_user_coupon` VALUES (31, '710986838590881', '商品优惠券', 867, 3, 'DISCOUNT', 'user', NULL, 0.00, 50.00, 0.90, '2023-05-08 00:00:00', '2023-05-31 00:00:00', NULL, 2, '已过期', 867, '2023-05-24 20:01:09', '2023-05-24 20:01:09', 1, 0, 0);
INSERT INTO `kz_user_coupon` VALUES (32, '154300390173244', 'cs优惠券', 867, 4, 'ALL', 'user', NULL, 4.00, NULL, NULL, '2023-05-08 00:00:00', '2023-05-31 00:00:00', NULL, 2, '已过期', 867, '2023-05-24 20:01:10', '2023-05-24 20:01:10', 1, 0, 0);
INSERT INTO `kz_user_coupon` VALUES (33, '121805350686976', '商品优惠券', 867, 3, 'DISCOUNT', 'user', NULL, 0.00, 50.00, 0.90, '2023-05-08 00:00:00', '2023-05-31 00:00:00', NULL, 2, '已过期', 867, '2023-05-26 14:07:35', '2023-05-26 14:07:35', 1, 0, 0);
INSERT INTO `kz_user_coupon` VALUES (34, '907949692580055', '新人见面礼', 867, 5, 'ALL', 'user', NULL, 10.00, NULL, NULL, '2023-06-01 00:00:00', '2024-01-01 00:00:00', NULL, 0, '未使用', 867, '2023-06-06 14:05:23', '2023-06-06 14:05:23', 1, 0, 0);
INSERT INTO `kz_user_coupon` VALUES (77, '297518960277091', '618满减', 867, 6, 'FULL', 'system', NULL, 50.00, 300.00, NULL, '2023-06-18 00:00:00', '2023-06-18 00:00:00', NULL, 2, '已过期', 1, '2023-06-06 15:42:19', '2023-06-06 15:42:19', 1, 0, 0);
INSERT INTO `kz_user_coupon` VALUES (78, '044882086472065', '618满减', 867, 6, 'FULL', 'user', NULL, 50.00, 300.00, NULL, '2023-06-18 00:00:00', '2023-06-18 00:00:00', NULL, 2, '已过期', 867, '2023-06-09 23:25:33', '2023-06-09 23:25:33', 1, 0, 0);
INSERT INTO `kz_user_coupon` VALUES (79, '883689748068471', '全场9折', 868, 8, 'DISCOUNT', 'user', NULL, NULL, 100.00, 0.90, '2023-06-01 00:00:00', '2024-07-31 00:00:00', NULL, 0, '未使用', 868, '2023-06-28 09:47:30', '2023-06-28 09:47:30', 1, 0, 0);
INSERT INTO `kz_user_coupon` VALUES (80, '123896220293988', '618满减', 868, 7, 'FULL', 'user', NULL, 50.00, 300.00, NULL, '2023-06-17 00:00:00', '2023-07-31 00:00:00', NULL, 0, '未使用', 868, '2023-06-28 09:47:32', '2023-06-28 09:47:32', 1, 0, 0);
INSERT INTO `kz_user_coupon` VALUES (81, '977326867229918', '全场9折', 871, 8, 'DISCOUNT', 'user', NULL, NULL, 100.00, 0.90, '2023-06-01 00:00:00', '2024-07-31 00:00:00', NULL, 0, '未使用', 871, '2023-07-20 11:24:12', '2023-07-20 11:24:12', 1, 0, 0);

-- ----------------------------
-- Table structure for kz_user_level
-- ----------------------------
DROP TABLE IF EXISTS `kz_user_level`;
CREATE TABLE `kz_user_level`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '权益信息',
  `sort` int(255) NULL DEFAULT NULL COMMENT '排序',
  `is_vip` tinyint(2) NULL DEFAULT 0 COMMENT '付费会员',
  `requirements` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '升级条件',
  `discount` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '折扣',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '购买价格',
  `days` int(255) NULL DEFAULT NULL COMMENT '天数',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户等级表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_user_level
-- ----------------------------
INSERT INTO `kz_user_level` VALUES (14, '青铜', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442040.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442049.png', '[{\"icon\":\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682582997379.png\",\"description\":\"等级优惠\",\"title\":\"等级优惠\"},{\"icon\":\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681199893387.png\",\"description\":\"专属客服\",\"title\":\"专属客服\"},{\"icon\":\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442043.png\",\"description\":\"包邮福利\",\"title\":\"包邮福利\"},{\"icon\":\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442040.png\",\"description\":\"等级优惠券\",\"title\":\"等级优惠券\"}]', 1, 0, '100', '95', 0.01, NULL, '2023-04-26 09:58:18', '2023-05-10 14:42:26', 1, 1, 0, 0);
INSERT INTO `kz_user_level` VALUES (15, '白银', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442047.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442044.png', '[{\"icon\":\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352992380.png\",\"description\":\"2222222\",\"title\":\"2121\"}]', 2, 0, '1500', '90', 1.00, NULL, '2023-04-26 10:43:36', '2023-04-26 11:07:17', 1, 1, 0, 0);
INSERT INTO `kz_user_level` VALUES (16, '黄金', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442043.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442050.png', '[{\"icon\":\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681199902244.png\",\"description\":\"22\",\"title\":\"11\"}]', 3, 0, '2000', '88', NULL, NULL, '2023-04-26 10:55:17', '2023-04-26 11:07:23', 1, 1, 0, 0);
INSERT INTO `kz_user_level` VALUES (17, '铂金', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442035.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442042.png', '[{\"icon\":\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681352992372.png\",\"description\":\"222\",\"title\":\"111\"}]', 4, 0, '5000', '70', NULL, NULL, '2023-04-26 10:55:54', '2023-04-27 15:50:18', 1, 1, 0, 0);
INSERT INTO `kz_user_level` VALUES (18, '荣耀', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442046.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442052.png', '[{\"icon\":\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682582997379.png\",\"description\":\"3333\",\"title\":\"11111\"},{\"title\":\"232323\",\"description\":\"53453\",\"icon\":\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1681199893387.png\"}]', 5, 0, '100000', '50', NULL, NULL, '2023-04-26 10:56:29', '2023-04-27 16:39:45', 1, 1, 0, 0);
INSERT INTO `kz_user_level` VALUES (19, '月卡', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682479764583.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442050.png', '[{\"icon\":\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442043.png\",\"description\":\"全场包邮\",\"title\":\"全场包邮\"},{\"icon\":\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442043.png\",\"description\":\"专属客服\",\"title\":\"专属客服\"}]', 1, 1, NULL, '70', 0.01, 30, '2023-04-26 11:30:18', '2023-04-26 11:52:51', 1, 1, 0, 0);
INSERT INTO `kz_user_level` VALUES (20, '荣耀-复制', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442046.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442052.png', '[{\"description\":\"222\",\"title\":\"11\"}]', 5, 0, '100000', '50', NULL, NULL, '2023-04-26 11:43:33', '2023-04-26 11:44:44', 1, 1, 1, 0);
INSERT INTO `kz_user_level` VALUES (21, '荣耀-复制', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442046.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442052.png', '[{\"description\":\"222\",\"title\":\"11\"}]', 5, 0, '100000', '50', NULL, NULL, '2023-04-26 11:44:46', '2023-04-26 11:44:50', 1, 1, 1, 0);
INSERT INTO `kz_user_level` VALUES (22, '季卡', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682479764583.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442050.png', '[{\"icon\":\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442043.png\",\"description\":\"全场包邮\",\"title\":\"全场包邮\"},{\"icon\":\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442043.png\",\"description\":\"专属客服\",\"title\":\"专属客服\"}]', 2, 1, NULL, '70', 0.02, 90, '2023-04-26 11:45:50', '2023-04-26 11:54:36', 1, 1, 0, 0);
INSERT INTO `kz_user_level` VALUES (23, '年卡', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682479764583.png', 'http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442050.png', '[{\"icon\":\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442043.png\",\"description\":\"全场包邮\",\"title\":\"全场包邮\"},{\"icon\":\"http://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/image1682477442043.png\",\"description\":\"专属客服\",\"title\":\"专属客服\"}]', 3, 1, NULL, '50', 0.03, 365, '2023-04-26 11:45:52', '2023-04-26 11:52:43', 1, 1, 0, 0);

-- ----------------------------
-- Table structure for kz_user_tags
-- ----------------------------
DROP TABLE IF EXISTS `kz_user_tags`;
CREATE TABLE `kz_user_tags`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户标签',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权益信息',
  `sort` int(255) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(2) NULL DEFAULT 1,
  `is_delete` tinyint(2) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_user_tags
-- ----------------------------
INSERT INTO `kz_user_tags` VALUES (13, '活跃用户', NULL, NULL, '活跃用户', 1, '2023-05-26 18:02:45', '2023-05-26 18:02:45', 1, 1, 0, 0);
INSERT INTO `kz_user_tags` VALUES (14, '男性', NULL, NULL, '男性用户', 2, '2023-05-26 18:03:08', '2023-05-26 18:04:11', 1, 1, 0, 0);
INSERT INTO `kz_user_tags` VALUES (15, '宝妈', NULL, NULL, '宝妈', 3, '2023-05-26 18:03:22', '2023-05-26 18:03:22', 1, 1, 0, 0);
INSERT INTO `kz_user_tags` VALUES (16, '女性', NULL, NULL, '女性', 2, '2023-05-26 18:03:38', '2023-05-26 18:03:38', 1, 1, 0, 0);
INSERT INTO `kz_user_tags` VALUES (17, '消费达人', NULL, NULL, '消费达人', 6, '2023-05-26 18:04:05', '2023-05-26 18:04:05', 1, 1, 0, 0);

-- ----------------------------
-- Table structure for kz_withdraw
-- ----------------------------
DROP TABLE IF EXISTS `kz_withdraw`;
CREATE TABLE `kz_withdraw`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `total_price` decimal(10, 2) NOT NULL COMMENT '提现金额',
  `pay_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '打款金额',
  `qrcode_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '提现到的账户',
  `status` int(11) NULL DEFAULT NULL COMMENT '提现申请状态 -1 取消 0 已提交 1 驳回 2  已打款',
  `status_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '提现状态',
  `status_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '提现拒绝原因',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注信息',
  `order_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '打款订单id',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '打款时间',
  `order_img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付截图',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `uid` int(10) NOT NULL,
  `enabled` tinyint(1) NULL DEFAULT 1,
  `is_delete` tinyint(1) NULL DEFAULT 0,
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 887 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '提现分润记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_withdraw
-- ----------------------------
INSERT INTO `kz_withdraw` VALUES (886, 2.00, 1.98, 'https://kinzhan-im-oss.oss-cn-hangzhou.aliyuncs.com/collectionCode/1686800988546123.png', 1, '驳回', '请上传正确的收款码！', NULL, NULL, NULL, NULL, 868, '2023-06-15 11:50:16', '2023-06-15 11:50:51', 868, 1, 0, 0);

-- ----------------------------
-- Table structure for kz_wx_task
-- ----------------------------
DROP TABLE IF EXISTS `kz_wx_task`;
CREATE TABLE `kz_wx_task`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `app_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '小程序类型，个人personal，企业company',
  `app_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `app_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `task_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务id',
  `authorize_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '给用户扫码认证的验证url',
  `payload` json NULL COMMENT '请求实体',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `status_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enabled` tinyint(2) NULL DEFAULT 1 COMMENT '状态',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '应用管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kz_wx_task
-- ----------------------------
INSERT INTO `kz_wx_task` VALUES (4, 'personal', 'wxbffa21d9a2ea56c8', NULL, '9d94405ba598e21a', 'https://mp.weixin.qq.com/publicpoc/fastregisterpersonalwxa?action=scan&tid=9d94405ba598e21a#wechat_redirect', '{\"idname\": \"丁晓松\", \"wxuser\": \"songsirvt\", \"componentPhone\": \"13675175408\"}', NULL, NULL, 1, '2023-05-24 16:33:13', '2023-05-24 16:33:13', 1, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
