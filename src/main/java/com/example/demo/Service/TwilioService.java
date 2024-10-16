package com.example.demo.Service;

import com.example.demo.Config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {
    @Autowired
    private TwilioConfig twilioConfig;

    public TwilioService() {
    }

    public void sendOTPSms(String name, String device) {
        Twilio.init(this.twilioConfig.getAccountSid(), this.twilioConfig.getAuthToken());
        LocalTime currentTime = LocalTime.now();
        int currentHour = currentTime.getHour();
        String wish = "";
        if (currentHour >= 5 && currentHour < 12) {
            wish = "Good Morning";
        } else if (currentHour >= 12 && currentHour < 17) {
            wish = "Good Afternoon";
        } else if (currentHour >= 17 && currentHour < 21) {
            wish = "Good Evening";
        } else {
            wish = "Good Night";
        }

        Message message = (Message)Message.creator(new PhoneNumber("+916374032518"), new PhoneNumber(this.twilioConfig.getTrialNumber()), wish + ", Admin! \n" + name + " has requested a " + device).create();
    }
}
