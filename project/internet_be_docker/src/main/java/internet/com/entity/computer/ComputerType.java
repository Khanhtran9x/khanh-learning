package internet.com.entity.computer;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;


/**
 * Created by: PhucNQ
 * Date created: 09/08/2022
 * Entity: ComputerType
 */
@Entity
public class ComputerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "computer_type_name")
    private String name;

    @OneToMany(mappedBy = "computerType")
    @JsonBackReference(value = "back_computer")
    private Set<Computer> computer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Computer> getComputer() {
        return computer;
    }

    public void setComputer(Set<Computer> computer) {
        this.computer = computer;
    }
}
