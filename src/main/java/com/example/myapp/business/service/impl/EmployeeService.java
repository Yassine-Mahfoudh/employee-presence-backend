package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IEmployeeService;
import com.example.myapp.persistence.model.Employee;
import com.example.myapp.persistence.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class EmployeeService implements IEmployeeService {

    public final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getListEmployee() {
        try {
            return employeeRepository.findAll();
        } catch (Exception e){
            throw new IllegalStateException("Error EmployeeService in method getListEmployee :: " + e.toString());

        }
    }

    @Override
    public Employee getEmployeeById(Long id){
        try {
            if (id == null)
                return new Employee();
            Employee e = employeeRepository.findEmployeeById(id);
            if (e == null)
                return new Employee();
            return e;
        } catch (Exception e){
            throw new IllegalStateException("Error EmployeeService in method getEmployeeById :: " + e.toString());
        }

    }



    @Override
    public Employee addEmployee(Employee emp) {
        try {
            Employee empNomUnique = employeeRepository.findEmployeeByLastname(emp.getLastname());

            if ( empNomUnique != null)
                throw new IllegalStateException("Employee name token");

            emp.setCreationdate(new Timestamp(new Date().getTime()));

            return employeeRepository.save(emp);
        } catch (Exception e) {
            throw new IllegalStateException("Error EmployeeService in method addEmployee :: " + e.toString());
        }
    }
    @Transactional
    @Override
    public Employee updateEmployeeById(Employee employee,Long id) {
        try {
            Employee upemp = employeeRepository.findEmployeeById(id);
            upemp.setLastname(employee.getLastname());
            upemp.setFirstname(employee.getFirstname());
            upemp.setRole(employee.getRole());
            upemp.setStatus(employee.getStatus());
            upemp.setBirthdate(employee.getBirthdate());
            upemp.setAddress(employee.getAddress());
            upemp.setPhonenumber(employee.getPhonenumber());
            upemp.setProject(employee.getProject());
            upemp.setSalle(employee.getSalle());

            upemp.setUpdatedate(new Timestamp(new Date().getTime()));
            upemp.setId(id);

            return employeeRepository.save(upemp);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error EmployeeService in method updateEmployeeById :: " + e.toString());
        }
    }

    @Transactional
    @Override
    public void deleteEmployeeById(Long id) {
        try {
            employeeRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error EmployeeService in method deleteEmployeeById :: " + e.toString());
        }
    }
}
