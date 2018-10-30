import java.util.concurrent.TimeUnit;

public class GraphingCalculator {
//M The new central class.
	public static void main(String[] args) {
		//M Where the program runs.
		GUI gui = new GUI();
		//M Filling xValues with consecutive x-values.
		gui.fillXValues();
		Graph graph = new Graph();
		double [] yValues = new double [20001];
		String equation = "";
		//M Infinite loop (AKA I have no idea how to synchronize) to continuously graph the updated yValues.
		while(true) {
			//M If the points are ready, then they are graphed.
			if(gui.getPointsReady() == true) {
				graph.clear();
				graph.draw();
				for(int i = 0; i < gui.getEquations().size(); i++) {
					equation = (String) gui.getEquations().get(i);
					if(equation.startsWith("dtwo")) {
						equation = equation.substring(4);
						yValues = gui.getYValues(gui.getXValues(), equation, 2);
					}
					else if(equation.startsWith("done")) {
						equation = equation.substring(4);
						yValues = gui.getYValues(gui.getXValues(), equation, 1);
					}
					else {
						//M Need to add syntax handling.
						yValues = gui.getYValues(gui.getXValues(), equation, 0);
					}
					graph.draw(gui.getXValues(), yValues);
					//M Calculating the key points, hopefully this leaves enough time.
					gui.findKeyPoints(gui.getXValues(), yValues);
					for(int j = 0; j < gui.getHolesLength(); j++) {
						graph.draw(gui.getHoles()[j][0], gui.getHoles()[j][1], "#F44F0D");
					}
					//M Preventing the method from executing again until the yValues are recalculated.
					if(Zeroes.lineCheck(gui.getXValues(), yValues) == false) {
						for(int k = 0; k < gui.getMaxesLength(); k++ ) {
							graph.draw(gui.getMaxes()[k][0], gui.getMaxes()[k][1], "#67D4C4");
							System.out.println("Drawing max ( " + gui.getMaxes()[k][0] + " , " + gui.getMaxes()[k][1] + " )");
						}
						for(int l = 0; l < gui.getMinsLength(); l++ ) {
							graph.draw(gui.getMins()[l][0], gui.getMins()[l][1], "#FFC0CB");
							System.out.println("Drawing min ( " + gui.getMins()[l][0] + " , " + gui.getMins()[l][1] + " )");
						}
						for(int m = 0; m < gui.getPoisLength(); m++ ) {
							//M This is drawing erroneous POIs at x = -10 in particular on some rational functions.
							//M Unfortunately, simply disabling them isn't an option unlike with lines.
							graph.draw(gui.getPois()[m][0], gui.getPois()[m][1], "#E9967A");
							System.out.println("Drawing POI ( " + gui.getPois()[m][0] + " , " + gui.getPois()[m][1] + " )");
						}
					}
					gui.setPointsReady(false);
				}
				//M Sending the points to be graphed.
				
			}
			else if(gui.getClearDesired() == true) {
				graph.clear();
				graph.draw();
				gui.setClearDesired(false);
			}
			//M This seems to do something for synchronization, so I'm just going to leave it for now.
			else {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
