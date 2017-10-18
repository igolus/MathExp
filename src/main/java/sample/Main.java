package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import math.suite.SuiteFactory;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.jenetics.mathexp.math.BigDecimalMath;
import com.jenetics.mathexp.math.BigIntegerSuite;
import com.jenetics.mathexp.math.Point;

public class Main extends Application {

	private static final int COLOR_NB = 65536;
	private static final int SIMPLE_LENGTH = 15000;
	private static BigDecimal bdPI =
			new BigDecimal("3.1415926535897932384626433832795028841971693993751058209");
	private static BigDecimal twoBdPI =
			bdPI.multiply(new BigDecimal("2"));
	//private static BigDecimal RADIUS = new BigDecimal(SIMPLE_LENGTH);

	private static int HEIGHT = 3840;
	private static int WIDTH = 3840;

	private double xOrigin = 0;
	private double yOrigin = 0;
	private float zoom = 1;
	private List<BigInteger> suite;
	private GraphicsContext gc;
	private Canvas canvas;
	//private BigIntegerSuite suiteUsed = SuiteFactory.getSimpleSuite(10000);
	//private BigIntegerSuite suiteUsed = SuiteFactory.getFoboSuite(200000);
	//private BigIntegerSuite suiteUsed = SuiteFactory.getPrimeSuite(SIMPLE_LENGTH);
	private BigIntegerSuite suiteUsed = SuiteFactory.getSimpleSuite(SIMPLE_LENGTH);
	//private BigIntegerSuite suiteUsed = SuiteFactory.getN2Suite(SIMPLE_LENGTH);


	@Override
	public void start(Stage primaryStage) throws Exception{


		primaryStage.setTitle("Prime Fractal");
		Group root = new Group();
		canvas = new Canvas(WIDTH, HEIGHT);
		gc = canvas.getGraphicsContext2D();
		canvas.setOnMouseClicked(event -> canvasClicked(event));


		double middlex = WIDTH / 2;
		double middley = HEIGHT / 2;

		Circle circle = new Circle();
		circle.setCenterX(middlex);
		circle.setCenterY(middley);
		circle.setRadius(WIDTH / 2);
		root.getChildren().add(circle);

		drawShapes(root);
		saveGraph();
		//root.getChildren().add(canvas);

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	private void canvasClicked(MouseEvent event) {
		double px = event.getX();
		double py = event.getY();

		xOrigin = xOrigin + (px / (WIDTH / 2) - 1) / zoom;
		yOrigin = yOrigin + (1 - py / (HEIGHT / 2)) / zoom;
		//System.out.println(event.getX() + "/" + event.getX());
		if (event.getButton() == MouseButton.PRIMARY) {
			zoom *= 2;
		}
		else if (event.getButton() == MouseButton.SECONDARY) {
			zoom /= 2;
		}


		drawShapes(null);


	}

	private static List<Point> positions = null;

	private void drawShapes(Group root) {

		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		suite = getBigDecimalsSuite();

		Color[] colors = new Color[65536];
		for (int i = 0; i < 255; i++) {
			for (int j = 0; j < 255; j++) {
				colors[i+j*255] = Color.color( (double)i/(double)255,  (double)j/(double)255, 1);
			}
		}


		if (positions == null) {
			positions = new ArrayList<>();
			for (int i = 0; i < suite.size(); i++) {
				if (i % 100 == 0) {
					System.out.println("Processed " + i + "/" + suite.size());
				}

				BigInteger prime = suite.get(i);
				BigDecimal divide = BigDecimalMath.divideRound(prime, bdPI);
				BigDecimal remainder = divide.subtract(new BigDecimal(divide.toBigInteger()));
				remainder = remainder.multiply(twoBdPI);
				double realx = BigDecimalMath.cos(remainder).doubleValue();
				double realy = BigDecimalMath.sin(remainder).doubleValue();
				positions.add(new Point(realx, realy));
			}
		}

		double middlex = WIDTH / 2;
		double middley = HEIGHT / 2;

		for (int i = 0; i < positions.size(); i++) {
			Point point = positions.get(i);
			if (point.getX() - xOrigin > 1/zoom || point.getY() - yOrigin > 1/zoom) {
				continue;
			}
			double xPos = WIDTH / 2 + (point.getX() - xOrigin)* zoom * (WIDTH / 2);
			double yPos = HEIGHT / 2 - (point.getY() - yOrigin) * zoom * (HEIGHT / 2);
			int colorIndex = Double.valueOf((double)i * (double)COLOR_NB / (double)SIMPLE_LENGTH).intValue();
			Color color = colors[  colorIndex %  (COLOR_NB - 1)];
			gc.setStroke(color);
			gc.setFill(color);
			gc.setLineWidth(1);
			gc.strokeLine(middlex, middley, xPos, yPos);
		}
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

	public void saveGraph()
	{   
		File file = new File("image.png");
		try {
			WritableImage writableImage = new WritableImage(WIDTH, HEIGHT);
			canvas.snapshot(null, writableImage);
			RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
			ImageIO.write(renderedImage, "png", file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
