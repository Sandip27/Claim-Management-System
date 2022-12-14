package com.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Model.Claim;
import com.demo.Model.Member;
import com.demo.Repository.ClaimRepository;


@Service
public class ClaimService {
	
	@Autowired
	private ClaimRepository claimRepository;
	
	public Claim getClaimDetailsById(Long id) {
		Claim claim = claimRepository.findById(id).get();
		return claim;		
	}

	public List<Claim> getAllClaims() {
		List<Claim> claims = claimRepository.findAll();
		return claims;
	}
	
	public Claim addClaim(Claim claim) {
		return claimRepository.save(claim);
	}
	
	public void deleteClaimById(Long id) {
		claimRepository.deleteById(id);
	}
	
	public Optional<Claim> findById(Long id) {
		Optional<Claim> claim =  claimRepository.findById(id);
		return claim;
	}

	public Long getTotalClaims() {
		return claimRepository.count();
	}
	
	
	public Claim getClaimByMemberId(Long id) {
		return claimRepository.getClaimByMemberId(id);
	}

	public Long getTotalPendingClaims(String status) {
		Long total = claimRepository.getTotalPendingClaims(status);
		return total;
	}

	public Long getTotalApprovedClaims(String status) {
		Long total = claimRepository.getTotalPendingClaims(status);
		return total;
	}

	

}
