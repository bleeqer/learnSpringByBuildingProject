package com.bleeqer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bleeqer.domain.Criteria;
import com.bleeqer.domain.ReplyVO;

public interface ReplyMapper {
	
	public int insert(ReplyVO vo);
	
	public ReplyVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(ReplyVO reply);
	
	// 2개 이상의 데이터를 파라미터로 전달하기위해 MyBatis에서는 Param을 사용해 이름을 사용할 수 있음
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri,
			@Param("bno") Long bno);
}
