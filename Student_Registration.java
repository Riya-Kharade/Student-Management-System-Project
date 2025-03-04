//Microproject Java
//Student Record System

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

class Student implements Serializable
{
	int roll_no;
	String name;
	float fee;
	long contact_no;

	public Student(int roll_no,String name,float fee,long contact_no)
	{
		this.roll_no= roll_no;
		this.name= name;
		this.fee= fee;
		this.contact_no= contact_no;
	}

	public String toString()
	{
		return "\nStudent Details :" + "\nRoll_No."+ this.roll_no + "\nName: "+this.name + "\nFee: "+
		this.fee+"\nContact No: "+this.contact_no;
	}
}

public class Student_Registration
{
	static void display(ArrayList<Student> al)
	{
		System.out.println("\n  ----------Student List----------  \n");
		System.out.println(String.format("%-10s%-15s%-10s%-20s","Roll_No.","Name","Fee","Contact-no"));
		for(Student s :al)
		{
			System.out.println(String.format("%-5s%-20s%-10s%-15s",s.roll_no,s.name,s.fee,s.contact_no));
		}
		
		System.out.println("\nTotal Count of registered Students:"+al.size());
	}
	
	public static void main(String args[])
	{
		int roll_no;
		String name;
		float fee;
		long contact_no;
		
		Scanner sc = new Scanner(System.in);
		Scanner S = new Scanner(System.in);
		ArrayList<Student>al = new ArrayList<Student>();
		
		File f = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try
		{
			f = new File("C:/Users/sanik/Desktop/Java programs/Java_Project.txt");
			if(f.exists())
			{
				fis = new FileInputStream(f);
				ois = new ObjectInputStream(fis);
				al = (ArrayList<Student>)ois.readObject();
			}
		}
		catch(Exception exp)
		{
			System.out.println(exp);
		}
		
		do
		{
			System.out.println("\n**********Welcome to the Student Registration**********\n");
			System.out.println("1) Register new student\n"+
							   "2) Search for Student\n"+
							   "3) Edit Student details\n"+
							   "4) Delete Student record\n"+
							   "5) Display all registered Students\n"+
							   "6) Exit\n");
			System.out.println("Enter your choice: ");
			int ch = sc.nextInt();
			
			switch(ch)
			{
				case 1:System.out.println("Enter how many students you want to Register: ");
						int n = S.nextInt();
						for(int i=0;i<n;i++)
						{
							System.out.println("\nEnter the following details to register:\n");
							System.out.println("Enter Roll No.:");
							roll_no = sc.nextInt();
							System.out.println("Enter Name of Student:");
							name = sc.next();
							System.out.println("Enter Fees:");
							fee = sc.nextFloat();
							System.out.println("Enter Contact No.:");
							contact_no = sc.nextLong();
							al.add(new Student(roll_no, name, fee, contact_no));
						}
						display(al);
						break;
						
				case 2:System.out.println("Enter student roll no. to search:");
						roll_no = sc.nextInt();
						int i = 0;
						for(Student s: al)
						{
							if(roll_no == s.roll_no)
							{
								System.out.println(s+"\n");
								i++;
							}
						}
						
						if(i == 0)
						{
							System.out.println("\nStudent Details are not available, Please enter a valid Roll No.!!");
						}
						break;
						
				case 3:System.out.println("\nEnter the student Roll No. to EDIT the details");
						roll_no = sc.nextInt();
						int j = 0;
						for(Student s: al)
						{
							if(roll_no == s.roll_no)
							{
								j++;
							do{
								int ch1 = 0;
								System.out.println("\nEdit student Details: \n"+
												   "1)Student Roll no.\n"+
												   "2)Student Name\n"+
												   "3)Contact No.\n"+
												   "4)GO BACK\n");
								System.out.println("Enter your choice: ");
								ch1 = sc.nextInt();
								switch(ch1)
								{
									case 1:System.out.println("\nEnter new Student Roll No.:");
											s.roll_no = sc.nextInt();
											System.out.println(s+"\n");
											break;
									
									case 2:System.out.println("Enter new Student Name:");
											sc.nextLine();
											s.name = sc.nextLine();
											System.out.println(s+"\n");
											break;
										
									case 3:System.out.println("Enter new Student Contact No.:");
											s.contact_no = sc.nextLong();
											System.out.println(s+"\n");
											break;
											
									case 4:j++;
											break;
											
									default:System.out.println("\nEnter the correct choice:");
											break;
								}
							}
							
							while(j==1);
							}
						}
						
						if(j==0)
						{
							System.out.println("\nStudent Details are not available,Please enter a valid Roll No.!!");
						}
						break;
						
				case 4: System.out.println("\nEnter Student Roll No.to DELETE the record:");
						roll_no = sc.nextInt();
						int k = 0;
						try
						{
							for(Student s: al)
							{
								if(roll_no == s.roll_no)
								{
									al.remove(s);
									display(al);
									k++;
								}
							}
							
							if(k==0)
							{
								System.out.println("\nStudent Details are not available, Please enter a valid Roll No.!!");
							}
						}
						
						catch(Exception ex)
						{
							System.out.println(ex);
						}
						break;
						
				case 5: try
							{
								al = (ArrayList<Student>)ois.readObject();
							}
							
							catch (ClassNotFoundException s2)
							{
								System.out.println(s2);
							}
							
							catch (Exception s2)
							{
								System.out.println(s2);
							}
							display(al);
							break;
							
				case 6: try
							{
								fos = new FileOutputStream(f);
								oos = new ObjectOutputStream(fos);
								oos.writeObject(al);
							}
							
							catch (IOException s1)
							{
								s1.printStackTrace();
							}
							
							catch (Exception s2)
							{
								s2.printStackTrace();
							}
							
							finally
							{
								try
								{
									fis.close();
									ois.close();
									fos.close();
									oos.close();
								}
								
								catch (Exception s1)
								{
									s1.printStackTrace();
								}
							}
							
							System.out.println("\nYou have chosen Exit!! saving the Files and closing tool.\nThank You!!");
							sc.close();
							System.exit(0);
							break;
							
					default: System.out.println("\nEnter a correct choice from the List:");
							 break;
			}
		}

		while(true);
	}
}
										
