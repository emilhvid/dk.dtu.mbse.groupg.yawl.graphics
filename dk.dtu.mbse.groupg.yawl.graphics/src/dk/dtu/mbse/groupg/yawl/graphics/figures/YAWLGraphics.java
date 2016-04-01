package dk.dtu.mbse.groupg.yawl.graphics.figures;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.pnml.tools.epnk.gmf.extensions.graphics.GraphicalExtension;
import org.pnml.tools.epnk.gmf.extensions.graphics.IUpdateableFigure;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.ArcFigure;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.PlaceFigure;
import org.pnml.tools.epnk.pnmlcoremodel.Arc;
import org.pnml.tools.epnk.pnmlcoremodel.Place;

import yawlnet.yawltypes.YawltypesPackage;

public class YAWLGraphics extends GraphicalExtension {

//	public YAWLGraphics() {
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public
	List<EClass> getExtendedNetTypes(){
		ArrayList<EClass> list = new ArrayList<EClass>();
		list.add(YawltypesPackage.eINSTANCE.getYAWLnet());
		return list;

	}

	@Override
	public List<EClass> getExtendedNetObjects(EClass netType) {
	ArrayList<EClass> list = new ArrayList<EClass>();
	if (netType.equals(YawltypesPackage.eINSTANCE.getYAWLnet())) {
	list.add(YawltypesPackage.eINSTANCE.getArc());
	list.add(YawltypesPackage.eINSTANCE.getPlace());
	}
	return list;
	}

	@Override
	public ArcFigure createArcFigure(Arc arc) {
	if (arc instanceof Arc) {
		return new ArcFigure((Arc) arc);
	}
	return null;
	}

	@Override
	public IUpdateableFigure createPlaceFigure(Place place) {
	if (place instanceof Place) {
	return new PlaceFigure((Place) place);
	}
	return null;
	}
}
