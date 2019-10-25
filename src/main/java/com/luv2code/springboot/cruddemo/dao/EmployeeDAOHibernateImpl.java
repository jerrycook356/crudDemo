package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	@Override
	public List<Employee> findAll() {
		//get current session
		Session currentSession = entityManager.unwrap(Session.class);
		//create query
		Query<Employee> theQuery = currentSession.createQuery(
				"from Employee",Employee.class);
		
		//execute query and return result
		List<Employee> employees = theQuery.getResultList();
		return employees;
	}

	@Override
	public Employee findById(int employeeId) {
		//get current session
		Session currentSession = entityManager.unwrap(Session.class);
		//create employee and return it
		Employee employee = currentSession.get(Employee.class, employeeId);
		if(employee == null) {
			throw new RuntimeException("Employee not found - "+employeeId);
		}
		
		return employee;
	}

	@Override
	public void save(Employee employee) {
		//get current session
		Session currentSession = entityManager.unwrap(Session.class);
		//save or update the employee
		//if id is 0 employee will be saved, otherwise updated
		currentSession.saveOrUpdate(employee);

	}

	@Override
	public void deleteById(int employeeId) {
		//get current session
		Session currentSession = entityManager.unwrap(Session.class);
		//make query and execute
		Query theQuery = currentSession.createQuery(
				"delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId",employeeId);
		theQuery.executeUpdate();

	}

}
