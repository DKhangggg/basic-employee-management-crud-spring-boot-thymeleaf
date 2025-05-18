package com.luv2code.springboot.thyleafdemo.controller;

import com.luv2code.springboot.thyleafdemo.entity.Employee;
import com.luv2code.springboot.thyleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }
    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        List<Employee> theEmployees = employeeService.findAll();
        theModel.addAttribute("employees", theEmployees);
        return "employees/list-employees";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);
        return "employees/showFormForAdd";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee")Employee employee){
        employeeService.save(employee);
        return "redirect:/employees/list";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForAdd(@RequestParam("id") int id,Model model) {
        // create model attribute to bind form data
        Employee theEmployee = employeeService.findById(id);
        model.addAttribute("employee", theEmployee);
        return "employees/showFormForUpdate";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") int id) {
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }
}
