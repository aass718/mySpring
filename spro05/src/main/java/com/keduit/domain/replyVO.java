package com.keduit.domain;

import java.util.Date;

import lombok.Data;

@Data
public class replyVO {
	
	private Long rno;
	private Long bno;
	
	private String reply;
	private String replyer;
	private Date replyDte;
	private Date updateDate;
	
	
}
