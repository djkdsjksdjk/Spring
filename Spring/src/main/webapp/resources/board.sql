create table tbl_board (
rno INT not NULL AUTO_INCREMENT,
bno INT not NULL default 0,
replytext VARCHAR(1000) NOT NULL,
reply VARCHAR(100) NOT NULL,
regdate TIMESTAMP not NULL DEFAULT NOW(),
updatedate TIMESTAMP,
PRIMARY KEY(rno)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE tbl_reply add CONSTRAINT fk_board
FOREIGN KEY (bno) REFERENCES tbl_board(bno);