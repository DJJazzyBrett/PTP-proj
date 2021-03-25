/*
 * $Header: /JohnsonBrettLab1updt/README.txt,v 1.0, 25 March 2021 $
 * $Author: Brett Johnson $
 * $Version: 2.0 $
 * $Date: 25 March 2021 $
 *
 * =============================================================================
 *
 * ITERATIVE SOLUTIONS FOR ARITHMETIC EXPRESSION CONVERSION
 *
 * PreToPostUpdt & PreToPost & PreToPostDelimited
 *
 * =============================================================================
 *
 */


////////////////////////////////////////////////////////////////////////////////
// PreToPost & PreToPostDelimited & PreToPostUpdt 2.0  -  $Date: 25 March 2021 $
////////////////////////////////////////////////////////////////////////////////


----> ALERT! <----

      PLEASE READ OR SKIM THIS FILE BEFORE OR AFTER USING THIS MODULE



------------------------- BGN UPDT ---------------------------------------------

2. Command Line Arguments <<PreToPostUpdt>>
================================================================================
A graphical representation of the module's file structure is included in
dir-tree.txt. This text file is in the same folder as this README document.

After having unzipped the JohnsonBrettLab1updt.zip folder and stored its
contents somewhere on your system, navigate to the './JohnsonBrettLab1updt/src/'
directory using your terminal of choice. Both the source files and compiled
versions of these files are situated in this locale.

To re-compile the source files, execute the following command:

home@cpu: ~/JohnsonBrettLab1updt/src$ javac *.java


To run the required .txt file, input the following command into your terminal
console:

$ java PreToPostUpdt    /required/input.txt /required/output.txt


----> Nomenclature note <----

      No such /required/output.txt file is included in the contents of the
      module. The PreToPostUpdt class offers the user an interactive experience
      by which one can select one of three options via the command line
      when prompted by the program. This will primarily occur when the
      PreToPostUpdt class does not recognize a particular element being read
      from the input file. Here is an example of what you might encounter.

      // Begin command line selection example //

      PreToPost does not recognize the element ^ from prefix expression.

      What would you like to do?

           1. Exclude element from further processing
           2. Treat element as operand
           3. Manually change the element

      // End command line selection example //

      By selecting option 3, you will then be directed to input a single
      character through the command line interface. Make a mistake and
      you will be shamed/mocked/scorned! Just kidding ... you'll just
      be prompted again to make a valid selection.

      To demonstrate this functionality, I have included three output
      files that are all derived from the same /required/input.txt file.

      /required/output-option1.txt
      /required/output-option2.txt
      /required/output-option3.txt

      Each output file corresponds to the option I selected during their
      processing. Note that for option3, I input "Z" and "$" respectively
      when directed by the prompt.

      Finally, please observe that both an input file and an output file must be
      declared as arguments. The input file must already be in existence;
      however, the output file need not already have been created. One will be
      created in the appropriate directory if it does not exist prior to program
      execution.


3. Sample I/O Files <<PreToPostUpdt>>
==================================================================================
To assist in the evaluation of the PreToPostUpdt class, seven sample
I/O files are included in the module. The seven files are organized as follows:


Input files --- > /JohnsonBrettLab1updt/input/PreToPostUpdt/

             dblPlusGood.txt
                 holyExp.txt
              lengthyExp.txt
            makeUpUrMind.txt
             rWeThereYet.txt
              switcheroo.txt
    WeTalkinBoutPractice.txt

The titles of the files - however whimsical and irreverent they may be - are
meant to reflect the type of scenario being tested by their contents.


Output files --- > /JohnsonBrettLab1updt/output/PreToPostUpdt/

      same names as input

Please remember that any output file can be specified; the ones included in the
module are meant to assist in program evaluation.


4. Setup Specifications <<PreToPostUpdt>>
================================================================================
The design and construction of java programs in this module utilized the
following tools / software / components:


----> Emacs Java IDE using Eclipse JDT Language Server <----

      https://emacs-lsp.github.io/lsp-java/
      https://projects.eclipse.org/projects/eclipse.jdt.ls


----> GNU Emacs <----

      GNU Emacs 27.1
      Copyright (C) 2020 Free Software Foundation, Inc.
      GNU Emacs comes with ABSOLUTELY NO WARRANTY.
      You may redistribute copies of GNU Emacs
      under the terms of the GNU General Public License.
      For more information about these matters, see the file named COPYING.


----> Java <----

      openjdk 11.0.10 2021-01-19
      OpenJDK Runtime Environment (build 11.0.10+9-Ubuntu-0ubuntu1.18.04)
      OpenJDK 64-Bit Server VM (build 11.0.10+9-Ubuntu-0ubuntu1.18.04,
                                mixed mode, sharing)


5. Miscellaneous <<PreToPostUpdt>>
================================================================================
Looking for a good time? Try redirecting the console output to a text file and
read through some of the output. See if you can keep track of the stack
operations throughout the various method calls.

java PreToPostUpdt > ../output/PreToPostUpdt/log.txt lengthyExp.txt
                                                        lengthyExp.txt

------------------------- END UPDT ---------------------------------------------



------------------------- BGN ORIG ---------------------------------------------

0. Table of Contents <<PreToPost & PreToPostDelimited>>
================================================================================

0. Table of Contents
1. PreToPost Introduction
2. Command Line Arguments
3. Sample I/O Files
4. Checks and Balances
5. Setup Specifications
6. File Directory


1. PreToPost Introduction <<PreToPost & PreToPostDelimited>>
================================================================================
The following module contains the necessary tools to assist in the conversion of
arithmetic expressions: from prefix notation to postfix notation. At the center
of the accompanying infrastructure lies the STACK data structure. Its last-in-
first-out, or LIFO, properties make it an ideal candidate for the manipulation
of a series of ordered, discrete elements.

Being an abstract data type, a STACK's tangible functionality and mechanisms
rely heavily upon its implementation. Within this module, we use a singly-linked
list as the underlying component to implement our STACK collection.

The PreToPost class accepts a single prefix expression as input from a .txt file.
The expression can be on one line of the file or it can span multiple lines.
Any whitespace characters in the file - including but not limited to
SPACE_SEPARATOR, LINE_SEPARATOR, and PARAGRAPH_SEPARATOR - are discarded and
have no effect on the conversion process & results.

A major constraint of the PreToPost class that should be noted is its inability
to account for singular values that are comprised of more than one character or
digit. Thus, the processing that occurs within the PreToPost class regards all
input as a single character, regardless of any delimiters or whitespace that may
be present.

----> PreToPostDelimited class <----

     To circumvent this limiting aspect, I've included a companion class that is
     entitled PreToPostDelimited. This class utilizes the same STACK interface
     and is implemented by the same singly-linked list as the PreToPost class.

     The operand values read by the PreToPostDelimited class can be comprised
     of any length. For instance, for variable input, ABCDEF is treated as one
     value. Similarly, the value 12345 is treated as a single entity in this
     delimited version of the class.

     The PreToPost and PreToPostDelimited classes will produce the same results,
     albeit in slightly different format, for any input that consists of single-
     digit operands. That is, the PreToPost class is a special case of the more
     general PreToPostDelimited class.

     To see an example of this in action, try using the same input file
     'input-var-00.txt' for instances of both classes. Compare the results.
     They should be the same, although the output format for the delimited
     class includes a single whitespace as a delimiter between operands and
     operators.


2. Command Line Arguments <<PreToPost & PreToPostDelimited>>
================================================================================
A graphical representation of the modules' file structure is included below in
section 6. Please reference this for any questions about file hierarchy / order.

After having unzipped the JohnsonBrettLab1.zip folder and stored its contents
somewhere on your system, navigate to the './JohnsonBrettLab1/src/' directory
using your terminal of choice. Both the source files and compiled versions of
these files are situated in this locale.

To re-compile the source files, execute the following command:

home@cpu: ~/JohnsonBrettLab1/src$ javac *.java


To run the sample input file (mentioned in the previous section) for both
classes, input the following commands into your terminal console:

$ java PreToPost          input-var-00.txt output-var-00.txt
$ java PreToPostDelimited input-var-00.txt output-var-00.txt


----> Nomenclature note <----

      Although the filenames are identical for both commands, the files do not
      overwrite each other. All I/O files for the PreToPost classes are
      automatically stored in the respective directories:

             ./JohnsonBrettLab1/input/PreToPost/
             ./JohnsonBrettLab1/output/PreToPost/

      And all I/O files for the PreToPostDelimited class are stored here:

             ./JohnsonBrettLab1/input/PreToPostDelimited/
             ./JohnsonBrettLab1/output/PreToPostDelimited/

      Also note that both an input file and an output file must be declared as
      arguments. The input file must already be in existence; however, the
      output file need not already have been created. One will be created in the
      appropriate directory if it does not exist prior to program execution.


3. Sample I/O Files <<PreToPost & PreToPostDelimited>>
================================================================================
To assist in the evaluation of the PreToPost(Delimited) class(es), eight sample
I/O files are included in the module. The eight files are organized as follows:


----> Input files <----

      input-num-01.txt
      input-num-02.txt
      input-num-03.txt
      input-num-04.txt

      input-var-01.txt
      input-var-02.txt
      input-var-03.txt
      input-var-04.txt


----> Output files <----

      output-num-01.txt
      output-num-02.txt
      output-num-03.txt
      output-num-04.txt

      output-var-01.txt
      output-var-02.txt
      output-var-03.txt
      output-var-04.txt


Each number affixed to the title corresponds to a unique prefix expression. That
is to say, there are four distinct expressions incorporated in the accompanying
input files. The "num" and "var" distinction indicate the type of value used in
the expression.

For instance, input-num-02.txt and input-var-02.txt are based on the exact same
prefix expression, but use numbers and (character) variables, respectively.

For the input-num-02.txt file the expression appears as: +-*$1234//56+78.
For the input-var-02.txt file the expression appears as: +-*$ABCD//EF+GH.

The only difference between the input files for the PreToPost class versus the
PreToPostDelimited class is the liberal use of whitespace used in the latter's
input files.

Please remember that any output file can be specified; the ones included in the
module are meant to assist in program evaluation.


4. Checks and Balances <<PreToPost & PreToPostDelimited>>
================================================================================
To check program performance and logic consistency, eight 'check' files are
available. Their names correspond exactly to those listed above, but can be
found in the following directories:

      ./JohnsonBrettLab1/input/PreToPost/check/
      ./JohnsonBrettLab1/output/PreToPost/check/

      ./JohnsonBrettLab1/input/PreToPostDelimited/check/
      ./JohnsonBrettLab1/output/PreToPostDelimited/check/

The four expressions used in the regular input files are used in the 'check'
files as well. The 'check' files, however, introduce irregularities that may
hinder (or perhaps even completely interrupt) program evaluation. The same
'check' scenarios are used in the PreToPost class files that are used in the
PreToPostDelimited files.

----> Testing scenarios <----

      1. Replace one operand with the symbol '#'
      2. Insert a newline (i.e., '\n') character between two operands
      3. Remove the last operand from the expression
      4. Remove the first operator from the expression

For both classes, scenarios 1 and 2 have no impact on program evaluation or
results. For scenario 3, in which an operand is removed, the programs for both
classes are interrupted and an EXCEPTION is thrown. And for the final scenario,
number 4, both sets of programs execute and return results, albeit ones that
correspond to a different (possibly unintended) postfix expression.

To run the programs with the testing inputs, prepend  "./check/" to the input
and output file names. No other alterations are necessary. For example:

$ java PreToPost          ./check/input-var-00.txt ./check/output-var-00.txt
$ java PreToPostDelimited ./check/input-var-00.txt ./check/output-var-00.txt


5. Setup Specifications <<PreToPost & PreToPostDelimited>>
================================================================================
The design and construction of java programs in this module utilized the
following tools / software / components:


----> Emacs Java IDE using Eclipse JDT Language Server <----

      https://emacs-lsp.github.io/lsp-java/
      https://projects.eclipse.org/projects/eclipse.jdt.ls


----> GNU Emacs <----

      GNU Emacs 27.1
      Copyright (C) 2020 Free Software Foundation, Inc.
      GNU Emacs comes with ABSOLUTELY NO WARRANTY.
      You may redistribute copies of GNU Emacs
      under the terms of the GNU General Public License.
      For more information about these matters, see the file named COPYING.


----> Java <----

      openjdk 11.0.10 2021-01-19
      OpenJDK Runtime Environment (build 11.0.10+9-Ubuntu-0ubuntu1.18.04)
      OpenJDK 64-Bit Server VM (build 11.0.10+9-Ubuntu-0ubuntu1.18.04,
                                mixed mode, sharing)


6. File Directory <<PreToPost & PreToPostDelimited>>
================================================================================
.
└── lab1
    ├── analysis
    │   └── Lab1_Analysis_BrettJohnson.org
    ├── info
    │   ├── code-samples.org
    │   ├── Error Handling and Exceptions.pdf
    │   ├── JavaCodeConventions.pdf
    │   ├── LabIterativePreToPost.pdf
    │   └── ProgrammingAssignmentsGuidelines.pdf
    ├── input
    │   ├── PreToPost
    │   │   ├── check
    │   │   │   ├── input-num-01.txt
    │   │   │   ├── input-num-02.txt
    │   │   │   ├── input-num-03.txt
    │   │   │   ├── input-num-04.txt
    │   │   │   ├── input-var-01.txt
    │   │   │   ├── input-var-02.txt
    │   │   │   ├── input-var-03.txt
    │   │   │   └── input-var-04.txt
    │   │   ├── input-num-01.txt
    │   │   ├── input-num-02.txt
    │   │   ├── input-num-03.txt
    │   │   ├── input-num-04.txt
    │   │   ├── input-var-01.txt
    │   │   ├── input-var-02.txt
    │   │   ├── input-var-03.txt
    │   │   └── input-var-04.txt
    │   └── PreToPostDelimited
    │       ├── check
    │       │   ├── input-num-01.txt
    │       │   ├── input-num-02.txt
    │       │   ├── input-num-03.txt
    │       │   ├── input-num-04.txt
    │       │   ├── input-var-01.txt
    │       │   ├── input-var-02.txt
    │       │   ├── input-var-03.txt
    │       │   └── input-var-04.txt
    │       ├── input-num-01.txt
    │       ├── input-num-02.txt
    │       ├── input-num-03.txt
    │       ├── input-num-04.txt
    │       ├── input-var-01.txt
    │       ├── input-var-02.txt
    │       ├── input-var-03.txt
    │       └── input-var-04.txt
    ├── output
    │   ├── PreToPost
    │   │   ├── check
    │   │   │   ├── output-num-01.txt
    │   │   │   ├── output-num-02.txt
    │   │   │   ├── output-num-03.txt
    │   │   │   ├── output-num-04.txt
    │   │   │   ├── output-var-01.txt
    │   │   │   ├── output-var-02.txt
    │   │   │   ├── output-var-03.txt
    │   │   │   └── output-var-04.txt
    │   │   ├── output-num-01.txt
    │   │   ├── output-num-02.txt
    │   │   ├── output-num-03.txt
    │   │   ├── output-num-04.txt
    │   │   ├── output-var-01.txt
    │   │   ├── output-var-02.txt
    │   │   ├── output-var-03.txt
    │   │   └── output-var-04.txt
    │   └── PreToPostDelimited
    │       ├── check
    │       │   ├── output-num-01.txt
    │       │   ├── output-num-02.txt
    │       │   ├── output-num-03.txt
    │       │   ├── output-num-04.txt
    │       │   ├── output-var-01.txt
    │       │   ├── output-var-02.txt
    │       │   ├── output-var-03.txt
    │       │   └── output-var-04.txt
    │       ├── output-num-01.txt
    │       ├── output-num-02.txt
    │       ├── output-num-03.txt
    │       ├── output-num-04.txt
    │       ├── output-var-01.txt
    │       ├── output-var-02.txt
    │       ├── output-var-03.txt
    │       └── output-var-04.txt
    ├── README.txt
    └── src
        ├── Node.class
        ├── Node.java
        ├── PreToPost.class
        ├── PreToPostDelimited.class
        ├── PreToPostDelimited.java
        ├── PreToPost.java
        ├── StackADT.class
        ├── StackADT.java
        ├── StackLinkBased.class
        └── StackLinkBased.java

------------------------- END ORIG ---------------------------------------------
