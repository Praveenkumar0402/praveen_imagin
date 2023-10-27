package reservation.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.security.crypto.password.PasswordEncoder;
import reservation.AuthenticationContext.AuthUtils;
import reservation.entity.User;
import reservation.enums.Gender;
import reservation.enums.UserStatus;

import reservation.exceptions.UserNotFoundException;
import reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthUtils authUtils;

    public List<User> findAll() throws UserNotFoundException {
        List<User> user = userRepository.findallusers();
        if (user.isEmpty()) {
            throw new UserNotFoundException("No Users found");
        } else {
            List<User> user1 = new ArrayList<>();
            for (User user2 : user) {
                User user3 = new User(user2);
                user1.add(user3);
            }
            return user1;
        }
    }

    public User updateUser(int id, User user1) throws UserNotFoundException {
        User user = authUtils.getUser();
        User user12 = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Id is not Present for updating"));
        if (user.getId() == user12.getId()) {
            user.setFirstname(user1.getFirstname());
            user.setLastname(user1.getLastname());
            user.setGender(user1.getGender());
            user.setEmail(user1.getEmail());
            user.setMobile(user1.getMobile());
            userRepository.save(user);
            return new User(user);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
    }

    public User deleteUser(int id) throws UserNotFoundException {
        User user = authUtils.getUser();
        User user1 = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Id is not Present for deleting"));
        if (user.getId() == user1.getId()) {
            userRepository.deleteById(id);
            return new User(user);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
    }

    public User create(User user) {
        user.setFirstname(user.getFirstname());
        user.setLastname(user.getLastname());
        String gender = String.valueOf((user.getGender()));
        user.setGender(check1(gender));
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setMobile(user.getMobile());
        String status = String.valueOf((user.getUserstatus()));
        user.setUserstatus(check(status));
        user.setUserstatus(user.getUserstatus());
        user.setRoles(user.getRoles());
        return userRepository.save(user);
    }

    public void generateExcel(HttpServletResponse response) throws IOException {
        List<User> users = userRepository.findAll();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("User details");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("first_name");
        row.createCell(2).setCellValue("last_name");
        row.createCell(3).setCellValue("gender");
        row.createCell(4).setCellValue("email");
        row.createCell(5).setCellValue("password");
        row.createCell(6).setCellValue("mobile");
        row.createCell(7).setCellValue("userstatus");
        row.createCell(8).setCellValue("roles");
        int rowindex = 1;
        for (User user : users) {
            HSSFRow row1 = sheet.createRow(rowindex);
            row1.createCell(0).setCellValue(user.getId());
            row1.createCell(1).setCellValue(user.getFirstname());
            row1.createCell(2).setCellValue(user.getLastname());
            row1.createCell(3).setCellValue(user.getGender().ordinal());
            row1.createCell(4).setCellValue(user.getEmail());
            row1.createCell(5).setCellValue(user.getPassword());
            row1.createCell(6).setCellValue(user.getMobile());
            row1.createCell(7).setCellValue(user.getUserstatus().ordinal());
            row1.createCell(8).setCellValue(user.getRoles());
            rowindex++;
        }
        ServletOutputStream servletOutputStream = response.getOutputStream();
        hssfWorkbook.write(servletOutputStream);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        //load user from database
//        Optional<User> user = userRepository.findByEmail(username);
//        return user.map(UserInfo::new).orElseThrow(() -> new UsernameNotFoundException("UserName Not Found " + username));
//    }

    UserStatus check(String status) {
        switch (status) {
            case "ACTIVE":
                return UserStatus.ACTIVE;
            case "INACTIVE":
                return UserStatus.INACTIVE;
            default:
                throw new RuntimeException("Status is not valid");
        }
    }

    Gender check1(String gender) {
        switch (gender) {
            case "MALE":
                return Gender.MALE;
            case "FEMALE":
                return Gender.FEMALE;
            case "OTHERS":
                return Gender.OTHERS;
            default:
                throw new RuntimeException("Check the Gender");
        }
    }
}
