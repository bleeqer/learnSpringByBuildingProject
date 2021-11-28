package com.bleeqer.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bleeqer.domain.BoardAttachVO;
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
		
		// ���� ������ AttachList ������
		if (board.getAttachList() != null) {
			
			board.getAttachList().forEach(attach -> log.info("attachList ���� �Դϴ�.: " + attach));
		}
		
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
		
		
		return "redirect:/board/list" + cri.getListLink();
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		log.info("remove..." + bno);
		
		List<BoardAttachVO> attachList = service.getAttachList(bno);
		
//		���� ������ 1 ��ȯ
		if (service.remove(bno)) {
			
			deleteFiles(attachList);

			rttr.addFlashAttribute("result", "success");
			
		}
		
		return "redirect:/board/list" + cri.getListLink();
	}
	
	@GetMapping("/create")
	public void register() {
		
	}
	
	/*
	 * @GetMapping(value = "/getAttachList", produces =
	 * MediaType.APPLICATION_JSON_UTF8_VALUE)
	 * 
	 * @ResponseBody public ResponseEntity<List<BoardAttachVO>> getAttachList(Long
	 * bno) {
	 * 
	 * log.info("getAttachList " + bno);
	 * 
	 * return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK); }
	 */
	
	@GetMapping(value = "/getAttachList",
		    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno) {

		log.info("getAttachList " + bno);
	
		return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK);

	}
	
	private void deleteFiles(List<BoardAttachVO> attachList) {
		
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		
		log.info("delete attach files.........");
		log.info(attachList);
		
		attachList.forEach(attach -> {
			
			try {
				
				Path file = Paths.get("C:\\upload\\" + attach.getUploadPath() + "\\" + attach.getUuid() + "_" + attach.getFileName());
				
				Files.deleteIfExists(file);
				
				if (Files.probeContentType(file).startsWith("image")) {
					
					Path thumbNail = Paths.get("C:\\upload\\" + attach.getUploadPath() + "\\s_" + attach.getUuid() + "_" + attach.getFileName());
					
					Files.delete(thumbNail);
				}
			} catch(Exception e) {
				
				log.error("delete file error" + e.getMessage());
				
			}
		});
	}
}
