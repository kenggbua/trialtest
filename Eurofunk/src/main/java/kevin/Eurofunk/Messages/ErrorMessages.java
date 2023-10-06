package kevin.Eurofunk.Messages;

public enum ErrorMessages {
    USER_DOES_NOT_EXIST("User does not exist!", "USER_DOES_NOT_EXIST"),
    GROUP_DOES_NOT_EXIST("GROUP does not exist!", "GROUP_DOES_NOT_EXIST"),
    USER_ALREADY_EXISTS("User already exists!", "USER_ALREADY_EXISTS"),
    GROUP_ALREADY_EXISTS("Group already exists!", "GROUP_ALREADY_EXISTS");

    private final String message;
    private final String code;

    ErrorMessages(String message, String errorCode) {
        this.message = message;
        this.code = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
