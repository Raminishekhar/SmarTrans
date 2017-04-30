# SmarTrans

SmarTrans is a very similar application like Uber or Ola but is targeted towards the common man who uses public transportation daily. It updates the user about the real time Location and Seat occupancy in the buses.

SmarTrans is a system developed to make the transit system efficient using information, communication,  and electronic technologies. It locates the real time coordinates of bus to passengers via user application using GPS so that they can determine when to go to the bus stop. Moreover sensors were installed at the base of bus-seats, so that seat occupancy of the public bus can be known. Thus it enables commuters to know beforehand how much occupied the bus is.

Bus View | Bus Stops | Geo Fencing  
:-------------------------:|:-------------------------:|:--------------------:
<img src="https://github.com/mehul-bechra/SmarTrans/blob/master/Screenshots/bus.png" width="300"> |<img src="https://github.com/mehul-bechra/SmarTrans/blob/master/Screenshots/bus-stop.png" width="300"> | <img src="https://github.com/mehul-bechra/SmarTrans/blob/master/Screenshots/geo-fence.png" width="300">

Hardware

For this appication hardware was designed using Rhydolabz Sim900, Arduino Uno and Neo 6m GPS module. The modules have been connected in the following way:

<img src="https://github.com/mehul-bechra/SmarTrans/blob/master/Screenshots/hardware.png">

In Arduino only one hardware serial port is available but that is used for Serial window and debugging purposes. Moreover the code cannot be dumped into Arduino while pins are connected to it (Pins 0 and 1). Thus choice has been made to use 2 Software Serial ports, one for GSM and another for GPS module. But both of these cannot function simultaneosly and whichever one starts first stops the other from accessing the software serial. Earlier in the code when I did this, the GSM module would start first and prevent the GPS from receiving any data. Therefore the solution to this is manually switch between the two ports using .listen() library method.

<img src="https://github.com/mehul-bechra/SmarTrans/blob/master/Screenshots/listen.png">

Software

To publish live GPS coordinates we are using Pubnub service.It works on the simple Publish/Subscribe method. Those who have subscribed to a particular channel will receive it as soon as it has been published. Currently we are using Channel Multiplexing (50 Subscribers max) but there is also a feature of Channel Groups through which upto 20,000 subcribers can be achieved, if ever the application needed scaling. The Arduino is publishing through the HTTP publish method which is achieved through SIM900 which provided internet connectivity.

<img src="https://github.com/mehul-bechra/SmarTrans/blob/master/Screenshots/pubnub.png">

The hardware needs to be installed on the buses that needs to be tracked with different channel names. These channel names should be subscribed in the code in android code. Initially all the buses will be visible. When a bus is clicked it will show the name of the bus. When that info window is clicked the remaining buses will disappear and the bus stops for the clicked bus will appear. When the bus stop is clicked a geo-fencing algorithm will nudge the user whenever the bus enters it.
