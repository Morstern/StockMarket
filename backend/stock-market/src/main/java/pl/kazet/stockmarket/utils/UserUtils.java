package pl.kazet.stockmarket.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import pl.kazet.stockmarket.entity.tables.User;

public class UserUtils {
    private final static String ADMIN_CONSTANT = "ADMIN";

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static boolean hasCurrentLoggedUserAdminRole() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getRole().equals(ADMIN_CONSTANT) ? true : false;
    }
}
