-- Table: public.bg_article

-- DROP TABLE public.bg_article;

CREATE TABLE public.bg_article
(
    article_id integer NOT NULL DEFAULT nextval('bg_article_article_id_seq'::regclass),
    article_title character varying(20) COLLATE pg_catalog."default" NOT NULL,
    article_content text COLLATE pg_catalog."default" NOT NULL,
    tag_id integer NOT NULL,
    create_user_id integer NOT NULL,
    create_time time with time zone NOT NULL DEFAULT now(),
    update_user_id integer NOT NULL,
    update_time time with time zone NOT NULL DEFAULT now(),
    CONSTRAINT "PK_article_id" PRIMARY KEY (article_id),
    CONSTRAINT "UN_createUserId_articleTitle" UNIQUE (create_user_id, article_title),
    CONSTRAINT "FK_article_createUserId" FOREIGN KEY (create_user_id)
        REFERENCES public.bg_user (user_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT "FK_article_tag_id" FOREIGN KEY (tag_id)
        REFERENCES public.bg_tag (tag_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT "FK_article_updateUserId" FOREIGN KEY (update_user_id)
        REFERENCES public.bg_user (user_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.bg_article
    OWNER to postgres;
COMMENT ON TABLE public.bg_article
    IS '文章表';

COMMENT ON COLUMN public.bg_article.article_id
    IS '文章ID';

COMMENT ON COLUMN public.bg_article.article_title
    IS '文章标题';

COMMENT ON COLUMN public.bg_article.article_content
    IS '文章内容';

COMMENT ON COLUMN public.bg_article.create_user_id
    IS '创建文章的ID';

COMMENT ON COLUMN public.bg_article.create_time
    IS '创建文章的时间';

COMMENT ON COLUMN public.bg_article.update_user_id
    IS '更新文章的ID';

COMMENT ON COLUMN public.bg_article.update_time
    IS '更新文章的时间';

COMMENT ON CONSTRAINT "UN_createUserId_articleTitle" ON public.bg_article
    IS '一个用户不能创建相同的文章名';

COMMENT ON CONSTRAINT "FK_article_createUserId" ON public.bg_article
    IS '该文章的创建者ID索引自bg_user表中的user_id';
COMMENT ON CONSTRAINT "FK_article_tag_id" ON public.bg_article
    IS '文章标签ID索引自标签表';
COMMENT ON CONSTRAINT "FK_article_updateUserId" ON public.bg_article
    IS '该文章的修改者ID索引自bg_user表的user_id';