create table book
(
    book_id bigint not null,
    book_nm varchar(100),
    constraint pk_book primary key (book_id)
);

create table book_tags
(
    book_id bigint       not null,
    tag_val varchar(100) not null,
    constraint pk_book_tags primary key (book_id, tag_val)
);

create table store
(
    store_id bigint not null,
    store_nm varchar(100),
    constraint pk_store primary key (store_id)
);

create table store_books
(
    store_id bigint  not null,
    book_id  bigint  not null,
    stock    integer not null default 0,
    constraint pk_store_books primary key (store_id, book_id)
);

create table sequence
(
    id  varchar(100) not null,
    val bigint
);