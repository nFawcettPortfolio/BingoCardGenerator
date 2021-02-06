/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingocardgenerator;

import java.awt.Point;
import static java.awt.SystemColor.text;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


/**
 *
 * @author Kami
 */
public class BingoCardGenerator extends Application {
    

    
    @Override
    public void start(Stage primaryStage) {
        Group group = new Group();
        
        // DRAW LINES
        ObservableList<Point> arrPoint = FXCollections.observableArrayList();
        for (int i = 0; i<6;i++){
            Point horizontalStart = new Point(50,100+(i*100));
            Point horizontalEnd = new Point(550,100+(i*100));
            arrPoint.addAll(horizontalStart, horizontalEnd);
        }
        System.out.println(arrPoint.size());
        for (int i = 0; i < arrPoint.size()-1; i+=2) {
            Line line = new Line();
            line.setStartX(arrPoint.get(i).getX());        
            line.setStartY(arrPoint.get(i).getY()); 
            line.setEndX(arrPoint.get(i+1).getX());
            line.setEndY(arrPoint.get(i+1).getY());
            group.getChildren().add(line);
        }   
        ObservableList<Point> vertPoints = FXCollections.observableArrayList();
        for (int i = 0; i<6;i++){
            Point vertStart = new Point(50+(i*100),100);
            Point vertEnd = new Point(50+(i*100),600);
            vertPoints.addAll(vertStart, vertEnd);
        }
        System.out.println(arrPoint.size());
        for (int i = 0; i < arrPoint.size()-1; i+=2) {
            Line line = new Line();
            line.setStartX(vertPoints.get(i).getX());     
            line.setStartY(vertPoints.get(i).getY());
            line.setEndX(vertPoints.get(i+1).getX());
            line.setEndY(vertPoints.get(i+1).getY());
            group.getChildren().add(line);
        }
        
        // BANNER IMAGE
        Image banner = new Image("file:src/Banner.png") {};
        ImageView vBanner = new ImageView(banner);
        vBanner.setFitHeight(90);
        vBanner.setFitWidth(502);
        Button vBtn = new Button();
        vBtn.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(primaryStage);
            if(file != null){
                Image newImg = new Image(file.toURI().toString());
                ImageView newView = new ImageView(newImg);
                newView.setFitHeight(90);
                newView.setFitWidth(502);
                vBtn.setGraphic(newView);
                primaryStage.show();
            }
        });
        vBtn.setLayoutX(41);
        vBtn.setLayoutY(5);
        vBtn.setStyle("-fx-background-color: transparent;");
        vBtn.setPrefSize(85, 85);
        vBtn.setGraphic(vBanner);
        group.getChildren().add(vBtn);
        
        
        
        // FREE SPACE
        Image img = new Image("file:src/Free.png") {};
        ImageView view = new ImageView(img);
        view.setFitHeight(100);
        view.setFitWidth(100);
        Button freeBtn = new Button();
        freeBtn.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(primaryStage);
            if(file != null){
                Image newImg = new Image(file.toURI().toString());
                ImageView newView = new ImageView(newImg);
                newView.setFitHeight(99);
                newView.setFitWidth(99);
                freeBtn.setGraphic(newView);
                primaryStage.show();
            }
        });
        freeBtn.setLayoutX(241);
        freeBtn.setLayoutY(295);
        freeBtn.setStyle("-fx-background-color: transparent;");
        freeBtn.setPrefSize(85, 85);
        freeBtn.setGraphic(view);
        group.getChildren().add(freeBtn);
        
        // TEXT FIELDS
        int boxes = 0;
        int count = 0;
        TextField[] txt = new TextField[24];
        for (int i=0; i<5; i++) {
            for (int j = 0; j<5; j++){
                if (count!=12){
                TextField textField = new TextField();
                txt[boxes]= textField;
                textField.setLayoutX((j*100)+51);
                textField.setLayoutY(i*100+101);
                textField.setPrefWidth((99));
                textField.setPrefHeight(99);
                textField.setAlignment(Pos.CENTER);
                textField.setStyle("-fx-background-color: transparent;");
                boxes++;
                }
                count++;
            } 
           }
           group.getChildren().addAll(Arrays.asList(txt));

        
        
        
        // SHUFFLE BUTTON
        Button shffuleBtn = new Button("Shuffle");
        shffuleBtn.setOnAction((ActionEvent event) -> {
            Random rand = new Random();
            String[] st = new String[txt.length];
            for (int i = 0; i < txt.length; i++) {
                st[i] = txt[i].getText();
            }
            List<String> stList = Arrays.asList(st);
            Collections.shuffle(stList);
            stList.toArray(st);
            for (int i = 0; i < st.length; i++) {
                txt[i].setText(st[i]);
            }
            primaryStage.show();
        });
        shffuleBtn.setLayoutX(400);
        shffuleBtn.setLayoutY(700);
        group.getChildren().add(shffuleBtn);

        
        // SAVE BUTTON
        Button saveButton = new Button("Save");
        saveButton.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            
            File file = fileChooser.showSaveDialog(primaryStage);
            
            if(file != null){
                try {
                    WritableImage writableImage = new WritableImage(620, 620);
                    group.snapshot(null, writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    ImageIO.write(renderedImage, "png", file);
                } catch (IOException ex) {
                    
                }
            }
        });
        saveButton.setLayoutX(200);
        saveButton.setLayoutY(700);
        group.getChildren().add(saveButton);
        Scene scene = new Scene(group, 620, 800, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
