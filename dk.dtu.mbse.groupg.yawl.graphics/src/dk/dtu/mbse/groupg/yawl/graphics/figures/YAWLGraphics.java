package dk.dtu.mbse.groupg.yawl.graphics.figures;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.pnml.tools.epnk.gmf.extensions.graphics.GraphicalExtension;
import org.pnml.tools.epnk.gmf.extensions.graphics.IUpdateableFigure;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.ArcFigure;
import org.pnml.tools.epnk.pnmlcoremodel.Arc;
import org.pnml.tools.epnk.pnmlcoremodel.Place;
import org.pnml.tools.epnk.pntypes.signalnets.SignalnetsPackage;
import org.pnml.tools.epnk.pntypes.signalnets.graphics.figures.SignalnetPlaceFigure;

public class YAWLGraphics extends GraphicalExtension {

//	public YAWLGraphics() {
//		// TODO Auto-generated constructor stub
//	}
	
	@Override
	public
	List<EClass> getExtendedNetTypes(){
		ArrayList<EClass> list = new ArrayList<EClass>();
		list.add(SignalnetsPackage.eINSTANCE.getSignalNet());
		return list;
		
	}
	
	@Override
	public List<EClass> getExtendedNetObjects(EClass netType) {
	ArrayList<EClass> list = new ArrayList<EClass>();
	if (netType.equals(SignalnetsPackage.eINSTANCE.getSignalNet())) {
	list.add(SignalnetsPackage.eINSTANCE.getArc());
	list.add(SignalnetsPackage.eINSTANCE.getPlace());
	}
	return list;
	}
	
	@Override
	public ArcFigure createArcFigure(Arc arc) {
	if (arc instanceof org.pnml.tools.epnk.pntypes.signalnets.Arc) {
	return new ArcFigure(
	(org.pnml.tools.epnk.pntypes.signalnets.Arc) arc);
	}
	return null;
	}
	
	@Override
	public IUpdateableFigure createPlaceFigure(Place place) {
	if (place instanceof
	org.pnml.tools.epnk.pntypes.signalnets.Place) {
	return new SignalnetPlaceFigure(
	(org.pnml.tools.epnk.pntypes.signalnets.Place) place);
	}
	return null;
	}
	
	void getExtendedNetObjects(){
		
	}
	
	ArcFigure createArcFigure(){
		return null;
		
	}

}
