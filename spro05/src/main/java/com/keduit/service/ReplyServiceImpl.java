package com.keduit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keduit.domain.Criteria;
import com.keduit.domain.replyVO;
import com.keduit.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;


@Service
@Log4j
@RequiredArgsConstructor
@ToString
public class ReplyServiceImpl implements ReplyService{

	//ReplyMapper를 뉴 !
	private final ReplyMapper mapper;
	
	@Override
	public int register(replyVO vo) {
		int result = mapper.insert(vo);
		log.info("register..................."+ vo);
		return result;
	}

	@Override
	public replyVO get(Long rno) {
		replyVO reply = mapper.read(rno);
		log.info("get!!!!!!!!"+reply);
		return reply;
	}

	@Override
	public int modify(replyVO vo) {
		log.info("modify .... . .. . ");
		int result = mapper.update(vo);
		return result;
	}

	@Override
	public int remove(long rno) {
		log.info("remove..... ....");
		int result = mapper.delete(rno);
		return result;
	}

	@Override
	public List<replyVO> getList(Criteria cri, Long bno) {
		// TODO Auto-generated method stub
		return null;
	}

}
