package reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reservation.entity.Train;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainDto {

    @NotNull
    private int Id;
    @NotBlank
    private int TrainNumber;
    @NotBlank
    private int BookingSeatNo;
    @NotBlank
    private int SeatAvailability;
    @NotBlank
    private int Totalseats = 50;
    @NotNull
    private int Bookingid;
    @NotNull
    private int Userid;


    public TrainDto(Train train) {
        Id = train.getId();
        TrainNumber = train.getTrainnumber();
        BookingSeatNo = train.getBookingseatno();
        SeatAvailability = train.getSeatavailability();
        Totalseats = train.getTotalseats();
        Bookingid = train.getBookingid();
        Userid = train.getUserid();
    }

}
