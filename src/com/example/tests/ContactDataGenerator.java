package com.example.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactDataGenerator {

	public static void main(String[] args) throws IOException {
		if (args.length < 3) {
			System.out.println("Please specify parameters: <amount of test data> <file> <format>");
			return;
		}
		
		int amount = Integer.parseInt(args[0]);
		File file = new File(args[1]);
		String format = args[2];
		
		if (file.exists()) {
			System.out.println("File exists, please remove it manually: " + file);
	
		}
		
		List<ContactData> contacts = generateRandomContacts(amount);
		if (format.equals("csv")) {
			saveContactsToCsvFile(contacts, file);
		} else if (format.equals("xml")) {
			saveContactsToXmlFile(contacts, file);
		} else {
			System.out.println("Unknown format" + format);
			return;
		}
	}

	private static void saveContactsToXmlFile(List<ContactData> contacts,
			File file) {
		
	}

	private static void saveContactsToCsvFile(List<ContactData> contacts, File file) throws IOException {
		FileWriter writer = new FileWriter(file);
		for (ContactData contact : contacts) {
			writer.write(contact.getLastname() + "," + contact.getFirstname() + "," + contact.getAddress() + "," 
		+ contact.getHomephone() + "," + contact.getMobilephone() + "," + contact.getWorkphone() + "," 
					+ contact.getEmail1() + "," + contact.getEmail2() + "," + contact.getAddress2() + "," 
		+ contact.getHomephone2() + "\n");
		}
		writer.close();
	}

	public static List<ContactData> generateRandomContacts(int amount) {
		  List<ContactData> list = new ArrayList<ContactData>();
		  for (int i = 0; i < amount; i++) {
			ContactData contact = new ContactData()
				.withFirstname(generateRandomString())
				.withLastname(generateRandomString())
				.withAddress(generateRandomString())
				.withHomephone(generateRandomString())
				.withMobilephone(generateRandomString())
				.withWorkphone(generateRandomString())
				.withEmail1(generateRandomString())
				.withEmail2(generateRandomString())
				.withAddress2(generateRandomString())
				.withHomephone2(generateRandomString());
			list.add(contact);
		}
		  return list;
	}
	
	  public static String generateRandomString() {
		    Random rnd = new Random();
			if (rnd.nextInt(5) == 0) {
				return "";			
			}	else {
				return "test" + rnd.nextInt();
			} 
	  }

}
