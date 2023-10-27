package reservation.services;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import reservation.AuthenticationContext.AuthUtils;
import reservation.dto.FlightDto;
import reservation.entity.Flight;
import reservation.entity.User;
import reservation.exceptions.UserNotFoundException;
import reservation.repository.BookingRepository;
import reservation.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    AuthUtils authUtils;

    public List<FlightDto> findAll() throws UserNotFoundException {
        List<Flight> flights = flightRepository.findallflights();
        if (flights.isEmpty()) {
            throw new UserNotFoundException("No flights found");
        } else {
            List<FlightDto> flightDto = new ArrayList<>();
            for (Flight flight : flights) {
                FlightDto flightDto1 = new FlightDto(flight);
                flightDto.add(flightDto1);
            }
            return flightDto;
        }
    }

    public FlightDto updateFlight(int id, FlightDto flightDto) throws UserNotFoundException {
        User user = authUtils.getUser();
        Flight flight = flightRepository.findById(id).orElseThrow(()->new UserNotFoundException("Id is not present for Flight Update"));
        if (user.getId() == flightDto.getUserid()) {
            flight.setTotalseats(flightDto.getTotalseats());
            flight.setFlightnumber(flightDto.getFlightNumber());
            flight.setBookingseatno(flightDto.getBookingSeatNo());
            flight.setSeatavailability(flightDto.getSeatAvailability());
            flight.setBookingid(flightDto.getBookingid());
            flightRepository.save(flight);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
        return new FlightDto(flight);
    }

    public FlightDto deleteFlight(int id) throws UserNotFoundException {
        User user = authUtils.getUser();
        Flight flight = flightRepository.findById(id).orElseThrow(()->new UserNotFoundException("Id is not present for Flight delete"));;
        if (user.getId() == flight.getUserid()) {
            flightRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
        return new FlightDto(flight);
    }

    public FlightDto createFlight(FlightDto flightDto) {
        if (flightDto.getTotalseats() < flightDto.getBookingSeatNo()) {
            throw new RuntimeException("Invalid Seat Number");
        }
        int count = flightRepository.seatsavailability(flightDto.getTotalseats(), flightDto.getFlightNumber());

        Flight flight = new Flight();
        flight.setTotalseats(flightDto.getTotalseats());
        flight.setFlightnumber(flightDto.getFlightNumber());
        flight.setBookingseatno(flightDto.getBookingSeatNo());
        flight.setSeatavailability((flightDto.getTotalseats() - count) - 1);
        flight.setBookingid(flightDto.getBookingid());
        flight.setUserid(flightDto.getUserid());
        flightRepository.save(flight);
        return new FlightDto(flight);
    }

    public void generateExcel(HttpServletResponse response) throws IOException {
        List<Flight> flights = flightRepository.findAll();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("Flight details");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("user_id");
        row.createCell(2).setCellValue("flight_number");
        row.createCell(3).setCellValue("booking_seatno");
        row.createCell(4).setCellValue("seats_availability");
        row.createCell(5).setCellValue("total_seats");
        row.createCell(6).setCellValue("booking_id");
        int rowindex = 1;
        for (Flight flight : flights) {
            HSSFRow row1 = sheet.createRow(rowindex);
            row1.createCell(0).setCellValue(flight.getId());
            row1.createCell(1).setCellValue(flight.getUserid());
            row1.createCell(2).setCellValue(flight.getFlightnumber());
            row1.createCell(3).setCellValue(flight.getBookingseatno());
            row1.createCell(4).setCellValue(flight.getSeatavailability());
            row1.createCell(5).setCellValue(flight.getTotalseats());
            row1.createCell(6).setCellValue(flight.getBookingid());
            rowindex++;
        }
        ServletOutputStream servletOutputStream = response.getOutputStream();
        hssfWorkbook.write(servletOutputStream);
    }


    public List<FlightDto> findByflight() throws UserNotFoundException {
        User user = authUtils.getUser();
        List<Flight> flights = flightRepository.findByUserid(user.getId());
        List<FlightDto> flightDtos = new ArrayList<>();
        for (Flight flight : flights) {
            FlightDto flightDto = new FlightDto(flight);
            flightDtos.add(flightDto);
        }
        return flightDtos;
    }
}

