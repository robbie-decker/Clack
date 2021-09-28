# CS242-Project1

Authors: Robbie Decker and Chris Hickman
This is part one for a small project for the CS242 Advanced Programming Concepts in Java class: a messaging system similar to Slack.

If given a negative value for a port number then our code in its current state will function fairly normally. The functions toString for
example will still work, but hashcode could run into a problem since the arithmetic we are doing could lead to a negative number.
The problems will start to arise once we actually start sending and receiving data since most computers do not usually
have negative port numbers. In the future we would like to have some sort of logical check to see if the port number being input
is greater than or equal to zero. If the number is less than zero then we would want to throw some sort of error.

If given a null value for the Username then most functions work pretty okay. The problem occurs when a hashcode 
is made for the object with a null username. Since we use the inputs of the object to create a hash and .hashcode() can not be called on a null element
we run in an exception which stops the code. In the future we will once again want to have username inputs not be null so that we do not 
run into this problem. If this input is given as null then throw an approriate error.