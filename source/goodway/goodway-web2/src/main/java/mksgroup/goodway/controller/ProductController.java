package mksgroup.goodway.controller;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.biz.ProductBiz;
import mksgroup.goodway.entity.Product;
import mksgroup.goodway.model.ProductModel;
import mksgroup.goodway.util.AppUtil;

@Controller
public class ProductController {
    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private ProductBiz productBiz;

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
        
    	Iterable<Product> listProducts = productBiz.getRepo().findAll();
        return listProducts;
    }

    @PostMapping("/product/save")
    @ResponseBody
    public Iterable<Product> saveProducts(@Valid @RequestBody ProductModel data, Errors errors, HttpServletRequest request) {
        LOG.info("saveProducts....");
        
        // If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            LOG.error(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));

            return null;
        } else {
            Iterable<Product> entities = AppUtil.parseProduct(data);
            productBiz.updateProducts(entities, data.getDeletedIds());
        }
        
        // Reload data from db
        Iterable<Product> orders = productBiz.getRepo().findAll();
        
        return orders;
    }
}
