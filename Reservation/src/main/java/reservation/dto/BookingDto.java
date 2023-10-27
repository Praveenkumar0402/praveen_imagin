package reservation.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reservation.entity.Booking;
import reservation.enums.StateOfTravel;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    @NotNull
    private Integer Id;
    @NotBlank(message = "Enter the From Address")
    private String From;
    @NotBlank(message = "Enter the To Address")
    private String To;
    @NotNull(message = "Enter the Booking date")
    private Date Bookingdate;

    @Enumerated(EnumType.STRING)
    private StateOfTravel Stateoftravel;

    private String Bookingstatus;
    @NotNull(message = "Enter the User id")
    private Integer Userid;

    public BookingDto(Booking bookingo) {
        Id=bookingo.getId();
        From = bookingo.getFrom();
        To = bookingo.getTo();
        Bookingdate = bookingo.getBookingdate();
        Stateoftravel = bookingo.getStateoftravel();
        Bookingstatus = bookingo.getBookingstatus();
        Userid = bookingo.getUserid();
    }

}
