/**
 * 
 */
package by.satalin.library.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import org.apache.log4j.Logger;
import by.satalin.library.command.CommandVO;
import by.satalin.library.command.CommandRequest;
import by.satalin.library.datamodel.User;

/**
 * @author SATALIN
 * implementation console
 */
public class InputConsole extends Console {
	private static final Logger log = Logger.getLogger(InputConsole.class);
	private BufferedReader reader;
	private BufferedWriter writer;

	public InputConsole(InputStream arg0, OutputStream arg1) {
		InputStreamReader reader = new InputStreamReader(arg0);
		OutputStreamWriter writer = new OutputStreamWriter(arg1);
		this.reader = new BufferedReader(reader);
		this.writer = new BufferedWriter(writer);
	}
	public InputConsole(){
		
	}

	public CommandVO getNextCommand(User user) throws IOException {

		writer.write("\n Enter command \n");
		writer.flush();
		writer.newLine();
		command = new CommandRequest();
		String operation = "";
		operation = reader.readLine();
		log.info("Input string - "+operation);
		if (operation.length() > 0) {
			command = parseStringCommand(operation.toUpperCase());
		}
		command.setUser(user);
		return command;
	}

	public User authentification() throws IOException {
		User user= new User();
		writer.write("\n Enter username and password \n");
		writer.flush();
		writer.newLine();
		command = new CommandRequest();
		String operation = "";
		operation = reader.readLine();
		log.info("Input string - "+operation);
		if (operation.length() > 0) {
			 user = parseAuthentification(operation.toUpperCase());
		}
		return user;
	}
	private User parseAuthentification(String str) {
		User user = new User();
		String[] words;
		String delimiter = " ";
		words = str.split(delimiter);
		user.setNickName(words[0]);
		log.info("Login ="+words[0]);
		user.setPassword(words[1]);
		log.info("Password ="+words[1]);
		return user;
	}
	@Override
	public void destroyBuffered() throws IOException {
		if (reader != null) {
			log.info("reader close");
			reader.close();
		}
		if (writer != null) {
			log.info("writer close");
			writer.close();
		}
	}

	public CommandVO parseStringCommand(String str) {
		CommandVO cmd = new CommandRequest();
		String[] words;
		String delimiter = " ";
		words = str.split(delimiter);
		cmd.setName(words[0]);
		log.info("word ="+words[0]);
		delimiter = "=";
		if (words.length > 1) {
			for (int i = 1; i < words.length; i++) {
				String[] tmp;
				tmp = words[i].split(delimiter);
				log.info("word ="+words[0]);
				log.info("word ="+words[1]);
				cmd.getParameters().put(tmp[0], tmp[1]);
			}
		}
		return cmd;
	}
	@Override
	public CommandVO getNextCommand() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}