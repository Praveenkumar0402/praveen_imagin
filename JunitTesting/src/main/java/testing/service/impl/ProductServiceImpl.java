package testing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import testing.entity.Product;
import testing.repository.ProductRepository;
import testing.service.ProductService;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    boolean flag;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> fetchAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product fetchProduct(int id) {
        var pid = productRepository.findById(id);
        if (pid.isPresent()) {
            var productid = pid.get();
            return productid;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id is not present");
        }
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        return productRepository.save(existingProduct);
    }

    @Override
    public String deleteProduct(int id) {
        productRepository.deleteById(id);
        return "Deleted Successfully!";
    }

}
