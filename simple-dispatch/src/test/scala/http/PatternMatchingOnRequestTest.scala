package http


import org.scalatest.{FunSuite}
import org.scalatest.matchers.{ShouldMatchers}

class RequestLikeThing(val unmatchedPath: String) {
  def path = unmatchedPath.split('/').toList
}



object Path {
  def unapplySeq(r: RequestLikeThing) =
    Some(r.unmatchedPath.split('/').toList)
}

class PatternMatchingOnRequestTest extends FunSuite with ShouldMatchers {
//  implicit def r2path(r: RequestLikeThing) = r.path

  test("this is some test") {
    workaroundToScalaBug
  }

  def workaroundToScalaBug() {
    val r = new RequestLikeThing("one/two/three")

     r match {
       case Path("one", tail @ _*) => println("matched, tail: " + tail )
       case _ => println("no match")
     }

    r.path match {
      case "one" :: tail => println("matched, tail: "+ tail)
      case _ => println("no match")
    }

//    r match {
//      case "one" :: tail => println("matched, tail: "+ tail)
//      case _ => println("no match")
//    }
  }
}
