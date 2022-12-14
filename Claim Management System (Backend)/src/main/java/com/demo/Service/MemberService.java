package com.demo.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Common.ApiResponse;
import com.demo.Model.Member;
import com.demo.Repository.MemberRepository;
import com.demo.Utils.jwtUtils;
import com.demo.dto.Login;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private jwtUtils jwtUtils;
	
	public Member getMemberDetailsById(Long id) {
		Member member = memberRepository.findById(id).get();
		return member;
	}
	
	public List<Member> getAllMembers() {
		List<Member> members = memberRepository.findAll();
		return members;
	}
	
	public Member addMember(Member member) {
		return memberRepository.save(member);
	}
	
	public void deleteMemberById(Long id) {
		memberRepository.deleteById(id);
	}
	
	public Optional<Member> findById(Long id) {
		Optional<Member> member =  memberRepository.findById(id);
		return member;
	}

	public Long getTotalMembers() {
		return memberRepository.count();
	}
	
	public ApiResponse LoginCheck(Login login) {
		ApiResponse apiResponse = new ApiResponse();
		Member member = memberRepository.findByusernameAndPassword(login.getUsername(), login.getPassword());
		
		if(member == null) {
			apiResponse.setData("Username and Password is wrong!");
		}
		else {		
			String token = jwtUtils.generateJwt(member);
			
			Map<String, Object> data = new HashMap<>();
			data.put("accessToken", token);
			apiResponse.setData(data);
		}
		
		return apiResponse;	
	}

	
}

	

