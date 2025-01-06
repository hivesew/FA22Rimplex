package testing;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import gui.RimplexWindow;
import utilities.ComplexNumber;

class ComplexNumberTest
{

  @Test
  void test()
  {

    RimplexWindow.preferences = new utilities.Preferences();
    // TESTS FOR COVERAGE --------------------------------------------------------
    String c1 = "(4+2i)";
    String c2 = "(3+7i)";
    String c3 = "(3+i)";
    String badInput = "testBadInput";

    assertEquals(ComplexNumber.parseComplexNumber(c3).getReal(), 3.0);
    assertEquals(ComplexNumber.parseComplexNumber(c3).getImaginary(), 1);

    assertThrows(NumberFormatException.class, () -> {
      ComplexNumber.parseComplexNumber(badInput);
    });

    ComplexNumber cn, cn2, cn6;
    ComplexNumber cn5 = new ComplexNumber();
    cn = ComplexNumber.parseComplexNumber(c1);
    cn2 = ComplexNumber.parseComplexNumber(c2);
    cn6 = ComplexNumber.parseComplexNumber(c2);
    ComplexNumber cn3 = cn.divide(cn2);
    // System.out.println(cn.toString());
    // System.out.println(cn2.toString());
    // System.out.println(cn3.toString());
    cn2.add(cn5);
    cn2.subtract(cn5);
    cn2.multiply(cn5);
    assertEquals(cn2.getReal(), cn6.getReal());

    // -------------------------------------------------------------------------

    // Both Positive Values ---------------------------------------------------
    // In GUI Calculator - WORKS

    // --JUnit Tests
    ComplexNumber c10 = ComplexNumber.parseComplexNumber("(15+2i)");

    ComplexNumber c11 = ComplexNumber.parseComplexNumber("(12+4i)");

    ComplexNumber c10ADDc11 = ComplexNumber.parseComplexNumber("(27+6.0i)");

    // System.out.print(c11.toString());

    ComplexNumber addResult = c10.add(c11);

    assertEquals(addResult.toString(), c10ADDc11.toString());

    // Both Negative Values ----------------------------------------------------
    // In GUI Calculator - WORKS

    // --JUnit Tests
    ComplexNumber c13 = ComplexNumber.parseComplexNumber("(-14-2i)");

    ComplexNumber c14 = ComplexNumber.parseComplexNumber("(-12-4i)");

    ComplexNumber c13SUBTRACTc14 = ComplexNumber.parseComplexNumber("(-2+2.0i)");

    // System.out.print(c11.toString());

    ComplexNumber subtractResult = c13.subtract(c14);

    assertEquals(subtractResult.toString(), c13SUBTRACTc14.toString());

    // Left Positive, Right Negative -------------------------------------------

    // In GUI Calculator - WORKS

    // --JUnit Tests
    ComplexNumber c15 = ComplexNumber.parseComplexNumber("(14-2i)");

    ComplexNumber c16 = ComplexNumber.parseComplexNumber("(12-4i)");

    ComplexNumber c15ADDc16 = ComplexNumber.parseComplexNumber("(26-6.0i)");

    // System.out.print(c11.toString());

    ComplexNumber c15ADDC16RESULT = c15.add(c16);

    assertEquals(c15ADDc16.toString(), c15ADDC16RESULT.toString());

    // Left Decimal, Right Whole -----------------------------------------------
    // In GUI Calculator - WORKS

    // --JUnit Tests
    ComplexNumber c18 = ComplexNumber.parseComplexNumber("(14.0+2i)");

    ComplexNumber c19 = ComplexNumber.parseComplexNumber("(12.0+4i)");

    ComplexNumber c18ADDc19 = ComplexNumber.parseComplexNumber("(26.0+6i)");

    ComplexNumber c18Addc19Result = c18.add(c19);

    assertEquals(c18Addc19Result.toString(), c18ADDc19.toString());

    // Left Whole, Right Decimal -----------------------------------------------
    // In GUI Calculator - WORKS

    // --JUnit Tests
    ComplexNumber c20 = ComplexNumber.parseComplexNumber("(14+2.0i)");

    ComplexNumber c21 = ComplexNumber.parseComplexNumber("(12+4.0i)");

    ComplexNumber c20ADDc21 = ComplexNumber.parseComplexNumber("(26+6.0i)");

    ComplexNumber c20ADDc21Result = c20.add(c21);

    assertEquals(c20ADDc21.toString(), c20ADDc21Result.toString());

    // Both Decimal, Short and Long -----------------------------------------------
    // In GUI Calculator - WORKS

    // --JUnit Tests
    ComplexNumber c25 = ComplexNumber.parseComplexNumber("(14.450+2.0i)");

    ComplexNumber c26 = ComplexNumber.parseComplexNumber("(12.050+5.0i)");

    ComplexNumber c25ADDc26 = ComplexNumber.parseComplexNumber("(26.5+7.0i)");

    ComplexNumber c25ADDc26Result = c25.add(c26);

    assertEquals(c25ADDc26.toString(), c25ADDc26Result.toString());

    // Both Max Length Numbers -----------------------------------------------

    ComplexNumber c99 = (ComplexNumber.parseComplexNumber("(99999997+999999i)"));
    System.out.print(c99.toString());

    ComplexNumber c50 = ComplexNumber.parseComplexNumber("(99999998+999999i)");

    ComplexNumber c51 = ComplexNumber.parseComplexNumber("(99999999+999999i)");

    ComplexNumber c50ADDc51 = ComplexNumber.parseComplexNumber("(199999997+1999998i)");

    ComplexNumber c50ADDc51Result = c50.add(c51);

    System.out.print(c50ADDc51Result.toString());

    assertEquals(c50ADDc51.toString(), c50ADDc51Result.toString());

    // Conjugate ---------------------------------------------------------------

    ComplexNumber c100 = ComplexNumber.parseComplexNumber("(5+10i)");

    ComplexNumber c101 = c100.conjugate();

    ComplexNumber c102 = ComplexNumber.parseComplexNumber("(5-10i)");

    assertEquals(c101.toString(), c102.toString());

    // Invert ------------------------------------------------------------------

    ComplexNumber c103 = c100.invert();

    // Log ---------------------------------------------------------------------

    ComplexNumber c110 = c100.log();

    // Exponent ----------------------------------------------------------------

    ComplexNumber c115 = c100.exponent();

    // Power -------------------------------------------------------------------

    ComplexNumber c220 = c100.power(c100);

    // SquareRoot --------------------------------------------------------------

    ComplexNumber c250 = c100.squareRoot();

    // EvaluateExpressionString -----------------------------------------------

    ComplexNumber c300 = ComplexNumber.evaluateExpressionString("(5+3i)");

    ComplexNumber c310 = ComplexNumber.evaluateExpressionString("5+3i)");

    ComplexNumber c350 = ComplexNumber.evaluateExpressionString("5+3");

    ComplexNumber.evaluateExpressionString("");

    // ToString -------------------------------------------------------------

    ComplexNumber c400 = ComplexNumber.evaluateExpressionString("");

    ComplexNumber c420 = ComplexNumber.evaluateExpressionString("0");

    ComplexNumber c421 = ComplexNumber.evaluateExpressionString("(0+4i)");

    c420.toString();

    c421.toString();

    // 11/1 Testing Notes --
    // Can't handle left value having leading decimals, only right (try .25+.25i, then 0.25+.25i)
    // Update - fixed that error using regex. DONE!

    // 11/3 Testing Notes -- Both Decimal with 14.450+2.0i and 12.050+5.0i lead to
    // uncertain values.

    // Max-Length errors with seemingly valid numbers.

    // 11/17 - added coverage for Conjugate, Invert, Log, Exponent, Power,
    // EvaluateExpressionString, ToString

  }

}
