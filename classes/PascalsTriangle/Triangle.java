import java.math.BigInteger;
import java.util.ArrayList;

public class Triangle{

  private String name;
  private int charCount;
  private int rows; 

  //2D ArrayList of BigIntegers for triangle
  private ArrayList<ArrayList<BigInteger>> triangle;

  //string spacing information
  private int spacing;
  private String space;
  
  public Triangle(){

    this.rows = 0;
    this.spacing = 1;
    this.space = " ";
    this.name = "triangle";

    //declares data
    this.triangle = new ArrayList<ArrayList<BigInteger>>();

  }

  public Triangle(int r, int s, String sp, String n){

    this.spacing = s;
    this.space = "";
    this.name = n;

    //creates spacing
    for(int i = 0; i<this.spacing; i++){
      this.space+=sp;
    }

    //declares data
    this.triangle = new ArrayList<ArrayList<BigInteger>>();
    
    //builds data
    for(int i = 0; i<r; i++){
      this.buildNewRow();
    }

  }

  //creates the next row for the triangle
  public void buildNewRow(){
    //adds new line of data
    this.triangle.add(new ArrayList<BigInteger>());
    this.rows++;

    //fills new line with placeholder data
    for(int i = 0; i < this.rows; i++){
      this.triangle.get(this.rows-1).add(new BigInteger("1"));
    }

    //assigns data the correct values
    if(this.rows>1){
      //iterates through each number of working row 
      for(int num = 1; num<this.rows-1; num++){
        //Iterates through each number of the previous row
        for(int num2 = 0; num2<this.rows-2; num2++){
          this.triangle.get(rows-1).set(num2+1, triangle.get(rows-2).get(num2).add(triangle.get(rows-2).get(num2+1)));
        }
      }
    }
    this.charCount = -1;
  }

  //returns row r in string format
  public String returnRow(int r){
    
    String temp = "";
    //iterates through each number of a row and adds it to temp
    for(int i = 0; i < r-1; i++){
      temp+=this.triangle.get(r-2).get(i)+space;
    }
      return temp;
  }

  //returns number n of row r as a string
  public String returnItem(int r, int n){
    return this.triangle.get(r-1).get(n-1).toString();
  }

  //returns triangle in string format
  public String toString(){
    String temp = "";
    
    String temp2 = "";

    //adds each row as a string by iterating through each row of the triangle
    for(int i = 0; i <this.rows+2; i++){
      temp = "";
      for(int i2 = dif(this.rows,i-1)/2-1 ; i2>=0; i2--){
        temp+=" ";
      }
      temp2+=temp+this.returnRow(i)+"\n";
    }
    return(temp2);
  }

  //Fractal version of returnRow; odd numbers are symbols, even numbers are spaces
  public String fractalRow(int r){
    
    String temp = "";
    //iterates through each number of a row 
    for(int i = 0; i < r-1; i++){
      if(this.triangle.get(r-2).get(i).mod(new BigInteger("2")).compareTo(new BigInteger("1"))==-1){
        temp+="  ";
      } else {
        temp+="▲ ";
      }
    }
    return temp;
  }

  //prints a fractal based on filtering out even numbers, and displaying a symbol instead of the odd numbers
  public String toFractal(){
    String temp = "";
    
    String temp2 = "";

    //adds each row as a string by iterating through each row of the triangle
    //fractal version
    for(int i = 0; i <this.rows+2; i++){
      temp = "";
      for(int i2 = (((rows*2)-(i-1))*2)/2-1 ; i2>=0; i2--){
        temp+=" ";
      }
      temp2+=temp+this.fractalRow(i)+"\n";
    }
    return temp2;
  }

  //returns the number of rows
  public String rows(){
    return("There are "+rows+" rows in "+this.name);
  }

  //returns the amount of characters in a row
  public int lengthOfRow(int r){

    int temp = ((r)*this.spacing)-1;

    //iterates through row r
    for(int i = 0; i < r; i++){
      temp+=this.triangle.get(r-1).get(i).toString().length();
    }
    return(temp);

  }

  //returns the amount of characters including spacing in the triangle if calculated
  public int returnCharCount(){
    return charCount;
  }

  //calculates the amount of characters including spacing of every row of the triangle
  public void calculateCharCount(){

    int temp = 0;

    //iterates through every row
    for(int i = 0; i<this.rows; i++){
      temp+=this.lengthOfRow(i);
    }

    this.charCount = temp;

  }

  //returns difference between the length of two rows
  public int dif(int a, int b){
    return(this.lengthOfRow(a)-this.lengthOfRow(b));
  }

  //returns the biggest number of the triangle
  public BigInteger returnBiggestNumber(){

    BigInteger number = new BigInteger("1");

    //iterates through rows and items of triangle comparing each consecutive number to find biggest number
    for(ArrayList<BigInteger> row: this.triangle){
      for(BigInteger item: row){
        if(item.compareTo(number)==1){
          number = item;
        }
      }
    }
    return number;
  }

  // adds ALL numbers of row r together and outputs as BigInteger
  public BigInteger addAllOnRow(int r){
    BigInteger num = new BigInteger("0");
    for(BigInteger item: this.triangle.get(r-1)){
      num.add(item);
    }
    return num;
  }

}
