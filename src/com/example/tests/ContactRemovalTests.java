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
		SortedListOf<ContactData> oldList = app.getContactHelper().getContacts();		
		
	    Random rnd = new Random();
	    int index = rnd.nextInt(oldList.size());
	    
	    // actions		
		app.getContactHelper().deleteContact(index);
	
		// save new state
		SortedListOf<ContactData> newList = app.getContactHelper().getContacts();
    
		// compare states  
		assertThat(newList, equalTo(oldList.without(index)));
    }

}
