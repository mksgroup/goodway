/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.entity.Address;
import mksgroup.goodway.entity.Vehicle;
import mksgroup.goodway.model.AddressModel;
import mksgroup.goodway.repository.AddressRepository;
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
	private AddressRepository addressRepository;

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

		Iterable<Address> address = addressRepository.findAll();

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
			List<Address> entityList = new ArrayList<Address>();
			entities.forEach(e-> entityList.add(e));
			List<Address> address = (List<Address>)addressRepository.findAll();
			for(Address a : address) {
				if(!entityList.contains(a)) {
					addressRepository.delete(a);
				}
			}
			
			addressRepository.saveAll(entities);
			LOG.info("addressModel=" + data + ";request=" + request);
		}

		// Reload data from db
		Iterable<Address> address = addressRepository.findAll();

		return address;
	}
}
