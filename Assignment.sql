CREATE DATABASE PizzaStore

USE PizzaStore

CREATE TABLE Account (
    AccountID VARCHAR(20) PRIMARY KEY,
    UserName VARCHAR(20) NOT NULL,
    Password VARCHAR(50) NOT NULL,
	FullName VARCHAR(50) NOT NULL,
    Type INT NOT NULL
);


CREATE TABLE Suppliers (
    SupplierID VARCHAR(20) PRIMARY KEY,
    CompanyName VARCHAR(20) NOT NULL,
    Address VARCHAR(100) NOT NULL,
	Phone VARCHAR(20) NOT NULL,
);


CREATE TABLE Categories (
    CategoryID VARCHAR(20) PRIMARY KEY,
    CategoryName VARCHAR(20) NOT NULL,
    Description VARCHAR(200) NOT NULL,	
);

CREATE TABLE Customers (
    CustomerID VARCHAR(20) PRIMARY KEY,
    Password VARCHAR(50) NOT NULL,
	ContactName VARCHAR(20) NOT NULL,
	Address VARCHAR(100) NOT NULL,
	Phone VARCHAR(20) NOT NULL,
);

CREATE TABLE Orders (
    OrderID VARCHAR(20) PRIMARY KEY,
	CustomerID VARCHAR(20) NOT NULL,
    OrderDate VARCHAR(20) NOT NULL,
	RequiredDate VARCHAR(20) NOT NULL,
	ShippedDate VARCHAR(20) NOT NULL,
	Freight DECIMAL(10, 2),
    ShipAddress VARCHAR(200) NOT NULL,
	CONSTRAINT FR_CustomerID FOREIGN KEY(CustomerID) REFERENCES Customers(CustomerID),	
);


CREATE TABLE Products (
    ProductID VARCHAR(20) PRIMARY KEY,
	ProductName VARCHAR(20) NOT NULL,
	Description VARCHAR(200) NOT NULL,
    SupplierID VARCHAR(20) NOT NULL,
	CategoryID VARCHAR(20) NOT NULL,
	QuantityPerUnit INT NOT NULL,
	UnitPrice FLOAT,
    ProductImage VARCHAR(200) NOT NULL,
	CONSTRAINT FR_SupplierID FOREIGN KEY(SupplierID) REFERENCES Suppliers(SupplierID),	
	CONSTRAINT FR_CategoryID FOREIGN KEY(CategoryID) REFERENCES Categories(CategoryID),
);

CREATE TABLE OrderDetails (
    OrderID VARCHAR(20),
    ProductID VARCHAR(20),
	UnitPrice FLOAT,
    Quantity INT,
    PRIMARY KEY (OrderID, ProductID),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Thêm người dùng bình thường
INSERT INTO Account (AccountID, UserName, Password, FullName, Type)
VALUES ('admin001', 'admin', 'admin', 'Admin User', 1);


INSERT INTO Account (AccountID, UserName, Password, FullName, Type)
VALUES ('user001', 'user', 'user', 'Normal User', 0);





-- Thêm dữ liệu cho bảng Categories
INSERT INTO Categories (CategoryID, CategoryName, Description)
VALUES ('C001', 'Pizza', 'Italian-style pizza');

INSERT INTO Categories (CategoryID, CategoryName, Description)
VALUES ('C002', 'Vegetarian', 'Pizza without meat');

INSERT INTO Categories (CategoryID, CategoryName, Description)
VALUES ('C003', 'Specialty', 'Unique and innovative pizzas');
DELETE FROM Categories
DELETE FROM Products
-- Thêm dữ liệu cho bảng Suppliers
INSERT INTO Suppliers (SupplierID, CompanyName, Address, Phone)
VALUES ('S001', 'Pizza Supplier 1', '123 Main Street, City A', '123-456-7890');

INSERT INTO Suppliers (SupplierID, CompanyName, Address, Phone)
VALUES ('S002', 'Pizza Supplier 2', '456 Oak Avenue, City B', '987-654-3210');

INSERT INTO Suppliers (SupplierID, CompanyName, Address, Phone)
VALUES ('S003', 'Pizza Supplier 3', '789 Elm Road, City C', '555-555-5555');


-- Thêm dữ liệu cho bảng Products
INSERT INTO Products (ProductID, ProductName, Description, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage)
VALUES ('P001', 'Margherita', 'Classic pizza topped with fresh tomatoes, mozzarella, and basil leaves.', 'S001', 'C001', 1, 9.99, 'https://eu.ooni.com/cdn/shop/articles/20220211142754-margherita-9920.jpg?crop=center&height=800&v=1644590277&width=800');

INSERT INTO Products (ProductID, ProductName, Description, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage)
VALUES ('P002', 'Pepperoni', 'Delicious pizza topped with spicy pepperoni slices and melted cheese.', 'S001', 'C001', 1, 11.99, 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Pepperoni_Pizza_%2829204589095%29.jpg/1200px-Pepperoni_Pizza_%2829204589095%29.jpg');

INSERT INTO Products (ProductID, ProductName, Description, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage)
VALUES ('P003', 'Vegetarian Deluxe', 'Veggie-packed pizza with a variety of fresh vegetables and flavorful seasonings.', 'S002', 'C002', 1, 12.99, 'https://www.killingthyme.net/wp-content/uploads/2020/08/veggie-deluxe-pizza.jpg');

INSERT INTO Products (ProductID, ProductName, Description, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage)
VALUES ('P004', 'Hawaiian', 'A tropical twist on pizza with ham, pineapple, and a blend of sweet and savory flavors.', 'S001', 'C001', 1, 10.99, 'https://www.kingarthurbaking.com/sites/default/files/styles/featured_image/public/2020-03/hawaiian-pizza.jpg?itok=a1-_QjRA');

-- Thêm dữ liệu cho bảng Products
INSERT INTO Products (ProductID, ProductName, Description, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage)
VALUES ('P005', 'Margarita', 'Classic Italian pizza with fresh tomatoes, mozzarella, and basil.', 'S003', 'C001', 1, 9.99, 'https://ohsweetbasil.com/wp-content/uploads/four-cheese-margherita-pizza-recipe-12-scaled.jpg');

INSERT INTO Products (ProductID, ProductName, Description, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage)
VALUES ('P006', 'Four Cheese', 'A cheesy delight with a blend of four different types of cheese.', 'S002', 'C001', 1, 11.99, 'https://static.onecms.io/wp-content/uploads/sites/19/2010/04/12/oh-four-cheese-pizza-x.jpg');

INSERT INTO Products (ProductID, ProductName, Description, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage)
VALUES ('P007', 'Veggie Supreme', 'Loaded with a variety of fresh vegetables and bursting with flavors.', 'S001', 'C002', 1, 12.99, 'https://i0.wp.com/www.thursdaynightpizza.com/wp-content/uploads/2022/06/veggie-pizza-side-view-out-of-oven.png?resize=720%2C480&ssl=1');

INSERT INTO Products (ProductID, ProductName, Description, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage)
VALUES ('P008', 'BBQ Chicken', 'A mouthwatering pizza topped with tender chicken and tangy barbecue sauce.', 'S001', 'C001', 1, 10.99, 'https://staticcookist.akamaized.net/wp-content/uploads/sites/22/2022/02/bbq-chicken-pizza-1.jpg');

INSERT INTO Products (ProductID, ProductName, Description, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage)
VALUES ('P009', 'Meat Lovers', 'For the meat enthusiasts, this pizza is loaded with a variety of savory meats.', 'S003', 'C001', 1, 11.99, 'https://www.acozykitchen.com/wp-content/uploads/2021/10/MeatLoversPizza-8-1.jpg');

INSERT INTO Products (ProductID, ProductName, Description, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage)
VALUES ('P010', 'Hawaiian BBQ', 'A fusion of Hawaiian and barbecue flavors, featuring ham, pineapple, and barbecue sauce.', 'S002', 'C001', 1, 12.99, 'https://www.brakebush.com/wp-content/uploads/Hawaiian-BBQ-Chicken-Pizza-500x375.jpg');



INSERT INTO Customers (CustomerID, Password, ContactName, Address, Phone)
VALUES ('C001', '1709', 'John Smith', '123 Main St, City, Country', '123-456-7890');

INSERT INTO Customers (CustomerID, Password, ContactName, Address, Phone)
VALUES ('C002', 'securepass', 'Jane Doe', '456 Elm St, City, Country', '987-654-3210');

INSERT INTO Customers (CustomerID, Password, ContactName, Address, Phone)
VALUES ('C003', 'password2022', 'Robert Johnson', '789 Oak St, City, Country', '555-123-4567');

SELECT *FROM Products
SELECT *FROM Customers
DELETE FROM Products

SELECT ProductID, ProductName, Description, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage FROM Products WHERE ProductName LIKE 'P'
INSERT INTO Orders (OrderID, CustomerID, OrderDate, RequiredDate, ShippedDate, Freight, ShipAddress)
VALUES ('O0001', 'C001', '2024-03-10', '2024-03-15', '2024-03-20', 10.50, '123 Main St');
	
INSERT INTO Orders (OrderID, CustomerID, OrderDate, RequiredDate, ShippedDate, Freight, ShipAddress)
VALUES ('O0002', 'C001', '2024-03-11', '2024-03-16', '2024-03-21', 15.75, '456 Elm St');
SELECT *FROM Orders
SELECT *FROM OrderDetails
SELECT *FROM Customers
SELECT MIN(OrderID) FROM Orders

DELETE FROM Orders WHERE OrderID = 'O0011';

SELECT ContactName From Customers where ContactName = 'John Smith'
SELECT COUNT(*) FROM Customers WHERE CustomerID = 'C001'