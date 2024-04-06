<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Product</title>
        <link rel="stylesheet" href="./css/CreateAccountPage.css">
        <!-- You can include additional CSS files for specific styling if needed -->
    </head>
    <body>
        <div class="container">
            <h2>Edit Product</h2>
            <form>
                <label for="productName">Product Name:</label>
                <input type="text" id="productName" name="productName" required>

                <label for="description">Description:</label>
                <textarea id="description" name="description" rows="4" required></textarea>

                <label for="category">Category:</label>
                <input type="text" id="category" name="category" required>

                <label for="price">Price:</label>
                <input type="number" id="price" name="price" min="0" step="0.01" required>

                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" min="0" required>

                <button type="submit">Update Product</button>
                <!-- New input for Image URL -->
                <label for="imageUrl">Image URL:</label>
                <input type="url" id="imageUrl" name="imageUrl" placeholder="Enter image URL">

                <!-- New button for updating Image -->
                <button type="button" onclick="updateImage()">Update Image</button>
            </form>
        </div>

    </body>
</html>