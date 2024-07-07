public class App {
    public static void main(String[] args) throws Exception {
        // Initialize the PetDB object
        PetDB db = new PetDB();
        
        System.out.println("Pet Database Program");
        while (true) {
            printMenu();
            String userInput = System.console().readLine();
            if (userInput.equals("1")) {
                viewAllPets(db);
            } else if (userInput.equals("2")) {
                addMorePets(db);
            } else if (userInput.equals("3")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. View all pets");
        System.out.println("2. Add more pets");
        System.out.println("3. Exit");
    }

    public static void viewAllPets(PetDB db) {
        System.out.println(db.listPets());
    }

    public static void addMorePets(PetDB db) {
        int count = 0;
        // Loop until user types "done"
        while (true) {
            System.out.println("add pet (name, age):");
            String userInput = System.console().readLine();
            if (userInput.equals("done")) {
                break;
            }
            String[] inputTokens = userInput.split(" ");
            String name = inputTokens[0];
            try {
                Integer.parseInt(inputTokens[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Please try again.");
                continue;
            }
            int age = Integer.parseInt(inputTokens[1]);
            db.addPet(new Pet(name, age));
            count++;
        }
        System.out.println(count + " pets added.\n");
    }
}