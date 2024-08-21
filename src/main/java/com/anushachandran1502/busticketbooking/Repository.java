package com.anushachandran1502.busticketbooking;

import java.util.ArrayList;
import java.util.HashMap;

public class Repository 
{
	private static Repository repo=null;
	private static int count=1;
	private static ArrayList<User> users=new ArrayList<>();
	private Repository()
	{
		
	}

	public static Repository getInstance()
	{
		if(repo==null)
		{
			repo=new Repository();
		}
		return repo;
	}

	public boolean isValidUser(String userName, String password) 
	{
		if(users!=null)
		{
			for(User user : users)
			{
				if(user.getUserName().equals(userName)) 
				{
					if(user.getPassword().equals(password))
					{
						return true;
					}
					else
					{
						return false;
					}
				}
			}
		}
		return false;
	}
}
