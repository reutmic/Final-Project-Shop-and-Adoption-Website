# tables initialization:

# ========= products table ============

INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (2, 'Beds', 54, 'Cats Bed', 'Comfortable and warm bed for all cats.', 'Cats', 7, 0, 'cats-bed.jpg');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (3, 'Clothes', 28, 'Black Wings Accessory', 'Not all heroes wear capes. Some of them are cats wearing black wings.', 'Cats', 18, 0, 'cats-clothes-bat-wings.png');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (4, 'Clothes', 12, 'Cats Bandana', 'Red bandana for cats.', 'Cats', 32, 0, 'cats-clothes-bandana.png');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (5, 'Clothes', 12, 'Dogs Red Bandana', 'Red bandana for dogs. Suitable for all dogs.', 'Dogs', 4, 0, 'dogs-clothes-bandana.png');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (6, 'Clothes', 89, 'Dogs Suit', 'High quality tailored tuxedo suit for your dog.', 'Dogs', 5, 0, 'dogs-clothes-suit.png');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (7, 'Clothes', 31, 'Dogs Hat', 'Little Christmas hat for dogs.', 'Dogs', 0, 0, 'dogs-clothes-hat.png');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (8, 'Beds', 54, 'Cats bed', 'Comfortable and warm bed for all cats.', 'Cats', 7, 0, 'cats-bed.jpg');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (9, 'Toys', 9, 'Ball', 'Colorful soft ball that your dog will not be able to tear apart! Hopefully...', 'Dogs', 16, 0, 'dogs-toys-ball.png');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (10, 'Toys', 30, 'Stick', 'No more bringing home muddy sticks from walks! The perfect toy for your dog!', 'Dogs', 11, 0, 'dogs-toys-stick.png');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (11, 'Toys', 19, 'Tennis Ball', 'Tennis ball suitable for dogs to play with.', 'Dogs', 89, 0, 'dogs-toys-tennis.png');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (12, 'Others', 34, 'Plushie', 'Soft big-sized plushie for your cat.', 'Cats', 7, 0, 'cats-plushie.jpg');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (13, 'Others', 99, 'Blanket', 'So comfy and warm, your dog will not want to get out of bed.', 'Dogs', 6, 0, 'dogs-clothes-blanket.png');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (14, 'Clothes', 10, 'Pink Hair Clip', 'Make your dog fabulous.', 'Dogs', 0, 0, 'dogs-clothes-1.png');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (15, 'Foods', 112, 'Dogs Food', 'Food for dogs in all ages.', 'Dogs', 11, 0, 'dogs-food.jpg');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (16, 'Foods', 9, 'Bone', 'Rich-flavored bone.', 'Dogs', 18, 0, 'dogs-food-bone.webp');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (17, 'Foods', 120, 'Cats Food', 'Food for cats in all ages.', 'Cats', 5, 0, 'cats-food.jfif');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (18, 'Others', 299, 'Cat Sofa', 'A whole big comfortable sofa just for your cat! Yes, just for your cat!', 'Cats', 3, 0, 'cats-sofa.jpg');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (19, 'Clothes', 89, 'Dogs Shirt', 'Bright and comfortable shirt.', 'Dogs', 13, 0, 'dogs-clothes-shirt.png');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (20, 'Others', 129, 'Cat camera', 'See your cat from everywhere. High-quality camera with a phone app.', 'Cats', 9, 0, 'cats-camera.jpg');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (21, 'Others', 78, 'Cat tower', 'Deluxe cat tower. Comes with everything your cat needs.', 'Cats', 18, 0, 'cats-tower.jpg');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (22, 'Others', 89, 'Cat Window Hammock', 'Comfy cat window hammock - perfect for every cat.', 'Cats', 14, 0, 'cats-wall-hammock.jpg');
INSERT INTO product (id, category, price, prod_name, description, suited_for, stock, quantity, image)
VALUES (23, 'Toys', 89, 'Cardboard Box', 'The perfect toy for your perfect cat!', 'Cats', 100, 0, 'cats-toys-box.jpg');

# --------------------------------------------------------------------------

# ========= pets table ============
INSERT INTO pet (id, age, description, name, breed, gender, type, image)
VALUES (2, 2, 'Very gentle and calm. Likes to sleep a lot and can be very playful.', 'Samuel', 'Siamese', 'Female', 'Cat', 'cat-adopt-1.png');
INSERT INTO pet (id, age, description, name, breed, gender, type, image)
VALUES (3, 2, 'Very gentle and calm. Likes to sleep a lot and can be very playful.', 'Samuel', 'Siamese', 'Female', 'Cat', 'cat-adopt-1.png');
INSERT INTO pet (id, age, description, name, breed, gender, type, image)
VALUES (4, 3, 'An adorable little fluffy friend that likes to cuddle.', 'Rex Lapis', 'Domestic Shorthair', 'Male', 'Cat', 'cat-adopt-4.png');
INSERT INTO pet (id, age, description, name, breed, gender, type, image)
VALUES (5, 1, 'Energetic and very active.', 'Lucy', 'Mixed', 'Female', 'Cat', 'cat-adopt-3.png');
INSERT INTO pet (id, age, description, name, breed, gender, type, image)
VALUES (6, 5, 'Lovely and adorable.', 'Lina', 'Mixed', 'Female', 'Cat', 'cat-adopt-2.png');
INSERT INTO pet (id, age, description, name, breed, gender, type, image)
VALUES (7, 6, 'Playful little buddy that gets along well with everyone.', 'Bobby', 'Beagle', 'Male', 'Dog', 'dog-adopt-1.png');
INSERT INTO pet (id, age, description, name, breed, gender, type, image)
VALUES (8, 4, 'Young soul full of energy.', 'Isabella', 'Mixed', 'Female', 'Dog', 'dog-adopt-2.jpg');
INSERT INTO pet (id, age, description, name, breed, gender, type, image)
VALUES (9, 10, 'Calm, gentle, likes belly rubs but does not show it. How can anyone resist those puppy dog eyes', 'Jonny', 'Mixed', 'Male', 'Dog', 'dog-adopt-4.jpg');
INSERT INTO pet (id, age, description, name, breed, gender, type, image)
VALUES (10, 2, 'Energetic and playful young fellow, who will bright up your day, everyday.', 'Bennett', 'Mixed', 'Male', 'Dog', 'dog-adopt-3.jpg');



