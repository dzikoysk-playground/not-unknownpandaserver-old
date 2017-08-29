package net.dzikoysk.server.util.io;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DataOutStream extends DataOutputStream {

    private ByteArrayOutputStream out;

    public DataOutStream(OutputStream out) {
        super(out);
    }

    public DataOutStream(OutputStream out, ByteArrayOutputStream a) {
        super(out);
        this.out = a;
    }

    public void append(byte[] bs) {
        try {
            super.write(bs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void append(int i) {
        try {
            super.write(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendByte(byte b) {
        try {
            super.writeByte(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendByte(int i) {
        try {
            super.writeByte(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendShort(int s) {
        try {
            super.writeShort(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendInt(int i) {
        try {
            super.writeInt(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendLong(long l) {
        try {
            super.writeLong(l);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendFloat(float f) {
        try {
            super.writeFloat(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendDouble(double d) {
        try {
            super.writeDouble(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendBoolean(boolean b) {
        try {
            super.writeBoolean(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendChar(char c) {
        try {
            super.writeChar(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendChars(String s) {
        try {
            super.writeChars(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendUTF(String s) {
        try {
            super.writeUTF(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendBytes(String s) {
        try {
            super.writeBytes(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ByteArrayOutputStream getByteArrayOutputStream() {
        return this.out;
    }
}
