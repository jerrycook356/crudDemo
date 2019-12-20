package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
@Table(name = "employee")

public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		//get current session
		Session currentSession = entityManager.unwrap(Session.class);
		//make query
		Query theQuery = currentSession.createQuery("from employee",Employee.class);
		//execute query
		List<Employee> employees = theQuery.getResultList();
		return employees;
	}

	@Override
	public Employee findById(int employeeId) {
		//get current session
		Session currentSession = entityManager.unwrap(Session.class);
		//get employee by id
		Employee employee = currentSession.get(Employee.class, employeeId);
		//return employee
		return employee;
	}

	@Override
	public void save(Employee employee) {
		//get current session
		Session currentSession = entityManager.unwrap(Session.class);
		//save or update, save if id is zero otherwise update
		currentSession.saveOrUpdate(employee);

	}

	@Override
	public void deleteById(int employeeId) {
		//get current session
		Session currentSession = entityManager.unwrap(Session.class);
		//make query
		Query theQuery = currentSession.createQuery(
				"delete from employee where id:=employeeId");
		//execute query
		theQuery.executeUpdate();

	}

}
