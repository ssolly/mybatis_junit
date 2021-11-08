package com.care.root;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.care.root.member.controller.MemberController;

@RunWith(SpringRunner.class)	//test동작 시킬 때 필수
@ContextConfiguration(locations= {"classpath:TestMember.xml",	//resources에서부터 위치를 잡는 것(main-main,test-test끼리)
								  "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestMock {

	@Autowired MemberController mc;
	MockMvc mock;
	
	@Before //셋팅할 때 먼저 처리해주기 위한 어노테이션 : 초기화용도
	public void setUp() {
		System.out.println("--- test 실행 전 ---");
		mock = MockMvcBuilders.standaloneSetup(mc).build();	//컨트롤러와 연결
	}
	
    @Test
    public void testIndex() throws Exception{
            System.out.println("----- test코드 실행");
            mock.perform(get("/index")) 				//경로, get방식, 경로 앞에 무조건 / 들어와야한다
            .andDo(print())
            .andExpect(status().isOk())					//200이여야 성공
            .andExpect(forwardedUrl("member/index"));	//리턴경로 확인
    }

    @Test
    @Transactional(transactionManager = "txMgr")	//root-context.xml에서 세팅
    public void testInsert() throws Exception {
    	mock.perform(post("/insert").param("id","999").param("name","김이름"))
    	.andDo(print())
    	.andExpect(status().is3xxRedirection());	//redirect라면 성공 : 302
    }
    
    @Test
    public void testMemberview() throws Exception {
       mock.perform(get("/memberview"))
       .andDo(print())
       .andExpect(status().isOk())						//200이여야 성공
       .andExpect(forwardedUrl("member/memberview"))
       .andExpect(model().attributeExists("list"));		//model에 list라는 값이 존재하는지 확인
    }

}
