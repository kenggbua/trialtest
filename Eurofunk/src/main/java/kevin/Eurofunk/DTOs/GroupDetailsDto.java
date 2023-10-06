package kevin.Eurofunk.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;

public class GroupDetailsDto {
    private Long id;
    @NotBlank
    @Size(max = 15)
    private String groupName;

    private ArrayList<String> permissions;
    private ArrayList<String> blackListedpermissions;

    public GroupDetailsDto(Long id, String groupName, ArrayList<String> permissions, ArrayList<String> blackListedpermissions) {
        this.id = id;
        this.groupName = groupName;
        this.permissions = permissions;
        this.blackListedpermissions = blackListedpermissions;
    }

    public GroupDetailsDto(String groupName) {
        this.groupName = groupName;
    }

    public String getName() {
        return groupName;
    }

    public void setName(String groupName) {
        this.groupName = groupName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<String> permissions) {
        this.permissions = permissions;
    }

    public ArrayList<String> getBlackListedpermissions() {
        return blackListedpermissions;
    }

    public void setBlackListedpermissions(ArrayList<String> blackListedpermissions) {
        this.blackListedpermissions = blackListedpermissions;
    }
}
