package kevin.Eurofunk.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import kevin.Eurofunk.DTOs.AssignUserGroupDto;
import kevin.Eurofunk.DTOs.UserDetailsDto;
import kevin.Eurofunk.Exceptions.UserAlreadyExistsException;
import kevin.Eurofunk.Exceptions.UserDoesNotExistException;
import kevin.Eurofunk.Messages.ErrorMessages;
import kevin.Eurofunk.Messages.SuccessMessages;
import kevin.Eurofunk.Modells.User;
import kevin.Eurofunk.Modells.UserGroups;
import kevin.Eurofunk.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }


    @GetMapping("/{userName}")
    public ResponseEntity<?> getUserByName(@PathVariable("userName") @NotNull String userName) {
        return ResponseEntity.ok(userService.getUserByName(userName));
    }


    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody UserDetailsDto dto) {
        try {
            User user = userService.convertUserDTO(dto);
            return ResponseEntity.ok(userService.createUser(user));
        }catch (UserAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, ErrorMessages.USER_ALREADY_EXISTS.getMessage(), e);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") @NotNull Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(SuccessMessages.USER_DELETED_SUCCESS.getMessage());
        }catch (UserDoesNotExistException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, ErrorMessages.USER_DOES_NOT_EXIST.getMessage(), e);

        }
    }

    @PostMapping("/assign-user-group")
    public ResponseEntity<?> assignUserGroup(@Valid @RequestBody AssignUserGroupDto dto) {
        return ResponseEntity.ok(userService.assignUserGroup(dto));
    }

    @PostMapping("/unassign-user-group")
    public ResponseEntity<?> unassignUserGroup(@Valid @RequestBody AssignUserGroupDto dto) {
        userService.unassignUserGroup(dto);
        return ResponseEntity.ok(SuccessMessages.USER_REMOVED_FROM_GROUP_SUCCESS.getMessage());
    }

}
