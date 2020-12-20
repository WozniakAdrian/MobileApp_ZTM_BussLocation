# ZTM Gdańsk - Buss location
>It is an application designed for the android system. Using the application, you can search for any bus and tram stop in Gdańsk, or check the nearest one to our location. Data on the nearest buses, their estimated arrival time and whether they are delayed are presented in real time. You can also track the location of the bus using google maps.


## Table of contents
* [General info](#general-info)
* [Screenshots](#screenshots)
* [Technologies](#technologies)
* [Features](#features)
* [Status](#status)
* [Contact](#contact)



## General info
> So where is the data from? Real-time data is provided by ckan.multimediagdansk.pl, it includes, among others, the location of stops, buses, trams and estimated arrival times at a given stop. At the time of the first launch, the user is asked to assign location permissions. If the user does not agree, the application does not show the nearest stops but displays their list in an unordered way. After selecting the stop you are interested in, a list of buses that will stop at this stop is presented, the buses are displayed along with information about the planned arrival, when the bus can be realistically expected and whether the bus is delayed. After selecting the desired means of transport, the application takes the user to google maps where the current location of the user, the selected stop and the vehicle that has been selected for tracking is marked. The maps refresh automatically when they detect the bus movement and update its position. The precision of the application is 20s, this is due to the security measures imposed by the API.

## Screenshots
1. Overview
<p align="center">
 <img src="./video.gif" data-canonical-src="./video.gif" width="180" height="400" />
</p>
2. Main UI
<p align="center">
 <img src="./ui.jpg" data-canonical-src="./ui.jpg" width="180" height="400" />
</p>
3. Details UI
<p align="center">
 <img src="./details.jpg" data-canonical-src="./details.jpg" width="180" height="400" />
</p>
4. Map UI
<p align="center">
 <img src="./map.jpg" data-canonical-src="./map.jpg" width="180" height="400" />
</p>



# Technologies
* Java
* Android Studio
* MVVM - ViewModel & LiveData
* DI - Dagger2 
* UI - RecyclerView & MaterialDesign
* REST - Retrofit
* RxJava & RxAndroid & RxLocation
* GoogleMaps
* EasyPermissions



## Features
* List of the nearest stops for the user's device location
* Check the current delay of buses and trams at the selected stop
* The current location of the selected ZTM Gdańsk vehicle



## Status
Finished



## Contact
Feel free to contact Adrian Wozniak (adrianwozniak576@gmail.com)

