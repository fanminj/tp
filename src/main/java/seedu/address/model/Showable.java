package seedu.address.model;

/**
 * Classes that implement this interface will be shown on the UI.
 * @param <T> Any class whose instances are shown on the UI.
 */
public interface Showable<T> {
    boolean isSame(T other);

    String getType();
}
