create table accounts (
                          id bigserial primary key,
                          username varchar(255),
                          email varchar(255),
                          password varchar(255)
);

create table work_items (
                            id bigserial primary key,
                            title varchar(255),
                            text varchar(255),
                            is_completed boolean,
                            account_id bigint,
                            foreign key (account_id) references accounts(id) on delete cascade
);

create table labels (
                        id bigserial primary key,
                        name varchar(255)
);

create table work_item_labels (
                                  work_item_id bigint,
                                  label_id bigint,
                                  foreign key (work_item_id) references work_items(id) on delete cascade,
                                  foreign key (label_id) references labels(id) on delete cascade,
                                  primary key (work_item_id, label_id)
);
