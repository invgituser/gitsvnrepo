package com.invessence.price.yahoo.quotes.fx;

import java.util.ArrayList;
import java.util.List;

import com.invessence.price.yahoo.Utils;
import com.invessence.price.yahoo.quotes.QuotesProperty;
import com.invessence.price.yahoo.quotes.QuotesRequest;

public class FxQuotesRequest
  extends QuotesRequest<FxQuote>
{
  public static final List<QuotesProperty> DEFAULT_PROPERTIES = new ArrayList();
  
  static
  {
    DEFAULT_PROPERTIES.add(QuotesProperty.Symbol);
    DEFAULT_PROPERTIES.add(QuotesProperty.LastTradePriceOnly);
  }
  
  public FxQuotesRequest(String query)
  {
    super(query, DEFAULT_PROPERTIES);
  }
  
  protected FxQuote parseCSVLine(String line)
  {
    String[] split = Utils.stripOverhead(line).split(",");
    if (split.length >= 2) {
      return new FxQuote(split[0], Utils.getBigDecimal(split[1]));
    }
    return null;
  }
}
