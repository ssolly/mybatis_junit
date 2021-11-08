package com.care.root.member.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;

public interface MemberService {

	public void insertMember(MemberDTO dto);
	public void memberView(Model model);
}
