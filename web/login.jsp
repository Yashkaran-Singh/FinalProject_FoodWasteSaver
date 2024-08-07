<%-- 
    Class   : login
    Author  : Yashkaran Singh  and Ziya Gurel
--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
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
        <div class="form-container">
            <form action="LoginServlet" method="post" class="login-form">
                <h2>Login</h2>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <button type="submit" class="submit-btn">Login</button>
            </form>
        </div>
    </main>
    <footer>
        <p>&copy; 2024 Food Waste Saver. All rights reserved.</p>
    </footer>
</body>
</html>
