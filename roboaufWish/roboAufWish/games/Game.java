package games;

/**
 * Represents a generic game that can be played in multiple rounds.
 *
 * <p>This interface defines the core functionality for controlling the flow of a game,
 * including advancing rounds, querying the current state of the game, and checking
 * for game outcomes such as winning, losing, or tying.</p>
 *
 * <p>Implementations of this interface should encapsulate the specific game logic
 * while providing these standard methods to interact with the game progress and results.</p>
 */
public interface Game {

    /**
     * Advances the game by one round. This method executes the game logic for the
     * next round, updating the game's state accordingly.
     *
     * <p>Typically, this involves processing player actions, resolving game events,
     * and preparing for the next stage of the game.</p>
     */
    void playNextRound();

    /**
     * Returns the current round number of the game, starting from 1 for the first round.
     *
     * @return the current round number, indicating how many rounds have been played so far
     */
    int getCurrentRound();

    /**
     * Determines whether the game has concluded.
     *
     * @return {@code true} if the game is finished and no further rounds can be played,
     * {@code false} otherwise
     */
    boolean isFinished();

    /**
     * Determines if the game has been won by the player or the winning party.
     *
     * @return {@code true} if the game outcome is a win, {@code false} otherwise
     */
    boolean isWon();

    /**
     * Determines if the game has been lost by the player or the losing party.
     *
     * @return {@code true} if the game outcome is a loss, {@code false} otherwise
     */
    boolean isLost();

    /**
     * Determines if the game ended in a tie or draw.
     *
     * <p>This is applicable only to games where a tie is a possible outcome.</p>
     *
     * @return {@code true} if the game ended in a tie, {@code false} otherwise
     */
    boolean isTie();
}
