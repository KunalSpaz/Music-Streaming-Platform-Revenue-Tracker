package application;

import java.util.Scanner;

//interface
interface EarningsCalculable {
  double calculateEarnings();
}

//abstract class implmenting interface
abstract class StreamingPlatform implements EarningsCalculable {
  protected String platformName;
  protected String artistName;
  protected double streamingHours;

  public StreamingPlatform(String platformName, String artistName, double streamingHours) {
      this.platformName = platformName;
      this.artistName = artistName;
      this.streamingHours = streamingHours;
  }

  @Override
  public abstract double calculateEarnings();


  @Override
  public String toString() {
      return "Platform: " + platformName + "\nArtist: " + artistName + "\nStreaming Hours: " + streamingHours
              + "\nEarnings: $" + calculateEarnings();
  }
}

//Inheriting StreamingPlatform
class Spotify extends StreamingPlatform {
  public Spotify(String artistName, double streamingHours) {
      super("Spotify", artistName, streamingHours);
  }

  @Override
  public double calculateEarnings() {
      double earnings = 0.0;

      if (streamingHours >= 200000) {
          earnings = streamingHours * 0.74 + 50000;
      } else if (streamingHours >= 50000) {
          earnings = streamingHours * 0.54 + 40000;
      } else {
          earnings = streamingHours * 0.41 + 5000;
      }

      return earnings;
  }
}
//Inheriting StreamingPlatform
class AmazonMusic extends StreamingPlatform {
  public AmazonMusic(String artistName, double streamingHours) {
      super("Amazon Music", artistName, streamingHours);
  }

  @Override
  public double calculateEarnings() {
      double earnings = 0.0;

      if (streamingHours >= 200000) {
          earnings = streamingHours * 0.68 + 50000;
      } else if (streamingHours >= 50000) {
          earnings = streamingHours * 0.57 + 40000;
      } else {
          earnings = streamingHours * 0.44 + 5000;
      }

      return earnings;
  }
}
//Inheriting StreamingPlatform
class YouTubeMusic extends StreamingPlatform {
  public YouTubeMusic(String artistName, double streamingHours) {
      super("YouTube Music", artistName, streamingHours);
  }

  @Override
  public double calculateEarnings() {
      double earnings = 0.0;

      if (streamingHours >= 200000) {
          earnings = streamingHours * 0.70 + 50000;
      } else if (streamingHours >= 50000) {
          earnings = streamingHours * 0.64 + 40000;
      } else {
          earnings = streamingHours * 0.60 + 5000;
      }

      return earnings;
  }
}

//Main class
public class RevenueTrackerClass {
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);

      System.out.println("Input Artist Information");
      System.out.print("Enter Artist Name: ");
      String artistName = sc.next();
      System.out.print("Enter Streaming Hours for Spotify: ");
      double spotifyHours = sc.nextDouble();
      System.out.print("Enter Streaming Hours for Amazon Music: ");
      double amazonMusicHours = sc.nextDouble();
      System.out.print("Enter Streaming Hours for YouTube Music: ");
      double youtubeMusicHours = sc.nextDouble();

      Spotify spotify = new Spotify(artistName, spotifyHours);
      AmazonMusic amazonMusic = new AmazonMusic(artistName, amazonMusicHours);
      YouTubeMusic youtubeMusic = new YouTubeMusic(artistName, youtubeMusicHours);

      System.out.println("\nCalculating and displaying earnings for each platform:");
      System.out.println(spotify);
      System.out.println(amazonMusic);
      System.out.println(youtubeMusic);

      // Calculate the total earnings
      double totalEarnings = spotify.calculateEarnings() + amazonMusic.calculateEarnings() + youtubeMusic.calculateEarnings();

      System.out.println("\nTotal Earnings: Rs" + totalEarnings);

      
  }
}

