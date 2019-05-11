CREATE TABLE if not exists public."role"
(
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(30) NOT NULL UNIQUE,
	qty INTEGER DEFAULT = 0
);

ALTER TABLE public."role" OWNER to postgres;

CREATE TABLE if not exists public."order_status"
(
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(30) NOT NULL UNIQUE
);

ALTER TABLE public."order_status" OWNER to postgres;

CREATE TABLE if not exists public."product_category"
(
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(30) NOT NULL UNIQUE
);

ALTER TABLE public."product_category" OWNER to postgres;

CREATE TABLE if not exists public."user"
(
	id SERIAL NOT NULL PRIMARY KEY,
	fio VARCHAR(90),
	login VARCHAR(30) NOT NULL UNIQUE,
	password VARCHAR(30) NOT NULL UNIQUE,
	phone VARCHAR(11) NOT NULL UNIQUE,
	email VARCHAR(30) NOT NULL UNIQUE,
	city VARCHAR(30) NOT NULL,
	street VARCHAR(30),
	house VARCHAR(10),
	apartment VARCHAR(10),
	registration_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	role_id INTEGER REFERENCES public."role" (id),
	qty_orders INTEGER DEFAULT = 0
);

ALTER TABLE public."user" OWNER to postgres;

CREATE TABLE if not exists public."product"
(
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(30) NOT NULL UNIQUE,
	category_id INTEGER REFERENCES public."product_category" (id),
	brand VARCHAR(30) NOT NULL,
	price INTEGER NOT NULL,
	qty INTEGER DEFAULT = 0,
	discount INTEGER DEFAULT = 0,
	description text NOT NULL,
);

ALTER TABLE public."product" OWNER to postgres;

CREATE TABLE if not exists public."order"
(
	id SERIAL NOT NULL PRIMARY KEY,
	user_id INTEGER REFERENCES public."user" (id),
	date TIMESTAMP WITHOUT TIME ZONE 
		NOT NULL DEFAULT CURRENT_TIMESTAMP,
	status_id INTEGER REFERENCES public."order_status" (id)
);

ALTER TABLE public."order" OWNER to postgres;

CREATE TABLE if not exists public."basket"
(
	order_id INTEGER REFERENCES public."order" (id),
	product_id INTEGER REFERENCES public."product" (id)
);

ALTER TABLE public."basket" OWNER to postgres;