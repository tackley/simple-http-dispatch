package net.tackley.http

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class PathTest extends FlatSpec with ShouldMatchers {
  class RequestWithFixedPath(override val path: String) extends Request(null)


  "Path" should "can be used to match a request in a sensible way" in {
    val r = new RequestWithFixedPath("/some/path/index.html")

    r match {
      case Path("some", "path", "index.html") => // expected
      case _ => fail("no match")
    }

    r match {
      case Path(first, path, index) =>
        first should be ("some")
        path should be ("path")
        index should be ("index.html")

      case _ => fail("match expected")
    }

    r match {
      case Path(_, _, "rubbish.html") => fail("match not expected")
      case Path(_, _, "index.html") => // expected
    }

    r match {
      case Path("pony") => fail
      case Path("pony", _*) => fail
      case Path("some", _*) => // expected
    }

    r match {
      case req @ Path("some", tail @ _*) =>
        tail should be (List("path", "index.html"))
        req should be theSameInstanceAs (r)
    }

  }

  it should "unapplySeq in a way that should work well in matching" in {

    val testCases = Map(
      "/some/path/index.html" -> List("some", "path", "index.html"),
      "/index.html" -> List("index.html"),
      "/" -> Nil,
      "" -> Nil,
      "/some/other/" -> List("some", "other"),
      "this/may/possibly/happen" -> List("this", "may", "possibly", "happen"))

    testCases.foreach { case (path, resultingList) =>
      Path.unapplySeq(new RequestWithFixedPath(path)) should be (Some(resultingList))
    }
  }




    //    when(servletRequest.getServletPath).thenReturn(
//      "/some/path/index.html",
//     ,
//      "/",
//      "",
//      "/some/other/",
//      "this/may/possibly/happen")
//
//    new Request(servletRequest).path should be()
//    new Request(servletRequest).path should be(List("index.html"))
//    new Request(servletRequest).path should be(Nil)
//    new Request(servletRequest).path should be(Nil)
//    new Request(servletRequest).path should be(List("some", "other"))
//    new Request(servletRequest).path should be(List("this", "may", "possibly", "happen"))
//
  
}