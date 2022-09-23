<!-- Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo -->
<!-- User interface for: Locations Page-->
<%@page import="com.provisio.daos.HotelsDAO"%>
<%@page import="com.provisio.models.Hotel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="ext" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!-- Locations -->
<c:set var="bodyContent">

	<section>
		<div class="map-wrapper-500">
			<div class="h-100" id="categoryMap"></div>
		</div>
	</section>

	<section class="py-6 bg-gray-100">
		<div class="container">
			<div class="row mb-5">
				<div class="col-md-8">
					<h2>Our Hotels</h2>
					Provisio Hotels has locations across North American! We are proud
					to offer you a luxurious yet affordable stay no matter where your
					travels take you.
				</div>
			</div>
			<!-- Slider main container-->
			<div
				class="swiper-container swiper-container-mx-negative swiper-init pt-3"
				data-swiper='{"slidesPerView":4,"spaceBetween":20,"roundLengths":true,"pagination":{"el":".swiper-pagination","clickable":true,"dynamicBullets":true}}'>
				<!-- Additional required wrapper-->
				<div class="swiper-wrapper pb-5">
					<!-- Slides-->

					<%
					HotelsDAO hdao = new HotelsDAO();
					List<Hotel> featuredHotels = hdao.fetchFeaturedHotels();
					%>

					<%
					for (Hotel hot : featuredHotels) {
					%>

					<div class="swiper-slide h-auto px-2">
						<div class="w-100 h-100 hover-animate">
							<div class="card h-100 border-0 shadow">
								<div class="card-img-top overflow-hidden gradient-overlay">
									<img class="img-fluid" src="<%=hot.getImages().get(0)%>"
										alt="<%=hot.getName()%>" /> <a class="tile-link"
										href="/ProvisioHotels/hotels/<%=hot.getName().toLowerCase().replace(' ', '-')%>"></a>
								</div>
								<div class="card-body d-flex align-items-center">
									<div class="w-100">
										<h6 class="card-title">
											<a class="text-decoration-none text-dark"
												href="/ProvisioHotels/hotels/<%=hot.getName().toLowerCase().replace(' ', '-')%>"><%=hot.getName()%></a>
										</h6>
										<div class="d-flex card-subtitle mb-3">
											<p class="flex-shrink-1 mb-0 card-stars text-xs text-end">
												<%
												for (int i = 1; i <= 5; i++) {
												%>
												<%
												if (i <= hot.getRate()) {
												%>
												<i class="fa fa-star text-warning"></i>
												<%
												} else {
												%>
												<i class="fa fa-star text-gray-300"></i>
												<%
												}
												}
												%>
											</p>
										</div>
										<p class="card-text text-muted">
											<span class="h4 text-primary">from $<%=hot.getPerNight()%></span>
											per night
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>

					<%
					}
					%>
				</div>
			</div>
		</div>
	</section>

</c:set>

<ext:base-page>

	<jsp:attribute name="title">Locations</jsp:attribute>

	<jsp:attribute name="bodyStyle">margin-top: 72px;</jsp:attribute>

	<jsp:attribute name="header">
      <jsp:include page="common/header.jsp">
      		<jsp:param name="active_nav" value="locations" />
      </jsp:include>
    </jsp:attribute>

	<jsp:attribute name="footer">
      <jsp:include page="common/footer.jsp" />
    </jsp:attribute>

	<jsp:attribute name="styles">
    	<link rel="stylesheet"
			href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css">
    </jsp:attribute>

	<jsp:attribute name="scripts">
	   	<!-- Map-->
	    <script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js"></script>
	    <!-- Available tile layers-->
	    <script src="js/map-layers.js"></script>
	    <script src="js/map-category.js"></script>
	    <script>
						createListingsMap({
							mapId : 'categoryMap',
							jsonFile : 'js/rooms-geojson.json',
							mapPopupType : 'rental',
							useTextIcon : true
						});
					</script>
    </jsp:attribute>

	<jsp:body>
        ${bodyContent}
    </jsp:body>

</ext:base-page>