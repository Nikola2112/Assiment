import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Tournament {
    private List<Team> teams;

    public Tournament() {
        teams = new ArrayList<>();
    }

    public void addTeam(Team team) {
        if (teams.size() < 4) {
            teams.add(team);
            System.out.println("Team " + team.getName() + " has been added to the tournament.");
        } else {
            System.out.println("Cannot add more than 4 teams to the tournament.");
        }
    }

    public class Team {
        private String name;
        private List<String> participants;
        private Map<String, Integer> eventPoints;

        public Team(String name) {
            this.name = name;
            participants = new ArrayList<>();
            eventPoints = new HashMap<>();
        }

        public String getName() {
            return name;
        }

        public boolean addParticipant(String participant) {
            if (participants.size() < 5) {
                participants.add(participant);
                System.out.println("Participant " + participant + " has been added to team " + name + ".");
                return true;
            } else {
                System.out.println("Cannot add more than 5 participants to a team.");
                return false;
            }
        }

        public void addPoints(String event, int points) {
            if (points > 20) {
                throw new IllegalArgumentException("Points cannot exceed 20.");
            }
            eventPoints.put(event, points);
        }

        public int getTotalScore() {
            int totalScore = 0;
            for (int points : eventPoints.values()) {
                totalScore += points;
            }
            return totalScore;
        }
    }

    public static class Event {
        public static final String EVENT_1 = "Football event";
        public static final String EVENT_2 = "Basketball event";
        public static final String EVENT_3 = "Chess event";
        public static final String EVENT_4 = "Coding event";
        public static final String EVENT_5 = "Marathon event";
    }

    public static void main(String[] args) {
        Tournament tournament = new Tournament();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 4; i++) {
            System.out.print("Enter team name: ");
            String teamName = scanner.nextLine();
            Tournament.Team team = tournament.new Team(teamName);

            for (int j = 0; j < 5; j++) {
                System.out.print("Enter participant name for team " + teamName + ": ");
                String participantName = scanner.nextLine();
                team.addParticipant(participantName);
            }

            tournament.addTeam(team);
            System.out.println();
        }

        for (int j = 0; j < 5; j++) {
            String eventName = Event.class.getDeclaredFields()[j].getName();
            System.out.println("Enter points for " + eventName + ":");

            for (Tournament.Team team : tournament.teams) {
                boolean validPoints = false;
                while (!validPoints) {
                    System.out.print("Enter points for team " + team.getName() + " (or leave blank to skip): ");
                    String pointsInput = scanner.nextLine();
                    if (pointsInput.isEmpty()) {
                        validPoints = true;
                    } else {
                        try {
                            int points = Integer.parseInt(pointsInput);
                            if (points < 0 || points > 20) {
                                System.out.println("Invalid points entered. Points must be between 0 and 20.");
                            } else {
                                team.addPoints(eventName, points);
                                validPoints = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid points entered. Please enter a valid integer or leave blank to skip.");
                        }
                    }
                }
            }
        }

        System.out.println("\nTotal scores:");


        for (Tournament.Team team : tournament.teams) {

            System.out.println("Team " + team.getName() + ": " + team.getTotalScore() + " points");
            
        }
    }
}
