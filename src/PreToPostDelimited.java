/*
 * PreToPostDelimited
 *
 * DJJazzyBrett
 * Data Structures with Java
 * ~/Java/PTP-proj
 *
 */
//package src;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <h1>PreToPostDelimited</h1>
 *
 * Converts alphanumeric expressions from prefix notation to postfix notation.
 * <p>
 * While operators are assumed to be one character in length, operands may
 * be any number of characters in length. A (single space) whitespace
 * delimiter is used to differentiate the stack elements.
 * <p>
 * Furthermore, there are no constraints on the amount and type of whitespace
 * that may be occur before, after, or in between operators and operands.
 * <p>
 * Only one expression is permitted per input file. Example input files
 * are available as .txt files in ../input/PreToPostDelimited/ locale.
 * Related output files can be found in ../output/PreToPostDelimited/ locale.
 *
 * @author DJJazzyBrett
 * @version 1.0 02 March 2021
 * @since 1.0
 */
public class PreToPostDelimited {

    /**
     * Used to intialize for formatting purposes. The initial value
     * of 2 represents placeholder for '[' and ']'.
     */
    private static int stackSize = 2;

    /** Method used to distinguish operands. */
    private static boolean isOperand(char opr) {
        return !Character.isWhitespace(opr) && !isOperator(opr) ;
    }

    /** Method used to distinguish operators. */
    private static boolean isOperator(char op) {
        return op == '+' || op == '-' || op == '*' || op == '/' || op == '$';
    }

    /** Method used to format stacks for print and write methods. */
    private static String getFormat(String str1, String str2) {
        String sFormat = "%-" + stackSize + "s%-" + stackSize + "s%n";
        return String.format(sFormat, str1, str2);
    }

    public static void main(String[] args) throws IOException {

         // Ensure correct number of args were passed in
        if (args.length != 2) {
            System.out.println("Insufficient args!" + "\n"
                               + "Please use: java "
                               + "PreToPostDelimited input output");
            System.exit(0);
        }

       BufferedReader br = new
            BufferedReader(new FileReader("../input/PreToPostDelimited/"
                                          + args[0]));
        BufferedWriter bw = new
            BufferedWriter(new FileWriter("../output/PreToPostDelimited/"
                                          + args[1]));
        StackLinkBased<Character> prefixStack = new
            StackLinkBased<Character>();

        int readVal;
        char prefixChar = ' ';
        char prefixCharPrior = ' ';

        bw.newLine();
        bw.write("PREFIX EXPRESSION from ../input/PreToPostDelimited/"
                 + args[0] + "\n");
        bw.newLine();
        System.out.println("\nPREFIX EXPRESSION from"
                           + "../input/PreToPostDelimited/"
                           + args[0] + "\n");

        while ((readVal = br.read()) != -1) {
            prefixChar = (char) readVal; // cast int to char
            if (!Character.isWhitespace(prefixChar)) {

                if ((isOperand(prefixCharPrior) && isOperand(prefixChar))
                         || Character.isWhitespace(prefixCharPrior)) {
                    prefixStack.push(prefixChar);

                } else {
                    prefixStack.push(' ');
                    prefixStack.push(prefixChar);
                }

            } else if (!Character.isWhitespace(prefixCharPrior)) {
                prefixStack.push(' ');

            } else {}

            bw.write(prefixChar);
            System.out.print(prefixChar);
            prefixCharPrior = Character.valueOf(prefixChar); // do every time!
        }

        /*
         * Band-aid for now. I think I can adjust the logic above
         * to make the IF statement below unnecessary.
         */
        if (Character.isWhitespace(prefixStack.peek())) {
            prefixStack.pop();
        }

        bw.write("\n/---------------------------------/\n");
        bw.newLine();
        System.out.println("\n/---------------------------------/\n");

        StackLinkBased<String> postfixStack = new StackLinkBased<String>();
        String postfixString;
        stackSize += prefixStack.size();

        bw.write(getFormat("PREFIX STACK (reversed)",
                           "POSTFIX STACK\n"));
        System.out.print(getFormat("PREFIX STACK (reversed)",
                                   "POSTFIX STACK\n"));

        bw.write(getFormat(prefixStack.toString(),
                           postfixStack.toString()));
        System.out.print(getFormat(prefixStack.toString(),
                                   postfixStack.toString()));

        while (!prefixStack.isEmpty()) {
            postfixString = "";

            if (isOperator(prefixStack.peek())) {
                postfixStack.pop(); // discard whitespace
                postfixString = ""
                    + postfixStack.pop()
                    + postfixStack.pop()
                    + postfixStack.pop()
                    + " "
                    + prefixStack.pop();
                postfixStack.push(postfixString);

            } else if (isOperand(prefixStack.peek())) {

                while (isOperand(prefixStack.peek())) {
                    postfixString =  ""
                        + prefixStack.pop()
                        + postfixString;
                }

                postfixStack.push(postfixString);

            } else {
                prefixStack.pop();
                postfixStack.push(" ");
            }

            bw.write(getFormat(prefixStack.toString(),
                               postfixStack.toString()));
            System.out.print(getFormat(prefixStack.toString(),
                                       postfixStack.toString()));
        }

        br.close();
        bw.close();

    }
}
