/**
 * Handles custom exceptions
 */

package duke.exception;

// Handles exceptions and prints them
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
