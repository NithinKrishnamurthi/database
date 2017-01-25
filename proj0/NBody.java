
public class NBody {
	public static void main(String[]args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		double[] xForces = new double[planets.length];
		double[] yForces = new double[planets.length];
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0,0,"images/starfield.jpg");
		for(int i = 0;i<planets.length;i++){
			planets[i].draw();
		}
		double t = 0;
		while(t<T){
			
			for (int i = 0; i<planets.length;i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i = 0;i<planets.length;i++){
				planets[i].update(dt,xForces[i],yForces[i]);					
			}
			
			StdDraw.picture(0,0,"images/starfield.jpg");
			for(int i = 0;i<planets.length;i++){
				planets[i].draw();
			}
			StdDraw.show(10);
			t = t+dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
		}
	}
	public static double readRadius(String fileName){
		In file = new In(fileName);
		file.readDouble();
		return file.readDouble();	
	}
	public static Planet[] readPlanets(String fileName){
		In file = new In(fileName);
		int numPlanets = file.readInt();
		file.readDouble();
		Planet[] planets = new Planet[numPlanets];
		for (int i = 0;i<numPlanets;i++){
			double xP = file.readDouble();
			double yP = file.readDouble();
			double xV = file.readDouble();
			double yV = file.readDouble();
			double m = file.readDouble();
			String img = file.readString();
			Planet p = new Planet(xP,yP,xV,yV,m,img);
			planets[i] = p;
		}
		return planets;
	}

}
