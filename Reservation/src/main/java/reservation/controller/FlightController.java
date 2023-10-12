package reservation.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import reservation.dto.BusDto;
import reservation.dto.FlightDto;
import reservation.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    FlightService flightservice;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<FlightDto>> getAll() {
        List<FlightDto> flightDto = flightservice.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(flightDto);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<FlightDto> update(@PathVariable("id") int id, @RequestBody FlightDto flightdto) {
        FlightDto flightDto = flightservice.updateFlight(id, flightdto);
        return ResponseEntity.status(HttpStatus.OK).body(flightDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<FlightDto> delete(@PathVariable("id") int id) {
        FlightDto flightDto = flightservice.deleteFlight(id);
        return ResponseEntity.status(HttpStatus.OK).body(flightDto);
    }

    @PostMapping("/create")
    public ResponseEntity<FlightDto> create(@RequestBody FlightDto flightDto) {
        FlightDto flightDto1 = flightservice.createFlight(flightDto);
        return ResponseEntity.status(HttpStatus.OK).body(flightDto1);
    }

    @GetMapping("/generate")
    public void generateExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        String headerKey = "Content-Deposition";
        String headerValue = "attachment;filename=flight.xlsx";
        response.setHeader(headerKey, headerValue);
        flightservice.generateExcel(response);
    }

    @GetMapping("/flightid")
    public ResponseEntity<List<FlightDto>> flightids(){
        List<FlightDto> flightDtos=flightservice.findByflight();
        return ResponseEntity.status(HttpStatus.OK).body(flightDtos);
    }
}
