<!-- Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo -->
<!-- User interface for: Page reviewing reservations-->
<%@page import="com.provisio.utils.CurrencyFormatUtil"%>
<%@page import="com.provisio.daos.HotelAmenityDAO"%>
<%@page import="java.util.*"%>
<%@ page import="com.google.gson.Gson"%>
<%@page import="com.provisio.models.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="ext" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!-- Review reservation page -->
<c:set var="bodyContent">

	<div class="container py-5">
		<div class="row">
			<div class="col-lg-7">
				<div class="text-block">
					<p class="text-primary fw-bold">RESERVATION</p>
					<h1>Review your reservation</h1>
					<p class="text fw-light">You're just a few clicks away from
						your next stay! We can't wait to see you!</p>
				</div>
				<div class="text-block">
					<h4 class="mb-4">Cancellation Policy</h4>
					<p class="text fw-light">We know plans can change. Reservations
						cancelled more than 72 hours prior to reserved check-in will
						receive a full refund. After that, you'll be charged for the first
						night of your stay plus any applicable taxes and fees.</p>
				</div>
				<div class="text-block">
					<div class="d-grid mb-4">
						<form method="POST"
							action="${initParam.appUrl}/confirm-reservation">
							<div class="row">
								<div class="col-md-6">
									<a class="btn float-start"
										href='${initParam.appUrl}/hotels/${SELECTED_HOTEL.name.replace(" ", "-").toLowerCase()}'><span
										class="text-muted">< Cancel</span></a>
								</div>
								<div class="col-md-6">
									<button class="btn btn-primary float-end" type="submit">Confirm
										></button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<%
			Map<String, String> data = (Map<String, String>) session
					.getAttribute("BOOKING_DATA");

			List<Integer> selectedAmts = new ArrayList<>();

			for (Map.Entry<String, String> entry : data.entrySet()) {

				if (entry.getKey().contains("ham_")) {
					selectedAmts.add(Integer.parseInt(entry.getKey().split("_")[1]));
				}
			}

			String[] bookingDates = data.get("bookingDate").toString().split("to");

			HotelAmenityDAO hadao = new HotelAmenityDAO();

			List<HotelAmentity> amts = selectedAmts.isEmpty() ? new ArrayList<>() : hadao.fetchAmenityData(selectedAmts);
			
			%>

			<div class="col-lg-5 ps-xl-5">
				<div class="card border-0 shadow">
					<div class="card-body p-4">
						<div class="text-block pb-3">
							<div class="d-flex align-items-center">
								<div>
									<h6>
										<a class="text-reset" href="#"><c:out
												value="${SELECTED_HOTEL.name}" /></a>
									</h6>
									<p class="text-muted text-sm mb-0">${SELECTED_HOTEL.location}</p>
								</div>
								<a class="flex-shrink-0" href="#"><img class="ms-3 rounded"
									src='${SELECTED_HOTEL.images.get(0)}' alt="hotel-img"
									width="100"></a>
							</div>
						</div>
						<div class="text-block py-3">
							<ul class="list-unstyled mb-0">
								<li class="mb-3"><i
									class="fas fa-users fa-fw text-muted me-2"></i><%=data.get("guests")%>
									guests</li>
								<li class="mb-0"><i
									class="far fa-calendar fa-fw text-muted me-2"></i><%=bookingDates[0].trim()%>
									<i class="fas fa-arrow-right fa-fw text-muted mx-3"></i><%=bookingDates[1].trim()%></li>
							</ul>
						</div>
						<div class="text-block pt-3 pb-0">
							<table class="w-100">
								<tbody>
									<tr>
										<th class="fw-normal py-2">$<%=CurrencyFormatUtil.format(Double.parseDouble(data.get("room_price")))%>
											x <%=data.get("nights")%> nights
										</th>
										<td class="text-end py-2">$<%=CurrencyFormatUtil.format(Double.parseDouble(data.get("room_price"))
		* Double.parseDouble(data.get("nights")))%></td>
									</tr>



									<%
									for (HotelAmentity amt : amts) {
										Double amtTotalPrice = (amt.getPriceDouble()
										* Double.parseDouble(data.get("nights")));
									%>

									<tr>
										<th class="fw-normal pt-2 pb-3"><%=amt.getAmentity().getName()%>

											<%
											if ((amt.getChargeType().equalsIgnoreCase("per_night"))) {
											%> ($<%=amt.getPriceDouble()%> per night)</th>
										<td class="text-end pt-2 pb-3">$<%=CurrencyFormatUtil.format(amtTotalPrice)%></td>


										<%
										} else {
										%>
										(<%=amt.getChargeType().replace("_", " ")%>)
										</th>
										<td class="text-end pt-2 pb-3">$<%=amt.getPrice()%></td>
										<%
										}
										%>


									</tr>
									<%
									}
									%>

								</tbody>
								<tfoot>
									<tr class="border-top">
										<th class="pt-3">Total</th>
										<td class="fw-bold text-end pt-3">$<%=CurrencyFormatUtil.format(Double.parseDouble(data.get("total")))%></td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</c:set>

<ext:base-page>

	<jsp:attribute name="title">Review Your Reservation</jsp:attribute>

	<jsp:attribute name="bodyStyle">margin-top: 72px;</jsp:attribute>

	<jsp:attribute name="header">
      <jsp:include page="common/header.jsp">
      		<jsp:param name="active_nav" value="" />
      </jsp:include>
    </jsp:attribute>

	<jsp:attribute name="footer">
      <jsp:include page="common/footer.jsp" />
    </jsp:attribute>

	<jsp:body>
        ${bodyContent}
    </jsp:body>

</ext:base-page>
