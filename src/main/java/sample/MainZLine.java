package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import math.suite.SuiteFactory;
import math.suite.complex.Zeta;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.complex.Complex;

import com.jenetics.mathexp.math.BigDecimalMath;
import com.jenetics.mathexp.math.BigIntegerSuite;
import com.jenetics.mathexp.math.Point;
import com.jenetics.mathexp.math.complex.fonction.ComplexContant;
import com.jenetics.mathexp.math.complex.fonction.F;
import com.jenetics.mathexp.math.complex.fonction.SinComplex;
import com.jenetics.mathexp.util.ChainedList;

public class MainZLine extends Application {


	private static final double REAXISLINE = 0.5;
	private static final int ITERZETA = 20000;
	private static int HEIGHT = 800;
	private static int WIDTH = 800;


	private double xOrigin = 0;
	private double yOrigin = 0;
	private double zoom = .1;

	private GraphicsContext gc;
	private Canvas canvas;
	private static Color[] colors;

	static {
		colors = new Color[255];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = Color.color(i /255, 0, 0);
		}
	}
	@Override
	public void start(Stage primaryStage) throws Exception{

		primaryStage.setTitle("Prime Fractal");
		Group root = new Group();
		canvas = new Canvas(WIDTH, HEIGHT);
		gc = canvas.getGraphicsContext2D();
		canvas.setOnMouseClicked(event -> canvasClicked(event));
		drawAll();
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	private void drawHalffAxis() {
		gc.setStroke(Color.GREEN);
		double half = WIDTH / 2  + (zoom * WIDTH / 2);
		gc.strokeLine(half, 0, half, HEIGHT);

	}

	private void drawAxis() {
		gc.setStroke(Color.BLUE);
		gc.strokeLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
		gc.strokeLine(0, HEIGHT/2, WIDTH, HEIGHT/2);
		gc.setStroke(Color.BLACK);
		gc.setFont(new Font(WIDTH/60));
		gc.strokeText("x:" + xOrigin + "/" + "y:" + yOrigin, WIDTH/2, HEIGHT/2);
		gc.strokeText("x:" + (xOrigin - 1/zoom) + "/" + "y:" + yOrigin, 0, HEIGHT/2);
		gc.strokeText("x:" + xOrigin + "/" + "y:" + (yOrigin - 1/zoom), WIDTH/2, 2 * HEIGHT/60);
		//gc.strokeText("x:" + xOrigin + "/" + "y:" + (yOrigin - 1/zoom), WIDTH/2, 0);
	}

	private void canvasClicked(MouseEvent event) {
		double px = event.getX();
		double py = event.getY();

		xOrigin = xOrigin + (px / (WIDTH / 2) - 1) / zoom;
		yOrigin = yOrigin + (1 - py / (HEIGHT / 2)) / zoom;
		if (event.getButton() == MouseButton.PRIMARY) {
			zoom *= 2;
		}
		else if (event.getButton() == MouseButton.SECONDARY) {
			zoom /= 2;
		}
		drawAll();

	}

	private void drawAll() {
		drawShapes();
		drawAxis();
		drawHalffAxis();
	}

	private static List<Point> positions = null;

	private void drawShapes() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		ChainedList<Complex> initialChain = null;
		ChainedList<Complex> chain = null;
		
		for (int h  =0 ; h < HEIGHT; h++) {
			double re = REAXISLINE;
			
			Complex c = new Complex(re, yOrigin - 1/zoom + (h * 2) / (HEIGHT * zoom));
			//System.out.println("c:" + c);
			//Complex c = new Complex(re, Double.valueOf(h));
			if (initialChain == null) {
				initialChain = new ChainedList<Complex>(c);
				chain = initialChain;
			}
			else {
				chain = chain.addFollowing(c);
			}
		}
		
		ChainedList<Complex> currentChain = initialChain; 
		
		ChainedList<Complex> initialTargetChain = null;
		ChainedList<Complex> targetChain = null;
		
		//TODO Factory
		Zeta zeta = new Zeta();
		F f = new F();
		SinComplex sin = new SinComplex();
		
		
		while(currentChain.getNext() != null) {
			Complex value = currentChain.getValue();
			//Complex fzeta = f.getValue(zeta.getValue(value, ITERZETA, null) , 0, null);
			//Complex fzeta = sin.getValue(value, ITERZETA, null);
			Complex fzeta = zeta.getValue(value, ITERZETA, null);
			
			if (fzeta.abs() < 0.3) {
				double x =  getRealDouble(fzeta);
				double y = getImDouble(fzeta);
				gc.setFill(Color.RED);
				gc.fillOval(value.getReal(), value.getImaginary(), 3, 3);
			}
			
			
			
			if (initialTargetChain == null) {
				initialTargetChain = new ChainedList<Complex>(fzeta);
				targetChain = initialTargetChain; 
			}
			else {
				targetChain = targetChain.addFollowing(fzeta);
			}
			currentChain = currentChain.getNext();
		}
		
		currentChain = initialTargetChain; 
		Complex first = null;
		while(currentChain.getNext() != null) {
			if (first == null) {
				first = currentChain.getValue();
			}
			currentChain = currentChain.getNext();
			Complex second = currentChain.getNext().getValue();
			drawLine (first, second);
			first = second;
			currentChain = currentChain.getNext();
			
		}
	}
 

	private void drawLine(Complex first, Complex second) {
		gc.setStroke(Color.BLUE);
		
		double xFirst = 	getRealDouble(first);
		double xSecond = 	getRealDouble(second);
		
		double yFirst =  getImDouble(first);
		double ySecond = getImDouble(second);
		
		gc.strokeLine(xFirst, yFirst, xSecond, ySecond);
	}

	private double getImDouble(Complex first) {
		return HEIGHT / 2 - (first.getImaginary() - yOrigin) * zoom * (HEIGHT /2);
	}

	private double getRealDouble(Complex first) {
		return WIDTH / 2 + (first.getReal() - xOrigin) * zoom * (WIDTH /2);
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}
}
