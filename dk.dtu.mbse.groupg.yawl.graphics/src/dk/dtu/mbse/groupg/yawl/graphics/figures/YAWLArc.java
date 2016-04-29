package dk.dtu.mbse.groupg.yawl.graphics.figures;

import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.swt.SWT;
import org.pnml.tools.epnk.gmf.extensions.graphics.decorations.FlashDecoration;
import org.pnml.tools.epnk.gmf.extensions.graphics.decorations.ReisigsArrowHeadDecoration;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.ArcFigure;

import dk.dtu.mbse.groupg.yawl.graphics.decorations.ResetArcHeadDecoration;
import yawlnet.yawltypes.Arc;
import yawlnet.yawltypes.ArcType;
import yawlnet.yawltypes.ArcTypes;

public class YAWLArc extends ArcFigure {

	private enum Type { NORMAL, RESETARC }

	private Type type;


	public YAWLArc(Arc arc) {
		super(arc);
		type = getType();
		setGraphics();
	}

	@Override
	public void update() {
		Type oldType = type;
		type = getType();
		if (oldType != type) {
			setGraphics();
		}
	}

	private Type getType() {
		if (this.arc instanceof Arc) {
			ArcType arctype = ((Arc) arc).getType();
			if (arctype != null) {
				switch (arctype.getText().getValue()) {
					case ArcTypes.NORMAL_VALUE:
						return Type.NORMAL;
					case ArcTypes.RESETARC_VALUE:
						return Type.RESETARC;
				}
			}
//				else {
//				Node source = arc.getSource();
//				Node target = arc.getTarget();
//				if (source instanceof TransitionNode &&
//						target instanceof TransitionNode) {
//					return Type.SIGNAL;
//				}
//			}
		}
		return Type.NORMAL;
	}

	private void setGraphics() {
		RotatableDecoration targetDecorator = null;
		//RotatableDecoration sourceDecorator = null;

		if (type == Type.NORMAL) {
			targetDecorator = new ReisigsArrowHeadDecoration();
			this.setTargetDecoration(targetDecorator);
			//sourceDecorator = new ReisigsArrowHeadDecoration();
			this.setLineStyle(SWT.LINE_SOLID);
		} else if (type == Type.RESETARC) {
			this.setLineStyle(SWT.LINE_DASH);			
//			this.setLineCap(SWT.CAP_ROUND);
			targetDecorator = new ResetArcHeadDecoration();
			this.setTargetDecoration(targetDecorator);
		}
		
//		} else if (type == Type.SIGNAL) {
//			sourceDecorator = new FlashDecoration();
//			targetDecorator = new ReisigsArrowHeadDecoration();
//		} else {
//			targetDecorator = new ReisigsArrowHeadDecoration();
//		}
//		this.setTargetDecoration(targetDecorator);
//		this.setSourceDecoration(sourceDecorator);
	
	}
}