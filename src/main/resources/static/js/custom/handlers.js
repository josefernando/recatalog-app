var $listAllCat = $('#listAllCatalogLink');

var startPath = 0;
var endPath = 0;

$('#listAllCatalog').on("click", function(e){
    var $target = $('e.target');
});


//***************************************************************************
//Executa evento no "show" do collapse element
$('#toggleNewCatalog').on('show.bs.collapse', function () {
	
	console.log(" PASSO 99x: " + this);
 	$(this).prev().addClass("text-center font-weight-light display-4");

});

//Executa evento no "hide" do collapse element
$('#toggleNewCatalog').on('hide.bs.collapse', function () {
 $(this).prev().removeClass("text-center font-weight-light display-4");  
 
 // remove messagens de sucesso ou de falha
$(".alert").remove();
});

//====================================================================
$('#toggleCreateCatalog').on('show.bs.collapse', function () {
 	$(this).prev().addClass("text-center font-weight-light display-4");
 	
 	
// 	var str =$(this).prev().html(); // pega texto do menu
// 	
// 	var regex = /[^<]+/;
//
// 	var match = regex.exec(str);
// 	console.log(match[0]);        
//
// 	var xxx = '<i class="fas fa-angle-left"></i> ' + match[0];
// 	
// 	$(this).prev().html(xxx);
 	
 	//($(this).prev()).prepend$('<i class="fas fa-angle-left"></i>');
});

$('#toggleCreateCatalog').on('hide.bs.collapse', function () {
	$(this).prev().removeClass("text-center font-weight-light display-4");  
	$(".alert").remove();
});
//=====================================================================

// Executa evento no "show" do collapse element
$('#toggleListAllCatalog').on('show.bs.collapse', function () {
     var webServer = "http://localhost:9080";
     var urlBase   = "arcatalog";
     var endPoint  = "catalogs";
     var routePoint = `${webServer}/${urlBase}/${endPoint}`;
     
     
 	console.log(" PASSO 991: " + this);

     console.log($(this).prev());
     
  	$(this).prev().addClass("text-center font-weight-light display-4");

    $.ajax({
        url: routePoint
    }).then(function(data) {
        var $bodyListAllCatalog = $('#bodyListAllCatalog');
        $bodyListAllCatalog.children().remove();
        data.forEach((elem) => {
            var tr = `<tr class='clickable'/>`;
            $bodyListAllCatalog.append(tr);
            var tdName = `<td class="pb-0"><i class="fa fa-book" aria-hidden="true">&nbsp;</i>${elem.id}</td>`;
            var tdType = `<td class="pb-0">${elem.typeCatalog}</td>`;
            var tdDesc = `<td class="pb-0">${elem.description}</td>`;
            var tdDtCreated = `<td class="pb-0">${elem.dtCreated}</td>`;
            $bodyListAllCatalog.children().last().append(tdName, tdType, tdDesc, tdDtCreated);
        });
    });
});

//=======================
//Executa evento no "hide" do collapse element
$('#toggleListAllCatalog').on('hide.bs.collapse', function () {
 $(this).prev().removeClass("text-center font-weight-light display-4");   
});
//=======================

// Executa evento no "show" do collapse element
$('#toggleCadProject').on('show.bs.collapse', function () {

    console.log($(this).prev());
    var webServer = "http://localhost:9080";  // server  do eclipse workspace ../service
    var urlBase   = "arcatalog";
    var endPoint  = "catalogs";
    var routePoint = `${webServer}/${urlBase}/${endPoint}`;

    // $(this).prev().addClass("text-center font-weight-light display-4");    

   $.ajax({
       url: routePoint
   }).then(function(data) {
       var $selectOptions = $('form#cadProjForm select#catalog-options');
       $selectOptions.children().remove();
       var option1 = `<option value=" ">Escolha um catálogo...</option>`;
       $selectOptions.append(option1);
       data.forEach((elem) => {
           var option = `<option value="${elem.name}">${elem.name}</option>`;
           $selectOptions.append(option);
      });
      $('.selectpicker').selectpicker('refresh');});
   
   	  $(this).prev().addClass("text-center font-weight-light display-4");
   
   }); 

//=======================
// Executa evento no "hide" do collapse element
$('#toggleCadProject').on('hide.bs.collapse', function () {
    $(this).prev().removeClass("text-center font-weight-light display-4");   
   });
//=======================


// Executa evento no "show" do collapse element
$('#toggleEvalModule').on('show.bs.collapse', function () {
    var webServer = "http://localhost:9080";
    var urlBase   = "arcatalog";
    var endPoint  = "api/codes/moduleName";
    var routePoint = `${webServer}/${urlBase}/${endPoint}`;
    
    $tbody = $("#ra-wrap-table-code-body");
    $tbody.children().remove();

   $.ajax({
          url: routePoint
   })
   .then(function(data) {
        var lines = data.split("\n");
        var lineHtml = lines.map(function(element){
            return `<pre style="margin-bottom: 0px;">${element}</pre>`;
        });
        var lineCount = 1;
        var firstLineOfCode = `<tr class="table-code-row"><td class="table-code-row-col-num cursor-pointer"></td><td class="table-code-row-col-markable cursor-pointer"></td><td class="table-code-row-col-code" style="min-width: 800px;"></td></tr>`
        var inHTML = lineHtml.reduce(function(accumulator, lineOfCode){
            return accumulator + `<tr class="table-code-row"><td class="table-code-row-col-num cursor-pointer">${lineCount++}</td><td class="table-code-row-col-markable cursor-pointer" style="min-width: 15px;"></td><td class="table-code-row-col-code" style="vertical-align: middle;">${lineOfCode}</td></tr>`
            }, firstLineOfCode );

        $tbody.append(inHTML);    
   });

 /*/===================================================================
   var request = $.ajax( routePoint
   ),
   chained =  request.then(function(data) {
        var lines = data.split("\n");
        var lineHtml = lines.map(function(element){
            return `<pre style="margin-bottom: 0px;">${element}</pre>`;
        });
        var lineCount = 1;
        var firstLineOfCode = `<tr class="table-code-row"><td class="table-code-row-col-num cursor-pointer"></td><td class="table-code-row-col-markable cursor-pointer"></td><td class="table-code-row-col-code" style="min-width: 800px;"></td></tr>`
        var inHTML = lineHtml.reduce(function(accumulator, lineOfCode){
            return accumulator + `<tr class="table-code-row"><td class="table-code-row-col-num cursor-pointer">${lineCount++}</td><td class="table-code-row-col-markable cursor-pointer" style="min-width: 15px;"></td><td class="table-code-row-col-code" style="vertical-align: middle;">${lineOfCode}</td></tr>`
            }, firstLineOfCode );

        $tbody.append(inHTML);
        return $.ajax( urlGraph );    
   });

   chained.done(function( data ) {
    // data retrieved from url2 as provided by the first request
  });

 //=================================================================*/

});

/*
Trata duplo clique na coluna numérica de texto
*/
$("#ra-wrap-table-code-body").on('click', 'tr', function(event){
   $this = $(event.target);

   if($this.is("pre")){ // clicado na linha de texto
    return;
   }

   $colMark = $this.closest("tr").find(".table-code-row-col-markable");

   if($this.hasClass('clicked')){ 
      $this.removeClass('clicked'); 
//--- code for doubleclick
       $colMark.trigger("doubleClick:end-path");
   } else {
      $this.addClass('clicked');
      setTimeout(function() { 
      if ($this.hasClass('clicked')){
          $this.removeClass('clicked'); 
//----- code for single click
          $colMark.trigger("click:start-path");
      }
    }, 500);
   }
}).on("click:start-path", function(event){
    $this = $(event.target);
    if($this.children(".arcat-start-path").length > 0){
        $this.closest("tr").find("i").remove();
        return;
    }
    $this.closest("tr").find("i").remove();
    $this.closest("tbody").find("i").remove(".arcat-start-path");
    var mark = `<i class="fas fa-map-marker-alt arcat-start-path" style="color:#007bff;" aria-hidden="true"></i>`;
    $this.append(mark);
}).on("doubleClick:end-path", function(event){
    $this = $(event.target);
    if($this.children(".arcat-end-path").length > 0){
        $this.closest("tr").find("i").remove();
        return;
    }
    $this.closest("tr").find("i").remove();
    $this.closest("tbody").find("i").remove(".arcat-end-path");
    var mark = `<i class="fas fa-flag-checkered arcat-end-path" style="color:#007bff;" aria-hidden="true"></i>`;
    $this.append(mark);
});



// Executa evento no "newCatalog"
$('#submitNewCatalog').on('click', function () {
    var webServer = "http://localhost:9080";
    var urlBase   = "arcatalog";
    var endPoint  = "";
    var routePoint = `${webServer}/${urlBase}/${endPoint}`;

   $.ajax({
       url: routePoint
   }).then(function(data) {
       var $bodyListAllCatalog = $('#bodyListAllCatalog');
       $bodyListAllCatalog.children().remove();
       data.forEach((elem) => {
           var tr1 = `<tr class='clickable'/>`;
           $bodyListAllCatalog.append(tr1);
           var tdName = `<td class="pb-0"><i class="fa fa-book" aria-hidden="true">&nbsp;</i>${elem.id}</td>`;
           var tdType = `<td class="pb-0">${elem.typeCatalog}</td>`;
           var tdDesc = `<td class="pb-0">${elem.description}</td>`;
           var tdDtCreated = `<td class="pb-0">${elem.dtCreated}</td>`;
           $bodyListAllCatalog.children().last().append(tdName, tdType, tdDesc, tdDtCreated);
       });
   });

   console.log("chamando rest api 2");
});

//===============================================================================
$('#addCatForm button.btn-submit').on('click',function (){
    var $form = $(this).closest('form');
    console.log($form);
    var formId = $form[0].id;  

    // atributo "name", cujo "value" = "endpoint" do elemento "input" do form id "#addCatForm"
    var $input = $("#" + formId +" input[name=endPoint]");
    var endPoint = $input[0].value; // ex: catalogs/catalog, catalogs/{catName}/properties
    var pathParams = endPoint.match(/\{\S+?\}/g); // ? existe pathParam no endPoint, ex.: catalogs/{catalogName}/properties

    if( pathParams != null) {
        endPoint = endPointWithPathParm(endPoint, pathParams, formId);
    }

    console.log(endPoint);
});



$('#cadProjForm button.btn-submit').on('click',function (){
    var $form = $(this).closest('form');
    console.log($form);
    var formId = $form[0].id;  

    // atributo "name", cujo "value" = "endpoint" do elemento "input" do form id "#addCatForm"
    var $input = $("#" + formId +" input[name=endPoint]");
    var endPoint = $input[0].value; // ex: catalogs/catalog, catalogs/{catName}/properties
    var pathParams = endPoint.match(/\{\S+?\}/g); // ? existe pathParam no endPoint, ex.: catalogs/{catalogName}/properties

    if( pathParams != null) {
        endPoint = endPointWithPathParm(endPoint, pathParams, formId);
    }

    console.log(endPoint);

    var webServer = "http://localhost:9080";
    var urlBase   = "arcatalog";
    var routePoint = `${webServer}/${urlBase}/${endPoint}`;

   $.ajax({
       url: routePoint
   }).then(function(data) {
       var $bodyListAllCatalog = $('#bodyListAllCatalog');
       $bodyListAllCatalog.children().remove();
       data.forEach((elem) => {
        
       });
   });
});

function endPointWithPathParm(endPoint, pathParams, formId) {
    if(pathParams == null || pathParams == undefined) return endPoint;

    var $inputPathParams = $("#" + formId + " input.pathParam");
    var $selectPathParams = $("#" + formId + " select.pathParam");

    console.log("$inputPathParams: " + $inputPathParams.length);
    console.log("$selectPathParams: " + $selectPathParams.length);

    var mapPathParams = new Map();
    for (var i = 0; i < $inputPathParams.length; i++) { 
        mapPathParams.set($inputPathParams[i].name, $inputPathParams[i].value);
    }

    for (var i = 0; i < $selectPathParams.length; i++) { 
        console.log("select name: " + $selectPathParams[i].name);
        var selectedIndex = $selectPathParams[i].selectedIndex;
        console.log("select option: " + $selectPathParams[i].options[selectedIndex].text);
        console.log("select option find : " + $($selectPathParams[i]).find('option:selected').text());
        mapPathParams.set($selectPathParams[i].name, $($selectPathParams[i]).find('option:selected').text());
    }

    endPoint = endPoint.replace(/\{(\S+?)\}/g, function(match, $1){
        return mapPathParams.get($1);
    });

    console.log("endPoint: " + endPoint);
    return endPoint;
}

function resolveEndPointTemplate(endPoint, pathParams, formId) {
    
    if(pathParams == null || pathParams == undefined) return endPoint;

    var $inputPathParams = $("#" + formId + " input.pathParam");
    var $selectPathParams = $("#" + formId + " select.pathParam");

    console.log("$inputPathParams: " + $inputPathParams.length);
    console.log("$selectPathParams: " + $selectPathParams.length);

    var mapPathParams = new Map();
    for (var i = 0; i < $inputPathParams.length; i++) { 
        mapPathParams.set($inputPathParams[i].name, $inputPathParams[i].value);
    }

    for (var i = 0; i < $selectPathParams.length; i++) { 
        console.log("select name: " + $selectPathParams[i].name);
        var selectedIndex = $selectPathParams[i].selectedIndex;
        console.log("select option: " + $selectPathParams[i].options[selectedIndex].text);
        console.log("select option find : " + $($selectPathParams[i]).find('option:selected').text());
        mapPathParams.set($selectPathParams[i].name, $($selectPathParams[i]).find('option:selected').text());
    }

    endPoint = endPoint.replace(/\{(\S+?)\}/g, function(match, $1){
        return mapPathParams.get($1);
    });

    console.log("endPoint: " + endPoint);
    return endPoint;
}

$('.input-files').on('click', function() {
    console.log($(this).attr("id"));
    var $inputFiles = $('.input-files');
    $inputFiles.attr("name","none");
    $inputFiles.each( function( index, element ){
        var id = $(element).attr("id");
        if(id == "fileUpload") {
            $($(element).siblings()[0]).text("Escolha os Arquivos");
        }
        else {
            $($(element).siblings()[0]).text("Escolha o Diretório");
        }
        // se o mesmo arquivo for escolhido, então o event "change"não funciona
        $(element).val("");
    });
    
    $(this).attr("name","files");
});

$('.input-files').on('change', function() {
    /*
     muda valor de "name" dos "input=file" para "none",  e depois move "files" 
      para o input file ativo, ou seja, aquele escolhido pelo cliente porque o usuário pode escolher
     enviar arquivos de diretórios ou files selecionados para o endPoint 
     e método recebe apenas 1 campo com o nome "files"
    */
    var $this = $(this);
    for (var i = 0; i < this.files.length; i++) {
          var x = `${this.files.length} arquivos selecionados`;
          $($this.siblings()[0]).text(x);
    }
});

$("#genSymbolTableForm").on("submit", function (event) {
    event.stopPropagation();
    event.preventDefault();

    var $form = $(this).closest('form');
    var formId = $form[0].id;  

    var $inputFiles = $form.find("input[type=file]");

    // verifica se foram selecionados arquivos ou diretório
    var hasFile = false;
    for(var i = 0; i < $inputFiles.length; i++){
        if($($inputFiles[0]).val().length > 0) {hasFile = true;}
    }

    if(!hasFile){
        var $wrap = $form.children("#ra-wrap-msgDiv-component");
        $wrap.children().remove();

        $divAlert = $('<div  id="msgDiv"  class="alert alert-danger alert-dismissible mb-15  message-genSymbolTableForm">');
        $button   = $('<button type="button" class="close fade show" data-dismiss="alert">&times;</button>');
        $paragraf = $('<p class=" m-0 text-error "><i class="fa fa fa-info-circle" aria-hidden="true">&nbsp;&nbsp;&nbsp;</i>Escolha um Arquivo ou Um Diretório</p>');
       
        $divAlert.append($button).append($paragraf);

        $wrap.append($divAlert);
        return;
    }

    // endPointTemplate deve ser especificaddo no attribute "action" do elementp "form"
    var routePointTemplate = $("#" + formId).attr("action");

    var pathParams = routePointTemplate.match(/\{\S+?\}/g); // ? existe pathParam no endPoint, ex.: catalogs/{catalogName}/properties

    var endPoint = routePointTemplate;

    if( pathParams != null) {
        endPoint = resolveEndPointTemplate(routePointTemplate, pathParams, formId);
    }

    // Base URL Path:  http://localhost:8080/arcatalog/api/symboltables
    // Base URL Path:  http://localhost:8080/arcatalog/api/catalogs
    // endPoint     :  http://localhost:8080/arcatalog/api/symboltables
    // action  (.../modules)   :  http://localhost:8080/arcatalog/api/symboltables/modules
    // parameter  (name(type)=value(frm))   :  http://localhost:8080/arcatalog/api/symboltables/modules?type=frm
    // outros exemplos:  
    //       http://localhost:8080/arcatalog/api/symboltables/variables?catalog=SEGUROS&project=CADASTRO
    // ou... http://localhost:8080/arcatalog/api/symboltables/{SEGUROS}/{CADASTRO}/variables
    // ou... http://localhost:8080/arcatalog/api/catalogs/{SEGUROS}/apps/{CADASTRO}/variables
    //  explicando retorna todas variáveis do projeto "CADASTRO" do catálogo "SEGUROS"
    // ou ...
    // http://localhost:8080/arcatalog/api/catalogs/{SEGUROS}/apps/{PESSOAS}/projs/{CADASTRO}/modules?db=true
    // ou para acessar o mesmo recurso...
    // http://localhost:8080/arcatalog/api/catalogs/modules?catalog=SEGUROS&app=PESSOAS&proj=CADASTRO&filters=db
    // recupera modules do catálogo "SEGUROS", aplicação "PESSOAS", projeto "CADASTRO", que acessem banco de dados

    // IMPORTANTE: Lembre-se de que REST é um estilo arquitetural e não um padrão

    var webServer = "http://localhost:9080";
    var routePoint = `${webServer}/${endPoint}`;    

            var submitDiv =  $form.children(".ra-wrap-submit"); //.children(".d-none").toggle();

           $(submitDiv).children().toggleClass("d-none");

            var formData = new FormData(this);
            $.ajax({
                url: routePoint,
                method: "POST",
                data: formData,
                cache: false,
                contentType: false,
                processData: false,
                enctype: 'multipart/form-data',
                timeout: 7200000 // trata timeout assim como falta de conexão internet
                }) 
//                .then(function (data) { // Verificar a diferença entre "sucess, done, then, ..."
                .done(function (data) { // Verificar a diferença entre "sucess, done, then, ..."
                    // Aqui você receberia do json um status talvez, que diz se foi feito o upload com sucesso ou não. No nosso caso vamos simular que sim.
                   // $("#msg").text("foi");
                   var $wrap = $form.children("#ra-wrap-msgDiv-component");
                   $wrap.children().remove();

                   console.log("SUCESS0");

                   console.log(data.symbols);

                   $divAlert = $('<div  id="msgDiv"  class="alert alert-success alert-dismissible mb-15  message-genSymbolTableForm">');
                   $button   = $('<button type="button" class="close fade show" data-dismiss="alert">&times;</button>');
                   $paragraf = $('<p class=" m-0 text-error ">Tabela de Símbolos Criada com Sucesso</p>');
                  
                   $divAlert.append($button).append($paragraf);

                   $wrap.append($divAlert);

                    //                   data.symbols.forEach((elem) => {
                    //                    console.log(elem);
                    //                    });
                   for (let [key, value] of Object.entries(data.symbols)) {
                    console.log(`${key}: ${value}`);
                  }

                  var $selectOptions = $('#ra-symbols-options');
                  $selectOptions.children().remove();
                  var option1 = `<option data-icon="fa fa-search" selected>Escolha um símbolo...</option>`;
                  $selectOptions.append(option1);
                  for (let [key, value] of Object.entries(data.symbols)) {
                    var option = `<option value="${key}">${key}</option>`;
                    $selectOptions.append(option);
                  }
                  $selectOptions.selectpicker('refresh');
                  $("#ra-wrap-symbols").removeClass("d-none");
                })
                .fail(function (jqXHR, status, err) {

                    var $wrap = $form.children("#ra-wrap-msgDiv-component");
                    $wrap.children().remove();

                    $divAlert = $('<div  id="msgDiv"  class="alert alert-danger alert-dismissible mb-15  message-genSymbolTableForm">');
                    $button   = $('<button type="button" class="close fade show" data-dismiss="alert">&times;</button>');
                    $paragraf = $('<p class=" m-0 text-error ">Erro na Criação da Tabela de Símbolos. Verifique se App Server está Ativo.</p>');
                   
                    $divAlert.append($button).append($paragraf);

                    $wrap.append($divAlert);

                    console.log(jqXHR.responseText);
                    console.log(status);
                    console.log(err);


//                    jqXHR.responseJSON.forEach((elem) => {
//                        console.log(elem);
//                        });
                  }) 
                  .always(function(jqXHROrData, textStatus, jqXHROrErrorThrown)
                       { $(submitDiv).children().toggleClass("d-none");
                    })               
    });
