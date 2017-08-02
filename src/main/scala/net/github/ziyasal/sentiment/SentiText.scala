package net.github.ziyasal.sentiment

import net.github.ziyasal.sentiment.utils.SentimentUtils
import util.control.Breaks._

private[sentiment] class SentiText(val text: String) {

  val wordsAndEmoticons: Seq[String] = getWordsAndEmoticons()
  val isCapDifferential: Boolean = SentimentUtils.allCapDifferential(wordsAndEmoticons)

  /**
    * Returns mapping of the form
    * {'cat,': 'cat'},
    * {',cat': 'cat'}
    *
    * @return
    */
  def wordsPlusPunc(): Map[String, String] = {
    val noPuncText: String = removePunctuation(text)
    val wordsOnly = noPuncText.split(" ").filter(x => x.length > 1)

    //for each word in wordsOnly, get each possible variant of punclist before/after
    //Seems poor. It can be improved in future.
    var puncDic: Map[String, String] = Map[String, String]()

    breakable {
      for (word <- wordsOnly) {
        for (punc <- SentimentUtils.puncList) {
          if (puncDic.contains(word + punc)) {
            break
          }

          puncDic += (word + punc -> word)
          puncDic += (punc + word -> word)
        }
      }
    }

    puncDic
  }

  /**
    * Removes leading and trailing punctuation. Leaves contractions and most emoticons.
    *
    * @return
    */
  private def getWordsAndEmoticons(): List[String] = {
    var wes: List[String] = text.split(" ").filter(x => x.length > 1).toList
    val wordsPuncDic = wordsPlusPunc()

    for (i <- wes.indices) {
      if (wordsPuncDic.contains(wes(i))) {
        wes = wes.updated(i, wordsPuncDic(wes(i)))
      }
    }

    wes
  }

  /**
    * Removes punctuation from word
    */
  private def removePunctuation(text: String): String = text.filter(c => !SentimentUtils.puncList.contains(c.toString))

}
