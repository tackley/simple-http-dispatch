package net.tackley

import javax.servlet._
import javax.servlet.http.{HttpServletRequest}

class DispatcherFilter extends Filter {
  def init(config: FilterConfig) {}

  def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
    val r = request.asInstanceOf[HttpServletRequest]

    println("doFilter running! '"+ r.getContextPath + "'")

    println("requestUrl='" + r.getRequestURL + "'")
    println("requestUrI='" + r.getRequestURI + "'")
    println("getServletPath='" + r.getServletPath + "'")
    chain.doFilter(request, response)
  }

  def destroy() {}

}