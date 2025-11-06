import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Member implements Comparable<Member> {
    private String firstName;
    private String lastName;
    private Map<String, Integer> ticketQuantities; // Store quantity for each ticket type

    public Member(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.ticketQuantities = new HashMap<>();
    }

    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }

    // Buy tickets
    public void buyTicket(Ticket ticket, int quantity){
        String ticketName = ticket.getName();
        
        // Check if member already has 3 different ticket types
        if(ticketQuantities.size() >= 3 && !ticketQuantities.containsKey(ticketName)){
            System.out.println("Cannot purchase more than 3 types of tickets!");
            return;
        }
        
        ticket.decreaseTickets(quantity);
        
        // If member already has this ticket type, add to quantity; otherwise, add new ticket type
        if(ticketQuantities.containsKey(ticketName)){
            int currentQuantity = ticketQuantities.get(ticketName);
            ticketQuantities.put(ticketName, currentQuantity + quantity);
        } else {
            ticketQuantities.put(ticketName, quantity);
        }
        
        System.out.println(firstName + " " + lastName + " bought " + quantity + " ticket(s) for " + ticketName);
    }
   
    // Cancel tickets
    public void cancelTicket(Ticket ticket, int quantity){
        String ticketName = ticket.getName();
       
        // Check if member has this ticket type
        if(!ticketQuantities.containsKey(ticketName)){
            System.out.println("Member does not have this ticket type!");
            return;
        }
       
        int currentQuantity = ticketQuantities.get(ticketName);
        
        // Check if cancel quantity is more than owned
        if(quantity > currentQuantity){
            System.out.println("Cannot cancel more tickets than owned!");
            return;
        }
       
        // Update quantity
        int newQuantity = currentQuantity - quantity;
        if(newQuantity == 0){
            ticketQuantities.remove(ticketName); // Remove ticket type if quantity becomes 0
        } else {
            ticketQuantities.put(ticketName, newQuantity);
        }
       
        // Increase available tickets
        ticket.increaseTickets(quantity);
       
        System.out.println(firstName + " " + lastName + " cancelled " + quantity + " ticket(s) for " + ticketName);
    }
   
    // Display member information (for 'm' command)
    public String getMemberInfo(List<Ticket> allTickets) {
        String result = firstName + " " + lastName + ":\n";
        double totalPrice = 0.0;
       
        // Go through all ticket types the member has
        for(String ticketName : ticketQuantities.keySet()){
            int quantity = ticketQuantities.get(ticketName);
           
            // Find the ticket object to get its price
            Ticket ticket = null;
            for(Ticket t : allTickets){
                if(t.getName().equals(ticketName)){
                    ticket = t;
                    break;
                }
            }
           
            if(ticket != null){
                double ticketPrice = ticket.getPrice();
                double totalForThisType = quantity * ticketPrice;
                totalPrice = totalPrice + totalForThisType;
               
                result = result + "  " + ticketName + ": " + quantity + " ticket(s), Total: $" + String.format("%.2f", totalForThisType) + "\n";
            }
        }
       
        result = result + "  Total price of all tickets: $" + String.format("%.2f", totalPrice);
        return result;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " holds " + ticketQuantities.size() + " types of tickets";
    }
    @Override
    public int compareTo(Member other) {
        int lastNameCompare = lastName.compareTo(other.lastName);
        if(lastNameCompare != 0) return lastNameCompare;
        return this.firstName.compareTo(other.getFirstName());
    }
}
