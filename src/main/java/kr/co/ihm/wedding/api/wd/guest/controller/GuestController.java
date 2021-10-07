/**
 * (주)오픈잇 | http://www.openit.co.kr
 * Copyright (c)2006-2021, openit Inc.
 * All right reserved.
 */

package kr.co.ihm.wedding.api.wd.guest.controller;
import kr.co.ihm.wedding.api.wd.guest.model.GuestDTO;
import kr.co.ihm.wedding.api.wd.guest.model.GuestSearchDTO;
import kr.co.ihm.wedding.api.wd.guest.service.GuestService;
import kr.co.ihm.wedding.common.ResponseObject;
import kr.co.ihm.wedding.common.ServiceConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ihmoon
 * @desc Guest Controller
 */
@Controller
public class GuestController {

	private static final Logger logger = LoggerFactory.getLogger(GuestController.class);

	@Autowired
	GuestService guestService;

	@GetMapping(path = "/guest", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseObject> getTargetGuest(@RequestParam(required =true, value = "phoneNumber") String phoneNumber) throws RuntimeException {

		GuestSearchDTO vo = new GuestSearchDTO();
		vo.setLastPhoneNum(phoneNumber);
		GuestDTO guestDTO = guestService.getTargetGuest(vo);
		ResponseObject responseObject = ResponseObject.builder(ServiceConstant.HttpStatus.OK, guestDTO, ServiceConstant.ResponseMessage.SUCCESS).build();

		return new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);
	}

}
