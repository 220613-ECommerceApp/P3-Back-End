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

INSERT INTO products (quantity, price, description, image_url, name) VALUES 
(34, 29.99, 'SURVIVE THE NIGHT It’s always best to avoid the unpredictable by distancing yourself from wandering mobs—you never know what’ll happen if they get too close!', 'https://propane-images.s3.amazonaws.com/images/minecraft.png', 'Minecraft'),
(24, 29.99, 'Set in the fictionalized 80s Latin America, Cartel Tycoon tells the story of a time when the cocaine took over first the US, and then the whole world.', 'https://propane-images.s3.amazonaws.com/images/cartel.png', 'Cartel Tycoon'),
(53, 24.99, 'Neon White is a lightning fast first-person action game about exterminating demons in Heaven. You are White, an assassin handpicked from Hell to compete with other demon slayers for a chance to live permanently in Heaven.', 'https://propane-images.s3.amazonaws.com/images/neonwhite.png', 'Neon White'),
(82, 29.99, 'Mario Golf: Toadstool Tour updates the Mario Golf series with more swing control, new courses, and fun side games.', 'https://propane-images.s3.amazonaws.com/images/mariogolf.png', 'Mario Golf: Toadstool Tour'),
(10, 29.99, 'Get ready for a new role-playing adventure as Mario returns to paper form to stop a dangerous threat. In Paper Mario 2, Mario can dodge, inflict damage, and impress the crowd to strengthen his attacks.', 'https://propane-images.s3.amazonaws.com/images/mariopaper.png', 'Paper Mario: The Thousand Year Door'),
(63, 59.99, 'The stakes have never been higher as players take on the role of lethal Tier One Operators in a heart-racing saga that will affect the global balance of power.', 'https://propane-images.s3.amazonaws.com/images/callofduty.png', 'Call of Duty: Modern Warfare'),
(49, 9.99, 'Land on strategic locations, loot weapons and supplies, and survive to become the last team standing across various, diverse Battlegrounds.', 'https://propane-images.s3.amazonaws.com/images/battlegrounds.png', 'PUBG: Battlegrounds'),
(20, 29.99, 'In Prey, you awaken aboard Talos I, a space station orbiting the moon in the year 2032. You are the key subject of an experiment meant to alter humanity forever – but things have gone terribly wrong.', 'https://propane-images.s3.amazonaws.com/images/prey.png', 'Prey'),
(50, 42.69, 'Join Hank, Peggy, Bobby, Bill, Dale and Boomhauer in a series of activities and competitions that include lawnmower racing, paintball, mini-golf, tic-tac-toss, and a scavenger hunt.', 'https://propane-images.s3.amazonaws.com/images/king_of_the_hill.png', 'King of the Hill');

INSERT INTO tag VALUES 
('Adventure'),
('Action'),
('Sports'),
('RPG'),
('Strategy');

INSERT INTO tag_junction VALUES 
(9, 'Adventure'),
(9, 'Action'),
(9, 'Sports'),
(9, 'RPG'),
(9, 'Strategy'),
(2, 'Strategy'),
(7, 'Strategy'),
(1, 'Adventure'),
(5, 'Adventure'),
(8, 'Adventure'),
(8, 'Action'),
(1, 'Action'),
(3, 'Action'),
(6, 'Action'),
(7, 'Action'),
(4, 'Sports'),
(2, 'RPG'),
(5, 'RPG'),
(8, 'RPG');

INSERT INTO orderhistory_item (product_id, user_id, quantity, purchase_time) VALUES 
(1, 1, 1, CURRENT_TIMESTAMP),
(2, 1, 2, CURRENT_TIMESTAMP),
(3, 1, 3, CURRENT_TIMESTAMP),
(4, 1, 4, CURRENT_TIMESTAMP),
(5, 1, 5, CURRENT_TIMESTAMP);

INSERT INTO wishlist_item (product_id, user_id, quantity) VALUES 
(4, 1, 1),
(2, 1, 1),
(5, 1, 1);
