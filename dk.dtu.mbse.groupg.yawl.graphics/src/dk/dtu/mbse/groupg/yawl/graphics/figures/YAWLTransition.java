package dk.dtu.mbse.groupg.yawl.graphics.figures;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.TransitionFigure;

import yawlnet.yawltypes.Transition;
import yawlnet.yawltypes.TransitionType;
import yawlnet.yawltypes.TransitionTypes;

public class YAWLTransition extends TransitionFigure{
	private enum Type {AND,XOR,OR}
	private Type type;
	
	public YAWLTransition(Transition transition) {
		super(transition);
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
		if (this.transition instanceof Transition) {
			TransitionType transitionType = ((Transition) transition).getJoin();
			if (transitionType != null) {
				switch (transitionType.getText().getValue()) {
					case TransitionTypes.AND_VALUE:
						return Type.AND;
					case TransitionTypes.OR_VALUE:
						return Type.OR;
					case TransitionTypes.XOR_VALUE:
						return Type.XOR;
				}
			}
			transitionType = ((Transition) transition).getSplit();
			if (transitionType != null) {
				switch (transitionType.getText().getValue()) {
					case TransitionTypes.AND_VALUE:
						return Type.AND;
					case TransitionTypes.OR_VALUE:
						return Type.OR;
					case TransitionTypes.XOR_VALUE:
						return Type.XOR;
				}
			}
		}
		return null;
	}

	@Override
	protected void fillShape(Graphics graphics) {
		super.fillShape(graphics);
		org.eclipse.draw2d.geometry.Rectangle rectangle = this.getClientArea();
		int m = 0;
		int cx = rectangle.x + rectangle.width/2;
		int cy = rectangle.y + rectangle.height/2;
		TransitionType transitionType;
		if (transition instanceof Transition) {
			transitionType = ((Transition) transition).getJoin();
			graphics.setLineWidth(2);
			if (transitionType != null) {
				// Tegn Join grafik..
				if(type == Type.AND) {
					graphics.setBackgroundColor(getForegroundColor());
					// Set up AND join figure
					PointList points = new PointList();
					points.addPoint(new Point(cx-20, cy+20));
					points.addPoint(new Point(cx, cy));
					points.addPoint(new Point(cx-20, cy-20));
					// Draw the triangle figure
					graphics.drawPolygon(points);
					// Draw the vertical line in center
					graphics.drawLine(new Point(cx, cy+20), new Point(cx, cy-20));
				}
				else if(type == Type.OR){
					graphics.setBackgroundColor(getForegroundColor());
					// Set up OR split figure
					PointList points = new PointList();
					points.addPoint(new Point(cx-20, cy));
					points.addPoint(new Point(cx-10, cy+20));
					points.addPoint(new Point(cx, cy));
					points.addPoint(new Point(cx-10, cy-20));
					// Draw the triangle figure
					graphics.drawPolygon(points);
					// Draw the vertical line in center
					graphics.drawLine(new Point(cx, cy+20), new Point(cx, cy-20));
				}
				else if(type == Type.XOR){
					graphics.setBackgroundColor(getForegroundColor());
					// Set up XOR join figure
					PointList points = new PointList();
					points.addPoint(new Point(cx, cy+20));
					points.addPoint(new Point(cx-20, cy));
					points.addPoint(new Point(cx, cy-20));
					// Draw the figure
					graphics.drawPolygon(points);
				}
			}
			
			transitionType = ((Transition) transition).getSplit();
			if (transitionType != null) {
				// Tegn split grafik..
				if(type == Type.AND) {
					graphics.setBackgroundColor(getForegroundColor());
					// Set up AND split figure
					PointList points = new PointList();
					points.addPoint(new Point(cx, cy));
					points.addPoint(new Point(cx+20, cy+20));
					points.addPoint(new Point(cx+20, cy-20));
					// Draw the triangle figure
					graphics.drawPolygon(points);
					// Draw the vertical line in center
					graphics.drawLine(new Point(cx, cy+20), new Point(cx, cy-20));
				}
				else if(type == Type.OR){
					graphics.setBackgroundColor(getForegroundColor());
					// Set up OR join figure
					PointList points = new PointList();
					points.addPoint(new Point(cx, cy));
					points.addPoint(new Point(cx+10, cy+20));
					points.addPoint(new Point(cx+20, cy));
					points.addPoint(new Point(cx+10, cy-20));
					// Draw the triangle figure
					graphics.drawPolygon(points);
					// Draw the vertical line in center
					graphics.drawLine(new Point(cx, cy+20), new Point(cx, cy-20));
				}
				else if(type == Type.XOR){
					graphics.setBackgroundColor(getForegroundColor());
					// Set up XOR split figure
					PointList points = new PointList();
					points.addPoint(new Point(cx, cy-20));
					points.addPoint(new Point(cx+20, cy));
					points.addPoint(new Point(cx, cy+20));
					
					// Draw the figure
					graphics.drawPolygon(points);
				}
			}
			m = getMarking((Transition) transition);
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

	private int getMarking(Transition transition) {
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
