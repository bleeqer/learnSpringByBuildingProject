package com.bleeqer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bleeqer.domain.BoardVO;
import com.bleeqer.domain.Criteria;
import com.bleeqer.domain.PageDTO;
import com.bleeqer.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

// Controller ������̼��� �߰��ؼ� �������� ������ �ν��� �� �ְ� ��
@Controller
@Log4j
@RequestMapping("/board/*")
//@AllArgsConstructor
public class BoardController {
	
	@Setter(onMethod_ = {@Autowired})
	private BoardService service;
	

	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		
		log.info("list" + cri);
		
		int total = service.getTotal(cri);
		
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		
		System.out.println(cri);
	}
	
	@PostMapping("/create")
//	�߰������� ���Ӱ� ��ϵ� �Խù��� ��ȣ�� ���� �����ϱ� ���ؼ� RedirectAttributes�� ���
	public String create(BoardVO board, RedirectAttributes rttr) {
		
		log.info("create: " + board);
		
		service.create(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get", "/modify"})
//	bno ���� �� �� ��������� ó���ϴ� @RequestParam�� �̿��ؼ� ����
//	@ModelAttribute�� Model ��ü�� �������̸�����("cri") �ڵ����� �����͸� ����� 
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		
		log.info("/get or /modify");
		model.addAttribute("board", service.get(bno));
	}
	
	@PostMapping("/modify")
//	bno ���� �� �� ��������� ó���ϴ� @RequestParam�� �̿��ؼ� ����
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		log.info("modify: " + board);
		
//		���� ������ 1 ��ȯ
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		log.info("remove..." + bno);
		
//		���� ������ 1 ��ȯ
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/create")
	public void register() {
		
	}
}
