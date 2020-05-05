package ua.nure.usik.SummaryTask4.config;

import ua.nure.usik.SummaryTask4.db.entity.enums.Role;

import java.util.*;

public class SecurityConfig {


    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }

    private static void init() {

        // Конфигурация для роли "CLIENT".
        List<String> urlPatterns1 = new ArrayList<String>();

        urlPatterns1.add("/userInfo");
        urlPatterns1.add("/buyTicket");

        mapConfig.put(String.valueOf(Role.getRole(2)), urlPatterns1);

        // Конфигурация для роли "ADMIN".
        List<String> urlPatterns2 = new ArrayList<String>();

        urlPatterns2.add("/userInfo");
        urlPatterns2.add("/adminPage");
        urlPatterns2.add("/buyTicket");

        mapConfig.put(String.valueOf(Role.getRole(1)), urlPatterns2);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}
