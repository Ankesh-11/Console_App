package consoleapp;
public class ConsoleApp {
    public static void main(String[] args) {
        System.out.println("**----------------***----------------**");
        System.out.println("\"Welcome to console-based application\"");
        System.out.println("**----------------***----------------**");
        while (true) {
            System.out.println("\n--------------***--------------");
            System.out.println("Press 1 to Create User");
            System.out.println("Press 2 to Fetch Single User");
            System.out.println("Press 3 to Fetch All Users");
            System.out.println("Press 4 to Update User");
            System.out.println("Press 5 to Delete User");
            System.out.println("Press 0 to Exit");
            System.out.println("--------------***--------------");
            System.out.print("Choose an option: ");

            int choice = Helper.getIntInput();
            switch (choice) {
                case 1:
                    try
                    {
                        Helper.createUser();
                    }
                    catch (PatternSyntaxException e){
                        System.err.print(e);
                    }

                    break;
                case 2:
                    try {
                        Helper.fetchSingleUser();
                    } catch (UserNotFoundException | PatternSyntaxException e) {
                        System.err.print(e);
                    }
                    break;
                case 3:
                    try {
                        Helper.fetchAllUsers();
                    } catch (UserNotFoundException e) {
                        System.err.print(e);
                    }
                    break;
                case 4:
                    try {
                        Helper.updateUser();
                    } catch (UserNotFoundException | PatternSyntaxException e) {
                        System.err.print(e);
                    }
                    break;
                case 5:
                    try {
                        Helper.deleteUser();
                    } catch (UserNotFoundException | PatternSyntaxException e) {
                        System.err.print(e);
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.out.println("-----------------***-----------------");
                    return;
                default:
                    System.err.println("Invalid choice. Try again.");
            }
        }
    }
}
