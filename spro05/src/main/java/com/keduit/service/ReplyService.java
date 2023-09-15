package com.keduit.service;

import java.util.List;

import com.keduit.domain.Criteria;
import com.keduit.domain.replyVO;

public interface ReplyService {
	
	//list
	public List<replyVO> getList(Criteria cri, Long bno);
	
	//register 의 뜻은 기억장치, 저장하는 것. create?
	public int register(replyVO vo);
	
	//읽기 read
	public replyVO get(Long rno	);
	
	//수정 update
	public int modify(replyVO vo);
	
	//삭제 delete
	public int remove(long rno);
	
	
	
}
