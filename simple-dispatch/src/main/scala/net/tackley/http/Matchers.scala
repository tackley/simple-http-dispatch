package net.tackley.http


object Path {
  def unapplySeq(r: Request) = Some(
    r.path.split('/').toList match {
      case Nil => Nil
      // this is the case when the path starts with a leading /
      case "" :: tail => tail
      // and this one when not...
      case other => other
    }
  )
}