package mksgroup.goodway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.entity.Product;
import mksgroup.goodway.repository.ProductRepository;

@Controller
public class ProductController {
    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private ProductRepository productRepository;

    /**
     * Go to product's index page.
     * @return
     */
    @GetMapping({"/product", "/product/search"})
    public String goProductSearch() {

        return "product/search";
    }
    
    /**
     * Go to product's add new product page.
     * @return
     */
    @GetMapping("/product/new")
    public String goProductNew() {

        return "product/new";
    }
    
    /**
     * 
     * Load product list.
     * @return
     */
    @GetMapping("/product/load-product")
    @ResponseBody()
    public Iterable<Product> loadProducts(){
        
        return productRepository.findAll();
    }

}
