
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.InputMismatchException;
public class ConsoleApp {

    public static ArrayList<User> usersList = new ArrayList<User>();

    public static void main(String[] args) {

        System.out.println("\nWelcome to console-based application");
        System.out.println("------------------------------------");

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("1. Create User");
            System.out.println("2. Fetch Single User");
            System.out.println("3. Fetch All Users");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    createUser(scanner);
                    break;
                case 2:
                    try {
                        fetchSingleUser(scanner);
                    } catch (UserNotFoundException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        fetchAllUsers();
                    } catch (UserNotFoundException e) {
                        System.err.println(e.getMessage());
                    }

                    break;
                case 4:
                    try {
                        updateUser(scanner);
                    } catch (UserNotFoundException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        deleteUser(scanner);
                    } catch (UserNotFoundException e) {
                        System.err.println(e.getMessage());
                    }

                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            System.out.println("\n");
        }
    }
    static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear the invalid input
            }
        }
    }
    static String getNumber(Scanner sc)
    {
        String number;
        number = sc.next();
        while (!isValidMobileNumber(number)) {
            System.out.println("Mobile number is not valid. Please enter a valid mobile number:");
            number = sc.next();
        }
        return number;
    }
    static String getValidEmail(Scanner sc)
    {
        String email;
        email = sc.next();
        while (!isValidEmail(email)) {
            System.out.println("Email is not valid. Please enter a valid email:");
            email = sc.next();
            sc.nextLine(); // Consume the newline
        }
        return email;
    }
    static String getName(Scanner sc)
    {
        String name;
        name = sc.nextLine();
        while (name.trim().isEmpty()) {
            System.out.println("Name cannot be empty. Please enter the user name:");
            name = sc.nextLine();
        }
        return name;
    }
    static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean isValidMobileNumber(String mobileNumber) {
        return mobileNumber.matches("\\d{10}");
    }

    static void createUser(Scanner sc) {
        System.out.println("Enter your email:");
        String email;
        email = getValidEmail(sc);
        sc.nextLine(); // Consume the newline
        boolean exists = true;
        for (User user : usersList) {
            while(exists) {
                if (user.getEmail().equals(email)) {
                    System.out.println("User already exist with this email");
                    System.out.println("Please enter another email:");
                    email = getValidEmail(sc);
                    exists = true;
                } else {
                    exists = false;
                }
            }
        }
        System.out.println("Enter the user name:");
        //User user =new User();
        String name = getName(sc);
        //System.out.println(user.getName());
        System.out.println("Enter your mobile number : ");
        String number = getNumber(sc);
        List<String> mobileNumbers = new ArrayList<>();
        mobileNumbers.add(number);
        number=null;
        System.out.println("If you want to add another number \n Enter 1 for add another number \n Enter 2 do not add another number ");
        int choice = getIntInput(sc);
        if(choice ==1) {
            System.out.println("\nEnter Mobile number :");
            number = getNumber(sc);
            mobileNumbers.add(number);
        }

        User newUser = new User(name, email, mobileNumbers);
        usersList.add(newUser);
       // System.out.println(newUser.getName()==null);
        System.out.println("User created successfully...");
    }

    static void fetchSingleUser(Scanner sc) throws UserNotFoundException {
        System.out.println("Enter your email:");
        String email = getValidEmail(sc);
        sc.nextLine(); // Consume the newline


        boolean userFound = false;
        for (User user : usersList) {
            if (user.getEmail().equals(email)) {

                System.out.println("\n---------------");
                System.out.println("User found :");
                System.out.println("User Name = " + user.getName());
                System.out.println("User Email = " + user.getEmail());
                System.out.println("User Mobile Numbers = " + String.join(", ", user.getMobileNumbers()));
                userFound = true;
                break;
            }
        }

        if (!userFound) {
            throw new UserNotFoundException("\nUser not found");
        }
    }

    static void fetchAllUsers() throws UserNotFoundException {
        if(usersList.size()==0)
            throw new UserNotFoundException("Don't have any user please create user\n");
        for (User user : usersList) {
            System.out.println("\n--------------------");
            System.out.println("User Name: " + user.getName());
            System.out.println("User Email: " + user.getEmail());
            System.out.println("User Mobile Numbers: " + user.getMobileNumbers());
        }
    }

    static void updateUser(Scanner sc) throws UserNotFoundException{
        System.out.println("Enter the email of the user you want to update:");
        String email = getValidEmail(sc);
        sc.nextLine(); // Consume the newline
        for (User user : usersList) {
            if (user.getEmail().equals(email)) {
                System.out.println("Enter 1 to update user name");
                System.out.println("Enter 2 to update existing mobile numbers");
                System.out.println("Enter 3 to add new mobile number");
                System.out.println("Enter 4 to exit");

                int choice = getIntInput(sc);
                sc.nextLine(); // Consume the newline
                List<String> mobileNumbersList = new ArrayList<>();
                switch (choice) {
                    case 1:
                        System.out.println("Enter new user name:");
                        String newName = getName(sc);
                        user.setName(newName);
                        System.out.println("User name updated.");
                        break;
                    case 2:
                        System.out.println("Enter new mobile number to replace existing numbers:");
                        String newMobileNumber = getNumber(sc);
                        sc.nextLine(); // Consume the newline
                        mobileNumbersList.add(newMobileNumber);
                        user.updateMobileNumbers(mobileNumbersList);
                        System.out.println("User mobile number updated.");
                        break;
                    case 3:
                        System.out.println("Enter new mobile number to add:");
                        String additionalMobileNumber ;
                        additionalMobileNumber = getNumber(sc);
                        sc.nextLine(); // Consume the newline
                        mobileNumbersList.add(additionalMobileNumber);
                        user.addMobileNumber(mobileNumbersList);
                        System.out.println("New mobile number added.");
                        break;
                    case 4:
                        System.out.println("Exiting update menu.");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
                return;
            }
        }
        throw new UserNotFoundException("\nUser not found with email : " + email);
    }

    static void deleteUser(Scanner sc) throws UserNotFoundException{
        System.out.println("Enter the email of the user you want to delete:");
        String email = getValidEmail(sc);
        sc.nextLine(); // Consume the newline
        Iterator<User> iterator = usersList.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getEmail().equals(email)) {
                iterator.remove();
                System.out.println("User with email " + email + " has been deleted.");
                return;
            }
        }

        throw new UserNotFoundException("\nUser not found with email : " + email);
    }

    public static ArrayList<User> getUsersList() {
        return usersList;
    }
}