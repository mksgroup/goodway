package mksgroup.goodway.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.biz.AddressBiz;
import mksgroup.goodway.biz.StockBiz;
import mksgroup.goodway.entity.Address;
import mksgroup.goodway.entity.Stock;
import mksgroup.goodway.model.StockModel;
import mksgroup.goodway.repository.AddressRepository;
import mksgroup.goodway.util.AppUtil;

@Controller
public class StockController extends BaseController {
    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private AddressBiz addressBiz;

    @Autowired
    private StockBiz stockBiz;

    /**
     * Go to product's index page.
     * @return
     */
    @GetMapping({"/stock", "/stock/new"})
    public String goProductSearch(ModelMap model) {
        model.addAttribute("map_key", mapKey);
        return "stock/new";
    }

    /**
     * Load stocks.
     * @return json of stocks lists.
     */
    @GetMapping("/stock/load-stock")
    @ResponseBody
    public Iterable<Stock> loadStock() {
        Iterable<Stock> listStock = stockBiz.getRepo().findAll();

        return listStock;
    }

    @PostMapping("/stock/save")
    @ResponseBody
    public Iterable<Stock> saveCustomers(@Valid @RequestBody StockModel data, Errors errors, HttpServletRequest request) {
        LOG.info("saveStocks....");
        
        // If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            LOG.error(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));

            return null;
        } else {
            Iterable<Stock> entities = AppUtil.parseStock(data);
            
            // Update default Address for Customer
            Address defaultAddr = addressBiz.getRepo().findAll().iterator().next();
            
            List<Stock> entityList = new ArrayList<Stock>();

            entities.forEach(stock -> {
                if (stock.getName() != null && !stock.getName().isEmpty()) {
                    if (stock.getAddress() == null) {
                        stock.setAddress(defaultAddr);
                    } else {
                        // Load address from db
                        Address addr = ((AddressRepository) addressBiz.getRepo()).findAll().iterator().next();
                        
                        if (addr == null ) { addr = defaultAddr; }
                        stock.setAddress(addr);
                    }
                    entityList.add(stock);
                }
            });
            
            stockBiz.updateStock(entities, data.getDeletedIds());
            LOG.info("customerModel=" + data + ";request=" + request);
        }
        
        // Reload data from db
        return stockBiz.getRepo().findAll();
    }

    @Override
    String getFilename() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    String getTemplate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Iterable<?> getDownloadData() {
        // TODO Auto-generated method stub
        return null;
    }
}
