package com.bleeqer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bleeqer.domain.BoardVO;
import com.bleeqer.domain.Criteria;

public interface BoardMapper {
	
//  BoardMapper.xml���� �����ϹǷ� �ּ�ó��
//	@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
//	�˻����� �ʿ��ϱ� ������ �ϴ� Criteria �޾Ƶ�
	public int getTotalCount(Criteria cri);
	
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount); // MyBatis�� �⺻������ 1���� �Ķ���͸� ó���ϱ� ������ 2�� �̻��� �����͸� �����Ϸ��� @Param�̶�� ������̼��� �����
}
