# NookipediaTuwaiqCapstone


![tuwaiq](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/4ee23fd6768f5e033d5e4bf49af6fae406eeef1b/images/tuwaiq.png)

This is the Individual Project for the final Tuwaiq CapStone for the Kotlin Track.

# Background Information


![animalcrossing](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/4ee23fd6768f5e033d5e4bf49af6fae406eeef1b/images/animalcrossing.jpg)

Animal Crossing:New Horizons is the latest installment in Nintendo's Animal Crossing series, released for their Nintendo Switch system on March 20, 2020.
It is a life-simulation game where the player moves to a deserted island. From there, it's up to them to transform it into a functioning community by crafting items for customization and inviting anthropomorphic animals to inhabit the island.
<p>&nbsp;</p>
<p>&nbsp;</p>


![phone](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/4c34403378fa3f81c7700448589cda2b871cdc05/images/phone.jpg)


In addition to that, the player can also engage in other activities. One of them is catching critters found on the island. These include fish, bug , and sea life.
The player is free to do whatever the want with what they caught, which will be automatically registerd to their in-game encyclopdeia called Critterpedia.
<p>&nbsp;</p>
<p>&nbsp;</p>

![critterpedia](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/4c34403378fa3f81c7700448589cda2b871cdc05/images/insidecritterpedia.jpeg)

<p>&nbsp;</p>
<p>&nbsp;</p>


The player can also either donate these critters to their island museum, or sell them to their island shop "Nook's Cranny" to earn bells, which is the in-game currency used on their island



Nook's Cranny            | Museum
:-------------------------:|:-------------------------:
![nookscranny](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/4c34403378fa3f81c7700448589cda2b871cdc05/images/nook.jpg)  |  ![museum](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/4c34403378fa3f81c7700448589cda2b871cdc05/images/museum.jpg)
<p>&nbsp;</p>
<p>&nbsp;</p>



However, if the player wishes to make more off of their bugs and fish, they can sell them to Flick and CJ respectively, who show up sporadically in their island

![flickandcj](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/4c34403378fa3f81c7700448589cda2b871cdc05/images/seabug.jpg)

<p>&nbsp;</p>
<p>&nbsp;</p>


One thing that the player can do is sell turnips at Nook's Cranny. These Turnips are bought from Daisy Mae on Sundays, and their price changes every week



![daisy](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/6789ca74670764f823383e234df8bd584f87499e/images/daisy.png)
<p>&nbsp;</p>
<p>&nbsp;</p>

their prices at Nook's Cranny change twice every day from Monday to Saturday at 8AM and 12PM, so the player must visit their store at these times and check the price to decide when they should sell their stock of turnips

![nookturnips](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/6789ca74670764f823383e234df8bd584f87499e/images/turnipsold.png)

# Nookipedia


Nookipedia is an Android app created using Android Studio and the Koltin Programming Language. It is an encyclopedia that contains all the information about the critters found in Animal Crossing:New Horizions to not only make catching them easier, but also to inform its user about their prices. It also allows for the user to favorite certain critters and add notes about them. Also the app will send notification to the user at 8AM and 12PM to remind them to check the current turnip prices.

Additionally, the app contains Arabic Language Support for the app's UI elements, allowing for easier navigation for Arabic speakers






# List of Technologies

This app was created using the Kotlin Programming language. It utilizes a RESTfull API to display information about the game, Firebase for authentication and for storing user related data, Picasso for showing images, and a localization library for translating UI elements

[Localization Library](https://github.com/akexorcist/Localization)

[Picasso](https://github.com/square/picasso)

[Animal Crossing API](https://api.nookipedia.com/)

[Firebase](https://firebase.google.com/)





# Wireframes 

The wireframes for this app were created using Figma: [Link](https://www.figma.com/file/ndA3uLv49eJ1u3tOcoqU8i/Untitled?node-id=0%3A1)
<p>&nbsp;</p>
<p>&nbsp;</p>

![splash](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/3a12e910650a7293f4afbb6cf1ad400d13898034/Splash.png)
<p>&nbsp;</p>
<p>&nbsp;</p>


This is the splash Screen, the first screen of the app
<p>&nbsp;</p>
<p>&nbsp;</p>


Login         | Register
:-------------------------:|:-------------------------:
![login](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/3a12e910650a7293f4afbb6cf1ad400d13898034/Login.png) | ![register](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/3a12e910650a7293f4afbb6cf1ad400d13898034/Register.png)
<p>&nbsp;</p>
<p>&nbsp;</p>


If the user hasn't already logged in beforehand, they will be taken to the login screen after the splash screen.Where they can either login, or create a new account

<p>&nbsp;</p>
<p>&nbsp;</p>

Fish       | Fish details
:-------------------------:|:-------------------------:
![fish](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/3a12e910650a7293f4afbb6cf1ad400d13898034/Fish.png)|![fishdetails](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/3a12e910650a7293f4afbb6cf1ad400d13898034/Fish%20details.png)
<p>&nbsp;</p>
<p>&nbsp;</p>
The first list of critters the user can browse through after a successful login is the fish list. They can view every fish in the game. If they wish to view a certain fish's details, they will need to click on the desired fish


<p>&nbsp;</p>
<p>&nbsp;</p>

Bugs       | Bug details
:-------------------------:|:-------------------------:
![bugs](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/3a12e910650a7293f4afbb6cf1ad400d13898034/Bug.png)|![bugdetails](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/3a12e910650a7293f4afbb6cf1ad400d13898034/Bug%20Details.png)
<p>&nbsp;</p>
<p>&nbsp;</p>

Sea       | Sea details
:-------------------------:|:-------------------------:
![sea](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/3a12e910650a7293f4afbb6cf1ad400d13898034/Sea.png)|![seadetails](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/3a12e910650a7293f4afbb6cf1ad400d13898034/Sea%20Details.png)








![favorite](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/a99e2d0ce6d5d16b1af5a3b01206d695ac79f369/favorites.png)

If the user added a critter to their favorite list, this is where they will find them. From here they can add notes to their entries, or delete them from their list

<p>&nbsp;</p>
<p>&nbsp;</p>

![profile](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/a99e2d0ce6d5d16b1af5a3b01206d695ac79f369/Profile.png)






# User Story






# Planning and Development Process








# Unsolved Problems






# Favorite Functioanlity









