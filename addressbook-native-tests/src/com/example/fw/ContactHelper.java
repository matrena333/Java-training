package com.example.fw;

public class ContactHelper extends HelpersBase{

	public ContactHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}

	public void createContact(Contact contact) {
		initContactCreation();
		fillContactForm(contact);
		confirmContactCreation();
	}
	
	public void deleteContact() {
		initContactDeletion();
		confirmContactDeletion();
	}


	private void initContactCreation() {
		manager.getAutoItHelper()
			.winWaitAndActivate("AddressBook Portable", "", 5000)
			.click("Add").winWaitAndActivate("Add Contact", "", 5000);
	}
	
	
	private void fillContactForm(Contact contact) {
		manager.getAutoItHelper()
			.send("TDBEdit12", contact.firstname)
			.send("TDBEdit11", contact.lastname);
	}
	
	private void confirmContactCreation() {
		manager.getAutoItHelper()
			.click("Save")
			.winWaitAndActivate("AddressBook Portable", "", 5000);
	}

	public Contact getFirstContact() {
		manager.getAutoItHelper()
			.winWaitAndActivate ("AddressBook Portable", "", 5000)
			.focus("TListView1")
			.send("{DOWN}{SPACE}")
			.click("Edit")
			.winWaitAndActivate("Update Contact", "", 5000);
		Contact contact = new Contact() 
			.setFirstname(manager.getAutoItHelper().getText("TDBEdit12"))
			.setLastname(manager.getAutoItHelper().getText("TDBEdit11"));
		manager.getAutoItHelper()
			.click("Cancel")
			.winWaitAndActivate("AddressBook Portable", "", 5000);
		return contact;
	}
	
	private void initContactDeletion() {
		manager.getAutoItHelper()
			.winWaitAndActivate ("AddressBook Portable", "", 5000)
			.focus("TListView1")
			.send("{DOWN}{SPACE}")
 			.click("Delete")
			.winWaitAndActivate("Confirm", "", 5000);
	}
	
	private void confirmContactDeletion() {
		manager.getAutoItHelper()
			.focus("TMessageForm")
			.click("TButton2")
			.winWaitAndActivate("AddressBook Portable", "", 5000);
	}
	
	public void exitAddressBook() {
		manager.getAutoItHelper().click("Exit");
	}



	public int checkContactDeletion(Contact contact)  {	
		manager.getAutoItHelper()
		.send("TEdit1",contact.lastname)
		.click("TRbButton10");
				
		boolean b=manager.getAutoItHelper().windowExist("Information");
		if (b){
			 manager.getAutoItHelper().click("TButton1"); 
			 return 1;
			 }
		else return 0;
		}
	

}
