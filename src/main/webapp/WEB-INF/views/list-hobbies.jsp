<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@include file="includes/header.jsp" %>
<body>
	<div class="container">
		<div class="col-md-offset-1 col-md-10">
			<h3 class="text-center">Spring MVC 5 + Spring Data JPA 2 + JSP +
				MySQL Example - Province Management</h3>
			<hr />

			<input type="button" value="Add Hobby"
				onclick="window.location.href='showForm'; return false;"
				class="btn btn-primary" /> <br /> <br />
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Hobby List</div>
				</div>
				<div class="panel-body">
					<table class="table table-striped table-bordered">
						<tr>
							<th>Name</th>
							<th>Customer</th>
							<th>Action</th>
						</tr>

						<!-- loop over and print our Provinces -->
						<c:forEach var="tempHobby" items="${hobbies}">

							<!-- construct an "update" link with Province id -->
							<c:url var="updateLink" value="/hobby/updateForm">
								<c:param name="hobbyId" value="${tempHobby.id}" />
							</c:url>

							<!-- construct an "delete" link with Province id -->
							<c:url var="deleteLink" value="/hobby/delete">
								<c:param name="hobbyId" value="${tempHobby.id}" />
							</c:url>

							<tr>
								<td>${tempHobby.name}</td>
								<td><c:forEach var="customer"
										items="${tempHobby.customers}">
										<span class="btn-sm">${customer.lastName}
											${customer.firstName}</span>
									</c:forEach></td>
								<td>
									<!-- display the update link --> <a href="${updateLink}">Update</a>
									| <a href="${deleteLink}"
									onclick="if (!(confirm('Are you sure you want to delete this Province?'))) return false">Delete</a>
								</td>

							</tr>

						</c:forEach>

					</table>

				</div>
			</div>
		</div>

	</div>
	<div class="footer">
		<p>Footer</p>
	</div>
</body>

</html>









