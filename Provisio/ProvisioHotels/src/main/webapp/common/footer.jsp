<!-- Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo -->
<!-- Footer jsp file for common footer -->
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- Footer -->
<div class="py-6 bg-gray-200 text-muted">
	<div class="container">
		<div class="row">
			<div class="col-lg-4 mb-5 mb-lg-0">
				<div class="fw-bold text-uppercase text-dark mb-3">Directory</div>
				<p>Finding the perfect place to stay should be the easiest part of your trip. 
				Book with Provisio Hotels and be ready to relax.</p>
			</div>

			<div class="col-lg-2 col-md-6 mb-5 mb-lg-0">
				<h6 class="text-uppercase text-dark mb-3">Pages</h6>
				<ul class="list-unstyled">
					<li><a class="text-muted" href="index.jsp">Home</a></li>
					<li><a class="text-muted" href="locations.jsp">Locations</a></li>
					<li><a class="text-muted" href="about.jsp">About Us</a></li>
				</ul>
			</div>
			<div class="col-lg-4">
				<h6 class="text-uppercase text-dark mb-3">Daily Offers &
					Discounts</h6>
				<p class="mb-3">Paradise at Provisio Hotels is always in the budget when 
				you sign up for our exclusive daily offers and discounts </p>
			</div>
		</div>
	</div>
</div>



<div class="fixed-bottom py-4 fw-light bg-gray-800 text-gray-300">
	<div class="container">
		<div class="row align-items-center">
			<div class="col-md-4 text-center text-md-start">
				<p class="text-sm mb-md-0">
					&copy;
					<%=new SimpleDateFormat("YYYY").format(new Date())%>, Proviso Hotels. All rights reserved.
				</p>
			</div>
				<div class="col-md-4 text-center text-md-start">
				<p class="text-sm mb-md-0">
					Arnet - DeGarmo - Hendren - Perkins - Thomas - Wright
				</p>
			</div>
			<div class="col-md-4">
				<ul class="list-inline mb-0 mt-2 mt-md-0 text-center text-md-end">
					<li class="list-inline-item"><img class="w-2rem"
						src="${initParam.appUrl}/img/visa.svg" alt="..."></li>
					<li class="list-inline-item"><img class="w-2rem"
						src="${initParam.appUrl}/img/mastercard.svg" alt="..."></li>
					<li class="list-inline-item"><img class="w-2rem"
						src="${initParam.appUrl}/img/paypal.svg" alt="..."></li>
					<li class="list-inline-item"><img class="w-2rem"
						src="${initParam.appUrl}/img/western-union.svg" alt="..."></li>
				</ul>
			</div>
		</div>
	</div>
</div>