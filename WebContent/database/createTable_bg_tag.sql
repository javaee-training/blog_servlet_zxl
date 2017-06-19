-- Table: public.bg_tag

-- DROP TABLE public.bg_tag;

CREATE TABLE public.bg_tag
(
    tag_id integer NOT NULL DEFAULT nextval('bg_tag_tag_id_seq'::regclass),
    tag_name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    create_user_id integer NOT NULL,
    create_time time with time zone NOT NULL DEFAULT now(),
    update_user_id integer NOT NULL,
    update_time time with time zone NOT NULL DEFAULT now(),
    CONSTRAINT "PK_tag_id" PRIMARY KEY (tag_id),
    CONSTRAINT "UN_tag_name" UNIQUE (tag_name),
    CONSTRAINT "FK_tag_createUserId" FOREIGN KEY (create_user_id)
        REFERENCES public.bg_user (user_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT "FK_tag_updateUserId" FOREIGN KEY (update_user_id)
        REFERENCES public.bg_user (user_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.bg_tag
    OWNER to postgres;
COMMENT ON TABLE public.bg_tag
    IS '标签表';

COMMENT ON COLUMN public.bg_tag.tag_id
    IS '标签ID';

COMMENT ON COLUMN public.bg_tag.tag_name
    IS '标签名字';

COMMENT ON COLUMN public.bg_tag.create_user_id
    IS '创建标签的用户ID';

COMMENT ON COLUMN public.bg_tag.create_time
    IS '创建时间';

COMMENT ON COLUMN public.bg_tag.update_user_id
    IS '更新标签的用户id';

COMMENT ON COLUMN public.bg_tag.update_time
    IS '更新标签的时间';

COMMENT ON CONSTRAINT "UN_tag_name" ON public.bg_tag
    IS '标签名唯一';

COMMENT ON CONSTRAINT "FK_tag_createUserId" ON public.bg_tag
    IS '创建该标签的用户ID索引自bg_user表中的user_id';
COMMENT ON CONSTRAINT "FK_tag_updateUserId" ON public.bg_tag
    IS '该标签的修改者ID索引自bg_user表中的user_id';