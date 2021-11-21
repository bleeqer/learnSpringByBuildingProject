package com.bleeqer.mapper;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bleeqer.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	private Long[] bnoArr = {241L, 240L, 239L, 238L, 237L}; 
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	/*
	 * @Test public void testmapper() {
	 * 	
	 * log.info(mapper); }
	 */
	
	/*
	 * @Test public void testCreate() {
	 * 
	 * IntStream.rangeClosed(1, 10).forEach(i -> {
	 * 
	 * ReplyVO vo = new ReplyVO();
	 * 
	 * // �Խù� ��ȣ vo.setBno(bnoArr[i % 5]); vo.setReply("��� �׽�Ʈ " + i);
	 * vo.setReplyer("replyer" + i);
	 * 
	 * mapper.insert(vo); }); }
	 */
	
	/*
	 * @Test public void testRead() {
	 * 
	 * Long targetRno = 5L;
	 * 
	 * ReplyVO vo = mapper.read(targetRno);
	 * 
	 * log.info(vo); }
	 */
	
	/*
	 * @Test public void testDelete() {
	 * 
	 * Long targetRno = 1L;
	 * 
	 * mapper.delete(targetRno);
	 * 
	 * }
	 */
	
	@Test
	public void testUpdate() {
		
		Long targetRno = 10L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("Update Reply ");
		
		int count = mapper.update(vo);
		
		log.info("UPDATE COUNT: " + count);
	}
	
}
	