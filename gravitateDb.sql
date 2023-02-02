--
-- PostgreSQL database dump
--

-- Dumped from database version 10.22 (Ubuntu 10.22-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.22 (Ubuntu 10.22-0ubuntu0.18.04.1)

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
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: additional_point; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.additional_point (
    additional_point_id bigint NOT NULL,
    user_id bigint NOT NULL,
    admin_id bigint NOT NULL,
    blog_id bigint,
    forum_answer_id bigint,
    user_skill_id bigint,
    comment character varying NOT NULL,
    quarter integer NOT NULL,
    category character varying(255) NOT NULL,
    points integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
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
-- Name: additional_point_blog_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.additional_point_blog_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.additional_point_blog_id_seq OWNER TO postgres;

--
-- Name: additional_point_blog_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.additional_point_blog_id_seq OWNED BY public.additional_point.blog_id;


--
-- Name: additional_point_forum_answer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.additional_point_forum_answer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.additional_point_forum_answer_id_seq OWNER TO postgres;

--
-- Name: additional_point_forum_answer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.additional_point_forum_answer_id_seq OWNED BY public.additional_point.forum_answer_id;


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
    role_id bigint,
    billing character varying(255),
    managed_by bigint,
    status boolean DEFAULT true,
    user_level integer DEFAULT 4,
    is_account_non_locked boolean DEFAULT true,
    is_account_non_expired boolean DEFAULT true,
    jira_id character varying,
    admin_page_access text[],
    otp character varying NOT NULL
);


ALTER TABLE public.app_user OWNER TO postgres;

--
-- Name: COLUMN app_user.user_level; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.app_user.user_level IS '1 for admin,2 for manager, 3 for developer, 4 for client';


--
-- Name: COLUMN app_user.is_account_non_locked; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.app_user.is_account_non_locked IS 'true for non locked account, false for locked';


--
-- Name: COLUMN app_user.is_account_non_expired; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.app_user.is_account_non_expired IS 'true for non expired account, false for expired account';


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
-- Name: blog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.blog (
    blog_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    problem_description character varying NOT NULL,
    tags character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    user_id bigint NOT NULL,
    topic_thumbnail character varying,
    is_awarded boolean DEFAULT false
);


ALTER TABLE public.blog OWNER TO postgres;

--
-- Name: blog_reply; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.blog_reply (
    blog_reply_id bigint NOT NULL,
    comment character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    user_id bigint NOT NULL,
    blog_id bigint NOT NULL,
    parent bigint
);


ALTER TABLE public.blog_reply OWNER TO postgres;

--
-- Name: blog_reply_parent_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.blog_reply_parent_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.blog_reply_parent_seq OWNER TO postgres;

--
-- Name: blog_reply_parent_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.blog_reply_parent_seq OWNED BY public.blog_reply.parent;


--
-- Name: client_referral; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client_referral (
    client_referral_id bigint NOT NULL,
    referral_status integer DEFAULT 77 NOT NULL,
    is_referencable character varying(255) NOT NULL,
    referred_by bigint NOT NULL,
    organisation_name character varying(255) NOT NULL,
    client_name character varying(255) NOT NULL,
    client_email character varying(255) NOT NULL,
    client_description character varying(255) NOT NULL,
    project_details character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    phone_number character varying NOT NULL
);


ALTER TABLE public.client_referral OWNER TO postgres;

--
-- Name: client_referral_client_referral_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_referral_client_referral_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_referral_client_referral_id_seq OWNER TO postgres;

--
-- Name: client_referral_client_referral_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.client_referral_client_referral_id_seq OWNED BY public.client_referral.client_referral_id;


--
-- Name: client_referral_referred_by_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_referral_referred_by_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_referral_referred_by_seq OWNER TO postgres;

--
-- Name: client_referral_referred_by_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.client_referral_referred_by_seq OWNED BY public.client_referral.referred_by;


--
-- Name: discussion_forum; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.discussion_forum (
    discussion_forum_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    description character varying NOT NULL,
    tags character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    user_id bigint NOT NULL,
    views integer DEFAULT 0
);


ALTER TABLE public.discussion_forum OWNER TO postgres;

--
-- Name: discussion_forum_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.discussion_forum_answer (
    forum_answer_id bigint NOT NULL,
    forum_id bigint NOT NULL,
    user_id bigint NOT NULL,
    parent bigint,
    comment character varying NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    is_accepted boolean DEFAULT false,
    is_awarded boolean DEFAULT false
);


ALTER TABLE public.discussion_forum_answer OWNER TO postgres;

--
-- Name: discussion_forum_answer_forum_answer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.discussion_forum_answer_forum_answer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.discussion_forum_answer_forum_answer_id_seq OWNER TO postgres;

--
-- Name: discussion_forum_answer_forum_answer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.discussion_forum_answer_forum_answer_id_seq OWNED BY public.discussion_forum_answer.forum_answer_id;


--
-- Name: discussion_forum_answer_forum_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.discussion_forum_answer_forum_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.discussion_forum_answer_forum_id_seq OWNER TO postgres;

--
-- Name: discussion_forum_answer_forum_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.discussion_forum_answer_forum_id_seq OWNED BY public.discussion_forum_answer.forum_id;


--
-- Name: discussion_forum_answer_parent_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.discussion_forum_answer_parent_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.discussion_forum_answer_parent_seq OWNER TO postgres;

--
-- Name: discussion_forum_answer_parent_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.discussion_forum_answer_parent_seq OWNED BY public.discussion_forum_answer.parent;


--
-- Name: discussion_forum_answer_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.discussion_forum_answer_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.discussion_forum_answer_user_id_seq OWNER TO postgres;

--
-- Name: discussion_forum_answer_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.discussion_forum_answer_user_id_seq OWNED BY public.discussion_forum_answer.user_id;


--
-- Name: discussion_forum_discussion_forum_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.discussion_forum_discussion_forum_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.discussion_forum_discussion_forum_id_seq OWNER TO postgres;

--
-- Name: discussion_forum_discussion_forum_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.discussion_forum_discussion_forum_id_seq OWNED BY public.discussion_forum.discussion_forum_id;


--
-- Name: discussion_forum_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.discussion_forum_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.discussion_forum_user_id_seq OWNER TO postgres;

--
-- Name: discussion_forum_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.discussion_forum_user_id_seq OWNED BY public.discussion_forum.user_id;


--
-- Name: forum_answer_up_vote; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.forum_answer_up_vote (
    up_vote_id bigint NOT NULL,
    forum_answer_id bigint NOT NULL,
    user_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.forum_answer_up_vote OWNER TO postgres;

--
-- Name: forum_answer_up_vote_forum_answer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.forum_answer_up_vote_forum_answer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.forum_answer_up_vote_forum_answer_id_seq OWNER TO postgres;

--
-- Name: forum_answer_up_vote_forum_answer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.forum_answer_up_vote_forum_answer_id_seq OWNED BY public.forum_answer_up_vote.forum_answer_id;


--
-- Name: forum_answer_up_vote_up_vote_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.forum_answer_up_vote_up_vote_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.forum_answer_up_vote_up_vote_id_seq OWNER TO postgres;

--
-- Name: forum_answer_up_vote_up_vote_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.forum_answer_up_vote_up_vote_id_seq OWNED BY public.forum_answer_up_vote.up_vote_id;


--
-- Name: forum_answer_up_vote_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.forum_answer_up_vote_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.forum_answer_up_vote_user_id_seq OWNER TO postgres;

--
-- Name: forum_answer_up_vote_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.forum_answer_up_vote_user_id_seq OWNED BY public.forum_answer_up_vote.user_id;


--
-- Name: job; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.job (
    job_id bigint NOT NULL,
    data bytea NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.job OWNER TO postgres;

--
-- Name: job_job_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.job_job_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.job_job_id_seq OWNER TO postgres;

--
-- Name: job_job_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.job_job_id_seq OWNED BY public.job.job_id;


--
-- Name: payment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payment (
    payement_id bigint NOT NULL,
    user_id bigint NOT NULL,
    total_amount numeric NOT NULL,
    project_id bigint NOT NULL,
    month integer NOT NULL,
    year integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.payment OWNER TO postgres;

--
-- Name: payment_payement_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.payment_payement_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.payment_payement_id_seq OWNER TO postgres;

--
-- Name: payment_payement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.payment_payement_id_seq OWNED BY public.payment.payement_id;


--
-- Name: payment_project_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.payment_project_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.payment_project_id_seq OWNER TO postgres;

--
-- Name: payment_project_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.payment_project_id_seq OWNED BY public.payment.project_id;


--
-- Name: payment_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.payment_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.payment_user_id_seq OWNER TO postgres;

--
-- Name: payment_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.payment_user_id_seq OWNED BY public.payment.user_id;


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
-- Name: performance_evaluation_criteria_score; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.performance_evaluation_criteria_score (
    performance_evaluation_criteria_score_id bigint NOT NULL,
    performance_evaluation_id bigint NOT NULL,
    admin_id bigint NOT NULL,
    user_id bigint NOT NULL,
    quarter integer NOT NULL,
    sprint integer NOT NULL,
    year integer NOT NULL,
    points integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.performance_evaluation_criteria_score OWNER TO postgres;

--
-- Name: performance_evaluation_criter_performance_evaluation_criter_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.performance_evaluation_criter_performance_evaluation_criter_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.performance_evaluation_criter_performance_evaluation_criter_seq OWNER TO postgres;

--
-- Name: performance_evaluation_criter_performance_evaluation_criter_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.performance_evaluation_criter_performance_evaluation_criter_seq OWNED BY public.performance_evaluation_criteria_score.performance_evaluation_criteria_score_id;


--
-- Name: performance_evaluation_criteria_s_performance_evaluation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.performance_evaluation_criteria_s_performance_evaluation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.performance_evaluation_criteria_s_performance_evaluation_id_seq OWNER TO postgres;

--
-- Name: performance_evaluation_criteria_s_performance_evaluation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.performance_evaluation_criteria_s_performance_evaluation_id_seq OWNED BY public.performance_evaluation_criteria_score.performance_evaluation_id;


--
-- Name: performance_evaluation_criteria_score_admin_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.performance_evaluation_criteria_score_admin_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.performance_evaluation_criteria_score_admin_id_seq OWNER TO postgres;

--
-- Name: performance_evaluation_criteria_score_admin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.performance_evaluation_criteria_score_admin_id_seq OWNED BY public.performance_evaluation_criteria_score.admin_id;


--
-- Name: performance_evaluation_criteria_score_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.performance_evaluation_criteria_score_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.performance_evaluation_criteria_score_user_id_seq OWNER TO postgres;

--
-- Name: performance_evaluation_criteria_score_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.performance_evaluation_criteria_score_user_id_seq OWNED BY public.performance_evaluation_criteria_score.user_id;


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
-- Name: performance_feedback; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.performance_feedback (
    performance_feedback_id bigint NOT NULL,
    done_by bigint NOT NULL,
    user_id bigint NOT NULL,
    year integer NOT NULL,
    quarter integer,
    sprint integer,
    feedback character varying NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.performance_feedback OWNER TO postgres;

--
-- Name: performance_feedback_done_by_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.performance_feedback_done_by_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.performance_feedback_done_by_seq OWNER TO postgres;

--
-- Name: performance_feedback_done_by_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.performance_feedback_done_by_seq OWNED BY public.performance_feedback.done_by;


--
-- Name: performance_feedback_performance_feedback_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.performance_feedback_performance_feedback_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.performance_feedback_performance_feedback_id_seq OWNER TO postgres;

--
-- Name: performance_feedback_performance_feedback_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.performance_feedback_performance_feedback_id_seq OWNED BY public.performance_feedback.performance_feedback_id;


--
-- Name: performance_feedback_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.performance_feedback_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.performance_feedback_user_id_seq OWNER TO postgres;

--
-- Name: performance_feedback_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.performance_feedback_user_id_seq OWNED BY public.performance_feedback.user_id;


--
-- Name: policy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.policy (
    policy_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
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
-- Name: position; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."position" (
    position_id bigint NOT NULL,
    admin_id bigint NOT NULL,
    project_id bigint,
    is_position_open boolean DEFAULT true NOT NULL,
    job_id character varying NOT NULL,
    role_id bigint NOT NULL,
    experience character varying NOT NULL,
    key_skills character varying NOT NULL,
    referral_amount numeric,
    opportunity_type character varying NOT NULL,
    position_type character varying,
    kra character varying,
    incentive_amount numeric,
    points integer,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public."position" OWNER TO postgres;

--
-- Name: position_admin_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.position_admin_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.position_admin_id_seq OWNER TO postgres;

--
-- Name: position_admin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.position_admin_id_seq OWNED BY public."position".admin_id;


--
-- Name: position_position_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.position_position_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.position_position_id_seq OWNER TO postgres;

--
-- Name: position_position_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.position_position_id_seq OWNED BY public."position".position_id;


--
-- Name: position_project_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.position_project_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.position_project_id_seq OWNER TO postgres;

--
-- Name: position_project_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.position_project_id_seq OWNED BY public."position".project_id;


--
-- Name: position_referral; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.position_referral (
    position_referral_id bigint NOT NULL,
    position_id bigint NOT NULL,
    referral_status integer DEFAULT 77 NOT NULL,
    referred_by bigint NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    key_skills character varying(255) NOT NULL,
    phone_number character varying(255) NOT NULL,
    country character varying(255) NOT NULL,
    email_id character varying(255) NOT NULL,
    cv_path character varying NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.position_referral OWNER TO postgres;

--
-- Name: position_referral_position_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.position_referral_position_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.position_referral_position_id_seq OWNER TO postgres;

--
-- Name: position_referral_position_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.position_referral_position_id_seq OWNED BY public.position_referral.position_id;


--
-- Name: position_referral_position_referral_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.position_referral_position_referral_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.position_referral_position_referral_id_seq OWNER TO postgres;

--
-- Name: position_referral_position_referral_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.position_referral_position_referral_id_seq OWNED BY public.position_referral.position_referral_id;


--
-- Name: position_referral_referred_by_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.position_referral_referred_by_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.position_referral_referred_by_seq OWNER TO postgres;

--
-- Name: position_referral_referred_by_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.position_referral_referred_by_seq OWNED BY public.position_referral.referred_by;


--
-- Name: position_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.position_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.position_role_id_seq OWNER TO postgres;

--
-- Name: position_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.position_role_id_seq OWNED BY public."position".role_id;


--
-- Name: position_self_referral; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.position_self_referral (
    position_self_referral_id bigint NOT NULL,
    position_id bigint NOT NULL,
    referral_status integer DEFAULT 77 NOT NULL,
    user_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.position_self_referral OWNER TO postgres;

--
-- Name: position_self_referral_position_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.position_self_referral_position_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.position_self_referral_position_id_seq OWNER TO postgres;

--
-- Name: position_self_referral_position_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.position_self_referral_position_id_seq OWNED BY public.position_self_referral.position_id;


--
-- Name: position_self_referral_position_self_referral_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.position_self_referral_position_self_referral_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.position_self_referral_position_self_referral_id_seq OWNER TO postgres;

--
-- Name: position_self_referral_position_self_referral_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.position_self_referral_position_self_referral_id_seq OWNED BY public.position_self_referral.position_self_referral_id;


--
-- Name: position_self_referral_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.position_self_referral_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.position_self_referral_user_id_seq OWNER TO postgres;

--
-- Name: position_self_referral_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.position_self_referral_user_id_seq OWNED BY public.position_self_referral.user_id;


--
-- Name: project; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.project (
    project_id bigint NOT NULL,
    client_email character varying(255) NOT NULL,
    client_name character varying(255) NOT NULL,
    phone_number character varying(255) NOT NULL,
    project_description character varying NOT NULL,
    project_name character varying(255) NOT NULL,
    admin_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    jira_project_key character varying NOT NULL,
    status integer DEFAULT 1 NOT NULL,
    project_lead bigint NOT NULL,
    technologies character varying NOT NULL,
    is_favorite boolean DEFAULT false,
    start_date timestamp without time zone NOT NULL,
    phase integer DEFAULT 0,
    lead_jira_account_id character varying NOT NULL
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
-- Name: project_incentive; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.project_incentive (
    project_incentive_id bigint NOT NULL,
    user_id bigint NOT NULL,
    admin_id bigint NOT NULL,
    project_id bigint NOT NULL,
    status boolean DEFAULT false NOT NULL,
    performance_bonus numeric DEFAULT 0.0 NOT NULL,
    client_referral numeric DEFAULT 0.0 NOT NULL,
    employee_referral numeric DEFAULT 0.0 NOT NULL,
    hot_opportunity numeric DEFAULT 0.0 NOT NULL,
    total_amount numeric DEFAULT 0.0 NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    month integer NOT NULL
);


ALTER TABLE public.project_incentive OWNER TO postgres;

--
-- Name: project_incentive_admin_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.project_incentive_admin_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.project_incentive_admin_id_seq OWNER TO postgres;

--
-- Name: project_incentive_admin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.project_incentive_admin_id_seq OWNED BY public.project_incentive.admin_id;


--
-- Name: project_incentive_project_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.project_incentive_project_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.project_incentive_project_id_seq OWNER TO postgres;

--
-- Name: project_incentive_project_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.project_incentive_project_id_seq OWNED BY public.project_incentive.project_id;


--
-- Name: project_incentive_project_incentive_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.project_incentive_project_incentive_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.project_incentive_project_incentive_id_seq OWNER TO postgres;

--
-- Name: project_incentive_project_incentive_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.project_incentive_project_incentive_id_seq OWNED BY public.project_incentive.project_incentive_id;


--
-- Name: project_incentive_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.project_incentive_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.project_incentive_user_id_seq OWNER TO postgres;

--
-- Name: project_incentive_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.project_incentive_user_id_seq OWNED BY public.project_incentive.user_id;


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
-- Name: project_project_lead_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.project_project_lead_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.project_project_lead_seq OWNER TO postgres;

--
-- Name: project_project_lead_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.project_project_lead_seq OWNED BY public.project.project_lead;


--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    role_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    rolekra character varying NOT NULL,
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
-- Name: security_question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.security_question (
    security_question_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    answer character varying(255) NOT NULL,
    question character varying NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    user_id bigint NOT NULL
);


ALTER TABLE public.security_question OWNER TO postgres;

--
-- Name: security_question_security_question_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.security_question_security_question_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.security_question_security_question_id_seq OWNER TO postgres;

--
-- Name: security_question_security_question_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.security_question_security_question_id_seq OWNED BY public.security_question.security_question_id;


--
-- Name: task_report; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.task_report (
    task_report_id bigint NOT NULL,
    user_id bigint NOT NULL,
    project_id bigint NOT NULL,
    start_date timestamp without time zone NOT NULL,
    jira_project_id character varying NOT NULL,
    jira_task_id character varying NOT NULL,
    task_name character varying NOT NULL,
    hours_spent integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    is_paid boolean DEFAULT false,
    is_archived boolean DEFAULT false
);


ALTER TABLE public.task_report OWNER TO postgres;

--
-- Name: task_report_project_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.task_report_project_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.task_report_project_id_seq OWNER TO postgres;

--
-- Name: task_report_project_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.task_report_project_id_seq OWNED BY public.task_report.project_id;


--
-- Name: task_report_task_report_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.task_report_task_report_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.task_report_task_report_id_seq OWNER TO postgres;

--
-- Name: task_report_task_report_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.task_report_task_report_id_seq OWNED BY public.task_report.task_report_id;


--
-- Name: task_report_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.task_report_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.task_report_user_id_seq OWNER TO postgres;

--
-- Name: task_report_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.task_report_user_id_seq OWNED BY public.task_report.user_id;


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

ALTER SEQUENCE public.topic_reply_topic_reply_id_seq OWNED BY public.blog_reply.blog_reply_id;


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

ALTER SEQUENCE public.topic_reply_user_id_seq OWNED BY public.blog_reply.user_id;


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

ALTER SEQUENCE public.topic_reply_user_topic_id_seq OWNED BY public.blog_reply.blog_id;


--
-- Name: user_project; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_project (
    user_project_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    user_id bigint NOT NULL,
    project_id bigint NOT NULL,
    is_favorite boolean DEFAULT false
);


ALTER TABLE public.user_project OWNER TO postgres;

--
-- Name: user_project_project_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_project_project_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_project_project_id_seq OWNER TO postgres;

--
-- Name: user_project_project_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_project_project_id_seq OWNED BY public.user_project.project_id;


--
-- Name: user_project_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_project_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_project_user_id_seq OWNER TO postgres;

--
-- Name: user_project_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_project_user_id_seq OWNED BY public.user_project.user_id;


--
-- Name: user_project_user_project_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_project_user_project_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_project_user_project_id_seq OWNER TO postgres;

--
-- Name: user_project_user_project_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_project_user_project_id_seq OWNED BY public.user_project.user_project_id;


--
-- Name: user_skill; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_skill (
    user_skill_id bigint NOT NULL,
    category character varying(255) NOT NULL,
    certificate_path character varying(255),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    expertise character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    user_id bigint NOT NULL,
    is_awarded boolean DEFAULT false
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

ALTER SEQUENCE public.user_topic_user_id_seq OWNED BY public.blog.user_id;


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

ALTER SEQUENCE public.user_topic_user_topic_id_seq OWNED BY public.blog.blog_id;


--
-- Name: wish_reply; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wish_reply (
    wish_reply_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    message character varying(255) NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    wish_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.wish_reply OWNER TO postgres;

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

ALTER SEQUENCE public.user_wish_user_wish_id_seq OWNED BY public.wish_reply.wish_reply_id;


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

ALTER SEQUENCE public.user_wish_wish_id_seq OWNED BY public.wish_reply.wish_id;


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
-- Name: wish_reply_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.wish_reply_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wish_reply_user_id_seq OWNER TO postgres;

--
-- Name: wish_reply_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.wish_reply_user_id_seq OWNED BY public.wish_reply.user_id;


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
-- Name: additional_point admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point ALTER COLUMN admin_id SET DEFAULT nextval('public.additional_point_admin_id_seq'::regclass);


--
-- Name: additional_point blog_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point ALTER COLUMN blog_id SET DEFAULT nextval('public.additional_point_blog_id_seq'::regclass);


--
-- Name: additional_point forum_answer_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point ALTER COLUMN forum_answer_id SET DEFAULT nextval('public.additional_point_forum_answer_id_seq'::regclass);


--
-- Name: additional_point user_skill_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point ALTER COLUMN user_skill_id SET DEFAULT nextval('public.additional_point_user_skill_id_seq'::regclass);


--
-- Name: app_user user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_user ALTER COLUMN user_id SET DEFAULT nextval('public.app_user_user_id_seq'::regclass);


--
-- Name: app_user role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_user ALTER COLUMN role_id SET DEFAULT nextval('public.app_user_role_id_seq'::regclass);


--
-- Name: blog blog_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog ALTER COLUMN blog_id SET DEFAULT nextval('public.user_topic_user_topic_id_seq'::regclass);


--
-- Name: blog user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog ALTER COLUMN user_id SET DEFAULT nextval('public.user_topic_user_id_seq'::regclass);


--
-- Name: blog_reply blog_reply_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog_reply ALTER COLUMN blog_reply_id SET DEFAULT nextval('public.topic_reply_topic_reply_id_seq'::regclass);


--
-- Name: blog_reply user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog_reply ALTER COLUMN user_id SET DEFAULT nextval('public.topic_reply_user_id_seq'::regclass);


--
-- Name: blog_reply blog_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog_reply ALTER COLUMN blog_id SET DEFAULT nextval('public.topic_reply_user_topic_id_seq'::regclass);


--
-- Name: blog_reply parent; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog_reply ALTER COLUMN parent SET DEFAULT nextval('public.blog_reply_parent_seq'::regclass);


--
-- Name: client_referral client_referral_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client_referral ALTER COLUMN client_referral_id SET DEFAULT nextval('public.client_referral_client_referral_id_seq'::regclass);


--
-- Name: client_referral referred_by; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client_referral ALTER COLUMN referred_by SET DEFAULT nextval('public.client_referral_referred_by_seq'::regclass);


--
-- Name: discussion_forum discussion_forum_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discussion_forum ALTER COLUMN discussion_forum_id SET DEFAULT nextval('public.discussion_forum_discussion_forum_id_seq'::regclass);


--
-- Name: discussion_forum user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discussion_forum ALTER COLUMN user_id SET DEFAULT nextval('public.discussion_forum_user_id_seq'::regclass);


--
-- Name: discussion_forum_answer forum_answer_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discussion_forum_answer ALTER COLUMN forum_answer_id SET DEFAULT nextval('public.discussion_forum_answer_forum_answer_id_seq'::regclass);


--
-- Name: discussion_forum_answer forum_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discussion_forum_answer ALTER COLUMN forum_id SET DEFAULT nextval('public.discussion_forum_answer_forum_id_seq'::regclass);


--
-- Name: discussion_forum_answer user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discussion_forum_answer ALTER COLUMN user_id SET DEFAULT nextval('public.discussion_forum_answer_user_id_seq'::regclass);


--
-- Name: discussion_forum_answer parent; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discussion_forum_answer ALTER COLUMN parent SET DEFAULT nextval('public.discussion_forum_answer_parent_seq'::regclass);


--
-- Name: forum_answer_up_vote up_vote_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.forum_answer_up_vote ALTER COLUMN up_vote_id SET DEFAULT nextval('public.forum_answer_up_vote_up_vote_id_seq'::regclass);


--
-- Name: forum_answer_up_vote forum_answer_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.forum_answer_up_vote ALTER COLUMN forum_answer_id SET DEFAULT nextval('public.forum_answer_up_vote_forum_answer_id_seq'::regclass);


--
-- Name: forum_answer_up_vote user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.forum_answer_up_vote ALTER COLUMN user_id SET DEFAULT nextval('public.forum_answer_up_vote_user_id_seq'::regclass);


--
-- Name: job job_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.job ALTER COLUMN job_id SET DEFAULT nextval('public.job_job_id_seq'::regclass);


--
-- Name: payment payement_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment ALTER COLUMN payement_id SET DEFAULT nextval('public.payment_payement_id_seq'::regclass);


--
-- Name: payment user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment ALTER COLUMN user_id SET DEFAULT nextval('public.payment_user_id_seq'::regclass);


--
-- Name: payment project_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment ALTER COLUMN project_id SET DEFAULT nextval('public.payment_project_id_seq'::regclass);


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
-- Name: performance_evaluation_criteria_score performance_evaluation_criteria_score_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_evaluation_criteria_score ALTER COLUMN performance_evaluation_criteria_score_id SET DEFAULT nextval('public.performance_evaluation_criter_performance_evaluation_criter_seq'::regclass);


--
-- Name: performance_evaluation_criteria_score performance_evaluation_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_evaluation_criteria_score ALTER COLUMN performance_evaluation_id SET DEFAULT nextval('public.performance_evaluation_criteria_s_performance_evaluation_id_seq'::regclass);


--
-- Name: performance_evaluation_criteria_score admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_evaluation_criteria_score ALTER COLUMN admin_id SET DEFAULT nextval('public.performance_evaluation_criteria_score_admin_id_seq'::regclass);


--
-- Name: performance_evaluation_criteria_score user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_evaluation_criteria_score ALTER COLUMN user_id SET DEFAULT nextval('public.performance_evaluation_criteria_score_user_id_seq'::regclass);


--
-- Name: performance_feedback performance_feedback_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_feedback ALTER COLUMN performance_feedback_id SET DEFAULT nextval('public.performance_feedback_performance_feedback_id_seq'::regclass);


--
-- Name: performance_feedback done_by; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_feedback ALTER COLUMN done_by SET DEFAULT nextval('public.performance_feedback_done_by_seq'::regclass);


--
-- Name: performance_feedback user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_feedback ALTER COLUMN user_id SET DEFAULT nextval('public.performance_feedback_user_id_seq'::regclass);


--
-- Name: policy policy_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.policy ALTER COLUMN policy_id SET DEFAULT nextval('public.policy_policy_id_seq'::regclass);


--
-- Name: policy admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.policy ALTER COLUMN admin_id SET DEFAULT nextval('public.policy_admin_id_seq'::regclass);


--
-- Name: position position_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position" ALTER COLUMN position_id SET DEFAULT nextval('public.position_position_id_seq'::regclass);


--
-- Name: position admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position" ALTER COLUMN admin_id SET DEFAULT nextval('public.position_admin_id_seq'::regclass);


--
-- Name: position project_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position" ALTER COLUMN project_id SET DEFAULT nextval('public.position_project_id_seq'::regclass);


--
-- Name: position role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position" ALTER COLUMN role_id SET DEFAULT nextval('public.position_role_id_seq'::regclass);


--
-- Name: position_referral position_referral_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.position_referral ALTER COLUMN position_referral_id SET DEFAULT nextval('public.position_referral_position_referral_id_seq'::regclass);


--
-- Name: position_referral position_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.position_referral ALTER COLUMN position_id SET DEFAULT nextval('public.position_referral_position_id_seq'::regclass);


--
-- Name: position_referral referred_by; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.position_referral ALTER COLUMN referred_by SET DEFAULT nextval('public.position_referral_referred_by_seq'::regclass);


--
-- Name: position_self_referral position_self_referral_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.position_self_referral ALTER COLUMN position_self_referral_id SET DEFAULT nextval('public.position_self_referral_position_self_referral_id_seq'::regclass);


--
-- Name: position_self_referral position_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.position_self_referral ALTER COLUMN position_id SET DEFAULT nextval('public.position_self_referral_position_id_seq'::regclass);


--
-- Name: position_self_referral user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.position_self_referral ALTER COLUMN user_id SET DEFAULT nextval('public.position_self_referral_user_id_seq'::regclass);


--
-- Name: project project_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project ALTER COLUMN project_id SET DEFAULT nextval('public.project_project_id_seq'::regclass);


--
-- Name: project admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project ALTER COLUMN admin_id SET DEFAULT nextval('public.project_admin_id_seq'::regclass);


--
-- Name: project project_lead; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project ALTER COLUMN project_lead SET DEFAULT nextval('public.project_project_lead_seq'::regclass);


--
-- Name: project_incentive project_incentive_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project_incentive ALTER COLUMN project_incentive_id SET DEFAULT nextval('public.project_incentive_project_incentive_id_seq'::regclass);


--
-- Name: project_incentive user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project_incentive ALTER COLUMN user_id SET DEFAULT nextval('public.project_incentive_user_id_seq'::regclass);


--
-- Name: project_incentive admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project_incentive ALTER COLUMN admin_id SET DEFAULT nextval('public.project_incentive_admin_id_seq'::regclass);


--
-- Name: project_incentive project_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project_incentive ALTER COLUMN project_id SET DEFAULT nextval('public.project_incentive_project_id_seq'::regclass);


--
-- Name: role role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN role_id SET DEFAULT nextval('public.role_role_id_seq'::regclass);


--
-- Name: role admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN admin_id SET DEFAULT nextval('public.role_admin_id_seq'::regclass);


--
-- Name: security_question security_question_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.security_question ALTER COLUMN security_question_id SET DEFAULT nextval('public.security_question_security_question_id_seq'::regclass);


--
-- Name: task_report task_report_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task_report ALTER COLUMN task_report_id SET DEFAULT nextval('public.task_report_task_report_id_seq'::regclass);


--
-- Name: task_report user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task_report ALTER COLUMN user_id SET DEFAULT nextval('public.task_report_user_id_seq'::regclass);


--
-- Name: task_report project_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task_report ALTER COLUMN project_id SET DEFAULT nextval('public.task_report_project_id_seq'::regclass);


--
-- Name: user_project user_project_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_project ALTER COLUMN user_project_id SET DEFAULT nextval('public.user_project_user_project_id_seq'::regclass);


--
-- Name: user_project user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_project ALTER COLUMN user_id SET DEFAULT nextval('public.user_project_user_id_seq'::regclass);


--
-- Name: user_project project_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_project ALTER COLUMN project_id SET DEFAULT nextval('public.user_project_project_id_seq'::regclass);


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
-- Name: wish_reply wish_reply_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wish_reply ALTER COLUMN wish_reply_id SET DEFAULT nextval('public.user_wish_user_wish_id_seq'::regclass);


--
-- Name: wish_reply wish_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wish_reply ALTER COLUMN wish_id SET DEFAULT nextval('public.user_wish_wish_id_seq'::regclass);


--
-- Name: wish_reply user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wish_reply ALTER COLUMN user_id SET DEFAULT nextval('public.wish_reply_user_id_seq'::regclass);


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
-- Name: blog blog_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog
    ADD CONSTRAINT blog_pkey PRIMARY KEY (blog_id);


--
-- Name: blog_reply blog_reply_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog_reply
    ADD CONSTRAINT blog_reply_pkey PRIMARY KEY (blog_reply_id);


--
-- Name: client_referral client_referral_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client_referral
    ADD CONSTRAINT client_referral_pkey PRIMARY KEY (client_referral_id);


--
-- Name: discussion_forum_answer discussion_forum_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discussion_forum_answer
    ADD CONSTRAINT discussion_forum_answer_pkey PRIMARY KEY (forum_answer_id);


--
-- Name: discussion_forum discussion_forum_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discussion_forum
    ADD CONSTRAINT discussion_forum_pkey PRIMARY KEY (discussion_forum_id);


--
-- Name: forum_answer_up_vote forum_answer_up_vote_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.forum_answer_up_vote
    ADD CONSTRAINT forum_answer_up_vote_pkey PRIMARY KEY (up_vote_id);


--
-- Name: job job_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.job
    ADD CONSTRAINT job_pkey PRIMARY KEY (job_id);


--
-- Name: payment payment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (payement_id);


--
-- Name: performance_evaluation_criteria_score performance_evaluation_criteria_score_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_evaluation_criteria_score
    ADD CONSTRAINT performance_evaluation_criteria_score_pkey PRIMARY KEY (performance_evaluation_criteria_score_id);


--
-- Name: performance_evaluation performance_evaluation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_evaluation
    ADD CONSTRAINT performance_evaluation_pkey PRIMARY KEY (role_performance_evaluation_id);


--
-- Name: performance_feedback performance_feedback_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_feedback
    ADD CONSTRAINT performance_feedback_pkey PRIMARY KEY (performance_feedback_id);


--
-- Name: policy policy_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.policy
    ADD CONSTRAINT policy_pkey PRIMARY KEY (policy_id);


--
-- Name: position position_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position"
    ADD CONSTRAINT position_pkey PRIMARY KEY (position_id);


--
-- Name: position_referral position_referral_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.position_referral
    ADD CONSTRAINT position_referral_pkey PRIMARY KEY (position_referral_id);


--
-- Name: position_self_referral position_self_referral_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.position_self_referral
    ADD CONSTRAINT position_self_referral_pkey PRIMARY KEY (position_self_referral_id);


--
-- Name: project_incentive project_incentive_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project_incentive
    ADD CONSTRAINT project_incentive_pkey PRIMARY KEY (project_incentive_id);


--
-- Name: project project_jira_project_key_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_jira_project_key_key UNIQUE (jira_project_key);


--
-- Name: project project_jira_project_key_key1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_jira_project_key_key1 UNIQUE (jira_project_key);


--
-- Name: project project_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_pkey PRIMARY KEY (project_id);


--
-- Name: project project_project_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_project_name_key UNIQUE (project_name);


--
-- Name: project project_project_name_key1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_project_name_key1 UNIQUE (project_name);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);


--
-- Name: security_question security_question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.security_question
    ADD CONSTRAINT security_question_pkey PRIMARY KEY (security_question_id);


--
-- Name: task_report task_report_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task_report
    ADD CONSTRAINT task_report_pkey PRIMARY KEY (task_report_id);


--
-- Name: user_project user_project_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_project
    ADD CONSTRAINT user_project_pkey PRIMARY KEY (user_project_id);


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
-- Name: wish_reply user_wish_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wish_reply
    ADD CONSTRAINT user_wish_pkey PRIMARY KEY (wish_reply_id);


--
-- Name: wish wish_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wish
    ADD CONSTRAINT wish_pkey PRIMARY KEY (wish_id);


--
-- Name: additional_point additional_point_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point
    ADD CONSTRAINT additional_point_fk1 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: additional_point additional_point_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point
    ADD CONSTRAINT additional_point_fk2 FOREIGN KEY (admin_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: additional_point additional_point_fk3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point
    ADD CONSTRAINT additional_point_fk3 FOREIGN KEY (forum_answer_id) REFERENCES public.discussion_forum_answer(forum_answer_id) ON DELETE CASCADE;


--
-- Name: additional_point additional_point_fk4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point
    ADD CONSTRAINT additional_point_fk4 FOREIGN KEY (blog_id) REFERENCES public.blog(blog_id) ON DELETE CASCADE;


--
-- Name: additional_point additional_point_fk5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_point
    ADD CONSTRAINT additional_point_fk5 FOREIGN KEY (user_skill_id) REFERENCES public.user_skill(user_skill_id) ON DELETE CASCADE;


--
-- Name: app_user app_user_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_fk2 FOREIGN KEY (managed_by) REFERENCES public.app_user(user_id) ON DELETE SET NULL;


--
-- Name: blog_reply blog_reply_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog_reply
    ADD CONSTRAINT blog_reply_fk2 FOREIGN KEY (parent) REFERENCES public.blog_reply(blog_reply_id) ON DELETE CASCADE;


--
-- Name: blog_reply fk1eans8ed7rjpavcwfoejqppv4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog_reply
    ADD CONSTRAINT fk1eans8ed7rjpavcwfoejqppv4 FOREIGN KEY (blog_reply_id) REFERENCES public.blog_reply(blog_reply_id);


--
-- Name: user_suggestion fk3yrehjh7si57cab7h8r5dxg43; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_suggestion
    ADD CONSTRAINT fk3yrehjh7si57cab7h8r5dxg43 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: blog fk435jqawocr69e749x6hux5exe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog
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
-- Name: wish_reply fk6jaq2u20edu7luol40791em16; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wish_reply
    ADD CONSTRAINT fk6jaq2u20edu7luol40791em16 FOREIGN KEY (wish_id) REFERENCES public.wish(wish_id) ON DELETE CASCADE;


--
-- Name: discussion_forum fk_discussion_forum1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discussion_forum
    ADD CONSTRAINT fk_discussion_forum1 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: user_skill fkc7vxgf2rjsuoxuclctcgoicgy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_skill
    ADD CONSTRAINT fkc7vxgf2rjsuoxuclctcgoicgy FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: wish fkfvn0c4y0fnsk1h6ll95s164om; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wish
    ADD CONSTRAINT fkfvn0c4y0fnsk1h6ll95s164om FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: blog_reply fkp7eo1bgo0dugkh5cfs2gml8wi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog_reply
    ADD CONSTRAINT fkp7eo1bgo0dugkh5cfs2gml8wi FOREIGN KEY (blog_id) REFERENCES public.blog(blog_id) ON DELETE CASCADE;


--
-- Name: blog_reply fktoi6eifjvf4ej2en5ttkeqjei; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blog_reply
    ADD CONSTRAINT fktoi6eifjvf4ej2en5ttkeqjei FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: discussion_forum_answer forum_answer_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discussion_forum_answer
    ADD CONSTRAINT forum_answer_fk1 FOREIGN KEY (forum_id) REFERENCES public.discussion_forum(discussion_forum_id) ON DELETE CASCADE;


--
-- Name: discussion_forum_answer forum_answer_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discussion_forum_answer
    ADD CONSTRAINT forum_answer_fk2 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: discussion_forum_answer forum_answer_fk3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discussion_forum_answer
    ADD CONSTRAINT forum_answer_fk3 FOREIGN KEY (parent) REFERENCES public.discussion_forum_answer(forum_answer_id) ON DELETE CASCADE;


--
-- Name: forum_answer_up_vote forum_answer_up_vote_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.forum_answer_up_vote
    ADD CONSTRAINT forum_answer_up_vote_fk1 FOREIGN KEY (forum_answer_id) REFERENCES public.discussion_forum_answer(forum_answer_id) ON DELETE CASCADE;


--
-- Name: forum_answer_up_vote forum_answer_up_vote_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.forum_answer_up_vote
    ADD CONSTRAINT forum_answer_up_vote_fk2 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: payment payment_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_fk1 FOREIGN KEY (project_id) REFERENCES public.project(project_id) ON DELETE CASCADE;


--
-- Name: payment payment_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_fk2 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: performance_evaluation_criteria_score performance_evaluation_criteria_score_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_evaluation_criteria_score
    ADD CONSTRAINT performance_evaluation_criteria_score_fk1 FOREIGN KEY (performance_evaluation_id) REFERENCES public.performance_evaluation(role_performance_evaluation_id) ON DELETE CASCADE;


--
-- Name: performance_evaluation_criteria_score performance_evaluation_criteria_score_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_evaluation_criteria_score
    ADD CONSTRAINT performance_evaluation_criteria_score_fk2 FOREIGN KEY (admin_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: performance_evaluation_criteria_score performance_evaluation_criteria_score_fk3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_evaluation_criteria_score
    ADD CONSTRAINT performance_evaluation_criteria_score_fk3 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: performance_evaluation performance_evaluation_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_evaluation
    ADD CONSTRAINT performance_evaluation_fk2 FOREIGN KEY (admin_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: performance_feedback performance_feedback_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_feedback
    ADD CONSTRAINT performance_feedback_fk1 FOREIGN KEY (done_by) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: performance_feedback performance_feedback_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance_feedback
    ADD CONSTRAINT performance_feedback_fk2 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: policy policy_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.policy
    ADD CONSTRAINT policy_fk1 FOREIGN KEY (admin_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: position position_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position"
    ADD CONSTRAINT position_fk1 FOREIGN KEY (role_id) REFERENCES public.role(role_id) ON DELETE CASCADE;


--
-- Name: position position_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position"
    ADD CONSTRAINT position_fk2 FOREIGN KEY (project_id) REFERENCES public.project(project_id) ON DELETE CASCADE;


--
-- Name: position position_fk3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position"
    ADD CONSTRAINT position_fk3 FOREIGN KEY (admin_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: position_referral position_referral_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.position_referral
    ADD CONSTRAINT position_referral_fk1 FOREIGN KEY (position_id) REFERENCES public."position"(position_id) ON DELETE CASCADE;


--
-- Name: client_referral position_referral_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client_referral
    ADD CONSTRAINT position_referral_fk1 FOREIGN KEY (referred_by) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: position_referral position_referral_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.position_referral
    ADD CONSTRAINT position_referral_fk2 FOREIGN KEY (referred_by) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: position_self_referral position_self_referral_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.position_self_referral
    ADD CONSTRAINT position_self_referral_fk1 FOREIGN KEY (position_id) REFERENCES public."position"(position_id) ON DELETE CASCADE;


--
-- Name: position_self_referral position_self_referral_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.position_self_referral
    ADD CONSTRAINT position_self_referral_fk2 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: project project_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_fk1 FOREIGN KEY (admin_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: project project_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_fk2 FOREIGN KEY (project_lead) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: project_incentive project_incentivefk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project_incentive
    ADD CONSTRAINT project_incentivefk1 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: project_incentive project_incentivefk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project_incentive
    ADD CONSTRAINT project_incentivefk2 FOREIGN KEY (admin_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: project_incentive project_incentivefk3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project_incentive
    ADD CONSTRAINT project_incentivefk3 FOREIGN KEY (project_id) REFERENCES public.project(project_id) ON DELETE CASCADE;


--
-- Name: role role_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_fk1 FOREIGN KEY (admin_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: task_report task_reportfk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task_report
    ADD CONSTRAINT task_reportfk2 FOREIGN KEY (project_id) REFERENCES public.project(project_id) ON DELETE CASCADE;


--
-- Name: task_report task_reportfkl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task_report
    ADD CONSTRAINT task_reportfkl FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: user_project user_project_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_project
    ADD CONSTRAINT user_project_fk1 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: user_project user_project_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_project
    ADD CONSTRAINT user_project_fk2 FOREIGN KEY (project_id) REFERENCES public.project(project_id) ON DELETE CASCADE;


--
-- Name: security_question user_security_question_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.security_question
    ADD CONSTRAINT user_security_question_fk1 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: wish wish_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wish
    ADD CONSTRAINT wish_fk2 FOREIGN KEY (admin_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- Name: wish_reply wish_reply_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wish_reply
    ADD CONSTRAINT wish_reply_fk1 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

