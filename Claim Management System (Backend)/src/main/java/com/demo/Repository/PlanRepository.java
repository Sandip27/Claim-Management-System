package com.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.Model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long>{

}
