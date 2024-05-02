drop database if exists sports;
create database sports;
use sports;

create table players(
    player_id varchar(50) NOT NULL check (player_id <> '0') ,
    player_name varchar(50) NOT NULL,
    player_age varchar(50) NOT NULL,
    primary key(player_id)
);

create table sports(
    sport_id varchar(50) NOT NULL check (sport_id <> '0'),
    sport_name varchar(50) NOT NULL,
    participation_points int NOT NULL,
    primary key(sport_id)
);

create table player_sports(
    player_id varchar(50),
    sport_id varchar(50),
    primary key(player_id, sport_id),
    foreign key(player_id) references players(player_id) ON DELETE CASCADE,
    foreign key(sport_id) references sports(sport_id) ON DELETE CASCADE
);

create table coaches(
    coach_id varchar(50) check (coach_id <> 'NULL'),
    coach_name varchar(50) NOT NULL,
    sport_id varchar(50),
    primary key(coach_id),
    foreign key(sport_id) references sports(sport_id) ON DELETE SET NULL
);

create table sports_per_player(
    player_id varchar(50),
    count int,
    primary key(player_id),
    foreign key(player_id) references players(player_id) ON DELETE CASCADE
);

insert into sports values('1', 'Football', 10);
insert into sports values('2', 'Basketball', 20);
insert into sports values('3', 'Baseball', 30);
insert into sports values('4', 'Cricket', 40);
insert into sports values('5', 'Tennis', 50);

insert into players values('1', 'John', '20');
insert into player_sports values('1', '1');
insert into sports_per_player values('1', 1);
insert into players values('2', 'Jane', '21');
insert into sports_per_player values('2', 0);
insert into players values('3', 'Jack', '22');
insert into sports_per_player values('3', 0);
insert into players values('4', 'Jill', '23');
insert into sports_per_player values('4', 0);
insert into players values('5', 'Jim', '24');
insert into sports_per_player values('5', 0);

insert into coaches values('1', 'Coach1', '1');
insert into coaches values('2', 'Coach2', '2');
insert into coaches values('3', 'Coach3', '3');
insert into coaches values('4', 'Coach4', '4');
insert into coaches values('5', 'Coach5', '5');
