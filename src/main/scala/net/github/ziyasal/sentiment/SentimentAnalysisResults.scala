package net.github.ziyasal.sentiment

case class SentimentAnalysisResults(negative: Double=0,
                                    neutral: Double=0,
                                    positive: Double=0,
                                    compound: Double=0)