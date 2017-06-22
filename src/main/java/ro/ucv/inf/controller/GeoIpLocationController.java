package ro.ucv.inf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.ucv.inf.model.GeoIpLocation;
import ro.ucv.inf.service.GeoIpLocationService;

@Controller
public class GeoIpLocationController {

	@Autowired
	private GeoIpLocationService geoIpLocationService;

	@RequestMapping(value = "/geoIp", method = RequestMethod.POST)
	@ResponseBody
	public GeoIpLocation getLocation(@RequestParam(value = "ipAddress", required = true) String ipAddress)
			throws Exception {
		//TODO SAFE IN DB OR SOMETHING
		return geoIpLocationService.getLocation(ipAddress);
	}

}
