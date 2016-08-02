package com.invessence.web.bean.custody;

import java.io.Serializable;
import javax.faces.bean.*;

import com.invessence.web.data.custody.Custody;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/1/16
 * Time: 9:52 AM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "tdameritrade")
@SessionScoped
public class TDAmeritrade extends Custody implements Serializable
{
}
