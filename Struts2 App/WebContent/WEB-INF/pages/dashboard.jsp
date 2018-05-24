<%@ include file="header.txt"%>
<h1>Hi ${sessionScope.user.name }, Welcome back!</h1>

<%@ include file="dashboardHeader.txt" %>

<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th><a href="sort?key=name">Name</a></th>
			<th><a href="sort?key=city">City</a></th>
			<th><a href="sort?key=email">Email id</a></th>
			<th><a href="sort?key=phone">Phone number</a></th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${requestScope.contactList}" var="c">
			<tr>
				<td>
					<a href="edit-contact?id=${c.id}"><span class="glyphicon glyphicon-pencil"></span></a>
					<a href="delete-contact?id=${c.id}"><span class="glyphicon glyphicon-trash"></span></a>
					${c.name}
				</td>
				<td>${c.city}</td>
				<td>${c.email}</td>
				<td>${c.phone}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="footer.txt"%>