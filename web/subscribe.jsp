<%-- 
    Class   : subscribe
    Author  : Yashkaran Singh and Ziya Gurel
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Subscribe for Surplus Food Alerts</title>
    <link rel="stylesheet" href="styles.css"> 
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
            <h2>Subscribe for Surplus Food Alerts</h2>
            <form action="SubscribeServlet" method="post">
                <label for="location">Location:</label>
                <input type="text" id="location" name="location" required><br><br>

                <label for="communicationMethod">Communication Method:</label>
                <select id="communicationMethod" name="communicationMethod" required>
                    <option value="email">Email</option>
                    <option value="phone">Phone</option>
                </select><br><br>

                <label for="foodPreferences">Food Preferences:</label><br>
                <textarea id="foodPreferences" name="foodPreferences" required></textarea><br><br>

                <input type="submit" value="Subscribe">
            </form>
        </div>
    </main>
    
    <footer>
        <p>&copy; 2024 Surplus Food Waste Saver. All rights reserved.</p>
    </footer>
</body>
</html>

