-- This script was generated by a beta version of the ERD tool in pgAdmin 4.
-- Please log an issue at https://redmine.postgresql.org/projects/pgadmin4/issues/new if you find any bugs, including reproduction steps.
BEGIN;


CREATE TABLE public.activation_code_for_employers
(
    id integer NOT NULL,
    employer_id integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.activation_code_for_job_seekers
(
    id integer NOT NULL,
    job_seeker_id integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.activation_codes
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    activation_code character varying(40) NOT NULL,
    is_confimed boolean NOT NULL,
    confimed_time date,
    dtype character varying(31) NOT NULL,
    confirmed_time timestamp without time zone,
    is_confirmed boolean,
    employer_id integer,
    job_seeker_id integer,
    PRIMARY KEY (id)
);

CREATE TABLE public.cities
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    city_name character varying(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.cover_letters
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    letter character varying(1000) NOT NULL,
    curriculum_vitae_id integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.curriculum_vitaes
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    job_seeker_id integer NOT NULL,
    github_address character varying(200),
    linkedin_address character varying(200),
    PRIMARY KEY (id)
);

CREATE TABLE public.educations
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    school_name character varying(100) NOT NULL,
    department character varying(100) NOT NULL,
    start_date date NOT NULL,
    graduation_date date,
    is_graduated boolean NOT NULL,
    curriculum_vitae_id integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.employer_activation_by_system_personels
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    employer_id integer NOT NULL,
    confirmed_system_personel_id integer NOT NULL,
    is_confimed boolean NOT NULL,
    confirmed_time date,
    PRIMARY KEY (id)
);

CREATE TABLE public.employers
(
    id integer NOT NULL,
    company_name character varying(50) NOT NULL,
    web_address character varying(50) NOT NULL,
    phone_number character varying(12) NOT NULL,
    is_activated boolean NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.job_advertisement
(
    id integer NOT NULL,
    description character varying(255),
    end_time timestamp without time zone,
    max_salary integer,
    min_salary integer,
    number_positions integer,
    city_id integer,
    employer_id integer,
    job_id integer,
    is_active boolean,
    publish_date date,
    PRIMARY KEY (id)
);

CREATE TABLE public.job_experiences
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    workplace_name character varying NOT NULL,
    position_name character varying NOT NULL,
    start_date date NOT NULL,
    end_date date,
    is_working_now boolean NOT NULL,
    curriculum_vitae_id integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.job_seekers
(
    id integer NOT NULL,
    first_name character varying(25) NOT NULL,
    last_name character varying(25) NOT NULL,
    identity_number character varying(11) NOT NULL,
    birth_date date NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.job_titles
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 5 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    job_name character varying(30) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.languages
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    language_name character varying NOT NULL,
    language_level integer NOT NULL,
    curriculum_vitae_id integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.photos
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    photo_name character varying(500),
    photo_url character varying(1000) NOT NULL,
    photo_id character varying(50),
    job_seeker_id integer,
    curriculum_vitae_id integer,
    PRIMARY KEY (id)
);

CREATE TABLE public.programing_languages
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    programing_language_name character varying(50) NOT NULL,
    curriculum_vitae_id integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.system_personels
(
    id integer NOT NULL,
    first_name character varying(25) NOT NULL,
    last_name character varying(25) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.users
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    email character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.activation_code_for_employers
    ADD FOREIGN KEY (id)
        REFERENCES public.activation_codes (id)
    NOT VALID;


ALTER TABLE public.activation_code_for_employers
    ADD FOREIGN KEY (employer_id)
        REFERENCES public.employers (id)
    NOT VALID;


ALTER TABLE public.activation_code_for_job_seekers
    ADD FOREIGN KEY (id)
        REFERENCES public.activation_codes (id)
    NOT VALID;


ALTER TABLE public.activation_code_for_job_seekers
    ADD FOREIGN KEY (job_seeker_id)
        REFERENCES public.job_seekers (id)
    NOT VALID;


ALTER TABLE public.cover_letters
    ADD FOREIGN KEY (curriculum_vitae_id)
        REFERENCES public.curriculum_vitaes (id)
    NOT VALID;


ALTER TABLE public.curriculum_vitaes
    ADD FOREIGN KEY (job_seeker_id)
        REFERENCES public.job_seekers (id)
    NOT VALID;


ALTER TABLE public.educations
    ADD FOREIGN KEY (curriculum_vitae_id)
        REFERENCES public.curriculum_vitaes (id)
    NOT VALID;


ALTER TABLE public.employer_activation_by_system_personels
    ADD FOREIGN KEY (employer_id)
        REFERENCES public.employers (id)
    NOT VALID;


ALTER TABLE public.employer_activation_by_system_personels
    ADD FOREIGN KEY (confirmed_system_personel_id)
        REFERENCES public.system_personels (id)
    NOT VALID;


ALTER TABLE public.employers
    ADD FOREIGN KEY (id)
        REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.job_advertisement
    ADD FOREIGN KEY (city_id)
        REFERENCES public.cities (id)
    NOT VALID;


ALTER TABLE public.job_advertisement
    ADD FOREIGN KEY (job_id)
        REFERENCES public.job_titles (id)
    NOT VALID;


ALTER TABLE public.job_advertisement
    ADD FOREIGN KEY (employer_id)
        REFERENCES public.employers (id)
    NOT VALID;


ALTER TABLE public.job_experiences
    ADD FOREIGN KEY (curriculum_vitae_id)
        REFERENCES public.curriculum_vitaes (id)
    NOT VALID;


ALTER TABLE public.job_experiences
    ADD FOREIGN KEY (curriculum_vitae_id)
        REFERENCES public.curriculum_vitaes (id)
    NOT VALID;


ALTER TABLE public.job_seekers
    ADD FOREIGN KEY (id)
        REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.languages
    ADD FOREIGN KEY (curriculum_vitae_id)
        REFERENCES public.curriculum_vitaes (id)
    NOT VALID;


ALTER TABLE public.photos
    ADD FOREIGN KEY (curriculum_vitae_id)
        REFERENCES public.curriculum_vitaes (id)
    NOT VALID;


ALTER TABLE public.photos
    ADD FOREIGN KEY (curriculum_vitae_id)
        REFERENCES public.curriculum_vitaes (id)
    NOT VALID;


ALTER TABLE public.photos
    ADD FOREIGN KEY (job_seeker_id)
        REFERENCES public.job_seekers (id)
    NOT VALID;


ALTER TABLE public.programing_languages
    ADD FOREIGN KEY (curriculum_vitae_id)
        REFERENCES public.curriculum_vitaes (id)
    NOT VALID;


ALTER TABLE public.system_personels
    ADD FOREIGN KEY (id)
        REFERENCES public.users (id)
    NOT VALID;

END;