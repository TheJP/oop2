import java.io.*;

public class RA_Storage {

	public RA_Storage(String fileName) {
		File fname = new File(fileName);
		storedClass = new StringBuilder();
		try {
			if (!fname.exists()) {
				// ***** file doesn't exist: create new one. ********************
				dFile = new RandomAccessFile(fname, "rw");
				isConnected = true;
			} else {
				// ***** file exists already: validation ************************
				dFile = new RandomAccessFile(fname, "rw");
				if(dFile.readInt() == 0xAFFEBABA){
					recordLength = dFile.readInt();
					byte[] b = new byte[56];
					dFile.read(b, 0, 56);
					storedClass = new StringBuilder(new String(b).trim());
					hasClassName = true;
					isConnected = true;
				}
			}
		} catch (IOException e) {
			isConnected = false;
			System.out.println("I/O exception!!");
		}
	}

	public void disconnect() {
		if (isConnected) {
			try {
				dFile.close();
			} catch (IOException e) {
			}
			isConnected = false;
			hasClassName = false;
			recordLength = 0;
			storedClass = null;
		}
	}

	public void appendItem(NonStandardSerializable obj) {
		try {
			if (isConnected) {
//				dFile.seek(dFile.length()); // append record
//				byte[] t = obj.serialize();
//				dFile.write(t);
				writeItem(0, obj);
				if (!hasClassName) { // brand file if not done already
					dFile.seek(0);
					dFile.writeInt(0xAFFEBABA);
					dFile.writeInt(obj.datasetLength());
					recordLength = obj.datasetLength();
					String s = obj.getClass().getName();
					for (int i = 0; i < s.length(); ++i) {
						dFile.writeByte(s.charAt(i));
					}
					storedClass = new StringBuilder(s);
					hasClassName = true;
				}
			}
		} catch (IOException e) {
		}
	}

	public void writeItem(int index, NonStandardSerializable obj) {
		// pre-condition: obj must be a valid object
		try {
			dFile.seek(64 + (index * recordLength));
			dFile.write(obj.serialize());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readItem(int index, NonStandardSerializable obj) {
		// pre-condition: obj must be a valid object
		try {
			dFile.seek(64 + (index * recordLength));
			byte[] b = new byte[recordLength];
			dFile.read(b, 0, recordLength);
			obj.deserialize(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long itemCount() {
		if (!isConnected)
			return -1;
		else if (recordLength == 0)
			return 0;
		else {
			long s = 0;
			try {
				s = dFile.length();
			} catch (IOException e) {
			}
			return (s - 64) / recordLength;
		}
	}

	public int datasetSize() {
		return recordLength;
	}

	public String className() {
		return storedClass.toString();
	}

	public boolean isConnected() {
		return isConnected;
	}

	public boolean isBranded() {
		return hasClassName;
	}

	// ***** attributes
	// *************************************************************
	private int recordLength; // length of dataset
	private StringBuilder storedClass; // branded classname
	private boolean isConnected; // connection state
	private boolean hasClassName; // is branded
	private RandomAccessFile dFile; // file streeam

}
