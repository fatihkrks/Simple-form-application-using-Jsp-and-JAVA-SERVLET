package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Employee;


public class EmployeeDAOImpl implements EmployeeDAO {

	private static final String INSERT_EMPLOYEE="insert into employee(name,surname,salary) values (?,?,?)";
	private static final String ALL_EMPLOYEE="select*from employee";
	private static final String DELETE_EMPLOYEE="delete from employee where id=?";
				
	private DataSource getDataSource() {
		ConnectionManager manager= new ConnectionManager();
		DataSource dataSource =manager.getMySqlDataSource();
		return dataSource;
	}
	
	@Override
	public void insertEmployee(String name, String surname, int salary) {
		DataSource dataSource=getDataSource();
		try {
			Connection connection=dataSource.getConnection();
			PreparedStatement ps=connection.prepareStatement(INSERT_EMPLOYEE);
			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setInt(3, salary);
			ps.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.err.println("An Error Occured!\n Error is:" + e);
		}
		
		
	}

	@Override
	public List<Employee> getEmployees() {
		DataSource dataSource=getDataSource();
		List<Employee> employees= new ArrayList<Employee>();
		try {
			Connection connection=dataSource.getConnection();
			PreparedStatement ps=connection.prepareStatement(ALL_EMPLOYEE);
			ResultSet resultSet=ps.executeQuery();
			while (resultSet.next()) {
				int id=resultSet.getInt("id");
				String name=resultSet.getString("name");
				String surname=resultSet.getString("surname");
				int salary=resultSet.getInt("salary");
				Employee employee=new Employee(id,name,surname,salary);
				employees.add(employee);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return employees;
	}

	@Override
	public void removeEmployee(int id) {
		DataSource dataSource=getDataSource();
		try {
			Connection connection=dataSource.getConnection();
			PreparedStatement ps=connection.prepareStatement(DELETE_EMPLOYEE);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			connection.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

}
