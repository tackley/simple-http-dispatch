package net.tackley.http

import javax.servlet.http.HttpServletRequest

/**
 * A simplified, clarified and scalafied equivalent of
 * HttpServletRequest
 */
class Request(r: HttpServletRequest, val path: List[String]) {
  def this(r: HttpServletRequest) = {
    this(r, r.getServletPath.split('/').toList match {
      case Nil => Nil
      // this is the case when the path starts with a leading /
      case "" :: tail => tail
      // and this one when not...
      case other => other
    })
  }

  /**
   * The full url invoked, up to but not including a ?
   *
   * "The returned URL contains a protocol, server name, port
   * number, and server stringPath, but it does not include query
   * string parameters."
   */
  def url = r.getRequestURL.toString


  /**
   * The stringPath invoked within this webapp.
   *
   * This excludes what servlet-api calls "the context"
   * (or what I prefer to call "the name of your war").
   *
   * It also excludes any query strings.
   *
   */
  def stringPath = r.getServletPath

  def rebase(newPath: List[String]) = new Request(r, newPath)

}


