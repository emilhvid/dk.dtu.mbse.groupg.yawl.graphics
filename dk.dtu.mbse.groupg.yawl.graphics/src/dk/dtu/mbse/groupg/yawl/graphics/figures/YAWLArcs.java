package dk.dtu.mbse.groupg.yawl.graphics.figures;

import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.swt.SWT;
import org.pnml.tools.epnk.gmf.extensions.graphics.decorations.CircleDecoration;
import org.pnml.tools.epnk.gmf.extensions.graphics.decorations.FlashDecoration;
import org.pnml.tools.epnk.gmf.extensions.graphics.decorations.ReisigsArrowHeadDecoration;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.ArcFigure;
import org.pnml.tools.epnk.pnmlcoremodel.Arc;
import org.pnml.tools.epnk.pnmlcoremodel.Node;
import org.pnml.tools.epnk.pnmlcoremodel.TransitionNode;

import yawlnet.yawltypes.ArcType;
import yawlnet.yawltypes.ArcTypes;

public class YAWLArcs extends ArcFigure {

	private enum Type { NORMAL, READ, INHIBIT, SIGNAL }

	private Type type;


	public YAWLArcs(Arc arc) {
		super(arc);
		type = getType();
		setGraphics();

		// TODO Auto-generated constructor stub
	}

	@Override
	public void Update(){
		Type oldType = type;
		type = getType();
		if (oldType != type) {
			setGraphics();
		}		
	}

	private Type getType() {
		if (this.arc instanceof Arc) {
			ArcType arctype = (arc).getType();
			if (arctype != null) {
				switch (arctype.getText().getValue()) {
				case ArcTypes.NORMAL_VALUE:
					return Type.READ;
				case ArcTypes.RESETARC_VALUE:
					return Type.INHIBIT;
				}
			} else {
				Node source = arc.getSource();
				Node target = arc.getTarget();
				if (source instanceof TransitionNode &&
						target instanceof TransitionNode) {
					return Type.SIGNAL;
				}
			}
		}
		return Type.NORMAL;
	}
	
	private void setGraphics() {
		RotatableDecoration targetDecorator = null;
		RotatableDecoration sourceDecorator = null;

		if (type == Type.READ) {
			targetDecorator = new ReisigsArrowHeadDecoration();
			sourceDecorator = new ReisigsArrowHeadDecoration();
			this.setLineStyle(SWT.LINE_DASH);
		} else if (type == Type.INHIBIT) {
			targetDecorator = new CircleDecoration();
			this.setLineStyle(SWT.LINE_SOLID);
		} else if (type == Type.SIGNAL) {
			sourceDecorator = new FlashDecoration();
			targetDecorator = new ReisigsArrowHeadDecoration();
		} else {
			targetDecorator = new ReisigsArrowHeadDecoration();
		}
		this.setTargetDecoration(targetDecorator);
		this.setSourceDecoration(sourceDecorator);
	}
}
