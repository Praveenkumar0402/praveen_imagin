package reservation.services;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import reservation.AuthenticationContext.AuthUtils;
import reservation.dto.TrainDto;
import reservation.entity.*;
import reservation.exceptions.UserNotFoundException;
import reservation.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainService {

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    AuthUtils authUtils;

    public List<TrainDto> getAllTrain() throws UserNotFoundException {
        List<Train> train = trainRepository.findAllTrains();
        if (train.isEmpty()) {
            throw new UserNotFoundException("No trains found");
        } else {
            List<TrainDto> trainDto = new ArrayList<>();
            for (Train train1 : train) {
                TrainDto trainDto1 = new TrainDto(train1);
                trainDto.add(trainDto1);
            }
            return trainDto;
        }
    }

    public TrainDto updateTrain(int id, TrainDto trainDto) throws UserNotFoundException {
        User user = authUtils.getUser();
        Train train = trainRepository.findById(id).orElseThrow(()->new UserNotFoundException("Id is not present for Train Update"));;
        if (user.getId() == train.getUserid()) {
            train.setTotalseats(trainDto.getTotalseats());
            train.setTrainnumber(trainDto.getTrainNumber());
            train.setBookingseatno(trainDto.getBookingSeatNo());
            train.setSeatavailability(trainDto.getSeatAvailability());
            train.setBookingid(trainDto.getBookingid());
            train.setUserid(trainDto.getUserid());
            trainRepository.save(train);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
        return new TrainDto(train);
    }

    public TrainDto deleteTrain(int id) throws UserNotFoundException {
        User user = authUtils.getUser();
        Train train = trainRepository.findById(id).orElseThrow(()->new UserNotFoundException("Id is not present for Train delete"));;
        if (user.getId() == train.getUserid()) {
            trainRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
        return new TrainDto(train);
    }

    public TrainDto createTrain(TrainDto trainDto) {
        if (trainDto.getTotalseats() < trainDto.getBookingSeatNo()) {
            throw new RuntimeException("Invalid Seat Number");
        }
        int count = trainRepository.seatsavailability(trainDto.getTotalseats(), trainDto.getTrainNumber());
        Train train = new Train();
        train.setTrainnumber(trainDto.getTrainNumber());
        train.setBookingseatno(trainDto.getBookingSeatNo());
        train.setSeatavailability((trainDto.getTotalseats() - count) - 1);
        train.setTotalseats(trainDto.getTotalseats());
        train.setBookingid(trainDto.getBookingid());
        train.setUserid(trainDto.getUserid());
        trainRepository.save(train);
        return new TrainDto(train);
    }

    public void generateExcel(HttpServletResponse response) throws IOException {
        List<Train> trains = trainRepository.findAll();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("Train details");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("user_id");
        row.createCell(2).setCellValue("train_number");
        row.createCell(3).setCellValue("booking_seatno");
        row.createCell(4).setCellValue("seats_availability");
        row.createCell(5).setCellValue("total_seats");
        row.createCell(6).setCellValue("booking_id");
        int rowindex = 1;
        for (Train train : trains) {
            HSSFRow row1 = sheet.createRow(rowindex);
            row1.createCell(0).setCellValue(train.getId());
            row1.createCell(1).setCellValue(train.getUserid());
            row1.createCell(2).setCellValue(train.getTrainnumber());
            row1.createCell(3).setCellValue(train.getBookingseatno());
            row1.createCell(4).setCellValue(train.getSeatavailability());
            row1.createCell(5).setCellValue(train.getTotalseats());
            row1.createCell(6).setCellValue(train.getBookingid());
            rowindex++;
        }
        ServletOutputStream servletOutputStream = response.getOutputStream();
        hssfWorkbook.write(servletOutputStream);
    }


    public List<TrainDto> findBytrain() throws UserNotFoundException {
        User user = authUtils.getUser();
        List<Train> trains = trainRepository.findByUserid(user.getId());
        List<TrainDto> trainDtos = new ArrayList<>();
        for (Train train : trains) {
            TrainDto trainDto = new TrainDto(train);
            trainDtos.add(trainDto);
        }
        return trainDtos;
    }
}

//        Booking booking1 = booking.stream().filter(t -> t.getUserid() == user.getId()).toList().get(0);
