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

    private String _buildHeader() {
        return _tableEdge +
                String.format("| %-3s | %-10s | %-4s |\n", "ID", "NAME", "AGE") +
                _tableEdge;
    }

    private String _buildFooter() {
        return _tableEdge +
                _pets.size() + " rows in set.\n";
    }

    private String _buildRow(Pet pet) {
        return String.format("| %3d | %-10s | %4d |\n", pet.getId(), pet.getName(), pet.getAge());
    }

    public String listPets() {
        String result = _buildHeader();
        for (Pet pet : _pets) {
            result += _buildRow(pet);
        }
        result += _buildFooter();
        return result;
    }
}
