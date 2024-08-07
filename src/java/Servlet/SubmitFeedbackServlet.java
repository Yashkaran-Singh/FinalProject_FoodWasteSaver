/**
    Author  : Yashkaran Singh
*/
package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import feedback.FeedbackDAO;

/**
 * Servlet implementation class SubmitFeedbackServlet
 * This servlet handles the submission of feedback.
 */
@WebServlet("/SubmitFeedbackServlet")
public class SubmitFeedbackServlet extends HttpServlet {
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve feedback from the form
        String description = request.getParameter("feedback");
        
        // Add feedback to the database
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        feedbackDAO.addFeedback(description);
        
        // Redirect to a confirmation page
        response.sendRedirect("feedbackConfirmation.jsp");
    }
}
