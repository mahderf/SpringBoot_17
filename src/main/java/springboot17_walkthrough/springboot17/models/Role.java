package springboot17_walkthrough.springboot17.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Collection<User>users;

  public Role(){

    this.users=new HashSet<User>();
   }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

//    public void addUser(User usr)
//    {
//        users.add(usr);
//    }
}
