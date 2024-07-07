/*
 *      Title:      Pet Database Program (Assignment 1)
 *      Author:     Norlander, Robert
 *      Date:       2024-07-07
 *      Class:      CSC 422-100 Software Engineering (Summer 2024)
 *      Instructor: Prof. James Tucker
 */

public class App {
    public static void main(String[] args) throws Exception {
        // Initialize the PetDB object
        PetDB db = new PetDB();
        
        System.out.println("Pet Database Program");
        while (true) {
            printMenu();
            String userInput = System.console().readLine();
            switch (userInput) {
                case "1":
                    viewAllPets(db);
                    break;
                case "2":
                    addMorePets(db);
                    break;
                case "3":
                    updatePet(db);
                    break;
                case "4":
                    removePet(db);
                    break;
                case "5":
                    searchPetsByName(db);
                    break;
                case "6":
                    searchPetsByAge(db);
                    break;
                case "7":
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public static void printMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. View all pets");
        System.out.println("2. Add more pets");
        System.out.println("3. Update an existing pet");
        System.out.println("4. Remove an existing pet");
        System.out.println("5. Search pets by name");
        System.out.println("6. Search pets by age");
        System.out.println("7. Exit program");
    }

    public static void viewAllPets(PetDB db) {
        System.out.println(db.listPets(db.getPets()));
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
            if (inputTokens.length != 2) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }
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

    public static void searchPetsByName(PetDB db) {
        System.out.println("Enter a name to search:");
        String name = System.console().readLine();
        System.out.println(db.listPets(db.searchByName(name)));
    }

    public static void searchPetsByAge(PetDB db) {
        System.out.println("Enter an age to search:");
        int age = Integer.parseInt(System.console().readLine());
        System.out.println(db.listPets(db.searchByAge(age)));
    }

    public static void updatePet(PetDB db) {
        System.out.println("Enter the ID of the pet you want to update:");
        String userInput = System.console().readLine();
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please try again.");
            return;
        }
        int id = Integer.parseInt(userInput);
        Pet pet = db.getById(id);
        if (pet == null) {
            System.out.println("Pet not found.");
            return;
        }
        String oldName = pet.getName();
        int oldAge = pet.getAge();

        System.out.println("Enter the new name and age:");
        userInput = System.console().readLine();
        String[] inputTokens = userInput.split(" ");
        if (inputTokens.length != 2) {
            System.out.println("Invalid input. Please try again.");
            return;
        }
        String newName = inputTokens[0];
        try {
            Integer.parseInt(inputTokens[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid age. Please try again.");
            return;
        }
        int newAge = Integer.parseInt(inputTokens[1]);
        db.updateById(id, newName, newAge);
        System.out.printf("%s %d changed to %s %d.\n", oldName, oldAge, newName, newAge);
    }

    public static void removePet(PetDB db) {
        System.out.println("Enter the ID of the pet you want to remove:");
        String userInput = System.console().readLine();
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please try again.");
            return;
        }
        int id = Integer.parseInt(userInput);
        Pet pet = db.getById(id);
        if (pet == null) {
            System.out.println("Pet not found.");
            return;
        }
        String name = pet.getName();
        int age = pet.getAge();
        db.removeById(id);
        System.out.printf("%s %d is removed.\n", name, age);
    }
}