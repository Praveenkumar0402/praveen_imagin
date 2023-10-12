package reservation.entity;

import jakarta.persistence.*;
import lombok.*;
import reservation.enums.StateOfTravel;

import java.util.Date;

@Entity
@Table(name = "booking")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingid")
    @SequenceGenerator(name = "bookingid", sequenceName = "bookingid", initialValue = 200, allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "from_adr")
    private String From;

    @Column(name = "to_adr")
    private String To;

    @Column(name = "booking_date")
    private Date bookingdate;

    @Column(name = "booking_status")
    private String Bookingstatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_of_travel")
    private StateOfTravel Stateoftravel;

    @Column(name = "user_id")
    private int userid;

}
