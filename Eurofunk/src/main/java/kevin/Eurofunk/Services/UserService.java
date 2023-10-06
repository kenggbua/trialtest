package kevin.Eurofunk.Services;

import jakarta.persistence.EntityNotFoundException;
import kevin.Eurofunk.DTOs.AssignUserGroupDto;
import kevin.Eurofunk.DTOs.UserDetailsDto;
import kevin.Eurofunk.Exceptions.UserAlreadyExistsException;
import kevin.Eurofunk.Exceptions.UserDoesNotExistException;
import kevin.Eurofunk.Modells.User;
import kevin.Eurofunk.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersGroupsService usersGroupsService;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserByName(String userName){
        Optional<User> user = userRepository.findByuserName(userName);
        return user.orElseThrow(EntityNotFoundException::new);
    }

    public User getUserById(Long userId){
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public User createUser(User entity) throws UserAlreadyExistsException{
        Optional<User> user = userRepository.findByuserName(entity.getUserName());

        if(user.isPresent()){
            throw new UserAlreadyExistsException("User already exists!");
        }
        entity.setUserName(entity.getUserName());
        return userRepository.save(entity);
    }

    @Transactional
    public void deleteUser(Long userId) throws UserDoesNotExistException {
        try {
            userRepository.delete(getUserById(userId));
        }catch (EntityNotFoundException e){
            throw new UserDoesNotExistException("User does not exist!");
        }
    }

    @Transactional
    public User assignUserGroup(AssignUserGroupDto assignUserGroupDto){
        User user = getUserById(assignUserGroupDto.getUserId());
        user.getUserInGroups().add(usersGroupsService.getGroupById(assignUserGroupDto.getGroupId()));
        return userRepository.save(user);
    }

    @Transactional
    public void unassignUserGroup(AssignUserGroupDto assignUserGroupDto){
        User user = getUserById(assignUserGroupDto.getUserId());
        user.getUserInGroups().remove(usersGroupsService.getGroupById(assignUserGroupDto.getGroupId()));
        userRepository.save(user);
    }

    public User convertUserDTO(UserDetailsDto userDetailsDto){
        User user = new User();
        user.setUserName(userDetailsDto.getUserName());
        return user;
    }
}
