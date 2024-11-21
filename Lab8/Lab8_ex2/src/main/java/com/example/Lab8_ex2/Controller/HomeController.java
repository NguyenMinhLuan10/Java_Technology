package com.example.Lab8_ex2.Controller;

import com.example.Lab8_ex2.Model.Employee;
import com.example.Lab8_ex2.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class HomeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public String home(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "index";
    }

    @GetMapping("/add")
    public String add() {
        return "add";
    }

    @PostMapping("/add")
    public String addEmployee(@RequestParam("name") String name,
                              @RequestParam("email") String email,
                              @RequestParam("address") String address,
                              @RequestParam("phone") String phone) {

        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setAddress(address);
        employee.setPhone(phone);

        employeeService.saveEmployee(employee);

        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);

        model.addAttribute("employee", employee);

        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable("id") Long id,
                                 @RequestParam("name")String name,
                                 @RequestParam("email") String email,
                                 @RequestParam("address") String address,
                                 @RequestParam("phone") String phone) {

        Employee employee = employeeService.getEmployeeById(id);
        employee.setName(name);
        employee.setEmail(email);
        employee.setAddress(address);
        employee.setPhone(phone);
        employeeService.saveEmployee(employee);

        return "redirect:/employees";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}
