export type AnalysisType = 'CRIME' | 'JOURNAL'

export interface SearchRequest {
  query: string
  limit: number
}

export interface SearchResult {
  id: string
  title: string
  url: string
  snippet: string
  source: string
}

export interface CrawlRequest {
  urls: string[]
}

export interface CrawlResponse {
  totalPages: number
  successfulPages: number
  failedPages: number
  processingTime: number
}

export interface AnalysisRequest {
  query: string
  limit: number
  analysisType: AnalysisType
}

export interface ExtractedItem {
  name: string
}

export interface RankedFeature {
  featureName: string
  frequency: number
  percentage: number
  rank: number
}

export interface RankedHeading {
  heading: string
  frequency: number
  percentage: number
  rank: number
}

export interface AnalysisResponse {
  analysisType: AnalysisType
  totalDocuments: number
  documentsAnalyzed: number
  features: ExtractedItem[]
  headings: ExtractedItem[]
  rankedFeatures: RankedFeature[]
  rankedHeadings: RankedHeading[]
  summary: string
  processingTime: number
}
