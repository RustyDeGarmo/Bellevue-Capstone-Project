<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>	
<!-- Header -->
<nav
	class="navbar navbar-expand-lg fixed-top shadow navbar-light bg-white">
	<div class="container-fluid">
		<div class="d-flex align-items-center">
			<a class="navbar-brand py-1" href="${initParam.appUrl}/home">
				<img src="http://localhost:8080/ProvisioHotels/img/logo-provisio.svg" alt="Provisio logo" style="width: 60%;">
			</a>
		</div>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
			aria-controls="navbarCollapse" aria-expanded="false"
			aria-label="Toggle navigation">
			<i class="fa fa-bars"></i>
		</button>
		<!-- Navbar Collapse -->
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav ms-auto">
				<li class="nav-item"><a
					class='nav-link ${param.active_nav.equals("home") ? "active" : ""}'
					href="${initParam.appUrl}/home">Home</a></li>
				<li class="nav-item"><a
					class='nav-link ${param.active_nav.equals("locations") ? "active" : ""}'
					href="${initParam.appUrl}/locations.jsp">Locations</a></li>
				<li class="nav-item"><a
					class='nav-link ${param.active_nav.equals("about") ? "active" : ""}'
					href="${initParam.appUrl}/about.jsp">About Us</a></li>

				<c:choose>
					<c:when test="${LOGGED_USER != null}">
        				<li class="nav-item mt-3 mt-lg-0 ms-lg-3 d-lg-none d-xl-inline-block">
							<div class="dropdown">
			                  <button class="btn btn-primary dropdown-toggle" style="padding-right: 20px;" id="userDropDown" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                  		<c:out value="${LOGGED_USER.firstName}"/>
			                  </button>
			                  <div class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropDown">
			                    <a class="dropdown-item" href="${initParam.appUrl}/my-bookings">My Reservations</a>
			                    <div class="dropdown-divider"></div>
			                    <a class="dropdown-item" href="${initParam.appUrl}/login?logout=true">Logout</a>
			                  </div>
                			</div>
						</li>
					</c:when>
					<c:otherwise>
       					<li
							class="nav-item mt-3 mt-lg-0 ms-lg-3 d-lg-none d-xl-inline-block">
							<a class="btn btn-primary" href="${initParam.appUrl}/login">LOGIN / REGISTER</a>
						</li>
					</c:otherwise>
				</c:choose>
		
			</ul>
		</div>
	</div>
</nav>