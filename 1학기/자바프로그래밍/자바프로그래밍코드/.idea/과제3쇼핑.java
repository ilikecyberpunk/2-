import java.util.Objects;

public class 과제3쇼핑 {

    public static final class Product {
        private final String product_id;
        private final String name;
        private final int price;

        public Product(String product_id, String name, int price) {
            if (price < 0 || name == null || name.isBlank()) {
                throw new IllegalArgumentException();
            }
            this.product_id = product_id;
            this.name = name;
            this.price = price;
        }

        public String getProduct_id() { return product_id; }
        public String getName() { return name; }
        public int getPrice() { return price; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Product)) return false;
            Product product = (Product) o;
            return price == product.price && 
                   Objects.equals(product_id, product.product_id) && 
                   Objects.equals(name, product.name);
        }

        @Override
        public int hashCode() { return Objects.hash(product_id, name, price); }

        @Override
        public String toString() { return product_id + "," + name + "," + price; }
    }

    public static final class Order {
        private final int order_no;
        private final int quantity;

        public Order(int order_no, int quantity) {
            if (quantity <= 0) throw new IllegalArgumentException();
            this.order_no = order_no;
            this.quantity = quantity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Order)) return false;
            Order order = (Order) o;
            return order_no == order.order_no && quantity == order.quantity;
        }

        @Override
        public int hashCode() { return Objects.hash(order_no, quantity); }

        @Override
        public String toString() { return order_no + "," + quantity; }
    }

    public static void main(String[] args) {
        Product p1 = new Product("P001", "MacBook", 2000);
        Product p2 = new Product("P001", "MacBook", 2000);
        Product p3 = new Product("P002", "iPhone", 1200);

        System.out.println(p1 != null);
        System.out.println(p1.getProduct_id().equals("P001"));
        System.out.println(p1.getName().equals("MacBook"));
        System.out.println(p1.getPrice() == 2000);
        System.out.println(p1.equals(p2));
        System.out.println(p1.equals(p3));
        System.out.println(p1.hashCode() == p2.hashCode());
        System.out.println(p1.toString());

        
    }
}
