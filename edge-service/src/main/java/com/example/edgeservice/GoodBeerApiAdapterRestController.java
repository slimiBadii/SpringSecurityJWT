package com.example.edgeservice;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
class GoodBeerApiAdapterRestController {

    private final BeerClient beerClient;
    @Autowired
	private SecurityTokenConfig securityTokenConfig;
    public GoodBeerApiAdapterRestController(BeerClient beerClient) {
        this.beerClient = beerClient;
    }
    @HystrixCommand(fallbackMethod = "callStudentServiceAndGetData_Fallback")
    @CrossOrigin(origins = "*")

    @GetMapping("/good-beers")
    public Object goodBeers(@RequestHeader(value = "Authorization") String authorizationHeader) {
    String	jwtToken = authorizationHeader;

    System.out.println("jwtToken="+jwtToken);
    	return beerClient.readBeers(jwtToken)
                .getContent()
                .stream()
                .filter(this::isGreat)
                .collect(Collectors.toList());
    }

    private boolean isGreat(Beer beer) {
        return !beer.getName().equals("Budweiser") && !beer.getName().equals("Coors Light") && !beer.getName().equals("PBR");
    }
    
    @SuppressWarnings("unused")
	private Object callStudentServiceAndGetData_Fallback(@RequestHeader(value = "Authorization") String authorizationHeader) {
    	System.out.println("callStudentServiceAndGetData_Fallback token ="+authorizationHeader);
		System.out.println("Student Service is down!!! fallback route enabled...");
		return "CIRCUIT BREAKER ENABLED!!!No Response From Student Service at this moment. Service will be back shortly - " + new Date();
	}
}