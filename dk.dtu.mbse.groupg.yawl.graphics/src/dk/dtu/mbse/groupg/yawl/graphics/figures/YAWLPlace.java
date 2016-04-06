package dk.dtu.mbse.groupg.yawl.graphics.figures;

import java.math.BigInteger;

import org.eclipse.draw2d.Graphics;
import org.eclipse.swt.graphics.Rectangle;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.PlaceFigure;

import yawlnet.yawltypes.Arc;
import yawlnet.yawltypes.ArcType;
import yawlnet.yawltypes.ArcTypes;
import yawlnet.yawltypes.Place;
import yawlnet.yawltypes.PlaceType;
import yawlnet.yawltypes.PlaceTypes;


public class YAWLPlace extends PlaceFigure {
	
	private enum Type { NORMAL, START, END }
	
	private Type type;


	public YAWLPlace(Place place) {
		super(place);
	}

	@Override
	public void update() {
		this.repaint();
		
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

	@Override
	protected void fillShape(Graphics graphics) {
		super.fillShape(graphics);
		org.eclipse.draw2d.geometry.Rectangle rectangle = this.getClientArea();
		int m = 0;
		if (place instanceof Place)
			m = getMarking((Place) place);
		int cx = rectangle.x + rectangle.width/2;
		int cy = rectangle.y + rectangle.height/2;
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
		//Draw black rectangle if place is start or end (Should be worked on later)
		if (place instanceof Place ) {
			if(type == Type.END) {
				graphics.fillRectangle(cx, cy, 10, 10);
			}
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
