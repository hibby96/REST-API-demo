create table family (
    "Id" integer GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1),
    name VARCHAR(100),
    PRIMARY KEY ("Id")
);

insert into family (name) VALUES ('Will');