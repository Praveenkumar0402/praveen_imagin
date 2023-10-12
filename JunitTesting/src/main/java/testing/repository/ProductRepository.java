package testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import testing.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
