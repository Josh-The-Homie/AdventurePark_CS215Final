import java.util.Scanner;
import java.util.TimerTask;
import java.awt.Desktop;
import java.net.URI;

/**
 * This program simulates a theme park.
 * 
 * @author Joshua Henderson
 */
public class AdventurePark {
    private static Player defaultPlayer = new Player();
    private static ArrayBag<Merchandise> purchasedMerchandise = new ArrayBag<>();
    private static Queue<Ride> rideQueue = new Queue<>();
    private static CustomTimer rideTimer = new CustomTimer();

    public static void main(String[] args) {
        System.out.println("Welcome to Adventure Park!");

        // Ask if the user wants to buy a pass
        System.out.println("Would you like to purchase a VIP pass or a regular pass?");
        System.out.println("The VIP pass is $100, and the Regular Pass is $20.");
        System.out.println("Your current balance is $" + defaultPlayer.getBalance());
        System.out.println("Enter V for VIP and R for Regular.");

        Scanner scanner = new Scanner(System.in);
        String passType = scanner.next();

        if (passType.equals("V")) {
            purchaseVIPPass();
        } else if (passType.equals("R")) {
            purchaseRegularPass();
        } else {
            System.out.println("Invalid choice. Please restart the program and enter V or R only.");
            System.exit(0);
        }

        // After purchasing the pass, display the main menu
        displayMainMenu();
    }
//purchases the vip pass
    private static void purchaseVIPPass() {
        if (defaultPlayer.getBalance() >= 100) {
            defaultPlayer.setBalance(defaultPlayer.getBalance() - 100);
            System.out.println("You purchased the VIP pass. Have a fun day!");
            System.out.println("Your new balance is $" + defaultPlayer.getBalance());
        } else {
            System.out.println("Insufficient balance to purchase VIP pass.");
        }
    }
//purchases the regular pass
    private static void purchaseRegularPass() {
        if (defaultPlayer.getBalance() >= 20) {
            defaultPlayer.setBalance(defaultPlayer.getBalance() - 20);
            System.out.println("You purchased the Regular pass. Have a fun day!");
            System.out.println("Your new balance is $" + defaultPlayer.getBalance());
        } else {
            System.out.println("Insufficient balance to purchase Regular pass.");
        }
    }
    private static void openBrowser(String url) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception e) {
                System.err.println("Error opening browser: " + e.getMessage());
            }
        } else {
            System.out.println("Desktop browsing is not supported on this system.");
        }
    }
//displays the main menu.
    private static void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAdventure Park Main Menu:");
            System.out.println("1. Go to the Shop");
            System.out.println("2. Ride Roller Coaster");
            System.out.println("3. Ride Ferris Wheel");
            System.out.println("4. Ride Bumper Cars");
            System.out.println("5. View Purchased Merchandise");
            System.out.println("6. Exit");
            System.out.println("Enter your choice (1-6):");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    goToShop();
                    break;
                case 2:
                	openBrowser("https://www.youtube.com/watch?v=jYm83y3b7Bg&t=100s");
                    enqueueRide("Roller Coaster");
                    break;
                case 3:
                	openBrowser("https://www.youtube.com/watch?v=oj6ZarXBsDE");
                    enqueueRide("Ferris Wheel");
                    break;
                case 4:
                	openBrowser("https://www.youtube.com/watch?v=CYGO2zWpv7Q&t=142s");
                    enqueueRide("Bumper Cars");
                    break;
                case 5:
                    viewPurchasedMerchandise();
                    break;
                case 6:
                    System.out.println("Thanks for visiting Adventure Park! Have a great day!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }
//simply goes to the shop,and gives you a prompt to purchase or exit the shop, then displays a main menu
    private static void goToShop() {
        boolean continueShopping = true;

        while (continueShopping) {
            System.out.println("\nWelcome to the Adventure Park Shop!");
            System.out.println("Merchandise available:");

            Merchandise[] merchandiseList = {
                    new Merchandise("Adventure Park T-Shirt", 15),
                    new Merchandise("Adventure Park Action Figure", 10),
                    new Merchandise("Adventure Park Hat", 8),
                    new Merchandise("Adventure Park Socks", 5),
                    new Merchandise("Elf Maid Poster", 12)
            };

            for (int i = 0; i < merchandiseList.length; i++) {
                System.out.println((i + 1) + ". " + merchandiseList[i].getName() + " - $" + merchandiseList[i].getPrice());
            }

            System.out.println("Enter the number of the item you would like to purchase (0 to leave):");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice >= 1 && choice <= merchandiseList.length) {
                purchaseMerchandise(merchandiseList[choice - 1]);
            } else if (choice == 0) {
                continueShopping = false;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        displayMainMenu();
    }
//purchases a merchandise of your choice
    private static void purchaseMerchandise(Merchandise item) {
        if (defaultPlayer.getBalance() >= item.getPrice()) {
            defaultPlayer.setBalance(defaultPlayer.getBalance() - item.getPrice());
            System.out.println("You purchased " + item.getName() + ". Have a great day!");
            System.out.println("Your new balance is $" + defaultPlayer.getBalance());
            purchasedMerchandise.add(item);
        } else {
            System.out.println("Insufficient balance to purchase " + item.getName() + ".");
        }
    }
//shows the current merchandise purchased 
    private static void viewPurchasedMerchandise() {
        System.out.println("\nYour Purchased Merchandise:");
        Merchandise[] purchasedItems = purchasedMerchandise.toArray();

        if (purchasedItems.length > 0) {
            for (int i = 0; i < purchasedItems.length; i++) {
                System.out.println((i + 1) + ". " + purchasedItems[i].getName());
            }
        } else {
            System.out.println("You haven't purchased any merchandise yet.");
        }

        displayMainMenu();
    }
//adds a ride to the queue
    private static void enqueueRide(String rideName) {
        int rideDuration = getRideDuration(rideName);
        rideQueue.enqueue(new Ride(rideName, rideDuration));

        System.out.println("You are now in the queue for " + rideName + ". Please wait for your turn.");
        processRideQueue();
    }

//removes the ride from the queue and tells user its their turn to ride.
    private static Ride processRideQueue() {
        if (!rideQueue.isEmpty() && !rideTimer.isTaskScheduled()) {
            Ride nextRide = rideQueue.dequeue();
            int rideDuration = getRideDuration(nextRide.getRideName());
            System.out.println("It's your turn to ride " + nextRide.getRideName() + "!");
            scheduleRideCompletion(rideDuration);
            return nextRide;
        }
        return null;
    }

//getter for the ride duration.
    private static int getRideDuration(String rideName) {
        // Placeholder method for ride duration logic
        if (rideName.equals("Roller Coaster")) {
            System.out.println("Search for \"Wildcats Revenge front seat on-ride 5K POV @60fps Hersheypark\" on YouTube.");
            return 10000; // 10 seconds
        } else if (rideName.equals("Bumper Cars")) {
            System.out.println("Search for \"Speedway Bumper Cars On Ride POV - Belmont Park\" on YouTube.");
            return 8000; // 8 seconds
        } else if (rideName.equals("Ferris Wheel")) {
            System.out.println("Search for \"Ferris Wheel Orange County Fair 4K PoV\" on YouTube.");
            return 9000; // 9 seconds
        }

        // Default duration if the ride name is not recognized
        return 10000; // 10 seconds
    }//end getRideDuration


//timer for the completion of the ride.
    private static void scheduleRideCompletion(int rideDuration) {
        TimerTask rideCompletionTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("You have completed the ride! Hope you enjoyed it!");
                processRideQueue();
            }//end run
        };//end TimerTask

        rideTimer.scheduleTask(rideCompletionTask, rideDuration);
    }//end scheduleRideCompletion
    }//end AdventurePark

