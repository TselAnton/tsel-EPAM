INSERT INTO public."role" (name, qty) VALUES ('�������������', -1), ('����������', 0), ('���������� ������', 5);
INSERT INTO public."order_status" (name) VALUES ('���������'), ('��������');
INSERT INTO public."product_category" (name) VALUES ('����������'), ('����������� �����'), ('����������'), ('����������� ������');

INSERT INTO public."user" (fio, login, password, phone, email, city, street, house, apartment, role_id) VALUES
	('��������� ������� ���������', 'jnuka', 'jnuka', '89063332364', 'jnuka@mail.ru', '��������', '40 ��� ������', '30', '24', 1),
	('���� ����� ���������', 'tsel', 'tsel', '89063772375', 'tsel@mail.ru', '��������', '70 ��� �������', '55', '52', 2);

INSERT INTO public."product" (name, category_id, brand, price, qty, discount, description) VALUES
	('FX-6300 AM3+ OEM', 1, 'AMD', 312000, 5, 0, '������ ���������, �� ������'),
	('FX-8350 AM3+ OEM', 1, 'AMD', 419000, 34, 30000, '��������� AMD'),
	('GA-970A-DS3P V2.1', 2, 'GIGABYTE', 490000, 13, 0, '������-�����-����� ���������'),
	('MAXIMUS VIII HERO', 2, 'ASUS', 569000, 18, 50000, '��� �� ����, ����� �����, � �� ������!'),
	('RX 570', 3, 'Radeon', 1219000, 73, 0, '���������� ������ �� �����, ����� � ������'),
	('GeForce GTX 1060', 3, 'ASUS', 1703000, 54, 0, '������ ����� ��� ����� ������-�����'),
	('DDR3 4Gb', 4, 'Kingston', 180000, 46, 600, '����������, ��� ����������, ������ ����������'),
	('DDR4 8Gb', 4, 'Kingston', 320000, 0, 0, '��� ���������� �� 4 ����, ������ �� 4 ��������');
