package Affichage;

import Jeu.Partie;
import Jeu.Fantome;
import Outils.Entites;
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.util.Duration;

import static java.lang.Thread.sleep;

public class Affichage extends Application{
    Fantome[] tabFantome;
    int[] compteurDeBoucle = {0,0,0,0};
    int[] direction = {0,0,0,0};
    @Override
    public void start(Stage stage)throws Exception{
        stage.setTitle("Preview PacmaFX");
        GridPane root = new GridPane();

        Partie p1 = new Partie();
        int x;

        for (int i = 0; i < 22; i++) {
            for (x = 0; x<22;x++) {
                Entites actualEntite = p1.getGrille()[i][x];
//                System.out.println(actualEntite);
                ImageView iv = new ImageView();
                if (actualEntite.equals(Entites.MUR)){
                    FileInputStream input = new FileInputStream("externes/images/MUR.png");
                    Image image = new Image(input,20,20 ,true,true);
                    iv.setImage(image);
                } else if (actualEntite.equals(Entites.PORTE)){
                    FileInputStream input = new FileInputStream("externes/images/FOND_BLANC.png");
                    Image image = new Image(input,20,20 ,true,true);
                    iv.setImage(image);
                }
                else{
                    FileInputStream inputN = new FileInputStream("externes/images/FOND_NOIR.png");
                    Image img = new Image(inputN,20,20 ,true,true);
                    iv.setImage(img);
                }
                root.add(iv, i, x);
            }
        }
        Scene scene = new Scene(root, 440, 440);
        stage.setScene(scene);


        tabFantome = p1.getFantome();

        root.add(tabFantome[0].getFantome(), tabFantome[0].getSpawnX(), tabFantome[0].getSpawnY());
        root.add(tabFantome[1].getFantome(), tabFantome[1].getSpawnX(), tabFantome[1].getSpawnY());
        root.add(tabFantome[2].getFantome(), tabFantome[2].getSpawnX(), tabFantome[2].getSpawnY());
        root.add(tabFantome[3].getFantome(), tabFantome[3].getSpawnX(), tabFantome[3].getSpawnY());

        stage.show();
        new AnimationTimer() {


            @Override
            public void handle(long now){


                for (int j = 0; j < 4; j++) {
//                    System.out.println("Fantome:"+j+","+compteurDeBoucle[j]+","+direction[j]);
//                    if(compteurDeBoucle[j] == 20){
//                    System.out.println("20");
//                        compteurDeBoucle[j] = 0;
                        int t = (int) (Math.random() * 4);
                        switch (t) {
                            case (0) -> tabFantome[j].getFantome().setTranslateX(20);
                            case (1) -> tabFantome[j].getFantome().setTranslateX(-20);
                            case (2) -> tabFantome[j].getFantome().setTranslateY(20);
                            case (3) -> tabFantome[j].getFantome().setTranslateY(-20);
                            default -> System.out.println("Error:" + t);
                        }
//                        direction[j] = t;
//                        compteurDeBoucle[j]++;
//                    }
//                    else{
//                        System.out.println("pas 20");
//                        compteurDeBoucle[j]++;
//                        switch (direction[j]) {
//                            case (0) -> tabFantome[j].getFantome().setTranslateX(1);
//                            case (1) -> tabFantome[j].getFantome().setTranslateX(-1);
//                            case (2) -> tabFantome[j].getFantome().setTranslateY(1);
//                            case (3) -> tabFantome[j].getFantome().setTranslateY(-1);
//                            default -> System.out.println("Error:" + direction[j]);
//                        }
//                    }
                }
//                sleep(1000);
            }
        }.start();


    }
    public static void main(String[] args){
        launch(args);
    }
}


