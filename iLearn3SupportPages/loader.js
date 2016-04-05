function load(toLoad, customDisplayLocation, isRemoteRequst, callBack){
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
			dataRequester.open("GET", prefix+toLoad+".html", true);
			dataRequester.send();
		}