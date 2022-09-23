<!-- Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo -->
<!-- User interface for: Index page -->
<%@page import="com.provisio.daos.HotelsDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="ext" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!-- Index page -->
<c:set var="bodyContent">
	<section class="hero-home">
		<div class="swiper-container hero-slider">
			<div class="swiper-wrapper dark-overlay">
				<div class="swiper-slide"
					style="background-image: url(img/photo/photo-1519974719765-e6559eac2575.jpg)"></div>
			</div>
		</div>
		<div class="container py-6 py-md-7 text-white z-index-20">
			<div class="row">
				<div class="col-xl-10">
					<div class="text-center text-lg-start">
						<h1 class="fw-bold text-shadow">Find your room in just few
							clicks</h1>
					</div>
					<div class="search-bar mt-5 p-3 p-lg-1 ps-lg-4">
						<form action="${initParam.appUrl}/search-results" method="GET">
							<div class="row">
								<div class="col-lg-3 d-flex align-items-center form-group">
									<select class="selectpicker" title="Select Location"
										name="location" data-style="btn-form-control">

										<c:forEach var="location" items="${HOTEL_LOCATIONS}">
											<option value="${location}">${location}</option>
										</c:forEach>

									</select>
								</div>
								<div class="col-lg-3 d-flex align-items-center form-group">
									<div
										class="input-label-absolute input-label-absolute-right w-100">
										<label class="label-absolute" for="check-in"><i
											class="fa fa-calendar"></i><span class="sr-only">Check
												In Date</span></label>
										<div class="check-in-dp">
											<input class="form-control border-0 shadow-0" type="text"
												name="checkIn" placeholder="Check In" id="check-in"
												autocomplete="off"  required>
										</div>
									</div>
								</div>
								<div class="col-lg-3 d-flex align-items-center form-group no-divider">
									<div
										class="input-label-absolute input-label-absolute-right w-100">
										<label class="label-absolute" for="check-out"><i
											class="fa fa-calendar"></i><span class="sr-only">Check
												Out Date</span></label>
										<div class="check-out-dp">
											<input class="form-control border-0 shadow-0" type="text"
												name="checkOut" placeholder="Check Out" id="check-out"
												autocomplete="off" required>
										</div>
									</div>
								</div>
								<div
									class="col-lg-2 d-flex align-items-center form-group no-divider">
									<select class="selectpicker" name="roomType" title="Room Type"
										data-style="btn-form-control">
										<option value="1">Double</option>
										<option value="2">Queen</option>
										<option value="3">Double Queen Beds</option>
										<option value="4">King</option>
									</select>
								</div>
								<div class="col-lg-1 d-grid">
									<button class="btn btn-primary" type="submit">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="py-6 bg-gray-100">
		<div class="container">
			<div class="row mb-5">
				<div class="col-md-8">
					<h2>Suggestions</h2>
				</div>
			</div>
			<!-- Slider main container-->
			<div
				class="swiper-container swiper-container-mx-negative swiper-init pt-3"
				data-swiper='{"slidesPerView":4,"spaceBetween":20,"roundLengths":true,"pagination":{"el":".swiper-pagination","clickable":true,"dynamicBullets":true}}'>
				<!-- Additional required wrapper-->
				<div class="swiper-wrapper pb-5">
					<!-- Slides-->
					<c:forEach var="fHotel" items="${FEATURED_HOTELS}">

						<div class="swiper-slide h-auto px-2">
							<!-- place item-->
							<div class="w-100 h-100 hover-animate">
								<div class="card h-100 border-0 shadow">
									<div class="card-img-top overflow-hidden gradient-overlay">
										<img class="img-fluid" src="${fHotel.images.get(0)}"
											alt="${fHotel.name}" /> <a class="tile-link"
											href="/ProvisioHotels/hotels/${fHotel.name.toLowerCase().replace(' ', '-')}"></a>
									</div>
									<div class="card-body d-flex align-items-center">
										<div class="w-100">
											<h6 class="card-title">
												<a class="text-decoration-none text-dark"
													href="/ProvisioHotels/hotels/${fHotel.name.toLowerCase().replace(' ', '-')}"> <c:out value="${fHotel.name}" />
												</a>
											</h6>
											<div class="d-flex card-subtitle mb-3">
												<p class="flex-shrink-1 mb-0 card-stars text-xs text-end">
													<c:forEach var="i" begin="1" end="5">
														<c:if test="${i <= fHotel.rate}">
															<i class="fa fa-star text-warning"></i>
														</c:if>
														<c:if test="${i > fHotel.rate}">
															<i class="fa fa-star text-gray-300"></i>
														</c:if>
													</c:forEach>
												</p>
											</div>
											<p class="card-text text-muted">
												<span class="h4 text-primary">from $ <c:out
														value="${fHotel.perNight}" /></span> per night
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>

					</c:forEach>

				</div>

			</div>
		</div>
	</section>

</c:set>


<ext:base-page>

	<jsp:attribute name="title">Home</jsp:attribute>

	<jsp:attribute name="bodyStyle">margin-top: 72px;</jsp:attribute>

	<jsp:attribute name="header">
      <jsp:include page="common/header.jsp">
      		<jsp:param name="active_nav" value="home" />
      </jsp:include>
    </jsp:attribute>

	<jsp:attribute name="footer">
      <jsp:include page="common/footer.jsp" />
    </jsp:attribute>

	<jsp:attribute name="scripts">
        <script
			src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js">
									
								</script>
        <script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery-date-range-picker/0.19.0/jquery.daterangepicker.min.js"></script>
    	<script type="text/javascript"
			src="${initParam.appUrl}/js/index.js"></script>
    </jsp:attribute>

	<jsp:body>
        ${bodyContent}
    </jsp:body>

</ext:base-page>