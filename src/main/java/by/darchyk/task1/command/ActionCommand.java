package by.darchyk.task1.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionCommand {
	void execute(HttpServletRequest request, HttpServletResponse response);}
