package ru.ea_dm.services;

import org.springframework.stereotype.Service;
import ru.ea_dm.models.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceService {
    private final List<Product> products = new ArrayList<>();
    private long ID = 0;

    {
        products.add(new Product(++ID, "Haircut", "Some haircut", 500));
        products.add(new Product(++ID, "Random Stuff", "Some stuff", 700));
    }

    public List<Product> index() {
        return products;
    }

    public void save(Product product) {
        product.setId(++ID);
        products.add(product);
    }

    public Product show(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public void delete(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
