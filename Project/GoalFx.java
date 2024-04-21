package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.List;

public class GoalFx extends Application {
    private Stage primaryStage;
    private Artist artist;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Music App Goal Setting");

        // Create artist
        artist = new Artist("Artist Name");

        // Create GUI components for the input page
        Label artistLabel = new Label("Artist Name:");
        TextField artistTextField = new TextField();

        Button createArtistButton = new Button("Create Artist");
        createArtistButton.setOnAction(event -> {
            String artistName = artistTextField.getText();
            artist = new Artist(artistName);
            displayOptionsPage();
        });

        // Layout using a VBox for the input page
        VBox inputVBox = new VBox(10);
        inputVBox.getChildren().addAll(
            artistLabel, artistTextField,
            createArtistButton
        );
        inputVBox.setPadding(new Insets(10));

        // Create and set the Scene for the input page
        Scene inputScene = new Scene(inputVBox, 400, 150);
        inputScene.setFill(Color.LIGHTBLUE);
        primaryStage.setScene(inputScene);

        // Show the Stage
        primaryStage.show();
    }

    // Display the options page
    private void displayOptionsPage() {
        // Create GUI components for options page
        Label optionsLabel = new Label("Options:");
        //optionsLabel.setFont(Font.font("Arial",16);

        Button addGoalButton = new Button("Add a New Goal");
        addGoalButton.setOnAction(event -> {
            displayAddGoalPage();
        });

        Button viewGoalsButton = new Button("View Artist Goals");
        viewGoalsButton.setOnAction(event -> {
            displayArtistGoals();
        });

        Button markAchievedButton = new Button("Mark a Goal as Achieved");
        markAchievedButton.setOnAction(event -> {
            displayMarkAchievedPage();
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            primaryStage.close();
        });

        // Layout using a VBox for the options page
        VBox optionsVBox = new VBox(10);
        optionsVBox.getChildren().addAll(
            optionsLabel,
            addGoalButton,
            viewGoalsButton,
            markAchievedButton,
            exitButton
        );
        optionsVBox.setPadding(new Insets(10));

        // Create and set the Scene for the options page
        Scene optionsScene = new Scene(optionsVBox, 400, 250);
        optionsScene.setFill(Color.LIGHTBLUE);
        primaryStage.setScene(optionsScene);
    }

    // Display the page for adding a new goal
    private void displayAddGoalPage() {
        // Create GUI components for adding a new goal
        Label goalDescriptionLabel = new Label("Goal Description:");
        TextField goalDescriptionTextField = new TextField();

        Label deadlineLabel = new Label("Deadline (e.g., 2023-01-01):");
        TextField deadlineTextField = new TextField();

        Button addButton = new Button("Add Goal");
        addButton.setOnAction(event -> {
            String goalDescription = goalDescriptionTextField.getText();
            String deadline = deadlineTextField.getText();
            artist.addGoal(goalDescription, deadline);
            displayOptionsPage();
        });

        // Layout using a VBox for the add goal page
        VBox addGoalVBox = new VBox(10);
        addGoalVBox.getChildren().addAll(
            goalDescriptionLabel, goalDescriptionTextField,
            deadlineLabel, deadlineTextField,
            addButton
        );
        addGoalVBox.setPadding(new Insets(10));

        // Create and set the Scene for the add goal page
        Scene addGoalScene = new Scene(addGoalVBox, 400, 250);
        addGoalScene.setFill(Color.LIGHTBLUE);
        primaryStage.setScene(addGoalScene);
    }

    // Display the artist's goals
    private void displayArtistGoals() {
        List<Goal> artistGoals = artist.getGoals();

        // Create a TextArea to display the artist's goals
        TextArea goalsTextArea = new TextArea();
        goalsTextArea.setWrapText(true);
        goalsTextArea.setEditable(false);

        for (int i = 0; i < artistGoals.size(); i++) {
            goalsTextArea.appendText("Goal " + (i + 1) + ": " + artistGoals.get(i) + "\n");
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            displayOptionsPage();
        });

        // Layout using a VBox for the artist goals page
        VBox artistGoalsVBox = new VBox(10);
        artistGoalsVBox.getChildren().addAll(
            goalsTextArea,
            backButton
        );
        artistGoalsVBox.setPadding(new Insets(10));

        // Create and set the Scene for the artist goals page
        Scene artistGoalsScene = new Scene(artistGoalsVBox, 400, 400);
        artistGoalsScene.setFill(Color.LIGHTBLUE);
        primaryStage.setScene(artistGoalsScene);
    }

    // Display the page for marking a goal as achieved
    private void displayMarkAchievedPage() {
        // Create GUI components for marking a goal as achieved
        Label goalNumberLabel = new Label("Enter the goal number to mark as achieved:");
        TextField goalNumberTextField = new TextField();

        Button markButton = new Button("Mark as Achieved");
        markButton.setOnAction(event -> {
            int goalNumber = Integer.parseInt(goalNumberTextField.getText());
            artist.markGoalAsAchieved(goalNumber - 1); // Adjust for 0-based indexing
            displayOptionsPage();
        });

        // Layout using a VBox for the mark achieved page
        VBox markAchievedVBox = new VBox(10);
        markAchievedVBox.getChildren().addAll(
            goalNumberLabel, goalNumberTextField,
            markButton
        );
        markAchievedVBox.setPadding(new Insets(10));

        // Create and set the Scene for the mark achieved page
        Scene markAchievedScene = new Scene(markAchievedVBox, 400, 250);
        markAchievedScene.setFill(Color.LIGHTBLUE);
        primaryStage.setScene(markAchievedScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

