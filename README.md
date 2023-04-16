# Stride180 Marketplace - CS180 PROJECT 4
## Project Description
Stride 180 is an online shoe shopping system where customers and sellers can interact with each other through messaging. Customers and sellers can create new or access existing accounts, and sellers can create multiple stores. Customers can view a list of stores or search for sellers to message, while sellers can view a list of customers to message or message a specific customer. However, if a user has blocked another user or has chosen to be invisible, they cannot interact with each other. Users can also view messaging statistics, block other users, or choose to become invisible to them.

## Submission information
While everyone split up and completed the work, only two people are submitting the deliverables:
- Project 4 Report: Submitted by Sanya Buti on Brightspace
- All classes and README: Submitted by Gabe Kurfman on Vocareum

## Compilation information
All of the described classes below will need to be imported and compiled as well as all necessary libraries. In terms of user interface, the only class that needs to be run is the Stride180 class. It acts as the main user interfacing class and all necessary capabilities, prompts, and outputs will be derived from that class. While all classes need to be compiled, the Stride180 class is the only one that needs to be run. 

    $ cd CS180-PJ04
    $ javac Stride180.java
    $ java Stride180

## Class Descriptions
### Stride180.java (Main)
#### Functionality
This class acts as the marketplace and creates the user experience. This is the class that is run and will guide the user through whatever tasks they do. All menus, inputs, and outputs of the program are done via this class. All input regulation and some exception handling are done in Stride180. This class also uses methods to aid log in capabilities, search capabilities, and messaging capabilities. 
#### Testing
This class was mainly tested through manual input to test integration, exception handling, and cohesion between all the other classes and methods. Different members of the team took turns trying to break the code and throw incorrect inputs. The team then evaluated how the main class as well as all associated classes performed and made the necessary adjustments. The class was also tested via the RunLocalTest class.
#### Relationship with other Classes
This class is the main hub of where the user can interact with the program. It creates instances of User and Users which are then utilized to call all necessary methods and functions to achieve the desired user experience and program capabilities. This class communicates the inputs of the physical user to different classes via parameters which then translates back the necessary outputs. 

### Statistics.java
#### Functionality:
This class gives unique statistics and data according to if the user is a customer or a seller. For the seller dashboard, the program outputs the store name, number of messages received from each customer, and the top 10 most common words sent to the store for each store the seller has. For the customer dashboard, the program outputs The list of stores in order of the number of messages received from the customer in descending order, listing the name, the number of messages received from the customer, the number of messages sent by the store, the number of customers who sent messages to the store, and the top 5 most common words used in all messages sent to the store by all customers for each store.
#### Testing
All testing is done through independently testing each method and then testing the overall implementation of the code into the general project. The testing included basic output and exception handling. This testing is done through testing other class capabilities as the outputs of the dashboard are dependent upon them.
#### Relationship with other Classes
This class is reliant upon the messaging features, the store features, the user inputs and outputs, and just about every other class as it displays the statistics of the whole marketplace.

### Message.java
#### Functionality
This class does the base capabilities of the messaging feature between the customers and sellers. Some of the capabilities the class has is the ability to create messages, get the timestamp of created messages, edit messages, delete messages, export to CSV, import from TXT, and get customers and sellers. All of the capabilities are individual methods in this message class that all interact with stored txt files that hold all the information. The use of files gives for the ability that the program can shut off and switch on again with all of the information and previous conversation saved. 
#### Testing
All of the testing in this class was manually inputted. The first iterations of testing were with the goal of getting basic messaging capabilities fixed and securing the ability to read and write to new files with the specified naming convention. The second round of testing was done through exception handling and with the goal of not having unexpected errors that ruin the capability of the program.
#### Relationship with other Classes
This messaging feature is used through other classes such as User and Stride180 due to the importance that it has on the core functionality of the marketplace. 

### Store.java
#### Functionality
This class defines a “store” and has private fields for a "User" object, a store name, and the number of messages received and sent. The class has a constructor that takes a "User" object and a store name as arguments, and it saves the store name and the username of the user to a file called "sellerData.txt" using a FileOutputStream and a PrintWriter. When a new store is created, information about the store and the user who created it is stored in a file for later use. 
#### Testing
The testing for this class was done through manual input in direct relationship with the user class, mainly for sellers. Like all the other code in this project, the testing and debugging was done on the grounds of error checking and functionality
#### Relationship with other Classes
The store class is connected to other classes such as Stride180 and the User class as both utilize its capitals to both create new stores and call existing stores. 

### User.java
#### Functionality
The class contains fields such as username, password, userType, blockedUsers, invisibleUsers, and storeList. It also has a constructor and getter and setter methods for these fields. The class has methods for getting messages between the current user and another user, adding stores to a user's storeList, adding blocked users and invisible users to a user's list, converting everything to a CSV format, and welcoming the user. The class overrides the toString() method to provide a String representation of the User object. The class simulates an individual user and creates instances of other other classes to complete the capabilities which is then translated back to an output for the Stride180 class to use. 
#### Testing
The main testing of this class was done through the integration between this, stride180, and all specific classes with specific capabilities. Like all the other code in this project, the testing and debugging was done on the grounds of error checking and functionality.
#### Relationship with other Classes:
The user class acts as an intermediary for the Stride180 input/output class and all the subsequent functional classes that need to be called. The user information for the physical user is stored via this class and defines the customer interaction and calling of other classes. 

### Users.java
#### Functionality
This class is responsible for storing a collection of User objects and applying methods to them. This class is the key driving force of the entire project and also uses User to act as an intermediary between the Stride180 class and other classes. This has the ability of loading data, saving data, logging in a user, creating a new user, getting all current Users, getting a list of all users, getting a data filename, setting a data filename, getting the current user, and setting the current user. All of these capabilities and functionalities are done through individual methods. 
#### Testing
This class was one of the classes with the most testing during the multiple iterations of this project. The main key features of the program is in this class thus the issue was not only to test and debug functional issues but integration issues between classes and people. Like all the other code in this project, the testing and debugging was done on the grounds of error checking and functionality.
#### Relationship with other Classes
This class is directly related to the stride180 class as well as the User class. It utilizes both classes as well as all subsidiary classes to be the driving force for the program and project. 

### RunLocalTests.java
#### Functionality
This class provides test cases for the program to run with the expected output in mind. The program tests this output with the given output and derives whether or not they pass each case.
    
    $ cd CS180-PJ04
    $ javac RunLocalTest.java
    $ java RunLocalTest
    
#### Testing
The testing for this was done by manually comparing the test cases and the output of this RunLocalTests class. Due to this the team decided to just switch to manually testing.
#### Relationship with other Classes
This class interacts with the input and output features of the Stride180 class.
