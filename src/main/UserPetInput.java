import exceptions.InvalidInputError;

public class UserPetInput {
    private String _input;

    public UserPetInput(String input) throws InvalidInputError {
        _input = input;
        _validateInput();
    }

    private void _validateInput() throws InvalidInputError {
        if (_input == null) {
            throw new InvalidInputError("Input cannot be null");
        }
        String[] inputTokens = _input.split(" ");
        if (inputTokens.length != 2) {
            throw new InvalidInputError("Input must contain two tokens separated by a space");
        }
        try {
            _validateTokens(inputTokens);
        } catch (InvalidInputError e) {
            throw new InvalidInputError(e.getMessage());
        }
    }


    private void _validateTokens(String[] tokens) throws InvalidInputError {
        // Validate the first token is a string
        if (tokens[0].length() == 0) {
            throw new InvalidInputError("Name cannot be empty");
        }

        // Validate the second token is an integer
        try {
            Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            throw new InvalidInputError("Age must be an integer");
        }
    }
    public String input() {
        return _input;
    }

}
