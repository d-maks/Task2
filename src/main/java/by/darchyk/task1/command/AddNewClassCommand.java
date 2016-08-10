package by.darchyk.task1.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import by.darchyk.task1.entity.Entity;
import by.darchyk.task1.service.Service;

public class AddNewClassCommand implements ActionCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out;
		Entity entity = new Entity();
		try {
			out = response.getWriter();

			entity.setClassName(request.getParameter("class"));
			entity.setKeywords(request.getParameter("addKeyword"));
			response.setContentType("text/html");
			response.setHeader("Cache-control", "no-cache, no-store");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");

			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type");
			response.setHeader("Access-Control-Max-Age", "86400");

			Service service = new Service();
			String addMessage;
			if(service.addClass(entity) == true){
				addMessage = "Class is added!";
				
			} else {
				addMessage = "Error!";
			}
			Gson gson = new Gson();
			JsonObject myObj = new JsonObject();
			JsonElement jE = gson.toJsonTree(addMessage);
			myObj.add("addMessage", jE);
			out.println(myObj.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
