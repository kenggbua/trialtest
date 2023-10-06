package kevin.Eurofunk.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import kevin.Eurofunk.DTOs.GroupDetailsDto;
import kevin.Eurofunk.DTOs.GroupListDto;
import kevin.Eurofunk.Exceptions.GroupAlreadyExistsException;
import kevin.Eurofunk.Exceptions.GroupDoesNotExistException;
import kevin.Eurofunk.Messages.ErrorMessages;
import kevin.Eurofunk.Messages.SuccessMessages;
import kevin.Eurofunk.Modells.UserGroups;
import kevin.Eurofunk.Services.UsersGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users-groups")
@CrossOrigin(origins = "http://localhost:4200")
public class GroupController {

    @Autowired
    private UsersGroupsService usersGroupsService;

    @GetMapping("")
    public ResponseEntity<?> getAllGroups() {
        return ResponseEntity.ok(usersGroupsService.getAllGroups());
    }


    @PostMapping("")
    public ResponseEntity<?> createGroup(@Valid @RequestBody GroupDetailsDto dto) {
        try {
            UserGroups userGroup = usersGroupsService.convertGroupDTO(dto);
            return ResponseEntity.ok(usersGroupsService.createGroup(userGroup));
        } catch (GroupAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ErrorMessages.GROUP_ALREADY_EXISTS.getMessage(), e);
        }
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<?> deleteGroup(@PathVariable("groupId") @NotNull Long groupId) {
        try {
            usersGroupsService.deleteGroup(groupId);
            return ResponseEntity.ok(SuccessMessages.GROUP_DELETED_SUCCESS.getMessage());
        } catch (GroupDoesNotExistException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ErrorMessages.GROUP_DOES_NOT_EXIST.getMessage(), e);

        }

    }


}
