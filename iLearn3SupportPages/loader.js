function load(toLoad, customDisplayLocation, isRemoteRequst, callBack){
	//console.log(toLoad+", "+customDisplayLocation+", "+(callBack != null))
	var dataRequester = new XMLHttpRequest()
	dataRequester.onreadystatechange = function() {
	  if (dataRequester.readyState == 4 && dataRequester.status == 200) {
	  	var displayLocation = "barneyMain"
	  	if (customDisplayLocation){
	  		displayLocation = customDisplayLocation
	  	}
	    document.getElementById(displayLocation).innerHTML = dataRequester.responseText;
	    if(callBack){
	    	callBack()
	    }
	  }
	}
    var prefix = ""
    if(isRemoteRequst){
        prefix = "https://yenrab.github.io/doing_more_with_java/iLearn3SupportPages/"
    }
    //console.log("about to load "+prefix+toLoad+".html")
	dataRequester.open("GET", prefix+toLoad+".html", true);
	dataRequester.send();
}
function stopDefault(theEvent){
	theEvent.preventDefault()
	return false
}