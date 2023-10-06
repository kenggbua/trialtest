package kevin.Eurofunk.Services;

import jakarta.persistence.EntityNotFoundException;
import kevin.Eurofunk.DTOs.GroupDetailsDto;
import kevin.Eurofunk.Exceptions.GroupAlreadyExistsException;
import kevin.Eurofunk.Exceptions.GroupDoesNotExistException;
import kevin.Eurofunk.Modells.UserGroups;
import kevin.Eurofunk.repositories.UsersGroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsersGroupsService {

    @Autowired
    private UsersGroupsRepository usersGroupsRepository;

    public List<UserGroups> getAllGroups() {
        return usersGroupsRepository.findAll();
    }

    public UserGroups getGroup(String groupName) {
        Optional<UserGroups> group = usersGroupsRepository.findByGroupName(groupName);
        return group.orElseThrow(EntityNotFoundException::new);
    }

    public UserGroups getGroupById(Long groupId) {
        Optional<UserGroups> group = usersGroupsRepository.findById(groupId);
        return group.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public UserGroups createGroup(UserGroups userGroups) throws GroupAlreadyExistsException {
        Optional<UserGroups> group = usersGroupsRepository.findByGroupName(userGroups.getGroupName());
        if (group.isPresent()) {
            throw new GroupAlreadyExistsException("Group already exists.");
        }
        userGroups.setGroupName(userGroups.getGroupName());
        return usersGroupsRepository.save(userGroups);
    }

    @Transactional
    public void deleteGroup(Long groupId) throws GroupDoesNotExistException {
        try {
            usersGroupsRepository.delete(getGroupById(groupId));
        } catch (EntityNotFoundException e) {
            throw new GroupDoesNotExistException("Group does not exist!");
        }
    }

    public UserGroups convertGroupDTO(GroupDetailsDto userDetailsDto) {
        UserGroups groups = new UserGroups();
        groups.setGroupName(userDetailsDto.getName());
        return groups;
    }

}
