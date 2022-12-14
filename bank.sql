PGDMP     4                	    z           bank    14.2    14.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            	           1262    27378    bank    DATABASE     c   CREATE DATABASE bank WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Estonian_Estonia.1252';
    DROP DATABASE bank;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                postgres    false            
           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   postgres    false    3            ?            1259    27421    transactions    TABLE     ?   CREATE TABLE public.transactions (
    id integer NOT NULL,
    balance_id integer,
    t_date timestamp without time zone NOT NULL,
    t_type integer NOT NULL,
    amount numeric NOT NULL,
    t_method character varying NOT NULL
);
     DROP TABLE public.transactions;
       public         heap    postgres    false    3            ?            1259    27420    transactions_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.transactions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.transactions_id_seq;
       public          postgres    false    214    3                       0    0    transactions_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.transactions_id_seq OWNED BY public.transactions.id;
          public          postgres    false    213            ?            1259    27407    user_balance    TABLE     q   CREATE TABLE public.user_balance (
    id integer NOT NULL,
    user_id integer,
    balance numeric NOT NULL
);
     DROP TABLE public.user_balance;
       public         heap    postgres    false    3            ?            1259    27406    user_balace_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.user_balace_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.user_balace_id_seq;
       public          postgres    false    3    212                       0    0    user_balace_id_seq    SEQUENCE OWNED BY     J   ALTER SEQUENCE public.user_balace_id_seq OWNED BY public.user_balance.id;
          public          postgres    false    211            ?            1259    27387    users    TABLE     ?   CREATE TABLE public.users (
    id integer NOT NULL,
    firstname character varying NOT NULL,
    lastname character varying NOT NULL,
    email character varying NOT NULL,
    address character varying NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false    3            ?            1259    27386    users_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    210    3                       0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    209            h           2604    27424    transactions id    DEFAULT     r   ALTER TABLE ONLY public.transactions ALTER COLUMN id SET DEFAULT nextval('public.transactions_id_seq'::regclass);
 >   ALTER TABLE public.transactions ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    213    214    214            g           2604    27410    user_balance id    DEFAULT     q   ALTER TABLE ONLY public.user_balance ALTER COLUMN id SET DEFAULT nextval('public.user_balace_id_seq'::regclass);
 >   ALTER TABLE public.user_balance ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    211    212    212            f           2604    27390    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    209    210    210                      0    27421    transactions 
   TABLE DATA           X   COPY public.transactions (id, balance_id, t_date, t_type, amount, t_method) FROM stdin;
    public          postgres    false    214                     0    27407    user_balance 
   TABLE DATA           <   COPY public.user_balance (id, user_id, balance) FROM stdin;
    public          postgres    false    212   ?        ?          0    27387    users 
   TABLE DATA           H   COPY public.users (id, firstname, lastname, email, address) FROM stdin;
    public          postgres    false    210   	!                  0    0    transactions_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.transactions_id_seq', 114, true);
          public          postgres    false    213                       0    0    user_balace_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.user_balace_id_seq', 16, true);
          public          postgres    false    211                       0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 3, true);
          public          postgres    false    209            p           2606    27428    transactions transactions_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.transactions DROP CONSTRAINT transactions_pkey;
       public            postgres    false    214            n           2606    27414    user_balance user_balace_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.user_balance
    ADD CONSTRAINT user_balace_pkey PRIMARY KEY (id);
 G   ALTER TABLE ONLY public.user_balance DROP CONSTRAINT user_balace_pkey;
       public            postgres    false    212            j           2606    27396    users users_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public            postgres    false    210            l           2606    27394    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    210            r           2606    27429 )   transactions transactions_balance_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_balance_id_fkey FOREIGN KEY (balance_id) REFERENCES public.user_balance(id);
 S   ALTER TABLE ONLY public.transactions DROP CONSTRAINT transactions_balance_id_fkey;
       public          postgres    false    212    3182    214            q           2606    27415 %   user_balance user_balace_user_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.user_balance
    ADD CONSTRAINT user_balace_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 O   ALTER TABLE ONLY public.user_balance DROP CONSTRAINT user_balace_user_id_fkey;
       public          postgres    false    3180    212    210               ?   x?m?1?0??9E/??۱?&[??(C????A)-???????LJ??ekD??jՀ?* ??<?eװU?*?P????~??B?
??SYC????ϧq?L??/jZ????????\S?H?]?|??2M?q???k`N[??,??G(?????m???????#??H>?         0   x??? 0?7?Z?????h?(?ّc??W?1ꜱ??$????      ?   ?   x?U???0@??W??j??????K??4?]s`?^???{?˫??<!	¦?s?a??L?????2w???f?j?:;G?*?';?8?Y??^C??7;?ʃt?Z2ӊ?Z*<???ˀ>?	?ҹ/??&?2wk?? ??8Q     