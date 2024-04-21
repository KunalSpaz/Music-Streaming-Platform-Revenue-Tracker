package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;

public class RevenueTracker extends Application {
    private Stage primaryStage;
    private VBox resultsVBox;
    private double totalEarnings;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Revenue Tracker");

        // Create GUI components for input page
        Label artistLabel = new Label("Artist Name:");
        TextField artistTextField = new TextField();

        Label spotifyLabel = new Label("Spotify Streaming Hours:");
        TextField spotifyTextField = new TextField();

        Label amazonLabel = new Label("Amazon Music Streaming Hours:");
        TextField amazonTextField = new TextField();

        Label youtubeLabel = new Label("YouTube Music Streaming Hours:");
        TextField youtubeTextField = new TextField();

        Button calculateButton = new Button("Calculate Earnings");

        // Layout using a VBox for input page
        VBox inputVBox = new VBox(10);
        inputVBox.getChildren().addAll(
            artistLabel, artistTextField,
            spotifyLabel, spotifyTextField,
            amazonLabel, amazonTextField,
            youtubeLabel, youtubeTextField,
            calculateButton
        );
        inputVBox.setPadding(new Insets(10));

        // Event handling for the Calculate Earnings button
        calculateButton.setOnAction(event -> {
            String artistName = artistTextField.getText();
            double spotifyHours = Double.parseDouble(spotifyTextField.getText());
            double amazonMusicHours = Double.parseDouble(amazonTextField.getText());
            double youtubeMusicHours = Double.parseDouble(youtubeTextField.getText());

            totalEarnings = 0.0;

            // Create results page and display the results
            displayResultsPage(artistName, spotifyHours, amazonMusicHours, youtubeMusicHours);
        });

        // Create and set the Scene for the input page
        Scene inputScene = new Scene(inputVBox, 600, 500); // Increased width and height
        inputScene.setFill(Color.LIGHTBLUE); // Set a background color
        primaryStage.setScene(inputScene);

        // Show the Stage
        primaryStage.show();
    }

    // Method to display the results page
    private void displayResultsPage(String artistName, double spotifyHours, double amazonMusicHours, double youtubeMusicHours) {
        // Create GUI components for results page
        Label resultsLabel = new Label("Earnings for " + artistName + ":");
        resultsLabel.setFont(Font.font("Arial", 16));

        resultsVBox = new VBox(10);
        resultsVBox.setPadding(new Insets(10));
        resultsVBox.getChildren().add(resultsLabel);

        // Display Spotify earnings
        createRevenueBox("Spotify", spotifyHours);

        // Display Amazon Music earnings
        createRevenueBox("Amazon Music", amazonMusicHours);

        // Display YouTube Music earnings
        createRevenueBox("YouTube Music", youtubeMusicHours);

        // Display the total earnings
        createTotalEarningsBox(artistName);

        // Create a ScrollPane for the results
        ScrollPane scrollPane = new ScrollPane(resultsVBox);

        // Layout using a VBox for results page with scrolling
        Scene resultsScene = new Scene(scrollPane, 600, 500); // Match the stage dimensions
        resultsScene.setFill(Color.LIGHTBLUE); // Set a background color
        primaryStage.setScene(resultsScene);
    }

    // Method to create a box for platform earnings
    private void createRevenueBox(String platformName, double streamingHours) {
        StreamingPlatform platform;

        if (platformName.equals("Spotify")) {
            platform = new Spotify("", streamingHours);
        } else if (platformName.equals("Amazon Music")) {
            platform = new AmazonMusic("", streamingHours);
        } else {
            platform = new YouTubeMusic("", streamingHours);
        }

        VBox revenueBox = new VBox(10);
        revenueBox.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");
        revenueBox.getChildren().addAll(
            new Label(platformName + " Revenue:"),
          //  new Label("Platform: " + platform.getPlatformName()), // Get platform name
            new Label("Streaming Hours: " + streamingHours),
            new Label("Earnings: Rs " + platform.calculateEarnings())
        );
        resultsVBox.getChildren().add(revenueBox);

        // Update total earnings
        totalEarnings += platform.calculateEarnings();
    }

    // Method to create a box for total earnings
    private void createTotalEarningsBox(String artistName) {
        VBox totalEarningsBox = new VBox(10);
        totalEarningsBox.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");
        totalEarningsBox.getChildren().addAll(
            new Label("Total Earnings for " + artistName + ":"),
            new Label("Total Earnings: Rs " + totalEarnings)
        );
        resultsVBox.getChildren().add(totalEarningsBox);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
