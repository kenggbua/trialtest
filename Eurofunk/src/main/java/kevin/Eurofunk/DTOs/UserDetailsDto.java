package kevin.Eurofunk.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserDetailsDto {


    private Long id;
    @NotBlank
    @Size(max = 15)
    private String userName;
    private List<String> permissions;
    private List<String> blacklistedPermissions;

    private List<Integer> groups;

    public UserDetailsDto(String userName, Long id, List<String> permissions, List<String> blacklistedPermissions, List<Integer> groups) {
        this.userName = userName;
        this.id = id;
        this.permissions = permissions;
        this.blacklistedPermissions = blacklistedPermissions;
        this.groups = groups;
    }

    public UserDetailsDto(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getBlacklistedPermissions() {
        return blacklistedPermissions;
    }

    public void setBlackklistedPermissions(List<String> blacklistedPermissions) {
        this.blacklistedPermissions = blacklistedPermissions;
    }

    public List<Integer> getGroups() {
        return groups;
    }

    public void setGroups(List<Integer> groups) {
        this.groups = groups;
    }
}
