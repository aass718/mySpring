package com.keduit.controller;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.domain.PageDTO;
import com.keduit.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
	// Controller는 Service 호출해서 사용하면 됨, Service(주입)가 꼭 필요함!!
	private final BoardService service;

//	@GetMapping("/list")
//	//Model타입의 model 을 받아 옴(MVC중에 M!)
//	public void list(Model model) {
//		log.info("list......");
//		//"list"로 가지고 오는데 위에 호출했던 service의 gerList 함수를 가지고 온다.
//		model.addAttribute("list", service.getList());
//	}

	@GetMapping("/list")
	// Model타입의 model 을 받아 옴(MVC중에 M!)
	public void list(Criteria cri, Model model) {
		log.info("list......" + cri);
		// "list"로 가지고 오는데 위에 호출했던 service의 gerList 함수를 가지고 온다.
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, 123));
	}

	// register 갈 수 있는 화면 / 구현 화면으로 날라감.
	@GetMapping("/register")
	public void register() {
	}

	// 비번 정보등 중요 정보가 있을 수 있음으로 get이 아닌 post 사용
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register......." + board);
		long bno = service.register(board);
		log.info("bno......." + bno);

		// 일회용 사용으로 addFlashAttribute
		rttr.addFlashAttribute("result", bno);

		return "redirect:/board/list";
	}

	// /board/get 으로 들어왔을 때.
	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info(".../get ... or /modify");
		// service에서 읽어온 bno를 board라 읽고
		model.addAttribute("board", service.get(bno));
	}

	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("..............modify : " + board);
		// modify는 boolean타입으로 true false를 반환함.
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info(".................................remove...........................................");
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "sucess");
		}
		return "redirect:/board/list";
	}

}