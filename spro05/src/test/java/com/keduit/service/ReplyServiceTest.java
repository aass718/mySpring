package com.keduit.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.replyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
						"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class ReplyServiceTest {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyService service;
	
	@Test
	public void testReplyService() {
		log.info("=============service ============="+service);
	}
	
	@Test
	public void testGet() {
		replyVO reply = service.get(1L);
		log.info("Get.................................");
		log.info(reply);
	}
	
	@Test
	public void testRegister() {
		replyVO reply = new replyVO();
		reply.setBno(110L);
		reply.setReply("선플 달기 운동");
		reply.setReplyer("웅웅");
		long num = service.register(reply);
		log.info("생성한 따뜻한 선플이 "+num+"개가 달렸습니다 ^^");
	}
}
