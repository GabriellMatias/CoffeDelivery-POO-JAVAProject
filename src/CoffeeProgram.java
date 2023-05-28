import java.util.*;

class CoffeeProgram {
    private final List<Coffee> coffees;
    private String loggedInUser;
    private int nextId;

    Scanner scanner = new Scanner(System.in);

    public CoffeeProgram() {
        coffees = new ArrayList<>();
        loggedInUser = null;
        nextId = 1;
    }
    public  void printFinalMessages(String input, String color){
        String message = String.format("|  %s  ", input);
        String colorAdvise = null;
        if(Objects.equals(color, "red")){
            colorAdvise="\u001B[31m";
        } else {
            colorAdvise="\u001B[32m";
        }
        System.out.println(colorAdvise + "\n|-----------------------------------------------");
        System.out.println(message);
        System.out.println("|-----------------------------------------------\n" + ANSI_RESET);
        scanner.nextLine();
    }
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static boolean isString(String input) {
        return input.matches("[a-zA-Z]+");
    }

    public void addCoffee(String name, String description, double price, String creator) {
        Coffee coffee = new Coffee(nextId, name, description, price, creator);
        coffees.add(coffee);
        nextId++;
        printFinalMessages("CAFE ADICIONADO COM SUCESSO!", "green");
    }

    public void removeCoffee(int id) {

        for (int i = 0; i < coffees.size(); i++) {
            Coffee coffee = coffees.get(i);
            if (coffee.getId() == id) {
                if (coffee.getCreator().equals(loggedInUser) || isAdmin()) {
                    coffees.remove(i);
                    printFinalMessages("CAFE REMOVIDO COM SUCESSO!", "green");
                } else {
                    printFinalMessages("VOCE NAO TEM PERMISSAO PARA REMOVER ESSE CAFE!", "red");
                }
                return;
            }
        }
        printFinalMessages("CAFE NAO ENCONTRADO!", "red");
    }

    public void modifyCoffee(int id) {
        for (Coffee coffee : coffees) {
            if (coffee.getId() == id) {
                    Scanner scanner = new Scanner(System.in);

                    System.out.print("informe o novo Nome do Cafe: ");
                    String newName = scanner.nextLine();
                    while (!isString(newName)) {
                        System.out.println(ANSI_RED + "Entrada inválida. Por favor, informe uma [STRING]" + ANSI_RESET);
                        newName = scanner.next();
                    }

                    System.out.print("Informe a nova Descricao do Cafe: ");
                    String newDescription = scanner.nextLine();
                    while (!isString(newDescription)) {
                        System.out.println(ANSI_RED + "Entrada inválida. Por favor, informe uma [STRING]" + ANSI_RESET);
                        newDescription = scanner.next();
                    }

                    System.out.print("Informe o novo preco desse Cafe: ");
                    double newPrice;
                    do {
                        while (!scanner.hasNextInt()) {
                            System.out.println(ANSI_RED + "Entrada inválida. Por favor, informe um [NUMERO]."+ ANSI_RESET);
                            scanner.next();
                        }
                        newPrice = scanner.nextInt();
                    } while (newPrice <= 0);
                    scanner.nextLine(); // Clear the scanner buffer

                    coffee.name = newName;
                    coffee.description = newDescription;
                    coffee.price = newPrice;
                    printFinalMessages("CAFE MODIFICADO COM SUCESSO", "green");

                }
                printFinalMessages("CAFE NAO ENCONTRADO", "red");
                return;
            }

        }

    public Coffee searchCoffeById(int id){
        for (Coffee coffee : coffees) {
            if (coffee.getId() == id) {
                return coffee;
            }else{
                printFinalMessages("ID DO CAFE INCORRETO!", "red");
                return null;
            }
        }
        return null;
    }

    public User login(String username, String password, UserManager userManager) {

        List<User> users = userManager.getUsers();

        for (User user: users){
            if (username.equals(user.getName())  && password.equals(user.getPassword()) && Objects.equals(user.getRole(), "superAdmin")) {
                loggedInUser = "admin";
                printFinalMessages("LOGADO COM SUCESSO COMO SUPER-ADMINISTRADOR", "green");
                return user;

            }if (username.equals(user.getName())  &&  password.equals(user.getPassword())) {
                loggedInUser = username;
                printFinalMessages("LOGADO COM SUCESSO COMO ADMINISTRADOR REGULAR", "green");
                return user;

            }if (!username.equals(user.getName())  || !password.equals(user.getPassword())) {
                printFinalMessages("SENHA OU NOME INCORRETOS", "red");
                return null;
            }
        }

    return null;
    }

    public void logout() {
        loggedInUser = null;
    }

    public void listCoffees() {
        if(coffees.isEmpty()){
            printFinalMessages("SEM CAFES NO MENU :(", "red");
        }else {
            printFinalMessages("LISTANDO TODOS CAFES EM ESTOQUE", "green");
            for (Coffee coffee : coffees) {
                System.out.println("\n|---------------------------------|");
                System.out.println("|  ID          | " + coffee.getId());
                System.out.println("|---------------------------------|");
                System.out.println("|  NOME        | " + coffee.getName());
                System.out.println("|---------------------------------|");
                System.out.println("|  DESCRICAO   | " + coffee.getDescription());
                System.out.println("|---------------------------------|");
                System.out.println("|  PRECO       | " + coffee.getPrice());
                System.out.println("|---------------------------------|\n");
                scanner.nextLine();
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
