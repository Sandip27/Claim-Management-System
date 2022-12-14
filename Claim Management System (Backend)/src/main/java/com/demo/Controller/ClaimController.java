package com.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Model.Claim;
import com.demo.Model.Member;
import com.demo.Service.ClaimService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/claim")
public class ClaimController {
	
	@Autowired
	private ClaimService claimService;
	
	@GetMapping("/all")
	public List<Claim> getAllClaims(){
		List<Claim> claims = claimService.getAllClaims();
		return claims;
	}
	
	@GetMapping("/{id}")
	public Claim getClaimDetailById(@PathVariable("id") Long id){
		Claim claim = claimService.getClaimDetailsById(id);
		return claim;
	}
	
	@GetMapping("/total")
	public Long getTotalClaims() {
		Long total = claimService.getTotalClaims();
		return total;
	}
	
	@GetMapping("/fetchMember/{id}")
	public Claim getClaimByMemberId(@PathVariable("id") Long id) {
		Claim claim = claimService.getClaimByMemberId(id);
		return claim;
	}
	
	@GetMapping("/total/pending")
	public Long getTotalPendingClaims() {
		Long total = claimService.getTotalPendingClaims("Pending");
		return total;
	}
	
	@GetMapping("/total/approved")
	public Long getTotalApprovedClaims() {
		Long total = claimService.getTotalApprovedClaims("Approved");
		return total;
	}
	
	@PostMapping("/add")
	public Claim save(@RequestBody Claim claim) {
		return claimService.addClaim(claim);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteClaim(@PathVariable("id") long id) {
		claimService.deleteClaimById(id);
	}
	
	@PutMapping("/process/{id}")
	public Claim processClaim(@PathVariable("id") Long id, @RequestBody Claim newClaim) {
		Optional<Claim> optional = claimService.findById(id);
		
		if(!optional.isPresent())
			throw new RuntimeException("ID is Invalid");	
		
		Claim oldClaim = optional.get();
		
		oldClaim.setClaimStatus(newClaim.getClaimStatus());
		
		return claimService.addClaim(oldClaim);
	}
	
	
	
	
}
