package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testAppInitialization() {
        // Creating an instance of App
        App app = new App();

        // Since the App class is a JavaFX Application,
        // its initialization can't be fully tested without starting the JavaFX runtime.
        // You would typically test business logic here if it were separate from JavaFX.

        // Example assertion (placeholders, since we can't test JavaFX components
        // directly)
        assertNotNull(app);
    }
}