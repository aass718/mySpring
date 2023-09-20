package com.keduit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.replyVO;
import com.keduit.mapper.BoardMapper;
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
	
	@Autowired
	private BoardMapper boardmapper;
	
	//무결성을 위해 트랜젝션을 넣어야 함.
	@Transactional
	@Override
	public int register(replyVO vo) {
		int result = mapper.insert(vo);
		log.info("register..................."+ vo);
		boardmapper.updateReplyCnt(vo.getBno(),1);
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
	
	//무결성을 위해 트랜젝션을 넣어야 함.
//	@Transactional
	@Override
	public int remove(long rno) {
		log.info("remove..... ....");
//		int result = mapper.delete(rno);
		replyVO vo = mapper.read(rno);
		boardmapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.delete(rno);
	}

	@Override
	public List<replyVO> getList(Criteria cri, Long bno) {
		log.info("getList..... ....");
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		log.info("------- getListPage -----" + cri + " and "+bno);
			//bno가 가지고 있는 bno 갯수 읽음. 
		return new ReplyPageDTO(mapper.getCountByBno(bno),
								mapper.getListWithPaging(cri, bno));
	}

}
