/*
 *      Title:      Pet Database Program (Assignment 1)
 *      Author:     Norlander, Robert
 *      Date:       2024-07-07
 *      Class:      CSC 422-100 Software Engineering (Summer 2024)
 *      Instructor: Prof. James Tucker
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.InvalidFileFormatError;

public class PetDB {
    private int _currentId = 0;
    private ArrayList<Pet> _pets = new ArrayList<Pet>();
    private String _tableEdge = "+-------------------------+\n";

    public void addPet(Pet pet, int id) {
        if (id == -1) {
            pet.setId(_currentId);
            _pets.add(pet);
            _currentId++;
        } else {
            pet.setId(id);
            _pets.add(pet);
            if (id >= _currentId) {
                _currentId = id + 1;
            }
        }
    }

    public ArrayList<Pet> getPets() {
        return _pets;
    }

    private String _buildHeader() {
        return _tableEdge +
                String.format("| %-3s | %-10s | %-4s |\n", "ID", "NAME", "AGE") +
                _tableEdge;
    }

    private String _buildFooter(int rowCount) {
        return _tableEdge +
                rowCount + " rows in set.\n";
    }

    private String _buildRow(Pet pet) {
        return String.format("| %3d | %-10s | %4d |\n", pet.getId(), pet.getName(), pet.getAge());
    }

    public String listPets(ArrayList<Pet> pets) {
        String result = _buildHeader();
        for (Pet pet : pets) {
            result += _buildRow(pet);
        }
        result += _buildFooter(pets.size());
        return result;
    }

    public ArrayList<Pet> searchByName(String name) {
        ArrayList<Pet> result = new ArrayList<Pet>();
        for (Pet pet : _pets) {
            if (pet.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(pet);
            }
        }
        return result;
    }

    public ArrayList<Pet> searchByAge(int age) {
        ArrayList<Pet> result = new ArrayList<Pet>();
        for (Pet pet : _pets) {
            if (pet.getAge() == age) {
                result.add(pet);
            }
        }
        return result;
    }

    public Pet getById(int id) {
        for (Pet pet : _pets) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        return null;
    }

    public void removeById(int id) {
        Pet pet = getById(id);
        if (pet != null) {
            _pets.remove(pet);
        }
    }

    public void updateById(int id, String name, int age) {
        Pet pet = getById(id);
        if (pet != null) {
            pet.setName(name);
            pet.setAge(age);
        }
    }

    public void loadFromFile(File file) throws InvalidFileFormatError {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                if (tokens.length != 3) {
                    throw new InvalidFileFormatError("Format should be 'ID,Name,Age': " + line);
                }
                String name = tokens[1];
                try {
                    Integer.parseInt(tokens[2]);
                } catch (NumberFormatException e) {
                    throw new InvalidFileFormatError("Invalid age: " + line);
                }
                int age = Integer.parseInt(tokens[2]);
                try {
                    Integer.parseInt(tokens[0]);
                } catch (NumberFormatException e) {
                    throw new InvalidFileFormatError("Invalid ID: " + line);
                }
                int id = Integer.parseInt(tokens[0]);
                addPet(new Pet(name, age), id);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }
    }

    public void saveToFile(File file) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        for (Pet pet : _pets) {
            writer.println(pet.getId() + "," + pet.getName() + "," + pet.getAge());
        }
        writer.close();
    }
}
