package biz_connect_info.models;

//////////////////////////////////////////////////////////////////
// CITY
//////////////////////////////////////////////////////////////////

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "mst_city")
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city_name", nullable = false, length = 25)
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;
}