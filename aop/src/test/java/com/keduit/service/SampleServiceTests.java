package com.keduit.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class SampleServiceTests {
	
	@Autowired
	private SampleService service;
	
	@Autowired
	private SampleTxService txservice;
	
	@Test
	public void testClass() {
		log.info(service);
		log.info(service.getClass().getName());
	}
	
	@Test
	public void testAdd() throws Exception{
		log.info(service.doAdd("564","500"));
	}
	
	@Test
	public void testException() throws Exception{
		log.info(service.doAdd("123", "abc"));
	}
	/*=================================================================================*/
	
	@Test
	public void testLong() {
		String str = "Lorem ipsum dolor sit amet, consectetur adipiscing "
		                + "nostrud exercitation ullamco laboris nisi ut aliquip "
		                + "elit, sed do eiusmod tempor incididunt ut labore et "
		                + "dolore magna aliqua. Ut enim ad minim veniam, quis "
		                + "nostrud exercitation ullamco laboris nisi ut aliquip "
		                + "ex ea commodo consequat.";
		log.info(str.getBytes().length);
		txservice.addData(str);
	}
	
}
