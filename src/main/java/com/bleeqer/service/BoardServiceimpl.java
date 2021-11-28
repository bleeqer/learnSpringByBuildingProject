package com.bleeqer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bleeqer.domain.BoardAttachVO;
import com.bleeqer.domain.BoardVO;
import com.bleeqer.domain.Criteria;
import com.bleeqer.mapper.BoardAttachMapper;
import com.bleeqer.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceimpl implements BoardService {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardAttachMapper attachMapper;
	
	@Transactional
	@Override
	public void create(BoardVO board) {
		
		log.info("create......" + board);
		
		mapper.insertSelectKey(board);
		
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return;
		}
		
		board.getAttachList().forEach(attach -> {
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
	}
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		
		log.info("getList........");
		
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public BoardVO get(Long bno) {
		
		log.info("get......" + bno);
		
		return mapper.read(bno);
	}
	
	@Override
	public boolean modify(BoardVO board) {
		
		log.info("modify....." + board);
		
		attachMapper.deleteAll(board.getBno());
		
		boolean modifyResult = mapper.update(board) == 1;
		
		if (modifyResult && board.getAttachList() != null && board.getAttachList().size() > 0) {
			
			board.getAttachList().forEach(attach -> {
				
				attach.setBno(board.getBno());
				
				attachMapper.insert(attach);
			});
		}
		
		return mapper.update(board) == 1;
	}
	
	@Transactional
	@Override
	public boolean remove(Long bno) {
		
		log.info("remove....." + bno);
		
		attachMapper.deleteAll(bno);
		
		return mapper.delete(bno) == 1;
	}
	
	@Override
	public int getTotal(Criteria cri) {
		
		log.info("get total count");
		
		return mapper.getTotalCount(cri);
	}
	
	
	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		
		log.info("getAttach list by bno" + bno);
		
		return attachMapper.findByBno(bno);
	}

}
