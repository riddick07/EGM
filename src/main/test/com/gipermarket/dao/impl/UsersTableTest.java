package com.gipermarket.dao.impl;

import java.util.List;

import com.gipermarket.dao.bean.User;

public class UsersTableTest {

	public static void main(String[] args) {

		UsersDao dao = new UsersDao();
		
		// Read
		System.out.println("******* READ *******");
		List<User> users = dao.usersList();
		
		
		
		for (User user : users) {
			System.out.println(user.getName());
		}
		
		
		
		
		System.out.println("Total Employees: " + users.size());
		
		// Write
		System.out.println("******* WRITE *******");
		// Update
		System.out.println("******* UPDATE *******");
		
		// Delete
		System.out.println("******* DELETE *******");
	}

}
