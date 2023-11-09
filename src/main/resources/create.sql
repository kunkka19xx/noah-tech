--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-11-09 16:43:34 JST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3695 (class 1262 OID 16534)
-- Name: noah_tech; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE noah_tech WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';


ALTER DATABASE noah_tech OWNER TO postgres;

\connect noah_tech

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3696 (class 0 OID 0)
-- Dependencies: 3695
-- Name: DATABASE noah_tech; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE noah_tech IS 'database for my project';


--
-- TOC entry 3698 (class 0 OID 0)
-- Name: noah_tech; Type: DATABASE PROPERTIES; Schema: -; Owner: postgres
--

ALTER DATABASE noah_tech SET search_path TO 'noah_tech';


\connect noah_tech

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 16535)
-- Name: noah_tech; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA noah_tech;


ALTER SCHEMA noah_tech OWNER TO postgres;

--
-- TOC entry 240 (class 1255 OID 16885)
-- Name: array_contains(character varying[], bytea); Type: FUNCTION; Schema: noah_tech; Owner: postgres
--

CREATE FUNCTION noah_tech.array_contains(arr character varying[], value bytea) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN convert_from(value, 'utf-8') = ANY(arr);
--  RETURN encode(value, 'hex') = ANY(arr);
END;
$$;


ALTER FUNCTION noah_tech.array_contains(arr character varying[], value bytea) OWNER TO postgres;

--
-- TOC entry 239 (class 1255 OID 16886)
-- Name: array_contains(character varying[], character varying); Type: FUNCTION; Schema: noah_tech; Owner: postgres
--

CREATE FUNCTION noah_tech.array_contains(arr character varying[], value character varying) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
BEGIN
--     RETURN convert_from(value, 'utf-8') = ANY(arr);
	 RETURN value = ANY(arr);
--  RETURN encode(value, 'hex') = ANY(arr);
END;
$$;


ALTER FUNCTION noah_tech.array_contains(arr character varying[], value character varying) OWNER TO postgres;

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 16560)
-- Name: category; Type: TABLE; Schema: noah_tech; Owner: postgres
--

CREATE TABLE noah_tech.category (
    name character varying(20) NOT NULL,
    description character varying(120),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    id integer NOT NULL
);


ALTER TABLE noah_tech.category OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16706)
-- Name: category_new_id_seq; Type: SEQUENCE; Schema: noah_tech; Owner: postgres
--

CREATE SEQUENCE noah_tech.category_new_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE noah_tech.category_new_id_seq OWNER TO postgres;

--
-- TOC entry 3699 (class 0 OID 0)
-- Dependencies: 229
-- Name: category_new_id_seq; Type: SEQUENCE OWNED BY; Schema: noah_tech; Owner: postgres
--

ALTER SEQUENCE noah_tech.category_new_id_seq OWNED BY noah_tech.category.id;


--
-- TOC entry 230 (class 1259 OID 16714)
-- Name: comment_id_seq; Type: SEQUENCE; Schema: noah_tech; Owner: postgres
--

CREATE SEQUENCE noah_tech.comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 100000000
    CACHE 1;


ALTER TABLE noah_tech.comment_id_seq OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16584)
-- Name: comment; Type: TABLE; Schema: noah_tech; Owner: postgres
--

CREATE TABLE noah_tech.comment (
    id integer DEFAULT nextval('noah_tech.comment_id_seq'::regclass) NOT NULL,
    post_id integer NOT NULL,
    user_id integer NOT NULL,
    content character varying(500) NOT NULL,
    created_at timestamp with time zone,
    updated_at timestamp with time zone
);


ALTER TABLE noah_tech.comment OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16594)
-- Name: hit_site; Type: TABLE; Schema: noah_tech; Owner: postgres
--

CREATE TABLE noah_tech.hit_site (
    ip character varying(20),
    hit_at timestamp with time zone,
    user_id integer,
    address character varying(50),
    id integer NOT NULL
);


ALTER TABLE noah_tech.hit_site OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16716)
-- Name: hit_site_new_id_seq; Type: SEQUENCE; Schema: noah_tech; Owner: postgres
--

CREATE SEQUENCE noah_tech.hit_site_new_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE noah_tech.hit_site_new_id_seq OWNER TO postgres;

--
-- TOC entry 3700 (class 0 OID 0)
-- Dependencies: 231
-- Name: hit_site_new_id_seq; Type: SEQUENCE OWNED BY; Schema: noah_tech; Owner: postgres
--

ALTER SEQUENCE noah_tech.hit_site_new_id_seq OWNED BY noah_tech.hit_site.id;


--
-- TOC entry 217 (class 1259 OID 16565)
-- Name: like; Type: TABLE; Schema: noah_tech; Owner: postgres
--

CREATE TABLE noah_tech."like" (
    post_id integer,
    user_id integer NOT NULL,
    created_at timestamp with time zone,
    type smallint NOT NULL,
    comment_id integer,
    total integer NOT NULL,
    id integer NOT NULL
);


ALTER TABLE noah_tech."like" OWNER TO postgres;

--
-- TOC entry 3701 (class 0 OID 0)
-- Dependencies: 217
-- Name: TABLE "like"; Type: COMMENT; Schema: noah_tech; Owner: postgres
--

COMMENT ON TABLE noah_tech."like" IS 'manage likes of a post and a user';


--
-- TOC entry 227 (class 1259 OID 16669)
-- Name: like_new_id_seq; Type: SEQUENCE; Schema: noah_tech; Owner: postgres
--

CREATE SEQUENCE noah_tech.like_new_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE noah_tech.like_new_id_seq OWNER TO postgres;

--
-- TOC entry 3702 (class 0 OID 0)
-- Dependencies: 227
-- Name: like_new_id_seq; Type: SEQUENCE OWNED BY; Schema: noah_tech; Owner: postgres
--

ALTER SEQUENCE noah_tech.like_new_id_seq OWNED BY noah_tech."like".id;


--
-- TOC entry 218 (class 1259 OID 16570)
-- Name: message; Type: TABLE; Schema: noah_tech; Owner: postgres
--

CREATE TABLE noah_tech.message (
    user_id integer,
    content character varying(1000) NOT NULL,
    subject character varying(120),
    attach character varying(200) NOT NULL,
    created_at timestamp with time zone,
    email character varying(30) NOT NULL,
    phone character varying(12),
    id integer NOT NULL
);


ALTER TABLE noah_tech.message OWNER TO postgres;

--
-- TOC entry 3703 (class 0 OID 0)
-- Dependencies: 218
-- Name: TABLE message; Type: COMMENT; Schema: noah_tech; Owner: postgres
--

COMMENT ON TABLE noah_tech.message IS 'message from user to admin';


--
-- TOC entry 232 (class 1259 OID 16722)
-- Name: message_new_id_seq; Type: SEQUENCE; Schema: noah_tech; Owner: postgres
--

CREATE SEQUENCE noah_tech.message_new_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE noah_tech.message_new_id_seq OWNER TO postgres;

--
-- TOC entry 3704 (class 0 OID 0)
-- Dependencies: 232
-- Name: message_new_id_seq; Type: SEQUENCE OWNED BY; Schema: noah_tech; Owner: postgres
--

ALTER SEQUENCE noah_tech.message_new_id_seq OWNED BY noah_tech.message.id;


--
-- TOC entry 225 (class 1259 OID 16613)
-- Name: notification; Type: TABLE; Schema: noah_tech; Owner: postgres
--

CREATE TABLE noah_tech.notification (
    post_id integer NOT NULL,
    user_id integer NOT NULL,
    status smallint,
    content character varying(150),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    id integer NOT NULL
);


ALTER TABLE noah_tech.notification OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 16730)
-- Name: notification_new_id_seq; Type: SEQUENCE; Schema: noah_tech; Owner: postgres
--

CREATE SEQUENCE noah_tech.notification_new_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE noah_tech.notification_new_id_seq OWNER TO postgres;

--
-- TOC entry 3705 (class 0 OID 0)
-- Dependencies: 233
-- Name: notification_new_id_seq; Type: SEQUENCE OWNED BY; Schema: noah_tech; Owner: postgres
--

ALTER SEQUENCE noah_tech.notification_new_id_seq OWNED BY noah_tech.notification.id;


--
-- TOC entry 219 (class 1259 OID 16577)
-- Name: part_of_post; Type: TABLE; Schema: noah_tech; Owner: postgres
--

CREATE TABLE noah_tech.part_of_post (
    post_id integer NOT NULL,
    index smallint NOT NULL,
    content character varying(1000),
    image character varying(1000),
    created_at timestamp with time zone DEFAULT now(),
    updated_at timestamp with time zone,
    id integer NOT NULL
);


ALTER TABLE noah_tech.part_of_post OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16696)
-- Name: part_of_post_new_id_seq; Type: SEQUENCE; Schema: noah_tech; Owner: postgres
--

CREATE SEQUENCE noah_tech.part_of_post_new_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE noah_tech.part_of_post_new_id_seq OWNER TO postgres;

--
-- TOC entry 3706 (class 0 OID 0)
-- Dependencies: 228
-- Name: part_of_post_new_id_seq; Type: SEQUENCE OWNED BY; Schema: noah_tech; Owner: postgres
--

ALTER SEQUENCE noah_tech.part_of_post_new_id_seq OWNED BY noah_tech.part_of_post.id;


--
-- TOC entry 234 (class 1259 OID 16743)
-- Name: post_id_seq; Type: SEQUENCE; Schema: noah_tech; Owner: postgres
--

CREATE SEQUENCE noah_tech.post_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 100000000
    CACHE 1;


ALTER TABLE noah_tech.post_id_seq OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16542)
-- Name: post; Type: TABLE; Schema: noah_tech; Owner: postgres
--

CREATE TABLE noah_tech.post (
    id integer DEFAULT nextval('noah_tech.post_id_seq'::regclass) NOT NULL,
    title character varying(120) NOT NULL,
    description character varying(120),
    view_number integer,
    user_id integer NOT NULL,
    created_at timestamp(3) with time zone,
    updated_at timestamp with time zone,
    categories character varying[],
    tags character varying[],
    length integer
);


ALTER TABLE noah_tech.post OWNER TO postgres;

--
-- TOC entry 3707 (class 0 OID 0)
-- Dependencies: 214
-- Name: TABLE post; Type: COMMENT; Schema: noah_tech; Owner: postgres
--

COMMENT ON TABLE noah_tech.post IS 'Post content management';


--
-- TOC entry 221 (class 1259 OID 16591)
-- Name: search; Type: TABLE; Schema: noah_tech; Owner: postgres
--

CREATE TABLE noah_tech.search (
    user_id integer NOT NULL,
    content character varying(100) NOT NULL,
    created_at timestamp with time zone
);


ALTER TABLE noah_tech.search OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16606)
-- Name: service; Type: TABLE; Schema: noah_tech; Owner: postgres
--

CREATE TABLE noah_tech.service (
    name character varying(30) NOT NULL,
    image character varying(200),
    description character varying(300),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    id integer NOT NULL
);


ALTER TABLE noah_tech.service OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 16750)
-- Name: service_new_id_seq; Type: SEQUENCE; Schema: noah_tech; Owner: postgres
--

CREATE SEQUENCE noah_tech.service_new_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE noah_tech.service_new_id_seq OWNER TO postgres;

--
-- TOC entry 3708 (class 0 OID 0)
-- Dependencies: 235
-- Name: service_new_id_seq; Type: SEQUENCE OWNED BY; Schema: noah_tech; Owner: postgres
--

ALTER SEQUENCE noah_tech.service_new_id_seq OWNED BY noah_tech.service.id;


--
-- TOC entry 226 (class 1259 OID 16618)
-- Name: subscribe; Type: TABLE; Schema: noah_tech; Owner: postgres
--

CREATE TABLE noah_tech.subscribe (
    user_id integer NOT NULL,
    author_id integer,
    type smallint,
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    id integer NOT NULL
);


ALTER TABLE noah_tech.subscribe OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 16761)
-- Name: subscribe_new_id_seq; Type: SEQUENCE; Schema: noah_tech; Owner: postgres
--

CREATE SEQUENCE noah_tech.subscribe_new_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE noah_tech.subscribe_new_id_seq OWNER TO postgres;

--
-- TOC entry 3709 (class 0 OID 0)
-- Dependencies: 236
-- Name: subscribe_new_id_seq; Type: SEQUENCE OWNED BY; Schema: noah_tech; Owner: postgres
--

ALTER SEQUENCE noah_tech.subscribe_new_id_seq OWNED BY noah_tech.subscribe.id;


--
-- TOC entry 223 (class 1259 OID 16599)
-- Name: tag; Type: TABLE; Schema: noah_tech; Owner: postgres
--

CREATE TABLE noah_tech.tag (
    name character varying(20) NOT NULL,
    created_at timestamp with time zone,
    id integer NOT NULL
);


ALTER TABLE noah_tech.tag OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 16769)
-- Name: tag_new_id_seq; Type: SEQUENCE; Schema: noah_tech; Owner: postgres
--

CREATE SEQUENCE noah_tech.tag_new_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE noah_tech.tag_new_id_seq OWNER TO postgres;

--
-- TOC entry 3710 (class 0 OID 0)
-- Dependencies: 237
-- Name: tag_new_id_seq; Type: SEQUENCE OWNED BY; Schema: noah_tech; Owner: postgres
--

ALTER SEQUENCE noah_tech.tag_new_id_seq OWNED BY noah_tech.tag.id;


--
-- TOC entry 238 (class 1259 OID 16776)
-- Name: user_id_seq; Type: SEQUENCE; Schema: noah_tech; Owner: postgres
--

CREATE SEQUENCE noah_tech.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 100000000
    CACHE 1;


ALTER TABLE noah_tech.user_id_seq OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16547)
-- Name: user; Type: TABLE; Schema: noah_tech; Owner: postgres
--

CREATE TABLE noah_tech."user" (
    id integer DEFAULT nextval('noah_tech.user_id_seq'::regclass) NOT NULL,
    username character varying(64) NOT NULL,
    name character varying(64),
    description character varying(128),
    email character varying(128) NOT NULL,
    phone character varying(12),
    birthdate date,
    roles character varying(120)[] NOT NULL,
    password character varying(256),
    token character varying(64),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    address character varying(100)
);


ALTER TABLE noah_tech."user" OWNER TO postgres;

--
-- TOC entry 3711 (class 0 OID 0)
-- Dependencies: 215
-- Name: TABLE "user"; Type: COMMENT; Schema: noah_tech; Owner: postgres
--

COMMENT ON TABLE noah_tech."user" IS 'User table for system';


--
-- TOC entry 3502 (class 2604 OID 16707)
-- Name: category id; Type: DEFAULT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.category ALTER COLUMN id SET DEFAULT nextval('noah_tech.category_new_id_seq'::regclass);


--
-- TOC entry 3508 (class 2604 OID 16717)
-- Name: hit_site id; Type: DEFAULT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.hit_site ALTER COLUMN id SET DEFAULT nextval('noah_tech.hit_site_new_id_seq'::regclass);


--
-- TOC entry 3503 (class 2604 OID 16670)
-- Name: like id; Type: DEFAULT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech."like" ALTER COLUMN id SET DEFAULT nextval('noah_tech.like_new_id_seq'::regclass);


--
-- TOC entry 3504 (class 2604 OID 16723)
-- Name: message id; Type: DEFAULT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.message ALTER COLUMN id SET DEFAULT nextval('noah_tech.message_new_id_seq'::regclass);


--
-- TOC entry 3511 (class 2604 OID 16731)
-- Name: notification id; Type: DEFAULT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.notification ALTER COLUMN id SET DEFAULT nextval('noah_tech.notification_new_id_seq'::regclass);


--
-- TOC entry 3506 (class 2604 OID 16697)
-- Name: part_of_post id; Type: DEFAULT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.part_of_post ALTER COLUMN id SET DEFAULT nextval('noah_tech.part_of_post_new_id_seq'::regclass);


--
-- TOC entry 3510 (class 2604 OID 16751)
-- Name: service id; Type: DEFAULT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.service ALTER COLUMN id SET DEFAULT nextval('noah_tech.service_new_id_seq'::regclass);


--
-- TOC entry 3512 (class 2604 OID 16762)
-- Name: subscribe id; Type: DEFAULT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.subscribe ALTER COLUMN id SET DEFAULT nextval('noah_tech.subscribe_new_id_seq'::regclass);


--
-- TOC entry 3509 (class 2604 OID 16770)
-- Name: tag id; Type: DEFAULT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.tag ALTER COLUMN id SET DEFAULT nextval('noah_tech.tag_new_id_seq'::regclass);


--
-- TOC entry 3521 (class 2606 OID 16713)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 3529 (class 2606 OID 16590)
-- Name: comment comment_pkey; Type: CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- TOC entry 3531 (class 2606 OID 16737)
-- Name: hit_site hit_site_pkey; Type: CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.hit_site
    ADD CONSTRAINT hit_site_pkey PRIMARY KEY (id);


--
-- TOC entry 3523 (class 2606 OID 16677)
-- Name: like like_pkey; Type: CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech."like"
    ADD CONSTRAINT like_pkey PRIMARY KEY (id);


--
-- TOC entry 3525 (class 2606 OID 16739)
-- Name: message message_pkey; Type: CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (id);


--
-- TOC entry 3537 (class 2606 OID 16741)
-- Name: notification notification_pkey; Type: CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.notification
    ADD CONSTRAINT notification_pkey PRIMARY KEY (id);


--
-- TOC entry 3527 (class 2606 OID 16705)
-- Name: part_of_post part_of_post_pkey; Type: CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.part_of_post
    ADD CONSTRAINT part_of_post_pkey PRIMARY KEY (id);


--
-- TOC entry 3515 (class 2606 OID 16546)
-- Name: post post_pkey; Type: CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (id);


--
-- TOC entry 3535 (class 2606 OID 16759)
-- Name: service service_pkey; Type: CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.service
    ADD CONSTRAINT service_pkey PRIMARY KEY (id);


--
-- TOC entry 3539 (class 2606 OID 16768)
-- Name: subscribe subscribe_pkey; Type: CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.subscribe
    ADD CONSTRAINT subscribe_pkey PRIMARY KEY (id);


--
-- TOC entry 3533 (class 2606 OID 16775)
-- Name: tag tag_pkey; Type: CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);


--
-- TOC entry 3517 (class 2606 OID 16605)
-- Name: user user_name; Type: CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech."user"
    ADD CONSTRAINT user_name UNIQUE (username);


--
-- TOC entry 3519 (class 2606 OID 16553)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 3513 (class 1259 OID 16559)
-- Name: fki_user_fkey; Type: INDEX; Schema: noah_tech; Owner: postgres
--

CREATE INDEX fki_user_fkey ON noah_tech.post USING btree (user_id);


--
-- TOC entry 3542 (class 2606 OID 16638)
-- Name: part_of_post part_post_fk; Type: FK CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.part_of_post
    ADD CONSTRAINT part_post_fk FOREIGN KEY (post_id) REFERENCES noah_tech.post(id) NOT VALID;


--
-- TOC entry 3543 (class 2606 OID 16628)
-- Name: comment post_comment_fk; Type: FK CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.comment
    ADD CONSTRAINT post_comment_fk FOREIGN KEY (post_id) REFERENCES noah_tech.post(id) NOT VALID;


--
-- TOC entry 3545 (class 2606 OID 16653)
-- Name: notification post_noti_fkey; Type: FK CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.notification
    ADD CONSTRAINT post_noti_fkey FOREIGN KEY (post_id) REFERENCES noah_tech.post(id) NOT VALID;


--
-- TOC entry 3544 (class 2606 OID 16623)
-- Name: comment user_comment_fkey; Type: FK CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.comment
    ADD CONSTRAINT user_comment_fkey FOREIGN KEY (user_id) REFERENCES noah_tech."user"(id) NOT VALID;


--
-- TOC entry 3540 (class 2606 OID 16554)
-- Name: post user_fkey; Type: FK CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.post
    ADD CONSTRAINT user_fkey FOREIGN KEY (user_id) REFERENCES noah_tech."user"(id) NOT VALID;


--
-- TOC entry 3541 (class 2606 OID 16633)
-- Name: like user_like_fkey; Type: FK CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech."like"
    ADD CONSTRAINT user_like_fkey FOREIGN KEY (user_id) REFERENCES noah_tech."user"(id) NOT VALID;


--
-- TOC entry 3546 (class 2606 OID 16648)
-- Name: notification user_noti_fkey; Type: FK CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.notification
    ADD CONSTRAINT user_noti_fkey FOREIGN KEY (user_id) REFERENCES noah_tech."user"(id) NOT VALID;


--
-- TOC entry 3547 (class 2606 OID 16643)
-- Name: subscribe user_sub_fkey; Type: FK CONSTRAINT; Schema: noah_tech; Owner: postgres
--

ALTER TABLE ONLY noah_tech.subscribe
    ADD CONSTRAINT user_sub_fkey FOREIGN KEY (user_id) REFERENCES noah_tech."user"(id) NOT VALID;


--
-- TOC entry 3697 (class 0 OID 0)
-- Dependencies: 3695
-- Name: DATABASE noah_tech; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON DATABASE noah_tech TO admin;


-- Completed on 2023-11-09 16:43:34 JST

--
-- PostgreSQL database dump complete
--

