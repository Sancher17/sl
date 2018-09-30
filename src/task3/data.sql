INSERT INTO books (id, dateAddedBookToStore, dateOfPublication, description, isAvailable, isOld, nameBook, price) VALUES (1, '2018-09-04 00:00:00', '2016-02-09 00:00:00', 'Книга о Java', true, false, 'Java', 150);
INSERT INTO books (id, dateAddedBookToStore, dateOfPublication, description, isAvailable, isOld, nameBook, price) VALUES (2, '2018-09-04 00:00:00', '2018-06-09 00:00:00', 'Книга об Android', true, false, 'Android', 75);
INSERT INTO books (id, dateAddedBookToStore, dateOfPublication, description, isAvailable, isOld, nameBook, price) VALUES (3, '2018-09-04 00:00:00', '2015-06-01 00:00:00', 'Книга об Thread', true, false, 'Thread', 233);
INSERT INTO books (id, dateAddedBookToStore, dateOfPublication, description, isAvailable, isOld, nameBook, price) VALUES (4, '2018-09-04 00:00:00', '2017-12-06 00:00:00', 'Книга об MongoDB', true, false, 'MongoDB', 99);
INSERT INTO books (id, dateAddedBookToStore, dateOfPublication, description, isAvailable, isOld, nameBook, price) VALUES (5, '2018-09-04 00:00:00', '2019-09-18 00:00:00', 'Книга об Patterns', true, false, 'Patterns', 257);
INSERT INTO books (id, dateAddedBookToStore, dateOfPublication, description, isAvailable, isOld, nameBook, price) VALUES (6, '2018-09-04 00:00:00', '2018-06-09 00:00:00', 'Книга о Junit', true, false, 'Junit', 25);


INSERT INTO orders (id, dateOfStartedOrder, dateOfCompletedOrder, isCompletedOrder, book_id) VALUES (1, '2018-09-28 19:30:20', null, false, 3);
INSERT INTO orders (id, dateOfStartedOrder, dateOfCompletedOrder, isCompletedOrder, book_id) VALUES (2, '2018-09-28 19:30:20', null, false, 2);
INSERT INTO orders (id, dateOfStartedOrder, dateOfCompletedOrder, isCompletedOrder, book_id) VALUES (3, '2018-09-28 19:30:20', null, false, 2);
INSERT INTO orders (id, dateOfStartedOrder, dateOfCompletedOrder, isCompletedOrder, book_id) VALUES (4, '2018-09-28 19:30:20', null, false, 3);
INSERT INTO orders (id, dateOfStartedOrder, dateOfCompletedOrder, isCompletedOrder, book_id) VALUES (5, '2018-09-28 19:30:20', null, false, 4);


INSERT INTO request (id, requireNameBook, requireIsCompleted, requireQuantity) VALUES (1, 'JavaScript', false, 1);
INSERT INTO request (id, requireNameBook, requireIsCompleted, requireQuantity) VALUES (2, 'Oracle', false, 1);
INSERT INTO request (id, requireNameBook, requireIsCompleted, requireQuantity) VALUES (3, 'Kotlin', false, 2);