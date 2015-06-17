package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

import static com.example.fw.ContactHelper.CREATION;
import static com.example.tests.ContactDataGenerator.loadContactsFromXmlFile;

public class ContactCreationTests extends TestBase {
	
  @DataProvider
	public Iterator<Object[] > contactsFromFile() throws IOException {
	  return wrapContactsForDataProvider(loadContactsFromXmlFile(new File("contacts.xml"))).iterator();
  }
	

  @Test(dataProvider = "contactsFromFile")
  public void testNonEmptyContactCreation(ContactData contact) throws Exception {
   
    // save old state
	 SortedListOf<ContactData> oldList	= new SortedListOf<ContactData>(app.getModel().getContacts());
       
	// actions
    app.getContactHelper().createContact(contact, CREATION);
    
    // save new state
	 SortedListOf<ContactData> newList	= new SortedListOf<ContactData>(app.getModel().getContacts());
    
    // compare states  
	assertThat(newList, equalTo(oldList.withAdded(contact)));
	
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