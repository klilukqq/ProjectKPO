package udp;

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;


public class UdpManager implements Runnable {

    public final static byte QUIT_MESSAGE = 0;
    public final static byte CONNECT_MESSAGE = 1;
    public final static byte APPLY_CONNECT_MESSAGE = 2;
    public final static byte ADD_PRIM_MESSAGE = 3;
    public final static byte REMOVE_PRIM_MESSAGE = 4;
    public final static byte ADD_POINT_MESSAGE = 5;
    public final static byte SET_COLOR_MESSAGE = 6;
    public final static byte SET_THIKNESS_MESSAGE = 7;
    
    public final static int MAX_SIZE = 512;
    public final static int SERVER_PORT = 8001;
    public final static int CLIENT_PORT = 8234;
    private static int num = 0;
    private DatagramSocket ds;
    private DatagramSocket ds1;
    private DatagramSocket ds2;
    private DatagramSocket ds3;
    private DatagramSocketListener listener;
    private boolean isServer;
    private boolean isConnected = false;
    
    private InetAddress addressTo;

    public UdpManager(DatagramSocketListener dsl) {
	this.listener = dsl;
    }

    public void create() {
	try {
	    String s = InetAddress.getLocalHost().getHostAddress();
 	    addressTo = InetAddress.getByName(s);//"localhost");
	    ds = new DatagramSocket(SERVER_PORT);
	    isServer = true;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    // КЛИЕНТ: "Запрос" на подключение клиента к серверу
    public void connectToServer(String addr) {
	try {
 	    addressTo = InetAddress.getByName(addr);
	    ds = new DatagramSocket(CLIENT_PORT, addressTo);
	    isServer = false;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}  	
	send(CONNECT_MESSAGE, null);
    }

    // СЕРВЕР: Подключение клиента к серверу
    private void connectClient(InetAddress addr) {
	addressTo = addr;
	// Отправка подтверждения клиенту
	send(APPLY_CONNECT_MESSAGE, null);
 	isConnected = true;
    }
    
    // КЛИЕНТ: Подтверждение, что клиент успешно подсоединен к серверу
    private void applyConnect() {
	isConnected = true;
    }

    public boolean isConnected() {
	return isConnected;
    }

    /////////////////////////////////////////////////////////
    public void run() {
	System.out.println(addressTo.toString());
	
	while (receive()) {
	}
	close();
    }

    public void close() {
	if (ds != null) {
	    // если выходим, оповещаем оппонента
	    if (isConnected) {
		send(QUIT_MESSAGE, null);
	    }

	    ds.close();
	    isConnected = false;
	}
    }

    /////////////////////////////////////////////////////////
    public void send(byte type, byte[] data) {
	byte[] mes = Message.packMessage(new Message(type, data));
	if (mes == null) return;
	// На какой порт?
	int port = (isServer) ? CLIENT_PORT : SERVER_PORT;
	DatagramPacket dp = new DatagramPacket(mes, mes.length,
		addressTo, port);
	try {
	    ds.send(dp);
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
    }

    /////////////////////////////////////////////////////////
    public synchronized boolean receive() {
	byte[] buf = new byte[MAX_SIZE];
	DatagramPacket dp = new DatagramPacket(buf, buf.length);
	try {
	    ds.receive(dp);
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
	Message mes = Message.unPackMessage(buf);
	byte type = mes.getType();
	switch (type) {
	    case QUIT_MESSAGE:
		return false;
	    case CONNECT_MESSAGE:
		connectClient(dp.getAddress());
		break;
	    case APPLY_CONNECT_MESSAGE:
		applyConnect();
		break;
	    case ADD_PRIM_MESSAGE:
	    case REMOVE_PRIM_MESSAGE:
	    case ADD_POINT_MESSAGE:
	    case SET_COLOR_MESSAGE:
	    case SET_THIKNESS_MESSAGE:
		listener.onReceiveData(type, mes.getData());
		break;
	}
	return true;
    }

    /////////////////////////////////////////////////////////
    public static byte[] intToBytes(int i) {
	return ByteBuffer.allocate(4).putInt(i).array();
    }

    public static byte[] intsCoordsToBytes(int[] ints) {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	DataOutputStream dos = new DataOutputStream(baos);
	try {
	    dos.writeInt(ints[0]);
	    dos.writeInt(ints[1]);
	    dos.flush();
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
	return baos.toByteArray();
    }

    /////////////////////////////////////////////////////////
    public static int bytesToInt(byte[] bytes) {
	return ByteBuffer.wrap(bytes).getInt();
    }

    public static int[] bytesCoordsToInts(byte[] bytes) {
	ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
	DataInputStream dis = new DataInputStream(bais);
	int x = 0, y = 0;
	try {
	    x = dis.readInt();
	    y = dis.readInt();
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
	return new int[] { x, y };
    }
}
