package com.omnirio.controller;

import com.omnirio.model.CustomResponse;
import com.omnirio.service.UserService;
import com.omnirio.util.LogUtil;
import com.omnirio.util.Utilities;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

@RestController
@RequestMapping("/omnirio")
public class UserController {

	private static Logger logger = LogUtil.getInstance();

	@Autowired
	private UserService mainService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<String> getAllUsers() {

		CustomResponse customResponse = mainService.getAllUsers();

		if (customResponse.getSuccess()) {
			return ResponseEntity.ok()
					.header("message", customResponse.getMessage())
					.body(customResponse.getInfoAsJson().toString());
		} else {
			return ResponseEntity.badRequest()
					.header("message", customResponse.getMessage())
					.body(customResponse.getMessage());
		}

	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> getUser(@PathVariable("id") String id) {

		CustomResponse customResponse = mainService.getUser(id);

		if (customResponse.getSuccess()) {
			return ResponseEntity.ok()
					.header("message", customResponse.getMessage())
					.body(customResponse.getInfoAsJson().toString());
		} else {
			return ResponseEntity.badRequest()
					.header("message", customResponse.getMessage())
					.body(customResponse.getMessage());
		}

	}

	@PostMapping("/user")
	@ResponseBody
	public ResponseEntity<String> createUser(@RequestBody String body) throws Exception {

		JSONObject jsonObject = new JSONObject(body.trim());
		CustomResponse customResponse = mainService.createUser(Utilities.jsonToUser(jsonObject));
		if (customResponse.getSuccess()) {
			return ResponseEntity.ok()
					.header("message", customResponse.getMessage())
					.body(customResponse.getInfoAsJson().toString());
		} else {
			return ResponseEntity.badRequest()
					.header("message", customResponse.getMessage())
					.body(customResponse.getMessage());
		}
	}

	// below services will generate a token, this token will be used by Account service in order to work well.
	@RequestMapping(value = "/user/{bm_id}/user", method = RequestMethod.PUT)
	public ResponseEntity<String> updateUser(@PathVariable("bm_id") String bm_id,
											 @RequestBody String body) throws JSONException {

		JSONObject jsonObject = new JSONObject(body.trim());
		CustomResponse customResponse = mainService.userUpdateUser(bm_id, Utilities.jsonToUser(jsonObject));
		if (customResponse.getSuccess()) {
			return ResponseEntity.ok()
					.header("message", customResponse.getMessage())
					.body(customResponse.getInfoAsJson().toString());
		} else {
			return ResponseEntity.badRequest()
					.header("message", customResponse.getMessage())
					.body(customResponse.getMessage());
		}
	}

}
