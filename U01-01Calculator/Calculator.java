//******************************************************************************
import terminal.VT;
//  Stack-based calculator                                                vtg  *
// --------------------------------------------------------------------------- *
//  version 1    : 2007-09                                                     *
//  version 2    : 2008-10 : data entry improved                               *
//  version 3    : 2009-09 : refactored as standalone class                    *
//  version 3.1  : 2009-10 : errors fixed: dropStack() & crearing areas        *
//  version 3.2  : 2010-11 : fixed: dropStack() forgotten if input error       *
//  version 4.0  : 2014-01 : relocatable main window & stack readIn/out        *
// --------------------------------------------------------------------------- *
//  used libraries : Vterm 1.4                                                 *
//  used classes   : Fraction                                                  *
//******************************************************************************
import terminal.VTerm;

public class Calculator{
  
  public Calculator(){
    stack = new Fraction[4];
    out = VTerm.getInstance(20, 80, " RPN Stack Calculator 4.0", VT.CS_SMALL);
    selection = ' ';
    for (int i=0; i<stack.length; ++i){
      stack[i] = new Fraction();
    }
    out.setCursor(false);
  }

//***** public interface *******************************************************
  public void run(){
    int temp;
    displayMenu();
    displayStack();
    while (selection != 'q'){
      selection = out.readChar();
      switch(selection){
      case 'q':
      case 'Q':
        selection = 'q';
        break;
      case 'e':
      case 'E':
        enterData();
        break;
      case 'r':
      case 'R':
        if (stack[0].getNumerator() != 0){
          temp = stack[0].getNumerator();
          stack[0].setNumerator(stack[0].getDenominator());
          stack[0].setDenominator(temp);
          displayStack();}
        else{
          displayMessage("ERROR: zero divide");
        }
        break;
      case 'c':
      case 'C':
        for (int i=0; i<stack.length; ++i){
          stack[i].setFraction(0, 1);
        }
        displayStack();
        break;
      case '+':
        stack[1].add(stack[0], stack[0]);
        dropStack();
        displayStack();
        break;
      case '-':
        stack[1].sub(stack[0], stack[0]);
        dropStack();
        displayStack();
        break;
      case '*':
        stack[1].mul(stack[0], stack[0]);
        dropStack();
        displayStack();
        break;
      case '/':
        if (stack[0].getNumerator() != 0){
          stack[1].div(stack[0], stack[0]);
          dropStack();
          displayStack();
        }else{
          displayMessage("ERROR: zero divide");
        }
        break;
      default:
        displayMessage("ERROR: invalid command");
        break;
      }
    }
  }
  
  // version 4.0 : main window relocation & calculator's initialization
  public void setPosition(int x, int y){
    out.setLocation(x, y);
  }
  
  public int[] getPosition(){
    int[] coord = new int[2];
    coord[0] = out.getLocation().x;
    coord[1] = out.getLocation().y;
    return coord;
  }
  
  public void setFraction(int nr, Fraction f){
    if (nr<0 || nr>=stack.length)
      throw new RuntimeException("calculator : invalid stack slot in initialization");
    stack[nr].setFraction(f.getNumerator(), f.getDenominator());
  }
  
  public Fraction getFraction(int nr){
    if (nr<0 || nr>=stack.length)
      throw new RuntimeException("calculator : invalid stack slot in initialization");
    return new Fraction(stack[nr]);
  }

//***** internal methods *******************************************************
  private void displayStack(){
    out.clearArea(3,10, 9,30);
    out.setColor(VT.WHITE);
    for (int i=0; i<stack.length; ++i){
      out.setCursor( 3 + 2*i,10);
      out.print(String.valueOf(stack[stack.length-i-1].getNumerator()));
      out.print("/");
      out.print(String.valueOf(stack[stack.length-i-1].getDenominator()));
    }
  }
  
  private void displayMenu(){
    out.setColor(VT.GREEN);
    out.setCursor( 6, 50); out.print("E : enter new fraction");
    
    out.setCursor( 8, 50); out.print("+ : add");
    out.setCursor( 9, 50); out.print("- : subtract");
    out.setCursor(10, 50); out.print("* : multiply");
    out.setCursor(11, 50); out.print("/ : divide");
    out.setCursor(12, 50); out.print("R : reciprocal");
    out.setCursor(14, 50); out.print("C : clear stack");
    out.setCursor(15, 50); out.print("Q : leave calculator");
  }
  
  private void displayMessage(String m){
    out.setCursor(19, 50);
    out.setColor(VT.RED);
    out.print(m);
    out.delay(667);
    out.clearArea(19,50,19,79);
  }
  
  private void liftStack(){
    for (int i=stack.length-1; i>0; --i){
      stack[i].setNumerator(stack[i-1].getNumerator());
      stack[i].setDenominator(stack[i-1].getDenominator());
    }
  }
  
  private void dropStack(){
    stack[1].setNumerator(stack[2].getNumerator());
    stack[1].setDenominator(stack[2].getDenominator());
    stack[2].setNumerator(stack[3].getNumerator());
    stack[2].setDenominator(stack[3].getDenominator());
  }
  
  private void enterData(){
    liftStack();
    out.clearArea(18, 5,19,20);
    out.setCursor(true);
    out.setColor(VT.ORANGE);
    out.setCursor(18,  5);
    out.print("Zähler: ");
    try{
      stack[0].setNumerator(Integer.parseInt(out.readString(9)));
      out.setCursor(19,  5);
      out.print("Nenner: ");
      stack[0].setDenominator(Integer.parseInt(out.readString(9)));
      displayStack();}
    catch(NumberFormatException e){
      displayMessage("ERROR: non parseable input");
      out.setCursor(false);
      dropStack();
    }
    out.setCursor(false);
    out.clearArea(18, 5,19,20);
  }

//***** attributes *************************************************************
  private Fraction[] stack;  // calculator's stack
  private VTerm out;         // output console
  private char selection;    // menu m_selection variable

}
