package ru.job4j.io;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Dispatch pattern for commands choosing.
 * Default values of commands and their ids
 * set in method {@link CommandsDisp#init()}
 * There are three default value at this moment:<br>
 * CONTINUE - 1<br>
 * STOP - 2<br>
 * EXIT - 3<br>
 *
 * @author Max Rybetsky (stsdewar@ya.ru)
 * @version $Id$
 * @since 0.1
 */
public class CommandsDisp {
    /**
     * Dispatch.
     */
    private final Map<Function<String, Boolean>, Integer> dispatch =
            new LinkedHashMap<>();
    /**
     * Current command id. Default value is 1 (CONTINUE)
     */
    private int currCommandID;

    /**
     * Creates new Commands Dispatcher object
     * to choose commands
     */
    public CommandsDisp() {
        init();
        currCommandID = 1;
    }

    /**
     * Initialize start commands STOP, CONTINUE and EXIT
     */
    private void init() {
        this.dispatch.put(
                "продолжить"::equals,
                1
        );
        this.dispatch.put(
                "стоп"::equals,
                2
        );
        this.dispatch.put(
                "закончить"::equals,
                3
        );
    }

    /**
     * Load message and its id to dispatch
     *
     * @param command New command
     * @param commandId New command's ID
     */
    public void load(String command, Integer commandId) {
        if (command != null) {
            dispatch.put(command::equals, commandId);
        }
    }

    /**
     * Update value of {@link CommandsDisp#currCommandID} if
     * {@code message} is a command from dispatch. Otherwise
     * returns old value of {@link CommandsDisp#currCommandID}
     *
     * @param message Message to check for command
     * @return {@link CommandsDisp#currCommandID}
     */
    public int getCommandID(String message) {
        for (Function<String, Boolean> predict : dispatch.keySet()) {
            if (predict.apply(message)) {
                currCommandID = this.dispatch.get(predict);
            }
        }
        return currCommandID;
    }
}
