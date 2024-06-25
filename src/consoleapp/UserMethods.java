package consoleapp;
import java.util.*;

public class UserMethods {
    static Scanner sc = new Scanner(System.in);
    private static final ArrayList<User> usersList = new ArrayList<User>();// visibility
    static int getIntInput() {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.err.println(e+" Invalid input.");
                System.out.println("Please enter an integer :");
            }
        }
    }
    public static boolean isValidMobileNumber(String mobileNumber) {
        return mobileNumber.matches("\\d{10}");
    }
    static String getValidMobileNumber()
    {
        String number;
        number = sc.next();
        while (!isValidMobileNumber(number)) {
            System.err.println("Mobile number is not valid");
            System.out.println("Please enter a valid mobile number :");
            number = sc.next();
        }
        return number;
    }
    static String getName()
    {
        String name;
        name = sc.nextLine();
        while (true) {
            if(name.isEmpty()){
                System.err.println("Name cannot be empty");
                System.out.println("Please enter the user name ");
                name = sc.nextLine();
            }
            else break;
       }
        return name;
    }
    static boolean isValidEmail(String email) {
           return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

    }
    static String getValidEmail() throws PatternSyntaxException
    {
        String email;
        email = sc.next();
            if (!isValidEmail(email)) {
                throw new PatternSyntaxException("Email is not valid\n");
            }
        return email;
    }
    static void createUser() throws PatternSyntaxException {
        System.out.println("--------------***--------------");
        System.out.println("Enter your email :");
        String email;
        email = getValidEmail();
        sc.nextLine();
        for (User user : usersList) {
            while(true) {
                if (user.getEmail().equals(email)) {
                    System.err.println("User already exist with this email");
                    System.out.println("Please enter another email :");
                    email = getValidEmail();
                }
                else
                    break;
            }
        }
        System.out.println("Enter the user name:");
        String name = getName();
        System.out.println("Enter your mobile number : ");
        String number = getValidMobileNumber();
        List<String> mobileNumbers = new ArrayList<>();
        mobileNumbers.add(number);
        number = null;
        int choice;
        while(true)
        {
            System.out.println("If you want to add another number");
            System.out.println("Press 1 to add another number");
            System.out.println("Press 2 to do not add another number");
            choice = getIntInput();
            if(choice ==1) {
                System.out.println("Enter Mobile number :");
                number = getValidMobileNumber();
                mobileNumbers.add(number);
            }
            else if(choice>2 || choice <=0)//add other condition
            {
                System.err.println("Please choose correct option : ");
            }
            else
            {
                break;
            }
        }
        User newUser = new User(name,email,mobileNumbers);
        usersList.add(newUser);
        System.out.println("User created successfully...");
        System.out.println("--------------***--------------");
    }
    static void fetchSingleUser() throws UserNotFoundException, PatternSyntaxException {
        System.out.println("Enter your email:");
            String email = getValidEmail();
            boolean userFound = false;
            for (User user : usersList) {
                if (user.getEmail().equals(email)) {
                    System.out.println("\n------------***------------");
                    System.out.println("User found :");
                    System.out.println("User Name = " + user.getName());
                    System.out.println("User Email = " + user.getEmail());
                    System.out.println("User Mobile Numbers = " +user.getMobileNumbers());
                    System.out.println("------------***------------");
                    userFound = true;
                    break;
                }
            }
            if (!userFound) {
                throw new UserNotFoundException("\nUser not found");
            }
    }

    static void fetchAllUsers() throws UserNotFoundException {
        if(usersList.isEmpty())
            throw new UserNotFoundException("Don't have any user please create user\n");
        for (User user : usersList) {
            System.out.println("\n--------------***---------------");
            System.out.println("User Name: " + user.getName());
            System.out.println("User Email: " + user.getEmail());
            System.out.println("User Mobile Numbers: " + user.getMobileNumbers());
            System.out.println("--------------***--------------");
        }
    }

    static void updateUser() throws UserNotFoundException, PatternSyntaxException {
        System.out.println("---------------------***---------------------");
        System.out.println("Enter the email of the user you want to update:");
        String email= getValidEmail();
        for (User user : usersList) {
            if (user.getEmail().equals(email)) {
                System.out.println("-----------------***-----------------");
                System.out.println("Press 1 to Update user name");
                System.out.println("Press 2 to Update existing mobile numbers");
                System.out.println("Press 3 to Add new mobile number");
                System.out.println("Press 4 to exit");
                System.out.println("-----------------***-----------------");
                System.out.println("Please choose option :");
                int choice = getIntInput();
                sc.nextLine();
                List<String> mobileNumbersList = new ArrayList<>();
                switch (choice) {
                    case 1:
                        System.out.println("Enter new user name:");
                        String newName = getName();
                        user.setName(newName);
                        System.out.println("User name updated.");
                        System.out.println("---------------------***---------------------");
                        break;
                    case 2:
                        System.out.println("Enter new mobile number to replace existing numbers:");
                        String newMobileNumber = getValidMobileNumber();
                        mobileNumbersList.add(newMobileNumber);
                        user.updateMobileNumbers(mobileNumbersList); // Handle Exception
                        System.out.println("User mobile number updated.");
                        System.out.println("---------------------***---------------------");
                        break;
                    case 3:
                        System.out.println("Enter new mobile number to add :");
                        String additionalMobileNumber ;
                        additionalMobileNumber = getValidMobileNumber();
                        mobileNumbersList.add(additionalMobileNumber);
                        user.addMobileNumber(mobileNumbersList);
                        System.out.println("New mobile number added.");
                        System.out.println("---------------------***---------------------");
                        break;
                    case 4:
                        System.out.println("Exiting update menu.");
                        System.out.println("---------------------***---------------------");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
                return;
            }
        }
        throw new UserNotFoundException("\nUser not found with email : " + email);
    }
    static void deleteUser() throws UserNotFoundException, PatternSyntaxException {
        System.out.println("----------------------***-----------------------");
        System.out.println("Enter the email of the user you want to delete:");
        String email = getValidEmail();
        Iterator<User> iterator = usersList.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getEmail().equals(email)) {
                iterator.remove();
                System.out.println("User with email " + email + " has been deleted.");
                System.out.println("----------------------***-----------------------");
                return;
            }
        }
        throw new UserNotFoundException("\nUser not found with email : " + email);
    }
}
