create table notification
(
	id bigint auto_increment,
	notifier bigint not null,
	receiver bigint not null,
	outerId bigint not null,
	type int not null,
	gmt_create bigint not null,
	status int default 0 not null,
	constraint notification_pk
		primary key (id)
);

comment on column notification.notifier is '通知的人
';

comment on column notification.receiver is '接受消息的人';

comment on column notification.outerId is '可能是问题的ID，回复的ID...
';

comment on column notification.status is '0未读 1已读';

