street = primitiveCity/street/
street2 = primitiveCity/Street
flags = -ea
RoadGen.class : RoadGen.java street/StreetD.class  streetTest/Test.class primitiveCityTest/PrimitiveCityTest.class
	javac RoadGen.java

streetTest/Test.class : streetTest/Test.java street/StreetD.class 
	javac streetTest/Test.java

street/StreetD.class : street/StreetD.java other/Other.class geometry/Geo.class geometry/GeoDraw.class  
	javac street/StreetD.java

other/Other.class : other/Other.java						
	javac other/Other.java
	
#**************************************************************************
primitiveCityTest/PrimitiveCityTest.class : primitiveCityTest/PrimitiveCityTest.java primitiveCity/PrimitiveCity.class primitiveCity/PCityComponent.class
	javac primitiveCityTest/PrimitiveCityTest.java
	
#**************************************************************************
geometry/Geo.class : geometry/Geo.java
	javac geometry/Geo.java

geometry/GeoDraw.class : geometry/GeoDraw.java
	javac geometry/GeoDraw.java
#**************************************************************************

mainFrame/MainFrame.class : mainFrame/MainFrame.java primitiveCity/PCityComponent.class
	javac mainFrame/MainFrame.java

primitiveCity/PCityComponent.class : primitiveCity/PCityComponent.java
	javac primitiveCity/PCityComponent.java

#**************************************************************************	
primitiveCity/PrimitiveCity.class : primitiveCity/PrimitiveCity.java $(street)UsualStreet.class $(street)BlindStreet.class $(street)DoubleBlindStreet.class primitiveCity/Intersection.class
	javac primitiveCity/PrimitiveCity.java
	
$(street)UsualStreet.class : $(street)UsualStreet.java $(street2).class
	javac $(street)UsualStreet.java
	
$(street)BlindStreet.class : $(street)BlindStreet.java $(street2).class
	javac $(street)BlindStreet.java
	
$(street)DoubleBlindStreet.class : $(street)DoubleBlindStreet.java $(street2).class
	javac $(street)DoubleBlindStreet.java
	
$(street2).class : $(street2).java
	javac  $(street2).java

primitiveCity/Intersection.class : primitiveCity/Intersection.java
	javac primitiveCity/Intersection.java
#**************************************************************************
	
generator/Generator.class : generator/Generator.class
	javac generator/Generator.class
#**************************************************************************

