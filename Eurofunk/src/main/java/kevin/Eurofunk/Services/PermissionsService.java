package kevin.Eurofunk.Services;

import jakarta.persistence.EntityNotFoundException;
import kevin.Eurofunk.DTOs.AssignPermissionDto;
import kevin.Eurofunk.Modells.Permissions;
import kevin.Eurofunk.Modells.User;
import kevin.Eurofunk.Modells.UserGroups;
import kevin.Eurofunk.repositories.PermissionsRepository;
import kevin.Eurofunk.repositories.UserRepository;
import kevin.Eurofunk.repositories.UsersGroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionsService {

    @Autowired
    private PermissionsRepository permissionsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersGroupsRepository usersGroupsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UsersGroupsService usersGroupsService;


    @Transactional
    public Permissions getPermission(String permission){
        Optional<Permissions> permissions = permissionsRepository.findBypermission(permission);
        return permissions.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public List<Permissions> getAllPermission(){
        return permissionsRepository.findAll();
    }

    @Transactional
    public Permissions addPermission(String permission){
        Permissions permissions = new Permissions();
        permissions.setPermission(permission);
        return permissionsRepository.save(permissions);
    }

    @Transactional
    public void giveUserPermission(String userName, String permission){
        User user = userService.getUserByName(userName);
        user.getPermissions().remove(getPermission(permission));
        userRepository.save(user);
    }

    @Transactional
    public void removeUserPermission(String userName, String permission){
        User user = userService.getUserByName(userName);
        user.getPermissions().remove(getPermission(permission));
        userRepository.save(user);
    }

    @Transactional
    public void giveGroupPermission(String groupName,String permission){
        UserGroups userGroups = usersGroupsService.getGroup(groupName);
        userGroups.getPermissions().add(getPermission(permission));
        usersGroupsRepository.save(userGroups);
    }

    @Transactional
    public void removeGroupPermission(String groupName, String permission){
        UserGroups userGroups = usersGroupsService.getGroup(groupName);
        userGroups.getPermissions().remove(getPermission(permission));
        usersGroupsRepository.save(userGroups);
    }

}
