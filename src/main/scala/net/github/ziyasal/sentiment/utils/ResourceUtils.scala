package net.github.ziyasal.sentiment.utils

import java.io.InputStream


private[sentiment] object ResourceUtils {

  def readFileAsList(path: String): Seq[String] = {
    val stream: InputStream = getClass.getResourceAsStream(path)
    val lines: Iterator[String] = scala.io.Source.fromInputStream(stream).getLines

   lines.toList
  }
}
