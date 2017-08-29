package net.dzikoysk.server;

public class KeepAliveThread extends Thread {

    public KeepAliveThread() {
        this.setName("UnknownPandaServer - KeepAliveThread");
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
