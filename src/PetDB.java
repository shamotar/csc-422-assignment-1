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
}
