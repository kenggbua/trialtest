package kevin.Eurofunk.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Group already exists!")
public class GroupAlreadyExistsException extends Exception{

    public GroupAlreadyExistsException(String errorMessage){
        super(errorMessage);
    }

}
