package reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import reservation.entity.Bus;

@Data
@NoArgsConstructor
public class BusDto {

    @NotNull
    private int Id;
    @NotBlank
    private int BusNumber;
    @NotBlank
    private int BookingSeatNo;
    @NotBlank
    private int SeatAvailability;
    @NotBlank
    private int Totalseats = 30;
    @NotNull
    private int bookingid;
    @NotNull
    private int Userid;


    public BusDto(Bus bus) {
        Id = bus.getId();
        BusNumber = bus.getBusnumber();
        BookingSeatNo = bus.getBookingseatno();
        SeatAvailability = bus.getSeatavailability();
        Totalseats = bus.getTotalseats();
        bookingid = bus.getBookingid();
        Userid = bus.getUserid();
    }
}
