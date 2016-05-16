package dk.dtu.mbse.groupg.yawl.graphics.figures;
/**
 * @author  Emil Hvid
 */
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.pnml.tools.epnk.gmf.extensions.graphics.GraphicalExtension;
import org.pnml.tools.epnk.gmf.extensions.graphics.IUpdateableFigure;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.ArcFigure;
import org.pnml.tools.epnk.pnmlcoremodel.Arc;
import org.pnml.tools.epnk.pnmlcoremodel.Place;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;

import yawlnet.yawltypes.YawltypesPackage;

public class YAWLGraphics extends GraphicalExtension {

	public YAWLGraphics() {
		super();
	}
	// TODO Auto-generated constructor stub }

	@Override
	public List<EClass> getExtendedNetTypes() {
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
			list.add(YawltypesPackage.eINSTANCE.getTransition());
		}
		return list;
	}

	/*
	 * The method createArcFigure() should return a new instance of the
	 * ArcFigure you implemented in the previous step (with the current arc as
	 * its parameter to the constructor).
	 *
	 * @see org.pnml.tools.epnk.gmf.extensions.graphics.GraphicalExtension#
	 * createArcFigure(org.pnml.tools.epnk.pnmlcoremodel.Arc)
	 */
	@Override
	public ArcFigure createArcFigure(Arc arc) {
		if (arc instanceof yawlnet.yawltypes.Arc) {
			return new YAWLArc((yawlnet.yawltypes.Arc) arc);
		}
		return null;
	}

	@Override
	public IUpdateableFigure createPlaceFigure(Place place) {
		if (place instanceof Place) {
			return new YAWLPlace((yawlnet.yawltypes.Place) place);
		}
		return null;
	}

	@Override
	public IUpdateableFigure createTransitionFigure(Transition transition) {
		if (transition instanceof Transition) {
			return new YAWLTransition((yawlnet.yawltypes.Transition) transition);
		}
		return null;
	}
}
