<%@page import="java.io.PrintWriter"%>
<html>
<body>

	<h2>
		<%
		String name = request.getParameter("name");
		out.print(name);
		%>
		</h2>
</body>
</html>
