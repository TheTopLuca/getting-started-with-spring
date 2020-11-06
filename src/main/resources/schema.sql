
create table MANUFACTURER(
id int primary key AUTO_INCREMENT,
name varchar(50)
);
create TABLE CAR(
    id int primary key AUTO_INCREMENT ,
    model varchar(50) ,
    manufacturer_id int constraint FK_CONSTRAINT references MANUFACTURER  ,
    name varchar(50) not null,
    owner varchar(50) ,
    color varchar(50),
    mileage int
);
