//import com.mailjet.client.errors.MailjetException;
//import com.mailjet.client.errors.MailjetSocketTimeoutException;
package cwTestCases;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Email;
import com.mailjet.client.resource.Emailv31;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
public class mailjet {
    /**
     * Run:
     * @throws IOException 
     */
	
	public static void main(String[] args) throws MailjetException, MailjetSocketTimeoutException, IOException {
		MailjetClient client;
	      MailjetRequest request;
	      MailjetResponse response;
	      java.nio.file.Path exlpath = java.nio.file.Paths.get("C:\\Users\\148972\\workspace\\Monitoring\\Shift_checklist_1.xlsx");
	      byte[] filecontent = java.nio.file.Files.readAllBytes(exlpath);
	      String fileData = com.mailjet.client.Base64.encode(filecontent);
	      client = new MailjetClient("90588b6a2462cd560d568cf8be3cdbd5","4cc80d345cf0dedf4b756e4f4cc6e9a9");
	      request = new MailjetRequest(Email.resource)
							.property(Email.FROMEMAIL, "alerts@thenavisafe.com")
							.property(Email.FROMNAME, "NaviSafe")
							.property(Email.SUBJECT, "Your email flight plan!")
							.property(Email.TEXTPART, "Dear passenger, welcome to Mailjet! May the delivery force be with you!")
							.property(Email.HTMLPART, "<h3>Dear passenger, welcome to <a href=\"https://www.mailjet.com/\">Mailjet</a>!<br />May the delivery force be with you!")
							.property(Email.RECIPIENTS, new JSONArray()
	                        .put(new JSONObject()
	                        .put("Email", "Ajay.Bobby@ust.com"))
	                        .put(new JSONObject()
                            .put("Email", "Lintin.PaulThekkanath@ust.com")
	                        .put("Name", "NAVISAFE")))
	                        .property(Email.ATTACHMENTS, new JSONArray()
	                        .put(new JSONObject()
	                        .put("Content-type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	                        .put("Filename", "Shift_checklist_1.xlsx")
//	                    .put("content", "excelFileBase64")));
	                        .put("content", fileData)));  
//	                    .put("Base64Content", fileData)));   
	      response = client.post(request);
	      System.out.println(response.getStatus());
	      System.out.println(response.getData());
	}
}