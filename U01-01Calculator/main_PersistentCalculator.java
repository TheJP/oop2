import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

//******************************************************************************
//  RPN Calculator with persistent data (Streams)                         vtg  *
// --------------------------------------------------------------------------- *
//  version 1   : 2007-09                                                      *
//  version 2   : 2008-10 : data entry improved                                *
//  version 3   : 2009-09 : user interface outsourced (Calculator)             *
//  version 3.1 : 2010-06 : minor changes                                      *
//  version 3.2 : 2010-09 : minor changes                                      *
//  version 4.0 : 2014-01 : recycled as Stream excercise                       *
// --------------------------------------------------------------------------- *
//  Used libraries/classes : VTerm 1.4, Fraction                               *
//******************************************************************************


public class main_PersistentCalculator {

  public static void main(String[] args) {
    Calculator c = new Calculator();
    File iniFile = new File("calculator.ini");
    if(iniFile.exists()){
		try(BufferedReader reader = new BufferedReader(new FileReader(iniFile))){
			Map<String, Integer> iniValues = new HashMap<>();
			String line = null;
			do {
				line = reader.readLine();
				if(line != null){
					String[] vals = line.split("=");
					if(vals.length >= 2){
						try {
							iniValues.put(vals[0].trim(), Integer.valueOf(vals[1].trim()));
						} catch(NumberFormatException e){}
					}
				}
			} while(line != null);
			if(iniValues.containsKey("posx") && iniValues.containsKey("posy")){
				System.out.println(iniValues.get("posx") + ", " + iniValues.get("posy"));
				c.setPosition(iniValues.get("posx"), iniValues.get("posy"));
			}
			if(iniValues.containsKey("nx") && iniValues.containsKey("dx")){
				c.setFraction(0, new Fraction(iniValues.get("nx"), iniValues.get("dx")));
			}
		} catch (Exception e) { }
    }
    c.run();
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(iniFile, false))){
    	writer.write("posx=" + c.getPosition()[0]); writer.newLine();
    	writer.write("posy="+ c.getPosition()[1]); writer.newLine();
    	writer.write("nx=" + c.getFraction(0).getNumerator()); writer.newLine();
    	writer.write("dx=" + c.getFraction(0).getDenominator()); writer.newLine();
    } catch (Exception e) { }
    System.exit(0);
  }
  
}
