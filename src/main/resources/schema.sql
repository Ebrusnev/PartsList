DROP TABLE IF EXISTS PART;
CREATE TABLE PART(
    id INT(11) not null AUTO_INCREMENT primary key,
    name varchar(50) not null default 'None',
    quantity int(11) not null default '0',
    isMustHave boolean not null default false
    )
engine = InnoDB default character set = utf8;