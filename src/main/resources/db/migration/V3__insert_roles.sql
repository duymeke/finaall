create table roles (
                       id bigserial primary key,
                       name varchar not null
);

create table accounts_roles (
                                account_id bigint not null references accounts(id) on delete cascade,
                                role_id bigint not null references roles(id) on delete cascade,
                                primary key (account_id, role_id)
);

insert into roles (name) values
                             ('ROLE_USER'),
                             ('ROLE_ADMIN');