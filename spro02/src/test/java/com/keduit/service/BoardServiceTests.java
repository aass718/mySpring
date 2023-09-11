package com.keduit.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Autowired
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("service에서 insert");
		board.setContent("service에서 insert한 내용");
		board.setWriter("글쓴이");
		long bno = service.register(board);
		log.info("생성된 게시물 번호 : "+ bno);
	}
	
	@Test 
	public void testGet() {
		BoardVO board = service.get(11L);
		log.info("READ .....................");
		log.info(board);
	}
	
	@Test
	public void testGetList() {
		service.getList().forEach(board->log.info(board));	
		}
	
	@Test 
	public void testRemove() {
		service.remove(11L);
		log.info("Remove .....................");
	}
	
	
	@Test
	public void testUpdate() {
		BoardVO board = service.get(7L);
		if(board == null) {
			return;
		}
		board.setTitle("update 제목");
		board.setContent("update 내용");
		board.setWriter("최승철");
		service.modify(board);
	}
}
