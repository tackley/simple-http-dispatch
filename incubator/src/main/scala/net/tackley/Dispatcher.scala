package net.tackley

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

/**
 * A simplified, clarified and scalafied equivalent of
 * HttpServletRequest
 */
class Request(r: HttpServletRequest) {

  /**
   * The full url invoked, upto but not including a ?
   *
   * "The returned URL contains a protocol, server name, port
   * number, and server path, but it does not include query
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


/**
 * Response to a Request.
 *
 * Note that HttpServletRequest is a "collecting" class, of which one copy
 * exists and is passed around the place for people for call methods on to modify.
 *
 * This class is quite different: dispactchers are expected to instatiate their
 * own (probably immutable) copy.
 *
 */
trait Response {
  def applyTo(r: HttpServletResponse)
}

trait Dispatcher {
  def dispatch(request: Request): Option[Response]
}

case class TextResponse(t: String) extends Response {
  def applyTo(r: HttpServletResponse) = {
    r.setContentType("text/plain")
    r.getWriter.append(t)
  }
}


class ExampleDispatcher extends Dispatcher {
  def dispatch(request: Request): Option[Response] = {
    if (request.path.startsWith("/example")) {
      println("hello from ExampleDispatcher")
      Some(TextResponse("hello from ExampleDispatcher: url was "+ request.url + " and path " + request.path))
    } else {
      None
    }
  }
}

