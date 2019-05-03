INSERT INTO public."role" (name, qty) VALUES ('Администратор', -1), ('Покупатель', 0), ('Постоянный клиент', 5);
INSERT INTO public."order_status" (name) VALUES ('Обрабатывается'), ('Производится доставка'), ('Готов к выдаче'), ('Завершён');
INSERT INTO public."product_category" (name) VALUES ('Процессоры'), ('Материнские платы'), ('Видеокарты'), ('Оперативная память');

INSERT INTO public."user" (fio, login, password, phone, email, city, street, house, role_id) VALUES
	('Кувшинова Евгения Андреевна', 'jnuka', 'jnuka', '89063332364', 'jnuka@mail.ru', 'Тольятти', '40 лет Победы', '30', 1),
	('Цель Антон Вадимович', 'tsel', 'tsel', '89063772375', 'tsel@mail.ru', 'Тольятти', '70 лет Октября', '55', 2),
	('Марков Александр Сергеевич', 'sasha', 'sasha', '89649696000', 'sasha@mail.ru', 'Екатеренбург', 'Цветной блуьвар', '13', 2);

INSERT INTO public."product" (name, category_id, brand, price, qty, discount, description) VALUES
	('FX-6300 AM3+ OEM', 1, 'AMD', 312000, 5, 0, 'Крутой процессор, но старый'),
	('FX-8350 AM3+ OEM', 1, 'AMD', 419000, 34, 30000, 'Процессор AMD'),
	('GA-970A-DS3P V2.1', 2, 'GIGABYTE', 490000, 13, 0, 'Ультра-супер-пупер материнка'),
	('MAXIMUS VIII HERO', 2, 'ASUS', 569000, 18, 50000, 'Это же асус, нужно брать, а не читать!'),
	('RX 570', 3, 'Radeon', 1219000, 73, 0, 'Видеокарта лишней не будет, пусть и радеон'),
	('GeForce GTX 1060', 3, 'ASUS', 1703000, 54, 0, 'Лучший выбор для вашей крипто-фермы'),
	('DDR3 4Gb', 4, 'Kingston', 180000, 46, 600, 'Оперативка, как оперативка, ничего особенного'),
	('DDR4 8Gb', 4, 'Kingston', 320000, 0, 0, 'Как оперативка на 4 гига, только на 4 побольше');

INSERT INTO public."order" (user_id, status_id) VALUES (2, 1), (2, 4), (3, 2);

INSERT INTO public."basket" (order_id, product_id) VALUES (1, 1), (1, 3), (2, 6), (3, 7);
