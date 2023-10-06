package kevin.Eurofunk.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Group was not found!")
public class GroupDoesNotExistException extends Exception{

    public GroupDoesNotExistException(String errorMessage){
        super(errorMessage);
    }

}
