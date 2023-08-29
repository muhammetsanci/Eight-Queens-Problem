# Eight Queens Problem
A practical Java solution for eight queens problem. 

## Problem
Eight queens problem is a classic puzzle in which the goal is to place eight chess queens on an 8x8 chessboard in such a way that no two queens threaten each other.
In other words, no two queens can be placed in the same row, column, or diagonal.  

This problem is often used to illustrate concepts in combinatorics and algorithms, as well as to showcase various problem-solving techniques.

## Solution Mechanism

In this solution, algorithm uses gradient descent optimalism.

1. A random state is generated with each queen in a vertical column. The reason for this, no matter the solution is, 8 queens placed at different rows and columns for not threating each other. Therefore, this is a good way of placement for starting state.

> Please note that representation of states in this algorithm uses index numbers. For example a state represented as `1 - 6 - 4 - 7 - 3 - 0 - 5 - 2` means queen at the first column is in the 1st cell in that column; queen at the second column is in the 6th cell in that column, and so on.

2. The “number of threats” is calculated for current state and for each cell. Let's say in a particular column, number of threats values are `15`, `10`, `12`, `17`, `8`, `11`, `16` and number of threats currently is `11`. This basically means if the queen in that column moves into cell with the value `8`, number of threats for that state will be 8; which is better than 11.
3. Lowest value of all 56 values (8 x 8 = 64 cells; 64 - 8 queen = 56 cells is available) is chosen as the most optimal option, then after queen in that row is moved into this optimal cell.
4. Checked if optimization is stucked at local optimum point; if there is stilness, return to step 1 and randomize queens.
5. If the most optimal state is found, which is 0 threats, the model is terminated; if not, it is repeated from the 2nd step.
