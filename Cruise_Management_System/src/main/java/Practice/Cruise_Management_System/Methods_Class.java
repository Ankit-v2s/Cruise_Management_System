package Practice.Cruise_Management_System;

import java.io.*;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Methods_Class {
	int count;
	Scanner sc = new Scanner(System.in);

	public ArrayList<Customer> read_Customer(String file) throws CsvValidationException, IOException {
		ArrayList<Customer> custom = new ArrayList<Customer>();
		try {
			String path = ("src/main/resources/" + file);
			FileReader fr = new FileReader(path);
			CSVReader reader = new CSVReader(fr);
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				if((nextLine[2].endsWith(".com") || nextLine[2].endsWith(".in")) && nextLine[2].contains("@")) {
					Customer cust = new Customer();
					cust.setCust_no(nextLine[0]);
					cust.setCust_name(nextLine[1]);
					cust.setEmail(nextLine[2]);
					custom.add(cust);
					count++;
				}
			}
			System.out.println("Data of given file is read");
			System.out.println("Number of records: " + count);
			reader.close();
			return custom;
		}
		catch(FileNotFoundException e) {
			System.out.println(file+" file not found");
		}
		return custom;
	}

	public void display(ArrayList<Customer> al) {
		System.out.println("ID \tCustomer Name");
		for (Customer customer : al) {
			System.out.println(customer.getCust_no() + "\t" + customer.getCust_name());
		}
	}

	public void search_By_Name(ArrayList<Customer> al, String search_name) {
		count = 0;
		for (Customer cust : al) {
			if (cust.getCust_name().contains(search_name) && count == 0) {
				System.out.println("ID \tCustomer_Name");
			}
			if (cust.getCust_name().contains(search_name)) {
				System.out.println(cust.getCust_no() + "\t" + cust.getCust_name());
				count++;
			}
		}
		if (count == 0) {
			System.out.println(search_name + " not found in Customer_Data");
		}
	}

	public void book_Cruise(ArrayList<Customer> al, String temp_name) {
		Methods_Class m = new Methods_Class();
		count = 0;
		for (Customer cust : al) {
			if (cust.getCust_name().equals(temp_name)) {
				String date = m.date_Checker();
				cust.setDate(date);
				System.out.println("Booking for " + temp_name + " is confirmed");
				System.out.println("From: cruise.booking@gmail.com" + "\nTo: " + cust.getEmail() + "\nDear " + temp_name
						+ "," + "\n\tBooking for " + date + " is confirmed.");
				count++;
			}
		}
		if (count == 0) {
			System.out.println(temp_name + " not found in Customer_Data");
		}
	}

	public void search_By_Date(ArrayList<Customer> al, String search_date) {
		count = 0;
		try {
			for (Customer cust : al) {
				if (cust.getDate().equals(search_date) && count == 0) {
					System.out.println("ID \tCustomer_Name");
				}
				if (cust.getDate().equals(search_date)) {
					System.out.println(cust.getCust_no() + "\t" + cust.getCust_name());
					count++;
				}
			}
		} catch (Exception e) {
			System.out.println("Some Customers have not made bookings.");
		}
		if (count == 0) {
			System.out.println("No bookings for " + search_date);
		} else {
			System.out.println("Number of attendees: " + count);
		}
	}

	public void create_File(ArrayList<Customer> al, String cruise_on_date, String date) throws IOException {
		count = 0;
		ArrayList<Customer> al3 = new ArrayList<Customer>();
		try {
			for (Customer cu : al) {
				if (cu.getDate().equals(cruise_on_date)) {
					al3.add(cu);
				}
			}
		} catch (Exception e) {
			System.out.println("Some Customers have not mentioned date.");
		}
		String filename = "/home/v2stech/Documents/" + date + ".csv";
		File file = new File(filename);
		file.createNewFile();
		FileWriter writer = new FileWriter(file);
		for (Customer cust : al3) {
			if (count == 0) {
				writer.append("ID \tCustomer_Name\n");
			}
			writer.append(cust.getCust_no() + "," + cust.getCust_name() + "\n");
			count++;
		}
		writer.close();
		if (count == 0) {
			System.out.println("No bookings for " + cruise_on_date);
		}
	}

	public String date_Checker() {
		System.out.println("Enter Date in DD/MM/YYYY format");
		System.out.println("Enter Year");
		int year = sc.nextInt();
		while (year < 1000 || year > 2022) {
			System.out.println("Enter correct year");
			year = sc.nextInt();
		}
		System.out.println("Enter Month");
		int month = sc.nextInt();
		while (month > 12 || month <= 0) {
			System.out.println("Enter correct month");
			month = sc.nextInt();
		}
		System.out.println("Enter Day");
		int day = sc.nextInt();
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			while (day <= 0 || day > 31) {
				System.out.println("Enter correct Day");
				day = sc.nextInt();
			}
		} else if (month == 2) {
			if (year % 4 == 0) {
				while (day <= 0 || day > 29) {
					System.out.println("Enter correct Day");
					day = sc.nextInt();
				}
			} else {
				while (day <= 0 || day > 28) {
					System.out.println("Enter correct Day");
					day = sc.nextInt();
				}
			}

		} else {
			while (day <= 0 || day > 30) {
				System.out.println("Enter correct Day");
				day = sc.nextInt();
			}
		}

		String date = (day + "/" + month + "/" + year);
		return date;
	}

	public void search_By_Id(ArrayList<Customer> al, String search_id) {
		count = 0;
		for (Customer cust : al) {
			if (cust.getCust_no().equals(search_id) && count == 0) {
				System.out.println("ID \tCustomer_Name");
			}
			if (cust.getCust_no().contains(search_id)) {
				System.out.println(cust.getCust_no() + "\t" + cust.getCust_name());
				count++;
			}
		}
		if (count == 0) {
			System.out.println(search_id + " not found in Customer_Data");
		}
	}
}
