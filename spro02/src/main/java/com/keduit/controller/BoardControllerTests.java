package com.keduit.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTests {
	
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	// request를 만들어서 보내주는 역활을 함
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
		
		log.info(
				// 주소표시줄에서의 호출된 효과
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}
	@Test
	public void testListPaging() throws Exception {
		
		log.info(
				// 주소표시줄에서의 호출된 효과
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
						.param("pageNum", "4")
						.param("amount", "10"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}

	@Test
	public void testRegister() throws Exception {
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title","mockmvc test title")
				.param("content","mockmvc test content")
				.param("writer","윤정한")
				).andReturn().getModelAndView().getViewName();
		log.info("................resultPage.................. : " +resultPage);
	}
	
	@Test
	public void testGet() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders
				.get("/board/get")
				.param("bno", "21")
				).andReturn()
				.getModelAndView()
				.getModelMap());
	}
	
	@Test
	public void testModify() throws Exception{
		log.info("Modify ..............................................");
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno","1")
				.param("title", "아낀다")
				.param("content","아낀다")
				.param("writer","버논"))
				.andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}
	
	
	@Test
	public void testRemove() throws Exception{
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno","22"))
				.andReturn()
				.getModelAndView()
				.getViewName();
		log.info(resultPage);
	}
	
}
