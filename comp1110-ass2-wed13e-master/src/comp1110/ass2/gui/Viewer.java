package comp1110.ass2.gui;

import comp1110.ass2.RailroadInk;
import comp1110.ass2.Tile;
import comp1110.ass2.TileType;
import gittest.B;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * A very simple viewer for tile placements in the Railroad Ink game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various tile placements.
 */
public class Viewer extends Application {
    /* board layout */
    private static final int VIEWER_WIDTH = 1600;
    private static final int VIEWER_HEIGHT = 768;
    private static final int OFFSET_X = 180;
    private static final int OFFSET_Y = 180;
    private static final int SIDE_LENGTH = 60;

    private double OX,OY,OTX,OTY;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    HBox dieBox = new HBox();
    TextField textField;
    private Group pieces = new Group();
    private Group score = new Group();
    private ArrayList<Tile> tiles = new ArrayList<>();

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer
        for(int i = 0; i < placement.length(); i += 5){
            tiles.add(new Tile(TileType.valueOf(placement.substring(i, i + 2)), placement.substring(i + 2, i + 4), placement.substring(i + 4, i + 5)));
        }
        for(Tile tile : tiles){
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(Viewer.class.getResource(URI_BASE+ tile.getTileType().toString() + ".png").toString()));
            imageView.setFitWidth(SIDE_LENGTH);
            imageView.setFitHeight(SIDE_LENGTH);
            pieces.getChildren().add(imageView);
            switch (tile.getPosition().charAt(0)){
                case 'A': imageView.setLayoutY(OFFSET_Y + 0 * SIDE_LENGTH);break;
                case 'B': imageView.setLayoutY(OFFSET_Y + 1 * SIDE_LENGTH);break;
                case 'C': imageView.setLayoutY(OFFSET_Y + 2 * SIDE_LENGTH);break;
                case 'D': imageView.setLayoutY(OFFSET_Y + 3 * SIDE_LENGTH);break;
                case 'E': imageView.setLayoutY(OFFSET_Y + 4 * SIDE_LENGTH);break;
                case 'F': imageView.setLayoutY(OFFSET_Y + 5 * SIDE_LENGTH);break;
                case 'G': imageView.setLayoutY(OFFSET_Y + 6 * SIDE_LENGTH);break;
            }
            switch (tile.getPosition().charAt(1)){
                case '0': imageView.setLayoutX(OFFSET_X + 0 * SIDE_LENGTH);break;
                case '1': imageView.setLayoutX(OFFSET_X + 1 * SIDE_LENGTH);break;
                case '2': imageView.setLayoutX(OFFSET_X + 2 * SIDE_LENGTH);break;
                case '3': imageView.setLayoutX(OFFSET_X + 3 * SIDE_LENGTH);break;
                case '4': imageView.setLayoutX(OFFSET_X + 4 * SIDE_LENGTH);break;
                case '5': imageView.setLayoutX(OFFSET_X + 5 * SIDE_LENGTH);break;
                case '6': imageView.setLayoutX(OFFSET_X + 6 * SIDE_LENGTH);break;
            }
            switch (tile.getOrientation()){
                case "0": imageView.setRotate(0);break;
                case "1": imageView.setRotate(90);break;
                case "2": imageView.setRotate(180);break;
                case "3": imageView.setRotate(270);break;
                case "4": switch (tile.getTileType().toString()) {
                    case "S4": case "A0": case "A5":imageView.setRotate(90);break;
                    case "A2": case "A3": imageView.setRotate(180);break;
                    case "B1": imageView.setImage(new Image(Viewer.class.getResource(URI_BASE+ "B14.png").toString()));imageView.setRotate(0);break;
                    default: imageView.setRotate(0);break;
                }break;
                case "5": switch (tile.getTileType().toString()) {
                    case "S4": case "A0": case "A5":imageView.setRotate(180);break;
                    case "A2": case "A3": imageView.setRotate(270);break;
                    case "B1": imageView.setImage(new Image(Viewer.class.getResource(URI_BASE+ "B14.png").toString()));imageView.setRotate(90);break;
                    default: imageView.setRotate(90);break;
                }break;
                case "6": switch (tile.getTileType().toString()) {
                    case "S4": case "A0": case "A5":imageView.setRotate(270);break;
                    case "A2": case "A3": imageView.setRotate(0);break;
                    case "B1": imageView.setImage(new Image(Viewer.class.getResource(URI_BASE+ "B14.png").toString()));imageView.setRotate(180);break;
                    default: imageView.setRotate(180);break;
                }break;
                case "7": switch (tile.getTileType().toString()) {
                    case "S4": case "A0": case "A5":imageView.setRotate(0);break;
                    case "A2": case "A3": imageView.setRotate(90);break;
                    case "B1": imageView.setImage(new Image(Viewer.class.getResource(URI_BASE+ "B14.png").toString()));imageView.setRotate(270);break;
                    default: imageView.setRotate(270);break;
                }break;

            }

        }
    }

    private void addBasicImages(){
        ImageView borad = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"Board.png").toString()));
        borad.setFitHeight(600);
        borad.setFitWidth(600);
        borad.setLayoutX(60);
        borad.setLayoutY(60);

        ImageView highExit1 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"HighExit.png").toString()));
        highExit1.setFitHeight(SIDE_LENGTH);
        highExit1.setFitWidth(SIDE_LENGTH);
        highExit1.setLayoutX(240);
        highExit1.setLayoutY(140);

        ImageView highExit2 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"HighExit.png").toString()));
        highExit2.setFitHeight(SIDE_LENGTH);
        highExit2.setFitWidth(SIDE_LENGTH);
        highExit2.setLayoutX(480);
        highExit2.setLayoutY(140);

        ImageView highExit3 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"HighExit.png").toString()));
        highExit3.setFitHeight(SIDE_LENGTH);
        highExit3.setFitWidth(SIDE_LENGTH);
        highExit3.setRotate(270);
        highExit3.setLayoutX(140);
        highExit3.setLayoutY(360);

        ImageView highExit4 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"HighExit.png").toString()));
        highExit4.setFitHeight(SIDE_LENGTH);
        highExit4.setFitWidth(SIDE_LENGTH);
        highExit4.setRotate(90);
        highExit4.setLayoutX(580);
        highExit4.setLayoutY(360);

        ImageView highExit5 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"HighExit.png").toString()));
        highExit5.setFitHeight(SIDE_LENGTH);
        highExit5.setFitWidth(SIDE_LENGTH);
        highExit5.setRotate(180);
        highExit5.setLayoutX(240);
        highExit5.setLayoutY(580);

        ImageView highExit6 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"HighExit.png").toString()));
        highExit6.setFitHeight(SIDE_LENGTH);
        highExit6.setFitWidth(SIDE_LENGTH);
        highExit6.setRotate(180);
        highExit6.setLayoutX(480);
        highExit6.setLayoutY(580);

        ImageView railExit1 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"RailExit.png").toString()));
        railExit1.setFitHeight(SIDE_LENGTH);
        railExit1.setFitWidth(SIDE_LENGTH);
        railExit1.setLayoutY(140);
        railExit1.setLayoutX(360);

        ImageView railExit2 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"RailExit.png").toString()));
        railExit2.setFitHeight(SIDE_LENGTH);
        railExit2.setFitWidth(SIDE_LENGTH);
        railExit2.setRotate(270);
        railExit2.setLayoutY(240);
        railExit2.setLayoutX(140);

        ImageView railExit3 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"RailExit.png").toString()));
        railExit3.setFitHeight(SIDE_LENGTH);
        railExit3.setFitWidth(SIDE_LENGTH);
        railExit3.setRotate(270);
        railExit3.setLayoutY(480);
        railExit3.setLayoutX(140);

        ImageView railExit4 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"RailExit.png").toString()));
        railExit4.setFitHeight(SIDE_LENGTH);
        railExit4.setFitWidth(SIDE_LENGTH);
        railExit4.setRotate(90);
        railExit4.setLayoutY(240);
        railExit4.setLayoutX(580);

        ImageView railExit5 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"RailExit.png").toString()));
        railExit5.setFitHeight(SIDE_LENGTH);
        railExit5.setFitWidth(SIDE_LENGTH);
        railExit5.setRotate(90);
        railExit5.setLayoutY(480);
        railExit5.setLayoutX(580);

        ImageView railExit6 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"RailExit.png").toString()));
        railExit6.setFitHeight(SIDE_LENGTH);
        railExit6.setFitWidth(SIDE_LENGTH);
        railExit6.setRotate(180);
        railExit6.setLayoutY(580);
        railExit6.setLayoutX(360);

        root.getChildren().add(borad);
        root.getChildren().add(highExit1);
        root.getChildren().add(highExit2);
        root.getChildren().add(highExit3);
        root.getChildren().add(highExit4);
        root.getChildren().add(highExit5);
        root.getChildren().add(highExit6);

        root.getChildren().add(railExit1);
        root.getChildren().add(railExit2);
        root.getChildren().add(railExit3);
        root.getChildren().add(railExit4);
        root.getChildren().add(railExit5);
        root.getChildren().add(railExit6);

    }

    private void generateDie(){
        String placement = RailroadInk.generateDiceRoll();
//        dieBox.getChildren().clear();
        root.getChildren().removeAll(getDieInOneRoll());
        ImageView piece1 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+placement.substring(0,2)+".png").toString()));
        piece1.setId("diePiece1");
        root.getChildren().add(piece1);
        piece1.setFitHeight(SIDE_LENGTH);
        piece1.setFitWidth(SIDE_LENGTH);
        piece1.setY(150);
        piece1.setX(850);
        piece1.setOnMousePressed(event ->{
            OX = event.getSceneX();
            OY = event.getSceneY();
            OTX = piece1.getTranslateX();
            OTY = piece1.getTranslateY();
        });
        piece1.setOnMouseDragged(event ->{
            piece1.setTranslateX(OTX + event.getSceneX() - OX);
            piece1.setTranslateY(OTY + event.getSceneY() - OY);
        });
        piece1.setOnMouseReleased(event ->{
//            if(piece1.getLayoutX() <= 60+2*SIDE_LENGTH || piece1.getLayoutX() >= 60+9*SIDE_LENGTH
//            ||piece1.getLayoutY() <= 60+2*SIDE_LENGTH || piece1.getLayoutY() >= 60+9*SIDE_LENGTH)
//
        });

        ImageView piece2 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+placement.substring(2,4)+".png").toString()));
        piece2.setId("diePiece2");
        root.getChildren().add(piece2);
        piece2.setFitHeight(SIDE_LENGTH);
        piece2.setFitWidth(SIDE_LENGTH);
        piece2.setY(250);
        piece2.setX(850);
        piece2.setOnMousePressed(event ->{
            OX = event.getSceneX();
            OY = event.getSceneY();
            OTX = piece2.getTranslateX();
            OTY = piece2.getTranslateY();
        });
        piece2.setOnMouseDragged(event ->{
            piece2.setTranslateX(OTX + event.getSceneX() - OX);
            piece2.setTranslateY(OTY + event.getSceneY() - OY);
        });
        piece2.setOnMouseReleased(event ->{
//            if(piece1.getLayoutX() <= 60+2*SIDE_LENGTH || piece1.getLayoutX() >= 60+9*SIDE_LENGTH
//            ||piece1.getLayoutY() <= 60+2*SIDE_LENGTH || piece1.getLayoutY() >= 60+9*SIDE_LENGTH)
//
        });

        ImageView piece3 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+placement.substring(4,6)+".png").toString()));
        piece3.setId("diePiece3");
        root.getChildren().add(piece3);
        piece3.setFitHeight(SIDE_LENGTH);
        piece3.setFitWidth(SIDE_LENGTH);
        piece3.setY(350);
        piece3.setX(850);
        piece3.setOnMousePressed(event ->{
            OX = event.getSceneX();
            OY = event.getSceneY();
            OTX = piece3.getTranslateX();
            OTY = piece3.getTranslateY();
        });
        piece3.setOnMouseDragged(event ->{
            piece3.setTranslateX(OTX + event.getSceneX() - OX);
            piece3.setTranslateY(OTY + event.getSceneY() - OY);
        });
        piece3.setOnMouseReleased(event ->{
//            if(piece1.getLayoutX() <= 60+2*SIDE_LENGTH || piece1.getLayoutX() >= 60+9*SIDE_LENGTH
//            ||piece1.getLayoutY() <= 60+2*SIDE_LENGTH || piece1.getLayoutY() >= 60+9*SIDE_LENGTH)
//
        });

        ImageView piece4 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+placement.substring(6,8)+".png").toString()));
        piece4.setId("diePiece4");
        root.getChildren().add(piece4);
        piece4.setFitHeight(SIDE_LENGTH);
        piece4.setFitWidth(SIDE_LENGTH);
        piece4.setY(450);
        piece4.setX(850);
        piece4.setOnMousePressed(event ->{
            OX = event.getSceneX();
            OY = event.getSceneY();
            OTX = piece4.getTranslateX();
            OTY = piece4.getTranslateY();
        });
        piece4.setOnMouseDragged(event ->{
            piece4.setTranslateX(OTX + event.getSceneX() - OX);
            piece4.setTranslateY(OTY + event.getSceneY() - OY);
        });
        piece4.setOnMouseReleased(event ->{
//            if(piece1.getLayoutX() <= 60+2*SIDE_LENGTH || piece1.getLayoutX() >= 60+9*SIDE_LENGTH
//            ||piece1.getLayoutY() <= 60+2*SIDE_LENGTH || piece1.getLayoutY() >= 60+9*SIDE_LENGTH)
//
        });

//        root.getChildren().add(dieBox);
//        dieBox.setLayoutX(750);
//        dieBox.setLayoutY(90);
//        dieBox.setSpacing(10);
    }

    private ArrayList<Node> getDieInOneRoll(){
        ArrayList<Node> temp = new ArrayList<>();
        for(Node node : root.getChildren()){
            if(node.getId() != null && ((node.getId().equals("diePiece1")  || node.getId() .equals("diePiece2") || node.getId() .equals("diePiece3") || node.getId() .equals("diePiece4"))))
                temp.add(node);
        }
        System.out.println(temp.size());
        return temp;
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(e -> {
            makePlacement(textField.getText());
            textField.clear();
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);

        Button dieButton = new Button("roll dice!");
        dieButton.setOnAction(event -> {
            generateDie();
        });
        dieButton.setLayoutX(700);
        dieButton.setLayoutY(60);
        root.getChildren().add(dieButton);
    }

    private void addSpieces(){
        ImageView s0 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"S0.png").toString()));
        s0.setFitHeight(SIDE_LENGTH);
        s0.setFitWidth(SIDE_LENGTH);
        s0.setLayoutX(1200);
        s0.setLayoutY(100);
        s0.setOnMousePressed(event ->{
            OX = event.getSceneX();
            OY = event.getSceneY();
            OTX = s0.getTranslateX();
            OTY = s0.getTranslateY();
        });
        s0.setOnMouseDragged(event ->{
            s0.setTranslateX(OTX + event.getSceneX() - OX);
            s0.setTranslateY(OTY + event.getSceneY() - OY);
        });
        s0.setOnMouseReleased(event ->{
//            if(piece1.getLayoutX() <= 60+2*SIDE_LENGTH || piece1.getLayoutX() >= 60+9*SIDE_LENGTH
//            ||piece1.getLayoutY() <= 60+2*SIDE_LENGTH || piece1.getLayoutY() >= 60+9*SIDE_LENGTH)
//
        });

        root.getChildren().add(s0);

        ImageView s1 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"S1.png").toString()));
        s1.setFitHeight(SIDE_LENGTH);
        s1.setFitWidth(SIDE_LENGTH);
        s1.setLayoutX(1200);
        s1.setLayoutY(200);
        s1.setOnMousePressed(event ->{
            OX = event.getSceneX();
            OY = event.getSceneY();
            OTX = s1.getTranslateX();
            OTY = s1.getTranslateY();
        });
        s1.setOnMouseDragged(event ->{
            s1.setTranslateX(OTX + event.getSceneX() - OX);
            s1.setTranslateY(OTY + event.getSceneY() - OY);
        });
        s1.setOnMouseReleased(event ->{
//            if(piece1.getLayoutX() <= 60+2*SIDE_LENGTH || piece1.getLayoutX() >= 60+9*SIDE_LENGTH
//            ||piece1.getLayoutY() <= 60+2*SIDE_LENGTH || piece1.getLayoutY() >= 60+9*SIDE_LENGTH)
//
        });
        root.getChildren().add(s1);

        ImageView s2 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"S2.png").toString()));
        s2.setFitHeight(SIDE_LENGTH);
        s2.setFitWidth(SIDE_LENGTH);
        s2.setLayoutX(1200);
        s2.setLayoutY(300);
        s2.setOnMousePressed(event ->{
            OX = event.getSceneX();
            OY = event.getSceneY();
            OTX = s2.getTranslateX();
            OTY = s2.getTranslateY();
        });
        s2.setOnMouseDragged(event ->{
            s2.setTranslateX(OTX + event.getSceneX() - OX);
            s2.setTranslateY(OTY + event.getSceneY() - OY);
        });
        s2.setOnMouseReleased(event ->{
//            if(piece1.getLayoutX() <= 60+2*SIDE_LENGTH || piece1.getLayoutX() >= 60+9*SIDE_LENGTH
//            ||piece1.getLayoutY() <= 60+2*SIDE_LENGTH || piece1.getLayoutY() >= 60+9*SIDE_LENGTH)
//
        });
        root.getChildren().add(s2);

        ImageView s3 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"S3.png").toString()));
        s3.setFitHeight(SIDE_LENGTH);
        s3.setFitWidth(SIDE_LENGTH);
        s3.setLayoutX(1200);
        s3.setLayoutY(400);
        s3.setOnMousePressed(event ->{
            OX = event.getSceneX();
            OY = event.getSceneY();
            OTX = s3.getTranslateX();
            OTY = s3.getTranslateY();
        });
        s3.setOnMouseDragged(event ->{
            s3.setTranslateX(OTX + event.getSceneX() - OX);
            s3.setTranslateY(OTY + event.getSceneY() - OY);
        });
        s3.setOnMouseReleased(event ->{
//            if(piece1.getLayoutX() <= 60+2*SIDE_LENGTH || piece1.getLayoutX() >= 60+9*SIDE_LENGTH
//            ||piece1.getLayoutY() <= 60+2*SIDE_LENGTH || piece1.getLayoutY() >= 60+9*SIDE_LENGTH)
//
        });

        root.getChildren().add(s3);

        ImageView s4 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"S4.png").toString()));
        s4.setFitHeight(SIDE_LENGTH);
        s4.setFitWidth(SIDE_LENGTH);
        s4.setLayoutX(1200);
        s4.setLayoutY(500);
        s4.setOnMousePressed(event ->{
            OX = event.getSceneX();
            OY = event.getSceneY();
            OTX = s4.getTranslateX();
            OTY = s4.getTranslateY();
        });
        s4.setOnMouseDragged(event ->{
            s4.setTranslateX(OTX + event.getSceneX() - OX);
            s4.setTranslateY(OTY + event.getSceneY() - OY);
        });
        s4.setOnMouseReleased(event ->{
//            if(piece1.getLayoutX() <= 60+2*SIDE_LENGTH || piece1.getLayoutX() >= 60+9*SIDE_LENGTH
//            ||piece1.getLayoutY() <= 60+2*SIDE_LENGTH || piece1.getLayoutY() >= 60+9*SIDE_LENGTH)
//
        });
        root.getChildren().add(s4);

        ImageView s5 = new ImageView(new Image(Viewer.class.getResource(URI_BASE+"S5.png").toString()));
        s5.setFitHeight(SIDE_LENGTH);
        s5.setFitWidth(SIDE_LENGTH);
        s5.setLayoutX(1200);
        s5.setLayoutY(600);
        s5.setOnMousePressed(event ->{
            OX = event.getSceneX();
            OY = event.getSceneY();
            OTX = s5.getTranslateX();
            OTY = s5.getTranslateY();
        });
        s5.setOnMouseDragged(event ->{
            s5.setTranslateX(OTX + event.getSceneX() - OX);
            s5.setTranslateY(OTY + event.getSceneY() - OY);
        });
        s5.setOnMouseReleased(event ->{
//            if(piece1.getLayoutX() <= 60+2*SIDE_LENGTH || piece1.getLayoutX() >= 60+9*SIDE_LENGTH
//            ||piece1.getLayoutY() <= 60+2*SIDE_LENGTH || piece1.getLayoutY() >= 60+9*SIDE_LENGTH)
//
        });

        root.getChildren().add(s5);
    }




    @Override
    public void start(Stage primaryStage) {
        int round=0;
        primaryStage.setTitle("StepsGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        addBasicImages();
        root.getChildren().add(pieces);
        root.getChildren().add(controls);
        root.getChildren().add(score);

        makeControls();
        addSpieces();

        Text scores = new Text();
        Text rounds = new Text();
        Text pieceS = new Text();
        rounds.setText("Round: "+round);
        scores.setText("Score: "+RailroadInk.getBasicScore(textField.getText()));
        pieceS.setText("S_Pieces:");
        scores.setX(700);
        scores.setY(260);
        rounds.setX(700);
        rounds.setY(300);
        pieceS.setX(1200);
        pieceS.setY(VIEWER_HEIGHT-710);
        score.getChildren().add(scores);
        score.getChildren().add(rounds);
        score.getChildren().add(pieceS);


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
