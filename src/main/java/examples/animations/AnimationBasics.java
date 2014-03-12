/*
 http://docs.oracle.com/javafx/2/animations/basics.htm
 */
package examples.animations;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class AnimationBasics extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        stage.setScene(scene);

        final Rectangle rectPath = new Rectangle(0, 0, 40, 40);
        rectPath.setArcHeight(10);
        rectPath.setArcWidth(10);
        rectPath.setFill(Color.ORANGE);

        Path path = new Path();
        path.getElements().add(new MoveTo(20, 20));
        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
        path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(rectPath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();

        //show indow
        stage.show();
    }

}


/*


1 Animation Basics

Animation in JavaFX can be divided into timeline animation and transitions. This chapter provides examples of each animation type.

    Transitions

    Timeline Animation

    Interpolators

Timeline and Transition are subclasses of the javafx.animation.Animation class. For more information about particular classes, methods, or additional features, see the API documentation.
Transitions

Transitions in JavaFX provide the means to incorporate animations in an internal timeline. Transitions can be composed to create multiple animations that are executed in parallel or sequentially. See the Parallel Transition and Sequential Transition sections for details. The following sections provide some transition animation examples.
Fade Transition

A fade transition changes the opacity of a node over a given time.

Example 1-1 shows a code snippet for a fade transition that is applied to a rectangle. First a rectangle with rounded corners is created, and then a fade transition is applied to it.

Example 1-1 Fade Transition

final Rectangle rect1 = new Rectangle(10, 10, 100, 100);
rect1.setArcHeight(20);
rect1.setArcWidth(20);
rect1.setFill(Color.RED);
...
FadeTransition ft = new FadeTransition(Duration.millis(3000), rect1);
ft.setFromValue(1.0);
ft.setToValue(0.1);
ft.setCycleCount(Timeline.INDEFINITE);
ft.setAutoReverse(true);
ft.play();

Path Transition

A path transition moves a node along a path from one end to the other over a given time.

Figure 1-1 Path Transition
Description of Figure 1-1 follows
Description of "Figure 1-1 Path Transition"

Example 1-2 shows a code snippet for a path transition that is applied to a rectangle. The animation is reversed when the rectangle reaches the end of the path. In code, first a rectangle with rounded corners is created, and then a new path animation is created and applied to the rectangle.

Example 1-2 Path Transition

final Rectangle rectPath = new Rectangle (0, 0, 40, 40);
rectPath.setArcHeight(10);
rectPath.setArcWidth(10);
rectPath.setFill(Color.ORANGE);
...
Path path = new Path();
path.getElements().add(new MoveTo(20,20));
path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
PathTransition pathTransition = new PathTransition();
pathTransition.setDuration(Duration.millis(4000));
pathTransition.setPath(path);
pathTransition.setNode(rectPath);
pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
pathTransition.setCycleCount(Timeline.INDEFINITE);
pathTransition.setAutoReverse(true);
pathTransition.play();

Parallel Transition

A parallel transition executes several transitions simultaneously.

Example 1-3 shows the code snippet for the parallel transition that executes fade, translate, rotate, and scale transitions applied to a rectangle.

Figure 1-2 Parallel Transition
Description of Figure 1-2 follows
Description of "Figure 1-2 Parallel Transition"

Example 1-3 Parallel Transition

Rectangle rectParallel = new Rectangle(10,200,50, 50);
rectParallel.setArcHeight(15);
rectParallel.setArcWidth(15);
rectParallel.setFill(Color.DARKBLUE);
rectParallel.setTranslateX(50);
rectParallel.setTranslateY(75);
...
        FadeTransition fadeTransition = 
            new FadeTransition(Duration.millis(3000), rectParallel);
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0.3f);
        fadeTransition.setCycleCount(2);
        fadeTransition.setAutoReverse(true);
        TranslateTransition translateTransition =
            new TranslateTransition(Duration.millis(2000), rectParallel);
        translateTransition.setFromX(50);
        translateTransition.setToX(350);
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);
        RotateTransition rotateTransition = 
            new RotateTransition(Duration.millis(3000), rectParallel);
        rotateTransition.setByAngle(180f);
        rotateTransition.setCycleCount(4);
        rotateTransition.setAutoReverse(true);
        ScaleTransition scaleTransition = 
            new ScaleTransition(Duration.millis(2000), rectParallel);
        scaleTransition.setToX(2f);
        scaleTransition.setToY(2f);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);
        
        parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                fadeTransition,
                translateTransition,
                rotateTransition,
                scaleTransition
        );
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.play();

Sequential Transition

A sequential transition executes several transitions one after another.

Example 1-4 shows the code for the sequential transition that executes one after another. Fade, translate, rotate, and scale transitions that are applied to a rectangle.

Example 1-4 Sequential Transition

Rectangle rectSeq = new Rectangle(25,25,50,50);
rectSeq.setArcHeight(15);
rectSeq.setArcWidth(15);
rectSeq.setFill(Color.CRIMSON);
rectSeq.setTranslateX(50);
rectSeq.setTranslateY(50);

...

         FadeTransition fadeTransition = 
            new FadeTransition(Duration.millis(1000), rectSeq);
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0.3f);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(true);
 
        TranslateTransition translateTransition =
            new TranslateTransition(Duration.millis(2000), rectSeq);
        translateTransition.setFromX(50);
        translateTransition.setToX(375);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);
 
        RotateTransition rotateTransition = 
            new RotateTransition(Duration.millis(2000), rectSeq);
        rotateTransition.setByAngle(180f);
        rotateTransition.setCycleCount(4);
        rotateTransition.setAutoReverse(true);
 
        ScaleTransition scaleTransition = 
            new ScaleTransition(Duration.millis(2000), rectSeq);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(2);
        scaleTransition.setToY(2);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);

sequentialTransition = new SequentialTransition();
sequentialTransition.getChildren().addAll(
        fadeTransition,
        translateTransition,
        rotateTransition,
        scaleTransition);
sequentialTransition.setCycleCount(Timeline.INDEFINITE);
sequentialTransition.setAutoReverse(true);

sequentialTransition.play();

For more information about animation and transitions, see the API documentation and the Animation section in the Ensemble project in the SDK.
Timeline Animation

An animation is driven by its associated properties, such as size, location, and color etc. Timeline provides the capability to update the property values along the progression of time. JavaFX supports key frame animation. In key frame animation, the animated state transitions of the graphical scene are declared by start and end snapshots (key frames) of the state of the scene at certain times. The system can automatically perform the animation. It can stop, pause, resume, reverse, or repeat movement when requested.
Basic Timeline Animation

The code in Example 1-5 animates a rectangle horizontally and moves it from its original position X=100 to X=300 in 500 ms. To animate an object horizontally, alter the x-coordinates and leave the y-coordinates unchanged.

Figure 1-3 Horizontal Movement
Description of Figure 1-3 follows
Description of "Figure 1-3 Horizontal Movement"

Example 1-5 shows the code snippet for the basic timeline animation.

Example 1-5 Timeline Animation

final Rectangle rectBasicTimeline = new Rectangle(100, 50, 100, 50);
rectBasicTimeline.setFill(Color.RED);
...
final Timeline timeline = new Timeline();
timeline.setCycleCount(Timeline.INDEFINITE);
timeline.setAutoReverse(true);
final KeyValue kv = new KeyValue(rectBasicTimeline.xProperty(), 300);
final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
timeline.getKeyFrames().add(kf);
timeline.play();

Timeline Events

JavaFX provides the means to incorporate events that can be triggered during the timeline play. The code in Example 1-6 changes the radius of the circle in the specified range, and KeyFrame triggers the random transition of the circle in the x-coordinate of the scene.

Example 1-6 Timeline Events

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
 
public class TimelineEvents extends Application {
    
    //main timeline
    private Timeline timeline;
    private AnimationTimer timer;
 
    //variable for storing actual frame
    private Integer i=0;
 
    @Override public void start(Stage stage) {
        Group p = new Group();
        Scene scene = new Scene(p);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        p.setTranslateX(80);
        p.setTranslateY(80);
 
        //create a circle with effect
        final Circle circle = new Circle(20,  Color.rgb(156,216,255));
        circle.setEffect(new Lighting());
        //create a text inside a circle
        final Text text = new Text (i.toString());
        text.setStroke(Color.BLACK);
        //create a layout for circle with text inside
        final StackPane stack = new StackPane();
        stack.getChildren().addAll(circle, text);
        stack.setLayoutX(30);
        stack.setLayoutY(30);
 
        p.getChildren().add(stack);
        stage.show();
 
        //create a timeline for moving the circle
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
 
//You can add a specific action when each frame is started.
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                text.setText(i.toString());
                i++;
            }
 
        };
 
        //create a keyValue with factory: scaling the circle 2times
        KeyValue keyValueX = new KeyValue(stack.scaleXProperty(), 2);
        KeyValue keyValueY = new KeyValue(stack.scaleYProperty(), 2);
 
        //create a keyFrame, the keyValue is reached at time 2s
        Duration duration = Duration.millis(2000);
        //one can add a specific action when the keyframe is reached
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                 stack.setTranslateX(java.lang.Math.random()*200-100);
                 //reset counter
                 i = 0;
            }
        };
 
  KeyFrame keyFrame = new KeyFrame(duration, onFinished , keyValueX, keyValueY);
 
        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);
 
        timeline.play();
        timer.start();
    }
        
        
    public static void main(String[] args) {
        Application.launch(args);
    }
  } 

Interpolators

Interpolation defines positions of the object between the start and end points of the movement. You can use various built-in implementations of the Interpolator class or you can implement your own Interpolator to achieve custom interpolation behavior.
Built-in Interpolators

JavaFX provides several built-in interpolators that you can use to create different effects in your animation. By default, JavaFX uses linear interpolation to calculate the coordinates.

Example 1-7 shows a code snippet where the EASE_BOTH interpolator instance is added to the KeyValue in the basic timeline animation. This interpolator creates a spring effect when the object reaches its start point and its end point.

Example 1-7 Built-in Interpolator

final Rectangle rectBasicTimeline = new Rectangle(100, 50, 100, 50);
rectBasicTimeline.setFill(Color.BROWN);
...
final Timeline timeline = new Timeline();
timeline.setCycleCount(Timeline.INDEFINITE);
timeline.setAutoReverse(true);
final KeyValue kv = new KeyValue(rectBasicTimeline.xProperty(), 300,
 Interpolator.EASE_BOTH);
final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
timeline.getKeyFrames().add(kf);
timeline.play();

Custom Interpolators

Apart from built-in interpolators, you can implement your own interpolator to achieve custom interpolation behavior. A custom interpolator example consists of two java files. Example 1-8 shows a custom interpolator that is used to calculate the y-coordinate for the animation. Example 1-9 shows the code snippet of the animation where the AnimationBooleanInterpolator is used.

Example 1-8 Custom Interpolator

public class AnimationBooleanInterpolator extends Interpolator {
    @Override
    protected double curve(double t) {
        return Math.abs(0.5-t)*2 ;
    }
}

Example 1-9 Animation with Custom Interpolator

 final KeyValue keyValue1 = new KeyValue(rect.xProperty(), 300);
 AnimationBooleanInterpolator yInterp = new AnimationBooleanInterpolator();
 final KeyValue keyValue2 = new KeyValue(rect.yProperty(), 0., yInterp);



*/
