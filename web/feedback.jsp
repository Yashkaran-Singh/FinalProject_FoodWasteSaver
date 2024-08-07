<%-- 
    Class   : feedback
    Author  : Yashkaran Singh and Ziya Gurel
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Portal</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
     <header>
        <h1>Food Waste Reduction Platform</h1>
        <nav>
            <a href="index.jsp">Home</a>
            <a href="login.jsp">Login</a>
            <a href="register.jsp">Register</a>
             <a href="LogoutServlet">Logout</a>
            <a href="feedback.jsp">Feedback</a>
            <a href="subscribe.jsp">Subscribe</a>
        </nav>
    </header>
    
    <main>
   
            <div class="content-container">
                <h2>We value your feedback</h2>
                 <form action="SubmitFeedbackServlet" method="post">
                
                    <label for="description">Feedback:</label><br>
                    <textarea id="description" name="description" required></textarea><br><br>
                    
                    <input type="submit" value="Submit Feedback">
                </form>
            </div>
       
    </main>
    
    <footer>
      
            <p>&copy; 2024 Surplus Food Waste Saver. All rights reserved.</p>

    </footer>
       
</body>
</html>

