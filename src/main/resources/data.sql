CREATE TABLE products (
	id SERIAL PRIMARY KEY,
	name varchar(255),
	price float,
	quantity int,
	image_url varchar(255),
	description varchar(255)
);

CREATE TABLE users (
	id serial PRIMARY KEY,
	firstname varchar(30),
	lastname varchar(30),
	username varchar(20),
	password varchar(30),
	email varchar(30)
);

CREATE TABLE cart_item (
	id serial PRIMARY KEY,
	product_id int, FOREIGN KEY (product_id) REFERENCES products(id),
	user_id int, FOREIGN KEY (user_id) REFERENCES users(id),
	quantity int
);

CREATE TABLE wishlist_item (
	id serial PRIMARY KEY,
	product_id int, FOREIGN KEY (product_id) REFERENCES products(id),
	user_id int, FOREIGN KEY (user_id) REFERENCES users(id),
	quantity int
);

CREATE TABLE orderhistory_item (
	id serial PRIMARY KEY,
	product_id int, FOREIGN KEY (product_id) REFERENCES products(id),
	user_id int, FOREIGN KEY (user_id) REFERENCES users(id),
	quantity int,
	purchase_time timestamp
);

CREATE TABLE tag (
	name varchar(20) PRIMARY KEY 
);

CREATE TABLE tag_junction(
	product_id int, FOREIGN KEY (product_id) REFERENCES products(id),
	tag_name varchar(20), FOREIGN KEY (tag_name) REFERENCES tag(name)
);

INSERT INTO products (id, quantity, price, description, image_url, name) VALUES (
    1,
    10,
    20.00,
    'A nice pair of headphones',
    'https://i.insider.com/54eb437f6bb3f7697f85da71?width=1000&format=jpeg&auto=webp',
    'Headphones'
),
(
    2,
    5,
    45.00,
    'A nice TeeShirt',
    'https://d3o2e4jr3mxnm3.cloudfront.net/Mens-Jake-Guitar-Vintage-Crusher-Tee_68382_1_lg.png',
    'TeeShirt'
),
(
    3,
    20,
    2.50,
    'A reusable shopping bag',
    'https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png',
    'Shopping Bag'
),
(
    4,
    20,
    10.00,
    'A fancy cap for a fancy person',
    'https://d3o2e4jr3mxnm3.cloudfront.net/Rocket-Vintage-Chill-Cap_66374_1_lg.png',
    'Baseball Cap'
),
(
    5,
    3,
    80.00,
    'A nice coat',
    'https://www.pngarts.com/files/3/Women-Jacket-PNG-High-Quality-Image.png',
    'Coat'
);

INSERT INTO users (id, email, password, username, firstname, lastname) VALUES (
    1,
    'testuser@gmail.com',
    'pass',
    'test',
    'nef',
    'bro'
);

INSERT INTO orderhistory_item (product_id, user_id, quantity, purchase_time) VALUES (
    1,
    1,
    10,
    CURRENT_TIMESTAMP
);

INSERT INTO orderhistory_item (product_id, user_id, quantity, purchase_time) VALUES (
    2,
    1,
    20,
    CURRENT_TIMESTAMP
);

INSERT INTO orderhistory_item (product_id, user_id, quantity, purchase_time) VALUES (
    3,
    1,
    30,
    CURRENT_TIMESTAMP
);

INSERT INTO orderhistory_item (product_id, user_id, quantity, purchase_time) VALUES (
    4,
    1,
    40,
    CURRENT_TIMESTAMP
);

INSERT INTO orderhistory_item (product_id, user_id, quantity, purchase_time) VALUES (
    5,
    1,
    50,
    CURRENT_TIMESTAMP
);


INSERT INTO tag (name) VALUES (
    'bryan'
);

INSERT INTO tag_junction (product_id, tag_name)  VALUES (
    4,
    'bryan'
);

INSERT INTO tag_junction (product_id, tag_name)  VALUES (
    3,
    'bryan'
);
