PGDMP         .                z           bank    14.2    14.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    27378    bank    DATABASE     c   CREATE DATABASE bank WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Estonian_Estonia.1252';
    DROP DATABASE bank;
                postgres    false            �            1259    27407    user_balance    TABLE     q   CREATE TABLE public.user_balance (
    id integer NOT NULL,
    user_id integer,
    balance numeric NOT NULL
);
     DROP TABLE public.user_balance;
       public         heap    postgres    false            �            1259    27406    user_balace_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_balace_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.user_balace_id_seq;
       public          postgres    false    212            �           0    0    user_balace_id_seq    SEQUENCE OWNED BY     J   ALTER SEQUENCE public.user_balace_id_seq OWNED BY public.user_balance.id;
          public          postgres    false    211            �            1259    27387    users    TABLE     �   CREATE TABLE public.users (
    id integer NOT NULL,
    firstname character varying NOT NULL,
    lastname character varying NOT NULL,
    email character varying NOT NULL,
    address character varying NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    27386    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    210                        0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    209            b           2604    27410    user_balance id    DEFAULT     q   ALTER TABLE ONLY public.user_balance ALTER COLUMN id SET DEFAULT nextval('public.user_balace_id_seq'::regclass);
 >   ALTER TABLE public.user_balance ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    211    212    212            a           2604    27390    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    209    210    210            �          0    27407    user_balance 
   TABLE DATA           <   COPY public.user_balance (id, user_id, balance) FROM stdin;
    public          postgres    false    212   �       �          0    27387    users 
   TABLE DATA           H   COPY public.users (id, firstname, lastname, email, address) FROM stdin;
    public          postgres    false    210                     0    0    user_balace_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.user_balace_id_seq', 6, true);
          public          postgres    false    211                       0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 3, true);
          public          postgres    false    209            h           2606    27414    user_balance user_balace_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.user_balance
    ADD CONSTRAINT user_balace_pkey PRIMARY KEY (id);
 G   ALTER TABLE ONLY public.user_balance DROP CONSTRAINT user_balace_pkey;
       public            postgres    false    212            d           2606    27396    users users_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public            postgres    false    210            f           2606    27394    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    210            i           2606    27415 %   user_balance user_balace_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_balance
    ADD CONSTRAINT user_balace_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 O   ALTER TABLE ONLY public.user_balance DROP CONSTRAINT user_balace_user_id_fkey;
       public          postgres    false    210    212    3174            �   /   x���  �7C�{{��:�ix��D
�IV�,Y[>���\�      �   �   x�U���0@��W��j������K��4�]s`�^��{�˫��<!	¦�s�a��L�����2w���f�j�:;G�*�';�8�Y��^C��7;�ʃt�Z2ӊ�Z*<���ˀ>�	�ҹ/��&�2wk�� ��8Q     