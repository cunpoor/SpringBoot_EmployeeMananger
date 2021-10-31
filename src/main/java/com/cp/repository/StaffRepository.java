package com.cp.repository;

import com.cp.entity.Staff;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff,Long>{
    
}
