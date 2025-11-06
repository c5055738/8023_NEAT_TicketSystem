import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MainProgram {
    public static void main(String[] args) {
        SortedLinkedList<Ticket> tickets = new SortedLinkedList<>();
        SortedLinkedList<Member> Members = new SortedLinkedList<>();

        //Read the file
        try{
            Scanner fileScanner = new Scanner(new File("input_data.txt"));

        //Read member quantity
            int memberQuantity = Integer.parseInt(fileScanner.nextLine());
            for(int i =0; i<memberQuantity; i++){
                String[] names = fileScanner.nextLine().split(" ");
                String firstName = names[0];
                String lastName = names[1];
                Members.add(new Member(firstName,lastName));
            }
        //Read ticket quantity
            int ticketQuantity = Integer.parseInt(fileScanner.nextLine());
            for(int i=0; i < ticketQuantity; i++){
                String names = fileScanner.nextLine();
                int availableTickets = Integer.parseInt(fileScanner.nextLine());
                double ticketPrice = Double.parseDouble(fileScanner.nextLine());
                tickets.insertSorted(new Ticket(names,availableTickets,ticketPrice));
            }

            fileScanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found!");
            return;
        }

        //Interactive menu (f/t/m/b/c)
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running){
            System.out.print("Enter query (f/t/m/b/c): ");
            String command = scanner.nextLine();

            switch (command){
                case "f": // End program
                    running = false;
                    break;

                case "t"://Show Ticket details
                    System.out.println("--- Tickets List ---");
                    for (Ticket t : tickets){
                        System.out.println(t);
                    }
                    break;

                case "m"://Show member info
                    System.out.println("--- Members List---");
                    for (Member m : Members){
                        System.out.println(m);
                    }
                    break;

                case "b"://Buy Ticket
                    System.out.println("Enter member's name: ");
                    String[] name = scanner.nextLine().split(" ");
                    Member member = findMember (Members,name[0],name[1]);
                    if (member == null) {
                        System.out.println("Member not found!");
                        break;
                    }
                    System.out.println("Enter ticket name: ");
                    String ticketName = scanner.nextLine();
                    Ticket ticket = findTicket(tickets,ticketName);
                    if (ticket == null) {
                        System.out.println("Ticket not found!");
                        writeLetter(member, ticketName);
                        break;
                    }


                    System.out.println("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    if (ticket.getTickets() <= 0 || quantity > ticket.getTickets()){
                        System.out.println("Not enough tickets!");
                        writeLetter(member, ticketName);
                        break;
                    }
                    member.buyTicket(ticket,quantity);
                    break;

                case "c"://Refund ticket
                    System.out.println("Enter member's name: ");
                    String[] cancelName = scanner.nextLine().split(" ");
                    Member cancelMember = findMember(Members, cancelName[0], cancelName[1]);
                    if (cancelMember == null) {
                        System.out.println("Member not found!");
                        break;
                    }
                    System.out.println("Enter ticket name: ");
                    String cancelTicketName = scanner.nextLine();
                    Ticket cancelTicket = findTicket(tickets, cancelTicketName);
                    if (cancelTicket == null) {
                        System.out.println("Ticket not found!");
                        break;
                    }
                    System.out.println("Enter quantity: ");
                    int cancelQuantity = Integer.parseInt(scanner.nextLine());
                    cancelMember.cancelTicket(cancelTicket,cancelQuantity);
                    break;

                    default:
                        System.out.println("Invalid option!");
            }
            //A line
            System.out.println();

        }
            }
        private static void writeLetter(Member member, String ticketName){
        try (PrintWriter writer = new PrintWriter(new FileWriter("letters.txt", true))) {
            writer.println("Dear " + member.getFirstName() + " " + member.getLastName() + ",");
            writer.println();
            writer.println("We regret to inform you that the requested ticket type '" + ticketName + "' is currently unavailable.");
            writer.println("Please consider selecting a different ticket type or checking back later.");
            writer.println();
            writer.println("Sincerely,");
            writer.println("Ticket Office");
            writer.println();
            writer.println("----------------------------------------");
        } catch (IOException e) {
            System.out.println("Failed to write letter: " + e.getMessage());
        }
        }
        private static Ticket findTicket(List<Ticket> tickets, String name){
        for (Ticket t : tickets){
            if (t.getName().equals(name)){
                return t;
            }
        }
        return null;
        }
        private static Member findMember(List<Member> members, String firstName, String lastName){
        for (Member m : members){
            if (m.getFirstName().equals(firstName) && m.getLastName().equals(lastName)){
                return m;
            }
        }
        return null;
        }


        }



