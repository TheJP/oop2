//******************************************************************************
//  OOP-1   Excercise 04                         rational numbers (fractions)  *
// --------------------------------------------------------------------------- *
//  version 2.0 - 20081024 - vtg                                               *
//  version 2.1 - 20121105 - other exceptions thrown                           *
//  version 2.2 - 20130911 - code styling, toString()                          *
//******************************************************************************

public class Fraction {
  
  
//***** constructors & finalizer ***********************************************
  public Fraction(){                                 // default-constructor
    numerator = 0;
    denominator = 1;
    ++c_instanceCount;
  }
  
  public Fraction(int numerator, int denominator){   // user defined constructor
    this.numerator = numerator;
    this.denominator = denominator;
    ++c_instanceCount;
  }
  
  public Fraction(Fraction f){                       // copy constructor (deep-copy)
    numerator = f.numerator;
    denominator = f.denominator;
    ++c_instanceCount;
  }
  
  public void finalize(){
    --c_instanceCount;
  }
  
//***** get & set attributes ***************************************************
  public int getNumerator(){
    return numerator;
  }
  
  public int getDenominator(){
    return denominator;
  }
  
  public void setNumerator(int v){
    numerator = v;
  }
  
  public void setDenominator(int v){
    if (v==0){
      throw new IllegalArgumentException();
    }
    denominator = v;
  }
  
  public void setFraction(int n, int d){
    numerator = n;
    if (d==0){
      throw new IllegalArgumentException();
    }
    denominator = d;
  }
  
  public void changeSign(){
    numerator = -numerator;
  }
  
  public static int getCount(){
    return c_instanceCount;
  }
  
  // this additional overridden method ensures System.out.print() functionality
  //  (see chapter "Inheritance"
  @Override
  public String toString(){
    return numerator + "/" + denominator;
  }
  
//***** math operations ********************************************************
  
  public Fraction add(Fraction s2, Fraction result){
    int rn, rd;
    int f;
    rn = numerator * s2.denominator + denominator * s2.numerator;
    rd = denominator * s2.denominator;
    f = ggT(rn, rd);
    if (result==null){
      return new Fraction (rn/f, rd/f);}
    else{
      result.numerator = rn/f;
      result.denominator = rd/f;
      return null;
    }
  }
  
  public Fraction sub(Fraction s2, Fraction result){
    int rn, rd;
    int f;
    rn = numerator * s2.denominator - denominator * s2.numerator;
    rd = denominator * s2.denominator;
    f = ggT(rn, rd);
    if (result==null){
      return new Fraction (rn/f, rd/f);}
    else{
      result.numerator = rn/f;
      result.denominator = rd/f;
      return null;
    }
  }
  
  public Fraction mul(Fraction s2, Fraction result){
    int rn, rd;
    int f;
    rn = numerator * s2.numerator;
    rd = denominator * s2.denominator;
    f = ggT(rn, rd);
    if (result==null){
      return new Fraction (rn/f, rd/f);}
    else{
      result.numerator = rn/f;
      result.denominator = rd/f;
      return null;
    }
  }
  
  public Fraction div(Fraction s2, Fraction result){
    int rn, rd;
    int f;
    rn = numerator * s2.denominator;
    rd = denominator * s2.numerator;
    f = ggT(rn, rd);
    if (result==null){
      return new Fraction (rn/f, rd/f);}
    else{
      result.numerator = rn/f;
      result.denominator = rd/f;
      return null;
    }
  }
  
//***** auxiliary functions ****************************************************
  
  private static int ggT(int a, int b){
    int r = 1;
    while (r!=0){
      r = a%b;
      a = b;
      b = r;
    }
    return a;
  }
  
//***** attributes *************************************************************
  private int numerator;
  private int denominator;
  private static int c_instanceCount = 0;

}
