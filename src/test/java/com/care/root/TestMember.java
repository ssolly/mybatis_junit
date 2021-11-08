package com.care.root;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.care.root.member.controller.MemberController;
import com.care.root.member.dao.MemberDAO;
import com.care.root.member.service.MemberService;

@RunWith(SpringRunner.class)	//test동작 시킬 때 필수
@ContextConfiguration(locations= {"classpath:TestMember.xml",	//resources에서부터 위치를 잡는 것(main-main,test-test끼리)
								  "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestMember {

	@Autowired MemberController mc;
	@Autowired MemberService ms;
	@Autowired MemberDAO dao;
	
	@Test
	public void testMc() {
		System.out.println("mc : " + mc);
		assertNotNull(mc);		//null이 아니면 성공
	}
	
	@Test
	public void testMs() {
		assertNotNull(ms);
	}
	
	@Test
	public void testDao() {
		assertNotNull(dao);
	}
}
