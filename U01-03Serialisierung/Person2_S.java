import java.io.Serializable;

//******************************************************************************
//  OOP2 : Personal Registration                                          vtg  *
//         Type 2 for serialization & file operations                          *
// --------------------------------------------------------------------------- *
//  version 2    : 2008-02-27                                                  *
//  version 3    : 2010-03-12 : non interactive getters & setters              *
//  version 4    : 2014-01-28 : code styling, marker                           *
//******************************************************************************

public class Person2_S implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8140192813524813681L;

	public Person2_S() {
		lastName.setLength(32);
		firstName.setLength(16);
		address.setLength(32);
	}

	// ***** setter suite
	// ***********************************************************
	public void setName(String lastName) {
		this.lastName.replace(0, lastName.length(), lastName);
		this.lastName.setLength(32);
	}

	public void setFirstName(String firstName) {
		this.firstName.replace(0, firstName.length(), firstName);
		this.firstName.setLength(16);
	}

	public void setAddress(String address) {
		this.address.replace(0, address.length(), address);
		this.address.setLength(32);
	}

	public void setBirthdate(int date) {
		this.birthDate = date;
	}

	public void setFinances(double money) {
		this.account = (float) money;
	}

	public void setFemale(boolean isFemale) {
		this.isFemale = isFemale;
	}

	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}

	public void setGlasses(boolean wearsGlasses) {
		this.wearsGlasses = wearsGlasses;
	}

	public void setMarker(boolean value) {
		this.isMarked = value;
	}

	// ***** getter suite
	// ***********************************************************
	public int getBirthdate() {
		return birthDate;
	}

	public float getMoney() {
		return account;
	}

	public boolean isFemale() {
		return isFemale;
	}

	public boolean isMarried() {
		return isMarried;
	}

	public boolean wearsGlasses() {
		return wearsGlasses;
	}

	public boolean isMarked() {
		return isMarked;
	}

	public String getFirstName() {
		return firstName.toString().trim();
	}

	public String getLastName() {
		return lastName.toString().trim();
	}

	public String getAddress() {
		return address.toString().trim();
	}

	// ***** display
	// ****************************************************************
	public void show() {
		System.out.print("name         : ");
		System.out.println(lastName.toString().trim());
		System.out.print("first name   : ");
		System.out.println(firstName.toString().trim());
		System.out.print("birthdate    : ");
		System.out.println(birthDate);
		System.out.print("address      : ");
		System.out.println(address.toString().trim());
		System.out.print("account      : ");
		System.out.println(account);
		System.out.print("attributes   : ");
		System.out.print(isFemale ? "female " : "male ");
		System.out.print(isMarried ? "married " : "unmarried ");
		System.out.print(wearsGlasses ? "glasses " : "noGlasses ");
		System.out.print(isMarked ? "MARKED " : " ");
	}

	// ***** attributes &
	// constants**************************************************
	private StringBuilder lastName = new StringBuilder();
	private StringBuilder firstName = new StringBuilder();
	private StringBuilder address = new StringBuilder();
	private int birthDate;
	private float account;
	private boolean isFemale;
	private boolean isMarried;
	private boolean wearsGlasses;
	private boolean isMarked;
}