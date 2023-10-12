package testing.service;

import java.util.List;

import testing.entity.Product;


public interface ProductService {
	public Product saveProduct(Product product);
	public List<Product> fetchAllProducts();
	public Product fetchProduct(int id);
	public Product updateProduct(int id, Product product);
	public String deleteProduct(int id);
}
