package com.luv2code.springboot.cruddemo.EmployeeService;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;
	
	public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		List<Employee> employees = employeeDAO.findAll();
		return employees;
	}

	@Override
	@Transactional
	public Employee findById(int employeeId) {
		Employee employee = employeeDAO.findById(employeeId);
		return employee;
	}

	@Override
	@Transactional
	public void save(Employee employee) {

		employeeDAO.save(employee);
	}

	@Override
	@Transactional
	public void deleteById(int employeeId) {
		employeeDAO.deleteById(employeeId);
	}

}
