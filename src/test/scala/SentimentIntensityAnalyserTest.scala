import org.scalatest.{FlatSpec, Matchers}
import net.github.ziyasal.sentiment.SentimentIntensityAnalyzer

class SentimentIntensityAnalyserTest extends FlatSpec with Matchers {

  "A SentimentIntensityAnalyzer" should "calculate polarity scores" in {
    val analyzer = new SentimentIntensityAnalyzer

    val standardGoodTest = analyzer.polarityScores("VADER is smart, handsome, and funny.")

    standardGoodTest.negative shouldEqual 0
    standardGoodTest.neutral shouldEqual 0.254
    standardGoodTest.positive shouldEqual 0.746
    standardGoodTest.compound shouldEqual 0.8316

    val kindOfTest = analyzer.polarityScores("The book was kind of good.")

    kindOfTest.negative shouldEqual 0
    kindOfTest.neutral shouldEqual 0.657
    kindOfTest.positive shouldEqual 0.343
    kindOfTest.compound shouldEqual 0.3832


    val complexTest = analyzer.polarityScores("The plot was good, but the characters are uncompelling and the dialog is not great.")
    complexTest.negative shouldEqual 0.327
    complexTest.neutral shouldEqual 0.579
    complexTest.positive shouldEqual 0.094
    complexTest.compound shouldEqual -0.7042
  }

}
