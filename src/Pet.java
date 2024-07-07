public class Pet {
    private int _id;
    private String _name;
    private int _age;

    public Pet(String name, int age) {
        _name = name;
        _age = age;
    }

    public void setId(int id) {
        _id = id;
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public int getAge() {
        return _age;
    }
}
