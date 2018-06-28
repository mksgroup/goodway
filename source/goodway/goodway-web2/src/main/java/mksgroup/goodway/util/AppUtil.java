/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mksgroup.goodway.entity.Address;
import mksgroup.goodway.entity.Customer;
import mksgroup.goodway.entity.OrderDetailProduct;
import mksgroup.goodway.entity.OrderMaster;
import mksgroup.goodway.entity.Product;
import mksgroup.goodway.entity.Question;
import mksgroup.goodway.entity.Vehicle;
import mksgroup.goodway.model.AddressModel;
import mksgroup.goodway.model.CustomerModel;
import mksgroup.goodway.model.OrderDetailProductModel;
import mksgroup.goodway.model.OrderModel;
import mksgroup.goodway.model.ProductModel;
import mksgroup.goodway.model.QuestionModel;
import mksgroup.goodway.model.VehicleModel;
import mksgroup.java.common.BeanUtil;

/**
 * This utility provides features:<br/>
 * - Parse matrix of Vehicle from handsontable
 * @author ThachLN
 *
 */
public class AppUtil {
    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(AppUtil.class);

    /** Flag to parse data from handsontable: avoid the empty row. */
    final static boolean SKIP_EMPTYROW = true;

    public static Iterable<Vehicle> parseVehicle(@Valid VehicleModel data) {
        final String[] HEADERS = {"id", "name", "length", "width", "height", "capacity"};
        List<Vehicle> listVehicle = (List<Vehicle>) BeanUtil.getDataList(data.getData(), HEADERS, Vehicle.class, SKIP_EMPTYROW, "createdbyUsername", "SYSTEM", "created");

        return listVehicle;
    }

    public static Iterable<Customer> parseCustomer(@Valid CustomerModel data) {
        final String[] HEADERS = {"id", "name", "cd", "shortName", "phone", "addr", "addrId"};

        List<Customer> listCustomer = (List<Customer>) BeanUtil.getDataList(data.getData(), HEADERS, Customer.class, SKIP_EMPTYROW, "createdbyUsername", "SYSTEM", "created");
        
        return listCustomer;
    }
  
    
    public static Iterable<Address> parseAddress(AddressModel data) {
        final String[] HEADERS = {"id", "label", "displayAddress", "latitude", "longitude"};

        LOG.info("Address data=" + data.getData());
        List<Address> listAddress = (List<Address>) BeanUtil.getDataList(data.getData(), HEADERS, Address.class, SKIP_EMPTYROW, "createdbyUsername", "SYSTEM", "created");
        
        return listAddress;
        
//        List<Address> listAddress = null;
//
//        if (data == null) {
//            return null;
//        } else {
//            List<List> rows = data.getData();
//            if (rows != null) {
//                listAddress = new ArrayList<Address>();
//                
//                Address address;
//                Integer id;
//                String city;
//                String displayAddress;
//                String street;
//                String ward;
//
//                for (List rowItem : rows) {
//                	address = new Address();
//                    id = CommonUtil.isNNandNB(rowItem.get(0)) ?  parseInt(rowItem.get(0)) : null;
//                    city = CommonUtil.isNNandNB(rowItem.get(1))  ?  (String)(rowItem.get(1)) : null;
//                    displayAddress = CommonUtil.isNNandNB(rowItem.get(2))  ? (String)(rowItem.get(2)) : null;
//                    street = CommonUtil.isNNandNB(rowItem.get(3))  ? (String)(rowItem.get(3)) : null;
//                    ward = CommonUtil.isNNandNB(rowItem.get(4))  ? (String)(rowItem.get(4)) : null;
//
//                    if (CommonUtil.isNNandNB(rowItem)) {
//                    	address.setId(id);
//                    	address.setCity((String) rowItem.get(1));
//                    	address.setDisplayAddress(displayAddress);
//                    	address.setStreet(street);
//                    	address.setWard(ward);
//                    	
//
//                    	address.setCreated(new Date());
//                    	address.setCreatedbyUsername("Cao Thai Son");
//                        
//                        listAddress.add(address);
//                    } else {
//                        // Skip the end empty line
//                    }
//                }
//            }
//        }
//        
//        return listAddress;
    }
    
    private static Double parseNum(Object obj) {
        Double num;

        if (obj == null) {
            return null;
        } else if (obj instanceof Integer) {
            num = 0.0 +  (Integer) obj;
        } else {
            num = Double.valueOf(obj.toString());
        }
        
        return num;
    }
    
    private static Integer parseInt(Object obj) {
        Integer num;

        if (obj == null) {
            return null;
        } else if (obj instanceof Integer) {
            num = 0 +  (Integer) obj;
        } else {
            num = Integer.valueOf(obj.toString());
        }
        
        return num;
    }

    public static OrderMaster parseOrder(OrderModel model) {
        final String[] HEADERS = {"id", "name", "length", "width", "height", "weight", "quantity"};
        OrderMaster order = new OrderMaster();
        
        order.setName(model.getOrderCd());
        
        Address addr = new Address();
        addr.setDisplayAddress(model.getAddress());
       
        // Set order's Address.
        order.setAddressId(addr);

        Customer customer = new Customer();
        customer.setId(model.getCustomer_id());
        
        // Set order's Customer.
        order.setCustomerId(customer);
        
        // Get Data from Order's handsonTable.
        List<OrderDetailProductModel> listOrderDetailProduct = (List<OrderDetailProductModel>) BeanUtil.getDataList(model.getProductData(), HEADERS, OrderDetailProductModel.class, true);
        
        List<OrderDetailProduct> orderDetailProductList = new ArrayList<>();

        OrderDetailProduct orderDetailProduct = new OrderDetailProduct();
        Product product = new Product();

        for (OrderDetailProductModel detailProduct : listOrderDetailProduct) {
            orderDetailProduct = new OrderDetailProduct();
            product = new Product();
            
            product.setId(detailProduct.getId());
            
            // Set OrderDetailProduct's Data
            orderDetailProduct.setProductId(product);
            orderDetailProduct.setQuantity(detailProduct.getQuantity());
            orderDetailProduct.setCreated(new Date());
            orderDetailProduct.setCreatedbyUsername("Nam Tang");
            
            orderDetailProductList.add(orderDetailProduct);
        }
        
        // Set order's OrderDetailProduct
        order.setOrderDetailProductList(orderDetailProductList);
        order.setCreated(new Date());

        return order;
    }
    
    public static Iterable<Product> parseProduct(ProductModel data) {
        final String[] HEADERS = {"id", "name", "description", "height", "width", "length", "weight"};

        LOG.info("Product data=" + data.getData());
        List<Product> listProduct = (List<Product>) BeanUtil.getDataList(data.getData(), HEADERS, Product.class, SKIP_EMPTYROW, "createdbyUsername", "SYSTEM", "created");
        
        return listProduct;
    }
    
    public static Iterable<Question> parseQuestion(QuestionModel data){
        final String[] HEADERS = {"id", "category", "question", "ask_person", "ask_date", "answer", "answer_person", "answer_date", "status"};
        
        LOG.info("Question data=" + data.getData());
        List<Question> questionList = (List<Question>) BeanUtil.getDataList(data.getData(), HEADERS, Question.class, SKIP_EMPTYROW, "createdbyUsername", "SYSTEM", "created");
        
        return questionList;
    }
}
