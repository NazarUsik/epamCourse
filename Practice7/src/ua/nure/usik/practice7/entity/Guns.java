package ua.nure.usik.practice7.entity;

import java.util.ArrayList;
import java.util.List;

public class Guns {
    private List<Gun> gunList;

    public List<Gun> getGunList() {
        if (gunList == null) {
            gunList = new ArrayList<>();
        }
        return gunList;
    }

    @Override
    public String toString() {
        if (gunList == null || gunList.isEmpty()) {
            return "Guns contains no instances\n";
        }
        StringBuilder result = new StringBuilder();
        for (Gun gun : gunList) {
            result.append(gun).append('\n');
        }
        return result.toString();
    }
}
