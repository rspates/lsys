package drawing.lsys

import groovy.json.JsonSlurper
import java.awt.Color
import org.junit.jupiter.api.Test
import util.Args

public class LSDriver {

	static def verbose=false
	def dataFile
	double metricValue = 1.0
	String mode = "metricFile"
	double scaleFactor = 5.0

	/**
	 * Run an L-System
	 * @param args
	 */
	public static void main(String[] args){
/*
-rule lsys/allPlants.json -image C:/temp/images/lsys.jpg -mode stoch -metric 2.0 -scale 2.0
-rule lsys/allPlants.json -image C:/temp/images/lsys.jpg -mode testFile -data testRSI.txt -scale 2.0
-rule lsys/allPlants.json -image C:/temp/images/lsys.jpg -mode constant -metric 2.0 
*/
		
		def map = Args.get(args)
		if (map.isEmpty()) {
			println """
Usage, drawing.lsys.LSDriver 
			-rule LSysRuleFile 
			-image imageFile(jpg) 
			-data metricFile 
			{-sleep millisecs}
			{-mode [metricFile (default), constant, testFile, stoch}
			{-metric [a constant]}
			{-scale [a scale factor, default=5.o]}
"""
			return
		}
		def lsd = new LSDriver()
		
		def rule = map["rule"]
		def image = map["image"]
		lsd.dataFile = map["data"]
		def time = (map["sleep"]?:"500") as long
		lsd.metricValue = (map["metric"]?:"1.0") as double
		lsd.mode = map["mode"]?:"metricFile"
		lsd.scaleFactor = (map["scale"]?:"5.0") as double
		
		assert rule , "no rule file"
		assert image , "no image file"
		if (lsd.mode == "metricFile")
			assert lsd.dataFile , "no data file"
		//assert mode in [""], "no mode"
		
		for (int i=0;true;i++) {
			def metric = lsd.getMetric()
			if (metric > 0.0) 
					lsd.draw(rule,image,metric)
			sleep(time)
		}
	}
	
	def random = new Random()
	def getMetric() {
		def m = 0.0
		switch (mode) {
			case "constant":
			m = metricValue
			break
			
			case "stoch":
			m = random.nextDouble() * scaleFactor
			println m
			break
			
			case "testFile":
			m = getTestMetric(dataFile)
			break
			
			case "metricFile":
			default:
			m = getMetric(dataFile)
			break
		}
		m
	}
	
	// RSI specific
	def extract(output) {
		def f = output.split(/[ \t]+/)
		if (f.size()<2)  return 0.0 // TODO-check if this works
		def m = f[1] as double
		def d = Math.abs(m - 50) / scaleFactor
		d
	}
	
	def getMetric(file) {
		def output = "tail -1 $file".execute().text
		if (output.trim() != "")
			extract(output)
	}
	
	BufferedReader reader
	def getTestMetric(file) {
		if (!reader) reader = new File(file).newReader()
		def output = reader.readLine()?: ""
		if (output.trim() != "")
			extract(output)
	}



	def draw(rule,image,metric){

		LSystem sys = new LSystem(2000,2000,metric);

		def c = new JsonSlurper().parse(new File(rule))

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
			if (verbose) println "${m.name}"
			sys.getInput(m.graph)

			sys.draw(m.x+xoff, m.y+yoff, turn, width, new Color(red,green,blue));
		}
		sys.save(image)
	}


}
