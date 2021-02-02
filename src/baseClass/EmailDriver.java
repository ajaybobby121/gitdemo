package baseClass;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
//import javax.mail.*;
//import javax.mail.internet.*;
import javax.activation.*;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

//import com.mailjet.client.ClientOptions;
//import com.mailjet.client.MailjetClient;
//import com.mailjet.client.MailjetRequest;
//import com.mailjet.client.MailjetResponse;
//import com.mailjet.client.errors.MailjetException;
//import com.mailjet.client.errors.MailjetSocketTimeoutException;
//import com.mailjet.client.resource.Email;
//import com.mailjet.client.resource.Emailv31;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
//import com.sendgrid.helpers.mail.objects.Email;
public class EmailDriver
{
//	public String MailAttch(String filename,String usrNme, String usrpwd,String  frmEmail, String toEmail, String subEmail, String contEmail){
//		final String username = usrNme;
//        final String password = usrpwd;   
//
//     //Get the session object
//      Properties properties = System.getProperties();
//      properties.setProperty("mail.smtp.host","smtp.gmail.com");
//      properties.put("mail.smtp.port", "465");
//      properties.put("mail.smtp.ssl.enable", "true");
//      properties.put("mail.smtp.auth", "true");
//      Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//
//          protected PasswordAuthentication getPasswordAuthentication() {
//
//              return new PasswordAuthentication(username, password);
//
//          }
//
//      });
//
//
//     //compose the message
//      try{
//         MimeMessage message = new MimeMessage(session);
//         message.setFrom(new InternetAddress(frmEmail));
//         message.addRecipients(Message.RecipientType.TO,new InternetAddress().parse(toEmail));
//         
//        
//         message.setSubject(subEmail);
//
//         Multipart multipart = new MimeMultipart();
//
////         MimeBodyPart attachmentPart = new MimeBodyPart();
//         MimeBodyPart attachmentPart=new MimeBodyPart();
//
//         MimeBodyPart textPart = new MimeBodyPart();
//
//         try {
//        	 
//
//             File f =new File(filename);
//             attachmentPart.attachFile(f);
//             textPart.setContent(contEmail,"text/html");
//             multipart.addBodyPart(textPart);
//             multipart.addBodyPart(attachmentPart);
//
//         } catch (IOException e) {
//
//             e.printStackTrace();
//
//         }
//
//         message.setContent(multipart);
//
//         System.out.println("sending...");
//         // Send message
//         Transport.send(message);
//         System.out.println("Sent message successfully....");
//         
//     } catch (MessagingException mex) {
//         mex.printStackTrace();
//     }
//      return "success";
//}
//	public static JsonNode sendSimpleMessage(String filename,String  frmEmail, String toEmail, String subEmail, String contEmail) throws UnirestException {
//
//        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + "NaviSafe" + "/messages")
//        		.basicAuth("api", "90588b6a2462cd560d568cf8be3cdbd5")
//                .field("from", frmEmail)
//                .field("to", toEmail)
//                .field("subject", subEmail)
//                 .field("html", contEmail)
//                .field("attachment", new File(filename))
//                .asJson();
//
//        return request.getBody();
//    }
//	public static void sendSimpleMessage(String filename,String  frmEmail, String toEmail, String subEmail, String contEmail) throws UnirestException, MailjetException, MailjetSocketTimeoutException {
//		 
//
//	        
//		MailjetClient client;
//        MailjetRequest request;
//        MailjetResponse response;
//        File f =new File(filename);
//       
//        client = new MailjetClient("90588b6a2462cd560d568cf8be3cdbd5","4cc80d345cf0dedf4b756e4f4cc6e9a9", new ClientOptions("v3.1"));
//        request = new MailjetRequest(Emailv31.resource)
//              .property(Emailv31.MESSAGES, new JSONArray()
//                  .put(new JSONObject()
//                      .put(Emailv31.Message.FROM, new JSONObject()
//                          .put("Email", "alerts@thenavisafe.com")
////                          .put("Name", "NaviSafe")
//                          )
//                      .put(Emailv31.Message.TO, new JSONArray()
//                          .put(new JSONObject()
//                              .put("Email","148972@ust-global.com")
//                              
//                                  ))
//                      .put(Emailv31.Message.SUBJECT, subEmail)
//                      
//                      .put(Emailv31.Message.HTMLPART, contEmail)
//                      .put(Emailv31.Message.ATTACHMENTS, new JSONArray()
//                              .put(new JSONObject()
//                                      .put("ContentType", "excel/xlsx")
//                                      .put("Filename", f)
////                                      .put("Base64Content", "VGhpcyBpcyB5b3VyIGF0dGFjaGVkIGZpbGUhISEK")))))
//                      
//                  );
//        response = client.post(request);
//        System.out.println(response.getStatus());
//        System.out.println(response.getData());
//      
//	}
	//Using mail jet
//	public static void sendSimpleMessage(String filename,String  frmEmail, String toEmail, String subEmail, String contEmail) throws UnirestException, MailjetException, MailjetSocketTimeoutException, IOException {
//		MailjetClient client;
//	      MailjetRequest request;
//	      MailjetResponse response;
//	      java.nio.file.Path exlpath = java.nio.file.Paths.get(filename);
//	      byte[] filecontent = java.nio.file.Files.readAllBytes(exlpath);
//	      String fileData = com.mailjet.client.Base64.encode(filecontent);
////	      Base64 codec = new Base64();
////	      String encoded = codec.encodeBase64String("Hello".getBytes());
//	      client = new MailjetClient("90588b6a2462cd560d568cf8be3cdbd5","4cc80d345cf0dedf4b756e4f4cc6e9a9");
//	      request = new MailjetRequest(Email.resource)
//							.property(Email.FROMEMAIL, "alerts@thenavisafe.com")
//							.property(Email.FROMNAME, "NaviSafe")
//							.property(Email.SUBJECT, subEmail)
////							.property(Email.TEXTPART, "Dear passenger, welcome to Mailjet! May the delivery force be with you!")
//							.property(Email.HTMLPART,contEmail)
//							 .property(Email.ATTACHMENTS, new JSONArray()
//						                .put(new JSONObject()
//						                		.put("Content-type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
//							                    .put("Filename", filename)
////					                            .put("Base64Content", "excelFileBase64")))
//							                    .put("content", fileData)))
//							.property(Email.RECIPIENTS, new JSONArray()
//	                .put(new JSONObject()
//	                    .put("Email", "Ajay.Bobby@ust.com")
//	                    .put("Name", "Navisafe")));
////	                .put(new JSONObject()
////                            .put("Email", "Lintin.PaulThekkanath@ust.com")
////	                        .put("Name", "NAVISAFE")));
//	     
//	      response = client.post(request);
//	      System.out.println(response.getStatus());//	      System.out.println(response.getData());
//	    }
	public static void sendMail(String filename,String  frmEmail, String toEmail, String subEmail, String contEmail,List<List<String>> email) throws UnirestException, IOException {
		  Email from = new Email(frmEmail);
		  List<Personalization>per= new ArrayList<Personalization>();
		   
	       Email to = new Email(toEmail); // use your own email address here
//		  Mail mail1 = new Mail();
		  
		  
	       String subject = subEmail;
	       
	       Content content = new Content("text/html", contEmail);

	       Mail mail = new Mail(from, subject,to, content);
	       
//	       for adding multiple receiptents
	        Personalization personalization = new Personalization();
            for (int i = 0, size = email.size(); i < size; i++) {
	  		    personalization.addTo(new Email(email.get(i).get(1)));             
	  		 }
	  		 mail.addPersonalization(personalization);
	       
	       //for attaching excel
	       final String path = filename;
           byte[] bFile = null;
	       try {
	         bFile = Files.readAllBytes(new File(path).toPath());
	       } catch (IOException e) {
	         e.printStackTrace();
	       }
	       Base64 x = new Base64();
	       Attachments attachments3 = new Attachments();
	       String imageDataString = x.encodeAsString(bFile);
	       attachments3.setContent(imageDataString);
//	       attachments3.setType("xlxs");// "application/pdf"
	       attachments3.setFilename("Shift_checklist.xlsx");
	       attachments3.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//	       attachments3.setFilename(filename);
	       attachments3.setDisposition("attachment");
	       mail.addAttachments(attachments3);

//	       SendGrid sg = new SendGrid(System.getenv("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"));
	       SendGrid sg = new SendGrid("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");
	      
	       Request request = new Request();

	       request.setMethod(Method.POST);
	       request.setEndpoint("mail/send");
	       request.setBody(mail.build());
	      
	       Response response = sg.api(request);

	       System.out.println(response.getStatusCode());
	       System.out.println(response.getHeaders());
	       System.out.println(response.getBody());
//		SendGrid sendgrid = new SendGrid("SENDGRID_APIKEY");
//
//	    SendGrid.Email email = new SendGrid.Email();
//
//	    email.addTo("test@sendgrid.com");
//	    email.setFrom("you@youremail.com");
//	    email.setSubject("Sending with SendGrid is Fun");
//	    email.setHtml("and easy to do anywhere, even with Java");
//
//	    SendGrid.Response response = sendgrid.send(email);
		
	}
		
		
	
}
	
	
	

