public class Ticket implements Comparable<Ticket> {

    private String Name;
    private int Tickets;
    private double Price;

    public Ticket(String Name, int Tickets, double Price) {
        this.Name = Name;
        this.Tickets = Tickets;
        this.Price = Price;
    }

    public String getName(){
        return Name;
    }
    public int getTickets(){
        return Tickets;
    }
    public double getPrice(){
        return Price;
    }

    public void decreaseTickets(int quantity){
        this.Tickets -= quantity;
    }
    public void increaseTickets(int quantity){
        this.Tickets += quantity;
    }

    @Override
    public String toString() {
        return Name + " - " + Tickets + " tickets remaining - $" + Price;
    }
    @Override
    public int compareTo(Ticket other) {
        return this.Name.compareTo(other.getName());
    }
}

