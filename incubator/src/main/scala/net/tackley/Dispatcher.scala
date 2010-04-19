package net.tackley


trait SimpleRequest {
  def url: String
}

trait SimpleResponse {

}

trait Dispatcher {
  def dispatch: PartialFunction[SimpleRequest, SimpleResponse]
}


object UrlStartsWith {
  def unapply(r: SimpleRequest) = { println("path"); true }

}
class ExampleDispatcher extends Dispatcher {

  def dispatch: PartialFunction[SimpleRequest, SimpleResponse] = {
    case UrlStartsWith() => new AnyRef with SimpleResponse
  }
}

object Something {
//  def getADispatcher: Dispatcher = 
}