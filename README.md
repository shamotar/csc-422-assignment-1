# Pet Database Program

This program is a simple pet database program that allows the user to add, remove, and search for pets in a database. The program is written in Java and uses a simple text file to store the pet data. The program is designed to be easy to use and understand.

## Features

- Add a pet to the database
- Remove a pet from the database
- Search for a pet in the database
- List all pets in the database

## Usage

To use the program, simply run the `App` class. The program will prompt you to enter a command, which can be one of the following:

- `1` to view all pets
- `2` to add a pet
- `3` to update a pet
- `4` to remove a pet
- `5` to search for a pet by name
- `6` to search for a pet by age
- `7` to exit the program

## Example

Here is an example of how to use the program:

``` shell
Welcome to the Pet Database Program!

What would you like to do?
1. View all pets
2. Add more pets
3. Update an existing pet
4. Remove an existing pet
5. Search pets by name
6. Search pets by age
7. Exit program
1
+-------------------------+
| ID  | NAME       | AGE  |
+-------------------------+
|   0 | Robert     |    3 |
+-------------------------+
1 rows in set.

```

## Unit Tests

Unit testing leverages JUnit 5. The tests are located in the `src/test/` directory. Visual Studio Code supports JUnit Jupiter and the JUnit Platform via the [Java Test Runner](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-test) extension which is installed by default as part of the [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack).

For more information consult the Testing section of the [Java in Visual Studio Code](https://code.visualstudio.com/docs/languages/java#_testing) documentation.
