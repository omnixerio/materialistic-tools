package dev.ultreon.mods.materialistic.debug;

import dev.architectury.platform.Platform;
import dev.ultreon.mods.materialistic.Materialistic;
import org.apache.commons.lang3.StringUtils;

public class Debugger {

    public static void log(String message) {
        if (Platform.isDevelopmentEnvironment()) {
            Materialistic.LOGGER.info(message);
        }
    }

    public static void log(Object... args) {
        if (Platform.isDevelopmentEnvironment()) {
            Materialistic.LOGGER.info(StringUtils.join(args, " "));
        }
    }
}
