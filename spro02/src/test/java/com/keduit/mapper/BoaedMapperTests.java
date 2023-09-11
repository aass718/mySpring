package com.keduit.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoaedMapperTests {
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("insert test 코드를 통한 입력");
		board.setContent("insert test 코드를 통한 입력 내용");
		board.setWriter("newbie ");
		mapper.insert(board);
		
		log.info(board);
	}
	
	@Test
	public void testInserSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("testInserSelectKey 코드");
		board.setContent("testInserSelectKey 코드 내용");
		board.setWriter("글쓴이");
		mapper.insertSelectKey(board);
		
		log.info("............번호"+ board.getBno());
		log.info(board);
	}
	
	@Test
	public void testRead() {
		BoardVO board = mapper.read(7L);
		log.info(board);
	}
	
	@Test
	public void testDelete() {
		int result = mapper.delete(10L);
		log.info("10번 삭제");
		log.info(result+"갯수 삭제");
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setBno(4L);
		board.setTitle("손오공");
		board.setContent("마치 된 것 같아 손오공");
		board.setWriter("에스쿱스");
		int result = mapper.update(board);
		log.info("update ===== "+board);
		log.info("update 갯수 =====> "+board);
		
		log.info(result);
	}
}
