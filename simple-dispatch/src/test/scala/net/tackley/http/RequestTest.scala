package net.tackley.http

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.mock.MockitoSugar
import javax.servlet.http.HttpServletRequest
import org.scalatest.{FlatSpec}
import org.mockito.Mockito._

class RequestTest extends FlatSpec with ShouldMatchers with MockitoSugar {
  val servletRequest = mock[HttpServletRequest]

  "A Request" should "return the stringPath ready for split matching" in {

    when(servletRequest.getServletPath).thenReturn(
      "/some/path/index.html",
      "/index.html",
      "/",
      "",
      "/some/other/",
      "this/may/possibly/happen")

    new Request(servletRequest).path should be(List("some", "path", "index.html"))
    new Request(servletRequest).path should be(List("index.html"))
    new Request(servletRequest).path should be(Nil)
    new Request(servletRequest).path should be(Nil)
    new Request(servletRequest).path should be(List("some", "other"))
    new Request(servletRequest).path should be(List("this", "may", "possibly", "happen"))
  }
}
