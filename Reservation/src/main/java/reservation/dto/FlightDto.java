package reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import reservation.entity.Flight;

@Data
@NoArgsConstructor
public class FlightDto {

    @NotNull
    private int Id;
    @NotBlank
    private int FlightNumber;
    @NotBlank
    private int BookingSeatNo;
    @NotBlank
    private int SeatAvailability;
    @NotBlank
    private int Totalseats=40;
    @NotNull
    private int Bookingid;
    @NotNull
    private int Userid;


    public FlightDto(Flight flight) {
        Id = flight.getId();
        FlightNumber = flight.getFlightnumber();
        BookingSeatNo = flight.getBookingseatno();
        SeatAvailability = flight.getSeatavailability();
        Totalseats = flight.getTotalseats();
        Bookingid = flight.getBookingid();
        Userid = flight.getUserid();
    }

}
