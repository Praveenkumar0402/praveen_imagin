package reservation.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import reservation.dto.BookingDto;
import reservation.dto.BusDto;
import reservation.entity.Bus;
import reservation.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    BusService busservice;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<BusDto> getAll() {
        return busservice.findAll();
    }


    @PutMapping("/update/{id}")
    public BusDto updates(@PathVariable("id") int id, @RequestBody BusDto busdto) {
        return busservice.updateBus(id,busdto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BusDto> remove(@PathVariable("id") int id) {
        BusDto busDto = busservice.deleteBus(id);
        return ResponseEntity.status(HttpStatus.OK).body(busDto);
    }

    @PostMapping("/create")
    public ResponseEntity<BusDto> create(@RequestBody BusDto busDto) {
        BusDto busDto1 = busservice.createBus(busDto);
        return ResponseEntity.status(HttpStatus.OK).body(busDto1);
    }


    @GetMapping("/generate")
    public void generateExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        String headerKey = "Content-Deposition";
        String headerValue = "attachment;filename=bus.xlsx";
        response.setHeader(headerKey, headerValue);
        busservice.generateExcel(response);
    }


//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerMail() throws MessagingException {
//        busservice.sendEmailAttachment("",
//                "This is email subject ",
//                "This is email body","/home/downloads/generate.xls");
//    }

    @GetMapping("/busid")
    public ResponseEntity<List<BusDto>> busids(){
        List<BusDto> busDtos=busservice.findBybus();
        return ResponseEntity.status(HttpStatus.OK).body(busDtos);
    }
}
