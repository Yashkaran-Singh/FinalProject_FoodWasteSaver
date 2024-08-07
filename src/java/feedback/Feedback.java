/**
    Author  : Yashkaran Singh
*/
package feedback;

/**
 * Class representing feedback provided by users.
 */
public class Feedback {
    private int feedbackId;
    private int userId;
    private String description;

    /**
     * Constructs a Feedback object with specified parameters.
     * @param feedbackId The ID of the feedback.
     * @param userId The ID of the user providing the feedback.
     * @param description The description of the feedback.
     */
    public Feedback(int feedbackId, int userId, String description) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.description = description;
    }

    /**
     * Gets the ID of the feedback.
     * @return The feedback ID.
     */
    public int getFeedbackId() {
        return feedbackId;
    }

    /**
     * Sets the ID of the feedback.
     * @param feedbackId The feedback ID to set.
     */
    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    /**
     * Gets the ID of the user providing the feedback.
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user providing the feedback.
     * @param userId The user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the description of the feedback.
     * @return The feedback description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the feedback.
     * @param description The feedback description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
