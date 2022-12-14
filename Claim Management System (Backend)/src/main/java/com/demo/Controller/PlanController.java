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


import com.demo.Model.Plan;
import com.demo.Service.PlanService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/plan")
public class PlanController {
	
	@Autowired
	private PlanService planService;
	
	
	@GetMapping("/{id}")
	public Plan getPlanDetailById(@PathVariable("id") Long id){
		Plan plan = planService.getMemberDetailsById(id);
		return plan;
	}
	
	@GetMapping("/all")
	public List<Plan> getAllMembers() {
		 List<Plan> plans = planService.getAllPlans();
		 return plans;
	}
	
	@PostMapping("/add")
	public Plan save(@RequestBody Plan plan) {
		return planService.addPlan(plan);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteMember(@PathVariable("id") long id) {
		planService.deleteMemberById(id);
		return "Deleted";
	}
	
	@PutMapping("/update/{id}")
	public Plan updateDetails(@PathVariable("id") Long id, @RequestBody Plan newPlan) {
		
		Optional<Plan> optional = planService.findById(id);
	
		if(!optional.isPresent())
			throw new RuntimeException("ID is Invalid");	
		
		Plan oldPlan = optional.get();
		
		oldPlan.setPlanName(newPlan.getPlanName());
		oldPlan.setInsuredAmount(newPlan.getInsuredAmount());
		oldPlan.setStartDate(newPlan.getStartDate());
		oldPlan.setEndDate(newPlan.getEndDate());
		
		return planService.addPlan(oldPlan);
	}
	

}
