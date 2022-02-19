//Class representing an electronic store
//Has an array of products that represent the items the store can sell
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class ElectronicStore{

  public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
  private int curProducts;
  private int sellable;

  private String name;

  private Product[] stock; //Array to hold all products
  private ArrayList<String> cart;
  private int[] cartCount;

  double revenue;

  private int sales;
  
  public ElectronicStore(String initName){
    revenue = 0.0;
    sales = 0;
    name = initName;
    stock = new Product[MAX_PRODUCTS];
    cart = new ArrayList<String>();
    cartCount = new int[MAX_PRODUCTS];
    curProducts = 0;
    sellable = 0;
  }

  public String[] getCartItems() {
    String[] names = new String[cart.size()];
    for (int i = 0; i < cart.size(); i++){
      names[i] = cart.get(i);
    }
    return names;
  }

  public int getCartCount(int index) {
    return cartCount[index];
  }

  public String[] getStockItems(Boolean listSellable) {
    ArrayList<String> sellable = new ArrayList<String>();
    for (int i = 0; i < stock.length; i++) {
      if (stock[i] != null) {
        if (listSellable) {
          if (stock[i].getStockQuantity() != 0 && cartCount[i] != stock[i].getStockQuantity()) {
            sellable.add(stock[i].toString());
          }
        } else {
          sellable.add(stock[i].toString());
        }
      }
    }
      String[] temp = new String[sellable.size()];
      for (int i = 0; i < temp.length; i++) {
        temp[i] = sellable.get(i);
      }
      return temp;
  }

  public Product[] getStock() {
    return stock;
  }

  public boolean transferToCart(int indexInStock){
    if (cartCount[indexInStock] < stock[indexInStock].getStockQuantity()) {
      cart.add(stock[indexInStock].toString());
      cartCount[indexInStock]++;
      return true;
    }
    return false;
  }

  public boolean transferToStock(int indexInCart){
    if (cart.get(indexInCart) != null) {
      for (int i = 0; i < MAX_PRODUCTS; i++){
        if (cart.get(indexInCart).contains(stock[i].toString())) {
          cartCount[i]--;
          break;
        }
      }
      cart.remove(indexInCart);
      return true;
    }
    return false;
  }

  public int getCartVal(){
    int sum = 0;
    for (int i = 0; i < MAX_PRODUCTS; i++){
      if (stock[i] != null) {
        sum += stock[i].getPrice() * cartCount[i];
      }
    }
    return sum;
  }

  public String[] getMostPopular(int num) {
    Product[] temp = Arrays.copyOf(stock, stock.length);
    String[] populars = new String[num];
    for (int i = 0; i < num; i++) {
      int highindex = 0;
      int highval = 0;
      for (int j = 0; j < temp.length; j++) {
        if (temp[j] != null) {
          if (temp[j].getSoldQuantity() >= highval) {
            highval = temp[j].getSoldQuantity();
            highindex = j;
          }
        }
      }
      populars[i] = temp[highindex].toString();
      temp[highindex] = null;
    }
    return populars;
  }

  public double getRevenuePerSale() {
    return revenue/sales;
  }

  public int getSales(){
    return sales;
  }

  public String getName(){
    return name;
  }
  
  //Adds a product and returns true if there is space in the array
  //Returns false otherwise
  public boolean addProduct(Product newProduct){
    if(curProducts < MAX_PRODUCTS){
     stock[curProducts] = newProduct;
     curProducts++;
     sellable++;
     return true;
    }
    return false;
  }

  public void clearCart() {
    cart = new ArrayList<String>();
    cartCount = new int[MAX_PRODUCTS];
  }

  public void tickSale(){
    sales++;
  }
  
  //Sells 'amount' of the product at 'index' in the stock array
  //Updates the revenue of the store
  //If no sale occurs, the revenue is not modified
  //If the index is invalid, the revenue is not modified
  public void sellProducts(int index, int amount){
    if(index >= 0 && index < curProducts){
      revenue += stock[index].sellUnits(amount);
    }
  }
  
  public double getRevenue(){
    return revenue;
  }
  
  //Prints the stock of the store
  public void printStock(){
    for(int i = 0; i < curProducts; i++){
      System.out.println(i + ". " + stock[i] + " (" + stock[i].getPrice() + " dollars each, " + stock[i].getStockQuantity() + " in stock, " + stock[i].getSoldQuantity() + " sold)");
    }
  }
  
  public static ElectronicStore createStore(){
    ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
    Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
    Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
    Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
    Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
    Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
    Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
    ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
    ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
    store1.addProduct(d1);
    store1.addProduct(d2);
    store1.addProduct(l1);
    store1.addProduct(l2);
    store1.addProduct(f1);
    store1.addProduct(f2);
    store1.addProduct(t1);
    store1.addProduct(t2);
    return store1;
  }
} 