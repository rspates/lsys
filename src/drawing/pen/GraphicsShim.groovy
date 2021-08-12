package drawing.pen

import java.awt.Color
import java.awt.Font
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import drawing.lsys.LSDriver

/**
 * A SketchPad is a top-level window for 
 * use with a DrawingTool object The center 
 * of the graphics window is at location 0,0. 
 * Positive X is to the right; positive Y is up. 
 * Headings (angles) are measured in degrees 
 * counterclockwise from the positive X axis.
 gpdraw coordinate space (example with 800x800 space)
 -------------------------------------
 -400,400				400,400
 				0,0
 -400,-400				400,-400
 -------------------------------------
 Graphics2D coordinate space
 -------------------------------------
 0,0							800,0
 				400,400
 0,800						800,800
 -------------------------------------
The image produced in the transition between
coordinate spaces still requires a transform
for scale and rotation.  See save()
 * @author ricks
 *
 */
class GraphicsShim implements IGraphics {

	Graphics g
	def transformRotateDeg = 180
	def transformScale = 1.75
	def metric = 1.0

	public GraphicsShim(h,w) {
		g = new Graphics(h,w, Graphics.color, metric)
	}

	public GraphicsShim(color) {
		g = new Graphics(Graphics.width, Graphics.height, color, metric)
	}

	public GraphicsShim() {
		g = new Graphics(Graphics.width, Graphics.height, Graphics.bgcolor, metric)
	}

	public GraphicsShim(width, height, bgcolor, metric) {
		g = new Graphics(width, height, bgcolor)
		this.metric = metric
	}

	public GraphicsShim(width, height, bgcolor, scale, rotate, metric) {
		g = new Graphics(width, height, bgcolor)
		transformRotateDeg = rotate
		transformScale = scale
		this.metric = metric
	}

	@Override
	public void down() {
		g.down()
	}

	@Override
	public void forward(double distance) {
		g.forward( distance)
	}

	@Override
	public Color getColor() {
		return g.getColor()
	}

	@Override
	public double getDirection() {
		return  g.getDirection()
	}

	@Override
	public int getWidth() {
		return g.getWidth();
	}

	@Override
	public double getXPos() {
		toGpdX(g.getXPos())
	}

	@Override
	public double getYPos() {
		toGpdY(g.getYPos())
	}

	@Override
	public void move(double x, double y) {
		g.move(toG2dX( x), toG2dY(y))

	}

	@Override
	public void setColor(Color c) {
		g.setColor(c)
	}
	
	def random = new Random()
	def getSign() {
		random.nextDouble()  < 0.5 ? -1 : 1
	}
	
	def degrees(degrees) {
		def offset = metric * getSign()
		return degrees + offset
	}

	@Override
	public void setDirection(double deg) {
		g.setDirection( degrees(deg))
	}

	@Override
	public void setWidth(int width) {
		g.setWidth( width)
	}

	@Override
	public void turnLeft(double deg) {
		g.turnLeft(degrees(deg))
	}

	@Override
	public void turnRight(double deg) {
		g.turnRight(degrees(deg))
	}

	@Override
	public void up() {
		g.up()
	}

	public void  save(String filename) {
//		def g3=g.ig2
//		g3.setColor(Color.black);
//		g3.setFont(new Font("TimesRoman", Font.PLAIN, 16)); 
//		g3.drawString(""+new Date(), 50, 20)
//		//g3.drawString("HELLO", 100, 100)
		BufferedImage bi = transform()
		File img = new File(filename)
		ImageIO.write(bi, "JPEG", img);
	}
	def transform() {
		BufferedImage before = g.bi
		int w = before.getWidth();
		int h = before.getHeight();
		BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(transformRotateDeg),w/2,h/2)
		at.scale(transformScale, transformScale);
		AffineTransformOp scaleOp =
		  new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		after = scaleOp.filter(before, after);
	}
	/*	
	 Translation between coordinate spaces:
	 */		
	// g2d x = (.5 imageSize) + gpd
	double toG2dX(double x) {
		0.5 * Graphics.width + x
	}

	// g2d y = imageSize - (.5 imageSize) + gpd
	double toG2dY(double x) {
		//Graphics.height - 0.5 * Graphics.height + x
		0.5 * Graphics.height + x
	}

	// gpd x = g2d - (.5 imageSize)
	double toGpdX(double x) {
		x - 0.5 * Graphics.width
	}
	
	// gpd y = imageSize - g2d - (.5 imageSize)
	double toGpdY(double x) {
		//Graphics.height - x - 0.5 * Graphics.height
		x - 0.5 * Graphics.height
	}

}
