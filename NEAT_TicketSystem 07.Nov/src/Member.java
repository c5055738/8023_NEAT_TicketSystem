import java.util.ArrayList;
import java.util.List;

public class Member implements Comparable<Member> {
    private String firstName;
    private String lastName;
    private List<Ticket> purchasedTickets; // 存放會員買過的票種類
    private List<Integer> ticketQuantities; // 對應每種票的購買數量

    public Member(String firstName,String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.purchasedTickets = new ArrayList<>();
        this.ticketQuantities = new ArrayList<>();
    }

    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}


   //Buy tickets
   public void buyTicket(Ticket ticket, int quantity){

       if (ticket.getTickets() < quantity){
           System.out.println("Not enough tickets available!");
           return;
       }


        if(purchasedTickets.size() >= 3 && !purchasedTickets.contains(ticket)){
            System.out.println("Cannot purchase more than 3 types of tickets!");
            return;
        }


        if (purchasedTickets.contains(ticket)) {
            int index = purchasedTickets.indexOf(ticket);
            ticketQuantities.set(index, ticketQuantities.get(index) + quantity);
        }
        else{
            purchasedTickets.add(ticket);
            ticketQuantities.add(quantity);
        }

        ticket.decreaseTickets(quantity);
        System.out.println(firstName + " " + lastName + " bought " + quantity + " ticket for " + ticket.getName());
   }

   //Cancel tickets
   public void cancelTicket(Ticket ticket, int quantity){
      if (!purchasedTickets.contains(ticket)) {
          System.out.println("Member does not have this ticket type!");
          return;
      }

      int index = purchasedTickets.indexOf(ticket);
      int currentQuantity = ticketQuantities.get(index);

      if (quantity >= currentQuantity) {
          purchasedTickets.remove(ticket);
          ticketQuantities.remove(index);
      }
      else {
          ticketQuantities.set(index, currentQuantity - quantity);
      }

      ticket.increaseTicket(quantity);

       System.out.println(firstName + " " + lastName + " cancelled " + quantity + " ticket for " + ticket.getName());
   }

   //member info
    public void memberInfo(){
        double totalPrice = 0.0;

        for (int i = 0; i < purchasedTickets.size(); i++){
            Ticket ticket = purchasedTickets.get(i);
            int quantity = ticketQuantities.get(i);
            totalPrice += ticket.getPrice() * quantity;
        }
        System.out.println(firstName + " " + lastName + " holds " + purchasedTickets.size() + " types of tickets. Total price: $" + String.format("%.2f", totalPrice));

        if (purchasedTickets.isEmpty()){
            return;
        }

        for (int i =0; i < purchasedTickets.size(); i++) {
            Ticket ticket = purchasedTickets.get(i);
            int quantity = ticketQuantities.get(i);
            totalPrice += ticket.getPrice() * quantity;
        }

        //Show tickets info
        for (int i = 0; i < purchasedTickets.size();i++){
            Ticket ticket = purchasedTickets.get(i);
            int qty = ticketQuantities.get(i);
            double total2 = ticket.getPrice() * qty;

            System.out.println(" " + ticket.getName() + ": " + qty + "tickets / $" + String.format("%.2f", total2));
        }
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
