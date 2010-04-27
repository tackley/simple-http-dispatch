package net.tackley.http.example

import net.tackley.http._

class ExampleDispatcher extends Dispatcher {
  def dispatch(request: Request): Option[Response] = {
    request.splitPath match {
      case "example" :: tail =>
        println("hello from ExampleDispatcher")
        Some(TextResponse("hello from ExampleDispatcher: url was "+ request.url + " and path " + request.path))
      case other =>
        println("no match for " + other)
        None
    }
  }
}

class ExampleFilter extends DispatcherFilter {
  val dispatcher = new ExampleDispatcher
}
