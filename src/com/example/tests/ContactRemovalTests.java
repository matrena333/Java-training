package com.example.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ContactRemovalTests extends TestBase{
	
	@Test
	public void deleteSomeContact() throws Exception {
		app.getNavigationHelper().openMainPage();
		
		// save old state
	    List<ContactData> oldList = app.getContactHelper().getContacts();		
		
	    Random rnd = new Random();
	    int index = rnd.nextInt(oldList.size());
	    
	 // actions		
		app.getContactHelper().initContactModification(index);
		app.getContactHelper().deleteContact();
		app.getNavigationHelper().returnToMainPage();

	
	   // save new state
    List<ContactData> newList = app.getContactHelper().getContacts();
    
    // compare states  
    oldList.remove(index);
    Collections.sort(oldList);
    Collections.sort(newList);
    assertEquals(newList, oldList);
    
   }

}
