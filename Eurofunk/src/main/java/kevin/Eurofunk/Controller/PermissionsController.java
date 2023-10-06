package kevin.Eurofunk.Controller;

import jakarta.validation.constraints.NotNull;
import kevin.Eurofunk.DTOs.AssignPermissionDto;
import kevin.Eurofunk.Messages.SuccessMessages;
import kevin.Eurofunk.Modells.Permissions;
import kevin.Eurofunk.Modells.User;
import kevin.Eurofunk.Modells.UserGroups;
import kevin.Eurofunk.Services.PermissionsService;
import kevin.Eurofunk.Services.UserService;
import kevin.Eurofunk.Services.UsersGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
@CrossOrigin(origins = "http://localhost:4200")
public class PermissionsController {

    @Autowired
    private PermissionsService permissionsService;

    @GetMapping("")
    public ResponseEntity<?> getAllPermissions() {
        return ResponseEntity.ok(permissionsService.getAllPermission());
    }

    @PostMapping("/{permission}")
    public ResponseEntity<?> addPermission(@PathVariable("permission") @NotNull String permission) {
        return ResponseEntity.ok(permissionsService.addPermission(permission));
    }

    @PostMapping("")
    public ResponseEntity<?> givePermissionToUser(@RequestBody AssignPermissionDto dto) {
        permissionsService.giveUserPermission(dto.getName(), dto.getPermission());
        return ResponseEntity.ok(SuccessMessages.GRANTED_USER_PERMISSION_SUCCESS.getMessage());
    }

    @PostMapping("/remove-permission")
    public ResponseEntity<?> removePermissionFromUser(@RequestBody AssignPermissionDto dto) {
        permissionsService.removeUserPermission(dto.getName(), dto.getPermission());
        return ResponseEntity.ok(SuccessMessages.REMOVED_USER_PERMISSION_SUCCESS.getMessage());
    }

    @PostMapping("/group-permission")
    public ResponseEntity<?> givePermissionToGroup(@RequestBody AssignPermissionDto dto) {
        permissionsService.giveGroupPermission(dto.getName(), dto.getPermission());
        return ResponseEntity.ok(SuccessMessages.GRANTED_GROUP_PERMISSION_SUCCESS.getMessage());
    }

    @PostMapping("/remove-permission-group")
    public ResponseEntity<?> removePermissionFromGroup(@RequestBody AssignPermissionDto dto) {
        permissionsService.removeGroupPermission(dto.getName(), dto.getPermission());
        return ResponseEntity.ok(SuccessMessages.REMOVED_GROUP_PERMISSION_SUCCESS.getMessage());
    }

}
