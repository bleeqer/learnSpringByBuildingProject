package com.bleeqer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bleeqer.domain.BoardVO;
import com.bleeqer.domain.Criteria;
import com.bleeqer.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor // 모든 파라미터를 이용하는 생성자를 만들기 때문에 BoardMapper를 주입받는 생성자가 만들어지게 됨
public class BoardServiceimpl implements BoardService {
	
	private BoardMapper mapper;
	
	@Override
	public void create(BoardVO board) {
		
		log.info("create......" + board);
		
		mapper.insertSelectKey(board);
	}
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		
		log.info("getList........");
		
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public BoardVO get(Long bno) {
		
		log.info("get......" + bno);
		
		return mapper.read(bno);
	}
	
	@Override
	public boolean modify(BoardVO board) {
		
		log.info("modify....." + board);
		
		return mapper.update(board) == 1;
	}
	
	@Override
	public boolean remove(Long bno) {
		
		log.info("remove....." + bno);
		
		return mapper.delete(bno) == 1;
	}
	
	@Override
	public int getTotal(Criteria cri) {
		
		log.info("get total count");
		
		return mapper.getTotalCount(cri);
	}
	


}
