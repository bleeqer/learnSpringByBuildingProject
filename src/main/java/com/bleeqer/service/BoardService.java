package com.bleeqer.service;

import java.util.List;

import com.bleeqer.domain.BoardVO;
import com.bleeqer.domain.Criteria;

public interface BoardService {
	
	public void create(BoardVO baord);
	
	public BoardVO get(Long bno);
	
	public boolean modify(BoardVO board);
	
	public boolean remove(Long bno);
	
	public List<BoardVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
	
}
