/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.58
 * Generated at: 2022-05-13 23:09:33 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.provisio.utils.DateFormatUtil;
import com.provisio.models.Reservation;
import java.util.List;

public final class my_002dbookings_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/WEB-INF/tags/base-page.tag", Long.valueOf(1652480234255L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1652480237996L));
    _jspx_dependants.put("jar:file:/C:/apache-tomcat-9.0.58/webapps/Provisio/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ProvisioHotels/WEB-INF/lib/jstl-1.2.jar!/META-INF/c-1_0-rt.tld", Long.valueOf(1153403082000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.provisio.models.Reservation");
    _jspx_imports_classes.add("com.provisio.utils.DateFormatUtil");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.release();
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!-- Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo -->\r\n");
      out.write("<!-- User interface for: MyBooks aka reservations made page -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Show my bookings -->\r\n");
      //  c:set
      org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
      boolean _jspx_th_c_005fset_005f0_reused = false;
      try {
        _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
        _jspx_th_c_005fset_005f0.setParent(null);
        // /my-bookings.jsp(13,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_c_005fset_005f0.setVar("bodyContent");
        int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
        if (_jspx_eval_c_005fset_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          if (_jspx_eval_c_005fset_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = org.apache.jasper.runtime.JspRuntimeLibrary.startBufferedBody(_jspx_page_context, _jspx_th_c_005fset_005f0);
          }
          do {
            out.write("\r\n");
            out.write("\r\n");
            out.write("	");

	
		List<Reservation> userReservations	= (List<Reservation>)request.getAttribute("userReservations");
	
	
            out.write("\r\n");
            out.write("\r\n");
            out.write("	<section class=\"py-6 bg-gray-100\">\r\n");
            out.write("		<div class=\"container\">\r\n");
            out.write("        <div class=\"d-flex justify-content-between align-items-center flex-column flex-lg-row mb-5\">\r\n");
            out.write("          <div class=\"me-3\">\r\n");
            out.write("          	<h2>My bookings</h2>\r\n");
            out.write("            <p class=\"mb-3 mb-lg-0\">You have <strong>");
            out.print( userReservations.size() );
            out.write(" total bookings</strong></p>\r\n");
            out.write("          </div>\r\n");
            out.write("          \r\n");
            out.write("          <div class=\"row\">\r\n");
            out.write("         \r\n");
            out.write("	         	<div class=\"col-md-2\">\r\n");
            out.write("	         		<label class=\"form-label mt-2 float-end\" for=\"bkSearch\">Search</label>\r\n");
            out.write("	         	</div>\r\n");
            out.write("	         	\r\n");
            out.write("	         	<div class=\"col-md-5\">\r\n");
            out.write("	         		<input class=\"form-control\" id=\"bkSearch\" type=\"text\" aria-describedby=\"inputSt\" placeholder=\"Booking ID here ...\"/>\r\n");
            out.write("	         	</div>\r\n");
            out.write("	         	\r\n");
            out.write("	         	<div class=\"col-md-5\">\r\n");
            out.write("	         		 <button class=\"btn btn btn-outline-primary\">\r\n");
            out.write("	            		<span class=\"d-none d-sm-inline\">Loyalty Points: ");
            if (_jspx_meth_c_005fout_005f0(_jspx_th_c_005fset_005f0, _jspx_page_context))
              return;
            out.write("</span>\r\n");
            out.write("	            	</button>\r\n");
            out.write("	         	</div>\r\n");
            out.write("         	\r\n");
            out.write("          	</div>\r\n");
            out.write("         \r\n");
            out.write("        </div>\r\n");
            out.write("        <div class=\"list-group shadow mb-5\">\r\n");
            out.write("        	");
 for(Reservation res : userReservations) { 
            out.write("\r\n");
            out.write("        	\r\n");
            out.write("		        <a class=\"list-group-item list-group-item-action p-4\" href=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${initParam.appUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("/view-reservation/");
            out.print( res.getCode());
            out.write("\">\r\n");
            out.write("		            <div class=\"row\">\r\n");
            out.write("		              <div class=\"col-lg-4 align-self-center mb-4 mb-lg-0\">\r\n");
            out.write("		                <div class=\"d-flex align-items-center mb-3\">\r\n");
            out.write("		                  <h2 class=\"h5 mb-0\">");
            out.print( res.getHotelRoom().getHotel().getName() );
            out.write("</h2>\r\n");
            out.write("		                </div>\r\n");
            out.write("		                <p class=\"text-sm text-muted\">");
            out.print( res.getHotelRoom().getRoom().getType() );
            out.write("</p><span class=\"badge badge-pill p-2 badge-secondary-light\">\r\n");
            out.write("		                	");
            out.print( DateFormatUtil.format(res.getCheckin(), "MM-dd-yyyy")+" through "+ DateFormatUtil.format(res.getCheckout(), "MM-dd-yyyy") );
            out.write("\r\n");
            out.write("		                </span>\r\n");
            out.write("		              </div>\r\n");
            out.write("		              <div class=\"col-lg-8\">\r\n");
            out.write("		                <div class=\"row\">\r\n");
            out.write("		                  <div class=\"col-6 col-md-4 col-lg-3 py-3 mb-3 mb-lg-0\">\r\n");
            out.write("		                    <h6 class=\"label-heading\">Nights </h6>\r\n");
            out.write("		                    <p class=\"text-sm fw-bold\">");
            out.print( res.getNights() );
            out.write("</p>\r\n");
            out.write("		                    <h6 class=\"label-heading\">Occupancy</h6>\r\n");
            out.write("		                    <p class=\"text-sm fw-bold mb-0\">");
            out.print( res.getGuests() );
            out.write("</p>\r\n");
            out.write("		                  </div>\r\n");
            out.write("		                  <div class=\"col-6 col-md-4 col-lg-3 py-3\">		                   \r\n");
            out.write("		                    <h6 class=\"label-heading\">Charge</h6>\r\n");
            out.write("		                    <p class=\"text-sm fw-bold\">$");
            out.print( res.getTotal() );
            out.write("</p>\r\n");
            out.write("		                    <h6 class=\"label-heading\">Booked Date</h6>\r\n");
            out.write("		                    <p class=\"text-sm fw-bold mb-0\">");
            out.print( DateFormatUtil.format(res.getBookedDate(), "MM-dd-yyyy") );
            out.write("</p>\r\n");
            out.write("		                  </div>\r\n");
            out.write("		                  <div class=\"col-12 col-lg-3 align-self-center\">\r\n");
            out.write("			                  <span class=\"text-primary text-sm text-uppercase\"><i class=\"fa fa-check fa-fw me-2\"> </i>Confirmed</span>\r\n");
            out.write("		                  </div>\r\n");
            out.write("				  <div class=\"col-6 col-md-4 col-lg-3 py-3 mb-3 mb-lg-0\">\r\n");
            out.write("									<h6 class=\"label-heading\">Loyalty Points Earned</h6>\r\n");
            out.write("									<p class=\"text-sm fw-bold\">");
            out.print((res.getNights()*150));
            out.write("</p>\r\n");
            out.write("				</div>							\r\n");
            out.write("		                </div>\r\n");
            out.write("		              </div>\r\n");
            out.write("		            </div>\r\n");
            out.write("		         </a>\r\n");
            out.write("        	\r\n");
            out.write("        	");
 } 
            out.write("\r\n");
            out.write("        </div>\r\n");
            out.write("      </div>\r\n");
            out.write("	</section>\r\n");
            out.write("\r\n");
            int evalDoAfterBody = _jspx_th_c_005fset_005f0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_c_005fset_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.popBody();
          }
        }
        if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return;
        }
        _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.reuse(_jspx_th_c_005fset_005f0);
        _jspx_th_c_005fset_005f0_reused = true;
      } finally {
        org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fset_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fset_005f0_reused);
      }
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_ext_005fbase_002dpage_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fset_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    boolean _jspx_th_c_005fout_005f0_reused = false;
    try {
      _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fset_005f0);
      // /my-bookings.jsp(41,64) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loyaltyPoints.getLoyaltyPoints()}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
      if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      _jspx_th_c_005fout_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fout_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fout_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_ext_005fbase_002dpage_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)_jspx_page_context.getRequest();
    javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)_jspx_page_context.getResponse();
    //  ext:base-page
    org.apache.jsp.tag.web.base_002dpage_tag _jspx_th_ext_005fbase_002dpage_005f0 = new org.apache.jsp.tag.web.base_002dpage_tag();
    _jsp_getInstanceManager().newInstance(_jspx_th_ext_005fbase_002dpage_005f0);
    try {
      _jspx_th_ext_005fbase_002dpage_005f0.setJspContext(_jspx_page_context);
      javax.servlet.jsp.tagext.JspFragment _jspx_temp0 = new Helper( 0, _jspx_page_context, _jspx_th_ext_005fbase_002dpage_005f0, null);
      // /my-bookings.jsp(95,0) name = title type = null reqTime = true required = false fragment = true deferredValue = false expectedTypeName = java.lang.String deferredMethod = false methodSignature = null
      _jspx_th_ext_005fbase_002dpage_005f0.setTitle(_jspx_temp0);
      java.lang.String _jspx_temp1 = "margin-top: 72px;";
      // /my-bookings.jsp(95,0) name = bodyStyle type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = java.lang.String deferredMethod = false methodSignature = null
      _jspx_th_ext_005fbase_002dpage_005f0.setBodyStyle(_jspx_temp1);
      javax.servlet.jsp.tagext.JspFragment _jspx_temp2 = new Helper( 1, _jspx_page_context, _jspx_th_ext_005fbase_002dpage_005f0, null);
      // /my-bookings.jsp(95,0) name = header type = null reqTime = true required = false fragment = true deferredValue = false expectedTypeName = java.lang.String deferredMethod = false methodSignature = null
      _jspx_th_ext_005fbase_002dpage_005f0.setHeader(_jspx_temp2);
      javax.servlet.jsp.tagext.JspFragment _jspx_temp3 = new Helper( 2, _jspx_page_context, _jspx_th_ext_005fbase_002dpage_005f0, null);
      // /my-bookings.jsp(95,0) name = footer type = null reqTime = true required = false fragment = true deferredValue = false expectedTypeName = java.lang.String deferredMethod = false methodSignature = null
      _jspx_th_ext_005fbase_002dpage_005f0.setFooter(_jspx_temp3);
      javax.servlet.jsp.tagext.JspFragment _jspx_temp4 = new Helper( 3, _jspx_page_context, _jspx_th_ext_005fbase_002dpage_005f0, null);
      // /my-bookings.jsp(95,0) name = scripts type = null reqTime = true required = false fragment = true deferredValue = false expectedTypeName = java.lang.String deferredMethod = false methodSignature = null
      _jspx_th_ext_005fbase_002dpage_005f0.setScripts(_jspx_temp4);
      _jspx_th_ext_005fbase_002dpage_005f0.setJspBody(new Helper( 4, _jspx_page_context, _jspx_th_ext_005fbase_002dpage_005f0, null));
      _jspx_th_ext_005fbase_002dpage_005f0.doTag();
    } finally {
      _jsp_getInstanceManager().destroyInstance(_jspx_th_ext_005fbase_002dpage_005f0);
    }
    return false;
  }

  private class Helper
      extends org.apache.jasper.runtime.JspFragmentHelper
  {
    private javax.servlet.jsp.tagext.JspTag _jspx_parent;
    private int[] _jspx_push_body_count;

    public Helper( int discriminator, javax.servlet.jsp.JspContext jspContext, javax.servlet.jsp.tagext.JspTag _jspx_parent, int[] _jspx_push_body_count ) {
      super( discriminator, jspContext, _jspx_parent );
      this._jspx_parent = _jspx_parent;
      this._jspx_push_body_count = _jspx_push_body_count;
    }
    public boolean invoke0( javax.servlet.jsp.JspWriter out ) 
      throws java.lang.Throwable
    {
      out.write("My Bookings");
      return false;
    }
    public boolean invoke1( javax.servlet.jsp.JspWriter out ) 
      throws java.lang.Throwable
    {
      javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)_jspx_page_context.getRequest();
      javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)_jspx_page_context.getResponse();
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "common/header.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("active_nav", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("", request.getCharacterEncoding()), out, false);
      return false;
    }
    public boolean invoke2( javax.servlet.jsp.JspWriter out ) 
      throws java.lang.Throwable
    {
      javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)_jspx_page_context.getRequest();
      javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse)_jspx_page_context.getResponse();
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "common/footer.jsp", out, false);
      return false;
    }
    public boolean invoke3( javax.servlet.jsp.JspWriter out ) 
      throws java.lang.Throwable
    {
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("    	 \r\n");
      out.write("    	 $(document).ready(function() {\r\n");
      out.write("    		 \r\n");
      out.write("    		 $('#bkSearch').on('keyup', function() {\r\n");
      out.write("    				\r\n");
      out.write("    				 var val = $('#bkSearch').val().trim();\r\n");
      out.write("    				 \r\n");
      out.write("    				 $.ajax({\r\n");
      out.write("						url : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${initParam.appUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/filter-reservations\",\r\n");
      out.write("						type : 'GET',\r\n");
      out.write("						data : {'rev_code': val},\r\n");
      out.write("						dataType : \"json\",\r\n");
      out.write("						success: function(success) {\r\n");
      out.write("							\r\n");
      out.write("							$('.list-group').empty();\r\n");
      out.write("							\r\n");
      out.write("							var html = \"\";\r\n");
      out.write("							\r\n");
      out.write("							var appURL = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${initParam.appUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/view-reservation/\";\r\n");
      out.write("							\r\n");
      out.write("							$.each(success, function(k, v) {\r\n");
      out.write("								\r\n");
      out.write("								html+= '<a class=\"list-group-item list-group-item-action p-4\" href=\"'+appURL+v.code+'\">'\r\n");
      out.write("								html+= '<div class=\"row\">'\r\n");
      out.write("								html+= '<div class=\"col-lg-4 align-self-center mb-4 mb-lg-0\">'\r\n");
      out.write("								html+= '<div class=\"d-flex align-items-center mb-3\">'\r\n");
      out.write("								html+= '<h2 class=\"h5 mb-0\">'+v.hotelRoom.hotel.name+'</h2>'\r\n");
      out.write("								html+= '</div>'\r\n");
      out.write("								html+= '<p class=\"text-sm text-muted\">'+v.hotelRoom.room.type+'</p><span class=\"badge badge-pill p-2 badge-secondary-light\">'+v.checkin+' - '+v.checkout+'</span>'\r\n");
      out.write("								html+= '</div>'\r\n");
      out.write("								html+= '<div class=\"col-lg-8\">'\r\n");
      out.write("								html+= '<div class=\"row\">'\r\n");
      out.write("								html+= '<div class=\"col-6 col-md-4 col-lg-3 py-3 mb-3 mb-lg-0\">'\r\n");
      out.write("								html+= '<h6 class=\"label-heading\">Nights </h6>'\r\n");
      out.write("								html+= '<p class=\"text-sm fw-bold\">'+v.nights+'</p>'\r\n");
      out.write("								html+= '<h6 class=\"label-heading\">Occupancy</h6>'\r\n");
      out.write("								html+= '<p class=\"text-sm fw-bold mb-0\">'+v.guests+'</p>'\r\n");
      out.write("								html+= '</div>'\r\n");
      out.write("								html+= '<div class=\"col-6 col-md-4 col-lg-3 py-3\">'		                   \r\n");
      out.write("								html+=  '<h6 class=\"label-heading\">Charge</h6>'\r\n");
      out.write("								html+= '<p class=\"text-sm fw-bold\">$'+v.total+'</p>'\r\n");
      out.write("								html+= '<h6 class=\"label-heading\">Booked Date</h6>'\r\n");
      out.write("								html+= '<p class=\"text-sm fw-bold mb-0\">'+v.bookedDate+'</p>'\r\n");
      out.write("								html+= '</div>'\r\n");
      out.write("								html+= '<div class=\"col-12 col-lg-3 align-self-center\">'\r\n");
      out.write("						        html+= '<span class=\"text-primary text-sm text-uppercase\"><i class=\"fa fa-check fa-fw me-2\"> </i>Confirmed</span>'\r\n");
      out.write("						        html+= ' </div>'\r\n");
      out.write("						        html+= ' </div>'\r\n");
      out.write("						        html+= ' </div>'\r\n");
      out.write("						        html+= '  </div>'\r\n");
      out.write("						        html+= ' </a>';\r\n");
      out.write("								\r\n");
      out.write("							});\r\n");
      out.write("							\r\n");
      out.write("							html = html ? html : '<p class=\"mb-3 mb-lg-0 text-center\">No data found !</p>'; \r\n");
      out.write("							\r\n");
      out.write("							$('.list-group').html(html);\r\n");
      out.write("						},\r\n");
      out.write("						error: function(error) {\r\n");
      out.write("							//console.log(error);\r\n");
      out.write("						}\r\n");
      out.write("    				 });	\r\n");
      out.write("    		 });\r\n");
      out.write("    		 \r\n");
      out.write("    	 });\r\n");
      out.write("    	 \r\n");
      out.write("    	 </script>");
      return false;
    }
    public boolean invoke4( javax.servlet.jsp.JspWriter out ) 
      throws java.lang.Throwable
    {
      out.write("\r\n");
      out.write("        ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bodyContent}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\r\n");
      out.write("    ");
      return false;
    }
    public void invoke( java.io.Writer writer )
      throws javax.servlet.jsp.JspException
    {
      javax.servlet.jsp.JspWriter out = null;
      if( writer != null ) {
        out = this.jspContext.pushBody(writer);
      } else {
        out = this.jspContext.getOut();
      }
      try {
        Object _jspx_saved_JspContext = this.jspContext.getELContext().getContext(javax.servlet.jsp.JspContext.class);
        this.jspContext.getELContext().putContext(javax.servlet.jsp.JspContext.class,this.jspContext);
        switch( this.discriminator ) {
          case 0:
            invoke0( out );
            break;
          case 1:
            invoke1( out );
            break;
          case 2:
            invoke2( out );
            break;
          case 3:
            invoke3( out );
            break;
          case 4:
            invoke4( out );
            break;
        }
        jspContext.getELContext().putContext(javax.servlet.jsp.JspContext.class,_jspx_saved_JspContext);
      }
      catch( java.lang.Throwable e ) {
        if (e instanceof javax.servlet.jsp.SkipPageException)
            throw (javax.servlet.jsp.SkipPageException) e;
        throw new javax.servlet.jsp.JspException( e );
      }
      finally {
        if( writer != null ) {
          this.jspContext.popBody();
        }
      }
    }
  }
}
