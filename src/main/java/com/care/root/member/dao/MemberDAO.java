package com.care.root.member.dao;

import java.util.ArrayList;

import com.care.root.member.dto.MemberDTO;

public interface MemberDAO {	//보통은 mapper로 많이 생성(MemberMapper), db와 "연결"만 진행
	
	public void insertMember(MemberDTO dto);
	public ArrayList<MemberDTO> memberView();
}
