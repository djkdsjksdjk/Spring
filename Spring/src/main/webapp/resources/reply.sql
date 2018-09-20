create table tbl_reply (
rno INT not NULL AUTO_INCREMENT,
bno INT not NULL default 0,
replytext VARCHAR(1000) NOT NULL,
reply VARCHAR(100) NOT NULL,
regdate TIMESTAMP not NULL DEFAULT NOW(),
updatedate TIMESTAMP not NULL DEFAULT  NOW(),
PRIMARY KEY(rno)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;