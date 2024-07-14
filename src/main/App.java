/*
 *      Title:      Pet Database Program (Assignment 1)
 *      Author:     Norlander, Robert
 *      Date:       2024-07-07
 *      Class:      CSC 422-100 Software Engineering (Summer 2024)
 *      Instructor: Prof. James Tucker
 */

import java.io.File;

public class App {
    static String dbDataFileName = "src/data.txt";
    public static void main(String[] args) throws Exception {
        // Initialize the data file
        boolean dbFileInitialized = initializeDbFile();
        if (!dbFileInitialized) {
            System.exit(1);
        }

        // Initialize the PetDB object
        PetDB db = new PetDB(5);

        // Load data from file
        boolean successfulLoad = loadFile(db);
        if (!successfulLoad) {
            System.exit(1);
        }
        
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
                    saveFile(db);
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
            // Validate the user input
            UserPetInput userPetInput;
            try {
                userPetInput = new UserPetInput(userInput);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                continue;
            }

            String[] userTokens = userPetInput.input().split(" ");
            String name = userTokens[0];
            int age = Integer.parseInt(userTokens[1]);
            try {
                db.addPet(new Pet(name, age), -1);
            } catch (Exception e) {
                System.err.println("Error adding pet: " + e.getMessage());
                break;
            }
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

        Pet pet;
        try {
            pet = db.getById(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        String oldName = pet.getName();
        int oldAge = pet.getAge();

        System.out.println("Enter the new name and age:");
        userInput = System.console().readLine();

        // Validate the user input
        UserPetInput userPetInput;
        try {
            userPetInput = new UserPetInput(userInput);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }

        String[] userTokens = userPetInput.input().split(" ");
        String newName = userTokens[0];
        int newAge = Integer.parseInt(userTokens[1]);
        try {
            db.updateById(id, newName, newAge);
        } catch (Exception e) {
            System.err.println("Error updating pet: " + e.getMessage());
            return;
        }
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
        Pet pet;
        try {
            pet = db.getById(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        String name = pet.getName();
        int age = pet.getAge();
        try {
            db.removeById(id);
        } catch (Exception e) {
            System.err.println("Error removing pet: " + e.getMessage());
            return;
        }
        System.out.printf("%s %d is removed.\n", name, age);
    }

    public static boolean initializeDbFile() {
        File file = new File(dbDataFileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Data file created.");
                return true;
            } catch (Exception e) {
                System.err.println("Error creating data file: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    public static boolean loadFile(PetDB db) {
        File file = new File(dbDataFileName);
        try {
            db.loadFromFile(file);
            System.out.println("Data loaded from file.");
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static void saveFile(PetDB db) {
        File file = new File(dbDataFileName);
        try {
            db.saveToFile(file);
            System.out.println("Data saved to file.");
        } catch (Exception e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }
}