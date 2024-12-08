import java.util.*;

public class CinemaHall {

    static String[][] hall;
    static List<String> bookingHistory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = 0, cols = 0;

        while (true) {
            System.out.println("\nCinema Hall Management");
            System.out.println("1. Set up seats");
            System.out.println("2. Book a seat");
            System.out.println("3. Cancel a booking");
            System.out.println("4. View booking history");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter number of rows: ");
                    rows = scanner.nextInt();
                    System.out.print("Enter number of columns: ");
                    cols = scanner.nextInt();
                    hall = new String[rows][cols];
                    initializeSeats(rows, cols);
                    displayHall(rows, cols);
                    break;

                case 2:
                    if (hall == null) {
                        System.out.println("Please set up the hall first.");
                    } else {
                        System.out.print("Enter seat code to book (e.g., A1): ");
                        String seatCode = scanner.nextLine().toUpperCase();
                        bookSeat(seatCode, rows, cols);
                    }
                    break;

                case 3:
                    if (hall == null) {
                        System.out.println("Please set up the hall first.");
                    } else {
                        System.out.print("Enter seat code to cancel booking (e.g., A1): ");
                        String seatCode = scanner.nextLine().toUpperCase();
                        cancelBooking(seatCode, rows, cols);
                    }
                    break;

                case 4:
                    viewBookingHistory();
                    break;

                case 5:
                    System.out.println("Exiting application. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void initializeSeats(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                hall[i][j] = (char) ('A' + i) + String.valueOf(j + 1) + "-AV";
            }
        }
    }

    public static void displayHall(int rows, int cols) {
        System.out.println("\nCinema Hall Layout:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(hall[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void bookSeat(String seatCode, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hall[i][j].startsWith(seatCode)) {
                    if (hall[i][j].endsWith("-BO")) {
                        System.out.println("Seat " + seatCode + " is already booked.");
                        return;
                    } else {
                        hall[i][j] = seatCode + "-BO";
                        String bookingInfo = "Seat: " + seatCode + ", Date: " + new Date();
                        bookingHistory.add(bookingInfo);
                        System.out.println("Seat " + seatCode + " successfully booked.");
                        displayHall(rows, cols);
                        return;
                    }
                }
            }
        }
        System.out.println("Invalid seat code.");
    }

    public static void cancelBooking(String seatCode, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hall[i][j].startsWith(seatCode)) {
                    if (hall[i][j].endsWith("-AV")) {
                        System.out.println("Seat " + seatCode + " is not booked.");
                        return;
                    } else {
                        hall[i][j] = seatCode + "-AV";
                        System.out.println("Booking for seat " + seatCode + " canceled.");
                        displayHall(rows, cols);
                        return;
                    }
                }
            }
        }
        System.out.println("Invalid seat code.");
    }

    public static void viewBookingHistory() {
        if (bookingHistory.isEmpty()) {
            System.out.println("No bookings have been made.");
        } else {
            System.out.println("\nBooking History:");
            for (String history : bookingHistory) {
                System.out.println(history);
            }
            System.out.println("Total booked seats: " + bookingHistory.size());
        }
    }
}
