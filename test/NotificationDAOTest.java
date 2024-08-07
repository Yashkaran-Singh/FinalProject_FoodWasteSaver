package notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.sql.DataSource;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class tests the NotificationDAO class.
 * It checks the CRUD operations for notifications.
 * @author Ziya
 */


public class NotificationDAOTest {

    private NotificationDAO notificationDAO;
    private Notification testNotification;
    private DataSource dataSource;

    /**
     * This method sets up the test environment.
     * It runs before each test.
     */
    @BeforeEach
    public void setUp() throws SQLException {
        dataSource = mock(DataSource.class);
        notificationDAO = new NotificationDAOImpl(dataSource);
        testNotification = new Notification(1, 1, 1, "Test notification message");
    }

    /**
     * This test checks if a notification can be created successfully.
     */
    @Test
    public void testCreateNotification() throws SQLException {
        notificationDAO.create(testNotification);
        Notification createdNotification = notificationDAO.read(testNotification.getNotificationId());
        assertEquals(testNotification.getNotificationMessage(), createdNotification.getNotificationMessage(),
         "The notification should be created successfully");
    }

    /**
     * This test checks if a notification can be retrieved successfully.
     */
    @Test
    public void testReadNotification() throws SQLException {
        notificationDAO.create(testNotification);
        Notification retrievedNotification = notificationDAO.read(testNotification.getNotificationId());
        assertNotNull(retrievedNotification, 
        "The notification should be retrieved successfully");
    }

    /**
     * This test checks if a notification can be updated successfully.
     */
    @Test
    public void testUpdateNotification() throws SQLException {
        notificationDAO.create(testNotification);
        testNotification.setNotificationMessage("Updated test notification message");
        notificationDAO.update(testNotification);
        Notification updatedNotification = notificationDAO.read(testNotification.getNotificationId());
        assertEquals("Updated test notification message", 
        updatedNotification.getNotificationMessage(), 
        "The notification message should be updated successfully");
    }

    /**
     * This test checks if a notification can be deleted successfully.
     */
    @Test
    public void testDeleteNotification() throws SQLException {
        notificationDAO.create(testNotification);
        notificationDAO.delete(testNotification.getNotificationId());
        Notification deletedNotification = notificationDAO.read(testNotification.getNotificationId());
        assertNull(deletedNotification, "The notification should be deleted successfully");
    }
}


    /**
     * 
     */