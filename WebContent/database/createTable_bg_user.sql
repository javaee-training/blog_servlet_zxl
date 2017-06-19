-- Table: public.bg_user

-- DROP TABLE public.bg_user;

CREATE TABLE public.bg_user
(
    user_id integer NOT NULL DEFAULT nextval('bg_user_user_id_seq'::regclass),
    user_name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    user_password text COLLATE pg_catalog."default" NOT NULL,
    create_user_id integer NOT NULL,
    create_time time with time zone NOT NULL DEFAULT now(),
    update_user_id integer NOT NULL,
    update_time time with time zone NOT NULL DEFAULT now(),
    CONSTRAINT "PK_user_id" PRIMARY KEY (user_id),
    CONSTRAINT "UN_user_name" UNIQUE (user_name)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.bg_user
    OWNER to postgres;
COMMENT ON TABLE public.bg_user
    IS '存储用户信息的表';

COMMENT ON COLUMN public.bg_user.user_id
    IS '用户id';

COMMENT ON COLUMN public.bg_user.user_name
    IS '用户名';

COMMENT ON COLUMN public.bg_user.user_password
    IS '用户密码';

COMMENT ON COLUMN public.bg_user.create_user_id
    IS '创建该用户的ID';

COMMENT ON COLUMN public.bg_user.create_time
    IS '创建时间';

COMMENT ON COLUMN public.bg_user.update_user_id
    IS '更新该用户的ID';

COMMENT ON COLUMN public.bg_user.update_time
    IS '更新时间';