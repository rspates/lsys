# lsys

lsys is a maven project coded in groovy.  

To see it work, open images.html in a browser, then invoke the drawing.lsys.LSystemTest.testLoop() method.  The result should be a wiki fractal plant image in the browser changing its shape due to random angle corrections applied to the drawing.

 The system consists of a borrowed kbhadury/LSystem implementation in java, divorced from its gpdraw base, and retrofitted on a home-grown graphics system implementing enough semantics to support the turtle drawing language requirements of the lsys.  The graphics subsystem is in two layers.  The layer sitting on java's Graphics2D API implements the required turtle graphics functions but using the java coordinate space.  A second "shim" layer underlying the lsys translates orientation and coordinate space between the two.
