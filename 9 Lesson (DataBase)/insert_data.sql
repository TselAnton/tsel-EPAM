INSERT INTO public."role" (name, qty) VALUES ('�������������', -1), ('����������', 0), ('���������� ������', 5);
INSERT INTO public."order_status" (name) VALUES ('��������������'), ('������������ ��������'), ('����� � ������'), ('��������');
INSERT INTO public."product_category" (name) VALUES ('����������'), ('����������� �����'), ('����������'), ('����������� ������');

INSERT INTO public."user" (fio, login, password, phone, email, city, street, house, role_id) VALUES
	('��������� ������� ���������', 'jnuka', 'jnuka', '89063332364', 'jnuka@mail.ru', '��������', '40 ��� ������', '30', 1),
	('���� ����� ���������', 'tsel', 'tsel', '89063772375', 'tsel@mail.ru', '��������', '70 ��� �������', '55', 2),
	('������ ��������� ���������', 'sasha', 'sasha', '89649696000', 'sasha@mail.ru', '������������', '������� �������', '13', 2);

INSERT INTO public."product" (name, category_id, brand, price, qty, discount, description) VALUES
	('FX-6300 AM3+ OEM', 1, 'AMD', 312000, 5, 0, '������ ���������, �� ������'),
	('FX-8350 AM3+ OEM', 1, 'AMD', 419000, 34, 30000, '��������� AMD'),
	('GA-970A-DS3P V2.1', 2, 'GIGABYTE', 490000, 13, 0, '������-�����-����� ���������'),
	('MAXIMUS VIII HERO', 2, 'ASUS', 569000, 18, 50000, '��� �� ����, ����� �����, � �� ������!'),
	('RX 570', 3, 'Radeon', 1219000, 73, 0, '���������� ������ �� �����, ����� � ������'),
	('GeForce GTX 1060', 3, 'ASUS', 1703000, 54, 0, '������ ����� ��� ����� ������-�����'),
	('DDR3 4Gb', 4, 'Kingston', 180000, 46, 600, '����������, ��� ����������, ������ ����������'),
	('DDR4 8Gb', 4, 'Kingston', 320000, 0, 0, '��� ���������� �� 4 ����, ������ �� 4 ��������');

INSERT INTO public."order" (user_id, status_id) VALUES (2, 1), (2, 4), (3, 2);

INSERT INTO public."basket" (order_id, product_id) VALUES (1, 1), (1, 3), (2, 6), (3, 7);
