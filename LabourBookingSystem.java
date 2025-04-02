import java.util.*;

class Labor {
    private String type;
    private Availability[] availabilityArray;
    private int numAvailability;

    public Labor(String type, int maxAvailability) {
        this.type = type;
        this.availabilityArray = new Availability[maxAvailability];
        this.numAvailability = 0;
    }

    public void addAvailability(String date, double rate) {
        Availability availability = new Availability(date, rate);
        availabilityArray[numAvailability++] = availability;
    }

    public String getType() {
        return type;
    }

    public Availability[] getAvailabilityArray() {
        return availabilityArray;
    }

    public int getNumAvailability() {
        return numAvailability;
    }
}

class Availability {
    private String date;
    private double rate;

    public Availability(String date, double rate) {
        this.date = date;
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public double getRate() {
        return rate;
    }
}

class Customer {
    private String name;
    private String address;
    private String phoneNumber;

    public Customer(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

class BookingSystem {
    private Labor[] labors;

    public BookingSystem() {
        labors = new Labor[]{
                new Labor("Painter", 3),
                new Labor("Plumber", 3),
                new Labor("Technician", 3),
                new Labor("Cleaner", 3)
        };

        // Adding availability for demo purposes
        labors[0].addAvailability("03/05/2025", 500.0);
        labors[0].addAvailability("04/05/2025", 520.0);
        labors[0].addAvailability("05/05/2025", 525.0);

        labors[1].addAvailability("03/05/2025", 625.0);
        labors[1].addAvailability("04/05/2025", 627.0);
        labors[1].addAvailability("05/05/2025", 630.0);

        labors[2].addAvailability("03/05/2025", 730.0);
        labors[2].addAvailability("04/05/2025", 732.0);
        labors[2].addAvailability("05/05/2025", 735.0);

        labors[3].addAvailability("03/05/2025", 415.0);
        labors[3].addAvailability("04/05/2025", 417.0);
        labors[3].addAvailability("05/05/2025", 420.0);
    }

    public void displayAvailableLabors(String date) {
        System.out.println("Available Labors for " + date + ":");
        for (Labor labor : labors) {
            for (int i = 0; i < labor.getNumAvailability(); i++) {
                Availability availability = labor.getAvailabilityArray()[i];
                if (availability.getDate().equals(date)) {
                    System.out.println("- " + labor.getType() + " (Rate: Rs." + availability.getRate() + ")");
                    break; // Found availability, no need to continue checking
                }
            }
        }
    }

    public void bookLabor(String laborType, String date) {
        Scanner scanner = new Scanner(System.in);
        for (Labor labor : labors) {
            if (labor.getType().equalsIgnoreCase(laborType)) {
                for (int i = 0; i < labor.getNumAvailability(); i++) {
                    Availability availability = labor.getAvailabilityArray()[i];
                    if (availability.getDate().equals(date)) {
                        System.out.println("Enter your name:");
                        String name = scanner.nextLine();
                        System.out.println("Enter your address:");
                        String address = scanner.nextLine();
                        System.out.println("Enter your phone number:");
                        String phoneNumber = scanner.nextLine();
                        System.out.println("Labor " + laborType + " is available for " + date + " at Rs." + availability.getRate() + " per hour.");
                        System.out.println("Name: " + name);
                        System.out.println("Address: " + address);
                        System.out.println("Phone Number: " + phoneNumber);
                        System.out.println("Do you want to book this labor? (yes/no)");
                        String confirmation = scanner.nextLine();
                        if (confirmation.equalsIgnoreCase("yes")) {
                            Customer customer = new Customer(name, address, phoneNumber);
                            System.out.println("Labor " + laborType + " booked successfully for " + date + " at Rs." + availability.getRate() + " per hour.");
                            displayCustomerDetails(customer);
                            return;
                        } else if (confirmation.equalsIgnoreCase("no")) {
                            System.out.println("Booking canceled.");
                            return;
                        } else {
                            System.out.println("Invalid input! Please enter 'yes' or 'no'.");
                            return;
                        }
                    }
                }
                System.out.println("Sorry, labor " + laborType + " is not available for " + date);
                return;
            }
        }
        System.out.println("Invalid labor type.");
    }

    private void displayCustomerDetails(Customer customer) {
        System.out.println("\nCustomer Details:");
        System.out.println("Name: " + customer.getName());
        System.out.println("Address: " + customer.getAddress());
        System.out.println("Phone Number: " + customer.getPhoneNumber());
    }
}

public class LabourBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingSystem bookingSystem = new BookingSystem();

        System.out.println("Welcome to the Labor Booking System!");

        while (true) {
            System.out.println("\nEnter the date for booking (DD/MM/YYYY) or type 'exit' to quit:");
            String date = scanner.nextLine();

            if(date.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program.");
                break;
            }

            bookingSystem.displayAvailableLabors(date);

            System.out.println("\nSelect the type of labor:");
            System.out.println("1. Painter");
            System.out.println("2. Plumber");
            System.out.println("3. Technician");
            System.out.println("4. Cleaner");
            System.out.println("5. Back to date selection");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    bookingSystem.bookLabor("Painter", date);
                    break;
                case 2:
                    bookingSystem.bookLabor("Plumber", date);
                    break;
                case 3:
                    bookingSystem.bookLabor("Technician", date);
                    break;
                case 4:
                    bookingSystem.bookLabor("Cleaner", date);
                    break;
                case 5:
                    continue;
                case 6:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 6.");
            }

            System.out.println("\nDo you want to book more labors? (yes/no)");
            String moreBookingChoice = scanner.nextLine();
            if (!moreBookingChoice.equalsIgnoreCase("yes")) {
                System.out.println("Exiting the program.");
                break;
            }
        }
    }
}
