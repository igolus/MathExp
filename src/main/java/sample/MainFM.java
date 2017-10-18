package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import math.suite.SuiteFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.complex.Complex;

import com.jenetics.mathexp.math.BigDecimalMath;
import com.jenetics.mathexp.math.BigIntegerSuite;
import com.jenetics.mathexp.math.Point;

public class MainFM extends Application {

    private static final int MAX_ABS = 2500;
	private static final int T2552 = 65025;
	private static final int NORMZ = 5000;
	private static final int ITER = 200;
	private static BigDecimal bdPI =
            new BigDecimal("3.1415926535897932384626433832795028841971693993751058209");
    private static BigDecimal twoBdPI =
            bdPI.multiply(new BigDecimal("2"));
    private static BigDecimal RADIUS = new BigDecimal(300);

    private static int HEIGHT = 400;
    private static int WIDTH = 400;
    private static Complex TWOC = new Complex(2);
    private static Complex EC = new Complex((double)2.718281828459045);
    private static Complex C1 = new Complex(1);
    

    private double xOrigin = 0;
    private double yOrigin = 0;
    private double zoom = 0.5;
    private List<BigInteger> suite;
    private GraphicsContext gc;
    private Canvas canvas;
    //private BigIntegerSuite suiteUsed = SuiteFactory.getSimpleSuite(10000);
    //private BigIntegerSuite suiteUsed = SuiteFactory.getFoboSuite(200000);
    private BigIntegerSuite suiteUsed = SuiteFactory.getPrimeSuite(10000);

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Prime Fractal");
        Group root = new Group();
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        canvas.setOnMouseClicked(event -> canvasClicked(event));
        drawShapes();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
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
        drawShapes();
    }

    private static List<Point> positions = null;

    private void drawShapes() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        suite = getBigDecimalsSuite();
        
        
        double realValue;
        double complexValue;
        
        Color[] colors = new Color[ITER + 1];
        for (int i = 0; i < colors.length; i++) {
        	colors[i] = Color.color(i /ITER, 0, 0);
		}
        
        Color[] colorsIn = new Color[T2552];
        int index = 0;
        for (int i = 0; i < 255; i++) {
        	for (int j = 0; j < 255; j++) {
        		colorsIn[index++] = Color.color(0,  i /ITER, i /ITER);
			}
        	
		}
        
        
        for (int w = 0; w < WIDTH; w++) {
        	 for (int h  = 0; h < HEIGHT; h++) {
             	double re = (xOrigin - 1 / zoom) + ((double)w / (double)WIDTH) * (2/zoom);
             	double im = (yOrigin + 1 / zoom) - ((double)h / (double)HEIGHT) * (2/zoom);
             	
             	Complex c = new Complex(re, im);
             	Complex seed = new Complex(1, 0);
             	int iter = 0;
             	
             	for (iter = 2; iter < ITER && seed.abs() < MAX_ABS; iter++) {
             		seed = f(f(seed.add(C1.divide(  new Complex(iter).pow(c)))));
             	}
             	if (seed.abs() < 0.1) {
             		
             		gc.setStroke(Color.BLUE);
             		gc.fillOval(w, h, 1/(20*zoom), 1/(20*zoom));

             	}
             	else if (iter == ITER) {
             		gc.setStroke(colorsIn[ Math.min(Double.valueOf(seed.abs() * T2552/ MAX_ABS).intValue() , T2552 - 1)]);
             	}
             	else {
             		gc.setStroke(colors[iter]);
             	}
             		
             	
//             	if (seed.abs() < NORMZ) {
//             		gc.setStroke(colors[ Math.min(Double.valueOf(seed.abs()*ITER/NORMZ).intValue() , ITER) ]);
//             	}
             	
             	//gc.setStroke(colors[iter]);
             	
//             	for (iter = 2; iter < ITER && seed.abs() < 2; iter++) {
//             		
//
//             		//seed = f(f(f(f(f(seed.multiply(seed).add(c))))))²;
//             		//seed = f(f(f(f(seed.multiply(seed).add(c)))));
//             		//seed = f(f(f(seed.multiply(seed).add(c))));
//             		//seed = f(f(seed.multiply(seed).add(c)));
//             		//seed = f(seed.multiply(seed).add(c));
//             		//seed = seed.multiply(seed).add(c);
//             	}
             	//gc.setStroke(colors[iter]);
             	gc.strokeLine(w, h, w, h);
             }
        }
        //System.out.println("end");
        //gc.strokeLine(xPos, yPos, xPos, yPos);
    }
    
    private Complex f(Complex z) {
    	return EC.pow(Complex.I.multiply(z));
    }

    private List<BigInteger> getBigDecimalsSuite() {
        if (suite == null) {
            suite =  suiteUsed.fillSuite();
        }
        return suite;
    }


    public static void main(String[] args) {
            launch(args);
    }
}
