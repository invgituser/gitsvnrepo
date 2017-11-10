package com.invessence.web.bean.custody;

import javax.faces.bean.*;

import com.invessence.web.service.aggr.AggregationService;
import com.invessence.web.service.custody.CustodyService;

/**
 * Created by abhangp on 11/10/2017.
 */
@ManagedBean(name = "uobCustodyBean")
@SessionScoped
public class UOBCustodyBean
{

   @ManagedProperty("#{uobCustodyService}")
   private CustodyService custodyService;

}
