package FinalProject;
import java.util.*;

public class TicketBookingSystem {
	private static class User {
        private String username;
        private String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    private static class Event {
        private String name;
        private String date;
        private String time;
        private int availableSeats;
        private double ticketPrice; // New attribute for ticket price

        public Event(String name, String date, String time, int availableSeats, double ticketPrice) {
            this.name = name;
            this.date = date;
            this.time = time;
            this.availableSeats = availableSeats;
            this.ticketPrice = ticketPrice;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public int getAvailableSeats() {
            return availableSeats;
        }

        public double getTicketPrice() {
            return ticketPrice;
        }

        public boolean bookTickets(int numTickets) {
            if (numTickets <= availableSeats) {
                availableSeats -= numTickets;
                return true;
            }
            return false;
        }
    }

    private Map<String, User> users;
    private List<Event> events;

    public TicketBookingSystem() {
        users = new HashMap<>();
        events = new ArrayList<>();
    }

    public void registerUser(String username, String password) {
        users.put(username, new User(username, password));
    }

    public boolean authenticateUser(String username, String password) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            return user.getPassword().equals(password);
        }
        return false;
    }

    public void addEvent(String name, String date, String time, int availableSeats, double ticketPrice) {
        events.add(new Event(name, date, time, availableSeats, ticketPrice));
    }

    public void displayEvents() {
        System.out.println("Available Events:");
        for (Event event : events) {
            System.out.println(event.getName() + " - " + event.getDate() + " " + event.getTime() +
                    " - Available Seats: " + event.getAvailableSeats() + " - Ticket Price: Rupee " + event.getTicketPrice());
        }
    }

    public void bookTickets(String eventName, int numTickets) {
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                boolean success = event.bookTickets(numTickets);
                if (success) {
                    double totalPrice = numTickets * event.getTicketPrice();
                    System.out.println(numTickets + " ticket(s) booked for " + eventName);
                    System.out.println("Total amount to be paid: Rupee" + totalPrice);

                    // The user to pay
                    System.out.println("Please proceed to payment.");
                } else {
                    System.out.println("Failed to book tickets for " + eventName + ". Insufficient seats.");
                }
                return;
            }
        }
        System.out.println("Event not found: " + eventName);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketBookingSystem system = new TicketBookingSystem();

        System.out.println("Welcome to the Ticket Booking System!");

        // Register users
        System.out.println("Please register:");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        system.registerUser(username, password);

        // Authenticate users
        System.out.println("\nPlease login to continue:");
        System.out.print("Username: ");
        username = scanner.nextLine();
        System.out.print("Password: ");
        password = scanner.nextLine();

        if (system.authenticateUser(username, password)) {
            System.out.println("Authentication successful!");
        } else {
            System.out.println("Authentication failed. Exiting...");
            return;
        }

        // Add events
        system.addEvent("Concert", "2024-02-25", "18:00", 100, 1000.0);
        system.addEvent("Movie", "2024-02-26", "15:00", 50, 400.0);

        // Display available events
        system.displayEvents();

        // Book tickets
        System.out.println("\nPlease select an event to book tickets:");
        System.out.print("Event Name: ");
        String eventName = scanner.nextLine();
        System.out.print("Number of Tickets: ");
        int numTickets = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        system.bookTickets(eventName, numTickets);

        scanner.close();
    }

}
