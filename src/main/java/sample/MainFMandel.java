package sample;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.math3.complex.Complex;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainFMandel extends Application {

	private static final int LOG_FREQUENCY = 70000;
	private static final int MAX_ABS = 2;
	private static final int ITER = 70;

	private static int HEIGHT = 400;
	private static int WIDTH = 400;

	private double xOrigin = 0;
	private double yOrigin = -0.5;
	private double zoom = 0.5;
	private GraphicsContext gc;
	private Canvas canvas;

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Mandel Like Fractal");
		Group root = new Group();
		canvas = new Canvas(WIDTH, HEIGHT);
		gc = canvas.getGraphicsContext2D();
		canvas.setOnMouseClicked(event -> canvasClicked(event));

		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		drawShapes();
	}

	private void canvasClicked(MouseEvent event) {
		double px = event.getX();
		double py = event.getY();

		xOrigin = xOrigin + (px / (WIDTH / 2) - 1) / zoom;
		yOrigin = yOrigin + (1 - py / (HEIGHT / 2)) / zoom;
		if (event.getButton() == MouseButton.PRIMARY) {
			zoom *= 2;
		} else if (event.getButton() == MouseButton.SECONDARY) {
			zoom /= 2;
		}

		drawShapes();
		System.out.println("clicked " + "zoom:" + zoom + "/" + "xOrigin:" + xOrigin + "/" + "yOrigin:" + yOrigin);
	}

	private void drawShapes() {

		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);

		// from white to blue can change if you want another rendering
		List<Color> outColorList = new ArrayList<>();
		
		
		int index = 0;
		for (int i = 0; i < 255; i++) {
			outColorList.add(Color.color((double) 1 - ((double) i / (double) 255),
					(double) 1 - ((double) i / (double) 255), 1));
//			outColorList.add(Color.color( 0, 0, (double)1 - ((double)i / (double)255) ));
		}
		
//		for (int i = 0; i < 30; i++) {
//			colorsOut1[index++] = Color.color((double) 1 - ((double) i / (double) 255),
//					(double) 1 - ((double) i / (double) 255), 1);
//			outColorList.add(Color.color( 0, ((double)i*(double)3 / (double) 255), 0 ));
//		}
		Color[] colorsOut1 = new Color[outColorList.size()];
		outColorList.toArray(colorsOut1);
		

//		Color[] colorsOut2 = new Color[255];
//		index = 0;
//		for (int i = 0; i < 255; i++) {
//			colorsOut2[index++] = Color.color((double) i / (double) 255, (double) i / (double) 255, 0);
//		}

		// from black to red inside the SET, can change if you want another
		// rendering
		Color[] colorsInBlackToRed = new Color[255];
		index = 0;
		for (int i = 0; i < 255; i++) {
			colorsInBlackToRed[index++] = Color.color((double) i / (double) 255, 0, 0);
		}
//
//		Color[] colorsInBlackToGreen = new Color[255];
//		index = 0;
//		for (int i = 0; i < 255; i++) {
//			colorsInBlackToGreen[index++] = Color.color(0, (double) i / (double) 255, 0);
//		}

		// Color[] colorsIn = colorsInBlackToRed;
		// Color[] colorsIn = colorsInBlackToGreen;

		for (int w = 0; w < WIDTH; w++) {
			for (int h = 0; h < HEIGHT; h++) {
				double re = (xOrigin - 1 / zoom) + ((double) w / (double) WIDTH) * (2 / zoom);
				double im = (yOrigin + 1 / zoom) - ((double) h / (double) HEIGHT) * (2 / zoom);
				if ((w * WIDTH + h) % LOG_FREQUENCY == 0) {
					System.out.println(w * WIDTH + h + "/" + WIDTH * HEIGHT);
				}

				Complex c = new Complex(re, im);
				Complex seed = new Complex(0, 0);
				int iter = 0;

				for (iter = 0; seed.abs() < MAX_ABS && iter < ITER; iter++) {
					seed = f(f(f(f(f(f(seed.multiply(seed).add(c)))))));
					//seed =seed.multiply(seed).sin().add(c);
					//seed =seed.multiply(seed).add(c);
					// seed =seed.multiply(seed).add(new Complex(0,
					// Math.PI)).tanh().add(c);
					// seed =seed.multiply(seed).multiply(seed).add(new
					// Complex(0, Math.PI)).tanh().add(c);
					// seed =seed.multiply(seed).multiply(seed).add(c);
					//seed = seed.multiply(seed).multiply(seed).atan().add(c);
					// WHAT YOU CAN IMAGINE !!!!!!
					// JUST A COMPLEX RECURSIVE FORMULA
				}

				if (seed.abs() < MAX_ABS) {
					gc.setStroke(
							colorsInBlackToRed[Double.valueOf(seed.abs() * (double) 255 / (double) MAX_ABS).intValue()
							                   % 255]);
				} else {
					//System.out.println(Double.valueOf( ( (double) iter / (double) ITER) * (double)colorsOut1.length ).intValue() % colorsOut1.length);
					gc.setStroke(
							//colorsOut1[Double.valueOf((double) iter * (double) 255 / (double) ITER).intValue() % 255]);
							colorsOut1[Double.valueOf( ( (double) iter / (double) ITER) * (double)colorsOut1.length ).intValue() % colorsOut1.length]);
				}
				gc.strokeLine(w, h, w, h);
			}
		}
		// WANT TO SAVE IT IN PNG FORMAT
		saveGraph();
	}
	
	private Complex f(Complex value) {
		//return new Complex(Math.E, Math.PI).multiply(new Complex(value.getImaginary(), value.getReal()));
		return new Complex(Math.E, 0).pow(new Complex(0, 1).multiply(value).multiply(new Complex(Math.PI)));
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void saveGraph() {
		File file = new File("myFractal.png");
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
