<%@ include file="header.txt"%>


<%@ taglib uri="/struts-tags" prefix="s" %>
<s:form class="form-horizontal" theme="simple" action="/login">
	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div class="col-sm-5">
			<h1>Please login</h1>
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-2 control-label">Email address</label>
		<div class="col-sm-5">
			<s:textfield cssClass="form-control" name="user.email"/>
		</div>
		<div class="col-sm-5">
			<s:fielderror fieldName="email" />
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-2 control-label">Password</label>
		<div class="col-sm-5">
			<s:password cssClass="form-control" name="user.password"/>
		</div>
		<div class="col-sm-5">
			<s:fielderror fieldName="password" />
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div class="col-sm-5">
			<s:submit cssClass="btn btn-primary" value="Login" />
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div class="col-sm-5"><p class="lead">${actionMessage}</p></div>
	</div>
</s:form>

<%@ include file="footer.txt"%>