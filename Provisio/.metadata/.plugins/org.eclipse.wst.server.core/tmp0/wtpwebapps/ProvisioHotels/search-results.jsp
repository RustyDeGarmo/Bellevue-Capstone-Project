<!-- Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo -->
<!-- User interface for: Search results page-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="ext" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!-- Search results page -->
<c:set var="bodyContent">

  <div class="container-fluid pt-5 pb-3 border-bottom px-lg-5">
      <div class="row">
        <div class="col-xl-8">
          <h1>Great Choice! </h1>
          <p class="lead text-muted"> You're just a few clicks away from your next stay!<br> We're almost as excited as you are for your next trip with Provisio Hotels.</p>
        </div>
      </div>
    </div>
    <div class="container-fluid py-5 px-lg-5">
      <div class="row">
        



        <div class="col-lg-9">
          <div class="d-flex justify-content-between align-items-center flex-column flex-md-row mb-4">
            <div class="me-3">
              <p class="mb-3 mb-md-0"><strong><c:out value="${hotels== null ? 0 : hotels.size()}"/></strong> results found</p>
            </div>
            
          </div>
          <div class="row">
            <!-- place item-->
 			<c:forEach var="hotel" items="${hotels}">
	 			<div class="col-sm-6 col-xl-4 mb-5 hover-animate">
	              <div class="card h-100 border-0 shadow">
	                <div class="card-img-top overflow-hidden gradient-overlay"> 
	                	<img class="img-fluid" src="${hotel.images.get(0)}" alt="${hotel.name}"/>
	                	<a class="tile-link" href="/ProvisioHotels/hotels/${hotel.name.toLowerCase().replace(' ', '-')}?checkin=${param.checkIn}&checkout=${param.checkOut}&roomtype=${param.roomType}"></a>
	                  	<div class="card-img-overlay-top text-end"><a class="card-fav-icon position-relative z-index-40" href="javascript: void();"> 
	                      <svg class="svg-icon text-white">
	                        <use xlink:href="#heart-1"> </use>
	                      </svg></a>
	                  	</div>
	                </div>
	                <div class="card-body d-flex align-items-center">
	                  <div class="w-100">
	                    <h6 class="card-title"><a class="text-decoration-none text-dark" href="/ProvisioHotels/hotels/${hotel.name.toLowerCase().replace(' ', '-')}"><c:out value="${hotel.name}"/></a></h6>
	                    <div class="d-flex card-subtitle mb-3">
	                      <p class="flex-grow-1 mb-0 text-muted text-sm"><c:out value="${hotel.location}"/></p>	
	                      <p class="flex-shrink-1 mb-0 card-stars text-xs text-end">
		                      <c:forEach var="i" begin = "1" end = "5">
		                        	<c:if test="${i <= hotel.rate}">
		                        		<i class="fa fa-star text-warning"></i>
		                        	</c:if>
		                        	<c:if test="${i > hotel.rate}">
		                        		<i class="fa fa-star text-gray-300"></i>
		                        	</c:if>
		                      </c:forEach>
	                      </p>
	                    </div>
	                    <p class="card-text text-muted"><span class="h4 text-primary">$<c:out value="${hotel.perNight}"/></span> per night</p>
	                  </div>
	                </div>
	              </div>
	            </div>
 			
 			</c:forEach>
          </div>
        </div>
      </div>
    </div>

</c:set>

<ext:base-page>

	<jsp:attribute name="title">Search Results</jsp:attribute>
	
	<jsp:attribute name="bodyStyle">margin-top: 72px;</jsp:attribute>
	
	<jsp:attribute name="header">
      <jsp:include page="common/header.jsp">
      		<jsp:param name="active_nav" value="contact" />
      </jsp:include>
    </jsp:attribute>
    
	<jsp:attribute name="footer">
      <jsp:include page="common/footer.jsp" />
    </jsp:attribute>
    
    <jsp:attribute name="scripts">
    
    <script src="${initParam.appUrl}/vendor/nouislider/nouislider.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"> </script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-date-range-picker/0.19.0/jquery.daterangepicker.min.js"></script>
    <script type="text/javascript">
	      var snapSlider = document.getElementById('slider-snap');
	      
	      noUiSlider.create(snapSlider, {
	          start: [ 100, 500 ],
	          snap: false,
	          connect: true,
	          step: 1,
	          range: {
	              'min': 100,
	              'max': 500
	          }
	      });
	      var snapValues = [
	          document.getElementById('slider-snap-value-from'),
	          document.getElementById('slider-snap-value-to')
	      ];
	      var inputValues = [
	          document.getElementById('slider-snap-input-from'),
	          document.getElementById('slider-snap-input-to')
	      ];
	      
	      snapSlider.noUiSlider.on('update', function( values, handle ) {
	          snapValues[handle].innerHTML = values[handle];
	          inputValues[handle].value = values[handle];
      	  });
      
      		var singleMonth = false;
		    if ($(window).width() < 767) {
		        singleMonth = true;
		    }
	
		    var dateRangeConfig = {
		        autoClose: true,
		        startDate: new Date(),
		        selectForward: true,
		        applyBtnClass: "btn btn-primary",
		        container: ".datepicker-container",
		        inline: true,
		        singleMonth: singleMonth,
		        showDateFilter: function (time, date) {
		            return (
		                '<div style="padding:5px;">\
		                            <span style="">' +
		                date +
		                "</span>\
		                        </div>"
		            );
		        },		       
		        customOpenAnimation: function (cb) {
		            $(this).fadeIn(300, cb);
		        },
		        customCloseAnimation: function (cb) {
		            $(this).fadeOut(300, cb);
		        },
		    };
	    
			$(document).ready(function () {
	    	
	    		$('#dateRange').dateRangePicker(dateRangeConfig);
	    		
	    	});
      
    </script>
    
    </jsp:attribute>
    
	<jsp:body>
        ${bodyContent}
    </jsp:body>
    
</ext:base-page>