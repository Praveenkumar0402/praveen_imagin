package reservation.services;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import reservation.AuthenticationContext.AuthUtils;
import reservation.dto.BookingDto;
import reservation.entity.Booking;
import reservation.entity.User;
import reservation.enums.StateOfTravel;
import reservation.exceptions.NoBookingFound;
import reservation.exceptions.NoUsersFoundException;
import reservation.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reservation.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthUtils authUtils;

    public List<BookingDto> findAll() {
        List<Booking> bookings = bookingRepository.findAllBookings();
        if (bookings.isEmpty()) {
            throw new NoUsersFoundException("Sorry no bookings found");
        } else {
            List<BookingDto> bookingDtos = new ArrayList<>();
            for (Booking booking : bookings) {
                BookingDto bookingDto = new BookingDto(booking);
                bookingDtos.add(bookingDto);
            }
            return bookingDtos;
        }
    }

    public BookingDto updateBooking(int id, BookingDto bookingDto) {
        User user = authUtils.getUser();
        Booking booking = bookingRepository.findById(id).get();
        if (user.getId() == booking.getUserid()) {
            booking.setFrom(bookingDto.getFrom());
            booking.setTo(bookingDto.getTo());
            booking.setBookingdate(bookingDto.getBookingdate());
            booking.setBookingstatus(bookingDto.getBookingstatus());
            booking.setStateoftravel(bookingDto.getStateoftravel());
            bookingRepository.save(booking);
        } else {
            throw new NoBookingFound("User Mismatch");
        }
        return new BookingDto(booking);

    }

    public BookingDto deleteBooking(int id) {
        User user = authUtils.getUser();
        Booking booking = bookingRepository.findById(id).get();
        if (user.getId() == booking.getUserid()) {
            bookingRepository.deleteById(id);
        } else {
            throw new NoUsersFoundException("User Mismatch");
        }
        return new BookingDto(booking);
    }

    public BookingDto createBooking(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setFrom(bookingDto.getFrom());
        booking.setTo(bookingDto.getTo());
        booking.setBookingdate(bookingDto.getBookingdate());
        String way=String.valueOf(bookingDto.getStateoftravel());
        booking.setStateoftravel(check(way));
        booking.setBookingstatus(bookingDto.getBookingstatus());
        booking.setUserid(bookingDto.getUserid());
        bookingRepository.save(booking);
        return new BookingDto(booking);
    }

    public void generateExcel(HttpServletResponse response) throws IOException {
        List<Booking> bookings = bookingRepository.findAll();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("Booking details");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("from_adr");
        row.createCell(2).setCellValue("to_adr");
        row.createCell(3).setCellValue("booking_date");
        row.createCell(4).setCellValue("booking_status");
        row.createCell(5).setCellValue("state_of_travel");
        row.createCell(6).setCellValue("user_id");
        int rowindex = 1;
        for (Booking booking : bookings) {
            HSSFRow row1 = sheet.createRow(rowindex);
            row1.createCell(0).setCellValue(booking.getId());
            row1.createCell(1).setCellValue(booking.getFrom());
            row1.createCell(2).setCellValue(booking.getTo());
            row1.createCell(3).setCellValue(booking.getBookingdate());
            row1.createCell(4).setCellValue(booking.getBookingstatus());
            row1.createCell(5).setCellValue(booking.getStateoftravel().ordinal());
            row1.createCell(6).setCellValue(booking.getUserid());
            rowindex++;
        }
        ServletOutputStream servletOutputStream = response.getOutputStream();
        hssfWorkbook.write(servletOutputStream);
    }

    //Id based retrieved the data
    public List<BookingDto> findbyemail() {
        User user = authUtils.getUser();
        List<Booking> bookings = bookingRepository.findByUserid(user.getId());
        List<BookingDto> bookingDtos = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingDto bookingDto = new BookingDto(booking);
            bookingDtos.add(bookingDto);
        }
        return bookingDtos;
    }


    StateOfTravel check(String way){
        switch (way){
            case "BUS" :return StateOfTravel.BUS;
            case "FLIGHT" :return StateOfTravel.FLIGHT;
            case "TRAIN" :return StateOfTravel.TRAIN;
            default: throw new RuntimeException("Check the state of travel");
        }
    }

}
