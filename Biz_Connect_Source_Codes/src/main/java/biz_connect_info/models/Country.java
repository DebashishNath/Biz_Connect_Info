package biz_connect_info.models;

import jakarta.persistence.*;
import lombok.Data;

//////////////////////////////////////////////////////////////////
// COUNTRY
//////////////////////////////////////////////////////////////////

@Entity
@Table(name = "mst_country")
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country_name", nullable = false, length = 25)
    private String countryName;
}