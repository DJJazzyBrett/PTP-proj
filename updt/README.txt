/*
 * $Header: /PTP-proj/updt/README.txt,v 1.0, 25 March 2021 $
 * $Author: DJJazzyBrett $
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
contents somewhere on your system, navigate to the './PTP-proj/updt/src/'
directory using your terminal of choice. Both the source files and compiled
versions of these files are situated in this locale.

To re-compile the source files, execute the following command:

djjazzy@home: ~/PTP-proj/updt/src$ javac *.java


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
================================================================================
To assist in the evaluation of the PreToPostUpdt class, seven sample
I/O files are included in the module. The seven files are organized as follows:


Input files --- > /PTP-proj/updt/input/PreToPostUpdt/

             dblPlusGood.txt
                 holyExp.txt
              lengthyExp.txt
            makeUpUrMind.txt
             rWeThereYet.txt
              switcheroo.txt
    WeTalkinBoutPractice.txt

The titles of the files - however whimsical and irreverent they may be - are
meant to reflect the type of scenario being tested by their contents.


Output files --- > /PTP-proj/updt/output/PreToPostUpdt/

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
