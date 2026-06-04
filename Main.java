import java.util.Scanner;

interface InventoryOperations {
    void addProduct(Product product);
    void displayProducts();
}

class InvalidStockException extends Exception {
    public InvalidStockException(String message) {
        super(message);
    }
}

abstract class Product {

    private int productId;
    private String productName;
    private int quantity;

    public Product() {
        productId = 0;
        productName = "Unknown";
        quantity = 0;
    }

    public Product(int productId, String productName, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    abstract void displayDetails();
}

class ElectronicsProduct extends Product {

    public ElectronicsProduct(int id, String name, int quantity) {
        super(id, name, quantity);
    }

    void displayDetails() {
        System.out.println(
                "Electronics | ID: " +
                getProductId() +
                " | Name: " +
                getProductName() +
                " | Qty: " +
                getQuantity());
    }
}

class GroceryProduct extends Product {

    public GroceryProduct(int id, String name, int quantity) {
        super(id, name, quantity);
    }

    @Override
    void displayDetails() {
        System.out.println(
                "Grocery     | ID: " +
                getProductId() +
                " | Name: " +
                getProductName() +
                " | Qty: " +
                getQuantity());
    }
}

class InventoryManager implements InventoryOperations {

    private Product[] products;
    private int count;

    private final int LOW_STOCK_LIMIT = 10;

    public InventoryManager(int size) {
        products = new Product[size];
        count = 0;
    }

    @Override
    public void addProduct(Product product) {

        if (count < products.length) {
            products[count] = product;
            count++;
            System.out.println("Product Added Successfully.");
        } else {
            System.out.println("Inventory Full.");
        }
    }
    public void searchProduct(int id) {

        for (int i = 0; i < count; i++) {

            if (products[i].getProductId() == id) {
                products[i].displayDetails();
                return;
            }
        }

        System.out.println("Product Not Found.");
    }
    public void searchProduct(String name) {

        for (int i = 0; i < count; i++) {

            if (products[i].getProductName()
                    .equalsIgnoreCase(name)) {

                products[i].displayDetails();
                return;
            }
        }

        System.out.println("Product Not Found.");
    }

    public void updateStock(int id, int quantity)
            throws InvalidStockException {

        if (quantity < 0) {
            throw new InvalidStockException(
                    "Stock cannot be negative.");
        }

        for (int i = 0; i < count; i++) {

            if (products[i].getProductId() == id) {

                products[i].setQuantity(quantity);

                System.out.println(
                        "Stock Updated Successfully.");

                return;
            }
        }

        System.out.println("Product Not Found.");
    }

    public void lowStockReport() {

        System.out.println("\nLow Stock Products:");

        boolean found = false;

        for (int i = 0; i < count; i++) {

            if (products[i].getQuantity()
                    < LOW_STOCK_LIMIT) {

                products[i].displayDetails();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No low stock products.");
        }
    }
    public void displayProducts() {

        if (count == 0) {
            System.out.println("Inventory Empty.");
            return;
        }

        System.out.println("\nInventory List");

        for (int i = 0; i < count; i++) {
            products[i].displayDetails();
        }
    }
}

public class WarehouseInventoryTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        InventoryManager manager =
                new InventoryManager(100);

        int choice;

        do {

            System.out.println("\n======================");
            System.out.println("Warehouse Inventory");
            System.out.println("======================");
            System.out.println("1. Add Electronics");
            System.out.println("2. Add Grocery");
            System.out.println("3. Display Products");
            System.out.println("4. Search by ID");
            System.out.println("5. Search by Name");
            System.out.println("6. Update Stock");
            System.out.println("7. Low Stock Report");
            System.out.println("8. Exit");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter ID: ");
                    int eid = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String ename = sc.nextLine();

                    System.out.print("Enter Quantity: ");
                    int eqty = sc.nextInt();

                    Product e =
                            new ElectronicsProduct(
                                    eid,
                                    ename,
                                    eqty);

                    manager.addProduct(e);

                    break;

                case 2:

                    System.out.print("Enter ID: ");
                    int gid = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String gname = sc.nextLine();

                    System.out.print("Enter Quantity: ");
                    int gqty = sc.nextInt();

                    Product g =
                            new GroceryProduct(
                                    gid,
                                    gname,
                                    gqty);

                    manager.addProduct(g);

                    break;

                case 3:

                    manager.displayProducts();
                    break;

                case 4:
                    System.out.print(
                            "Enter Product ID: ");

                    int sid = sc.nextInt();

                    manager.searchProduct(sid);

                    break;
                case 5:
                    sc.nextLine();
                    System.out.print(
                            "Enter Product Name: ");

                    String sname = sc.nextLine();

                    manager.searchProduct(sname);

                    break;

                case 6:

                    try {

                        System.out.print(
                                "Enter Product ID: ");

                        int uid = sc.nextInt();

                        System.out.print(
                                "Enter New Quantity: ");

                        int qty = sc.nextInt();

                        manager.updateStock(
                                uid,
                                qty);

                    } catch (
                            InvalidStockException e1) {

                        System.out.println(
                                "Error: "
                                        + e1.getMessage());
                    } finally {

                        System.out.println(
                                "Update Process Finished");
                    }

                    break;

                case 7:

                    manager.lowStockReport();

                    break;

                case 8:
                    System.out.println(
                            "Exiting Program...");
                    break;

                default:
                    System.out.println(
                            "Invalid Choice");
            }
        } while (choice != 8);

        StringBuilder report =
                new StringBuilder();

        report.append("\nInventory Session Closed.");
        report.append("\nThank You!");

        System.out.println(report);

        manager = null;
        System.gc();

    }
}
