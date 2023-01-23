create sequence travel_cards_seq start with 1 increment by 50;
create sequence users_seq start with 1 increment by 50;


create table transport (
                           name varchar(255) not null,
                           price numeric(38,2),
                           primary key (name)
);


create table travel_cards (
                              id bigint not null,
                              end_time timestamp(6),
                              start_time timestamp(6),
                              transport varchar(255),
                              primary key (id)
);


create table users (
                       id bigint not null,
                       email varchar(255),
                       first_name varchar(255),
                       last_name varchar(255),
                       password varchar(255),
                       balance numeric(38,2),
                       enabled boolean not null,
                       primary key (id)
);


create table users_travel_cards (
                                    user_id bigint not null,
                                    travel_cards_id bigint not null,
                                    primary key (user_id, travel_cards_id)
);


alter table if exists users_travel_cards
    add constraint UK_hknpvwdait0yvuystutdc9tvk unique (travel_cards_id);


alter table if exists travel_cards
    add constraint FK6egu5y7rv2yj0i2vxn3leox1n
        foreign key (transport)
            references transport;


alter table if exists users_travel_cards
    add constraint FKmpi0mvngndedcddd6qucvqux3
        foreign key (travel_cards_id)
            references travel_cards;


alter table if exists users_travel_cards
    add constraint FKoko3vi9x4mpe0d3njdovblpvx
        foreign key (user_id)
            references users;