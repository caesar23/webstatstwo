package ro.ucv.inf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ro.ucv.inf.model.GeoIpLocation;
import ro.ucv.inf.service.GeoIpLocationService;

import javax.inject.Inject;

@Controller
public class GeoIpLocationController {

	private GeoIpLocationService geoIpLocationService;

	@Inject
	public GeoIpLocationController(GeoIpLocationService geoIpLocationService) {
		this.geoIpLocationService = geoIpLocationService;
	}

	@PostMapping(value = "/geoIp")
	@ResponseBody
	public GeoIpLocation getLocation(@RequestParam(value = "ipAddress", required = true) String ipAddress)
			throws Exception {
		//TODO SAFE IN DB OR SOMETHING
		return geoIpLocationService.getLocation(ipAddress);
	}

}
