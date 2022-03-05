package Practice.Cruise_Management_System;

import java.io.*;
import java.util.*;

import com.opencsv.exceptions.CsvValidationException;

public class App {
	public static void main(String[] args) throws CsvValidationException, IOException {
		Methods_Class methods = new Methods_Class();
		Scanner sc = new Scanner(System.in);
		ArrayList<Customer> al = new ArrayList<Customer>();
		int day,month,year;
		int ch,counter=0;
		do {
			System.out.println("==============Main Menu==============");
			System.out.println("1.Read Customer data into list file" + "\n2.Display all customer id and names form List"
					+ "\n3.Search a Customer in a list" + "\n4.Allow customer to book a cruise"
					+ "\n5.View Customer who booked cruise" + "\n6.Save customers to file" + "\n0.Exit");
			System.out.println("Enter your choice from above");
			ch = sc.nextInt();
			switch (ch) {
			case 1:
				System.out.println("Enter file name");
				String file=sc.next();
				ArrayList<Customer> customer = methods.read_Customer(file);
				al.addAll(customer);
				counter++;
				break;
			case 2:
				if(counter!=0) {
					methods.display(al);
				}
				else {
					System.out.println("First you should read the file");
				}
				break;
			case 3:
				if(counter!=0) {
					System.out.println("Enter name you want to search");
					String search_name = sc.next();
					methods.search_By_Name(al, search_name);
				}
				else {
					System.out.println("First you should read the file");
				}
				break;
			case 4:
				if(counter!=0) {
					System.out.println("Enter Customer name");
					String fname = sc.next();
					String lname = sc.nextLine();
					String temp_name = fname + lname;
					methods.book_Cruise(al, temp_name);
				}
				else {
					System.out.println("First you should read the file");
				}
				break;
			case 5:
				if(counter!=0) {
					String search_date=methods.date_Checker();
					methods.search_By_Date(al, search_date);
				}
				else {
					System.out.println("First you should read the file");
				}
				break;
			case 6:
				if(counter!=0) {
					System.out.println("Enter Date in DD/MM/YYYY format");
					System.out.println("Enter Year");
					year=sc.nextInt();
					while(year<1000 || year>2022)
					{
						System.out.println("Enter correct year");
						year=sc.nextInt();
					}
					System.out.println("Enter Month");
					month=sc.nextInt();
					while(month>12 || month<=0)
					{
						System.out.println("Enter correct month");
						month=sc.nextInt();
					}
					System.out.println("Enter Day");
					day=sc.nextInt();
					if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)
					{
						while(day<=0 || day>31)
						{
							System.out.println("Enter correct Day");
							day=sc.nextInt();
						}
					}
					else if(month==2)
					{
						if(year%4==0)
						{
							while(day<=0 || day>29)
							{
								System.out.println("Enter correct Day");
								day=sc.nextInt();
							}
						}
						else
						{
							while(day<=0 || day>28)
							{
								System.out.println("Enter correct Day");
								day=sc.nextInt();
							}
						}
						
					}
					else
					{
						while(day<=0 || day>30)
						{
							System.out.println("Enter correct Day");
							day=sc.nextInt();
						}
					}
					
					String cruise_on_date=(day+"/"+month+"/"+year);
					String date=String.valueOf(day)+String.valueOf(month)+String.valueOf(year);
					methods.create_File(al,cruise_on_date,date);
				}
				else {
					System.out.println("First you should read the file");
				}
				break;
			case 0:
				System.exit(ch);
				break;
			default:
				System.out.println("Enter correct option");
			}
		} while (ch != 0);
	}
}
