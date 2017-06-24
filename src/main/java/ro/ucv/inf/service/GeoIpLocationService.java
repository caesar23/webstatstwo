package ro.ucv.inf.service;

import java.io.IOException;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import ro.ucv.inf.model.GeoIpLocation;

public interface GeoIpLocationService {

	GeoIpLocation getLocation(String ip) throws IOException, GeoIp2Exception;

}
