package com.example.fw;

import static com.example.fw.ContactHelper.CREATION;
import static com.example.fw.ContactHelper.MODIFICATION;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;
import com.example.tests.GroupData;
import com.example.utils.SortedListOf;

public class ContactHelper extends HelperBase {
	
	public static boolean CREATION = true;
	public static boolean MODIFICATION = false;

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}
	
	private SortedListOf<ContactData> cachedContacts;

	public SortedListOf<ContactData> getContacts() {
		if(cachedContacts == null) {
			rebuildCache();
		}
		return cachedContacts;
	}		
		
	private void rebuildCache() {
		cachedContacts = new SortedListOf<ContactData>();
		
		manager.navigateTo().mainPage();
		List<WebElement> lastnames = driver.findElements(By.xpath("//tr[@name='entry']/td[2]"));
		for (WebElement lastname : lastnames) {
		String secondname = lastname.getText();
		//System.out.println(contact);
		cachedContacts.add(new ContactData().withLastname(secondname));
		}		
	}



	public ContactHelper createContact(ContactData contact, boolean formType) {
		manager.navigateTo().mainPage();		
		initContactCreation();
		fillContactForm(contact, CREATION);
		submitContactCreation();
		returnToMainPage();
		rebuildCache();
		return this;
	}
	
	public ContactHelper modifyContact(int index, ContactData contact, boolean formType) {
		manager.navigateTo().mainPage();
		initContactModification(index);
		fillContactForm(contact, MODIFICATION);
		submitContactModification();
		returnToMainPage();
		rebuildCache();
		return this;
	}
	
	public ContactHelper deleteContact(int index) {
		manager.navigateTo().mainPage();
		initContactModification(index);
		submitContactDeletion();
		returnToMainPage();
		rebuildCache();
		return this;
	}

//--------------------------------------------------------------------------------
	public ContactHelper initContactCreation() {
		manager.navigateTo().mainPage();
		click(By.linkText("add new"));
		return this;
	  }

	public ContactHelper fillContactForm(ContactData contact, boolean formType) {
		type(By.name("firstname"), contact.getFirstname());
	    type(By.name("lastname"), contact.getLastname());
	    type(By.name("address"), contact.getAddress());
	    type(By.name("home"), contact.getHomephone());
	    type(By.name("mobile"), contact.getMobilephone());
	    type(By.name("work"), contact.getWorkphone());
	    type(By.name("email"), contact.getEmail1());
	    type(By.name("email2"), contact.getEmail2());
	    selectByText(By.name("bday"), contact.getBday());
	    selectByText(By.name("bmonth"), contact.getBmonth());
	    type(By.name("byear"), contact.getByear());
	    if (formType == CREATION) {
			
		} else {
			if (driver.findElements(By.name("new_group")).size() != 0) {
				throw new Error("Group selector exists in contact modification form");
			}
		}
	   // selectByText(By.name("new_group"), contact.group);
	    type(By.name("address2"), contact.getAddress2());
	   //type(By.name("phone2"), contact.getHomephone2());
		return this;
	  }



	public ContactHelper submitContactCreation() {
		click(By.name("submit"));
		cachedContacts = null;
		return this;
	  }

	public ContactHelper initContactModification(int index) {
		manager.navigateTo().mainPage();
		click(By.xpath("//*[@id='maintable']/tbody/tr["+(index+2)+"]/td[7]/a/img")); //(index+2) - с учетом нумерации xpath и шапки таблицы
		return this;
		}


	public ContactHelper submitContactModification() {
		click(By.name("update"));
		cachedContacts = null;
		return this;
	}

	public ContactHelper submitContactDeletion() {
		click(By.xpath("//input[@value='Delete']"));
		cachedContacts = null;
		return this;
	}
	
	public ContactHelper returnToMainPage() {
		click(By.linkText("home page"));
		return this;
	}

}
