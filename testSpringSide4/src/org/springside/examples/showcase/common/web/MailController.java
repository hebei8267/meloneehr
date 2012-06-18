package org.springside.examples.showcase.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.examples.showcase.utilities.email.MimeMailService;
import org.springside.examples.showcase.utilities.email.SimpleMailService;

@Controller
@RequestMapping(value = "/common/mail")
public class MailController {
	@Autowired
	private MimeMailService mimeMailService;

	@Autowired
	private SimpleMailService simpleMailService;

	@RequestMapping(value = "sendSimpleMail")
	public String sendSimpleMail() {
		simpleMailService.sendNotificationMail("calvinTest");
		return "story/utilizes";
	}

	@RequestMapping(value = "sendMimeMail")
	public String sendMimeMail() {
		mimeMailService.sendNotificationMail("calvinTest");
		return "story/utilizes";
	}
}
