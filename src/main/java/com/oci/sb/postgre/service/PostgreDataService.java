package com.oci.sb.postgre.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.oci.sb.postgre.model.PostgreUser;

@Service
public class PostgreDataService {

	@Value("${postgre.url}")
	private String postgreUrl;

	@Value("${postgre.user}")
	private String postgreUser;

	@Value("${postgre.password}")
	private String postgrePassword;

	public void addUser(PostgreUser user) throws Exception{
		final String INSERT_USERS_SQL = "INSERT INTO users" + "  (id, name, email, country, password) VALUES "
				+ " (?, ?, ?, ?, ?);";
		try {
			Connection connection = DriverManager.getConnection(postgreUrl, postgreUser, postgrePassword);
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getCountry());
			preparedStatement.setString(5, user.getPassword());
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(System.err);
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println("Message: " + e.getMessage());
			Throwable t = e.getCause();
			while (t != null) {
				System.out.println("Cause: " + t);
				t = t.getCause();
			}
			throw e;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
}
