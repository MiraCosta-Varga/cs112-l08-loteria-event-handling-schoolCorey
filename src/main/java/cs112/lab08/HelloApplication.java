package cs112.lab08;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application {

    //CONSTANTS
    private static final double STARTING_NUMBER_OF_CARDS = 4.0; // double to avoid integer division

    //no longer a constant
    //array of LoteriaCards to use for game:
    private static LoteriaCard[] loteriaCards = {
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
        titleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR,14));
        ImageView cardImageView = new ImageView(echaleLogoCard.getImage());
        cardImageView.setPreserveRatio(true);
        cardImageView.setFitHeight(350);
        Label messageLabel = new Label("Click the button to start the game!\nThe progress bar will keep track of cards drawn.");
        messageLabel.setFont(Font.font("Arial",FontWeight.NORMAL,FontPosture.REGULAR,13));
        messageLabel.setTextAlignment(TextAlignment.CENTER);
        ProgressBar gameProgressBar = new ProgressBar(0); //starts half-full so we can see visually
        gameProgressBar.setMinWidth(150);

        Button drawCardButton = new Button("Draw Random Card");
        drawCardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(loteriaCards.length>0){
                    int cardIndex = rng.nextInt(loteriaCards.length);
                    LoteriaCard nextCard = loteriaCards[cardIndex]; //Between 0 inc and value excl, so no off-by-one errors
                    cardImageView.setImage(nextCard.getImage());
                    messageLabel.setText("\n"+nextCard.getCardName()); //newline to keep formatting nice; stops the elements from changing positions
                    loteriaCards = removeElement(loteriaCards,cardIndex);
                    gameProgressBar.setProgress(1-(loteriaCards.length/STARTING_NUMBER_OF_CARDS));

                }else{
                    gameProgressBar.setStyle("-fx-accent: red;");
                    drawCardButton.setDisable(true);
                    cardImageView.setImage(echaleLogoCard.getImage());
                    messageLabel.setText("All cards have already been drawn!\nExit and run program again to reset ^_^");
                }



            }
        });


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

    public static LoteriaCard[] removeElement(LoteriaCard[]originalArray, int element){
        int oldLength = originalArray.length;
        int newIndex = 0;
        if(oldLength<=0){
            return originalArray;
        }
        LoteriaCard[] newArray = new LoteriaCard[oldLength-1];
        for (int i = 0; i < oldLength; i++){
            if (i == element){
                continue;
            }else{
                newArray[newIndex] = originalArray[i];
            }
            newIndex++;
        }
        return newArray;
    }
}