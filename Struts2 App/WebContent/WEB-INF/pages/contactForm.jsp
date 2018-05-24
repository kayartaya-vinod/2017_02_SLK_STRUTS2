<%@ include file="header.txt"%>

<h1>Contact form</h1>

<%@ include file="dashboardHeader.txt" %>

<s:form class="form-horizontal" theme="simple" action="save-contact">
	<s:hidden name="contact.id" />
	<div class="form-group">
		<label class="col-md-3">Name</label>
		<div class="col-md-5">
			<s:textfield name="contact.name" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-3">City</label>
		<div class="col-md-5">
			<s:textfield name="contact.city" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-3">Email id</label>
		<div class="col-md-5">
			<s:textfield name="contact.email" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-3">Phone number</label>
		<div class="col-md-5">
			<s:textfield name="contact.phone" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-md-offset-3 col-md-5">
			<s:submit class="btn btn-primary" value="Save" />
		</div>
	</div>
</s:form>
<%@ include file="footer.txt"%>