package net.dzikoysk.server.connection.protocol;

public enum ProtocolVersion {

	R_1_8("1.8.x", 47),
	R_1_7_6("1.7.6 - 1.7.10", 5),
	R_1_7("1.7 - 1.7.5", 4);

	private final String name;
	private final int number;

	private ProtocolVersion(String name, int number){
		this.name = name;
		this.number = number;
	}

	public String getName(){
		return this.name;
	}

	public int getNumber(){
		return this.number;
	}

	public static ProtocolVersion getProtocolVersion(int number){
		for(ProtocolVersion version : values())
			if(version.getNumber() == number) return version;
		return null;
	}

}
