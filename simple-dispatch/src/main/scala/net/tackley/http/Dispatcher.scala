package net.tackley.http

trait Dispatcher {
  def dispatch(request: Request): Option[Response]
}



