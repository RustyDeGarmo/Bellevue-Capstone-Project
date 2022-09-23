<!-- Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo -->
<!-- User interface for: Log In page-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="ext" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!-- Login page -->
<c:set var="bodyContent">
	<div class="container-fluid px-3">
		<div class="row min-vh-100">
			<div class="col-md-8 col-lg-6 col-xl-5 d-flex align-items-center">
				<div class="w-100 py-5 px-md-5 px-xxl-6 position-relative">
					<div class="mb-5">
						<a href="${initParam.appUrl}/home"><img class="img-fluid mb-3"
							src="${initParam.appUrl}/img/logo-provisio.svg" alt="login-logo" style="width: 15em;"></a>
						<h2>Welcome back</h2>
						<p class="text-success">
							<c:out value="${signupSuccess}" />
						</p>
						<p class="text-muted">
							<c:out value="${LOGIN_MESSAGE}" />
						</p>
					</div>
					<form class="form-validate" method="POST"
						action="${initParam.appUrl}/login">
						<div class="mb-4">
							<label class="form-label" for="email"> Email Address</label> <input
								class="form-control" name="email" id="email" type="email"
								placeholder="name@address.com" autocomplete="off" required
								data-msg="Please enter your email">
						</div>
						<div class="mb-4">
							<div class="row">
								<div class="col">
									<label class="form-label" for="password"> Password</label>
								</div>

							</div>
							<input class="form-control" name="password" id="password"
								placeholder="Password" type="password" required
								data-msg="Please enter your password"> <small
								class="form-text text-danger"><c:out value='${error}' /></small>
						</div>
						<div class="mb-4"></div>
						<!-- Submit-->
						<div class="d-grid">
							<button class="btn btn-lg btn-primary">Sign in</button>
						</div>
						<p class="text-center mt-3">
							<small class="text-muted text-center">Don't have an
								account yet? <a href="${initParam.appUrl}/signup">Sign Up</a>
							</small>
						</p>
					</form>
					<a class="close-absolute me-md-5 me-xl-6 pt-5" href="javascript:history.back()">
						<svg class="svg-icon w-3rem h-3rem">
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
	<jsp:attribute name="title">Login</jsp:attribute>
	<jsp:attribute name="scripts">
    </jsp:attribute>
	<jsp:body>
        ${bodyContent}
    </jsp:body>
</ext:base-page>