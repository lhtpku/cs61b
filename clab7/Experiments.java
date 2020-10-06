/**
 * Created by hug.
 */
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Experiments {

    public static void experiment1() {
        Random r = new Random();
        BST<Integer> test = new BST<>();
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        xValues.add(0);
        yValues.add(0.0);
        y2Values.add(0.0);
        for (int x = 1; x <= 5000; x += 1) {
            ExperimentHelper.getInt(test);
            xValues.add(x);
            yValues.add(test.averageDepth()); //real depth
            y2Values.add(ExperimentHelper.optimalAverageDepth(x)); // Theoretical depth
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Real depth", xValues, yValues);
        chart.addSeries("Optimal depth", xValues, y2Values);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        BST<Integer> test = new BST<>();
        int temp = 0;
        int count = 0;
        while (count < 5000) {
            count++;
            ExperimentHelper.getInt(test);
        }
        double start_depth = test.averageDepth();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        xValues.add(0);
        yValues.add(start_depth);
        for (int x = 1; x < 100000; x += 1) {
            test.deleteTakingSuccessor(test.getRandomKey());
            ExperimentHelper.getInt(test);
            xValues.add(x);
            yValues.add(test.averageDepth()); //real depth
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Real depth", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        BST<Integer> test = new BST<>();
        int temp = 0;
        int count = 0;
        while (count < 5000) {
            count++;
            ExperimentHelper.getInt(test);
        }
        double start_depth = test.averageDepth();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        xValues.add(0);
        yValues.add(start_depth);
        for (int x = 1; x < 300000; x += 1) {
            test.deleteTakingRandom(test.getRandomKey());
            ExperimentHelper.getInt(test);
            xValues.add(x);
            yValues.add(test.averageDepth()); //real depth
        }

        XYChart chart = new XYChartBuilder().width(1500).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Real depth", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment3();
    }
}
