package com.keduit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.replyVO;
import com.keduit.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/reply/")
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService service;
	
	@PostMapping(value="/new", 
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody replyVO vo) {
		log.info("..............................replyVO: "+vo);
		int insertCount = service.register(vo);
		log.info(".......................reply insert count"+insertCount);
		return insertCount == 1? new ResponseEntity<String>("succese",HttpStatus.OK)
								:new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/{rno}",
				produces= {MediaType.APPLICATION_XML_VALUE,
							MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<replyVO> get(@PathVariable("rno") Long rno) {
		log.info(".............get : "+ rno);
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
		
	}
	
	@GetMapping(value="/pages/{bno}/{page}",
						produces = {MediaType.APPLICATION_JSON_VALUE,
									MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ReplyPageDTO> getList(
			@PathVariable("bno") Long bno,
			@PathVariable("page") int page)
	
	{  log.info("...................getList....... bno: "+ bno +"  page: " + page);
	
		Criteria cri = new Criteria(page, 10);
		log.info("...................cri ......... : "+cri);
		
		return new ResponseEntity<>(service.getListPage(cri, bno),HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{rno}", 
							//TEXT_PLAIN_VALUE는 스트링 리턴한다.
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remve(@PathVariable("rno") Long rno){
		log.info("...............remove" +rno);
		return service.remove(rno)==1? 
					new ResponseEntity<String>("success",HttpStatus.OK)
					:new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}
								//전체 수정           일부수정
	@RequestMapping(method= {RequestMethod.PUT,RequestMethod.PATCH},
					value="/{rno}",
					consumes ="application/json",
					produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(
			@RequestBody replyVO vo,
			@PathVariable("rno") Long rno){
		vo.setRno(rno);
		log.info("modify rno.........: "+rno);
		log.info("modify vo.........: "+vo);
		
		return service.modify(vo)==1?
				new ResponseEntity<String>("success",HttpStatus.OK)
				:new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
