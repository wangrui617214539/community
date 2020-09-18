create table comment
(
	id BIGINT auto_increment,
	parent_id BIGINT not null,
	type_id int not null,
	commentator int not null,
	mt_create BIGINT not null,
	gmt_modified BIGINT not null,
	like_count BIGINT default 0,
	constraint COMMENT_PK
		primary key (id)
);