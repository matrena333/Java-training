package com.example.tests;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
	
  @Test
  public void testNonEmptyContactCreation() throws Exception {
    openMainPage();
    initContactCreation();
    ContactData contact = new ContactData();
    contact.firstname = "Anna";
    contact.lastname = "Kulikova";
    contact.address = "Moscow";
    contact.homephone = "84956745346";
    contact.mobilephone = "89263546565";
    contact.workphone = "83647463535";
    contact.email1 = "email1@email.com";
    contact.email2 = "email2@email.com";
    contact.bday = "15";
    contact.bmonth = "June";
    contact.byear = "1985";
    contact.group = "Group 1";
    contact.address2 = "Address2";
    contact.homephone2 = "94957463524";
	fillContactForm(contact);
    submitContactCreation();
    
  }
}
