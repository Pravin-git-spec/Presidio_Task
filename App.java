import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		System.out.println("\nEmployee Management System");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose the service that you want use : ");
        while (true) {
            System.out.println("1. Show all employees ? ");
            System.out.println("2. Search Employees ? ");
            System.out.println("3. Update details ? ");
            System.out.println("4. Delete ?");
            System.out.println("5. Wanna retrieve based on some criteria ? ");
            System.out.println("6. Wanna calculate specified department's average salary ? ");
            System.out.println("7. Wanna calculate average salary of all the employees ?");
            System.out.println("8.Exit ?");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    retrieveEmployee();
                    break;
                case "2":
                    searchEmployee();
                    break;
                case "3":
                    updateEmployee();
                    break;
                case "4":
                	deleteEmployee();
                	break;
                case "5":
                	retrieveWithCriteria();
                	break;
                case "6":
                	averageSalaryByDeparment();
                	break;
                case "7":
                	averageSalaryByEmployee();
                	break;
                case "8":
                	System.out.print("Getting you out...");
                	System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
	}
	public static void retrieveEmployee()
	{
		 
        String csvFilePath = "C:\\Users\\Pravin\\Desktop\\Presidio\\emp.csv";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
        
            String header = reader.readLine();
        
            String line;
            while ((line = reader.readLine()) != null) {
        
                String[] fields = line.split(",");

        
                if (fields.length >= 5) 
                { 
                	
                    try {
                        String fullName = fields[0].trim();
                        int age = Integer.parseInt(fields[1].trim());
                        String dob = fields[2].trim();
                        double salary = Double.parseDouble(fields[3].trim());
                        String department = fields[4].trim();

                    
                        System.out.println("Full Name: " + fullName);
                        System.out.println("Age: " + age);
                        System.out.println("DOB: " + dob);
                        System.out.println("Salary: " + salary);
                        System.out.println("Department: " + department);
                        System.out.println("----------------------");
                    } catch (NumberFormatException e) {
                    
                        System.out.println("Invalid numeric format in line: " + line);
                    }
                } else {
                    
                    System.out.println("Insufficient fields in line: " + line);
                    continue; 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	public static void searchEmployee()
	{
		String csvFilePath = "C:\\Users\\Pravin\\Desktop\\Presidio\\emp.csv";
		Scanner sc = new Scanner(System.in);
        
		System.out.print("Enter the Employee name : ");
        String targetFullName = sc.next();

        
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
        
            String header = reader.readLine();
            String line;
            boolean employeeFound = false;
            while ((line = reader.readLine()) != null) {
                
                String[] fields = line.split(",");

                
                if (fields.length >= 5) 
                { 
                	
                    String fullName = fields[0].trim();

                    
                    if (fullName.equals(targetFullName)) {
                       
                        System.out.println("Full Name: " + fullName);
                        System.out.println("Age: " + fields[1].trim());
                        System.out.println("DOB: " + fields[2].trim());
                        System.out.println("Salary: " + fields[3].trim());
                        System.out.println("Department: " + fields[4].trim());
                        System.out.println("----------------------");
                        employeeFound = true;
                        break;
                    }
                }
            }

           
            if (!employeeFound) {
                System.out.println("Employee with Full Name '" + targetFullName + "' not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public static void updateEmployee()
	{
		String csvFilePath = "C:\\Users\\Pravin\\Desktop\\Presidio\\emp.csv";

        
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the employee name whom you want to update : ");
        String targetFullName = sc.next();

        
        System.out.print("Enter new age : ");
        String newAge = sc.next();
        System.out.print("Enter new salary : ");
        String newSalary = sc.next();
        System.out.print("Enter new department : ");
        String newDepartment = sc.next();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            
            String header = reader.readLine();

            
            List<String> employeeRecords = new ArrayList<>();
            employeeRecords.add(header);

            
            String line;
            while ((line = reader.readLine()) != null) {
                
                String[] fields = line.split(",");

                
                if (fields.length >= 5) { 
                    String fullName = fields[0].trim();

                    
                    if (fullName.equals(targetFullName)) {
                        
                        fields[1] = newAge.trim();
                        fields[3] = newSalary.trim();
                        fields[4] = newDepartment.trim();
                    }
                    String updatedLine = String.join(",", fields);
                    employeeRecords.add(updatedLine);
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                for (String record : employeeRecords) {
                    writer.write(record);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Updated successfully...!");
        
	}
	public static void deleteEmployee()
	{
		String csvFilePath = "C:\\Users\\Pravin\\Desktop\\Presidio\\emp.csv";
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the employee name whom you want to delete : ");
        String targetFullName = sc.next();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
           
            String header = reader.readLine();

            
            List<String> employeeRecords = new ArrayList<>();
            employeeRecords.add(header);

            
            String line;
            while ((line = reader.readLine()) != null) {
                
                String[] fields = line.split(",");

                if (fields.length >= 5) { 
                    String fullName = fields[0].trim();

                    if (!fullName.equals(targetFullName)) {
                        employeeRecords.add(line);
                    }
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                for (String record : employeeRecords) {
                    writer.write(record);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Deleted successfully...!");
	}
	public static void retrieveWithCriteria()
	{
		 String csvFilePath = "C:\\Users\\Pravin\\Desktop\\Presidio\\emp.csv";

	        Scanner scanner = new Scanner(System.in);
	        String targetDepartment="";
	        System.out.println("On what criteria do you want to filter : \n 1.Name \n 2.Age \n 3.DOB \n 4.Salary \n 5.Department");
	        int choice=scanner.nextInt();
	        if(choice == 1) {
	        	System.out.print("Enter the name: ");
	        	 targetDepartment = scanner.next();
	        }
	        else if(choice == 2)
	        {
	        	System.out.print("Enter the Age: ");
	        	targetDepartment = scanner.next();
	        }
	        else if(choice == 3)
	        {
	        	System.out.print("Enter the DOB: ");
	        	targetDepartment = scanner.next();
	        }
	        else if(choice == 4)
	        {
	        	System.out.print("Enter the Salary: ");
	        	targetDepartment = scanner.next();
	        }
	        else if(choice == 5)
	        {
	        	System.out.print("Enter the Department: ");
	        	targetDepartment = scanner.next();
	        }

	        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
	            String header = reader.readLine();

	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] fields = line.split(",");

	                if (fields.length >= 5) { 
	                	if(choice == 1)
	                	{
	                		String fullName = fields[0].trim();

	                        if (fullName.equals(targetDepartment)) {

                                System.out.println("Full Name: " + fullName);
	                            System.out.println("Age: " + fields[1].trim());
	                            System.out.println("DOB: " + fields[2].trim());
	                            System.out.println("Salary: " + fields[3].trim());
	                            System.out.println("Department: " + fields[4].trim());
	                            System.out.println("----------------------");
	                        }
	                	}
	                	if(choice == 2 )
	                	{
	                		String age = fields[1].trim();

	                        if (age.equals(targetDepartment)) {

                                System.out.println("Full Name: " + fields[0].trim());
	                            System.out.println("Age: " + age);
	                            System.out.println("DOB: " + fields[2].trim());
	                            System.out.println("Salary: " + fields[3].trim());
	                            System.out.println("Department: " + fields[4].trim());
	                            System.out.println("----------------------");
	                        }
	                	}
	                	if(choice == 3 )
	                	{
	                		String dob = fields[2].trim();
	                        if (dob.equals(targetDepartment)) {
	                            System.out.println("Full Name: " + fields[0].trim());
	                            System.out.println("Age: " + fields[1].trim());
	                            System.out.println("DOB: " + dob);
	                            System.out.println("Salary: " + fields[3].trim());
	                            System.out.println("Department: " + fields[4].trim());
	                            System.out.println("----------------------");
	                        }
	                	}
	                	if(choice == 4)
	                	{
	                		String salary = fields[3].trim();
	                		if(salary.equals(targetDepartment))
	                		{
	                			System.out.println("Full Name: " + fields[0].trim());
	                            System.out.println("Age: " + fields[1].trim());
	                            System.out.println("DOB: " + fields[2].trim());
	                            System.out.println("Salary: " + salary);
	                            System.out.println("Department: " + fields[4].trim());
	                            System.out.println("----------------------");
	                		}
	                	}
	                	if(choice == 5 )
	                	{
	                		String department = fields[4].trim();
	                        
	                        if (department.equals(targetDepartment)) {
	                        	System.out.println("Full Name: " + fields[0].trim());
	                            System.out.println("Age: " + fields[1].trim());
	                            System.out.println("DOB: " + fields[2].trim());
	                            System.out.println("Salary: " + fields[3].trim());
	                            System.out.println("Department: " + department);
	                            System.out.println("----------------------");
	                            System.out.println("----------------------");
	                        }
	                		
	                	}
	                       
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	public static void averageSalaryByDeparment()
	{
		String csvFilePath = "C:\\Users\\Pravin\\Desktop\\Presidio\\emp.csv";

        
		Scanner sc =new Scanner(System.in);
		System.out.print("Enter the department : ");
        String targetDepartment = sc.next();

        
        Map<String, Double> departmentTotalSalary = new HashMap<>();
        Map<String, Integer> departmentEmployeeCount = new HashMap<>();

        
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
        
            String header = reader.readLine();

        
            String line;
            while ((line = reader.readLine()) != null) {
        
                String[] fields = line.split(",");

        
                if (fields.length >= 5) { 
                    String department = fields[4].trim();

        
                    if (department.equals(targetDepartment)) {
        
                        double salary = Double.parseDouble(fields[3].trim());

                        departmentTotalSalary.put(department, departmentTotalSalary.getOrDefault(department, 0.0) + salary);
                        departmentEmployeeCount.put(department, departmentEmployeeCount.getOrDefault(department, 0) + 1);
                    }
                }
            }

            
            double averageSalary = departmentTotalSalary.getOrDefault(targetDepartment, 0.0) /
                    departmentEmployeeCount.getOrDefault(targetDepartment, 1); 

            
            System.out.println("Average Salary in " + targetDepartment + ": " + averageSalary);

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public static void averageSalaryByEmployee()
	{
	    double totalSalary = 0.0;
        int employeeCount = 0;

        String csvFilePath= "C:\\Users\\Pravin\\Desktop\\Presidio\\emp.csv";
		
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            
            String header = reader.readLine();

            
            String line;
            while ((line = reader.readLine()) != null) {
                
                String[] fields = line.split(",");

                
                if (fields.length >= 5) { 
                    double salary = Double.parseDouble(fields[3].trim());

                    
                    totalSalary += salary;
                    employeeCount++;
                }
            }
            double averageSalary = (employeeCount > 0) ? totalSalary / employeeCount : 0.0;
            System.out.println("Total Employees: " + employeeCount);
            System.out.println("Total Salary: " + totalSalary);
            System.out.println("Average Salary: " + averageSalary);

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
