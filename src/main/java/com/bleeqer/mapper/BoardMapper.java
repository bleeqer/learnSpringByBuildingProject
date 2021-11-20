package com.bleeqer.mapper;

import java.util.List;

import com.bleeqer.domain.BoardVO;
import com.bleeqer.domain.Criteria;

public interface BoardMapper {
	
//  BoardMapper.xml에서 설정하므로 주석처리
//	@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
}
