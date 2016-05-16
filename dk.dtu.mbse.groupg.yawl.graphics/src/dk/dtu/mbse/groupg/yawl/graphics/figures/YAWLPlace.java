package dk.dtu.mbse.groupg.yawl.graphics.figures;
/**
 * @author Nicklas Hansen (s144858), Emil Hvid
 */

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.PlaceFigure;

import com.sun.prism.paint.Color;

import yawlnet.yawltypes.Place;
import yawlnet.yawltypes.PlaceType;
import yawlnet.yawltypes.PlaceTypes;


public class YAWLPlace extends PlaceFigure {
	
	private enum Type { NORMAL, START, END }
	
	private Type type;


	public YAWLPlace(Place place) {
		super(place);
		type = getType();
	}

	@Override
	public void update() {
		Type oldType = type;
		type = getType();
		if (oldType != type) {
			this.repaint();
		}
	}
		
	private Type getType() {
		if (this.place instanceof Place) {
			PlaceType placeType = ((Place) place).getType();
			if (placeType != null) {
				switch (placeType.getText().getValue()) {
					case PlaceTypes.NORMAL_VALUE:
						return Type.NORMAL;
					case PlaceTypes.START_VALUE:
						return Type.START;
					case PlaceTypes.END_VALUE:
						return Type.END;
				}
			}
		}
		return Type.NORMAL;
	}

	/**
	 * Orignal @author Ekkart Kindler from org.pnml.tools
	 * @author Stefan Hyltoft s144872
	 */
	@Override
	protected void fillShape(Graphics graphics) {
		super.fillShape(graphics);
		org.eclipse.draw2d.geometry.Rectangle rectangle = this.getClientArea();
		int m = 0;
		int cx = rectangle.x + rectangle.width/2;
		int cy = rectangle.y + rectangle.height/2;
		if (place instanceof Place) {
			m = getMarking((Place) place);
			if(type == Type.END) {
				graphics.setBackgroundColor(ColorConstants.red);
				graphics.fillRectangle(cx-6, cy-6, 12, 12);
			}
			if(type == Type.START){
				graphics.setBackgroundColor(ColorConstants.green);
				PointList points = new PointList();
				points.addPoint(new Point(cx-7, cy-10));
				points.addPoint(new Point(cx+10, cy));
				points.addPoint(new Point(cx-7, cy+10));
				graphics.fillPolygon(points);
			}
		}

		if (m == 0) {
			return;
		} else if (m == 1) {
			graphics.setBackgroundColor(getForegroundColor());
			graphics.fillOval(cx-6, cy-6, 12, 12);
		} else if (m == 2) {
			graphics.setBackgroundColor(getForegroundColor());
			graphics.fillOval(cx-11, cy-11, 12, 12);
			graphics.fillOval(cx, cy, 12, 12);
		} else if (m == 3) {
			graphics.setBackgroundColor(getForegroundColor());
			graphics.fillOval(cx-6, cy-13, 12, 12);
			graphics.fillOval(cx-13, cy, 12, 12);
			graphics.fillOval(cx+1, cy, 12, 12);
		} else if (m == 4) {
			graphics.setBackgroundColor(getForegroundColor());
			graphics.fillOval(cx-13, cy-13, 12, 12);
			graphics.fillOval(cx+1, cy-13, 12, 12);
			graphics.fillOval(cx-13, cy+1, 12, 12);
			graphics.fillOval(cx+1, cy+1, 12, 12);
		} else {
			graphics.drawString(""+m, cx-5, cy-7);
		}
	}

	private int getMarking(Place place) {
		/* Marking marking = place.getMarking();
		if (marking != null) {
			BigInteger value = marking.getText();
			if (value != null) {
				return value.intValue();
			}
		}
		*/
		return 0;
	}
}
