package drawing.pen;

public interface IGraphics {
	
	// This object is set to drawing mode.
	void down();

	// This DrawingTool object is moved forward from current direction by distance
	// pixels from the old (previous) location.
	void forward(double distance);

	// Gets the color of the DrawingTool
	java.awt.Color getColor();

	// Gets the direction of this DrawingTool.
	double getDirection();

	// Get the drawing width of the DrawingTool
	int getWidth();

	// Gets the x coordinate of the current postion of this DrawingTool.
	double getXPos();

	// Gets the y coordinate of the current postion of this DrawingTool.
	double getYPos();

	// This DrawingTool object is moved from the current position to the position
	// specified by the coordinates x and y.
	void move(double x, double y);

	// The color of the DrawingTool object is set to c.
	void setColor(java.awt.Color c);

	// Sets the direction to d degrees.
	void setDirection(double deg);

	// Sets the width of the DrawingTool object is to width pixels.
	void setWidth(int width);

	// Changes the current direction counterclockwise by degrees degrees from the
	// current direction
	void turnLeft(double degrees);

	// Changes the current direction clockwise by degrees degrees from the current
	// direction
	void turnRight(double degrees);

	// This object is set to moving mode.
	void up();

	// save graphic state to image file
	void save(String filename);
}
