package livemarket.business.b2bcart.models.rules;


import livemarket.business.b2bcart.models.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
public class State extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;


    private Integer sequence;


    @OneToMany
    private Set<State> nextStates = new HashSet<State>();

    @OneToOne
    private State currentState;


    public void addState(State state) {
        if (this.nextStates == null) {
            this.nextStates = new HashSet<State>();
        }
        this.nextStates.add(state);
    }

    private Boolean sendEmilToAdmin;
    private Boolean sendEmilToClient;
    private Boolean sendEmilToClientUser;
    private Boolean sendEmilToStaff;


}

