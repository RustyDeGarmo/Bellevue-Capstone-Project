<!-- Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo -->
<!-- User interface for: Making a new reservation page-->
<%@page import="java.util.List"%>
<%@page import="com.provisio.utils.CurrencyFormatUtil"%>
<%@page import="com.provisio.utils.DateFormatUtil"%>
<%@page import="com.provisio.models.HotelAmentity"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.provisio.models.Reservation"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="ext" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!-- Review reservation details page -->
<c:set var="bodyContent">

<% 

	Reservation res = (Reservation)request.getAttribute("reservation");

	List<HotelAmentity> amts = res.getAmenities();

%>

	<div class="container py-5">
      <div class="row">
        <div class="col-lg-7">
          <div class="text-block">
            <h1>Reservation: <%= res.getCode() %></h1>
            <p class="fw-bold fw-light">Status: <%= res.getStatus() %></p>
          </div>
          <div class="text-block">
            <h6 class="mb-4"><%= res.getNights() %> nights in <%= res.getHotelRoom().getHotel().getName()+", "+res.getHotelRoom().getHotel().getLocation() %> for <%= res.getGuests() %> guests</h6>
            <div class="row mb-3">
              <div class="col-md-6 d-flex align-items-center mb-3 mb-md-0">
                <div class="date-tile me-3">
                  <div class="text-uppercase"> <span class="text-sm"><%= DateFormatUtil.format(res.getCheckin(), "MMM") %></span><br><strong class="text-lg"><%= DateFormatUtil.format(res.getCheckin(), "dd") %></strong></div>
                </div>
                <p class="text-sm mb-0"><%= DateFormatUtil.format(res.getCheckin(), "EEEEE") %> check-in</p>
              </div>
              <div class="col-md-6 d-flex align-items-center">
                <div class="date-tile me-3">
                  <div class="text-uppercase"> <span class="text-sm"><%= DateFormatUtil.format(res.getCheckout(), "MMM") %></span><br><strong class="text-lg"><%= DateFormatUtil.format(res.getCheckout(), "dd") %></strong></div>
                </div>
                <p class="text-sm mb-0"><%= DateFormatUtil.format(res.getCheckout(), "EEEEE") %> check-out</p>
              </div>
            </div>
          </div>
          <div class="text-block">
            <div class="row">
              <div class="col-md-12">
                <h6> Room size</h6>
                <p class="text-muted"><%= res.getHotelRoom().getRoom().getType() %></p>
              </div>
            </div>
            <%
            if (amts != null && !amts.isEmpty()) {
            %>
            <div class="row">
              <div class="col-md-12">
                <h6> Amenities</h6>
                <p class="text-muted">                	
                	<% 
                	
	                	for(HotelAmentity amt : amts) { 
	            			
	            			out.write(amt.getAmentity().getName().concat("<br/>"));
	            		}

					%>

				</p>
              </div>
            </div>
            <% } %>
          </div>
        </div>
        <div class="col-lg-5 ps-xl-5">
          <div class="card border-0 shadow">
            <div class="card-body p-4">
              <div class="text-block pb-3">
                <div class="d-flex align-items-center">
                  <div>
                    <h6> <a class="text-reset" href="detail-rooms.html"><%= res.getHotelRoom().getHotel().getName() %></a></h6>
                    <p class="text-muted text-sm mb-0"><%= res.getHotelRoom().getHotel().getLocation() %></p>
                  </div>
                  <a class="flex-shrink-0" href="detail-rooms.html"><img class="ms-3 rounded" src="img/photo/photo-1512917774080-9991f1c4c750.jpg" alt="" width="100"></a>
                </div>
              </div>
              <div class="text-block py-3">
                <ul class="list-unstyled mb-0">
                  <li class="mb-3"><i class="fas fa-users fa-fw text-muted me-2"></i><%= res.getGuests() %> guests</li>
                  <li class="mb-0"><i class="far fa-calendar fa-fw text-muted me-2"></i><%= DateFormatUtil.format(res.getCheckin()) %> <i class="fas fa-arrow-right fa-fw text-muted mx-3"></i><%= DateFormatUtil.format(res.getCheckout()) %></li>
                </ul>
              </div>
              <div class="text-block pt-3 pb-0">
                <table class="w-100">
                  <tbody>
                  
                  <%
                  			BigDecimal rmPrice = new BigDecimal(0);
                  
                  			if(res.getHotelRoom() != null && res.getHotelRoom().getPrice() != null) {
                  				rmPrice = res.getHotelRoom().getPrice();
                  			}
                  			
                  			int nights = res.getNights();
                  			
                  			BigDecimal lineTot = res.getPerNight().multiply(BigDecimal.valueOf(nights));
                  %>
                  
                  <tr>
                    <th class="fw-normal py-2">$<%= res.getPerNight() %> x <%= nights %> nights</th>
                    <td class="text-end py-2">$<%= lineTot %></td>
                  </tr>
                  
                  <% 
                	
                		if (amts != null && !amts.isEmpty()) {
                			
                			for(HotelAmentity amt : amts) { 
                			Double amtTotalPrice = (amt.getPriceDouble() * nights); %>
                    			
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
                			}
						%>
					
                  </tbody>
                  <tfoot>
                  <tr class="border-top">
                    <th class="pt-3">Total</th>
                    <td class="fw-bold text-end pt-3">$<%= res.getTotal() %></td>
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

	<jsp:attribute name="title">Reservation Info</jsp:attribute>
	
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
