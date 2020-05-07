Практическое задание №8
_______________________

Замечание.

1. Класс User должен содержать:
1) метод 'getLogin()', который возвращает логин пользователя;
2) метод 'toString()', который возвращает логин пользователя;
3) реализацию метода 'equals(Object)', согласно которой два объекта User равны тогда и только тогда, когда они имеют один логин.

2. Класс Team должен содержать:
1) метод 'getName()', который возвращает название группы;
2) метод 'toString()', который возвращает название группы;
3) реализацию метода 'equals(Object)', согласно которой два объекта Team равны тогда и только тогда, когда они имеют одно название.

3. Соединение получать с помощью вызова метода 'DriverManager.getConnection(CONNECTION_URL)'.
CONNECTION_URL - строка соединения, включающая логин и пароль пользователя.
Строку соединения читать из файла 'app.properties' по ключу 'connection.url'.
Файл расположить в корне проекта.

Пример содержимого файла app.properties:
-------------------------------------------------------
connection.url = jdbc:mysql://localhost:3306/p8db?user=user&password=userpass
-------------------------------------------------------

Замечание. Класса драйвера вручную не загружать (речь идет о вызове 'Class.forName(JDBC-DRIVER-FQN)', подобного рода строки исключить из кода).

4. Метод 'setTeamsForUser(User, Team...)' реализовать с помощью транзакции: вследствие вызова данного метода пользователю будут назначены либо все группы, либо ни одной.
Если метод будет вызван так: setTeamsForUser(user, teamA, teamB, teamC),
то в таблицу связей 'users_teams' записи должны быть вставлены последовательно в порядке появления групп в списке аргументов слева направа:

user_id, teamA_id
user_id, teamB_id 
user_id, teamC_id

Если последняя запись не может быть добавлена, то первые две также не попадают в базу данных (rollback transaction).

При тестировании Jenkins будет использовать Apache Derby RDBMS.
Скрипт создания тестовой базы (для Derby): 
-------------------------------------------------------
CREATE TABLE users (
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	login VARCHAR(10) UNIQUE
);
CREATE TABLE teams (
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	name VARCHAR(10)
);
CREATE TABLE users_teams (
	user_id INT REFERENCES users(id) ON DELETE CASCADE,
	team_id INT REFERENCES teams(id) ON DELETE CASCADE,
	UNIQUE (user_id, team_id)
);
-------------------------------------------------------

5. Уточнение, метод DBManager#getUserTeams должен возвращать объект List<Team>.
______________________

База данных.

В качестве базы данных использовать любую реляционную БД.

БД содержит три таблицы:
users (id, login)
teams (id, name)
users_teams (user_id, team_id)

Изначально таблицы БД должны иметь некоторое наполнение (см. код класса Demo)

В корне создать каталог sql и сохранить в нем скрипт создания базы данных db-create.sql
_______________________


Класс Demo (скопировать в свой проект)
-------------------------------------------------------
package ua.nure.your_last_name.practice8;

import java.util.List;

import ua.nure.your_last_name.practice8.db.DBManager;
import ua.nure.your_last_name.practice8.db.entity.Team;
import ua.nure.your_last_name.practice8.db.entity.User;

public class Demo {

    private static void printList(List<?> list) {
        System.out.println(list);
    }

    public static void main(String[] args) {
        // users  ==> [ivanov]
        // teams ==> [teamA]

        DBManager dbManager = DBManager.getInstance();

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

        Team teamA = dbManager.getTeam("teamA");
        Team teamB = dbManager.getTeam("teamB");
        Team teamC = dbManager.getTeam("teamC");

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

    }
}
-------------------------------------------------------

Во всех заданиях создать и реализовать соответствующие типы таким образом,
чтобы при запуске класса Demo отрабатывала соответствующая функциональность.

Задание 1
_______________________

Метод DBManager#insertUser должен модифицировать поле id объекта User.
Метод DBManager#findAllUsers возвращает объект java.util.List<User>.
_______________________


Задание 2
_______________________

Метод DBManager#insertTeam должен модифицировать поле id объекта Team.
Метод DBManager#findAllTeams возвращает объект java.util.List<Team>.
_______________________


Задание 3
_______________________

Метод DBManager#setTeamsForUser должен реализовывать транзакцию. Грамотно реализовать логику commit/rollback транзакции.
Метод DBManager#getUserTeams возвращает объект java.util.List<Team>.
_______________________


Задание 4
_______________________

Метод DBManager#deleteTeam удаляет группу по имени.
Все дочерние записи из таблицы users_teams также должны быть удалены.
Последнее реализовать с помощью каскадных ограничений ссылочной целостности:
ON DELETE CASCADE.
_______________________


Задание 5
_______________________

Метод DBManager#updateTeam обновляет группу.
_______________________