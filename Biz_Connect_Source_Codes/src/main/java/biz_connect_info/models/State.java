package biz_connect_info.models;

//////////////////////////////////////////////////////////////////
// STATE
//////////////////////////////////////////////////////////////////

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mst_state")
@Data
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    private Integer stateId;

    @Column(name = "state_name", nullable = false, length = 25)
    private String stateName;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}