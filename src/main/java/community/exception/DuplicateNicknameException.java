package community.exception;

public class DuplicateNicknameException extends RuntimeException {

    public DuplicateNicknameException(String message) {
        super(message);
    }
}