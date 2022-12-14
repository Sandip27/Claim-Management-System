package com.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.demo.Model.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long>{

	@Query(value="from Claim as c inner join c.member as m where m.memberId=?1")
	public Claim getClaimByMemberId(Long id);

	@Query(value="select count(c) from Claim c where c.claimStatus=?1")
	public Long getTotalPendingClaims(String status);
	
	@Query(value="select count(c) from Claim c where c.claimStatus=?1")
	public Long getTotalApprovedClaims(String status);

}
