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

@WebAppConfiguration // ServletContext를 사용하기 위함

@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@Log4j
public class BoardControllerTests {
	
//	AllargsConstructor는 모든 속성에 대해 주입하기 때문에 해당 클래스에서는 Autowired를 사용함
	@Setter(onMethod_ = {@Autowired} )
	private WebApplicationContext ctx;
	
//	가짜 URL이나 파라미터등을 브라우저에서 사용하는 것처럼 만들어서 Controller를 실행해 볼 수 있음

	private MockMvc mockMvc;
	
	@Before // Junit의 Before 어노테이션으로 모든 테스트에 앞서 매번 실행되는 메소드를 설정
	public void setup() {
//		해당 Context에 MockMvc를 빌드함
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
//				.param("title", "테스트 새 글 제목 입니다.")
//				.param("content", "테스트 새 글 내용")
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
////				bno라는 파라미터라 필요하므로 전달
//				.param("bno", "4"))
////				리턴값의 모델과 뷰를 받아서 모델 맵 객체 출력하기
//				.andReturn().getModelAndView().getModelMap()
//				);
//	}
	
//	@Test
//	public void testModify() throws Exception {
//		
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders
//				.post("/board/modify")
////				bno라는 파라미터라 필요하므로 전달
//				.param("bno", "4")
//				.param("title", "수정된 테스트 새 글 제목")
//				.param("content", "수정된 테스트 새 글 내용")
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
