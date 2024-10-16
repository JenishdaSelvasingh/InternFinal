package com.example.demo.Service;

import jakarta.transaction.Transactional;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class MailtrapClient {
    private static final Logger log = LoggerFactory.getLogger(MailtrapClient.class);
    private final OkHttpClient client;

    public void sendEmail(String from, String to, String subject, String text, String category) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"from\":{\"email\":\"" + from + "\",\"name\":\"Mailtrap Test\"},\"to\":[{\"email\":\"" + to + "\"}],\"subject\":\"" + subject + "\",\"text\":\"" + text + "\",\"category\":\"" + category + "\"}");
        Request request = (new Request.Builder()).url("https://send.api.mailtrap.io/api/send").method("POST", body).addHeader("Authorization", "Bearer 29f292fa2d8568f4606dc09014510e62").addHeader("Content-Type", "application/json").build();
        Response response = this.client.newCall(request).execute();
    }

    public MailtrapClient(final OkHttpClient client) {
        this.client = client;
    }
}
