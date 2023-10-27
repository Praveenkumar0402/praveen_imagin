package reservation.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import reservation.dto.TrainDto;
import reservation.exceptions.UserNotFoundException;
import reservation.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/train")
public class TrainController {

    @Autowired
    TrainService trainservice;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<List<TrainDto>> trainsAll() throws UserNotFoundException {
        List<TrainDto> trainDto = trainservice.getAllTrain();
        return ResponseEntity.status(HttpStatus.OK).body(trainDto);
    }


    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<TrainDto> trainUpdate(@PathVariable("id") int id, @RequestBody @Valid TrainDto trainDto) throws UserNotFoundException {
        TrainDto trainDto1 = trainservice.updateTrain(id, trainDto);
        return ResponseEntity.status(HttpStatus.OK).body(trainDto1);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<TrainDto> trainDelete(@PathVariable("id") int id) throws UserNotFoundException {
        TrainDto trainDto = trainservice.deleteTrain(id);
        return ResponseEntity.status(HttpStatus.OK).body(trainDto);
    }

    @PostMapping("/create")
    public ResponseEntity<TrainDto> trainCreate(@RequestBody @Valid TrainDto trainDto) {
        TrainDto trainDto1 = trainservice.createTrain(trainDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(trainDto1);
    }

    @GetMapping("/generate")
    public void generateExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        String headerKey = "Content-Deposition";
        String headerValue = "attachment;filename=train.xlsx";
        response.setHeader(headerKey, headerValue);
        trainservice.generateExcel(response);
    }

    @GetMapping("/trainid")
    public ResponseEntity<List<TrainDto>> trainids() throws UserNotFoundException {
        List<TrainDto> trainDtos = trainservice.findBytrain();
        return ResponseEntity.status(HttpStatus.OK).body(trainDtos);
    }

}
