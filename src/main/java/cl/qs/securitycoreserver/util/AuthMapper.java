package cl.qs.securitycoreserver.util;

import cl.qs.securitycoreserver.entity.UserAuthorization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthMapper {
    private AuthMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> buildAuthorities(List<UserAuthorization> userAuthorizations) {
        return userAuthorizations.stream()
                .map(userAuthorization -> userAuthorization.getPrivileges().getName())
                .collect(Collectors.toList());
    }

    public static Map<String, Object> buildDefaulValues(List<UserAuthorization> userAuthorizations) {
        Map<String, Object> map = new HashMap<>();
        for (UserAuthorization userAuthorization : userAuthorizations) {
            map.put(userAuthorization.getPrivileges().getName(), getValueSplit(userAuthorization.getDefaultValue()));
        }
        return map;
    }

    private static Object getValueSplit(String defaultValue) {
        String[] values = defaultValue.split("_");
        if (values.length > 1) {
            return values;
        }
        return defaultValue;
    }
}
