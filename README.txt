After loading the project on local drive, you can make command 
make dev - to build and run docker containers of the project
make down - to stop docker containers.

Then make sure that containers are working (command docker ps -a), and 
access the pgadmin4 via your favorite web browser by visiting the URL http://localhost:5050/. 
Use the admin@admin.com as the email address and root as the password to log in.
Click Servers > Create > Server to create a new server.
Select the General tab. For the name field, use any name. 
In this case, I’ll use my_db. Now move to the Connection tab. 
We have the value of Host, which in this case green_shop_postgres_db.
(Instead of IPAddress, you can use as the value of Host the container name,
because the container name is identical to the hostname.
You can find the PostgreSQL database server’s container name using the docker ps command 
and grab the name from the NAMES column. In this post, we have explicitly named the container 
in the docker-compose.yml file, so you can refer to that as well.)
Use the value postgres for Username and alex for Password, also tick the Save password 
if you don’t want to type the password every time you log in to pgadmin4.

Then create database shop_db

You can create tables in the database by backuping data using pgadmin4
from the file shop_db.backup in folder postgres-data

You can also create tables in the database performing these queries:

USE shop_db;

CREATE TABLE categories (
  id SERIAL NOT NULL,
  title varchar(32),
  PRIMARY KEY (id) 
);

CREATE TABLE products (
  id SERIAL NOT NULL,
  name varchar(64),
  image_data oid,
  price numeric(10,2), 
  quantity numeric(10,2),
  category_id int,  
  PRIMARY KEY (id), 
  FOREIGN KEY (category_id) REFERENCES categories(id));

INSERT INTO categories (title)
VALUES
	('Berries'),
	('Vegetables'),
	('Fruits'),
	('Cereals'),
	('Spices');

Then in POSTMAN you should perform POST queries (body with keys name, imageData, price, quantity, category) 
on url http://localhost:8080/shop_rest/shop/products
DATA for queries (images are located in FRONTEND/src/assets... folder:
 
	('Blueberry', '../../assets/img/berries/blueberries.jpg', 8, 10, 1),
	('Strawberry', '../../assets/img/berries/strawberries.jpg', 5, 10, 1),
	('Raspberry', '../../assets/img/berries/raspberries.jpg', 7, 10, 1),
	('Blackberry', '../../assets/img/berries/blackberries.jpg', 6, 10, 1),
	('Red_Currants', '../../assets/img/berries/redcurrants.jpg', 8, 10, 1),
	('White_Currants', '../../assets/img/berries/whitecurrants.jpg', 9, 10, 1),
	('Blackcurrants', '../../assets/img/berries/blackcurrants.jpg', 8, 10, 1),
	('Cranberry', '../../assets/img/berries/cranberries.jpg', 12, 10, 1),
	('Gooseberry', '../../assets/img/berries/gooseberry.jpg', 10, 10, 1),
	('Brussels_Sprouts', '../../assets/img/vegetables/brusselssprouts.jpg', 10, 10, 2),
	('Cucumber', '../../assets/img/vegetables/cucumber.jpg', 3, 10, 2),
	('Zucchini', '../../assets/img/vegetables/zucchini.jpg', 6.5, 10, 2),
	('Potato', '../../assets/img/vegetables/potatoes.jpg', 2, 10, 2),
	('Celery', '../../assets/img/vegetables/celery.jpg', 10, 10, 2),
	('Onion', '../../assets/img/vegetables/onions.jpg', 3, 10, 2),
	('Garlic', '../../assets/img/vegetables/garlic.jpg', 10, 10, 2),
	('Tomato', '../../assets/img/vegetables/tomatoes.jpg', 4.5, 10, 2),
	('Orange', '../../assets/img/fruits/oranges.jpg', 7, 10, 3),
	('Grapefruit', '../../assets/img/fruits/grapefruits.jpg', 7, 10, 3),
	('Nectarine', '../../assets/img/fruits/nectarines.jpg', 11, 10, 3),
	('Mango', '../../assets/img/fruits/mangoes.jpg', 15, 10, 3),
	('Plum', '../../assets/img/fruits/plums.jpg', 4, 10, 3),
	('Rice', '../../assets/img/cereals/rice.jpg', 3.75, 10, 4),
	('Corn', '../../assets/img/cereals/corn.jpg', 8, 10, 4),
	('Oats', '../../assets/img/cereals/oats.jpg', 5, 10, 4),
	('Sorghum', '../../assets/img/cereals/sorghum.jpg', 12, 10, 4),
	('Quinoa', '../../assets/img/cereals/quinoa.jpg', 12, 10, 4),
	('Millet', '../../assets/img/cereals/millet.jpg', 9, 10, 4),
	('Buckwheat', '../../assets/img/cereals/buckwheat.jpg', 6, 10, 4),
	('Cardamom', '../../assets/img/spices/cardamom.jpg', 15, 10, 5),
	('Cinnamon', '../../assets/img/spices/cinnamon.jpg', 20, 10, 5),
	('Cloves', '../../assets/img/spices/cloves.jpg', 25, 10, 5),
	('Turmeric', '../../assets/img/spices/turmeric.jpg', 25, 10, 5),
	('Saffron', '../../assets/img/spices/saffron.jpg', 20, 10, 5),
	('Cumin', '../../assets/img/spices/cumin.jpg', 25, 10, 5);


The working site is located on address http://localhost:3000

It is possible to modify frontend and changes immediately will be displayed on the site.
The backend was developed in IntellijIdea, package file shop_rest.war is located in folder /backend/target