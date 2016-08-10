package by.darchyk.task1.command;

import javax.servlet.http.HttpServletRequest;

import by.darchyk.task1.command.ActionCommand;
import by.darchyk.task1.command.CommandEnum;

public class ActionFactory {
	
		public ActionCommand defineCommand(HttpServletRequest request) {
			ActionCommand	current = null;
			String action = request.getParameter("page");
			try {
				CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
				current = currentEnum.getCurrentCommand();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();;
			}
			return current;
		}
}