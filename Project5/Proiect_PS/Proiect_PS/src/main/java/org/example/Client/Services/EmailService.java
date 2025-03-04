package org.example.Client.Services;

import java.io.IOException;

public class EmailService {
   /* private String apiKey;

    public EmailService(String apiKey) {
        this.apiKey = apiKey;
    }

    public void sendEmail(String to, String subject, String content) throws IOException {
        Email from = new Email("sirbuclaudiu737@gmail.com");
        Email toEmail = new Email(to);
        Content emailContent = new Content("Ati fost angajat in muzeu.", content);
        Mail mail = new Mail(from, subject, toEmail, emailContent);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Body: " + response.getBody());
            System.out.println("Headers: " + response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }*/
}
