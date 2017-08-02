# Sentiment
Sentiment analysis using VADER with Scala

> **VADER** Sentiment Analysis. VADER (Valence Aware Dictionary and sEntiment Reasoner) is a lexicon and rule-based sentiment analysis tool that is specifically attuned to sentiments expressed in social media, and works well on texts from other domains.

### Usage

```scala
import Sentiment._

object Program {
  def main(args: Array[String]): Unit = {
  
    val analyzer = new SentimentIntensityAnalyzer
    
    val text = "The party was good but appetizers and drinks were poorly selected."
    
    val results = analyzer.polarityScores(text)

    println(s"Positive score: $results.positive")
    println(s"Negative score: $results.negative")
    println(s"Neutral score:  $results.neutral")
    println(s"Compound score: $results.compound")
  }
}
```

### TODO
* [ ] publish package
* [ ] sample usage in Flink jobs


### Credits
> Hutto, C.J. & Gilbert, E.E. (2014). VADER: A Parsimonious Rule-based Model for Sentiment Analysis of Social Media Text. Eighth International Conference on Weblogs and Social Media (ICWSM-14). Ann Arbor, MI, June 2014.

@z i λ a s a l
