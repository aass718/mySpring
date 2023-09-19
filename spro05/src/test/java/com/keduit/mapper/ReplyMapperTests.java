package com.keduit.mapper;

import java.util.List;
import java.util.stream.IntStream;

import javax.servlet.annotation.WebServlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.Criteria;
import com.keduit.domain.replyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	

	private Long[] bnoArr = {111L, 112L, 113L, 114L, 115L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	@Test
	public void testCreate() {
		log.info("create.......................");
		IntStream.range(1, 10).forEach(i -> {
			replyVO vo = new replyVO();
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트"+ i);
			vo.setReplyer("Replyer"+i);
			
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testRead() {
		Long rno = 41L;
		replyVO vo = mapper.read(rno);
		log.info(vo);
		
	}
	
	@Test
	public void testDelete() {
		Long rno = 41L;
		mapper.delete(rno);
		log.info(rno +"는 삭제 되었습니다.");
	}
	
	@Test
	public void testUpdate() {
		Long rno = 38L;
		replyVO vo = mapper.read(rno);
		vo.setReply("댓글 수정");
		int count = mapper.update(vo);
		log.info("update count : "+ count);
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<replyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		replies.forEach(reply -> log.info(reply));
	}
	
	@Test
	public void testList2() {
		Criteria cri = new Criteria(1,10);
		List<replyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		replies.forEach(reply -> log.info(reply));
	}
	
}
