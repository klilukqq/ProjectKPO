import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import udp.DatagramSocketListener;
import udp.Message;
import udp.UdpManager;

public class UdpManagerTest {

    private static UdpManager udpManager;

    @BeforeClass
    public static void udpManagerDefaultCreation(){
        udpManager = new UdpManager(new DatagramSocketListener() {
            @Override
            public void onReceiveData(int type, byte[] data) {
                byte bytes1[] = new byte[data.length+1];
                bytes1[0] = (byte)type;
                for (int i = 1; i < bytes1.length; i++) {
                    bytes1[i] = data[i-1];
                }
                Message.unPackMessage(bytes1);
            }
        });
        udpManager.create();
    }

    @Test
    public void datagramSocketShouldNotBeNull(){
        Assert.assertNotNull(udpManager.getDs());
    }

    @Test
    public void listenerShouldNotBeNull(){
        Assert.assertNotNull(udpManager.getListener());
    }

    @Test
    public void isServerShouldNotBeNull(){
        Assert.assertNotNull(udpManager.isServer());
    }

    @Test
    public void addressToShouldNotBeNull(){
        Assert.assertNotNull(udpManager.getAddressTo());
    }

}
