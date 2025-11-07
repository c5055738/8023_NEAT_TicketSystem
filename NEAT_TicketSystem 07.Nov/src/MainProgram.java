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
        SortedLinkedList<Member> members = new SortedLinkedList<>();

        //Read the file
        try{
            Scanner fileScanner = new Scanner(new File("input_data.txt"));

        //Read member info
            int memberQuantity = Integer.parseInt(fileScanner.nextLine());
            for(int i =0; i<memberQuantity; i++){
                String[] names = fileScanner.nextLine().split(" ");
                members. insertSorted(new Member(names[0], names[1]));
            }

        //Read ticket quantity
            int ticketQuantity = Integer.parseInt(fileScanner.nextLine());
            for(int i=0; i < ticketQuantity; i++){
                String ticketName = fileScanner.nextLine();
                int availableTickets = Integer.parseInt(fileScanner.nextLine());
                double ticketPrice = Double.parseDouble(fileScanner.nextLine());
                tickets.insertSorted(new Ticket(ticketName, ticketPrice, availableTickets));
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
            String command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "f": // End program
                    running = false;
                    break;

                case "t"://Show Ticket details
                    System.out.println("--- Tickets List ---");
                    for (Ticket t : tickets) {
                        System.out.println(t);
                    }
                    break;

                case "m"://Show member info
                    System.out.println("--- Members List---");
                    for (Member m : members) {
                        m.memberInfo();
                    }
                    break;

                case "b"://Buy Ticket
                    System.out.println("Enter member's name: ");
                    String[] name = scanner.nextLine().split(" ");
                    Member member = findMember(members, name[0], name[1]);
                    if (member == null) {
                        System.out.println("Member not found!");
                        break;
                    }

                    System.out.println("Enter ticket name: ");
                    String ticketName = scanner.nextLine();
                    Ticket ticket = findTicket(tickets,ticketName);
                    if (ticket == null) {
                        System.out.println("Ticket not found!");
                        break;
                    }


                    System.out.println("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    if (quantity > ticket.getTickets()){
                        System.out.println("Not enough tickets available!");
                        writeLetter(member, ticketName);
                        break;
                    }

                    member.buyTicket(ticket, quantity);
                    break;

                case "c"://Refund ticket
                    System.out.println("Enter member's name: ");
                    String[] cancelName = scanner.nextLine().split(" ");
                    Member cancelMember = findMember(members, cancelName[0], cancelName[1]);
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
            writer.println("Dear Member,");
            writer.println();
            writer.println("We are sorry to inform you that the ticket '" + ticketName + "' is currently not enough. Please try again or select other ticket type. Thank you. ");
            writer.println();
            writer.println("Kind regards,");
            writer.println("Ticket Office");
            writer.println();
            writer.println("----------------------------------------");
        } catch (IOException e) {
            System.out.println("Failed to write letter: " + e.getMessage());
        }
        }

        //Find tickets
        private static Ticket findTicket(List<Ticket> tickets, String name){
        for (Ticket t : tickets){
            if (t.getName().equalsIgnoreCase(name)){
                return t;
            }
        }
        return null;
        }

        //Find Members
        private static Member findMember(List<Member> members, String firstName, String lastName){
        for (Member m : members){
            if (m.getFirstName().equals(firstName) && m.getLastName().equals(lastName)){
                return m;
            }
        }
        return null;
        }


        }



