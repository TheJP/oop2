import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

// vtg - 2014-01-27 - OOP2 : Streams : Assignment 2

public class main_PersistentDoubles {

	private static final int INITIAL_SIZE = 10;
	private static final int DELTA = 20;

	private static double[] values = null;

	public static void main(String[] args) {
		int count = 0;
		double newValue = 0;

		count = read();
		
		// initialize program
		if(count <= 0) {values = new double[INITIAL_SIZE];}

		// acquire new data
		while (true) {
			System.out.print("value #" + count + " : ");
			try {
				newValue = Terminal.readDouble();
			} catch (NumberFormatException e) {
				save(count);
				System.out.println("program terminated");
				System.exit(0);
			}
			if (count == values.length) {
				enlargeArray();
			}
			values[count++] = newValue;
		}
	}

	private static int read() {
		int count = 0;
		File datFile = new File("doubles.dat");
		if(datFile.exists()){
			try(DataInputStream reader = new DataInputStream(new FileInputStream(datFile))){
				count = reader.readInt();
				values = new double[count + INITIAL_SIZE];
				for(int i = 0; i < count; i++){
					values[i] = reader.readDouble();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	private static void save(int count) {
		try (DataOutputStream writer = new DataOutputStream(
				new FileOutputStream("doubles.dat"))) {
			writer.writeInt(count);
			for (int i = 0; i < count; i++) {
				writer.writeDouble(values[i]);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private static void enlargeArray() {
		double[] temp = new double[values.length + DELTA];
		for (int i = 0; i < values.length; ++i) {
			temp[i] = values[i];
		}
		values = temp;
	}

}
