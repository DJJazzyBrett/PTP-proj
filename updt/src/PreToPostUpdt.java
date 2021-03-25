/*
 * PreToPostUpdt
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
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.IOException;

import java.util.NoSuchElementException;
/**
 * <h1>PreToPostUpdt</h1>
 *
 *
 * @author Brett Johnson
 * @version 1.0 25 March 2021
 * @since 1.0
 */
public class PreToPostUpdt {
    private static String prefixHeader = "|- PREFIX STACK -|";
    private static String postfixHeader = " |- POSTFIX STACK -|";


    /**
     * Helper method to facilitate identifying mathematical type
     * of each input character
     *
     * @param op Character that will be evaluated as an operand,
     *           operator, end-of-line character, or other
     * @return Operand, operator, EOL character, or null
     */
    private static String isOp(char op) {
        if (op == '+' || op == '-' || op == '*' || op == '/' || op == '$') {
            return "operator";
        } else if (Character.isLetter(op)) {
            return "operand";
        } else if (op == '\n' || op == '\r') {
            return "eol";
        } else {
            return null;
        } // END if
    } // END isOp


    /**
     * Helper method to formalize output stack style
     *
     * @param prefix Prefix stack object
     * @param postfix stack object
     *
     * @return String representation of stacks
     *
     */
    private static String toFormat(StackLinkBased<Character> prefix,
                                   StackLinkBased<String> postfix) {
        int printSize = 2;
        if (prefix.size() < prefixHeader.length()) {
            printSize += prefixHeader.length();
        } else {
            printSize += prefix.size();
        }
        String sFormat = "%-" + printSize + "s%-" + printSize + "s%n";
        return String.format(sFormat, prefix.toString(), postfix.toString());
    }


    /**
     * Let the games begin!
     *
     * @param args[] Provide input and output filenames from command line
     *               Input file must already be in existence
     *               Output file need not already be in existence
     *
     */
    public static void main(String[] args) throws IOException {

        String runIn = "KB: "
            + (double)
            (Runtime.getRuntime().totalMemory()
             - Runtime.getRuntime().freeMemory()) / 1024;

        BufferedReader br;
        BufferedWriter bw;

        if (args.length != 2) {
            System.out.println("Insufficient args!"
                               + "\n"
                               + "Please use: java PreToPost input output");
            System.exit(0);
        }

        try {
            br = new BufferedReader(new
                         FileReader("../input/PreToPostUpdt/" + args[0]));
            bw = new BufferedWriter(new
                         FileWriter("../output/PreToPostUpdt/" + args[1]));

        } catch (Exception e) {
            System.err.println(e.toString());
            return;
        }

        bw.write("Prefix expressions read from " + args[0] + "\n");

        StackLinkBased<Character> prefixStack = new StackLinkBased<Character>();
        StackLinkBased<String> postfixStack = new StackLinkBased<String>();
        StackLinkBased<String> checkStack = new StackLinkBased<String>();

        char prefixChar = '\0';
        int exprsnNum = 0;
        int convFlag;

        for (int readVal = br.read(); readVal != -1; readVal = br.read()) {
            prefixChar = (char) readVal;
            convFlag = 0;

            if (prefixStack.isEmpty()) {
                System.out.println(
                           "\n---- Begin processing of prefix expression #"
                           + (exprsnNum+1) + " ----\n");
                bw.newLine();
                bw.write("Expression #" + (exprsnNum+1) + ": \n");
            } // END if

            if (isOp(prefixChar) == "operator"
                || isOp(prefixChar) == "operand") {
                prefixStack.push(prefixChar);
                System.out.println("Pushing item to stack: " + prefixChar);

            } else if (Character.isSpaceChar(prefixChar)) {
                System.out.println(
                           "\n"
                           + " . .. ... ignoring whitespace ... .. . "
                           + "\n");

            } else if (isOp(prefixChar) != "eol") {
                userInput(prefixChar, prefixStack);

            } else { // isOp(prefixChar) == "eol"

                try {
                    checkStack = checkStack(prefixStack);

                    if (checkStack.size() > 1) {
                        System.out.println(
                                   "\n/////////////////////////////////////");
                        System.out.println(
                                     "/////////// INVALID PREFIX //////////");
                        System.out.println(
                                   "/////////////////////////////////////\n");
                        System.out.println(
                   "OPERATOR ERROR! Too few operators in prefix expression!");

                        bw.write("Orig prefix expression: ");
                        bw.write(prefixStack.origString() + "\n");
                        bw.write("Fnl postfix expression: <invalid>\n");
                        bw.write(
                       "ERROR ---> too few operators in prefix expression\n");

                        prefixStack.popAll();
                        checkStack.popAll();

                    } else if (checkStack.size() == 0) {
                        System.out.println(
                               "EMPTY ROW! Nothing to see here. Move along.");
                        bw.write("Orig prefix expression: <missing>\n");
                        bw.write("Fnl postfix expression: <missing>\n");

                    } else {
                        convFlag = 1;

                    } // END if

                } catch (NoSuchElementException e) {
                    System.out.println(
                               "\n/////////////////////////////////////\n"
                               + "/////////// INVALID PREFIX //////////\n"
                               + "/////////////////////////////////////\n"
                   + "OPERAND ERROR! Too few operands in prefix expression!");

                    bw.write("Orig prefix expression: ");
                    bw.write(prefixStack.origString() + "\n");
                    bw.write("Fnl postfix expression: <invalid>\n");
                    bw.write(
                        "ERROR ---> too few operands in prefix expression\n");

                    prefixStack.popAll();
                    checkStack.popAll();

                } finally {
                    exprsnNum++;
                    System.out.println(
                               "\n---- End processing of prefix expression #"
                               + exprsnNum + " ----\n");
                } // END try/catch/finally

            } // END if

            if (convFlag != 1) {
                continue;
            } // END if

            /* Call method to convert expression from prefix to postfix. */
            convertStack(prefixStack, postfixStack, bw);

            /* Clean up stacks before moving to next expression [or EOF]. */
            prefixStack.popAll();
            postfixStack.popAll();

        } // END for

        br.close();
        bw.close();

    } // END main




    /**
     * Converts validated prefix expression to corresponding
     * postfix equivalent
     *
     * @param prefixStackp Input prefix stack
     * @param postfixStack Initialized postfix stack structure
     * @param bw For output file writing
     *
     * @return none
     *
     */
    private static void convertStack(StackLinkBased<Character> prefixStack,
                                     StackLinkBased<String> postfixStack,
                                     BufferedWriter bw) throws IOException {
        String postfixString = null;

        System.out.println(
             "\n////////////// COMMENCE CONVERSION PROCESS //////////////\n"
             + "\nOriginal prefix expression: ");
        System.out.println(prefixStack.origString() + "\n");
        System.out.println(prefixHeader + postfixHeader + "\n");
        System.out.print(toFormat(prefixStack, postfixStack));

        bw.write("Orig prefix expression: ");
        bw.write(prefixStack.origString() + "\n");

        while (!prefixStack.isEmpty()) {

            if (isOp(prefixStack.peek()) == "operator") {
                postfixString = "" + postfixStack.pop()
                                   + postfixStack.pop()
                                   + prefixStack.pop();
                postfixStack.push(postfixString);
                System.out.print(toFormat(prefixStack, postfixStack));

            } else {
                postfixString = "" + prefixStack.pop();
                postfixStack.push(postfixString);
                System.out.print(toFormat(prefixStack, postfixStack));

            } // END if

        } // END while

        System.out.print("\nFinal postfix expression: ");
        System.out.println(postfixStack.origString());
        System.out.println(
           "\n////////////// CONCLUDE CONVERSION PROCESS //////////////\n");

        bw.write("Fnl postfix expression: ");
        bw.write(postfixStack.origString());
        bw.newLine();

    } // END convertStack


    /**
     * Check validity of input prefix expression
     *
     * @param stack Input prefix stack to check
     *
     * @return Empty stack if valid, non-empty stack if
     *
     */
    private static StackLinkBased<String> checkStack(
                                            StackLinkBased<Character> stack) {
        StackLinkBased<Character> cloneStack = stack.cloneStack();
        StackLinkBased<String> checkStack = new StackLinkBased<String>();
        String checkString = null;

        while (!cloneStack.isEmpty()) {

            if (isOp(cloneStack.peek()) == "operator") {
                checkString = "" + checkStack.pop()
                                 + cloneStack.pop()
                                 + checkStack.pop();
                checkStack.push(checkString);

            } else {
                checkString = "" + cloneStack.pop();
                checkStack.push(checkString);

            } // END if

        } // END while

        return checkStack;

    } // END checkStack


    /**
     * Lets user select from options during runtime
     * from command line interface; used to decide
     * upon course of action when input char is
     * undetermined or otherwise ambiguous
     *
     * @param prefixCheck Character of interest
     * @param prefixStack Input prefix expression
     *
     */
    private static void userInput(char prefixCheck,
                 StackLinkBased<Character> prefixStack) throws IOException {

        BufferedReader brSys = new BufferedReader(
                                          new InputStreamReader(System.in));
        int intInput = -1;
        char charInput = prefixCheck;

        System.out.println("\n/////////////////////////////////////");
        System.out.println("//////////// INPUT ERROR ////////////");
        System.out.println("/////////////////////////////////////\n");
        System.out.println("PreToPost does not recognize the element "
                           + prefixCheck
                           + " from prefix expression.");
        System.out.println("\nWhat would you like to do?\n"
        + "\n      1. Exclude element from further processing."
        + "\n      2. Treat element as operand."
        + "\n      3. Manually change the element.\n");

        while (intInput < 1 || intInput > 3) {

            try {
                intInput = Integer.parseInt(brSys.readLine());
            } catch (NumberFormatException e) {
            }

            if (intInput == 1) {
                // don't do anything ... logic should take me out of loop
                System.out.println();

            } else if (intInput == 2) {
                prefixStack.push(prefixCheck);
                System.out.println("\nPushing item to stack: " + prefixCheck);

            } else if (intInput == 3) {
                System.out.println("\nPlease input single character:\n");

                String strInput = brSys.readLine();
                prefixCheck = strInput.charAt(0);
                prefixStack.push(prefixCheck);
                System.out.println("\nChanging " + charInput
                                   + " to " + prefixCheck + ".");
                System.out.println("\nPushing item to stack: " + prefixCheck);

            } else {
                System.out.println("\nHmmmmm .... no me gusto.");
                System.out.println("Please select a valid option.\n");

            } // END if

        } // END while

    } // END userInput

} // END PreToPostUpdt
