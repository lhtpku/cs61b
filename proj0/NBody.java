public class NBody {

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt= Double.parseDouble(args[1]);

        String filename = args[2];

        Body[] bodies = readBodies(filename);
        double radius = readRadius(filename);

        StdDraw.setScale(-radius,radius);

        StdDraw.picture(0,0,"images/starfield.jpg");
        StdAudio.play("audio/2001.mid");

//        for (Body b: bodies) {
//            b.draw();
//        }
        double time = 0;

        while (time < T) {
            double[] xForces = new double[bodies.length];
            double[] yforces = new double[bodies.length];
            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yforces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xForces[i], yforces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body p : bodies) {
                p.draw();
            }
            StdDraw.show();
            time += dt;
        }

//        StdOut.printf("%d\n", bodies.length);
//        StdOut.printf("%.2e\n", radius);
//        for (int i = 0; i < bodies.length; i++) {
//            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
//                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
//                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
//        }
//        StdOut.close();
    }

    public static double readRadius(String fileName){
        In in = new In(fileName);
        int bodyNum = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        int bodyNum = in.readInt();
        double radius = in.readDouble();
        Body[] bodies = new Body[bodyNum];

        for(int i=0; i<bodyNum; i++) {
            bodies[i] = new Body(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }
        return bodies;
    }

}
