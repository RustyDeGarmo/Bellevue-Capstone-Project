<!-- Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo -->
<!-- User interface for: New User Sign Up Page-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="ext" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!-- Sign-up page -->
<c:set var="bodyContent">

	<div class="container-fluid px-3">
		<div class="row min-vh-100">
			<div class="col-md-8 col-lg-6 col-xl-5 d-flex align-items-center">
				<div class="w-100 py-5 px-md-5 px-xxl-6 position-relative">
					<div class="mb-4">
						<a href="${initParam.appUrl}/home"><img class="img-fluid mb-3"
							src="${initParam.appUrl}/img/logo-provisio.svg" alt="signup-logo" style="width: 15em;"></a>
						<h2>Sign up</h2>
						<p class="text-muted">
							Sign up, make an account, and we'll help you keep track of the
							details! <br>By creating an account you'll be able to review
							reservations, cancel reservations, and check your Loyalty Points.
						</p>
					</div>
					<form class="form-validate" method="POST"
						action="${initParam.appUrl}/signup">
						<div class="mb-4">
							<label class="form-label" for="email"> Email Address</label> <input
								class="form-control" name="email" id="email"
								value="${user.email}" type="email"
								placeholder="name@address.com" autocomplete="off" required
								data-msg="Please enter your email">
							<c:if test='${errors.containsKey("email")}'>
								<small class="form-text text-danger"><c:out
										value='${errors.get("email")}' /></small>
							</c:if>
						</div>
						<div class="mb-4">
							<label class="form-label" for="password"> Password</label> <input
								class="form-control" name="password" id="password"
								placeholder="Password" type="password" required
								data-msg="Please enter your password">
							<c:if test='${errors.containsKey("password")}'>
								<small class="form-text text-danger"><c:out
										value='${errors.get("password")}' /></small>
							</c:if>
						</div>
						<div class="mb-4">
							<label class="form-label" for="firstName"> First Name</label> <input
								class="form-control" name="firstName" id="firstName"
								value="${user.firstName}" placeholder="John" type="text">
							<c:if test='${errors.containsKey("firstName")}'>
								<small class="form-text text-danger"><c:out
										value='${errors.get("firstName")}' /></small>
							</c:if>
						</div>
						<div class="mb-4">
							<label class="form-label" for="lastName"> Last Name</label> <input
								class="form-control" name="lastName" id="lastName"
								value="${user.lastName}" placeholder="Doe" type="text">
							<c:if test='${errors.containsKey("lastName")}'>
								<small class="form-text text-danger"><c:out
										value='${errors.get("lastName")}' /></small>
							</c:if>
						</div>
						<div class="d-grid gap-2">
							<button class="btn btn-lg btn-primary" type="submit">Sign
								up</button>
						</div>
						<ul class="list-unstyled mt-3">
							<li><a class="text-gray-00 text-sm text-decoration-none"
								href="#">* Password Requirements</a></li>
							<li><a class="text-gray-00 text-sm text-decoration-none"
								href="#"><span class="text-muted">- 8 characters in
										length</span></a></li>
							<li><a class=" text-sm text-decoration-none" href="#"><span
									class="text-muted">- Uppercase and lowercase letters</span></a></li>
							<li><a class=" text-sm text-decoration-none" href="#"><span
									class="text-muted">- At least one special character</span></a></li>
						</ul>
					</form>
					<a class="close-absolute me-md-5 me-xl-6 pt-5"
						href="javascript:history.back()"> <svg
							class="svg-icon w-3rem h-3rem">
                <use xlink:href="#close-1"> </use>
              </svg>
					</a>
				</div>
			</div>
			<div class="col-md-4 col-lg-6 col-xl-7 d-none d-md-block">
				<!-- Image-->
				<div class="bg-cover h-100 me-n3"
					style="background-image: url(img/photo/photo-1497436072909-60f360e1d4b1.jpg);"></div>
			</div>
		</div>
	</div>
	<!-- Inject SVG icon -->
	<script>
		function injectSvgSprite(path) {

			var ajax = new XMLHttpRequest();
			ajax.open("GET", path, true);
			ajax.send();
			ajax.onload = function(e) {
				var div = document.createElement("div");
				div.className = 'd-none';
				div.innerHTML = ajax.responseText;
				document.body.insertBefore(div, document.body.childNodes[0]);
			}
		}
		injectSvgSprite('icons/orion-svg-sprite.svg');
	</script>
</c:set>

<ext:base-page>

	<jsp:attribute name="title">Sign Up</jsp:attribute>

	<jsp:attribute name="scripts">

    </jsp:attribute>

	<jsp:body>
        ${bodyContent}
    </jsp:body>

</ext:base-page>