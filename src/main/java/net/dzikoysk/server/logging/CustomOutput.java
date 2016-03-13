package net.dzikoysk.server.logging;

import java.io.OutputStream;

public class CustomOutput extends OutputStream {

	private final Logger logger;
	private final StringBuilder stringBuilder;

	protected CustomOutput(Logger logger){
		this.logger = logger;
		this.stringBuilder = new StringBuilder();
	}

	@Override
	public void write(int i){
		stringBuilder.append((char) i);
		if(stringBuilder.toString().endsWith(System.lineSeparator())){
			logger.log(Level.INFO, stringBuilder.toString(), true);
			stringBuilder.setLength(0);
		}
	}

}
