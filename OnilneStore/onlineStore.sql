PGDMP         6                w            online_store    9.6.12    9.6.12 B    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �           1262    16594    online_store    DATABASE     �   CREATE DATABASE online_store WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE online_store;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12387    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    16688    basket    TABLE     M   CREATE TABLE public.basket (
    order_id integer,
    product_id integer
);
    DROP TABLE public.basket;
       public         postgres    false    3            �            1259    16671    order    TABLE     �   CREATE TABLE public."order" (
    id integer NOT NULL,
    user_id integer,
    date timestamp without time zone DEFAULT now() NOT NULL,
    status_id integer
);
    DROP TABLE public."order";
       public         postgres    false    3            �            1259    16669    order_id_seq    SEQUENCE     u   CREATE SEQUENCE public.order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.order_id_seq;
       public       postgres    false    3    196            �           0    0    order_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.order_id_seq OWNED BY public."order".id;
            public       postgres    false    195            �            1259    16608    order_status    TABLE     g   CREATE TABLE public.order_status (
    id integer NOT NULL,
    name character varying(30) NOT NULL
);
     DROP TABLE public.order_status;
       public         postgres    false    3            �            1259    16606    order_status_id_seq    SEQUENCE     |   CREATE SEQUENCE public.order_status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.order_status_id_seq;
       public       postgres    false    3    188            �           0    0    order_status_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.order_status_id_seq OWNED BY public.order_status.id;
            public       postgres    false    187            �            1259    16651    product    TABLE       CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    category_id integer,
    brand character varying(30) NOT NULL,
    price integer NOT NULL,
    qty integer DEFAULT 0,
    discount integer DEFAULT 0,
    description text NOT NULL
);
    DROP TABLE public.product;
       public         postgres    false    3            �            1259    16618    product_category    TABLE     k   CREATE TABLE public.product_category (
    id integer NOT NULL,
    name character varying(30) NOT NULL
);
 $   DROP TABLE public.product_category;
       public         postgres    false    3            �            1259    16616    product_category_id_seq    SEQUENCE     �   CREATE SEQUENCE public.product_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.product_category_id_seq;
       public       postgres    false    190    3            �           0    0    product_category_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.product_category_id_seq OWNED BY public.product_category.id;
            public       postgres    false    189            �            1259    16649    product_id_seq    SEQUENCE     w   CREATE SEQUENCE public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.product_id_seq;
       public       postgres    false    194    3            �           0    0    product_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;
            public       postgres    false    193            �            1259    16597    role    TABLE     z   CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    qty integer DEFAULT 0
);
    DROP TABLE public.role;
       public         postgres    false    3            �            1259    16595    role_id_seq    SEQUENCE     t   CREATE SEQUENCE public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.role_id_seq;
       public       postgres    false    186    3            �           0    0    role_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;
            public       postgres    false    185            �            1259    16628    user    TABLE       CREATE TABLE public."user" (
    id integer NOT NULL,
    fio character varying(90),
    login character varying(30) NOT NULL,
    password character varying(30) NOT NULL,
    phone character varying(11) NOT NULL,
    email character varying(30) NOT NULL,
    city character varying(30) NOT NULL,
    street character varying(30),
    house character varying(10),
    apartment character varying(10),
    registration_date timestamp without time zone DEFAULT now() NOT NULL,
    role_id integer,
    qty_orders integer DEFAULT 0
);
    DROP TABLE public."user";
       public         postgres    false    3            �            1259    16626    user_id_seq    SEQUENCE     t   CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.user_id_seq;
       public       postgres    false    3    192            �           0    0    user_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;
            public       postgres    false    191            �           2604    16674    order id    DEFAULT     f   ALTER TABLE ONLY public."order" ALTER COLUMN id SET DEFAULT nextval('public.order_id_seq'::regclass);
 9   ALTER TABLE public."order" ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    195    196    196            �           2604    16611    order_status id    DEFAULT     r   ALTER TABLE ONLY public.order_status ALTER COLUMN id SET DEFAULT nextval('public.order_status_id_seq'::regclass);
 >   ALTER TABLE public.order_status ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    187    188    188            �           2604    16654 
   product id    DEFAULT     h   ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);
 9   ALTER TABLE public.product ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    193    194    194            �           2604    16621    product_category id    DEFAULT     z   ALTER TABLE ONLY public.product_category ALTER COLUMN id SET DEFAULT nextval('public.product_category_id_seq'::regclass);
 B   ALTER TABLE public.product_category ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    190    189    190            �           2604    16600    role id    DEFAULT     b   ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);
 6   ALTER TABLE public.role ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    186    185    186            �           2604    16631    user id    DEFAULT     d   ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);
 8   ALTER TABLE public."user" ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    191    192    192            �          0    16688    basket 
   TABLE DATA               6   COPY public.basket (order_id, product_id) FROM stdin;
    public       postgres    false    197   �G       �          0    16671    order 
   TABLE DATA               ?   COPY public."order" (id, user_id, date, status_id) FROM stdin;
    public       postgres    false    196   H       �           0    0    order_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.order_id_seq', 2, true);
            public       postgres    false    195            �          0    16608    order_status 
   TABLE DATA               0   COPY public.order_status (id, name) FROM stdin;
    public       postgres    false    188   >H       �           0    0    order_status_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.order_status_id_seq', 2, true);
            public       postgres    false    187            �          0    16651    product 
   TABLE DATA               b   COPY public.product (id, name, category_id, brand, price, qty, discount, description) FROM stdin;
    public       postgres    false    194   �H       �          0    16618    product_category 
   TABLE DATA               4   COPY public.product_category (id, name) FROM stdin;
    public       postgres    false    190   �J       �           0    0    product_category_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.product_category_id_seq', 4, true);
            public       postgres    false    189            �           0    0    product_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.product_id_seq', 17, true);
            public       postgres    false    193            �          0    16597    role 
   TABLE DATA               -   COPY public.role (id, name, qty) FROM stdin;
    public       postgres    false    186   XK       �           0    0    role_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.role_id_seq', 3, true);
            public       postgres    false    185            �          0    16628    user 
   TABLE DATA               �   COPY public."user" (id, fio, login, password, phone, email, city, street, house, apartment, registration_date, role_id, qty_orders) FROM stdin;
    public       postgres    false    192   �K       �           0    0    user_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.user_id_seq', 4, true);
            public       postgres    false    191                       2606    16677    order order_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_pkey;
       public         postgres    false    196    196                       2606    16615 "   order_status order_status_name_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.order_status
    ADD CONSTRAINT order_status_name_key UNIQUE (name);
 L   ALTER TABLE ONLY public.order_status DROP CONSTRAINT order_status_name_key;
       public         postgres    false    188    188                       2606    16613    order_status order_status_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.order_status
    ADD CONSTRAINT order_status_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.order_status DROP CONSTRAINT order_status_pkey;
       public         postgres    false    188    188            	           2606    16625 *   product_category product_category_name_key 
   CONSTRAINT     e   ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT product_category_name_key UNIQUE (name);
 T   ALTER TABLE ONLY public.product_category DROP CONSTRAINT product_category_name_key;
       public         postgres    false    190    190                       2606    16623 &   product_category product_category_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT product_category_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.product_category DROP CONSTRAINT product_category_pkey;
       public         postgres    false    190    190                       2606    16663    product product_name_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_name_key UNIQUE (name);
 B   ALTER TABLE ONLY public.product DROP CONSTRAINT product_name_key;
       public         postgres    false    194    194                       2606    16661    product product_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.product DROP CONSTRAINT product_pkey;
       public         postgres    false    194    194                       2606    16605    role role_name_key 
   CONSTRAINT     M   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_name_key UNIQUE (name);
 <   ALTER TABLE ONLY public.role DROP CONSTRAINT role_name_key;
       public         postgres    false    186    186                       2606    16603    role role_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public         postgres    false    186    186                       2606    16643    user user_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_email_key;
       public         postgres    false    192    192                       2606    16637    user user_login_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_login_key UNIQUE (login);
 ?   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_login_key;
       public         postgres    false    192    192                       2606    16639    user user_password_key 
   CONSTRAINT     W   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_password_key UNIQUE (password);
 B   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_password_key;
       public         postgres    false    192    192                       2606    16641    user user_phone_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_phone_key UNIQUE (phone);
 ?   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_phone_key;
       public         postgres    false    192    192                       2606    16635    user user_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public         postgres    false    192    192                        2606    16691    basket basket_order_id_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.basket
    ADD CONSTRAINT basket_order_id_fkey FOREIGN KEY (order_id) REFERENCES public."order"(id);
 E   ALTER TABLE ONLY public.basket DROP CONSTRAINT basket_order_id_fkey;
       public       postgres    false    2075    196    197            !           2606    16696    basket basket_product_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.basket
    ADD CONSTRAINT basket_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.product(id);
 G   ALTER TABLE ONLY public.basket DROP CONSTRAINT basket_product_id_fkey;
       public       postgres    false    197    194    2073                       2606    16683    order order_status_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_status_id_fkey FOREIGN KEY (status_id) REFERENCES public.order_status(id);
 F   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_status_id_fkey;
       public       postgres    false    2055    196    188                       2606    16678    order order_user_id_fkey    FK CONSTRAINT     z   ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(id);
 D   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_user_id_fkey;
       public       postgres    false    192    196    2069                       2606    16664     product product_category_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.product_category(id);
 J   ALTER TABLE ONLY public.product DROP CONSTRAINT product_category_id_fkey;
       public       postgres    false    194    2059    190                       2606    16644    user user_role_id_fkey    FK CONSTRAINT     v   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(id);
 B   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_role_id_fkey;
       public       postgres    false    192    186    2051            �   %   x�3��2�4b#$��M���6�bS�x� ���      �   (   x�3�4�420��50�54R00�#NC.#NC21z\\\ �	_      �   6   x�3�0����.쿰���@�^.#��/l����֋;.N
��qqq ��=      �   H  x�uRMkQ]���kM��|-G��AbKҖn����NR��qUA��D�2I��_��/�K<��,�!������sN@qT��ݨPk{|�o{�S܈�{���V]|��%�*7fbFf I�͙�efYo�,KI�H���J�8Eju�Xv)�V��wrL��iW*ۦ�Qy%3ɴ�p;a���\��L�/�)0PfFwY� ?2�����+�Dqo�������ܒ�F�6ye7P�b�������n�2�L�_a�w���|aGY�VV0o�dK3v�T���'҃��W�:YŪ����|�U���h����`����
����d(1C�M�w�'�M���!W�|��\����O8d4J������4�s���v_Up|��*A�����z�<��5
s���|^f�w�ԡ(ʜ��Q��<h�a������."f])��E��Y�7��r��'23TY���j�A�3�	��<P,�����)�̾M�Ww�Us��%��ۙ�`���E�ڑ�F�&s,sw�������Y9�3��/����u���%��W�y���,�C.��>�Z���'���l	DK&�Ee��<�v��J�      �   l   x���	�@Dϛ*�@�S�Ŭ�(,xma�o����2�y��aU�W{$m�e�:H�0#jG�q�aq�.+�Tَ��m��p��в�=8��a
6��5���E���N�      �   a   x���� �wU؀F4vc1�OM�[�!	�`{�|6ٝ�*"�LbD��$�j��@��o�-���r_�/�ܲe�]�#�/����ǆ�?4q;f      �   8  x�}��J1�ϳO��L�f��>����mj﵂

+�

��u��}��7r��XA����3�|��>�9_QM+ZӜJE��h!Fͅ�[YT<c!��p8���ut�Z����;�����Y.����jpZђ<U�$������t��tZڷ*��v9�4�>8t�@�R/�"��[�J��T	�Gd��NO�D������7�#��YЌ'\����?44BT	�v��{�~f"`o�m�kxƘ��FM�����kB冷������b�i寵k�r1�w��{�;�f��.�3�K�$��2 �     