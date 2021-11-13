package com.bleeqer.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bleeqer.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardmapperTests {
	
//	onMethod executes something when the method is generated
//	Autowired finds the bean and inject it(mapper in this case)
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
//	@Test
//	public void testGetList() {
//		mapper.getList().forEach(board -> log.info(board));
//	}
	
//	@Test
//	public void testInsert() {
//		
//		BoardVO board = new BoardVO();
//		board.setTitle("creating new post");
//		board.setContent("new content");
//		board.setWriter("newbie");
//		
//		mapper.insert(board);
//		
//		log.info(board);
	
//	@Test
//	public void testInsertSelectKey() {
//		
//		BoardVO board = new BoardVO();
//		board.setTitle("���� �ۼ��ϴ� �� select key");
//		board.setTitle("creating new post select key");
//		board.setContent("new content select key");
//		board.setWriter("newbie select key");
//		
//		mapper.insertSelectKey(board);
//		
//		log.info(board);
//	}
	
//	@Test public void testRead() {
//		
////		suffix L means that we have a long literal
//		BoardVO board = mapper.read(5L);
//		
//		log.info(board);
//	}
	
//	@Test
//	public void testDelete() {
//		
//		log.info("DELETE COUNT: " + mapper.delete(3L));
//	}
	
	@Test
	public void testUpdate() {
		
		BoardVO board = new BoardVO();
//		������ �����ϴ� ��ȣ���� Ȯ���� ��
		board.setBno(5L);
		board.setTitle("������ ����");
		board.setContent("������ ����");
		board.setWriter("user00");
		
		int count = mapper.update(board);
		log.info("UPDATE COUNT: " + count);
	}
}
	