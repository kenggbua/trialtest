package kevin.Eurofunk.Modells;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import kevin.Eurofunk.repositories.PermissionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permissions")
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id", unique = true, nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "permission", unique = true)
    private String permission;

    @Column(name = "blacklisted")
    private Boolean blackListed;

    @ManyToMany(targetEntity = User.class, mappedBy = "permissions", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @ManyToMany(targetEntity = UserGroups.class, mappedBy = "permissions", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserGroups> userGroups = new HashSet<>();
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Boolean getBlackListed() {
        return blackListed;
    }

    public void setBlackListed(Boolean blackListed) {
        this.blackListed = blackListed;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<UserGroups> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<UserGroups> userGroups) {
        this.userGroups = userGroups;
    }
}

@Component
class PermissionsLoader implements CommandLineRunner {

    @Autowired
    PermissionsRepository permissionsRepository;

    @Override
    public void run(String... args) {
        String[] roles = new String[]{
                "USER",
                "ADMIN",
                "EDITOR"
        };

        for (String role : roles) {
            if (permissionsRepository.findBypermission(role).isEmpty()) {
                Permissions entity = new Permissions();
                entity.setPermission(role);
                permissionsRepository.save(entity);
            }
        }
    }
}