package biz_connect_info.models;

import jakarta.persistence.*;
import lombok.Data;
import utils.MessageResponse;

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

    @Transient
    private MessageResponse returnMessage;

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}