<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dutluk</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="footer.jsp" />
	<%@ page import="java.sql.*, Dutluk.DatabaseService" %>
	<%ResultSet rs =null;%>
	<div class="container">
		<div class="row">
		
			<div class="col-xs-6">
				<div style="padding: 10px">
					<h2>New Story</h2>
					<form id="editForm" method="post" action="AddStory"
						class="editForm form-horizontal">
						<div class="form-group">
							Story: <input class="form-control" name="editStory" type="text" style="height: 200px;"/>
							When did it happen?: <input class="form-control" id="editBirthdate" name="editStime" type=text 
							 placeholder="dd/mm/yyyy"></input>
							
							<%
							DatabaseService db = new DatabaseService();
							try{
								Connection connection = db.getConnection();
						        Statement statement = connection.createStatement() ;
						        rs =statement.executeQuery("SELECT * FROM Themes ORDER BY  Themes.ThemeID ASC") ;
							%>
							
					        Theme:
					        <select name = "theme">
					        <%  while(rs.next()){ %>
					            <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
					        <%
					        	} 
					        %>
					        </select>
					        <%
							
					        }
					        catch(Exception e)
					        {
					             out.println(e);
					        }
							%>		
						</div>
						<div class="form-group">
							<button id="editProfileButton" type="submit" class="btn btn-default">Add Story</button>
						</div>
						<input type="hidden" name="func" value="edit" />
					</form>
				</div>
			</div>
			
			
		</div>
	</div>

</body>
</html>