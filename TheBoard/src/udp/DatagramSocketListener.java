package udp;

public interface DatagramSocketListener {

    public abstract void onReceiveData(int type, byte[] data);
}
