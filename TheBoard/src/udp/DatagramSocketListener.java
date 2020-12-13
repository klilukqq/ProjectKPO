package udp;

/**
 *
 * @author truebondar
 */
public interface DatagramSocketListener {
    
    public abstract void onReceiveData(int type, byte[] data);
}
