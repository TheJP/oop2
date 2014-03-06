import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class DeserializeTest {
	public static void main(String[] args) {
		try(ObjectInputStream os = new ObjectInputStream(new FileInputStream("Test.txt"))){
			Person2_N p = new Person2_N();
			byte[] b = new byte[p.datasetLength()];
			os.read(b);
			p.deserialize(b);
			p.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
