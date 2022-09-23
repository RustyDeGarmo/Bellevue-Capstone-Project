<!-- Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo -->
<!-- User interface for: MyBooks aka reservations made page -->
<%@page import="com.provisio.utils.DateFormatUtil"%>
<%@page import="com.provisio.models.Reservation"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="ext" tagdir="/WEB-INF/tags"%>
<%@ page session="true" %>

<!-- Show my bookings -->
<c:set var="bodyContent">

	<%
	
		List<Reservation> userReservations	= (List<Reservation>)request.getAttribute("userReservations");
	
	%>

	<section class="py-6 bg-gray-100">
		<div class="container">
        <div class="d-flex justify-content-between align-items-center flex-column flex-lg-row mb-5">
          <div class="me-3">
          	<h2>My bookings</h2>
            <p class="mb-3 mb-lg-0">You have <strong><%= userReservations.size() %> total bookings</strong></p>
          </div>
          
          <div class="row">
         
	         	<div class="col-md-2">
	         		<label class="form-label mt-2 float-end" for="bkSearch">Search</label>
	         	</div>
	         	
	         	<div class="col-md-5">
	         		<input class="form-control" id="bkSearch" type="text" aria-describedby="inputSt" placeholder="Booking ID here ..."/>
	         	</div>
	         	
	         	<div class="col-md-5">
	         		 <button class="btn btn btn-outline-primary">
	            		<span class="d-none d-sm-inline">Loyalty Points: <c:out value="${loyaltyPoints.getLoyaltyPoints()}" /></span>
	            	</button>
	         	</div>
         	
          	</div>
         
        </div>
        <div class="list-group shadow mb-5">
        	<% for(Reservation res : userReservations) { %>
        	
		        <a class="list-group-item list-group-item-action p-4" href="${initParam.appUrl}/view-reservation/<%= res.getCode()%>">
		            <div class="row">
		              <div class="col-lg-4 align-self-center mb-4 mb-lg-0">
		                <div class="d-flex align-items-center mb-3">
		                  <h2 class="h5 mb-0"><%= res.getHotelRoom().getHotel().getName() %></h2>
		                </div>
		                <p class="text-sm text-muted"><%= res.getHotelRoom().getRoom().getType() %></p><span class="badge badge-pill p-2 badge-secondary-light">
		                	<%= DateFormatUtil.format(res.getCheckin(), "MM-dd-yyyy")+" through "+ DateFormatUtil.format(res.getCheckout(), "MM-dd-yyyy") %>
		                </span>
		              </div>
		              <div class="col-lg-8">
		                <div class="row">
		                  <div class="col-6 col-md-4 col-lg-3 py-3 mb-3 mb-lg-0">
		                    <h6 class="label-heading">Nights </h6>
		                    <p class="text-sm fw-bold"><%= res.getNights() %></p>
		                    <h6 class="label-heading">Occupancy</h6>
		                    <p class="text-sm fw-bold mb-0"><%= res.getGuests() %></p>
		                  </div>
		                  <div class="col-6 col-md-4 col-lg-3 py-3">		                   
		                    <h6 class="label-heading">Charge</h6>
		                    <p class="text-sm fw-bold">$<%= res.getTotal() %></p>
		                    <h6 class="label-heading">Booked Date</h6>
		                    <p class="text-sm fw-bold mb-0"><%= DateFormatUtil.format(res.getBookedDate(), "MM-dd-yyyy") %></p>
		                  </div>
		                  <div class="col-12 col-lg-3 align-self-center">
			                  <span class="text-primary text-sm text-uppercase"><i class="fa fa-check fa-fw me-2"> </i>Confirmed</span>
		                  </div>
				  <div class="col-6 col-md-4 col-lg-3 py-3 mb-3 mb-lg-0">
									<h6 class="label-heading">Loyalty Points Earned</h6>
									<p class="text-sm fw-bold"><%=(res.getNights()*150)%></p>
				</div>							
		                </div>
		              </div>
		            </div>
		         </a>
        	
        	<% } %>
        </div>
      </div>
	</section>

</c:set>


<ext:base-page>

	<jsp:attribute name="title">My Bookings</jsp:attribute>
	
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
    	 <script type="text/javascript">
    	 
    	 $(document).ready(function() {
    		 
    		 $('#bkSearch').on('keyup', function() {
    				
    				 var val = $('#bkSearch').val().trim();
    				 
    				 $.ajax({
						url : "${initParam.appUrl}/filter-reservations",
						type : 'GET',
						data : {'rev_code': val},
						dataType : "json",
						success: function(success) {
							
							$('.list-group').empty();
							
							var html = "";
							
							var appURL = "${initParam.appUrl}/view-reservation/";
							
							$.each(success, function(k, v) {
								
								html+= '<a class="list-group-item list-group-item-action p-4" href="'+appURL+v.code+'">'
								html+= '<div class="row">'
								html+= '<div class="col-lg-4 align-self-center mb-4 mb-lg-0">'
								html+= '<div class="d-flex align-items-center mb-3">'
								html+= '<h2 class="h5 mb-0">'+v.hotelRoom.hotel.name+'</h2>'
								html+= '</div>'
								html+= '<p class="text-sm text-muted">'+v.hotelRoom.room.type+'</p><span class="badge badge-pill p-2 badge-secondary-light">'+v.checkin+' - '+v.checkout+'</span>'
								html+= '</div>'
								html+= '<div class="col-lg-8">'
								html+= '<div class="row">'
								html+= '<div class="col-6 col-md-4 col-lg-3 py-3 mb-3 mb-lg-0">'
								html+= '<h6 class="label-heading">Nights </h6>'
								html+= '<p class="text-sm fw-bold">'+v.nights+'</p>'
								html+= '<h6 class="label-heading">Occupancy</h6>'
								html+= '<p class="text-sm fw-bold mb-0">'+v.guests+'</p>'
								html+= '</div>'
								html+= '<div class="col-6 col-md-4 col-lg-3 py-3">'		                   
								html+=  '<h6 class="label-heading">Charge</h6>'
								html+= '<p class="text-sm fw-bold">$'+v.total+'</p>'
								html+= '<h6 class="label-heading">Booked Date</h6>'
								html+= '<p class="text-sm fw-bold mb-0">'+v.bookedDate+'</p>'
								html+= '</div>'
								html+= '<div class="col-12 col-lg-3 align-self-center">'
						        html+= '<span class="text-primary text-sm text-uppercase"><i class="fa fa-check fa-fw me-2"> </i>Confirmed</span>'
						        html+= ' </div>'
						        html+= ' </div>'
						        html+= ' </div>'
						        html+= '  </div>'
						        html+= ' </a>';
								
							});
							
							html = html ? html : '<p class="mb-3 mb-lg-0 text-center">No data found !</p>'; 
							
							$('.list-group').html(html);
						},
						error: function(error) {
							//console.log(error);
						}
    				 });	
    		 });
    		 
    	 });
    	 
    	 </script>
    </jsp:attribute>
    
	<jsp:body>
        ${bodyContent}
    </jsp:body>
    
</ext:base-page>
