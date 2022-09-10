package com.juan.string;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
public class StringApplication {

	public static void main(String[] args) {
		SpringApplication.run(StringApplication.class, args);
	}
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "Hey man!";
	}
	@RequestMapping(value="next", method=RequestMethod.GET)
	public String next() {
		return "This is a nice message for user";
	}
}
