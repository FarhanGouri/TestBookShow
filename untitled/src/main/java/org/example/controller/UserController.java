package org.example.controller;

import org.example.Service.UserService;
import org.example.authentication.UserSession;
import org.example.request.UserRequestDto;
import org.example.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {


	@Autowired
	UserService userService;

	@Autowired
	UserSession userSession;

	@PostMapping(value = "/user-signup/", produces = "application/json")
	public RestResponse signUpUser(@RequestBody UserRequestDto userDto ) {
		return userService.register(userDto);
	}
	
	@PostMapping(value = "/user-signin/", produces = "application/json")
	public RestResponse signInUser(@RequestBody UserRequestDto userDto ) {
		return (RestResponse) userService.login(userDto);
	}
	
	@GetMapping(value = "/user/",  produces = "application/json")
	public RestResponse getUser() {
		return userService.getUserDeatils(Long.parseLong(userSession.getUserId()));
	}

}
