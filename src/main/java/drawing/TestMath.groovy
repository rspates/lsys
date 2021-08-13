package drawing

import static org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class TestMath {
	

// https://math.stackexchange.com/questions/656500/given-a-point-slope-and-a-distance-along-that-slope-easily-find-a-second-p
	@Test
	void test() {
		
		def x=5,y=5
		def angle = 45
		def d = 1
		def xy = pointAngleDistance(x,y,angle,d)
		printf """
x = $x
y = $y
angle = $angle
d = $d
x2 = ${xy[0]}
y2 = ${xy[1]}
"""
		//testDrawLine(new Double(xy[0]).intValue(), new Double(xy[1]).intValue())
		testDrawLine( xy[0], xy[1])
	}
	
	def testDrawLine(double x, double y) {
		testDrawLine(new Double(x).intValue(), new Double(y).intValue())
	}
	
	def testDrawLine(int x, int y) {
		println "here"
	}
	
	def pointAngleDistance(x,y,angle,d) {
			
		def m = Math.tan(Math.toRadians(angle))
		def b = y - m * 0
//		println """
//m = $m
//b = $b
//"""
		def x2 = d * ( 1 / ( 1 + (m * m) )) + x
		def y2 = m * x2 + b

		[x2,y2]
	}

	@Test
	void test0() {
		
		def x=5,y=5
		def angle = 45
		def d = 1
		
		def m = Math.tan(Math.toRadians(angle))
		def b = y - m * 0
		 
		def x2 = d * ( 1 / ( 1 + (m * m) )) + x
		def y2 = m * x2 + b

		
		printf """
x = $x
y = $y
angle = $angle
d = $d
m = $m
b = $b
x2 = $x2
y2 = $y2
"""
	}

}
