<!-- Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo -->
<!-- User interface for: Reservation Confirmation Page-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="ext" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page import="com.provisio.utils.CurrencyFormatUtil"%>
<%@page import="com.provisio.daos.HotelAmenityDAO"%>

<!-- Reservation confirmed page -->
<c:set var="bodyContent">

	<div class="container py-5">
      <div class="row">
        <div class="col-lg-7">
          <div class="text-block">
            <h2>Booking Confirmed</h2>
            <p class="text-muted fw-light">
              Thank you for booking with Provisio Hotels! Your reservation has been confirmed.
            </p>
            <div class="row">
              <div class="col-md-12">
                <a class="btn btn-primary" href="${initParam.appUrl}/view-reservation/${code}"><i class="far fa-file me-2"></i>View Your Order</a>
              </div>
            </div>
            <div class="col-lg-5 d-flex align-items-center mt-5">
              <img class="img-fluid" src="img/illustration/undraw_celebration_0jvk.svg" alt="booking-confirmed-img">
            </div>
          </div>
        </div>
        <div class="col-lg-5 ps-xl-5">
          <div class="card border-0 shadow">
            <div class="card-body p-4">
              <div class="text-block pb-3">
                <div class="d-flex align-items-center">
                  <div>
                    <h6> <a class="text-reset" href="#">${hotel_name}</a></h6>
                    <p class="text-muted text-sm mb-0">${location}</p>
                  </div>
                  <a class="flex-shrink-0" href="#"><img class="ms-3 rounded" src="${imgURL}" alt="hotel-img" width="100"></a>
                </div>
              </div>
              <div class="text-block py-3">
                <ul class="list-unstyled mb-0">
                  <li class="mb-3"><i class="fas fa-users fa-fw text-muted me-2"></i><c:out value="${guests}"/> guests</li>
                  <li class="mb-0"><i class="far fa-calendar fa-fw text-muted me-2"></i><c:out value="${checkin}"/><i class="fas fa-arrow-right fa-fw text-muted mx-3"></i><c:out value="${checkout}"/></li>
                </ul>
              </div>
              <div class="text-block pt-3 pb-0">
                <table class="w-100">
                
                  <tbody>
                  <tr>
                    <td class="fw-normal py-2">$<c:out value="${roomPrice}"/> x <c:out value="${nights}"/> nights</td>
                    <td class="text-end py-2">$<c:out value="${roomTot}"/></td>
                  </tr>
                  
                   
                   <c:forEach var="amt" items="${amentities}">
                   		<c:if test = "${amt.getChargeType().equalsIgnoreCase('per_night')}">
                   		<tr>
                  			<th class="fw-normal pt-2 pb-3"><c:out value="${amt.getAmentity().getName()}" />
                   			($<c:out value='${amt.getPrice()}' /> per night)</th>
	                    	<td class="text-end pt-2 pb-3">$<c:out value="${amt.getPrice() * nights}" /></td>
     				 	</c:if>
     				 	
     				 	<c:if test = "${amt.getChargeType().equalsIgnoreCase('flat_rate')}">
                   		<tr>
                  			<th class="fw-normal pt-2 pb-3"><c:out value="${amt.getAmentity().getName()}" />
                   			(flat rate)</th>
	                    	<td class="text-end pt-2 pb-3">$<c:out value="${amt.getPrice()}" /></td>
     				 	</c:if>
     				 	
 
	                </tr>	  
                  </c:forEach>
				  	
                 
                  </tbody>
                  <tfoot>
                  <tr class="border-top">
                    <th class="pt-3">Total</th>
                    <td class="fw-bold text-end pt-3">$<c:out value="${total}" /></td>
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

	<jsp:attribute name="title">Reservation Confirmed</jsp:attribute>
	
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