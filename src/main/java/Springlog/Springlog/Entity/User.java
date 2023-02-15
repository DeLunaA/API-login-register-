package Springlog.Springlog.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grh_user")
public class User  implements Serializable {  // implements Seira


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // difference between autoand identity ?
    Integer id;  // change to Long

    @NonNull
    @Column(name = "username",unique = true)
    String username;


    @NonNull
    @Column
    String password;



    @ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles" , joinColumns = @JoinColumn(name = "user_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id" , referencedColumnName = "id"))

    private List<Role> roles = new ArrayList<>();


}
