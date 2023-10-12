package reservation.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private int Id;
    @NotBlank
    private String From;
    @NotBlank
    private String To;
    @NotBlank
    private Date Bookingdate;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private StateOfTravel Stateoftravel;
    @NotBlank
    private String Bookingstatus;
    @NotBlank
    private int Userid;

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
