package com.bleeqer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bleeqer.domain.BoardVO;
import com.bleeqer.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor // ��� �Ķ���͸� �̿��ϴ� �����ڸ� ����� ������ BoardMapper�� ���Թ޴� �����ڰ� ��������� ��
public class BoardServiceimpl implements BoardService {
	
	private BoardMapper mapper;
	
	@Override
	public void register(BoardVO board) {
		
		log.info("register......" + board);
		
		mapper.insertSelectKey(board);
	}
	
	@Override
	public List<BoardVO> getList() {
		
		log.info("getList........");
		
		return mapper.getList();
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
	


}
