var stompClient = null;
//Global variables
/** CodeMirror */
var contentEditor;
var conversationEditor;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
//        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
}

/**
 * Open connection and transfer user name into the server.
 * @returns number of joined user or -1.
 */
function connect() {
	var nUser = -1;
	var name = $("#name").val();

	var socket = new SockJS('/gs-guide-websocket');

    stompClient = Stomp.over(socket);
    stompClient.connect({'name': name}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (result) {
        	showResult(result.body);
        });
    });
    
    return nUser;
}

/**
 * New user join to group
 * @returns
 */
function newUer() {
    var socket = new SockJS('/jon-websocket');
    var joinClient = Stomp.over(socket);
    joinClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        joinClient.subscribe('/user/list', function (greeting) {
        	console.log("greeting=" + greeting);
//            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
	var name = $("#name").val();
	var content = contentEditor.getValue();
	
	console.log("content=" + content);

	var jsonMessage = JSON.stringify({'name': name, 'content': content });
	
    stompClient.send("/app/hello", {}, jsonMessage);
}

function showResult(message) {
    var doc = conversationEditor.getDoc();
	doc.setValue(message);
}

function validateUser() {
	var name = $("#name").val();
	
	if (name) {
		return true;
	} else {
		return false;
	}
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() {
    	if (validateUser()) {
    		connect();
    	} else {
    		alert("Name is required.");
    	}
    });

    $( "#disconnect" ).click(function() { disconnect(); });

    

    $("#content").keyup(function(event) {
        if (event.which == 13) {
        	if (!event.shiftKey) {
                console.log("return");
                
                event.preventDefault();
                sendMessage();
        	}

        } 
    });

});

/**
 * Init the CodeEditor
 * @returns
 */
$(function () {
	contentEditor = CodeMirror.fromTextArea(document.getElementById("content"), {
	    lineNumbers: true,
	    matchBrackets: true,
	    mode: "text/x-java", // Refer: https://codemirror.net/mode/clike/
	    indentUnit: 4,
	    extraKeys: {
	        "Ctrl-Space": "autocomplete", // Refer: https://stackoverflow.com/questions/41953077/codemirror-editor-show-hint-after-specific-key-pattern-like
	        "Ctrl-S": function(cm) {
	        	//alert("Save.." + cm.getValue());
	        	saveData(cm);
	        },
	    	Tab: function(cm) {
	    	    var spaces = Array(cm.getOption("indentUnit") + 1).join(" ");
	    	    cm.replaceSelection(spaces);
	    	  },
	    	Enter: function(cm) {
	    		console.log("Enter");
	    		sendMessage();
	    		return CodeMirror.Pass;
	    	}
	    },
	    cursorActivity: function(cm) {
	    	
	    }
	});
//	cmResize(contentEditor, {resizableWidth: false});
	contentEditor.on("inputRead", function(cm, changeObj) {
		console.log("changeObj=" + JSON.stringify(changeObj));

//        if (event.which == 13) {
//        	if (!event.shiftKey) {
//                console.log("return");
//                
//                event.preventDefault();
//                sendMessage();
//        	}
//
//        } 
		
		
	});
	
	conversationEditor = CodeMirror.fromTextArea(document.getElementById("conversation"), {
	    lineNumbers: true,
	    mode: "text/x-java", // Refer: https://codemirror.net/mode/clike/
	    readOnly: true
	});
});



