package stream;


public class Product {

    private int amount;
    private String name;

    public Product(int amount, String name){
        this.amount = amount;
        this.name = name;
    };

    public String getName(){
        return name;
    }
    public int getAmount(){
        return amount;
    }

}
