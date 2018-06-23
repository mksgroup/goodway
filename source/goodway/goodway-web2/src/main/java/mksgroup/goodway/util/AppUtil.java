/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.util;

import java.util.ArrayList;
import java.util.Date;
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
import mksgroup.java.common.CommonUtil;

/**
 * This utility provides features:<br/>
 * - Parse matrix of Vehicle from handsontable
 * @author ThachLN
 *
 */
public class AppUtil {

    public static Iterable<Vehicle> parseVehicle(@Valid VehicleModel data) {
        List<Vehicle> listVehicle = null;

        if (data == null) {
            return null;
        } else {
            List<List> rows = data.getData();
            if (rows != null) {
                listVehicle = new ArrayList<Vehicle>();
                
                Vehicle vehicle;
                Integer id;
                Double length;
                Double width;
                Double height;
                Double capacity;

                for (List rowItem : rows) {
                    vehicle = new Vehicle();
                    id = CommonUtil.isNNandNB(rowItem.get(0)) ?  parseInt(rowItem.get(0)) : null;
                    length = CommonUtil.isNNandNB(rowItem.get(2))  ?  parseNum(rowItem.get(2)) : null;
                    width = CommonUtil.isNNandNB(rowItem.get(3))  ? parseNum(rowItem.get(3)) : null;
                    height = CommonUtil.isNNandNB(rowItem.get(4))  ? parseNum(rowItem.get(4)) : null;
                    capacity = CommonUtil.isNNandNB(rowItem.get(5))  ? parseNum(rowItem.get(5)) : null;

                    if (CommonUtil.isNNandNB(rowItem)) {
                        vehicle.setId(id);
                        vehicle.setName((String) rowItem.get(1));
                        vehicle.setLength(length);
                        vehicle.setWidth(width);
                        vehicle.setHeight(height);
                        vehicle.setCapacity(capacity);

                        vehicle.setCreated(new Date());
                        vehicle.setCreatedbyUsername("TBD");
                        
                        listVehicle.add(vehicle);
                    } else {
                        // Skip the end empty line
                    }
                }
            }
        }
        
        return listVehicle;
    }

    public static Iterable<Customer> parseCustomer(@Valid CustomerModel data) {
        List<Customer> listCustomer = null;

        if (data == null) {
            return null;
        } else {
            List<List> rows = data.getData();
            if (rows != null) {
                listCustomer = new ArrayList<Customer>();
                
                Customer customer;
                Integer id;
                String cd;
                String name;
                String shortName;

                for (List rowItem : rows) {
                    customer = new Customer();
                    id = CommonUtil.isNNandNB(rowItem.get(0)) ?  parseInt(rowItem.get(0)) : null;
                    cd = CommonUtil.isNNandNB(rowItem.get(1))  ? (String)(rowItem.get(1)) : null;
                    name = CommonUtil.isNNandNB(rowItem.get(2))  ? (String)(rowItem.get(2)) : null;
                    shortName = CommonUtil.isNNandNB(rowItem.get(3))  ? (String)(rowItem.get(3)) : null;

                    if (CommonUtil.isNNandNB(rowItem)) {
                        customer.setId(id);
                        customer.setCd(cd);
                        customer.setSeqNo(0);
                        customer.setVersion(0);
                        customer.setName(name);
                        customer.setShortName(shortName);
                        customer.setCreated(new Date());
                        customer.setCreatedbyUsername("NamTang");
                        
                        listCustomer.add(customer);
                    } else {
                        // Skip the end empty line
                    }
                }
            }
        }
        
        return listCustomer;
    }
    
    public static Iterable<Address> parseAddress(AddressModel data) {
        List<Address> listAddress = null;

        if (data == null) {
            return null;
        } else {
            List<List> rows = data.getData();
            if (rows != null) {
                listAddress = new ArrayList<Address>();
                
                Address address;
                Integer id;
                String city;
                String displayAddress;
                String street;
                String ward;

                for (List rowItem : rows) {
                	address = new Address();
                    id = CommonUtil.isNNandNB(rowItem.get(0)) ?  parseInt(rowItem.get(0)) : null;
                    city = CommonUtil.isNNandNB(rowItem.get(1))  ?  (String)(rowItem.get(1)) : null;
                    displayAddress = CommonUtil.isNNandNB(rowItem.get(2))  ? (String)(rowItem.get(2)) : null;
                    street = CommonUtil.isNNandNB(rowItem.get(3))  ? (String)(rowItem.get(3)) : null;
                    ward = CommonUtil.isNNandNB(rowItem.get(4))  ? (String)(rowItem.get(4)) : null;

                    if (CommonUtil.isNNandNB(rowItem)) {
                    	address.setId(id);
                    	address.setCity((String) rowItem.get(1));
                    	address.setDisplayAddress(displayAddress);
                    	address.setStreet(street);
                    	address.setWard(ward);
                    	

                    	address.setCreated(new Date());
                    	address.setCreatedbyUsername("Cao Thai Son");
                        
                        listAddress.add(address);
                    } else {
                        // Skip the end empty line
                    }
                }
            }
        }
        
        return listAddress;
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
        
        List<Product> listProduct =  BeanUtil.getDataList(model.getProductData(), HEADERS, Product.class);
        
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
