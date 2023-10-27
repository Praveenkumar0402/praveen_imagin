package reservation.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import reservation.dto.BookingDto;
import reservation.exceptions.UserNotFoundException;
import reservation.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingservice;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<BookingDto>> getAll() throws UserNotFoundException {
        List<BookingDto> bookingDto = bookingservice.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(bookingDto);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<BookingDto> update(@PathVariable("id") int id, @RequestBody @Valid BookingDto bookingDto) throws UserNotFoundException {
        BookingDto bookingDto1 = bookingservice.updateBooking(id, bookingDto);
        return ResponseEntity.status(HttpStatus.OK).body(bookingDto1);
    }

    @PostMapping("/create")
    public ResponseEntity<BookingDto> create(@RequestBody @Valid BookingDto bookingDto) {
        BookingDto bookingDto1 = bookingservice.createBooking(bookingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDto1);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<BookingDto> remove(@PathVariable("id") int id) throws UserNotFoundException {
        BookingDto bookingDto = bookingservice.deleteBooking(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookingDto);
    }

    @GetMapping("/generate")
    public void generateExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        String headerKey = "Content-Deposition";
        String headerValue = "attachment;filename=booking.xlsx";
        response.setHeader(headerKey, headerValue);
        bookingservice.generateExcel(response);
    }

    @GetMapping("/ids")
    public ResponseEntity<List<BookingDto>> idsdata() throws UserNotFoundException {
        List<BookingDto> bookingDto=bookingservice.findbyemail();
        return ResponseEntity.status(HttpStatus.OK).body(bookingDto);
    }
}