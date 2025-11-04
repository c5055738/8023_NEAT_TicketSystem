import java.util.ArrayList;
import java.util.List;

public class Member implements Comparable<Member> {
    private String firstName;
    private String lastName;
    private List<Ticket> purchasedTickets;

    public Member(String firstName,String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.purchasedTickets = new ArrayList<>();
    }

    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public List<Ticket> getPurchasedTickets(){return purchasedTickets;}

   public void buyTicket(Ticket ticket, int quantity){
        if(purchasedTickets.size() >= 3){
            System.out.println("Hit the limit of 3 different tickets!");
        }
        ticket.decreaseTickets(quantity);
        purchasedTickets.add(ticket);
        System.out.println(firstName + " " + lastName + " bought " + quantity + " ticket for " + ticket.getName());
   }
   public void cancelTicket(Ticket ticket, int quantity){
       purchasedTickets.remove(ticket);
       System.out.println(firstName + " " + lastName + " cancelled " + quantity + " ticket for " + ticket.getName());
   }

    @Override
    public String toString() {
        return firstName + " " + lastName + " holds " + purchasedTickets.size() + " types of tickets";
    }
    @Override
    public int compareTo(Member other) {
        int lastNameCompare = lastName.compareTo(other.lastName);
        if(lastNameCompare != 0) return lastNameCompare;
        return this.firstName.compareTo(other.getFirstName());
    }
}
