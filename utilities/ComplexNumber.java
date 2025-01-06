package utilities;

import java.util.ArrayList;
import java.util.regex.*;

import gui.RimplexWindow;

/**
 * ComplexNumber class. A class that represents a complex number in the form a+bi
 *
 * @author Ewen Hives-F22TeamA
 */
public class ComplexNumber
{
  private static final String I = "i";
  private static final String PLUS = "+";
  private static final String MINUS = "-";
  private static final String DIVIDE = "\u00f7";
  private static final String MULT = "x";
  private static final String RE = "Re";
  private static final String IMAG = "Imag";
  private static final String EXP = "exp";
  private static final String CONJ = "z*";
  private static final String INV = "Inv";
  private static final String POW = "^";
  private static final String SQRT = "sqrt";
  private static final String LOG = "log";
  private static final String OP_PAR = "(";
  private static final String CL_PAR = ")";
  private static final String UND = "UNDEFINED";
  private static final String DEC = ".";
  // private static final String FORMAT = "#.########";
  private double real;
  private double imaginary;

  /**
   * @param real
   * @param imaginary
   */
  public ComplexNumber(final double real, final double imaginary)
  {
    this.real = real;
    this.imaginary = imaginary;
  }

  /**
   *
   */
  public ComplexNumber()
  {
    new ComplexNumber(0, 0);
  }

  /**
   * getReal getter method.
   *
   * @return real portion of this complex number
   */
  public double getReal()
  {
    return this.real;
  }

  /**
   * getImaginary getter method.
   *
   * @return imaginary portion of this complex number
   */
  public double getImaginary()
  {
    return this.imaginary;
  }

  /**
   * add method.
   *
   * @param other
   *     operand
   * @return complex number result of addition
   */
  public ComplexNumber add(final ComplexNumber other)
  {
    double realSum = this.getReal() + other.getReal();
    double imagSum = this.getImaginary() + other.getImaginary();
    return new ComplexNumber(realSum, imagSum);
  }

  /**
   * subtract method.
   *
   * @param other
   *     operand
   * @return complex number result of subtraction
   */
  public ComplexNumber subtract(final ComplexNumber other)
  {
    double realDiff = this.getReal() - other.getReal();
    double imagDiff = this.getImaginary() - other.getImaginary();
    return new ComplexNumber(realDiff, imagDiff);
  }

  /**
   * multiply method.
   *
   * @param other
   *     operand
   * @return complex number result of multiplying
   */
  public ComplexNumber multiply(final ComplexNumber other)
  {
    double realProduct =
        this.getReal() * other.getReal() - this.getImaginary() * other.getImaginary();
    double imagProduct =
        this.getReal() * other.getImaginary() + other.getReal() * this.getImaginary();
    return new ComplexNumber(realProduct, imagProduct);
  }

  /**
   * divide method.
   *
   * @param other
   *     operand
   * @return complex number result of dividing
   */
  public ComplexNumber divide(final ComplexNumber other)
  {
    double realNumerator =
        this.getReal() * other.getReal() + this.getImaginary() * other.getImaginary();
    double denomenator = Math.pow(other.getReal(), 2) + Math.pow(other.getImaginary(), 2);
    double imagNumerator =
        this.getImaginary() * other.getReal() - this.getReal() * other.getImaginary();
    double realResult = realNumerator / denomenator;
    double imagResult = imagNumerator / denomenator;
    return new ComplexNumber(realResult, imagResult);
  }

  /**
   * conjugate method.
   *
   * @return complex number result of conjugating
   */
  public ComplexNumber conjugate()
  {
    return new ComplexNumber(this.getReal(), -(this.getImaginary()));
  }

  /**
   * invert method. Calculates the multiplicative inverse of this complex number. (reciprocal)
   *
   * @return complex number result of inverting
   */
  public ComplexNumber invert()
  {
    // 1/a+bi = a/(a^2+b^2) + (- b/(a^2+b^2))i
    double realNumerator = this.getReal();
    double denomenator =
        this.getReal() * this.getReal() + this.getImaginary() * this.getImaginary();
    double imagNumerator = -(this.getImaginary());
    double realResult = realNumerator / denomenator;
    double imagResult = imagNumerator / denomenator;
    return new ComplexNumber(realResult, imagResult);
  }

  /**
   * logarithm method. Calculates the natural logarithm of this complex number.
   *
   * @return complex number result of logaritm
   */
  public ComplexNumber log()
  {
    // ln(z) = ln(|z|) + arctan(b/a)i
    // |z| = sqrt(a^2+b^2)
    // ln(a+bi) = ln(sqrt(a^2+b^2)) + arctan(b/a)i

    double absolute =
        Math.sqrt(this.getReal() * this.getReal() + this.getImaginary() * this.getImaginary());
    double realResult = Math.log(absolute);
    double imagResult = Math.atan(this.getImaginary() / this.getReal());
    return new ComplexNumber(realResult, imagResult);
  }

  /**
   * exponential method. Calculates e raised to the power of this complex number.
   *
   * @return complex number result of exponentiation
   */
  public ComplexNumber exponent()
  {
    // e^z = e^a * e^(bi)
    // e^a = e^a
    // e^(bi) = cos(b) + isin(b)
    double realResult = Math.exp(this.getReal()) * Math.cos(this.getImaginary());
    double imagResult = Math.exp(this.getReal()) * Math.sin(this.getImaginary());
    return new ComplexNumber(realResult, imagResult);
  }

  /**
   * power method.
   *
   * @param other
   *     complex number operand
   * @return complex number result of this^other
   */
  public ComplexNumber power(final ComplexNumber other)
  {
    // z^w = e^(w * ln(z))
    ComplexNumber multiplied = other.multiply(this.log());
    return multiplied.exponent();
  }

  /**
   * squareRoot method. Calculates the square root of this complex number
   *
   * @return complex number representing the square root
   */
  public ComplexNumber squareRoot()
  {
    // sqrt(z) = sqrt(r) * e^(i*theta/2)
    // r = sqrt(a^2+b^2)
    // theta = arctan(b/a)
    double r =
        Math.sqrt(this.getReal() * this.getReal() + this.getImaginary() * this.getImaginary());
    double theta = Math.atan(this.getImaginary() / this.getReal());
    double realResult = Math.sqrt(r) * Math.cos(theta / 2);
    double imagResult = Math.sqrt(r) * Math.sin(theta / 2);
    return new ComplexNumber(realResult, imagResult);
  }

  /**
   * @param complex
   *     string representation of a complex number
   * @return a ComplexNumber object if the string passed has a valid complex number
   * @throws NumberFormatException
   */
  public static ComplexNumber parseComplexNumber(final String complex) throws NumberFormatException
  {
    // String representation of a regex representing complex numbers in the form a+bi
    String regexComplex = "\\(([+-]?\\d*\\.?\\d*)([+-]\\d*\\.?\\d*)i\\)";
    // String representation of a regex representing real numbers
    String regexReal = "([+-]?\\d*\\.?\\d*)";
    // String representation of a regex representing imaginary numbers
    String regexImag = "([+-]?\\d*\\.?\\d*)i";

    Pattern patternComplex = Pattern.compile(regexComplex);
    Pattern patternReal = Pattern.compile(regexReal);
    Pattern patternImag = Pattern.compile(regexImag);

    Matcher matcherComplex = patternComplex.matcher(complex);
    Matcher matcherReal = patternReal.matcher(complex);
    Matcher matcherImag = patternImag.matcher(complex);

    double real;
    double imag;
    if (matcherComplex.matches())
    {

      real = Double.parseDouble(matcherComplex.group(1));
      try
      {
        imag = Double.parseDouble(matcherComplex.group(2));
      }
      catch (NumberFormatException nfe)
      {
        // matcher group two has no leading digits, just i or 1i
        imag = 1.0;
      }

    }
    else if (matcherReal.matches())
    {
      real = Double.parseDouble(matcherReal.group(1));
      imag = 0;
    }
    else if (matcherImag.matches())
    {
      real = 0;
      String s = matcherImag.group();
      if (s.equals(I))
      {
        imag = 1;
      }
      else
      {
        imag = Double.parseDouble(matcherImag.group(1));
      }
    }
    else
    {
      throw new NumberFormatException("Not a valid real, imaginary or complex number");
    }

    return new ComplexNumber(real, imag);

  }

  /**
   * evaluate expression method.
   *
   * @param expression
   *     String to attempt evaluation
   * @return complex number result of evaluating the string or null if the string is not a valid
   *     expression
   */
  public static ComplexNumber evaluateExpressionString(final String expression)
  {
    ComplexNumber result = null;
    String[] tokens = expression.split(" ");

    ArrayList<ComplexNumber> complexNumbers = new ArrayList<ComplexNumber>();
    ArrayList<String> operators = new ArrayList<String>();

    for (int i = 0; i < tokens.length; i++)
    {
      try
      {
        ComplexNumber complex = ComplexNumber.parseComplexNumber(tokens[i]);
        // parse a complex number operand and add it to the list of operands
        complexNumbers.add(complex);
      }
      catch (NumberFormatException nfe)
      {
        // token is not a complex number, should be an operator
        if (tokens[i].equals(PLUS) || tokens[i].equals(MINUS) || tokens[i].equals(MULT) 
            || tokens[i].equals(DIVIDE) || tokens[i].equals(POW) || tokens[i].equals(SQRT) 
            || tokens[i].equals(LOG) || tokens[i].equals(EXP) || tokens[i].equals(CONJ) 
            || tokens[i].equals(INV) || tokens[i].equals(RE) || tokens[i].equals(IMAG))
        {
          operators.add(tokens[i]);
        }
        else
        {
          // token is not a valid operator
          // input is invalid
          return null;
        }
      }
    }
    // evaluate the expression
    // check if unary operator (one operand and one operator)
    if (complexNumbers.size() == 1 && operators.size() == 1)
    {
      result = complexNumbers.get(0);
      if (operators.get(0).equals(SQRT))
      {
        result = result.squareRoot();
      }
      else if (operators.get(0).equals(LOG))
      {
        result = result.log();
      }
      else if (operators.get(0).equals(EXP))
      {
        result = result.exponent();
      }
      else if (operators.get(0).equals(CONJ))
      {
        result = result.conjugate();
      }
      else if (operators.get(0).equals(INV))
      {
        result = result.invert();
      }
      else if (operators.get(0).equals(RE))
      {
        result = new ComplexNumber(result.getReal(), 0);
      }
      else if (operators.get(0).equals(IMAG))
      {
        result = new ComplexNumber(0, result.getImaginary());
      }
      else
      {
        // invalid unary operator
        return null;
      }

    }
    else
    {
      // binary operator
      result = complexNumbers.get(0);
      for (int i = 0; i < operators.size(); i++)
      {
        if (operators.get(i).equals(PLUS))
        {
          result = result.add(complexNumbers.get(i + 1));
        }
        else if (operators.get(i).equals(MINUS))
        {
          result = result.subtract(complexNumbers.get(i + 1));
        }
        else if (operators.get(i).equals(MULT))
        {
          result = result.multiply(complexNumbers.get(i + 1));
        }
        else if (operators.get(i).equals(DIVIDE))
        {
          result = result.divide(complexNumbers.get(i + 1));
        }
        else if (operators.get(i).equals(POW))
        {
          result = result.power(complexNumbers.get(i + 1));
        }
      }
    }
    return result;
  }

  /**
   * toString method.
   *
   * @return string representing the complex number in the form a+bi
   */
  public String toString()
  {
    // using a decimal format to ensure maximum 8 digits after the decimal point
    // DecimalFormat df = new DecimalFormat(FORMAT);
    // String realF = df.format(this.getReal());
    // String imagF = df.format(this.getImaginary());
    String realF = String.valueOf(this.getReal());
    String imagF = String.valueOf(this.getImaginary());
    realF = realF + trailingZeros();
    imagF = imagF + trailingZeros();
    realF = numOfDecimals(realF);
    imagF = numOfDecimals(imagF);
    if (RimplexWindow.preferences.isThousandsSeparator())
    {
      realF = commaGiver(realF);
      imagF = commaGiver(imagF);
    }

    String complexString = "";
    if (Double.isNaN(this.getReal()) || Double.isNaN(this.getImaginary()))
    {
      complexString += UND;
    }
    else
    {
      if (this.getImaginary() == 0)
      {
        complexString += realF;
      }
      else if (this.getReal() == 0)
      {
        complexString += imagF + I;
      }
      else if (this.getImaginary() < 0)
      {

        complexString += OP_PAR + realF + imagF + I + CL_PAR;
      }
      else
      {
        complexString += OP_PAR + realF + PLUS + imagF + I + CL_PAR;
      }

    }
    return complexString;
  }

  /**
   * polarToString method.
   *
   * @return Polar form of complex number
   */
  public String polarToString()
  {
    // using a decimal format to ensure maximum 8 digits after the decimal point
    // DecimalFormat df = new DecimalFormat(FORMAT);

    double r = Math.sqrt(Math.pow(this.getReal(), 2) + Math.pow(this.getImaginary(), 2));
    double theta = Math.atan2(this.getImaginary(), this.getReal());

    // String rF = df.format(r);
    // String thetaF = df.format(theta);

    String rF = String.format("%f", r);
    String thetaF = String.format("%f", theta);
    rF = rF + trailingZeros();
    thetaF = thetaF + trailingZeros();
    rF = numOfDecimals(rF);
    thetaF = numOfDecimals(thetaF);
    if (RimplexWindow.preferences.isThousandsSeparator())
    {
      rF = commaGiver(rF);
      thetaF = commaGiver(thetaF);
    }

    String polarString = "";
    if (Double.isNaN(this.getReal()) || Double.isNaN(this.getImaginary()))
    {
      polarString += UND;
    }
    else
    {
      polarString += rF + "(cos(" + thetaF + ") + isin(" + thetaF + "))";
    }

    return polarString;
  }

  /**
   * creates trailing zeros to be appended to the end of a number.
   *
   * @return true if the two complex numbers are equal, false otherwise
   */
  public String trailingZeros()
  {
    return "0".repeat(Math.max(0, RimplexWindow.preferences.getTrailingZeros()));
  }

  /**
   * Conforms resulting operator to user preferences on length of decimal places.
   * @param num is the number to give the number of decimals to
   * @return string representing number with user specified number of decimal places.
   */
  public String numOfDecimals(final String num)
  {
    int limit = RimplexWindow.preferences.getNumberOfDecimalPlaces();
    int decimalIndex = 0;
    String wholeTemp;
    String decimalTemp;
    for (int i = 0; i < num.length(); i++)
    {
      if (num.charAt(i) == '.')
      {
        decimalIndex = i;
        break;
      }
    }
    wholeTemp = num.substring(0, decimalIndex);
    decimalTemp = num.substring(decimalIndex + 1);
    if (decimalTemp.length() > limit)
    {
      decimalTemp = decimalTemp.substring(0, limit);
    }
    return wholeTemp + DEC + decimalTemp;
  }

  /**
   * Adds commas to a number to make it easier to read.
   * @param num is the number to give the commas to
   * @return string representing number with commas.
   */
  public String commaGiver(final String num)
  {
    int decimalIndex = 0;
    String wholeTemp;
    String decimalTemp;
    for (int i = 0; i < num.length(); i++)
    {
      if (num.charAt(i) == '.')
      {
        decimalIndex = i;
        break;
      }
    }
    wholeTemp = num.substring(0, decimalIndex);
    decimalTemp = num.substring(decimalIndex + 1);
    // for any number with more than 3 digits, add a comma every 3 digits from the right.
    if (wholeTemp.length() > 3)
    {
      for (int i = wholeTemp.length() - 3; i > 0; i -= 3)
      {
        wholeTemp = wholeTemp.substring(0, i) + "," + wholeTemp.substring(i);
      }
    }
    return wholeTemp + DEC + decimalTemp;
  }
}
