package com.keduit.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyPageDTO {
	

	
	public ReplyPageDTO() {
		super();
	}
	private int replyCnt;
	private List<replyVO> list;

}
