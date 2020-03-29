
var webServer = "http://localhost:9080/arcatalog";
var context   = "";      // url-pattern in webapp file (Jersey Web Application)
var endPoint  = webServer //+ "/" + context;

console.log("Passo 01");

function actionPostCatalog(formName, routePoint){
      var $form = $('#addCatForm input:invalid').length;
       
      var formValid = false;

      if($('#addCatForm input:invalid').length > 0){
        formValid = false;
      }

      // atributo "name", cujo "value" = "endpoint" do elemento "input" do form id "#addCatForm"
      var $input = $('#addCatForm input[name=endPoint]');

      console.log($form);
      console.log($form.length);
      console.log($input[0].value);

      console.log();

       var frm = document.getElementById(formName);
       if (frm == null) {
           alert("Missing id in form");
           return;
       }

       frm.addEventListener("submit", function(event){
            event.preventDefault();
            event.stopPropagation()
        });

        console.log(endPoint);

        var inputs = frm.getElementsByClassName("inputForm");
        var mapInputValues = new Map();

        var i;
        for (i = 0; i < inputs.length; i++) { 
            mapInputValues.set(inputs[i].name, inputs[i].value);
        }

        console.log(" PASSO 02: " + endPoint);
      var patt1 = /\{\S+?\}/g; // Pesquisa pathParam

console.log(" PASSO 03: " + endPoint);

      var routes = routePoint.match(patt1);
      var routesPathParams = [];
      if(routes != null){ // existe pathParam ?
        var i;
        for (i = 0; i < routes.length; i++) { // remove "{}" dos pathParam
            routesPathParams.push(routes[i].replace("{","").replace("}",""));
        }
        var numPathParam = 0;

        for (i = 0; i < routesPathParams.length; i++) { 
            if(routesPathParams[i] in inputs) { // pathParam está definido em <input> ?
                numPathParam++;
                console.log(mapInputValues + " is ok " );
            }
            else {
                console.log(routesPathParams[i] + " invalid routePoint part " );
            }
        }

        var routePoint;
        for (i = 0; i < routes.length; i++){
            routePoint = routePoint.replace(routes[i],mapInputValues.get(routesPathParams[i]));
        }

        console.log("routes finais: " + routePoint);
        endPoint = endPoint + "/" + routePoint;
        console.log("final: " + endPoint);
      }
      else {
         endPoint = endPoint +  "/" + routePoint;
      }
        frm.action = endPoint;
        if (formValid) {
            frm.submit();
        }
      console.log(" PASSO 04: " + endPoint);
}