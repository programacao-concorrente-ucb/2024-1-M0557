package socket_multicast_publisher;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class Publisher extends Thread {
    private DatagramSocket socket;
    private InetAddress group;
    private byte[] buf;
    public static void main(String args[]) {
        (new Publisher()).start();
    }
    public void run() {
        try {
        	int i = 1;

        	while(true) {
                socket = new DatagramSocket();

                String multicastMessage = "Hello world " + i;
                i++;
                group = InetAddress.getByName("230.0.0.0");
                buf = multicastMessage.getBytes();
                DatagramPacket packet
                    = new DatagramPacket(buf, buf.length, group, 4446);
                socket.send(packet);
                socket.close();
                try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
