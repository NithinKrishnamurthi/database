public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	
	public Planet(double xP,double yP, double xV, double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img; 
	}
	
	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName; 
	}
	public double calcDistance(Planet p){
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		return java.lang.Math.pow(java.lang.Math.pow(dx,2) + java.lang.Math.pow(dy,2),0.5);
 	}
	public double calcForceExertedBy(Planet p){
		return this.mass*p.mass*6.67*java.lang.Math.pow(10,-11)/java.lang.Math.pow(this.calcDistance(p),2);
	}
	public double calcForceExertedByX(Planet p){
		double dx = p.xxPos - this.xxPos;
		return this.calcForceExertedBy(p)*(dx)/this.calcDistance(p);
	}
	public double calcForceExertedByY(Planet p){
		double dy = p.yyPos - this.yyPos;
		return this.calcForceExertedBy(p)*(dy)/this.calcDistance(p);
	}
	public double calcNetForceExertedByX(Planet[] planets){
		double netForce = 0;
		for(int i = 0;i<planets.length;i++){
			if(!this.equals(planets[i])){
				netForce += calcForceExertedByX(planets[i]);
			}
		}
		return netForce;
	}
	public double calcNetForceExertedByY(Planet[] planets){
		double netForce = 0;
		for(int i = 0;i<planets.length;i++){
			if(!this.equals(planets[i])){
				netForce += calcForceExertedByY(planets[i]);
			}
		}
		return netForce;
	}
	public void update(double dt,double fX,double fY){
		double ax = fX/this.mass;
		double ay = fY/this.mass;
		this.xxVel = this.xxVel + ax*dt;
		this.yyVel = this.yyVel + ay*dt;
		this.xxPos = this.xxVel*dt + this.xxPos;
		this.yyPos = this.yyVel*dt + this.yyPos;
	}
	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,"images/" + this.imgFileName);
	}
}
