package net.tackley

import javax.servlet._
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

abstract class DispatcherFilter extends Filter {
  def init(config: FilterConfig) {}

  def doFilter(request: ServletRequest, servletResponse: ServletResponse, chain: FilterChain) {
    val httpRequest = request.asInstanceOf[HttpServletRequest]
    val httpResponse = servletResponse.asInstanceOf[HttpServletResponse]

    val response = dispatcher.dispatch(new Request(httpRequest))

    if (response.isDefined) {
      response.foreach(_.applyTo(httpResponse))
    } else {
      chain.doFilter(request, servletResponse)
    }
  }

  def destroy() {}

  def dispatcher: Dispatcher
}


class ExampleFilter extends DispatcherFilter {
  val dispatcher = new ExampleDispatcher
}