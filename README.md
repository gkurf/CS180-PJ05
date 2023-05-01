# Stride180 Marketplace - CS180 PROJECT 5
## Project Description
Stride 180 is an online shoe shopping system where customers and sellers can interact with each other through messaging. This program is concurrent, utilizing network IO and a full graphical user interface (GUI). Customers and sellers can create new or access existing accounts, and sellers can create multiple stores. Customers can view a list of stores or search for sellers to message, while sellers can view a list of customers to message or message a specific customer. All users can also view messaging statistics, block other users, or choose to become invisible to them.

## Submission information
While everyone split up and completed the work, only two people are submitting the deliverables:
- Project 5 Report: Submitted by Sanya Buti on Brightspace
- Project 5 Presentation: Submitted by Jack Burns on Bongo
- All classes and README: Submitted by Gabe Kurfman on Vocareum

## Compilation information
All the described classes below will need to be imported as well as all necessary libraries. To use the Stride180 application, the first step is to compile and run the Server class that handles all user data.

    $ cd CS180-PJ05
    $ javac Server.java
    $ java Server

Once the Server is running, you can open and run as many instances of the GUI Stride180 class as needed.

    $ javac Stride180.java
    $ java Stride180

## Class Descriptions
### Stride180.java (Main)
#### Functionality
This class acts as the marketplace and implements the GUI experience. This is the class that is run and will guide the user through whatever tasks they do. All menus, inputs, and outputs of the program are done via this class. All input regulations and some exception handlings are done in Stride180. This class also uses methods to aid log in capabilities, search capabilities, and messaging capabilities. 
#### Testing
This class was mainly tested through manual input to test integration, exception handling, and cohesion between all the other classes and methods. Different members of the team took turns trying to break the code and throw incorrect input. The team then evaluated how the main class as well as all associated classes performed and made the necessary adjustments.
#### Relationship with other Classes
This class is the main hub where the user can interact with the program. It creates instances of User and Users which are then utilized to call all necessary methods and functions to achieve the desired user experience and program capabilities. This class communicates the inputs of the physical user to different classes via parameters which then translates back the necessary outputs. 

### GUI.java
#### Functionality:
This class utilizes Java's Swing GUI capabilities to create graphical elements for the user to interact with. It implements buttons, labels, text boxes, and spinners to provide an easy and effective user interface. It is used at any point where a graphical window is needed to display information or request input from the user. 
#### Testing
Testing of the GUI class was done through independently testing each method and then testing the overall implementation of the code into the general project. The testing included basic output and exception handling.
#### Relationship with other Classes
This class is called by Stride180.java (main), Users.java, User.java, and Statistics.java at various points during program execution. It is used at any point where a graphical window is needed to display information or request input from the user. 

### Message.java
#### Functionality
This class does the base capabilities of the messaging feature between the customers and sellers. Some of the capabilities the class has are the ability to create messages, get the timestamp of created messages, edit messages, delete messages, export to CSV, import from TXT, and get customers and sellers. All the capabilities are individual methods in this message class that all interact with stored txt files that hold all the information. The use of files gives the ability that the program can shut off and switch on again with all the information and previous conversation saved. 
#### Testing
All the testing in this class was manually inputted. The first iterations of testing were with the goal of getting basic messaging capabilities fixed and securing the ability to read and write to new files with the specified naming convention. The second round of testing was done through exception handling and with the goal of not having unexpected errors that ruin the capability of the program.
#### Relationship with other Classes
This messaging feature is used through other classes such as User and Stride180 due to the importance that it has on the core functionality of the marketplace. 

### Server.java
#### Functionality
This class provides the framework for Stride180 to be a network IO-based application. It utilizes Java PrintWriters and BufferedReaders to save data in a central location, which is then accessed via clients in the Stride180 main class. 
#### Testing
All the testing in this class was manually inputted. The second round of testing was done through exception handling and with the goal of not having unexpected errors that ruin the capability of the program.
#### Relationship with other Classes
The server class is indirectly used by every other class in the Stride180 program. Specific calls to the network memory occur in the Users.java, Statistics.java, and Store.java, where data is required to be stored or accessed.

### Statistics.java
#### Functionality:
This class gives unique statistics and data according to whether the user is a customer or a seller. For the seller dashboard, the program outputs the store name, number of messages received from each customer, and the top 10 most common words sent to the store for each store the seller has. For the customer dashboard, the program outputs The list of stores in order of the number of messages received from the customer in descending order, listing the name, the number of messages received from the customer, the number of messages sent by the store, the number of customers who sent messages to the store, and the top 5 most common words used in all messages sent to the store by all customers for each store.
#### Testing
All testing is done through independently testing each method and then testing the overall implementation of the code into the general project. The testing included basic output and exception handling. This testing is done through testing other class capabilities as the outputs of the dashboard are dependent upon them.
#### Relationship with other Classes
This class is reliant upon the messaging features, the store features, the user inputs and outputs, and just about every other class as it displays the statistics of the whole marketplace.

### Store.java
#### Functionality
This class defines a “store” and has private fields for a "User" object, a store name, and the number of messages received and sent. The class has a constructor that takes a "User" object and a store name as arguments, and it saves the store name and the username of the user via network IO.
#### Testing
The testing for this class was done through manual input in direct relationship with the user class, mainly for sellers. Like all the other code in this project, the testing and debugging was done on the grounds of error checking and functionality.
#### Relationship with other Classes
The store class is connected to other classes such as Stride180 and the User class as both utilize their capitals to both create new stores and call existing stores. 

### User.java
#### Functionality
The class contains fields such as username, password, userType, blockedUsers, invisibleUsers, and storeList. It also has a constructor and getter and setter methods for these fields. The class has methods for getting messages between the current user and another user, adding stores to a user's storeList, adding blocked users and invisible users to a user's list, converting everything to a CSV format, and welcoming the user. The class overrides the toString() method to provide a String representation of the User object. The class simulates an individual user and creates instances of other classes to complete the capabilities which is then translated back to an output for the Stride180 class to use. 
#### Testing
The main testing of this class was done through the integration between this, Stride180, and all specific classes with specific capabilities. Like all the other code in this project, the testing and debugging was done on the grounds of error checking and functionality.
#### Relationship with other Classes:
The user class acts as an intermediary for the Stride180 input/output class and all the subsequent functional classes that need to be called. The user information for the physical user is stored via this class and defines the customer interaction and calling of other classes. 

### Users.java
#### Functionality
This class is responsible for storing a collection of User objects and applying methods to them. This class is the key driving force of the entire project and uses User to act as an intermediary between the Stride180 class and other classes. This has the ability of loading data from network IO, saving data to the network, logging in a user, creating a new user, getting all current Users, getting a list of all users, getting a data filename, setting a data filename, getting the current user, and setting the current user. All these capabilities and functionalities are done through individual methods. 
#### Testing
This class was one of the classes with the most testing during the multiple iterations of this project. The main key features of the program are in this class thus the issue was not only to test and debug functional issues but integration issues between classes and people. Like all the other code in this project, the testing and debugging was done on the grounds of error checking and functionality.
#### Relationship with other Classes
This class is directly related to the Stride180 class as well as the User class. It utilizes both classes as well as all subsidiary classes to be the driving force for the program and project.
