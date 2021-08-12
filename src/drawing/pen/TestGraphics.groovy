package drawing.pen

import static org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class TestGraphics {

	@Test
	void test4() {
		def g = new GraphicsShim(1000,1000,java.awt.Color.cyan)
		g.forward(150)
		g.turnRight(45)
		g.forward(100)
		g.turnRight(45)
		g.forward(50)
		g.save("lTest3.JPG")
		
	}
	
	@Test
	void test3() {
		def g = new Graphics(java.awt.Color.cyan)
		g.forward(100)
		g.turnRight(25)
		g.forward(50)
		g.save("lTest2.JPG")
		
	}
	
	@Test
	void test2() {
	def x = 400.0
	def y = 400.0
	def angle = 91
	def d = 50.0
	
	def rad = Math.toRadians(angle)
	def m = Math.tan(rad) // y/x
	def b = y - m * 0
	def y2 = Math.sin(rad) * d + y
	def x2 = Math.cos(rad) * d + x
	
//	def x2a = d * ( 1 / ( 1 + (m * m) )) + x
//	def y2a = d * ( 1 / ( 1 + (m * m) )) + y
//	def y2b = m * x2 + b

	println """
x=$x
y=$y
angle=$angle
d=$d
rad=$rad
m=$m
b=$b
y2=$y2
x2=$x2
"""
//	y2a=$y2a
//	x2a=$x2a
//	y2b=$y2b
	
	}
	
	@Test
	void test() {
		def g = new GraphicsShim(java.awt.Color.cyan)
		println """
x = ${g.getXPos()}
y = ${g.getYPos()}
deg = ${g.getDirection()}
"""
			
		g.forward(50)
		println """		forward 50
x = ${g.getXPos()}
y = ${g.getYPos()}
deg = ${g.getDirection()}
"""
		g.turnRight(25)
		println """		right 25
x = ${g.getXPos()}
y = ${g.getYPos()}
deg = ${g.getDirection()}
"""
		//g.setDirection(25)
		g.forward(75)
		
		println """		forward 75
x = ${g.getXPos()}
y = ${g.getYPos()}
deg = ${g.getDirection()}
"""
		
		g.save("lTest.JPG")
	}

}
