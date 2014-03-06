//******************************************************************************
//  Excercise OOP2.2 : dataset-oriented storing of data                        *
//                     (test suite for RA-Storage)                             *
// --------------------------------------------------------------------------- * 
//  version 1b  :  2008-03-01                                                  *
//  version 2   :  2011-02-01  :  VTerm edition                                *
//  version 2.1 :  2014-01-28  :  adapted to Person2_N, improved UI            *    
// --------------------------------------------------------------------------- *                            
//  depends on:  Person2_N,  RA_Storage,  VTerm 1.4                            *
//******************************************************************************

import terminal.*;

public class main_OOP2_Excercise_2 {
	public static void main(String[] args) {
		c_out = VTerm.getInstance(HEIGHT, WIDTH,
				"OOP2 - Personal File with RA_Storage", VT.CS_SMALL);
		c_out.setCursor(false);
		char selection = ' ';
		showBaseScreen();
		showMenu(true);
		showStatus();
		while (selection != 'Q') {
			selection = Character.toUpperCase((c_out.readChar()));
			c_out.clearArea(2, 0, 2, WIDTH - 30);
			switch (selection) {
			case 'A': // ***** append new record *****
				if (c_file == null || !c_file.isConnected()) {
					showError("no stream connected");
					break;
				}
				readRecordFromKbd(c_currRec, 12, 5);
				c_file.appendItem(c_currRec);
				c_out.delay(750);
				c_out.clearArea(12, 5, 20, 50);
				break;
			case 'D': // ***** display single record *****
				if (c_file == null || !c_file.isConnected()) {
					showError("no stream connected");
					break;
				}
				c_file.readItem(c_currentRecNo, c_currRec);
				c_out.setCnC(18, 5, VT.WHITE);
				c_out.print("Record " + (c_currentRecNo + 1) + " of "
						+ c_file.itemCount());
				displayRecord(c_currRec, 12, 5);
				c_out.setCnC(5, 40, VT.ROSE);
				c_out.print("use ");
				c_out.print(VT.SY_ARROW_LEFT);
				c_out.print(" and ");
				c_out.print(VT.SY_ARROW_RIGHT);
				c_out.print(" to navigate");
				c_out.setCursor(6, 40);
				c_out.print("press ESC to exit record display");
				int key = 0;
				while (key != VT.K_ESCAPE) {
					key = c_out.readKey();
					c_out.clearArea(12, 5, 18, 70);
					if (key == VT.K_RIGHT
							&& c_currentRecNo < c_file.itemCount() - 1)
						++c_currentRecNo;
					if (key == VT.K_LEFT && c_currentRecNo > 0)
						--c_currentRecNo;
					c_file.readItem(c_currentRecNo, c_currRec);
					c_out.setCnC(18, 5, VT.WHITE);
					c_out.print("Record " + (c_currentRecNo + 1) + " of "
							+ c_file.itemCount());
					displayRecord(c_currRec, 12, 5);
				}
				c_out.clearArea(5, 5, 18, 79);
				break;
			case 'O': // ***** open file *****
				if (c_file != null)
					c_file.disconnect();
				c_out.setCnC(2, 3, VT.YELLOW);
				c_out.print("?? Filename: ");
				c_out.setColor(VT.WHITE);
				c_fileName = c_out.readString(35);
				c_file = new RA_Storage(c_fileName);
				c_currentRecNo = 0;
				showStatus();
				break;
			case 'Q': // ***** quit application *****
				if (c_file != null)
					c_file.disconnect();
				c_out.readyToExit(false);
				break;
			default:
				showError("Invalid Selection");
			}
			showStatus();
			c_out.clearArea(2, 0, 2, WIDTH - 30);
		}

	}

	// ***** auxiliaries ************************************************************
	private static void displayRecord(Person2_N p, int y, int x) {
		final short LABEL = VT.GREEN;
		final short VALUE = VT.MINT;
		c_out.setCnC(y + 0, x, LABEL);
		c_out.print("Name       : ");
		c_out.setColor(VALUE);
		c_out.print(p.getFirstName() + " " + p.getLastName());
		c_out.setCnC(y + 1, x, LABEL);
		c_out.print("Address    : ");
		c_out.setColor(VALUE);
		c_out.print(p.getAddress());
		c_out.setCnC(y + 2, x, LABEL);
		c_out.print("Birth      : ");
		c_out.setColor(VALUE);
		c_out.print("" + p.getBirthdate() / 10000 + "." + p.getBirthdate()
				/ 100 % 100 + "." + p.getBirthdate() % 100);
		c_out.setCnC(y + 3, x, LABEL);
		c_out.print("Kröten     : ");
		c_out.setColor(VALUE);
		c_out.print("" + p.getMoney());
		c_out.setCnC(y + 4, x, LABEL);
		c_out.print("Attributes : ");
		c_out.setColor(VALUE);
		c_out.print((p.isFemale() ? "female" : "male") + "  ");
		c_out.print((p.isMarried() ? "married" : "unmarried") + "  ");
		c_out.print((p.wearsGlasses() ? "glasses" : "noGlasses") + "  ");
		c_out.print((p.isMarked() ? "MARKED" : " ") + "  ");
	}

	private static void readRecordFromKbd(Person2_N p, int y, int x) {
		final short LABEL = VT.GREEN;
		final short VALUE = VT.MAUVE;
		c_out.setCnC(y + 0, x, LABEL);
		c_out.print("First Name : ");
		c_out.setColor(VALUE);
		p.setFirstName(c_out.readString(16));
		c_out.setCnC(y + 1, x, LABEL);
		c_out.print("Last Name  : ");
		c_out.setColor(VALUE);
		p.setName(c_out.readString(32));
		c_out.setCnC(y + 2, x, LABEL);
		c_out.print("Address    : ");
		c_out.setColor(VALUE);
		p.setAddress(c_out.readString(32));
		c_out.setCnC(y + 3, x, LABEL);
		c_out.print("Birth Date : ");
		c_out.setColor(VALUE);
		p.setBirthdate(Integer.parseInt(c_out.readString(8)));
		c_out.setCnC(y + 4, x, LABEL);
		c_out.print("Kröten     : ");
		c_out.setColor(VALUE);
		p.setFinances(Float.parseFloat(c_out.readString(8)));
		c_out.setCnC(y + 5, x, LABEL);
		c_out.print("Female?    : ");
		c_out.setColor(VALUE);
		p.setFemale(c_out.readBoolean());
		c_out.print(Boolean.toString(p.isFemale()));
		c_out.setCnC(y + 6, x, LABEL);
		c_out.print("Married?   : ");
		c_out.setColor(VALUE);
		p.setMarried(c_out.readBoolean());
		c_out.print(Boolean.toString(p.isMarried()));
		c_out.setCnC(y + 7, x, LABEL);
		c_out.print("Glasses?   : ");
		c_out.setColor(VALUE);
		p.setGlasses(c_out.readBoolean());
		c_out.print(Boolean.toString(p.wearsGlasses()));
		c_out.setCnC(y + 8, x, LABEL);
		c_out.print("Marker?    : ");
		c_out.setColor(VALUE);
		p.setMarker(c_out.readBoolean());
		c_out.print(Boolean.toString(p.isMarked()));
	}

	private static void showBaseScreen() {
		c_out.setColor(VT.BLUE); // menu separator
		c_out.printHLine(HEIGHT - 2, 0, WIDTH - 1, false);
		c_out.setCnC(HEIGHT - 6, 1, VT.BLUE); // status box & Labels
		c_out.print("status");
		c_out.setColor(VT.SILVER);
		c_out.printFrame(HEIGHT - 7, 8, HEIGHT - 4, WIDTH - 9);
		c_out.printVLine(WIDTH / 2, HEIGHT - 7, HEIGHT - 4, true);
		c_out.setColor(VT.WHITE);
		c_out.setCursor(HEIGHT - 6, 10);
		c_out.print("RA-Stor :");
		c_out.setCursor(HEIGHT - 5, 10);
		c_out.print("   File :");
		c_out.setCursor(HEIGHT - 6, WIDTH / 2 + 2);
		c_out.print("Brand :");
		c_out.setCursor(HEIGHT - 5, WIDTH / 2 + 2);
		c_out.print(" Size :");
	}

	private static void showStatus() {
		c_out.clearArea(HEIGHT - 6, 20, HEIGHT - 5, WIDTH / 2 - 1);
		c_out.clearArea(HEIGHT - 6, WIDTH / 2 + 10, HEIGHT - 5, WIDTH - 10);
		if (c_file == null) {
			c_out.setCnC(HEIGHT - 6, 20, VT.GREY);
			c_out.print("undefined");
			c_out.setCnC(HEIGHT - 5, 20, VT.GREY);
			c_out.print("undefined");
			c_out.setCnC(HEIGHT - 6, WIDTH / 2 + 10, VT.GREY);
			c_out.print("undefined");
			c_out.setCnC(HEIGHT - 5, WIDTH / 2 + 10, VT.GREY);
			c_out.print("undefined");
		} else if (!c_file.isConnected()) {
			c_out.setCnC(HEIGHT - 6, 20, VT.RED);
			c_out.print("failed");
			c_out.setCnC(HEIGHT - 5, 20, VT.YELLOW);
			c_out.print(c_fileName);
			c_out.setCnC(HEIGHT - 6, WIDTH / 2 + 10, VT.GREY);
			c_out.print("undefined");
			c_out.setCnC(HEIGHT - 5, WIDTH / 2 + 10, VT.GREY);
			c_out.print("undefined");
		} else if (!c_file.isBranded()) {
			c_out.setCnC(HEIGHT - 6, 20, VT.GREEN);
			c_out.print("connected");
			c_out.setCnC(HEIGHT - 5, 20, VT.GREEN);
			c_out.print(c_fileName);
			c_out.setCnC(HEIGHT - 6, WIDTH / 2 + 10, VT.YELLOW);
			c_out.print("new file");
			c_out.setCnC(HEIGHT - 5, WIDTH / 2 + 10, VT.YELLOW);
			c_out.print("empty");
		} else {
			c_out.setCnC(HEIGHT - 6, 20, VT.GREEN);
			c_out.print("connected");
			c_out.setCnC(HEIGHT - 5, 20, VT.GREEN);
			c_out.print(c_fileName);
			c_out.setCnC(HEIGHT - 6, WIDTH / 2 + 10, VT.GREEN);
			c_out.print(c_file.className());
			c_out.setCnC(HEIGHT - 5, WIDTH / 2 + 10, VT.GREEN);
			c_out.print("" + c_file.itemCount());
		}
	}

	private static void showMenu(boolean enabled) {
		c_out.setCnC(HEIGHT - 1, 1, VT.WHITE);
		for (int i = 0; i < MENUDEF.length(); ++i)
			if (MENUDEF.charAt(i) == ':')
				c_out.setColor(VT.RED);
			else {
				c_out.print(MENUDEF.charAt(i));
				c_out.setColor(VT.WHITE);
			}
		if (!enabled)
			c_out.colorizeArea(HEIGHT - 1, 0, HEIGHT - 1, WIDTH - 1, VT.GREY);
	}

	private static void showError(String e) {
		c_out.setCnC(2, 3, VT.RED);
		c_out.print(e);
		c_out.delay(750);
	}

	// ***** globals ****************************************************************
	private static VTerm c_out;
	private static Person2_N c_currRec = new Person2_N();
	private static int c_currentRecNo = -1;
	private static RA_Storage c_file = null;
	private static String c_fileName = " ";

	// ***** constants **************************************************************
	private final static int HEIGHT = 30; // console dimension
	private final static int WIDTH = 80; // console dimension
	private final static String MENUDEF = new String(
			":Append   :Display   :Open   :Quit");

}