package com.bleeqer.service;

import java.util.List;

import com.bleeqer.domain.BoardVO;

public interface BoardService {
	
	public void register(BoardVO baord);
	
	public BoardVO get(Long bno);
	
	public boolean modify(BoardVO board);
	
	public boolean remove(Long bno);
	
	public List<BoardVO> getList();
	
}
