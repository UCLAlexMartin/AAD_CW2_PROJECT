
(function($) {
  var Global = {
    
    showErrorType : {
      ALERT : 1, 
      PANLE : 2, 
      STYLE : 3, 
      LABEL : 4, 
      DIV   : 5  
    }, 
    
    
    registerType : {
      ADD       : 0, 
      FORM      : 1, 
      ADD_FORM  : 2  
    }
  };
  
	$.Config = {
	  registerType           : Global.registerType.ADD_FORM, 
    isFocus                : true,  
    isEnableSubValidator   : true, 
    titleSepartor          : ",", 
    isManyErrorMessage     : true, 
    showErrorType          : Global.showErrorType.ALERT
	};
})(jValidator);
