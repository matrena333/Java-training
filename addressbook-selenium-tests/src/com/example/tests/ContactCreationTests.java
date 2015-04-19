package com.example.tests;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
	
  @Test
  public void testNonEmptyContactCreation() throws Exception {
    openMainPage();
    initContactCreation();
    ContactData contact = new ContactData();
    contact.firstname = "firstname";
    contact.lastname = "lastname";
    contact.address = "address1";
    contact.homephone = "homephone";
    contact.mobilephone = "mobilephone";
    contact.workphone = "workphone";
    contact.email1 = "email1@email.com";
    contact.email2 = "email2@email.com";
    contact.bday = "15";
    contact.bmonth = "June";
    contact.byear = "1985";
    contact.group = "Group 1";
    contact.address2 = "Address2";
    contact.homephone2 = "homephone2";
	fillContactForm(contact);
    submitContactCreation();
    
  }
}
