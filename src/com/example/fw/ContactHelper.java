package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;

public class ContactHelper extends HelperBase {

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	public void initContactCreation() {
		click(By.linkText("add new"));
	  }

	public void fillContactForm(ContactData contact) {
		type(By.name("firstname"), contact.firstname);
	    type(By.name("lastname"), contact.lastname);
	    type(By.name("address"), contact.address);
	    type(By.name("home"), contact.homephone);
	    type(By.name("mobile"), contact.mobilephone);
	    type(By.name("work"), contact.workphone);
	    type(By.name("email"), contact.email1);
	    type(By.name("email2"), contact.email2);
	    selectByText(By.name("bday"), contact.bday);
	    selectByText(By.name("bmonth"), contact.bmonth);
	    type(By.name("byear"), contact.byear);
	    selectByText(By.name("new_group"), contact.group);
	    type(By.name("address2"), contact.address2);
	    type(By.name("phone2"), contact.homephone2);
	  }


	public void submitContactCreation() {
		click(By.name("submit"));
	  }

	public void initContactModification(int index) {
		//click(By.xpath("//img[@alt='Edit']["+(index+1)+"]"));
		click(By.xpath("//*[@id='maintable']/tbody/tr["+(index+2)+"]/td[7]/a/img")); //(index+2) - с учетом нумерации xpath и шапки таблицы
		}

	public void deleteContact() {
		click(By.xpath("//input[@value='Delete']"));	
	}

	public void submitContactModification() {
		click(By.name("update"));
	}

	public List<ContactData> getContacts() {
		List<ContactData> contacts = new ArrayList<ContactData>();
		List<WebElement> lastnames = driver.findElements(By.xpath("//tr[@name='entry']/td[2]"));
		//List<WebElement> lastnames = driver.findElements(By.xpath(".//*[@id='maintable']/tbody/tr/td[2]"));
		//List<WebElement> lastnames = driver.findElements(By.tagName("td[2]"));
		for (WebElement lastname : lastnames) {
		 ContactData contact = new ContactData();
		 contact.lastname = lastname.getText();
		 //System.out.println(contact);
		 contacts.add(contact);
		}		
		return contacts;
	}

}
