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

---------------PART 3----------------------
Case 1
This tests was done on a single machine with localhost.

Client:
$ java -jar out/artifacts/ClackClient/ClackClient.jar robbie
Client is now running..
hello?
hello?
what is up?
what is up?
DONE
DONE

Server:
$ java -jar out/artifacts/ClackServer_jar/ClackServer.jar
Server now running on port: 7000
New Connection from: /127.0.0.1
Data from client: hello?
Data from client: what is up?
Data from client: DONE


Chris did not have access to remote desktop (Free windows iso does not have this feature) so for these test cases we were using my laptop to connect to
my desktop PC. This still shows off the two different machines connecting and communicating.

Case 2 (No port number specified)
Client:
​​Roberts-MacBook-Pro:CS242-Project1 robbob$ java -jar out/artifacts/ClackClient/ClackClient.jar robbie@128.153.221.52
Client is now running..
is this a new connection?
is this a new connection?
wow!
wow!
DONE
DONE

Server:
$ java -jar out/artifacts/ClackServer_jar/ClackServer.jar 
Server now running on port: 7000
New Connection from: /128.153.167.78
Data from client: is this a new connection?
Data from client: wow!
Data from client: DONE

Case 3 (With port number)
Client:
Roberts-MacBook-Pro:CS242-Project1 robbob$ java -jar out/artifacts/ClackClient/ClackClient.jar robbie@128.153.221.52:5000
Client is now running..
Using a new port num!
Using a new port num!
COOOOOL!!!!!!
COOOOOL!!!!!!
DONE
DONE


Server:
$ java -jar out/artifacts/ClackServer_jar/ClackServer.jar 5000
Server now running on port: 5000
New Connection from: /128.153.167.78
Data from client: Using a new port num!
Data from client: COOOOOL!!!!!!
Data from client: DONE


Extra tests ClackClient (all done on localhost)

With no args:

Client:
$ java -jar out/artifacts/ClackClient/ClackClient.jar
Client is now running..
hi
hi
It is still working!
It is still working!
DONE;
DONE

Server:
$ java -jar out/artifacts/ClackServer_jar/ClackServer.jar
Server now running on port: 7000
New Connection from: /127.0.0.1
Data from client: hi
Data from client: It is still working!
Data from client: DONE

----------------Part-4-------------------
We need two classes for threading because we need separate threads running for each client, and then one for the user. 
This can only be achieved by multi-threading, which must be done with separate classes. We could have used anonymous classes
as well, but this is more efficient.

There should be a separate class to receive data from the server because the server needs to be handling the clients, while when
each new client receives a connection, it needs access to that data. All the client needs to do is send data. This class is called
a "listener" because it's listening for data from the server.

We need a separate thread for each client because we want the clients to be running concurrently. We could technically handle
all clients in the server with an anonymous class, but this would be inefficient, it's better to abstractly create another
class and put it into a data structure. ClientSideServerListener is different from ServerSideClientIO because the Client side 
receives data from the client and sends it to the server, while the server side broadcasts the data to all the clients.

Broadcast and remove are synchronized so that you can close an object while the server is running, therefore allowing
the server to continue broadcasting. We're accessing a shared object, so we have to release the lock if broadcast is continuously
running.

The new methods implemented to get the LISTUSERS functionality working is the listUsers method in ClackServer. This goes through the ListArray, checks if the data is null, and if it is not null then it appends the userName using the ClackData method getUserName to the sting users. Once we iterate through the entire ListArray we used the passed in value clientIO to set the dataToSendToClient of this object to the MessageClackData object with the all the users as the message. What is cool about doing it this way is that it will only send the message to the client that asked for the LISTUSERS info. The listUsers method was then implemented in the recieveData method in ServerSideClientIO where if the data read equals "LISTUSERS" we call the listUsers method from the server passing in the ServerSideClientIO object. 


All tests were screenshotted and put into the Clack/src/test/Part4-images folder.