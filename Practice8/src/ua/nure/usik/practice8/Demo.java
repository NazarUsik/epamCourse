package ua.nure.usik.practice8;

import java.sql.SQLException;
import java.util.List;

import ua.nure.usik.practice8.db.DBManager;
import ua.nure.usik.practice8.db.entity.Team;
import ua.nure.usik.practice8.db.entity.User;

public class Demo {

    private static void printList(List<?> list) {
        System.out.println(list);
    }

    public static void main(String[] args) throws SQLException {
        // users  ==> [ivanov]
        // teams  ==> [teamA]

        DBManager dbManager = DBManager.getInstance();

        System.out.print("users  ==> ");
        printList(dbManager.findAllUsers());
        System.out.print("teams  ==> ");
        printList(dbManager.findAllTeams());
        System.out.println("===========================");

        // Part 1
        dbManager.insertUser(User.createUser("petrov"));
        dbManager.insertUser(User.createUser("obama"));
        printList(dbManager.findAllUsers());
        // users  ==> [ivanov, petrov, obama]

        System.out.println("===========================");

        // Part 2
        dbManager.insertTeam(Team.createTeam("teamB"));
        dbManager.insertTeam(Team.createTeam("teamC"));

        printList(dbManager.findAllTeams());
        // teams ==> [teamA, teamB, teamC]

        System.out.println("===========================");

        // Part 3
        User userPetrov = dbManager.getUser("petrov");
        User userIvanov = dbManager.getUser("ivanov");
        User userObama = dbManager.getUser("obama");

        System.out.println(userIvanov + "  " + userObama + "  " + userPetrov);

        Team teamA = dbManager.getTeam("teamA");
        Team teamB = dbManager.getTeam("teamB");
        Team teamC = dbManager.getTeam("teamC");

        System.out.println(teamA + "  " + teamB + "  " + teamC);

        // method setTeamsForUser must implement transaction!
        dbManager.setTeamsForUser(userIvanov, teamA);
        dbManager.setTeamsForUser(userPetrov, teamA, teamB);
        dbManager.setTeamsForUser(userObama, teamA, teamB, teamC);

        for (User user : dbManager.findAllUsers()) {
            printList(dbManager.getUserTeams(user));
            System.out.println("~~~~~");
        }
        // teamA
        // teamA teamB
        // teamA teamB teamC


        System.out.println("===========================");

        // Part 4

        // on delete cascade!
        dbManager.deleteTeam(teamA);

        // Part 5
        teamC.setName("teamX");
        dbManager.updateTeam(teamC);
        printList(dbManager.findAllTeams());
        // teams ==> [teamB, teamX]

//        User[] users = new User[]{User.createUser("user0"), User.createUser("user1"), User.createUser("user2"), User.createUser("user3"), User.createUser("user4")};
//        Team[] teams = new Team[]{Team.createTeam("team0"), Team.createTeam("team1"), Team.createTeam("team2"), Team.createTeam("team3"), Team.createTeam("team4")};
//
//        for (int i = 0; i < users.length; i++) {
//            dbManager.insertUser(users[i]);
//            dbManager.insertTeam(teams[i]);
//        }
//
//        dbManager.setTeamsForUser(users[0], teams[0]);
//        dbManager.setTeamsForUser(users[1], teams[0], teams[1]);
//        dbManager.setTeamsForUser(users[2], teams[0], teams[1], teams[2]);
//        dbManager.setTeamsForUser(users[3], teams[0], teams[1], teams[2], teams[3]);
//        dbManager.setTeamsForUser(users[4], teams[0], teams[1], teams[2], teams[3], teams[4]);
//
//        dbManager.deleteTeam(teams[0]);
//        dbManager.deleteTeam(teams[1]);
//        dbManager.deleteTeam(teams[3]);
//
//        System.out.println(dbManager.getUserTeams(users[4]));
    }
}
