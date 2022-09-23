<!-- Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo -->
<!-- User interface for: Info on Single Hotel Page-->
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.*"%>
<%@page import="com.provisio.models.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="ext" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!-- Single hotel view -->
<c:set var="bodyContent">

	<div class="container py-5">
		<div class="row">
			<div class="col-lg-8">
				<div class="text-block">
					<p class="text-primary">
						<i class="fa-map-marker-alt fa me-1"></i>
						<c:out value="${SELECTED_HOTEL.location}" />
					</p>
					<h1>
						<c:out value="${SELECTED_HOTEL.name}" />
					</h1>
					<c:out value="${SELECTED_HOTEL.description}" escapeXml="false" />

					<br>
					<h4 class="mb-4">Amenities</h4>
					<div class="row">

						<%
						Collection<List<HotelAmentity>> amts = (Collection<List<HotelAmentity>>) session.getAttribute("HOTEL_AMENTITIES");
						%>

						<%
						for (List<HotelAmentity> lst : amts) {
						%>

						<div class="col-md-6">

							<ul class="list-unstyled text-muted">

								<%
								for (HotelAmentity amt : lst) {
								%>

								<li class="mb-2"><i
									class="<%=amt.getAmentity().getIcon()%> text-secondary w-1rem me-3 text-center"></i><span
									class="text-sm"><%=amt.getAmentity().getName()%></span></li>

								<%
								}
								%>

							</ul>

						</div>

						<%
						}
						%>

					</div>
				</div>

				<div class="text-block">
					<h5 class="mb-4">Attractions</h5>
					<div class="row">
						<div class="col-md-6">
							<ul class="list-unstyled text-muted">
								<c:forEach var="attr" items="${SELECTED_HOTEL.attractions}">
									<li class="mb-2"><i
										class="${attr.icon} text-secondary w-1rem me-3 text-center"></i><span
										class="text-sm"><c:out value="${attr.name}" /></span></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>

				<div class="text-block">
					<h5 class="mb-4">Gallery</h5>
					<div class="row gallery mb-3 ms-n1 me-n1">

						<c:forEach var="image" items="${SELECTED_HOTEL.images}">

							<div class="col-lg-4 col-6 px-1 mb-2">
								<a href="${initParam.appUrl}/${image}" data-fancybox="gallery"
									title="Our street"> <img class="img-fluid"
									src="${initParam.appUrl}/${image}" alt="...">
								</a>
							</div>

						</c:forEach>

					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="p-4 shadow ms-lg-4 rounded sticky-top"
					style="top: 100px;">
					<p class="text-muted">
						<span class="text-primary h2" id="per-night">$<c:out
								value="${SELECTED_HOTEL.perNight}" /></span> per night
					</p>
					<hr class="my-4">
					<form class="form" id="booking-form" method="POST" action='${initParam.appUrl}/hotels/${SELECTED_HOTEL.name.replace(" ", "-").toLowerCase()}' autocomplete="off">
					
						<%
						
							Map<String, String> data = null;
						
							if(session.getAttribute("BOOKING_DATA") != null) {
								
								data  = (Map<String, String>) session.getAttribute("BOOKING_DATA");
							}
						
						%>
					
						<div class="mb-4">
							<label class="form-label" for="bookingDate">Your stay</label>
							<div class="datepicker-container datepicker-container-right">
                  				<input class="form-control" type="text" name="bookingDate" id="bookingDate" value='<%= data != null ? data.get("bookingDate") : "" %>' placeholder="Choose your dates" required="required">
                			</div>
							<small class="form-text text-danger" id="bookingDateHelp"></small>
						</div>
						<div class="mb-4">
							<label class="form-label" for="guests">Guests</label> 
							
							<% 
								int selectedGuests = data != null ? Integer.parseInt(data.get("guests")) : -1;
							%>
							
							<select class="form-control" name="guests" id="guests">
								<option value="1" <%=selectedGuests == 1 ? "selected" : ""  %>>1 Guest</option>
								<option value="2" <%=selectedGuests == 2 ? "selected" : ""  %>>2 Guests</option>
								<option value="3" <%=selectedGuests == 3 ? "selected" : ""  %>>3 Guests</option>
								<option value="4" <%=selectedGuests == 4 ? "selected" : ""  %>>4 Guests</option>
								<option value="5" <%=selectedGuests == 5 ? "selected" : ""  %>>5 Guests</option>
							</select> 
							
							<small class="form-text text-danger" id="guestsHelp"></small>
						</div>
						<div class="mb-4">
							<label class="form-label" for="room_size">Room Type</label> 
						
							<c:set var="map" scope="session" value="${BOOKING_DATA}"/>
							
							<select class="form-control" name="room_size" id="room_size">
								<c:forEach var="option" items="${hotel_rooms}" >
	                				<option value="${option.id}" <c:if test="${map['room_size'] == option.id}"><c:out value="selected"/></c:if>><c:out value="${option.room.type}"/></option>
	                			</c:forEach>
							</select>
						</div>
						<div class="mb-4">
							<label class="form-label">Amenities</label>

							<%
							List<HotelAmentity> amentities = amts.stream().flatMap(List::stream).collect(Collectors.toList());
							for (HotelAmentity amentity : amentities) {
							%>

							<div class="form-check">
								<input class="form-check-input" name="ham_<%=amentity.getId()%>" type="checkbox" <%= data != null && data.containsKey("ham_"+amentity.getId()) ? "checked" : ""%>> 
									<label class="form-check-label">
									 <span class="text-sm"><%=amentity.getAmentity().getName() + " ($" + amentity.getPrice() + " "+ amentity.getChargeType().replace("_", " ") + ")"%></span>
								</label>
							</div>

							<%
							}
							%>

						</div>
						<div class="d-grid mb-4">
							<input type="hidden" name="total" value="0" /> 
							<input type="hidden" name="nights" value="0" /> <input type="hidden" name="room_price" value="${SELECTED_HOTEL.perNight}" />
							<button id="submitBtn" class="btn btn-primary" type="submit">Book your stay</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</c:set>

<ext:base-page>

	<jsp:attribute name="title">Single Room Details</jsp:attribute>

	<jsp:attribute name="bodyStyle">margin-top: 72px;</jsp:attribute>

	<jsp:attribute name="header">
      <jsp:include page="common/header.jsp">
      		<jsp:param name="active_nav" value="" />
      </jsp:include>
    </jsp:attribute>

	<jsp:attribute name="footer">
      <jsp:include page="common/footer.jsp" />
    </jsp:attribute>

	<jsp:attribute name="scripts">
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-date-range-picker/0.19.0/jquery.daterangepicker.min.js"></script>
	    <script type="text/javascript">
						var singleMonth = false;
						if ($(window).width() < 767) {
							singleMonth = true;
						}

						var dateRangeConfig = {
							autoClose : true,
							startDate : new Date(),
							selectForward : true,
							applyBtnClass : "btn btn-primary",
							container : ".datepicker-container",
							inline : true,
							singleMonth : singleMonth,
							showDateFilter : function(time, date) {
								return ('<div style="padding:5px;">\
		                            <span style="">'
										+ date + "</span>\
		                        </div>");
							},
							customOpenAnimation : function(cb) {
								$(this).fadeIn(300, cb);
							},
							customCloseAnimation : function(cb) {
								$(this).fadeOut(300, cb);
							},
						};

						$(document).ready(
								function() {

									setTimeout(function() {
										$('#room_size').trigger('change');
									}, 1000);
									
									$('form :input').change(function() {
										updateFormData();
									});

									$('#bookingDate').dateRangePicker(
											dateRangeConfig).bind(
											'datepicker-change',
											function(event, obj) {
												updateFormData();
											});

								});

						function updateFormData() {

							if ($('#bookingDate').val()) {

								$('#guestsHelp').html('');
								$('#bookingDateHelp').html('');
								$('#submitBtn').prop("disabled", false);
								$("input[name='nights']").val(0);
								$("input[name='total']").val(0);

								var formData = $('#booking-form').serialize();

								$
										.ajax({
											url : "${initParam.appUrl}/reservation-calculator",
											type : 'POST',
											data : formData,
											dataType : "json",
											success : function(success) {
												$("input[name='nights']").val(
														success.nights);
												$("input[name='total']").val(
														success.total);
												$("input[name='room_price']")
														.val(success.roomPrice);
												$('#per-night')
														.html(
																'$'
																		+ success.roomPrice);
											},
											error : function(error) {
												let errorMsg = error.responseJSON;
												switch (errorMsg.error) {

												case 'invalid-guests':
													$('#guestsHelp').html(
															errorMsg.message);
													$('#submitBtn').prop(
															"disabled", true);
													break;

												case 'invalid-dates':
													$('#bookingDateHelp').html(
															errorMsg.message);
													$('#submitBtn').prop(
															"disabled", true);
													break;

												}

											},
										});

							}

						}
					</script>
    </jsp:attribute>

	<jsp:body>
        ${bodyContent}
    </jsp:body>

</ext:base-page>
