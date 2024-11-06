package cs112.lab08;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application {

    //CONSTANTS

    //array of LoteriaCards to use for game:
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    };

    //Random seed
    Random rng = new Random();

    private static final LoteriaCard echaleLogoCard = new LoteriaCard(); //Default constructor has logo info already


    @Override
    public void start(Stage stage) throws IOException {
        //removed FXML code, fill this in with components, scene, stage, etc.

        //Setup components
        Label titleLabel = new Label("Welcome to EChALE STEM Loteria!");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR,16));
        ImageView cardImageView = new ImageView(echaleLogoCard.getImage());
        cardImageView.setPreserveRatio(true);
        cardImageView.setFitHeight(375);
        Label messageLabel = new Label("Click the button to start the game!");
        messageLabel.setFont(Font.font("Arial",FontWeight.NORMAL,FontPosture.REGULAR,14));
        Button drawCardButton = new Button("Draw Random Card");
        drawCardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LoteriaCard nextCard = LOTERIA_CARDS[rng.nextInt(LOTERIA_CARDS.length)]; //Between 0 inc and value excl, so no off-by-one errors
                cardImageView.setImage(nextCard.getImage());
                messageLabel.setText(nextCard.getCardName());
            }
        });

        ProgressBar gameProgressBar = new ProgressBar(0); //starts half-full so we can see visually
        gameProgressBar.setMinWidth(150);

        //Add components
        VBox layout = new VBox(8); //set spacing in constructor arg
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(titleLabel,cardImageView,messageLabel,drawCardButton,gameProgressBar);
        stage.show();

        //Setup scene, show
        Scene scene = new Scene(layout,350,500);
        stage.setScene(scene);
        stage.setTitle("EChALE STEM Loteria");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}