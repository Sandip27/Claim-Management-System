package com.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.Model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	Member findByusernameAndPassword(String username, String password);

}
