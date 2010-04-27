package net.tackley.http

import javax.servlet._
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

abstract class DispatcherFilter extends Filter {
  def init(config: FilterConfig) {}

  def doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, chain: FilterChain) {
    val httpRequest = servletRequest.asInstanceOf[HttpServletRequest]
    val httpResponse = servletResponse.asInstanceOf[HttpServletResponse]

    dispatcher.dispatch(new Request(httpRequest)) match {
      case Some(response) => response.applyTo(httpResponse)
      case None => chain.doFilter(servletRequest, servletResponse)
    }
  }

  def destroy() {}

  def dispatcher: Dispatcher
}


