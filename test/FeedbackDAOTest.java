

import feedback.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * This class tests the FeedbackDAO class.
 * It checks if the addFeedback method works correctly.
 * @author Ziya
 */
public class FeedbackDAOTest {

    private FeedbackDAO feedbackDAO;

    /**
     * This method sets up the test environment.
     * It runs before each test.
     */
    @BeforeEach
    public void setUp() {
        feedbackDAO = new FeedbackDAO();
    }

    /**
     * This test checks if adding feedback works.
     * It should return true if the feedback is added successfully.
     */
    @Test
    public void testAddFeedbackSuccess() {
        String description = "This is a test feedback";
        boolean result = feedbackDAO.addFeedback(description);
        assertTrue(result, "Feedback should be added successfully");
    }

    /**
     * This test checks if adding feedback with an empty description fails.
     * It should return false if the feedback description is empty.
     */
    @Test
    public void testAddFeedbackFail() {
        String description = "";
        boolean result = feedbackDAO.addFeedback(description);
        assertFalse(result, "Feedback should not be added with empty description");
    }
}


    /**
     *  This test checks 
     */
    //@Test