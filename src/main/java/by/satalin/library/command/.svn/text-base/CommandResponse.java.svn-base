/**
 * 
 */
package by.satalin.library.command;

import java.util.HashMap;
import java.util.Map;
import by.satalin.library.exceptions.BaseException;

/**
 * @author SATALIN Response on command. it is result all complited operations,
 *         with parameters or exception if error
 */
public class CommandResponse {
	protected CommandVO command;
	protected Map<String, Object> parameters;
	protected BaseException exception;

	public CommandResponse() {
		this.parameters =  new HashMap<String, Object>();
	}

	public BaseException getException() {
		return exception;
	}

	public void setException(BaseException exception) {
		this.exception = exception;
	}

	/**
	 * @return the cmd
	 */
	public CommandVO getCommand() {
		return command;
	}

	/**
	 * @param cmd
	 *            the cmd to set
	 */
	public void setCommand(CommandVO cmd) {
		this.command = cmd;
	}

	/**
	 * @return the parameters
	 */
	public Map<String, Object> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
	/**
	 * 
	 * @param parameters add parameters
	 */
	public void addParameters(Map<String, Object> parameters){
		this.parameters.putAll(parameters);
	}
	/**
	 * put parameters
	 * @param key
	 * @param value
	 */
	public void putParameters(String key, Object value){
		this.parameters.put(key, value);
	}
	/**
	 * 
	 * @param key 
	 * @return return object by key
	 */
	public Object getResultParameters(String key) {
		return this.parameters.get(key);
	}

}
