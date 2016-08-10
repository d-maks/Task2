package by.darchyk.task1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.darchyk.task1.command.ActionCommand;
import by.darchyk.task1.command.ActionFactory;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionFactory actionFactory = new ActionFactory();
		ActionCommand actionCommand = actionFactory.defineCommand(request);
		actionCommand.execute(request, response);
	}

}
