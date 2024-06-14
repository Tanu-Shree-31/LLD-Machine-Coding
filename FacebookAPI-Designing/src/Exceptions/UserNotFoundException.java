package Exceptions;

public class UserNotFoundException extends FacebookException {
    public UserNotFoundException(Integer userId) {
        super("User with id " + userId + " not found.");
    }
}
