
public class Body {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public final static double G = 6.67e-11;

	public Body(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
		this.xxPos = xxPos;
		this.yyPos = yyPos;
		this.xxVel = xxVel;
		this.yyVel = yyVel;
		this.mass = mass;
		this.imgFileName = imgFileName;
	}

	public Body(Body B) {
		this.xxPos = B.xxPos;
		this.yyPos = B.yyPos;
		this.xxVel = B.xxVel;
		this.yyVel = B.yyVel;
		this.mass = B.mass;
		this.imgFileName = B.imgFileName;
	}

	public double calcDistance(Body other) {
		double dx = this.xxPos - other.xxPos;
		double dy = this.yyPos - other.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double calcForceExertedBy(Body other) {
		double distance = this.calcDistance(other);
		return G * this.mass * other.mass / (distance * distance);
	}

	public double calcForceExertedByX(Body other) {
		if (this.equals(other)) {
			return 0;
		}
		double dx = other.xxPos - this.xxPos;
		double distance = this.calcDistance(other);
		return this.calcForceExertedBy(other) * dx / distance;
	}

	public double calcForceExertedByY(Body other) {
		if (this.equals(other)) {
			return 0;
		}
		double dy = other.yyPos - this.yyPos;
		double distance = this.calcDistance(other);
		return this.calcForceExertedBy(other) * dy / distance;
	}

	public void update(double dt, double xForce, double yForce) {
		double xAcc = xForce / this.mass;
		double yAcc = yForce / this.mass;

		this.xxVel += xAcc * dt;
		this.yyVel += yAcc * dt;

		this.xxPos += this.xxVel * dt;
		this.yyPos += this.yyVel * dt;
	}

	public double calcNetForceExertedByX(Body[] planets) {
        /* calculates the net X force exerted by all planets
           in the array upon the current planet
         */
		double result = 0;
		for (Body p : planets) {
			result += this.calcForceExertedByX(p);
		}
		return result;
	}

	public double calcNetForceExertedByY(Body[] planets) {
        /* calculates the net Y force exerted by all planets
           in the array upon the current planet
         */
		double result = 0;
		for (Body p : planets) {
			result += this.calcForceExertedByY(p);
		}
		return result;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}