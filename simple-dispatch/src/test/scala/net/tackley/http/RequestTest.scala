package net.tackley.http

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.mock.MockitoSugar
import javax.servlet.http.HttpServletRequest
import org.scalatest.{FlatSpec}
import org.mockito.Mockito._

class RequestTest extends FlatSpec with ShouldMatchers with MockitoSugar {
  val servletRequest = mock[HttpServletRequest]

  "A Request" should "return the paths as from servletRequest" in {
    when(servletRequest.getServletPath).thenReturn("/some/path")

    new Request(servletRequest).path should be ("/some/path")
  }
}
