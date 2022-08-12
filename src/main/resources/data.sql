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
	email varchar(30) UNIQUE
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

INSERT INTO products (id, quantity, price, description, image_url, name) VALUES 
(6, 34, 29.99, 'SURVIVE THE NIGHT It’s always best to avoid the unpredictable by distancing yourself from wandering mobs—you never know what’ll happen if they get too close!', 'https://drive.google.com/uc?id=1j_8K-LXonex25HYr5g243I1eVHuLc97T', 'Minecraft'),
(7, 24, 29.99, 'Set in the fictionalized 80s Latin America, Cartel Tycoon tells the story of a time when the cocaine took over first the US, and then the whole world.', 'https://drive.google.com/uc?id=16vs9Zz5om5Uso8Wf-LBO1yO5aWs2m2RQ', 'Cartel Tycoon'),
(8, 53, 24.99, 'Neon White is a lightning fast first-person action game about exterminating demons in Heaven. You are White, an assassin handpicked from Hell to compete with other demon slayers for a chance to live permanently in Heaven.', 'https://drive.google.com/uc?id=1UzJhTjR-2QBWvNuwVnnwzmJYRiAIPcuZ', 'Neon White'),
(9, 82, 29.99, 'Mario Golf: Toadstool Tour updates the Mario Golf series with more swing control, new courses, and fun side games.', 'https://drive.google.com/uc?id=1UMa_50D_Wxhah6qeE1a4inVb1Xu8S5EL', 'Mario Golf: Toadstool Tour'),
(10, 10, 29.99, 'Get ready for a new role-playing adventure as Mario returns to paper form to stop a dangerous threat. In Paper Mario 2, Mario can dodge, inflict damage, and impress the crowd to strengthen his attacks.', 'https://drive.google.com/uc?id=13D7lfuQopvMFTUmrJEImO6AZDbxFhzDl', 'Paper Mario: The Thousand Year Door'),
(12, 63, 59.99, 'The stakes have never been higher as players take on the role of lethal Tier One Operators in a heart-racing saga that will affect the global balance of power.', 'https://drive.google.com/uc?id=1tUAWCLMode8z-ev7R_6KOPSusYy8i8L7', 'Call of Duty: Modern Warfare'),
(13, 49, 9.99, 'Land on strategic locations, loot weapons and supplies, and survive to become the last team standing across various, diverse Battlegrounds.', 'https://drive.google.com/uc?id=1alg6tMgYY7RLKAOkgOD8wRd1ELiGU9dQ', 'PUBG: Battlegrounds'),
(14, 20, 29.99, 'In Prey, you awaken aboard Talos I, a space station orbiting the moon in the year 2032. You are the key subject of an experiment meant to alter humanity forever – but things have gone terribly wrong.', 'https://drive.google.com/uc?id=1YOuO_O90Vmu8qQ6TlG0XtAlCOqE-dLGj', 'Prey'),
(15, 50, 42.69, 'Join Hank, Peggy, Bobby, Bill, Dale and Boomhauer in a series of activities and competitions that include lawnmower racing, paintball, mini-golf, tic-tac-toss, and a scavenger hunt.', 'https://drive.google.com/uc?id=167hXC-8ogQHFhN1vFuEFhV5DeUcYVV6a', 'King of the Hill');

INSERT INTO users (id, email, password, username, firstname, lastname) VALUES (
    1,
    'testuser@gmail.com',
    'pass',
    'test',
    'nef',
    'bro'
);

INSERT INTO users (id, email, password, username, firstname, lastname) VALUES (
    2,
    'testuser2@gmail.com',
    'pass2',
    'test2',
    'nef2',
    'bro2'
);

BEGIN;

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

COMMIT;

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

INSERT INTO wishlist_item (product_id, user_id, quantity) VALUES (
    4,
    1,
    1
);

INSERT INTO wishlist_item (product_id, user_id, quantity) VALUES (
    2,
    1,
    1
);

INSERT INTO wishlist_item (product_id, user_id, quantity) VALUES (
    5,
    1,
    1
);