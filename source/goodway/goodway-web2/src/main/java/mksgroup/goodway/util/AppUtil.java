/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import mksgroup.goodway.entity.Address;
import mksgroup.goodway.entity.Customer;
import mksgroup.goodway.entity.OrderDetailProduct;
import mksgroup.goodway.entity.OrderMaster;
import mksgroup.goodway.entity.Product;
import mksgroup.goodway.entity.Vehicle;
import mksgroup.goodway.model.AddressModel;
import mksgroup.goodway.model.CustomerModel;
import mksgroup.goodway.model.OrderModel;
import mksgroup.goodway.model.VehicleModel;
import mksgroup.java.common.BeanUtil;

/**
 * This utility provides features:<br/>
 * - Parse matrix of Vehicle from handsontable
 * @author ThachLN
 *
 */
public class AppUtil {
    /** Flag to parse data from handsontable: avoid the empty row. */
    final static boolean SKIP_EMPTYROW = true;

    public static Iterable<Vehicle> parseVehicle(@Valid VehicleModel data) {
        final String[] HEADERS = {"id", "name", "length", "width", "height", "capacity"};
        List<Vehicle> listVehicle = (List<Vehicle>) BeanUtil.getDataList(data.getData(), HEADERS, Vehicle.class, SKIP_EMPTYROW, "createdbyUsername", "SYSTEM", "created");

        return listVehicle;
    }

    public static Iterable<Customer> parseCustomer(@Valid CustomerModel data) {
        final String[] HEADERS = {"id", "name", "cd", "shortName", "phone", "addr"};

        List<Customer> listCustomer = (List<Customer>) BeanUtil.getDataList(data.getData(), HEADERS, Customer.class, SKIP_EMPTYROW, "createdbyUsername", "SYSTEM", "created");
        
        return listCustomer;
    }
    
    public static Iterable<Address> parseAddress(AddressModel data) {
        final String[] HEADERS = {"id", "displayAddress", "latitude", "longitude"};

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
        final String[] HEADERS = {"name", "length", "width", "height", "weight"};
        OrderMaster order = new OrderMaster();
        
        order.setName(model.getOrderCd());
        
        Address addr = new Address();
        addr.setDisplayAddress(model.getAddress());
        addr.setLatitude(model.getLatitude());
        addr.setLongitude(model.getLongitude());
        
        order.setAddressId(addr);

        Customer customer = new Customer();
        customer.setAddr(model.getAddress());
        
        order.setCustomerId(customer);
        
        List<Product> listProduct = (List<Product>) BeanUtil.getDataList(model.getProductData(), HEADERS, Product.class, true);
        
        List<OrderDetailProduct> orderDetailProductList = new ArrayList<>();

        OrderDetailProduct orderDetailProduct = new OrderDetailProduct();

        for (Product product : listProduct) {
            orderDetailProduct = new OrderDetailProduct();
            orderDetailProduct.setProductId(product);
            orderDetailProduct.setProductName(product.getName());

            orderDetailProductList.add(orderDetailProduct);
        }
        
        order.setOrderDetailProductList(orderDetailProductList);

        return order;
    }
}
