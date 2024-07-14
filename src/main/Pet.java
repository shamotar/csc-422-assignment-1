/*
 *      Title:      Pet Database Program (Assignment 1)
 *      Author:     Norlander, Robert
 *      Date:       2024-07-07
 *      Class:      CSC 422-100 Software Engineering (Summer 2024)
 *      Instructor: Prof. James Tucker
 */

import exceptions.AgeError;

public class Pet {
    private int _id;
    private String _name;
    private int _age;

    public Pet(String name, int age) throws AgeError {
        setName(name);
        setAge(age);
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

    public void setAge(int age) throws AgeError {
        if (age < 1 || age > 20) {
            throw new AgeError("Age must be between 1 and 20");
        }
        _age = age;
    }
}
