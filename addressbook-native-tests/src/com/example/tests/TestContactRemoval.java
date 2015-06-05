package com.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.fw.Contact;

public class TestContactRemoval extends TestBase{
	@Test
	public void shouldDeleteContact() throws InterruptedException {
		Contact createdContact= app.getContactHelper().getFirstContact();
		app.getContactHelper().deleteContact();
		int resultDeletion=app.getContactHelper().checkContactDeletion(createdContact);
	    Assert.assertNotEquals(resultDeletion, 0);
		app.getContactHelper().exitAddressBook();
	}
	
}
