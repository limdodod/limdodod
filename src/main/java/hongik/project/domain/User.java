package hongik.project.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
@Entity

public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String password;
    private String name ;
    private int age;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Item> item = new ArrayList<>();
}
