package com.bleeqer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.bleeqer.domain.BoardVO;

public interface BoardMapper {
	
//  BoardMapper.xml에서 설정하므로 주석처리
//	@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
}
