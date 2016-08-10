package by.darchyk.task1.command;

public enum CommandEnum {

	SEARCH {
		{
			this.command = new SearchClassCommand();
		}
	},
	ADD

	{
		{
			this.command = new AddNewClassCommand();
		}

	};

	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}
}
