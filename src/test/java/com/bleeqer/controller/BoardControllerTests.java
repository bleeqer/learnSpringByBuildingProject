package com.bleeqer.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration // ServletContext�� ����ϱ� ����

@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@Log4j
public class BoardControllerTests {
	
//	AllargsConstructor�� ��� �Ӽ��� ���� �����ϱ� ������ �ش� Ŭ���������� Autowired�� �����
	@Setter(onMethod_ = {@Autowired} )
	private WebApplicationContext ctx;
	
//	��¥ URL�̳� �Ķ���͵��� ���������� ����ϴ� ��ó�� ���� Controller�� ������ �� �� ����

	private MockMvc mockMvc;
	
	@Before // Junit�� Before ������̼����� ��� �׽�Ʈ�� �ռ� �Ź� ����Ǵ� �޼ҵ带 ����
	public void setup() {
//		�ش� Context�� MockMvc�� ������
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		System.out.println(this.mockMvc);
	}
	
//	@Test
//	public void testList() throws Exception {
//		
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
//				.andReturn()
//				.getModelAndView()
//				.getModelMap()
//				);
//	}
	
//	@Test
//	public void testCreate() throws Exception {
//		
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/create")
//				.param("title", "�׽�Ʈ �� �� ���� �Դϴ�.")
//				.param("content", "�׽�Ʈ �� �� ����")
//				.param("writer", "user00"))
//		.andReturn().getModelAndView().getViewName();
//		
//		log.info(resultPage);
//	}

//	@Test
//	public void testGet() throws Exception {
//		
//		log.info(mockMvc.perform(MockMvcRequestBuilders
//				.get("/board/get")
////				bno��� �Ķ���Ͷ� �ʿ��ϹǷ� ����
//				.param("bno", "4"))
////				���ϰ��� �𵨰� �並 �޾Ƽ� �� �� ��ü ����ϱ�
//				.andReturn().getModelAndView().getModelMap()
//				);
//	}
	
//	@Test
//	public void testModify() throws Exception {
//		
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders
//				.post("/board/modify")
////				bno��� �Ķ���Ͷ� �ʿ��ϹǷ� ����
//				.param("bno", "4")
//				.param("title", "������ �׽�Ʈ �� �� ����")
//				.param("content", "������ �׽�Ʈ �� �� ����")
//				.param("writer", "user00")
//				)
//				.andReturn().getModelAndView().getViewName();
//		
//		log.info(resultPage);
//	}
	
	@Test
	public void testRemove() throws Exception {
		
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "23")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
}
