package com.bleeqer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bleeqer.domain.Criteria;
import com.bleeqer.domain.ReplyPageDTO;
import com.bleeqer.domain.ReplyVO;
import com.bleeqer.mapper.BoardMapper;
import com.bleeqer.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;

	@Override
	public int create(ReplyVO vo) {

		log.info("create......" + vo);
		
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		
		log.info("get......" + rno);
		
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {

		log.info("modify......" + vo);
		
		return mapper.update(vo);
	}
	
	@Transactional // tbl_board에도 영향을 미치므로 transaction 처리가 필요함
	@Override
	public int remove(Long rno) {

		log.info("remove...." + rno);
		
		// Bno를 구하기 위해 Rno로 데이터 조회
		ReplyVO vo = mapper.read(rno);
		
		// boardMapper의 댓글수카운터에 Bno와 갯수 전달
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		
		log.info("get reply list of a post " + bno);
		
		return mapper.getListWithPaging(cri, bno);
	}
	
	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		
		return new ReplyPageDTO(
				mapper.getCountByBno(bno),
				mapper.getListWithPaging(cri, bno));
	}

}
