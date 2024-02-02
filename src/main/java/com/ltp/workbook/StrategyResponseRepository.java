package com.ltp.workbook;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StrategyResponseRepository extends MongoRepository<StrategyResponse, String> {
    @Aggregation(pipeline = {
            "{$match: {symbol: ?0}}",
            "{$sort: {searchKey: -1}}",
            "{$limit: 1}",
            "{$project: {_id: 0, symbol: 1, newsSummary: 1, " +
                    "isPositiveTrend: {$regexMatch: {input: '$newsSummary', regex: ?1, options: 'i'}}, " +
                    "isNegativeTrend: {$regexMatch: {input: '$newsSummary', regex: ?2, options: 'i'}}}}",
            "{$project: {symbol: 1, newsSummary: 1, " +
                    "marketTrend: {$cond: {if: {$eq: ['$isPositiveTrend', true]}, then: 'Positive', " +
                    "else: {$cond: {if: {$eq: ['$isNegativeTrend', true]}, then: 'Negative', else: 'Neutral'}}}}}}"
    })
    MarketTrend calculateMarketTrend(String symbol, String positiveRegex, String negativeRegex);

    @Aggregation(pipeline = {
            "{$match: { symbol: ?0 }}",
            "{$sort: {searchKey: -1}}",
            "{$limit: 14}",
            "{$project: {searchKey: 1, priceChange: {$subtract: ['$currentPrice', '$openingPrice']}}}",
            "{$group: {_id: null, " +
                    "  averageGain: {$avg: {$cond: [{$gt: ['$priceChange', 0]}, '$priceChange', 0]}}, " +
                    "  averageLoss: {$avg: {$cond: [{$lt: ['$priceChange', 0]}, {$abs: '$priceChange'}, 0]}}" +
                    "}}",
            "{$project: {_id: 0, RS: {$cond: {if: {$eq: ['$averageLoss', 0]}, then: 'Infinity', else: {$divide: ['$averageGain', '$averageLoss']}}}}}",
            "{$project: {RSI: {$cond: {if: {$eq: ['$RS', 'Infinity']}, then: 100, else: {$subtract: [100, {$divide: [100, {$add: [1, '$RS']}] }]}}}}}"
    })
    RSI calculateRsi(String symbol);
}
