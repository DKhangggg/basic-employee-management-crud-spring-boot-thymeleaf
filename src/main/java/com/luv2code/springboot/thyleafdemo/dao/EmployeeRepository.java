package com.luv2code.springboot.thyleafdemo.dao;

import com.luv2code.springboot.thyleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //sortByLastNameAsc
    public List<Employee> findAllByOrderByLastNameAsc();

}
