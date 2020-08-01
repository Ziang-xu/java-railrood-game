Reviewer: Wenpei Wang(u6684474)
Component: task6
Author: Ziang Xu(u6645802)

Review Comments:

1. The names of variables are clear, and I like the method of "List.clone", which provides a good way to check the connected tiles by Task5 method.

2. I think the best part of Task6 code is the self-defined public variable in Tile file, called hasConnectedNeighbour and isConnectedToExit. One is used with the Task5 to check whether the tile is connected to the neighbor tiles and another variable used to check whether the tiles in board string connect the exits(valid connection). When both of the conditions are met, this takes will return a result(true), else will return false.

3. According to the Version Control of IntelliJ, he just commits the code for once when he finished all of the tasks. It's better to commit frequently.

4. The class and method structure is not good, even it can pass all the tests of Task6 and have a good function. It is a long and complex code and it is hard for readers to understand at first. In fact, this class can be divided into several methods, which will return the same result as the single class but will have a clear and better code structure.
The code lack of comments, so it is a little difficult to understand at first.

5. One case(Duplication test, which is a new test provide several days ago in Gitlab) has not been considered. So it can not pass the new test, and I write a new method for solving this problem.