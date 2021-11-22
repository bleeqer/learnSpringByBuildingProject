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
	
	// 2�� �̻��� �����͸� �Ķ���ͷ� �����ϱ����� MyBatis������ Param�� ����� �̸��� ����� �� ����
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri,
			@Param("bno") Long bno);
}
