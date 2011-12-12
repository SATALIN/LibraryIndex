/**
 * 
 */
package by.satalin.library.view;

import java.io.IOException;

import by.satalin.library.command.CommandVO;

/**
 * @author SATALIN
 * describe how doing with console, and set of functions
 */
public abstract class Console {
	public CommandVO command;

	/**
	 * @return the command
	 */
	public CommandVO getCommand() {
		return command;
	}

	/**
	 * @param command
	 *            the command to set
	 */
	public void setCommand(CommandVO command) {
		this.command = command;
	}
	public abstract void destroyBuffered() throws IOException;
	public abstract CommandVO parseStringCommand(String str);

	public abstract CommandVO getNextCommand() throws IOException;
}
