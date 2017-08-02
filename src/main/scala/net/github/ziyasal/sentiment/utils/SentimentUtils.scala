package net.github.ziyasal.sentiment.utils


private[sentiment] object SentimentUtils {

  val BIncr: Double = 0.293
  val BDecr: Double = -0.293
  val CIncr: Double = 0.733
  val NScalar: Double = -0.74

  val puncList = List(
    ".", "!", "?", ",", ";", ":", "-", "'", "\"", "!!", "!!!",
    "??", "???", "?!?", "!?!", "?!?!", "!?!?"
  )

  val negate = List(
    "aint", "arent", "cannot", "cant", "couldnt", "darent", "didnt", "doesnt",
    "ain't", "aren't", "can't", "couldn't", "daren't", "didn't", "doesn't",
    "dont", "hadnt", "hasnt", "havent", "isnt", "mightnt", "mustnt", "neither",
    "don't", "hadn't", "hasn't", "haven't", "isn't", "mightn't", "mustn't",
    "neednt", "needn't", "never", "none", "nope", "nor", "not", "nothing", "nowhere",
    "oughtnt", "shant", "shouldnt", "uhuh", "wasnt", "werent",
    "oughtn't", "shan't", "shouldn't", "uh-uh", "wasn't", "weren't",
    "without", "wont", "wouldnt", "won't", "wouldn't", "rarely", "seldom", "despite"
  )

  val boosterDict = Map[String, Double](
    "absolutely" -> BIncr,
    "amazingly" -> BIncr,
    "awfully" -> BIncr,
    "completely" -> BIncr,
    "considerably" -> BIncr,
    "decidedly" -> BIncr,
    "deeply" -> BIncr,
    "effing" -> BIncr,
    "enormously" -> BIncr,
    "entirely" -> BIncr,
    "especially" -> BIncr,
    "exceptionally" -> BIncr,
    "extremely" -> BIncr,
    "fabulously" -> BIncr,
    "flipping" -> BIncr,
    "flippin" -> BIncr,
    "fricking" -> BIncr,
    "frickin" -> BIncr,
    "frigging" -> BIncr,
    "friggin" -> BIncr,
    "fully" -> BIncr,
    "fucking" -> BIncr,
    "greatly" -> BIncr,
    "hella" -> BIncr,
    "highly" -> BIncr,
    "hugely" -> BIncr,
    "incredibly" -> BIncr,
    "intensely" -> BIncr,
    "majorly" -> BIncr,
    "more" -> BIncr,
    "most" -> BIncr,
    "particularly" -> BIncr,
    "purely" -> BIncr,
    "quite" -> BIncr,
    "really" -> BIncr,
    "remarkably" -> BIncr,
    "so" -> BIncr,
    "substantially" -> BIncr,
    "thoroughly" -> BIncr,
    "totally" -> BIncr,
    "tremendously" -> BIncr,
    "uber" -> BIncr,
    "unbelievably" -> BIncr,
    "unusually" -> BIncr,
    "utterly" -> BIncr,
    "very" -> BIncr,
    "almost" -> BDecr,
    "barely" -> BDecr,
    "hardly" -> BDecr,
    "just enough" -> BDecr,
    "kind of" -> BDecr,
    "kinda" -> BDecr,
    "kindof" -> BDecr,
    "kind-of" -> BDecr,
    "less" -> BDecr,
    "little" -> BDecr,
    "marginally" -> BDecr,
    "occasionally" -> BDecr,
    "partly" -> BDecr,
    "scarcely" -> BDecr,
    "slightly" -> BDecr,
    "somewhat" -> BDecr,
    "sort of" -> BDecr,
    "sorta" -> BDecr,
    "sortof" -> BDecr,
    "sort-of" -> BDecr
  )

  val specialCaseIdioms = Map[String, Double](
    "the shit" -> 3,
    "the bomb" -> 3,
    "bad ass" -> 1.5,
    "yeah right" -> -2,
    "cut the mustard" -> 2,
    "kiss of death" -> -1.5,
    "hand to mouth" -> -2
  )


  /**
    * Determine if input contains negation words
    *
    * @param inputWords
    * @param includenT
    * @return
    */
  def negated(inputWords: List[String], includenT: Boolean = true): Boolean = {
    for (word <- negate) {
      if (inputWords.contains(word)) {
        return true
      }
    }

    for (word <- negate) {
      if (inputWords.contains(word)) {
        return true
      }
    }

    if (includenT) {
      for (word <- inputWords) {
        if (word.contains("n't")) {
          return true
        }
      }
    }

    if (inputWords.contains("least")) {
      val i = inputWords.indexOf("least")
      if (i > 0 && inputWords(i - 1) != "at") {
        return true
      }
    }

    false
  }

  /**
    * Normalizes score to be between -1 and 1
    *
    * @param score
    * @param alpha
    * @return
    */
  def normalize(score: Double, alpha: Double = 15): Double = {
    val normScore: Double = score / math.sqrt(score * score + alpha)

    if (normScore < -1.0) {
      -1.0
    }
    else if (normScore > 1.0) {
      1.0
    } else {

      normScore
    }
  }

  /**
    * Checks whether some but not all of words in input are ALL CAPS
    *
    * @param words
    * @return
    */
  def allCapDifferential(words: Seq[String]): Boolean = {
    var allCapWords: Int = 0

    for (word <- words) {
      if (isUpper(word)) {
        allCapWords += 1
      }
    }

    val capDifferential = words.size - allCapWords

    capDifferential > 0 && capDifferential < words.size
  }

  /**
    * Check if preceding words increase, decrease or negate the valence
    *
    * @param word
    * @param valence
    * @param isCapDiff
    * @return
    */
  def scalarIncDec(word: String, valence: Double, isCapDiff: Boolean): Double = {
    val wordLower: String = word.toLowerCase()
    if (!boosterDict.contains(wordLower)) {
      0.0
    } else {
      var scalar: Double = boosterDict(wordLower)
      if (valence < 0) {
        scalar *= -1
      }

      if (isUpper(word) && isCapDiff) {

        if (valence > 0) {
          scalar += CIncr
        } else {
          scalar += -CIncr
        }
      }

      scalar
    }
  }

  def isUpper(cs: String): Boolean = {
    cs.forall(c => Character.isUpperCase(c))
  }
}
