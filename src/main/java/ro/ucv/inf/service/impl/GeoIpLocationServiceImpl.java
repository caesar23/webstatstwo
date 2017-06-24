package ro.ucv.inf.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.maxmind.db.Reader.FileMode;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import ro.ucv.inf.model.GeoIpLocation;
import ro.ucv.inf.service.GeoIpLocationService;

import javax.inject.Inject;

@Service("geolocationservice")
public class GeoIpLocationServiceImpl implements GeoIpLocationService {

	private DatabaseReader dbReader;

	@Inject
	public GeoIpLocationServiceImpl(ResourceLoader resourceLoader) {
		Resource resource = resourceLoader.getResource("classpath:localdb/GeoLite2-City.mmdb");
		InputStream dbAsStream;
		try {
			dbAsStream = resource.getInputStream();
			dbReader = new DatabaseReader.Builder(dbAsStream).fileMode(FileMode.MEMORY).build();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public GeoIpLocation getLocation(String ip) throws IOException, GeoIp2Exception {
		InetAddress ipAddress = InetAddress.getByName(ip);
		CityResponse response = dbReader.city(ipAddress);

		String cityName = response.getCity().getName();
		String latitude = response.getLocation().getLatitude().toString();
		String longitude = response.getLocation().getLongitude().toString();
		return new GeoIpLocation(ip, cityName, latitude, longitude);
	}
	
}
