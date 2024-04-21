package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Goal {
    private String goalDescription;
    private String deadline;
    private boolean achieved;

    public Goal(String goalDescription, String deadline) {
        this.goalDescription = goalDescription;
        this.deadline = deadline;
        this.achieved = false;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void markAsAchieved() {
        achieved = true;
    }

    @Override
    public String toString() {
        return "Goal: " + goalDescription + ", Deadline: " + deadline + ", Achieved: " + (achieved ? "Yes" : "No");
    }
}

class Artist {
    private String name;
    private List<Goal> goals;

    public Artist(String name) {
        this.name = name;
        goals = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addGoal(String goalDescription, String deadline) {
        Goal goal = new Goal(goalDescription, deadline);
        goals.add(goal);
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void markGoalAsAchieved(int goalIndex) {
        if (goalIndex >= 0 && goalIndex < goals.size()) {
            goals.get(goalIndex).markAsAchieved();
        }
    }
}



