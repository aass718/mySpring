package com.keduit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
@ToString
public class BoardServiceInpl implements BoardService {
	
	//BoardMapper mapper을 new 한 것.
	private final BoardMapper mapper;

	@Override
	public Long register(BoardVO board) {
		log.info("...... register ......" + board);
		mapper.insertSelectKey(board);
		
		return board.getBno();
	}

	@Override
	public BoardVO get(Long bno) {
			BoardVO board = mapper.read(bno);
			log.info("get......................." + bno);
		return board;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("delete.................." + bno);
		return mapper.delete(bno) == 1;
	}
	

		@Override
	public boolean modify(BoardVO board) {
		log.info("modify........................................");
		
		return mapper.update(board) ==1;
	}
//	@Override
//	public List<BoardVO> getList() {
//		log.info("getList................");
//		return mapper.getList();
//	}
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("getList................" + cri);
		return mapper.getListWithPaging(cri);
	}

}
