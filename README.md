# CS242-Project1

Authors: Robbie Decker and Chris Hickman
This is part one for a small project for the CS242 Advanced Programming Concepts in Java class: a messaging system similar to Slack.

--------------PART 1-----------------------

If given a negative value for a port number then our code in its current state will function fairly normally. The functions toString for
example will still work, but hashcode could run into a problem since the arithmetic we are doing could lead to a negative number.
The problems will start to arise once we actually start sending and receiving data since most computers do not usually
have negative port numbers. In the future we would like to have some sort of logical check to see if the port number being input
is greater than or equal to zero. If the number is less than zero then we would want to throw some sort of error.

If given a null value for the Username then most functions work pretty okay. The problem occurs when a hashcode 
is made for the object with a null username. Since we use the inputs of the object to create a hash and .hashcode() can not be called on a null element
we run in an exception which stops the code. In the future we will once again want to have username inputs not be null so that we do not 
run into this problem. If this input is given as null then throw an approriate error.

---------------PART 2----------------------
Below are the command line inputs to test the readClientData method in ClackClient

LISTUSERS
Implementation coming soon
SENDFILE Clack/src/test/Part2_document.txt
DONE
file name: Clack/src/test/Part2_document.txt, file contents: A digital computer can usually be regarded as consisting of three parts: (i) Store. (ii) Executive unit. (iii) Control. ...The executive unit is the part which carries out the various individual operations involved in a calculation. ...It is the duty of the control to see that...[the table of] instructions are obeyed correctly and in the right order. ...A typical instruction might say—"Add the number stored in position 6809 to that in 4302 and put the result back into the latter storage position." Needless to say it would not occur in the machine expressed in English. It would more likely be coded in a form such as 6809430217. Here 17 says which of various possible operations [add] is to be performed on the two numbers. ...It will be noticed that the instruction takes up 10 digits and so forms one packet of information..., user name: Chris, type: 3, date: Thu Oct 14 05:52:51 EDT 2021
If you run these command you will see that this creatse a FileClackObject with the content
from Part2_document.txt

~new session
WRONG INPUT 
DONE
message: WRONG INPUT , user name: Chris, type:2, date: Thu Oct 14 05:54:14 EDT 2021
This will create MessageClackData object with the input being whatever was typed in. 


ClackClient wrong = new ClackClient("Jimbo", "Office", 500);
Will throw exception since port is less than 800 

ClackClient wrong = new ClackClient("", "Office");
Will throw exception since username is empty

ClackClient wrong = new ClackClient("Jimbo", "");
Will throw exception since host name is empty