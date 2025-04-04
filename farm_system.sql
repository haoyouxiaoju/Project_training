/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80021 (8.0.21)
 Source Host           : localhost:3306
 Source Schema         : farm_system

 Target Server Type    : MySQL
 Target Server Version : 80021 (8.0.21)
 File Encoding         : 65001

 Date: 04/04/2025 20:32:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (2, 'admin', 'admin', 'admin', 'http://localhost:9090/files/download/1742482024617-avatar.png', 'ADMIN');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '农产品分类' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (8, '普洱');
INSERT INTO `category` VALUES (10, '红茶');
INSERT INTO `category` VALUES (11, '绿茶');
INSERT INTO `category` VALUES (12, '白茶');
INSERT INTO `category` VALUES (13, '乌龙茶');
INSERT INTO `category` VALUES (14, '黑茶');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片',
  `descr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '简介',
  `specials` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '特色',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '单位',
  `store` int NULL DEFAULT NULL COMMENT '库存量',
  `category_id` int NULL DEFAULT NULL COMMENT '分类',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '农产品信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (7, '匠界云雾绿茶明前嫩芽2024新茶茶叶明前高山云雾茶浓香型耐泡罐装100g', 'http://localhost:9090/files/download/1742818971021-微信图片_20250324202142_41.jpg', '匠界云雾绿茶明前嫩芽2024新茶茶叶明前高山云雾茶浓香型耐泡罐装100g', '明前高山云雾茶浓香型耐泡罐装', 10.00, '100g', 100, 11);
INSERT INTO `goods` VALUES (8, '匠界茶叶正山红茶小种正宗武夷山原产蜜香浓郁盒装100g', 'http://localhost:9090/files/download/1742819047721-微信图片_20250324202149_43.jpg', '匠界茶叶正山红茶小种正宗武夷山原产蜜香浓郁盒装100g', '正宗武夷山原产蜜香浓郁盒装', 11.00, '100g', 100, 10);
INSERT INTO `goods` VALUES (9, '八马茶业乌龙茶 爱上茶武夷岩茶大红袍一级160g 礼盒装 茶叶送礼', 'http://localhost:9090/files/download/1742819028540-微信图片_20250324202153_45.jpg', '八马茶业乌龙茶 爱上茶武夷岩茶大红袍一级160g 礼盒装 茶叶送礼', '茶武夷岩茶大红袍一级礼盒装 ', 80.00, '160g', 100, 10);
INSERT INTO `goods` VALUES (10, '八窨飘雪 毛尖绿茶茶叶 新茶春茶 四川雨前一级高山云雾浓香型自己喝125克', 'http://localhost:9090/files/download/1742819058912-微信图片_20250324202156_47.jpg', '八窨飘雪 毛尖绿茶茶叶 新茶春茶 四川雨前一级高山云雾浓香型自己喝125克', '四川雨前一级高山云雾浓香型', 20.00, '125g', 100, 11);
INSERT INTO `goods` VALUES (11, '中谷御品茶叶乌龙茶 冻顶乌龙茶 特级新茶浓香型台式高山茶叶礼盒250g', 'http://localhost:9090/files/download/1742819095655-微信图片_20250324202158_49.jpg', '中谷御品茶叶乌龙茶 冻顶乌龙茶 特级新茶浓香型台式高山茶叶礼盒250g', '御品茶叶乌龙茶 冻顶乌龙茶 特级新茶浓香型', 40.00, '250g', 100, 13);
INSERT INTO `goods` VALUES (12, '福茗源绿茶 安吉白叶一号白茶叶250g(125g*2罐)明前新茶叶礼盒珍稀白茶', 'http://localhost:9090/files/download/1742819119451-微信图片_20250324202200_51.jpg', '福茗源绿茶 安吉白叶一号白茶叶250g(125g*2罐)明前新茶叶礼盒珍稀白茶', '明前新茶叶礼盒珍稀白茶', 30.00, '250g', 100, 12);
INSERT INTO `goods` VALUES (13, '福茗源白茶 云南月光白毫银针花果香70g散茶罐装茶叶古树自己喝', 'http://localhost:9090/files/download/1742819136124-微信图片_20250324202203_53.jpg', '福茗源白茶 云南月光白毫银针花果香70g散茶罐装茶叶古树自己喝', '散茶罐装茶叶古树 云南月光白毫银针花果香', 20.00, '70g', 97, 12);
INSERT INTO `goods` VALUES (14, '七彩雲南茶叶 普洱茶熟茶 2018年勐海陈料 高端2011年古树茶皇357g礼盒', 'http://localhost:9090/files/download/1742820073614-微信图片_20250324204051_55.jpg', '七彩雲南茶叶 普洱茶熟茶 2018年勐海陈料 福饼357g ', '2018年勐海陈料 2011年古树茶皇', 5888.00, '357g', 100, 8);
INSERT INTO `goods` VALUES (15, '正山堂金骏眉红茶牡丹礼盒  武夷山桐木关特级茶叶送礼礼品300g', 'http://localhost:9090/files/download/1742820168147-微信图片_20250324204058_57.jpg', '正山堂金骏眉红茶牡丹礼盒  武夷山桐木关特级茶叶送礼礼品300g', '武夷山桐木关特级茶叶', 5788.00, '300g', 100, 10);
INSERT INTO `goods` VALUES (16, '贡牌【2025新茶上市】真西湖龙井茶天赐珍品级250g明前纸包源头直发', 'http://localhost:9090/files/download/1742820207481-微信图片_20250324204101_59.jpg', '贡牌【2025新茶上市】真西湖龙井茶天赐珍品级250g明前纸包源头直发', '真西湖龙井茶天赐珍品级250g明前纸包', 4888.00, '250g', 100, 11);
INSERT INTO `goods` VALUES (17, '曦瓜（xigua）曼陀峰五星大红袍茶叶特级武夷山正岩大红袍茶茶叶礼盒装256g', 'http://localhost:9090/files/download/1742820240458-微信图片_20250324204103_61.jpg', '曦瓜（xigua）曼陀峰五星大红袍茶叶特级武夷山正岩大红袍茶茶叶礼盒装256g', '曼陀峰五星大红袍茶叶特级武夷山正岩大红袍茶茶叶', 5688.00, '256g', 100, 10);
INSERT INTO `goods` VALUES (18, '碧螺2025新茶绿茶春露苏州洞庭碧螺春特级一等200g明前头采送人礼盒装', 'http://localhost:9090/files/download/1742820298998-微信图片_20250324204106_63.jpg', '碧螺2025新茶绿茶春露苏州洞庭碧螺春特级一等200g明前头采送人礼盒装', '碧螺新茶绿茶春露苏州洞庭碧螺春特级一等', 4488.00, '200g', 100, 11);
INSERT INTO `goods` VALUES (19, '七春  安化黑茶湖南金花茯茶熟茶陈年黑茶散口粮茶叶袋装250g节日自喝', 'http://localhost:9090/files/download/1742820477730-微信图片_20250324204753_65.jpg', '七春  安化黑茶湖南金花茯茶熟茶陈年黑茶', '湖南金花茯茶熟茶陈年黑茶散口粮茶叶袋装', 45.00, '250g', 100, 14);
INSERT INTO `goods` VALUES (20, '泾渭茯茶黑茶 过年春节礼品 咸阳茯茶 陕西特产 金花茯砖茶 贡金茯茶1kg', 'http://localhost:9090/files/download/1742820512188-微信图片_20250324204757_67.jpg', '泾渭茯茶黑茶 过年春节礼品 咸阳茯茶 陕西特产 金花茯砖茶 贡金茯茶1kg', '泾渭茯茶黑茶 咸阳茯茶 陕西特产 金花茯砖茶', 200.00, '1kg', 100, 14);
INSERT INTO `goods` VALUES (21, '今大福茶业班章五星荣耀青饼 42片/件', 'http://localhost:9090/files/download/1742820630112-微信图片_20250324205013_69.jpg', '今大福茶业班章五星荣耀青饼 42片/件', '今大福茶业班章五星荣耀青饼 42片/件', 15888.00, '357g', 98, 8);

-- ----------------------------
-- Table structure for goods_stock
-- ----------------------------
DROP TABLE IF EXISTS `goods_stock`;
CREATE TABLE `goods_stock`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `goods_id` int NULL DEFAULT NULL COMMENT '商品ID',
  `num` int NULL DEFAULT NULL COMMENT '进货数量',
  `channel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '进货渠道',
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '进货日期',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '农产品进货' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods_stock
-- ----------------------------
INSERT INTO `goods_stock` VALUES (1, 2, 100, '农产品批发市场', '2024-07-05', '1');
INSERT INTO `goods_stock` VALUES (2, 1, 100, '农产品批发市场', '2024-07-05', NULL);
INSERT INTO `goods_stock` VALUES (3, 2, 100, '农产品批发市场', '2024-07-05', NULL);
INSERT INTO `goods_stock` VALUES (4, 1, 200, '	 农产品批发市场', '2024-07-05', NULL);
INSERT INTO `goods_stock` VALUES (5, 6, 3, '12312', '2025-03-24', '3123');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统公告' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '好物上新！新鲜黄桃低至5元起！', '好物上新！新鲜黄桃低至5元起！甄选黄桃，口味绝佳，夏日必备！', '2024-07-01 15:00:00');
INSERT INTO `notice` VALUES (2, '夏天最适合吃什么？当然是西瓜更配！', '新鲜无籽麒麟西瓜新鲜上市！农家自种，现摘现发，无添加更健康！', '2024-07-02 15:00:00');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单编号',
  `goods_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品ID',
  `num` int NULL DEFAULT NULL COMMENT '购买数量',
  `user_id` int NULL DEFAULT NULL COMMENT '下单人',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单状态',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (16, 'e3b64a5942874951bee9423d111aad80', '13', 1, 1, '待支付', '2025-03-24 20:30:37');
INSERT INTO `orders` VALUES (17, '7e4a0e0592874fbdb261fa1c7e3c5b13', '13', 2, 1, '待发货', '2025-03-24 20:37:07');
INSERT INTO `orders` VALUES (19, 'd5b5d706a68b462aa98e417ac9917d10', '21', 1, 1, '待发货', '2025-04-01 11:05:39');

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart`  (
  `id` int NOT NULL COMMENT '编号',
  `userId` int NOT NULL COMMENT '用户id',
  `productId` int NOT NULL COMMENT '商品id',
  `number` int NOT NULL COMMENT '购买数量',
  `price` float NOT NULL COMMENT '单价',
  `status` tinyint(1) NOT NULL COMMENT 'true--被选中 false--为选择',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '普通用户信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'aaa', '123', '青哥哥', 'http://localhost:9090/files/download/1742482024617-avatar.png', 'USER', '男', '13988997788', 'aaa@163.com');

SET FOREIGN_KEY_CHECKS = 1;
