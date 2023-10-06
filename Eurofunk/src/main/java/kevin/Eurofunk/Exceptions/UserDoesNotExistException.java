package kevin.Eurofunk.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User was not found!")
public class UserDoesNotExistException extends Exception{

    public UserDoesNotExistException(String errorMessage){
        super(errorMessage);
    }

}
