/**
 * 
 */
package by.satalin.library.command;

import java.util.HashMap;
import java.util.Map;

import by.satalin.library.datamodel.User;
import by.satalin.library.security.Role;

/**
 * @author SATALIN
 *  entity with  command name and parameters
 */
public abstract class CommandVO {

	protected String name;
	protected Map<String, String> parameters;
	protected User user;
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	public Role getRole(){
		return this.user.getRole();
	}
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
	/**
	 * 
	 * @param parameters add parameters
	 */
	public void addParameters(Map<String, String> parameters){
		this.parameters.putAll(parameters);
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public CommandVO() {
		this.parameters = new HashMap<String, String>();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the params
	 */
	public Map<String, String> getParameters() {
		return parameters;
	}

	public String getParameterByKey(String key) {
		return this.parameters.get(key.toUpperCase());
	}

}
