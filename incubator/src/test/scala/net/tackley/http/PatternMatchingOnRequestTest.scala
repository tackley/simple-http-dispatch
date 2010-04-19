package net.tackley.http

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class RequestLikeThing(val unmatchedPath: String) {
}

object Path {
  def unapplySeq(r: RequestLikeThing) =
    Some(r.unmatchedPath.split('/').toList)
}

class PatternMatchingOnRequestTest extends FunSuite with ShouldMatchers {
  test("this is some test") {
    val r = new RequestLikeThing("one/two/three")

    r match {
      case Path("one", tail @ _*) => println("matched, tail: " + tail )
      case _ => println("no match")
    }

    val s = "tackley.net"
    s match {
      case Domain("net", "tackley") => println("match")
      case _ => println("no match")
    }
}
}

object Domain {

    // The injection method (optional)
    def apply(parts: String*): String =
      parts.reverse.mkString(".")

    // The extraction method (mandatory)
    def unapplySeq(whole: String): Option[Seq[String]] =
      Some(whole.split("\\.").reverse)
  }
