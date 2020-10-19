package ru.job4j.exam;

import java.util.*;
import java.util.stream.Collectors;

public class Analyze {
    /**
     *  Count number of modifications with list {@code previous} via {@code Map} objects.
     * {@code current} list converts to {@code Map} object {@code currMap} via {@code getMapFromList} with {@code User}
     * id as a key and {@code User} object as a value.<br><br>
     *
     *  Then iterate over {@code previous} list via foreach loop. In the loop get the value of the current {@code user}'s
     * id for {@code currMap} to {@code curr} variable.
     * Not null {@code curr} means this value wasn't deleted. Comparing {@code curr} and {@code user} in this case
     * gives answer of changing this element of list. If it was, increase {@code changed} variable. Remove this value
     * from {@code currMap} after this check for counting number of added values to list.<br><br>
     *
     *  Null {@code curr} means deleting this value from list.
     * @param previous list before modifications
     * @param current list after modifications
     * @return a new {@code Info} object with information about list changes (number of added, changed and deleted elements)
     */
    public Info diff(List<User> previous, List<User> current) {
        Map<Integer, User> currMap = getMapFromList(current);
        int deleted = 0;
        int changed = 0;
        for (User user : previous) {
            int userId = user.getId();
            User curr = currMap.get(userId);
            if (curr != null) {
                if(!curr.equals(user)) {
                    ++changed;
                }
                currMap.remove(userId);
            } else {
                ++deleted;
            }
        }
        return new Info(currMap.size(), changed, deleted);
    }

    /**
     * Convert {@code List<User>} object to {@code Map<Integer, User>} object
     * via stream methods.
     * @param list of {@code User} objects. If there are duplicates the last value
     *             of objects with same id is chosen.
     * @return map object with {@code User}'s id as a key and {@code User} object as a value
     *              without duplicates
     */
    private Map<Integer, User> getMapFromList(List<User> list) {
        return list.stream()
                .collect(Collectors.toMap(
                        User::getId,
                        (x) -> x,
                        (x, y) -> y,
                        HashMap::new
                ));
    }
}
