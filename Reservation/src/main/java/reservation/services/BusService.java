package reservation.services;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import reservation.AuthenticationContext.AuthUtils;
import reservation.dto.BusDto;
import reservation.entity.Bus;

import reservation.entity.User;
import reservation.exceptions.UserNotFoundException;
import reservation.repository.BookingRepository;
import reservation.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reservation.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusService {

//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    public void sendEmailAttachment(String toEmail,
//                                    String subject,
//                                    String body, String attachment) throws MessagingException {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setTo(toEmail);
//        helper.setText(body);
//        helper.setSubject(subject);
//
//        FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
//        helper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
//        javaMailSender.send(message);
//    }

    @Autowired
    BusRepository busRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthUtils authUtils;
    @Autowired
    BookingRepository bookingRepository;

    public List<BusDto> findAll() throws UserNotFoundException {
        List<Bus> bus = busRepository.findallbuses();
        if (bus.isEmpty()) {
            throw new UserNotFoundException("No buses found");
        } else {
            List<BusDto> busDto = new ArrayList<>();
            for (Bus buses : bus) {
                BusDto busDto1 = new BusDto(buses);
                busDto.add(busDto1);
            }
            return busDto;
        }
    }


    public BusDto updateBus(int id, BusDto busDto) throws UserNotFoundException {
        User user = authUtils.getUser();
        Bus bus = busRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Bus id not FOUND"));
        if (user.getId() == bus.getUserid()) {
            bus.setTotalseats(busDto.getTotalseats());
            bus.setBusnumber(busDto.getBusNumber());
            bus.setBookingseatno(busDto.getBookingSeatNo());
            bus.setSeatavailability(busDto.getSeatAvailability());
            bus.setBookingid(busDto.getBookingid());
            busRepository.save(bus);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
        return new BusDto(bus);
    }


    public BusDto deleteBus(int id) throws UserNotFoundException {
        User user=authUtils.getUser();
        Bus bus = busRepository.findById(id).orElseThrow(()->new UserNotFoundException("Bus id not found"));
        if (user.getId()==bus.getUserid()) {
            busRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
        return new BusDto(bus);
    }

    public BusDto createBus(BusDto busDto) {
        if (busDto.getTotalseats() < busDto.getBookingSeatNo()) {
            throw new RuntimeException("Invalid Seat Number");
        }
        int count = busRepository.seatsavailability(busDto.getTotalseats(), busDto.getBusNumber());
        Bus bus = new Bus();
        bus.setTotalseats(busDto.getTotalseats());
        bus.setBusnumber(busDto.getBusNumber());
        bus.setBookingseatno(busDto.getBookingSeatNo());
        bus.setSeatavailability((busDto.getTotalseats() - count) - 1);
        bus.setBookingid(busDto.getBookingid());
        bus.setUserid(busDto.getUserid());
        busRepository.save(bus);
        return new BusDto(bus);

    }

    public void generateExcel(HttpServletResponse response) throws IOException {
        List<Bus> buses = busRepository.findAll();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("bus details");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("user_id");
        row.createCell(2).setCellValue("bus_number");
        row.createCell(3).setCellValue("booking_seatno");
        row.createCell(4).setCellValue("seats_availability");
        row.createCell(5).setCellValue("total_seats");
        row.createCell(6).setCellValue("booking_id");
        int rowindex = 1;
        for (Bus bus : buses) {
            HSSFRow row1 = sheet.createRow(rowindex);
            row1.createCell(0).setCellValue(bus.getId());
            row1.createCell(1).setCellValue(bus.getUserid());
            row1.createCell(2).setCellValue(bus.getBusnumber());
            row1.createCell(3).setCellValue(bus.getBookingseatno());
            row1.createCell(4).setCellValue(bus.getSeatavailability());
            row1.createCell(5).setCellValue(bus.getTotalseats());
            row1.createCell(6).setCellValue(bus.getBookingid());
            rowindex++;
        }
        ServletOutputStream servletOutputStream = response.getOutputStream();
        hssfWorkbook.write(servletOutputStream);

    }

    public List<BusDto> findBybus() throws UserNotFoundException {
        User user = authUtils.getUser();
        List<Bus> busList = busRepository.findByUserid(user.getId());
        List<BusDto> busDtos = new ArrayList<>();
        for (Bus bus : busList) {
            BusDto busDto = new BusDto(bus);
            busDtos.add(busDto);
        }
        return busDtos;
    }

}
