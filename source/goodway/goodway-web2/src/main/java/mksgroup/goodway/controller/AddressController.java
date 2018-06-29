/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.biz.AddressBiz;
import mksgroup.goodway.entity.Address;
import mksgroup.goodway.model.AddressModel;
import mksgroup.goodway.util.AppUtil;

/**
 * This controller processes requests about Address.
 * 
 * @author ThachLN
 */
@Controller
public class AddressController {

	/** For logging. */
	private final static Logger LOG = LoggerFactory.getLogger(VehicleController.class);

    @Value("${map.key}")
    String mapKey;

	@Autowired
	private AddressBiz addressBiz;

	/**
	 * Goto the index page.
	 * 
	 * @return
	 */
	@RequestMapping({ "/address", "/address/search" })
	public String goAddressearch() {
		return "address/search";
	}

	@RequestMapping("/address/new")
	public String goAddressrNew(ModelMap model) {
	    model.addAttribute("map_key", mapKey);

		return "address/new";
	}

	/**
	 * Load danh sách các xe.
	 * 
	 * @param packageId
	 * @return
	 */
	@GetMapping("/address/load-address")
	@ResponseBody
	public Iterable<Address> loadAddress() {

		Iterable<Address> listAddress = addressBiz.getRepo().findAll();

		return listAddress;
	}
	
    @GetMapping("/address/load-address/{id}")
    @ResponseBody
    public Address loadAddressById(@PathVariable("id") Integer addressId) {

        Address address = addressBiz.getRepo().findById(addressId).get();

        return address;
    }

	@PostMapping("/address/save")
	@ResponseBody
	public Iterable<Address> saveAddresses(@Valid @RequestBody AddressModel data, Errors errors,
			HttpServletRequest request) {
		LOG.info("saveAddress....");

		// If error, just return a 400 bad request, along with the error message
		if (errors.hasErrors()) {

			LOG.error(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));

			return null;
		} else {
			Iterable<Address> entities = AppUtil.parseAddress(data);
            addressBiz.updateAddresses(entities, data.getDeletedIds());
		}

		// Reload data from db
		Iterable<Address> address = addressBiz.getRepo().findAll();

		return address;
	}
}
