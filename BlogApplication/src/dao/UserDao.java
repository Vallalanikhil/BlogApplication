package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import utility.ConnectionManager;

public class UserDao implements UserDaoInterface {
	@Override
	public int signup(User user) {
	//	String insert="insert into users(email,password)values(?,?)";
		int result=0;
		try {
			System.out.println("email "+user.getEmail());
			System.out.println("password "+user.getPassword());
			Connection con=ConnectionManager.getConnection();
			PreparedStatement ps=con.prepareStatement("INSERT INTO USERS(email,password)VALUES(?,?)");
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			System.out.println(ps);
			result=ps.executeUpdate();
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean loginUser(User user) {
		boolean status=false;
		try {
			Connection con=ConnectionManager.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from users where email = ? and password = ?");
			ps.setString(1, user.getEmail());
			ps.setString(2,user.getPassword());
			System.out.println(ps);
			ResultSet rs=ps.executeQuery();
			status=rs.next();
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return status;
	}
	
	
	

}