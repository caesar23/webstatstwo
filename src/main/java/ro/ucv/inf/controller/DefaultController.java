package ro.ucv.inf.controller;

import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.ucv.inf.model.RequestHeaderDto;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
public class DefaultController {

	@GetMapping(value = "/")
	public String redirectToDefault() {
		return "redirect:/myinfo/";
	}
	
	/*@RequestMapping(value = "/myinfo", method = RequestMethod.POST)
	public String myinfoPost(HttpServletRequest request, Model model, Device device) {
		
	}*/

    @GetMapping(value = "/myinfo")
    public String myinfoGet(HttpServletRequest request, Model model, Device device) {
        Enumeration<String> enn = request.getHeaderNames();
        List<RequestHeaderDto> rhList = new ArrayList<RequestHeaderDto>();

        while (enn.hasMoreElements()) {
            String name = enn.nextElement();
            String value = request.getHeader(name);
            if (StringUtils.isEmpty(name) || StringUtils.isEmpty(value)) {
                continue;
            }
            RequestHeaderDto rh = new RequestHeaderDto(name, value);
            rhList.add(rh);
        }
        model.addAttribute("rhlist", rhList);

        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        model.addAttribute("browser", userAgent.getBrowser().getName() + " v" + userAgent.getBrowserVersion());
        model.addAttribute("os", userAgent.getOperatingSystem());

        String deviceType = "unknown";
        if (device.isNormal()) {
            deviceType = "PC";
        } else if (device.isMobile()) {
            deviceType = "Mobile";
        } else if (device.isTablet()) {
            deviceType = "Tablet";
        }
        model.addAttribute("devicetype", deviceType);

        return "myinfopage";
    }
	
}
