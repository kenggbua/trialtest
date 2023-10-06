package kevin.Eurofunk.Modells;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "user_name", unique = true)
    private String userName;

    @ManyToMany(targetEntity = Permissions.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Permissions> permissions = new HashSet<>();

    @ManyToMany(targetEntity = UserGroups.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserGroups> userInGroups= new HashSet<>();

    public User(Long id, String userName, Set<Permissions> permissions, Set<UserGroups> userInGroups) {
        this.id = id;
        this.userName = userName;
        this.permissions = permissions;
        this.userInGroups = userInGroups;
    }

    public User() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<UserGroups> getUserInGroups() {
        return userInGroups;
    }

    public void setUserInGroups(Set<UserGroups> userInGroups) {
        this.userInGroups = userInGroups;
    }
}
