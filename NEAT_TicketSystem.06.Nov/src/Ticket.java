public class Ticket implements Comparable<Ticket> {

    private String name;
    private int availableTickets;
    private double price;

    public Ticket(String name, int ticket, double price) {
        this.name = name;
        this.availableTickets = ticket;
        this.price = price;
    }

    public String getName(){
        return name;
    }
    public int getTickets(){
        return availableTickets;
    }
    public double getPrice(){
        return price;
    }


    public boolean whenTickestEnough (int quantity){
        return availableTickets >= quantity;
    }

    public void decreaseTickets(int quantity){
        availableTickets -= quantity;
    }

    public void increaseTicket(int quantity){
        availableTickets += quantity;
    }


    public void increaseTickets(int quantity){
        availableTickets += quantity;
    }

    @Override
    public String toString() {
        return name + ": " + availableTickets + " tickets remaining / $" + price;
    }
    @Override
    public int compareTo(Ticket other) {
        return this.name.compareTo(other.getName());
    }
}

//

