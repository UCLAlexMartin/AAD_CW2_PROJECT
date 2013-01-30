(function($) {
  $.messages = {
    
    // ~ Validator Messages
    // ---------------------------------------------------------------------------------
    checkNotEmpty     : "[{title}] it cann't be null",
    checkEnglish          : "[{title}] only can be English!",  
    checkEngAndNum        : "[{title}] only can be English and number!", 
    checkWordChar         : "[{title}] only can be number and underline!",
    checkWordChar         : "[{title}] only can be ¡°-¡±¡¢¡°_¡±¡¢blank¡¢character and number!",
    checkMustSelected     : "please choose [{title}]!", 
    checkCustomFunction   : "input [{title}]worng!", 
    checkEmail            : "[{title}]input format is wrong£¬please input correct email address!", 
    checkURL              : "[{title}]input format is wrong£¬please input correct URL address!",
    dateStyle             : "[{title}]input format is wrong",
    
    // ~ Code Messages
    // ---------------------------------------------------------------------------------
    C001 : "[{title}]cannot contain¡°{0}¡±{1}!"
        
  };
})(jValidator);
