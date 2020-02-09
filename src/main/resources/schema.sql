DROP TABLE IF EXISTS PRODUCT_DETAILS;
CREATE TABLE PRODUCT_DETAILS(
	PRODUCT_ID INT(10) NOT NULL PRIMARY KEY,
	PRODUCT_NAME VARCHAR(50) NOT NULL,
	PRODUCT_DESC VARCHAR(200) NOT NULL,
	PRODUCT_PRICE DECIMAL(10,2) NOT NULL,
	PRODUCT_SIZE VARCHAR(50) NOT NULL,
	BRAND_ID INT(10) NOT NULL,
	CATEGORY_ID INT(10) NOT NULL,
	COLOUR_ID INT(10) NOT NULL
);

DROP TABLE IF EXISTS BRAND_DETAILS;
CREATE TABLE BRAND_DETAILS(
	BRAND_ID INT(10) NOT NULL PRIMARY KEY,
	BRAND_NAME VARCHAR(50) NOT NULL,
	BRAND_DESC VARCHAR(200) NOT NULL
);

DROP TABLE IF EXISTS CATEGORY_DETAILS;
CREATE TABLE CATEGORY_DETAILS(
	CATEGORY_ID INT(10) NOT NULL PRIMARY KEY,
	CATEGORY_NAME VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS COLOUR_DETAILS;
CREATE TABLE COLOUR_DETAILS(
	COLOUR_ID INT(10) NOT NULL PRIMARY KEY,
	COLOUR_NAME VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS AVAILABLE_PRODUCT;
CREATE TABLE AVAILABLE_PRODUCT(
	PRODUCT_ID INT(10) NOT NULL PRIMARY KEY,
	PRODUCT_COUNT INT(10) NOT NULL
);