<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Shopping Cart</title>
</head>
<body>
<h1>Shopping Cart</h1>

<form action="${pageContext.request.contextPath}/cart" method="POST">
  <h3>Select items to add to the cart:</h3>
  <label><input type="checkbox" name="item" value="Laptop"> Laptop</label><br>
  <label><input type="checkbox" name="item" value="Phone"> Phone</label><br>
  <label><input type="checkbox" name="item" value="Tablet"> Tablet</label><br>

  <h3>Select items to remove from the cart:</h3>
  <select name="removeItem">
    <option value="">--Select item to remove--</option>
    <option value="Laptop">Laptop</option>
    <option value="Phone">Phone</option>
    <option value="Tablet">Tablet</option>
  </select><br><br>

  <input type="submit" name="action" value="add">
  <input type="submit" name="action" value="remove">
</form>

<h2>Cart Contents</h2>
<c:if test="${!empty cart}">
  <ul>
    <c:forEach var="item" items="${cart}">
      <li>${item}</li>
    </c:forEach>
  </ul>
</c:if>
<c:if test="${empty cart}">
  <p>Your cart is empty.</p>
</c:if>

<a href="${pageContext.request.contextPath}/">Go back to shopping</a>
</body>
</html>

