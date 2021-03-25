/*
 * PreToPost
 *
 * Brett Johnson
 * EN.605.202.81.SP21 Data Structures
 * Lab 1-updt
 *
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * <h1>PreToPost</h1>
 *
 * Converts alphanumeric expressions from prefix notation to postfix notation.
 * <p>
 * All operators and operands are assumed to be one character in length.
 * <p>
 * Only one expression is permitted per input file. Example input files
 * are available as .txt files in  ../input/PreToPost/ locale.
 * Associated output files can be found in ../output/PreToPost/ locale.
 *
 * @author Brett Johnson
 * @version 1.0 02 March 2021
 * @since 1.0
 */
public class PreToPost {

    /**
     * Used to intialize for formatting purposes. The initial value
     * of 2 represents placeholder for '[' and ']'.
     */
    private static int stackSize = 2;

    /** Method used to distinguish operators, */
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
                               + "Please use: java PreToPost input output");
            System.exit(0);
        }

        BufferedReader br = new
            BufferedReader(new FileReader("../input/PreToPost/" + args[0]));
        BufferedWriter bw = new
            BufferedWriter(new FileWriter("../output/PreToPost/" + args[1]));

        StackLinkBased<Character> prefixStack = new
            StackLinkBased<Character>();

        int readVal;
        char prefixChar = ' ';

        bw.newLine();
        bw.write("PREFIX EXPRESSION from ../input/PreToPost/"
                 + args[0] + "\n");
        bw.newLine();
        System.out.println("\nPREFIX EXPRESSION from ../input/PreToPost/"
                           + args[0] + "\n");

        while ((readVal = br.read()) != -1) {
            prefixChar = (char) readVal; // cast int to char

            if (!Character.isWhitespace(prefixChar)) {
                prefixStack.push(prefixChar);
            }
            bw.write(prefixChar);
            System.out.print(prefixChar);
        }


        bw.write("\n/---------------------------------/\n");
        bw.newLine();
        System.out.println("\n/---------------------------------/\n");

        StackLinkBased<String> postfixStack = new StackLinkBased<String>();
        String postfixString = null;
        stackSize += prefixStack.size();

        bw.write(getFormat("PREFIX STACK",
                           "POSTFIX STACK"));
        System.out.print(getFormat("PREFIX STACK",
                                   "POSTFIX STACK"));

        bw.write(getFormat("(reversed)",
                           "\n"));
        System.out.print(getFormat("(reversed)",
                                   "\n"));

        bw.write(getFormat(prefixStack.toString(),
                           postfixStack.toString()));
        System.out.print(getFormat(prefixStack.toString(),
                                   postfixStack.toString()));

        while (!prefixStack.isEmpty()) {

            if (isOperator(prefixStack.peek())) {
                postfixString = ""
                    + postfixStack.pop()
                    + postfixStack.pop()
                    + prefixStack.pop();
                postfixStack.push(postfixString);

                bw.write(getFormat(prefixStack.toString(),
                                   postfixStack.toString()));
                System.out.print(getFormat(prefixStack.toString(),
                                           postfixStack.toString()));

            } else {
                postfixString = "" + prefixStack.pop();
                postfixStack.push(postfixString);

                bw.write(getFormat(prefixStack.toString(),
                                   postfixStack.toString()));
                System.out.print(getFormat(prefixStack.toString(),
                                           postfixStack.toString()));
            }
        }

        br.close();
        bw.close();

    }
}
