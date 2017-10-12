/**
 * Created by Anastasia Pauliuchuk on 9/26/2017.
 */

import com.anp.calc.Calc;
import com.anp.calc.ExpressionFormatException;
import com.anp.reader.MyDataResourcePropReader;

import java.text.DecimalFormat;

public class Main {


    private static final String INPUT_FILENAME = "inputExpression.properties";
    private static final String INPUT_PROPERTYNAME = "expression";

    public static void main(String[] args) {


    /*Task 3
    *
    * read expression from properties file
    * write result to console
    *
    */

        MyDataResourcePropReader reader = new MyDataResourcePropReader(INPUT_FILENAME);
        String expression = reader.readProperty(INPUT_PROPERTYNAME);
        Calc calc = new Calc();
        try {
            String formattedRes = new DecimalFormat("#0.000").format(calc.count(expression));
            System.out.println(expression + " = " + formattedRes);
        } catch (ExpressionFormatException e) {
            System.out.println("Error. Wrong expression format");
        } catch (ArithmeticException e) {
            System.out.println("Error. Div by zero!");
        }
    }

    //task2.1


       /* MyDataResourceJSONReader dataReader=new MyDataResourceJSONReader("config2.json");
        MyTask1 myTask1 = new MyTask1(new ArrayList<String>(Arrays.asList("string1", "string2")),
                dataReader,
                new MyDataConsoleWriter("Задание 1"));


        myTask1.performTask();


        //task2.2


        MyDataResourcePropReader dataReader2 = new MyDataResourcePropReader("config1.txt");
        MyTask2 myTask2 = new MyTask2(new ArrayList<String>(Arrays.asList("string", "word")),
                dataReader2,
                new MyDataConsoleWriter("Задание 2")) {

        };

        myTask2.performTask();
*/


}
