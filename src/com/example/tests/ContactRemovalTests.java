package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

import java.util.Random;

public class ContactRemovalTests extends TestBase{
	
	@Test
	public void deleteSomeContact() throws Exception {
		app.navigateTo().mainPage();
		
		// save old state
		 SortedListOf<ContactData> oldList	= new SortedListOf<ContactData>(app.getModel().getContacts());
		
	    Random rnd = new Random();
	    int index = rnd.nextInt(oldList.size());
	    
	    // actions		
		app.getContactHelper().deleteContact(index);
	
		// save new state
		 SortedListOf<ContactData> newList	= new SortedListOf<ContactData>(app.getModel().getContacts());
    
		// compare states  
		assertThat(newList, equalTo(oldList.without(index)));
		
		if ("yes".equals(app.getProperty("check.db"))) {
			if (wantToCheck()) {
				assertThat(app.getModel().getContacts(), equalTo(app.getHibernateHelper().listContacts()));
			}
		if ("yes".equals(app.getProperty("check.ui"))) {
			if (wantToCheck()) {
				assertThat(app.getModel().getContacts(), equalTo(app.getContactHelper().getUiContacts()));
				}	
			}
		}
	   
    }

}
