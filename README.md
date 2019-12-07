# Fractional-Calculator
This is a Java program that will perform basic operations - addition, subtraction, multiplication & division on mixed numbers, fractions or whole numbers and will produce a fractional result.

# Description
This calculator can take multiple numbers (>=2) and perform valid operations on them.

Some examples - 

a) Expression of 2 mixed numbers : 3_1/3 + 4_1/4 produces result as 7_7/12

b) Expression of more than 2 mixed numbers : 3_1/3 + 4_1/4 * 2 / produces result as 6_1/6

c) Expression of more than 2 mixed numbers, one of them being -ive : 3_1/3 + 4_1/4 * 2 / -3 produces result as 1/2

In case the expression has multiplication/division, it takes precendence over addition or subtraction.

Also, as can be seen from the example above, the calculator is designed to handle -ive numbers as well.

The supported operands for this calculator are + , - , * , /

# Execution
To execute the program, run the com.onelogin.main.Main class from the command line (java com.onelogin.main.Main)
The application would wait at prompt for the user to key in the expression to be calculated. When done, hit enter; and the result will be produced. The program then waits for the next expression and will keep repeating until the expression is : quit
