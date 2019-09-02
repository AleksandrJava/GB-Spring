package productsSystem.repositories;


import org.springframework.stereotype.Repository;
import productsSystem.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productList;

    @PostConstruct
    public void init() {
        productList = new ArrayList<Product>();
        productList.add(new Product(1L, "clock", 2500));
        productList.add(new Product(2L, "puzzle", 1800));
        productList.add(new Product(3L, "laptop", 50000));
    }

    public List<Product> findAll(){
        return productList;
    }

    public Product findOne(Long id){
        for (Product o : productList) {
            if(o.getId() == id){
                return o;
            }
        }
        return null;
    }

    public Product addOne(Product product){
        productList.add(product);
        return product;
    }

}
