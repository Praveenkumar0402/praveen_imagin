package testing;

import testing.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Product, Integer> {
}
