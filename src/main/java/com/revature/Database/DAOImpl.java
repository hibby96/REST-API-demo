package com.revature.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.Objects.Person;

public class DAOImpl implements DAO {
	Connection connection = null;
    PreparedStatement stmt = null;
    
    public Person getPerson(int id) {
        try {
            connection = ConnectionUtil.getConnection();
            String query = "SELECT * FROM family WHERE \"Id\" = ?;";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	Person member = new Person(rs.getString("name"), rs.getInt("Id"));
				return member;
			} else {
				return null;
			}
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ran into an issue at the querying stage");
            return null;
		}
	}
	
	public boolean addPerson(Person person) {
		try {
			connection = ConnectionUtil.getConnection();
            String query = "INSERT INTO family (name) VALUES (?);";
            stmt = connection.prepareStatement(query);
			stmt.setString(1, person.getName());
			if (stmt.executeUpdate() != 0) {			
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
            System.out.println("Ran into an issue at the querying stage");
			return false;
		}
	}

	public boolean deletePerson(int id) {
		try {
            connection = ConnectionUtil.getConnection();
            String query = "DELETE FROM family WHERE \"Id\" = ?;";
            stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			if (stmt.executeUpdate() != 0) {			
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
            System.out.println("Ran into an issue at the querying stage");
			return false;
		}
	}

	public boolean updatePerson(Person person, int id) {
		try {
            connection = ConnectionUtil.getConnection();
            String query = "UPDATE family SET name = ? WHERE \"Id\" = ?;";
			stmt = connection.prepareStatement(query);
			stmt.setString(1, person.getName());
			stmt.setInt(2, id);
			if (stmt.executeUpdate() != 0) {			
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
            System.out.println("Ran into an issue at the querying stage");
			return false;
		}
	}

    protected void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}


	}

}
