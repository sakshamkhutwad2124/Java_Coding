
import java.util.Scanner;

public class Assignmenet_2_HotelBookingSystem {

    private static final int FLOORS = 3;
    private static final int ROOMS_PER_FLOOR = 5;

    private static String[][] rooms = new String[FLOORS][ROOMS_PER_FLOOR];

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize all rooms as "Empty"
        for (int i = 0; i < FLOORS; i++) {
            for (int j = 0; j < ROOMS_PER_FLOOR; j++) {
                rooms[i][j] = "Empty";
            }
        }

        while (true) {
            System.out.println("\n--- Hotel Booking System ---");
            System.out.println("1. View Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    System.out.println("Exiting system. Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void viewRooms() {
        System.out.println("\nRoom Status (Floor x Room):");
        for (int i = 0; i < FLOORS; i++) {
            System.out.print("Floor " + (i + 1) + ": ");
            for (int j = 0; j < ROOMS_PER_FLOOR; j++) {
                String status = rooms[i][j].equals("Empty") ? "Available" : "Booked(" + rooms[i][j] + ")";
                System.out.print("Room " + (j + 1) + " [" + status + "]  ");
            }
            System.out.println();
        }
    }

    private static void bookRoom() {
        System.out.print("Enter floor number (1-" + FLOORS + "): ");
        int floor = scanner.nextInt();
        System.out.print("Enter room number (1-" + ROOMS_PER_FLOOR + "): ");
        int room = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (floor < 1 || floor > FLOORS || room < 1 || room > ROOMS_PER_FLOOR) {
            System.out.println("Invalid floor or room number.");
            return;
        }

        if (!rooms[floor - 1][room - 1].equals("Empty")) {
            System.out.println("Sorry, that room is already booked.");
            return;
        }

        System.out.print("Enter guest name to book the room: ");
        String guestName = scanner.nextLine();
        rooms[floor - 1][room - 1] = guestName;
        System.out.println("Room " + room + " on floor " + floor + " has been booked for " + guestName + ".");
    }
}
