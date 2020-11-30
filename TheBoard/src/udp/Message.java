package udp;

/**
 *
 * @author truebondar
 */
public class Message {
    byte type;
    byte[] data;

    public Message(byte type, byte[] data) {
	this.type = type;
	this.data = data;
    }
    
    /////////////////////////////////////////////////////////
    public static byte[] packMessage(Message mes) {
	if (mes == null) return null;
	if (mes.getData() == null)
	    return new byte[] { mes.getType() };
	
	int dataSize = mes.getData().length;
	byte[] res = new byte[dataSize + 1];
	res[0] = mes.getType();
	System.arraycopy(mes.getData(), 0, res, 1, dataSize);
	return res;
    }
    
    /////////////////////////////////////////////////////////
    public static Message unPackMessage(byte[] bytes) {
	if (bytes == null || bytes.length == 0) return null;
	
	byte type = bytes[0];
	int dataSize = bytes.length - 1;
	byte[] data = new byte[dataSize];
	System.arraycopy(bytes, 1, data, 0, dataSize);
	return new Message(type, data);
    }

    /////////////////////////////////////////////////////////
    public byte getType() {
	return type;
    }

    public byte[] getData() {
	return data;
    }

    public void setType(byte type) {
	this.type = type;
    }

    public void setData(byte[] data) {
	this.data = data;
    }
}
