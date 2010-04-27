package net.tackley.http

import javax.servlet.http.HttpServletResponse

/**
 * Response to a Request.
 *
 * Note that HttpServletRequest is a "collecting" class, of which one copy
 * exists and is passed around the place for people for call methods on to modify.
 *
 * This class is quite different: dispatchers are expected to instantiate their
 * own (probably immutable) copy.
 *
 */
trait Response {
  def applyTo(r: HttpServletResponse)
}


case class TextResponse(t: String) extends Response {
  def applyTo(r: HttpServletResponse) = {
    r.setContentType("text/plain")
    r.getWriter.append(t)
  }
}
