import java.util.Scanner;


public class Main {
    public static void printCoffe() {
        System.out.println("    ( (");
        System.out.println("     ) )");
        System.out.println("  ........");
        System.out.println("  |      |]");
        System.out.println("  \\      /");
        System.out.println("   `'--'");
    }

    public static void main(String[] args) {
        ShoppingCart shoppingCart =  new ShoppingCart();
        CoffeeProgram program = new CoffeeProgram();
        Scanner scanner = new Scanner(System.in);
        boolean runningAllSwitch = true;

        while (runningAllSwitch) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Perform Login");
            System.out.println("2. Entrar como cliente [Acesso publico]");
            System.out.println("0. Exit");
            System.out.print("Option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Clear the scanner buffer

            switch (option) {
                case 1 -> {
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    program.login(username, password);
                    boolean runningUserLoggedSwitch = true;
                    while (runningUserLoggedSwitch) {
                        System.out.print("Bem vindo " + username);
                        System.out.print("1 - adicionar cafe ");
                        System.out.print("2 - remover cafe ");
                        System.out.print("3 modificar cafe ");
                        System.out.print("4 list all coffes ");
                        System.out.print("5 Log Out ");
                        int loggedInUserOption = scanner.nextInt();

                        switch (loggedInUserOption) {
                            case 1 -> {
                                System.out.print("Coffee Name: ");
                                String name = scanner.nextLine();
                                System.out.print("Coffee Description: ");
                                String description = scanner.nextLine();
                                System.out.print("Coffee Price: ");
                                double price = scanner.nextDouble();
                                scanner.nextLine(); // Clear the scanner buffer
                                program.addCoffee(name, description, price, program.getLoggedInUser());
                            }
                            case 2 -> {
                                System.out.print("ID of the Coffee to remove: ");
                                int idToRemove = scanner.nextInt();
                                scanner.nextLine(); // Clear the scanner buffer
                                program.removeCoffee(idToRemove);
                            }
                            case 3 -> {
                                System.out.print("ID of the Coffee to modify: ");
                                int idToModify = scanner.nextInt();
                                scanner.nextLine(); // Clear the scanner buffer
                                program.modifyCoffee(idToModify);
                            }
                            case 4 -> program.listCoffees();
                            case 5 -> {
                                program.logout();
                                runningUserLoggedSwitch = false;
                            }
                            default -> System.out.println("Invalid option!");
                        }
                    }
                }
                case 2 -> {
                    System.out.println("Antes que seja possivel alguma entrada de dados necessitamos do seu nome para registro");
                    String clientName = scanner.next();
                    boolean runningClientSwitch = true;
                    while (runningClientSwitch) {
                        System.out.println("Bem vindo " + clientName + "ao Coffe Delivery");
                        System.out.println("Escolha uma opcao");
                        System.out.println("1. listar os cafes do menu");
                        System.out.println("2. Adicionar um cafe");
                        System.out.println("3. Remove Coffee to cart");
                        System.out.println("4. List Coffees on cart");
                        System.out.println("5 Back menu");

                        scanner.nextLine();
                        int optionCart = scanner.nextInt();

                        switch (optionCart) {
                            case 1 -> program.listCoffees();
                            case 2 -> {
                                System.out.print("ID of the Coffee to add on your cart: ");
                                int idToAdd = scanner.nextInt();
                                Coffee coffeToAddOnCart = program.searchCoffeById(idToAdd);
                                shoppingCart.addProduct(coffeToAddOnCart);
                            }
                            case 3 -> {
                                System.out.print("ID of the Coffee to remove: ");
                                int idToRemove = scanner.nextInt();
                                scanner.nextLine(); // Clear the scanner buffer
                                shoppingCart.removeProduct(idToRemove);
                            }
                            case 4 -> shoppingCart.displayCart();
                            case 5 -> {
                                runningClientSwitch = false;
                                System.out.print("Retornando ao menu inicial\n");
                                printCoffe();

                            }

                            default -> System.out.println("Invalid option!");
                        }
                    }
                }
                case 0 -> runningAllSwitch = false;
                default -> System.out.println("Invalid option!");
            }
        }
        System.out.println("Program terminated.");
    }
}