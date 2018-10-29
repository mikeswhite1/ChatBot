package guide;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.common.collect.ImmutableMap;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.TwiMLException;



public class HelloWorldServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HelloWorldServlet(){

	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("again");
		response.setContentType("text/html");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String body = request.getParameter("Body");
		String message = "Message";
		if (body != null) {
			if (body.equals("hello")) {
				// Say hi
				message = "Hi there!";
			} else if (body.equals("bye")) {
				// Say goodbye
				message = "Goodbye!";
			}
		}
		
		// Create a TwiML response and add our friendly message.
		Body messageBody = new Body.Builder(message).build();
		Message sms = new Message.Builder().body(messageBody).build();
		MessagingResponse twiml = new MessagingResponse.Builder().message(sms).build();

		response.setContentType("application/xml");

		try {
			response.getWriter().print(twiml.toXml());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

