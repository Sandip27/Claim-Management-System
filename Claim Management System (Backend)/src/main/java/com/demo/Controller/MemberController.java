package com.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Common.ApiResponse;
import com.demo.Model.Member;
import com.demo.Service.MemberService;
import com.demo.dto.Login;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/{id}")
	public Member getMemberDetailById(@PathVariable("id") Long id){
		Member member = memberService.getMemberDetailsById(id);
		return member;
	}
	
	@GetMapping("/all")
	public List<Member> getAllMembers() {
		 List<Member> members = memberService.getAllMembers();
		 return members;
	}
	
	@GetMapping("/total")
	public Long getTotalMembers() {
		Long total = memberService.getTotalMembers();
		return total;
	}
	
	@PostMapping("/add")
	public Member save(@RequestBody Member member) {
		return memberService.addMember(member);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteMember(@PathVariable("id") long id) {
		memberService.deleteMemberById(id);
	}
	
	@PutMapping("/update/{id}")
	public Member updateDetails(@PathVariable("id") Long id, @RequestBody Member newMember) {
		
		Optional<Member> optional = memberService.findById(id);
	
		if(!optional.isPresent())
			throw new RuntimeException("ID is Invalid");	
		
		Member oldMember = optional.get();
		
		oldMember.setMemberName(newMember.getMemberName());
		oldMember.setEmailId(newMember.getEmailId());
		oldMember.setContactNo(newMember.getContactNo());
		oldMember.setDob(newMember.getDob());
		oldMember.setCity(newMember.getCity());
		oldMember.setState(newMember.getState());
		
		return memberService.addMember(oldMember);
	}
	
	
	@PostMapping("/loginCheck")
	public ResponseEntity<ApiResponse> loginCheck(@RequestBody Login login){
		String status = null;
		
		ApiResponse apiResponse = memberService.LoginCheck(login);
		
		if(apiResponse!=null) {
			status = "Successfully Logged In !";
		}else {
			status = "Please check your Username and Password";
		}
		
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
}
