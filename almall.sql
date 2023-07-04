/*
 Navicat Premium Data Transfer

 Source Server         : fcl
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : almall

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 04/07/2023 16:34:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mall_admin
-- ----------------------------
DROP TABLE IF EXISTS `mall_admin`;
CREATE TABLE `mall_admin`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_card` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `role` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '角色0超级管理员1店家',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_admin
-- ----------------------------
INSERT INTO `mall_admin` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '13745682741', '', NULL, '2023-02-19 15:39:43', '2023-03-02 09:27:02', 0);
INSERT INTO `mall_admin` VALUES (2, 'alotest', 'e10adc3949ba59abbe56e057f20f883e', 'shopOwner', '18754147854', NULL, NULL, '2023-03-02 09:27:12', '2023-03-02 09:27:40', 1);

-- ----------------------------
-- Table structure for mall_cart
-- ----------------------------
DROP TABLE IF EXISTS `mall_cart`;
CREATE TABLE `mall_cart`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `store_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `user_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `goods_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `goods_count` int(11) UNSIGNED NULL DEFAULT NULL,
  `goods_price` decimal(10, 2) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_cart
-- ----------------------------

-- ----------------------------
-- Table structure for mall_category
-- ----------------------------
DROP TABLE IF EXISTS `mall_category`;
CREATE TABLE `mall_category`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `category_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `category_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `store_id` int(11) UNSIGNED NULL DEFAULT 0,
  `flag` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '分类标志0默认分类及店家不能删除1为每个店铺自定义的分类最多三个',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_category
-- ----------------------------
INSERT INTO `mall_category` VALUES (1, '西点蛋糕', '1678188274061.png', 0, 0);
INSERT INTO `mall_category` VALUES (2, '酸奶', '1678188345369.png', 0, 0);
INSERT INTO `mall_category` VALUES (5, '休闲零食', '1678255883135.png', 0, 0);
INSERT INTO `mall_category` VALUES (6, '元气饮品', '1678256056959.png', 0, 0);

-- ----------------------------
-- Table structure for mall_goods
-- ----------------------------
DROP TABLE IF EXISTS `mall_goods`;
CREATE TABLE `mall_goods`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `category_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `store_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `goods_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stock` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '库存',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品描述',
  `flag` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '标志0为系统默认商品及每个店铺必须销售1为每个店铺可推出自定义商品',
  `goods_status` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '上架标志0下架1上架',
  `default_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '默认商品表的编号',
  `goods_price` decimal(10, 2) UNSIGNED NULL DEFAULT NULL,
  `goods_points` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_goods
-- ----------------------------
INSERT INTO `mall_goods` VALUES (5, '牛角面包', 1, 1, '1678254875348.jpg', 5, '政府哈萨克的hawk', 0, 1, 3, 10.00, 3);
INSERT INTO `mall_goods` VALUES (6, '牛角面包', 1, 2, '1678254875348.jpg', 20, '政府哈萨克的hawk', 0, 1, 3, 10.00, 2);
INSERT INTO `mall_goods` VALUES (7, '全麦面包', 1, 1, '1678255442049.jpg', 3, '国际私法的骄傲劳动法啊', 0, 1, 4, 10.00, 2);
INSERT INTO `mall_goods` VALUES (8, '全麦面包', 1, 2, '1678255442049.jpg', 5, '国际私法的骄傲劳动法啊', 0, 1, 4, 10.00, 2);
INSERT INTO `mall_goods` VALUES (9, '招牌酸奶', 2, 1, '1678255553224.jpg', 0, '防守反击奥利弗的那位的', 0, 1, 5, 10.00, 2);
INSERT INTO `mall_goods` VALUES (10, '招牌酸奶', 2, 2, '1678255553224.jpg', 7, '防守反击奥利弗的那位的', 0, 1, 5, 10.00, 2);
INSERT INTO `mall_goods` VALUES (11, '巧克力珍宝蛋糕', 5, 1, '1678256405169.png', 3, '翻身的机会当符合噶空间等哈我', 0, 1, 6, 10.00, 2);
INSERT INTO `mall_goods` VALUES (12, '巧克力珍宝蛋糕', 5, 2, '1678256405169.png', 5, '翻身的机会当符合噶空间等哈我', 0, 1, 6, 10.00, 2);
INSERT INTO `mall_goods` VALUES (13, '蔓越莓饼干', 5, 1, '1678256588103.png', 5, '发的哈肯定会发生', 0, 1, 7, 10.00, 2);
INSERT INTO `mall_goods` VALUES (14, '蔓越莓饼干', 5, 2, '1678256588103.png', 7, '发的哈肯定会发生', 0, 1, 7, 10.00, 2);
INSERT INTO `mall_goods` VALUES (15, '酒心黑巧冷萃咖啡', 6, 1, '1678256627496.png', 4, '及时反馈是否苦涩分别是看过', 0, 1, 8, 10.00, 2);
INSERT INTO `mall_goods` VALUES (16, '酒心黑巧冷萃咖啡', 6, 2, '1678256627496.png', 3, '及时反馈是否苦涩分别是看过', 0, 1, 8, 10.00, 2);
INSERT INTO `mall_goods` VALUES (17, '鲜奶（热）', 6, 1, '1678256646143.png', 6, '分时间段佛哈看到', 0, 1, 9, 10.00, 2);
INSERT INTO `mall_goods` VALUES (18, '鲜奶（热）', 6, 2, '1678256646143.png', 5, '分时间段佛哈看到', 0, 1, 9, 10.00, 2);
INSERT INTO `mall_goods` VALUES (21, '鲜奶（冷）', 6, 1, '1678357913524.png', 0, '疯狂角色的哈', 0, 1, 11, 12.00, 3);
INSERT INTO `mall_goods` VALUES (22, '鲜奶（冷）', 6, 2, '1678357913524.png', 0, '疯狂角色的哈', 0, 1, 11, 12.00, 3);
INSERT INTO `mall_goods` VALUES (23, '蛋黄酥', 1, 2, 'ddwas.jpg', 20, '非常好吃', 1, 1, NULL, 15.00, 5);
INSERT INTO `mall_goods` VALUES (24, '蛋黄酥', 5, 1, '1678883884558.jpg', 2, '蛋黄酥好好吃', 1, 1, NULL, 15.00, 3);
INSERT INTO `mall_goods` VALUES (25, '牛角面包', 1, 3, '1678254875348.jpg', 0, '政府哈萨克的hawk', 0, 1, 3, 10.00, 2);
INSERT INTO `mall_goods` VALUES (26, '全麦面包', 1, 3, '1678255442049.jpg', 0, '国际私法的骄傲劳动法啊', 0, 1, 4, 10.00, 2);
INSERT INTO `mall_goods` VALUES (27, '招牌酸奶', 2, 3, '1678255553224.jpg', 0, '防守反击奥利弗的那位的', 0, 1, 5, 10.00, 2);
INSERT INTO `mall_goods` VALUES (28, '巧克力珍宝蛋糕', 5, 3, '1678256405169.png', 0, '翻身的机会当符合噶空间等哈我', 0, 1, 6, 10.00, 2);
INSERT INTO `mall_goods` VALUES (29, '蔓越莓饼干', 5, 3, '1678256588103.png', 0, '发的哈肯定会发生', 0, 1, 7, 10.00, 2);
INSERT INTO `mall_goods` VALUES (30, '酒心黑巧冷萃咖啡', 6, 3, '1678256627496.png', 0, '及时反馈是否苦涩分别是看过', 0, 1, 8, 10.00, 2);
INSERT INTO `mall_goods` VALUES (31, '鲜奶（热）', 6, 3, '1678256646143.png', 0, '分时间段佛哈看到', 0, 1, 9, 10.00, 2);
INSERT INTO `mall_goods` VALUES (32, '鲜奶（冷）', 6, 3, '1678357913524.png', 0, '疯狂角色的哈', 0, 1, 11, 12.00, 3);

-- ----------------------------
-- Table structure for mall_goods_default
-- ----------------------------
DROP TABLE IF EXISTS `mall_goods_default`;
CREATE TABLE `mall_goods_default`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `category_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `goods_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goods_status` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '0下架1上架默认下架',
  `goods_price` decimal(10, 2) UNSIGNED NULL DEFAULT NULL,
  `goods_points` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_goods_default
-- ----------------------------
INSERT INTO `mall_goods_default` VALUES (3, '牛角面包', 1, '1678254875348.jpg', '政府哈萨克的hawk', 1, 10.00, 2);
INSERT INTO `mall_goods_default` VALUES (4, '全麦面包', 1, '1678255442049.jpg', '国际私法的骄傲劳动法啊', 1, 10.00, 2);
INSERT INTO `mall_goods_default` VALUES (5, '招牌酸奶', 2, '1678255553224.jpg', '防守反击奥利弗的那位的', 1, 10.00, 2);
INSERT INTO `mall_goods_default` VALUES (6, '巧克力珍宝蛋糕', 5, '1678256405169.png', '翻身的机会当符合噶空间等哈我', 1, 10.00, 2);
INSERT INTO `mall_goods_default` VALUES (7, '蔓越莓饼干', 5, '1678256588103.png', '发的哈肯定会发生', 1, 10.00, 2);
INSERT INTO `mall_goods_default` VALUES (8, '酒心黑巧冷萃咖啡', 6, '1678256627496.png', '及时反馈是否苦涩分别是看过', 1, 10.00, 2);
INSERT INTO `mall_goods_default` VALUES (9, '鲜奶（热）', 6, '1678256646143.png', '分时间段佛哈看到', 1, 10.00, 2);
INSERT INTO `mall_goods_default` VALUES (11, '鲜奶（冷）', 6, '1678357913524.png', '疯狂角色的哈', 1, 12.00, 3);

-- ----------------------------
-- Table structure for mall_order
-- ----------------------------
DROP TABLE IF EXISTS `mall_order`;
CREATE TABLE `mall_order`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `store_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `total_price` decimal(10, 2) UNSIGNED NULL DEFAULT NULL,
  `user_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `order_status` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '订单状态',
  `points` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '订单积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_order
-- ----------------------------
INSERT INTO `mall_order` VALUES (1, 1, 20.00, 1, '2023-03-11 22:09:30', 1, 2);
INSERT INTO `mall_order` VALUES (2, 1, 20.00, 1, '2023-03-11 22:28:03', 1, 2);
INSERT INTO `mall_order` VALUES (3, 1, 50.00, 1, '2023-03-11 22:50:20', 1, 10);
INSERT INTO `mall_order` VALUES (4, 1, 30.00, 1, '2023-05-02 09:55:12', 0, 7);

-- ----------------------------
-- Table structure for mall_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `mall_order_detail`;
CREATE TABLE `mall_order_detail`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `goods_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `goods_count` int(11) UNSIGNED NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_order_detail
-- ----------------------------
INSERT INTO `mall_order_detail` VALUES (1, 1, 9, 1, '2023-03-11 22:09:30');
INSERT INTO `mall_order_detail` VALUES (2, 1, 13, 1, '2023-03-11 22:09:30');
INSERT INTO `mall_order_detail` VALUES (3, 2, 7, 1, '2023-03-11 22:28:03');
INSERT INTO `mall_order_detail` VALUES (4, 2, 9, 1, '2023-03-11 22:28:03');
INSERT INTO `mall_order_detail` VALUES (5, 3, 9, 5, '2023-03-11 22:50:20');
INSERT INTO `mall_order_detail` VALUES (6, 4, 5, 1, '2023-05-02 09:55:12');
INSERT INTO `mall_order_detail` VALUES (7, 4, 11, 1, '2023-05-02 09:55:12');
INSERT INTO `mall_order_detail` VALUES (8, 4, 13, 1, '2023-05-02 09:55:12');

-- ----------------------------
-- Table structure for mall_store
-- ----------------------------
DROP TABLE IF EXISTS `mall_store`;
CREATE TABLE `mall_store`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `store_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '门店名字',
  `store_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '门店地址',
  `opening_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '营业时间',
  `longitude` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '经度',
  `latitude` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '纬度',
  `status` tinyint(3) UNSIGNED NULL DEFAULT 1 COMMENT '状态0营业中1歇业中默认歇业',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '门店电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_store
-- ----------------------------
INSERT INTO `mall_store` VALUES (1, 'al甜品店店', '浙江工业大学莫干山校区-德馨府食堂', '08:00-22:00', '120.046781', '30.55868', 0, '021-451512325');
INSERT INTO `mall_store` VALUES (2, '横河头店', '横河头3号', '09:00-23:00', '120.116283', '30.480752', 0, '18754521478');
INSERT INTO `mall_store` VALUES (3, '测试门店', '杭州市余杭区就暗杀', '09:00-23:00', '120', '30', 0, '021-5154548122');

-- ----------------------------
-- Table structure for mall_store_owner
-- ----------------------------
DROP TABLE IF EXISTS `mall_store_owner`;
CREATE TABLE `mall_store_owner`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_card` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_store_owner
-- ----------------------------
INSERT INTO `mall_store_owner` VALUES (1, 'alotest', 'e10adc3949ba59abbe56e057f20f883e', '测试账号', '1874514541', '330521199904063015', '2023-03-02 12:36:53', '2023-03-02 14:41:54');
INSERT INTO `mall_store_owner` VALUES (2, 'aldtest', 'e10adc3949ba59abbe56e057f20f883e', '测试账号更新', '1874514875', '330521199904063015', '2023-03-02 13:35:36', '2023-03-02 13:37:50');
INSERT INTO `mall_store_owner` VALUES (3, '达瓦达瓦', 'e10adc3949ba59abbe56e057f20f883e', '大大', '18768215189', '330521200108023012', '2023-05-02 09:44:22', '2023-05-02 09:48:46');

-- ----------------------------
-- Table structure for mall_store_owner_relation
-- ----------------------------
DROP TABLE IF EXISTS `mall_store_owner_relation`;
CREATE TABLE `mall_store_owner_relation`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `store_id` int(10) UNSIGNED NULL DEFAULT NULL,
  `owner_id` int(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_store_owner_relation
-- ----------------------------
INSERT INTO `mall_store_owner_relation` VALUES (3, 2, 2);
INSERT INTO `mall_store_owner_relation` VALUES (4, 1, 1);
INSERT INTO `mall_store_owner_relation` VALUES (5, 3, 3);

-- ----------------------------
-- Table structure for mall_user
-- ----------------------------
DROP TABLE IF EXISTS `mall_user`;
CREATE TABLE `mall_user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `points` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '积分',
  `sex` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '0男1女',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_user
-- ----------------------------
INSERT INTO `mall_user` VALUES (1, 'altest', 'e10adc3949ba59abbe56e057f20f883e', '13758475715', 'salt_fish.gif', '安澜', 10, 0);
INSERT INTO `mall_user` VALUES (3, 'useraddtest', 'e10adc3949ba59abbe56e057f20f883e', '13874551447', 'default_avatar.jpg', '用户修改测试', 0, 1);
INSERT INTO `mall_user` VALUES (5, 'testaccount', 'e10adc3949ba59abbe56e057f20f883e', '18768215189', 'default_avatar.jpg', '发发方', 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
