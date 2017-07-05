# Sentiment
Sentiment analysis using VADER with Scala

### Usage

```scala
import Sentiment._

object Program {
  def main(args: Array[String]): Unit = {
  
    val analyzer: SentimentIntensityAnalyzer = new SentimentIntensityAnalyzer

    val results = analyzer.polarityScores("The party was good but appetizers and drinks were poorly selected.")

    println(s"Positive score: $results.positive")
    println(s"Negative score: $results.negative")
    println(s"Neutral score:  $results.neutral")
    println(s"Compound score: $results.compound")
  }
}
```

@z i λ a s a l
