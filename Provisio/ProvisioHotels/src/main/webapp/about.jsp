<!-- Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo -->
<!-- User interface for: About Us/Contact Page-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="ext" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%-- About us page --%>
<c:set var="bodyContent">
<%--Hero Banner for About Us --%>
<section class="hero py-6 py-lg-7 text-white dark-overlay">
    	<img class="bg-image" src="${initParam.appUrl}/img/photo/aron-visuals-3jBU9TbKW7o-unsplash.jpg" alt="About Provisio Hotels" />
		<div class="container overlay-content">
			<h1 class="hero-heading">About Provisio Hotels</h1>
		</div>
	</section>
<%--About Us Text--%>
	<section class="hero py-4 py-lg-5">
		<div class="row">
			<div class="col-xl-8 mx-auto">
				<p class="text-muted">Provisio Hotels was established in 1984
					and is proud to have several locations throughout North America. We
					pride ourselves in offering consistently great experiences by
					having luxurious, yet attainable, suites with a variety of
					amenities. Diversity and inclusion is fundamental to our core
					values and strategic business goals. Taking care of people and
					their well-being is our guiding principle in all we do.</p>
				<p class="text-muted">Many of hotels offer fine dining options,
					swimming pools, and fitness centers, so whether the stay is for
					work or play, it's always a pleasure!</p>
				<ul class="text-muted fw-light" style="text-align: left;">
					<li>Room service is available 24 hours a day</li>
					<li>You'll find all our rooms provide simple additions to make
						your stay even easier such as mini-fridges, coffeemakers, and flat
						screen TVs.</li>
					<li>Breakfast can be ordered, and is delivered directly to
						your room.</li>
					<li>Wi-Fi is available at all locations for a low cost.</li>
					<li>Parking is available at all locations for a low cost.</li>
				</ul>

			</div>
		</div>
	</section>
<%--Hero Banner for Contact Info --%>
	<section class="hero py-6 py-lg-7 text-white dark-overlay">
    	<img class="bg-image" src="${initParam.appUrl}/img/photo/photo-1522143049013-2519756a52d4.jpg" alt="How can we help you today?" />
		<div class="container overlay-content">
			<h1 class="hero-heading">How can we help you today?</h1>
		</div>
	</section>
	<%--Contact info and various means of contact --%>
	<section class="py-6">
		<div class="container">
			<div class="row">
				<div class="col-md-4 text-center text-md-start mb-4 mb-md-0">
					<div class="icon-rounded mb-4 bg-primary-light">
						<i class="fa fa-2x text-primary fa-map-marker-alt"></i>
					</div>
					<h3 class="h5">Address</h3>
					<p class="text-muted">
						1000 Galvin Rd S<br>Bellevue, NE 68005
					</p>
				</div>
				<div class="col-md-4 text-center text-md-start mb-4 mb-md-0">
					<div class="icon-rounded mb-4 bg-primary-light">
						<i class="fa fa-2x text-primary fa-phone"></i>
					</div>
					<h3 class="h5">Call center</h3>
					<p class="text-muted">Our call center is available 24 hours a
						day to assist with reservations, answer questions, and ensure your
						stay is nothing short of perfection.</p>
					<p class="text-muted">
						<strong>1 (800) 535-4028</strong>
					</p>
				</div>
				<div class="col-md-4 text-center text-md-start mb-4 mb-md-0">
					<div class="icon-rounded mb-4 bg-primary-light">
						<i class="fa fa-2x text-primary fa-envelope"></i>
					</div>
					<h3 class="h5">Email Us</h3>
					<p class="text-muted">Feel free to email Provisio Hotels day or
						night, we are always at your service.</p>
					<ul class="list-unstyled text-muted">
						<li><a
							href="mailto:comments@provisio.com?subject=Comments and Inquires from Provisio.com">
								Comments and Inquiries </a></li>
						<li><a
							href="mailto:support@provisio.com?subject=Technical Support Request Provisio.com">
								Technical Support Request </a></li>
					</ul>
				</div>
			</div>
		</div>
	</section>

	<section class="py-6 bg-gray-100">
		<div class="container">
			<h2 class="h4 mb-5">Contact form</h2>
			<c:if test='${emailDelivered}'>
				<div class="alert alert-success" role="alert"><c:out value='${emailDelivered}' /></div>
			</c:if>
			<div class="row">
				<div class="col-md-7 mb-5 mb-md-0">
					<form class="form" id="contact-form" method="POST" action="">
						<div class="controls">
							<div class="row">
								<div class="col-sm-6">
									<div class="mb-4">
										<label class="form-label" for="firstname">Your firstname *</label>
										<input class="form-control" type="text" name="firstname" id="firstname" placeholder="Enter your firstname" required>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="mb-4">
										<label class="form-label" for="lastname">Your lastname*</label> 
										<input class="form-control" type="text" name="lastname" id="lastname" placeholder="Enter your  lastname" required>
									</div>
								</div>
							</div>
							<div class="mb-4">
								<label class="form-label" for="email">Your email *</label> 
								<input class="form-control" type="email" name="email" id="email" placeholder="Enter your  email" required>
							</div>
							<div class="mb-4">
								<label class="form-label" for="subject">Subject *</label> 
								<input class="form-control" type="text" name="subject" id="subject" placeholder="Subject" required>
							</div>
							<div class="mb-4">
								<label class="form-label" for="message">Your message for us *</label>
								<textarea class="form-control" rows="4" name="message" id="message" placeholder="Enter your message" required></textarea>
							</div>
							<button class="btn btn-outline-primary" type="submit">Send message</button>
						</div>
					</form>
				</div>
				<div class="col-md-5">
					<div class="ps-lg-4 text-sm">
						<p class="text-muted">We're here for you, and always ready to
							talk. Please provide your contact information and Provisio Hotels
							will gladly reach out to you. Whether it is a future reservation
							with some very specific requests, or a past stay that you wish to
							discuss, we are always happy to hear from you to ensure Provisio
							Hotels are always your first booking choice.</p>

					</div>
				</div>
			</div>
		</div>
	</section>

</c:set>

<ext:base-page>

	<jsp:attribute name="title">About Us</jsp:attribute>

	<jsp:attribute name="bodyStyle">margin-top: 72px;</jsp:attribute>

	<jsp:attribute name="header">
      <jsp:include page="common/header.jsp">
      		<jsp:param name="active_nav" value="about" />
      </jsp:include>
    </jsp:attribute>

	<jsp:attribute name="footer">
      <jsp:include page="common/footer.jsp" />
    </jsp:attribute>

	<jsp:attribute name="scripts">

    </jsp:attribute>

	<jsp:body>
        ${bodyContent}
    </jsp:body>

</ext:base-page>
