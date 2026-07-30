import { api } from '../api/api'
import type {
  AnalysisRequest,
  AnalysisResponse,
  CrawlRequest,
  CrawlResponse,
  SearchRequest,
  SearchResult,
} from '../types/api'

export async function checkBackendStatus() {
  const { data } = await api.get<string>('/api/status')
  return data
}

export async function searchSERP(request: SearchRequest) {
  const { data } = await api.post<SearchResult[]>('/api/search', request)
  return data
}

export async function crawlUrls(request: CrawlRequest) {
  const { data } = await api.post<CrawlResponse>('/api/crawl', request)
  return data
}

export async function analyzeQuery(request: AnalysisRequest) {
  const { data } = await api.post<AnalysisResponse>('/api/analyze', request)
  return data
}
