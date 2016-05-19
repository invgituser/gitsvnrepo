package com.invessence.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/18/14
 * Time: 12:26 AM
 * To change this template use File | Settings | File Templates.
 */

@FacesValidator(value="AllocationValidator")
public class AllocationValidator  implements Validator
{


      public AllocationValidator(){}

      @Override
      public void validate(FacesContext context, UIComponent component,
                           Object value) throws ValidatorException {

         if (value != null) {
            if  ((Double) value != 100.00) {
               FacesMessage msg =
                  new FacesMessage("Asset Allocation not complete.",
                                   "Asset Allocation must equal to 100%");
               msg.setSeverity(FacesMessage.SEVERITY_ERROR);
               throw new ValidatorException(msg);
            }

         }
      }
}
