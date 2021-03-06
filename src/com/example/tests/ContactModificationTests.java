package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Random;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class ContactModificationTests extends TestBase {
 
	@Test(dataProvider = "randomValidContactGenerator")
	public void modifySomeContact(ContactData contact) {
		app.navigateTo().mainPage();
		
	    // save old state
		 SortedListOf<ContactData> oldList	= new SortedListOf<ContactData>(app.getModel().getContacts());
	    
	    Random rnd = new Random();
	    int index = rnd.nextInt(oldList.size()-1);
	    
	    // actions
	    app.getContactHelper().modifyContact(index, contact, false);
		
	    // save new state
		 SortedListOf<ContactData> newList	= new SortedListOf<ContactData>(app.getModel().getContacts());
	    
	    // compare states  
		assertThat(newList, equalTo(oldList.without(index).withAdded(contact)));
		
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
