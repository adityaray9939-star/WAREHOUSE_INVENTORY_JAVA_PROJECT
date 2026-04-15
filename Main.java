class Product {
    String productName;
    int quantity;
    int threshold;

    Product(String productName, int quantity, int threshold) {
        this.productName = productName;
        this.quantity = quantity;
        this.threshold = threshold;
    }
    void displayProduct() {
        System.out.println("Product: " + productName);
        System.out.println("Quantity: " + quantity ); 
        System.out.println("Threshold: " + threshold);
    }
}

class InventoryManager {
    Product[] products;
    int count;

    InventoryManager(int size) {
        products = new Product[size];
        count = 0;
    }

    void addProduct(Product p) {
        if (count < products.length) {
            products[count] = p;
            count++;
        } else {
            System.out.println("Inventory is full!");
        }
    }

    void displayInventory() {
        System.out.println("\n-- Inventory Details --");
        for (int i = 0; i < count; i++) {
            products[i].displayProduct();
        }
    }

    void checkLowStock() {
        System.out.println("\n-- Stock Status --");
        for (int i = 0; i < count; i++) {
            if (products[i].quantity < products[i].threshold) {
                System.out.println(products[i].productName + " :- Low Stock Alert!");
            } else {
                System.out.println(products[i].productName + " :- Stock OK");
            }
        }
    }
}

public class Main{
    public static void main(String[] args) {

        InventoryManager I1 = new InventoryManager(5);

        I1.addProduct(new Product("Laptop", 10, 5));
        I1.addProduct(new Product("Mouse", 2, 5));
        I1.addProduct(new Product("Keyboard", 7, 3));

        I1.displayInventory();

        I1.checkLowStock();
    }
}