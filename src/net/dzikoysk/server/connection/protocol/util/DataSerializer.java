package net.dzikoysk.server.connection.protocol.util;

import net.dzikoysk.server.connection.PlayerConnection;
import net.dzikoysk.server.util.io.DataInStream;
import net.dzikoysk.server.util.io.DataOutStream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataSerializer {
	
	private final PlayerConnection connection;
	private DataInputStream inputStream;
	private DataInStream in;
	private DataOutStream out;
	
	public DataSerializer(PlayerConnection pc, DataOutputStream output, DataInputStream input){
		this.out = new DataOutStream(output);
		this.in = new DataInStream(input);
		this.inputStream = input;
		this.connection = pc;
	}
	
	public DataSerializer(PlayerConnection pc, DataOutputStream output){
		this.out = new DataOutStream(output);
		this.connection = pc;
	}
	
	public DataSerializer(PlayerConnection pc, DataInputStream input){
		this.in = new DataInStream(input);
		this.inputStream = input;
		this.connection = pc;
	}

	public DataSerializer partOfData(int length){
		byte[] data = new byte[length];
		for(int i = 0; i < length; i++) data[i] = readByte();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
		DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
		DataSerializer serializer = new DataSerializer(connection, dataInputStream);
		try {
			byteArrayInputStream.close();
			dataInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return serializer;
	}

	public DataSerializer transfer(){
		return partOfData(available());
	}
	
	public int available(){
		return this.in.readableBytes();
	}

	public int skipBytes(int i){
		return this.in.skipAndReadByte(i);
	}
	
	public void write(byte[] bs){
		this.out.append(bs);
	}
	
	public void writeByte(byte b){
		this.out.appendByte(b);
	}
	
	public void writeByte(int i){
		this.out.appendByte(i);
	}
	
	public void writeShort(int s){
		this.out.appendShort(s);
	}
	
	public void writeInt(int i){
		this.out.appendInt(i);
	}
	
	public void writeLong(long l){
		this.out.appendLong(l);
	}
	
	public void writeFloat(float f){
		this.out.appendFloat(f);
	}
	
	public void writeDouble(double d){
		this.out.appendDouble(d);
	}
	
	public void writeBoolean(boolean b){
		this.out.appendBoolean(b);
	}
	
	public void writeChars(String s){
		this.out.appendChars(s);
	}
	
	public void writeUTF(String s){
		this.out.appendUTF(s);
	}
	
	public void writeBytes(String s){
		this.out.appendBytes(s);
	}
	
	public int read(){
		return this.in.getRead();
	}

	public byte readByte(){
		return this.in.getByte();
	}
	
	public short readShort(){
		return this.in.getShort();
	}
	
	public int readInt(){
		return this.in.getInt();
	}
	
	public long readLong(){
		return this.in.getLong();
	}
	
	public float readFloat(){
		return this.in.getFloat();
	}
	
	public double readDouble(){
		return this.in.getDouble();
	}
	
	public boolean readBoolean(){
		return this.in.getBoolean();
	}
	
	public String readUTF(){
		return this.in.getUTF();
	}
	
	public int readUnsignedByte(){
		return this.in.getUnsignedByte();
	}
	
	public int readUnsignedShort(){
		return this.in.getUnsignedShort();
	}
	
	public DataInStream getDataInStream(){
		return this.in;
	}
	
	public DataOutStream getDataOutStream(){
		return this.out;
	}

	public DataInputStream getInputStream(){
		return this.inputStream;
	}

	public PlayerConnection getPlayerConnection(){
		return this.connection;
	}
}
