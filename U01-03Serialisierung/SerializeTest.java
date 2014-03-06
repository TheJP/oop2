import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Random;


public class SerializeTest {
	public static void main(String[] args) {
		Random r = new Random();
		Person2_N p = new Person2_N();
		p.setAddress("Address");
		p.setBirthdate((int)new Date().getTime());
		p.setFinances(5.5);
		p.setFirstName("firstName");
		p.setName("lastName");
		p.setFemale(r.nextBoolean());
		p.setGlasses(r.nextBoolean());
		p.setMarker(r.nextBoolean());
		p.setMarried(r.nextBoolean());
		p.show();
		try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Test.txt"))){
			os.write(p.serialize());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
