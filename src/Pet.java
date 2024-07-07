/*
 *      Title:      Pet Database Program (Assignment 1)
 *      Author:     Norlander, Robert
 *      Date:       2024-07-07
 *      Class:      CSC 422-100 Software Engineering (Summer 2024)
 *      Instructor: Prof. James Tucker
 */

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

    public void setName(String name) {
        _name = name;
    }

    public void setAge(int age) {
        _age = age;
    }
}
