import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CoffeeProgram {
    private final List<Coffee> coffees;
    private String loggedInUser;
    private int nextId;

    public CoffeeProgram() {
        coffees = new ArrayList<>();
        loggedInUser = null;
        nextId = 1;
    }

    public void addCoffee(String name, String description, double price, String creator) {
        Coffee coffee = new Coffee(nextId, name, description, price, creator);
        coffees.add(coffee);
        nextId++;
        System.out.println("Coffee added successfully!");
    }

    public void removeCoffee(int id) {
        for (int i = 0; i < coffees.size(); i++) {
            Coffee coffee = coffees.get(i);
            if (coffee.getId() == id) {
                if (coffee.getCreator().equals(loggedInUser) || isAdmin()) {
                    coffees.remove(i);
                    System.out.println("Coffee removed successfully!");
                } else {
                    System.out.println("You don't have permission to remove this coffee.");
                }
                return;
            }
        }
        System.out.println("Coffee not found.");
    }

    public void modifyCoffee(int id) {
        for (Coffee coffee : coffees) {
            if (coffee.getId() == id) {
                if (coffee.getCreator().equals(loggedInUser) || isAdmin()) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("New name: ");
                    String newName = scanner.nextLine();
                    System.out.print("New description: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("New price: ");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine(); // Clear the scanner buffer
                    coffee.name = newName;
                    coffee.description = newDescription;
                    coffee.price = newPrice;
                    System.out.println("Coffee modified successfully!");
                } else {
                    System.out.println("You don't have permission to modify this coffee.");
                }
                return;
            }
        }
        System.out.println("Coffee not found.");
    }

    public Coffee searchCoffeById(int id){
        for (Coffee coffee : coffees) {
            if (coffee.getId() == id) {
                return coffee;
            }else{
                System.out.println("Id incorretc to add on cart");
            }
        }
        return null;
    }
    public void login(String username, String password) {
        if (username.equals("admin") && password.equals("admin123")) {
            loggedInUser = "admin";
            System.out.println("Login successful as an administrator.");
        } else {
            loggedInUser = username;
            System.out.println("Login successful as a regular user.");
        }
    }

    public void logout() {
        loggedInUser = null;
        System.out.println("Logout successful.");
    }

    public void listCoffees() {
        if(coffees.isEmpty()){
            System.out.println("Theres no coffe in menu");
        }else {
            System.out.println("List of Coffees:");
            for (Coffee coffee : coffees) {
                System.out.println("ID: " + coffee.getId());
                System.out.println("Name: " + coffee.getName());
                System.out.println("Description: " + coffee.getDescription());
                System.out.println("Price: " + coffee.getPrice());
                System.out.println("Creator: " + coffee.getCreator());
                System.out.println("-----------------------");
            }
        }
    }

    private boolean isAdmin() {
        return loggedInUser != null && loggedInUser.equals("admin");
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }
}