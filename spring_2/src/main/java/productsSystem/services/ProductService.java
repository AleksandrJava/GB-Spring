package productsSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productsSystem.models.Product;
import productsSystem.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product getOne(Long id){
        return productRepository.findOne(id);
    }

    public Product setOne(Product product){
        if(product == null){
            return null;
        }
        return productRepository.addOne(product);
    }
}
