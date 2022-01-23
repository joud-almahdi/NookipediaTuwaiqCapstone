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


![fishshadow](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/40cd99c3679cae2fad4751455b92e80008651f90/images/fishshadow.jpg)


Fish can either be found in rivers or by the sea. The player needs to throw their fishing rod at the shadow that can be seen, which indicates the existance of a fish. The bigger the shadow the bigger the fish.
<p>&nbsp;</p>
<p>&nbsp;</p>

![bugcatching](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/40cd99c3679cae2fad4751455b92e80008651f90/images/bugcatch.jpg)

Bugs can be caught with a net, and can be seen either flying, hiding in trees, or hanging on palm trees
<p>&nbsp;</p>
<p>&nbsp;</p>


![seashadow](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/b256589fa93b9ac18d2f3370b0f67a4d26107831/images/seashadow.jpg)

Similar to fish, the existance of sea creatures is indicated by their shadows. In this case, they can be found on the sea floor and the player must dive to catch them. Sea creatures can also run away from the player, the speed of which varies from a creature to another



After every successful catch, the player gets to read a quote unique to what critter they caught



![critterquote](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/bbf033b5a66374c62581d9214fd0777949ce9480/images/caughtathing.jpg)



The player can also either donate their caught critters to their island museum, or sell them to their island shop "Nook's Cranny" to earn bells, which is the in-game currency used on their island



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


[Retrofit](https://github.com/square/retrofit)

```
dependencies {
 implementation 'com.google.firebase:firebase-auth-ktx:21.0.1'
 implementation 'com.google.firebase:firebase-firestore-ktx:24.0.0'
 implementation 'com.google.firebase:firebase-messaging-ktx:23.0.0'
 implementation 'com.squareup.retrofit2:retrofit:2.9.0'
 implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
 implementation 'com.squareup.retrofit2:retrofit:2.9.0'
 implementation 'com.akexorcist:localization:1.2.11'
 implementation 'com.squareup.picasso:picasso:2.71828'

}
```

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
![fish](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/3a12e910650a7293f4afbb6cf1ad400d13898034/Fish.png)|![fishdetails](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/39a67cd1a01295e7b112550e1c00c3fec6743974/images/Fish%20details.png)
<p>&nbsp;</p>
<p>&nbsp;</p>
The first list of critters the user can browse through after a successful login is the fish list. They can view every fish in the game. If they wish to view a certain fish's details, they will need to click on the desired fish


The user can learn about where they can find a pirticular fish, its shadow sizes, its price at Nook's Cranny, and CJ's price

The star icon is what enables the user to add a critter to their favorite list, and the present icon lets them share the critter's entry on [Nookipedia.com](https://nookipedia.com/wiki/Main_Page) to others




<p>&nbsp;</p>
<p>&nbsp;</p>

Bugs       | Bug details
:-------------------------:|:-------------------------:
![bugs](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/3a12e910650a7293f4afbb6cf1ad400d13898034/Bug.png)|![bugdetails](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/39a67cd1a01295e7b112550e1c00c3fec6743974/images/Bug%20Details.png)

The user can navigate to the bugs and sea creatures list with the navigation items at the bottom of their screen. The bug details view shows where they can find a bug, as well as its prices when sold to Nook's Cranny or Flick.
<p>&nbsp;</p>
<p>&nbsp;</p>

Sea       | Sea details
:-------------------------:|:-------------------------:
![sea](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/3a12e910650a7293f4afbb6cf1ad400d13898034/Sea.png)|![seadetails](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/39a67cd1a01295e7b112550e1c00c3fec6743974/images/Sea%20Details.png)


Similarly, the sea creatures list can be reached  with the navigation items, the details view shows a creature's shadow size, movement speed, and its price at Nook's Cranny.








![favorite](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/39a67cd1a01295e7b112550e1c00c3fec6743974/images/favorites.png)

If the user added a critter to their favorite list, this is where they will find them. From here they can add notes to their entries, or delete them from their list

<p>&nbsp;</p>
<p>&nbsp;</p>

![profile](https://github.com/joud-almahdi/NookipediaTuwaiqCapstone/blob/a99e2d0ce6d5d16b1af5a3b01206d695ac79f369/Profile.png)

This is the Profile view, which lets the user view their personal user ID, and their Email Address






# User Story

*As a completionist, I want to know how and where to find any critter in the game so that I can complete my critterpedia

*As someone who likes to keep notes, I want to have notes about any critter I want so that I can view them later for reference

*As someone who is forgetful and wants to sell their turnips, I want to be reminded whenever their prices changes to that I can check them immediately

*As someone with their own prefrences, I want to be able to create an account so that I can log in to it and keep my preferences saved in that account

*As someone who has trouble understanding English, I want to be able to change the app's language to Arabic so that I can navigate through it more easily




# Planning and Development Process

The outline for my development plan was similar to this:


1-Design wireframe


2-Design XML


3-Code requirements


4-Code Any possible Extras


5-Cleanup and testing


And starting with step 2, everything related to this app had been backed up here on this Github repository. Per the requirements from Tuwaiq, this project was built using the MVVM architecture.However, the code was initially written in the fragments as is to make sure it worked first, and then was transferred to a repository and a viewmodel later.

The API used required a key, which is obtained by sending a request form to the API's creators,who then would send the key to the email address enterd in the form. The API only contained GET requests while one requirement was to include full CRUD support.This is how the idea of allowing the user to favorite critters and add notes came to be.Using Firestore, databases can be created in the form of collection, and every record is a document inside the collection.When the user adds a critter to the favorites list, this critter will be added to the favorites collection alongside their userid, with the notes field being empty by default. Storing the userid is what allows the app to only show critters added by the currently logged in user. When the user wishes to add a note, they are simply editing the notes field, which originally is just an empty string, though they can of course edit their notes as many times as they want.


One extra that was implemented was a second lanuage support. Initally, the app would only change the language to Arabic if it was the phone's chosen language,doing which required no coding and only a translation file was can be easily created with Android Studio. However, with the use of an external library, not only can the language now be changed manually depending on the user's preference, but it is also saved in the phone's memory, meaning that there was no need to use shared preferences 

One problem that was encountered  was that the hint text would not change its alignment when changing the language to Arabic. This is supposedly due to a bug in Android Studio.

This was fixed by including the following in the XML code


```
 android:textAlignment="viewStart"
```








# Unsolved Problems

One plan was to include notifications for in-game events, such as Halloween in October and Toy Day in December.However, Firebase only allows for 10 notifications to be active at a time,which was not enough, so the idea was scrapped and was replaced with turnip price notifications.


There are two types of notifications in this app: In-app notifications which are launched with every successful login and/or signup, and firebase notifications which are launched at a set time when turnip prices change. Firebase notifcations launch inconsistently, meaning that they are not sent everyday that they are meant to. This only seems to happen when the time is set to match the the recipient's timezone


Due to the nature of MVVM, notes get updated immediately after being changed.However, this sometimes causes the app to lag a bit,occasionally showing a previous version of the list before reverting back to the new,correct list .This could be the result of phone space,but I nevertheless would like to improve upon this


The image in the profile view is static and cannot be changed,I would like to be able to store images in firebase to allow for changing it






# Favorite Functioanlity


Providing multiple language support was something that I knew existed before this project, but I was not aware of how easy it was to implement. All it needed was an extra Strings file and required next to no coding.


When the user wishes to add a note, they will be promted to enter said note in a dialog box.If a note already exists that is not the default empty one, the note will show up as an edited text instead of the the generic hint. After they are done writing, the note will automatically show up in their list thanks to MVVM and the usage of live data






