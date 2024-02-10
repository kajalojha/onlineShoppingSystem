package Feb_09_OnlineShoppingSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

abstract class product
{
    private int prd_id;
   private String prd_name;
   private double prd_price;
   //constructor
    public product(int prd_id,String prd_name,double prd_price)
    {
        this.prd_id = prd_id;
        this.prd_name = prd_name;
        this.prd_price = prd_price;
    }

    public int getPrd_id()
    {
        return prd_id;
    }

    public String getPrd_name() {
        return prd_name;
    }

    public void setPrd_name(String prd_name) {
        this.prd_name = prd_name;
    }

    public void setPrd_id(int prd_id)
    {
        this.prd_id=prd_id;
    }
    public double getPrd_price()
    {
        return prd_price;
    }
    public void setPrd_price()
    {
        this.prd_price = prd_price;
    }
    // method to display product details
    public abstract void productDetails();

    // abstract method to find final price
    public  abstract double prd_finalprice(int discount);
}
class electronic extends product
{
    private int prd_warranty;
    public electronic(int prd_id, String prd_name , double prd_price , int prd_warranty)
    {
        super(prd_id,prd_name,prd_price);
        this.prd_warranty  =prd_warranty;
    }
    public int getPrd_warranty()
    {
        return prd_warranty;
    }
    public void productDetails(){
        System.out.println("Product id: "+ getPrd_id());
        System.out.println("Product name: "+ getPrd_name());
        System.out.println("Price: "+ getPrd_price());
        System.out.println("Warranty Period: "+ getPrd_warranty()+"years");
    }
    public void setPrd_warranty(int prd_warranty)
    {
        this.prd_warranty = prd_warranty;
    }
    //override method to calculate discounted price
    public double prd_finalprice(int discount)
    {
           return getPrd_price() - getPrd_price()*(double) (discount/100);
    }
}
class cloth extends product
{
    private String clt_color;
    private String clt_size ;
    public cloth( int prd_id , String prd_name , double prd_price , String clt_color , String clt_size)
    {
        super(prd_id,prd_name,prd_price);
        this.clt_color = clt_color;
        this.clt_size = clt_size;
    }
    public String getClt_color()
    {
        return clt_color;
    }
    public void setClt_color(String clt_color)
    {
        this.clt_color = clt_color;
    }
    public String getClt_size()
    {
        return clt_size;
    }
    public void setClt_size(String clt_size)
    {
        this.clt_size = clt_size;
    }
    public void productDetails() {
        System.out.println("Product ID: " + getPrd_id());
        System.out.println("Product Name: " + getPrd_name());
        System.out.println("Price: " + getPrd_price());
        System.out.println("Size: " + getClt_size());
        System.out.println("Color: " + getClt_color());
    }

    @Override
    public double prd_finalprice(int discount)
    {
        return getPrd_price() - getPrd_price()*(double)(discount/100);
    }

//    public double calculateFinalPrice(int discount) {
//        return getPrd_price() - getPrd_price()*(double)(discount/100);
//    }
}

// implement interface user that represent user
interface user
{
    void addProduct(product p);
    void createAccount();
    void browseproduct();
    void addToCart(product p);
    void placeorder();
}
class Order{
    private ArrayList<product> orders;

    public Order(ArrayList<product> orders){
        this.orders = new ArrayList<>(orders);
    }

    //total amount
    public int calculateTotalAmount() {
        int  totalAmount = 0;
        for (product p : orders) {
            totalAmount += p.getPrd_price();
        }
        return totalAmount;
    }
    //order history
    public void orderHistory(){
        for(int i=0;i<orders.size();i++){
            System.out.println(orders.get(i).getPrd_name());
        }
    }
}
class Guest implements user
{
    private HashMap<Integer,product> store;
    private ArrayList<product> cart;
    public  ArrayList<product> orderHistory;

    public Guest(){
        store = new HashMap<>();
        cart = new ArrayList<>();
        orderHistory= new ArrayList<>();
    }

    public void addProduct(product p){
        store.put(p.getPrd_id(),p);
        System.out.println("Product added successfully!");
    }

    public void createAccount(){
        System.out.println("Guests cannot create accounts. Please  registered your self.");
    }

    @Override
    public void browseproduct()
    {
        System.out.println("Available Products:");
        for (product product : store.values()) {
            product.productDetails();
        }
    }

    @Override
    public void addToCart(product p) {
        cart.add(p);
        System.out.println("Item added to cart!");
    }

    @Override
    public void placeorder()
    {
        if(!cart.isEmpty()){
            for(int i=0;i<cart.size();i++){
                orderHistory.add(cart.get(i));
            }
            cart.clear();
            System.out.println("Order placed Successfully....");
        }
        else {
            System.out.println("Cart is empty. Please add products to cart .....");
        }
    }

    }

//    public void browseProducts() {
//        System.out.println("Available Products:");
//        for (product product : store.values()) {
//            product.productDetails();
//        }
//    }

//    public void AddtoCart(product p){
//        cart.add(p);
//        System.out.println("Item added to cart!");
//    }

//    public void placeOrder(){
//        if(!cart.isEmpty()){
//            for(int i=0;i<cart.size();i++){
//                orderHistory.add(cart.get(i));
//            }
//            cart.clear();
//            System.out.println("Order placed Successfully....");
//        }
//        else {
//            System.out.println("Cart is empty. Please add products to cart .....");
//        }
//    }

class RegisteredUser{
    private HashMap<Integer,product> store = new HashMap<>();
    private HashMap<String,Integer> accounts = new HashMap<>();
    private ArrayList<product> cart = new ArrayList<>();
    public static ArrayList<product> orderHistory = new ArrayList<>();


    void addProduct(product p){
        store.put(p.getPrd_id(),p);
        System.out.println("Product added successfully!");
    }

    void createAccount(String name,int USerID){
        accounts.put(name,USerID);
        System.out.println("USer added successfully!");
    }
    public void browseProducts() {
        System.out.println("Available Products:");
        for (product product : store.values()) {
            product.productDetails();
        }
    }

    public void AddtoCart(product p){
        cart.add(p);
        System.out.println("Item added to cart!");

    }

    public void placeOrder(){
        if(!cart.isEmpty()){
            orderHistory.addAll(cart);

            cart.clear();
            System.out.println("Order placed Successfully");
        }
        else {
            System.out.println("Cart is empty. Please add products in cart ....");
        }
    }
}



public class main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        boolean flag = true;

        RegisteredUser user = new RegisteredUser();
        Order o = new Order(RegisteredUser.orderHistory);

        do {
            System.out.println("----- Welcome to Online Shopping System -----");
            System.out.println("1. Add Products \n" +
                    "2. Create Account\n" +
                    "3. Browse Product\n" +
                    "4. Add to Cart\n" +
                    "5. Total Amount\n" +
                    "6. Place order\n" +
                    "7. Order History\n" +
                    "8. Exit");

            System.out.print("Enter your choice ");
            int n = sc.nextInt();

            switch (n) {
                case 1:
                    System.out.print("If you want to add Clothes enter 'c' if you wnat to add elctronics enter 'e': ");
                    char ch = sc.next().charAt(0);
                    System.out.print("Eneter the Product ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter the Product name:");
                    String pname = sc1.nextLine();
                    System.out.print("Enter the price: ");
                    double price = sc1.nextDouble();
                    if (ch == 'c') {
                        System.out.print("Enter the size: ");
                        String size = sc.next();
                        System.out.print("Enter the color: ");
                        String color = sc.nextLine();
                        cloth c1 = new cloth(id, pname, price, size, color);
                        user.addProduct(c1);
                    } else if (ch == 'e') {
                        System.out.print("Warranty period in years: ");
                        int Warranty = sc.nextInt();
                        electronic e1 = new electronic(id, pname, price, Warranty);
                        user.addProduct(e1);
                    } else {
                        System.out.println("Invalid Input..");
                    }
                    break;
                case 2:
                    System.out.println("Enter the name of the user: ");
                    String name = sc1.nextLine();
                    System.out.println("Enter the user ID:");
                    int userID = sc.nextInt();
                    user.createAccount(name, userID);
                    System.out.println("Member Added Successfully..");
                    break;

                case 3:
                    user.browseProducts();
                    break;

                case 4:
                    System.out.print("If you want to add Clothes to cart enter 'c' if you wnat to add elctronics enter 'e': ");
                    char c = sc.next().charAt(0);
                    System.out.print("Enter the Product ID: ");
                    int i = sc.nextInt();
                    System.out.print("Enter the Product name:");
                    String pn = sc1.nextLine();
                    System.out.print("Enter the price: ");
                    double p = sc1.nextDouble();
                    if (c == 'c') {
                        System.out.print("Enter the size: ");
                        String s = sc.next();
                        System.out.print("Enter the color: ");
                        String clr = sc.nextLine();
                        cloth c1 = new cloth(i, pn, p, s, clr);
                        user.AddtoCart(c1);;
                    } else if (c == 'e') {
                        System.out.print("Warranty period in years : ");
                        int W = sc.nextInt();
                        electronic e1 = new electronic(i, pn, p, W);
                        user.AddtoCart(e1);;
                    } else {
                        System.out.println("Invalid Input!");
                    }
                    break;
                case 5:
                    System.out.println(o.calculateTotalAmount());
                    break;
                case 6:
                    user.placeOrder();
                    break;
                case 7:
                    o.orderHistory();
                    break;

                case 8:
                    flag=false;
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }while(flag);
        sc.close();
        sc1.close();

    }
}
