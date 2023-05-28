import java.util.ArrayList;
import java.util.List;

class ShoppingCart {
    private List<Coffee> CoffesInCart;

    public ShoppingCart() {
        CoffesInCart = new ArrayList<>();
    }

    public void addProduct(Coffee product) {
        CoffesInCart.add(product);
        System.out.println("Product added to the shopping cart.");
    }

    public void removeProduct(int productId) {
        if(CoffesInCart.isEmpty()){
            System.out.println("You dont have any products in cart");
        }else {
            for (int i = 0; i < CoffesInCart.size(); i++) {
                if (CoffesInCart.get(i).getId() == productId) {
                    CoffesInCart.remove(i);
                    System.out.println("Product removed from the shopping cart.");
                    return;
                }
            }
            System.out.println("Product not found in the shopping cart.");
        }
    }

    public void displayCart() {
        if (CoffesInCart.isEmpty()) {
            System.out.println("Shopping cart is empty.");
        } else {
            System.out.println("Products in the shopping cart:");
            double total = 0.0;
            for (Coffee product : CoffesInCart) {
                System.out.println("ID: " + product.getId());
                System.out.println("Name: " + product.getName());
                System.out.println("Price: " + product.getPrice());

                total += product.getPrice();
                System.out.println("Total on cart: " + total);
                System.out.println("-----------------------");

            }
        }
    }
}