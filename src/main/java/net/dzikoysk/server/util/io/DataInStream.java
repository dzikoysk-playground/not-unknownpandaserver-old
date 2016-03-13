package net.dzikoysk.server.util.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataInStream extends DataInputStream {

    public DataInStream(InputStream is) {
        super(is);
    }

    public int readableBytes() {
        try {
            return super.available();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int skipAndReadByte(int i) {
        try {
            return super.skipBytes(i);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getRead() {
        try {
            return super.read();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public byte getByte() {
        try {
            return super.readByte();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public short getShort() {
        try {
            return super.readShort();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getInt() {
        try {
            return super.readInt();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long getLong() {
        try {
            return super.readLong();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public float getFloat() {
        try {
            return super.readFloat();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getDouble() {
        try {
            return super.readDouble();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean getBoolean() {
        try {
            return super.readBoolean();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public char getChar() {
        try {
            return super.readChar();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getUTF() {
        try {
            return super.readUTF();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getUnsignedByte() {
        try {
            return super.readUnsignedByte();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getUnsignedShort() {
        try {
            return super.readUnsignedShort();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
