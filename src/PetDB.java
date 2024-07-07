/*
 *      Title:      Pet Database Program (Assignment 1)
 *      Author:     Norlander, Robert
 *      Date:       2024-07-07
 *      Class:      CSC 422-100 Software Engineering (Summer 2024)
 *      Instructor: Prof. James Tucker
 */

import java.util.ArrayList;

public class PetDB {
    private int _currentId = 0;
    private ArrayList<Pet> _pets = new ArrayList<Pet>();
    private String _tableEdge = "+-------------------------+\n";

    public void addPet(Pet pet) {
        pet.setId(_currentId);
        _pets.add(pet);
        _currentId++;
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
}
