package pedropedrope;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class App extends Application {

    private boolean tocando = false;
    private MediaPlayer player;
    private ImageView imagem;

    @Override
    public void start(Stage stage) {
        Button botao = new Button("Predo");

        // Imagem invisível no início
        imagem = new ImageView();
        imagem.setVisible(false);
        imagem.setFitWidth(200);
        imagem.setPreserveRatio(true);

        // Música
        Media media = new Media(new File("media/pedro-pedro-pe.mp3").toURI().toString());
        player = new MediaPlayer(media);

        // Loop infinito
        player.setOnEndOfMedia(() -> {
            player.seek(Duration.ZERO);
            player.play();
        });

        // Ação do botão
        botao.setOnAction(e -> {
            if (!tocando) {
                Image img = new Image("file:img/giphy.gif");
                imagem.setImage(img);
                imagem.setVisible(true);
                player.play();
                tocando = true;
            } else {
                imagem.setVisible(false);
                player.pause();
                player.seek(Duration.ZERO);
                tocando = false;
            }
        });

        VBox layout = new VBox(10, botao, imagem);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("Pedro Pedro Pe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
