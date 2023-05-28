import java.awt.color.ProfileDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ShoppingCart {
    private final List<Coffee> CoffesInCart;

    public ShoppingCart() {
        CoffesInCart = new ArrayList<>();
    }

    public CoffeeProgram coffe = new CoffeeProgram();
    Scanner scanner = new Scanner(System.in);

    public void addProduct(Coffee product) {
        if(product == null){
            coffe.printFinalMessages("CAFE NAO ENCONTRADO!", "red");
        }else {
            CoffesInCart.add(product);
            coffe.printFinalMessages("PRODUTO ADICIONADO AO CARRINHO DE COMPRAS", "green");
        }
    }

    public void removeProduct(int productId) {
        if(CoffesInCart.isEmpty()){
            coffe.printFinalMessages("VOCE NAO TEM PRODUTOS EM SEU CARRINHO", "red");
        }else {
            for (int i = 0; i < CoffesInCart.size(); i++) {
                if (CoffesInCart.get(i).getId() == productId) {
                    CoffesInCart.remove(i);
                    coffe.printFinalMessages("PRODUTO REMOVIDO DO CARRINHO", "green");
                    return;
                }
            }
            coffe.printFinalMessages("PRODUTO NAO ENCONTRADO!", "red");
        }
    }

    public void displayCart() {

        if (CoffesInCart.isEmpty()) {
            coffe.printFinalMessages("CARRINHO DE COMPRAS VAZIO", "red");
        } else {
            coffe.printFinalMessages("LISTANDO PRODUTOS NO CARRINHO DE COMPRAS", "green");
            double total = 0.0;
            for (Coffee product : CoffesInCart) {

                System.out.println("\n|---------------------------------|");
                System.out.println("|  ID      | " + product.getId());
                System.out.println("|---------------------------------|");
                System.out.println("|  NOME    | " + product.getName());
                System.out.println("|---------------------------------|");
                System.out.println("|  PRECO   | " + product.getPrice());
                System.out.println("|---------------------------------|");

                total += product.getPrice();
                System.out.println("|  TOTAL   | " + total);
                System.out.println("|---------------------------------|\n");
                scanner.nextLine();


            }
        }
    }
}