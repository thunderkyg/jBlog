------------------------------
--Drop Table Comments
drop table comments;
--Drop Table Post
drop table post;
--Drop Table Category
drop table category;
--Drop Table Blog
drop table blog;
--Drop Table Users
drop table users;

--Drop Sequence Users
drop sequence seq_users_no;
--Drop Sequence Board
drop sequence seq_category_no;
--Drop Sequence Board
drop sequence seq_post_no;
--Drop Sequence Board
drop sequence seq_comments_no;

-------------------------------

--Create Table Users
Create table users (
    userNo number,
    id varchar2(50) not null unique,
    userName varchar2(100) not null,
    password varchar2(50) not null,
    joinDate date not null,
    primary key(userNo)
    );

--Create Table Blog
create table blog(
    id varchar2(50),
    blogTitle varchar2(200) not null,
    logoFile varchar2(200),
    primary key(id),
    constraint id_fk foreign key (id)
    references users(id)
    );
    
--Create Table Category
create table category(
    cateNo number,
    id varchar2(50),
    cateName varchar2(200) not null,
    description varchar2(500),
    regDate date not null,
    primary key (cateNo),
    constraint id_foreign foreign key (id)
    references blog(id)
);

--Create Table Post
create table post (
    postNo number,
    cateNo number,
    postTitle varchar2(300) not null,
    postContent varchar2(4000),
    regDate date not null,
    primary key (postNo),
    constraint cateNo_fk foreign key (cateNo)
    references category(cateNo)
);

--Create Table Comments
create table comments(
    cmtNo number,
    postNo number,
    userNo number,
    cmtContent varchar2(1000),
    regDate date,
    primary key(cmtNo),
    constraint postNo_fk foreign key (postNo)
    references post(postNo),
    constraint userNo foreign key (userNo)
    references users(userNo)
);
    
--Create Sequence Users
create sequence seq_users_no
increment by 1
start with 1
nocache;

--Create Sequence Category
create sequence seq_category_no
increment by 1
start with 1
nocache;

--Create Sequence Post
create sequence seq_post_no
increment by 1
start with 1
nocache;

--Create Sequence Comments
create sequence seq_comments_no
increment by 1
start with 1
nocache;

commit;
