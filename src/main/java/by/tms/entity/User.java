package by.tms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String name;

    @OneToOne
    private Address address;
    @ManyToMany
    private List<Tag> tags;

    public void addTag(Tag tag){
        if (tags == null){
            tags = new ArrayList<>();
        }
        tags.add(tag);
    }
}