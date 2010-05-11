package net.tackley.http

import javax.servlet.http.HttpServletRequest

/**
 * A simplified, clarified and scalafied equivalent of
 * HttpServletRequest
 */
class Request(r: HttpServletRequest) {
  

  /**
   * The full url invoked, up to but not including a ?
   *
   * "The returned URL contains a protocol, server name, port
   * number, and server stringPath, but it does not include query
   * string parameters."
   */
  def url = r.getRequestURL.toString


  /**
   * The path invoked within this webapp.
   *
   * This excludes what servlet-api calls "the context"
   * (or what I prefer to call "the name of your war").
   *
   * It also excludes any query strings.
   *
   */
  def path = r.getServletPath


}


