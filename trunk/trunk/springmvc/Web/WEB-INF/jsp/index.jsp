<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<title>Untitled</title>
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.5.2/build/fonts/fonts-min.css" />
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.5.2/build/treeview/assets/skins/sam/treeview.css" />
	<script type="text/javascript" src="http://yui.yahooapis.com/2.5.2/build/yahoo/yahoo-min.js"></script>
	
	<script type="text/javascript" src="http://yui.yahooapis.com/2.5.2/build/event/event-min.js"></script>
	<script type="text/javascript" src="http://yui.yahooapis.com/2.5.2/build/connection/connection-min.js"></script>
	<script type="text/javascript" src="http://yui.yahooapis.com/2.5.2/build/treeview/treeview-min.js"></script>
	
</head>

<body>

	
	<form name="A010901Form" action="hello1.html" method="POST">
		Hello World!
		<h2>Test Page say:${say}</h2>
		<h2>Person Name:${person.name}</h2>
		<h2>Person Age:${person.age}</h2>
		<input type="text" name="name" value="${person.name}"/>
		<input type="text" name="age" value="${person.age}"/>
		<input type="submit" value="OK"/>
		
		<form:input path="person.name" />
		<form:password path="person.name"/>
		
		
		<form:select path="city" items="${cityMap}">
			
		</form:select>
		
		
		<table>
		<c:forEach items="${person.catList}" var="cat">
		<tr>
			<td><c:out value="${cat.name}" /></td>
			<td><c:out value="${cat.name}" /></td>
		</tr>
		</c:forEach>
		</table>
		
		<div id="treeDiv1"></div>
	</form>
	<script type="text/javascript">

YAHOO.example.treeExample = function() {

    var tree, currentIconMode;

    function changeIconMode() {
        var newVal = parseInt(this.value);
        if (newVal != currentIconMode) {
            currentIconMode = newVal;
        }
        buildTree();
    }
    
        function loadNodeData(node, fnLoadComplete)  {
            
            //We'll load node data based on what we get back when we
            //use Connection Manager topass the text label of the 
            //expanding node to the Yahoo!
            //Search "related suggestions" API.  Here, we're at the 
            //first part of the request -- we'll make the request to the
            //server.  In our success handler, we'll build our new children
            //and then return fnLoadComplete back to the tree.
            
            //Get the node's label and urlencode it; this is the word/s
            //on which we'll search for related words:
            var nodeLabel = encodeURI(node.label);
            
            //prepare URL for XHR request:
            var sUrl = "hello2.html?query=" + nodeLabel;
            
            //prepare our callback object
            var callback = {
            
                //if our XHR call is successful, we want to make use
                //of the returned data and create child nodes.
                success: function(oResponse) {
                    YAHOO.log("XHR transaction was successful.", "info", "example");
           
                    //YAHOO.log(oResponse.responseText);
                    var oResults = eval("(" + oResponse.responseText + ")");
                    
                
                    
                    
                    if((oResults.dateList) && (oResults.dateList.length)) {
                        //Result is an array if more than one result, string otherwise
                        if(YAHOO.lang.isArray(oResults.dateList)) {
                            for (var i=0, j=oResults.dateList.length; i<j; i++) {
                                var tempNode = new YAHOO.widget.TextNode(oResults.dateList[i], node, false);
                            }
                        } else {
                            //there is only one result; comes as string:
                            var tempNode = new YAHOO.widget.TextNode(oResults.dateList, node, false)
                        }
                    }
                    
                    //When we're done creating child nodes, we execute the node's
                    //loadComplete callback method which comes in via the argument
                    //in the response object (we could also access it at node.loadComplete,
                    //if necessary):
                    oResponse.argument.fnLoadComplete();
                },
                
                //if our XHR call is not successful, we want to
                //fire the TreeView callback and let the Tree
                //proceed with its business.
                failure: function(oResponse) {
                    YAHOO.log("Failed to process XHR transaction.", "info", "example");
                    oResponse.argument.fnLoadComplete();
                },
                
                //our handlers for the XHR response will need the same
                //argument information we got to loadNodeData, so
                //we'll pass those along:
                argument: {
                    "node": node,
                    "fnLoadComplete": fnLoadComplete
                },
                
                //timeout -- if more than 7 seconds go by, we'll abort
                //the transaction and assume there are no children:
                timeout: 7000
            };
            
            //With our callback object ready, it's now time to 
            //make our XHR call using Connection Manager's
            //asyncRequest method:
            YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
        }

        function buildTree() {
           //create a new tree:
           tree = new YAHOO.widget.TreeView("treeDiv1");
           
           //turn dynamic loading on for entire tree:
           tree.setDynamicLoad(loadNodeData, 1);
           
           //get root node for tree:
           var root = tree.getRoot();
           
           //add child nodes for tree; our top level nodes are
           //all the states in India:
           var aStates = ["Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","West Bengal"];
           
           for (var i=0, j=aStates.length; i<j; i++) {
                var tempNode = new YAHOO.widget.TextNode(aStates[i], root, false);
           }

           // Use the isLeaf property to force the leaf node presentation for a given node.
           // This disables dynamic loading for the node.
           var tempNode = new YAHOO.widget.TextNode('This is a leaf node', root, false);
           tempNode.isLeaf = true;
           
           //render tree with these toplevel nodes; all descendants of these nodes
           //will be generated as needed by the dynamic loader.
           tree.draw();
        }


    return {
        init: function() {
            YAHOO.util.Event.on(["mode0", "mode1"], "click", changeIconMode);
            var el = document.getElementById("mode1");
            if (el && el.checked) {
                currentIconMode = parseInt(el.value);
            } else {
                currentIconMode = 0;
            }

            buildTree();
        }

    }
} ();

//once the DOM has loaded, we can go ahead and set up our tree:
YAHOO.util.Event.onDOMReady(YAHOO.example.treeExample.init, YAHOO.example.treeExample,true)

</script>
	
</body>
</html>