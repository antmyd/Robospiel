package challenges;

import java.io.Serializable;

/**
 * Represents a generic challenge that can be attempted by submitting input.
 * This interface defines methods for trying to solve the challenge,
 * checking whether it is solved, and managing the number of attempts.
 *
 * Implementations of this interface should keep track of whether the challenge
 * has been solved, how many attempts remain, and provide descriptions and hints.
 */
public interface Challenge extends Serializable {

    /**
     * Attempts to solve the challenge by submitting the provided input.
     * The input can be code or any form of solution depending on the challenge implementation.
     *
     * This method should update the internal state regarding attempts and solved status.
     *
     * @param input the code or solution input submitted to solve the challenge
     * @return {@code true} if the submitted input successfully solves the challenge,
     *         {@code false} otherwise
     */
    boolean tryCode(String input);

    /**
     * Checks whether the challenge has already been solved successfully.
     *
     * @return {@code true} if the challenge is solved, {@code false} otherwise
     */
    boolean isSolved();

    /**
     * Returns the number of remaining attempts to solve the challenge.
     * This count usually decreases with each failed attempt.
     *
     * @return the number of attempts left
     */
    int getAttemptsLeft();

    /**
     * Provides a textual description of the challenge.
     * This can include the problem statement or instructions.
     *
     * @return a string describing the challenge
     */
    String getDescription();

    /**
     * Provides a hint or tip to help solve the challenge.
     * This may be empty or null if no hints are available.
     *
     * @return a hint string for the challenge
     */
    String getHint();
}
