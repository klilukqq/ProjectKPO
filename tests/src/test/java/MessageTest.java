import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import udp.Message;

public class MessageTest {

    private static Message msg;
    private static byte[] bytess = {2,1,2,3};

    @BeforeClass
    public static void messageDefaultCreation(){
        byte[] bytes = {1,2,3};
        msg = new Message((byte) 2,bytes);
    }

    @Test
    public void typeShouldNotBeNull(){
        Assert.assertNotNull(msg.getType());
    }

    @Test
    public void dataShouldNotBeNull(){
        Assert.assertNotNull(msg.getData());
    }

    @Test
    public void unPackMessageShouldHandleEmptyArray(){
        byte[] bytes = {};
        Assert.assertNull(Message.unPackMessage(bytes));
    }

    @Test
    public void unPackMessageShouldHandleNull(){
        Assert.assertNull(Message.unPackMessage(null));
    }

    @Test
    public void packMessageShouldHandleNull(){
        Assert.assertNull(Message.packMessage(null));
    }

    @Test
    public void packMessageShouldWorkWithCorrectParameter(){
        byte[] bytes = Message.packMessage(msg);
        byte[] dbytes = new byte[bytes.length - 1];
        for (int i = 0; i < dbytes.length; i++) {
            dbytes[i] = bytes[i+1];
        }
        Assert.assertEquals(bytes[0],msg.getType());
        Assert.assertArrayEquals(dbytes,msg.getData());
    }

    @Test
    public void unPackMessageShouldWorkWithCorrectParameter(){
        Message message = Message.unPackMessage(bytess);
        Assert.assertArrayEquals(msg.getData(),message.getData());
        Assert.assertEquals(msg.getType(),message.getType());
    }

}
