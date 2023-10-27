package reservation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.client.RestTemplate;
import reservation.AuthenticationContext.AuthUtils;
import reservation.dto.TrainDto;
import reservation.entity.Train;
import reservation.entity.User;
import reservation.enums.Gender;
import reservation.enums.UserStatus;
import reservation.exceptions.UserNotFoundException;
import reservation.repository.TrainRepository;
import reservation.repository.UserRepository;
import reservation.services.TrainService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Disabled
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservationApplicationTests {

    //    @LocalServerPort
//    int port;
//
//    private static String baseUrl = "http://localhost";
//
//    @Autowired
//    private static RestTemplate restTemplate;
//
//    @BeforeAll
//    public static void init() {
//        restTemplate = new RestTemplate();
//    }
//
//    @BeforeEach
//    public void createurl() {
//        baseUrl = baseUrl.concat(":").concat(String.valueOf(port)).concat("/train");
//    }
//
//    @Autowired
//    TestRepository testRepository;
//
//    @Test
//    public void addTrain() {
//        TrainDto train = new TrainDto(706, 12760, 41, 46, 50, 205, 101);
//        TrainDto response = restTemplate.postForEntity(baseUrl + "/create", train, TrainDto.class).getBody();
//        if (response != null) {
//            assertEquals(710, response.getId());
//        }
//        assertEquals(10, testRepository.findAll().size());
//    }
//
//    @Test
//    public void getAllTrains() {
//        List<TrainDto> response = restTemplate.getForObject(baseUrl + "/all", List.class);
//        assertEquals(10, response.size());
//        assertEquals(10, testRepository.findAll().size());
//    }
//
//
//    @Test
//    public void deleteTrainById() {
//        List<TrainDto> response = restTemplate.getForObject(baseUrl + "/all", List.class);
//        assertEquals(10, testRepository.findAll().size());
//        restTemplate.delete(baseUrl + "/delete/{id}", 709);
//        assertEquals(9, testRepository.findAll().size());
//    }
    @InjectMocks
    TrainService trainService;
    @InjectMocks
    AuthUtils authUtils;
    @Mock
    UserRepository userRepository;

    @Mock
    TrainRepository trainRepository;
    @MockBean
    AuthenticationManager authenticationManager;
    private Train trainDto;
    private User user;

    @BeforeEach
    void setup() {
        trainDto = new Train(706, 101, 12760, 46, 50, 50, 205);
        user=new User(101,"Praveen","Kumar",Gender.MALE,"praveen@gmail.com","praveen","9573002329" ,UserStatus.ACTIVE,"Admin");
    }


    @Test
    public void save() {
       when(trainRepository.save(trainDto)).thenReturn(trainDto);
       TrainDto trainDto1=new TrainDto(trainDto);
        TrainDto person1 = trainService.createTrain(trainDto1);
        assertAll(
                () -> assertEquals(12760, person1.getTrainNumber()),
                () -> assertNotNull(person1)
        );
    }

    @Test
    public void delete() throws UserNotFoundException {
        when(authUtils.getUser()).thenReturn(user);
        //when( trainRepository.).thenReturn(trainDto);
        TrainDto trainDto1=new TrainDto(trainDto);
        when(trainService.deleteTrain(706)).thenReturn(trainDto1);
       assertEquals(101,user.getId());
        Mockito.verify(trainRepository, times(1)).deleteById(706);
    }
}
