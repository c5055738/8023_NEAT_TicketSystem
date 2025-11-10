import java.util.ArrayList;
import java.util.List;

// Member class w comparable
public class Member implements Comparable<Member> {
    private String firstName;
    private String lastName;
    private List<Ticket> purchasedTickets;
    private List<Integer> ticketQuantities;

    // Constructor
    public Member(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.purchasedTickets = new ArrayList<>();
        this.ticketQuantities = new ArrayList<>();
    }

    // Getters
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }

    // Purchase tickets
    public void buyTicket(Ticket ticket, int quantity){

        // Show reject text if member buy more than 3 types
        if(purchasedTickets.size() >= 3 && !purchasedTickets.contains(ticket)){
            System.out.println("You have reached the maximum amount(3) of tickets.");
            return;
        }

        // If member has bought the type of ticket, update qty
        if (purchasedTickets.contains(ticket)) {
            int index = purchasedTickets.indexOf(ticket);
            ticketQuantities.set(index, ticketQuantities.get(index) + quantity);
        }

        // Or if first time buy a new ticket type add it
        else {
            purchasedTickets.add(ticket);
            ticketQuantities.add(quantity);
        }

        ticket.decreaseTickets(quantity);
        System.out.println(firstName + " " + lastName + " bought " + quantity + " ticket for " + ticket.getName());
    }

    // Cancel tickets
    public void cancelTicket(Ticket ticket, int quantity){
        if (!purchasedTickets.contains(ticket)) {
            System.out.println("Member does not purchase this ticket!");
            return;
        }

        // Find ticket index
        int index = purchasedTickets.indexOf(ticket);
        int currentQuantity = ticketQuantities.get(index);


        if (quantity > currentQuantity) {
            System.out.println("You can not cancel more quantity than purchase!");
            return;
        }

        // If cancel qty equals current qty show 0; otherwise decrease it
        if (quantity == currentQuantity) {
            ticketQuantities.set(index, 0);
        } else {
            ticketQuantities.set(index, currentQuantity - quantity);
        }

        ticket.increaseTicket(quantity);
        System.out.println(firstName + " " + lastName + " cancelled " + quantity + " ticket for " + ticket.getName());
    }

    // Member purchase info
    public void memberInfo(){
        double totalPrice = 0.0;

        // Calculate total price
        for (int i = 0; i < purchasedTickets.size(); i++){
            Ticket ticket = purchasedTickets.get(i);
            int quantity = ticketQuantities.get(i);
            totalPrice += ticket.getPrice() * quantity;
        }

        // Display member summary
        System.out.println(firstName + " " + lastName + " holds " + purchasedTickets.size() + " types of tickets. Total price: $" + String.format("%.2f", totalPrice));

        if (purchasedTickets.isEmpty()){
            return;
        }

        // Display each ticket detail
        for (int i = 0; i < purchasedTickets.size(); i++){
            Ticket ticket = purchasedTickets.get(i);
            int qty = ticketQuantities.get(i);
            double ticketTotal = ticket.getPrice() * qty;

            System.out.println(" " + ticket.getName() + ": " + qty + "tickets / $" + String.format("%.2f", ticketTotal));
        }
    }

    @Override // Return member's info
    public String toString() {
        return firstName + " " + lastName + " holds " + purchasedTickets.size() + " types of tickets";
    }

    @Override // Sorted by last name, then by first name
    public int compareTo(Member other) {
        int lastNameCompare = lastName.compareTo(other.lastName);
        if(lastNameCompare != 0) {
            return lastNameCompare;
        }
        return this.firstName.compareTo(other.getFirstName());
    }
}
