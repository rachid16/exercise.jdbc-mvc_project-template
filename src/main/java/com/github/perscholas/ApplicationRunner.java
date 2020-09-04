package com.github.perscholas;

import com.github.perscholas.controllers.ControllerInterface;
import com.github.perscholas.controllers.PersonController;
import com.github.perscholas.dao.PersonRepository;
import com.github.perscholas.routers.ControllerRouter;
import com.github.perscholas.service.PersonService;
import com.github.perscholas.utils.IOConsole;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Created by leon on 8/13/2020.
 */
public class ApplicationRunner implements Runnable {
    private IOConsole console = new IOConsole(IOConsole.AnsiColor.GREEN);

    @Override
    public void run() {
        Object personControllerObject = new PersonController(new PersonService(new PersonRepository()));
        ControllerInterface personController = (ControllerInterface)personControllerObject;

        String userInput;
        do {
            userInput = console.getStringInput(new StringJoiner("\n")
                    .add("\n\nWelcome to the application request router.")
                    .add("From here, you can select any of the following options:")
                    .add(Arrays
                            .toString(ControllerRouter.values())
                            .replaceAll("_", "-")
                            .toLowerCase())
                    .toString());
            try {
                ControllerRouter.getValueOf(userInput).perform(personController);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!"quit".equalsIgnoreCase(userInput));
    }
}
