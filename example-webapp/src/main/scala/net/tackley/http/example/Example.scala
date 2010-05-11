package net.tackley.http.example

import net.tackley.http._

object AsInt {
  def unapply(s: String) = {
    try {
      Some(s.toInt)
    } catch {
      case _ : NumberFormatException => None
    }
  }
}

class UserDispatcher extends Dispatcher {
  def dispatch(request: Request): Option[Response] = {
    request match {
      case Path(_, AsInt(id), "show") => Some(TextResponse("I will now show user id " + id))
      case other => println("no userDispatcher match for " + other); None
    }
  }
}

class RootDispatcher extends Dispatcher {
  val userDispatch = new UserDispatcher

  def dispatch(request: Request): Option[Response] = {
    request match {
      case Path("example", _*) =>
        println("hello from RootDispatcher")
        Some(TextResponse("hello from RootDispatcher!!: url was "+ request.url + " and path " + request.path))
      case Path("user", tail @ _*) =>
        println("processing user request...")
        userDispatch.dispatch(request)
      case other =>
        println("no match for " + other)
        None
    }
  }
}

class ExampleFilter extends DispatcherFilter {
  val dispatcher = new RootDispatcher
}
