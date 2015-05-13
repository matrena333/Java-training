package com.example.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.example.fw.ApplicationManager;

public class TestBase {
	
	protected static ApplicationManager app;

	@BeforeTest
	public void setUp() throws Exception {
		app = new ApplicationManager ();
	  }

	@AfterTest
	public void tearDown() throws Exception {
		app.stop();
	  }

	  @DataProvider
		public Iterator<Object[] > randomValidGroupGenerator() {
		  List<Object[]> list = new ArrayList<Object[]>();
		  for (int i = 0; i < 5; i++) {
			GroupData group = new GroupData()
				.withName(generateRandomString())
				.withHeader(generateRandomString())
				.withFooter(generateRandomString());
			list.add(new Object[]{group});
		}
		  return list.iterator();
	  }
	  


		@DataProvider
			public Iterator<Object[] > randomValidContactGenerator() {
			  List<Object[]> list = new ArrayList<Object[]>();
			  for (int i = 0; i < 5; i++) {
				ContactData contact = new ContactData()
					.withFirstname(generateRandomString())
					.withLastname(generateRandomString())
					.withAddress(generateRandomString())
					.withHomephone(generateRandomString())
					.withMobilephone(generateRandomString())
					.withWorkphone(generateRandomString())
					.withEmail1(generateRandomString())
					.withEmail2(generateRandomString())
					.withAddress2(generateRandomString())
					.withHomephone2(generateRandomString());
				list.add(new Object[]{contact});
			}
			  return list.iterator();
		  }

		
	  public String generateRandomString() {
		    Random rnd = new Random();
			if (rnd.nextInt(5) == 0) {
				return "";			
			}	else {
				return "test" + rnd.nextInt();
			} 
	  }

}
