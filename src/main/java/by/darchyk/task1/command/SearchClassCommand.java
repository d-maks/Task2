package by.darchyk.task1.command;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import by.darchyk.task1.service.Service;
import by.darhyk.task1.toggle.Toggle;

public class SearchClassCommand implements ActionCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out;
		try {
			out = response.getWriter();

			String keyword = request.getParameter("keyword");
			response.setContentType("text/html");
			response.setHeader("Cache-control", "no-cache, no-store");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");

			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type");
			response.setHeader("Access-Control-Max-Age", "86400");

			String message;
			Service service = new Service();
			String className = service.findClassName(keyword);
			if (className != null) {
				message = className + ": I'm turned " + Toggle.getState(className) + "!";

			} else {
				message = "This class is not found! Enter correct keyword!";
			}
			System.out.println(message);
			Gson gson = new Gson();
			JsonObject myObj = new JsonObject();
			JsonElement jE = gson.toJsonTree(message);
			myObj.add("message", jE);
			out.println(myObj.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
