package net.tackley.http.example

import net.tackley.http._

class UserDispatcher extends Dispatcher {
  def dispatch(request: Request): Option[Response] = {
    request.path match {
      case id :: "show" :: Nil => Some(TextResponse("I will now show user id " + id))
      case other => println("no userDispatcher match for " + other); None
    }
  }
}

class RootDispatcher extends Dispatcher {
  val userDispatch = new UserDispatcher

  def dispatch(request: Request): Option[Response] = {
    request.path match {
      case "example" :: tail =>
        println("hello from RootDispatcher")
        Some(TextResponse("hello from RootDispatcher!!: url was "+ request.url + " and stringPath " + request.stringPath))
      case "user" :: tail =>
        println("processing user request...")
        userDispatch.dispatch(request rebase tail)
      case other =>
        println("no match for " + other)
        None
    }
  }
}

class ExampleFilter extends DispatcherFilter {
  val dispatcher = new RootDispatcher
}
