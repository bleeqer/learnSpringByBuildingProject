package com.bleeqer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
//	검색에서 필요하기 때문에 일단 Criteria 받아둠
	public int getTotalCount(Criteria cri);
	
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount); // MyBatis는 기본적으로 1개의 파라미터만 처리하기 때문에 2개 이상의 데이터를 전달하려면 @Param이라는 어노테이션을 사용함
}
