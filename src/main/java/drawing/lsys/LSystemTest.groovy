package drawing.lsys

import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.*
import groovy.json.JsonSlurper
import java.awt.Color
import org.apache.commons.io.FileUtils
class LSystemTest {

	/**
	 * Produces a lindenmayer-system tree
	 * as a jpeg file
	 */
	@Test
	void test() {

		LSystem sys = new LSystem(2000,2000,0.2);

		def c = new JsonSlurper().parse(new File("lsys/allPlants.json"))

		def cfg = c.find{m->
			m.type=="config"
		}
		def xoff = cfg.xbase ?: 0
		def yoff = cfg.ybase ?: 0
		def width = cfg.width ?: 2
		def turn = cfg.turn ?: 90
		def red = cfg.color.red  ?: 1
		def green = cfg.color.green  ?: 150
		def blue = cfg.color.blue  ?: 150
		
		c.findAll{m->
			m.type=="lsys" &&  m.active
		}.each { m->
				println "${m.name}"
				sys.getInput(m.graph)

				sys.draw(m.x+xoff, m.y+yoff, turn, width, new Color(red,green,blue));
		}
		sys.save("lsys.jpg")
	}

	/**
	 * Open images.html in the project root dir.
	 * Run this test to produce a series of 
	 * lindenmayer-system tree jpeg images.
	 * Each one differs by a random amount from
	 * the previous image.
	 * The html updates periodically animating
	 * the tree movement.
	 */
	@Test
	void testLoop() {
		def metric = 1.0
		//for (int i=0;i<1;i++) {
		for (int i=0;true;i++) {
			//def ms = System.currentTimeMillis()
			draw(metric)
			FileUtils.copyFile(
				new File("./lsys0.jpg"),
				new File("./lsys.jpg")
				)
			//println "${System.currentTimeMillis() - ms}"
			sleep(200)
		}
	}
	
	def draw(metric){

		LSystem sys = new LSystem(2000,2000,metric);

		def c = new JsonSlurper().parse(new File("lsys/allPlants.json"))

		def cfg = c.find{m->
			m.type=="config"
		}
		def xoff = cfg.xbase ?: 0
		def yoff = cfg.ybase ?: 0
		def width = cfg.width ?: 2
		def turn = cfg.turn ?: 90
		def red = cfg.color.red  ?: 1
		def green = cfg.color.green  ?: 150
		def blue = cfg.color.blue  ?: 150
		
		c.findAll{m->
			m.type=="lsys" &&  m.active
		}.each { m->
				println "${m.name}"
				sys.getInput(m.graph)

				sys.draw(m.x+xoff, m.y+yoff, turn, width, new Color(red,green,blue));
		}
		sys.save("lsys0.jpg")
	}

	@Test
	void test0() {
		LSystem sys = new LSystem();
		Random rand = new Random();
		//def json="lsys/stochPlant2.json"
		def json="lsys/plant1.json"
		def m = new JsonSlurper().parse(new File(json))
		sys.getInput(m)

		for(int i = 0; i < 107; ++i){
			int x = rand.nextInt(500)-250;
			int y = rand.nextInt(100)-50;
			sys.draw(x, y, 90, 2, new Color(1,150,150));
		}
		sys.save("lsys.jpg")
	}

}

