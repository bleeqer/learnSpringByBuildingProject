package com.bleeqer.service;

import java.util.List;

import com.bleeqer.domain.Criteria;
import com.bleeqer.domain.ReplyPageDTO;
import com.bleeqer.domain.ReplyVO;

public interface ReplyService {
	
	public int create(ReplyVO vo);
	
	public ReplyVO get(Long rno);
	
	public int modify(ReplyVO vo);
	
	public int remove(Long rno);
	
	public List<ReplyVO> getList(Criteria cri, Long bno);
	
	public ReplyPageDTO getListPage(Criteria cri, Long bno);
}
