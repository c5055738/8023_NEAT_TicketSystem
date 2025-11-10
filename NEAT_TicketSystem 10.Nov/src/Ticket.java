public class Ticket implements Comparable<Ticket> {

    private String name;
    private double price;
    private int availableTickets;


    public Ticket(String name, double price, int availableTickets) {
        this.name = name;
        this.price = price;
        this.availableTickets = availableTickets;
    }

    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getTickets(){
        return availableTickets;
    }

    public void decreaseTickets(int quantity){
        if (quantity > availableTickets) throw new IllegalArgumentException("Sorry! There are not enough tickets!");
        availableTickets -= quantity;
    }

    public void increaseTicket(int quantity){
        availableTickets += quantity;
    }

    @Override
    public int compareTo(Ticket other) {

        return this.name.compareTo(other.name);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Ticket))
            return false;
        Ticket other = (Ticket)obj;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }


    @Override
    public String toString() {
        return name + " / $" + String.format("%.2f", price) + ", " + availableTickets + " left";
    }

}




