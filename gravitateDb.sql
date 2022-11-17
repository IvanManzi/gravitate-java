--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: additional_point; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.additional_point (
    additional_point_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    points integer,
    quarter character varying(255),
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    user_id bigint NOT NULL,
    user_blog_id bigint NOT NULL,
    user_skill_id bigint NOT NULL,
    user_topic_id bigint NOT NULL,
    admin_id bigint NOT NULL
);


ALTER TABLE public.additional_point OWNER TO postgres;

--
-- Name: additional_point_additional_point_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.additional_point_additional_point_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.additional_point_additional_point_id_seq OWNER TO postgres;

--
-- Name: additional_point_additional_point_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.additional_point_additional_point_id_seq OWNED BY public.additional_point.additional_point_id;


--
-- Name: additional_point_admin_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.additional_point_admin_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.additional_point_admin_id_seq OWNER TO postgres;

--
-- Name: additional_point_admin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.additional_point_admin_id_seq OWNED BY public.additional_point.admin_id;


--
-- Name: additional_point_user_blog_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.additional_point_user_blog_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.additional_point_user_blog_id_seq OWNER TO postgres;

--
-- Name: additional_point_user_blog_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.additional_point_user_blog_id_seq OWNED BY public.additional_point.user_blog_id;


--
-- Name: additional_point_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.additional_point_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.additional_point_user_id_seq OWNER TO postgres;

--
-- Name: additional_point_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.additional_point_user_id_seq OWNED BY public.additional_point.user_id;


--
-- Name: additional_point_user_skill_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.additional_point_user_skill_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.additional_point_user_skill_id_seq OWNER TO postgres;

--
-- Name: additional_point_user_skill_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.additional_point_user_skill_id_seq OWNED BY public.additional_point.user_skill_id;


--
-- Name: additional_point_user_topic_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.additional_point_user_topic_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.additional_point_user_topic_id_seq OWNER TO postgres;

--
-- Name: additional_point_user_topic_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.additional_point_user_topic_id_seq OWNED BY public.additional_point.user_topic_id;


--
-- Name: app_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.app_user (
    user_id bigint NOT NULL,
    user_type character varying(255),
    account_number character varying(255),
    password character varying(255),
    alternate_email character varying(255),
    bank_name character varying(255),
    contract_path character varying(255),
    country character varying(255),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    date_of_birth timestamp without time zone,
    joining_date timestamp without time zone,
    email character varying(255),
    employment_status character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    phone_number character varying(255),
    profile_picture_path character varying(255),
    responsibility character varying(255),
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    role_id bigint DEFAULT null,
    billing character varying(255),
    is_admin integer DEFAULT 0
);


ALTER TABLE public.app_user OWNER TO postgres;

--
-- Name: app_user_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.app_user_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_role_id_seq OWNER TO postgres;

--
-- Name: app_user_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.app_user_role_id_seq OWNED BY public.app_user.role_id;


--
-- Name: app_user_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.app_user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_user_id_seq OWNER TO postgres;

--
-- Name: app_user_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.app_user_user_id_seq OWNED BY public.app_user.user_id;


--
-- Name: blog_reply; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.blog_reply (
    blog_reply_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    reply character varying(255) NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    user_id bigint NOT NULL
);


ALTER TABLE public.blog_reply OWNER TO postgres;

--
-- Name: blog_reply_blog_reply_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.blog_reply_blog_reply_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.blog_reply_blog_reply_id_seq OWNER TO postgres;

--
-- Name: blog_reply_blog_reply_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.blog_reply_blog_reply_id_seq OWNED BY public.blog_reply.blog_reply_id;


--
-- Name: blog_reply_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.blog_reply_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.blog_reply_user_id_seq OWNER TO postgres;

--
-- Name: blog_reply_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.blog_reply_user_id_seq OWNED BY public.blog_reply.user_id;


--
-- Name: performance_evaluation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.performance_evaluation (
    role_performance_evaluation_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    criteria character varying(255),
    description character varying(255),
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    role_id bigint NOT NULL,
    admin_id bigint NOT NULL
);


ALTER TABLE public.performance_evaluation OWNER TO postgres;

--
-- Name: performance_evaluation_admin_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.performance_evaluation_admin_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.performance_evaluation_admin_id_seq OWNER TO postgres;

--
-- Name: performance_evaluation_admin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.performance_evaluation_admin_id_seq OWNED BY public.performance_evaluation.admin_id;


--
-- Name: performance_evaluation_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.performance_evaluation_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.performance_evaluation_role_id_seq OWNER TO postgres;

--
-- Name: performance_evaluation_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.performance_evaluation_role_id_seq OWNED BY public.performance_evaluation.role_id;


--
-- Name: performance_evaluation_role_performance_evaluation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.performance_evaluation_role_performance_evaluation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.performance_evaluation_role_performance_evaluation_id_seq OWNER TO postgres;

--
-- Name: performance_evaluation_role_performance_evaluation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.performance_evaluation_role_performance_evaluation_id_seq OWNED BY public.performance_evaluation.role_performance_evaluation_id;


--
-- Name: policy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.policy (
    policy_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    description character varying(255) NOT NULL,
    policy_file_path character varying(255) NOT NULL,
    policy_name character varying(255) NOT NULL,
    policy_type character varying(255) NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    admin_id bigint NOT NULL
);


ALTER TABLE public.policy OWNER TO postgres;

--
-- Name: policy_admin_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.policy_admin_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.policy_admin_id_seq OWNER TO postgres;

--
-- Name: policy_admin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.policy_admin_id_seq OWNED BY public.policy.admin_id;


--
-- Name: policy_policy_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.policy_policy_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.policy_policy_id_seq OWNER TO postgres;

--
-- Name: policy_policy_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.policy_policy_id_seq OWNED BY public.policy.policy_id;


--
-- Name: project; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.project (
    project_id bigint NOT NULL,
    client_email character varying(255) NOT NULL,
    client_name character varying(255) NOT NULL,
    phone_number character varying(255) NOT NULL,
    project_description character varying(255) NOT NULL,
    project_name character varying(255) NOT NULL,
    admin_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.project OWNER TO postgres;

--
-- Name: project_admin_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.project_admin_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.project_admin_id_seq OWNER TO postgres;

--
-- Name: project_admin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.project_admin_id_seq OWNED BY public.project.admin_id;


--
-- Name: project_project_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.project_project_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.project_project_id_seq OWNER TO postgres;

--
-- Name: project_project_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.project_project_id_seq OWNED BY public.project.project_id;


--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    role_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    rolekra character varying(255) NOT NULL,
    role_name character varying(255) NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    admin_id bigint NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- Name: role_admin_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_admin_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_admin_id_seq OWNER TO postgres;

--
-- Name: role_admin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_admin_id_seq OWNED BY public.role.admin_id;


--
-- Name: role_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_role_id_seq OWNER TO postgres;

--
-- Name: role_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_role_id_seq OWNED BY public.role.role_id;


--
-- Name: topic_reply; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topic_reply (
    topic_reply_id bigint NOT NULL,
    comment character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    user_id bigint NOT NULL,
    user_topic_id bigint NOT NULL
);


ALTER TABLE public.topic_reply OWNER TO postgres;

--
-- Name: topic_reply_topic_reply_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.topic_reply_topic_reply_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.topic_reply_topic_reply_id_seq OWNER TO postgres;

--
-- Name: topic_reply_topic_reply_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.topic_reply_topic_reply_id_seq OWNED BY public.topic_reply.topic_reply_id;


--
-- Name: topic_reply_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.topic_reply_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.topic_reply_user_id_seq OWNER TO postgres;

--
-- Name: topic_reply_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.topic_reply_user_id_seq OWNED BY public.topic_reply.user_id;


--
-- Name: topic_reply_user_topic_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.topic_reply_user_topic_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.topic_reply_user_topic_id_seq OWNER TO postgres;

--
-- Name: topic_reply_user_topic_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.topic_reply_user_topic_id_seq OWNED BY public.topic_reply.user_topic_id;


--
-- Name: user_blog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_blog (
    user_blog_id bigint NOT NULL,
    content character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    title character varying(255) NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    user_id bigint NOT NULL
);


ALTER TABLE public.user_blog OWNER TO postgres;

--
-- Name: user_blog_user_blog_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_blog_user_blog_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_blog_user_blog_id_seq OWNER TO postgres;

--
-- Name: user_blog_user_blog_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_blog_user_blog_id_seq OWNED BY public.user_blog.user_blog_id;


--
-- Name: user_blog_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_blog_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_blog_user_id_seq OWNER TO postgres;

--
-- Name: user_blog_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_blog_user_id_seq OWNED BY public.user_blog.user_id;


--
-- Name: user_manager; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_manager (
    user_manager_id bigint NOT NULL,
    admin_id bigint NOT NULL,
    user_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.user_manager OWNER TO postgres;

--
-- Name: user_manager_manager_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_manager_manager_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_manager_manager_id_seq OWNER TO postgres;

--
-- Name: user_manager_manager_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_manager_manager_id_seq OWNED BY public.user_manager.admin_id;


--
-- Name: user_manager_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_manager_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_manager_user_id_seq OWNER TO postgres;

--
-- Name: user_manager_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_manager_user_id_seq OWNED BY public.user_manager.user_id;


--
-- Name: user_manager_user_manager_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_manager_user_manager_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_manager_user_manager_id_seq OWNER TO postgres;

--
-- Name: user_manager_user_manager_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_manager_user_manager_id_seq OWNED BY public.user_manager.user_manager_id;


--
-- Name: user_skill; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_skill (
    user_skill_id bigint NOT NULL,
    category character varying(255) NOT NULL,
    certificate_path character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    expertise character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    user_id bigint NOT NULL
);


ALTER TABLE public.user_skill OWNER TO postgres;

--
-- Name: user_skill_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_skill_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_skill_user_id_seq OWNER TO postgres;

--
-- Name: user_skill_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_skill_user_id_seq OWNED BY public.user_skill.user_id;


--
-- Name: user_skill_user_skill_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_skill_user_skill_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_skill_user_skill_id_seq OWNER TO postgres;

--
-- Name: user_skill_user_skill_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_skill_user_skill_id_seq OWNED BY public.user_skill.user_skill_id;


--
-- Name: user_suggestion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_suggestion (
    user_suggestion_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    suggestion character varying(255) NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    user_id bigint NOT NULL
);


ALTER TABLE public.user_suggestion OWNER TO postgres;

--
-- Name: user_suggestion_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_suggestion_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_suggestion_user_id_seq OWNER TO postgres;

--
-- Name: user_suggestion_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_suggestion_user_id_seq OWNED BY public.user_suggestion.user_id;


--
-- Name: user_suggestion_user_suggestion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_suggestion_user_suggestion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_suggestion_user_suggestion_id_seq OWNER TO postgres;

--
-- Name: user_suggestion_user_suggestion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_suggestion_user_suggestion_id_seq OWNED BY public.user_suggestion.user_suggestion_id;


--
-- Name: user_topic; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_topic (
    user_topic_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    problem_description character varying(255) NOT NULL,
    tags character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    user_id bigint NOT NULL
);


ALTER TABLE public.user_topic OWNER TO postgres;

--
-- Name: user_topic_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_topic_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_topic_user_id_seq OWNER TO postgres;

--
-- Name: user_topic_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_topic_user_id_seq OWNED BY public.user_topic.user_id;


--
-- Name: user_topic_user_topic_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_topic_user_topic_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_topic_user_topic_id_seq OWNER TO postgres;

--
-- Name: user_topic_user_topic_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_topic_user_topic_id_seq OWNED BY public.user_topic.user_topic_id;


--
-- Name: user_wish; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_wish (
    user_wish_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    message character varying(255) NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    wish_id bigint NOT NULL
);


ALTER TABLE public.user_wish OWNER TO postgres;

--
-- Name: user_wish_user_wish_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_wish_user_wish_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_wish_user_wish_id_seq OWNER TO postgres;

--
-- Name: user_wish_user_wish_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_wish_user_wish_id_seq OWNED BY public.user_wish.user_wish_id;


--
-- Name: user_wish_wish_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_wish_wish_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_wish_wish_id_seq OWNER TO postgres;

--
-- Name: user_wish_wish_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_wish_wish_id_seq OWNED BY public.user_wish.wish_id;


--
-- Name: wish; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wish (
    wish_id bigint NOT NULL,
    comment character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    wish_type character varying(255) NOT NULL,
    user_id bigint NOT NULL,
    admin_id bigint NOT NULL
);


ALTER TABLE public.wish OWNER TO postgres;

--
-- Name: wish_admin_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.wish_admin_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wish_admin_id_seq OWNER TO postgres;

--
-- Name: wish_admin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.wish_admin_id_seq OWNED BY public.wish.admin_id;


--
-- Name: wish_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.wish_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wish_user_id_seq OWNER TO postgres;

--
-- Name: wish_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.wish_user_id_seq OWNED BY public.wish.user_id;


--
-- Name: wish_wish_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.wish_wish_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wish_wish_id_seq OWNER TO postgres;

--
-- Name: wish_wish_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.wish_wish_id_seq OWNED BY public.wish.wish_id;


--
-- Name: additional_point additional_point_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point ALTER COLUMN additional_point_id SET DEFAULT nextval('public.additional_point_additional_point_id_seq'::regclass);


--
-- Name: additional_point user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point ALTER COLUMN user_id SET DEFAULT nextval('public.additional_point_user_id_seq'::regclass);


--
-- Name: additional_point user_blog_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point ALTER COLUMN user_blog_id SET DEFAULT nextval('public.additional_point_user_blog_id_seq'::regclass);


--
-- Name: additional_point user_skill_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point ALTER COLUMN user_skill_id SET DEFAULT nextval('public.additional_point_user_skill_id_seq'::regclass);


--
-- Name: additional_point user_topic_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point ALTER COLUMN user_topic_id SET DEFAULT nextval('public.additional_point_user_topic_id_seq'::regclass);


--
-- Name: additional_point admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point ALTER COLUMN admin_id SET DEFAULT nextval('public.additional_point_admin_id_seq'::regclass);


--
-- Name: app_user user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_user ALTER COLUMN user_id SET DEFAULT nextval('public.app_user_user_id_seq'::regclass);


--
-- Name: app_user role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_user ALTER COLUMN role_id SET DEFAULT nextval('public.app_user_role_id_seq'::regclass);


--
-- Name: blog_reply blog_reply_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog_reply ALTER COLUMN blog_reply_id SET DEFAULT nextval('public.blog_reply_blog_reply_id_seq'::regclass);


--
-- Name: blog_reply user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog_reply ALTER COLUMN user_id SET DEFAULT nextval('public.blog_reply_user_id_seq'::regclass);


--
-- Name: performance_evaluation role_performance_evaluation_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_evaluation ALTER COLUMN role_performance_evaluation_id SET DEFAULT nextval('public.performance_evaluation_role_performance_evaluation_id_seq'::regclass);


--
-- Name: performance_evaluation role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_evaluation ALTER COLUMN role_id SET DEFAULT nextval('public.performance_evaluation_role_id_seq'::regclass);


--
-- Name: performance_evaluation admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_evaluation ALTER COLUMN admin_id SET DEFAULT nextval('public.performance_evaluation_admin_id_seq'::regclass);


--
-- Name: policy policy_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.policy ALTER COLUMN policy_id SET DEFAULT nextval('public.policy_policy_id_seq'::regclass);


--
-- Name: policy admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.policy ALTER COLUMN admin_id SET DEFAULT nextval('public.policy_admin_id_seq'::regclass);


--
-- Name: project project_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project ALTER COLUMN project_id SET DEFAULT nextval('public.project_project_id_seq'::regclass);


--
-- Name: project admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project ALTER COLUMN admin_id SET DEFAULT nextval('public.project_admin_id_seq'::regclass);


--
-- Name: role role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN role_id SET DEFAULT nextval('public.role_role_id_seq'::regclass);


--
-- Name: role admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN admin_id SET DEFAULT nextval('public.role_admin_id_seq'::regclass);


--
-- Name: topic_reply topic_reply_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic_reply ALTER COLUMN topic_reply_id SET DEFAULT nextval('public.topic_reply_topic_reply_id_seq'::regclass);


--
-- Name: topic_reply user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic_reply ALTER COLUMN user_id SET DEFAULT nextval('public.topic_reply_user_id_seq'::regclass);


--
-- Name: topic_reply user_topic_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic_reply ALTER COLUMN user_topic_id SET DEFAULT nextval('public.topic_reply_user_topic_id_seq'::regclass);


--
-- Name: user_blog user_blog_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_blog ALTER COLUMN user_blog_id SET DEFAULT nextval('public.user_blog_user_blog_id_seq'::regclass);


--
-- Name: user_blog user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_blog ALTER COLUMN user_id SET DEFAULT nextval('public.user_blog_user_id_seq'::regclass);


--
-- Name: user_manager user_manager_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_manager ALTER COLUMN user_manager_id SET DEFAULT nextval('public.user_manager_user_manager_id_seq'::regclass);


--
-- Name: user_manager admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_manager ALTER COLUMN admin_id SET DEFAULT nextval('public.user_manager_manager_id_seq'::regclass);


--
-- Name: user_manager user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_manager ALTER COLUMN user_id SET DEFAULT nextval('public.user_manager_user_id_seq'::regclass);


--
-- Name: user_skill user_skill_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_skill ALTER COLUMN user_skill_id SET DEFAULT nextval('public.user_skill_user_skill_id_seq'::regclass);


--
-- Name: user_skill user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_skill ALTER COLUMN user_id SET DEFAULT nextval('public.user_skill_user_id_seq'::regclass);


--
-- Name: user_suggestion user_suggestion_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_suggestion ALTER COLUMN user_suggestion_id SET DEFAULT nextval('public.user_suggestion_user_suggestion_id_seq'::regclass);


--
-- Name: user_suggestion user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_suggestion ALTER COLUMN user_id SET DEFAULT nextval('public.user_suggestion_user_id_seq'::regclass);


--
-- Name: user_topic user_topic_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_topic ALTER COLUMN user_topic_id SET DEFAULT nextval('public.user_topic_user_topic_id_seq'::regclass);


--
-- Name: user_topic user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_topic ALTER COLUMN user_id SET DEFAULT nextval('public.user_topic_user_id_seq'::regclass);


--
-- Name: user_wish user_wish_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_wish ALTER COLUMN user_wish_id SET DEFAULT nextval('public.user_wish_user_wish_id_seq'::regclass);


--
-- Name: user_wish wish_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_wish ALTER COLUMN wish_id SET DEFAULT nextval('public.user_wish_wish_id_seq'::regclass);


--
-- Name: wish wish_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wish ALTER COLUMN wish_id SET DEFAULT nextval('public.wish_wish_id_seq'::regclass);


--
-- Name: wish user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wish ALTER COLUMN user_id SET DEFAULT nextval('public.wish_user_id_seq'::regclass);


--
-- Name: wish admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wish ALTER COLUMN admin_id SET DEFAULT nextval('public.wish_admin_id_seq'::regclass);


--
-- Name: additional_point additional_point_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point
    ADD CONSTRAINT additional_point_pkey PRIMARY KEY (additional_point_id);


--
-- Name: app_user app_user_alternate_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_alternate_email_key UNIQUE (alternate_email);


--
-- Name: app_user app_user_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_email_key UNIQUE (email);


--
-- Name: app_user app_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_pkey PRIMARY KEY (user_id);


--
-- Name: blog_reply blog_reply_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog_reply
    ADD CONSTRAINT blog_reply_pkey PRIMARY KEY (blog_reply_id);


--
-- Name: performance_evaluation performance_evaluation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_evaluation
    ADD CONSTRAINT performance_evaluation_pkey PRIMARY KEY (role_performance_evaluation_id);


--
-- Name: policy policy_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.policy
    ADD CONSTRAINT policy_pkey PRIMARY KEY (policy_id);


--
-- Name: project project_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_pkey PRIMARY KEY (project_id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);


--
-- Name: topic_reply topic_reply_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic_reply
    ADD CONSTRAINT topic_reply_pkey PRIMARY KEY (topic_reply_id);


--
-- Name: user_blog user_blog_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_blog
    ADD CONSTRAINT user_blog_pkey PRIMARY KEY (user_blog_id);


--
-- Name: user_manager user_manager_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_manager
    ADD CONSTRAINT user_manager_pkey PRIMARY KEY (user_manager_id);


--
-- Name: user_skill user_skill_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_skill
    ADD CONSTRAINT user_skill_pkey PRIMARY KEY (user_skill_id);


--
-- Name: user_suggestion user_suggestion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_suggestion
    ADD CONSTRAINT user_suggestion_pkey PRIMARY KEY (user_suggestion_id);


--
-- Name: user_topic user_topic_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_topic
    ADD CONSTRAINT user_topic_pkey PRIMARY KEY (user_topic_id);


--
-- Name: user_wish user_wish_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_wish
    ADD CONSTRAINT user_wish_pkey PRIMARY KEY (user_wish_id);


--
-- Name: wish wish_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wish
    ADD CONSTRAINT wish_pkey PRIMARY KEY (wish_id);


--
-- Name: additional_point additional_point_fk5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point
    ADD CONSTRAINT additional_point_fk5 FOREIGN KEY (admin_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: topic_reply fk1eans8ed7rjpavcwfoejqppv4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic_reply
    ADD CONSTRAINT fk1eans8ed7rjpavcwfoejqppv4 FOREIGN KEY (topic_reply_id) REFERENCES public.topic_reply(topic_reply_id);


--
-- Name: blog_reply fk1f8v4y5vo31t01gf3pgvbu7r9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog_reply
    ADD CONSTRAINT fk1f8v4y5vo31t01gf3pgvbu7r9 FOREIGN KEY (blog_reply_id) REFERENCES public.blog_reply(blog_reply_id);


--
-- Name: user_suggestion fk3yrehjh7si57cab7h8r5dxg43; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_suggestion
    ADD CONSTRAINT fk3yrehjh7si57cab7h8r5dxg43 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: user_topic fk435jqawocr69e749x6hux5exe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_topic
    ADD CONSTRAINT fk435jqawocr69e749x6hux5exe FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: app_user fk49hx9nj6onfot1fxtonj986ab; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT fk49hx9nj6onfot1fxtonj986ab FOREIGN KEY (role_id) REFERENCES public.role(role_id) ON DELETE SET NULL;


--
-- Name: performance_evaluation fk56l9ero0msbedtn2dfyxdf925; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_evaluation
    ADD CONSTRAINT fk56l9ero0msbedtn2dfyxdf925 FOREIGN KEY (role_id) REFERENCES public.role(role_id) ON DELETE CASCADE;


--
-- Name: user_wish fk6jaq2u20edu7luol40791em16; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_wish
    ADD CONSTRAINT fk6jaq2u20edu7luol40791em16 FOREIGN KEY (wish_id) REFERENCES public.wish(wish_id) ON DELETE CASCADE;


--
-- Name: additional_point fk9bshydwwn2vy8ceai4qjgus1p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point
    ADD CONSTRAINT fk9bshydwwn2vy8ceai4qjgus1p FOREIGN KEY (user_skill_id) REFERENCES public.user_skill(user_skill_id) ON DELETE CASCADE;


--
-- Name: user_skill fkc7vxgf2rjsuoxuclctcgoicgy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_skill
    ADD CONSTRAINT fkc7vxgf2rjsuoxuclctcgoicgy FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: user_blog fkf9vrdukekl9prmxwy8clbhgil; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_blog
    ADD CONSTRAINT fkf9vrdukekl9prmxwy8clbhgil FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: additional_point fkfabxvm8xxvump3vkexjvggfjn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point
    ADD CONSTRAINT fkfabxvm8xxvump3vkexjvggfjn FOREIGN KEY (user_topic_id) REFERENCES public.user_topic(user_topic_id) ON DELETE CASCADE;


--
-- Name: wish fkfvn0c4y0fnsk1h6ll95s164om; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wish
    ADD CONSTRAINT fkfvn0c4y0fnsk1h6ll95s164om FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: additional_point fkhm9okps0el9o162wwljp2xapx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point
    ADD CONSTRAINT fkhm9okps0el9o162wwljp2xapx FOREIGN KEY (user_blog_id) REFERENCES public.user_blog(user_blog_id) ON DELETE CASCADE;


--
-- Name: blog_reply fkocw8sh8oe15m7ybic88plc2jc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog_reply
    ADD CONSTRAINT fkocw8sh8oe15m7ybic88plc2jc FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: topic_reply fkp7eo1bgo0dugkh5cfs2gml8wi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic_reply
    ADD CONSTRAINT fkp7eo1bgo0dugkh5cfs2gml8wi FOREIGN KEY (user_topic_id) REFERENCES public.user_topic(user_topic_id) ON DELETE CASCADE;


--
-- Name: additional_point fkrwbjh4p12ohkwwh5d5oqixwd1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point
    ADD CONSTRAINT fkrwbjh4p12ohkwwh5d5oqixwd1 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: topic_reply fktoi6eifjvf4ej2en5ttkeqjei; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic_reply
    ADD CONSTRAINT fktoi6eifjvf4ej2en5ttkeqjei FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: performance_evaluation performance_evaluation_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_evaluation
    ADD CONSTRAINT performance_evaluation_fk2 FOREIGN KEY (admin_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: policy policy_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.policy
    ADD CONSTRAINT policy_fk1 FOREIGN KEY (admin_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: project project_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_fk1 FOREIGN KEY (admin_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: role role_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_fk1 FOREIGN KEY (admin_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: user_manager user_manager_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_manager
    ADD CONSTRAINT user_manager_fk1 FOREIGN KEY (admin_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: user_manager user_manager_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_manager
    ADD CONSTRAINT user_manager_fk2 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: wish wish_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wish
    ADD CONSTRAINT wish_fk2 FOREIGN KEY (admin_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

