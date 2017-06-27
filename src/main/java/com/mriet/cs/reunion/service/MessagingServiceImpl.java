package com.mriet.cs.reunion.service;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service("messagingService")
public class MessagingServiceImpl implements MessagingService {

	public static final String ACCOUNT_SID = "ACcafef409561436310515601c953b962c";
	public static final String AUTH_TOKEN = "3a63213f022eb0a7bb0ece403dced29f";
	public static final PhoneNumber APP_PHONE_NUMBER = new PhoneNumber("+13213237695");
	

	public void sendMessage(String to, String message) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message sms = Message.creator(new PhoneNumber(to), APP_PHONE_NUMBER, message).create();
		System.out.println(sms.getSid());
	}

}
