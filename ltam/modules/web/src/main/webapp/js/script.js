var html = document.getElementsByTagName('html')[0];
html.className = html.className.replace('no-js', '');
var mhComponents = [];
window.mh = window.mh || {};
mh.Page = {};
var menu_scroll_progress = false;

mhComponents.push({component: 'mh.view.marketing.HeaderMenu'},
    {component: 'mh.view.marketing.MarketingPage',options: {"autoOpenGetInTouchModal": true}},
    {component: 'mh.view.marketing.MarketingViewFinder'}
);

window.mh = mh || {};
mh.view = mh.view || {};

/**
 * Base class for all* views at MicroHealth. (*There are likely going to be exceptions.)
 * @class
 */
mh.view.BaseView = Backbone.View.extend({
  page: function() {
    return mh.Page;
  },
  clearClass: function(className) {
    var context = this.$el.length ? this.$el : undefined;
    mh.utils.clearClass(className, context);
  },
  get$byClass: function(className) {
    return this.$('.' + className);
  },
  get$byName: function(name) {
    return this.$(['[name=', name, ']'].join(''));
  },
  get$byId: function(id) {
    return this.$('#' + id);
  },
  get$byDataAttr: function(dataAttrId, dataAttrValue) {
    return this.$('[data-' + dataAttrId + '="' + dataAttrValue + '"]');
  },
  setTimeout: function(func, timeout) {
    return setTimeout(_.bind(func, this), timeout);
  },
  setInterval: function(func, interval) {
    return setInterval(_.bind(func, this), interval);
  }
});

mh.utils = mh.utils || {};
mh.utils.getNextIteration = function(currentIndex, numOfItems) {
  var currentIndex_ = !_.isFinite(currentIndex) ? -1 : currentIndex;

  return {
    'current': currentIndex_,
    'next': currentIndex_ >= numOfItems - 1 ? 0 : currentIndex_ + 1,
    'previous': currentIndex_ <= 0 ? numOfItems - 1 : currentIndex_ - 1
  };
};

mh.utils.smoothScrollToSelector = function(selector) {
  this.smoothScroll($(selector).first().offset().top);
};

mh.utils.smoothScroll = function(scrollTopTarget, optEl, optIgnoreHeader) {
  var $el = optEl || $('body, html');
  if (!optIgnoreHeader) {
    scrollTopTarget -= $('header').outerHeight();
  }

  var scrollTopWindow = $el.scrollTop();
  var distance = Math.abs(scrollTopTarget - scrollTopWindow);
  var duration = Math.min(1, distance / 700);

  $el.animate({scrollTop: scrollTopTarget}, 400 * duration);
};

mh.utils.getDeepReference = function(obj, traversal, defaultVal) {
  defaultVal = defaultVal || undefined;
  if (!_.isObject(obj)) {
    return defaultVal;
  }

  var traversalArr = traversal.split('.');
  var key = traversalArr.shift();

  if (!traversalArr.length) {
    return _.has(obj, key) ? obj[key] : defaultVal;
  }

  return mh.utils.getDeepReference(obj[key], traversalArr.join('.'), defaultVal);
};

mh.utils.urlFor = function(baseUrl, values) {
  var url = baseUrl;
  if (!_.isArray(values)) {
    values = [values];
  }
  _.each(values, function(val) {
    url = url.replace('%25s', val);
  });

  return url;
};

mh.utils.isSmallMode = function() {
  return $('html').width() <= '1024';
};

mh.utils.putCursorAtEnd = function($input, delay, callback) {
  _.delay(function() {
    $input.focus();
    var tmpStr = $input.val();
    $input.val('');
    $input.val(tmpStr);
    $input.scrollTop(999999);
    if (_.isFunction(callback)) {
      callback();
    }
  }, delay || 500);
};

mh.utils.getHeaderHeight = function() {
  var constantSizes = mh.Page.getConstants().sizes;
  return mh.utils.isSmallMode() ? constantSizes.mobileHeaderHeight : constantSizes.headerHeight;
};

mh.utils.url = {
  addPlanStateQueryParam_: function(baseUrl) {
      // naive for now as we can assume base url do not have query params yet
      return baseUrl + '?planState=' + mh.Page.getVars().getUser().get('locationState');
    },
  benefitDetail: function (benefitId, name) {
    // extracts integer ie extracts 9 from 'Benefit-9'
    return '/benefits/' + benefitId.match(/\d+/)[0] + '/' + encodeURIComponent(name);
  },
  carePaths: function(medlineId, name) {
    return '/care-paths/' + medlineId + '/' + encodeURIComponent(name);
  },
  careRouterMap: function(query, args) {
    args = _.isObject(args) ? args : {};
    delete args.q;
    return this.addPlanStateQueryParam_('/care/') + '&q=' + encodeURIComponent(query) + '&' + $.param(args) ;
  },
  drugProfile: function(id) {
    return this.addPlanStateQueryParam_('/drugs/' + id);
  },
  facilityProfile: function(id) {
    return this.addPlanStateQueryParam_('/places/' + id);
  },
  providerProfile: function(id, optLocId) {
    return this.addPlanStateQueryParam_('/people/' + id + (optLocId ? '/' + optLocId : ''));
  },
  seeAllMetasearchResults: function(query, resultType) {
    return this.addPlanStateQueryParam_('/care/search/') + '&q=' + query + '&resultType=' + resultType;
  }
};

mh.utils.extractPlanInfoFromPlanId = function(planId) {
  var planInfo = planId.split('.');
  return {
    'locationState': planInfo[0],
    'date': planInfo[1]
  };
};

mh.utils.curry = function(fn, scope) {
  var args = [];
  var len = arguments.length;
  for (var i = 1; i < len; ++i) {
    args.push(arguments[i]);
  }

  return function() {
    var args2 = [];
    for (var j = 0; j < arguments.length; j++) {
      args.push(arguments[j]);
    }

    var argstotal = args.concat(args2);
    return fn.apply(scope, argstotal);
  };
};

mh.utils.escapeHTML = function(str) {
  var entityMap = {
    "&": "&amp;",
    "<": "&lt;",
    ">": "&gt;",
    '"': '&quot;',
    "'": '&#39;',
    "/": '&#x2F;'
  };

  return String(str).replace(/[&<>"'\/]/g, function(s) {
    return entityMap[s];
  });
};

mh.view = mh.view || {};
mh.view.CustomSelectView = mh.view.BaseView.extend({
  Class: {
    SHOW: 'custom-select--show',
    ANY: 'custom-select-option--any',
    HIDE: 'custom-select-option--hide',
    HIGHLIGHTED: 'custom-select-option--highlighted',
    OPTIONS: 'custom-select-options',
    OPTION: 'custom-select-option',
    SELECTED: 'custom-select-option--selected',
    HAS_ANY: 'form-field--custom-select--any',
    HAS_CLOSE_X: 'form-field--custom-select--close-x',
    HAS_SEARCH: 'form-field--custom-select--search',
    IS_MULTIPLE: 'form-field--custom-select--multiple',
    UNPROCESSED: 'form-field--unprocessed',
    DROPDOWN: 'custom-select-dropdown',
    DROPDOWN_CONTENT: 'custom-select-dropdown-content'
  },
  Event: {
    OPEN: 'open.custom-select',
    CLOSE: 'close.custom-select',
    SELECTIONS_UPDATED: 'selections-updated',
    FOCUS_IN: 'focusin.custom-select',
    FOCUS_OUT: 'focusout.custom-select'
  },

  HIGHLIGHTED_STARTING_POS: -1,

  PAGE_FREEZE_NAME: 'custom-select'
});

mh.utils = mh.utils || {};
window.mh = mh || {};
mh.view.WTFormsBaseView = mh.view.BaseView.extend({});
mh.view = mh.view || {};
mh.view.marketing = mh.view.marketing || {};
mh.model = mh.model || {};
mh.model.BaseModel = Backbone.Model.extend({

  mappings: null,
  discriminatorMap: null,

  page: function() {
    return mh.Page;
  },

  deepToJSON: function() {
    return JSON.parse(JSON.stringify(this.attributes));
  },

  /* eslint-disable max-len */
  // Based off of https://engineering.groupon.com/2012/javascript/extending-backbone-js-to-map-rough-api-responses-into-beautiful-client-side-models/
  /* eslint-enable max-len */

  postParseCalculation: function () {
    //no-op
  },

  fromJSON: function (input) {
    this.set(this.parse(input, null));
  },

  mergeMappings: function(base, extend) {
    if (!_.isArray(base)) {
      base = [];
    }

    if (!_.isArray(extend)) {
      extend = [];
    }

    var mappings, mappingsTo, merged;
    mappings = [];
    mappingsTo = {};
    merged = _.union(extend, base);

    _.each(merged, function(mapping) {
      if (!_.isObject(mapping)) {
        return;
      }

      if (mappingsTo[mapping.to]) {
        return;
      }

      mappings.push(mapping);
      mappingsTo[mapping.to] = true;
    });

    return mappings;
  },

  /* eslint-disable no-eval */
  parse: function (resp, xhr) {
    var i, j, result, attrMapping, mapping, mappingTo, val, mapParts, modelInst,
        model, collection, collectionInst, discriminatorMap, discriminatorModel, processCollectionModels;
    if (this.mappings === null) {
      return resp;
    }

    processCollectionModels = function (data) {
      // Defensive against weird results
      if (!_.isObject(data)) {
        return;
      }

      if (_.isObject(discriminatorMap) && !_.isUndefined(discriminatorMap[data.type])) {
        discriminatorModel = eval(discriminatorMap[data.type]);

        if (!discriminatorModel) {
          throw 'Model ' + discriminatorMap[data.type] + ' defined by ' + attrMapping.collection +
            ' not declared';
        }

        modelInst = new discriminatorModel();
      } else {
        modelInst = new model();
      }

      modelInst.fromJSON(data);
      modelInst.set({'_parent': this}, {silent: true});

      collectionInst.push(modelInst);
    };

    result = {};
    for (i = 0; i < this.mappings.length; i++) {
      attrMapping = this.mappings[i];
      mapping = null;
      mappingTo = null;
      val = null;

      if (_.isObject(attrMapping)) {
        mapping = attrMapping.from ? attrMapping.from : attrMapping.to;
        mappingTo = attrMapping.to;
      }

      if (!mappingTo) {
        continue;
      }

      try {
        mapParts = mapping.split(".");
        val = resp;
        for (j = 0; j < mapParts.length; j++) {
          val = val[mapParts[j]];
        }
      }
      catch (err) {
        if (err.name === "TypeError") {
          val = null;
        } else {
          throw err;
        }
      }

      if (val === null || _.isUndefined(val)) {
        result[mappingTo] = undefined;
      } else {
        switch (attrMapping.type) {
          case "string":
            result[mappingTo] = String(val);
            break;

          case "date":
            result[mappingTo] = Date.parse(val);
            break;

          case "int":
            result[mappingTo] = parseInt($.trim(val), 10);
            break;

          case "float":
            result[mappingTo] = parseFloat($.trim(val));
            break;

          case "array":
            if (_.isArray(val)) {
              result[mappingTo] = val;
            }
            break;

          case "boolean":
            result[mappingTo] = !!val;
            break;

          case "calculation":
            result[mappingTo] = this[attrMapping.calculationMethod](result);
            break;

          case "model":
            model = eval(attrMapping.model);
            if (!model) {
              throw 'Model ' + attrMapping.model + ' not declared';
            }

            modelInst = new model();

            modelInst.fromJSON(val);
            modelInst.set({'_parent': this}, {silent: true});

            result[mappingTo] = modelInst;
            break;

          case "collection":
            if (_.isArray(val) || (_.isObject(val) && _.keys(val).length)) {
              collection = eval(attrMapping.collection);
              if (!collection) {
                throw 'Collection ' + attrMapping.collection + ' not declared';
              }

              collectionInst = new collection();

              model = eval(collectionInst.model);
              if (!model) {
                throw 'Model ' + collectionInst.model + ' defined by ' + attrMapping.collection + ' not declared';
              }

              discriminatorMap = collectionInst.discriminatorMap;

              _.each(val, processCollectionModels);

              result[mappingTo] = collectionInst;
            }
            break;

          default:
            result[mappingTo] = val;
        }
      }
    }

    this.postParseCalculation(result);

    return result;
  }
  /* eslint-enable no-eval */
});
// depends on: ["js/base/BaseView.js"]


/**
 * Modal controller. Provides access to modals from other views. In the future, will be used to resolve conflicts
 * between competing modals.
*/
mh.view.ModalController = mh.view.BaseView.extend({
  /**
   * Sets-up the page freezer and trigger listener.
   */
  initialize: function() {
    this.modals_ = {};
  },

  register: function(name, modalView) {
    if (!name) {
      throw 'Tried to register a modal with no name';
    }

    // Note: This check might be problematic. Since BaseModal depends on the ModalController, this could cause an
    // unfortunate circular dependency.
    if (!(modalView instanceof mh.view.BaseModal)) {
      throw ['The modal with name "', name, '" is not an instance of mh.view.BaseModal'].join(' ');
    }

    if (_.has(this.modals_, name)) {
      throw ['A modal with name "', name, '" has already been registered'].join(' ');
    }

    this.modals_[name] = modalView;
  },

  getModalByName: function(modalName) {
    return this.modals_[modalName];
  }
});

window.mh = mh || {};
mh.model = mh.model || {};


mh.model.SharedConstants = mh.model.BaseModel.extend({
  charCodes: {
    // Arrows
    ARROW_LEFT: 37,
    ARROW_UP: 38,
    ARROW_RIGHT: 39,
    ARROW_DOWN: 40,

    ENTER: 13,
    ESCAPE: 27,
    DELETE: 46,
    TAB: 9,
    BACKSPACE: 8,

    // Numbers
    ZERO: 48,
    ONE: 49,
    TWO: 50,
    THREE: 51,
    FOUR: 52,
    FIVE: 53,
    SIX: 54,
    SEVEN: 55,
    EIGHT: 56,
    NINE: 57,
    FF_SPECIALCHAR: 0
  },

  scssTransitionTiming: {
    NO_TIME: 0,
    QUICK: 100,
    NORMAL: 200,
    SLOW: 400
  },

  sizes: {
    headerHeight: 60,
    mobileHeaderHeight: 50,
    STANDARD_MIN_HEIGHT: 600
  }
});

window.mh = mh || {};
mh.view = mh.view || {};

mh.view.BasePage = Backbone.View.extend({
  initialize: function(options) {
    var options_ = options || {};

    this.components_ = {};
    this.vars_ = this.vars_ || {};
    this.constants_ = this.constants_ || new mh.model.SharedConstants({});
  },

  boot: function(components) {
    _.each(components, function(component) {
      var id;  // Early initialize due to throw
      try {
        if (!_.isObject(component)) {
          throw 'Unknown component initialized';
        }

        var componentStr = component.component;
        id = component.id || componentStr;
        var options = _.isObject(component.options) ? component.options : {};

        if (!componentStr) {
          throw 'No component specified.';
        } else if (this.components_[componentStr]) {
          throw 'Multiple components of class "' + componentStr + '" initialized without a given id';
        }

        // Disable linter rules about eval and lower case class names (dynamic invocation)
        /* eslint-disable no-eval */
        var componentClass = eval(componentStr);
        /* eslint-enable no-eval */

        /* eslint-disable new-cap */
        this.components_[id] = new componentClass(options);
        /* eslint-enable new-cap */
      } catch(err) {
        // TODO (stephen): Update this to us the custom console wrapper once it lands.
        /* eslint-disable no-console */
        console.error('Oops. Component ' + id + ' could not be instantiated!', err, err.message, err.stack);
        /* eslint-enable no-console */
        if (window.NREUM) {
          window.NREUM.noticeError(err);
        }
      }
    }, this);
  },

  getVars: function() {
    return this.vars_;
  },

  getConstants: function() {
    return this.constants_;
  }
});
window.mh = mh || {};
mh.model = mh.model || {};
mh.view = mh.view || {};
mh.view.Page = mh.view.BasePage.extend({
  initialize: function(options) {
    mh.view.BasePage.prototype.initialize.apply(this, arguments);
  }
});

window.mh = mh || {};
mh.view = mh.view || {};
mh.view.quote = mh.view.quote || {};
mh.view.marketing = mh.view.marketing || {};

mh.view.BaseModal = mh.view.BaseView.extend({
  name: '',
  triggerEl: '',

  events: {
    'click': 'handleClick_',
    'click .mh-modal-close': 'closeModal'
  },
  initialize: function() {
    if (!this.name) {
      throw 'Oops. Modal "name" not defined!';
    }

    if (!this.$el.hasClass(mh.view.BaseModal.Class.BASE)) {
      this.setElement($(['.', mh.view.BaseModal.Class.BASE, '--', this.name].join('')));
    }

    if (!this.$el.hasClass(mh.view.BaseModal.Class.BASE)) {
      throw 'Oops. Modal does not have a valid element defined!';
    }

    _.extend(this.events, mh.view.BaseModal.prototype.events);

    this.isShowing_ = false;
    this.$window_ = $(window);

    this.page().getVars().getModalController().register(this.name, this);

    if (this.triggerEl) {
      $('body').on('click', this.triggerEl, _.bind(this.showModal, this));
    }

    this.modalTemplate = _.template($(this.templateSelector).html());

    this.isDismissable_ = this.$el.data('dismissable') === 'True';
  },

  /**
   * Shows the modal and triggers the page freezer.
   */
  showModal: function () {
    this.$el.addClass(mh.view.BaseModal.Class.SHOW);
    this.isShowing_ = true;
    this.$window_.on(this.getWindowKeydownEventName_(), _.bind(this.handleKeydown_, this));
    this.trigger(mh.view.BaseModal.Event.MODAL_SHOWN);
  },

  /**
   * Hides the modal and triggers the page unfreeze event.
   * @private
   */
  closeModal: function() {
    if (!this.isDismissable_) {
      return;
    }

    this.$el.removeClass(mh.view.BaseModal.Class.SHOW);

    this.setTimeout(function() {
    }, this.page().getConstants().scssTransitionTiming.SLOW);

    this.$window_.off(this.getWindowKeydownEventName_());
    this.isShowing_ = false;
    this.trigger(mh.view.BaseModal.Event.MODAL_CLOSED);
  },

   /**
   * Handles clicks on this.$el. It does nothing if the clicked el != this.el, otherwise it closes the modal.
   *
   * @param {jQuery.Event} e The click event
   * @private
   */
  handleClick_: function(e) {
    if (e.target !== this.el) {
      return;
    }

    this.closeModal();
  },

  /**
   * Handles key down event on the window. On escape, it closes the modal.
   *
   * @param {jQuery.Event} e The key press event
   * @private
   */
  handleKeydown_: function(e) {
    if (e.which !== this.page().getConstants().charCodes.ESCAPE) {
      return;
    }

    e.stopPropagation();
    e.preventDefault();

    this.closeModal();
  },
  /**
   * Gets the namespaced keydown event.
   * @returns {string}
   * @private
   */
  getWindowKeydownEventName_: function() {
    return [mh.view.BaseModal.Event.WINDOW_KEYDOWN_BASE, this.name].join('.');
  }
 }, {
  Class: {
    BASE: 'mh-modal',
    SHOW: 'mh-modal--show'
  },
  Event: {
    WINDOW_KEYDOWN_BASE: 'keydown',
    MODAL_CLOSED: 'modal-closed',
    MODAL_SHOWN: 'modal-shown'
  },
  PAGE_FREEZE_BASE: 'mh-modal'
});
// depends on: ["web_shared:js/modal/BaseModal.js"]

window.mh = mh || {};
mh.view = mh.view || {};
mh.view.roadblockModal = mh.view.roadblockModal || {};

mh.collection = mh.collection || {};
mh.collection.BaseCollection = Backbone.Collection.extend({
  discriminatorMap: {},

  page: function() {
    return mh.Page;
  }
});
// depends on: ["web_shared:js/forms/WTFormsBaseView.js"]

window.mh = mh || {};
mh.view = mh.view || {};
mh.view.planPicker = mh.view.planPicker || {};

/**
 * Handles the interactions for the plan selection form.
 * @class
 */
mh.view.planPicker.PlanSelectionForm = mh.view.WTFormsBaseView.extend({
  el: '.plan-picker-form',

  events: {
    'click .pick-plan-button': 'pickPlan_'
  },

  initialize: function() {
    mh.view.WTFormsBaseView.prototype.initialize.apply(this, arguments);

    this.$planPickerIdInput_ = this.get$byId(mh.view.planPicker.PlanSelectionForm.Id.PLAN_PICKER_ID_INPUT);
    this.$planPickerPriceInput_ = this.get$byId(mh.view.planPicker.PlanSelectionForm.Id.PLAN_PICKER_PRICE_INPUT);
  },

  pickPlan_: function(e) {
    var $selectedPlan = $(e.currentTarget);
    this.$planPickerIdInput_.val($selectedPlan.data('plan-id'));
    this.$planPickerPriceInput_.val($selectedPlan.data('price'));

    this.onFormSubmit_();
  }
}, {
  Id: {
    PLAN_PICKER_ID_INPUT: 'plan-picker-plan_id',
    PLAN_PICKER_PRICE_INPUT: 'plan-picker-quoted_price'
  }
});
// depends on: ["js/modal/BaseModal.js"]


window.mh = mh || {};
mh.view = mh.view || {};
mh.view.marketing = mh.view.marketing || {};

mh.view.marketing.GET_IN_TOUCH_MODAL_NAME = 'mh-get-in-touch';

/**
 * Controls for the get in touch modal.
 * @class
 */
mh.view.marketing.GetInTouchModal = mh.view.BaseModal.extend({
  name: mh.view.marketing.GET_IN_TOUCH_MODAL_NAME,
  triggerEl: '.js-launch-mh-get-in-touch',

  /**
   * Init.
   */
  initialize: function() {
    mh.view.BaseModal.prototype.initialize.apply(this, arguments);

    var getInTouchForm = new mh.view.marketing.GetInTouchForm();
    this.listenTo(getInTouchForm, mh.view.marketing.GetInTouchForm.Event.FORM_SUBMITTED, this.handleFormSubmission_);
  },

  /**
   * Reacts to the form being submitted via the form listener.
   * @private
   */
  handleFormSubmission_: function() {
    this.$el.addClass(mh.view.marketing.GetInTouchModal.Class.FORM_SUBMITTED);
  }
}, {
  Class: {
    FORM_SUBMITTED: 'mh-modal--mh-get-in-touch--submitted'
  }
});
// depends on: ["web_shared:js/base/BaseView.js"]

window.mh = mh || {};
mh.view = mh.view || {};
mh.view.quote = mh.view.quote || {};

mh.view.quote.QuoteFormFieldTextView = mh.view.BaseView.extend({

  initialize: function(options) {
    var options_ = options || {};

    this.name = options_.name || this.$el.data('name');

    this.conditionMap = {
      'member-only': this.model.hasMemberOnly,
      'has-single-or-no-kids': this.model.hasSingleOrNoKids,
      'has-multiple-kids': this.model.hasMultipleKids,
      'has-any-kids': this.model.hasAnyKids,
      'has-spouse': this.model.hasSpouse,
      'has-spouse-only': this.model.hasSpouseOnly,
      'has-spouse-and-kids': this.model.hasSpouseAndKids,
      'has-single-tax-household': this.model.hasSingleTaxHousehold,
      'has-zero-or-multiple-tax-household': this.model.hasZeroOrMultipleTaxHousehold
    };

    this.$textContainers = this.$(mh.view.quote.QuoteFormFieldTextView.Selector.CONTAINER);

    this.shouldReveal_ = options_.shouldReveal || true;
  },

  shouldReveal: function() {
    return _.isFunction(this.shouldReveal_) ? this.shouldReveal_() : this.shouldReveal_;
  },

  updateTextGrammar: function() {
    _.each(this.$textContainers, function(container) {
      var $container = $(container);
      var conditionFunction = this.conditionMap[$container.data('condition')];

      var conditionValue = _.isFunction(conditionFunction) ? !!_.bind(conditionFunction, this.model)() : true;

      this.toggleText_($container, conditionValue);
    }, this);
  },

  toggleText_: function($textContainer, shouldShow) {
    $textContainer.toggleClass(mh.view.quote.QuoteFormFieldTextView.Class.SHOW, shouldShow);
  },

  isApplicable_: function() {
    var isApplicableCondition = this.$el.data('is-applicable');
    var conditionFunction = this.conditionMap[isApplicableCondition];

    if (_.isFunction(conditionFunction)) {
      return _.bind(conditionFunction, this.model)();
    }

    return true;
  },

  isVisible: function() {
    var result = this.isApplicable_() && this.shouldReveal();
    if (result) {
      this.trigger(mh.view.quote.QuoteFormFieldTextView.Event.IS_VISIBLE);
    }
    return result;
  },

  toggleField: function() {
    this.$el.toggleClass(mh.view.quote.QuoteFormFieldTextView.Class.HIDDEN, !this.isVisible());
  },

  focus: function() {
    // general case
    this.$('input').focus();
  },

  render: function() {
    this.updateTextGrammar();
    this.toggleField();
  }
}, {

  Selector: {
    CONTAINER: '.field-inline-text-container'
  },

  Class: {
    PLURAL: 'field-inline-text-container--plural',
    HIDDEN: 'field-inline--hidden',
    SHOW: 'field-inline-text-container--show'
  },

  Event: {
    IS_VISIBLE: 'is-visible.quote-form-field-text-view'
  }

});
// depends on: ["web_shared:js/forms/WTFormsBaseView.js"]

window.mh = mh || {};
mh.view = mh.view || {};
mh.view.getmhar = mh.view.getmhar || {};

/**
 * Handles the early bird form for people trying to sign up for mhar before open enrollment opens.
 * @class
 */
mh.view.getmhar.EaryBirdForm = mh.view.WTFormsBaseView.extend({
  el: '.early-bird-form',
  submitEl: '.submit_button',

  events: {
    'input input': 'onInputChange_'
  },

  /**
   * Initializes the form. Currently only used to set some jQuery object instance variables.
   */
  initialize: function() {
    mh.view.WTFormsBaseView.prototype.initialize.apply(this, arguments);

    this.$errorText_ = this.get$byClass(mh.view.getmhar.EaryBirdForm.Class.ERROR_TEXT);
  },

  /**
   * Handles errors sent back from the server after the form is submitted. Specifically, it adds error states to the
   * fields which have them, and reveals the error text.
   *
   * @param {mh.view.getmhar.EaryBirdForm} formView - This view.
   * @param {Object.<string, string>} response The response from the server.
   * @private
   */
  handleSubmissionError_: function(formView, response) {
    // Gets the error text.
    var errorMessage = _.first(mh.utils.getDeepReference(response, 'errors.form_errors', []));

    // Adds the error class to the fields that have errors.
    var fieldErrors = mh.utils.getDeepReference(response, 'errors.field_errors', {});
    _.each(fieldErrors, function(error, inputId) {
      this.get$byClass('form-field--' + inputId).addClass(mh.view.getmhar.EaryBirdForm.Class.INPUT_ERROR);
    }, this);

    // Replaces the current error text with the error from the response and add the show error class.
    this.$errorText_.html(errorMessage);
    this.$errorText_.addClass(mh.view.getmhar.EaryBirdForm.Class.ERROR_TEXT_SHOW);
  },

  /**
   * Handles whenever an input's val is changed, namely removing the error state from that field if there was one.
   *
   * @param e
   * @private
   */
  onInputChange_: function(e) {
    $(e.currentTarget).closest('.' + mh.view.getmhar.EaryBirdForm.Class.INPUT_ERROR)
                      .removeClass(mh.view.getmhar.EaryBirdForm.Class.INPUT_ERROR);
  },

  /**
   * Removes the form error states before the form is submitted.
   *
   * @private
   */
  presubmit_: function() {
    mh.utils.clearClass(mh.view.getmhar.EaryBirdForm.Class.INPUT_ERROR, this.$el);
    mh.utils.clearClass(mh.view.getmhar.EaryBirdForm.Class.ERROR_TEXT_SHOW, this.$el);
  },

  /**
   * Shows the form's thank you message after the form is successfully submitted.
   * @private
   */
  handleSubmissionSuccess_: function() {
    this.$el.addClass(mh.view.getmhar.EaryBirdForm.Class.FORM_SUBMITTED);
  },

  /**
   * Shows the form's server error message when a 500 comes back from the server.
   * @private
   */
  handleSubmissionServerError_: function() {
    this.$el.addClass(mh.view.getmhar.EaryBirdForm.Class.FORM_SERVER_ERROR);
  }
}, {
  Class: {
    ERROR_TEXT: 'early-bird-form-errors',
    ERROR_TEXT_SHOW: 'early-bird-form-errors--show',
    INPUT_ERROR: 'form-field--error',
    FORM_SERVER_ERROR: 'early-bird-form--server-error',
    FORM_SUBMITTED: 'early-bird-form--submitted'
  }
});
// depends on: [
//     "web_shared:js/base/BaseView.js"
// ]

window.mh = mh || {};
mh.view = mh.view || {};
mh.view.brokers = mh.view.brokers || {};

mh.view.brokers.BrokerLinkBuilderFormView = mh.view.WTFormsBaseView.extend({
  el: '.broker-link-builder-form',

  initialize: function() {
    mh.view.WTFormsBaseView.prototype.initialize.apply(this, arguments);

    this.$linkOutput_ = this.get$byClass(mh.view.brokers.BrokerLinkBuilderFormView.Class.LINK_OUTPUT);

    this.$errors_ = this.get$byClass('broker-link-builder-form-errors');
    this.errorMessages_ = [];
    this.errorTemplate_ = _.template($('#broker-link-builder-error-template').html());

    this.locationCustomSelectView_ = this.customSelectWidgets_['broker-link-builder-location'];

    this.initListeners_();
  },

  initListeners_: function() {
    this.listenTo(this.locationCustomSelectView_,
                  mh.view.CustomSelectView.Event.SELECTIONS_UPDATED,
                  this.handleLocationChange_);
  },

  handleSubmissionError_: function(formView, response) {
    var fieldErrors = mh.utils.getDeepReference(response, 'errors.field_errors', {});
    var formErrors = mh.utils.getDeepReference(response, 'errors.form_errors', []);

    this.toggleOutput_(false);

    this.handleFieldErrors_(fieldErrors);
    this.handleFormErrors_(formErrors);

    this.renderErrors_();
  },

  handleFieldErrors_: function(fieldErrors) {
    // fieldErrors: object
    _.each(fieldErrors, function(error, fieldName) {
      this.errorMessages_.push(error[0]);
      this.toggleFieldErrorState_(fieldName);
    }, this);
  },

  handleFormErrors_: function(formErrors) {
    // formErrors: array
    _.each(formErrors, function(errorMessage) {
      this.errorMessages_.push(errorMessage);
    }, this);
  },

  renderErrors_: function() {
    this.$errors_.html(this.errorTemplate_({errorMessages: this.errorMessages_}));
  },

  toggleFieldErrorState_: function(fieldName) {
    this.$('.form-field--' + fieldName).toggleClass(mh.view.brokers.BrokerLinkBuilderFormView.Class.FIELD_ERROR);
  },

  handleSubmissionServerError_: function() {
    this.errorMessages_.push('There was a problem generating your link.');
    this.renderErrors_();
  },

  handleSubmissionSuccess_: function(formView, response) {
    var url = mh.utils.getDeepReference(response, 'info.url', '');
    this.$linkOutput_.text(url);

    mh.utils.smoothScroll(0);
    this.toggleOutput_(true);
  },

  presubmit_: function() {
    this.errorMessages_ = [];
    this.clearClass(mh.view.brokers.BrokerLinkBuilderFormView.Class.FIELD_ERROR);
    this.renderErrors_();
  },

  handleLocationChange_: function(eventData) {
    var showClass = mh.view.brokers.BrokerLinkBuilderFormView.Class.INFO_INPUT_SHOW;
    var infoInputClass = mh.view.brokers.BrokerLinkBuilderFormView.Class.INFO_INPUT;
    var selectedValue = eventData.values.selectedValues[0];
    var selectedLocations = selectedValue.split('+');  // can be one or more

    this.clearClass(showClass);

    _.each(selectedLocations, function(location) {
      // e.g. location === 'NY'
      this.get$byClass([infoInputClass, location.toLowerCase()].join('--')).addClass(showClass);
    }, this);
  },

  toggleOutput_: function(shouldShow) {
    this.get$byClass('broker-link-builder-form-output')
        .toggleClass('broker-link-builder-form-output--show', shouldShow);
  }

}, {
  Class: {
    FIELD_ERROR: 'broker-link-builder-form-field-error',
    LINK_OUTPUT: 'broker-link-builder-form-output-link',
    INFO_INPUT: 'broker-link-builder-form-info',
    INFO_INPUT_SHOW: 'broker-link-builder-form-info--show'
  }
});

mh.view.marketing = mh.view.marketing || {};

mh.view.marketing.GET_QUOTE_MODAL_NAME = 'get-quote-modal';

/**
 * Controls for the GetQuote modal
 * @class
 */
mh.view.marketing.GetQuoteModal = mh.view.BaseModal.extend({
  name: mh.view.marketing.GET_QUOTE_MODAL_NAME
});

window.mh = mh || {};
mh.view = mh.view || {};
mh.view.brokers = mh.view.brokers || {};

/**
 * Handles the get in touch form for the brokers pages.
 * @class
 */
mh.view.brokers.WorkWithUsForm = mh.view.WTFormsBaseView.extend({
  el: '.mh-brokers-work-with-us-form',

  events: {
    'input .mh-brokers-input': 'onInputChange_'
  },

  /**
   * Init.
   */
  initialize: function() {
    mh.view.WTFormsBaseView.prototype.initialize.apply(this, arguments);
    this.$errorText_ = this.get$byClass(mh.view.brokers.WorkWithUsForm.Class.ERROR_TEXT);
  },

  /**
   * Handles errors sent back from the server after the form is submitted. Specifically, it adds error states to the
   * fields which have them, and reveals the error text.
   *
   * TODO: Make this part of a generic brokers form.
   * @param {mh.view.brokers.WorkWithUsForm} formView This form.
   * @param {Object.<string, string>} response The response from the server.
   * @private
   */
  handleSubmissionError_: function(formView, response) {
    // Gets the error text.
    var errorMessage = _.first(mh.utils.getDeepReference(response, 'errors.form_errors', []));

    // Adds the error class to the fields that have errors.
    var fieldErrors = mh.utils.getDeepReference(response, 'errors.field_errors', {});
    _.each(fieldErrors, function(error, inputId) {
      var $input = this.get$byId(inputId);
      $input.addClass(mh.view.brokers.WorkWithUsForm.Class.INPUT_ERROR);
    }, this);

    // Replaces the current error text with the error from the response and add the show error class.
    this.$errorText_.html(errorMessage);
    this.$errorText_.addClass(mh.view.brokers.WorkWithUsForm.Class.ERROR_TEXT_SHOW);
  },

  /**
   * Handles server errors (e.g. 500s)
   * @private
   */
  handleSubmissionServerError_: function() {
    this.$el.addClass(mh.view.brokers.WorkWithUsForm.Class.FORM_SERVER_ERROR);
  },

  /**
   * Handles whenever an input's val is changed, namely removing the error state from that field if there was one.
   *
   * TODO: Make this part of a generic brokers form.
   * @param e
   * @private
   */
  onInputChange_: function(e) {
    $(e.currentTarget).removeClass(mh.view.brokers.WorkWithUsForm.Class.INPUT_ERROR);
  },

  /**
   * Removes the form error states before the form is submitted. Also preps the DOM to prepare it for revealing the
   * thank you message on success.
   *
   * TODO: Make this part of a generic brokers form (except the name part).
   * @private
   */
  presubmit_: function() {
    mh.utils.clearClass(mh.view.brokers.WorkWithUsForm.Class.INPUT_ERROR, this.$el);
    mh.utils.clearClass(mh.view.brokers.WorkWithUsForm.Class.ERROR_TEXT_SHOW, this.$el);

    var firstName = this.get$byId(mh.view.brokers.WorkWithUsForm.Id.FIRST_NAME).val();
    this.get$byClass(mh.view.brokers.WorkWithUsForm.Class.MEMBER_NAME).text(firstName);
  },

  /**
   * Shows the form's thank you message after the form is successfully submitted.
   * @private
   */
  handleSubmissionSuccess_: function() {
    this.$el.addClass(mh.view.brokers.WorkWithUsForm.Class.FORM_SUBMITTED);
  }
}, {
  Class: {
    ERROR_TEXT: 'mh-brokers-work-with-us-error',
    ERROR_TEXT_SHOW: 'mh-brokers-work-with-us-error--show',
    FORM_SERVER_ERROR: 'mh-brokers-work-with-us-form--server-error',
    FORM_SUBMITTED: 'mh-brokers-work-with-us-form--submitted',
    INPUT_ERROR: 'mh-brokers-input--error',
    MEMBER_NAME: 'mh-brokers-work-with-us-name'
  },
  Id: {
    FIRST_NAME: 'work-with-us-first_name'
  }
});
// depends on: ["web_shared:js/forms/WTFormsBaseView.js"]

window.mh = mh || {};
mh.view = mh.view || {};
mh.view.getmhar = mh.view.getmhar || {};

mh.view.getmhar.GetmharFormBaseView = mh.view.WTFormsBaseView.extend({
  el: '.get-mhar-form',
  submitEl: '.submit_form',

  events: {
    'input input': 'handleInputChange_',
    'click .get-mhar-form-field--condition': 'handleConditionalChange_',
    'click .js-close-form-errors': 'closeFormErrors_',
    'click .js-close-form-modal': 'closeFormModal_'
  },

  initialize: function() {
    mh.view.WTFormsBaseView.prototype.initialize.apply(this, arguments);

    this.$errors_ = this.get$byClass(mh.view.getmhar.GetmharFormBaseView.Class.ERRORS);
    this.errorListTemplate_ = _.template($('#get-mhar-form-field-errors-template').html());

    this.$formError_ = this.get$byClass(mh.view.getmhar.GetmharFormBaseView.Class.FORM_ERROR);
    this.formErrorListTemplate_ = _.template($('#get-mhar-form-error-template').html());

    this.$formModal_ = this.get$byClass(mh.view.getmhar.GetmharFormBaseView.Class.FORM_MODAL);

    _.each(this.customSelectWidgets_, function(widget) {
      var handler = _.bind(this.handleCustomSelectConditionalChange_, this, {currentTarget: widget.$el});
      this.listenTo(widget,
                    mh.view.CustomSelectView.Event.SELECTIONS_UPDATED,
                    handler);
    }, this);
  },

  handleInputChange_: function(e) {
    var fieldErrorClass = mh.view.getmhar.GetmharFormBaseView.Class.FIELD_ERROR;
    $(e.currentTarget).closest('.' + fieldErrorClass)
                      .removeClass(fieldErrorClass);
  },

  handleCustomSelectConditionalChange_: function(e) {
    this.handleInputChange_(e);

    // check for target
    var $targetInput = $(e.currentTarget).find('[data-show-section]');

    if (_.isEmpty($targetInput)) {
      return;
    }

    // toggle class
    var revealTargetSelector = $targetInput.data('show-section');
    var $revealTarget = this.$(revealTargetSelector);
    var shouldReveal = _.first($targetInput.val()) === $targetInput.data('show-if');

    $revealTarget.toggleClass('get-mhar-form-section--hidden', !shouldReveal);
  },

  handleConditionalChange_: function(e) {
    this.handleInputChange_(e);

    var $target = $(e.currentTarget);
    var isNotChecked = !$target.find('input').is(':checked');
    $target.closest('.' + mh.view.getmhar.GetmharFormBaseView.Class.CONDITIONAL_FIELD)
           .toggleClass(mh.view.getmhar.GetmharFormBaseView.Class.CONDITIONAL_FIELD_HIDDEN, isNotChecked);
  },

  presubmit_: function() {
    mh.utils.clearClass(mh.view.getmhar.GetmharFormBaseView.Class.FIELD_ERROR, this.$el);
    mh.utils.clearClass(mh.view.getmhar.GetmharFormBaseView.Class.ERRORS_SHOW, this.$el);
    mh.utils.clearClass(mh.view.getmhar.GetmharFormBaseView.Class.FORM_ERROR_SHOW, this.$el);
    mh.utils.clearClass(mh.view.getmhar.GetmharFormBaseView.Class.FORM_MODAL_SHOW, this.$el);
},

  handleSubmissionError_: function(formView, response) {
    var fieldErrors = mh.utils.getDeepReference(response, 'errors.field_errors', {});
    var formErrors = mh.utils.getDeepReference(response, 'errors.form_errors', []);

    this.renderFormErrors_(formErrors);
    this.renderFieldErrors_(fieldErrors);
  },

  handleSubmissionSuccess_: function(formView, response) {
    var modalMsg = mh.utils.getDeepReference(response, 'info.modal');

    this.renderFormModal_(modalMsg);
  },

  renderFormModal_: function(modalMsg) {
    this.$formModal_.html(modalMsg)
                    .addClass(mh.view.getmhar.GetmharFormBaseView.Class.FORM_MODAL_SHOW);
  },

  renderFormErrors_: function(formErrors) {
    if (_.isEmpty(formErrors)) {
      return;
    }

    var errorMessage = _.first(formErrors);

    this.$formError_.html(this.formErrorListTemplate_({
      errorMsg: errorMessage
    }));

    this.$formError_.addClass(mh.view.getmhar.GetmharFormBaseView.Class.FORM_ERROR_SHOW);
  },

  closeFormErrors_: function(e) {
    var formErrorClass = mh.view.getmhar.GetmharFormBaseView.Class.FORM_ERROR_SHOW;
    $(e.currentTarget).closest('.' + formErrorClass)
                      .removeClass(formErrorClass);
  },

  closeFormModal_: function(e) {
    var formModalClass = mh.view.getmhar.GetmharFormBaseView.Class.FORM_MODAL_SHOW;
    $(e.currentTarget).closest('.' + formModalClass)
                      .removeClass(formModalClass);
  },

  renderFieldErrors_: function(fieldErrors) {
    var errorMsgs = [];

    _.each(fieldErrors, function(errors, fieldName) {
      this.get$fieldByName(fieldName).addClass(mh.view.getmhar.GetmharFormBaseView.Class.FIELD_ERROR);
      errorMsgs.push(errors[0]);
    }, this);

    // de-dup
    errorMsgs = _.uniq(errorMsgs);

    this.$errors_.html(this.errorListTemplate_({
      errorMsgs: errorMsgs
    }));

    this.$errors_.addClass(mh.view.getmhar.GetmharFormBaseView.Class.ERRORS_SHOW);
  },

  handleSubmissionServerError_: function() {
    this.$el.addClass(mh.view.getmhar.GetmharFormBaseView.Class.FORM_SERVER_ERROR);
  },

  handleSubmissionRedirect_: function(formView, response) {
    // Delay the redirect 100ms for tracking purposes on specific forms.
    var trackingForms = [
      mh.view.getmhar.GetmharFormBaseView.Class.CONTACT_INFO_FORM,
      mh.view.getmhar.GetmharFormBaseView.Class.PLAN_CONFIRMATION_FORM
    ];

    var isTrackingForm = _.some(trackingForms, function(trackingForm) {
      return formView.$el.hasClass(trackingForm);
    }, this);

    this.setTimeout(function() {
      mh.view.WTFormsBaseView.prototype.handleSubmissionRedirect_.call(this, formView, response);
    }, isTrackingForm ? 100 : 0);
  },

  get$fieldByName: function(fieldName) {
    return this.get$byClass('form-field--' + fieldName);
  }
}, {
  Class: {
    CONDITIONAL_FIELD: 'get-mhar-form-fields--conditional',
    CONDITIONAL_FIELD_HIDDEN: 'get-mhar-form-fields--conditional--hidden',
    CONTACT_INFO_FORM: 'get-mhar-form--contact-info',
    ERRORS: 'get-mhar-form-field-errors',
    ERRORS_SHOW: 'get-mhar-form-field-errors--show',
    FIELD_ERROR: 'form-field--error',
    FORM_ERROR: 'get-mhar-form-error',
    FORM_ERROR_SHOW: 'get-mhar-form-error--show',
    FORM_MODAL: 'get-mhar-form-modal',
    FORM_MODAL_SHOW: 'get-mhar-form-modal--show',
    FORM_SERVER_ERROR: 'get-mhar-form--server-error',
    PLAN_CONFIRMATION_FORM: 'get-mhar-form--planconfirmation'
  }
});
// depends on: ["web_shared:js/base/BaseModel.js"]

window.mh = mh || {};
mh.model = mh.model || {};
mh.model.quote = mh.model.quote || {};

mh.model.quote.QuoteFormModel = mh.model.BaseModel.extend({
  mappings: null,
  discriminatorMap: null,

  defaults: function() {
    return {
      'zip_code': null,
      'beneficiary': null,
      'member_age': null,
      'spouse_age': null,
      'kid_amount': null,
      'kid_ages': null,
      'income': null,
      'tax_household': null,
      'revealedFields': [],
      'validatedFields': [],
      'fieldsReadyForValidation': [],
      'householdSizeEstimate': null
    };
  },

  numKids: function() {
    return Number(this.get('kid_amount'));
  },

  getBeneficiaryValueString: function() {
    var beneficiaryValueRaw = this.get('beneficiary');
    return _.isArray(beneficiaryValueRaw) ? beneficiaryValueRaw[0] : '';
  },

  getTaxHouseholdValue: function() {
    var taxHouseholdValueRaw = this.get('tax_household');

    return _.isArray(taxHouseholdValueRaw) ? taxHouseholdValueRaw[0] : '1';
  },

  updateHouseholdSizeEstimate: function() {
    var householdSize = 1;  // always include member
    var householdSizeMax = mh.model.quote.QuoteFormModel.TAX_HOUSEHOLD_MAX;

    if (this.hasSpouse()) {
      householdSize += 1;
    }

    if (this.hasAnyKids()) {
      householdSize += this.numKids();
    }

    if (householdSize > householdSizeMax) {
      householdSize = householdSizeMax;
    }

    this.set({
      householdSizeEstimate: householdSize
    });

    return householdSize;
  },

  hasMemberOnly: function() {
    return this.getBeneficiaryValueString() === 'member';
  },

  hasSpouseOnly: function() {
    return this.getBeneficiaryValueString() === 'member-spouse';
  },

  hasSpouseAndKids: function() {
    return this.getBeneficiaryValueString() === 'member-spouse-kids';
  },

  hasMember: function() {
    var targetStr = mh.model.quote.QuoteFormModel.FieldValues.BENEFICIARY_MEMBER;

    return this.getBeneficiaryValueString().indexOf(targetStr) !== -1;
  },

  hasSpouse: function() {
    var targetStr = mh.model.quote.QuoteFormModel.FieldValues.BENEFICIARY_SPOUSE;

    return this.getBeneficiaryValueString().indexOf(targetStr) !== -1;
  },

  hasAnyKids: function() {
    var targetStr = mh.model.quote.QuoteFormModel.FieldValues.BENEFICIARY_KIDS;

    return this.getBeneficiaryValueString().indexOf(targetStr) !== -1;
  },

  hasSingleOrNoKids: function() {
    // no kids situation for default text
    var numKids = this.numKids();
    return numKids === 1 || numKids === 0;
  },

  hasMultipleKids: function() {
    return this.numKids() > 1;
  },

  hasSingleTaxHousehold: function() {
    return this.getTaxHouseholdValue() === '1';
  },

  hasZeroOrMultipleTaxHousehold: function() {
    return this.getTaxHouseholdValue() !== '1';
  },

  areFieldsValidated: function(fields) {
    var validatedFields = this.get('validatedFields');

    return _.every(fields, function(field) {
      return _.contains(validatedFields, field);
    });
  },

  allRequiredFieldsValidated: function() {
    // start with fields that are always required
    var requiredFields = [
      'zip_code',
      'beneficiary',
      'member_age',
      'income',
      'tax_household'
    ];

    if (this.hasSpouse()) {
      requiredFields.push('spouse_age');
    }

    if (this.hasAnyKids()) {
      requiredFields.push('kid_amount');
      requiredFields.push('kid_ages');
    }

    return this.areFieldsValidated(requiredFields);
  }

},  {
  FieldValues: {
    BENEFICIARY_MEMBER: 'member',
    BENEFICIARY_SPOUSE: 'spouse',
    BENEFICIARY_KIDS: 'kids'
  },

  TAX_HOUSEHOLD_MAX: 8
});
// depends on: ["web_shared:js/forms/WTFormsBaseView.js"]

// This is a Singleton Class

window.mh = mh || {};
mh.view = mh.view || {};
mh.view.brokers = mh.view.brokers || {};

mh.view.brokers.BrokersEnrollmentFormView = mh.view.WTFormsBaseView.extend({
  el: '.broker-enrollment-form',
  submitEl: '.submit_form',

  mailingAddress: {
    className: 'mailing_address_form',
    sameAddress: true
  },

  formRegEx: /forms?-(\d+)-/g,

  events: {
    'input input': 'handleInputChange_',
    'click input[type=radio]': 'handleInputChange_',
    'click input[type=checkbox]': 'handleInputChange_',
    'click .new-fieldset-button': 'handleAddFieldSet_',
    'click .delete-fieldset-button': 'handleDeleteFieldSet_',
    'click .submission-error-container': 'hideSubmissionError_'
  },

  initialize: function() {
    mh.view.WTFormsBaseView.prototype.initialize.apply(this, arguments);
    this.$errorText_ = this.get$byClass(this.Class.ERRORS);


    this.listenTo(this, mh.view.CustomSelectView.Event.SELECTIONS_UPDATED, this.handleCustomSelectConditionalChange_);

    this.childReview_ = {
      template: this.getChildReviewTemplate_(),
      parentEl: document.getElementById('childInfoReview')
    };

    this.subformTemplateInfo_ = {
      'child-subform': {
        template: false,
        index: 0
      },
      'broker-details-subform': {
        template: false,
        index: 0
      }
    };

    this.yesNoMap_ = {
      y: 'Yes',
      n: 'No'
    };

    this.reviewCache_ = {};

    this.fieldErrorMessage = _.template(this.get$byId('field-error-msg-template').html());
    this.serverErrorMessage = _.template(this.get$byId('server-error-msg-template').html());
    this.sepErrorMessage = _.template(this.get$byId('sep-error-msg-template').html());

    this.$errDiv = this.get$byClass(this.Class.FORM_ERROR);
    this.$errDivText = this.get$byClass(this.Class.FORM_ERROR_TEXT);
    this.serverError_ = false;

  },

  insertTemplateVariables_: function(regex, template) {
    return template.replace(this.formRegEx, function(match) {
      return match.replace(/\d+/, '<%= index %>');
    });
  },

  getChildReviewTemplate_: function() {
    var childProto = document.getElementById('child-review-template');

    if (!childProto) {
      return false;
    }

    return _.template(this.insertTemplateVariables_(this.formRegEx, childProto.innerHTML));
  },

  displaySubmissionError_: function(msg) {
    this.$errDivText.html(msg);
    this.$errDiv.addClass(this.Class.FORM_ERROR_SHOW);
  },

  hideSubmissionError_: function() {
    this.$errDiv.removeClass(this.Class.FORM_ERROR_SHOW);
    this.$errDivText.empty();
  },

  handleSubmissionServerError_: function() {
    this.serverError_ = true;
    this.displaySubmissionError_(this.serverErrorMessage);
  },

  handleSubmissionError_: function(formView, response) {
    // Gets the error text.
    var errorMessage = _.first(mh.utils.getDeepReference(response, 'errors.form_errors', {}));
    var fieldErrors = mh.utils.getDeepReference(response, 'errors.field_errors', {});

    // If there is a special enrollment error, it means a complete startover
    if(fieldErrors[this.SpecialErrors.SEP_ERROR]) {
      this.displaySubmissionError_(this.sepErrorMessage);

    // If not just put up a general error
    } else {
      this.displaySubmissionError_(this.fieldErrorMessage);
    }

    this.loopThroughErrors_('', fieldErrors);

    // Replaces the current error text with the error from the response and add the show error class.
    this.$errorText_.html(errorMessage);
    this.$errorText_.addClass(mh.view.brokers.BrokersEnrollmentFormView.Class.ERRORS);
  },

  presubmit_: function() {
    this.hideSubmissionError_();
  },

  loopThroughErrors_: function(baseInputStr, errors) {
    _.each(errors, function(error, inputId) {
      // Grabbing the last div, since some input types (radio buttons) have multiple error fields
      var $errorDiv = this.get$byClass('form-field--' + baseInputStr + inputId).filter(':last');

      $errorDiv.addClass(this.Class.INPUT_ERROR);
      $errorDiv.find('.' + this.Class.ERRORS).html(_.first(error));

    }, this);

  },

  handleInputChange_: function(e) {
    var fieldErrorClass = this.Class.INPUT_ERROR;
    var $targetInput = $(e.currentTarget);
    $targetInput.closest('.' + fieldErrorClass).removeClass(fieldErrorClass);
    // This is to get the actual value of the custom select input
    if ($targetInput.hasClass(this.Class.CUSTOM_SELECT) ) {
      $targetInput = $(_.first($targetInput.find('select')));
    }

    if ($targetInput.attr('class').indexOf('different_mailing_address') !== -1) {
      this.mailingAddress.sameAddress = $targetInput.val() !== 'y';
    }

    this.checkIfReveal_($targetInput[0]);

    this.updateFormReview_($targetInput[0]);
  },

  checkIfReveal_: function(inputEl) {
    _.each(this.hideShowMap, function(val, key) {
      if (this.checkParentClass(key, inputEl)) {
        this.handleReveal_(inputEl, val);
      }
    }, this);
  },

  handleReveal_: function(inputEl, rules) {
    var value;

    if (inputEl.type) {
      value = inputEl.type === 'checkbox' ? inputEl.checked : inputEl.value;
    } else if (inputEl.tagName === 'SELECT') {
      value = inputEl.value;
    } else {
      throw "Unsupported element type";
    }

    _.each(rules, function(rule) {
      this.toggleShowElement_(
        rule.show,
        $(inputEl).closest('.' + this.Class.FORM_FIELD_BASE),
        _.contains(rule.condition, value));
    }, this);
  },

  toggleShowElement_: function(elName, input, hide) {
    var query = elName[0];
    var context = elName[1];
    var showClass = this.Class.REVEAL_CLASS;
    var $revealedEl;
    // TODO Make a more sane version of this
    switch(query.charAt(0)) {
      case '#':
        $revealedEl = $(query);
        break;

      case '.':
        if (context === 'parent') {
          $revealedEl = $(input).parents(query);
        } else if (context === 'sibling') {
          $revealedEl = $(input).siblings(query);
        } else if (context === 'child') {
          $revealedEl = $(input).children(query);
        } else {
          $revealedEl = $(query);
        }
        break;

      default:
        $revealedEl = $(query);
    }

    $revealedEl.toggleClass(showClass, hide);
  },


  handleCustomSelectConditionalChange_: function(context, e) {
    var currentTarget = e.customSelectView.$el.get();
    this.handleInputChange_({currentTarget: currentTarget});

    // check for target
    var $targetInput = e.customSelectView.$el.find('[data-show-section]');

    if (_.isEmpty($targetInput)) {
      return;
    }

    // toggle class
    var revealTargetSelector = $targetInput.data('show-section');
    var $revealTarget = this.$(revealTargetSelector);
    var shouldReveal = _.first($targetInput.val()) === $targetInput.data('show-if');

    $revealTarget.toggleClass('get-mhar-form-section--hidden', !shouldReveal);
  },

  buildOutputIdString_: function(inputString) {
    return inputString + '--output';
  },

  updateFormReview_: function(el) {
    var baseIdString = el.type === 'radio' ? el.name : el.id;
    var reviewIdString = this.buildOutputIdString_(baseIdString);
    var reviewValue = el.value;

    // Mailing address and permanent address may be the same
    if (el.id.indexOf('permanent_address_form') !== -1 && this.mailingAddress.sameAddress) {
      var outputID = el.id.replace('permanent_address_form', this.mailingAddress.className);
      this.updateFormReviewField_(this.buildOutputIdString_(outputID), el.value);
    }

    // To keep form values consistent, we have to change the y and n to more human readable values
    if (this.yesNoMap_[reviewValue]) {
      reviewValue = this.yesNoMap_[reviewValue];
    }

    this.updateFormReviewField_(reviewIdString, reviewValue);
  },

  checkCache_: function(id) {
    if (!this.reviewCache_[id]) {
      var reviewEl = document.getElementById(id);
      if (!reviewEl) {
        return false;
      }
      this.reviewCache_[id] = reviewEl;
    }
    return true;
  },

  updateFormReviewField_: function(id, value) {
    if(this.checkCache_(id)) {
      this.reviewCache_[id].innerHTML = value;
    }
  },

  compileFormTemplate_: function(templateString) {
    return _.template(this.insertTemplateVariables_(this.formRegEx, templateString));
  },

  /*
  *   Adds another fieldset for specific forms (children, brokers, etc)
  */
  handleAddFieldSet_: function(e) {
    e.preventDefault();

    var $target = $(e.currentTarget);
    var $formContainer = $target.siblings('.' + this.Class.FORM_SECTION_CONTAINER).last();
    var $formParent = $target.closest('.form-section');
    var formType = $formParent.attr('id');
    if (formType && _.has(this.subformTemplateInfo_, formType)) {
      var subform = this.subformTemplateInfo_[formType];

      if (!subform.template) {
        var template = $('#' + formType + '-template').html();
        subform.template = this.compileFormTemplate_(template);
      }

      $formContainer.after(subform.template({index: ++subform.index}));

      var $newSubform = $formParent.find('.' + this.Class.FORM_SECTION_CONTAINER + ':last-of-type');

      _.each($newSubform.find('.' + this.Class.CUSTOM_SELECT), function(el) {
        this.setupSingleCustomSelect_(el);
      }, this);

      // Check if we need to add another child review section
      // This is also another substring search due to wtforms naming convention for multiple
      // subforms
      if ($formContainer.find('input')[0].className.indexOf('child_info') !== -1) {
        var $lastChild = $(this.childReview_.parentEl).find('.form-review-info-container').last();

        $lastChild.after(
          this.childReview_.template({index: subform.index})
        );
      }
    }
  },

  handleDeleteFieldSet_: function(e) {
    var $target = $(e.currentTarget);
    var $parent = $target.closest('.' + this.Class.FORM_SECTION_CONTAINER);
    var $parentSection = $target.closest('.form-section');
    var index = $parentSection.find('.' + this.Class.FORM_SECTION_CONTAINER).index($parent);
    var id = $parentSection.attr('id');

    if (this.subformTemplateInfo_[id]) {
      this.subformTemplateInfo_[id].index--;
    }

    if (id.indexOf('child-subform') !== -1) {
      $('#childInfoReview').find('.form-review-info-container').eq(index).remove();
    }

    $target.parents('.' + this.Class.FORM_SECTION_CONTAINER).remove();
  },

  checkParentClass: function(key, inputEl) {
    var $container = $(inputEl).closest('.' + this.Class.FORM_FIELD_BASE);
    return $container.hasClass(key);
  },

  /*
  *   This is a map to reveal elements based on certain conditions.
  *   Certain inputs can have multiple reveals depending on rules, hence the arrays
  *
  *   The array in show is essentially a tuple, with the first being the query, and the second
  *   being the context( the elment's parent, child, silbling )
  */

  hideShowMap: {
    'form-field--do_not_have_ssn': [
      {
        show: ['.ssn-immigration-status', 'parent'],
        condition: [true]
      }
    ],
    'form-field--plan_details_form-plan_type': [
      {
        show: ['.child-info-section'],
        condition: ['Individual and Child(ren)', 'Family', 'Child Only']
      },
      {
        show: ['.spouse-info-section'],
        condition: ['Family', 'Individual and Spouse']
      }
    ],
    'form-field--different_mailing_address': [
      {
        show: ['.form-section--mailing-address'],
        condition: ['y']
      }
    ],
    'form-field--home_outside_nj': [
      {
        show: ['.form-field--applicant_address_form-home_outside_nj_location'],
        condition: ['y']
      },
      {
        show: ['.form-field--applicant_address_form-num_months_outside_nj'],
        condition: ['y']
      }
    ],
    'form-field--has_same_home_address': [
      {
        show: ['.form-field--different_home_address_reason', 'sibling'],
        condition: ['n']
      },
      {
        show: ['.form-section--dependent-address', 'sibling'],
        condition: ['n']
      }
    ]
  },

  Class: {
    FORM_FIELD_BASE: 'form-field',
    INPUT_ERROR: 'form-field--error',
    ERRORS: 'form-field-error-text',
    ERRORS_SHOW: 'form-field-errors--show',
    FORM_ERROR: 'submission-error-container',
    FORM_ERROR_TEXT: 'submission-error-content',
    FORM_ERROR_SHOW: 'submission-error-container--show',
    FIELD_ERROR: 'form-field-error-text',
    REVEAL_CLASS: 'section--show',
    CUSTOM_SELECT: 'form-field--custom-select',
    FORM_SECTION_CONTAINER: 'form-section-container'
  },

  // List of keys that are "special errors"
  SpecialErrors: {
    SEP_ERROR: 'plan_details_form-special_enrollment_info_id'
  }
});
// depends on: [
//    "js/base/BaseModel.js",
//    "js/base/BaseCollection.js"
// ]

window.mh = mh || {};
mh.view = mh.view || {};
mh.view.hra = mh.view.hra || {};

/**
 * Handles the interactions for the HRA form.
 * @class
 */
mh.view.hra.Form = mh.view.WTFormsBaseView.extend({
  el: '.hra-form',
  submitEl: 'button',

  events: {
    'change .form-field--radio input': 'onFormSubmit_',
    'keypress .hra-dimension_form-height': 'handleDimensionKeypress_',
    'keypress .hra-dimension_form-weight': 'handleDimensionKeypress_',
    'keyup .hra-dimension_form-height': 'formatHeightInput_',
    'input input': 'handleInputChange_'
  },

  /**
   * Init for the HRA form. Sets up listener for custom select widgets, loading events, and window resize. Also
   * kicks the form to reveal the first question.
   */
  initialize: function() {
    mh.view.WTFormsBaseView.prototype.initialize.apply(this, arguments);

    this.clickedButtons_ = {};
    this.charCodes_ = this.page().getConstants().charCodes;
    this.sizes_ = this.page().getConstants().sizes;

    this.listenTo(this, mh.view.CustomSelectView.Event.SELECTIONS_UPDATED, this.handleCustomSelectChange_);

    this.$window_ = $(window);

    this.$window_.resize(_.bind(this.handleWindowResize_, this));

    // Turn off the loading listeners, and add custom listeners.
    this.stopListening(this, mh.view.WTFormsBaseView.Event.FORM_LOADING);
    this.stopListening(this, mh.view.WTFormsBaseView.Event.FORM_FETCHED);

    this.listenTo(this, mh.view.WTFormsBaseView.Event.FORM_LOADING, this.addLoader_);
    this.listenTo(this, mh.view.WTFormsBaseView.Event.FORM_FETCHED, this.removeLoader_);

    this.$continuationField_ = this.get$byClass(mh.view.hra.Form.Class.CONTINUATION);
    this.isContinuation_ = this.$continuationField_.val();

    this.$submittedSubformField_ = this.get$byClass(mh.view.hra.Form.Class.SUBMITTED_SUBFORM);

    // Kick the submit, so that the first question shows with animation.
    this.onFormSubmit_();
  },

  /**
   * When the window resizes, all of the hardcoded heights should similarly be updated.
   *
   * TODO: Consider setting the height to 100% once the animation has completed, which would make this unnecessary.
   * @private
   */
  handleWindowResize_: function() {
    var $subforms = this.get$byClass(mh.view.hra.Form.Class.SUBFORM);

    _.each($subforms, function(subform) {
      var $subform = $(subform);

      if ($subform.hasClass(mh.view.hra.Form.Class.SUBFORM_SHOW)) {
        this.setSubformHeight_($subform);
      }
    }, this);
  },

  /**
   * Overwrites the standard getData string to add the button that was clicked to the form.
   * @returns {string} Serialized form data.
   */
  getData: function() {
    var serializedForm = mh.view.WTFormsBaseView.prototype.getData.apply(this, arguments);

    // Adds all of the values from the clicked buttons to the serialized data
    return [serializedForm, $.param(this.clickedButtons_)].join('&');
  },

  /**
   * Overwrites the standard onFormSubmit_ to keep track of which button was clicked.
   * @param {jQuery.Event} e
   * @private
   */
  onFormSubmit_: function(e) {
    if (e && e.type === 'click') {
      // Keeps track of which buttons were clicked so we can add validation to them.
      var $button = $(e.target);
      this.updateButtonValue_($button);
    }

    if (e) {
      var $triggeringEl = $(e.currentTarget);
      var subformName = $triggeringEl.data('subform');
      if (!subformName) {
        subformName = $triggeringEl.closest('.' + mh.view.hra.Form.Class.SUBFORM).data('subform');
      }

      this.$submittedSubformField_.val(subformName);
    }

    if (this.isContinuation_) {
      this.isContinuation_ = false;
    } else {
      this.$continuationField_.val('');
    }

    mh.view.WTFormsBaseView.prototype.onFormSubmit_.apply(this, arguments);
  },

  /**
   * Adds a button to the clickedButtons_ object so that it gets passed to the server as having been clicked.
   * @param $button
   * @private
   */
  updateButtonValue_: function($button) {
    this.clickedButtons_[$button.prop('id')] = true;
  },

  /**
   * Clears errors every time the form is submitted.
   * @private
   */
  presubmit_: function() {
    this.clearClass(mh.view.hra.Form.Class.SUBFORM_ERROR_TEXT_SHOW);
  },

  /**
   * Handles error events from the server.
   * @param {mh.view.hra.Form} formView This form view
   * @param {Object} e The event
   * @private
   */
  handleSubmissionError_: function(formView, e) {
    this.handleSubmissionResponse_(e);
  },

  /**
   * Handles success events from the server.
   * @param {mh.view.hra.Form} formView This form view
   * @param {Object} e The event
   * @private
   */
  handleSubmissionSuccess_: function(formView, e) {
    this.handleSubmissionResponse_(e);
  },

  /**
   * Progressive disclosure for this form is handled via info in the form error event object. This gets the next
   * form to be revealed from the server, and does the necessary work to reveal it.
   * @param {Object} e The event
   * @private
   */
  handleSubmissionResponse_: function(e) {
    var visibleSubforms = mh.utils.getDeepReference(e, 'info.visible-subforms', []);
    var nextSubform = mh.utils.getDeepReference(e, 'info.next-subform', '');

    // Make sure all of the visible forms are visible.
    _.each(visibleSubforms, function(subformName) {
      var $subform = this.get$subformFromName_(subformName);
      this.showSubform_($subform);
      this.markButtons_($subform);
    }, this);

    // Handle the next subform, if needed.
    this.handleNextSubform_(this.get$subformFromName_(nextSubform));

    this.handleErrorMessages_(mh.utils.getDeepReference(e, 'errors.field_errors', {}));
  },

  /**
   * Finds submit buttons in a subform and marks them as having been clicked.
   * @param $subform
   * @private
   */
  markButtons_: function($subform) {
    var $button = $subform.find('.' + mh.view.hra.Form.Class.SUBMIT_SUBFORM);
    if (!$button.length) {
      return;
    }

    this.updateButtonValue_($button);
  },

  /**
   * Handles revealing a subform.
   * @param {jQuery} $subform: The subform to show.
   * @returns {boolean} Whether or not the subform was visible before showSubform was called.
   * @private
   */
  showSubform_: function($subform) {
    if ($subform.hasClass(mh.view.hra.Form.Class.SUBFORM_SHOW)) {
      return false;
    }

    this.setSubformHeight_($subform);

    _.defer(function() {
      $subform.addClass(mh.view.hra.Form.Class.SUBFORM_SHOW);
    });

    return true;
  },

  /**
   * Handles revealing, scrolling to, and focusing inputs in the next subform.
   * @param $subform
   * @private
   */
  handleNextSubform_: function($subform) {
    if (!$subform.length) {
      return;
    }

    var firstShow = this.showSubform_($subform);

    if (!this.isContinuation_ && !this.isSubformNested_($subform)) {
      mh.utils.smoothScrollToSelector($subform);
    }

    // Only focus the input if the form wasn't already showing.
    if (firstShow) {
      // Give the first text input focus
      var $textInput = $subform.find('input[type=text]').first();
      if ($textInput.length) {
        _.defer(function() {
          $textInput.focus();  // This needs to defer so that it happens after the field is visibility: visible.
        });
      }
    }
  },

  /**
   * Gets an subform from its name
   * @param {string} subformName: Name of the subform.
   * @returns {JQuery} The subform's jQuery object.
   * @private
   */
  get$subformFromName_: function(subformName) {
    return this.get$byClass('form-subform--' + subformName);
  },

  /**
   * Renders error messages that get returned from the server.
   * @param {Object.<string, Array>} errors: The errors returned
   * @private
   */
  handleErrorMessages_: function(errors) {
    _.each(errors, function(error, fieldName) {
      var errorMessage = _.first(error);
      if (!_.isString(errorMessage)) {
        return;
      }

      var $field = this.get$byClass(fieldName);
      var $subform = $field.closest('.' + mh.view.hra.Form.Class.SUBFORM);
      var $errorText = $subform.find('.' + mh.view.hra.Form.Class.SUBFORM_ERROR_TEXT).first();
      $errorText.html(errorMessage);

      $errorText.addClass(mh.view.hra.Form.Class.SUBFORM_ERROR_TEXT_SHOW);
    }, this);
  },

  /**
   * Sets the appropriate height for a given subform. Handling varies between top-level and nested subforms.
   * @param {jQuery} $subform The subform to set the height for.
   * @private
   */
  setSubformHeight_: function($subform) {
    // For nested subforms, set the height so it can be animated.
    var revealedHeight;

    if (this.isSubformNested_($subform)) {
      revealedHeight = $subform.find('.' + mh.view.hra.Form.Class.SUBFORM_CONTENT).outerHeight();
    } else {
      var windowHeight = this.$window_.height();
      revealedHeight = windowHeight <= this.sizes_.STANDARD_MIN_HEIGHT ? this.sizes_.STANDARD_MIN_HEIGHT : windowHeight;
    }

    $subform.height(revealedHeight);
  },

  /**
   * Determines whether or not a subform is nested
   * @param {jQuery} $subform The subform to check
   * @returns {boolean} Whether or not the subform is nested
   * @private
   */
  isSubformNested_: function($subform) {
    return !!$subform.closest('.' + mh.view.hra.Form.Class.SUBFORM_CONTENT).length;
  },

  /**
   * Formats the text in the 'height' field to be in the form <feet>'<inches>"
   * @param {jQuery.Event} e The input event.
   * @private
   */
  formatHeightInput_: function(e) {
    var $heightInput = $(e.currentTarget);

    // Strip out non-digits
    var currentVal = this.cleanInput_($heightInput.val());

    var valArr = currentVal.split('');

    // If the user tries to delete, delete the number as well as the apostrophe, as needed.
    // TODO: Handle DELETE
    if (e.which === this.charCodes_.BACKSPACE && !_.isEmpty(valArr)) {
      valArr.pop();
    }

    // If there are no non-digits, clear the value.
    if (_.isEmpty(valArr)) {
      $heightInput.val('');
      return;
    }

    // Insert an apostrophe in the second position.
    // TODO: Use smart quotes
    valArr.splice(1, 0, '\'');

    // If there is more than just feet, add the inches notation.
    if (valArr.length > 2) {
      // TODO: Use smart quotes
      valArr.push('"');
    }

    $heightInput.val(valArr.join(''));
  },

  /**
   * Handles keypresses in either of the dimension fields (height or weight)
   * @param {jQuery.Event} e The keypress event.
   * @returns {boolean} Whether or not the browser should continue to process the event or block it.
   * @private
   */
  handleDimensionKeypress_: function(e) {
    // Allow enter and backspace if there's no key.
    var allowedKeys = [
      this.charCodes_.BACKSPACE,
      this.charCodes_.ENTER
    ];

    if (!e.which || _.contains(allowedKeys, e.which)) {
      return true;
    }

    // Remove non-digits from the value.
    var cleanedInputVal = this.cleanInput_($(e.currentTarget).val());

    // If there are more than 3 characters as a result of this input, reject it.
    // Note: This happens prior to the value being updated, which is why the evaluation is inclusive.
    if (cleanedInputVal.length >= 3) {
      return false;
    }

    // If they are non-digits, reject it.
    if (e.which < this.charCodes_.ZERO || e.which > this.charCodes_.NINE) {
      return false;
    }

    return true; // Keeps the linter happy.
  },

  /**
   * Converts a raw input to a digits only input.
   * @param {string} input The raw input
   * @returns {string} The cleaned input
   * @private
   */
  cleanInput_: function(input) {
    return input.replace(/\D/g, '');
  },


  /**
   * Submits the form on input events.
   * @param {jQuery.Event} e
   * @private
   */
  handleInputChange_: function(e) {
    // This is a terribly unfortunate delay. It's needed so that the height form is fully formatted before submitting
    // the form.
    this.setTimeout(function() {
       this.onFormSubmit_(e);
    }, 100);
  },

  /**
   * Submits the form when custom select is changed.
   * @param {mh.view.hra.Form} formView: This form view
   * @param {Object.<string>} selectedInfo: selectedInfo from the custom select widget.
   * @private
   */
  handleCustomSelectChange_: function(formView, selectedInfo) {
    // Fakes an event so that the CustomSelect's el can be used to get the submitted subform.
    this.onFormSubmit_({
      'currentTarget': mh.utils.getDeepReference(selectedInfo, 'customSelectView.$el')
    });
  },

  /**
   * Adds the loading class for the form. The is separate from the remove method (and does not use toggle) because
   * multiple queries could be triggered before the first response comes back.
   * @private
   */
  addLoader_: function() {
    this.$el.addClass(mh.view.hra.Form.Class.HRA_FORM_LOADING);
  },

  /**
   * Removes the loading class for the form.
   * @private
   */
  removeLoader_: function() {
    this.$el.removeClass(mh.view.hra.Form.Class.HRA_FORM_LOADING);
  }
}, {
  Class: {
    CONTINUATION: 'hra-hra_continuation',
    HRA_FORM_LOADING: 'hra-form--loading',
    SUBFORM: 'form-subform',
    SUBFORM_CONTENT: 'form-subform-content',
    SUBFORM_ERROR_TEXT: 'subform-error-text',
    SUBFORM_ERROR_TEXT_SHOW: 'subform-error-text--show',
    SUBFORM_SHOW: 'form-subform--show',
    SUBMIT_SUBFORM: 'submit-form',
    SUBMITTED_SUBFORM: 'hra-submitted_subform'
  }
});
// depends on: ["web_shared:js/base/BaseView.js", "web_shared:js/utils/utils.js"]

window.mh = mh || {};
mh.view = mh.view || {};
mh.view.marketing = mh.view.marketing || {};

/**
 * View finder appearing on the marketing page.
 *
 * Editorial Notes: It hurt my soul a little not using a CSS animation for this, but I chose this sadder approach for
 * two main reasons:
 *   1. It will work in all browsers, at least to some degree.
 *   2. Three different pieces of the view finder get animated: the image to rotate in, the opacity of the text on the
 *      slide, and the container's background-color. Handling the timing of these three (which are all slightly
 *      different) was much simpler using transitions than animations (which would have required 3 separate animations
 *      with quite a bit of interdependency and some fudging to get the percentages for the keyframes to work out
 *      correctly).
 *
 * Now it works by setting an index id on the view finder. CSS handles all of the rest.
 */
mh.view.marketing.MarketingViewFinder = mh.view.BaseView.extend({
  el: '.panel-view-finder',

  /**
   * Init
   */
  initialize: function() {
    this.slides_ = this.get$byClass(mh.view.marketing.MarketingViewFinder.Class.SLIDE);

    /** {number} The current slide index. Initially set to the last slide so the first call to selectNextSlide_
     *           is index 0.
     **/
    this.currentSlideIndex_ = this.slides_.length - 1;

    this.setInterval(this.selectNextSlide_, 5000);
  },

  /**
   * Selects the next slide based on the current slide.
   * @private
   */
  selectNextSlide_: function() {
    var iteration = mh.utils.getNextIteration(this.currentSlideIndex_, this.slides_.length);
    var nextIndex = iteration.next;
    var currentIndex = iteration.current;

    this.$el.removeClass(this.getClassNameFromIndex_(currentIndex))
            .addClass(this.getClassNameFromIndex_(nextIndex));

    this.currentSlideIndex_ = nextIndex;
  },

  /**
   * Gets the slide class name from an index value.
   * @param {number} index - The index of the slide class.
   * @returns {string} The view finder's class name with the provided slide index appended to the end.
   * @private
   */
  getClassNameFromIndex_: function(index) {
    // These are plus 1 to play nicely with jinja2 loops and SASS lists, which both start at 1.
    return [mh.view.marketing.MarketingViewFinder.Class.VIEW_FINDER, index + 1].join('--');
  }
}, {
  Class: {
    SLIDE: 'panel-slide',
    VIEW_FINDER: 'panel-view-finder'
  }
});

window.mh = mh || {};
mh.view = mh.view || {};
mh.view.quote = mh.view.quote || {};
mh.view.marketing = mh.view.marketing || {};

mh.view.marketing.HeaderMenu = mh.view.BaseView.extend({
  el: '.mh-menu',

  events: {
    'click .mh-close-menu': 'toggleMenu_'
  },
  initialize: function() {
    this.$window_ = $(window);
    $('body').on('click', '.' + mh.view.marketing.HeaderMenu.Class.TRIGGER, _.bind(this.toggleMenu_, this));

    this.isShowing_ = false;
  },
  toggleMenu_: function() {
    this.$el.toggleClass(mh.view.marketing.HeaderMenu.Class.SHOW_MENU);

    this.isShowing_ = !this.isShowing_;

    if (this.isShowing_) {
      this.$window_.on(mh.view.marketing.HeaderMenu.WINDOW_KEYDOWN, _.bind(this.handleKeydown_, this));
    } else {
      this.$window_.off(mh.view.marketing.HeaderMenu.WINDOW_KEYDOWN);
    }
  }
}, {
  Class: {
    SHOW_MENU: 'mh-menu--show',
    TRIGGER: 'js-launch-mh-menu'
  },

  PAGE_FREEZE_NAME: 'mh-header-menu',
  WINDOW_KEYDOWN: 'keydown.mh-header-menu'
});

window.mh = mh || {};
mh.view = mh.view || {};
mh.view.marketing = mh.view.marketing || {};

/**
 * The carousel with the user and press comments appearing on the marketing page.
 *
 * TODO: Make this more generic so it can be reused (templates and CSS included).
 */
mh.view.marketing.MarketingCommentsCarousel = mh.view.BaseView.extend({
  el: '.panel--comments',

  events: {
    'click .panel-comment-slide-control': 'handleControlClick_'
  },
  initialize: function() {
    this.$slides_ = this.get$byClass(mh.view.marketing.MarketingCommentsCarousel.Class.SLIDE);
    this.currentSlideIndex_ = 0;
    this.slideWidth_ = undefined;

    // Note: the interval_ and timeout_ variables are the internal index for the given timers. The actual lengths of
    // the interval/timeout are hardcoded in the set methods below.
    this.interval_ = undefined;
    this.timeout_ = undefined;

    this.$firstSlide_ = this.$slides_.first();

    this.setSlideWidth_();

    $(window).resize(_.bind(function() {
      this.setSlideWidth_();
      this.moveToCurrentSlide_();
    }, this));
  },
  setCarouselInterval_: function() {
    if (this.interval_) {
      clearInterval(this.interval_);
    }
    this.interval_ = this.setInterval(this.selectNextSlide_, 7000);
  },
  setCarouselTimeout_: function() {
    if (this.timeout_) {
      clearTimeout(this.timeout_);
    }

    this.timeout_ = this.setTimeout(this.setCarouselInterval_, 7000);
  },
  selectNextSlide_: function() {
    var iteration = mh.utils.getNextIteration(this.currentSlideIndex_, this.$slides_.length);
    this.selectSlide_(iteration.next);
  },
  handleControlClick_: function(e) {
    clearInterval(this.interval_);

    this.selectSlide_($(e.currentTarget).data('slide-id'));

    this.setCarouselTimeout_();
  },
  selectSlide_: function(index) {
    this.currentSlideIndex_ = index;
    var $nextSlide = this.get$byClass(this.getSlideClassFromIndex_(this.currentSlideIndex_));
    var $nextSlideControl = this.get$byClass(this.getSlideControlClassFromIndex_(this.currentSlideIndex_));

    this.moveToCurrentSlide_();

    this.clearClass(mh.view.marketing.MarketingCommentsCarousel.Class.SLIDE_CURRENT);
    $nextSlide.addClass(mh.view.marketing.MarketingCommentsCarousel.Class.SLIDE_CURRENT);

    this.clearClass(mh.view.marketing.MarketingCommentsCarousel.Class.SLIDE_CONTROL_CURRENT);
    $nextSlideControl.addClass(mh.view.marketing.MarketingCommentsCarousel.Class.SLIDE_CONTROL_CURRENT);
  },
  setSlideWidth_: function() {
    this.slideWidth_ = this.$firstSlide_.outerWidth();
  },
  moveToCurrentSlide_: function() {
    this.$firstSlide_.css({
      'margin-left': -1 * this.currentSlideIndex_ * this.slideWidth_
    });
  },
  getSlideControlClassFromIndex_: function(index) {
    return this.getClassFromIndex_(index, mh.view.marketing.MarketingCommentsCarousel.Class.SLIDE_CONTROL);
  },
  getSlideClassFromIndex_: function(index) {
    return this.getClassFromIndex_(index, mh.view.marketing.MarketingCommentsCarousel.Class.SLIDE);
  },
  getClassFromIndex_: function(index, baseClass) {
    return [baseClass, index].join('--');
  },

  /**
   * Starts the carousel.
   * @returns {boolean}
   */
  startCarousel: function() {
    this.setCarouselInterval_();
  },

  /**
   * Returns whether or not the carousel has been started.
   * @returns {boolean}
   */
  isCarouselRunning: function() {
    return !!this.interval_;
  }
}, {
  Class: {
    SLIDE: 'panel-comment-slide',
    SLIDE_CURRENT: 'panel-comment-slide--current',
    SLIDE_CONTROL: 'panel-comment-slide-control',
    SLIDE_CONTROL_CURRENT: 'panel-comment-slide-control--current'
  }
});

window.mh = mh || {};
mh.view = mh.view || {};
mh.view.brokers = mh.view.brokers || {};
mh.view.quote = mh.view.quote || {};
mh.view.marketing = mh.view.marketing || {};

mh.view.marketing.MarketingPage = mh.view.BaseView.extend({
  el: '.page',

  events: {
    'click .panel-navigation': 'scrollToNextPanel_',
    'click .panel-talk-call-button': 'togglePeopleMediaContainer_'
  },
  initialize: function(options) {
    this.$window_ = $(window);
    this.$window_.scroll(_.bind(
      _.throttle(this.handleScroll_, mh.view.marketing.MarketingPage.ONSCROLL_THROTTLE_TIMING),
      this)
    );

    this.$header_ = this.get$byClass(mh.view.marketing.MarketingPage.Class.HEADER);

    var $panels = this.get$byClass(mh.view.marketing.MarketingPage.Class.PANEL);
    this.$firstBookendPanel_ = $panels.first();
    this.$lastBookendPanel_ = $panels.last();
    this.$missionPanel_ = this.get$byClass(mh.view.marketing.MarketingPage.Class.PANEL_MISSION);
    this.missionActivated_ = false;
    this.panelTriggerPositions_ = undefined;

    this.$window_.resize(_.bind(function() {
      this.setUpScrollTracking_($panels);
    }, this));
    this.setUpScrollTracking_($panels);
    this.handleScroll_();

  },
  setUpScrollTracking_: function($panels) {
    this.panelNameByScrollTrigger_ = {};
    this.panelTriggerPositions_ = [];

    _.each($panels, function(panel) {
      var $panel = $(panel);
      var $panelContent = $panel.find('.' + mh.view.marketing.MarketingPage.Class.PANEL_CONTENT);
      var panelContentTop = $panelContent.offset().top;

      var panelContentBottom = panelContentTop + $panelContent.height();
      this.panelTriggerPositions_.push(panelContentBottom);
    }, this);

    this.panelTriggerPositions_.sort(function(a, b) {
      return a - b;
    });
  },
  scrollToNextPanel_: function(e) {
    // Gets the next panel by going up the tree to the parent panel and getting the next sibling.
    var $nextPanel = $(e.currentTarget).closest('.' + mh.view.marketing.MarketingPage.Class.PANEL).next();
    mh.utils.smoothScroll($nextPanel.offset().top);
  },
  handleScroll_: function() {
    var scrollPos = this.$window_.scrollTop();
    this.updateHeaderStatus_(scrollPos);
    this.updateMissionAnimation_(scrollPos);
  },
  updateHeaderStatus_: function(scrollPos) {
    var firstPanelOffset = this.$firstBookendPanel_.outerHeight();
    var landingTriggerPos = firstPanelOffset * mh.view.marketing.MarketingPage.LANDING_CLASS_TOGGLE_THRESHOLD;
    var landingOn = scrollPos < landingTriggerPos;
    this.$header_.toggleClass(mh.view.marketing.MarketingPage.Class.HEADER_LANDING, landingOn);
    var obj = $(".mh-header-main2");
    if (!landingOn && !obj.is(':visible')) {
        if (!menu_scroll_progress) {
            obj.slideDown("fast");
            menu_scroll_progress = true;
        }
    } else {
        if (scrollPos==0 && obj.is(':visible')) {
            obj.slideUp("fast", function() {
                menu_scroll_progress = false;
            });
        }
    }
  },
  updateMissionAnimation_: function(scrollPos) {
    if (this.missionActivated_) {
      return;
    }

    if (scrollPos > 0) {
      this.$missionPanel_.addClass(mh.view.marketing.MarketingPage.Class.PANEL_MISSION_ACTIVATED);
    }
  }
}, {
  Class: {
    HEADER: 'mh-header',
    HEADER_BOOKEND: 'mh-header--bookend',
    HEADER_LANDING: 'mh-header--landing',
    HEADER_LANDING_BOTTOM: 'mh-header--landing-bottom',
    HEADER_MAIN: 'mh-header-main',
    HEADER_MISSION: 'mh-header--mission',
    PANEL: 'panel',
    PANEL_CONTENT: 'panel-content',
    PANEL_MISSION: 'panel-homepage-users',
    PANEL_MISSION_ACTIVATED: 'panel-homepage-users--activated'
  },
  Selector: {
    PEOPLE_NAV_TRIGGER: '.panel--people .panel-navigation'
  },
  LANDING_CLASS_TOGGLE_THRESHOLD: .1,
  MARKETING_MODAL_COOKIE_EXPIRY_DAYS: 60,
  MARKETING_MODAL_COOKIE_NAME: 'talk_to_a_guide',
  ONSCROLL_THROTTLE_TIMING: 100
});

mh.Page = new mh.view.Page();

$(document).ready(function () {
    mh.Page.boot(mhComponents);
    $("a#LinkOpenMenu, a#LinkOpenMenu2").on("click",function() {
        $(".mh-menu").animate();
    });

    $('#btnFormContact, .link_contact').click(function(e){
        e.preventDefault();

        $.fancybox({
            width: 340,
            height: 470,
            padding: 0,
            fitToView : false,
            autoSize : false,
            'content': $("#divFormContact").html(),
            beforeShow: function(){
                $(".fancybox-inner").addClass("white_bg");
            }
        });

        return false;
    });

    $('#btnFormCommunity, .link_community').click(function(e){
        e.preventDefault();

        $.fancybox({
            width: 340,
            height: 470,
            padding: 0,
            fitToView : false,
            autoSize : false,
            'content': $("#divFormCommunity").html(),
            beforeShow: function(){
                $(".fancybox-inner").addClass("white_bg");
            }
        });

        return false;
    });

    $('.meet_team .block').on("mouseover",function(){
        $(this).find(".img_hover").get(0).style.display='';
    }).on("mouseout",function(){
        $(this).find(".img_hover").get(0).style.display='none';
    });

    $('#BodyTeam .meet_team .people_link, #BodyTeam .meet_team .img_hover').click(function(e){
        e.preventDefault();

        $.fancybox({
            width: 340,
            height: 470,
            padding: 0,
            fitToView : false,
            autoSize : false,
            'content': $(".people_popup."+$(this).parent().find('.people_link').attr("data-rel")).html(),
            beforeShow: function(){
                $(".fancybox-inner").addClass("white_bg");
            }
        });

        return false;
    });
});

function display_menu() {
    $("#menu").slideToggle("slow");
}