CREATE
    DATABASE ProductManagement;
USE
    ProductManagement;

CREATE TABLE Product
(
    Product_Id      INT AUTO_INCREMENT PRIMARY KEY,                  -- Mã sản phẩm
    Product_Name    VARCHAR(100) NOT NULL UNIQUE,                    -- Tên sản phẩm
    Product_Price   FLOAT        NOT NULL CHECK (Product_Price > 0), -- Giá sản phẩm
    Product_Title   VARCHAR(200) NOT NULL,                           -- Tiêu đề sản phẩm
    Product_Created DATE         NOT NULL,                           -- Ngày tạo
    Product_Catalog VARCHAR(100) NOT NULL,                           -- Danh mục
    Product_Status  BIT DEFAULT 1                                    -- Trạng thái (1: còn bán, 0: ngừng bán)
);


# Thêm sản phẩm

DELIMITER //
CREATE PROCEDURE add_product(
    IN in_name VARCHAR(100),
    IN in_price FLOAT,
    IN in_title VARCHAR(200),
    IN in_created DATE,
    IN in_catalog VARCHAR(100),
    IN in_status BIT
)
BEGIN
    INSERT INTO Product (Product_Name, Product_Price, Product_Title, Product_Created, Product_Catalog, Product_Status)
    VALUES (in_name, in_price, in_title, in_created, in_catalog, in_status);
END //
DELIMITER ;

# Lấy danh sách sản phẩm

DELIMITER //
CREATE PROCEDURE get_all_products()
BEGIN
    SELECT * FROM Product;
END //
DELIMITER ;


# Cập nhật sản phẩm

DELIMITER //
CREATE PROCEDURE update_product(
    IN in_id INT,
    IN in_name VARCHAR(100),
    IN in_price FLOAT,
    IN in_title VARCHAR(200),
    IN in_created DATE,
    IN in_catalog VARCHAR(100),
    IN in_status BIT
)
BEGIN
    UPDATE Product
    SET Product_Name    = in_name,
        Product_Price   = in_price,
        Product_Title   = in_title,
        Product_Created = in_created,
        Product_Catalog = in_catalog,
        Product_Status  = in_status
    WHERE Product_Id = in_id;
END //
DELIMITER ;


# Xóa sản phẩm

DELIMITER //
CREATE PROCEDURE delete_product(
    IN in_id INT
)
BEGIN
    DELETE FROM Product WHERE Product_Id = in_id;
END //
DELIMITER ;