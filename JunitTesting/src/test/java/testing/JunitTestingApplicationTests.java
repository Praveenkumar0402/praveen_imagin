package testing;

import testing.entity.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JunitTestingApplicationTests {

    @LocalServerPort
    private int port;

    private static String baseUrl = "http://localhost";

    @Autowired
    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void createUri() {
        baseUrl = baseUrl.concat(":").concat(String.valueOf(port)).concat("/products");
    }

    @Autowired
    private TestRepository testRepository;

    @Test
    public void addProduct() {
        Product product = new Product(1, "Book", 200, 10);
        Product response = restTemplate.postForEntity(baseUrl + "/save", product, Product.class).getBody();
        assertEquals("Book", response.getName());
        assertEquals(2, testRepository.findAll().size());
    }

    @Test
    @Sql(statements = "insert into product(id, name, price, quantity) values (2, 'Pen', 20, 5)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "delete from product where name='Pen'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getAllProducts() {
        List<Product> response = restTemplate.getForObject(baseUrl + "/fetchAll", List.class);
        assertEquals(3, response.size());
        assertEquals(3, testRepository.findAll().size());
    }

    @Test
    @Sql(statements = "insert into product(id, name, price, quantity) values (2, 'Pencil', 10, 20)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "delete from product where name='Pencil'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getProductById() {
        Product response = restTemplate.getForObject(baseUrl + "/fetch/{id}", Product.class, 2);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(2, response.getId()),
                () -> assertEquals("Pencil", response.getName())
        );
    }

    @Test
    @Sql(statements = "insert into product(id, name, price, quantity) values (4, 'Bottle', 100, 5)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "delete from product where name='Bottle'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getUpdateProductById() {
        Product product = new Product(4, "Bottle", 100, 5);
        Product response = restTemplate.getForObject(baseUrl + "/fetch/{id}", Product.class, 4);
        restTemplate.put(baseUrl + "/update/{id}", product, 4);
        Product response1 = restTemplate.getForObject(baseUrl + "/fetch/{id}", Product.class, 4);
        assertAll(
                () -> assertNotNull(response1),
                () -> assertEquals(4, response.getId()),
                () -> assertEquals("Bottle", response.getName())
        );
    }

    @Test
//    @Sql(statements = "insert into product(id, name, price, quantity) values (5, 'Bag', 500, 9)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void deleteProductById() {
        List<Product> response = restTemplate.getForObject(baseUrl + "/fetchAll", List.class);
        assertEquals(3, testRepository.findAll().size());
        restTemplate.delete(baseUrl + "/delete/{id}", 5);
        assertEquals(2, testRepository.findAll().size());
    }
}
