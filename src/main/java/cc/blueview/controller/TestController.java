package cc.blueview.controller;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import cc.blueview.service.TestService;

@RestController
@RequestMapping("/bvc/test")
public class TestController {
	@Autowired
    TestService testService;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String hello(@QueryParam("testWord")String testWord) { 
//		String result = testService.getTestResult();
		return testWord;
	}

	@RequestMapping(value = "/noauth", method = RequestMethod.GET,produces = MediaType.ALL_VALUE)
	public String noauth(@QueryParam("testWord")String testWord) { 
//		String result = testService.getTestResult();
		return testWord;
	}
}
