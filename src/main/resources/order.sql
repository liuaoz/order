-- 商品信息表
CREATE TABLE
  product
(
  id VARCHAR(32) NOT NULL,
  name VARCHAR(64) COMMENT '商品名称',
  price DECIMAL(10,2) DEFAULT '0.00' NOT NULL COMMENT '价格',
  stock INT NOT NULL COMMENT '库存数量',
  remark VARCHAR(256) COMMENT '商品描述',
  icon VARCHAR(512) COMMENT '图标',
  category_code INT NOT NULL COMMENT '类目编号',
  status TINYINT NOT NULL DEFAULT 0 COMMENT '商品状态:0正常;1下架',
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON
  UPDATE
  CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
)
  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息';

-- 商品类目表
CREATE TABLE
  product_category
(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(64) COMMENT '类目名称',
  code INT NOT NULL COMMENT '类目编号',
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON
  UPDATE
  CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id),
  CONSTRAINT uidx_product_category_code UNIQUE (code)
)
  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类目';

-- 订单表
CREATE TABLE
  order_master
(
  id VARCHAR(32) NOT NULL,
  buyer_name VARCHAR(32) COMMENT '买家名字',
  buyer_phone VARCHAR(12) COMMENT '买家手机',
  buyer_addr VARCHAR(128) COMMENT '买家地址',
  buyer_openid VARCHAR(64) COMMENT '买家微信openid',
  amount DECIMAL(10,2) DEFAULT '0.00' NOT NULL COMMENT '订单金额',
  order_status TINYINT DEFAULT '0' NOT NULL COMMENT '订单状态，0新下单',
  pay_status TINYINT DEFAULT '0' NOT NULL COMMENT '支付状态，0未支付',
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON
  UPDATE
  CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
)
  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- 订单明细表
CREATE TABLE
  order_detail
(
  id VARCHAR(32) NOT NULL,
  order_id VARCHAR(32) NOT NULL COMMENT '订单id',
  product_id VARCHAR(32) NOT NULL COMMENT '商品id',
  product_name VARCHAR(64) COMMENT '商品名称',
  product_price DECIMAL(10,2) DEFAULT '0.00' NOT NULL COMMENT '商品价格',
  product_quantity INT DEFAULT '1' NOT NULL COMMENT '商品数量',
  product_icon VARCHAR(512) COMMENT '商品图标',
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON
  UPDATE
  CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
)
  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情表';

-- 卖家信息
CREATE TABLE
  seller_info
(
  id bigint NOT NULL AUTO_INCREMENT,
  code VARCHAR(12) NOT NULL COMMENT '卖家编号',
  name VARCHAR(64) NOT NULL COMMENT '卖家名称',
  phone VARCHAR(18) COMMENT '手机号',
  addr VARCHAR(256) COMMENT '卖家地址',
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON
  UPDATE
  CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
)
  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卖家信息';


