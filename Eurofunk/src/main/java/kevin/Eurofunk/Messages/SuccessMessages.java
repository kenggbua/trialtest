package kevin.Eurofunk.Messages;

public enum SuccessMessages {
    USER_DELETED_SUCCESS("User deleted successfully!", "USER_DELETED_SUCCESS"),
    USER_REMOVED_FROM_GROUP_SUCCESS("User removed from group!", "USER_REMOVED_FROM_GROUP_SUCCESS"),
    USER_ADDED_TO_GROUP_SUCCESS("User added to group!", "USER_ADDED_TO_GROUP_SUCCESS"),
    GRANTED_USER_PERMISSION_SUCCESS("Granted user permission!", "GRANTED_USER_PERMISSION_SUCCESS"),
    GRANTED_GROUP_PERMISSION_SUCCESS("Granted group permission!", "GRANTED_GROUP_PERMISSION_SUCCESS"),
    REMOVED_USER_PERMISSION_SUCCESS("Removed user permission!", "REMOVED_USER_PERMISSION_SUCCESS"),
    REMOVED_GROUP_PERMISSION_SUCCESS("Removed group permission!", "REMOVED_GROUP_PERMISSION_SUCCESS"),
    GROUP_DELETED_SUCCESS("Group deleted successfully!", "GROUP_DELETED_SUCCESS");

    private final String message;
    private final String code;

    SuccessMessages(String successMessage, String successCode) {
        this.message = successMessage;
        this.code = successCode;
    }

    public String getMessage() {
        return "{ \"message\":\"" + message + "\"}";
    }

    public String getCode() {
        return code;
    }
}
