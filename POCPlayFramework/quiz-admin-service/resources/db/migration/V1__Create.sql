-- SEQUENCE: public.answer_id_seq
-- DROP SEQUENCE public.answer_id_seq;
create sequence public.answer_id_seq
    increment 1
    start 1
    minvalue 1
    maxvalue 9223372036854775807
    cache 1;
alter sequence public.answer_id_seq OWNER TO postgres;

-- SEQUENCE: public.choice_id_seq
-- DROP SEQUENCE public.choice_id_seq;
create sequence public.choice_id_seq
    increment 1
    start 1
    minvalue 1
    maxvalue 9223372036854775807
    cache 1;
alter sequence public.choice_id_seq OWNER TO postgres;

-- SEQUENCE: public.question_id_seq
-- DROP SEQUENCE public.question_id_seq;
create sequence public.question_id_seq
    increment 1
    start 1
    minvalue 1
    maxvalue 9223372036854775807
    cache 1;
alter sequence public.question_id_seq
    OWNER TO postgres;

-- Table: public.answer
-- DROP TABLE public.answer;
create TABLE public.answer
(
    type character(1) COLLATE pg_catalog."default" NOT NULL,
    id bigint NOT NULL DEFAULT nextval('answer_id_seq'::regclass),
    CONSTRAINT answer_pkey PRIMARY KEY (id)
)
with (OIDS = FALSE)
TABLESPACE pg_default;
alter table public.answer OWNER to postgres;

-- Table: public.question
-- DROP TABLE public.question;
create TABLE public.question
(
    id bigint NOT NULL DEFAULT nextval('question_id_seq'::regclass),
    question character varying(255) COLLATE pg_catalog."default",
    answer_id bigint,
    CONSTRAINT question_pkey PRIMARY KEY (id),
    CONSTRAINT fk2w9qd6mx9oh2vchntaokhlj4f FOREIGN KEY (answer_id)
        REFERENCES public.answer (id) MATCH SIMPLE
        ON update NO ACTION
        ON delete NO ACTION
)
with (OIDS = FALSE)
TABLESPACE pg_default;
alter table public.question OWNER to postgres;

-- Table: public.multiple
-- DROP TABLE public.multiple;
create TABLE public.multiple
(
    id bigint NOT NULL,
    CONSTRAINT multiple_pkey PRIMARY KEY (id),
    CONSTRAINT fk3i71vtcrknqr96mgqx4x1dgt5 FOREIGN KEY (id)
        REFERENCES public.answer (id) MATCH SIMPLE
        ON update NO ACTION
        ON delete NO ACTION
)
with (OIDS = FALSE)
TABLESPACE pg_default;
alter table public.multiple OWNER to postgres;

-- Table: public.descriptive
-- DROP TABLE public.descriptive;
create TABLE public.descriptive
(
    answer character varying(255) COLLATE pg_catalog."default",
    id bigint NOT NULL,
    CONSTRAINT descriptive_pkey PRIMARY KEY (id),
    CONSTRAINT fkpn46co1k5gb1e0uve1054j5n6 FOREIGN KEY (id)
        REFERENCES public.answer (id) MATCH SIMPLE
        ON update NO ACTION
        ON delete NO ACTION
)
with (OIDS = FALSE)
TABLESPACE pg_default;
alter table public.descriptive OWNER to postgres;

-- Table: public.choice
-- DROP TABLE public.choice;
create TABLE public.choice
(
    id bigint NOT NULL DEFAULT nextval('choice_id_seq'::regclass),
    description character varying(255) COLLATE pg_catalog."default",
    is_answer boolean,
    answer_id bigint NOT NULL,
    CONSTRAINT choice_pkey PRIMARY KEY (id),
    CONSTRAINT fkf29d45doyj38lqtrv44ts59o5 FOREIGN KEY (answer_id)
        REFERENCES public.answer (id) MATCH SIMPLE
        ON update NO ACTION
        ON delete NO ACTION
)
with (OIDS = FALSE)
TABLESPACE pg_default;
alter table public.choice OWNER to postgres;