import java.sql.Date;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        Category electronics = new Category(1, "Electronics");
        Category clothes = new Category(2, "Clothes");

        Product tv = new Product(1, "TV", "Shows pictures", 2000.0, electronics);
        Product pc = new Product(2, "PC", "Computes things", 4500.0, electronics);
        Product tShirt = new Product(3, "T-shirt", "Short sleeve shirts", 250.0, clothes);
        Product pants = new Product(4, "Pants", "Covers legs", 500.0, clothes);

        List<Product> products = new ArrayList<>(Arrays.asList(tv, pc, tShirt, pants));

        Customer customer1 = new Customer(1, "Ali", "Hoseini");
        Customer customer2 = new Customer(2, "Mohammad", "Rahimi");
        Customer customer3 = new Customer(3, "Hosein", "Mohammadi");

        Order order1 = new Order(1, new Date(2021 - 1900, 2, 3), customer1, OrderStatus.DONE);
        Order order2 = new Order(2, new Date(2021 - 1900, 2, 4), customer2, OrderStatus.PENDING);
        Order order3 = new Order(3, new Date(2021 - 1900, 2, 1), customer3, OrderStatus.DONE);

        Order order4 = new Order(4, new Date(2021 - 1900, 2, 5), customer3, OrderStatus.DONE);
        Order order5 = new Order(5, new Date(2021 - 1900, 2, 5), customer2, OrderStatus.DONE);
        Order order6 = new Order(6, new Date(2021 - 1900, 2, 5), customer1, OrderStatus.DONE);

        List<Order> orders = new ArrayList<>(Arrays.asList(order1, order2, order3, order4, order5, order6));



        HashMap<Product, Integer> order1Products = new HashMap<>();
        order1Products.put(tv,8);
        HashMap<Product, Integer> order4Products = new HashMap<>();
        order4Products.put(pc, 4);
        HashMap<Product, Integer> order5Products = new HashMap<>();
        order5Products.put(tShirt, 3);
        HashMap<Product, Integer> order6Products = new HashMap<>();
        order6Products.put(pants, 2);

        OrderDetails orderDetails1 = new OrderDetails(order1,order1Products);
        OrderDetails orderDetails4 = new OrderDetails(order4, order4Products);
        OrderDetails orderDetails5 = new OrderDetails(order5, order5Products);
        OrderDetails orderDetails6 = new OrderDetails(order6, order6Products);

        List<OrderDetails> orderDetails = new ArrayList<>(Arrays.asList(orderDetails1,orderDetails4, orderDetails5, orderDetails6));

        //1-
        products
                .stream()
                .filter(product -> product.getCategory().getCatName().equals("Electronics"))
                .forEach(product -> System.out.println(product + "\n"));

        //2-
        orders
                .stream()
                .filter(order -> isWithinRange(order.getOrderDate()))
                .forEach(order -> System.out.println(order + "\n"));
        //3-
        Product minPriceProduct = products
                .stream()
                .filter(product -> product.getCategory().getCatName().equals("Electronics"))
                .min(Comparator.comparing(Product::getPrice))
                .orElseThrow(NoSuchElementException::new);
        System.out.println(minPriceProduct);

        //4-
        OptionalDouble averageOfDay = orderDetails
                .stream()
                .filter(od -> equalsDate(od.getOrder().getOrderDate(),new Date(2021-1900,2,5)))
                .mapToDouble(od -> orderPriceCalc(od.getProducts()))
                .average();
        System.out.println("Average Order on this day: " + averageOfDay.orElse(-1));
    }

    public static boolean isWithinRange(Date date) {
        return date.after(new Date(2021 - 1900, 2, 1))
                && date.before(new Date(2021 - 1900, 2, 5));
    }

    public static boolean equalsDate(Date input, Date toCheck) {
        return input.getYear() == toCheck.getYear() && input.getMonth() == toCheck.getMonth() && input.getDay() == toCheck.getDay();
    }

    public static Double orderPriceCalc(HashMap<Product, Integer> products) {
        AtomicReference<Double> totalPrice = new AtomicReference<>(0.d);
        products.entrySet()
                .forEach((product -> totalPrice.updateAndGet(v -> v + product.getKey().getPrice() * product.getValue())));
        return totalPrice.get();
    }
}
