# Fractional-Calculator
This is a Java program that will perform basic operations - addition, subtraction, multiplication & division on mixed numbers, fractions or whole numbers and will produce a fractional result.

## Description
This calculator can take multiple numbers (>=2) and perform valid operations on them.

Some examples - 

a) Expression of 2 mixed numbers : **_3_1/3 + 4_1/4_** produces result as **_7_7/12_**

b) Expression of more than 2 mixed numbers : **_3_1/3 + 4_1/4 * 2 / 3_** produces result as **_6_1/6_**

c) Expression of more than 2 mixed numbers, one of them being -ive : **_3_1/3 + 4_1/4 * 2 / -3_** produces result as **_1/2_**

In case the expression has multiplication/division, it takes precendence over addition or subtraction.

Also, as can be seen from the example above, the calculator is designed to handle -ive numbers as well.

The supported operands for this calculator are **+ , - , * , /**

## Execution
To execute the program, run the **_com.onelogin.main.Main_** class from the command line (**_java com.onelogin.main.Main_**)
The application would wait at prompt for the user to key in the expression to be calculated. When done, hit enter; and the result will be produced. The program then waits for the next expression and will keep repeating until the expression is : **quit**
