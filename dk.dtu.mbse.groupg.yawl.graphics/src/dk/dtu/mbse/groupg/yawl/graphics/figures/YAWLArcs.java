package dk.dtu.mbse.groupg.yawl.graphics.figures;

import org.eclipse.swt.SWT;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.ArcFigure;
import org.pnml.tools.epnk.pnmlcoremodel.Arc;

public class YAWLArcs extends ArcFigure {

	public YAWLArcs(Arc arc) {
		super(arc);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void Update(){
		this.setLineStyle(SWT.LINE_DASH);
		this.setLineStyle(SWT.LINE_SOLID);
	}


}
